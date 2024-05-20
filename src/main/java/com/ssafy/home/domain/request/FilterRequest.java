package com.ssafy.home.domain.request;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilterRequest {

    private double startLongitude;
    private double endLongitude;
    private double startLatitude;
    private double endLatitude;
    private int min;
    private int max;
}
