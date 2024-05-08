package com.ssafy.home.mapper;

import com.ssafy.home.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public MemberDto login(MemberDto memberDto);
    public MemberDto getMemberById(String member_id);
    public int updateMember(MemberDto memberDto);
    public void signUp(MemberDto memberDto);
}
