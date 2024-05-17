package com.ssafy.home.domain.service;

import com.ssafy.home.domain.dto.AddressInfoDto;
import com.ssafy.home.domain.dto.HouseInfoDto;
import com.ssafy.home.domain.request.CoordinateRangeRequest;
import com.ssafy.home.domain.mapper.HouseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    private final HouseMapper houseMapper;

    public HouseService(HouseMapper houseMapper) {
        this.houseMapper = houseMapper;
    }

    public List<HouseInfoDto> houseList(CoordinateRangeRequest coordinateRangeRequest) {
        return houseMapper.houseList(coordinateRangeRequest);
    }

    public List<AddressInfoDto> dongList(CoordinateRangeRequest coordinateRangeRequest) {
        return houseMapper.dongList(coordinateRangeRequest);
    }
}