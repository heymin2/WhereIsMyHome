package com.ssafy.home.mapper;

import com.ssafy.home.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public Member login(Member member);
    public Member getMemberById(String member_id);
    public int updateMember(Member member);
}
