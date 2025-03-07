package com.ssafy.home.domain.service;

import com.ssafy.home.domain.dto.LoginDto;
import com.ssafy.home.domain.dto.MemberDto;
import com.ssafy.home.domain.mapper.AuthMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthMapper authMapper;

    public AuthService(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    public LoginDto login(String id, String pass){
        return authMapper.login(id, pass);
    }

    public LoginDto info(int memberId) {
        return authMapper.info(memberId);
    };
}
