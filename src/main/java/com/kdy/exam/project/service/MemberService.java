package com.kdy.exam.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kdy.exam.project.repository.MemberRepository;
import com.kdy.exam.project.utill.Ut;
import com.kdy.exam.project.vo.Member;
import com.kdy.exam.project.vo.ResultData;


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

	public ResultData join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		
		
		//로그인아이디 중복체크
		Member oldmember = getMemberByLoginId(loginId);
		
		if(oldmember != null ) {
			return ResultData.from("F-7", Ut.f("해당 로그인아이디(%s)는 이미 사용중입니다.", loginId));
		}
		
		//이름+이메일 중복체크
		
		oldmember = getMemberByNameAndEmail(name, email);
		
		if(oldmember != null) {
			return ResultData.from("F-8", Ut.f("해당 이름(%s)과 이메일(%s)은 이미 사용중입니다.", name, email));
		}
		
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		int id = memberRepository.getLastInsertId();
		
		return ResultData.from("S-1", "회원가입이 완료되었습니다.", id);
	}
	

	private Member getMemberByNameAndEmail(String name, String email) {
		
		return memberRepository.getMemberByNameAndEmail(name, email);
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
