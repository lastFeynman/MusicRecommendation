<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录后主页面</title>

</head>
<body bgcolor="#6db1fc">
	当前登录用户名：
	<%
	HttpSession s = request.getSession();
%>
	<%=(String) s.getAttribute("loginName")%>
<br/>
	<%@page import="java.util.ArrayList"%>
	<%@page import="Entity.User"%>

</body>
</html>