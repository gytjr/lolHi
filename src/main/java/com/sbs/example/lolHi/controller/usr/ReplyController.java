package com.sbs.example.lolHi.controller.usr;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.example.lolHi.dto.Article;
import com.sbs.example.lolHi.dto.Reply;
import com.sbs.example.lolHi.service.ReplyService;
import com.sbs.example.lolHi.util.Util;

@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	@RequestMapping("usr/reply/doWrite")
	public String doWriteReply(@RequestParam Map<String, Object> param, Model model, HttpServletRequest req, String redirectUrl) {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		
		String body = Util.getAsStr(param.get("body"), "");
		
		if (body.length() == 0) {
			model.addAttribute("msg", "내용을 입력해주세요");
			model.addAttribute("historyBack", true);
			
			return "common/redirect";
		}
		
		param.put("memberId", loginedMemberId);

		int replyId = replyService.writeReply(param);
		
		System.out.println(replyId);
		String relTypeCode = (String)param.get("relTypeCode");
		int relId =  Util.getAsInt(param.get("relId"));
		
		if(redirectUrl == null || redirectUrl.length() == 0) {
			redirectUrl = String.format("/usr/%s/detail?id=%d", relTypeCode, relId);
		}
		
		model.addAttribute("msg", String.format("댓글이 등록되었습니다."));
		model.addAttribute("replaceUri", redirectUrl);

		return "common/redirect";
	}
	
	@RequestMapping("usr/reply/doDelete")
	public String doDelete(int id, Model model, HttpServletRequest req, String redirectUrl) {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		
		Reply reply = replyService.getReplyById(id);
		
		if (loginedMemberId != reply.getMemberId()) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("replaceUri", String.format("/usr/article/detail?id=%d", reply.getRelId()));
			return "common/redirect";
		}
		
		if (redirectUrl == null || redirectUrl.length() == 0) {
			redirectUrl = String.format("/usr/%s/detail?id=%d",reply.getRelTypeCode(), reply.getRelId());
		}

		replyService.deleteReplyById(id);
		
		model.addAttribute("msg", String.format("%d번 댓글이 삭제되었습니다.", id));
		model.addAttribute("replaceUri", redirectUrl);

		return "common/redirect";
	}

	
	@RequestMapping("usr/reply/modify")
	public String showModify(Model model, int id, HttpServletRequest req) {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		Reply reply = replyService.getReplyById(id);

		if (loginedMemberId != reply.getMemberId()) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("replaceUri", String.format("/usr/article/detail?id=%d", reply.getRelId()));
			return "common/redirect";
		}

		model.addAttribute("reply", reply);
		return "usr/reply/modify";	
	}
	

	@RequestMapping("usr/reply/doModify")
	public String doModify(@RequestParam Map<String, Object> param, Model model, HttpServletRequest req) {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		
		int id = Util.getAsInt(param.get("id"));
		String body = (String) param.get("body");
		
		if (body.length() == 0) {
			model.addAttribute("msg", "수정할 내용을 입력해주세요");
			model.addAttribute("historyBack", true);
			
			return "common/redirect";
		}
		
		Reply reply = replyService.getReplyById(id);

		if (loginedMemberId != reply.getMemberId()) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("replaceUri", String.format("/usr/article/detail?id=%d", reply.getRelId()));
			return "common/redirect";
		}

		replyService.modifyReplyById(param);
		
		model.addAttribute("msg", String.format("%d번 댓글이 수정되었습니다.", id));
		model.addAttribute("replaceUri", String.format("/usr/article/detail?id=%d", reply.getRelId()));
		return "common/redirect";
	}
}
