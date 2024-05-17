package com.ssafy.home.domain.service;

import com.ssafy.home.domain.dto.AddressInfoDto;
import com.ssafy.home.domain.dto.BusDto;
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

    public List<HouseInfoDto> houseList(CoordinateRangeRequest request) {
        return houseMapper.houseList(request);
    }

    public List<AddressInfoDto> dongList(CoordinateRangeRequest request) {
        return houseMapper.dongList(request);
    }

    public List<BusDto> busList(CoordinateRangeRequest request) {
        return houseMapper.busList(request);
    }
}