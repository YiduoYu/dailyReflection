package com.edumind.mapper;

import com.edumind.domain.Auth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthMapper {

    // 根据学号查询学生（用于登录和注册时判断是否已存在）
    Auth selectAuthByAuthId(@Param("studentId") String studentId);

    // 插入一个新用户（注册）
    int insertAuth(Auth auth);

    Auth selectAuthByEmail(String email);

}