package com.ssafy.home.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HouseDealDto {

    private int dealId;
    private int aptId;
    private int dealAmount;
    private int dealYear;
    private int dealMonth;
    private int dealDay;
    private double size;
    private int floor;
}
