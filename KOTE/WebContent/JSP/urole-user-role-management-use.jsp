<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.kes.kote.util.*" %>
<%@page import="java.util.*" %>
<%@page import="com.kes.kote.domain.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>URole - User Role Management (USE)</title>

<link type="text/css" href="../css/font-awesome.css" rel="stylesheet" />
<link type="text/css" href="../css/font-awesome.min.css" rel="stylesheet" />
<link type="text/css" href="../css/style.css" rel="stylesheet" />
<link type="text/css" href="../css/responsive.css" rel="stylesheet" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/custom.js"></script>
<script type="text/javascript" src="../js/scroll_table.js"></script>
<script type="text/javascript" src="../js/fixed_table_rc.js"></script>


<style type="text/css">
@font-face {
	font-family: 'FontAwesome';
	src: url('fonts/fontawesome-webfont.eot?v=4.7.0');
	src: url('fonts/fontawesome-webfont.eot?#iefix&v=4.7.0') format('embedded-opentype'), url('fonts/fontawesome-webfont.woff2?v=4.7.0') format('woff2'), url('fonts/fontawesome-webfont.woff?v=4.7.0') format('woff'), url('fonts/fontawesome-webfont.ttf?v=4.7.0') format('truetype'), url('fonts/fontawesome-webfont.svg?v=4.7.0#fontawesomeregular') format('svg');
	font-weight: normal;
	font-style: normal
}
@font-face {
	font-family: 'robotomedium';
	src: url('fonts/roboto-medium.woff2') format('woff2'), url('fonts/roboto-medium.woff') format('woff');
	font-weight: normal;
	font-style: normal;
}
</style>

<%
if(session.getAttribute("username")==null)
	response.sendRedirect("../JSP/sessionerror.jsp");

List<UseRoleDomain> roleDetails=ExcelSheetUtil.getUseRoles(session);
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

<div class="show-status0 common"><div class="circle1"><strong></strong></div></div>
<div class="show-status1 common"><div class="circle2"><strong></strong></div></div>

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
  </div>
</header>
<section class="middle-column">
  <div class="container">
    <div class="page-title">
      <h3>URole - User Role Management <span>(USE)</span></h3>
    </div>
    <form class="paramForm" id="paramForm" action="/KOTE/ControllerServlet/UseService?From=roleMang" method="post">
    <div class="dwrapper">
      <table id="fixed_hdr2">
        <thead>
          <tr>
            <th class="header-cell col11">Role ID</th>
            <th class="header-cell col11">Role Description</th>
            <th class="header-cell col11">Role Type</th>
          </tr>
        </thead>
        <tbody>
          <%if(roleDetails!=null && roleDetails.size()>0){
        	for(int i=0;i<roleDetails.size();i++) {
        		UseRoleDomain useParam = roleDetails.get(i);
        		
        		String id="";if(useParam.getRoleId()!=null)id=useParam.getRoleId();
        		String desc="";if(useParam.getDescription()!=null)desc=useParam.getDescription();
        		String type="";if(useParam.getType()!=null) type=useParam.getType();
        		boolean odd=true;
        		int cnt=i+1;
        		int even=cnt%2;
        		if(even==0)
        			odd=false;
        		else
        			odd=true;
        		if(odd) {%>
        			<tr class="light-bg">
        		<%	} else  { %>
        			<tr class="dark-bg">
        		<% } %> 
            <td class="body-cell col11"><input type="text" name="roleId<%=i%>" id="roleId<%=i%>" value="<%=id%>" placeholder="Enter Role ID" maxlength="25" /></td>
            <td class="body-cell col11"><input type="text" name="desc<%=i%>" class="txtOnly" id="desc<%=i%>" value="<%=desc%>" placeholder="Enter Role Description"  maxlength="50" /></td>
            <td class="body-cell col11">
            <select name="type<%=i%>">
                	<option value="" selected>Select Role Type</option>
                	<option value="Super Admin" <% if(type.trim().equalsIgnoreCase("SuperAdmin")) { %> selected <%} %>  >Super Admin</option>
                    <option value="Admin"  <% if(type.trim().equalsIgnoreCase("Admin")) { %> selected <%} %>  >Admin</option>
                    <option value="Faculty"  <% if(type.trim().equalsIgnoreCase("Faculty")) { %> selected <%} %>  >Faculty</option>
                </select></td>
                
           <% }%>
        <%}%>     
          </tr>
          
         
        </tbody>
      </table>
      <div class="btnblock margtop20">
        <input type="submit" value="Submit" class="button"/>
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
<div class="body-overlay"></div>
</body>
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
			{ width: 25, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'center' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 5, align: 'center' }
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
    fill: { color: "#DAA520" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(100 * progress) + '<span>% CINE</span>');
  });
  $('.circle2').circleProgress({
	value: 0.42,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.floor(42 * progress) + '<span>% USE</span>');
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
    })
});
</script>
</html>