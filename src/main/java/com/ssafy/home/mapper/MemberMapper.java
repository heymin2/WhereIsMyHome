package com.ssafy.home.mapper;

import com.ssafy.home.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public MemberDto login(MemberDto memberDto);
    public int getMemberById(String member_id);
    public int updateMember(MemberDto memberDto);
    public void signUp(MemberDto memberDto);
    public String findByPass(String id, String name);
}
