package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.dto.AddressInfoDto;
import com.ssafy.home.domain.request.HouseInfoRequest;
import com.ssafy.home.domain.dto.CoordinateRangeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HouseMapper {

    List<HouseInfoRequest> houseList(CoordinateRangeDTO coordinateRangeDTO);
    List<AddressInfoDto> dongList(CoordinateRangeDTO coordinateRangeDTO);
}