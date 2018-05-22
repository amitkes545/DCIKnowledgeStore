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
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<link href="../CSS/kedu.css" rel="stylesheet"/>
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
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

function clickAssessmentID(){
	var assessid=document.getElementById("asessID").value;
	//alert("assessment id:"+assessid);
	document.cerify.action="/KODE_DEV/ControllerServlet/CertifyStudentServlet?assessid="+assessid+"";
	document.cerify.submit();
}
function clickStudentID(){
	var stuID=document.getElementById("studentID").value;
	//alert("student id:"+stuID);
	document.cerify.action="/KODE_DEV/ControllerServlet/CertifyStudentServlet?studentID="+stuID+"";
	document.cerify.submit();
}
function clickSubmit(){
	var assignid=document.getElementById("asessID").value;
	var studentID=document.getElementById("studentID").value;
	var assessmentMarks=document.getElementById("assessMarks").value;
	var status=document.getElementById("status").value;
	var reMarks=document.getElementById("remarks").value;
	if(assignid == "" || assignid == null){
		alert("Select Assessment ID");
		return false;
	}else if(studentID == "" || studentID == null){
		alert("Select Student ID");
		return false;
	}else if(assessmentMarks == "" || assessmentMarks == null){
		alert("enter marks");
		document.getElementById("assessMarks").focus();
		return false;
	}else if(status == "" || status==null){
		alert("Enter status");
		document.getElementById("status").foucs();
		return false;
	}else if(reMarks == "" ||reMarks == null){
		alert("Enter remarks");
		document.getElementById("remarks").focus();
		return false;
	}
	else {
		document.cerify.action="/KODE_DEV/ControllerServlet/CertifyStudentServlet";
		document.cerify.submit();
	}
	
}
//applying this jquery code to text box for enter only numbers
$(document).ready(function() {
	$("#assessMarks").keydown(function (e) {
    	
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
	<% String assesid=(String)session.getAttribute("assessid");
	//System.out.println("assessment id in an onload:"+assesid);
	if(assesid!=null && assesid.length()>0) {
		//System.out.println("assessment id in onload:"+assesid);
	 %> 
	 var selectBox = document.getElementById("asessID");
	 for(var i=0;i<selectBox.options.length;i++){
			 if(selectBox.options[i].value == '<%=assesid%>')
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

   
	 String username=(String)session.getAttribute("username");
  	 String facultyid=(String)session.getAttribute("userid");
  	 String orgid=(String)session.getAttribute("orgid");
     String filepath=(String)session.getAttribute("assessmentFilePath");
 	 String uploadedid=(String)session.getAttribute("assessmentUploadedId");
  
     String[] stuId=(String[])session.getAttribute("assessResultDomain");
  
  // //System.out.println(" faculty success message value in if:"+request.getAttribute("FacultySuccess").toString());
%>
<body>
<div class="container">
		<%@include file="headers.jsp"%>
	

<div style="clear: both;"></div>
<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>

    <div class="faculty_mod" style="width: 298px"> 
    <p class="strong">Assessment Details</p>

<!-- action="/KODE_DEV/ControllerServlet/CertifyStudentServlet" -->
<% if (request.getAttribute("FacultySuccess")!=null) { 
					//System.out.println(" faculty success message value in if:"+request.getAttribute("FacultySuccess"));
					%>
					<p class="su autohide"><%=request.getAttribute("FacultySuccess")%>	</p>				
					<%
				} else  if (request.getAttribute("FacultyFailure")!= null) {
					
					%>
					<p class="autohide" style="color:red; font-size:17px; font-weight: bold; top: 11%; left: 41%; position: absolute;"><%= request.getAttribute("FacultyFailure") %></p>				
					<%
				} 
				
			%>

<form name="cerify" method="post">
<table>

<tr>
 	<!-- <td>Assessment ID</td>
 	<td>:</td> -->
	<td>
							 <% CertiftStudentDao asdao=new CertiftStudentDao();
		
                             ArrayList<AdminDomain>al=new ArrayList<AdminDomain>();
		                   al=asdao.retriveAssessMentDetails(facultyid,orgid);
		                      Iterator<AdminDomain> it= al.iterator();%>
		                      <select name="asessID" id="asessID"  class="box_lng" onchange="clickAssessmentID();">
		                      <option value="">--Select Assessment ID--</option>
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
				<option value="">--Select Student ID--</option>
				<%				 
 					 
			  		 if(stuId!=null)
			  	 		{
							for(String sid:stuId){%>
						
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
			
			
			<td><a style="color: #000; padding-top:5px;" href="/KODE_DEV/ControllerServlet/FTPDownloadFileDemo?StudentID=<%=uploadedid%>&filePath=<%=filepath%>">Download File</a></td></tr>
			<tr>
			
			
			<td><input type="text" name="marks" placeholder="Enter Assessment Marks" id="assessMarks"></td></tr>
			<tr><!-- <td>Enter Assessment Status</td>
			<td>:</td> -->
			<td><input type="text" name="status" placeholder="Enter Assessment Status" id="status">
			<input type="hidden" name="uploadedid" value="<%=uploadedid %>"></td>
			</tr>
			<tr><!-- <td>Enter Assessment Remarks</td>
			<td>:</td> -->
			<td><input type="text" name="remarks" placeholder="Enter Assessment Remarks" id="remarks"></td></tr>
			
			<tr>
			
			<td>
			<input type="button"   class="add_btn1" value="submit" onclick="clickSubmit();">
			<!-- <a href="../JSP/CertifyStudent.jsp">Back</a> -->
			</td>
			<%} %>
			<td style="float: left;width: 100px;line-height:35px; margin-left:10px;">
			</td>
			</tr>
</table>
</form>
</div>

</div>
<%@ include file="../JSP/FooterViews.jsp"%>

</body>
</html>