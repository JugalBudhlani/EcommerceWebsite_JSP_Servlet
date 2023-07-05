package com.demo.model;

public class Products {

	private int id;
	private String name;
	private String category;
	private Double price;
	private String images;
	public Products() {
		
	}
	public Products(int id, String name, String category, Double price, String images) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.images = images;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "Products [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", images="
				+ images + "]";
	}
	  
	
	
}
