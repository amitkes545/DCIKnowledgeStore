<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.kes.kote.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
util.resetEverthing();
util.setfailedmsg(PropertiesUtil.getMessageProperty("session.failed"));
response.sendRedirect("/KOTE/JSP/login.jsp");

%>

</body>
</html>