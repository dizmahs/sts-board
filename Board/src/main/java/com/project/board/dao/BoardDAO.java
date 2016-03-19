package com.project.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.project.board.common.ObjMap;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository
public class BoardDAO extends EgovAbstractMapper {
	
	protected Log log = LogFactory.getLog(BoardDAO.class);
	
	public List selectBoard() throws Exception {
		// TODO Auto-generated method stub
		return selectList("board.selectBoardList");
	}
	
}
