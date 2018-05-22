<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../CSS/all-one-stylesheet.css" rel="stylesheet"></link>
<style type="text/css">
.new_log{
position: absolute;
height: 50px;
top:-60px;
right: 0px;
}
</style>
</head>
<body>



<div style="clear: both;"></div>

<div id="anim_img1"></div>

			<div id="kds_logo"></div>
			
			<%if(session.getAttribute("username")!=null){%>
			
			<div id="heading">Welcome <%=session.getAttribute("username")%></div>
			
			<%}else{%>
			
			 <%response.sendRedirect("Login.jsp");%>
			
			<%}%>
			<%-- <div id="heading">Welcome <%=session.getAttribute("username")%></div> --%>
			<div class="right_menu">
			<p><img class="new_log" alt="KeDucomlogo" src="../Image/New_Logo_keducom1.png" /></p>
			<ul>
			<li id="cpwd-li">
			<a href="../JSP/ChangePassword.jsp?userId=${userid}" id="cpwd">
			<img style="margin-top: -4px; width:80px;" src="../Image/resetpassword22.png"/>
			</a>
			</li>
			
			<li>
			<a href="../JSP/logout.jsp">
			<img style="margin-top: 15px; width:80px;" src="../Image/logout.png"/>
			</a> 
			</li>
			</ul>
			
			</div>
</body>
</html>