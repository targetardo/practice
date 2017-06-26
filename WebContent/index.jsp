<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "controller.JPAController"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Циклінський П. КІ-141</title>
</head>
<body>
<center>
	<h3>Робота з базою даних Салон весільних послуг</h3>
	<iframe name="forMenu"src="menuTable.html"width="400"height="460">
	</iframe>
	<iframe name="forTable"width="840"height="460"></iframe>
	<br>
	<iframe name="forOperation"src="menuOperation.html"width="1250"
		height="100"></iframe>
	<br>
	<iframe name="forDialog"width="1250"height="250"></iframe>
</center>
		<%
				session.setAttribute("controller", new JPAController());
		%>
</body>
</html>