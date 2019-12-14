<%@page import="com.capgemini.onlinemedicalstorespringmvc.beans.MessageBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page session="false"%>
<%
	List<MessageBean> messageList = (List<MessageBean>) request.getAttribute("messageList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show User Messages</title>
</head>
<body>
	<a href="./home">Home</a>
	<br>
	<br>
	<%
		if (messageList != null && !messageList.isEmpty()) {
	%>
	<div align="center">
		<table border="1">
			<tr>
				<th>User Id</th>
				<th>Message</th>
			</tr>
			<%
				for (MessageBean messageBean : messageList) {
			%>
			<tr>
				<td><%=messageBean.getUserId()%></td>
				<td><%=messageBean.getMessage()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	<%
		} else {
	%>
	<h3 style="color: red;">Unable To Fetch User Messages!!!</h3>
	<%
		}
	%>
<a href="./giveUserReply">Give Reply To User Messages</a>
	
</body>
</html>