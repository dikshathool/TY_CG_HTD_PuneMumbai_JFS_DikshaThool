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
<title>Remove Cart Product</title>
</head>
<body>
	<fieldset>
		<legend>Delete Cart Product</legend>
		<form action="./removeProduct" method="post">
			<table border="1" align="center">
				<tr>
					<td>Enter Cart ID</td>
					<td>:</td>
					<td><input type="number" name="cartId" required></td>
				</tr>
				<tr>
					<td colspan="3" align="right"><br> <input type="submit"
						style="color: red;" value="Delete"></td>
				</tr>
			</table>
		</form>
	</fieldset>
	<% if (msg != null && !msg.isEmpty()) { %>
		<h3 style="color: red;"><%=msg%></h3>
	<% } %>
</body>
</html>