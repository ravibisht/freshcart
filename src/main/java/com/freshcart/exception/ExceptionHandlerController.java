package com.freshcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {


	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView projectNotFoundHandler(Exception message) {

		ModelAndView mav = new ModelAndView("error/404");
		mav.addObject("errorMessage", message.getMessage());
		return mav;
	}

	
	  @ResponseStatus(value = HttpStatus.NOT_FOUND)
	  
	  @ExceptionHandler(UserNotFoundException.class) public ModelAndView
	  userNotFoundHandler(Exception message) {
	  
	  ModelAndView mav = new ModelAndView("error/404");
	  mav.addObject("errorMessage", message.getMessage()); return mav; }
	 

	  @ResponseStatus(value = HttpStatus.NOT_FOUND)
	  @ExceptionHandler(ProductCategoryNotFoundException.class)
	  public ModelAndView projectCategoryNotFoundHandler(Exception message) {
		ModelAndView mav = new ModelAndView("error/404");
		mav.addObject("errorMessage", message.getMessage());
		return mav;
	}

	 
	  
	  
	  
	   @ResponseStatus(value=HttpStatus.NOT_FOUND)
	   @ExceptionHandler(FeedbackNotFoundException.class) 
	   public ModelAndView feedbackNotFounHandler(Exception message) { 
		   ModelAndView mav=new ModelAndView("error/404"); 
		   mav.addObject("errorMessage",message.getMessage()); 
	   return mav; 
	  }
	  
	  
	   
}
