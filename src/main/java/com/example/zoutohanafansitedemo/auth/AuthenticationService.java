package com.example.zoutohanafansitedemo.auth;

import com.example.zoutohanafansitedemo.entity.auth.*;
import com.example.zoutohanafansitedemo.entity.enums.UserRole;
import com.example.zoutohanafansitedemo.entity.enums.UserStatus;
import com.example.zoutohanafansitedemo.entity.user.User;
import com.example.zoutohanafansitedemo.exception.*;
import com.example.zoutohanafansitedemo.repository.AdminUserRepository;
import com.example.zoutohanafansitedemo.repository.UserRepository;
import com.example.zoutohanafansitedemo.service.GenerateSecurityKeyService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AdminUserRepository adminUserRepository;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService, PasswordEncoder passwordEncoder, UserRepository userRepository, GenerateSecurityKeyService generateSecurityKeyService, AdminUserRepository adminUserRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.adminUserRepository = adminUserRepository;
    }

    // 認証してレスポンスを返す
    public LoginResponse authentication(LoginRequest loginRequest) {
        if (loginRequest.getLoginId() == null || loginRequest.getLoginId().isEmpty()) {
            throw new LoginArgumentNotValidException("login id was empty");
        }

        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            throw new LoginArgumentNotValidException("password was empty");
        }

        // 認証
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLoginId(),
                        loginRequest.getPassword()
                )
        );

        User user = userRepository.selectUserByLoginId(loginRequest.getLoginId());
        if(user == null){
            throw new RoleEndpointMismatchException("not have role");
        }
        user.setRole(UserRole.ROLE_USER);
        if(user.getStatus() != UserStatus.ACTIVE){
            throw new AccountDisabledException("This account has been suspended");
        }

        List<String> roles = List.of(user.getRole().name());

        // JWTトークンの生成
        String token = jwtService.generateToken(loginRequest.getLoginId(), roles);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        return loginResponse;
    }

    public LoginResponse adminAuthentication(LoginRequest loginRequest){
        if (loginRequest.getLoginId() == null || loginRequest.getLoginId().isEmpty()) {
            throw new LoginArgumentNotValidException("login id was empty");
        }

        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            throw new LoginArgumentNotValidException("password was empty");
        }

        // 認証
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLoginId(),
                        loginRequest.getPassword()
                )
        );

        User user = adminUserRepository.selectAdminUserByLoginId(loginRequest.getLoginId());
        if(user == null){
            throw new RoleEndpointMismatchException("not have role");
        }
        user.setRole(UserRole.ROLE_ADMIN);

        List<String> roles = List.of(user.getRole().name());

        // JWTトークンの生成
        String token = jwtService.generateToken(loginRequest.getLoginId(), roles);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        return loginResponse;
    }

    public UserRegisterResponse registerUser(UserRegisterRequest  userRegisterRequest) {
        if(userRegisterRequest.getLoginId() == null || userRegisterRequest.getLoginId().isEmpty()){
            throw new UserRegistrationException("login id was empty");
        }

        if(userRegisterRequest.getPassword() == null || userRegisterRequest.getPassword().isEmpty()){
            throw new UserRegistrationException("password was empty");
        }

        if(userRepository.selectUserByLoginId(userRegisterRequest.getLoginId())!=null || adminUserRepository.selectAdminUserByLoginId(userRegisterRequest.getLoginId()) != null){
            throw new UserRegistrationException("login id was exist");
        }

        if(userRegisterRequest.getNickname() == null || userRegisterRequest.getNickname().isEmpty()){
            throw new UserRegistrationException("nickname was empty");
        }

//        if(userRegisterRequest.getIcon() == null){
//            throw new UserRegistrationException("icon was empty");
//        }

        if(userRegisterRequest.getAddress() == null || userRegisterRequest.getAddress().isEmpty()){
            throw new UserRegistrationException("address was empty");
        }

        Integer userBirthYear = userRegisterRequest.getBirthYear();
        int nowYear = LocalDate.now().getYear();

        if(userBirthYear == null){
            throw new UserRegistrationException("birth year was null");
        }

        if(userBirthYear < 1930 || userBirthYear > nowYear){
            throw new UserRegistrationException("birth year was out of range");
        }

        if(userRegisterRequest.getGender() == null){
            throw new UserRegistrationException("gender was empty");
        }

        String hashedPassword = passwordEncoder.encode(userRegisterRequest.getPassword());
        String securityKey = GenerateSecurityKeyService.generateSecurityKey();
        String hashedSecurityKey = passwordEncoder.encode(securityKey);

        User user = new User();
        user.setLoginId(userRegisterRequest.getLoginId());
        user.setPassword(hashedPassword);
        user.setNickname(userRegisterRequest.getNickname());
        user.setSelfIntroduction(userRegisterRequest.getSelfIntroduction());
        user.setAddress(userRegisterRequest.getAddress());
        user.setGender(userRegisterRequest.getGender());
        user.setBirthYear(userRegisterRequest.getBirthYear());
        user.setSecurityKey(hashedSecurityKey);

        userRepository.insertUser(user);

        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setLoginId(user.getLoginId());
        userRegisterResponse.setSecurityKey(securityKey);

        return userRegisterResponse;
    }

    public UserRegisterResponse passwordReset(PasswordResetRequest passwordResetRequest) {
        User user = userRepository.selectUserByLoginId(passwordResetRequest.getLoginId());
        if(user == null){
            throw new InvalidPasswordResetException("loginId or securityKey was invalid");
        }

        if(!passwordEncoder.matches(passwordResetRequest.getSecurityKey(), user.getSecurityKey())){
            throw new InvalidPasswordResetException("loginId or securityKey was invalid");
        }

        String hashedPassword = passwordEncoder.encode(passwordResetRequest.getPassword());

        String newSecurityKey = GenerateSecurityKeyService.generateSecurityKey();
        String hashedSecurityKey = passwordEncoder.encode(newSecurityKey);
        userRepository.updatePassword(hashedPassword, hashedSecurityKey, user.getLoginId());

        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setLoginId(user.getLoginId());
        userRegisterResponse.setSecurityKey(newSecurityKey);

        return  userRegisterResponse;
    }
}
