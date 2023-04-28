package com.kdy.exam.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdy.exam.project.service.ReplyService;
import com.kdy.exam.project.utill.Ut;
import com.kdy.exam.project.vo.Reply;
import com.kdy.exam.project.vo.ResultData;
import com.kdy.exam.project.vo.Rq;

@Controller
public class UsrReplyController {
	private ReplyService replyService;
	private Rq rq;

	public UsrReplyController(ReplyService replyService, Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}

	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(String relTypeCode, int relId, String body, String replaceUri) {
		if (Ut.empty(relTypeCode)) {
			return rq.jsHistoryBack("relTypeCode(을)를 입력해주세요.");
		}

		if (Ut.empty(relId)) {
			return rq.jsHistoryBack("relId(을)를 입력해주세요.");
		}

		if (Ut.empty(body)) {
			return rq.jsHistoryBack("body(을)를 입력해주세요.");
		}

		ResultData<Integer> writeReplyRd = replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId, body);

		int id = writeReplyRd.getData1();

		if (Ut.empty(replaceUri)) {
			switch (relTypeCode) {
			case "article":
				replaceUri = Ut.f("../article/detail?id=%d", relId);
				break;
			}
		}

		return rq.jsReplace(writeReplyRd.getMsg(), replaceUri);
	}

	@RequestMapping("/usr/reply/doDelete")
	@ResponseBody
	public String doDelete(int id, String replaceUri) {
		if (Ut.empty(id)) {
			return rq.jsHistoryBack("relTypeCode(을)를 입력해주세요.");
		}

		Reply reply = replyService.getForPrintReply(rq.getLoginedMemberId(), id);

		if (reply == null) {
			return rq.jsHistoryBack(Ut.f("%d번 댓글이 존재하지 않습니다.", id));
		}
		
		if (reply.isExtra_actorCanDelete() == false) {
			return rq.jsHistoryBack(Ut.f("%d번 댓글을 삭제할 권한이 없습니다.", id));
		}
		
		ResultData deleteReplyRd = replyService.deleteReply(id);
		
		if (Ut.empty(replaceUri)) {
			switch (reply.getRelTypeCode()) {
			case "article":
				replaceUri = Ut.f("../article/detail?id=%d", reply.getRelId());
				break;
			}
		}

		return rq.jsReplace(deleteReplyRd.getMsg(), replaceUri);
	}

	@RequestMapping("/usr/reply/modify")
	public String modify(Model model, int id, String replaceUri) {
		if (Ut.empty(id)) {
			return rq.jsHistoryBack("relTypeCode(을)를 입력해주세요.");
		}

		Reply reply = replyService.getForPrintReply(rq.getLoginedMemberId(), id);

		if (reply == null) {
			return rq.jsHistoryBack(Ut.f("%d번 댓글이 존재하지 않습니다.", id));
		}
		
		if (reply.isExtra_actorCanModify() == false) {
			return rq.jsHistoryBack(Ut.f("%d번 댓글을 수정할 권한이 없습니다.", id));
		}
		
		model.addAttribute("reply", reply);
		
		return "usr/reply/modify";
	}
	
	@RequestMapping("/usr/reply/doModify")
	@ResponseBody
	public String doModify(int id, String body, String replaceUri) {
		if (Ut.empty(id)) {
			return rq.jsHistoryBack("relTypeCode(을)를 입력해주세요.");
		}

		Reply reply = replyService.getForPrintReply(rq.getLoginedMemberId(), id);

		if (reply == null) {
			return rq.jsHistoryBack(Ut.f("%d번 댓글이 존재하지 않습니다.", id));
		}
		
		if (reply.isExtra_actorCanModify() == false) {
			return rq.jsHistoryBack(Ut.f("%d번 댓글을 수정할 권한이 없습니다.", id));
		}
		
		if (Ut.empty(body)) {
			return rq.jsHistoryBack("body(을)를 입력해주세요");
		}
		
		ResultData modifyReplyRd = replyService.modifyReply(id, body);
		
		if (Ut.empty(replaceUri)) {
			switch (reply.getRelTypeCode()) {
			case "article":
				replaceUri = Ut.f("../article/detail?id=%d", reply.getRelId());
				break;
			}
		}

		return rq.jsReplace(modifyReplyRd.getMsg(), replaceUri);
	}
	
}
