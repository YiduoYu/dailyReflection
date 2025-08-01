package com.edumind.service.Impl;

import com.edumind.domain.Auth;
import com.edumind.mapper.AuthMapper;

import com.edumind.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private AuthMapper AuthMapper;

    @Autowired
    private Validator validator;

    @Override
    public Auth getAuthById(String id) {
        return AuthMapper.selectAuthByAuthId(id);
    }

    @Override
    public Auth getAuthByEmail(String email) {
        return AuthMapper.selectAuthByEmail(email);
    }

    @Override
    public int insertAuth(Auth auth) {
        Set<ConstraintViolation<Auth>> violations = validator.validate(auth);
        if (!violations.isEmpty()) {
            violations.forEach(v -> System.out.println("Validation error: " + v.getMessage()));
            return 0;
        }

        if (AuthMapper.selectAuthByEmail(auth.getEmail()) != null) {
            System.out.println("Email already exists: " + auth.getEmail());
            return 0;
        }

        auth.setCreateTime(LocalDateTime.now());
        return AuthMapper.insertAuth(auth);
    }
}
