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
<title>Insert title here</title>
</head>
<body>
	<fieldset>
		<legend>Delete Cart Product</legend>
		<form action="./removeCartProduct" method="get">
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
</body>
</html>