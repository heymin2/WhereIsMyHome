package com.ssafy.home.domain.service;

import com.ssafy.home.domain.dto.MemberDto;
import com.ssafy.home.domain.mapper.AuthMapper;
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

    public MemberDto info(int memberId) {
        return authMapper.info(memberId);
    };
}
