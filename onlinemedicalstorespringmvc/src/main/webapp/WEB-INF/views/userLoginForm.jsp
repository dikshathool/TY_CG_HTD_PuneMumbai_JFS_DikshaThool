<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>
<% String msg = (String) request.getAttribute("msg"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% if(msg != null && !msg.isEmpty()) { %>
	<h3 style="color: red;"><%=msg%></h3>
	<%} %>
	<fieldset>
		<legend>Login here!!!</legend>
		<!-- When submit form ,it comes to /empLogin -->
			<form action="./userLogin" method="post">
				<table>
					<tr>
						<td>Email </td>
						<td> : </td>
						<td><input type="email" name="email"> </td>
					</tr>
					<tr>
						<td>Password </td>
						<td> : </td>
						<td><input type="password" name="password"> </td>
					</tr>
					<tr>
						<td colspan="3" align="center"><br>
							<input type="submit" name="login">
					    </td>
					</tr>
				</table>
			</form>
	</fieldset>
</body>
</html>