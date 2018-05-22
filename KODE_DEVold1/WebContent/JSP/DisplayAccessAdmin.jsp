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
<style type="text/css">

.su{
		    color: #008000;
    font-size: 19px;
    font-weight: bold;
    top: 15%;
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
<%
session=request.getSession();
ArrayList<AdminDomain> resultValue,accessDetails;
AdminDomain pdomain = new AdminDomain();
AdminDomain pdomain1 = new AdminDomain();
resultValue = (ArrayList<AdminDomain>) session.getAttribute("resultvalue");
accessDetails=(ArrayList<AdminDomain>)session.getAttribute("AvailableValueinDB");
String selectedUserId =(String)session.getAttribute("adminid");
String username=(String)session.getAttribute("username");
//String resultAcess=(String)request.getAttribute("resultAccess");
//System.out.println("selected user id is"+selectedUserId);
session.setAttribute("AvailableValueinDB",accessDetails);
%>

<body>
<div class="container">

		<jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> 

		<div style="clear: both;"></div>
		<div class="product_access" style="height: 280px;">
                   <p class="strong"> Admin Access Details</p>

<form action="/KODE_DEV/ControllerServlet/DisplayAccessAdminServlet" method="post"><!-- start from form -->
		
<%	 if (request.getAttribute("SuperAdminSuccess")!= null) { 
					
					%>
					<p class="su"><%= request.getAttribute("SuperAdminSuccess") %></p>					
					<%
				} else  if (request.getAttribute("SuperAdminFailure")!= null) { 
					
					%>
					<p style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("SuperAdminFailure") %></p>					
					<%
				} 	
			%>
		
			<table border="2">
			<%-- <%if(resultAcess!=null){%>
				<p style="color: red; margin-top: 33px; margin-left: 28px; font-size: 14px;"> <%= resultAcess %> </p>
			<% }%>
			 --%>
			 
				<tr>

					<th>processName</th>
					<th>Access</th>
				</tr>
				<%
				// Iterator it1=access.iterator();
				Iterator it = resultValue.iterator();
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
       
       <td><input type="checkbox" id="access" name="access" value="<%=pdomain.getProcessid()%>" checked></td>
   
       <%
       break;
      }
     }
     if(checked == 0)
     {
         %>
         
        <td> <input type="checkbox" id="access" name="access" value="<%=pdomain.getProcessid()%>" ></td>
         
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
					<a style="color: #c2c2c2;" href="../JSP/AccessSuperAdmin.jsp">Back</a>
				</p>		
               
          <input type="hidden" name="selectedUserId" id="selectedUserId" value="<%=selectedUserId %>">
	</form><!-- form end -->
</div>
		<%@include file="all_one_footer.jsp"%>
	</div>
</body>
</html>