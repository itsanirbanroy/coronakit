<%@page import="com.iiht.evaluation.coronokit.model.ProductMaster"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<br/>
	<a href="user?action=showkit"><button>View Kit</button></a>
	<br/>
	<%
		// fetch the shared data
		List<ProductMaster> products =  (List<ProductMaster>) request.getAttribute("products");
	%>
	<table border="1" width="100%">
		<thead>
			<th>NAME</th>
			<th>COST</th>
			<th>DESCRIPTION</th>
			<th>QUANTITY</th>
			<th></th>
		</thead>
		
		<tbody>
			<% for(ProductMaster product : products) { %>
			<tr>
				<td><%=product.getProductName()%></td>
				<td><%=product.getCost()%></td>
				<td><%=product.getProductDescription()%></td>
				<td><input type="text" name="qty"></td>
				<td><a href="user?action=addnewitem&id=<%=product.getId()%>&name=<%=product.getProductName()%>&cost=<%=product.getCost()%>"><button>Add Item</button></a></td>
			</tr>
			<% } %>
		</tbody>
	</table>



<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>