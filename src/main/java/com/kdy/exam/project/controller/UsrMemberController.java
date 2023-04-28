package com.kdy.exam.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdy.exam.project.service.MemberService;
import com.kdy.exam.project.utill.Ut;
import com.kdy.exam.project.vo.Member;
import com.kdy.exam.project.vo.ResultData;
import com.kdy.exam.project.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrMemberController {

	@Autowired
	private MemberService memberService;
	private Rq rq;

	public UsrMemberController(MemberService memberService, Rq rq) {
		this.memberService = memberService;
		this.rq = rq;
	}


	@RequestMapping("/usr/member/getMember")
	@ResponseBody
	private Object getMember(int id) {
		Member Member = memberService.getMember(id);
		if (Member == null) {
			return id + "번 회원정보가 존재하지 않습니다.";
		}
		return Member;
	}

	

	// 서비스 메서드 끝

	// 액션 메서드 시작
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout() {

		if ( !rq.isLogined() ) {
			return rq.jsHistoryBack("이미 로그아웃 상태입니다.");
		}

		rq.logout();

		return rq.jsReplace("로그아웃 되었습니다.","/");
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {

		if (Ut.empty(loginId)) {
			return ResultData.from("F-1", "loginId(을)를 입력해주세요.");
		}
		if (Ut.empty(loginPw)) {
			return ResultData.from("F-2", "loginPw(을)를 입력해주세요.");
		}
		if (Ut.empty(name)) {
			return ResultData.from("F-3", "name(을)를 입력해주세요.");
		}
		if (Ut.empty(nickname)) {
			return ResultData.from("F-4", "nickname(을)를 입력해주세요.");
		}
		if (Ut.empty(cellphoneNo)) {
			return ResultData.from("F-5", "cellphoneNo(을)를 입력해주세요.");
		}
		if (Ut.empty(email)) {
			return ResultData.from("F-6", "email(을)를 입력해주세요.");
		}

		// S-1
		// 회원가입이 완료되었습니다.
		// 7(몇번회원인지)
		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		if (joinRd.isFail()) {
			return (ResultData) joinRd;
		}

		Member Member = memberService.getMember(joinRd.getData1());

		return ResultData.newData(joinRd, "Member", Member);
	}
	
	@RequestMapping("/usr/member/login")
	public String showLogin() {
		
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {

		if (rq.isLogined()) {
			return rq.jsHistoryBack("이미 로그인되었습니다.");
		}

		if (Ut.empty(loginId)) {
			return rq.jsHistoryBack("loginId(을)를 입력해주세요.");
		}
		if (Ut.empty(loginPw)) {
			return rq.jsHistoryBack("loginPw(을)를 입력해주세요.");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return rq.jsHistoryBack("존재하지 않은 로그인아이디 입니다.");
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			return rq.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}

		rq.login(member);

		// 로그인성공시
		return rq.jsReplace(Ut.f("%s님 환영합니다.", member.getNickname()), "/");
	}

	@RequestMapping("/usr/member/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Member Member = memberService.getMember(id);
		if (Member == null) {
			return id + "번 회원이 존재하지 않습니다.";
		}

		memberService.deleteMember(id);
		return id + "번 회원정보가 삭제되었습니다.";
	}

	@RequestMapping("/usr/member/doModify")
	@ResponseBody
	public String doModify(int id, String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		Member Member = memberService.getMember(id);
		if (Member == null) {
			return id + "번 회원이 존재하지 않습니다.";
		}
		
		

		memberService.modifyMember(id, loginId, loginPw, name, nickname, cellphoneNo, email);
		return id + "번 회원정보가 수정되었습니다.";
	}
	
	@RequestMapping("/usr/member/getMembers")
	@ResponseBody
	public List<Member> getMembers() {
		return memberService.getMembers();
	}
	
	@RequestMapping("/usr/member/myPage")
	@ResponseBody
	public String showMyPage() {
		return "usr/member/myPage";
	}
}
