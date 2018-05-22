<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
	
<%@page import="com.kes.kote.util.*" %>
<%@page import="com.kes.kote.dao.*" %>
<%@page import="com.kes.kote.domain.*" %>
<%@page import="com.kes.kote.interfaces.*" %>
<%@page import="java.util.*" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Image -  Image Loading</title>
<link type="text/css" href="../css/font-awesome.css" rel="stylesheet" />
<link type="text/css" href="../css/font-awesome.min.css" rel="stylesheet" />
<link type="text/css" href="../css/style.css" rel="stylesheet" />
<link type="text/css" href="../css/responsive.css" rel="stylesheet" />
<script src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/custom.js"></script>
<script type="text/javascript" src="../js/scroll_table.js"></script>
<script type="text/javascript" src="../js/fixed_table_rc.js"></script>

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
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<div class="show-status0 common"><div class="circle1"><strong></strong></div></div>
<div class="show-status1 common"><div class="circle2"><strong></strong></div></div>
<div class="show-status2 common"><div class="circle3"><strong></strong></div></div>
<div class="show-status3 common"><div class="circle4"><strong></strong></div></div>
<div class="show-status4 common"><div class="circle5"><strong></strong></div></div>
<div class="show-status5 common"><div class="circle6"><strong></strong></div></div>

<header>
  <div class="container">
    <div class="client_logo right"> <a href="#"><img src="../images/logo.png" alt="logo" /></a> </div>
  </div>
  <div class="header_banner">
    <div class="container">
      <div class="main_menu">
        <div class="menu_btn"> <a class="menubar1" onclick="openNav()"> <i class="fa fa-bars" aria-hidden="true"></i> </a>
          <div id="mySidenav" class="left-nave-menu sidenav"> <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <ul>
              <li><a href="#"><span><i class="fa fa-sliders resize-icon" aria-hidden="true"></i></span>Configuration</a> </li>
              <li><a href="#"> <span><i class="fa fa-user resize-icon" aria-hidden="true"></i></span>Customer</a> </li>
              <li><a href="#"> <span><i class="fa fa-users resize-icon" aria-hidden="true"></i></span>User Management</a> </li>
              <li><a href="#"> <span><i class="fa fa-list-alt resize-icon" aria-hidden="true"></i></span>Setup Curriculum</a> </li>
              <li><a href="#"> <span><i class="fa fa-snowflake-o resize-icon" aria-hidden="true"></i></span>Knowledge Store</a> </li>
              <li><a href="#"> <span><i class="fa fa-filter resize-icon" aria-hidden="true"></i></span>Fees Config</a> </li>
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
  </div>
</header>
<section class="middle-column">
  <div class="container">
    <div class="page-title">
      <h3><span>Image -</span>  Image Loading</h3>
    </div>
    <!-- <form class="cine-setup" action="/KOTE/ControllerServlet/ImageService" method="post" enctype="multipart/form-data"> -->
    <div class="dwrapper">
      <table id="fixed_hdr2">
        <thead>
          <tr>
            <th class="header-cell col11">Image Type</th>
            <th class="header-cell col11">Source Path</th>
            <th class="header-cell col11">Status</th> 
          </tr>
        </thead>
        <tbody>
          <tr class="light-bg">
            <td class="body-cell col1 nomargpad"><div class="fillbox">Course Images</div></td>
            <td class="body-cell col1"><input type="file" id="Course" onchange="callSave(event)" webkitdirectory mozdirectory msdirectory odirectory directory /></td>
            <td class="body-cell col1"><img src="../images/done.png" id="crstick" > </img></td>
          </tr>
          <tr class="light-bg">
            <td class="body-cell col1 nomargpad"><div class="fillbox">Department Images</div></td>
            <td class="body-cell col1"><input type="file" id="Department" onchange="callSave(event)" webkitdirectory mozdirectory msdirectory odirectory directory /></td>
            <td class="body-cell col1"><img src="../images/done.png" id="depttick" /></td>
          </tr>
          <tr class="light-bg">
            <td class="body-cell col1 nomargpad"><div class="fillbox">Teaching Source Image</div></td>
            <td class="body-cell col1"><input type="file" id="TSImage" onchange="callSave(event)" webkitdirectory mozdirectory msdirectory odirectory directory /></td>
            <td class="body-cell col1"><img src="../images/done.png" id="tstick" /></td>
          </tr>
          <tr class="light-bg">
            <td class="body-cell col1 nomargpad"><div class="fillbox">Certificate Images</div></td>
            <td class="body-cell col1"><input type="file" id="CDImage" onchange="callSave(event)" webkitdirectory mozdirectory msdirectory odirectory directory /></td>
            <td class="body-cell col1"><img src="../images/done.png" id="cdtick" /></td>
          </tr>
          
        
        </tbody>
      </table>
      <div class="btnblock margtop20">
        <input type="submit" value="Submit" class="button" onclick="openPage()" />
      </div>
    </div>
   <!-- </form> -->
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

<div class="body-overlay"></div>
</body>

<script type="text/javascript">
 function openPage()
 {
 window.location.href = "thankyou.jsp";
 }
</script>

<script type="text/javascript">
function callSave(e)
{
	
	
	var allFiles = e.target.files;
	var eleID=e.target.id;
	var isAllImage=false;
	for (i = 0; i < allFiles.length; i++) {
		var fileName = allFiles[i]. name
		isAllImage = fileName.endsWith(".png")
		//alert(fileName  +"	isimage == "+isAllImage);
	}
	//alert(isAllImage);
if(isAllImage==true)
	{
		//alert("More:"+allFiles.length)
		
		var start = 0;
		var setter 
		var buff = true;
		
		setter = setInterval(function() {
			//alert("buff")
			 if (buff == true) {
				
				buff = false;
				var uploader = new XMLHttpRequest()
				var file = new FormData();
				file.append('file', allFiles[start])
				uploader.onreadystatechange = function() {
					//alert("here more..")
					if (uploader.readyState == 4
							&& uploader.status == 200) {
					//	alert(uploader.responseText)
						start++;
						buff = true;
					}

				}
				//alert("1 "+window.location.href);
				
				uploader.open('POST', '../ControllerServlet/ImageService', true);
				uploader.send(file);
									
			}
			if(start > allFiles.length){
				clearInterval(setter)
			} 

		}, 500);
		if(eleID=="Course")
			$("#crstick").css("visibility", "visible");
		else if(eleID=="Department")
			$("#depttick").css("visibility", "visible");
		else if(eleID=="TSImage")
			$("#tstick").css("visibility", "visible");
		else if(eleID=="CDImage")
			$("#cdtick").css("visibility", "visible");
	}else
		{
			alert("Folder needs to contains only .PNG images");
			$("#"+eleID).val("");
			if(eleID=="Course")
				$("#crstick").css("visibility", "hidden");
			else if(eleID=="Department")
				$("#depttick").css("visibility", "hidden");
			else if(eleID=="TSImage")
				$("#tstick").css("visibility", "hidden");
			else if(eleID=="CDImage")
				$("#cdtick").css("visibility", "hidden");
			
		}
}
</script>
<script type="text/javascript">
	$(function () {
		$('#fixed_hdr1').fxdHdrCol({
			fixedCols:0,
			width:     '100%',
			height:    400,
			colModal: [
			]					
		});		
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 400,
			colModal: [
			{ width: 140, align: 'left' },
			{ width: 180, align: 'left' },
			{ width: 180, align: 'left' },
			{ width: 120, align: 'left' },
			{ width: 100, align: 'left' },
			{ width: 150, align: 'left' },
			{ width: 200, align: 'left' }
			],
			sort: false
		});		
	});
	$(document).on('click', '#select_all', function(){
		if($(this).is(':checked')){
			$('.children_checkbox').prop('checked', true);
		} else {
			$('.children_checkbox').prop('checked', false);
		}
	});	
</script>
<script>
function openNav() {
    document.getElementById("mySidenav").style.width = "320px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
</script>
<script src="../js/circle-progress.js"></script>
<script type="text/javascript">
  $('.circle1').circleProgress({
	value: 1.0,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(100 * progress) + '<span>% CINE</span>');
  });
  $('.circle2').circleProgress({
	value: 1.0,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(100 * progress) + '<span>% USE</span>');
  });
  $('.circle3').circleProgress({
	value: 1.0,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(100 * progress) + '<span>% STAMP</span>');
  });
  $('.circle4').circleProgress({
	value: 1.0,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(100 * progress) + '<span>% STACS</span>');
  });
  $('.circle5').circleProgress({
	value: 1.0,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(100 * progress) + '<span>% FISE</span>');
  });
  $('.circle6').circleProgress({
	value: 1.0,
    size: 90,
    fill: { color: "#DAA520" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(100 * progress) + '<span>% IMAGE</span>');
  });
</script>
<script>
$(document).ready(function() {
	$(".menubar1").click(function(){
		$(".sidenav,.body-overlay").css("display","block").show();
    });
	$(".closebtn").click(function(){
		$(".body-overlay").hide();
    });
	$(".body-overlay").click(function(){
		 document.getElementById("mySidenav").style.width = "0";
		 $(".body-overlay").hide();
    });
	
	$("#crstick").css("visibility", "hidden");
	$("#depttick").css("visibility", "hidden");
	$("#tstick").css("visibility", "hidden");
	$("#cdtick").css("visibility", "hidden");
	$("#autick").css("visibility", "hidden");
});
</script>
</html>