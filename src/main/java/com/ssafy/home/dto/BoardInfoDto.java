package com.ssafy.home.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardInfoDto {
    private int board_id;
    private String id;
    private String name;
    private String title;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
