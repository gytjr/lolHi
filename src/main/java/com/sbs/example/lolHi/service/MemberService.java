package com.sbs.example.lolHi.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.example.lolHi.dao.MemberDao;

@Service
public class MemberService {
	@Autowired
	MemberDao memberDao;

	public void insertMember(Map<String, Object> param) {
		 memberDao.insertMember(param);
	}
	
	
}
