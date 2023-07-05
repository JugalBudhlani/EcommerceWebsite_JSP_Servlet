package com.demo.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.demo.model.Cart;
import com.demo.model.Products;


public class ProductsDao {

private Connection conn;
	
	private String qury;
	private PreparedStatement pst;
	private ResultSet rs;
	public ProductsDao(Connection conn) {
	
		this.conn = conn;
	}
	
	
	public List<Products> getAllProducts(){
		
		List<Products> list=new ArrayList<Products>();
		
		try {
			qury="select * from products";
			
			pst=this.conn.prepareStatement(qury);
			
			
			rs=pst.executeQuery();
			
			
			while(rs.next()) {
				
				Products pd=new Products();
				pd.setId(rs.getInt("id"));
				pd.setName(rs.getString("name"));
				pd.setCategory(rs.getString("category"));
				pd.setPrice(rs.getDouble("price"));
				pd.setImages(rs.getString("image"));
			
				
				list.add(pd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return list;
	}
	
	
	public List<Cart> getCartProdducts(ArrayList<Cart> cartlist){
		
		
		List<Cart> products=new ArrayList<Cart>();
		
		
		
		try {
			if(cartlist.size()>0) {
				
				for(Cart item:cartlist) {
					qury="select * from products where id=?";
					pst=this.conn.prepareStatement(qury);
					pst.setInt(1, item.getId());
					rs=pst.executeQuery();
					
					while(rs.next()) {
						
						Cart rw=new Cart();
						rw.setId(rs.getInt("id"));
						rw.setName(rs.getString("name"));
						rw.setCategory(rs.getString("category"));
						rw.setPrice(rs.getDouble("price")*item.getQuantity());
						rw.setQuantity(item.getQuantity());
						
						products.add(rw);
						
						
						
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
		
		
	
	}
	
	
	
	//this method you can use  to get single products from our index page and  returning row
	
	public Products getSingleProducts(int id) {
		Products row=null;
		
		try {
			qury="select * from products where id=?";
			pst=this.conn.prepareStatement(qury);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			while(rs.next()) {
				row=new Products();
				row.setId(rs.getInt("id"));

				
				row.setName(rs.getString("name"));
				
				row.setCategory(rs.getString("category"));
				
				row.setPrice(rs.getDouble("price"));
				
				row.setImages(rs.getString("image"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return row;
		
	}
	
	
	public double getTotalPrice(ArrayList<Cart> cartArry) {
		
	double sum=0;
	
	
	try {
		if(cartArry.size()>0) {
			
			for( Cart items:cartArry) {
			
		qury="select price from products where id=?";
		
			pst=this.conn.prepareStatement(qury);
		
			pst.setInt(1, items.getId());
			rs=pst.executeQuery();
			while(rs.next()) {
				sum+=rs.getDouble("price")*items.getQuantity();
			}
			}	
	
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
		return sum;	
	}
}
