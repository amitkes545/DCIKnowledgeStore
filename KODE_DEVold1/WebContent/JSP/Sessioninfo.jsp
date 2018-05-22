<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.SessionDomain" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
</head>
 <%!SessionDomain sdom = new SessionDomain();%>
	<%
		sdom = (SessionDomain) session.getAttribute("SessionDomain");
	%>
	<%
		String status = sdom.getStatus();
	%>
<body>
 <form action="/KODE_DEV/ControllerServlet/SessionRecoveryServlet" method="post">
   <table align="center">
    <%-- <% if(status.equalsIgnoreCase("active")) { %>
         <%=status %>
         <tr><td><input type="submit" value="deactive"></td></tr>
      <% } else 
     { %>
           <%=status%> --%>
           <%=status %>
   <tr><td>enter session_id</td>
   <td><input type="text" name="session_id"></td></tr>
   <tr><td><input type="submit" value="active"></td></tr>
   <%-- <%} %> --%>
    
   </table>
   </form>
       

</body>
</html>