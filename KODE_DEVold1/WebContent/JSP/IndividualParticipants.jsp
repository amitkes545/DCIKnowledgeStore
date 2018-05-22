	<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.kds.keducom.dao.*"  %>
   <%@page import="com.kds.keducom.domain.*" %>
   <%@page import="java.util.*" %>
   <%@page import="java.sql.*"%>
   <%@page import="java.io.InputStream"%>
   <%@page import="java.io.OutputStream"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Individual Students</title>

<link href="../CSS/AssessmentView.css" rel="stylesheet"></link>
<style type="text/css">
.individual li
{
list-style: none;
float: left;
padding-right: 20px;
text-align: center;
}
.individual li a
{
text-decoration: none;
}
</style>

</head>
<body>
<%@ include file="../JSP/FacultyViewHeader.jsp" %>
<div class="body-mtr"> <!-- body content start -->
<div class="container">
<div style="padding-top: 75px">
<div class="sem_main">
					<div class="sem_list1">
					<ul class="sem_list1_ul">
<%
UsersInfoDomain uid=new UsersInfoDomain();
uid.setCreatedBy(session.getAttribute("userId").toString());
uid.setOrgId(session.getAttribute("orgId").toString());
DisplayStudentsDao dc=new DisplayStudentsDao();
ArrayList<RetriveImagesDomain> arl=new ArrayList<RetriveImagesDomain>();
arl=dc.selectIndividualStudents(uid);
	Iterator<RetriveImagesDomain> it= arl.iterator();
	while(it.hasNext())
    {
		RetriveImagesDomain dd=it.next();
     %>
     <li class="nn-part">
     <img alt="Profile" src="../Image/profile_list.png">
     <br/>
     <a href="/KODE_DEV/ControllerServlet/IndividualStudentDetailsService?studentId=<%=dd.getCourseImageLocation()%>" ><img class="dbimg" src="../imagelist/assignment_li.png"/><br/><span><%=dd.getCourseName()%></span></a></li>
     
  <% } %>
</ul>
</div>
</div>

</div>
</div>
</div>
<%@ include file="../JSP/FooterView.jsp" %>
</body>
</html> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.kds.KODE_DEV.domain.UsersInfoDomain" %>
     <%@page import="com.kds.KODE_DEV.domain.RetriveImagesDomain" %>
    <%@page import="com.kds.KODE_DEV.dao. DisplayStudentsDao" %>
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
<link href="../CSS/kedu.css" rel="stylesheet"></link>
</head>
<script type="text/javascript">
function clickStudentID(){
	var studentId=document.getElementById("studentId").value;
	if(studentId == "" || studentId == null){
		alert("Select student ID");
		return false;
	}else {
		//alert("else");
		document.assessForm.action="/KODE_DEV/ControllerServlet/IndividualStudentDetailsService";
		document.assessForm.submit();
	}
}
</script>
<%
UsersInfoDomain uid=new UsersInfoDomain();
uid.setCreatedBy(session.getAttribute("userId").toString());
uid.setOrgId(session.getAttribute("orgId").toString());%>
<body>
<div class="container">
		 <%@include file="headers.jsp"%> 
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%=username%>" /> 
         </jsp:include> --%>
         
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		
		<div class="owner_setup_faculty">
		<p class="strong">Students Reports</p>
		<form  name="assessForm" method="post">

<table>
<tr class="select_tr">
		<!-- <td align="left">
							<font>Assessment ID </font>
		</td>
		<td>:</td> -->
		<td>
							 <% DisplayStudentsDao dc=new DisplayStudentsDao();
							 ArrayList<RetriveImagesDomain> arl=new ArrayList<RetriveImagesDomain>();
							 arl=dc.selectIndividualStudents(uid);
							 	Iterator<RetriveImagesDomain> it= arl.iterator();%>
		                      <select name="studentId" class="box_lng" id="studentId" onfocus='this.size=5;' onblur='this.size=1;' onchange="this.size=1; this.blur();">
		                      <option value="">--select student ID--</option>
		                    <% while(it.hasNext())
		                     {
		    	             String id=it.next().getCourseName();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	                
		</td>
</tr>
	
<tr class="select_tr">
						
						<td><input type="submit"  class="add_btn1" name="submit" value="Submit" onclick="clickStudentID()"></td>
						<td><!-- <a href="../JSP/Home.jsp">Back</a> -->
						</td>
</tr>
					
					
</table>
</form>
 
    	</div>	
<%@ include file="../JSP/FooterViews.jsp"%>   
</div>
    
</body>
</html>