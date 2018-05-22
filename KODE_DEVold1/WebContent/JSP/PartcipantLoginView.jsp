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
<script type="text/javascript" src="../JS/ValidateParticipant.js"></script>
		
<script type="text/javascript" src="../JS/Coursedetails.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KODE_DEV - Login</title>
<link href="../CSS/LoginWebView.css" rel="stylesheet"></link>

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
  background: url('../Image/man.png')257px 5px no-repeat;
}
</style>
</head>
<body>
<%
		//HttpSession session = request.getSession(false);
		HttpSession mess = request.getSession();
	
		String msg = "";
	
		/* if(!"".equals(msg)){ */
		msg = (String) mess.getAttribute("MsgValue");
		
		//}
	%>
<div style="clear: both;"></div>
<div id="kdsheader">
<div class="kdslogo"><img id="kdslogo" alt="kds logo" src="../Image/kds.png" height="55px;" /></div>
</div>

<div class="body-mtr"><!--  body content start -->
<div class="container">

<div class="log_in_in" >
<div class="log_in_header">
<p style="padding-top: 7px; padding-left: 120px;"><a class="login_link" href="#">Sign In</a> <a class="create_link" href="#">Register</a> </p>
</div>

<div class="sigin_box">
<form name="loginform" method="post">
<img id="uparrow" alt="up arrow" src="../Image/uparrow.png" />
<input class="usname_bg" id="logid" placeholder="Username" type="text" name="loginid"></input>
<input class="pwd_bg" id="pwdid" placeholder="Password" type="password" name="pwd"></input>
<input id="remember" type="checkbox"><span style="margin-top: 10px; position: absolute;">Remember me</span></input>
<p style="margin-top: 10px; margin-bottom: 10px;"><a class="forget_pwd" href="#">Forgot Password?</a></p>
 <%
			 
				if (request.getAttribute("MsgValue")!= null) 
				{ 
					%>
				<p style="width:100%; overflow: hidden; color: red; margin-bottom: 10px;" >	<%= request.getAttribute("MsgValue") %>	</p>				
					<%
				} 
			%>
<p><input id="sig_btn" type="submit" value="Sign In" onclick="validate()"/></p>
</form>
</div>

<div class="register_box">
<img id="uparrow-reg" alt="up arrow" src="../Image/uparrow.png" />
<input class="" placeholder="First Name" type="text" />
<input class="" placeholder="Last Name" type="text" />
<input class="" placeholder="User Name" type="text" />
<input class="" placeholder="Mail-ID" type="text" />
<input class="" placeholder="Password" type="text" />
<input class="" placeholder="Re-Type Password" type="text" />
<input id="reg_btn" type="button" value="Register" />
</div>
</div>
</div><!-- container close -->
</div><!--  body content close -->
<%@ include file="../JSP/FooterView.jsp" %>

</body>
</html>