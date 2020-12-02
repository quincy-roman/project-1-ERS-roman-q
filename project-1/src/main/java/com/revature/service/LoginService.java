package com.revature.service;

import org.apache.log4j.Logger;

import com.revature.dao.UserDao;
import com.revature.model.Users;

public class LoginService {
	
	private static Logger log = Logger.getLogger(LoginService.class);
	private UserDao ud = new UserDao();
	
	public LoginService() {}
	
	public Users validateUser(Users user, String password) {
		if(user.getUsername() != null && password != null) {
			return ud.login(user, password);
		}else {
			return null;
		}
	}

}
