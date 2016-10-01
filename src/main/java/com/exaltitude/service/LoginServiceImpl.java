package com.exaltitude.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exaltitude.dao.LoginDAO;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDAO loginDAO;
	
	public LoginDAO getLoginDAO() {
		return loginDAO;
	}
	
	

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public Boolean authenticateUser(String username, String password){
		Boolean result;
		result = this.getLoginDAO().authenticateUser(username, password);
		return result;
	}
}
