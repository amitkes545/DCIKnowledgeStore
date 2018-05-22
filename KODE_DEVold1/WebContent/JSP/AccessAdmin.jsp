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
</head>
<script type="text/javascript">
function validateAdminAccess(){
	var id=document.getElementById("adminid").value;
	if(id == ""){
		alert("Select Admin ID");
		return false;
	}else {
		document.AccessAdmin.action="/KODE_DEV/ControllerServlet/AccessAdminServlet";
		document.AccessAdmin.submit();
	}
}
</script>
<%
		HttpSession mess = request.getSession();
		String msg = "";
	   String username=(String)mess.getAttribute("username");
	   
		msg = (String) mess.getAttribute("MsgValue");
		String orgid=(String)mess.getAttribute("orgid");
		String userid=(String)mess.getAttribute("userid");
		//System.out.println("organization id:"+orgid+"created id:"+userid);
		
		//}
	%>
<body>
<div class="container">
	
	 		<jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> 
 		
			<div style="clear: both;"></div>
			<div class="search_details" style="height: 200px; width: 310px">
		<!-- 	 action="/KODE_DEV/ControllerServlet/AccessSuperAdminServlet" -->
 <p class="strong_left">Access Admin</p>
<form  name="AccessAdmin"method="post">
     <table align="center">
     <tr>
     <td>User ID</td>
        <td>:</td>
        <td>
							 <%SearchAdminDao sdao= new SearchAdminDao();
					           ArrayList<AdminDomain>ad=new ArrayList<AdminDomain>();
							    ad=sdao.selectAccessID(orgid,userid);
							   
							    Iterator<AdminDomain> it= ad.iterator();%>
							    <select name="adminid" id="adminid">
							    <option value="">--Select Admin ID--</option>
							    <% while(it.hasNext())
							    {
							    	 
							    	String id=((AdminDomain)it.next()).getAdminId();
							    	//System.out.println("result status"+id);%>
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
						<input class="search_search_btn" style="width: 100px; margin-left: 0px;" type="submit" value="search" onclick="validateAdminAccess()"> 
						<a href="../JSP/Home.jsp">Back</a></td>
				 </tr>
				</table>
				</form>
			</div>
               <%@include file="all_one_footer.jsp" %>		
</div>
</body>
</html>