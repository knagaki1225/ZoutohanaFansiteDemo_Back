package com.example.zoutohanafansitedemo.entity.auth;

import lombok.Data;

@Data
public class UserRegisterResponse {
    private String loginId;
    private String securityKey;
}
