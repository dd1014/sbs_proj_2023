package com.kdy.exam.project.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	private int id;
	
	private String regDate;
	private String updateDate;
	private int authLevel;
	private boolean delStatus;
	private String delDate;
	
	private String loginId;
	private String loginPw;
	private String name;
	private String nickname;
	private String cellPhoneNo;
	private String email;
	
}