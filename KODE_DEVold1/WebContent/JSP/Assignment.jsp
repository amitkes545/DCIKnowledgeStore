<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.kds.KODE_DEV.domain.AdminDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.AssignmentDao" %>
    <%@page import="com.kds.KODE_DEV.dao.SearchDao" %>
    <%@page import="java.util.Iterator" %>
     <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<link href="../CSS/kedu.css" rel="stylesheet"></link>
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<%
//HttpSession mess = request.getSession();
//SearchDao sdao=new SearchDao();

//String msg = "";
String username=(String)session.getAttribute("username");
String orgid=(String)session.getAttribute("orgid");
String facultyid=(String)session.getAttribute("userid");
//System.out.println("organization id:"+orgid+"created id:"+facultyid);

%>


<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
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
<style type="text/css">
.select_tr, .group_tr
{
display: none;
}


</style>

</head>
<script type="text/javascript">
  function validateAssignment(){
	//var assignId=document.getElementById("assignId").value;
	var assignName=document.getElementById("assignmentname").value;
	var file1=document.getElementById("document").value;
	 if(assignName == ""){
		alert("Enter Assignment Name");
		document.getElementById("assignmentname").focus();
		return false;
	}else if(file1 == ""){
		alert("Select document for assignment");
		document.getElementById("document").focus();
		return false;
	}else {
		document.assignment.action="/KODE_DEV/ControllerServlet/AssignmentServlet";
		document.assignment.submit();
	}
	
}
  $(document).ready(function() {
		$(".autohide").delay(5000).fadeOut("slow");
	});
  
</script>
<body>
<div class="container">
		<%-- <%@include file="all_one_header.jsp"%> --%>
		<jsp:include page="../JSP/headers.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>

		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div class="faculty_mod fiveeight"style="height: 425px;">

			<p class="strong">Create Assignment</p>
			<% if (request.getAttribute("FacultySuccess")!=null) { 
					//System.out.println(" faculty success message value in if:"+request.getAttribute("FacultySuccess"));
					String message=(String)request.getAttribute("FacultySuccess");
				/* 	 String str[]=message.split("#");
					 String valid=str[0];
					 String valid1=str[1];
				 */	%>
				<%-- 	<p class="su autohide"><%= valid %></p><br> --%>
					<p class="su1 autohide"><%=message %></p>						
					<%
				} else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
					<p class="autohide" style="color:red; font-size:23px; font-weight: bold; top: 11%; left: 46%; position: absolute;"><%= request.getAttribute("FacultyFailure") %></p>					
					<%
				} 
				
			%>
			<!-- action="/KODE_DEV/ControllerServlet/AssignmentServlet" -->
<form name="assignment" method="post" enctype="multipart/form-data">
<table>
<%if (request.getAttribute("MsgExit")!= null) {  %>
			
<p class="autohide" style="color:red; font-size:15px; font-weight: bold; top: 12%; left: 30%; position: absolute;"><%= request.getAttribute("MsgExit") %></p>					
					<%}%>
<%-- <tr><td>Assignment ID*</td><td>:</td>
<td><input type="text" name="assignId" id="assignId" value="<%= assignid%>" readonly="readonly"></td></tr> --%>
<tr><!-- <td>Assignment name</td><td>:</td> -->
<td><input  type="text" name="assignmentname" id="assignmentname" placeholder="Assignment Name"></td>
</tr>
<tr><!-- <td>Description</td><td>:</td> -->
<td><textarea name="Description" rows="5" cols="25" placeholder="Description"></textarea></td></tr>

 <tr><!-- <td>Select question paper</td><td>:</td> --><td><input  style="color: #000;" type="file" name="document" id="document"></td></tr>
 
  <tr>
  <!-- <td>To send</td>
  <td>:</td> -->
   <td class="r123">
 <input id="r1" type="radio" value="Individual" name="group">Individual 
 <input id="r2" type="radio" value="Group" name="group">Group
 <input id="r3" type="radio" value="All" name="group">All
 
 </td>
 </tr>
 <tr class="select_tr">
 
 
						<!-- <td align="left">
							<font>Student ID</font>
						</td>
						<td>:</td> -->
						<td>
							 <% AssignmentDao asdao=new AssignmentDao();
		
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
		                   al=asdao.sendGroupId(facultyid,orgid);
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
					
					<tr>
					
					
					
					<td><input class="add_btn1" type="button" value="submit" onclick="validateAssignment()">
					<!-- <a class="back_txt" href="../JSP/Home.jsp">Back</a --></td>
					<td><input type="hidden" name="facultyid" value=<%= facultyid %>></td>
					<td><input type="hidden" name="orgid" value=<%=orgid %>></td>
					</tr>
					
</table>
</form>
		</div>
		<%@ include file="../JSP/FooterViews.jsp"%>
	</div>

</body>
</html>
