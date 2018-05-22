<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.kes.kote.util.*" %>
<%@page import="com.kes.kote.domain.*" %>
<%@page import="com.kes.kote.dao.*" %>
<%@page import="com.kes.kote.interfaces.*" %>
<%@page import="java.util.*" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>CACS - Course Catalog System (STACS)</title>
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
<style>
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

List<StacsCourseCatelogDomain> CourseCatelogDetails=ExcelSheetUtil.getCourseCatelogDetails(session);

LookupInterface dao=new LookupDao();
List<DisciplineDomain> DesciplineList=dao.getDesciplineList(session);
List<ProgramDomain> ProgramList=dao.getProgramList(session);
List<UsersInfoDomain> AllUsersList=dao.getAllUsersList(session);
HashMap<String, String> UidMapping=dao.getUidMapping(session);

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
      <h3> CACS - Course Catalog System <span>(STACS)</span></h3>
    </div>
    <form class="cine-setup" action="/KOTE/ControllerServlet/StacsService?From=CourseCatelog" method="post">
    <div class="dwrapper">
      <table id="fixed_hdr2">
        <thead>
          <tr>
            <th class="header-cell col1">Discipline Name</th>
            <th class="header-cell col1">Program Name</th>
            <th class="header-cell col1">Course ID</th>
            <th class="header-cell col1">Course Name</th>
            <th class="header-cell col1">Course Image Name</th>
            <th class="header-cell col1">Course Image Size</th>
            <th class="header-cell col1">Department ID</th>
            <th class="header-cell col1">Department Name</th>
            <th class="header-cell col1">Dept Image Name</th>
            <th class="header-cell col1">Dept Image Size</th>
            <th class="header-cell col1">Duration Type</th>
            <th class="header-cell col1">Duration Period</th>
            <th class="header-cell col1">Teaching Pattern</th>
            <th class="header-cell col1">Total No. of Sessions</th>
            <th class="header-cell col1">Can be done Parallel?</th>
            <th class="header-cell col1">Primary Admin UID</th>
            <th class="header-cell col1">Secondary  Admin UID</th>
            
          </tr>
        </thead>
        <tbody>
        <%
        if(CourseCatelogDetails!=null && CourseCatelogDetails.size()>0)
        {
        	for(int i=0;i<CourseCatelogDetails.size();i++)
        	{
        		StacsCourseCatelogDomain row=CourseCatelogDetails.get(i);
        		
        		String DisciplineIDRowKey="DiscName"+i;
				String DisciplineNameRowKey="";
				String ProgramNameRowKey="ProgName"+i;
				String CourseIDRowKey="CrsID"+i;
				String CourseNameRowKey="CrsName"+i;
				String CourseImageNameRowKey="CrsImgName"+i;
				String CourseImageSizeRowKey="CrsImgSize"+i;
				String DepartmentIDRowKey="DeptID"+i;
				String DepartmentNameRowKey="DeptName"+i;
				String DepartmentImageNameRowKey="DeptImgName"+i;
				String DepartmentImageSizeRowKey="DeptImgSize"+i;
				String DurationTypeRowKey="DurType"+i;
				String DurationPeriodRowKey="DurPrd"+i;
				String TeachingPatternRowKey="TeachPatt"+i;
				String NoOfSessionsRowKey="NoOfSess"+i;
				String CanBeDoneinParallelRowKey="CanBe"+i;
				String PAdminUIDRowKey="PAdminUID"+i;
				String SAdminUIDRowKey="SAdminUID"+i;
				
        		String DisciplineName=row.getDisciplineName();	if(DisciplineName==null)DisciplineName="";
				String ProgramName=row.getProgramName();	if(ProgramName==null)ProgramName="";
				String CourseID=row.getCourseID();	if(CourseID==null)CourseID="";
				String CourseName=row.getCourseName();	if(CourseName==null)CourseName="";
				String CourseImageName=row.getCourseImageName();	if(CourseImageName==null)CourseImageName="";
				String CourseImageSize=row.getCourseImageSize();	if(CourseImageSize==null)CourseImageSize="";
				String DepartmentID=row.getDepartmentID();	if(DepartmentID==null)DepartmentID="";
				String DepartmentName=row.getDepartmentName();	if(DepartmentName==null)DepartmentName="";
				String DepartmentImageName=row.getDepartmentImageName();	if(DepartmentImageName==null)DepartmentImageName="";
				String DepartmentImageSize=row.getDepartmentImageSize();	if(DepartmentImageSize==null)DepartmentImageSize="";
				String DurationType=row.getDurationType();	if(DurationType==null)DurationType="";
				int DurationPeriod=row.getDurationPeriod();	if(DurationPeriod<=0) DurationPeriod=0;
				String TeachingPattern=row.getTeachingPattern();	if(TeachingPattern==null)TeachingPattern="";
				int NoOfSessions=row.getNoOfSessions();	if(NoOfSessions<=0)NoOfSessions=0;
				String CanBeDoneinParallel=row.getCanBeDoneinParallel();	if(CanBeDoneinParallel==null)CanBeDoneinParallel="";
				String PAdminUID=row.getPAdminUID();	if(PAdminUID==null)PAdminUID="";
				String SAdminUID=row.getSAdminUID();	if(SAdminUID==null)SAdminUID="";
		%>	
		
		<tr class="light-bg">
            <td class="body-cell col1">
            <select id="<%=DisciplineIDRowKey %>" name="<%=DisciplineIDRowKey %>">
                <option value="" visible>Choose Discipline Name*</option>
                <% if(DesciplineList!=null && DesciplineList.size()>0)
                {
                	for(int j=0;j<DesciplineList.size();j++)
                	{
                		DisciplineDomain discinfo=DesciplineList.get(j);
                		
                		if(DisciplineName.trim().equalsIgnoreCase(discinfo.getDisciplineName()))
                		{
                			%> <option value="<%=discinfo.getDisciplineID() %>" selected ><%=discinfo.getDisciplineName() %></option> <%			
                		}else
                		{
                			%> <option value="<%=discinfo.getDisciplineID() %>" ><%=discinfo.getDisciplineName() %></option> <%
                		}
                	}
                }%>
                
                
              </select>
              </td>
            <td class="body-cell col1">
            <select id="<%=ProgramNameRowKey %>" name="<%=ProgramNameRowKey %>" >
                <option value="" visible>Choose Progaram Name*</option>
                <% 
                boolean proFound=false;
                if(ProgramList!=null && ProgramList.size()>0)
                {
                	for(int k=0;k<ProgramList.size();k++)
                	{
                		ProgramDomain pinfo=ProgramList.get(k);
                		
                		if(ProgramName.trim().equalsIgnoreCase(pinfo.getProgramName()))
                		{
                			proFound=true;
                			%> <option value="<%=pinfo.getProgramName() %>" selected ><%=pinfo.getProgramName() %></option> <%
                		}else
                		{
                			proFound=false;
                			%> <option value="<%=pinfo.getProgramName() %>" ><%=pinfo.getProgramName() %></option> <%
                		}
                	}
                }
                if(proFound==false)
                {	%> <option value="<%=ProgramName %>" selected ><%=ProgramName %></option> <% }
                %>
                
              </select>
            </td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="<%=CourseIDRowKey %>" placeholder="Course ID" value="<%=CourseID %>" /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="<%=CourseNameRowKey %>" placeholder="Course Name" value="<%=CourseName %>" /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="<%=CourseImageNameRowKey %>" placeholder="Course Image Name" value="<%=CourseImageName %>" /></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="<%=CourseImageSizeRowKey %>" placeholder="Course Image Size" value="<%=CourseImageSize %>" /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="<%=DepartmentIDRowKey %>" placeholder="Department ID" value="<%=DepartmentID %>" /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="<%=DepartmentNameRowKey %>" placeholder="Department Name" value="<%=DepartmentName %>" /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="<%=DepartmentImageNameRowKey %>" placeholder="Dept Image Name" value="<%=DepartmentImageName %>" /></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="<%=DepartmentImageSizeRowKey %>" placeholder="Dept Image Size" value="<%=DepartmentImageSize %>" /></td>
            <td class="body-cell col1">
            <select name="<%=DurationTypeRowKey%>">
                <option value="" visible>Choose Duration Type*</option>
                <option value="Years" <%if(DurationType.trim().equalsIgnoreCase("Years")) { %> selected <%} %> >Years</option>
                <option value="Months" <%if(DurationType.trim().equalsIgnoreCase("Months")) { %> selected <%} %> >Months</option>
                <option value="Weeks" <%if(DurationType.trim().equalsIgnoreCase("Weeks")) { %> selected <%} %> >Weeks</option>
                <option value="Days" <%if(DurationType.trim().equalsIgnoreCase("Days")) { %> selected <%} %> >Days</option>
            </select>
            </td>
            <td class="body-cell col1"><input type="text" class="number-only" name="<%=DurationPeriodRowKey %>" placeholder="Duration Period" value="<%= DurationPeriod %>" /> </td>
            <td class="body-cell col1">
            <select name="<%= TeachingPatternRowKey%>">
                <option value="" visible>Choose Teaching Pattern*</option>
                <option value="nonsemester" <%if(TeachingPattern.trim().equalsIgnoreCase("nonsemester")) { %> selected <%} %> >Non-Semester</option>
                <option value="semester" <%if(TeachingPattern.trim().equalsIgnoreCase("semester")) { %> selected <%} %> >Semester</option>
                <option value="trimester" <%if(TeachingPattern.trim().equalsIgnoreCase("trimester") || TeachingPattern.trim().equalsIgnoreCase("trimister")) { %> selected <%} %> >Trimester</option>
                <option value="yearly" <%if(TeachingPattern.trim().equalsIgnoreCase("yearly")) { %> selected <%} %> >Yearly</option>
            </select>
            </td>
            <td class="body-cell col1"><input type="text" class="number-only" name="<%=NoOfSessionsRowKey %>" placeholder="Total No. of Sessions" value="<%=NoOfSessions %>" maxlength="4" /></td>
            <td class="body-cell col1"><select name="<%= CanBeDoneinParallelRowKey%>">
                <option value="" visible>Choose Can be done Parallel?*</option>
                <option value="yes" <%if(CanBeDoneinParallel.trim().equalsIgnoreCase("Yes")) { %> selected <%} %> >Yes</option>
                <option value="no" <%if(CanBeDoneinParallel.trim().equalsIgnoreCase("No")) { %> selected <%} %> >No</option>
            </select></td>
            <td class="body-cell col9" style="text-align: center;">
            <select name="<%=PAdminUIDRowKey %>">
                <option value="" visible>Choose Admin UID*</option>
                
                 <%
                if(AllUsersList!=null && AllUsersList.size()>0)
            	{
            		for(int k=0;k<AllUsersList.size();k++)
            		{
            			UsersInfoDomain user=AllUsersList.get(k);
            			
            			String id=user.getUserID();
            			String name=user.getUserName();
            			String selected="";
            			String uid_ts="";
            			if(UidMapping.containsKey(id))
            			{
            				uid_ts=UidMapping.get(id);
            				if(uid_ts.trim().equalsIgnoreCase(PAdminUID))
            					selected="selected";
            				
            			}
            		%>	<option value="<%=id %>" <%=selected%>  ><%=uid_ts %></option> <%
            		}
            	}
            	%>
                
                </select>
            </td>
            <td class="body-cell col9" style="text-align: center;">
            <select name="<%=SAdminUIDRowKey %>">
                <option value="" visible>Choose Admin UID*</option>
                
                 <%
                if(AllUsersList!=null && AllUsersList.size()>0)
            	{
            		for(int k=0;k<AllUsersList.size();k++)
            		{
            			UsersInfoDomain user=AllUsersList.get(k);
            			
            			String id=user.getUserID();
            			String name=user.getUserName();
            			String selected="";
            			String uid_ts="";
            			if(UidMapping.containsKey(id))
            			{
            				uid_ts=UidMapping.get(id);
            				if(uid_ts.trim().equalsIgnoreCase(SAdminUID))
            					selected="selected";
            				
            			}
            		%>	<option value="<%=id %>" <%=selected%>  ><%=uid_ts %></option> <%
            		}
            	}
            	%>
                
                </select>
            </td>
          </tr>
          	
        <%	}
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
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 5, align: 'left' },
			{ width: 25, align: 'left' },
			{ width: 25, align: 'left' },
			{ width: 25, align: 'left' },
			{ width: 25, align: 'left' },
			{ width: 25, align: 'left' },
			{ width: 25, align: 'left' },
			{ width: 30, align: 'left' },
			{ width: 30, align: 'center' }
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
    fill: { color: "#DAA520" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(100 * progress) + '<span>% STACS</span>');
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