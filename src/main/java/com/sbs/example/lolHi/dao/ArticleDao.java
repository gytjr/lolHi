package com.sbs.example.lolHi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.example.lolHi.dto.Article;
import com.sbs.example.lolHi.dto.Board;
import com.sbs.example.lolHi.dto.Reply;

@Mapper
public interface ArticleDao {
	
	List<Article> getForPrintArticles(Map<String, Object> param);
	Article getForPrintArticleById(@Param("id") int id);
	void deleteArticleById(@Param("id") int id);
	void modifyArticleById(@Param("id") int id,@Param("title") String title, @Param("body") String body);
	void insertArticle(Map<String, Object> param);
	int getTotalCount(Map<String, Object> param);
	void writeArticleReply(Map<String, Object> param);
	List<Reply> getForPrintArticleReplies(int articleId);
	Board getBoardByCode(@Param("boardCode") String boardCode);
}
