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
</head>
<%
	HttpSession message = request.getSession();
	String message1 = "";
	message1 = (String) message.getAttribute("MsgValue");
	String username=(String)message.getAttribute("username");
	String organizationName=(String)message.getAttribute("organizationName");
%>
<body>

<div class="container">
	
 	 <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>
			<div style="clear: both;"></div>
			<div class="user_report" style="height: 480px; width: 580px;">
			
			<p class="strong_left">User Report Details</p>
			
			
			
			<%!AdminDomain ad = new AdminDomain();%>
	<%
		ad = (AdminDomain) session.getAttribute("AdminDomain");
		String adminid = ad.getAdminId();
	%>
	<center>
	<table>
	
	<tr>
				<td>User Name</td><td>:</td>
				<td><input type="text" name="sname" 
					value="<%=ad.getAdminName()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>User ID</td><td>:</td>
				<td><input type="text" name="sid" 
					value="<%=ad.getAdminId()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>Email ID</td><td>:</td>
				<td><input type="text" name="email" id="email"
					value="<%=ad.getEmail()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>Address</td><td>:</td>
				<td><input type="text" name="address" id="address"
					value="<%=ad.getAddress()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>Contact Number</td><td>:</td>
				<td><input type="text" name="phone" id="phone"
					value="<%=ad.getPhone()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>Privilege</td><td>:</td>
				<td><input type="text" name="privilege" id="privilege"
					value="<%=ad.getPrivilege()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>Organization ID</td><td>:</td>
				<td><input type="text" name="orgid" id="orgid"
					value="<%=organizationName%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>Created By</td><td>:</td>
				<td><input type="text" name="createdid" id="createdid"
					value="<%=ad.getCreated_by()%>" readonly="readonly"></td>
			</tr>
			<tr height="20px;"></tr>
			<tr>
			<td></td>
			<td></td>
			<td><a style="color: #c2c2c2;" href="../JSP/UsersAllReports.jsp">Back</a></td>
			
			
			</tr>
			
	</table>
	</center>
	
		</div>
             <%@include file="all_one_footer.jsp" %>		
</div>	
</body>
</html>