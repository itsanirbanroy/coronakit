<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-New User(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<form action="user?action=insertuser" method="post">
	<div>
		<div><label>User name</label> <input type="text" name="name"></div>
		<br/>
		<div><label>User email</label> <input type="text" name="email"></div>
		<br/>
		<div><label>User phone number</label> <input type="text" name="phn"></div>
		<div> <input type="submit" value="Add"> </div>
	</div>
</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>