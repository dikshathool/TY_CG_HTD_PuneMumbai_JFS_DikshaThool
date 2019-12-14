<%@page import="com.capgemini.onlinemedicalstorespringmvc.beans.CartBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%
	List<CartBean> cartList = (List<CartBean>) request.getAttribute("cartList");
	double price = (double) request.getAttribute("price");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Cart Products</title>
</head>
<body>
	<a href="./userHome">Home</a>
	<br>
	<br>
	<%
		if (cartList != null && !cartList.isEmpty()) {
	%>
	<div align="center">
		<table border="1">
			<tr>
				<th>Cart ID</th>
				<th>User ID</th>
				<th>User Name</th>
				<th>Product ID</th>
				<th>Product Name</th>
				<th>Price</th>
			</tr>
			<%
				for (CartBean cartBean : cartList) {
			%>
			<tr>
				<td><%=cartBean.getCartId()%></td>
				<td><%=cartBean.getUserId()%></td>
				<td><%=cartBean.getUserName()%></td>
				<td><%=cartBean.getProductId()%></td>
				<td><%=cartBean.getProductName()%></td>
				<td><%=cartBean.getPrice()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	<%
		} else {
	%>
	<h3 style="color: red;">Unable To Show All Products!!!</h3>
	<%
		}
	%>
</body>
</html>