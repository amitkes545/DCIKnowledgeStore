<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="../CSS/webindex.css" rel="stylesheet"></link>
		<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
		<link rel="shortcut icon" href="Image/title_icon.ico" type="image/x-icon">
		<!-- <script src="js/jquery-2.1.3.min.js" type="text/javascript"></script> -->
		<script type="text/javascript" src="../JS/Validateform.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>WelCome To KODE_DEV</title>
		<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
		<style type="text/css">
		.container{overflow: hidden;}
		</style>
	</head>
	<!-- <script type="text/javaScript">
	        function disableBackButton() {
		     window.history.forward(0);
	       }
	     setTimeout("disableBackButton()", 0);
      </script> -->
      <script>
         window.location.hash="no-back-button";
         window.location.hash="Again-No-back-button";//again because google chrome don't insert first hash into history
         window.onhashchange=function(){window.location.hash="";}
     </script>
	<%-- <%
		//HttpSession session = request.getSession(false);
		HttpSession mess = request.getSession();
		String msg = "";
		/* if(!"".equals(msg)){ */
		msg = (String) mess.getAttribute("MsgValue");
		
		//}
	%> --%>
	<!-- onload="disableBackButton()" -->
	<body>
		<div class="container">
			<form name="loginform" method="post">
			<div id="anim_img1"></div>
			<div id="kds_logo"></div>
			<div class="right_menu">
			<p><img class="kk" alt="KeDucomlogo" src="../Image/New_Logo_keducom1.png" /></p>
			</div>	
			<div id="heading"></div>						
     		<div id="login_bg"></div>				
			<div id="login">
      			<div id="username"></div> 
				<input type="text" name="loginid" id="logid" placeholder=" User Name"></input>
				<div id="password"></div> 
				<input type="password" name="pwd" id="pwdid" placeholder=" Password"></input>
					 <%
			 
				if (request.getAttribute("MsgValue")!= null) { 
					
					%>
					<p style="color: red; margin-top:60px; margin-left: 28px; font-size: 14px;"> <%= request.getAttribute("MsgValue") %> </p>					
					<%
				} 
				
			%>
				<input type="submit" id="submit_btn" value="Login" onclick="validate()">	
									
				<a href="../JSP/ForgotPassword.jsp" id="fpwd">Forgot Password?</a>		
			</div>
		</form>	
		<div style="clear: both;"></div>      
			<%@include file="all_one_footer.jsp" %>
		</div>
	</body>
</html>