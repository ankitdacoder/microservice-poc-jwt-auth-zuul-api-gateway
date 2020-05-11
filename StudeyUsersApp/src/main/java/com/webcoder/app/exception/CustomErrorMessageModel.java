package com.webcoder.app.exception;

import java.util.Date;

public class CustomErrorMessageModel {

	private Date timestamp;
	private String message;

	public CustomErrorMessageModel(Date date, String localizedMessage) {
		this.timestamp=date;
		this.message=localizedMessage;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
