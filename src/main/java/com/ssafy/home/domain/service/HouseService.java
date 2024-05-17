package com.ssafy.home.domain.service;

import com.ssafy.home.domain.dto.AddressInfoDto;
import com.ssafy.home.domain.request.HouseInfoRequest;
import com.ssafy.home.domain.dto.CoordinateRangeDTO;
import com.ssafy.home.domain.mapper.HouseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    private final HouseMapper houseMapper;

    public HouseService(HouseMapper houseMapper) {
        this.houseMapper = houseMapper;
    }

    public List<HouseInfoRequest> houseList(CoordinateRangeDTO coordinateRangeDTO) {
        return houseMapper.houseList(coordinateRangeDTO);
    }

    public List<AddressInfoDto> dongList(CoordinateRangeDTO coordinateRangeDTO) {
        return houseMapper.dongList(coordinateRangeDTO);
    }
}