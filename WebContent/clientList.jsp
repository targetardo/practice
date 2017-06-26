<%@ page import="tpp4.Client"%>
<%@ page import="controller.JPAController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPEhtml>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>studList</title>
</head>
<body>
	<center>
		клієнти <select size="5"name="clientId">
			<%
			JPAController controller = 
			(JPAController) session.getAttribute("controller");
				Class<Client> c = Client.class;
				for (Object x : controller.getObjectList(c)) {
					Client obj = (Client) x;
			%>
			<option>
				<%=obj.toString()%></option>
			<%
				}
			%>
		</select>
	</center>
</body>
</html>
