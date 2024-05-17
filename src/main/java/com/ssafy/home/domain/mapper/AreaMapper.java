package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.request.HouseInfoRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AreaMapper {

    List<String> findSido();
    List<String> findGugun(String sido);
    List<String> findDong(@Param("sido") String sido, @Param("gugun") String gugun);
    List<HouseInfoRequest> findArea(@Param("sido") String sido, @Param("gugun") String gugun, @Param("dong") String dong);
}
