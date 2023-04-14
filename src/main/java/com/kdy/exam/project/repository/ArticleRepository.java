package com.kdy.exam.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kdy.exam.project.vo.Article;

@Mapper
public interface ArticleRepository {
	public Article getArticle(int id);

	public List<Article> getArticles();

	public Article writeArticle(String title, String body);

	public void deleteArticle(int id);

	public void modifyArticle(int id, String title, String body);

}
