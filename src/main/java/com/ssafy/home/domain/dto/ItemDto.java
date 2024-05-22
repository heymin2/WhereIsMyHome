package com.ssafy.home.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDto {

    private int itemId;
    private String type;
    @Setter
    private double longitude;
    @Setter
    private double latitude;
    private String address;
    private String title;
    private String description;
    private int salePrice;
    private int deposit;
    private int monthlyRent;
    private int rooms;
    private int bathrooms;
    private double size;
    @Setter
    private int memberId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Setter
    private boolean sold;
    @Setter
    private boolean zzim;
}