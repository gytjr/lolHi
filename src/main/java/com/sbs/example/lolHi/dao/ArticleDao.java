package com.sbs.example.lolHi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.example.lolHi.dto.Article;

@Mapper
public interface ArticleDao {
	
	List<Article> getArticles(Map<String, Object> param);
	Article getArticleById(@Param("id") int id);
	void deleteArticleById(@Param("id") int id);
	void modifyArticleById(@Param("id") int id,@Param("title") String title, @Param("body") String body);
	void insertArticle(Map<String, Object> param);
}
