package com.project.board.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.project.board.dao.BoardDAO;
import com.project.board.service.BoardService;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Resource(name="boardDAO")
	private BoardDAO boardDAO;
	
	private static Log log = LogFactory.getLog(BoardServiceImpl.class);
	
	@Override
	public List selectBoard() throws Exception {
		// TODO Auto-generated method stub
		return (List) boardDAO.selectBoard();
	}

}
