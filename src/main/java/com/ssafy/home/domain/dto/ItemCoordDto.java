package com.ssafy.home.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCoordDto {

    private int itemId;
    private String type;
    private double longitude;
    private double latitude;
    private String address;
    private int salePrice;
    private int deposit;
    private int monthlyRent;
    private double size;
    private boolean sold;
}
