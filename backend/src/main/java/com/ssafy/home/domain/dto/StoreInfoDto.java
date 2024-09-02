package com.ssafy.home.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreInfoDto {

    private int storeId;
    private String name;
    private String jibun;
    private String tel;
    private double longitude;
    private double latitude;
    private String areaId;
}
