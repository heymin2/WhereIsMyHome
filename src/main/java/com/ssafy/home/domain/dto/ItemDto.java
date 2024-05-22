package com.ssafy.home.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
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
    private List<MultipartFile> img;
    private boolean sold;
}