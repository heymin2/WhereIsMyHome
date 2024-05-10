package com.ssafy.home.mapper;

import com.ssafy.home.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    void signUp(MemberDto memberDto);
    int getMemberById(String member_id);
    int updateMember(MemberDto memberDto);
}
