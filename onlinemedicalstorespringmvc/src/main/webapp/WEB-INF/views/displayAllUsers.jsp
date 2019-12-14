<%@page
	import="com.capgemini.onlinemedicalstorespringmvc.beans.UserBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>

<%
	List<UserBean> usersList = (List<UserBean>) request.getAttribute("usersList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show All Users</title>
</head>
<body>
	<a href="./adminHome">Home</a>
	<br>
	<br>
	<%
		if (usersList != null && !usersList.isEmpty()) {
	%>
	<div align="center">
		<table border="1">
			<tr>
				<th>User ID :</th>
				<th>User Name :</th>
				<th>Email :</th>
				<th>Mobile Number :</th>
			</tr>
			<%
				for (UserBean userBean : usersList) {
			%>
			<tr>
				<td><%=userBean.getUserId()%></td>
				<td><%=userBean.getUserName()%></td>
				<td><%=userBean.getEmail()%></td>
				<td><%=userBean.getMobileNumber()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>

	<%
		} else {
	%>
	<h3 style="color: red;">Unable To Show All Users!!!</h3>
	<%
		}
	%>
</body>
</html>