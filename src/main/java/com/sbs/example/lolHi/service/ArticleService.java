package com.sbs.example.lolHi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.example.lolHi.dao.ArticleDao;
import com.sbs.example.lolHi.dto.Article;
import com.sbs.example.lolHi.util.Util;


@Service
public class ArticleService {
	
	@Autowired
	private ArticleDao articleDao;
	
	public List<Article> getArticles() {
		return articleDao.getArticles();
	}
	
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	
	public void deleteArticleById(int id) {
		articleDao.deleteArticleById(id);
	}

	public void modifyArticleById(int id, String title, String body) {
		articleDao.modifyArticleById(id, title, body);
		
	}

	public int insertArticle(Map<String, Object> param) {
		articleDao.insertArticle(param);
		
		int id = Util.getAsInt(param.get("id"));
		
		return id;
		
	}

}
