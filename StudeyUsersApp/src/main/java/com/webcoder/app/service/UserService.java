package com.webcoder.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.webcoder.app.shared.Dto.UserDto;

public interface UserService extends UserDetailsService {

	UserDto createUser(UserDto userDetails);

	UserDto getUserDetailsByEmail(String email);
	
	UserDto getUserByUserId(String userId);
}
