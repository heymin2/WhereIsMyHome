package com.ssafy.home.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HouseDealDto {

    int dealId;
    int aptId;
    String dealAmount;
    int dealYear;
    int dealMonth;
    int dealDay;
    String size;
    String floor;
}
