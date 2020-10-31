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
	private ReplyDao ReplyDao;
	

	public void writeReply(Map<String, Object> param) {
		ReplyDao.writeReply(param);
	}

	public List<Reply> getForPrintArticleReplies(int articleId) {

		return ReplyDao.getForPrintArticleReplies(articleId);
	}

}