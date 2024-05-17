package com.ssafy.home.domain.service;

import com.ssafy.home.domain.request.HouseInfoRequest;
import com.ssafy.home.domain.mapper.AreaMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {

    private final AreaMapper areaMapper;

    public AreaService(AreaMapper areaMapper) {
        this.areaMapper = areaMapper;
    }

    public List<String> findSido(){
        return areaMapper.findSido();
    };
    public List<String> findGugun(String sido){
        return areaMapper.findGugun(sido);
    }

    public List<String> findDong(String sido, String gugun){
        return areaMapper.findDong(sido, gugun);
    }

    public List<HouseInfoRequest> fingArea(String sido, String gugun, String dong) {
        return areaMapper.findArea(sido, gugun, dong);
    }
}
