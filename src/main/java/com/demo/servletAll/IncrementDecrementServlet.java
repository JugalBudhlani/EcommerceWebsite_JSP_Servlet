package com.demo.servletAll;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.model.Cart;

/**
 * Servlet implementation class IncrementDecrementServlet
 */
@WebServlet("/IncrementDecrementServlet")
public class IncrementDecrementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncrementDecrementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	
	response.setContentType("text/html");
	
	PrintWriter out=response.getWriter();
	
	String action=request.getParameter("action");
	int id=Integer.parseInt(request.getParameter("id"));
	
	
	ArrayList<Cart> cart_list= (ArrayList<Cart>)   request.getSession().getAttribute("cart-list");
	
	if(action!=null && id>=1) {
		
		
		if(action.equals("inc")) {
			for(Cart c: cart_list) {
				if(c.getId()==id && c.getQuantity()>=1 ) 
				{
					int quantity =c.getQuantity();
					
					quantity++;
					 c.setQuantity(quantity);
					 
					 response.sendRedirect("cart.jsp");
				}
			}
		}
		
		if(action.equals("dec")) {
			for(Cart c: cart_list) {
				if(c.getId()==id && c.getQuantity()>=1) {
					int quantity =c.getQuantity();
					
					quantity--;
					 c.setQuantity(quantity);
					 
					break;
				}
			}
			 response.sendRedirect("cart.jsp");
		}
		
	}
		 
	}

}
