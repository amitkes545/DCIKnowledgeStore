<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.AdminDomain" %>
     <%@page import="com.kds.KODE_DEV.domain.CreateDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.SearchAdminDao" %>
    <%@page import="java.util.*" %>
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
<style type="text/css">
.faculity_setup
{
position: relative; top: 130px;
width: 400px;
box-shadow: 5px 5px 5px #000;
margin: 15px auto 0px;
padding: 15px 87px;
border: 2px solid #A52A2A;
background-color: rgba(0, 0, 0, 0.9);
}
a
{
color: #c2c2c2;
}
.faculity_setup tr td:last-child > input
{
width: 184px; margin: 5px 0px; padding: 5px; 
}
.faculity_setup tr td:last-child > select
{
width: 199px; padding: 5px; margin: 5px 0px;
}
.faculity_setup tr td:first-child
{
	color: #fff;
	font-size: 18px;	
	font-family: sans-serif;
	width:115px;
	float:left;
	
}
.faculity_setup tr td span{
color: #f63a4c;
}
.faculity_setup tr td:nth-child(2) {
    color: #fff;
    padding: 0 18px;
    width: 40px;
    float: left;
}
tr {
    float: left;
    line-height: 35px;
}
.faculity_setup tr td:nth-child(3) {
    float: left;
     width: 200px;
}

</style>
</head>
<style type="text/css">

.su{
		    color: #008000;
    font-size: 18px;
    font-weight: bold;
    top: 19%;
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
.faculity_setup a {
    margin-left: 65px;
}
</style>
<script type="text/javascript">

$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
});
function validateSubject(){
	var fId=document.getElementById("facultyid").value;
	var subId=document.getElementById("subid").value;
	
	if(fId == ""){
		alert("Select Faculty ID");
		return false;
	}else if(subId == ""){
		alert("Enter Subject ID");
		return false;
	}else {
		document.facultyform.action="/KODE_DEV/ControllerServlet/AssaignFacultyServlet";
		document.facultyform.submit();
	}
}
</script>
<%
		/* HttpSession mess = request.getSession();
		String msg = "";
		msg = (String) mess.getAttribute("MsgValue"); */
		String username=(String)session.getAttribute("username");
		String orgid=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		//System.out.println("organization id:"+orgid+"created id:"+userid);
		
		//}
	%>
<body>
<div class="container">
	<%@ include file="../JSP/all_one_header.jsp" %>
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>  --%>
			
<div style="clear: both;"></div>
			<div class="faculity_setup">
			 
 <p class="strong_left">Faculty Subject Details</p>			
<!-- action="/KODE_DEV/ControllerServlet/AssaignFacultyServlet" -->
  <form  method="post" name="facultyform">
     <table>
     <% if (request.getAttribute("AdminSuccess")!= null) { 
					////System.out.println("message value in if:"+Facultymsg);
					%>
					<p class="su autohide"><%= request.getAttribute("AdminSuccess") %></p>					
					<%
				} else  if (request.getAttribute("AdminFailure")!= null) { 
					
					%>
					<p class="autohide" style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("AdminFailure") %></p>					
					<%
				} 	
			%>
    <tr>
    <td>
         Faculty ID
    </td>
    <td>:</td>
        <td>
							 <%SearchAdminDao sdao= new SearchAdminDao();
							   String id=null;
					           ArrayList<AdminDomain>od=new ArrayList<AdminDomain>();
							    od=sdao.getFacultyID(orgid,userid);
							    Iterator<AdminDomain> it= od.iterator();%>
							    <select name="facultyid" id="facultyid">
							    <option value="">--Select User ID--</option>
							    <% while(it.hasNext())
							    {
							    	 id=((AdminDomain)it.next()).getAdminId();%>
							    <option value="<%= id %>"><%=id%></option>	
							   <%}%>
							   </select>
						</td>
					</tr>
				         <tr><td>Subject ID</td><td>:</td>
				         <td><input type="text" name="subid" id="subid"></td></tr>
				         <tr><td>Stream</td><td>:</td>
				         <td><input type="text" name="stream" style="padding:5px 5px;width: 185px;"></td>
				         <td><input type="hidden" name="orgid" value=<%=orgid %>></td>
				         </tr>
				         <tr><td style="padding:5px 5px;width: 165px;">&nbsp;</td>
				         <td style="padding-right: 15px;">
                  
                  <input class="submit_btn" style="width: 100px; margin-right: 15px;" type="submit" value="submit" onclick="validateSubject()"></td>
                 <td style="float:left; width:100px;"> <a style="color: #c2c2c2; font-weight: bold;" href="../JSP/Konnect.jsp">Back</a></td>
                  </tr>
                <tr height="15px;"></tr>
				</table>
				
				<!-- <p style="margin-left: 80px; margin-top: 15px"> <input type="button" value="submit" onclick="validateSubject()">  <a style="margin-left: 10px;color: #c2c2c2; font-weight: bold; " href="../JSP/Home.jsp">Back</a></p> -->
				
				</form>
		
</div>
</div>

  

</body>
</html>