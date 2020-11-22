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
import com.sbs.example.lolHi.dto.ResultData;
import com.sbs.example.lolHi.service.MemberService;
import com.sbs.example.lolHi.util.Util;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/usr/member/checkLoginPw")
	public String showCheckLoginPw() {
		return "usr/member/checkLoginPw";
	}
	
	@RequestMapping("/usr/member/doCheckLoginPw")
	public String doCheckLoginPw(String loginPw, HttpServletRequest req, Model model, String redirectUrl) {
		Member loginedMember = (Member) req.getAttribute("loginedMember");

		if (loginedMember.getLoginPw().equals(loginPw) == false) {
			model.addAttribute("historyBack", true);
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			return "common/redirect";
		}

		String authCode = memberService.genCheckLoginPwAuthCode(loginedMember.getId());

		if (redirectUrl == null || redirectUrl.length() == 0) {
			redirectUrl = "/usr/home/main";
		}

		redirectUrl = Util.getNewUri(redirectUrl, "checkLoginPwAuthCode", authCode);

		model.addAttribute("replaceUri", redirectUrl);

		return "common/redirect";
	}
	
	@RequestMapping("/usr/member/findLoginId")
	public String showFindLoginId() {
		return "usr/member/findLoginId";
	}
	
	@RequestMapping("/usr/member/doFindLoginId")
	public String doFindLoginId(String name, String email, Model model) {
		Member member = memberService.getMemberByNameAndEmail(name, email);
		
		if(member == null) {
			model.addAttribute("msg", "해당회원은 존재하지 않습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		model.addAttribute("msg", String.format("가입날짜 : %s, 로그인 아이디 : %s",member.getRegDate(), member.getLoginId()));
		model.addAttribute("historyBack", true);
		
		return "common/redirect";
	}
	
	@RequestMapping("/usr/member/findLoginPw")
	public String showFindLoginPw() {
		return "usr/member/findLoginPw";
	}
	
	@RequestMapping("/usr/member/doFindLoginPw")
	public String doFindLoginPw(String email, String loginId, Model model) {
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null) {
			model.addAttribute("msg", "해당회원은 존재하지 않습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		if(member.getEmail().equals(email) == false) {
			model.addAttribute("msg", "해당회원은 존재하지 않습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		ResultData setTempPasswordAndNotifyRsData = memberService.setTempPasswordAndNotify(member);
		
		model.addAttribute("msg", String.format(setTempPasswordAndNotifyRsData.getMsg()));
		model.addAttribute("historyBack", true);
		
		return "common/redirect";
	}
	
	@RequestMapping("/usr/member/join")
	public String showJoin() {
		return "usr/member/join";
	}
	
	@RequestMapping("/usr/member/doJoin")
	public String doJoin(@RequestParam Map<String, Object> param, Model model) {
		
		String loginId = Util.getAsStr(param.get("loginId"), "");
		String name = Util.getAsStr(param.get("name"), "");
		String email = Util.getAsStr(param.get("email"), "");
		
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
		
		boolean isJoinAvailableNameAndEmail = memberService.isJoinAvailableNameAndEmail(name, email);
		
		if(isJoinAvailableNameAndEmail == false) {
			model.addAttribute("msg", "이미 가입된 회원의 정보입니다.");
			model.addAttribute("replaceUri", "/usr/member/findLoginId");
			return "common/redirect";
		}
		
		memberService.insertMember(param);
		
		model.addAttribute("msg", "회원가입 완료");
		model.addAttribute("replaceUri", "/usr/article-free/list");
		
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
		model.addAttribute("replaceUri", "/usr/article-free/list");
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
		model.addAttribute("replaceUri", "/usr/article-free/list");
		return "common/redirect";
	}
	
	@RequestMapping("/usr/member/doLogout")
	public String doLogout(HttpSession session, Model model) {
		session.removeAttribute("loginedMemberId");
		
		model.addAttribute("replaceUri", "/usr/article-free/list");
		return "common/redirect";
	}
}
