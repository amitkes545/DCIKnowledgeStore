<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.kes.kote.util.*" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CINE - TOP LAYER</title>
<link type="text/css" href="../css/style.css" rel="stylesheet" />
<link type="text/css" href="../css/responsive.css" rel="stylesheet" />

<link href="../css/font-awesome.min.css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<style>
@font-face {
 font-family:'FontAwesome';
 src:url('fonts/fontawesome-webfont.eot?v=4.7.0');
 src:url('fonts/fontawesome-webfont.eot?#iefix&v=4.7.0') format('embedded-opentype'), url('fonts/fontawesome-webfont.woff2?v=4.7.0') format('woff2'), url('fonts/fontawesome-webfont.woff?v=4.7.0') format('woff'), url('fonts/fontawesome-webfont.ttf?v=4.7.0') format('truetype'), url('fonts/fontawesome-webfont.svg?v=4.7.0#fontawesomeregular') format('svg');
 font-weight:normal;
 font-style:normal
}
@font-face {
 font-family: 'oswalddemibold';
 src: url('fonts/oswald-demibold.woff2') format('woff2'), url('fonts/oswald-demibold.woff') format('woff');
 font-weight: normal;
 font-style: normal;
}
@font-face {
 font-family: 'robotoregular';
 src: url('fonts/roboto-regular.woff2') format('woff2'), url('fonts/roboto-regular.woff') format('woff');
 font-weight: normal;
 font-style: normal;
}
</style>


<%
if(session.getAttribute("username")==null)
	response.sendRedirect("../JSP/sessionerror.jsp");

SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");

%>
<script type="text/javascript">    
   window.history.forward();
   function noBack() { 
       window.history.forward(); 
   }
</script>
</head>
<body class="dashboard-page" onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">

<header>
  <div class="container row">
    <div class="logo right"><a href="#"><img src="../images/logo.png" alt="KES Logo" /></a></div>
  </div>
</header>
<section class="middle-container">
  <div class="banner">
    <div class="main_menu">
      <div class="menu_btn"> <a href="#" class="menubar1" onclick="openNav()"> <i class="fa fa-bars" aria-hidden="true"></i> </a>
        <div id="mySidenav" class="left-nave-menu sidenav"> <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
          <ul>
            <li><a href="../JSP/upload-excel.jsp"><span><i class="fa fa-sliders resize-icon" aria-hidden="true"></i></span>Configuration</a> </li>
            <li><a href="#"> <span><i class="fa fa-user resize-icon" aria-hidden="true"></i></span>Customer</a> 
              
              <!--<ul class="submenu">
                            	<li><a href="#">Setup Customer</a></li>
                            </ul>--> 
            </li>
            <li><a href="#"> <span><i class="fa fa-users resize-icon" aria-hidden="true"></i></span>User Management</a> 
              
              <!--<ul class="submenu">
                            	<li><a href="#">Setup Knowledge Store</a></li>
                            </ul>--> 
            </li>
            <li><a href="#"> <span><i class="fa fa-list-alt resize-icon" aria-hidden="true"></i></span>Setup Curriculum</a> 
              
              <!--<ul class="submenu">
                            	<li><a href="#">Create</a></li>
                            </ul>--> 
            </li>
            <li><a href="#"> <span><i class="fa fa-snowflake-o resize-icon" aria-hidden="true"></i></span>Knowledge Store</a> 
              
              <!--<ul class="submenu">
                            	<li><a href="#">Setup Knowledge Store</a></li>
                            </ul>--> 
            </li>
            <li><a href="#"> <span><i class="fa fa-filter resize-icon" aria-hidden="true"></i></span>Fees Config</a> 
              
              <!--<ul class="submenu">
                            	<li><a href="#">Fees Creation</a></li>
                            	<li><a href="#">Inst. Fees Config</a></li>
                            	<li><a href="#">Coursewise Fees</a></li>
                            	<li><a href="#">Fees Group</a></li>
                            	<li><a href="#">Fees Receipt Template Design</a></li>                                
                            </ul>--> 
            </li>
            <li><a href="#"><span><i class="fa fa-briefcase resize-icon" aria-hidden="true"></i></span>My Account</a> </li>
            <li><a href="../JSP/change-password.jsp"><span><i class="fa fa-unlock-alt resize-icon" aria-hidden="true"></i> </span>Change Password</a> </li>
            <li><a href="#"><span> <i class="fa fa-question-circle resize-icon" aria-hidden="true"></i></span>FAQ</a> </li>
            <li><a href="#"><span><i class="fa fa-info-circle resize-icon" aria-hidden="true"></i></span>Help</a> </li>
            <li><a href="../JSP/logout.jsp"><span><i class="fa fa-sign-out resize-icon" aria-hidden="true"></i></span>Logout</a> </li>
          </ul>
        </div>
      </div>
    </div>
    <span>CINE - Customer Information Environment Setup - Top Layer</span> </div>
  <div class="container row">
  <form id="teachingform" name="teachingform" action="" method="post" >
  
    <ul class="teaching-source">
      <li class="univ"> <a href="#" onclick="submit('University');"><span>University</span> <img src="../images/univ.png" alt="University" /></a> </li>
      <li class="univ"> <a href="#" onclick="submit('Corporate');"><span>Corporate</span> <img src="../images/corp.png" alt="Corporate" /></a> </li>
      <li class="univ"> <a href="#" onclick="submit('VTI');"><span>VTI</span> <img src="../images/inst.png" alt="Institute" /></a> </li>
      <li class="univ"> <a href="#" onclick="submit('Professionals');"><span>Professionals</span> <img src="../images/prof.png" alt="Professionals" /></a> </li>
    </ul>
    </form>
  </div>
</section>
<footer>
  <center>
    <div class="container">
      <p>Copyright &copy; 2017. Kompac Education Systems Pvt. Ltd. All Rights Reserved.</p>
    </div>
  </center>
</footer>
<%  String SuccessMsg=""; if(util.getsuccessmsg()!=null) SuccessMsg=util.getsuccessmsg();
String FailureMsg=""; if(util.getfailedmsg()!=null) FailureMsg=util.getfailedmsg();

if(SuccessMsg.trim().length()>0)
{ %>
	<div class="success-notification"><%=SuccessMsg %></div>
<%	util.resetmsgs();} %>
	
<% if(FailureMsg.trim().length()>0)
{ %>
	<div class="failure-notification"><%=FailureMsg %></div>
<%	util.resetmsgs(); } %>
<div class="body-overlay"> </div>
</body>


<script type="text/javascript">
function submit(from)
{
	document.teachingform.action="/KOTE/ControllerServlet/UploadExcelService?From=TeachingSource&Selected="+from;
	document.teachingform.submit();
}
function openNav() {
    document.getElementById("mySidenav").style.width = "320px";
}
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
</script>
<script type="text/javascript">
$(document).ready(function() {
	$(".menubar1").click(function(){
		$(".sidenav,.body-overlay ").css("display","block").show();
    });
	$(".closebtn").click(function(){
		$(".body-overlay").hide();
    });
	$(".body-overlay").click(function(){
		 document.getElementById("mySidenav").style.width = "0";
		 $(".body-overlay").hide();
		 $(".dashboard_filter_list").slideUp();
    })
    
    $(".success-notification").fadeOut(5000);
	$(".failure-notification").fadeOut(5000);
});
</script>
</html>