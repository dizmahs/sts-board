package com.project.board;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.board.domain.SessionVO;
import com.project.board.domain.UserVO;
import com.project.board.interceptor.SessionInterceptor;
import com.project.board.service.UserService;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private SessionVO sessionData;
	
	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	@RequestMapping(value = "/join/login.html", method = RequestMethod.GET)
	public String displayLogin(HttpServletRequest request, HttpServletResponse response) {
		logger.info("GET] displayLogin...");
		return "join/login";
	}

	@RequestMapping(value = "/join/login.html", method = RequestMethod.POST)
	public String executeLogin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("loginBean") UserVO loginBean) {
		logger.info("POST] executeLogin...");
		logger.debug("data=" + loginBean);
		
		String redirectUrl = sessionData.getRedirectUrl();
		logger.debug("redirectUrl : {}", redirectUrl);
		
		try {
			UserVO user = userService.login(loginBean);
			if (user != null) {
				logger.debug("User Login Successful");
				sessionData.initialize(user, null);				
				if( StringUtils.hasText(redirectUrl) == false){
					redirectUrl = "/bbs/board/index.html";
				}
				//return "forward:" + redirectUrl;
				return "redirect:" + redirectUrl;
			} else {
				logger.debug("User Login Invalid credentials!!");
				//sessionData.setMessage("Invalid credentials!!");
				request.setAttribute("message", "Invalid credentials!!");				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return "join/login";
	}
	
	@RequestMapping(value = "/join/logout.html", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		logger.info("GET] logout...");
		sessionData.clear();
		HttpSession session = request.getSession(true);		
		session.setAttribute("sessionInfo", sessionData);
		return "forward:/join/login.html";
	}

}
