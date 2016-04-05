package com.project.board.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.project.board.domain.UserVO;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository
public class UserDAO extends EgovAbstractMapper {

	protected Log log = LogFactory.getLog(UserDAO.class);

	/**
	 * <b>required field </b><br>
	 * insert - #{id}, #{password}, #{name} <br>
	 * update - #{name}, #{email} <br>
	 * opt1: changePassword - #{password} <br>
	 * @return 'success' or 'fail'
	 * */
	public String upsertUser(UserVO user) {
		String result = "Success";
		try {
			update("user.upsertUser", user);
		} catch (Exception e) {
			result = "fail: " + e.getMessage();
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <b>required field </b><br>
	 * #{id}, #{password} <br>
	 * @return null is not exist user
 	 * */
	public UserVO signIn( UserVO user ) throws Exception{
		return selectOne("user.signIn", user);		
	}
	
	/**
	 * <b>required field </b><br>
	 * none.
	 * opt1: paging - #{startIndex}, #{pageSize} <br>
	 *  - #{startIndex} 1 ~ totalCount / pageSize <br>
	 * */
	public List getList( UserVO user ) throws Exception {		
		return selectList("user.getList", user);
	}
	
	/**
	 * <b>required field </b><br>
	 * #{name} <br>
	 * opt1: count  <br>
	 * opt2: like <br>
	 * */
	public UserVO getUser( UserVO user ) throws Exception {
		return selectOne("user.getUser", user);
	}
}
