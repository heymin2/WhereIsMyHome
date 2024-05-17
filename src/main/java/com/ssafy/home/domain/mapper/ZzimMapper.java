package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.dto.ZzimDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZzimMapper {

     void likeApt(@Param("member_id") int memberId, @Param("apt_id") int aptId);
     int check(@Param("member_id") int memberId, @Param("apt_id") int aptId);
     int delete(@Param("member_id") int memberId, @Param("apt_id") int aptId);
     List<ZzimDto> search(@Param("member_id") int memberId);
     int aptCnt(@Param("apt_id") int aptId);
}
