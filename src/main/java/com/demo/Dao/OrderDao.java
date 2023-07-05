package com.demo.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;

import com.demo.model.*;

public class OrderDao {
	
private Connection conn;
	
	private String qury;
	private PreparedStatement pst;
	private ResultSet rs;

	
	public OrderDao(Connection conn) {
		
		this.conn=conn;
	}
	//  buy order method 
	public boolean insertOrder(Order order) {
		
		boolean res=false;
		
		
		try {
			qury="insert into orders (p_id,u_id,o_quantity,o_date) values(?,?,?,?)";
			
			pst=this.conn.prepareStatement(qury);
			
			pst.setInt(1, order.getId());
			
			pst.setInt(2, order.getUserID());
			
			pst.setInt(3, order.getQuantity());
			pst.setString(4, order.getDate());
			
			pst.executeUpdate();
			
			res=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
		
	}
	// show all the user select orders details
	
	public List<Order> userOrders(int id){
		List<Order> list=new ArrayList<>();
	
		
		try {
			qury="select * from orders where u_id=? order by orders.o_id desc";
			
			pst=this.conn.prepareStatement(qury);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			while(rs.next()) {
				Order order=new Order();
				ProductsDao pdao=new ProductsDao(this.conn);
				int  pID=rs.getInt("p_id");
				
				Products ps=pdao.getSingleProducts(pID);
				order.setOrderID(rs.getInt("o_id"));
				order.setId(pID);
				order.setName(ps.getName());
				order.setCategory(ps.getCategory());
				order.setPrice(ps.getPrice()*rs.getInt("o_quantity"));
				order.setDate(rs.getString("o_date"));
				order.setQuantity(rs.getInt("o_quantity"));
			list.add(order);
			
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return list;
		
		
		
		
		
	}
	
	
	
	// cancel order method 
	
	public void cancelOrderMethod(int id) {
	    //boolean result = false;
        try {
            qury = "delete from orders where o_id=?";
            pst = this.conn.prepareStatement(qury);
            pst.setInt(1, id);
            pst.execute();
            //result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
		
		
	}
	
	
	
	
}
