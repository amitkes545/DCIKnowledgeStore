<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.AdminDomain"%>
    <%@page import="java.util.*"%>
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
<%
		//HttpSession mess = request.getSession();
		String username=(String)session.getAttribute("username");
		String orgid=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		//System.out.println("organization id:"+orgid+"created id:"+userid);
	%>
<body>

 <div class="container">
 <%@include file="all_one_header.jsp" %>
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>  --%>
			
<div style="clear: both;"></div>
			<div class="access_report" style="height: 260px;">
			 
 <p class="strong_left">Admin Access Details</p>

   <%
	ArrayList<AdminDomain> msg;
	AdminDomain adminDomain = new AdminDomain();

	msg = (ArrayList<AdminDomain>) request.getAttribute("AdminDomain");

	
%>
	  <p style="color: green;margin-left: 210px"> User Id:<%=request.getAttribute("AccessUserId").toString() %></p>
	<table border="1">
	
	<tr><th>Process ID</th>
	<th>Process Name</th></tr>
	<% 
	              Iterator it = msg.iterator();
					while (it.hasNext()) {
						adminDomain = (AdminDomain) it.next();%>
						
	<tr><td><%=adminDomain.getProcessid() %></td><td><%=adminDomain.getProcessname()%></td></tr>
	<%} %>
	</table>
	<p>	<a class="back" style="color: #c2c2c2; margin-left: 150px; margin-top: 15px; font-weight: bold;" href="../JSP/AdminAccessDetails.jsp">Back</a></p>
	
		</div>
 
 <%@include file="all_one_footer.jsp" %>	
 </div>
</body>
</html>