package com.freshcart.service.servicesiml;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.GYS.app.model.User;

@Component
public class SessionServicesImpl  {

	
	
	public void addUserSession(User user,HttpSession session) {
		
		user.setPassword("");
		session.setAttribute("loggedInUser", user);
		session.setAttribute("loggedInUserId", user.getId());
		session.setAttribute("role","USER");
	 }
	
	
    public void userLogOut(HttpSession session) {
		
		session.removeAttribute("loggedInUser");
		session.removeAttribute("ROLE");
		session.removeAttribute("loggedInUserId");
		session.invalidate();
	}
	
    
    
    
	public void addAdminSession(String adminUsername,HttpSession session) {
	
		session.setAttribute("adminUsername", adminUsername);
   	    session.setAttribute("role", "ADMIN"); 
		
	}


	public void adminLogout(HttpSession session) {
		
		session.removeAttribute("adminUsername");
		session.removeAttribute("role");
		session.invalidate();
		
	}
	
	
	
}
