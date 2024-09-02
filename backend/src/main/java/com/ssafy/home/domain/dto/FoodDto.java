package com.ssafy.home.domain.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FoodDto {

    private int storeId;
    private String name;
    private String jibun;
    private String tel;
    @Setter
    private int distance;
    private double longitude;
    private double latitude;
}
