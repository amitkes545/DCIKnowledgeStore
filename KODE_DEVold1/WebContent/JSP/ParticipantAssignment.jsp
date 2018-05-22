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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KODE_DEV</title>
<link href="../CSS/AssessmentView.css" rel="stylesheet"></link>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../JS/Coursedetails.js"></script>
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
margin-left: 650px;
position: absolute;
}
.sub_mit
{
margin-top: -10px;
margin-left: 650px;
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
<div class="body-mtr"><!--  body content start -->
<div class="container">
<p style="color: #c2c2c2; font-size:18px; margin-top: 50px; margin-bottom: 20px;"><span style="color:#ba8c1c; font-weight: bold; font-size: 24px">Assignments > </span></p>

<div class="sem_main">	
<div class="sem_list1">
<ul class="sem_list1_ul">
<%
DisplayCoursesDomain ri=new DisplayCoursesDomain();
ri.setCourseName(session.getAttribute("userId").toString());
ri.setCourseDetails(session.getAttribute("orgId").toString());
ri.setCourseFee(session.getAttribute("createdBy").toString());

ParticipantAssignmentsDao dc=new ParticipantAssignmentsDao();
ArrayList<RetriveImagesDomain> arl=new ArrayList<RetriveImagesDomain>();
arl=dc.selectCourses(ri);
	Iterator<RetriveImagesDomain> it= arl.iterator();
	while(it.hasNext())
    {
		RetriveImagesDomain dd=it.next();
		 %>
     <li class="nn-part"><a href="/KODE_DEV/ControllerServlet/ParticipantAssignmentDetailsService?assessmentId=<%=dd.getCourseImageSize()%>&facultyName=<%=dd.getCourseImageLocation() %>"  >
     <img class="dbimg" src="../Image/assignment_li.png" width="80px" height="120px;" />
     <br/>
     <span class="ww" style="text-transform: capitalize;"><%=dd.getCourseName()%></span></a></li>
     
  <% } %>
  

</ul>
</div>

<div style="clear: both;"></div>
<div class="full-sem-contant" >


 <form action="/KODE_DEV/ControllerServlet/AssignmentFileUploadService" method="post" enctype="multipart/form-data">

<%
  if(session.getAttribute("assessmentDetails")!=null)
  {
	  ArrayList<AssessmentsDetailsDomain> arl1=(ArrayList<AssessmentsDetailsDomain>)session.getAttribute("assessmentDetails");
	  Iterator<AssessmentsDetailsDomain> it1= arl1.iterator();
	  while(it1.hasNext())
	  {
		  AssessmentsDetailsDomain add=it1.next();
		  String[] s1=add.getFilePath().split("/");
		  %>
		
		<h4 style="border-top: 1px solid #ccc; padding-top: 5px;"><input class="h44" type="text" name="assesmentName" value="<%=add.getAssessmentName() %>" readonly="readonly"></h4>  
		 <span>Description : </span> <%=add.getAssessmentDescription() %>
		 <br/><span>Assignment ID : </span><input type="text" name="facultyName" value="<%=add.getAssessmentId()%>" readonly="readonly"> 
		 <br/><span>Faculty Name : </span>  <%=add.getUploadedBy() %>
		  <input type="hidden" name="filePath" value="<%=add.getFilePath()%>" readonly="readonly">
		 <br/><span>Date : </span> <%=add.getUploadedDate() %>
		 <br/><span> Time : </span> <%=add.getUploadedTime() %>
		  <br/><span>File : </span> <a href="/KODE_DEV/ControllerServlet/AssignDownloadfile?filePath=<%=add.getFilePath()%>"><%=s1[s1.length-1] %></a> 
		 <!--<br/><span>File : </span> <a href="/KODE_DEV/ControllerServlet/FTPDownloadFileDemo?filePath=<%=add.getFilePath()%>"><%=s1[s1.length-1] %></a>  -->
		  <%
	  }
	  %>
	 <%  if(request.getAttribute("successMessage")!=null){
	 
	  System.out.println("success message:"+request.getAttribute("successmsg")); %>
		  <p style="color:green; font-size:28px; font-weight: bold; top: 25%; left: 30%; position: absolute;"><%=request.getAttribute("successMessage") %></p>
	<%} else if(request.getAttribute("failureMessage")!=null){
	 
	  %>
		  <p style="color:red; font-size:28px; font-weight: bold; top: 25%; left: 30%; position: absolute;"><%=request.getAttribute("failureMessage") %></p>
	<%} %> 
	
	  <input class="up-load" type="file" name="upload file" />
	  <input class="sub_mit" type="submit">
	  </form>
 <%
 }

  %>
</div>
</div>
</div>

</div>
<%@ include file="../JSP/FooterView.jsp" %>
</body>
</html>