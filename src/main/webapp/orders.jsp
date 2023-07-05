<%@page import="com.demo.connectios.DBConnection"%>
<%@page import="com.demo.Dao.OrderDao"%>
<%@page import="com.demo.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<% Users auth=(Users) request.getSession().getAttribute("auth");
	List<Order>order =null;
	
    if(auth!=null){
   request.setAttribute("auth", auth);
    order  =new OrderDao(DBConnection.getConnection()).userOrders(auth.getId());
    
    }
    
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");


    if (cart_list != null) {

    	request.setAttribute("cart_list", cart_list);
    }
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


<title>Order Page</title>
<%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<div class="container">
<div class="card-header my-3">All Order</div>

<table class="table table-light">
<thead>
<tr>
<th scope="col">Date</th>
<th scope="col">Name</th>
<th scope="col">Category</th>
<th scope="col">Quantity</th>
<th scope="col">Price</th>
<th scope="col">Cancel</th>
</tr>
</thead>
<tbody>
<%
if(order!=null){
	for(Order o:order){%>
	<tr>
		<td><%= o.getDate() %></td>
		<td><%= o.getName() %></td>
		<td><%= o.getCategory() %></td>
		<td><%= o.getQuantity()%></td>
		<td><%= o.getPrice() %></td>
		<td><a class="btn btn-sm btn-danger" href="CancelOrderServlet?id=<%=o.getOrderID()%>">Cancel Order</a></td>

</tr>
	
	<% }
}
%>
</tbody>
</table>


</div>


<%@include file="includes/footer.jsp" %>
</body>
</html>