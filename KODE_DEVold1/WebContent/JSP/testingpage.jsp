<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="../CSS/owner.css" rel="stylesheet"></link>
		<link href="../CSS/global.css" rel="stylesheet"></link>
		<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
		
		<link rel="shortcut icon" href="Image/title_icon.ico" type="image/x-icon">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
		
	</head>
	<%
	//HttpSession session = request.getSession(false);
	HttpSession mess = request.getSession();

	String msg = "";

	/* if(!"".equals(msg)){ */
		String username=(String)mess.getAttribute("username");
	String	userid=(String)mess.getAttribute("userid");
	//System.out.println("userid is:"+userid);
	msg = (String) mess.getAttribute("MsgValue");
	//}
%>
	
	<body>
	<div class="container">
		
			<div id="anim_img1"></div>
			<div id="kds_logo"></div>
			<div id="heading">Welcome xxxxxx</div>
			<div class="right_menu">
			<ul>
			<li id="cpwd-li"><a href="#" id="cpwd">change Password</a></li>
			
			<li><a href="/KODE_DEV/ControllerServlet/Demo"><button id="logout">Logout</button></a></li>
			</ul>
			
			</div>
			
		
	<!-- 		<div id="page_footer1"><span id="page_footer1_txt">Powered by <a style="color: #fff;text-decoration: none" target="_newwindow" href="www.kompacdigit.com">Kompac Digital Systems</a></span></div> -->
	<%-- <%@ include file="../JSP/all_one_footer.jsp" %> --%>			
		
		</div>
		</body>
</html>