package com.test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.dao.LoginJDBCTemplate;
import com.test.domain.Login;

@Controller
public class LoginController {
	
	@Autowired
	private LoginJDBCTemplate jdbcTemplate;
	
	@RequestMapping("/main")
	public String main(Model model, HttpSession session) {
		session.invalidate();
		return "/login/page-login";
	}	
	
	@RequestMapping("/login")
	public String login(Login login, HttpSession session) {

		//id, pw를 이용한 로그인 액션 처리
		//->성공한 경우 세션 객체 생성
		Login l = null;
		String uri = "redirect:/main?loginFail=1";

		try {
			l = this.jdbcTemplate.login(login);
			
			if (l.getAccess_id().equals("LV001")) {
				session.setAttribute("managerLoginInfo", l);
				uri = "redirect:/manager/main";
			} else if (l.getAccess_id().equals("LV002")) {
				session.setAttribute("instructorLoginInfo", l);
				uri = "redirect:/instructor/main";
			} else if (l.getAccess_id().equals("LV003")) {
				session.setAttribute("studentLoginInfo", l);
				uri = "redirect:/student/main";
			}
			
		}catch(DataAccessException e) {
			System.out.println(e.getMessage());
		}		
		
		return uri;
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/main";
	}
	
	@RequestMapping("/pwchange")
	public String pwchange(HttpSession session, RedirectAttributes rttr, String login_id, String current_pw, String new_pw) {
		
		int result = this.jdbcTemplate.pwchange(login_id, current_pw, new_pw);
		rttr.addFlashAttribute("success", result);
		
		String uri = "";
		if(session.getAttribute("managerLoginInfo")!=null) {
			uri="redirect:/manager/main";
		} else if(session.getAttribute("instructorLoginInfo")!=null) {
			uri = "redirect:/instructor/main";
		} else if(session.getAttribute("studentLoginInfo")!=null) {
			uri = "redirect:/student/main";
		}
		return uri;
	}
	
}