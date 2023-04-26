package com.kdy.exam.project.service;

import org.springframework.stereotype.Service;

import com.kdy.exam.project.repository.ReactionPointRepository;

@Service
public class ReactionPointService {
	private ReactionPointRepository reactionPointRepository;
	
	public ReactionPointService(ReactionPointRepository reactionPointRepository) {
		this.reactionPointRepository = reactionPointRepository;
	}
	
	
	public boolean actorCanMakeReactionPoint(int actorId, String relTypeCode, int id) {
		
		if(actorId == 0) {
			return false;
		}
		
		return reactionPointRepository.actorCanMakeReactionPoint(actorId,relTypeCode, id) == 0; 
	}
}
