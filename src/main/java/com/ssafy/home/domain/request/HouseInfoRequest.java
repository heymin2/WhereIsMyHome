package com.ssafy.home.domain.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HouseInfoRequest {

    private int aptId;
    private int buildYear;
    private String sigunguCode;
    private String eubmyundongCode;
    private String areaId;
    private String jibun;
    private String apartmentName;
    private double longitude;
    private double latitude;
    private int dealAmount;
}
