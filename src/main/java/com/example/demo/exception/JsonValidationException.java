package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonValidationException extends Exception {
	private static final Logger logger = LoggerFactory.getLogger(JsonValidationException.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JsonValidationException(String message) {
		logger.error(message);
	}

	JsonValidationException(String errorCode, String message) {
		logger.error("Error Code: " + errorCode + "- " + message);
	}
}
