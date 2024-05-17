package com.ssafy.home.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HouseInfoDto {

    private int aptId;
    private int buildYear;
    private String sigunguCode;
    private String eubmyundongCode;
    private String areaId;
    private String sido;
    private String gugun;
    private String dong;
    private String jibun;
    private String apartmentName;
    private double longitude;
    private double latitude;
    private int dealAmount;
    private double size;
}
