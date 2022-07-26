package com.example.demo.services;

import com.example.demo.exception.JsonValidationException;

public interface ValidateOrder {
		 abstract void validateOrderDetails(String detail) throws JsonValidationException;
	}
