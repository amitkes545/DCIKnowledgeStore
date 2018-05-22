<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="com.kds.KODE_DEV.domain.DepartmentsDomain" %>
     <%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/SubjectDescription.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form name="SubjectDesc" method="post">
<%

ArrayList<DepartmentsDomain> arl1=(ArrayList<DepartmentsDomain>)request.getAttribute("depart");
 		
	Iterator<DepartmentsDomain> it1= arl1.iterator();
	while(it1.hasNext())
    {
		DepartmentsDomain dd=it1.next();
		String[] s1=dd.getDepartmentsNmae().split("#");
		
		for(String s:s1){
    %>
		<input type="submit" name="Subjects" value="<%=s%>" onclick="Description()" /><br/>
  <%  
		}
  }

%>
</form>

</body>
</html>