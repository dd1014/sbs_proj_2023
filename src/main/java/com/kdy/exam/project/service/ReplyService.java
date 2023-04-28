package com.kdy.exam.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kdy.exam.project.repository.ReplyRepository;
import com.kdy.exam.project.utill.Ut;
import com.kdy.exam.project.vo.Article;
import com.kdy.exam.project.vo.Reply;
import com.kdy.exam.project.vo.ResultData;

@Service
public class ReplyService {
	private  ReplyRepository  replyRepository;
	
	public ReplyService(ReplyRepository  replyRepository) {
		this.replyRepository = replyRepository;
	}

	public ResultData<Integer> writeReply(int actorId, String relTypeCode, int relId, String body) {
		replyRepository.writeReply(actorId, relTypeCode, relId, body);
		int id = replyRepository.getLastInsertId();
		
		return ResultData.from("S-1", Ut.f("%d번 댓글이 생성되었습니다.", id), "id", id);
	}

	public List<Reply> getForPrintReplies(int actortId, String relTypeCode, int relId) {
		List<Reply> replies = replyRepository.getForPrintReplies(actortId, relTypeCode, relId);
		
		for(Reply reply : replies) {
			updateForPrintData(actortId, reply);
		}
		
		return replies;
	}

	private void updateForPrintData(int actorId, Reply reply) {
		if (reply == null) {
			return;
		}

		ResultData actorCanDeleteRd = actorCanDelete(actorId, reply);
		reply.setExtra_actorCanDelete(actorCanDeleteRd.isSuccess());

		ResultData actorCanModifyRd = actorCanModify(actorId, reply);
		reply.setExtra_actorCanModify(actorCanModifyRd.isSuccess());
	}
	
	public ResultData actorCanModify(int actorId, Reply reply) {
		if (reply == null) {
			return ResultData.from("F-1", "댓글이 존재하지 않습니다.");
		}

		if (reply.getMemberId() != actorId) {
			return ResultData.from("F-2", "권한이 없습니다.");
		}

		return ResultData.from("S-1", "댓글 수정이 가능합니다.");
	}
	public ResultData actorCanDelete(int actorId, Reply reply) {
		if (reply == null) {
			return ResultData.from("F-1", "댓글이 존재하지 않습니다.");
		}
		
		if (reply.getMemberId() != actorId) {
			return ResultData.from("F-2", "권한이 없습니다.");
		}
		
		return ResultData.from("S-1", "댓글 삭제가 가능합니다.");
	}

	public Reply getForPrintReply(int actorId, int id) {
		Reply reply = replyRepository.getForPrintReply(actorId, id);
		
		updateForPrintData(actorId, reply);
		
		return reply;
	}

	public ResultData deleteReply(int id) {
		replyRepository.deleteReply(id);
		
		return ResultData.from("S-1", Ut.f("%d번 댓글을 삭제하였습니다.", id));
	}
	
	
}
