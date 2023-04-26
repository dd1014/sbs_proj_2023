package com.kdy.exam.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kdy.exam.project.vo.Article;

@Mapper
public interface ArticleRepository {
	
	



	public Article getForPrintArticle(@Param("id") int id);
	
	
	public List<Article> getForPrintArticles(int boardId, @Param("limitStart") int limitStart,@Param("limitTake") int limitTake, String searchKeywordTypeCode, String searchKeyword);
	
	
	public void writeArticle(@Param("memberId") int memberId, @Param("boardId") int boardId, @Param("title") String title, @Param("body") String body);
	
	
	public void deleteArticle(@Param("id") int id);
	
	
	public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);
	
	
	public int getLastInsertId();


	public int getArticlesCount(int boardId, String searchKeywordTypeCode, String searchKeyword);


	public int increaseHitCount(int id);


	public int getArticleHitCount(int id);


	public int actorCanMakeReactionPoint(int memberId, int id);

}
