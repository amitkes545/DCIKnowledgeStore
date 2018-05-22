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
		<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
</head>
<style type="text/css">
		.su{
		    color: #008000;
    font-size: 17px;
    font-weight: bold;
    top: 21%;
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
.search_details tr td:nth-child(1) {
width: 100px;
}

.search_details tr td:nth-child(3) {
width: 100px;
}
.container{overflow: hidden;}
	.search_details tr td:nth-child(2) {
    color: #FFF;
    width: 40px;
    text-align: center;
}	
		
		</style>
<script type="text/javascript">
$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
});
function validateSearch(){
	var orgid=document.getElementById("orgid").value;
	var id=document.getElementById("sid").value;
	if(orgid == ""|| orgid == null){
		alert("Select Institute ID");
		return false;
	}
	else if(id == ""){
		alert("Select User ID");
		return false;
	}else {
		document.superadminsearch.action="/KODE_DEV/ControllerServlet/DispalySAdminServlet";
		document.superadminsearch.submit();
	}
}
function getUserId(){
	var orgid=document.getElementById("orgid").value;
	
	if(orgid == null || orgid == ""){
		alert("Select Institute ID");
		return false;
	}else {
		
		document.superadminsearch.action="/KODE_DEV/ControllerServlet/DispalySAdminServlet?orgId="+orgid+"";
		document.superadminsearch.submit();
	}
}
function onload(){
	<% String onloadOrgid=(String)session.getAttribute("scriptOrgid");
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
		
		//String username=(String)session.getAttribute("username");
		/* msg = (String) mess.getAttribute("MsgValue"); */
		String orgid=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		////System.out.println("organization id:"+orgid+"created id:"+userid);
		String[] resultuserid=(String[])session.getAttribute("resultuserid");
	%>
<body>

<div class="container">
<%@include file="all_one_header.jsp" %>	
		<%-- <%@include file="all_one_header.jsp" %> --%>
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>  --%>
	
			<div style="clear: both;"></div>
			<div class="search_details" style="height: 240px; width:480px; padding-right: 0px">
			 <!-- <div class="search_details_setup" style="height: 240px; width:480px; padding-right: 0px"> -->
			 
 <p class="strong">Super Admin Search</p>
<!--  action="/KODE_DEV/ControllerServlet/DispalySAdminServlet" -->
<form  method="post" name="superadminsearch">
<% 
				if (request.getAttribute("OwnerSuccessMAil")!= null) { 
					String msg2=(String)request.getAttribute("OwnerSuccessMAil");
				/* 	 String str[]=msg2.split("#");
					 String valid=str[0];
					 String valid1=str[1];
				 */	%>
				<%-- 	<p class="su autohide"><%= valid %></p><br> --%>
					<p class="su1 autohide"><%=msg2 %></p>					
					<%
				}
				else if (request.getAttribute("OwnerSuccess")!= null) { 
					
					%>
					<p class="su autohide"><%= request.getAttribute("OwnerSuccess") %></p>					
					<%
				} 
				else  if (request.getAttribute("OwnerFailure")!= null) { 
					
					%>
					<p class="su autohide" style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("OwnerFailure") %></p>					
					<%
				} 
				
			%>
     <table align="center">
     <tr>
     <td>Institute Name</td>
     <td>:</td>
        <td>
							 <%AccessDao dao= new AccessDao();
                              ArrayList<CreateDomain>ad=new ArrayList<CreateDomain>();
							    ad=dao.selectAccessId();
							    Iterator<CreateDomain> it= ad.iterator();%>
							    <select name="orgid" id="orgid" onchange="getUserId();">
							    <option value="">--Select Institute Name--</option>
							    <% while(it.hasNext())
							    {
							    	CreateDomain domain=it.next();
							    	String organizationId=domain.getOrg_id();
							    	////System.out.println("organization id:"+organizationId);
							    	String organizationName=domain.getOrg_name();
							        String	 idAndName=organizationName+" ("+organizationId+")";
							    	%>
							    <option value="<%= organizationId %>"><%=idAndName%></option>	
							   <% }%>
							   </select>
						</td>
					</tr>
     <tr>
     <td align="left">ID</td>
     <td>:</td>
     <td>
							<%--  <%SearchAdminDao sdao= new SearchAdminDao();
					           ArrayList<AdminDomain>ad=new ArrayList<AdminDomain>();
							    ad=sdao.getAdminId(orgid);
							    //System.out.println("super admin id:"+ad);
							    Iterator<AdminDomain> it= ad.iterator();%> --%>
							    <select name="sid" id="sid" >
							    <option value="">--select User ID--</option> 
							    <% if(resultuserid!=null)
			  	 		      {
							for(String uid:resultuserid){%>
						
				       <option value="<%=uid%>"><%=uid%></option>
						<% }}%>
							   <%--  <% while(it.hasNext())
							    {
							    	String id=((AdminDomain)it.next()).getAdminId();
							    	//System.out.println("super admin id:"+id);%>
							    <option value="<%= id %>"><%=id%></option>	
							   <% }%> --%>
							   </select>
						</td>
					</tr>
				 
						<tr height="20px;"></tr>
						<tr>
						<td></td>
						<td></td>
						<td style="">
						<input style="width: 100px; margin-left: 0px;" class="search_search_btn" type="button" value="search" onclick="validateSearch()">
						<a class="back" style="color: #c2c2c2;" style="margin-left: 15px;" href="../JSP/Home.jsp">Back</a></td>
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