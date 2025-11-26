package com.example.zoutohanafansitedemo.mapper;

import com.example.zoutohanafansitedemo.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminUserMapper {
    @Select("SELECT id, login_id, nickname, password FROM admin_users WHERE login_id = #{loginId}")
    User selectAdminUserByLoginId(String loginId);
}
