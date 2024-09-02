package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.dto.AddressInfoDto;
import com.ssafy.home.domain.dto.BusDto;
import com.ssafy.home.domain.dto.HouseInfoDto;
import com.ssafy.home.domain.dto.StoreInfoDto;
import com.ssafy.home.domain.request.CoordinateRangeRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HouseMapper {

    List<HouseInfoDto> houseList(CoordinateRangeRequest request);
    List<AddressInfoDto> dongList(CoordinateRangeRequest request);
    List<BusDto> busList(CoordinateRangeRequest request);
    List<StoreInfoDto> foodList(CoordinateRangeRequest request);
    List<StoreInfoDto> cafeList(CoordinateRangeRequest request);
}