<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.AdminDomain" %>
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
    top: 56px;
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
function valiadteAdminSearch(){
	var id=document.getElementById("adminid").value;
	if(id == ""){
		alert("Select USer ID");
		return false;
	}else{
		document.AdminSearch.action="/KODE_DEV/ControllerServlet/DisplayAdminServlet";
		document.AdminSearch.submit();
	}
}
</script>
<%
	
		String orgid=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
	//msg = (String) mess.getAttribute("MsgValue");
	String username=(String)session.getAttribute("username");
	
%>
<body>
 
 <div class="container">
 <%@include file="all_one_header.jsp" %>
		<%-- <%@include file="all_one_header.jsp" %> --%>
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>  --%>
			
<div style="clear: both;"></div>
			<div class="customer_create" style="height: 200px; width: 380px">
			 
 <p class="strong_left">Search User ID</p>
<!-- action="/KODE_DEV/ControllerServlet/DisplayAdminServlet" -->
 <form  name="AdminSearch"method="post">
 <%  if (request.getAttribute("SuperAdminSuccess")!= null) { 
					
					%>
					<p class="su autohide"><%= request.getAttribute("SuperAdminSuccess") %></p>					
					<%
				} else  if (request.getAttribute("SuperAdminFailure")!= null) { 
					
					%>
					<p class="autohide" style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("SuperAdminFailure") %></p>					
					<%
				} 	
			%>
     <table align="center">
     <tr>
     <td style="width: 50px;">ID</td>
     <td style="width: 50px;">:</td>
     <td>
							 <%SearchAdminDao sdao= new SearchAdminDao();
					           ArrayList<AdminDomain>ad=new ArrayList<AdminDomain>();
							    ad=sdao.selectAdminID(orgid,userid);
							    Iterator<AdminDomain> it= ad.iterator();%>
							    <select name="adminid" id="adminid">
							    <option value="">--select User ID--</option>
							    <% while(it.hasNext())
							    {
							    	String id=((AdminDomain)it.next()).getAdminId();%>
							    <option value="<%= id %>"><%=id%></option>	
							   <% }%>
							</select>
	</td>
	</tr>
	<tr height="10px;"></tr>
	<tr>
	<td></td>
	<td></td>
	<td>
	
						<input class="submit_btn" style="width: 100PX;" type="button" value="search" onclick="valiadteAdminSearch()">
						<a class="back" style="color: #c2c2c2;" style="margin-left: 15px;" href="../JSP/Home.jsp">Back</a></td>
				 </tr>
			
				</table>
				</form>

		</div>
 
           	<%@include file="all_one_footer.jsp" %>
 
  </div>
</body>
</html>
     