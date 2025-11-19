package com.example.zoutohanafansitedemo.repository;

import com.example.zoutohanafansitedemo.entity.user.User;
import com.example.zoutohanafansitedemo.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private UserMapper userMapper;

    public UserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User selectUserByLoginId(String userId){
        return userMapper.getUserByLoginId(userId);
    }

    public void insertUser(User user){
        userMapper.insertUser(user);
    }
}
