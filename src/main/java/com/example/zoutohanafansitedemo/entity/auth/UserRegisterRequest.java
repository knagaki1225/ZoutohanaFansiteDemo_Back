package com.example.zoutohanafansitedemo.entity.auth;

import com.example.zoutohanafansitedemo.entity.enums.UserGender;
import lombok.Data;

@Data
public class UserRegisterRequest {
    private String loginId;
    private String password;
    private String nickname;
    private String selfIntroduction;
    private Integer icon;
    private String address;
    private Integer birthYear;
    private UserGender gender;
}
