package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.exception.JsonValidationException;
import com.example.demo.services.RabbitMQSender;
import com.example.demo.services.ValidateOrderImpl;
import com.example.demo.utility.ObjectMapperUtility;

@RestController
public class RabbitMQPublisherController {

	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	HttpServletRequest httpServletRequest;



	@Autowired
	ValidateOrderImpl validateOrderImpl;
	@Autowired
	RabbitMQSender rabbitMQSender;

	@PostMapping(path = "/order")
	
	public ResponseEntity<Object> createOrderRequest(@RequestBody(required = true) String order,
			HttpServletRequest request) throws JsonValidationException {
		rabbitMQSender.send(order);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}

}
