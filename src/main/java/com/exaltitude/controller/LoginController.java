package com.exaltitude.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.exaltitude.bean.LoginBean;
import com.exaltitude.service.LoginService;

@Controller
public class LoginController
{
	@Autowired
	private LoginService loginService;
	

	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("login");
		LoginBean loginBean = new LoginBean();
		model.addObject("loginBean", loginBean);
		return model;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginBean")LoginBean loginBean)
	{
		ModelAndView model= null;
		try
		{
			Boolean isValidUser = this.getLoginService().authenticateUser(loginBean.getUsername(), loginBean.getPassword());
			if(isValidUser)
			{
				System.out.println("User Login Successful");
				request.setAttribute("loggedInUser", loginBean.getUsername());
				model = new ModelAndView("home");
			}
			else
			{
				model = new ModelAndView("login");
				model.addObject("loginBean", loginBean);
				request.setAttribute("message", "Invalid credentials!!");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
}
