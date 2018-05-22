<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
		<link href="../CSS/owner.css" rel="stylesheet"></link>
		<link href="../CSS/global.css" rel="stylesheet"></link>
</head>
<% 
	HttpSession mess = request.getSession();
	String msg = "";
		String username=(String)mess.getAttribute("username");
		String userid=(String)mess.getAttribute("userid");
		String orgid=(String)mess.getAttribute("orgid");
%>
<body>

	<div class="container">
		
		<!-- 	<div id="anim_img1"></div>
			<div id="kds_logo"></div>
			<div id="heading">Welcome xxxxxx</div>
			<div class="right_menu">
			<ul>
			<li id="cpwd-li"><a href="#" id="cpwd">change Password</a></li>
			<li><a href="../JSP/Login.jsp"><button id="logout">Logout</button></a></li>
			</ul>
			</div> -->
			<jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>
			<div style="clear: both;"></div>
			
			<div style="margin-top: 200px;">
	<p style="color: green; font-size: 24px; text-align: center;">operation has done</p>		
		</div>
	<!-- 		<div id="page_footer1"><span id="page_footer1_txt">Powered by <a style="color: #fff;text-decoration: none" target="_newwindow" href="www.kompacdigit.com">Kompac Digital Systems</a></span></div> -->
	<%-- <%@ include file="../JSP/all_one_footer.jsp" %> --%>			
		
		</div>
		
		


  
  
  
</body>
</html>