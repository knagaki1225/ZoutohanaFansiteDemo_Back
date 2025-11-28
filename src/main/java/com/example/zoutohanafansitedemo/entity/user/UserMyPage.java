package com.example.zoutohanafansitedemo.entity.user;

import lombok.Data;

@Data
public class UserMyPage {
    private String loginId;
    private String nickname;
    private String selfIntroduction;

    public UserMyPage(String loginId, String selfIntroduction, String nickname) {
        this.loginId = loginId;
        this.selfIntroduction = selfIntroduction;
        this.nickname = nickname;
    }
}
