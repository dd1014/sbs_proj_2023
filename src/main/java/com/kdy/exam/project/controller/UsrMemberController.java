package com.kdy.exam.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdy.exam.project.service.MemberService;

@Controller
public class UsrMemberController {

	@Autowired
	private MemberService memberService;
	
	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		return "성공!";
		
		
	}
}
