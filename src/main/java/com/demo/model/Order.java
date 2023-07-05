package com.demo.model;

public class Order extends Products {
	

	private int orderID;
	 private int userID;
	 
	 private int quantity;
	 private String Date;
	 
	 
	public Order() {
		super();
	}


	public Order(int orderID, int userID, int quantity, String date) {
		super();
		this.orderID = orderID;
		this.userID = userID;
		this.quantity = quantity;
		Date = date;
	}


	public Order(int userID, int quantity, String date) {
		super();
		this.userID = userID;
		this.quantity = quantity;
		Date = date;
	}


	public int getOrderID() {
		return orderID;
	}


	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}


	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getDate() {
		return Date;
	}


	public void setDate(String date) {
		Date = date;
	}


	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", userID=" + userID + ", quantity=" + quantity + ", Date=" + Date + "]";
	}
	 
	 
	 

}
