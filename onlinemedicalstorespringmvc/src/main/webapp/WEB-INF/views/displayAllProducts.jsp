<%@page
	import="com.capgemini.onlinemedicalstorespringmvc.beans.ProductBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%
	List<ProductBean> productsList = (List<ProductBean>) request.getAttribute("productsList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display All Products</title>
</head>
<body>
	<a href="./adminHome">Home</a>
	<br>
	<br>
	<%
		if (productsList != null && !productsList.isEmpty()) {
	%>
	<div align="center">
		<table border="1">
			<tr>
				<th>Product Id</th>
				<th>Product Name</th>
				<th>Product Brand</th>
				<th>Category</th>
				<th>Price</th>
				<th>Details</th>
				<th>Available</th>
			</tr>
			<%
				for (ProductBean productBean : productsList) {
			%>
			<tr>
				<td><%=productBean.getProductId()%></td>
				<td><%=productBean.getProductName()%></td>
				<td><%=productBean.getProductBrand()%></td>
				<td><%=productBean.getCategory()%></td>
				<td><%=productBean.getPrice()%></td>
				<td><%=productBean.getDetails()%></td>
				<td><%=productBean.getAvailable()%></td>
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
	
	<fieldset>
	<form action="./totalBill" method="post">
		<table align="center">
			<tr>
				<td>Total Bill</td>
				<td>:</td>
				<td><input type="number" name="price" required></td>
			</tr>
		</table>
	</form>
	</fieldset>

</body>
</html>