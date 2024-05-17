package com.ssafy.home.domain.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ZzimMapper {

     void likeApt(@Param("member_id") int memberId, @Param("apt_id") int aptId);
     int check(@Param("member_id") int memberId, @Param("apt_id") int aptId);
}
