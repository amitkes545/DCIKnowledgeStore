<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<!-- <link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" /> -->
<!-- <link href="../CSS/owner.css" rel="stylesheet"/>
<link href="../CSS/global.css" rel="stylesheet"/> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
</head>
<%
		
	String	message = (String) session.getAttribute("MsgValue");
		String orgid=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		//System.out.println("organization id:"+orgid+"created id:"+userid);
		
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
		<div class="faculty_mod report_track" style="height: 180px;">
				<div class="center-align track-student">
					<form action="#">
					<table>
					<p class="strong">Track Details</p>
					 <tr><td><a href="../JSP/TrackAssessment.jsp" class="submit_btn">Assessment</a>
					  <tr><td><a href="../JSP/TrackAssignment.jsp" class="submit_btn">Assignment</a>
					  <tr><td><!-- <a href="../JSP/Home.jsp" style="color: #fff">Back</a> -->
					</table>
					</form>
				</div>
		</div>


		<%@ include file="../JSP/all_one_footer.jsp"%>
</div>

</body>
</html>