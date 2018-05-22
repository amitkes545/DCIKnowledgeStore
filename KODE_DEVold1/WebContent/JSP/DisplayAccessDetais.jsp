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
</head>
<%
	
	

	String[] access = (String []) request.getAttribute("resultvalue");

	 String userid =(String)request.getAttribute("userid");
	 //System.out.println("user id is"+userid);
	for(String s:access)
    {
	 //System.out.println("selected id are"+s);
    }
%>
<body>
<form action="/KODE_DEV/ControllerServlet/ProductAccessServlet"
		method="post">
		<center>

			<table border="2">
				<tr>

					<th>process_Name</th>
					<th>access</th>
				</tr>
				
						<% 
						    for(String s:access)
					         {
					    	 //System.out.println("selected id are"+s);%>
					    	 
					    	 <tr>
								<td><%=s%></td>
								<td><input type="checkbox" id="access" name="access"
									value="<%=s%>"></td>
							</tr>
					     
				
			
				<%
					     }
				%>
				
			</table>
			<input type="submit" name="submit" value="submit">
			
					<a href="../JSP/ProductAcl.jsp">Back</a>
               <input type="hidden" name="userid" value="<%=userid%>">
		</center>

	</form>
	
</body>
</html>