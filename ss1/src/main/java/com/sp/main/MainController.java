package com.sp.main;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("mainController")
public class MainController {
	 
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String method(HttpSession session) {
		// 이곳에서 세션에 값을 저장하며 "/main"을 타지 않으면 로그인 되어도 로그인이 되지 않는 화면으로 출력
		String userId = null;
		Boolean isAnonymous = true;
		
		// principal : 사용자의 id를 가져옴
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails) {
			userId = ((UserDetails) principal).getUsername();
			isAnonymous = false;
		}
		
		session.setAttribute("username", userId);
		session.setAttribute("isAnonymous", isAnonymous);
		
		return "main/main";
	}
	
}
