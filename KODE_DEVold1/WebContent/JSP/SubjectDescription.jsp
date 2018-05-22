<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ page import="com.kds.KODE_DEV.domain.*" %>
     <%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form name="SubjectDesc" method="post">
<table>
<tr><td>
<%
SubjectDescriptionDomain dd;
ArrayList<SubjectDescriptionDomain> arl=(ArrayList<SubjectDescriptionDomain>)request.getAttribute("departm");

	Iterator<SubjectDescriptionDomain> it= arl.iterator();
	while(it.hasNext())
    {
		 dd=it.next();
		 %>
		 <tr><td></td>
		<%
		String[] s1=dd.getSubjectDescription().split("#");
		for(String s:s1){
    %>
	<%=dd.getAuthorName() %>	</td><td><%=s %></td>
	  <%  
		}
  }

%>
</table>
</form>
</body>
</html>