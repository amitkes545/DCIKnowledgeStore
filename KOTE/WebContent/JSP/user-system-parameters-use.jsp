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
<title>User System Parameters (USE)</title>
<link type="text/css" href="../css/font-awesome.css" rel="stylesheet" />
<link type="text/css" href="../css/font-awesome.min.css" rel="stylesheet" />
<link type="text/css" href="../css/style.css" rel="stylesheet" />
<link type="text/css" href="../css/responsive.css" rel="stylesheet" />

<script src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/scroll_table.js"></script>
<script type="text/javascript" src="../js/fixed_table_rc.js"></script>
<script type="text/javascript" src="../js/custom.js"></script>
<style type="text/css">
@font-face {
	font-family: 'FontAwesome';
	src: url('../fonts/fontawesome-webfont.eot?v=4.7.0');
	src: url('../fonts/fontawesome-webfont.eot?#iefix&v=4.7.0') format('embedded-opentype'), url('../fonts/fontawesome-webfont.woff2?v=4.7.0') format('woff2'), url('../fonts/fontawesome-webfont.woff?v=4.7.0') format('woff'), url('../fonts/fontawesome-webfont.ttf?v=4.7.0') format('truetype'), url('../fonts/fontawesome-webfont.svg?v=4.7.0#fontawesomeregular') format('svg');
	font-weight: normal;
	font-style: normal
}
@font-face {
	font-family: 'robotomedium';
	src: url('../fonts/roboto-medium.woff2') format('woff2'), url('../fonts/roboto-medium.woff') format('woff');
	font-weight: normal;
	font-style: normal
}
</style>


<%

if(session.getAttribute("username")==null)
	response.sendRedirect("../JSP/sessionerror.jsp");

List<UseParamDomain> paramDetails=ExcelSheetUtil.getUserParamDetails(session);

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
      <h3> User System Parameters <span>(USE)</span></h3>
    </div>
    <form class="paramForm" id="paramForm" action="/KOTE/ControllerServlet/UseService?From=useDetails" method="post">
    <div class="dwrapper">
      <table id="fixed_hdr2">
        <thead>
          <tr>
            <th class="header-cell uspcol4">Parameter Description</th>
            <th class="header-cell uspcol4">Parameter Key</th>
            <th class="header-cell uspcol4">Parameter Value</th>
            <th class="header-cell uspcol4">Parameter Type</th>
          </tr>
        </thead>
        <tbody>
        
        <%if(paramDetails!=null && paramDetails.size()>0){
        	for(int i=0;i<paramDetails.size();i++) {
        		UseParamDomain useParam = paramDetails.get(i);
        		
        		String desc="";if(useParam.getDescription()!=null)desc=useParam.getDescription();
        		String key="";if(useParam.getKey()!=null) key=useParam.getKey();
        		String val=""; if(useParam.getValue()!=null)val=useParam.getValue();
        		String type=""; if(useParam.getType()!=null)type=useParam.getType();
        		
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
            <td class="body-cell uspcol4"><%=desc%><input type="hidden" value="<%=desc%>" name="paramDesc<%=i%>"/></td>
            <td class="body-cell uspcol4"><%=key%><input type="hidden" value="<%=key%>" name="paramKey<%=i%>"/></td>
            <td class="body-cell uspcol4"><input type="text" name="paramValue<%=i%>" id="paramValue<%=i%>" class="textnumber" placeholder="Parameter Value" value="<%=val%>" maxlength="25" /></td>
            <td class="body-cell uspcol4"><%=type%><input type="hidden" value="<%=type%>" name="paramType<%=i%>"/></td>
            
            </tr>

        	<% }%>
        <%}%>
        
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
			{ width: 10, align: 'left' }
			],
			sort: false
		});		
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

<script src="../js/circle-progress.js" type="text/javascript"></script>
<script type="text/javascript">
  $('.circle1').circleProgress({
	value: 1.0,
    size: 90,
    fill: { color: "#DAA520" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(100 * progress) + '<span>% CINE</span>');
  });
  $('.circle2').circleProgress({
	value: 1.0,
    size: 90,
    fill: { color: "#DAA520" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.floor(100 * progress) + '<span>% USE</span>');
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