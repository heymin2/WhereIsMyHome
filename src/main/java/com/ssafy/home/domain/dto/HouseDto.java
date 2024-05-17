package com.ssafy.home.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HouseDto {

    private int aptId;
    private int dealId;
    private int buildYear;
    private String areaId;
    private String sido;
    private String gugun;
    private String dong;
    private String jibun;
    private String apartmentName;
    private int floor;
    private double size;
    private int dealAmount;
    private int dealYear;
    private int dealMonth;
    private int dealDay;
}
