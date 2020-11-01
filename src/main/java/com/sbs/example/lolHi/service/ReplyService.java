package com.sbs.example.lolHi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.example.lolHi.dao.ReplyDao;
import com.sbs.example.lolHi.dto.Reply;


@Service
public class ReplyService {
	
	@Autowired
	private ReplyDao replyDao;
	

	public void writeReply(Map<String, Object> param) {
		replyDao.writeReply(param);
	}

	public List<Reply> getForPrintArticleReplies(int articleId) {

		return replyDao.getForPrintArticleReplies(articleId);
	}

	public Reply getReplyById(int id) {
		
		return replyDao.getReplyById(id);
	}

	public void deleteReplyById(int id) {
		replyDao.deleteReplyById(id);
	}

	public void modifyReplyById(Map<String, Object> param) {
		replyDao.modifyReplyById(param);		
	}

}
