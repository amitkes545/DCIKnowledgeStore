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
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>

<style type="text/css">
.product_access tr th {
	width: 150px;
}
</style>
<script type="text/javascript">
		$(document).ready(function() {
			$(".autohide").delay(5000).fadeOut("slow");
		});
</script>
</head>
<%
//HttpSession mess = request.getSession();
	ArrayList<AdminDomain> msg,accessDetails;
	AdminDomain pdomain = new AdminDomain();
	AdminDomain pdomain1 = new AdminDomain();
	msg = (ArrayList<AdminDomain>) session.getAttribute("AccessValues");
    accessDetails=(ArrayList<AdminDomain>)session.getAttribute("AvailableValueinDB");
	String selectedSuperId =(String)session.getAttribute("superAdminId");
	//System.out.println("selectedSuperId"+selectedSuperId);
	session.setAttribute("AvailableValueinDB",accessDetails);
	String username=(String)session.getAttribute("username");
%>
<body>

	<div class="container">
           <%@include file="all_one_header.jsp" %>
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>  --%>

		<div style="clear: both;"></div>
		<div class="product_access" style="height: 280px;">

			<p class="strong">ProductAcl Details</p>
			<% 
				
				 if (request.getAttribute("OwnerSuccess")!= null) { 
					
					%>
					<p class="autohide" style="color:green; font-size:14px; font-weight: bold; top: 18%; left: 26%; position: absolute;"><%= request.getAttribute("OwnerSuccess") %></p>				
					<%
				} 
				else  if (request.getAttribute("OwnerFailure")!= null) { 
					
					%>
					<p class="autohide" style="color:red; font-size:18px; font-weight: bold; top: 15%; left: 30%; position: absolute;"><%= request.getAttribute("OwnerFailure") %></p>					
					<%
				} 
				
			%>
			<form
				action="/KODE_DEV/ControllerServlet/DisplayAccessSuperAdminServlet"
				method="post">
				<table border="2">
				 
					<tr>

						<th>Process Name</th>
						<th>Access</th>
					</tr>
					<%
				// Iterator it1=access.iterator();
				Iterator it = msg.iterator();
				while (it.hasNext()) {
					pdomain = (AdminDomain) it.next();	
					//System.out.println(pdomain.getProcessid()); 
					//System.out.println(pdomain.getProcessname());
				%>
					<tr>

						<td><%=pdomain.getProcessname()%></td>
						<% int checked=0;
     Iterator it1=accessDetails.iterator();
     while(it1.hasNext())
     {
      pdomain1 = (AdminDomain) it1.next(); 
      if(pdomain1.getProcessid().equals(pdomain.getProcessid()))
      {
        checked = 1;
        	
        
       %>

						<td><input type="checkbox" id="access" name="access"
							value="<%=pdomain.getProcessid()%>" checked></td>
						<%   %>
						<%
       break;
      }
     }
     if(checked == 0)
     {
         %>

						<td><input type="checkbox" id="access" name="access"
							value="<%=pdomain.getProcessid()%>"></td>

						<%
     }     
     %>
						<%
					}
				%>
					
				</table>
				<div style="clear: both; height: 20px;"></div>
				<p style="text-align: center; margin-left: -125px;">
					<input class="submit_btn" style="margin-left: 25px;" type="submit" name="submit" value="submit">
					<a style="color: #c2c2c2;" href="../JSP/AccessSuperAdminOwner.jsp">Back</a>
				</p>		
						 <input type="hidden"
						name="selecteduserid" value="<%=selectedSuperId%>">
			</form>



		</div>
		<%@include file="all_one_footer.jsp"%>
	</div>

</body>

</html>