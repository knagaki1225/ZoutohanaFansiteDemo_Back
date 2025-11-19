package com.example.zoutohanafansitedemo.mapper;

import com.example.zoutohanafansitedemo.entity.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE login_id = #{loginId}")
    User getUserByLoginId(String loginId);

    @Insert("""
            INSERT INTO
                users(login_id, nickname, password, self_introduction, address, birth_year, gender, security_key) 
                VALUES (#{loginId}, #{nickname}, #{password}, #{selfIntroduction}, #{address}, #{birthYear}, #{gender}, #{securityKey})
            """)
    void insertUser(User user);
}
