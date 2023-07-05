package com.demo.servletAll;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.Dao.OrderDao;
import com.demo.connectios.DBConnection;


/**
 * Servlet implementation class CancelOrderServlet
 */
@WebServlet("/CancelOrderServlet")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try(PrintWriter out = response.getWriter()) {
			String id = request.getParameter("id");
			if(id != null) {
				OrderDao orderDao = new OrderDao(DBConnection.getConnection());
				orderDao.cancelOrderMethod(Integer.parseInt(id));
			}
			response.sendRedirect("orders.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

}
	}