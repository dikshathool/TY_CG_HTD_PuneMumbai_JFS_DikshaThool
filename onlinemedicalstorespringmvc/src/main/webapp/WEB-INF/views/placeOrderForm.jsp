<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>
<%
	String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Place Order</title>
</head>
<body>
	<h4><a href="./billing" style="color: blue">Move Further For Payment Purpose</a></h4>
	<%
		if (msg != null && !msg.isEmpty()) {
	%>
	<h3 style="color: green;"><%=msg%></h3>
	<%
		}
	%>
	<h4 align="center">Add Product</h4>
	<form action="./addProductIntoCart" method="post">
		<table align="center">
			<tr>
				<td>Enter User Id</td>
				<td>:</td>
				<td><input type="number" name="userId" required></td>
			</tr>
			<tr>
				<td>Product Id</td>
				<td>:</td>
				<td><input type="text" name="productId" required></td>
			</tr>
			<tr>
				<td colspan="3" align="right"><br> <br> <input
					type="submit" style="color: blue;" value="Add "></td>
			</tr>
		</table>
	</form>
	<br>
	
	<h3><a href="./showAllCartProducts">Show All Products From Cart</a><br>
	<a href="./removeCartProductForm">Remove Product From Cart</a><br></h3>	
</body>
</html>