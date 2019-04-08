package com.team.sprout.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String errorHandler(Exception e, Model model) {
		String errMsg = e.getMessage();
		model.addAttribute("errMsg", errMsg);
		
		return "exception/error";
	}
}
