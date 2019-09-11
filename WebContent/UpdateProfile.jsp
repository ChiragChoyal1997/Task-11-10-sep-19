<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.ssi.utility.DBConnect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String userid = (String)session.getAttribute("UserId");
DBConnect connection = DBConnect.connect(application);
Connection con = (Connection)connection.getConnection();
String sql = "Select * from users where userid='"+userid+"'";
PreparedStatement stmt = con.prepareStatement(sql);
ResultSet rs = stmt.executeQuery();
rs.next();
%>
<form action="UpdateUserProfile" method="Post">
<table>
<tr><td>UserId</td><td><input type="text" name="UserId" value='<%=(String)rs.getString(1) %>'></td></tr>
<tr><td>Password</td><td><input type="text" name="Password" value='<%=(String)rs.getString(2) %>'></td></tr>
<tr><td>UserName</td><td><input type="text" name="UserName" value='<%=(String)rs.getString(3) %>'></td></tr>
<tr><td>Address</td><td><textarea  name="Address"><%=(String)rs.getString(4) %></textarea></td></tr>
<tr><td>Mobile</td><td><input type="text" name="Mobile" value='<%=(String)rs.getString(5) %>'></td></tr>
<tr><td>Email</td><td><input type="text" name="Email" value='<%=(String)rs.getString(6) %>'></td></tr>
<tr><td><input type="submit" /></td></tr>
</table>
</form>
</body>
</html>