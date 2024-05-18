package com.ssafy.home.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HouseDto {

    private int aptId;
    private int buildYear;
    private String areaId;
    private String sido;
    private String gugun;
    private String dong;
    private String jibun;
    private String apartmentName;
    private boolean zzim;
}
