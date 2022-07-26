package com.example.demo.constants;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonPathConstants {

	public final String ORDERID="orderId";
	public final String ITEMID="orderedItem.itemId";
	public final String PRODUCTTYPE="orderedItem.productType";
	public final String QUANTITY="orderedItem.quantity";
	public final String PRICE="orderedItem.price";
	
	
	
	
}
