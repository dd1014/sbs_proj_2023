package com.kdy.exam.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kdy.exam.project.repository.ReplyRepository;
import com.kdy.exam.project.utill.Ut;
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

		return replyRepository.getForPrintReplies(actortId, relTypeCode, relId);
	}
	
	
	
}
