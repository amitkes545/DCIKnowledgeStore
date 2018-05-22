<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.kes.kote.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Change Password</title>
<link type="text/css" href="../css/style.css" rel="stylesheet" />
<link type="text/css" href="../css/responsive.css" rel="stylesheet" />

<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/custom.js"></script>

<%
SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");

%>

<script type="text/javascript">    
   window.history.forward();
   function noBack() { 
       window.history.forward(); 
   }
</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">

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
        	<h3>Change Password</h3>
            <form id="change-password-form" action="/KOTE/ControllerServlet/UserService?From=ChangePwd" method="post" >
            	<ul>
                <li><input type="password" name="password" placeholder="New Password" value="" id="password" class="margin20 passhidetext"/>
                	<span class="passhideshow"></span>
                </li>
                <li><input type="password" placeholder="Re-enter New Password" name="confirmpassword" value="" id="confirmpassword" class="passhidetext1"/>
                	<span class="passhideshow1"></span>
                </li>
                <li><input type="submit" value="SUBMIT" class="button" onClick="changepswdval()"/></li>
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
<% String SuccessMsg=""; if(util.getsuccessmsg()!=null) SuccessMsg=util.getsuccessmsg();
String FailureMsg=""; if(util.getfailedmsg()!=null) FailureMsg=util.getfailedmsg();  

if(SuccessMsg.trim().length()>0)
{ %>
	<div class="success-notification"><%=SuccessMsg %></div>
<%	util.resetmsgs(); } %>
	
<% if(FailureMsg.trim().length()>0)
{ %>
	<div class="failure-notification"><%=FailureMsg %></div>
<%	util.resetmsgs(); } %>
</body>
</html>