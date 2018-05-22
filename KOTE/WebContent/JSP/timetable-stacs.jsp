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
<title>CosTST - Course Subject, Topic and Sub Topic Mapping (STACS)</title>
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

List<StacsTimeTableDomain> timeTableDetails=ExcelSheetUtil.getTimeTableDetails(session);

LookupInterface dao=new LookupDao();
List<DisciplineDomain> DesciplineList=dao.getDesciplineList(session);
List<ProgramDomain> ProgramList=dao.getProgramList(session);
List<CourseInfoDomain> CourseList=dao.getCourseList(session);
List<DepartmetInfoDomain> DeptList=dao.getDeptList(session);
List<SubjectDomain> SubjectsList=dao.getSubjectsList(session);
List<TopicDomain> TopicsList=dao.getTopicsList(session);
List<SubTopicDomain> SubTopicsList=dao.getSubTopicsList(session);
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
        <div class="menu_btn"> <a class="menubar1" onClick="openNav()"> <i class="fa fa-bars" aria-hidden="true"></i> </a>
          <div id="mySidenav" class="left-nave-menu sidenav"> <a href="javascript:void(0)" class="closebtn" onClick="closeNav()">&times;</a>
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
      <h3>CosTST - Timetable<span>(STACS)</span></h3>
    </div>
    <form class="cine-setup" action="/KOTE/ControllerServlet/StacsService?From=TimeTable" method="post">
    <div class="dwrapper">
      <table id="fixed_hdr2">
        <thead>
          <tr>
            <th class="header-cell col1">Discipline Name</th>
            <th class="header-cell col1">Program Name</th>
            <th class="header-cell col1">Course ID</th>
            <th class="header-cell col1">Course Name</th>
            <th class="header-cell col1">Department ID</th>
            <th class="header-cell col1">Department Name</th>
            <th class="header-cell col1">Subject ID</th>
            <th class="header-cell col1">Subject Name</th>
            <th class="header-cell col1">Topic ID</th>
            <th class="header-cell col1">Topic Name</th>
            <th class="header-cell col1">Sub Topic ID</th>
            <th class="header-cell col1">Sub Topic Name</th>
            <th class="header-cell col1">Faculty UID</th>
            <th class="header-cell col1">Session Name</th>
			<th class="header-cell col1">Session Start Date Time</th>
			<th class="header-cell col1">Session End Date Time</th>
			<th class="header-cell col1">Session Duration</th>
            <th class="header-cell col1">Session Material</th>
          </tr>
        </thead>
        <tbody>
        	<%
        		if(timeTableDetails!=null && timeTableDetails.size()>0)
        		{
        			for(int i=0;i<timeTableDetails.size();i++)
        			{
        				StacsTimeTableDomain row=timeTableDetails.get(i);
        				
        				String DisciplineNameRK="DiscName"+i;
        				String ProgramNameRK="ProgName"+i;
        				String CourseIDRK="CrsID"+i;
        				String CourseNameRK="CrsName"+i;
        				String DepartmentIDRK="DeptID"+i;
        				String DepartmentNameRK="DeptName"+i;
        				String SubjectIdRK="SubID"+i;
        				String SubjectNameRK="SubName"+i;
        				String TopicIdRK="TopicID"+i;
        				String TopicNameRK="TopicName"+i;
        				String SubTopicIdRK="SubTopicID"+i;
        				String SubTopicNameRK="SubTopicName"+i;
        				String FacultyUIDRK="FacultyUID"+i;
        				
        				String sessionNameRK="sessionName"+i;
        				String sessionsDateRK="sessionsDate"+i;
        				String sessioneDateRK="sessioneDate"+i;
        				String sessionDurationRK="sessionDuration"+i;
        				String sessionDocuRK="sessionDocu"+i;
        				
        				String DisciplineName="";if(row.getDisciplineName()!=null)DisciplineName=row.getDisciplineName();
        				String ProgramName="";if(row.getProgramName()!=null) ProgramName=row.getProgramName();
        				String CourseID="";if(row.getCourseID()!=null)CourseID=row.getCourseID();
        				String CourseName="";if(row.getCourseName()!=null)CourseName=row.getCourseName();
        				String DepartmentID="";if(row.getDepartmentID()!=null) DepartmentID=row.getDepartmentID();
        				String DepartmentName="";if(row.getDepartmentName()!=null) DepartmentName=row.getDepartmentName();
        				String SubjectId="";if(row.getSubjectId()!=null)SubjectId=row.getSubjectId();
        				String SubjectName="";if(row.getSubjectName()!=null) SubjectName=row.getSubjectName();
        				String TopicId="";if(row.getTopicId()!=null)TopicId=row.getTopicId();
        				String TopicName="";if(row.getTopicName()!=null)TopicName=row.getTopicName();
        				String SubTopicId="";if(row.getSubTopicId()!=null)SubTopicId=row.getSubTopicId();
        				String SubTopicName="";if(row.getSubTopicName()!=null)SubTopicName=row.getSubTopicName();
        				String FacultyUID="";if(row.getFacultyUID()!=null) FacultyUID=row.getFacultyUID();
        				
        				String sessionName="";if(row.getSessionName()!=null)sessionName=row.getSessionName();
        				String sessionsDate="";if(row.getSessionStartDateTime()!=null)sessionsDate=row.getSessionStartDateTime(); 
        				String sessioneDate="";if(row.getSessionEndDateTime()!=null)sessioneDate=row.getSessionEndDateTime();
        				String sessionDuration="";if(row.getSessionDuration()!=null)sessionDuration=row.getSessionDuration();
        				String sessionDocu="";if(row.getSessionMaterial()!=null)sessionDocu=row.getSessionMaterial();
        				
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
        		                
        		                
        		              </select>
        		              </td>
        		            <td class="body-cell col1">
        		            <select id="<%=ProgramNameRK %>" name="<%=ProgramNameRK %>" >
        		                <option value="" >Choose Progaram Name*</option>
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
        		                %>
        		              </select></td>
        		            <td class="body-cell col1"><input type="text" class="txtOnly" name="<%=CourseNameRK %>" placeholder="Enter Course Name" value="<%=CourseName %>"/></td>
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
        		                %>
        		              </select></td>
        		            <td class="body-cell col1"><input type="text" class="txtOnly" name="<%=DepartmentNameRK %>" placeholder="Enter Department Name" value="<%=DepartmentName %>" /></td>
        		            <td class="body-cell col1">
        		            <select name="<%= SubjectIdRK%>">
        		                <option value="" selected>Select Subject ID</option>
        		                <%
        		                if(SubjectsList!=null && SubjectsList.size()>0)
        		                {
        		                	for(int m=0;m<SubjectsList.size();m++)
        		                	{
        		                		SubjectDomain subname=SubjectsList.get(m);
        		                		String SubId=subname.getSubjectId();
        		                		String DbSubId=subname.getDBSubjectId();
        		                		if(SubjectId.trim().equalsIgnoreCase(SubId) ) 
        		                		{
        		                			%> <option value="<%=DbSubId %>" selected ><%=SubId %></option> <%
        		                		}else
        		                		{
        		                			%> <option value="<%=DbSubId %>" ><%=SubId %></option> <%
        		                		}
        		                		
        		                	}
        		                }
        		                %>
        		              </select>
        		              </td>
        		            <td class="body-cell col1"><input type="text" class="txtOnly" name="<%=SubjectNameRK %>" placeholder="Enter Subject Name" value="<%=SubjectName %>" /></td>
        		            <td class="body-cell col1">  
        		            <select name="<%=TopicIdRK %>" >
        		                <option value="" visible>Choose Topic ID*</option>
        		                <% if(TopicsList!=null && TopicsList.size()>0)
        		                {
        		                	for(int n=0;n<TopicsList.size();n++)
        		                	{
        		                		TopicDomain topic=TopicsList.get(n);
        		                		
        		                		String Topic_id=topic.getTopic_id();
        		                		String topic_name=topic.getTopic_name();
        		                		String Topic_id_by_ts=topic.getTopic_id_by_ts();
        		                		%>
        		                		<option value="<%=Topic_id %>"  <% if(Topic_id_by_ts.trim().equalsIgnoreCase(TopicId)) { %> selected <% } %> ><%=Topic_id_by_ts %></option>
        		                <%	}
        		                }
        		                %>
        		            </select></td>
        		            <td class="body-cell col1"><input type="text" class="txtOnly" name="<%=TopicNameRK %>" placeholder="Enter Topic Name" value="<%=TopicName %>" /></td>
        		            <td class="body-cell col1">
        		            <select name="<%=SubTopicIdRK %>" >
        		                <option value="" visible>Choose Sub Topic ID*</option>
        		                <% if(SubTopicsList!=null && SubTopicsList.size()>0)
        		                {
        		                	for(int n=0;n<SubTopicsList.size();n++)
        		                	{
        		                		SubTopicDomain topic=SubTopicsList.get(n);
        		                		
        		                		String SubTopic_id=topic.getSubTopic_id();
        		                		String Subtopic_name=topic.getSubTopic_name();
        		                		String SubTopic_id_by_ts=topic.getSubTopic_id_by_ts();
        		                		%>
        		                		<option value="<%=SubTopic_id %>" <% if(SubTopic_id_by_ts.trim().equalsIgnoreCase(SubTopicId)) { %> selected <% } %> ><%=SubTopic_id_by_ts %></option>
        		                <%	}
        		                }
        		                %>
        		            </select>
        		            </td>
        		            <td class="body-cell col1"><input type="text" class="txtOnly" name="<%=SubTopicNameRK %>" placeholder="Enter Sub-Topic Name" value="<%=SubTopicName %>" /></td><td class="body-cell col1">
        		            <select name="<%=FacultyUIDRK %>">
        		                <option value="" visible>Choose Faculty UID*</option>
        		               
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
        		            				if(uid_ts.trim().equalsIgnoreCase(FacultyUID))
        		            					selected="selected";
        		            				
        		            			}
        		            		%>	<option value="<%=id %>" <%=selected%>  ><%=uid_ts %></option> <%
        		            		}
        		            	}
        		            	%>
        		                
        		                </select>
        		            </td>
        		            <td class="body-cell col1"><input type="text" class="textnumber" name="<%= sessionNameRK%>" placeholder="Session Name" value="<%=sessionName %>" /></td>
            				<td class="body-cell col1"><input type="text" class="textnumber" name="<%=sessionsDateRK %>" placeholder="Session Start Date Time" value="<%=sessionsDate %>" /></td>
            				<td class="body-cell col1"><input type="text" class="textnumber" name="<%=sessioneDateRK %>" placeholder="Session End Date Time" value="<%=sessioneDate %>" /></td>
            				<td class="body-cell col1"><input type="text" class="textnumber" name="<%=sessionDurationRK %>" placeholder="Session Duration" value="<%=sessionDuration %>" /></td>
            				<td class="body-cell col1"><input type="text" class="textnumber" name="<%=sessionDocuRK %>" placeholder="Session Material"  value="<%=sessionDocu %>" /></td>
        		            
        		          </tr>
        		          
        		          		
        						
        		       <% 	}
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
			{ width: 25, align: 'left' },
			{ width: 25, align: 'left' },
			{ width: 50, align: 'left' },
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
    fill: { color: "#DAA520" }
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