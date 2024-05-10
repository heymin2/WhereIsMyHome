package com.ssafy.home.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardInfoDto {
    private int boardId;
    private String id;
    private String name;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
