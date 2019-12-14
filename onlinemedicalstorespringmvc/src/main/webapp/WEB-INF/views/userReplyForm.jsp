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
		<legend style="background: navy; color: white;">User Reply</legend>
		<form action="./userReplyForm" method="post">
			<table border="1">
				<tr>
					<td>Enter User ID</td>
					<td>:</td>
					<td><input type="number" name="userId" required></td>
				</tr>
				<tr>
					<td>Select Option : </td>
					<select name="type">
						<option value="Answer">Answer</option>
						<option value="Question">Question</option>
					</select>
				</tr>
				<tr>
					<td>Enter message</td>
					<td>:</td>
					<td><input type="text" name="message" required></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><br> <input type="submit"
						value="Reply"></td>
				</tr>
			</table>
		</form>
	</fieldset>
	<br>
	<br>
	<%
		if (msg != null && !msg.isEmpty()) {
	%>
	<h3 style="color: red;"><%=msg%></h3>
	<%
		}
	%>
</body>
</html>