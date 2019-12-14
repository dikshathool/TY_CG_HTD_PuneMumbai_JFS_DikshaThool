<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page session="false"%>

<%
	String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Product</title>
</head>
<body>
	<%
		if (msg != null && !msg.isEmpty()) {
	%>
	<h3><%=msg%></h3>
	<%
		}
	%>
	<fieldset>
		<legend >Update Product</legend>
		<form action="./updateProduct" method="post">
			<table>
				<tr>
					<td>Product Id</td>
					<td>:</td>
					<td><input type="number" name="productId" required></td>
				</tr>
				<tr>
					<td>Product Name</td>
					<td>:</td>
					<td><input type="text" name="productName" required></td>
				</tr>
				<tr>
					<td>Product Brand</td>
					<td>:</td>
					<td><input type="text" name="productBrand" required></td>
				</tr>
				<tr>
					<td>Category</td>
					<td>:</td>
					<td><input type="text" name="category" required></td>
				</tr>
				<tr>
					<td>Price</td>
					<td>:</td>
					<td><input type="number" name="price" required></td>
				</tr>
				<tr>
					<td>Details</td>
					<td>:</td>
					<td><input type="text" name="details" required></td>
				</tr>
				<tr>
					<td>Available</td>
					<td>:</td>
					<td><input type="text" name="available" required></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><br> <input type="submit"
						value="Add Product"></td>
				</tr>
			</table>
		</form>
	</fieldset>
</body>
</html>