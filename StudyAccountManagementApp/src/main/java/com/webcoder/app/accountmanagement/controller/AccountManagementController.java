package com.webcoder.app.accountmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountManagementController {

	
	@GetMapping("/status/check")
	public String checkStatus()
	{
		 return "Account Service is working!";
	}
	
	@GetMapping("/password")
	public String ChangePassword()
	{
		return "Password Change Working";
	}
}
