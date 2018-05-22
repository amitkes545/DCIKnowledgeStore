<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
		<link href="../CSS/global.css" rel="stylesheet"></link>
		<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
		<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
</head>
<script type="text/javascript">
		$(document).ready(function() {
			$(".autohide").delay(5000).fadeOut("slow");
		});
		</script>
<style type="text/css">
		.su{
    color: #468847;
    font-weight: bold;
    top: 60%;
    position: absolute;
    background: none repeat scroll 0% 0% #DFF0D8;
    left: 30%;
    border: 1px solid #D6E9C6;
    right: 30%;
    text-align: center;
    font-size: 18px;
    padding: 10px 0px;
    font-family: arial;
    border-radius: 10px;
}

		</style>
<%
		
		
		String orgid=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		//System.out.println("organization id:"+orgid+"created id:"+userid);
		
		//}
	%>
<body>

<div class="container">
		<%@include file="all_one_header.jsp"%>
			<!-- <div id="anim_img1"></div>
			<div id="kds_logo"></div>
			<div id="heading">Welcome xxxxxx</div>
			<div class="right_menu">
			<ul>
			<li id="cpwd-li"><a href="#" id="cpwd">change Password</a></li>
			<li><a href="../JSP/Login.jsp"><button id="logout">Logout</button></a></li>
			</ul>
			</div> -->
			<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>  --%>
         <%
				if (request.getAttribute("Success")!= null) { 
					
					%>
					<p class="su autohide"><%= request.getAttribute("Success") %></p>					
					<%
				} 
				else  if (request.getAttribute("Failure")!= null) { 
					
					%>
					<p class="autohide" style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("Failure") %></p>					
					<%
				} 
				
			%>
<div style="clear: both;"></div>
			<div class="customer_search">
			<p style="margin-left: 0px;" class="strong1">Database Backup</p>
 
<form action="/KODE_DEV/ControllerServlet/DataBaseBackup" method="post">

<p style="font-size: 19px; margin-left:-20px; color: #fff; text-align: center;">DataBase Stored on one folder if click submit</p>

<p style="font-size: 19px; margin-left:-20px; color: #fff; text-align: center;">
<input class="submit_btn" style="margin-top: 15px !important;" type="submit"value="Choose Folder">
<a style="margin-left: 15px;color: #c2c2c2;" href="../JSP/Home.jsp">Back</a></p>


<!--   <table align="center">
     <tr><td>
        DataBase Stored on one folder if click submit </td></tr>
        <tr><td></td>
        
        
        <td></td>
        </tr></table> -->
        
    </form>
    
		</div>
<%@include file="all_one_footer.jsp" %>	

 </div>
</body>
</html>
     