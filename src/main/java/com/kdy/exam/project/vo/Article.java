package com.kdy.exam.project.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String title;
	private String body;
	
	private String extra_writerName;
	private boolean extra_actorCanDelete;

}
