package com.project.board;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.board.common.Utility;
import com.project.board.domain.SessionVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private SessionVO sessionData;

	private String extractNameInUrl( String path ){
		String name = "";
		if( StringUtils.hasText(path)){
			int divLastIndex = path.lastIndexOf("/");
			int length = path.length();
			if( divLastIndex != -1 && length > ( divLastIndex + 1 ) ){
				name = path.substring(divLastIndex + 1, length );
				int extPath = name.lastIndexOf(".");
				if( extPath != -1 ){
					name = name.substring(0, extPath);
				}
			}
		}
		return name;
	}

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String index(HttpServletRequest req, Locale locale) {
		logger.info(req.getMethod() + "] index...");
		logger.debug("Welcome home! The client locale is {}.", locale);

		return "index";
	}

	@RequestMapping("/bbs/**/*.html")
	public String main(HttpServletRequest req, Locale locale, Model model) {

		logger.info(req.getMethod() + "] main...");

		HttpSession session = req.getSession(true);
		sessionData.setTargetUrl(Utility.targetUrl(req));
		String targetUrl = sessionData.getTargetUrl();

		session.setAttribute("sessionInfo", sessionData);

		logger.debug("targetUrl : {}", targetUrl);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home/main";
	}
	
	

	@RequestMapping("/bbs/content.html")
	public String mainContent(HttpServletRequest req, Model model) {
		logger.info(req.getMethod() + "] mainContent...");

		String targetUrl = sessionData.getTargetUrl();
		logger.debug("targetUrl : {}", targetUrl);

		if (targetUrl.indexOf("/join/login") > -1) {
			return "forward:/join/login.html";
		} else if (targetUrl.indexOf("/join/logout") > -1) {
			return "forward:/join/logout.html";
		} else if (targetUrl.indexOf("board/index") > -1) {
			return "forward:/board/boardContent.html";
		} else if (targetUrl.indexOf("board/") > -1) {
			String articleId = extractNameInUrl(targetUrl);
			logger.debug("articleId=" + articleId );
			return "forward:/board/"+articleId+".html";
		}

		logger.debug("Are you request index? ");

		return "forward:/index.html";
	}

}
