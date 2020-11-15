package com.sbs.example.lolHi.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.example.lolHi.dao.MemberDao;
import com.sbs.example.lolHi.dto.Member;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;

	public void insertMember(Map<String, Object> param) {
		 memberDao.insertMember(param);
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
	
	
}
