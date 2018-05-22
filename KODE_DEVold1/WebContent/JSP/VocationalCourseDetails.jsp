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

.newdiv{display: none;}
</style>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script> 
$(document).ready(function(){

    $(".sub_btn").click(function(){
        /* $(".sem_i").slideToggle(20); */
        
        $(".vocational_right_ul").hide();
        $(".newdiv").show();
    });
    });
    </script>






</head>
<body>
<%@ include file="../JSP/WebViewHeader.jsp"  %>
<div class="body-mtr"><!--  body content start -->
<div class="container">
<!-- testing part -->

<div class="subject-div">
<form name="VocationalCoursesDetails" method="post" >

<div class="vocational_left">

<ul class="vocational_left_ul">

<%
ArrayList<DisplayCoursesDomain> arl=(ArrayList<DisplayCoursesDomain>)request.getAttribute("openCoursesList");

Iterator<DisplayCoursesDomain> it= arl.iterator();

while(it.hasNext())
{
	DisplayCoursesDomain dd=it.next();
%>
     				<li class="nn-part"><a href="#" ><img class="db_big_img" src="<%=dd.getImagePath()%>"/><br/><span><input class="sub_btn" type="button" name="DepartmentsName" value="<%=dd.getCourseFee()%>" ></input></span></a></li>
     				
   
    
    
  <%  }  %>

</ul>

</div>

<div class="vocational_right">

<div class="newdiv">
<h1 style="margin-left: 150px; margin-top: 100px;">Payment Mode Under Construction </h1>


</div>




<ul class="vocational_right_ul">

<%
ArrayList<DisplayCoursesDomain> arl1=(ArrayList<DisplayCoursesDomain>)request.getAttribute("openCoursesList");
Iterator<DisplayCoursesDomain> it1= arl1.iterator();
while(it1.hasNext())
{
	DisplayCoursesDomain dd1=it1.next();
%>
		<li><%=dd1.getCourseName()%></li>
       <%String[] s1=dd1.getCourseDetails().split("#");
       for(String s:s1){ %>
	 <li><%=s %></li> 		
  <%}
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