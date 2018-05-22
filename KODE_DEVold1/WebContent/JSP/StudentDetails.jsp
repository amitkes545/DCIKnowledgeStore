<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.AdminDomain"%>
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
background-color: rgba(25, 25, 55, 0.5);

}

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

		</style>
</head>
<%
	//HttpSession session = request.getSession(false);
	HttpSession mess = request.getSession();

	String msg = "";

	/* if(!"".equals(msg)){ */
		String username=(String)mess.getAttribute("username");%>

<body>
<div class="container" style="height: 1000px;">	
		<jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>
			
<div style="clear: both;"></div>
			<div class="faculity_setup">
			 
 <p class="strong_left">Details oF Faculty</p>			

<%!AdminDomain ad = new AdminDomain();%>
	<%
		ad = (AdminDomain) session.getAttribute("AdminDomain");
		String adminid = ad.getAdminId();
	%>

	<form action="/KODE_DEV/ControllerServlet/UpdateFacultyServlet" method="post">
		<table align="center">
			<tr>
				<td>ID</td><td>:</td>
				<td>
					<%
						if (ad == null) {
					%> <input type="text" name="fid" id="fid"
					value=""> <%
 	} else {
 %> <input type="text" name="fid"
					id="fid" value="<%=ad.getAdminId()%>" readonly="readonly">
				</td>
			</tr>
			<%
				}
			%>
			<tr>
				<td>Name</td><td>:</td>
				<td><input type="text" name="fname" id="fname"
					value="<%=ad.getAdminName()%>"></td>
			</tr>
			<tr>
				<td>Password</td><td>:</td>
				<td><input type="password" name="pwd" id="pwd"
					value="<%=ad.getPwd()%>"></td>
			</tr>
			<tr>
				<td>EmailId</td><td>:</td>
				<td><input type="text" name="email" id="email"
					value="<%=ad.getEmail()%>"></td>
			</tr>
			<tr>
				<td>Address</td><td>:</td>
				<td><input type="text" name="address" id="address"
					value="<%=ad.getAddress()%>"></td>
			</tr>
			<tr>
				<td>ContactNumber</td><td>:</td>
				<td><input type="text" name="phone" id="phone"
					value="<%=ad.getPhone()%>"></td>
			</tr>
			<tr>
				<td>Privilege</td><td>:</td>
				<td><input type="text" name="privilege" id="privilege"
					value="<%=ad.getPrivilege()%>"></td>
			</tr>
			<tr>
				<td>Organization ID</td><td>:</td>
				<td><input type="text" name="orgid" id="orgid"
					value="<%=ad.getOrgid()%>"></td>
			</tr>
			<tr>
				<td>Created By</td><td>:</td>
				<td><input type="text" name="createdid" id="createdid"
					value="<%=ad.getCreated_by()%>"></td>
			</tr>
			<tr height="15px"></tr>
			
			<tr><td></td>
			<td></td>
				<td><input class="submit_btn" style="width: 85px; float: left;" type="submit" name="update" value="update">
				<input class="submit_btn" style="width: 85px" type="submit" name="delete" value="delete"> <a style="color: #c2c2c2; font-weight: bold; margin-left: 75px;" href="FacultySearch.jsp">Back</a> </td>
				</tr>
				<tr height="15px"></tr>
				
		</table>
	</form>

		
	<!-- 		<div id="page_footer1"><span id="page_footer1_txt">Powered by <a style="color: #fff;text-decoration: none" target="_newwindow" href="www.kompacdigit.com">Kompac Digital Systems</a></span></div> -->
	<%-- <%@ include file="../JSP/all_one_footer.jsp" %> --%>			
		
		</div>
		<%@include file="all_one_footer.jsp" %>
</div>



</body>
</html>