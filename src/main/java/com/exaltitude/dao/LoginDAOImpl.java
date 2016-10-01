package com.exaltitude.dao;

import org.springframework.stereotype.Repository;

@Repository
public class LoginDAOImpl implements LoginDAO{
	public Boolean authenticateUser(String username, String password){
		Boolean result = false;
		
		if(username != null && password != null){
			if(password.equals(username+"AA")){
				result = true;
			}
		}
		
		return result;
	}
}
