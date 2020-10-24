package com.sbs.example.lolHi.controller.usr;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.example.lolHi.dto.Article;
import com.sbs.example.lolHi.service.ArticleService;
import com.sbs.example.lolHi.util.Util;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/article/list")
	public String showList(Model model, @RequestParam Map<String, Object> param) {
		int totalCount = articleService.getTotalCount();
		int itemsCountInAPage = 10;
		int totalPage = (int) Math.ceil(totalCount / (double) itemsCountInAPage);

		int pageMenuArmSize = 3;
		int page = Util.getAsInt(param.get("page"), 1);
		int pageMenuStart = page - pageMenuArmSize;
		if (pageMenuStart < 0) {
			pageMenuStart = 1;
		}

		int pageMenuEnd = page + pageMenuArmSize;
		if (pageMenuEnd > totalPage) {
			pageMenuEnd = totalPage;
		}

		// 여기서 설정안하면 서비스에서 결정
		param.put("itemsCountInAPage", itemsCountInAPage);
		List<Article> articles = articleService.getArticles(param);

		model.addAttribute("totalCount", totalCount);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("pageMenuArmSize", pageMenuArmSize);
		model.addAttribute("pageMenuStart", pageMenuStart);
		model.addAttribute("pageMenuEnd", pageMenuEnd);
		model.addAttribute("page", page);
		model.addAttribute("articles", articles);

		return "usr/article/list";
	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(int id, Model model) {

		Article article = articleService.getArticleById(id);

		model.addAttribute("article", article);
		return "usr/article/detail";
	}

	@RequestMapping("usr/article/doDelete")
	public String doDelete(int id, Model model, HttpSession session) {
		int loginedMemberId = 0;
		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}

		if (loginedMemberId == 0) {
			model.addAttribute("msg", "로그인 후 이용해주세요");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		Article article = articleService.getArticleById(id);
		if(loginedMemberId == article.getWriterId()) {
			articleService.deleteArticleById(id);
			model.addAttribute("msg", String.format("%d번 게시물이 삭제되었습니다.", id));
			model.addAttribute("replaceUri", "/usr/article/list");
		} else {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("replaceUri", "/usr/article/list");
		}

		return "common/redirect";
	}

	@RequestMapping("usr/article/modify")
	public String showModify(Model model, int id, HttpSession session) {
		int loginedMemberId = 0;

		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			
			Article article = articleService.getArticleById(id);
			
			if (loginedMemberId == article.getWriterId()) {
				model.addAttribute("article", article);
				return "usr/article/modify";
			} else {
				model.addAttribute("msg", "권한이 없습니다.");
				model.addAttribute("replaceUri", "/usr/article/list");
			}
		}

		if (loginedMemberId == 0) {
			model.addAttribute("msg", "로그인 후 이용해주세요");
			model.addAttribute("replaceUri", "/usr/member/login");
			return "common/redirect";
		}

		return "common/redirect";
	}

	@RequestMapping("usr/article/doModify")
	public String doModify(int id, String title, String body, Model model, HttpSession session) {

		int loginedMemberId = 0;

		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			
			Article article = articleService.getArticleById(id);
			if (loginedMemberId == article.getWriterId()) {
				
				articleService.modifyArticleById(id, title, body);
				model.addAttribute("msg", String.format("%d번 게시물이 수정되었습니다.", id));
				model.addAttribute("replaceUri", String.format("/usr/article/detail?id=%d", id));
			} else {
				model.addAttribute("msg", "권한이 없습니다.");
				model.addAttribute("replaceUri", "/usr/article/list");
			}

			
		}

		if (loginedMemberId == 0) {
			model.addAttribute("msg", "로그인 후 이용해주세요");
			model.addAttribute("replaceUri", "/usr/member/login");
			return "common/redirect";
		}
		return "common/redirect";
	}

	@RequestMapping("usr/article/write")
	public String showWrite(HttpSession session, Model model) {
		int loginedMemberId = 0;

		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}

		if (loginedMemberId == 0) {
			model.addAttribute("msg", "로그인 후 이용해주세요");
			model.addAttribute("replaceUri", "/usr/member/login");
			return "common/redirect";
		}

		return "usr/article/write";
	}

	@RequestMapping("usr/article/doWrite")
	public String doWrite(@RequestParam Map<String, Object> param, Model model,HttpSession session) {
		int loginedMemberId = 0;

		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}

		if (loginedMemberId == 0) {
			model.addAttribute("msg", "로그인 후 이용해주세요");
			model.addAttribute("replaceUri", "/usr/member/login");
			return "common/redirect";
		}
		
		param.put("memberId", loginedMemberId);

		int id = articleService.insertArticle(param);

		model.addAttribute("msg", String.format("%d번 게시물이 등록되었습니다.", id));
		model.addAttribute("replaceUri", String.format("/usr/article/detail?id=%d", id));

		return "common/redirect";
	}

}
