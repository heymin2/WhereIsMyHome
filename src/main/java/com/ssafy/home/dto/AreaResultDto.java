package com.ssafy.home.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AreaResultDto {

    List<String> sido;
    List<String> gugun;
    List<String> dong;
}
