package com.kdy.exam.project.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
	private int id;
	private Date regDate;
	private Date updateDate;
	private String loginId;
	private String loginPw;
	private String name;
	private String nickname;
	private String cellphoneNo;
	private String email;
}
