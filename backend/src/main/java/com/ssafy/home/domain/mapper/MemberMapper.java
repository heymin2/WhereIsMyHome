package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    void signUp(MemberDto memberDto);
    int getMemberById(String member_id);
    int getMemberByEmail(String member_id);
    int updateMember(MemberDto memberDto);
}
