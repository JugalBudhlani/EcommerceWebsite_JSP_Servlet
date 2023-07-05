<%@page import="com.demo.connectios.DBConnection"%>
<%@page import="com.demo.Dao.ProductsDao"%>
<%@page import="java.util.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.demo.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
Users auth = (Users) request.getSession().getAttribute("auth");

if (auth != null) {
	request.setAttribute("auth",auth);

}

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

List<Cart> cartProducts = null;
if (cart_list != null) {
	ProductsDao pdao = new ProductsDao(DBConnection.getConnection());
	cartProducts = pdao.getCartProdducts(cart_list);
	
	double total=pdao.getTotalPrice(cart_list);
   
	 request.setAttribute("cart_list", cart_list);

    request.setAttribute("total", total);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


<title>Shopping Cart</title>
<%@include file="includes/head.jsp"%>
<style type="text/css">
.table  tbody td {
	vartical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	fon-size: 25px;
}
</style>

</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="d-flex py-3">
			<h3>Total Price:$ ${ (total>0)?total:0 }</h3>
			<a class="mx-3 btn btn-primary" href="CheckOutServlet">Check out</a>
		</div>
		<table class="table table-loght">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			
			<%
			
			if(cart_list!=null){
				
				
				for(Cart c:cartProducts){%>

					<tr>
					<td><%= c.getName() %></td>
					<td><%= c.getCategory() %></td>
					<td>$<%= c.getPrice() %></td>
					<td>
						<form action="BuyServlet" method="post" class="form-inline">

							<input type="hidden" name="id" value="<%= c.getId() %>" class="form-input">
							<div class="form-group d-flex justiy-content-between w-50">

								<a class="btn btn-sm btn-decre" href="IncrementDecrementServlet?action=dec&id=<%= c.getId()%>"><i
									class="fas fa-minus-square"></i></a> 
									<input type="text"
									name="quantity" value="<%= c.getQuantity() %>" class="form-control w-50"
									readonly="readonly"> <a class="btn btn-sm btn-incre"
									href="IncrementDecrementServlet?action=inc&id=<%= c.getId()%>"><i class="fas fa-plus-square"></i></a>
							</div>
<button  type="submit" class="btn btn-primary vtn-sm">Buy</button>
						</form>
					</td>
					<td><a class="btn btn-sm btn-danger" href="RemoveServlet?id=<%= c.getId()%>">Remove</a></td>
				</tr>
					
				<% }
			}
			
			%>

			</tbody>
		</table>
	</div>


	<%@include file="includes/footer.jsp"%>
</body>
</html>