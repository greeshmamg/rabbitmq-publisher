package com.example.demo.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.example.demo.constants.JsonPathConstants;
import com.example.demo.rabbitmq.model.OrderDetail;
import com.jayway.jsonpath.JsonPath;

@Configuration
public class JsonValidatorUtility {
	@Autowired
	JsonPathConstants jsonpathConstants;
	String value ;

	public boolean validateJSON(String order, String path) {
		value = JsonPath.read(order, path);
		if (value=="" ) {
			return false;
		}
		return true;
	}

	public boolean validateProductType(String order) {
		value=JsonPath.read(order,jsonpathConstants.PRODUCTTYPE);
		if(!value.equalsIgnoreCase("device")||(!value.equalsIgnoreCase("plan"))) {
			return false;
		}
		return true;
	}
}
