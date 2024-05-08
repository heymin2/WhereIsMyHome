package com.ssafy.home.service;

import com.ssafy.home.entity.Member;
import com.ssafy.home.mapper.MemberMapper;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public Member login(Member member){
        return memberMapper.login(member);
    }

    public Member getMemberById(String member_id) {
        return memberMapper.getMemberById(member_id);
    }

    public int updateMember(Member member){
        return memberMapper.updateMember(member);
    }
}
