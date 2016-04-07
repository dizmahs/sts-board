package com.project.board;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.board.domain.UserVO;
import com.project.board.service.UserService;

@Controller
public class LoginController {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response) {
		logger.info( "HttpGet] displayLogin..." );
		
		ModelAndView model = new ModelAndView("login/login");
		UserVO loginBean = new UserVO();
		model.addObject("loginBean", loginBean);
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response,
									 @ModelAttribute("loginBean") UserVO loginBean ) {
		logger.info( "HttpPost] executeLogin..." );
		logger.debug( "data=" + loginBean );
		
		ModelAndView model = null;
		try {
			boolean isValidUser = userService.isValidUser(loginBean);
			if (isValidUser) {
				logger.debug("User Login Successful");				
				request.setAttribute("loggedInUser", loginBean.getName() );
				model = new ModelAndView("home");
			} else {
				model = new ModelAndView("login/login");
				model.addObject("loginBean", loginBean);
				request.setAttribute("message", "Invalid credentials!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}


}
