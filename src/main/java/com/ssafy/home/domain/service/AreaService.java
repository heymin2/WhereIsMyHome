package com.ssafy.home.domain.service;

import com.ssafy.home.domain.dto.*;
import com.ssafy.home.domain.mapper.AreaMapper;
import com.ssafy.home.domain.mapper.ZzimMapper;
import com.ssafy.home.domain.request.CoordinateRangeRequest;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class AreaService {

    private final AreaMapper areaMapper;
    private final ZzimMapper zzimMapper;
    private static final int EARTH_RADIUS = 6371;

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
            List<AreaInfoDto> dongInfoList = areaMapper.dongInfo(sido, gugun, dong);
            dongInfoList.get(0).setAddress(dong);
            result.add(dongInfoList.get(0));
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

    public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat/2)* Math.sin(dLat/2)+ Math.cos(Math.toRadians(lat1))* Math.cos(Math.toRadians(lat2))* Math.sin(dLon/2)* Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return EARTH_RADIUS * c * 1000;    // Distance in m
    }

    public List<CafeDto> aptCafe(int aptId) {
        HouseCoordDto coord = areaMapper.aptCoord(aptId);

        //현재 위도 좌표 (y 좌표)
        double nowLatitude = coord.getLatitude();
        //현재 경도 좌표 (x 좌표)
        double nowLongitude = coord.getLongitude();

        //m당 y 좌표 이동 값
        double mForLatitude =(1 /(EARTH_RADIUS* 1 *(Math.PI/ 180)))/ 1000;
        //m당 x 좌표 이동 값
        double mForLongitude =(1 /(EARTH_RADIUS* 1 *(Math.PI/ 180)* Math.cos(Math.toRadians(nowLatitude))))/ 1000;

        //현재 위치 기준 검색 거리 좌표
        double maxY = nowLatitude +  (mForLatitude * 1000);
        double minY = nowLatitude -  (mForLatitude * 1000);
        double maxX = nowLongitude + (mForLongitude * 1000);
        double minX = nowLongitude - (mForLongitude * 1000);

        CoordinateRangeRequest request = CoordinateRangeRequest.builder()
                .startLatitude(minY)
                .endLatitude(maxY)
                .startLongitude(minX)
                .endLongitude(maxX)
                .level(1)
                .build();

        List<CafeDto> cafe = areaMapper.aptCafe(request);

        System.out.println(cafe);
        List<CafeDto> cafeList = new ArrayList<>();

        //정확한 거리 측정
        for(CafeDto c : cafe) {
            double distance = AreaService.getDistance(nowLatitude, nowLongitude, c.getLatitude(), c.getLongitude());
            if(distance < 1000) {
                c.setDistance((int)distance);
                cafeList.add(c);
            }
        }

        cafeList.sort((Comparator.comparingInt(CafeDto::getDistance)));
        return cafeList;
    }

    public List<FoodDto> aptFood(int aptId) {
        HouseCoordDto coord = areaMapper.aptCoord(aptId);

        double nowLatitude = coord.getLatitude();
        double nowLongitude = coord.getLongitude();

        double mForLatitude =(1 /(EARTH_RADIUS* 1 *(Math.PI/ 180)))/ 1000;
        double mForLongitude =(1 /(EARTH_RADIUS* 1 *(Math.PI/ 180)* Math.cos(Math.toRadians(nowLatitude))))/ 1000;

        double maxY = nowLatitude +  (mForLatitude * 1000);
        double minY = nowLatitude -  (mForLatitude * 1000);
        double maxX = nowLongitude + (mForLongitude * 1000);
        double minX = nowLongitude - (mForLongitude * 1000);

        CoordinateRangeRequest request = CoordinateRangeRequest.builder()
                .startLatitude(minY)
                .endLatitude(maxY)
                .startLongitude(minX)
                .endLongitude(maxX)
                .level(2)
                .build();

        List<FoodDto> food = areaMapper.aptFood(request);
        List<FoodDto> foodList = new ArrayList<>();

        //정확한 거리 측정
        for(FoodDto f : food) {
            double distance = AreaService.getDistance(nowLatitude, nowLongitude, f.getLatitude(), f.getLongitude());
            if(distance < 1000) {
                f.setDistance((int)distance);
                foodList.add(f);
            }
        }

        foodList.sort((Comparator.comparingInt(FoodDto::getDistance)));
        return foodList;
    }
}
