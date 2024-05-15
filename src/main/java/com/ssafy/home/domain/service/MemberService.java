package com.ssafy.home.domain.service;

import com.ssafy.home.domain.dto.MemberDto;
import com.ssafy.home.domain.mapper.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public int getMemberById(String member_id) {
        return memberMapper.getMemberById(member_id);
    }

    @Transactional
    public int updateMember(MemberDto memberDto){
        return memberMapper.updateMember(memberDto);
    }
}
