package com.ssafy.home.dto;

import java.time.LocalDateTime;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDto {
	private String name;
	private String id;
	private String pw;
	private String phone;
	private String email;
	private boolean role;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;

	public boolean getRole() {
		return role;
	}
}
