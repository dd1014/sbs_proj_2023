package com.kdy.exam.project.service;

import org.springframework.stereotype.Service;

import com.kdy.exam.project.repository.BoardRepository;
import com.kdy.exam.project.vo.Board;

@Service
public class BoardService {
	private BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public Board getBoardById(int id) {
		return boardRepository.getBoardById(id);
	}
	
}
