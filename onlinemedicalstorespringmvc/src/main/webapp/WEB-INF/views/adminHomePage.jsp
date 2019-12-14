<%@page import="com.capgemini.onlinemedicalstorespringmvc.beans.AdminBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>
<% HttpSession session = request.getSession(false);
	AdminBean adminBean = (AdminBean) session.getAttribute("adminBean"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Medical Store</title>
</head>
<body>
	<h3>WELCOME <%=adminBean.getAdminName() %>!</h3>
	<h3><a href="./userOperationPage">Operations On User</a><br>
	<a href="./productOperationPage">Operation On Product</a><br>
    <a href="./userMessages">User Queries And Give Reply</a><br>
	<a href="./logout">Logout</a></h3>
</body>
</html>