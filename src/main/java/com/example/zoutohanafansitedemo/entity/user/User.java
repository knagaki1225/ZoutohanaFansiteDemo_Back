package com.example.zoutohanafansitedemo.entity.user;

import com.example.zoutohanafansitedemo.entity.enums.UserGender;
import com.example.zoutohanafansitedemo.entity.enums.UserRole;
import com.example.zoutohanafansitedemo.entity.enums.UserStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private UserRole role;
    private long id;
    private String loginId;
    private String nickname;
    private String password;
    private String selfIntroduction;
    private String address;
    private int birthYear;
    private UserGender gender;
    private String securityKey;
    private UserStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDelete;
}


