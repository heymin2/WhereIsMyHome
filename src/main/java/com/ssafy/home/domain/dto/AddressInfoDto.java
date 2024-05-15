package com.ssafy.home.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressInfoDto {

    private int addressId;
    private String address;
    private int level;
    private double longitude;
    private double latitude;
    private double amount;
}
