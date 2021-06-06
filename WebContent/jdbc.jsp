<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Class.forName("com.mysql.jdbc.Driver");
	String url = "jdbc:mysql://localhost:3306/test2?serverTimezone=UTC";
	Connection conn = DriverManager.getConnection(url,"root","Kidragon!13");
	
	PreparedStatement stmt = conn.prepareStatement("insert into member values(?,?,?);");
	stmt.setString(1, "kiyong");
	stmt.setString(2, "1234");
	stmt.setString(3, "kiyong");
	stmt.executeUpdate();
	
	stmt.close();
	conn.close();
%>
okay!!
</body>
</html>