package com.ssafy.home.domain.dto;

import java.time.LocalDateTime;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDto {

	@Setter
	private int memberId;
	private String name;
	private String id;
	private String pw;
	private String phone;
	private String email;
	private boolean admin;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
