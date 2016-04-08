package com.project.board.serviceImpl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.project.board.dao.UserDAO;
import com.project.board.domain.UserVO;
import com.project.board.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "userDAO")
	private UserDAO userDao;

	private static Log log = LogFactory.getLog(UserServiceImpl.class);

	@Override
	public UserVO login(String id, String password) throws Exception {
		UserVO user = new UserVO();
		user.setId(id);
		user.setPassword(password);
		return login(user);
	}

	@Override
	public UserVO login(UserVO user) throws Exception {
		return userDao.signIn(user);
	}
	
}
