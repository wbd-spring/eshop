package com.wbd.eshop.inventory.model;

import java.io.Serializable;

public class ProductInfo implements Serializable {
	private Long id;
	private String name;
	private Double price;
	
	public ProductInfo() {
		
	}
	
	public ProductInfo(Long id, String name, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}