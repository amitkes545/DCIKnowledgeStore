   <%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<style type="text/css">
.subject-ul{text-align: center;}
.subject-ul li
	{
	margin-top:50px;
	margin-left:50px;
	text-align: center;
	list-style: none;
	float: left;
	width: 205px;
	}
.subject-ul li:first-child{margin-left: 0px;}
.subject-ul li a{text-decoration: none;color: #ba8c1c; font-size: 18px; font-weight: 800; font-family: Roboto-Regular;}
</style>
</head>
<body>


<%
/** 

Faculty View Home Page
It contains @include Header Page,@include Footer page.

*/

%>



<%@ include file="../JSP/FacultyViewHeader.jsp" %>
<div class="body-mtr"> <!-- body content start -->
<div class="container">
<p style="color: #c2c2c2; font-size:18px; margin-top: 50px; margin-bottom: 20px;">Faculty > <span style="color:#ba8c1c;">Courses</span></p>
<div class="subject-div">
<ul class="subject-ul">
<li><a href="#"><img src="../Image/course1.jpg" /><br/><span>Technology</span></a></li>
<li><a href="#"><img src="../Image/course2.jpg" /><br/><span>Engineering</span></a></li>
<li><a href="#"><img src="../Image/course3.jpg" /><br/><span>Mathematics</span></a></li>
<li><a href="#"><img src="../Image/course4.jpg" /><br/><span>commerce</span></a></li>
</ul>
</div>
<div style="clear: both;"></div>
</div>
</div><!-- body content close -->
<%@ include file="../JSP/FooterView.jsp" %>
</body>
</html> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<!-- <link href="../CSS/owner.css" rel="stylesheet"></link>
		<link href="../CSS/global.css" rel="stylesheet"></link> -->
		<link href="../CSS/kedu.css" rel="stylesheet"/>
		<link href="../CSS/design-common.css" rel="stylesheet"/>
		<link rel="shortcut icon" href="Image/title_icon.ico" type="image/x-icon">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
		<script>
		$(document).ready(function(){ 
			 
			setTimeout(function() {
			    $('.su').fadeOut('slow');
			}, 6000);	
		});
		</script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
		<style type="text/css">
		.su{
    color: #468847;
    font-weight: bold;
    top: 60%;
    position: absolute;
    background: none repeat scroll 0% 0% #DFF0D8;
    left: 30%;
    border: 1px solid #D6E9C6;
    right: 30%;
    text-align: center;
    font-size: 18px;
    padding: 10px 0px;
    font-family: arial;
    border-radius: 10px;
}
.container{overflow: hidden;}
		</style>
	</head>
	<%
	
	/* HttpSession mess = request.getSession();	
    String msg = ""; */
    
	String username=(String)session.getAttribute("userName");
     System.out.println(session.getAttributeNames().hasMoreElements());
    
    if(username==null)
		response.sendRedirect("../JSP/error.jsp");
	String	userid=(String)session.getAttribute("userId");
	//System.out.println("userid is:"+userid);
	String orgid=(String)session.getAttribute("orgId");
	
	//System.out.println("organization id:"+orgid);
	String message = (String) session.getAttribute("MsgValue");
	session.setAttribute("orgid", orgid);
	session.setAttribute("userid", userid);
	session.setAttribute("username",username);
	//}
%>
	<body>
	<div class="container">
		<article>
		<div style="clear: both;"></div>
		<div>
		<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>

		<%-- <jsp:include page="../JSP/headers.jsp"> 
         <jsp:param name="username" value="<%=username%>" /> 
         </jsp:include>  --%>
			
			
				<div>
		      <%@ include file= "../JSP/Participant_menu.jsp" %>
		      </div>
				<!-- <div class="dropdown">
				<button>Participants</button>
				<ul class="dropdown-menu">
					<li><a href="../JSP/Student.jsp">Create</a></li>
					<li><a href="../JSP/CollaborateStudent.jsp">Create Group</a></li>
					<li><a href="../JSP/AccessStudent.jsp">Access</a></li>
					<li><a href="../JSP/Assessment.jsp">Assessment</a></li>
					<li><a href="../JSP/Assignment.jsp">Assignment</a></li>
					<li><a href="../JSP/CertifyStudent.jsp">Certify</a></li>
					<li><a href="../JSP/TrackStudent.jsp">Track</a></li>
					<li><a href="../JSP/StudentReports.jsp">Details</a></li>
					<li><a href="../JSP/ProductAcl.jsp">ProductACL</a></li>
					<li><a href="#">Reports</a></li>
				</ul>
			</div> 
			<div class="dropdown1">
				<button style="padding-left: 10px;">Knowledge Assets</button>
				<ul class="dropdown-menu1">
					<li><a href="../JSP/FacilitatorKnowSetup.jsp">Create KAsset</a></li>
					<li><a href="../JSP/FacilitatorKnowPublish.jsp">Publish</a></li>
					<li><a href="../JSP/FacilitatorKAssetsReports.jsp">Details</a></li>
				</ul>
			</div> 
			<div class="dropdown2">
				<button style="padding-left: 10px;">Knowledge Store</button>
				<ul class="dropdown-menu2">
					<li><a href="../JSP/FacilitatorBuildKnowLib.jsp">Build Library</a></li>
					<li><a href="../JSP/FacilitatorManageLib.jsp">Manage</a></li>
					<li><a href="../JSP/FacilitatorFileShareKnowStore.jsp">Share</a></li>
					<li><a href="../JSP/AdminDataBackupKnowStore.jsp">Data Backup</a></li>
					<li><a href="../JSP/FacilitatorKnowReport.jsp">Details</a></li>
				</ul>
			</div> 
			<div class="dropdown3">
				<button>Session Management</button>
				<ul class="dropdown-menu3">
					<li><a href="../JSP/CreateSession.jsp">Create</a></li>
					<li><a href="../JSP/PostPoneSession.jsp">Postpone</a></li>
					<li><a href="../JSP/ManageSessionSearch.jsp">Manage</a></li>
					<li><a href="../JSP/ShareFileToFTP.jsp">Share</a></li>
					<li><a href="../JSP/FacilitatorSessionCancel.jsp">Cancel</a></li>
					<li><a href="../JSP/FacilitatorGetSessionBackup.jsp">Session Backup</a></li>
					<li><a href="../JSP/FacilitatorSessionReport.jsp">Details</a></li>
					
				</ul>
			</div>  -->
			<%
				if (request.getAttribute("FacultySuccess")!= null) { 
					////System.out.println("message value in if:"+Facultymsg);
					%>
					<p class="su"><%= request.getAttribute("FacultySuccess") %></p>					
					<%
				} else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
					<p style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("FacultyFailure") %></p>					
             	<%
				} 	
			%>
			
			
		</article>
		
		<div style="clear: both;"></div>
		<%@include file="FooterViews.jsp" %>
		</div>
	</body>
</html>