<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.AdminDomain" %>
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
		<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
		<link href="../CSS/kedu.css" rel="stylesheet"/>
</head>
<script type="text/javascript">
function validateAccessReports() {
	
	var id=document.getElementById("userid").value;
	 if(id == ""){
		alert("Select User ID");
		return false;
	}else {
		//alert("else");
		document.accessReports.action="/KODE_DEV/ControllerServlet/FacilitatorAccessReportsServlet";
		document.accessReports.submit();
	}
}


</script>
<%
		/* HttpSession mess = request.getSession();
		String msg = ""; */
		String orgid=(String)session.getAttribute("orgid");
		//msg = (String) mess.getAttribute("MsgValue");
		String username=(String)session.getAttribute("username");
		String userid=(String)session.getAttribute("userid");
		String[] resultuserid=(String[])session.getAttribute("resultuserid");
		//}
	%>
<body>

<div class="container">
		<%@ include file="../JSP/headers.jsp" %>
 <%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> --%>

		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="search_details" style="height: 200px;">
			 
 <p class="strong_left">Facilitator Access Report</p>
 <!-- action="/KODE_DEV/ControllerServlet/AccessReportsServlet" -->
  <form  name="accessReports" method="post">
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
  <tr><td>User ID <span style="color: #fff; margin: 0px 18px;">:</span></td>
                            <td>  <%SearchAdminDao sdao= new SearchAdminDao();
					           ArrayList<AdminDomain> ad=new ArrayList<AdminDomain>();
							    ad=sdao.getFacultyID(orgid,userid);
							    Iterator<AdminDomain> it= ad.iterator();%> 
							    <select name="userid" id="userid" >
							    <option value="">--select User ID--</option>
							   <%--  <% if(resultuserid!=null)
			  	 		      {
							for(String uid:resultuserid){%>
						
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
				<tr height="15px"></tr>
				<tr>
						<td></td>
						<td style=" text-align: left;"><input style="width: 100px; margin-left: 0px;"
							class="search_search_btn" type="button" value="submit" onclick="validateAccessReports()"> <a
							class="back" style="color: #c2c2c2;" style="margin-left: 15px;"
							href="../JSP/facilitatorReports.jsp">Back</a></td>
					</tr>
		 
   </table>
  </form>
		</div>
		<%@include file="FooterViews.jsp" %>		
</div>
</body>

</html>