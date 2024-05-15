package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {

    MemberDto login(MemberDto memberDto);
    String findByPass(String id, String name);
    MemberDto info(int memberId);
}
