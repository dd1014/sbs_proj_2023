package com.kdy.exam.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kdy.exam.project.repository.MemberRepository;
import com.kdy.exam.project.vo.Member;


@Service
public class MemberService {
	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public Member getMember(int id) {
		return memberRepository.getMember(id);
	}

	public List<Member> getMembers() {
		return memberRepository.getMembers();
	}

	public int join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		
		Member oldmember = getMemberByLoginId(loginId);
		
		if(oldmember != null ) {
			return -1;
		}
		
		
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
		return memberRepository.getLastInsertId();
	}
	

	private Member getMemberByLoginId(String loginId) {
		
		return memberRepository.getMemberByLoginId(loginId);
	}

	public void deleteMember(int id) {
		memberRepository.deleteMember(id);
	}

	public void modifyMember(int id, String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		memberRepository.modifyMember(id, loginId, loginPw, name, nickname, cellphoneNo, email);
	}
}
