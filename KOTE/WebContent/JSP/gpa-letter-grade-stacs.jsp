<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
	
<%@page import="com.kes.kote.util.*" %>
<%@page import="com.kes.kote.dao.*" %>
<%@page import="com.kes.kote.interfaces.*" %>
<%@page import="com.kes.kote.domain.*" %>
<%@page import="java.util.*" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>GPA - Letter Grade Points with Range (STACS)</title>
<link type="text/css" href="../css/style.css" rel="stylesheet" />
<link type="text/css" href="../css/font-awesome.css" rel="stylesheet" />
<link type="text/css" href="../css/font-awesome.min.css" rel="stylesheet" />
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
	src: url('../JSP/fonts/fontawesome-webfont.eot?v=4.7.0');
	src: url('../JSP/fonts/fontawesome-webfont.eot?#iefix&v=4.7.0') format('embedded-opentype'), url('../JSP/fonts/fontawesome-webfont.woff2?v=4.7.0') format('woff2'), url('../JSP/fonts/fontawesome-webfont.woff?v=4.7.0') format('woff'), url('../JSP/fonts/fontawesome-webfont.ttf?v=4.7.0') format('truetype'), url('../JSP/fonts/fontawesome-webfont.svg?v=4.7.0#fontawesomeregular') format('svg');
	font-weight: normal;
	font-style: normal
}
</style>

<%

if(session.getAttribute("username")==null)
	response.sendRedirect("../JSP/sessionerror.jsp");

List<StacsSGPACGPADomain> GradesDetails=ExcelSheetUtil.getSgpaCgpaDetails(session);

LookupInterface dao=new LookupDao();
List<LetterGradeDomain> letterGrades=dao.getLetterGrades(session);

SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
// util.printsheetdata();

%>
<script type="text/javascript">    
   window.history.forward();
   function noBack() { 
       window.history.forward(); 
   }
</script>

</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="" >
<div class="show-status0 common"><div class="circle1"><strong></strong></div></div>
<div class="show-status1 common"><div class="circle2"><strong></strong></div></div>
<div class="show-status2 common"><div class="circle3"><strong></strong></div></div>
<div class="show-status3 common"><div class="circle4"><strong></strong></div></div>
<header>
  <div class="container">
    <div class="client_logo right"><a href="#"><img src="../images/logo.png" alt="logo" /></a></div>
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
      <h3>GPA - Letter Grade Points with Range <span>(STACS)</span></h3>
    </div>
    <form class="cine-setup" action="/KOTE/ControllerServlet/StacsService?From=SGPACGPA" method="post">
    <div class="dwrapper">
      <table id="fixed_hdr2">
        <thead>
          <tr>
            <th class="header-cell colnew">Letter Grade</th>
            <th class="header-cell colnew">Description</th>
            <th class="header-cell colnew">Grade Points</th>
            <th class="header-cell colnew">Min. Range (%)</th>
            <th class="header-cell colnew">Max. Range (%)</th>
          </tr>
        </thead>
        <tbody>
        <%
        if(GradesDetails!=null && GradesDetails.size()>0)
        {
        	for(int i=0;i<GradesDetails.size();i++)
        	{
        		StacsSGPACGPADomain row=GradesDetails.get(i);
        		
        		String LGRK="LG"+i;
        		String DescRK="Desc"+i;
        		String GPRK="GP"+i;
        		String MinRK="Min"+i;
        		String MaxRK="Max"+i;
        		
        		String letterGrade=row.getLetterGrade();
        		String description=row.getDescription();
        		double gradePoints=row.getGradePoints();
        		double min=row.getMinRange();
        		double max=row.getMaxRange();
        	
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
    		
            <td class="body-cell colnew">
            	<select name="<%=LGRK %>"  >
            		<option value="" selected="">Select Letter Grade</option>
            		<%
            		if(letterGrades!=null && letterGrades.size()>0)
            		{
            			for(int j=0;j<letterGrades.size();j++)
            			{
            				LetterGradeDomain info=letterGrades.get(j);
            				
            				String lGrade=info.getLetterGrade();
            				%>
            				<option value="<%=lGrade%>" <% if(letterGrade.trim().equalsIgnoreCase(lGrade)) { %> selected <% } %>)> <%=lGrade%> </option>
            			<%}
            		}
            		%>
                   </select>
              </td>
              <td class="body-cell colnew"><input type="text" class="txtOnly" name="<%=DescRK %>" value="<%=description %>" maxlength="50" /></td>
              <td class="body-cell colnew"><input type="text" class="number-only" name="<%=GPRK %>" placeholder="" maxlength="8" value="<%=gradePoints %>" /></td>
              <td class="body-cell colnew"><input type="text" class="number-only" name="<%=MinRK %>" placeholder="" maxlength="8" value="<%=min %>"  /></td>
              <td class="body-cell colnew"><input type="text" class="number-only" name="<%=MaxRK %>" placeholder="" maxlength="8" value="<%=max %>" /></td>
          </tr>
        	<%}
        }
        %>
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
<div class="body-overlay"></div>
</body>
<script type="text/javascript">
	$(function () {	
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 400,
			colModal: [
			{ width: 150, align: 'left' },
			{ width: 150, align: 'left' },
			{ width: 150, align: 'left' },
			{ width: 150, align: 'left' },
			{ width: 150, align: 'left' },
			{ width: 150, align: 'left' },
			
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
	value: 0.77,
    size: 90,
    fill: { color: "#DAA520" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(77 * progress) + '<span>% STACS</span>');
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