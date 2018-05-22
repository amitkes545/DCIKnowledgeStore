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
<title>COCR - Course Credit System (STACS)</title>
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

List<StacsCourseCreditDomain> CourseCreditDetails=ExcelSheetUtil.getCourseCreditDetails(session);

LookupInterface dao=new LookupDao();
List<DisciplineDomain> DesciplineList=dao.getDesciplineList(session);
List<ProgramDomain> ProgramList=dao.getProgramList(session);
List<CourseInfoDomain> CourseList=dao.getCourseList(session);
List<DepartmetInfoDomain> DeptList=dao.getDeptList(session);
List<SubjectDomain> SubjectsList=dao.getSubjectsList(session);

SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
String[] cols=util.getSheetCols();
//util.printStringArray(cols);
//util.printsheetdata();


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
      <h3>COCR - Course Credit System <span>(STACS)</span></h3>
    </div>
    <form class="cine-setup" action="/KOTE/ControllerServlet/StacsService?From=CourseCredit" method="post">
    <div class="dwrapper">
      <table id="fixed_hdr2">
        <thead>
          <tr>
            <th class="header-cell col1">Discipline Name</th>
            <th class="header-cell col1">Program Name</th>
            <th class="header-cell col1">Course ID</th>
            <th class="header-cell col1">Department ID</th>
            <th class="header-cell col1">Semester</th>
            <th class="header-cell col1">Subject ID</th>
            <th class="header-cell col1">Session</th>
            <th class="header-cell col1">Theory</th>
            <th class="header-cell col1">Practical</th>
            <th class="header-cell col1">Session (Min)</th>
            <th class="header-cell col1">Theory (Min)</th>
            <th class="header-cell col1">Practical (Min)</th>
            <th class="header-cell col1">Credit Hours</th>
            
          </tr>
        </thead>
        <tbody>
        <%
        if(CourseCreditDetails!=null && CourseCreditDetails.size()>0)
        {
        	for(int i=0;i<CourseCreditDetails.size();i++)
        	{
        		StacsCourseCreditDomain row=CourseCreditDetails.get(i);
        		
        		// util.print(i+" = "+row.toString());
        		
        		String DisciplineNameRK="DiscName"+i;
				String ProgramNameRK="ProgName"+i;
				String CourseIDRK="CrsID"+i;
				String DepartmentIDRK="DeptID"+i;
				String SemesterRK="Semester"+i;
				String SubjectIDRK="SubID"+i;
				String SessionMaxRK="SMax"+i;
				String TheoryMaxRK="TMax"+i;
				String PracticalMaxRK="PMax"+i;
				String SessionMinRK="SMin"+i;
				String TheoryMinRK="TMin"+i;
				String PracticalMinRK="PMin"+i; 
				String CHRK="CreditHours"+i;
				
				String DisciplineName=""; if(row.getDisciplineName()!=null)DisciplineName=row.getDisciplineName();
				String ProgramName=""; if(row.getProgramName()!=null) ProgramName=row.getProgramName();
				String CourseID=""; if(row.getCourseID()!=null) CourseID=row.getCourseID();
				String DepartmentID=""; if(row.getDepartmentID()!=null) DepartmentID=row.getDepartmentID();
				int SemesterID=0; if(row.getSemester()>0) SemesterID=row.getSemester();
				String SubjectId=""; if(row.getSubjectID()!=null) SubjectId=row.getSubjectID();
				double SessionMax=0;if(row.getSessionMax()>0) SessionMax=row.getSessionMax();
				double TheoryMax=0;if(row.getTheoryMax()>0)TheoryMax=row.getTheoryMax();
				double PracticalMax=0;if(row.getPracticalMax()>0)PracticalMax=row.getPracticalMax();
				double SessionMin=0;if(row.getSessionMin()>0) SessionMin=row.getSessionMin();
				double TheoryMin=0;if(row.getTheoryMin()>0)TheoryMin=row.getTheoryMin();
				double PracticalMin=0;if(row.getPracticalMin()>0)PracticalMin=row.getPracticalMin();
				double CreditHours=0;if(row.getCreditHours()>0)CreditHours=row.getCreditHours();
			%>
			
			  <tr class="light-bg">
            <td class="body-cell col1">
            <select id="<%=DisciplineNameRK %>" name="<%=DisciplineNameRK %>">
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
                
               </select></td>
            <td class="body-cell col1">
            <select id="<%=ProgramNameRK %>" name="<%=ProgramNameRK %>" >
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
                  </select></td>
            <td class="body-cell col1">
            <select name="<%=CourseIDRK %>">
                <option value="" selected>Select Course ID</option>
                <%
                if(CourseList!=null && CourseList.size()>0)
                {
                	for(int l=0;l<CourseList.size();l++)
                	{
                		CourseInfoDomain cinfo=CourseList.get(l);
                		
                		if(CourseID.trim().equalsIgnoreCase(cinfo.getCourseID()) ) 
                		{
                			%> <option value="<%=cinfo.getCourseID() %>" selected ><%=cinfo.getCourseID() %></option> <%
                		}else
                		{
                			%> <option value="<%=cinfo.getCourseID() %>" ><%=cinfo.getCourseID() %></option> <%
                		}
                	}
                }
                %>  </select></td>
            <td class="body-cell col1">
            <select name="<%=DepartmentIDRK %>">
                <option value="" selected>Select Department ID</option>
                <%
                if(DeptList!=null && DeptList.size()>0)
                {
                	for(int l=0;l<DeptList.size();l++)
                	{
                		DepartmetInfoDomain dinfo=DeptList.get(l);
                		
                		if(DepartmentID.trim().equalsIgnoreCase(dinfo.getDeptID()) ) 
                		{
                			%> <option value="<%=dinfo.getDeptID() %>" selected ><%=dinfo.getDeptID() %></option> <%
                		}else
                		{
                			%> <option value="<%=dinfo.getDeptID() %>" ><%=dinfo.getDeptID() %></option> <%
                		}
                	}
                }
                %>  </select></td>
            <td class="body-cell col1">
			<select name="<%= SemesterRK%>" >
                <option value="-1" selected>Select Semester ID</option>
                <option value="0" <%if(SemesterID==0) { %> selected <% } %> >Non-Semester</option>
                <%
                for(int n=1;n<16;n++)
                {
                	if(SemesterID==n)
                	{ %> <option value="<%=n %>" selected >Semester <%=n %></option> <% }
                	else
                	{ %> <option value="<%=n %>">Semester <%=n %></option> <% }
                }
                %>
              </select></td>
            <td class="body-cell col1">
            <select name="<%= SubjectIDRK%>">
                <option value="" selected>Select Subject ID</option>
                <%
                if(SubjectsList!=null && SubjectsList.size()>0)
                {
                	for(int m=0;m<SubjectsList.size();m++)
                	{
                		SubjectDomain subname=SubjectsList.get(m);
                		String SubId=subname.getSubjectId();
                		
                		if(SubjectId.trim().equalsIgnoreCase(SubId) ) 
                		{
                			%> <option value="<%=SubId %>" selected ><%=SubId %></option> <%
                		}else
                		{
                			%> <option value="<%=SubId %>" ><%=SubId %></option> <%
                		}
                		
                	}
                }
                %>  </select></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="<%=SessionMaxRK %>" placeholder="Enter Session" maxlength="8" value="<%=SessionMax %>"/></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="<%=TheoryMaxRK %>" placeholder="Enter Theory" maxlength="8" value="<%=TheoryMax %>" /></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="<%=PracticalMaxRK %>" placeholder="Enter Practical" maxlength="8" value="<%=PracticalMax %>" /></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="<%=SessionMinRK %>" placeholder="Enter Session (Min)" maxlength="8"  value="<%=SessionMin %>" /></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="<%=TheoryMinRK %>" placeholder="Enter Theory (Min)" maxlength="8"  value="<%=TheoryMin %>" /></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="<%=PracticalMinRK %>" placeholder="Enter Practical (Min)" maxlength="8"  value="<%=PracticalMin %>"/></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="<%=CHRK %>" placeholder="Enter Credit Hours" maxlength="7"  value="<%=CreditHours %>"/></td>
            
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
			{ width: 150, align: 'left' },
			{ width: 150, align: 'left' },
			{ width: 150, align: 'left' },
			{ width: 150, align: 'left' },
			{ width: 150, align: 'left' },
			{ width: 150, align: 'left' },
			{ width: 150, align: 'left' }
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
	value: 0.70,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(70 * progress) + '<span>% STACS</span>');
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