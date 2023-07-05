<%@page import="com.demo.model.Products"%>
<%@page import="java.util.*"%>
<%@page import="com.demo.Dao.ProductsDao"%>
<%@page import="com.demo.connectios.DBConnection"%>
<%@page import="com.demo.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
// sessions 
Users auth = (Users) request.getSession().getAttribute("auth");

if (auth != null) {
	request.setAttribute("auth", auth);
}
// products
ProductsDao pdo = new ProductsDao(DBConnection.getConnection());
List<Products> list = pdo.getAllProducts();

//ArryList
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");


if (cart_list != null) {

	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


<title>Online Shopping Project</title>
<%@include file="includes/head.jsp"%>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
			<%
			if (!list.isEmpty()) 
			{
				  for (Products p : list){%>
			<div class="col-md-3 my-3">
			
			<div class="card w-100" style="width: 18rem;">
				
				<img class="card-img-top" src="images/<%=p.getImages()%>"alt="Card image cap">
					
					<div class="card-body">
					
						<h5 class="card-title"><%=p.getName()%></h5>
						
						<h6 class="price">Price:$<%=p.getPrice()%></h6>
						
						<h6 class="category">Category :<%=p.getCategory()%></h6>
						
						<div class="mt-3 d-flex justify-content-between">
						
						<a href="CartServlet?id=<%=p.getId() %>"class="btn btn-dark">Add to Cart</a> 
							
						<a href="BuyServlet?quantity=1&id=<%= p.getId() %>"class="btn btn-primary">Buy Now</a>
							
						</div>
					</div>
				</div>
			</div>
<%}}%>
	 </div>
	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>