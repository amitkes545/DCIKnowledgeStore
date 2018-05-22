<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kds.KODE_DEV.domain.AdminDomain"%>
<%@page import="com.kds.KODE_DEV.dao.SearchAdminDao"%>
<%@page import="java.util.*"%>
<%@page import="com.kds.KODE_DEV.dao.AccessDao" %>
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
<script type="text/javascript">
function validateReports(){
	//var orgid=document.getElementById("orgid").value;
	var id=document.getElementById("userid").value;
	/* if(orgid == "" || orgid == ""){
		alert("Select Organization ID");
		return false;
	}
	else */ if(id == ""){
		alert("Select User ID");
		return false;
	}else {
		document.userReports.action="/KODE_DEV/ControllerServlet/UsersAllReportsServlet";
		document.userReports.submit();
	}
}
/* function getUserId(){
	var orgid=document.getElementById("orgid").value;
	if(orgid == null || orgid == ""){
		alert("Select Organization ID");
		return false;
	}else {
		document.userReports.action="/KODE_DEV/ControllerServlet/UsersAllReportsServlet?orgId="+orgid+"";
		document.userReports.submit();
	}
} */
<%-- function onload(){
	<% String onloadOrgid=(String)session.getAttribute("scriptorgid");
	//System.out.println(" Organizationid in an onload:"+onloadOrgid);
	if(onloadOrgid!=null && onloadOrgid.length()>0) {
		//System.out.println("organization id in onload:"+onloadOrgid);
	 %> 
	 var selectBox = document.getElementById("orgid");
	 for(var i=0;i<selectBox.options.length;i++){
			 if(selectBox.options[i].value == '<%=onloadOrgid%>')
			{
	 selectBox.options[i].selected=true;
				 break;
			 }
		 }
				
		<%}%>
} --%>
</script>
<%
		String orgid=(String)session.getAttribute("orgid");
		String message = (String) session.getAttribute("MsgValue");
		String username=(String)session.getAttribute("username");
		String[] resultUserid=(String[])session.getAttribute("resultuserid") ;
	%>
<body>
	<div class="container">
		<jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> 

		<div style="clear: both;"></div>
		<div class="search_details" style="height: 200px;">

			<p class="strong_left">User Report</p>
		<!-- 	action="/KODE_DEV/ControllerServlet/UsersReportsServlet" -->
			<form  name="userReports" method="post">
				<table>
				<%if(request.getAttribute("MsgValue")!=null){ %>
				<p style="color: red; margin-top: 33px; margin-left: 28px; font-size: 14px;"> <%=request.getAttribute("MsgValue") %> </p>
				<%} %>
					<%-- <tr>
     <td>Organization ID <span style="color: #fff; margin: 0px 5px;">:</span></td>
        <td>
							 <%AccessDao dao= new AccessDao();
                              ArrayList<AdminDomain>ad=new ArrayList<AdminDomain>();
							    ad=dao.selectAccessId();
							    Iterator<AdminDomain> it= ad.iterator();%>
							    <select name="orgid" id="orgid" onchange="getUserId();">
							    <option value="">--Select Organization ID--</option>
							    <% while(it.hasNext())
							    {
							    	String id=((AdminDomain)it.next()).getAdminId();%>
							    <option value="<%= id %>"><%=id%></option>	
							   <% }%>
							   </select>
						</td>
					</tr> --%>
					<tr>
						<td>User ID <span style="color: #fff; margin: 0px 18px;">:</span></td>
						<td>
							 <%SearchAdminDao sdao= new SearchAdminDao();
					           ArrayList<AdminDomain> ad=new ArrayList<AdminDomain>();
							    ad=sdao.selectSuperAdminReportsID(orgid);
							    Iterator<AdminDomain> it= ad.iterator();%> 
							    <select name="userid" id="userid">
							    <option value="">--select User ID--</option>
							  <%--  <% if(resultUserid!=null)
			  	 		      {
							for(String uid:resultUserid){%>
						
				       <option value="<%=uid%>"><%=uid%></option>
						<% }}%> --%>
								 <% while(it.hasNext())
							    {
							    	String id=((AdminDomain)it.next()).getAdminId();%>
								<option value="<%= id %>"><%=id%></option>
								<% }%>
						</select>
						</td>
					</tr>
					<tr>
					<tr height="20px;"></tr>
					<tr>
						<td><input type="hidden" name="orgid" id="orgid" value="<%= orgid%>"></td>
						<td style=""><input style="width: 100px; margin-left: -30px;"
							class="search_search_btn" type="button" value="search" onclick="validateReports()"> <a
							class="back" style="color: #c2c2c2;" style="margin-left: 15px;"
							href="../JSP/SuperadminAllReports.jsp">Back</a></td>
					</tr>


				</table>
			</form>
</div>
		<%@include file="all_one_footer.jsp"%>
	</div>
</body>
<script>
onload();
</script>
</html>