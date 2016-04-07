package com.project.board.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository
public class BoardDAO extends EgovAbstractMapper {
	
	protected Log log = LogFactory.getLog(BoardDAO.class);
	
	public List selectBoard() throws Exception {
		// TODO Auto-generated method stub
		return selectList("board.selectBoardList");
	}
	
}
