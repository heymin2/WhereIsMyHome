package com.ssafy.home.domain.service;

import com.ssafy.home.domain.dto.LoginDto;
import com.ssafy.home.domain.dto.MemberDto;
import com.ssafy.home.domain.mapper.AuthMapper;
import com.ssafy.home.domain.mapper.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final AuthMapper authMapper;

    public MemberService(MemberMapper memberMapper, AuthMapper authMapper) {
        this.memberMapper = memberMapper;
        this.authMapper = authMapper;
    }

    @Transactional
    public void signUp(MemberDto memberDto) throws Exception {
        if(getMemberById(memberDto.getId()) >= 1) {
            throw new Exception();
        }
        memberMapper.signUp(memberDto);
    }

    public int getMemberById(String id) {
        return memberMapper.getMemberById(id);
    }

    public int getMemberByEmail(String email) {
        return memberMapper.getMemberByEmail(email);
    }

    @Transactional
    public LoginDto updateMember(MemberDto memberDto){
        System.out.println(memberDto);
        if(memberMapper.updateMember(memberDto) == 1){
            return authMapper.info(memberDto.getMemberId());
        }
        return null;
    }
}
