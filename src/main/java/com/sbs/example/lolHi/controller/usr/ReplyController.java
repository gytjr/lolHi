package com.sbs.example.lolHi.controller.usr;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.example.lolHi.service.ReplyService;
import com.sbs.example.lolHi.util.Util;

@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	@RequestMapping("usr/reply/doWrite")
	public String doWriteReply(@RequestParam Map<String, Object> param, Model model, HttpServletRequest req) {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		
		String body = Util.getAsStr(param.get("body"), "");
		
		if (body.length() == 0) {
			model.addAttribute("msg", "내용을 입력해주세요");
			model.addAttribute("historyBack", true);
			
			return "common/redirect";
		}

		param.put("memberId", loginedMemberId);

		replyService.writeReply(param);
		String relTypeCode = (String)param.get("relTypeCode");
		int relId =  Util.getAsInt(param.get("relId"));
		
		model.addAttribute("msg", String.format("댓글이 등록되었습니다."));
		model.addAttribute("replaceUri", String.format("/usr/%s/detail?id=%d", relTypeCode, relId));

		return "common/redirect";
	}


}
