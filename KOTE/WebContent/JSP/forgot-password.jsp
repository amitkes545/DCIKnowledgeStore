<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.kes.kote.util.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Forgot Password</title>
<link type="text/css" href="../css/style.css" rel="stylesheet" />
<link type="text/css" href="../css/responsive.css" rel="stylesheet" />
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/custom.js"></script>
</head>
<%
SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
%>
<body class="login" onload='document.forgotpswd.usernm.focus()'>
<header>
  <div class="container row">
    <div class="logo left"><a href="#"><img src="../images/logo.png" alt="KES Logo" /></a></div>
  </div>
</header>
<section class="middle-container">
  <div class="container row">
    <div class="login-form">
      <h1>Forgot Password</h1>
      <form class="forgotpswd" action="/KOTE/ControllerServlet/UserService?From=ForgotPwd" method="post" >
        <div class="forgotpswd-box">
          <input type="email" name="usernm" placeholder="Enter UserID / Email" id="email" value="" />
        </div>
        <div class="btnblock">
          <input type="submit" value="reset password" name="Login" class="button" />
        </div>
        
      </form>
    </div>
  </div>
</section>
<footer>
<%  String SuccessMsg=""; if(util.getsuccessmsg()!=null) SuccessMsg=util.getsuccessmsg();
String FailureMsg=""; if(util.getfailedmsg()!=null) FailureMsg=util.getfailedmsg();  

if(SuccessMsg.trim().length()>0)
{ %>
	<div class="success-notification"><%=SuccessMsg %></div>
<%	util.resetmsgs(); } %>
	
<% if(FailureMsg.trim().length()>0)
{ %>
	<div class="failure-notification"><%=FailureMsg %></div>
<%	util.resetmsgs(); } %>
  <center>
    <div class="container">
      <p>Copyright &copy; 2017. Kompac Education Systems Pvt. Ltd. All Rights Reserved.</p>
    </div>
  </center>
</footer>
</body>
</html>