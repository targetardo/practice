<%@ page import="tpp4.Product"%>
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
		 продукти <select size="5"name="productId">
			<%
			JPAController controller = 
			(JPAController) session.getAttribute("controller");
				Class<Product> c = Product.class;
				for (Object x : controller.getObjectList(c)) {
					Product obj = (Product) x;
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
