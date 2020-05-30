package com.webcoder.app.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.webcoder.app.data.AlbumServiceClient;
import com.webcoder.app.data.UserEntity;
import com.webcoder.app.data.UserRepository;
import com.webcoder.app.model.AlbumReponseModel;
import com.webcoder.app.service.UserService;
import com.webcoder.app.shared.Dto.UserDto;

import feign.FeignException;

@Service
public class UserServiceImpl implements UserService {

	UserRepository userRepo;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	// RestTemplate restTemplate;
	Environment env;
	AlbumServiceClient albumServiceClient;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public UserServiceImpl(UserRepository userRepo, BCryptPasswordEncoder bCryptPasswordEncoder,
			AlbumServiceClient albumServiceClient, Environment env) {
		this.userRepo = userRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		// this.restTemplate = restTemplate;
		this.env = env;
		this.albumServiceClient = albumServiceClient;
	}

	@Override
	public UserDto createUser(UserDto userReq) {

		userReq.setUserId(UUID.randomUUID().toString());
		userReq.setEncryptedPassword(bCryptPasswordEncoder.encode(userReq.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(userReq, UserEntity.class);
		userRepo.save(userEntity);
		UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepo.findByEmail(username);
		if (userEntity == null)
			throw new UsernameNotFoundException(username);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true,
				new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		UserEntity userEntity = userRepo.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		return new ModelMapper().map(userEntity, UserDto.class);
	}

	@Override
	public UserDto getUserByUserId(String userId) {

		UserEntity userEntity = userRepo.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException("User not found!");

		UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
		/*
		 * String albumUrl = String.format(env.getProperty("albums.url"), userId);
		 * 
		 * ResponseEntity<List<AlbumReponseModel>> albumReponse =
		 * restTemplate.exchange(albumUrl, HttpMethod.GET, null, new
		 * ParameterizedTypeReference<List<AlbumReponseModel>>() { });
		 * List<AlbumReponseModel> albumList = albumReponse.getBody();
		 */

		List<AlbumReponseModel> albumList = null;
		try {
			albumList = albumServiceClient.getAlbums(userId);
		} catch (FeignException e) {
			logger.error(e.getLocalizedMessage());
		}
		userDto.setAlbums(albumList);
		return userDto;

	}

}
