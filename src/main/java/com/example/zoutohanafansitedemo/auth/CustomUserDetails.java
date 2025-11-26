package com.example.zoutohanafansitedemo.auth;

import com.example.zoutohanafansitedemo.entity.enums.UserGender;
import com.example.zoutohanafansitedemo.entity.enums.UserRole;
import com.example.zoutohanafansitedemo.entity.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final User user;
    private final UserRole role;

    public CustomUserDetails(User user, UserRole role) {
        this.user = user;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLoginId();
    }

    public long getId() {
        return user.getId();
    }

    public String getNickname() {
        return user.getNickname();
    }

    public String getAddress() {
        return user.getAddress();
    }

    public int getBirthYear() {
        return user.getBirthYear();
    }

    public UserGender getGender() {
        return user.getGender();
    }

    public String getSelfIntroduction(){
        return user.getSelfIntroduction();
    }
}
