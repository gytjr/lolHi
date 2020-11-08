package com.sbs.example.lolHi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.example.lolHi.dao.ReplyDao;
import com.sbs.example.lolHi.dto.Article;
import com.sbs.example.lolHi.dto.Member;
import com.sbs.example.lolHi.dto.Reply;
import com.sbs.example.lolHi.util.Util;

@Service
public class ReplyService {

	@Autowired
	private ReplyDao replyDao;

	public int writeReply(Map<String, Object> param) {
		replyDao.writeReply(param);

		int id = Util.getAsInt(param.get("id"));

		return id;
	}
	
public List<Reply> getForPrintArticleReplies(int articleId) {
		
		return replyDao.getForPrintArticleReplies(articleId);
	}


	public List<Reply> getForPrintArticleReplies(Member actorMember, int articleId) {
		
		List<Reply> replies = replyDao.getForPrintArticleReplies(articleId);

		for ( Reply reply : replies ) {
			if ( reply.getExtra() == null ) {
				reply.setExtra(new HashMap<>()); 
			}

			boolean actorCanDelete = false;
			if(actorMember != null) {
				actorCanDelete = actorMember.getId() == reply.getMemberId();
			}
			boolean actorCanModify = actorCanDelete;

			reply.getExtra().put("actorCanDelete", actorCanDelete);
			reply.getExtra().put("actorCanModify", actorCanModify);
		}

		return replies;
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
