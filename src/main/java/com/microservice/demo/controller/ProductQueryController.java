package com.microservice.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.demo.dto.ProductEvent;
import com.microservice.demo.entity.Product;
import com.microservice.demo.service.ProductQueryService;

@RestController
@RequestMapping("/products")
public class ProductQueryController {
	
	@Autowired
	private ProductQueryService queryService  ;
	
	@GetMapping
	public List<Product>  fetchAllProducts(){
		return  queryService.getProducts();
	}
	
	public void processProductEvents(ProductEvent productEvent) {
		
	}
  }
