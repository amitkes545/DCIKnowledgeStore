<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="com.kds.KODE_DEV.dao.*"  %>
   <%@page import="com.kds.KODE_DEV.domain.*" %>
    <%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="../CSS/webindex.css" rel="stylesheet"></link>
<link href="../CSS/VocationalCourse.css" rel="stylesheet"></link>
<style type="text/css">
.InstitutionDetails_li a
{text-decoration: none;
color: #000;
text-transform: capitalize;
}

</style>
</head>
<body>
<%@ include file="../JSP/WebViewHeader.jsp"  %>
<div class="body-mtr"><!--  body content start -->
<div class="container">
<!-- testing part -->

<div class="subject-div">
<form name="VocationalCoursesDetails" method="post" action="../JSP/Login.jsp">

<div class="vocational_left">

<ul class="vocational_left_ul">

<%
ArrayList<DisplayCoursesDomain> arl=(ArrayList<DisplayCoursesDomain>)request.getAttribute("InstitutionsList");

//Iterator<DisplayCoursesDomain> it= arl.iterator();


	DisplayCoursesDomain dd=arl.get(0);
%>
     				<li class="nn-part"><a href="#" ><img class="db_big_img" src="<%=dd.getImagePath()%>"/><br/></a></li>
     				
   
    
    
  <%    %>

</ul>

</div>

<div class="vocational_right">

<ul class="vocational_right_ul">

<%
ArrayList<DisplayCoursesDomain> arl1=(ArrayList<DisplayCoursesDomain>)request.getAttribute("InstitutionsList");
Iterator<DisplayCoursesDomain> it1= arl1.iterator();
DisplayCoursesDomain dd2=arl1.get(0);
%>
<li> <%=dd2.getCourseName()%></li>
<%
while(it1.hasNext())
{
	DisplayCoursesDomain dd1=it1.next();
%>
		
       
	 <li class="InstitutionDetails_li"><a href="#"><%=dd1.getCourseDetails() %></a></li> 		
  <%
  }  %>
</ul>


</div>
</form>

</div>
</div>
</div>
<div id="page_footer1">
<p id="footer-text">Powered by <a href="www.kompacdigit.com" target="_newwindow" style="color: #fff;text-decoration: none">Kompac Digital Systems</a></p>
</div>

</body>
</html>