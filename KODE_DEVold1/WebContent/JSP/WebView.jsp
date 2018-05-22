   <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
   <%@ page import="com.kds.KODE_DEV.dao.*"  %>
   <%@page import="com.kds.KODE_DEV.domain.*" %>
   <%@page import="java.util.*" %>
   <%@page import="java.sql.*"%>
   <%@page import="java.io.InputStream"%>
   <%@page import="java.io.OutputStream"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<script type="text/javascript" src="../JS/Coursedetails.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KODE_DEV</title>
<link href="../CSS/webindex.css" rel="stylesheet"></link>
<script type="text/javascript">
function institutions(){

	document.CourseDetails.action="/KODE_DEV/ControllerServlet/VocationalCoursesService";
	document.CourseDetails.submit(); 
	//window.location.href = document.getElementById('Institutions').href;
}
</script>

</head>
<body>
<%@ include file="../JSP/WebViewHeader.jsp" %>
<div class="body-mtr"><!--  body content start -->
<div class="container">
<!-- testing part -->
<div class="subject-div">
<form name="CourseList" method="post">
<ul class="subject-ul">

<%
DisplayCoursesDao dc=new DisplayCoursesDao();
ArrayList<RetriveImagesDomain> arl=new ArrayList<RetriveImagesDomain>();
arl=dc.selectCourses();
	Iterator<RetriveImagesDomain> it= arl.iterator();
	while(it.hasNext())
    {
		RetriveImagesDomain dd=it.next();
     %>
     <li class="nn-part"><a href="/KODE_DEV/ControllerServlet/CourseDetailsService?CourseName=<%=dd.getCourseName()%>" ><img class="dbimg" src="<%=dd.getCourseImageLocation().toString() %>"/><br/><span><%=dd.getCourseName()%></span></a></li>
     
  <% } %>

</ul>
</form>
</div>
</div>
</div>
<div id="page_footer1">
<%@ include file="../JSP/FooterView.jsp" %>
</div>

</body>
</html>