package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.dto.HouseInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AreaMapper {

    List<String> findSido();
    List<String> findGugun(String sido);
    List<String> findDong(String sido, String gugun);
    List<HouseInfoDto> findArea(@Param("sido") String sido, @Param("gugun") String gugun, @Param("dong") String dong);
}
