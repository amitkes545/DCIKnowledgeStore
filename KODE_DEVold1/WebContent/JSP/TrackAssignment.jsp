<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.AssessmentDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.TrackAssignmentDao" %>
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
<script>
$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
});
function getStudentID(){
	var assignid=document.getElementById("assign_id").value;
	//alert("hello");
	document.TrackAssessment.action="/KODE_DEV/ControllerServlet/TrackAssignmentServlet?assignID="+assignid+"";
	document.TrackAssessment.submit();
}
function validateTrack(){
	var assignid=document.getElementById("assign_id").value;
	var studentID=document.getElementById("studentID").value;
	if(assignid == null || assignid == ""){
		alert("Select Assignment ID");
		return false;
	}else if(studentID == null || studentID == ""){
		alert("Select Student ID");
		return false;
	}else{
		document.TrackAssessment.action="/KODE_DEV/ControllerServlet/TrackAssignmentServlet";
		document.TrackAssessment.submit();
	}
}
function onload(){
	<% String assignid=(String)session.getAttribute("assignid");
	////System.out.println("assignment id in an onload:"+assignid);
	if(assignid!=null && assignid.length()>0) {
		////System.out.println("assignment id in onload:"+assignid);
	 %> 
	 var selectBox = document.getElementById("assign_id");
	 for(var i=0;i<selectBox.options.length;i++){
			 if(selectBox.options[i].value == '<%=assignid%>')
			{
	 selectBox.options[i].selected=true;
				 break;
			 }
		 }
				
		<%}%>
}
</script>

<%
/* HttpSession mess = request.getSession();
String msg = ""; */

	String username=(String)session.getAttribute("username");
   String facultyid=(String)session.getAttribute("userid");
   String orgid=(String)session.getAttribute("orgid");
   String assignmentID=(String)session.getAttribute("assignid");
   //System.out.println("assignment id in jsp:"+assignmentID);
   String[] stuId=(String[])session.getAttribute("ResultDomain");
   //System.out.println("array list value:"+stuId);%>
<body>
<div class="container">
		<%@include file="all_one_header_knowstore.jsp"%>
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%=username%>" /> 
         </jsp:include> --%>
<div style="clear: both;"></div>
<div>
		<%@ include file= "../JSP/menu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="faculty_mod" style="height: 250px; padding:15px 25px 15px 25px!important;">
<p class="strong">Track Details</p>
    
    <!-- action="/KODE_DEV/ControllerServlet/TrackAssignmentServlet" -->
<% if (request.getAttribute("FacultySuccess")!=null) { 
					//System.out.println(" faculty success message value in if:"+request.getAttribute("FacultySuccess"));
					%>
				<p class="su autohide">	<%= request.getAttribute("FacultySuccess") %></p>				
					<%
				} else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
				<p class="autohide" style="color:red; font-size:15px; font-weight: bold; top: 85%; left: 13%; position: absolute;"><%= request.getAttribute("FacultyFailure") %>	</p>				
					<%
				} 
				
			%>
<div class="track-assement">
<form name="TrackAssessment"method="post">
<table>
    <tr class="select_tr">
 						<!-- <td align="left">
							<font>Assignment ID </font>
						</td>
						<td>:</td> -->
						<td>
							 <% TrackAssignmentDao asdao=new TrackAssignmentDao();
		
                             ArrayList<AssessmentDomain>al=new ArrayList<AssessmentDomain>();
		                   al=asdao.retriveAssignmentID(facultyid,orgid);
		                      Iterator<AssessmentDomain> it= al.iterator();%>
		                      <select name="assign_id" id="assign_id" class="box_lng" onchange="getStudentID()">
		                      <option value="">-----Select Assignment ID-----</option>
		                    <% while(it.hasNext())
		                     {
		    	             String id=it.next().getAssessmentId();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	                
						</td>
					</tr>
		
<tr class="select_tr_stu">			
 		<!-- <td> Students ID</td>
 		 <td>:</td> -->
		<td><select name="studentID" class="box_lng" id="studentID">
				<option value="">--Select Student ID--</option>
				<%				 
 					 
			  		 if(stuId!=null)
			  	 		{
							for(String sid:stuId){%>
						
				       <option value="<%=sid%>"><%=sid%></option>
						<% }}%>
    	
	 		</select></td></tr>
	 		
	 		
	 		<tr class="select_tr_stu">
	 		
	 		
			<td><input type="button" class="add_btn1" value="GetDetails"  onclick="validateTrack()"></td>
			<!-- <td><a href="../JSP/TrackStudent.jsp">Back</a></td> -->
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