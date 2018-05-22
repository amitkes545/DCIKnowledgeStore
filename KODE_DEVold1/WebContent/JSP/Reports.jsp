<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
		<link href="../CSS/owner.css" rel="stylesheet"></link>
		<link href="../CSS/global.css" rel="stylesheet"></link>
		<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
		<style type="text/css">
		td a{color: #c2c2c2 !important;}
		</style>
</head>
<%-- <%
	HttpSession mess = request.getSession();
	String msg = "";
	msg = (String) mess.getAttribute("MsgValue");
	String username=(String)mess.getAttribute("username");
%> --%>
<body>
<div class="container">
 <%@include file="all_one_header.jsp" %>
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> --%> 
		
			<div style="clear: both;"></div>
			<div class="customer_create" style="height: 200px;">
			 
 <p class="strong">Reports</p>

<table>
  <tr height="30px"><td><a href="../JSP/UsersReports.jsp">UserReports</a>
  <tr height="30px"><td><a href="../JSP/AccessDetails.jsp">AccessDetails</a>
  <tr height="30px"><td><a href="../JSP/Home.jsp">Back</a>
  </table>
  </div>

       <%@include file="all_one_footer.jsp" %>		

</div>
 
</body>
</html>