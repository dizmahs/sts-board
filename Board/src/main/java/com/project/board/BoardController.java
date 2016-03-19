package com.project.board;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.board.service.BoardService;

@Controller
public class BoardController {

	@Resource(name="boardService")
	private BoardService boardService;
	
	@Resource(name="messageSource")
    private MessageSource messageSource;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/boardList")
	public String boardList(HttpServletRequest req, HttpServletResponse resp, ModelMap model)throws Exception{
		
		//model.addAttribute("MEMINFO", boardService.selectBoard());
		
		return "board/BoardList";
	}
}
