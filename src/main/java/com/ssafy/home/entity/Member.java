package com.ssafy.home.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private String name;
	private String id;
	private String pw;
	private String phone;
	private String email;
	private String nickname;
	private boolean role;
	private Date create_at;
}
