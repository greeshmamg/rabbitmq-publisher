package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.example.demo.constants.JsonPathConstants;
import com.example.demo.exception.JsonValidationException;
import com.example.demo.rabbitmq.model.OrderDetail;
import com.example.demo.utility.JsonValidatorUtility;

@Configuration
public class ValidateOrderImpl implements ValidateOrder {
	@Autowired
	JsonValidatorUtility jsonValiatorUtility;

	@Autowired
	JsonPathConstants jsonPathConsts;

	@Override
	public void validateOrderDetails(String detail) throws JsonValidationException {
		if (!jsonValiatorUtility.validateJSON(detail, jsonPathConsts.ORDERID)) {
			throw new JsonValidationException("OrderID is missing");
		}

		if (!jsonValiatorUtility.validateJSON(detail, jsonPathConsts.ITEMID)) {
			throw new JsonValidationException("Item ID is missing");
		}
		if (!jsonValiatorUtility.validateJSON(detail, jsonPathConsts.PRICE)) {
			throw new JsonValidationException("Price is missing");
		}
		if(!jsonValiatorUtility.validateProductType(detail)) {
			throw new JsonValidationException("Invalid product type");
		}
		if(!jsonValiatorUtility.validateJSON(detail, jsonPathConsts.QUANTITY)){
			throw new JsonValidationException("Quantity missing");
		}
	}
}