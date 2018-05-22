<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.kds.KODE_DEV.domain.AdminDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.AssessmentDao" %>
     <%@page import="com.kds.KODE_DEV.dao.SearchDao" %>
    <%@page import="java.util.Iterator" %>
     <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<%
//SearchDao sdao=new SearchDao();
/* String sequence=sdao.getSequenceValue();//selecting sequence value from dao
String assessid="assess"+sequence;
//System.out.println("sequence value in jsp:"+sequence+" assessid:"+assessid); */


	String username=(String)session.getAttribute("username");
   String facultyid=(String)session.getAttribute("userid");
   String orgid=(String)session.getAttribute("orgid");
   String assessmentId=(String)request.getAttribute("MsgExit");
   //System.out.println("faculty id in assessment jsp:"+facultyid+"orgid:"+orgid+ "assessment ID:"+assessmentId);
%>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>  -->
<script src="../JS/jquery.min.js"></script> 
<script>
$(document).ready(function () { 
	$("#r1").click(function() {
	 $(".select_tr").show();
	 $(".group_tr").hide();
	 });
	
	
	$("#r2").click(function() {
		 $(".group_tr").show();
		 $(".select_tr").hide();
		 });
	$("#r3").click(function() {
		 $(".group_tr").hide();
		 $(".select_tr").hide();
		 });
		
	/*  $("#watch-me").click(function(){
	 if($('watch-me').prop('checked')===false) {
	    $('#show-me').hide();}
	    }); */
	});
</script>
<style type="text/css">

.su{
		    color: #008000;
    font-size: 12px;
    font-weight: bold;
    top: 12%;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
.r123 input {
    width: 15px!important;
}
.su1{
		    color: #008000;
    font-size: 10px;
    font-weight: bold;
    top: 15%;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<style type="text/css">
.select_tr, .group_tr
{
display: none;
}


</style>

</head>
<script type="text/javascript">
function validateAssessment(){
	//var asid=document.getElementById("assessId").value;
	var asName=document.getElementById("assessmentname").value;
	var file1=document.getElementById("document").value;
	var marks=document.getElementById("marks").value;
	 if(asName == ""){
		alert("Enter Assessment Name");
		document.getElementById("assessmentname").focus();
		return false;
	}else if(file1 == ""){
		alert("Select document for assessment");
		document.getElementById("document").focus();
		return false;
	}else if(marks == ""){
		alert("Enter marks");
		document.getElementById("marks").focus();
		return false;
	}
	else {
		document.assessment.action="/KODE_DEV/ControllerServlet/AssessmentServlet";
		document.assessment.submit();
	}
}
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

$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
});
</script>
<body>

<div class="container">
	 <%@include file="headers.jsp"%> 
		<%-- <jsp:include page="../JSP/all_one_header_knowstore.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> --%>

		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="faculty_mod fiveeight" style="height: 425px;">

			<p class="strong">Create Assessment </p>
			<% if (request.getAttribute("FacultySuccess")!=null) { 
					//System.out.println(" faculty success message value in if:"+request.getAttribute("FacultySuccess"));
					String message=(String)request.getAttribute("FacultySuccess");
			/* 		 String str[]=message.split("#");
					 String valid=str[0];
					 String valid1=str[1];
			 */		%>
				<%-- 	<p class="su autohide"><%= valid %></p><br> --%>
					<p class="su1 autohide"><%=message %></p>		
					<%
				} else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
					<p class="autohide" style="color:red; font-size:12px; font-weight: bold; top: 10%; left: 30%; position: absolute;"><%= request.getAttribute("FacultyFailure") %></p>					
					<%
				} 
				
			%>
			<!-- action="/KODE_DEV/ControllerServlet/AssessmentServlet" -->
			<form name="assessment" method="post" enctype="multipart/form-data">
<table>
<%if (request.getAttribute("MsgExit")!= null) {  %>
			
<p class="autohide" style="color:red; font-size:12px; font-weight: bold; top: 10%; left: 30%; position: absolute;"><%= request.getAttribute("MsgExit") %></p>					
					<%}%>
				
<%-- <tr><td>Assessment ID*</td><td>:</td>
<td><input type="text" name="assessId" id="assessId" value=<%=assessid %> readonly="readonly"></td></tr> --%>
<tr><!-- <td>Assessment Name</td><td>:</td> -->
<td><input type="text" name="assessmentname" placeholder="Assessment Name" id="assessmentname"></td>
</tr>
<tr><!-- <td>Description</td><td>:</td> -->
<td><textarea name="Description" placeholder="Description" rows="5" cols="25"></textarea></td></tr>

 <tr><!-- <td>Select question paper</td><td>:</td> -->
 <td><input style="color: #000;" type="file" name="document" id="document" placeholder="Select Question Paper"></td></tr>
  <tr><!-- <td>To send</td><td>:</td> -->
 <td class="r123">
 <input id="r1" type="radio" value="Individual" name="group">Individual 
 <input id="r2" type="radio" value="Group" name="group">Group
 <input id="r3" type="radio" value="All" name="group">All
 
 </td>
 <!-- <td class="r123"></td>
 <td class="r123"></td> -->
 </tr>
 <tr class="select_tr">
 
 
						<!-- <td align="left">
							<font>Student ID </font>
						</td>
						
						<td>:</td> -->
						<td>
							 <% AssessmentDao asdao=new AssessmentDao();
		
                             ArrayList<AdminDomain>al=new ArrayList<AdminDomain>();
		                   al=asdao.sendIndualGroup(facultyid,orgid);
		                      Iterator<AdminDomain> it= al.iterator();%>
		                      <select name="student_id">
		                      <option value="select">--select--</option>
		                    <% while(it.hasNext())
		                     {
		    	             String id=it.next().getAdminId();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	                
						</td>
					</tr>
					 <tr class="group_tr">
						<!-- <td align="left">
							<font>Group ID</font>
						</td>
						<td>:</td> -->
						
						<td>
							 <% 
		
                             ArrayList<AdminDomain>al1=new ArrayList<AdminDomain>();
		                   al=asdao.sendGroupId(facultyid);
		                      Iterator<AdminDomain> it1= al.iterator();%>
		                      <select name="group_id">
		                      <option value="select">--select--</option>
		                    <% while(it1.hasNext())
		                     {
		    	             String id=it1.next().getGroup_name();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	                
						</td>
					</tr>
					<tr><!-- <td>Marks</td><td>:</td> -->
					<td><input type="text" name="marks" id="marks" placeholder="Marks" maxlength="3" style="margin-bottom: 10px"></td></tr>
					
					<tr>
					<td>
					<input class="add_btn1" type="button" value="submit" onclick="validateAssessment()">
					<!-- <a class="back_txt" href="../JSP/Home.jsp">Back</a> --></td>
					<td><input type="hidden" name="facultyid" value=<%= facultyid %>></td>
					<td><input type="hidden" name="orgid" value=<%=orgid %>></td></tr>
					
</table>
</form>
		</div>
		<%@ include file="../JSP/FooterViews.jsp"%>
	</div>

</body>
</html>
