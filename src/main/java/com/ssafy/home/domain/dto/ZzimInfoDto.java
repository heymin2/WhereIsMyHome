package com.ssafy.home.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ZzimInfoDto {

    private int zzimId;
    private String sido;
    private String gugun;
    private String dong;
    private String jibun;
    private String apartmentName;
    private LocalDateTime createdAt;
}
