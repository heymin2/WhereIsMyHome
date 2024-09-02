package com.ssafy.home.domain.service;

import com.ssafy.home.domain.dto.AddressInfoDto;
import com.ssafy.home.domain.dto.BusDto;
import com.ssafy.home.domain.dto.HouseInfoDto;
import com.ssafy.home.domain.dto.StoreInfoDto;
import com.ssafy.home.domain.mapper.ZzimMapper;
import com.ssafy.home.domain.request.CoordinateRangeRequest;
import com.ssafy.home.domain.mapper.HouseMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseService {

    private final HouseMapper houseMapper;
    private final ZzimMapper zzimMapper;

    public HouseService(HouseMapper houseMapper, ZzimMapper zzimMapper) {
        this.houseMapper = houseMapper;
        this.zzimMapper = zzimMapper;
    }

    public List<HouseInfoDto> houseList(CoordinateRangeRequest request, Object memberId) {
        List<HouseInfoDto> house = houseMapper.houseList(request);
        List<HouseInfoDto> house2 = new ArrayList<>();

        if(memberId != null) {
            for (HouseInfoDto h : house) {
                boolean zzim = false;
                int cnt = zzimMapper.check((int) memberId, h.getAptId());

                if (cnt == 1) {
                    zzim = true;
                }

                h.setZzim(zzim);
                house2.add(h);
            }
            return house2;
        }
        return house;
    }

    public List<AddressInfoDto> dongList(CoordinateRangeRequest request) {
        return houseMapper.dongList(request);
    }

    public List<BusDto> busList(CoordinateRangeRequest request) {
        return houseMapper.busList(request);
    }

    public List<StoreInfoDto> foodList(CoordinateRangeRequest request) {
        return houseMapper.foodList(request);
    }

    public List<StoreInfoDto> cafeList(CoordinateRangeRequest request) {
        return houseMapper.cafeList(request);
    }
}