package com.ssafy.home.mapper;

import com.ssafy.home.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {

    MemberDto login(MemberDto memberDto);
    String findByPass(String id, String name);
}
