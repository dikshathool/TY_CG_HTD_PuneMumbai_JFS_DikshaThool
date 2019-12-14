<%@page import="com.capgemini.onlinemedicalstorespringmvc.beans.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% HttpSession se = request.getSession(false);
	UserBean userBean = (UserBean) session.getAttribute("userBean"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Medical Store</title>
</head>
<body>
	<h3>WELCOME <%=userBean.getUserName() %>!</h3>
	<h3><a href="./showAllProducts">Show All Products</a><br>
	<a href="./placeOrder">Place Order Or To Perform Operation On Cart</a><br>
	<a href="./discussionBoard">See Admin Responses And Give Your query</a><br>
	<a href="./logout">Logout</a>
</body>
</html>