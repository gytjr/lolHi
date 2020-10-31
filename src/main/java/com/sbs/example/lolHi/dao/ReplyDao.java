package com.sbs.example.lolHi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.example.lolHi.dto.Article;
import com.sbs.example.lolHi.dto.ArticleReply;

@Mapper
public interface ReplyDao {
	
	void writeArticleReply(Map<String, Object> param);
	List<ArticleReply> getForPrintArticleReplies(int articleId);
}
