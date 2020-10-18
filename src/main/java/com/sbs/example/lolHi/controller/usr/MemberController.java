package com.sbs.example.lolHi.controller.usr;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public String doJoin(@RequestParam Map<String, Object> param) {
		
		String loginId = Util.getAsStr(param.get("loginId"), "");
		
		if(loginId.length() == 0) {
			return String.format("<script> alert('로그인 아이디를 입력해주세요.'); history.back(); </script>");
		}
		
		boolean isJoinAvailableLoginId = memberService.isJoinAvailableLoginId(loginId);
		
		if(isJoinAvailableLoginId == false) {
			return String.format("<script> alert('이미 사용중인 아이디 입니다.'); history.back(); </script>");
		}
		
		
		memberService.insertMember(param);
		
		return String.format("<script> alert('회원가입 완료'); location.replace('/usr/article/list')</script>");
	}
	
	@RequestMapping("/usr/member/login")
	public String showLogin() {
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(@RequestParam Map<String, Object> param, HttpSession session) {
		String loginId = (String)param.get("loginId");
		String loginPw = (String)param.get("loginPw");
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null) {
			return String.format("<script> alert('존재하지않는 아이디 입니다.'); history.back(); </script>");
		}
		else if (member.getLoginPw().equals(loginPw) == false) {
			return String.format("<script> alert('비밀번호가 일치하지 않습니다.'); history.back(); </script>");
		}
		
		return member.getLoginId();
	}
}
