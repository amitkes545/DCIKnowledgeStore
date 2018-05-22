<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.AdminDomain"%>
    <%@page import="com.kds.KODE_DEV.domain.CreateDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.InsertSuperAdminDao" %>
    <%@page import="java.util.Iterator" %>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<script src="../JS/jquery1.11.3.js"></script>
<!--  for country code select css -->
<link rel="stylesheet" href="../CSS/intlTelInput.css">
</head>
<script type="text/javascript">

//validate for fields
function validateSuperAdmin(){
	
	var email=document.getElementById("email").value;
	var phone=document.getElementById("phone").value;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	
	if (email == "") {
		alert("Enter Email ID");
		document.getElementById("email").focus();
	    return false;
   }
	else if (!(email.match(mailformat))) {
		alert("Enter valid email ID");
		document.getElementById("email").value="";
		document.getElementById("email").focus();
		return false;
  }else if(phone.length < 10){
	  alert("Enter valid 10 digit mobile number");
	  document.getElementById("phone").focus();
	  return false;
  }
	else {
		
		  document.manageDetails.action="/KODE_DEV/ControllerServlet/UpdateSuperAdminServlet";
		  document.manageDetails.submit();
	  } 
}
//onclick for delete button
function clickDelete(){
	  document.manageDetails.action="/KODE_DEV/ControllerServlet/UpdateSuperAdminServlet";
	  document.manageDetails.submit();
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
	
	 $('#sname').keydown(function (e) {
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
<%!AdminDomain adminDomain = new AdminDomain();%>
	<%
	adminDomain = (AdminDomain) session.getAttribute("AdminDomain");
		String adminid = adminDomain.getAdminId();
		
		//msg = (String) mess.getAttribute("MsgValue");
		String username=(String)session.getAttribute("username");
	%>

 <div class="container" style="height: 852px">
		<%-- <%@include file="all_one_header.jsp" %>	 --%>
		<%@include file="all_one_header.jsp" %>	
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>  --%>
		
<div style="clear: both;"></div>
			<div class="customer_create" style="width: 455px;">
			 
 <p class="strong_left">Super Admin Details</p>

<!-- action="/KODE_DEV/ControllerServlet/UpdateSuperAdminServlet" -->
<form  method="post" name="manageDetails">
		<table align="center">
		
			<tr>
				<td>ID</td><td>:</td>
				<td>
					<%
						if (adminDomain == null) {
					%> <input type="text" name="adminid" id="adminid"
					value=""> <%
 	} else {
 %> <input type="text" name="sid"
					id="sid" value="<%=adminDomain.getAdminId()%>" readonly="readonly">
				</td>
			</tr>
			<%
				}
			%>
			<tr>
				<td>Name</td><td>:</td>
				<td><input type="text" name="sname" id="sname"
					value="<%=adminDomain.getAdminName()%>"></td>
			</tr>
			<tr>
				<!-- <td>Password</td><td>:</td> -->
				<td><input type="hidden" name="pwd" id="pwd"
					value="<%=adminDomain.getPwd()%>" readonly="readonly"></td>
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
			<%-- <tr>
				<td>Privilege</td><td>:</td>
				<td><input type="text" name="privilege" id="privilege"
					value="<%=ad.getPrivilege()%>"></td>
			</tr> --%>
			<tr>
						<!-- <td>Privilege</td>
						<td>:</td> -->
						 <td><input type="hidden" name="privilege" value="<%=adminDomain.getPrivilege()%>"
							readonly="readonly"></td>
						<%-- <td><select id="privilege" name="privilege">
							<option value="<%=ad.getPrivilege()%>"><%=ad.getPrivilege()%></option>
						    <!-- <option value="superadmin">SuperAdmin</option> -->
							<option value="admin">Admin</option>
							<option value="faculity">Faculity</option>
							<option value="student">Student</option>
					</select></td> --%>

					<!-- </tr> -->
			<tr>
						<td align="left">
							<font>Organization Name</font>
							<!-- <span style="margin-left: 10px; margin-right: 10px; color: #fff">:</span> -->
							
							
						</td>
						<td>:</td>
						<td><input type="text" name="orgid" id="orgid" value="<%=adminDomain.getOrgid()%>" readonly="readonly"></td>
						<%-- <td>
							 <%InsertSuperAdminDao dao= new InsertSuperAdminDao();
					           ArrayList<CreateDomain>al=new ArrayList<CreateDomain>();
							    al=dao.getOrgId();
							    Iterator<CreateDomain> it= al.iterator();%>
							    <select name="orgid" id="orgid">
							    <option value="<%=adminDomain.getOrgid()%>"><%=adminDomain.getOrgid()%></option>
							    <% String organizationName="";
							    String OrganizationID="";
							    String idAndName="";
							    while(it.hasNext())
							    {
							    	CreateDomain domain=it.next();
							    	 organizationName=domain.getOrg_name();
							    	 OrganizationID=domain.getOrg_id();
							    	//System.out.println("organization id:"+OrganizationID+ "organizationName:"+organizationName);
							     idAndName=organizationName+"("+OrganizationID+")";%>
							    <option value="<%= OrganizationID %>"><%=idAndName%></option>	
							   <% }%>
							   </select>
						</td> --%>
					</tr>
			<%-- <tr>
				<td>Organization ID</td><td>:</td>
				<td><input type="text" name="orgid" id="orgid"
					value="<%=ad.getOrgid()%>" readonly="readonly"></td>
			</tr> --%>
			<%-- <tr>
				<td>Created By</td><td>:</td>
				<td><input type="text" name="createdid" id="createdid"
					value="<%=ad.getCreated_by()%>" readonly="readonly"></td>
			</tr> --%>
		<tr height="10px;"></tr>
        			<tr>
        			<td></td>
        			<td></td>
        			<td>
        			<input class="submit_btn" style="width:88px;  float: left;" type="submit" name="update" value="Update" onclick="validateSuperAdmin()">
        			<input class="submit_btn" style="width:88px; margin-right: 5px !important;" type="submit" name="delete" value="Delete" onclick="clickDelete()">
        			
			    	<!-- <a style="margin-left: 25px;" href="../JSP/Home.jsp">Go to Home Folder</a> --></td>
			    	</tr>
			    	<tr>
        			<td></td>
        			<td></td>
        			<td><a class="back" style="color: #c2c2c2;" href="SuperAdminSearch.jsp">Back</a></td>
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