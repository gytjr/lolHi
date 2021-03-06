package com.sbs.example.lolHi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.example.lolHi.dto.Reply;

@Mapper
public interface ReplyDao {
	int writeReply(Map<String, Object> param);
	List<Reply> getForPrintArticleReplies(int articleId);
	void deleteReplyById(int id);
	Reply getReplyById(int id);
	void modifyReplyById(Map<String, Object> param);
}
