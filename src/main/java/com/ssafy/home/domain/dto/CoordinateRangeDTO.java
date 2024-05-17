package com.ssafy.home.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CoordinateRangeDTO {

    private double startLongitude;
    private double endLongitude;
    private double startLatitude;
    private double endLatitude;
    private int level;
}
