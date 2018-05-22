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
		<style type="text/css">
		/* Access Report Details*/

.faculity_setup{position: relative; top: 130px;
width: 440px;
box-shadow: 5px 5px 5px #000;
margin: 15px auto 0px;
padding: 15px 87px;
border: 2px solid #A52A2A;
background-color:rgba(0, 0, 0, 0.9);

}

.faculity_setup tr td:first-child
{
	color: #fff;
	font-size: 18px;	
	font-family: sans-serif;
	width:185px;
}
.faculity_setup tr td span{
color: #f63a4c;
}
.faculity_setup tr td:last-child > input
{width: 249px; margin: 5px 0px; padding: 5px; }
.faculity_setup tr td:last-child > select
{width: 199px; padding: 5px; margin: 5px 0px;}
.faculity_setup tr td:last-child > textarea
{width: 249px; padding: 5px; margin: 5px 0px;}

tr td:nth-child(2) {
    color: #fff;
    padding: 0 18px;
    width: 40px;
}


		</style>
</head>
<script type="text/javascript">
function validateFacultyDetails(){
	var email=document.getElementById("email").value;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var phone=document.getElementById("phone").value;
	if(!(email.match(mailformat))){
		alert("Enter Valid Email ID");
		document.getElementById("email").focus();
		return false;
	}else if(phone.length < 10){
		alert("Enter valid 10 digits mobile number");
		document.getElementById("phone").focus();
		return false;
	}
	else {
		document.facultyDetails.action="/KODE_DEV/ControllerServlet/UpdateFacultyServlet";
		document.facultyDetails.submit();
	}
}
function validateDelete(){
	document.facultyDetails.action="/KODE_DEV/ControllerServlet/UpdateFacultyServlet";
	document.facultyDetails.submit();
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
//applying this code to text box for enter characters
$(function () {
	 $('#fname').keydown(function (e) {
	 if (e.altKey) {
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
<%-- <%
	HttpSession mess = request.getSession();
	String msg = "";
	String username=(String)mess.getAttribute("username");%> --%>

<body>
<div class="container" style="height: 925px;">	
<%@ include file="../JSP/all_one_header.jsp" %>
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> --%>
			
<div style="clear: both;"></div>
			<div class="faculity_setup">
			 
 <p class="strong_left">Details oF Faculty</p>			

<%!AdminDomain adminDomain = new AdminDomain();%>
	<%
	adminDomain = (AdminDomain) session.getAttribute("AdminDomain");
		String adminid = adminDomain.getAdminId();
	%>
	<%String organizationName=(String)session.getAttribute("organizationName"); 
	String organizationId=(String)session.getAttribute("orgid");%>
<!-- action="/KODE_DEV/ControllerServlet/UpdateFacultyServlet" -->
	<form  name="facultyDetails"method="post">
		<table align="center">
		
			<tr>
				<td>ID</td><td>:</td>
				<td>
					<%
						if (adminDomain == null) {
					%> <input type="text" name="fid" id="fid"
					value=""> <%
 	} else {
 %> <input type="text" name="fid"
					id="fid" value="<%=adminDomain.getAdminId()%>" readonly="readonly">
				</td>
			</tr>
			<%
				}
			%>
			<tr>
				<td>Name</td><td>:</td>
				<td><input type="text" name="fname" id="fname"
					value="<%=adminDomain.getAdminName()%>"></td>
			</tr>
			<tr>
				<!-- <td>Password</td><td>:</td> -->
				<td><input type="hidden" name="pwd" id="pwd"
					value="<%=adminDomain.getPwd()%>"></td>
			</tr>
			<tr>
				<td>Email ID</td><td>:</td>
				<td><input type="text" name="email" id="email"
					value="<%=adminDomain.getEmail()%>"></td>
			</tr>
			<tr>
				<td>Address</td><td>:</td>
				<td><textarea rows="5" cols="25" name="address" id="address"><%=adminDomain.getAddress()%></textarea>
					</td>
			</tr>
			<tr>
				<td>Mobile #</td><td>:</td>
				<td><input type="text" name="phone" id="phone"
					value="<%=adminDomain.getPhone()%>" maxlength="10"></td>
			</tr>
			<tr>
				<td>Privilege</td><td>:</td>
				<td><input type="text" name="privilege" id="privilege" readonly="readonly"
					value="<%=adminDomain.getPrivilege()%>"></td>
			</tr>
			<tr>
				<td>Institute Name</td><td>:</td>
				<td><input type="text" name="orgid" id="orgid"
					value="<%=organizationName%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>Created By</td><td>:</td>
				<td><input type="text" name="createdid" id="createdid"
					value="<%=adminDomain.getCreated_by()%>" readonly="readonly"></td>
			</tr>
			<tr height="15px"></tr>
			<td><input type="hidden" name="organizationId" value="<%=organizationId %>"></td>
			<tr><td></td>
			<td></td>
				<td><input class="submit_btn" style="width: 85px; float: left;" type="submit" name="update" value="update" onclick="validateFacultyDetails()">
				<input class="submit_btn" style="width: 85px" type="submit" name="delete" value="delete" onclick="validateDelete()">
				</td>
				</tr>
				<tr height="15px"></tr>
				<tr>
				<td></td>
				<td></td>
				<td><a style="color: #c2c2c2; font-weight: bold; margin-left: <a style="color: #c2c2c2; font-weight: bold; margin-left: 75px;" href="FacultySearch.jsp">Back</a>px;" href="FacultySearch.jsp">Back</a></td>
				</tr>
				<tr height="15px"></tr>
				
		</table>
	</form>

		
	<!-- 		<div id="page_footer1"><span id="page_footer1_txt">Powered by <a style="color: #fff;text-decoration: none" target="_newwindow" href="www.kompacdigit.com">Kompac Digital Systems</a></span></div> -->
	<%-- <%@ include file="../JSP/all_one_footer.jsp" %> --%>			
		
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