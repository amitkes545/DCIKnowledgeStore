<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.CreateDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.SearchDao" %>
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
		<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
</head>
<style type="text/css">
		.su{
		    color: #008000;
    font-size: 16px;
    font-weight: bold;
    top: 25%;
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
		
		
		</style>
<script type="text/javascript">
$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
});
function validateSearch(){
	var id=document.getElementById("org_id").value;
	if(id == ""){
		alert("Select Institute ID");
		return false;
	}else {
		document.searchOrg.action="/KODE_DEV/ControllerServlet/ManageCustomerServlet";
		document.searchOrg.submit();
	}
}
</script>
<%-- <%
	HttpSession mess = request.getSession();
	String msg = "";
     String username=(String)mess.getAttribute("username");%> --%>
<body>
<div class="container">
<%@include file="all_one_header.jsp" %>	
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>  --%>
			<div style="clear: both;"></div>
			<div class="customer_create" style="height: 200px; width: 340px">
			<!--  action="ManageCustomer.jsp" -->
 <p class="strong_left">Search Institute Name</p>
<form  method="post" name="searchOrg">
<% 
				if (request.getAttribute("OwnerSuccessMAil")!= null) { 
					String msg2=(String)request.getAttribute("OwnerSuccessMAil");
				/* 	 String str[]=msg2.split("#");
					 String valid=str[0];
					 String valid1=str[1];
				 */	%>
					<%-- <p class="su autohide"><%= valid %></p><br> --%>
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
					<p class="autohide" style="color:red; font-size:16px; font-weight: bold; top: 25%; right: 5px; position: absolute;"><%= request.getAttribute("OwnerFailure") %></p>					
					<%
				} 
				
			%> 
	
	
<table align="center">
					<tr>
						<td align="left">
							<font> Name</font>
							<span style="margin-left: 10px; margin-right: 10px; color: #fff">:</span>
							
						</td>
						
						<td>
							 <%SearchDao dao= new SearchDao();
					           ArrayList<CreateDomain>al=new ArrayList<CreateDomain>();
							    al=dao.getOrg_id();
							    Iterator<CreateDomain> it= al.iterator();%>
							    <select name="org_id" id="org_id" >
							    <option value="">--select Institute Name--</option>
							    <% String organizationName="";
							    String OrganizationID="";
							    String idAndName="";
							    while(it.hasNext())
							    {
							    	CreateDomain domain=it.next();
							    	 organizationName=domain.getOrg_name();
							    	 OrganizationID=domain.getOrg_id();
							    	//System.out.println("organization id:"+OrganizationID+ "organizationName:"+organizationName);
							     idAndName=organizationName+" ("+OrganizationID+")";
							 %>
							 <option value="<%= OrganizationID %>"><%=idAndName%></option>
							 <% }%>	
							   </select>
						</td>
					</tr>
				 <tr height="20px;"></tr>
						<tr>
						<td style="text-align: right;"></td>
						<td class="back" style="color: #c2c2c2; text-align: left;"><input class="search_search_btn" style="width: 100px;" type="button" value="search" onclick="validateSearch()">
						<a href="../JSP/Home.jsp">Back</a></td>
				 </tr>

				</table>
				</form>
			
		
	
		</div>

<%@include file="all_one_footer.jsp" %>	

</div>
</body>
</html>