package com.demo.servletAll;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.Dao.UserDao;
import com.demo.connectios.DBConnection;
import com.demo.model.Users;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		
		
		
	String emails=request.getParameter("email");
	String pass=request.getParameter("password");
	
	try {
		UserDao udo=new UserDao(DBConnection.getConnection());
		
		Users user=udo.userLogin(emails, pass);
		
		if(user!=null) {
			out.print("User login ");
			request.getSession().setAttribute("auth", user);
			response.sendRedirect("index.jsp");
		}
		else {
			out.print("user login failed ..");
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
