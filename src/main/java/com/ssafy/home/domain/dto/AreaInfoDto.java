package com.ssafy.home.domain.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AreaInfoDto {

    @Setter
    private String address;
    private double longitude;
    private double latitude;
}