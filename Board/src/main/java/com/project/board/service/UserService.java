package com.project.board.service;

import com.project.board.domain.UserVO;

public interface UserService {

	public UserVO login(String username, String password) throws Exception;
	
	public UserVO login(UserVO user) throws Exception;	
}
