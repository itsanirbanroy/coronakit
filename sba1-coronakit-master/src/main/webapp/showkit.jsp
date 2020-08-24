<%@page import="com.iiht.evaluation.coronokit.model.KitDetail"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="javax.servlet.http.HttpSession"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-My Kit(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<%
		//HttpSession session = request.getSession();	
		//HashMap<String, String> user = (HashMap<String, String>) session.getAttribute("user");
		
		// fetch the shared data
		List<KitDetail> products =  (List<KitDetail>) request.getAttribute("kitdata");
		HashMap<String, String> map1 = new HashMap();
		HashMap<String, String> map2 = new HashMap();
		for(KitDetail product : products) {
			int id = product.getProductId();
			String name = product.getName();
			int cost = product.getAmount();
			
			map1.put(String.valueOf(id), name);
			
			if(map2.containsKey(String.valueOf(id))){
				String val = map2.get(String.valueOf(id));
				cost =  Integer.parseInt(val)+cost;
			}
			map2.put(String.valueOf(id), String.valueOf(cost));
		}
		
	%>
<table border="1" width="100%">
		<thead>
			<th>ID</th>
			<th>Name</th>
			<th>Cost</th>
		</thead>
		<tbody>
		<% for (Map.Entry<String,String> entry : map1.entrySet()) {
			String key = entry.getKey();
			String name = entry.getValue();
			String amount = map2.get(key);%>
			<tr>
				<td><%=key%></td>
				<td><%=name%></td>
				<td><%=amount%></td>
			</tr>
		<% } %>	
		</tbody>
	</table>
	<a href="user?action=placeorder"><button>Place Order</button></a>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>