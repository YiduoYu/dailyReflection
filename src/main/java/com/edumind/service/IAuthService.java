package com.edumind.service;

import com.edumind.domain.Auth;

public interface IAuthService {

    Auth getAuthById(String id);

    Auth getAuthByEmail(String email);

    int insertAuth(Auth auth);
}