package com.example.zoutohanafansitedemo.repository;

import com.example.zoutohanafansitedemo.entity.user.User;
import com.example.zoutohanafansitedemo.mapper.AdminUserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AdminUserRepository {
    private final AdminUserMapper adminUserMapper;

    public AdminUserRepository(AdminUserMapper adminUserMapper) {
        this.adminUserMapper = adminUserMapper;
    }

    public User selectAdminUserByLoginId(String loginId){
        return adminUserMapper.selectAdminUserByLoginId(loginId);
    }
}
