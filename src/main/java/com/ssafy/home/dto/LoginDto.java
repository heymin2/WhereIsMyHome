package com.ssafy.home.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    private int memberId;
    private String name;
    private String id;
    private String email;
    @JsonProperty("isAdmin")
    private boolean isAdmin;
}
