<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.kds.KODE_DEV.domain.AdminDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.SearchAdminDao" %>
    <%@page import="com.kds.KODE_DEV.dao.AccessDao" %>
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
.container{overflow: hidden;}
.search_details tr td:nth-child(1) {width: 130px;}
.search_details tr td:nth-child(3) {width: 100px;}
.search_details tr td:nth-child(2) {color: #FFF;width: 40px;text-align: center;}	
</style>

</head>
<script type="text/javascript">
function validateAccessSearch(){
	//var orgID=document.getElementById("superAdminOrgid");
	var id=document.getElementById("superAdminId").value;
	/* if(orgID == null || orgID == ""){
		alert("Select Institute ID");
		return false;
	}
	else */
	if(id == "" || id == null){
		alert("Select SuperAdmin ID");
		return false;
	}else {
		document.accessSuperAdmin.action="/KODE_DEV/ControllerServlet/AccessSuperAdminServlet";
		document.accessSuperAdmin.submit();
	}
}
function getUserId(){
	var orgid=document.getElementById("superAdminOrgid").value;
	if(orgid == null || orgid == ""){
		alert("Select Institute ID");
		return false;
	}else {
		document.accessSuperAdmin.action="/KODE_DEV/ControllerServlet/AccessSuperAdminServlet?orgId="+orgid+"";
		document.accessSuperAdmin.submit();
	}
}
function onload(){
	<% String onloadOrgid=(String)session.getAttribute("selectedOrgid");
	////System.out.println(" Organizationid in an onload:"+onloadOrgid);
	if(onloadOrgid!=null && onloadOrgid.length()>0) {
		////System.out.println("organization id in onload:"+onloadOrgid);
	 %> 
	 var selectBox = document.getElementById("superAdminOrgid");
	 for(var i=0;i<selectBox.options.length;i++){
			 if(selectBox.options[i].value == '<%=onloadOrgid%>')
			{
	 selectBox.options[i].selected=true;
				 break;
			 }
		 }
				
		<%}%>
}
</script>
<%
	
	String orgid=(String)session.getAttribute("orgid");
	//msg = (String) mess.getAttribute("MsgValue");
	String username=(String)session.getAttribute("username");
	String userid=(String)session.getAttribute("userid");
	String[] resultUserid=(String[])session.getAttribute("resultUserId");
%>
<body>

<div class="container">
	<%@include file="all_one_header.jsp" %>
	 		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> --%> 
 		
			<div style="clear: both;"></div>
			<div class="search_details" style="height: 240px; width:510px; padding-right: 0px">
		<!-- 	 action="/KODE_DEV/ControllerServlet/AccessSuperAdminServlet" -->
 <p class="strong">Access SuperAdmin</p>
<form  method="post" name="accessSuperAdmin">
     <table align="center">
     	<%-- <tr>
     <td>Institute ID</td>
     <td>:</td>
        <td>
							 <%AccessDao dao= new AccessDao();
                              ArrayList<AdminDomain>ad=new ArrayList<AdminDomain>();
							    ad=dao.selectAccessId();
							    Iterator<AdminDomain> it= ad.iterator();%>
							    <select name="superAdminOrgid" id="superAdminOrgid" onchange="getUserId();">
							    <option value="">--Select Institute ID--</option>
							    <% while(it.hasNext())
							    {
							    	String id=((AdminDomain)it.next()).getAdminId();%>
							    <option value="<%= id %>"><%=id%></option>	
							   <% }%>
							   </select>
						</td>
					</tr> --%>
     <tr>
     <td>User ID</td>
     <td>:</td>
        <td>
							 <%SearchAdminDao sdao= new SearchAdminDao();
					           ArrayList<AdminDomain>ad1=new ArrayList<AdminDomain>();
							    ad1=sdao.getAdminId(orgid,userid);
							    Iterator<AdminDomain> it1= ad1.iterator();%>
							    
							    <select name="superAdminId" id="superAdminId">
							    <option value="">--Select User ID--</option>
							    <%-- <%				 
 					 
			  		 if(resultUserid!=null)
			  	 		{
							for(String uid:resultUserid){%>
						
				       <option value="<%=uid%>"><%=uid%></option>
						<% }}%> --%>
							    <% while(it1.hasNext())
							    {
							    	String id=((AdminDomain)it1.next()).getAdminId();%>
							    <option value="<%= id %>"><%=id%></option>	
							   <% }%>
							   </select>
						</td>
					</tr>
				 <tr height="20px;"></tr>
						<tr>
						<td></td>
						<td></td>
						<td class="back" style="color: #c2c2c2;">
						<input class="search_search_btn" style="width: 100px; margin-left: 0px;" type="button" value="search" onclick="validateAccessSearch()"> <a href="../JSP/Home.jsp">Back</a></td>
				 </tr>
				 <tr><td><input type="hidden" name="orgid" value="<%= orgid%>"></td></tr>
				</table>
				</form>
			</div>
               <%@include file="all_one_footer.jsp" %>		
</div>

</body>
<script>
onload();
</script>
</html>