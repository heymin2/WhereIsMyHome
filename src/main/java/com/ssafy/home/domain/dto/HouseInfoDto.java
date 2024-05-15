package com.ssafy.home.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HouseInfoDto {

    int aptId;
    int buildYear;
    String sigunguCode;
    String eubmyundongCode;
    String jibun;
    String apartmentName;
    String longitude;
    String latitude;
}
