package com.project.board;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.board.domain.AjaxBootstrapTablesVO;
import com.project.board.domain.ApiResultVO;
import com.project.board.domain.BoardVO;

/**
 * Handles requests for the application content page.
 */
@Controller
public class WebBoardController {

	private static final Logger logger = LoggerFactory.getLogger(WebBoardController.class);

	@RequestMapping("/board/boardContent.html")
	public String boardContent(HttpServletRequest req) throws Exception {

		logger.info(req.getMethod() + "] boardList...");
		return "/board/boardContent";
	}

	@RequestMapping("/board/{articleId}.html")
	public String boardDetail(HttpServletRequest req, @PathVariable("articleId") int articleId, Model model)
			throws Exception {

		logger.info(req.getMethod() + "] boardDetail...");
		BoardVO article = new BoardVO().test(articleId);
		
		model.addAttribute("article", article);

		return "/board/boardDetail";
	}

	/////////////////////////////////////////
	// Ajax Controller
	//

	@RequestMapping("/ajax/boards")
	public @ResponseBody ApiResultVO boardList(HttpServletRequest req,
			@RequestParam(value = "category", defaultValue = "detault") String category,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

		logger.info(req.getMethod() + "] boardList...");
		logger.debug("category=" + category + ", page=" + pageNo + ", pageNo=" + pageSize);

		ApiResultVO apiResult = new ApiResultVO();

		try {
			AjaxBootstrapTablesVO<BoardVO> bootstrapTable = new AjaxBootstrapTablesVO<BoardVO>();
			List<BoardVO> list = new LinkedList<BoardVO>();
			for (int i = 0; i < pageSize; i++) {
				list.add(new BoardVO().test(i + pageNo));
			}
			bootstrapTable.total = 30;
			bootstrapTable.rows = list;

			apiResult.buildSuccess(null, bootstrapTable);
		} catch (Exception e) {
			e.printStackTrace();
			apiResult.buildError(e.getMessage(), null);
		}

		return apiResult;
	}

	@RequestMapping("/ajax/boards/{id}")
	public ResponseEntity<ApiResultVO> boardById(HttpServletRequest req, @PathVariable("id") int id) {

		logger.info(req.getMethod() + "] boardById...");
		logger.debug("id=" + id);

		ApiResultVO apiResult = new ApiResultVO();

		try {
			// find by id
			BoardVO entity = new BoardVO();
			entity.test(id);
			if (entity == null) {
				throw new Exception("NotFoundException: invalid id=" + id);
				// return new ResponseEntity<ApiResultVO>(apiResult,
				// HttpStatus.OK);
			}
			apiResult.buildSuccess(null, entity);
		} catch (Exception e) {
			e.printStackTrace();
			apiResult.buildError(e.getMessage(), null);
		}

		return new ResponseEntity<ApiResultVO>(apiResult, HttpStatus.OK);
		// return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
}
