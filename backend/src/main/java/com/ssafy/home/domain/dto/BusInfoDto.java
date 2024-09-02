package com.ssafy.home.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusInfoDto {

    private int busId;
    private String name;
    @Setter
    private int distance;
    private double longitude;
    private double latitude;
}