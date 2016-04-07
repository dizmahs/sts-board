package com.project.board.service;

import com.project.board.domain.UserVO;

public interface UserService {

	public boolean isValidUser(String username, String password) throws Exception;
	
	public boolean isValidUser(UserVO user) throws Exception;	
}
