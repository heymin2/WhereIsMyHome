package com.ssafy.home.domain.service;

import com.ssafy.home.domain.dto.AreaInfoDto;
import com.ssafy.home.domain.dto.HouseDealDto;
import com.ssafy.home.domain.dto.HouseDto;
import com.ssafy.home.domain.dto.HouseInfoDto;
import com.ssafy.home.domain.mapper.AreaMapper;
import com.ssafy.home.domain.mapper.ZzimMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaService {

    private final AreaMapper areaMapper;
    private final ZzimMapper zzimMapper;

    public AreaService(AreaMapper areaMapper, ZzimMapper zzimMapper) {
        this.areaMapper = areaMapper;
        this.zzimMapper = zzimMapper;
    }

    public List<AreaInfoDto> findSido() {
        List<String> sidoList = areaMapper.findSido();
        List<AreaInfoDto> result = new ArrayList<>();

        for (String sido : sidoList) {
            result.addAll(areaMapper.sidoInfo(sido));
        }
        return result;
    }

    public List<AreaInfoDto> findGugun(String sido){
        List<String> gugunList = areaMapper.findGugun(sido);
        List<AreaInfoDto> result = new ArrayList<>();

        for (String gugun : gugunList) {
            result.addAll(areaMapper.gugunInfo(sido, gugun));
        }
        return result;
    }

    public List<AreaInfoDto> findDong(String sido, String gugun){
        List<String> gugunList = areaMapper.findDong(sido, gugun);
        List<AreaInfoDto> result = new ArrayList<>();

        for (String dong : gugunList) {
            result.addAll(areaMapper.dongInfo(sido, gugun, dong));
        }
        return result;
    }

    public List<HouseInfoDto> fingArea(String sido, String gugun, String dong) {
        return areaMapper.findArea(sido, gugun, dong);
    }

    public HouseDto aptInfo(int aptId, Object memberId) {
        boolean zzim = false;
        if(memberId != null){
            int cnt = zzimMapper.check((int) memberId, aptId);

            if (cnt == 1) {
                zzim = true;
            }
        }
        return areaMapper.aptInfo(aptId, zzim);
    }

    public List<HouseDealDto> aptDeal(int aptId) {
        return areaMapper.aptDeal(aptId);
    }
}
