<%@page import="com.capgemini.onlinemedicalstorespringmvc.beans.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>
<% HttpSession session = request.getSession(false);
	UserBean userBean = (UserBean) session.getAttribute("userBean");
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3><a href="./showAllUser">To Show All Users</a><br>
	<a href="./removeUserForm">To Remove User</a><br>	
</body>
</html>