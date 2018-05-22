<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.kes.kote.util.*" %>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Rest Password</title>
<link type="text/css" href="../css/style.css" rel="stylesheet" />
<link type="text/css" href="../css/responsive.css" rel="stylesheet" />

<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/custom.js"></script>
<%
	String Email=request.getParameter("Email").toString().trim();
SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
%>
</head>
<body>
<header>
	<div class="container">
    	<div class="client_logo left">
        	<a href="#"><img src="../images/logo.png" alt="logo" /></a>
        </div>
    </div>
    <div class="header_banner">
    	<div class="container"></div>
    </div>
</header>
<section class="middle-column row">
	<div class="container">
    	<div class="change-password-box">
        	<h3>Reset Password</h3>
            <form id="change-password-form" action="/KOTE/ControllerServlet/UserService?From=resetPwd" method="post">
            	<ul>
                <li><input type="text" name="randompassword" placeholder="Type the password send in your email"  id="ramdompassword" class="margbot20"/></li>
                <li><input type="password" name="password" placeholder="New Password" id="password"class="margbot20 passhidetext"/><span class="passhideshow"></span></li>
                <li><input type="password" placeholder="Re-enter New Password" name="confirmpassword" id="confirmpassword" class="passhidetext1"/><span class="passhideshow1"></span></li>
                <li><input type="submit" value="SUBMIT" class="button"  onclick="changepswdval()" /></li>
                <li> <input type="hidden" name="usrEmail" id="usrEmail" value="<%=Email %>"  /></li>
                </ul>
            </form>
        </div>
    </div>
</section>
<footer>
  <center>
    <div class="container">
    	<p>Copyright &copy; 2017. Kompac Education Systems Pvt. Ltd. All Rights Reserved.</p>
    </div>
  </center>
</footer>
<%  String SuccessMsg=""; if(util.getsuccessmsg()!=null) SuccessMsg=util.getsuccessmsg();
String FailureMsg=""; if(util.getfailedmsg()!=null) FailureMsg=util.getfailedmsg();  %>

<% if(SuccessMsg.trim().length()>0)
{ %>
	<div class="success-notification"><%=SuccessMsg %></div>
<%	util.resetmsgs(); } %>
	
<% if(FailureMsg.trim().length()>0)
{ %>
	<div class="failure-notification"><%=FailureMsg %></div>
<%	util.resetmsgs(); } %>
</body>
<script>


</script>
</html>
