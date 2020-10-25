package com.sbs.example.lolHi.controller.usr;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.example.lolHi.dto.Member;
import com.sbs.example.lolHi.service.MemberService;
import com.sbs.example.lolHi.util.Util;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	
	@RequestMapping("/usr/member/join")
	public String showJoin() {
		return "usr/member/join";
	}
	
	@RequestMapping("/usr/member/doJoin")
	public String doJoin(@RequestParam Map<String, Object> param, Model model) {
		
		String loginId = Util.getAsStr(param.get("loginId"), "");
		
		if(loginId.length() == 0) {
			model.addAttribute("msg", "로그인 아이디를 입력해주세요.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		boolean isJoinAvailableLoginId = memberService.isJoinAvailableLoginId(loginId);
		
		if(isJoinAvailableLoginId == false) {
			model.addAttribute("msg", "이미 사용중인 아이디 입니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		memberService.insertMember(param);
		
		model.addAttribute("msg", "회원가입 완료");
		model.addAttribute("replaceUri", "/usr/article/list");
		
		return "common/redirect";
	}
	
	@RequestMapping("/usr/member/login")
	public String showLogin() {
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	public String doLogin(@RequestParam Map<String, Object> param, HttpSession session ,Model model) {
		String loginId = (String)param.get("loginId");
		String loginPw = (String)param.get("loginPw");
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null) {
			model.addAttribute("msg", "존재하지않는 아이디 입니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		else if (member.getLoginPw().equals(loginPw) == false) {
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		session.setAttribute("loginedMemberId", member.getId());
		
		model.addAttribute("msg", String.format("%s님 환영합니다.", member.getName()));
		model.addAttribute("replaceUri", "/usr/article/list");
		return "common/redirect";
	}
	
	@RequestMapping("/usr/member/modify")
	public String showModify(HttpServletRequest req, Model model) {
		
		return "usr/member/modify";
	}
	
	@RequestMapping("/usr/member/doModify")
	public String doModify(HttpServletRequest req, Model model,@RequestParam Map<String, Object> param) {
		int loginedMemberId = (int)req.getAttribute("loginedMemberId");
		
		String name = Util.getAsStr(param.get("name"), "");
		
		if(name.length() == 0) {
			model.addAttribute("msg", "수정할 이름을 입력해주세요");
			model.addAttribute("historyBack", true);
			
			return "common/redirect";
		}
		
		param.put("id", loginedMemberId);
		//해킹방지
		param.remove("loginId");
		param.remove("loginPw");
		
		memberService.modifyMemberById(param);
		
		model.addAttribute("msg", "수정완료");
		model.addAttribute("replaceUri", "/usr/article/list");
		return "common/redirect";
	}
	
	@RequestMapping("/usr/member/doLogout")
	public String doLogout(HttpSession session, Model model) {
		session.removeAttribute("loginedMemberId");
		
		model.addAttribute("replaceUri", "/usr/article/list");
		return "common/redirect";
	}
}
