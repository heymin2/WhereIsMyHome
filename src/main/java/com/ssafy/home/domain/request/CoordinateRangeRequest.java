package com.ssafy.home.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CoordinateRangeRequest {

    private double startLongitude;
    private double endLongitude;
    private double startLatitude;
    private double endLatitude;
    private int level;
}
