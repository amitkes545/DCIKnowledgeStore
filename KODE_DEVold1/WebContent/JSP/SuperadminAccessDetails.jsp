<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.kds.KODE_DEV.domain.CreateDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.SearchAdminDao" %>
    <%@page import="java.util.*" %>
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
function validateAccessReports() {
	var orgid=document.getElementById("orgid").value;
	var id=document.getElementById("userid").value;
	if(orgid == "" ||orgid == null){
		alert("Select Institute ID");
		return false;
	}
	else if(id == ""){
		alert("Select User ID");
		return false;
	}else {
		//alert("else");
		document.accessReports.action="/KODE_DEV/ControllerServlet/SuperadminAccessReportsServlet";
		document.accessReports.submit();
	}
}
function getUserId(){
	var orgid=document.getElementById("orgid").value;
	if(orgid == null || orgid == ""){
		alert("Select Institute ID");
		return false;
	}else {
		document.accessReports.action="/KODE_DEV/ControllerServlet/SuperadminAccessReportsServlet?orgId="+orgid+"";
		document.accessReports.submit();
	}
}
function onload(){
	<% String onloadOrgid=(String)session.getAttribute("orgid");
	////System.out.println(" Organizationid in an onload:"+onloadOrgid);
	if(onloadOrgid!=null && onloadOrgid.length()>0) {
		////System.out.println("organization id in onload:"+onloadOrgid);
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
}
</script>
<%
		
		String orgid=(String)session.getAttribute("orgid");
		String	message = (String)session.getAttribute("MsgValue");
		String username=(String)session.getAttribute("username");
		String[] resultuserid=(String[])session.getAttribute("resultuserid");
		//}
	%>
<body>

<div class="container">
	<%@include file="all_one_header.jsp" %>	
 <%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> --%>

		<div style="clear: both;"></div>
		<div class="search_details" style="height: 250px; width: 500px; padding-right: 0px">
			 
 <p class="strong">Super Admin Access Reports</p>
 <!-- action="/KODE_DEV/ControllerServlet/AccessReportsServlet" -->
  <form  name="accessReports" method="post">
  <table>
  <%if(request.getAttribute("MsgValue")!=null){ %>
				<p style="color: green; margin-top: 15px; margin-left: 145px; font-size: 14px;"> <%=request.getAttribute("MsgValue") %> </p>
				<%} %>
  <tr>
     <td>Institute Name</td>
     <td>:</td>
        <td>
							 <%AccessDao dao= new AccessDao();
                              ArrayList<CreateDomain> ad =new ArrayList<CreateDomain>();
							    ad=dao.selectAccessId();
							    Iterator<CreateDomain> it= ad.iterator();%>
							    <select name="orgid" id="orgid" onchange="getUserId();">
							    <option value="">--Select Institute Name--</option>
							    <% while(it.hasNext())
							    {
							    	CreateDomain domain=it.next();
							    	String organizationId=domain.getOrg_id();
							    	String organizationName=domain.getOrg_name();
							    	String idAndName=organizationId+" ("+organizationName+")";%>
							    <option value="<%= organizationId %>"><%=idAndName%></option>
							   <% }%>
							   </select>
						</td>
					</tr>
  <tr><td>User ID</td>
  <td>:</td>
                            <td>  <%-- <%SearchAdminDao sdao= new SearchAdminDao();
					           ArrayList<AdminDomain> ad=new ArrayList<AdminDomain>();
							    ad=sdao.selectSuperAdminID(orgid);
							    Iterator<AdminDomain> it= ad.iterator();%> --%>
							    <select name="userid" id="userid" >
							    <option value="">--select User ID--</option>
							    <% if(resultuserid!=null)
			  	 		      {
							for(String uid:resultuserid){%>
						
				       <option value="<%=uid%>"><%=uid%></option>
						<% }}%>
							   <%--  <% while(it.hasNext())
							    {
							    	String id=((AdminDomain)it.next()).getAdminId();%>
							    <option value="<%= id %>"><%=id%></option>	
							   <% }%> --%>
							   </select>
						</td>
					</tr>
					
					<tr height="15px"></tr>
				
				<tr>
						<td></td>
						<td></td>
						<td style=""><input style="width: 100px;"
							class="search_search_btn" type="button" value="submit" onclick="validateAccessReports()"> <a
							class="back" style="color: #c2c2c2;" style="margin-left: 15px;"
							href="../JSP/SuperadminReports.jsp">Back</a></td>
					</tr>
		 
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