package com.ssafy.home.domain.mapper;

import com.ssafy.home.domain.dto.HouseInfoDto;
import com.ssafy.home.domain.dto.HouseRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HouseMapper {

    List<HouseInfoDto> houseList(HouseRequestDto houseRequestDto);
}
