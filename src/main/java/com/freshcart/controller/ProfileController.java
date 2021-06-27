package com.freshcart.controller;

import java.util.Date;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.GYS.app.exception.UserNotFoundException;
import com.GYS.app.model.User;
import com.GYS.app.services.UserServices;

@Controller
public class ProfileController {
	
	@Autowired
	private UserServices us;
	
		
	
	@RequestMapping(value= {"/profile/{username}","{username}","@{username}"})
	public String UserProject(@PathVariable String username,HttpSession session,Model model) throws UserNotFoundException {
		
		String checkuser;
        try {
        	checkuser=((User)session.getAttribute("loggedInUser")).getUsername();
         } 
        catch(NullPointerException ne) {
        	return "redirect:/login";
         }
        
        if(checkuser==null) 
        	return "redirect:/login";
		
		 User user=us.findByUser(username);
		 
		 if(user==null)
			 throw new UserNotFoundException(username);
		 
		 model.addAttribute("profile", user);
		 model.addAttribute("active_profile", "active-item");
		 model.addAttribute("title", "Profile | "+username);
		 return "user/UserProfile";
		 
	}
	
	
	@PostMapping("/profile/edit/user")
	public String editProfile( Model model,@ModelAttribute("edituser")User user,
			
			String previousEmail,String previousUsername, HttpSession session,MultipartFile imageFile) {
		
	     	int userId=(int)session.getAttribute("loggedInUserId");
	        String username=((User)session.getAttribute("loggedInUser")).getUsername();
	        
	     	
	       if(!previousUsername.equals(user.getUsername())) {
	    	
	    	  if(us.UserExitsByUsername(user.getUsername())) {
		
	    		model.addAttribute("Uerror", "Username is Already taken");
	    		
	    		return "user/EditProfile";
	    	   }	
	       }	
	    	
	      
	       if(!previousEmail.equals(user.getEmail())) {
	        	
	    	    if(us.UserExitsByEmail(user.getEmail())){
				
				model.addAttribute("emailerror", "Email is Already taken");
				return "user/EditProfile";
	    	   }
			} 
	   	
	    
		
	   if(user.getUsername().equals(username)) {  
		 
		 User updatedUser=us.getUserById(userId);
		  if(updatedUser!=null) {
		      updatedUser.setBio(user.getBio());
		      updatedUser.setMobileNo(user.getMobileNo());
		      updatedUser.setFirstname(user.getFirstname());
		      updatedUser.setLastname(user.getLastname());
		      updatedUser.setEmail(user.getEmail());
		      updatedUser.setUsername(user.getUsername());
		      updatedUser.setProfession(user.getProfession());
		      updatedUser.setUpdatedDate(new Date());
		      us.saveUserWithProfileImage(updatedUser, imageFile);
		      
		      session.setAttribute("loggedInUser", updatedUser);
		 } 
	  }    
		 
	     model.addAttribute("navName", "Back to Profile");
	     model.addAttribute("navUrl", "/profile/"+username);
	     model.addAttribute("status", "true");  
	 return "user/EditProfile";
	}
	
	

	@RequestMapping("edit/{username}")
	public String editProfile(Model model,@PathVariable String username,HttpSession session) throws UserNotFoundException {
		
		String loggedInUsername=((User)session.getAttribute("loggedInUser")).getUsername();
		
		  if(username.equals(loggedInUsername)) {
			 
			  User user=us.findByUser(username);
			  
			  if(user==null) 
				  throw new UserNotFoundException(username);
			  
			  model.addAttribute("edituser", user);
			  model.addAttribute("navName", "Back to Profile");
			  model.addAttribute("navUrl", "/"+username);
			  
			  model.addAttribute("title", "Edit Profile");
			  return "user/EditProfile";
			  
	     }
			  
			 
		  return "error/error";
	}

}
