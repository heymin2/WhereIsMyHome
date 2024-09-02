package com.ssafy.home.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ZzimDto {

    private int zzimId;
    @Setter
    private int memberId;
    private int aptId;
    private LocalDateTime createdAt;
}
