package com.demo.servletAll;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.model.Cart;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCartServlet() {
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
		
		ArrayList<Cart> cartlist=new ArrayList<Cart>();
		
		int id=Integer.parseInt(request.getParameter("id"));
		Cart cart=new Cart();
		cart.setId(id);
		cart.setQuantity(1);
		
		HttpSession session=request.getSession();
		
		ArrayList<Cart> cart_List=(ArrayList<Cart>) session.getAttribute("cart-list");
		
		if(cart_List==null) {
			cartlist.add(cart);
			session.setAttribute("cart-list", cartlist);
			response.sendRedirect("index.jsp");
		}
		
		else {
			cartlist=cart_List;
			boolean ext=false;
			
			
			
			for(Cart c:cart_List)
			{
				if(c.getId()==id)
				{
					ext=true;
					out.print("<h3 style='color:crimson; text-align:center'>Item Already exits in Cart.<a href='cart.jsp'>Go to page</a></h3>");
				}
			}
			if(!ext)
			{
				cartlist.add(cart);
				response.sendRedirect("index.jsp");
			
			}
	
		}
		
		
		
	}

}
