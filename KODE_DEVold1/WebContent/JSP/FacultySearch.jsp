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
</head>
<style type="text/css">

.su{
		    color: #008000;
    font-size: 17px;
    font-weight: bold;
    top: 59px;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}</style>

<script type="text/javascript">
$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
});
function validateFaculty(){
	var id=document.getElementById("facultyid").value;
	if(id == ""){
		alert("Select User ID");
		return false;
	}else {
		document.facultyform.action="/KODE_DEV/ControllerServlet/DisplayFacultyServlet";
		document.facultyform.submit();
	}
}
</script>
<%
	/* HttpSession mess = request.getSession();
	String msg = ""; */
		String organizationId=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");%>

<body>

<div class="container">
<%@ include file="../JSP/all_one_header.jsp" %>
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> --%>
		
			<div style="clear: both;"></div>
			<div class="customer_create" style="height: 200px; padding-right: 0px; width: 480px">
			 
 <p class="strong">Search UserID</p>
 <!-- action="/KODE_DEV/ControllerServlet/DisplayFacultyServlet" -->
 <% if (request.getAttribute("AdminSuccess")!= null) { 
					////System.out.println("message value in if:"+Facultymsg);
					%>
					<p class="su autohide"><%= request.getAttribute("AdminSuccess") %></p>					
					<%
				} else  if (request.getAttribute("AdminFailure")!= null) { 
					
					%>
					<p class="autohide"style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("AdminFailure") %></p>					
					<%
				} 	
			%>
  <form  method="post" name="facultyform">
     <table align="center">
    
    <tr><td>Faculty ID</td>
    <td>:</td>
    <td>
							 <%SearchAdminDao sdao= new SearchAdminDao();
							   String id=null;
					           ArrayList<AdminDomain>od=new ArrayList<AdminDomain>();
							    od=sdao.selectFacultyId(organizationId,userid);
							    Iterator<AdminDomain> it= od.iterator();%>
							    <select name="facultyid" id="facultyid" >
							    <option value="">--Select User ID--</option>
							    <% while(it.hasNext())
							    {
							    	 id=((AdminDomain)it.next()).getAdminId();%>
							    <option value="<%= id %>"><%=id%></option>	
							   <%}%>
							   </select>
						</td>
					</tr>
				 <tr>
				 <td></td>
				 <td></td>
				<td>
				<p style="margin-left: 0px; margin-top: 15px">
				<input class="submit_btn" type="submit" value="search" onclick="validateFaculty()"> 
				<a style="margin-left: 15px;color: #c2c2c2; font-weight: bold; " href="../JSP/Home.jsp">Back</a></p>
				</td>
				</table>
				</form>
			</div>

<%@include file="all_one_footer.jsp" %>	

</div>

  

</body>
</html>