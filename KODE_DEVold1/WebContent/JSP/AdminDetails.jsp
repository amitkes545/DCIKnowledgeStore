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
<!--  for country code select css -->
<link rel="stylesheet" href="../CSS/intlTelInput.css">
<script src="../JS/jquery1.11.3.js"></script>
</head>

<script type="text/javascript">
function validateManageAdmin(){
	var email=document.getElementById("email").value;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var phone=document.getElementById("phone").value;
	if(phone.length < 10){
		//alert("Enter valid 10 digit mobile number");
		document.getElementById("phone").focus();
	}
	else if (!(email.match(mailformat))) {
		//alert("Enter valid email ID");
		document.getElementById("email").value="";
		document.getElementById("email").focus();
		return false;
	}
	 else {
		document.AdminDetails.action="/KODE_DEV/ControllerServlet/UpdateAdminServlet";
		document.AdminDetails.submit();
	}
	
}
function validateDelete(){
	document.AdminDetails.action="/KODE_DEV/ControllerServlet/UpdateAdminServlet";
	document.AdminDetails.submit();
}
//applying this jquery code to text box for enter only numbers
$(document).ready(function() {
	$("#phone").keydown(function (e) {
    	
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
            (e.keyCode == 65 && ( e.ctrlKey === true || e.metaKey === true ) ) || 
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 return;
        }
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105))
        {
            e.preventDefault();
        }
    });
});
$(function () {
	 $('#adminname').keydown(function (e) {
	 if (e.shiftKey || e.altKey) {
	 e.preventDefault();
	 } else {
	 var key = e.keyCode;
	 if (!((key == 8) || (key == 9) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
	 e.preventDefault();
	 }
	 }
	 });
	 });
</script>
<body>
	<%!AdminDomain ad = new AdminDomain();%>
	<%
		ad = (AdminDomain) session.getAttribute("AdminDomain");
		String adminid = ad.getAdminId();
		HttpSession message = request.getSession();
		//String message1 = "";
      String username=(String)message.getAttribute("username");
      String organizationName=(String)message.getAttribute("organizationName");
      String organizationId=(String)message.getAttribute("orgid");
	%>
<div class="container" style="height: 870px">
       <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> 
<div style="clear: both;"></div>
			<div class="customer_create">
			 
 <p class="strong_left">Admin Details</p>
<!-- action="/KODE_DEV/ControllerServlet/UpdateAdminServlet" -->
	<form  name="AdminDetails"method="post">
	 
		<table align="center">
			<tr>
				<td>ID</td><td>:</td>
				<td>
					<%
						if (ad == null) {
					%> <input type="text" name="adminid" id="adminid"
					value=""> <%
 	} else {
 %> <input type="text" name="adminid"
					id="adminid" value="<%=ad.getAdminId()%>" readonly="readonly">
				</td>
			</tr>
			<%
				}
			%>
			<tr>
				<td>Name</td><td>:</td>
				<td><input type="text" name="adminname" id="adminname"
					value="<%=ad.getAdminName()%>"></td>
			</tr>
			<tr>
				<!-- <td>Password</td><td>:</td> -->
				<td><input type="hidden" name="pwd" id="pwd"
					value="<%=ad.getPwd()%>"></td>
			</tr>
			<tr>
				<td>Email ID</td><td>:</td>
				<td><input type="text" name="email" id="email"
					value="<%=ad.getEmail()%>"></td>
			</tr>
			<tr>
				<td>Address</td><td>:</td>
				<td><textarea rows="5" cols="25" name="address" id="address"><%=ad.getAddress()%></textarea>
					</td>
			</tr>
			<tr>
				<td>Mobile #</td><td>:</td>
				<td><input type="text" name="phone" id="phone" maxlength="10"
					value="<%=ad.getPhone()%>"></td>
			</tr>
			<tr>
				<td>Privilege</td><td>:</td>
				<td><input type="text" name="privilege" id="privilege" readonly="readonly"
					value="<%=ad.getPrivilege()%>"></td>
			</tr>
			<tr>
				<td>Institute ID</td><td>:</td>
				<td><input type="text" name="orgid" id="orgid"
					value="<%=organizationName%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>Created By</td><td>:</td>
				<td><input type="text" name="createdid" id="createdid"
					value="<%=ad.getCreated_by()%>" readonly="readonly"></td>
			</tr>
			<tr>
			<td></td>
			<td></td>
				<td><input class="submit_btn" style="width: 85px; float: left;" type="submit" name="update" value="update" onclick="validateManageAdmin()">
				<input class="submit_btn" style="width: 85px" type="submit" name="delete" value="delete" onclick="validateDelete()">
				</td>
			</tr>
			<tr>
			<td></td>
			<td><input type="hidden" name="organizationId" value="<%=organizationId %>"></td>
			<td></td>
			<td style="padding-top: 10px;"><a class="back" style="color: #c2c2c2;" href="AdminSearch.jsp">Back</a></td>
			</tr>
			
		</table>
	</form>
</div>
<%@include file="all_one_footer.jsp" %>
</div>
<script src="../JS/intlTelInput.js"></script>
<script>
$("#phone").intlTelInput({
  utilsScript: "../JS/utils.js"
});
</script>
</body>
</html>