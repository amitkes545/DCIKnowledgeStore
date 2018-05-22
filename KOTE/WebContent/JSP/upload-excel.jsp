<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.kes.kote.util.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Upload Excel</title>
<link type="text/css" href="../css/style.css" rel="stylesheet" />
<link type="text/css" href="../css/responsive.css" rel="stylesheet" />
<link type="text/css" href="../css/font-awesome.min.css" rel="stylesheet" />
<script type="text/javascript" src="../js/custom.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
<script src="../js/custom.js" type="text/javascript"></script>


<style type="text/css">
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

.loader {
  width: 120px;
  height: 120px;
      position: fixed;
    display: inline-block;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    margin: auto;
    display: block !important;
   
}

.Please-text{
	position: relative;
    top:0px;
    border-radius: 5px;
    text-align: center;
    margin: auto;
    width:100%;
    height:100%;
    padding: 10px;
    background:rgba(0,0,0,0.6);
    border: medium none;
    color: #fff;
}
</style>

<%
SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
%>
<script type="text/javascript">    
   window.history.forward();
   function noBack() { 
       window.history.forward(); 
   }
</script>
</head>
<body  onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<header>
  <div class="container">
    <div class="logo right"><a href="#"><img src="../images/logo.png" alt="KES Logo" /></a></div>
  </div>
</header>

<% if(session.getAttribute("username")==null)
	response.sendRedirect("../JSP/sessionerror.jsp"); %>

<section class="middle-container">
  <div class="banner_small">
    <div class="main_menu">
      <div class="menu_btn"> <a href="#" class="menubar" onclick="openNav()"> <i class="fa fa-bars" aria-hidden="true"></i> </a>
        <div id="mySidenav" class="left-nave-menu sidenav"> <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
          <ul>
            <li><a href="#"><span><i class="fa fa-sliders resize-icon" aria-hidden="true"></i></span>Configuration</a> </li>
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
  </div>
  <div class="container">
  <form id="uploadexcelform" name="uploadexcelform" action="" method="post" enctype="multipart/form-data">
    <div class="uploadbox">
      <div class="box1 inline vtop">
        <h5>Configuration</h5>
        <a class="createmanual" href="#" onclick="createManually();" ><img src="../images/arrow-bullet.png" alt="Arrow Bullet" /><span>Create Manually</span></a>
        <div class="excelupload"><img src="../images/arrow-down.png" alt="Arrow Bullet" /><span>Excel Upload</span></div>
        <div class="upload">
          <input type="file" name="uploadconfigexcel" id="uploadconfigexcel"  accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" />
        </div>
        <div class="upload-section">
          <button class="uploadbtn right" onclick="ConfigByUpload();">Upload</button>
        </div>
      </div>
      <div class="seprator inline vtop">
        <h5><img src="../images/seprator.png" alt="Separator" /></h5>
      </div>
      <div class="box2 inline vtop">
        <h5>Existing Students</h5>
        <div class="existingstud"><img src="../images/arrow-down.png" alt="Arrow Bullet" /><span>Excel Upload</span></div>
        <div class="upload">
          <input type="file" name="uploadstuexcel" id="uploadstuexcel" accept=".xlsx,.xls" />
        </div>
        <div class="upload-section">
          <button class="uploadbtn right" onclick="StudentByUpload();">Upload</button>
        </div>
      </div>
    </div>
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

<div id="spinner" class="loader" style="visibility:hidden;z-index:999;" ><img id = "myImage" src = "../images/wait.gif"/> <span style="padding-left:15px;color:#fff;font-size:15;">Please wait.... </span></div>  
    <div class="Please-text" id="waitText" style="visibility:hidden;">Please Wait... till process completes!</div>
    
</body>
<!-- <script type="text/javascript">
window.location.hash="no-back-button";
window.location.hash="Again-No-back-button";//again because google chrome don't insert first hash into history
window.onhashchange=function(){window.location.hash="no-back-button";}
</script> -->

<script type="text/javascript">
function skipFunction() {
 disableScreen();
 document.getElementById('spinner').style.visibility = 'visible';
 document.getElementById('waitText').style.visibility = 'visible';
}

function disableScreen() {
    var div= document.createElement("div");
    div.className += "overlay";
    document.body.appendChild(div);
}
</script>

<script type="text/javascript">

function createManually()
{
	document.uploadexcelform.action="/KOTE/ControllerServlet/UploadExcelService?From=Manually";
	document.uploadexcelform.submit();	
}
function ConfigByUpload()
{
	var f = $('#uploadconfigexcel').val();
	
	if(f.length>0)
	{
		skipFunction();
		
		document.uploadexcelform.action="/KOTE/ControllerServlet/UploadExcelService?From=ConfigUpload";
		document.uploadexcelform.submit();
		  
	}else {
		$('#uploadconfigexcel').css({ "border": "1px solid red"  });		
	}
	
}
function StudentByUpload()
{
	document.uploadexcelform.action="/KOTE/ControllerServlet/UploadExcelService?From=StudentUpload";
	document.uploadexcelform.submit();	
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
	$(".menubar").click(function(){
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
});
</script>
</html>