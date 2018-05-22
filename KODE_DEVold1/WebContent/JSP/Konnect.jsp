<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
		<link href="../CSS/global.css" rel="stylesheet"></link>
		<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
		<style type="text/css">
		/* Access Report Details*/

.faculity_setup{position: relative; top: 130px;
width: 375px;
/* height: 380px; */
box-shadow: 5px 5px 5px #000;
margin: 15px auto 0px;
padding: 15px 87px;
border: 2px solid #A52A2A;
background-color: rgba(0, 0, 0, 0.9);

}
a{color: #c2c2c2;}
.faculity_setup tr td:first-child
{
	color: #fff;
	font-size: 18px;	
	font-family: sans-serif;
}
.faculity_setup tr td span{
color: #f63a4c;
}
.faculity_setup tr td:last-child > input
{width: 184px; margin: 5px 0px; padding: 5px; }
.faculity_setup tr td:last-child > select
{width: 199px; padding: 5px; margin: 5px 0px;}
.submit_btn{width: 255px !important}
		</style>

</head>
<%-- <%
	//HttpSession session = request.getSession(false);
	HttpSession mess = request.getSession();

	String msg = "";

	/* if(!"".equals(msg)){ */
		String username=(String)mess.getAttribute("username");%> --%>
<body>

<div class="container">
<%@ include file="../JSP/all_one_header.jsp" %>
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> --%>
         <div style="clear: both;"></div>
         
         <div style="clear: both;"></div>
		<div class="faculty_mod_admin" style="height: 250px; padding-left: 0px">
		 <p class="strong_left">Faculty Details</p>			
				<div class="center-align track-student">
					<form action="#">
					<table>
					 <tr><td><a href="AssaignSubject.jsp" class="submit_btn">Assign Subject To Faculty</a>
					  <tr><td><a href="ManageFaculty.jsp" class="submit_btn">Manage Faculty</a>
					  <tr><td>
					  <a class="back_ne3" href="../JSP/Home.jsp" style="width: 100px">Back</a>
					</table>
					</form>
				</div>
		</div>
</div>
</body>
</html>