package com.webcoder.app.shared;

import java.util.UUID;

import org.springframework.stereotype.Service;
@Service
public class Utils {

	public String genrateUserId() {
		return UUID.randomUUID().toString();
	}
}
