<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
int count = (int)request.getAttribute("count");
int price = (int)request.getAttribute("Price");
if(count>5 && count<10){
	price = price + (int)(price*0.1);
}
else if(count>=10){
	price = price + (int)(price*0.2);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Book-Details</h3>
<hr>
<table>
<tr><td>BCode</td><td><%=request.getAttribute("BCode")%></td></tr>
<tr><td>Title</td><td><%=request.getAttribute("Title") %>}</td></tr>
<tr><td>Author</td><td><%=request.getAttribute("Author") %></td></tr>
<tr><td>Subject</td><td><%=request.getAttribute("Subject") %></td></tr>
<tr><td>Price</td><td><%=price%></td></tr>
</table>
<hr>
<form action="CartManager?price=<%=price%>" method="get">
<input type="hidden" name="BCode" value='<%=(String)request.getAttribute("BCode")%>'>
<input type="submit" value="Add To Cart">
</form>
<a href=SubjectPageServlet>Subject-Page</a><br>
<a href=buyerpage.jsp>Buyer-Page</a><br>
</body>
</html>