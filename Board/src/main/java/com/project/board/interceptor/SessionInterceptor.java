package com.project.board.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.board.common.Utility;
import com.project.board.domain.SessionVO;

public class SessionInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);
	
	@Autowired
	private SessionVO sessionData;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.debug("PreHandle");
		// Login false
		if ( sessionData.isValidate() == false ) {
			logger.debug("need user login.");
			String redirectUrl = Utility.targetUrl(request);
			sessionData.setRedirectUrl(redirectUrl);
			logger.info("redirectUrl : {}", sessionData.getRedirectUrl());
			
			RequestDispatcher rd = request.getRequestDispatcher("/bbs/join/login.html");
			rd.forward(request, response);
			//response.sendRedirect("/bbs/join/login.html");
			return false;
		}
		else {
			sessionData.setRedirectUrl("");
			// Login true
			logger.debug("user has previous login.");
			return true; //super.preHandle(request, response, handler);
		}
	}	

}
