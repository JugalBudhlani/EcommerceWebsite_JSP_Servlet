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
 * Servlet implementation class BuyServlet
 */
@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			PrintWriter out=response.getWriter();
			
			out.print("This is Buy now ");
			
			SimpleDateFormat formatt=new SimpleDateFormat("yyyy-MM-dd");
			
			Date date=new Date();
			
			
			Users auth=(Users) request.getSession().getAttribute("auth");
			
			if(auth !=null) {
				
				String productID=request.getParameter("id");
				
				int prodcutsQuantity=Integer.parseInt(request.getParameter("quantity"));
				
				if(prodcutsQuantity<=0) {
					prodcutsQuantity=1;
					
				}
				
				Order ordermodel=new Order();
				
				ordermodel.setId(Integer.parseInt(productID));
				ordermodel.setUserID(auth.getId());
				ordermodel.setQuantity(prodcutsQuantity);
				ordermodel.setDate(formatt.format(date));
				
				OrderDao ordeDao=new OrderDao(DBConnection.getConnection());
				
		Boolean res=ordeDao.insertOrder(ordermodel);
				
				if(res) {
					
					ArrayList<Cart> cart_list=(ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					
					if(cart_list!=null) {
						for(Cart c:cart_list) {
							
						if(c.getId()== Integer.parseInt(productID)) {
							cart_list.remove(cart_list.indexOf(c));
							break;
						}
						}
						
					
						}
				response.sendRedirect("orders.jsp");
				
				}else {
					out.print("order failss");
				}
				
			}else {
				response.sendRedirect("login.jsp");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
