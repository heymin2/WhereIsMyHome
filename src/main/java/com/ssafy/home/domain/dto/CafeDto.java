package com.ssafy.home.domain.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CafeDto {

    private int storeId;
    private String name;
    private String jibun;

    @Setter
    private int distance;
    private double longitude;
    private double latitude;
}
