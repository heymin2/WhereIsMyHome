package com.ssafy.home.service;

import com.ssafy.home.dto.MemberDto;
import com.ssafy.home.mapper.AuthMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthMapper authMapper;

    public AuthService(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    public MemberDto login(MemberDto memberDto){
        return authMapper.login(memberDto);
    }

    public String findByPass(String id, String name) {
        return authMapper.findByPass(id, name);
    }
}
