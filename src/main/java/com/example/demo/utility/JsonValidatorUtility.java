package com.example.demo.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.example.demo.constants.CommonConstants;
import com.example.demo.constants.JsonPathConstants;
import com.jayway.jsonpath.JsonPath;

@Configuration
public class JsonValidatorUtility {
	@Autowired
	JsonPathConstants jsonpathConstants;
	
	@Autowired
	CommonConstants constants;
	String value;
	public boolean validateJSON(String order, String path) {
		value = (JsonPath.read(order, path)).toString();
		if (value == constants.EMPTY_STRING) {
			return false;
		}
		return true;
	}

	public boolean validateProductType(String order) {
		value = JsonPath.read(order, jsonpathConstants.PRODUCTTYPE);
		if (!value.equalsIgnoreCase(constants.PRODUCTTYPE_DEVICE)&&(!value.equalsIgnoreCase(constants.PRODUCTTYPE_PLAN))) {
			return false;
		}
		return true;
	}
}
