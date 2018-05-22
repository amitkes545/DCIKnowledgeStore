<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KODE_DEV</title>
<link href="../CSS/WebViewHeader.css" rel="stylesheet"></link>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- <script> 
$(document).ready(function(){
    $("#courses_menu").click(function(){
        $("#submenu_div").slideToggle(20);
    });
});
</script> -->
</head>
<body>
<div style="clear: both;"></div>
<%@ include file="../JSP/WebViewHeader1.jsp"  %>

<div class="menu_container">
<div class="header">
<div class="header-left">
<img src="../Image/kducom-logo.jpg" />
</div>
<div class="header-right">
<ul class="menu">
<li><a href="../JSP/WebView.jsp" id="courses_menu">Courses</a></li>
<li><a href="../JSP/InstitutionsList.jsp">Institutions</a></li>
<li><a href="../JSP/VocationalCourses.jsp">Vocational</a></li>
<li><a href="../JSP/OpenCourses.jsp">Open Course Ware</a></li>
</ul>



</div>
<div id="submenu_div">
<ul class="menu1">
<li><a href="#">Courses</a></li>
<li><a href="#">Vocational</a></li>
<li><a href="#">Institutions</a></li>
<li><a href="#">Subjects</a></li>
<li><a href="#">Open Course Ware</a></li>
</ul>
</div>
</div><!-- close div header -->
</div>
<div style="clear: both;"></div>



</body>
</html>