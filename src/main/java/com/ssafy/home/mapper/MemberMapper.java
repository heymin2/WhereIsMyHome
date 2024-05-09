package com.ssafy.home.mapper;

import com.ssafy.home.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberDto login(MemberDto memberDto);
    int getMemberById(String member_id);
    int updateMember(MemberDto memberDto);
    void signUp(MemberDto memberDto);
    String findByPass(String id, String name);
}
