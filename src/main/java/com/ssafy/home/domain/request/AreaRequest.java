package com.ssafy.home.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AreaRequest {

    private String address;
    private double longitude;
    private double latitude;
}