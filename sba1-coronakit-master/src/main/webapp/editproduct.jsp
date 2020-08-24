<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Edit Product(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<%
		// fetch the shared data
		String id = (String) request.getAttribute("id");
	%>
<form action="admin?action=updateproduct&id=<%=id%>" method="post">
	<div>
		<div><label>Product name</label> <input type="text" name="name"></div>
		<br/>
		<div><label>Product Cost</label> <input type="number" name="cost"></div>
		<br/>
		<div><label>Product Description</label> <input type="text" name="desc"></div>
		<div> <input type="submit" value="Update"> </div>
	</div>
</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>