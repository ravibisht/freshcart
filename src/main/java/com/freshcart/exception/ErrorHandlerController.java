package com.freshcart.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.web.servlet.error.ErrorController;


  @Controller 
  public class ErrorHandlerController implements ErrorController{
  
  
  @RequestMapping("/error") 
  public String handleError() { 
	  return "error/error"; 
	}
  
  @Override public String getErrorPath() {
  
  return "/error"; }
  
  }
