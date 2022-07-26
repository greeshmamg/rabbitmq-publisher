package com.example.demo.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.example.demo.constants.JsonPathConstants;
import com.example.demo.exception.JsonValidationException;
import com.example.demo.rabbitmq.model.Order;
import com.example.demo.rabbitmq.model.OrderDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@Configuration
public class ObjectMapperUtility {
	@Autowired
	JsonPathConstants jsonPathConstants;
	@Autowired
	OrderDetail orderDetails;
	
	ObjectMapper objectMapper = new ObjectMapper();
	

	public OrderDetail convert(String details) throws JsonValidationException {
		try {
			orderDetails = objectMapper.readValue(details, OrderDetail.class);
		} catch (JsonProcessingException e) {

			throw new JsonValidationException(e.getMessage());
		}
		return orderDetails;
	}
	public OrderDetail getDetails(String order) {
		
		orderDetails.setOrderId(JsonPath.read(order,jsonPathConstants.ORDERID));	
		Order orderItems= new Order();
		orderItems.setItemId(JsonPath.read(order, jsonPathConstants.ITEMID));
		orderItems.setProductType(JsonPath.read( order, jsonPathConstants.PRODUCTTYPE));
		orderItems.setQuantity(JsonPath.read( order, jsonPathConstants.QUANTITY));
		orderItems.setPrice(JsonPath.read( order, jsonPathConstants.PRICE));
		orderDetails.setOrderdItem(orderItems);
		return orderDetails;
	}
}
