package com.example.zoutohanafansitedemo.entity.auth;

import lombok.Data;

@Data
public class PasswordResetRequest {
    private String loginId;
    private String securityKey;
    private String password;
}
