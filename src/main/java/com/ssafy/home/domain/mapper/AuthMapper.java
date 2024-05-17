package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthMapper {

    MemberDto login(@Param("id") String id, @Param("pw") String pass);
    String findByPass(@Param("id") String id, @Param("name") String name);
    String findByEmail(@Param("id") String id, @Param("name") String name);
    MemberDto info(int memberId);
}
