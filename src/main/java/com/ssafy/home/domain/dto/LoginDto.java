package com.ssafy.home.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDto {

    private int memberId;
    private String name;
    private String id;
    private String email;
    @Setter
    @JsonProperty("isAdmin")
    private boolean isAdmin;

    public void update(MemberDto member) {
        this.memberId = member.getMemberId();
        this.name = member.getName();
        this.id = member.getId();
        this.isAdmin = member.isAdmin();
        this.email = member.getEmail();
    }
}
