package com.kdy.exam.project.service;

import java.util.List;

import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.stereotype.Service;

import com.kdy.exam.project.repository.MemberRepository;

@Service
public class MemberService {
	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public void join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		
		
	}
	
	
}
