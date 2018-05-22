<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KODE_DEV</title>
<link href="../CSS/styles1.css" rel="stylesheet"></link>

<!-- <link href="../CSS/WebViewHeader.css" rel="stylesheet"></link> -->
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.15/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>

/* $(document).ready(function(){
    $("#courses_menu").click(function(){
        $("#submenu_div").slideToggle(20);
    });
}); */

</script>
<style type="text/css">
.menu li{padding: 10px 13px;}
</style>
</head>
<body ng-app="">
<%@ include file="../JSP/WebViewHeader1.jsp"  %>
<%

/** 

Faculty View Header Page 
It contains header and links to all other menus 

*/


%>




<div style="clear: both;"></div>
<div class="menu_container">
<div class="header">
<div class="header-left">
<img src="../Image/kducom-logo.jpg" />
</div>
<div class="header-right">
<ul class="menu">
<li><a href="../JSP/FacultyViewParticipants.jsp" id="courses_menu">Participants</a></li>
<li><a href="../JSP/FacultyKnowledgeAssets.jsp">Knowledge Assets</a></li>
<li><a href="../JSP/Ftpfile.jsp">KnoWStore</a></li>
<li><a href="../JSP/FacultySessionManagement.jsp">Session Management</a></li>
<li><a href="/KODE_DEV/ControllerServlet/FacilitatorDashBoardService">Dash board</a></li>
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

<!-- Here after delete -->

</body>
</html>