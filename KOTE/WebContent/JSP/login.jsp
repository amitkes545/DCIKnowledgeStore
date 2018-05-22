<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.kes.kote.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Welcome to KOTE</title>
<link type="text/css" href="/KOTE/css/style.css" rel="stylesheet" />
<link type="text/css" href="/KOTE/css/responsive.css" rel="stylesheet" />
<%-- ${pageContext.request.contextPath} --%>
<script type="text/javascript" src="/KOTE/js/jquery.min.js"></script>
<script type="text/javascript" src="/KOTE/js/jquery.validate.js"></script>
<script type="text/javascript" src="/KOTE/js/custom.js"></script>

</head>
<%
SessionUtil util=null;
if(session.getAttribute("SessionUtil")!=null)
	util=(SessionUtil) session.getAttribute("SessionUtil");
%>
<body class="login">
<header>
  <div class="container row">
    <div class="logo right"><a href="#"><img src="/KOTE/images/logo.png" alt="KES Logo" /></a></div>
  </div>
</header>

<section>
<div class="container row">
	<div class="login-form">
		<h1>Login</h1>
		<form id="login" action="/KOTE/ControllerServlet/UserService?From=Login" method="POST">
          <div class="login-info">
              <input type="text" id="usernm" name="usernm" placeholder="Enter Username" autofocus value="kesowner" />
              <input type="password" id="password" name="password" placeholder="Password" value="kesown#123"/>
          </div>
          <div class="login-forgot-pass">
          	<a href="/KOTE/JSP/forgot-password.jsp">Forgot Password ?</a>
		  </div>
		  <div class="btnblock">
		  	<input type="submit" value="Login" class="button" />
		  </div>
		</form>
	</div>
</div>
</section>

<footer>
  <center>
    <div class="container">
      <p>Copyright &copy; 2017. Kompac Education Systems Pvt. Ltd. All Rights Reserved. V.0.18.01.19</p>
    </div>
  </center>
</footer> 
<%  String SuccessMsg=""; if(util!=null && util.getsuccessmsg()!=null) SuccessMsg=util.getsuccessmsg();
String FailureMsg=""; if(util!=null && util.getfailedmsg()!=null) FailureMsg=util.getfailedmsg(); 

	
	
 if(SuccessMsg.trim().length()>0)
{ %>
	<div class="success-notification"><%=SuccessMsg %></div>
<%	if(util!=null) util.resetmsgs();	} %>
	
<% if(FailureMsg.trim().length()>0)
{ %>
	<div class="failure-notification"><%=FailureMsg %></div>
<%	if(util!=null) util.resetmsgs(); 	} %>
</body>

</html>