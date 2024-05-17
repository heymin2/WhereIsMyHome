package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.dto.AreaInfoDto;
import com.ssafy.home.domain.dto.HouseInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AreaMapper {

    List<String> findSido();
    List<AreaInfoDto> sidoInfo(String sido);
    List<String> findGugun(String sido);
    List<AreaInfoDto> gugunInfo(@Param("sido") String sido, @Param("gugun") String gugun);
    List<String> findDong(@Param("sido") String sido, @Param("gugun") String gugun);
    List<AreaInfoDto> dongInfo(@Param("sido") String sido, @Param("gugun") String gugun, @Param("dong") String dong);
    List<HouseInfoDto> findArea(@Param("sido") String sido, @Param("gugun") String gugun, @Param("dong") String dong);
}
