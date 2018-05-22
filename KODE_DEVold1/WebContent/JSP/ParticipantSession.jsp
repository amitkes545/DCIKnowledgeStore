<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import="com.kds.KODE_DEV.dao.*"  %>
   <%@page import="com.kds.KODE_DEV.domain.*" %>
   <%@page import="java.util.*" %>
   <%@page import="java.sql.*"%>
   <%@page import="java.io.InputStream"%>
   <%@page import="java.io.OutputStream"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/Coursedetails.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KODE_DEV</title>
<link href="../CSS/AssessmentView.css" rel="stylesheet"></link>
<script type="text/javascript">
function institutions(){
	document.CourseDetails.action="/KODE_DEV/ControllerServlet/VocationalCoursesService";
	document.CourseDetails.submit(); 
	//window.location.href = document.getElementById('Institutions').href;
}
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script> 

$(document).ready(function() {
	var $btns = $('.ww').click(function() {
		  /*   var $el = $('.' + this.id).fadeIn(1500);   /* slideDown(1500); */
		    $('.full-sem-contant > div').not($el).hide(); 
		    $btns.removeClass('active');
		  $(this).addClass('active');
		});
});
</script>
<style type="text/css">
.ww{
text-transform: capitalize;
}
input{
border: none;
}
.h44
{
font-size: 22px;
text-transform: uppercase;
}
.up-load
{
margin-top: -40px;
margin-left: 150px;
position: absolute;
}
.sub_mit
{
margin-top: -10px;
margin-left: 150px;
position: absolute;
width: 100px;
background: #3366FF;
padding: 10px;
color: #fff;
}

.sub_mit:hover
{
background: #CC33FF;

}
</style>
</head>
<body>
<%@ include file="../JSP/ParticipantViewHeader.jsp" %>
<!-- <div class="body-mtr"> body content start
<div class="container"> -->
<p style="color: #c2c2c2; font-size:18px; margin-top: 50px; margin-bottom: 20px;"><span style="color:#ba8c1c; font-weight: bold; font-size: 24px">Sessions > </span></p>

<div class="sem_main">
<div class="sem_list1">
<ul class="sem_list1_ul">

<%
int i=1;
DisplayCoursesDomain ri=new DisplayCoursesDomain();
ri.setCourseName(session.getAttribute("userId").toString());
ri.setCourseDetails(session.getAttribute("orgId").toString());
ri.setCourseFee(session.getAttribute("createdBy").toString());
ParticipantSessionDao dc=new ParticipantSessionDao();
ArrayList<RetriveImagesDomain> arl=new ArrayList<RetriveImagesDomain>();
arl=dc.selectCourses(ri);
	Iterator<RetriveImagesDomain> it= arl.iterator();
	while(it.hasNext())
    {
		RetriveImagesDomain dd=it.next();
     %>
     <li id="id_<%=i %>" class="nn-part">
     <a href="/KODE_DEV/ControllerServlet/ParticipantSessionDetailsService?assessmentName=<%=dd.getCourseImageSize()%>&facultyName=<%=dd.getCourseImageLocation() %>" >
     <img class="dbimg" src="../imagelist/assignment_li.png" height="120px;" width="80px;"/><br/>
     <span class="ww"><%=dd.getCourseName()%></span></a></li>
     
  <% i++; } %>

</ul>
</div>

<div style="clear: both;"></div>
<div class="full-sem-contant" >


   
<%
  if(request.getAttribute("assessmentDetails")!=null)
  {
	  ArrayList<ParticipantSessionDomain> arl1=(ArrayList<ParticipantSessionDomain>)request.getAttribute("assessmentDetails");
	  Iterator<ParticipantSessionDomain> it1= arl1.iterator();
	  while(it1.hasNext())
	  {
		  ParticipantSessionDomain add=it1.next();
		  //String[] s1=add.getFilePath().split("/");
		  %>
		  
		 <h4 class="h44" style="border-top:1px solid #ccc; padding-top: 10px;"><%=add.getSessionName() %></h4>  
		 <br/><span>Session ID : </span> <%=add.getSessionId() %>
		    <br/><span>Faculty Name : </span> <%=add.getFacultyName() %>
		     <br/><span>Duration : </span> <%=add.getDuration() %>
		      <br/><span>Session Start Timing : </span> <%=add.getSessionStartTiming() %>
		      <br /><span>Session End Time :</span> <%=add.getSessionEndTime() %>
		       <br/><span>Cost Of Session : </span> <%=add.getCostOfSession() %>
		        <br/><span>Organization ID : </span> <%=add.getOrganizationId() %>
		        <%if(add.getPathOfFile()!=null){ %>
		         <br/><span>File Path : </span> <%=add.getPathOfFile() %>
 <%}
	  } %>
	  
 <% }

  %>
</div>
</div>
</div>
</div>
<%@ include file="../JSP/FooterView.jsp" %>
</body>
</html>