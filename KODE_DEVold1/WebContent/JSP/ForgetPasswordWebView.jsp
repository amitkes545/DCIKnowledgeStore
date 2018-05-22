<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
</head>
<%
String message = request.getParameter("message");
if (message == null) {
	message = "";
}

	else if(message.equals("User ID is wrong")){
		message="Your User Id is Wrong";
	}
	else if(message.equals("Email ID is wrong")){
		message="Your Email Id is Wrong";
	}
%> 
<script type="text/javascript">
function validateFPWD(){
	var userid=document.getElementById("uid").value;
	var emailid=document.getElementById("eid").value;
	if(userid == ""||userid ==null){
		alert("Enter User ID");
		return false;
	}else if(emailid == ""||emailid == null){
		alert("Enter Email ID");
		return false;
	}else {
		document.fpwd.action="/KODE_DEV/ControllerServlet/ForgotPasswordServlet1";
		document.fpwd.submit();
	}
}
</script>
<%
		
		String messsge = (String) session.getAttribute("MsgValue");
		String orgid=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		//System.out.println("organization id:"+orgid+"created id:"+userid);
		
	%>
<body>
<div id="kdsheader">
<div class="kdslogo"><img id="kdslogo" alt="kds logo" src="../Image/kds.png" height="65px"/></div>

</div>
<!-- <div class="container"> -->
		<%@include file="all_one_header_knowstore.jsp"%>
			
			  <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>

		<div style="clear: both;"></div>
<!-- <div class="main">
<div id="kdsheader">
<div class="kdslogo"><img id="kdslogo" alt="kds logo" src="../Image/kds.png" height="65px"/></div>
</div> -->

<div class="body-mtr"><!--  body content start -->
<div class="container">
<div class="log_in_in" >
<div class="log_in_header">
<p style="padding-top: 7px; padding-left: 120px;">
	<%if(request.getAttribute("successMessage")!=null) {%>
       <span style="color:green; font-family:arial; font-size:12px; position:absolute;left:159px; top:149px;">  <%=request.getAttribute("successMessage")%> </span>
         <%} else if(message!=null){ %>
        <span style="color:red; position:absolute;left:159px; top:149px; font-family:arial; font-size:12px;"> <%=message %></span><%} %>
        <div class="track-assement">
        <form action="#" name="fpwd" method="post">
        <table>
<tr><td>User ID</td><td>:</td><td><input type="text" name="uid" id="uid"></td></tr>
<tr><td>Email ID</td><td>:</td><td><input type="text" name="eid" id="eid"></td></tr>
<tr height="20px"></tr>
<tr>
<td></td><td></td>
<td><input style="width: 100px" class="submit_btn" type="button" name="fpwd" id="fpwd" value="submit" onclick="validateFPWD();">
 <a href="../JSP/LoginWebView.jsp">Back</a></td>
</table>
</form>
</div>
</div>
</div>
		</div>
	</div>
	<%@ include file="../JSP/FooterView.jsp" %>
		</div>
		
</body>
</html> --%>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../JS/Validateform1.js"></script>
		<script type="text/javascript" src="../JS/jquery.js"></script>
<script type="text/javascript" src="../JS/Coursedetails.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KODE_DEV - Login</title>
<link href="../CSS/LoginWebView.css" rel="stylesheet"></link>
<link href="../CSS/kedu.css" rel="stylesheet"></link>
<link href="../CSS/design-common.css" rel="stylesheet"></link>
<link href="../CSS/notification-new.css" rel="stylesheet"></link>
<script src="../JS/MessageFadeOut.js"></script>
<style type="text/css">
.strong{
font-size: 18px;
color: #fff;
text-align: center;
margin: 0px 5px 10px;
font-family: bold;
}
</style>


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

<script type="text/javascript">
function Back()
{
 document.fpwd.action="../JSP/LoginWebView.jsp";
 document.fpwd.submit();
 }
function validateFPWD(){
	var userid=document.getElementById("userId").value;
	var emailid=document.getElementById("emialId").value;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(userid == ""||userid ==null){
		alert("Enter User ID");
		document.getElementById("userId").focus();
		return false;
	}else if(emailid == ""||emailid == null){
		alert("Enter Email ID");
		document.getElementById("emialId").focus();
		return false;
	} else if (!(emailid.match(mailformat))) {
		alert("Enter valid email ID");
		document.getElementById("emialId").value="";
		document.getElementById("emialId").focus();
		return false;
  }
	else {
		document.fpwd.action="/KODE_DEV/ControllerServlet/ForgotPasswordServlet1";
		document.fpwd.submit();
	}
}
</script>
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
$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
});
</script>


</head>
<body>
<div class="main">


<div>
<%@ include file="../JSP/faculty_login_headers.jsp" %>
</div>

<div class="body-mtr"><!--  body content start -->
<div class="container" style="height:560px;">

<div class="log_in_in" >
<div class="log_in_header">
<!-- <p style="padding-top: 7px; padding-left: 120px;" > -->
<p class="strong">Forgot Password</p>
<!-- <a class="login_link" href="#">Forgot Password</a>  -->
 
</div>
<%-- <%if(request.getAttribute("successMessage")!=null) {%>
       <span style="color:green; font-family:arial; font-size:12px; position:absolute;left:159px; top:149px;">  <%=request.getAttribute("successMessage")%> </span>
         <%} else if(request.getAttribute("successMessage")!=null){ %>
        <span style="color:red; position:absolute;left:159px; top:149px; font-family:arial; font-size:12px;"> <%=request.getAttribute("successMessage") %></span><%} %> --%>
<div class="sigin_box">
 <form action="#" name="fpwd" method="post">
<img id="uparrow" alt="up arrow" src="../Image/uparrow.png" />
<input class="usname_bg" id="userId" placeholder="User ID" type="text" name="userId"></input>
<input class="pwd_bg" id="emialId" placeholder="Email ID" type="text" name="emialId"></input>
<!-- <input id="remember" type="checkbox"><span style="margin-top: 10px; position: absolute;">Remember me</span></input> 
<p style="margin-top: 10px; margin-bottom: 10px;"><a class="forget_pwd" href="/KODE_DEV/JSP/ForgetPasswordWebView.jsp">Forget Password?</a></p>-->
 <%
			 
				if (request.getAttribute("successMessage")!= null) 
				{ 
					%>
				<p class="success" >	<%= request.getAttribute("successMessage") %>	</p>				
					<%
				} else if (request.getAttribute("message")!= null) 
					
				{ 
					%>
				<p class="failure" >	<%= request.getAttribute("message") %>	</p>				
					<%
				} 
			%>
			
<p style="margin-top: 10px; margin-bottom: 10px;">
<input id="sig_btn_fpwd" type="button" value="Submit" onclick="validateFPWD();"/>
<!-- <a style="color: black;" href="../JSP/LoginWebView.jsp">Back</a> -->
<input id="sig_btn_fpwd" type="button" value="Back" onclick="Back();"/>
</p>

</form>
</div>

</div>
<!-- <div class="section2">
<img alt="" src="../Image/body-bg.jpg">
</div>
<div class="section3">
<img alt="" src="../Image/final ads2.jpg" style="width: 425px;
border: 1px solid rgb(194, 194, 194);
margin-left: 101px;"/> -->
<!-- <img alt="" src="../Image/aruna.jpg" style="width: 425px;
border: 1px solid rgb(194, 194, 194);
margin-left: 101px;"/> -->
<!-- <p class="secpara">1. Provides Online Infrastructure for working with Knowledge based  Assets/ Courseware</p>
<p class="secpara">2. Provides Online Infrastructure for working with Knowledge based  Assets/ Courseware</p>
<p class="secpara">3. Accessible from Anywhere via any Digital Medium</p>
<p class="secpara">4. Provides Tools for managing and using the Knowledge based  Assets / Courseware</p>
<p class="secpara">5. Helps build subject based libraries for better access to material on Demand</p>
<p class="secpara">6. Provides utilities for managing ,tracking ,assessing and certifying participants</p>
<p class="secpara">7. Provides utilities for managing ,tracking ,assessing and certifying participants</p>
<p class="secpara">8. Provides utilities for managing ,tracking ,assessing and certifying participants</p>
<p class="secpara">9. Provides utilities for managing ,tracking ,assessing and certifying participants</p>
<p class="secpara">10. Provides utilities for managing ,tracking ,assessing and certifying participants</p> -->
</div>
<!-- <div class="section2">
<img alt="" src="../Image/body.png">
</div> -->
</div><!-- container close -->
</div><!--  body content close -->
<!-- </div> -->
<%@ include file="../JSP/FooterView.jsp" %>

</body>
</html>