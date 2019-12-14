<%@page import="java.util.List"%>
<%@page import="com.capgemini.onlinemedicalstorespringmvc.beans.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page session="false" %>

<%	List<ProductBean> productList = (List<ProductBean>) request.getAttribute("productList");
	String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="./adminHome">Home</a>
	<br><br>
	<fieldset>
		<legend style="background: navy; color: white;">Search Product Based On Category</legend>
		<form action="./searchCategoryProducts" method="get">
			<table>
				<tr>
					<td>Enter Product Category</td>
					<td> : </td>
					<td><input type="text" name="category" required></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><br>
						<input type="submit" value="Search">
					</td>
				</tr>
			</table>
		</form>
	</fieldset>
	<br>
	<% if (productList != null && !productList.isEmpty()) { %>
	<h3 style="color: green;">Product Category Found...</h3>
	<div align="center">
		<table border="1">
			<tr>
				<th>Product Id</th>
				<th>Product Name</th>
				<th>Product Brand</th>
				<th>Category</th>
				<th>Price</th>
				<th>Details</th>
				<th>Avaialable</th>
			</tr>
			<% for (ProductBean productBean : productList) { %>
				<tr>
				<td><%=productBean.getProductId()%></td>
				<td><%=productBean.getProductName()%></td>
				<td><%=productBean.getProductBrand()%></td>
				<td><%=productBean.getCategory()%></td>
				<td><%=productBean.getPrice()%></td>
				<td><%=productBean.getDetails()%></td>
				<td><%=productBean.getAvailable()%></td>
				</tr>
			<% } %>	
		</table>
	</div>
	<% } else { %>
		<h3 style="color: red;">Product Category Not Found!!!</h3>
	<% } %>
</body>
</html>