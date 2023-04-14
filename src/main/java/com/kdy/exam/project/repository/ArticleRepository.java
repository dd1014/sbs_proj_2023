package com.kdy.exam.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kdy.exam.project.vo.Article;

@Mapper
public interface ArticleRepository {
	
	public Article getArticle(@Param("id") int id);
	
	
	public List<Article> getArticles();
	
	
	public void writeArticle(@Param("title") String title, @Param("body") String body);
	
	
	public void deleteArticle(@Param("id") int id);
	
	
	public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);
	
	
	public int getLastInsertId();

}
