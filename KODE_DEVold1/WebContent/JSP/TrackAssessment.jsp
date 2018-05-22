<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.AssessmentDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.TrackAssessmentDao" %>
    <%@page import="java.util.Iterator" %>
     <%@page import="java.util.ArrayList" %>
     <%@page import=" java.io.File" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<script src="../JS/jquery1.11.3.js"></script>
</head>
<style>
.su{
    color: #008000;
    font-size: 13px;
    font-weight: bold;
    top: 16%;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
});
function validateAssessID(){
	var accessId=document.getElementById("assess_id").value;
	var stuID=document.getElementById("studentid").value;
	if(accessId == ""){
		alert("Select Assessment ID");
		return false;
	}else if(stuID == "" || stuID == null){
		alert("Select Student ID");
		return false;
	}
	else {
		document.assessmentForm.action="/KODE_DEV/ControllerServlet/TrackAssessmentServlet";
		document.assessmentForm.submit();
	}
	
}
function clickAssessmentID(){
	var assessid=document.getElementById("assess_id").value;
	document.assessmentForm.action="/KODE_DEV/ControllerServlet/TrackAssessmentServlet?assessID="+assessid+"";
	document.assessmentForm.submit();
	
}
function onload(){
	<% String assessid=(String)session.getAttribute("assessid");
	//System.out.println("assessment id in an onload:"+assessid);
	if(assessid!=null && assessid.length()>0) {
		//System.out.println("assessment id in onload:"+assessid);
	 %> 
	 var selectBox = document.getElementById("assess_id");
	 for(var i=0;i<selectBox.options.length;i++){
			 if(selectBox.options[i].value == '<%=assessid%>')
			{
	 selectBox.options[i].selected=true;
				 break;
			 }
		 }
				
		<%}%>
}

$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
});
</script>
<%


	String username=(String)session.getAttribute("username");
   String facultyid=(String)session.getAttribute("userid");
   String orgid=(String)session.getAttribute("orgid");
   String assessmentID=(String)session.getAttribute("assessid");
   //System.out.println("assemment id in jsp:"+assessmentID);
   String[] studentID=(String[])session.getAttribute("resultId");
   //System.out.println("student ids are in jsp:"+studentID);
   %>
<body>
<div class="container">
		<%-- <%@include file="all_one_header.jsp"%> --%>
		<jsp:include page="../JSP/all_one_header_knowstore.jsp"> 
         <jsp:param name="username" value="<%=username%>" /> 
         </jsp:include>
<div style="clear: both;"></div>
<div>
		<%@ include file= "../JSP/menu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="faculty_mod" style="height: 250px; padding:15px 25px 15px 25px!important;">
		<p class="strong">Track Details</p>
 <!-- action="/KODE_DEV/ControllerServlet/TrackAssessmentServlet" -->
 <% if (request.getAttribute("FacultySuccess")!=null) { 
					//System.out.println(" faculty success message value in if:"+request.getAttribute("FacultySuccess"));
					%>
					<p class="su autohide"><%= request.getAttribute("FacultySuccess") %></p>				
					<%
				} else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
				<p class="autohide"style="color:red; font-size:13px; font-weight: bold; top: 85%; left: 13%; position: absolute;"><%= request.getAttribute("FacultyFailure") %>	</p>				
					<%
				} 
				
			%>
<div class="track-assement">
			
<form name="assessmentForm" method="post">

<table>




<tr class="select_tr">
	<!-- <td>
		<font>Assessment ID </font>
	</td> -->
						
						<!-- <td>:</td> -->
						<td>
							 <% TrackAssessmentDao asdao=new TrackAssessmentDao();
		
                             ArrayList<AssessmentDomain>al=new ArrayList<AssessmentDomain>();
		                   al=asdao.retriveAssessmentID(facultyid,orgid);
		                      Iterator<AssessmentDomain> it= al.iterator();%>
		                      <select name="assess_id" class="box_lng" id="assess_id" onchange="clickAssessmentID();">
		                      <option value="">--select Assessment ID--</option>
		                    <% while(it.hasNext())
		                     {
		    	             String id=it.next().getAssessmentId();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	                
						</td>
					</tr>
					
	<tr class="select_tr_stu">
 		<!-- <td>Student ID</td> -->
 		 
		<td><select name="studentid"  class="box_lng" id="studentid">
				<option value="">--select student ID--</option>
				<%				 
 					 
			  		 if(studentID!=null){
			  	 		for(String sID :studentID){%>
						
				<option value="<%=sID%>"><%=sID%></option>
						<% }}%>
    	
			</select></td>
			
	<tr class="select_tr_stu">
	<td><input type="button" class="add_btn1" value="GetDetails" onclick="validateAssessID()" ></td>
	<td><!-- <a href="../JSP/TrackStudent.jsp">Back</a> --></td>
	
	</tr>
</table>
</form>
</div>
		</div>

<%@ include file="../JSP/all_one_footer.jsp"%>
</div>
					
</body>
<script>
onload();
</script>
</html>