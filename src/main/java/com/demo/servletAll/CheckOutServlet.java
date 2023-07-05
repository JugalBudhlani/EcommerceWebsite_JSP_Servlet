package com.demo.servletAll;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.Dao.OrderDao;
import com.demo.connectios.DBConnection;
import com.demo.model.Cart;
import com.demo.model.Order;
import com.demo.model.Users;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		
		try {
			PrintWriter out=response.getWriter();
			out.print("Check Out Servlet");
			
			SimpleDateFormat formatt=new SimpleDateFormat("yyyy-MM-dd");
			
			Date date=new Date();
			
			//retrive all cart products
			ArrayList<Cart> cart_list=(ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			

			Users auth=(Users) request.getSession().getAttribute("auth");
			
			//check auth and cart list
			
			if(cart_list != null && auth !=null) {
				//prepare  the order objects
				
				for(Cart c:cart_list) {
					Order order=new Order();
					order.setId(c.getId());
					
					order.setUserID(auth.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(formatt.format(date));
					
					
					// instantiate the order Dao class
					OrderDao od=new OrderDao(DBConnection.getConnection());
					
					// calling the insertOrder  method
				boolean res=	od.insertOrder(order);
					
				if(!res)break;
				}
				cart_list.clear();
				
				response.sendRedirect("orders.jsp");
				
			}else {
			if(auth==null) response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
			
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
