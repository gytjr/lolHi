package com.sbs.example.lolHi.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sbs.example.lolHi.dao.MemberDao;
import com.sbs.example.lolHi.dto.Member;
import com.sbs.example.lolHi.dto.ResultData;
import com.sbs.example.lolHi.util.Util;


@Service
public class MemberService {
	@Value("${custom.siteName}")
	private String siteName;
	@Value("${custom.siteMainUri}")
	private String siteMainUri;
	@Value("${custom.siteLoginUri}")
	private String siteLoginUri;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MailService mailService;

	public void insertMember(Map<String, Object> param) {
		 memberDao.insertMember(param);
		 
		 sendJoinCompleteMail((String) param.get("email"));
	}
	
	private void sendJoinCompleteMail(String email) {
		String mailTitle = String.format("[%s] 가입이 완료되었습니다.", siteName);

		StringBuilder mailBodySb = new StringBuilder();
		mailBodySb.append("<h1>가입이 완료되었습니다.</h1>");
		mailBodySb.append(String.format("<p><a href=\"%s\" target=\"_blank\">%s</a>로 이동</p>", siteMainUri, siteName));

		mailService.send(email, mailTitle, mailBodySb.toString());
	}


	public Member getMemberByLoginId(String loginId) {
		
		return memberDao.getMemberByLoginId(loginId);
	}


	public boolean isJoinAvailableLoginId(String loginId) {
		Member member = memberDao.getMemberByLoginId(loginId);
		if(member == null) {
			return true;
		}
		return false;
	}


	public Member getMemberById(int loginedMemberId) {
		
		return memberDao.getMemberById(loginedMemberId);
	}


	public void modifyMemberById(Map<String, Object> param) {
		memberDao.modifyMemberById(param);
	}


	public boolean isJoinAvailableNameAndEmail(String name, String email) {
		if (name == null || name.length() == 0) {
			return false;
		}
		if (email == null || email.length() == 0) {
			return false;
		}
		
		Member member = memberDao.getMemberByNameAndEmail(name, email);
		return member == null;
	}


	public Member getMemberByNameAndEmail(String name, String email) {
		
		return memberDao.getMemberByNameAndEmail(name, email);
	}

	public ResultData setTempPasswordAndNotify(Member member) {
		Random r = new Random();
		
		String tempLoginPw = (10000 + r.nextInt(90000)) + "";
		
		String mailTitle = String.format("[%s] 임시 비밀번호가 발송었습니다.", siteName);
		
		String mailBody= "";
		mailBody += String.format("로그인 아이디 : %s", member.getLoginId());
		mailBody += "<br>";
		mailBody += String.format("임시 비밀번호 : %s", tempLoginPw);
		mailBody += "<br>";
		mailBody += String.format("<a href=\"%s\" target=\"_blank\">로그인 하러가기</a>", siteLoginUri);
		
		
		ResultData sendResultData = mailService.send(member.getEmail(), mailTitle, mailBody);
		
		if (sendResultData.isFail()) {
			return new ResultData("F-1", "메일발송에 실패했습니다.");
		}
		Map<String, Object> modifyParam = new HashMap<>();
		
		modifyParam.put("loginPw", Util.sha256(tempLoginPw));
		modifyParam.put("id", member.getId());
		
		memberDao.modifyMemberById(modifyParam);
		
		return new ResultData("S-1", "임시 비밀번호를 메일로 발송하였습니다.");
	}
	
	
}
