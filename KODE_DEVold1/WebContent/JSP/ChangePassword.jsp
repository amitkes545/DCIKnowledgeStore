<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../JS/ValidateChangePassword.js"></script>
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
</head>
<style type="text/css">

.su{
		    color: #008000;
    font-size: 17px;
    font-weight: bold;
    top: 14%;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
</style>
<% /* String userid=(String)request.getParameter("userId");
    //System.out.println("user id is:"+userid); */
    
	
		String username=(String)session.getAttribute("username");
		String userid=(String)session.getAttribute("userid");
		String orgid=(String)session.getAttribute("orgid");
%>
<body>
<!-- <form action="/KODE_DEV/ControllerServlet/ChangePasswordServlet"
		method="post"> -->
		<div class="container">
		<%@ include file="../JSP/all_one_header.jsp"%>

		<div style="clear: both;"></div>
		<div class="search_details" style="height: 270px; width: 450px">

			<p class="strong_left">Change Password</p>
		
		<form name="passwordform" method="post">
		<% if (request.getAttribute("Success")!= null) { 
					
					%>
					<p class="su"><%= request.getAttribute("Success") %></p>					
					<%
				} else  if (request.getAttribute("Failure")!= null) { 
					
					%>
					<p style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("Failure") %></p>					
					<%
				} 	
			%>
<center>
<table>
 <tr><td>Current Password</td>
 <td>:</td>
 <td><input type="password" name="cpwd" id="oldpwd"></td><tr>
 <tr><td>New Password</td>
 <td>:</td>
   <td><input type="password" name="npwd" id="npwd"></td></tr>
   
   <tr><td>Conform Password</td>
   <td>:</td>
      <td><input type="password" name="cnpwd" id="cnpwd"></td></tr>
      <tr height="15px"></tr>
      <tr>
      <td></td>
      <td></td>
      <td><input class="search_search_btn" style="width: 100px" type="button" value="save" onclick="Validatepwd()">
      <a class="back" style="color: #c2c2c2; text-align: left;" href="../JSP/Home.jsp">Back</a>
      </td>
	</tr>
   </table>
   <input type="hidden" name="userid" value="<%=userid%>">
</center>
</form>
</div>
	</div>
</body>
</html>

