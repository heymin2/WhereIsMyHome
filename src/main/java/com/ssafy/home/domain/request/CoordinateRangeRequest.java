package com.ssafy.home.domain.request;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CoordinateRangeRequest {

    private double startLongitude;
    private double endLongitude;
    private double startLatitude;
    private double endLatitude;
    private int level;
}
