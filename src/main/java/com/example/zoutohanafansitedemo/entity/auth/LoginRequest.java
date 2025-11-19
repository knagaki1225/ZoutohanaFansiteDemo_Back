package com.example.zoutohanafansitedemo.entity.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String loginId;
    private String password;
}
