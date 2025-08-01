package com.edumind.controller;

import com.edumind.common.AjaxResult;
import com.edumind.domain.Auth;
import com.edumind.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//负责用户的注册、登录等“身份验证相关”的功能。

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IAuthService studentService;

    /**
     * 注册接口：根据 id + name 注册，如果已存在则报错
     */
    @PostMapping("/register")
    public AjaxResult register(@RequestBody Auth student) {
        // 判断是否存在该 studentId
        Auth existing = studentService.getAuthById(student.getStudentId());
        if (existing != null) {
            return AjaxResult.error("该学号已注册，请直接登录");
        }

        int rows = studentService.insertAuth(student);
        if (rows > 0) {
            return AjaxResult.success("注册成功");
        } else {
            return AjaxResult.error("注册失败，请重试");
        }
    }

    /**
     * 登录接口：根据 id + name 判断是否存在，成功就返回 success，否则失败
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody Auth student) {
        Auth dbStudent = studentService.getAuthById(student.getStudentId());

        if (dbStudent == null) {
            return AjaxResult.error("未找到该学号，请先注册");
        }

        if (!dbStudent.getName().equals(student.getName())) {
            return AjaxResult.error("姓名与学号不匹配");
        }

        return AjaxResult.success("登录成功");
    }
}


