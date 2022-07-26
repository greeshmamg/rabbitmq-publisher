package com.example.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.example.demo.constants.CommonConstants;
import com.example.demo.exception.JsonValidationException;
import com.example.demo.rabbitmq.model.OrderDetail;
import com.example.demo.utility.JsonValidatorUtility;
import com.example.demo.utility.ObjectMapperUtility;

@Service
public class RabbitMQSender {
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQSender.class);
	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${rabbitmq.exchange}")
	private String exchange;

	@Value("${rabbitmq.routingkey}")
	private String routingkey;

	@Autowired
	CommonConstants commons;

	@Autowired
	ValidateOrderImpl validateOrderImpl;
	@Autowired
	ObjectMapperUtility objectMapperUtility;

	public void send(String order) throws JsonValidationException {

		//validateOrderImpl.validateOrderDetails(order);
		OrderDetail orderDetail = objectMapperUtility.getDetails(order);
		
		MessageProperties messageProperties = new MessageProperties();
		
		if (orderDetail.getOrderdItem().getProductType().equalsIgnoreCase(commons.PRODUCTTYPE_DEVICE)) {
			messageProperties.setHeader(commons.TYPE, commons.PRODUCTTYPE_PRODUCT);
			messageProperties.setHeader("consumerQueue", "ORDER.PRODUCT");
		} else {
			messageProperties.setHeader(commons.TYPE, commons.PRODUCTTYPE_PLAN);
			messageProperties.setHeader("consumerQueue", "ORDER.PLAN");
		}
		MessageConverter messageConverter = new SimpleMessageConverter();
		Message message = messageConverter.toMessage(orderDetail, messageProperties);
		logger.info("Message send" + message);
		rabbitTemplate.convertAndSend(exchange, "", message);
	}
}
