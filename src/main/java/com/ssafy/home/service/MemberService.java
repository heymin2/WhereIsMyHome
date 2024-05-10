package com.ssafy.home.service;

import com.ssafy.home.dto.MemberDto;
import com.ssafy.home.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Transactional
    public void signUp(MemberDto memberDto) throws Exception {
        if(getMemberById(memberDto.getId()) >= 1) {
            throw new Exception();
        }
        memberMapper.signUp(memberDto);
    }

    @Transactional
    public int updateMember(MemberDto memberDto){
        return memberMapper.updateMember(memberDto);
    }

    public MemberDto login(MemberDto memberDto){
        return memberMapper.login(memberDto);
    }

    public int getMemberById(String member_id) {
        return memberMapper.getMemberById(member_id);
    }

    public String findByPass(String id, String name) {
        return memberMapper.findByPass(id, name);
    }
}
