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

<style type="text/css">
.product_access tr th {
	width: 150px;
}
</style>

</head>
<%
//HttpSession mess = request.getSession();
	ArrayList<AdminDomain> msg,accessDetails;
	AdminDomain adminDomain = new AdminDomain();
	AdminDomain adminDomain1 = new AdminDomain();
	msg = (ArrayList<AdminDomain>) session.getAttribute("AccessValues");
    accessDetails=(ArrayList<AdminDomain>)session.getAttribute("AvailableValueinDB");
	String selectedStudentId =(String)session.getAttribute("studentId");
	//System.out.println("selectedStudentId"+selectedStudentId);
	session.setAttribute("AvailableValueinDB",accessDetails);
	String username=(String)session.getAttribute("username");
%>
<body>
<div class="container">
		<%@include file="all_one_header_knowstore.jsp"%>
			
			  <%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> --%>

		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="faculty_mod">
	<%-- <div class="container">
           <%@include file="all_one_header.jsp" %>
		<jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> 

		<div style="clear: both;"></div>
		<div class="product_access" style="height: 280px;"> --%>

			<p class="strong">ProductAcl Details</p>
			<% 
				
				 if (request.getAttribute("AdminSuccess")!= null) { 
					
					%>
					<p style="color:green; font-size:14px; font-weight: bold; top: 18%; left: 26%; position: absolute;"><%= request.getAttribute("AdminSuccess") %></p>				
					<%
				} 
				else  if (request.getAttribute("AdminFailure")!= null) { 
					
					%>
					<p style="color:red; font-size:18px; font-weight: bold; top: 15%; left: 30%; position: absolute;"><%= request.getAttribute("AdminFailure") %></p>					
					<%
				} 
				
			%>
			<form
				action="/KODE_DEV/ControllerServlet/DisplayAccessStudentServlet"
				method="post">
				<table>
				 
					<tr>

						<th>Process Name</th>
						<th>Access</th>
					</tr>
					<%
				// Iterator it1=access.iterator();
				Iterator it = msg.iterator();
				while (it.hasNext()) {
					adminDomain = (AdminDomain) it.next();	
					//System.out.println(adminDomain.getProcessid()); 
					//System.out.println(adminDomain.getProcessname());
				%>
					<tr>

						<td><%=adminDomain.getProcessname()%></td>
						<% int checked=0;
     Iterator it1=accessDetails.iterator();
     while(it1.hasNext())
     {
    	 adminDomain1 = (AdminDomain) it1.next(); 
      if(adminDomain1.getProcessid().equals(adminDomain.getProcessid()))
      {
        checked = 1;
        	
        
       %>

						<td><input type="checkbox" id="access" name="access"
							value="<%=adminDomain.getProcessid()%>" checked></td>
						<%   %>
						<%
       break;
      }
     }
     if(checked == 0)
     {
         %>

						<td><input type="checkbox" id="access" name="access"
							value="<%=adminDomain.getProcessid()%>"></td>

						<%
     }     
     %>
						<%
					}
				%>
					
				</table>
				<div style="clear: both; height: 20px;"></div>
				<p style="text-align: center; margin-left: -125px;">
					<input class="add_btn1" type="submit" name="submit" value="submit">
					<!-- <a style="color: #c2c2c2;" href="../JSP/AccessStudent.jsp">Back</a> -->
				</p>		
						 <input type="hidden"
						name="selectedUserId" id="selectedUserId" value="<%=selectedStudentId%>">
			</form>
</div>
		<%@include file="all_one_footer.jsp"%>
	</div>




</body>

</html>