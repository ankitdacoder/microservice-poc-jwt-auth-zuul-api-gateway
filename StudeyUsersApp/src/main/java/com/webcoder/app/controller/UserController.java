package com.webcoder.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webcoder.app.model.CreateUserResponseModel;
import com.webcoder.app.model.User;
import com.webcoder.app.model.UserResponseModel;
import com.webcoder.app.service.UserService;
import com.webcoder.app.shared.Dto.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private Environment env;

	Map<String, User> users;

	@Autowired
	UserService userService;

	@GetMapping("/status/check")
	public String checkStatus() {
		return "User Service is working on! " + env.getProperty("local.server.port") + " with token "
				+ env.getProperty("token.secret");
	}

	@GetMapping()
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {

		return "users list get called" + "page =" + page + " limit = " + limit + "order = " + sort;
	}

	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId) {

		UserDto userDto = userService.getUserByUserId(userId);

		UserResponseModel userResponseModel = new ModelMapper().map(userDto, UserResponseModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(userResponseModel);

	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody User userReq) {

		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userReq, UserDto.class);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto createdUser = userService.createUser(userDto);

		CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}

	@PutMapping(path = "/{userId}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<User> updateUser(@PathVariable("userId") String userId, @Valid @RequestBody User userReq) {

		return null;
	}

	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userID) {

		if (users.containsKey(userID)) {
			users.remove(userID);
		}
		return ResponseEntity.noContent().build();

	}

}
