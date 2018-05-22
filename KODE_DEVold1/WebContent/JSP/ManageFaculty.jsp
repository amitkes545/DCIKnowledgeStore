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
		<style type="text/css">
		/* Access Report Details*/

.faculity_setup{
    position: relative;
    top: 130px;
    width: 450px;
    box-shadow: 5px 5px 5px #000;
    padding-right:0px !important;
    margin: 15px auto 0px;
    padding: 15px 87px;
    border: 2px solid #A52A2A;
    background-color: rgba(0, 0, 0, 0.9);
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
{width: 240px; padding: 5px; margin: 5px 0px;}

		</style>
</head>
<script type="text/javascript">
function validateManageFaculty(){
	var id=document.getElementById("facultyid").value;
	if(id == ""){
		alert("Select Faculty ID");
		return false;
	}else {
		document.facultyform.action="/KODE_DEV/ControllerServlet/ManageFacultySubject";
		document.facultyform.submit();
	}
}
</script>
<%
	/* HttpSession mess = request.getSession();
	String msg = ""; */
	String username=(String)session.getAttribute("username");
	String orgid=(String)session.getAttribute("orgid");
	String userid=(String)session.getAttribute("userid");%>
<body>
<div class="container">
<div style="clear: both;"></div>
 <%@ include file="../JSP/all_one_header.jsp" %> 
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="userid" value="<%= username%>" /> 
         </jsp:include> --%>
	
<div style="clear: both;"></div>
			<div class="faculity_setup">
			 
 <p class="strong">Faculty ID</p>			
<!-- action="/KODE_DEV/ControllerServlet/ManageFacultySubject" -->
<form  method="post" name="facultyform">
     <table align="center">
    
    <tr><td>
        Faculty ID</td>
        <td style="color: #fff; padding: 0px 15px">:</td>
        <td>
							 <%SearchAdminDao sdao= new SearchAdminDao();
							   String id=null;
					          Set<String> od=new HashSet<String>();
							    od=sdao.selectFacultyID(orgid);
							    Iterator<String> it= od.iterator();%>
							    <select name="facultyid" id="facultyid">
							    <option value="">--Select Faculty ID--</option>
							    <% while(it.hasNext())
							    {
							    	 id=it.next();%>
							    <option value="<%= id %>"><%=id%></option>	
							   <%}%>
							   </select>
						</td>
					</tr>
					<tr height="15px"></tr>
					<tr>
					<td></td>
					<td></td>
					<td><input class="setup_add_btn" style="width: 100px;" type="button" value="search" onclick="validateManageFaculty()">
					 <a style="margin-left: 10px;color: #c2c2c2; font-weight: bold; " href="../JSP/Konnect.jsp">Back</a></td>
					
					</tr>
				 </table>
				 </form>
		
		</div>
		<%@ include file="../JSP/all_one_footer.jsp" %>
</div>



</body>
</html>