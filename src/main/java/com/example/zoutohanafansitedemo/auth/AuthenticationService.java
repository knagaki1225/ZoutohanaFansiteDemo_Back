package com.example.zoutohanafansitedemo.auth;

import com.example.zoutohanafansitedemo.entity.auth.LoginRequest;
import com.example.zoutohanafansitedemo.entity.auth.LoginResponse;
import com.example.zoutohanafansitedemo.entity.auth.UserRegisterRequest;
import com.example.zoutohanafansitedemo.entity.auth.UserRegisterResponse;
import com.example.zoutohanafansitedemo.entity.user.User;
import com.example.zoutohanafansitedemo.repository.UserRepository;
import com.example.zoutohanafansitedemo.service.GenerateSecurityKeyService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService, PasswordEncoder passwordEncoder, UserRepository userRepository, GenerateSecurityKeyService generateSecurityKeyService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    // 認証してレスポンスを返す
    public LoginResponse authentication(LoginRequest loginRequest) {
        if (loginRequest.getLoginId() == null || loginRequest.getLoginId().isEmpty()) {
//            throw new LoginArgumentNotValidException("username is empty");
        }

        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
//            throw new LoginArgumentNotValidException("password is empty");
        }

        // 認証
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLoginId(),
                        loginRequest.getPassword()
                )
        );

        // JWTトークンの生成
        String token = jwtService.generateToken(loginRequest.getLoginId());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        return loginResponse;
    }

    public UserRegisterResponse registerUser(UserRegisterRequest  userRegisterRequest) {
        if(userRegisterRequest.getLoginId() == null || userRegisterRequest.getLoginId().isEmpty()){
//            throw new UserRegistrationException("username is empty");
        }

        if(userRegisterRequest.getPassword() == null || userRegisterRequest.getPassword().isEmpty()){
//            throw new UserRegistrationException("password is empty");
        }

        if(userRepository.selectUserByLoginId(userRegisterRequest.getLoginId())!=null){
//            throw new UserRegistrationException("username is exist");
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
}
