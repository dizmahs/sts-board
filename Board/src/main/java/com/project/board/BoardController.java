package com.project.board;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.board.domain.AjaxBootstrapTablesVO;
import com.project.board.domain.BoardVO;
import com.project.board.service.BoardService;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Resource(name = "boardService")
	private BoardService boardService;

	@Resource(name = "messageSource")
	private MessageSource messageSource;	

	@RequestMapping("/board/boardContent.html")
	public String boardContent(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws Exception {

		return "/board/boardContent";
	}

	@RequestMapping("/boardList")
	public String boardList(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws Exception {

		// model.addAttribute("MEMINFO", boardService.selectBoard());

		return "board/BoardList";
	}

	@RequestMapping("/ajax/boards")
	public @ResponseBody AjaxBootstrapTablesVO<BoardVO> boardList(HttpServletRequest request,
			@RequestParam(value = "category", defaultValue = "detault") String category,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

		logger.info(request.getMethod() + "] boardList...");
		logger.debug("category=" + category + ", page=" + pageNo + ", pageNo=" + pageSize);

		AjaxBootstrapTablesVO<BoardVO> bootstrapTable = new AjaxBootstrapTablesVO<BoardVO>();
		
		List<BoardVO> list = new LinkedList<BoardVO>();
		for (int i = 0; i < pageSize; i++) {
			list.add(new BoardVO().test( i + pageNo ) );
		}		
		bootstrapTable.total = 30;
		bootstrapTable.rows = list;

		return bootstrapTable;
	}

	@RequestMapping("/ajax/boards/{id}")
	public ResponseEntity<BoardVO> boardById(HttpServletRequest request, @PathVariable("id") int id) {
		logger.info(request.getMethod() + "] boardById...");
		logger.debug("id=" + id);
		
		// find by id
		BoardVO entity = new BoardVO();
		entity.test(id);
		
		if( entity != null ){
			return new ResponseEntity<BoardVO>(entity, HttpStatus.OK);
		}
		
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

}
