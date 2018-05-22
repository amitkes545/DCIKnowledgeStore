<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.AdminDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.CertiftStudentDao" %>
    <%@page import="java.util.Iterator" %>
     <%@page import="java.util.ArrayList" %>
     <%@page import="java.io.File" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>

<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />

<!-- <link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<link href="../CSS/kedu.css" rel="stylesheet"/>
</head>
<style>
.su{
		    color: #008000;
    font-size: 12px;
    font-weight: bold;
    top:11%;
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
function clickAssignmentID(){
	var assignid=document.getElementById("asignID").value;
	document.AssignCertify.action="/KODE_DEV/ControllerServlet/AssignCerifyStudentServlet?assignid="+assignid+"";
	document.AssignCertify.submit();
	
}function clickStudentID(){
	var stuID=document.getElementById("studentID").value;
	//alert("student id:"+stuID);
	document.AssignCertify.action="/KODE_DEV/ControllerServlet/AssignCerifyStudentServlet?studentID="+stuID+"";
	document.AssignCertify.submit();
}
function clickSubmit(){
	var assignid=document.getElementById("asignID").value;
	var studentID=document.getElementById("studentID").value;
	var assignMarks=document.getElementById("marks").value;
	var status=document.getElementById("status").value;
	var reMarks=document.getElementById("remarks").value;
	if(assignid == "" || assignid == null){
		alert("Select Assignment ID");
		return false;
	}else if(studentID == "" || studentID == null){
		alert("Select Student ID");
		return false;
	}else if(assignMarks == "" || assignMarks == null){
		alert("Enter marks");
		document.getElementById("marks").focus();
		return false;
	}else if(status == "" || status == null){
		alert("Enter status");
		document.getElementById("status").focus();
		return false;
	}else if(reMarks == "" || reMarks == null){
		alert("Enter remarks");
		document.getElementById("remarks").focus();
		return false;
	}
	else {
		document.AssignCertify.action="/KODE_DEV/ControllerServlet/AssignCerifyStudentServlet";
		document.AssignCertify.submit();
	}
	
}
//applying this jquery code to text box for enter only numbers
$(document).ready(function() {
	$("#marks").keydown(function (e) {
    	
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
            (e.keyCode == 65 && ( e.ctrlKey === true || e.metaKey === true ) ) || 
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 return;
        }
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105))
        {
            e.preventDefault();
        }
    });
});
//applying this code to text box for enter characters
$(function () {
	 $('#status').keydown(function (e) {
	 if (e.shiftKey || e.altKey) {
	 e.preventDefault();
	 } else {
	 var key = e.keyCode;
	 if (!((key == 8) || (key == 9) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
	 e.preventDefault();
	 }
	 }
	 });
	 });
//applying this code to text box for enter characters
$(function () {
	 $('#remarks').keydown(function (e) {
	 if (e.shiftKey || e.altKey) {
	 e.preventDefault();
	 } else {
	 var key = e.keyCode;
	 if (!((key == 8) || (key == 9) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
	 e.preventDefault();
	 }
	 }
	 });
	 });
function onload(){
	<% String assignid=(String)session.getAttribute("assignid");
	//System.out.println("assignment id in an onload:"+assignid);
	if(assignid!=null && assignid.length()>0) {
		//System.out.println("assignment id in onload:"+assignid);
	 %> 
	 var selectBox = document.getElementById("asignID");
	 for(var i=0;i<selectBox.options.length;i++){
			 if(selectBox.options[i].value == '<%=assignid%>')
			{
	 selectBox.options[i].selected=true;
				 break;
			 }
		 }
				
		<%}%>
}
function onloadStuId(){
	<% String studentID=(String)session.getAttribute("studentID");
	//System.out.println("student id in an onload:"+studentID);
	if(studentID!=null && studentID.length()>0) {
		//System.out.println("student id in onload:"+studentID);
	 %> 
	 var selectBox = document.getElementById("studentID");
	 for(var i=0;i<selectBox.options.length;i++){
			 if(selectBox.options[i].value == '<%=studentID%>')
			{
	 selectBox.options[i].selected=true;
				 break;
			 }
		 }
				
		<%}%>
}
</script>
<%
 //HttpSession mess = request.getSession();
 
   String username=(String)session.getAttribute("username");
   String facultyid=(String)session.getAttribute("userid");
   String orgid=(String)session.getAttribute("orgid");
   String  studentId[]=(String[])session.getAttribute("ResultDomain") ;
   String filepath=(String)session.getAttribute("FilePath");
   String uploadedid=(String)session.getAttribute("uploadedid");
   //System.out.println("file path:"+filepath +"uploadedid:"+uploadedid);
   
   %>
  
<body>
<div class="container">
		<%@include file="../JSP/headers.jsp"%>
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%=username%>" /> 
         </jsp:include> --%>
         <div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
         
        
    <div class="faculty_mod" style="width: 298px;"> 
    <p class="strong">Assignment Details</p>
    
         <% if (request.getAttribute("FacultySuccess")!=null) { 
					
					%>
					<p class="su autohide"><%= request.getAttribute("FacultySuccess") %></p>					
					<%
				} else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
					<p class="autohide" style="color:red; font-size:12px; font-weight: bold; top: 10%; left: 43%; position: absolute;"><%= request.getAttribute("FacultyFailure") %></p>					
					<%
				} 
				
			%>
         
         <form name="AssignCertify" method="post">
<table>

<tr>
 	<!-- <td>Assignment ID</td>
 	<td>:</td> -->
	<td>
							 <% CertiftStudentDao certifyStudentDao=new CertiftStudentDao();
		
                             ArrayList<AdminDomain>al=new ArrayList<AdminDomain>();
		                   al=certifyStudentDao.retriveAssignMentDetails(facultyid,orgid);
		                      Iterator<AdminDomain> it= al.iterator();%>
		                      <select name="asignID" id="asignID" class="box_lng" onchange="clickAssignmentID();">
		                      <option value="">--select Assignment ID--</option>
		                    <% while(it.hasNext())
		                     {
		    	             String id=it.next().getAssignment_name();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	    				</td>
					</tr>
		<tr>
 		<!-- <td>Uploaded Students</td>
 		<td>:</td> -->
 		 
		<td><select name="studentID" id="studentID" class="box_lng" onchange="clickStudentID();">
				<option value="">--select student ID--</option>
				<% if(studentId!=null){
					for(String sid:studentId){%>
					 <option value="<%=sid%>"><%=sid%></option>
						<% }}%>
				
	 		</select></td></tr>
	 		<tr>
 		 <% if(filepath!=null)
			 {%>
			<%if(request.getAttribute("successMessage")!=null){ 
			//System.out.println("message value:"+request.getAttribute("successMessage"));%>
			  <p class="su autohide">  <%= request.getAttribute("successMessage")%></p>
			     <%} else if(request.getAttribute("failureMessage")!=null) { %>
			     <p class="autohide" style="color:red; font-size:17px; font-weight: bold; top: 11%; left: 41%; position: absolute;"><%= request.getAttribute("failureMessage") %></p>
			     <%} %>
			 
			<%-- <tr> <td> File DownLoaded Path</td>
			<td><input type="text" name="filepath" value="<%= filepath %>"></td><tr> --%>
			<tr>
			<!-- <td>DownLoad File</td>
			<td>:</td> -->
			<td><a style="color: #0;padding-bottom: 5px;" href="/KODE_DEV/ControllerServlet/AssignFTPDownloadFile?StudentID=<%=uploadedid%>&filePath=<%=filepath%>">Download File</a></td></tr>
			<tr><!-- <td>Enter Assignment Marks</td>
			<td>:</td> -->
			<td><input type="text" name="marks"  placeholder="Enter Assignment Marks" id="marks"></td></tr>
			<tr><!-- <td>Enter Assignment Status</td>
			<td>:</td> -->
			<td><input type="text" name="status" placeholder="Enter Assignment Status"  id="status">
			<input type="hidden" name="uploadedid" value="<%=session.getAttribute("uploadedid") %>"></td>
			</tr>
			<!-- <tr><td>Enter Assignment Remarks</td>
			<td>:</td> -->
			<td><input type="text" placeholder="Enter Assignment Remarks" name="remarks" id="remarks"></td></tr>
			<tr>
			<td>
			<input type="button"  class="add_btn1" value="submit" onclick="clickSubmit();"></td>
			<%} %>
			<td> <!-- style="float: left;width: 100px;line-height:35px; margin-left:10px;"><a href="../JSP/CertifyStudent.jsp">Back</a> -->
			</td>
			</tr>
			</table>
</form>
         
         </div>
         
 <%@ include file="../JSP/FooterViews.jsp"%>        
</div>
</body>
<script>
onload();
onloadStuId();
</script>
</html>