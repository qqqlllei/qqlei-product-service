package com.qqlei.shop.product.service.impl;

import com.qqlei.shop.product.rabbitmq.RabbitMQSender;
import com.qqlei.shop.product.rabbitmq.RabbitQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qqlei.shop.product.mapper.CategoryMapper;
import com.qqlei.shop.product.model.Category;
import com.qqlei.shop.product.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;


	@Autowired
	private RabbitMQSender rabbitMQSender;

	
	public void add(Category category) {
		categoryMapper.add(category);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"add\", \"data_type\": \"category\", \"id\": " + category.getId() + "}");
	}

	public void update(Category category) {
		categoryMapper.update(category);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"update\", \"data_type\": \"category\", \"id\": " + category.getId() + "}");
	}

	public void delete(Long id) {
		categoryMapper.delete(id);
		rabbitMQSender.send(RabbitQueue.DATA_CHANGE_QUEUE, "{\"event_type\": \"delete\", \"data_type\": \"category\", \"id\": " + id + "}");
	}

	public Category findById(Long id) {
		return categoryMapper.findById(id);
	}

}
