package com.example.zoutohanafansitedemo.service;

import com.example.zoutohanafansitedemo.entity.enums.ProjectStatus;
import com.example.zoutohanafansitedemo.entity.info.PaginationInfo;
import com.example.zoutohanafansitedemo.entity.info.PaginationView;
import com.example.zoutohanafansitedemo.entity.project.*;
import com.example.zoutohanafansitedemo.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import java.io.InputStream;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final PaginationService paginationService;
    private final ImageService imageService;

    public ProjectService(ProjectRepository projectRepository, PaginationService paginationService, ImageService imageService) {
        this.projectRepository = projectRepository;
        this.paginationService = paginationService;
        this.imageService = imageService;
    }

    public List<Project> getAll() {
        return projectRepository.selectAll();
    }

    public Project findById(long id) {
        return projectRepository.findById(id);
    }

    public Project selectById(long id) {
        return projectRepository.selectById(id);
    }

    public List<Project> getFourEndProject(){
        return projectRepository.selectFourEndProjects();
    }

    public List<Project> getProgressProjects(){
        return projectRepository.selectProgressProjects();
    }

    public ProjectPagination getProjectPagination(int page){

        List<Project> projects = projectRepository.selectEndProjects();
        List<ProjectList> projectLists = new ArrayList<>();


        PaginationView paginationView = paginationService.getPaginationView(page, projects.size(), 5);

        PaginationInfo paginationInfo = paginationView.getPaginationInfo();

        for(int i = paginationView.getStartNum(); i < paginationView.getEndNum(); i++){
            Project project = projects.get(i);
            projectLists.add(new ProjectList(project.getId(), project.getUrlKey(), project.getName(), project.getProjectStartAt(), project.getProjectEndAt(), project.getIntroduction()));
        }

        return new ProjectPagination(paginationInfo, projectLists);
    }

    public Project insert(ProjectRegisterRequest projectRegisterRequest) {
        String imageName;
        try {
            imageName = imageService.saveImage(projectRegisterRequest.getMainImg());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Project newProject = new Project();
        newProject.setName(projectRegisterRequest.getName());
        newProject.setUrlKey(projectRegisterRequest.getUrlKey());
        newProject.setIntroduction(projectRegisterRequest.getIntroduction());
        newProject.setMainImgUrl(imageName);
        newProject.setThemeColor(projectRegisterRequest.getThemeColor());
        newProject.setStatus(ProjectStatus.BEFORE_SUBMISSION);
        newProject.setEnableVisibleBookTitle(projectRegisterRequest.isEnableVisibleBookTitle());
        newProject.setEnableVisibleReviewTitle(projectRegisterRequest.isEnableVisibleReviewTitle());
        newProject.setEnableVisibleUserInfo(projectRegisterRequest.isEnableVisibleUserInfo());
        newProject.setPublished(projectRegisterRequest.isPublished());
        newProject.setProjectStartAt(projectRegisterRequest.getProjectStartAt());
        newProject.setProjectEndAt(projectRegisterRequest.getProjectEndAt());
        newProject.setSubmissionStartAt(projectRegisterRequest.getSubmissionStartAt());
        newProject.setSubmissionEndAt(projectRegisterRequest.getSubmissionEndAt());
        newProject.setVotingStartAt(projectRegisterRequest.getVotingStartAt());
        newProject.setVotingEndAt(projectRegisterRequest.getVotingEndAt());

        try {
            String projectUrl = "http://localhost:8080/projects/" + newProject.getUrlKey(); // 企画ページのパスに後で修正
            byte[] qrBytes = generateQRCodeImage(projectUrl, 300, 300);
            MultipartFile qrFile = new ByteArrayMultipartFile(qrBytes, "qr.png", "image/png");
            String qrFileName = imageService.saveImage(qrFile);
            newProject.setQrImgUrl(qrFileName);
        } catch (Exception e) {
            throw new RuntimeException("QRコード生成に失敗しました: " + e.getMessage(), e);
        }

        projectRepository.insert(newProject);
        return newProject;
    }

    private byte[] generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "png", baos);
            return baos.toByteArray();
        }
    }

    private static class ByteArrayMultipartFile implements MultipartFile {
        private final byte[] bytes;
        private final String name;
        private final String contentType;

        public ByteArrayMultipartFile(byte[] bytes, String name, String contentType) {
            this.bytes = bytes;
            this.name = name;
            this.contentType = contentType;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getOriginalFilename() {
            return name;
        }

        @Override
        public String getContentType() {
            return contentType;
        }

        @Override
        public boolean isEmpty() {
            return bytes == null || bytes.length == 0;
        }

        @Override
        public long getSize() {
            return bytes.length;
        }

        @Override
        public byte[] getBytes() throws IOException {
            return bytes;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(bytes);
        }

        public void traznsferTo(java.nio.file.Path dest) throws IOException, IllegalStateException {
            java.nio.file.Files.write(dest, bytes);
        }

        @Override
        public void transferTo(java.io.File dest) throws IOException, IllegalStateException {
            java.nio.file.Files.write(dest.toPath(), bytes);
        }
    }

    public List<ProjectMyPage> getProjectMyPage(){
        List<ProjectMyPage> projectMyPages = new ArrayList<>();
        List<Project> progressProjects = getProgressProjects();

        for(Project p:progressProjects) {
            ProjectMyPage projectMyPage = new ProjectMyPage();
            projectMyPage.setId(p.getId());
            projectMyPage.setName(p.getName());
            projectMyPage.setProjectStatus(p.getStatus());

            LocalDateTime today =  LocalDateTime.now();
            Integer finishDate = null;

            switch (p.getStatus()) {
                case BEFORE_SUBMISSION:
                    finishDate = (int) ChronoUnit.DAYS.between(today, p.getSubmissionStartAt()) + 1;
                    break;
                case DURING_SUBMISSION:
                    finishDate = (int) ChronoUnit.DAYS.between(today, p.getSubmissionEndAt()) + 1;
                    break;
                case FIRST_PHASE:
                    if(p.getVotingStartAt() != null){
                        finishDate = (int) ChronoUnit.DAYS.between(today, p.getVotingStartAt()) + 1;
                    }
                    break;
                case SECOND_PHASE_VOTING:
                    finishDate = (int) ChronoUnit.DAYS.between(today, p.getVotingEndAt()) + 1;
                    break;
            }
            projectMyPage.setLastDate(finishDate);

            projectMyPages.add(projectMyPage);
        }

        return projectMyPages;
    }
}
