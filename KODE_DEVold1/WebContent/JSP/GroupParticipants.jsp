<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ page import="com.kds.KODE_DEV.dao.*"  %>
   <%@page import="com.kds.KODE_DEV.domain.*" %>
   <%@page import="java.util.*" %>
   <%@page import="java.sql.*"%>
   <%@page import="java.util.ArrayList" %>
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<link href="../CSS/kedu.css" rel="stylesheet"/>
</head>
<script type="text/javascript">
function validateSubmit(){
	var groupId=document.getElementById("groupId").value;
	var studentId=document.getElementById("studentid").value;
	if(groupId == "" || groupId == null){
		alert("select group ID");
		return false;
	} else if(studentId == "" || studentId == null){
		alert("select student ID");
		return false;
	}else {
		document.groupForm.action="/KODE_DEV/ControllerServlet/IndividualStudentDetailsService?studentId="+studentId;
		document.groupForm.submit();
	}
}
function clickAssessmentID(){
	//alert("helo");
	var groupId=document.getElementById("groupId").value;
	//alert("groupId:"+groupId);
	document.groupForm.action="/KODE_DEV/ControllerServlet/GroupDetailsService?groupName="+groupId;
	document.groupForm.submit();
}
 function onload(){
	<% String groupid=(String)session.getAttribute("groupId");
	//System.out.println("group id in an onload:"+groupid);
	if(groupid!=null && groupid.length()>0) {
		//System.out.println("assessment id in onload:"+groupid);
	 %> 
	 var selectBox = document.getElementById("groupId");
	 for(var i=0;i<selectBox.options.length;i++){
			 if(selectBox.options[i].value == '<%=groupid%>')
			{
	 selectBox.options[i].selected=true;
				 break;
			 }
		 }
				
		<%}%>
} 
</script>
<body>
<div class="container">
		<%@include file="../JSP/headers.jsp"%> 
		<%-- <jsp:include include="../JSP/all_one_header_knowstore.jsp"> 
         <jsp:param name="username" value="<%=username%>" /> 
         </jsp:include> --%>
<div style="clear: both;"></div>
<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="faculty_mod" style="height: 250px; padding:15px 25px 15px 25px!important;">
		<p class="strong">Student Details</p>
		<div class="track-assement">
		<form name="groupForm" method="post">
		<table>
<tr class="select_tr">
	
						<td>
		<%
UsersInfoDomain uid=new UsersInfoDomain();
uid.setCreatedBy((String)session.getAttribute("userId"));
uid.setOrgId((String)session.getAttribute("orgId"));
StudentGroupNameDao dc=new StudentGroupNameDao();
ArrayList<String> studentIds=(ArrayList<String>)session.getAttribute("studentinfo");
ArrayList<RetriveImagesDomain> arl=new ArrayList<RetriveImagesDomain>();
arl=dc.selectIndividualStudents(uid);
	Iterator<RetriveImagesDomain> it= arl.iterator();%>
	<select name="groupId" class="box_lng" id="groupId" onchange="clickAssessmentID();">
    <option value="">--select group ID--</option>
	<% while(it.hasNext())
    {
		String id=it.next().getCourseName();%>
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
				if(studentIds!=null){
					Iterator<String> it1=studentIds.iterator();

		  			while(it1.hasNext()){
		  				String rid=it1.next();
		  				
		  				%>
					
			<option value="<%=rid %>"><%=rid %></option>
					<% }}%>
	
		</select> </td></tr>
		<tr class="select_tr_stu">
	<td><input type="button" class="add_btn1" value="GetDetails" onclick="validateSubmit()" ></td>
	<td><!-- <a href="../JSP/TrackStudent.jsp">Back</a> --></td>
	
	</tr>
			</table>
			
     </form>
     </div>
     </div>
     </div>
	<%@ include file="../JSP/FooterViews.jsp"%>
</div>
     </div>
</body>
<script>
onload();
</script>
</html>