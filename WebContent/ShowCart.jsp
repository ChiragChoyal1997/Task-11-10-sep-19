<%@page import="com.ssi.utility.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>My Cart</h2>
<%
if(session.getAttribute("CartList") != null){
ArrayList<Product> rs = (ArrayList<Product>)session.getAttribute("CartList");
if(rs.size()>0){
%>
<table border=2>
<tr><th>BCode</th><th>Title</th><th>Author</th><th>Subject</th><th>Price</th><th></th></tr>
<%for(int i=0; i<rs.size(); i++){ %>
<tr><td><%=rs.get(i).getBcode() %></td><td><%=rs.get(i).getTitle() %></td><td><%=rs.get(i).getSubject() %></td><td><%=rs.get(i).getAuthor() %></td><td><%=rs.get(i).getPrice() %></td><td><a href="RemoveItem?index=<%=i%>">Remove</a></td></tr>
<%
}
%>
</table>
<%
}
else{
	out.println("Cart is Empty !");
}
}
else{
	out.println("Cart is Empty !");
}
%>
<br>
<hr>
<br>
<a href="buyerpage.jsp">Buyer-DashBoard</a>
</body>
</html>