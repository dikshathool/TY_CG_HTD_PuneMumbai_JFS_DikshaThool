<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>

<% String msg = (String) request.getAttribute("msg"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
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
		<legend>Register here!!!</legend>
		<form action="./userRegister" method="post">
			<table>
				<tr>
					<td>User Id</td>
					<td>:</td>
					<td><input type="number" name="userId" required></td>
				</tr>
				<tr>
					<td>User Name</td>
					<td>:</td>
					<td><input type="text" name="userName" required></td>
				</tr>
				<tr>
					<td>email</td>
					<td>:</td>
					<td><input type="text" name="email" required></td>
				</tr>
				<tr>
					<td>Mobile Number</td>
					<td>:</td>
					<td><input type="number" name="mobileNumber" required></td>
				</tr>
				<tr>
					<td>Password</td>
					<td>:</td>
					<td><input type="password" name="password" required></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><br> <input type="submit"
						value="Add"></td>
				</tr>
			</table>
		</form>
	</fieldset>
</body>
</html>