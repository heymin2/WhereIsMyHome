package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.request.AreaRequest;
import com.ssafy.home.domain.request.HouseInfoRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AreaMapper {

    List<String> findSido();
    List<AreaRequest> sidoInfo(String sido);
    List<String> findGugun(String sido);
    List<AreaRequest> gugunInfo(@Param("sido") String sido, @Param("gugun") String gugun);
    List<String> findDong(@Param("sido") String sido, @Param("gugun") String gugun);
    List<AreaRequest> dongInfo(@Param("sido") String sido, @Param("gugun") String gugun, @Param("dong") String dong);
    List<HouseInfoRequest> findArea(@Param("sido") String sido, @Param("gugun") String gugun, @Param("dong") String dong);
}
