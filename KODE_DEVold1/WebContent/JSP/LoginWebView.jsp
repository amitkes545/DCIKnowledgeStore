   	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
   	<%@ page import="com.kds.KODE_DEV.dao.*"  %>
   	<%@page import="com.kds.KODE_DEV.domain.*" %>
   	<%@page import="java.util.*" %>
   	<%@page import="java.sql.*"%>
	<%@page import="java.io.InputStream"%>
	<%@page import="java.io.OutputStream"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="../JS/jquery.min.js"></script>
<script type="text/javascript" src="/KODE_DEV/JS/Validateform1.js"></script>
		
<script type="text/javascript" src="/KODE_DEV/JS/Coursedetails.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KODE_DEV - Login</title>
<link href="/KODE_DEV/CSS/LoginWebView.css" rel="stylesheet"/>
<link href="/KODE_DEV/CSS/kedu.css" rel="stylesheet"/>
<link href="../CSS/notification-new.css" rel="stylesheet"></link>
<script src="../JS/MessageFadeOut.js"></script>

<!-- <script type="text/javascript">
$(document).ready(function(){
    $(".login_link").click(function(){
    	alert(message);
        $(".sigin_box").show();
        $(".register_box").hide();
    });
    
    $("create_link").click(function(){
        $(".register_box").show();
        $(".sigin_box").hide();
    });
});
</script> -->


<script> 
$(document).ready(function(){
	$(".register_box").hide();
    $(".login_link").click(function(){
    $(".register_box").hide();
    $(".sigin_box").show(); 
    
    });
    // next
    $(".create_link").click(function(){
    $(".register_box").show();
    $(".sigin_box").hide(); 
    
    });
});
</script>
<script>
window.location.hash="no-back-button";
window.location.hash="Again-No-back-button";//again because google chrome don't insert first hash into history
window.onhashchange=function(){window.location.hash="";}
</script> 
<style type="text/css">
input:-webkit-autofill {
    -webkit-box-shadow: 0 0 0px 1000px white inset;
  background: url('/KODE_DEV/Image/man.png')257px 5px no-repeat;
}
#page_footer1 {
    left: 0px;
    width: 100%;
    background-color: #0094DA;
    position: absolute;
    bottom: 0px;
}
.section3
{
width:11%;
float: left;
margin-top:5px;
font-family: regular;
;

}
.success_message {
color: green;
    font-size: 16px;
    font-weight: bold;
    top: -53px;
    left: 0px;
    position: relative;
}
</style>
</head>
<body>
<div class="main">

<div class="body-mtr"><!--  body content start -->
<div class="container">
<div>
<%@ include file="../JSP/faculty_login_headers.jsp" %>
</div>
<div class="log_in_in" >
<div class="log_in_header">
<p style="padding-top: 7px; padding-left: 120px;">
<a class="login_link" href="#">Sign In</a> 
 </p>
</div>

<div class="sigin_box">
<form name="loginform" method="post">
<img id="uparrow" alt="up arrow" src="/KODE_DEV/Image/uparrow.png" />
<link href="../CSS/design-common.css" rel="stylesheet"></link>
<input class="usname_bg" id="logid" placeholder="Username" type="text" name="loginid"></input>
<input class="pwd_bg" id="pwdid" placeholder="Password" type="password" name="pwd"></input>
<!--<a href="../JSP/GuestUser.jsp"><span style="margin-top: 69px; position: absolute; margin-left: -108px;">Sign up</span></a>
 <input id="remember" type="checkbox"><span style="margin-top: 10px; position: absolute;">Remember me</span></input> -->
<p style="margin-top: 10px; margin-bottom: 10px;"><a class="forget_pwd" href="JSP/ForgetPasswordWebView.jsp">Forgot Password?</a></p>
 <%
			 
				if (request.getAttribute("message")!= null) 
				{ 
					%>
				<p style="width:100%; overflow: hidden; color: red; margin-bottom: 10px;" >	<%= request.getAttribute("message") %>	</p>				
					<%
				} 
			%>
			
					<% 
					String status="";
					if(request.getParameter("status")!=null && request.getParameter("status").trim().equalsIgnoreCase("Success"))
					{
					status="Enrolled Successfully, credentials sent via Email";
					System.out.println("status="+status);
					}
				%>
					
					
				<%
				if (status != null && status.length()>0) { 
					%>
					
					<p class="success" ><%=status %></p>
					<%
				} 
				
			%>
			
					
				
			
			
			
<p><input id="sig_btn" type="submit" value="Submit" onclick="validate()"/></p>
</form>
</div>

</div>
<div class="section2">
<!-- <img alt="" src="/KODE_DEV/Image/body.png"> -->
</div>
<div class="section3">
<!-- <img alt="" src="/KODE_DEV/Image/banner.jpg" style="width: 400px;
border: 1px solid rgb(194, 194, 194);
margin-left: 51px;"/> -->

</div>

</div><!-- container close -->
</div><!--  body content close -->
</div>
<%@ include file="../JSP/FooterView.jsp" %>

</body>
</html>