<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="com.kds.KODE_DEV.domain.DepartmentsDomain" %>
     <%@page import="java.util.*" %>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/SubjectDetails.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Semesters</title>
</head>
<body>
<form name="Semesters"  method="post">
<%

ArrayList<DepartmentsDomain> arl=(ArrayList<DepartmentsDomain>)request.getAttribute("depart12");
 
	Iterator<DepartmentsDomain> it= arl.iterator();
	while(it.hasNext())
    {
		
		DepartmentsDomain dd=it.next();
		 //System.out.println(dd.getDepartmentsNmae());
    %>
		<input type="submit" name="SemesterName" value="<%=dd.getDepartmentsNmae()%>" onclick="Subjects()" /><br/>
  <%  

  }

%>
</form>
</body>
</html>