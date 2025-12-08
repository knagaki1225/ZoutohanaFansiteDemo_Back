package com.example.zoutohanafansitedemo.service;

import com.example.zoutohanafansitedemo.entity.info.PaginationInfo;
import com.example.zoutohanafansitedemo.entity.info.PaginationView;
import com.example.zoutohanafansitedemo.exception.InvalidPaginationException;
import org.springframework.stereotype.Service;

@Service
public class PaginationService {
    /**
     * ペジネーションの情報を取得する関数
     *
     * @param page     何ページ目の情報を取得するか
     * @param listSize      対象情報の全件数
     * @param loopNum 繰り返し回数(ページネーションで表示する件数)
     * @return PaginationView(paginationInfo, startNum, endNum) startNum -> ループの開始添え字, endNum -> ループの終了添え字
     */
    public PaginationView getPaginationView(int page, int listSize, int loopNum){
        if(page < 1){
            throw new InvalidPaginationException("Invalid page number");
        }

        int pageSize = listSize / loopNum;
        if(listSize % loopNum != 0){
            pageSize++;
        }

        if(page > pageSize){
            page = pageSize;
        }

        int startNum = (page - 1) * loopNum;
        int endNum = Math.min(startNum + loopNum, listSize);

        return new PaginationView(new PaginationInfo(page, pageSize), startNum, endNum);
    }
}