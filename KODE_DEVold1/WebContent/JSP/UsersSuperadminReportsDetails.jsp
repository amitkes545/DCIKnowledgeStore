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
<%-- <%
	HttpSession mess = request.getSession();
	String msg = "";
	msg = (String) mess.getAttribute("MsgValue");
	String username=(String)mess.getAttribute("username");
%> --%>
<body>

<div class="container">
	 <%@include file="all_one_header.jsp" %>
 	 <%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> --%>
			<div style="clear: both;"></div>
			<div class="user_report" style="height: 475px; width: 580px;">
			
			<p class="strong1">super Admin Report Details</p>
			
			
			
			<%!AdminDomain adminDomain = new AdminDomain();%>
	<%
	adminDomain = (AdminDomain) session.getAttribute("AdminDomain");
		String adminid = adminDomain.getAdminId();
	%>
	<center>
	<table>
	
	<tr>
				<td> Name</td><td>:</td>
				<td><input type="text" name="sname" 
					value="<%=adminDomain.getAdminName()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td> ID</td><td>:</td>
				<td><input type="text" name="sid" 
					value="<%=adminDomain.getAdminId()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>Email ID</td><td>:</td>
				<td><input type="text" name="email" id="email"
					value="<%=adminDomain.getEmail()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>Address</td><td>:</td>
				<td><input type="text" name="address" id="address"
					value="<%=adminDomain.getAddress()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>Mobile #</td><td>:</td>
				<td><input type="text" name="phone" id="phone"
					value="<%=adminDomain.getPhone()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>Privilege</td><td>:</td>
				<td><input type="text" name="privilege" id="privilege"
					value="<%=adminDomain.getPrivilege()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>Organization ID</td><td>:</td>
				<td><input type="text" name="orgid" id="orgid"
					value="<%=adminDomain.getOrgid()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>Created By</td><td>:</td>
				<td><input type="text" name="createdid" id="createdid"
					value="<%=adminDomain.getCreated_by()%>" readonly="readonly"></td>
			</tr>
			<tr height="20px;"></tr>
			<tr>
			<td></td>
			<td></td>
			<td><a style="color: #c2c2c2;" href="../JSP/UserSuperAdminReports.jsp">Back</a></td>
			</tr>
			<tr height="20px;"></tr>
			
	</table>
	</center>
	
		</div>
             <%@include file="all_one_footer.jsp" %>		
</div>	
</body>
</html>