package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.dto.*;
import com.ssafy.home.domain.request.CoordinateRangeRequest;
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
    HouseDto aptInfo(@Param("apt_id") int aptId, @Param("zzim") boolean zzim);
    List<HouseDealDto> aptDeal(int aptId);
    HouseCoordDto aptCoord(int aptId);
    List<CafeDto> aptCafe(CoordinateRangeRequest request);
}
