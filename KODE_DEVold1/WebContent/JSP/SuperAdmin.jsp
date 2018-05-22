<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="com.kds.KODE_DEV.domain.CreateDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.InsertSuperAdminDao" %>
    <%@page import="java.util.Iterator" %>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/Validatesuperadmin.js"></script>
<script src="../JS/jquery1.11.3.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<!--  for country code select css -->
<link rel="stylesheet" href="../CSS/intlTelInput.css">
<style type="text/css">
.customer_superadmin
{
width:480px!important;
}
.customer_superadmin tr td:nth-child(2) {
	width: 40px;
	color: #fff;
	text-align: center;
}
.su{
		    color: #008000;
    font-size: 20px;
    font-weight: bold;
    top: 9%;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
.su1{
		    color: #008000;
    font-size: 12px;
    font-weight: bold;
    top: 14%;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
.customer_superadmin tr td span{
 float: right;
    margin-left: 20px;
    margin-top: 10px;
    position: absolute;    
}

span.astr {
    color: #f63a4c;
    margin: 3px 5px 0 !important;
}


</style>
</head>
<script><!-- calling ajax code from jsp-->
var request;
function getID()
{
var v=document.superadminform.sid.value;
var url="customerID.jsp?val="+v;

if(window.XMLHttpRequest){
request=new XMLHttpRequest();
}
else if(window.ActiveXObject){
request=new ActiveXObject("Microsoft.XMLHTTP");
}

try
{
request.onreadystatechange=getInfo;
request.open("GET",url,true);
request.send();
}
catch(e)
{
alert("Unable to connect to server");
}
}

function getInfo(){
if(request.readyState==4){
var val=request.responseText;
document.getElementById('amit').innerHTML=val;
}
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
<%
		
		String username=(String)session.getAttribute("username");
		//msg = (String) session.getAttribute("MsgValue");
		String orgid=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		//System.out.println("organization id:"+orgid+"created id:"+userid);
	%>
<body>

	<div class="container" style="height: 850px;">
<%@include file="all_one_header.jsp" %>	
		
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>  --%>

		<div style="clear: both;"></div>
		<div class="customer_superadmin">

			<p class="strong_left">Create Super Admin</p>

			<form name="superadminform" method="post">
            <table align="center">
					<%-- <%
			 
				if (request.getAttribute("MsgValue")!= null) { 
					%>
				<p style="color: red;">	<%= request.getAttribute("MsgValue") %></p>
					<%
				} 
				
			%> --%>
			<% 
				if (request.getAttribute("OwnerSuccessMAil")!= null) { 
					String msg2=(String)request.getAttribute("OwnerSuccessMAil");
				/* 	 String str[]=msg2.split("#");
					 String valid=str[0];
					 String valid1=str[1];
				 */	%>
				<%-- 	<p class="su"><%= valid %></p><br> --%>
					<p class="su1"><%=msg2 %></p>					
					<%
				}
				else if (request.getAttribute("OwnerSuccess")!= null) { 
					
					%>
					<p class="su"><%= request.getAttribute("OwnerSuccess") %></p>					
					<%
				} 
				else  if (request.getAttribute("OwnerFailure")!= null) { 
					
					%>
					<p style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("OwnerFailure") %></p>					
					<%
				} 
				
			%>
					<tr>
						<td>Name<span class="astr">*</span></td>
						<td>:</td>
						<td><input type="text" name="sname" id="sname"  required></td>
					</tr>
					<tr>
						<td>ID<span class="astr">*</span></td>
						<td>:</td>
						<td><input type="text" name="sid" id="sid" onblur="getID()"><span id="amit"></span></td>
					</tr>
					<tr>
						<td>Password<span class="astr">*</span></td>
						<td>:</td>
					<td><input type="password" name="pwd" id="pwd"></td> 
						<!-- <td> <input type="password" name="password" id="pwd111" required/></td> -->
					</tr>
					<tr>
						<td> Conform Password<span class="astr">*</span></td>
						<td>:</td>
						<td><input type="password" name="cfpwd" id="cfpwd"></td>
					</tr>
					<tr>
						<td>Email ID<span class="astr">*</span></td>
						<td>:</td>
						<td><input type="text" name="email" id="email"></td>
					</tr>
					<tr>
						<td>Address<span class="astr">*</span></td>
						<td>:</td>
						<td><textarea rows="5" cols="25" name="address" id="address"></textarea></td>
					</tr>
					<tr>
						<td>Mobile #<span class="astr">*</span></td>
						<td>:</td>
						<td>
						
						<!-- <input type="text" value="+91" disabled="disabled" style=" width:30px!important; float:left;"> -->
	                  <input  type="text" name="phone" id="phone" maxlength="11">
						
						</td>
					</tr>
					<tr>
						<td>Privilege</td>
						<td>:</td>
					<td><input type="text" name="privilege" id="privilege" value="SuperAdmin"
							readonly="readonly"></td>
						<!-- <td><select id="privilege" name="privilege">
							<option value="">--select privilege--</option>
						    <option value="superadmin">SuperAdmin</option>
							<option value="admin">Admin</option>
							<option value="faculity">Faculity</option>
							<option value="student">Student</option>
					</select></td>

					</tr> -->
					<%-- <tr>
						<td>Organization ID</td>
						<td>:</td>
						<td><input type="text" name="orgid" id="orgid"
							value="<%=orgid %>" readonly="readonly"></td>

					</tr> --%>
					<tr>
						<td align="left">
							<font>Institute ID</font>
							<!-- <span style="margin-left: 10px; margin-right: 10px; color: #fff">:</span> -->
							</td>
						<td>:</td>
						<td>
							 <%InsertSuperAdminDao dao= new InsertSuperAdminDao();
					           ArrayList<CreateDomain>al=new ArrayList<CreateDomain>();
							    al=dao.getOrgId();
							    Iterator<CreateDomain> it= al.iterator();%>
							    <select name="orgid" id="orgid">
							    <option value="">--select Institute ID--</option>
							    <% while(it.hasNext())
							    {
							    	String id=((CreateDomain)it.next()).getOrg_id();%>
							    <option value="<%= id %>"><%=id%></option>	
							   <% }%>
							   </select>
						</td>
					</tr>
					<tr>
						<td><input type="hidden" name="createdid" value="<%=userid %>"></td>
					</tr>
					<tr>

						<td></td>
						<td></td>

						<td><input class="setup_add_btn"
							style="width: 100px; margin-right: 10px;" type="button"
							value="Add" onclick="Validatesuperadmin()"> <a
							class="back" style="color: #c2c2c2;" href="../JSP/Home.jsp">Back</a></td>
					</tr>
				</table>

			</form>
		
</div>
	
		<%@include file="all_one_footer.jsp"%>
		</div>
		<script src="../JS/intlTelInput.js"></script>
<script>
$("#phone").intlTelInput({
  utilsScript: "../JS/utils.js"
});
</script>
</body>
</html>