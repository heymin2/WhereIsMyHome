package com.ssafy.home.domain.service;

import com.ssafy.home.domain.request.AreaRequest;
import com.ssafy.home.domain.request.HouseInfoRequest;
import com.ssafy.home.domain.mapper.AreaMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaService {

    private final AreaMapper areaMapper;

    public AreaService(AreaMapper areaMapper) {
        this.areaMapper = areaMapper;
    }

    public List<AreaRequest> findSido() {
        List<String> sidoList = areaMapper.findSido();
        List<AreaRequest> result = new ArrayList<>();

        for (String sido : sidoList) {
            result.addAll(areaMapper.sidoInfo(sido));
        }
        return result;
    }

    public List<AreaRequest> findGugun(String sido){
        List<String> gugunList = areaMapper.findGugun(sido);
        List<AreaRequest> result = new ArrayList<>();

        for (String gugun : gugunList) {
            result.addAll(areaMapper.gugunInfo(sido, gugun));
        }
        return result;
    }

    public List<AreaRequest> findDong(String sido, String gugun){
        List<String> gugunList = areaMapper.findDong(sido, gugun);
        List<AreaRequest> result = new ArrayList<>();

        for (String dong : gugunList) {
            result.addAll(areaMapper.dongInfo(sido, gugun, dong));
        }
        return result;
    }

    public List<HouseInfoRequest> fingArea(String sido, String gugun, String dong) {
        return areaMapper.findArea(sido, gugun, dong);
    }
}
