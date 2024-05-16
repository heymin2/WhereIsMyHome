package com.ssafy.home.domain.service;

import com.ssafy.home.domain.dto.AddressInfoDto;
import com.ssafy.home.domain.dto.HouseInfoDto;
import com.ssafy.home.domain.dto.HouseRequestDto;
import com.ssafy.home.domain.mapper.HouseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    private final HouseMapper houseMapper;

    public HouseService(HouseMapper houseMapper) {
        this.houseMapper = houseMapper;
    }

    public List<HouseInfoDto> houseList(HouseRequestDto houseRequestDto) {
        return houseMapper.houseList(houseRequestDto);
    }

    public List<AddressInfoDto> dongList(HouseRequestDto houseRequestDto) {
        return houseMapper.dongList(houseRequestDto);
    }
}