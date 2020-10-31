package com.sbs.example.lolHi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.example.lolHi.dao.ReplyDao;
import com.sbs.example.lolHi.dto.ArticleReply;


@Service
public class ReplyService {
	
	@Autowired
	private ReplyDao ReplyDao;
	

	public void writeArticleReply(Map<String, Object> param) {
		ReplyDao.writeArticleReply(param);
	}

	public List<ArticleReply> getForPrintArticleReplies(int articleId) {
		
		
		return ReplyDao.getForPrintArticleReplies(articleId);
	}

}
