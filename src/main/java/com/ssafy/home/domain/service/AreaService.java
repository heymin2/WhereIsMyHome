package com.ssafy.home.domain.service;

import com.ssafy.home.domain.mapper.AreaMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {

    AreaMapper areaMapper;

    public AreaService(AreaMapper areaMapper) {
        this.areaMapper = areaMapper;
    }

    public List<String> findSido(){
        return areaMapper.findSido();
    };
    public List<String> findGugun(String sido){
        return areaMapper.findGugun(sido);
    }

    public List<String> findDong(String gugun){
        return areaMapper.findDong(gugun);
    }
}
