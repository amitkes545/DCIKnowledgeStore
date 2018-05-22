<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.SessionDomain"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<%!SessionDomain sessionDomain = new SessionDomain();%>
<%
sessionDomain = (SessionDomain) session.getAttribute("SessionDomain");
       String assesmentid = sessionDomain.getAssessmentId();
       //System.out.println("assignment id in jsp of cetify:"+assesmentid);
       String username=(String)session.getAttribute("username");
		/* HttpSession mess = request.getSession();
		String msg = "";
      String result=(String)mess.getAttribute("SessionDomain"); */
	%>
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
<div style="clear: both;"></div>
<div class="owner_setup_faculty">
	<p class="strong">Student Details</p>
	<table>
	
	<tr>
				<td>Assignment ID</td><td>:</td><td><%=sessionDomain.getAssessmentId()%></td>
				<%-- <td><input type="text" placeholder="Assignment ID" class="box" name="asname" 
					value="<%=sessionDomain.getAssessmentId()%>" readonly="readonly"></td> --%>
			</tr>
			<tr>
				<td>Student ID</td><td>:</td><td><%=sessionDomain.getFacultyId()%></td>
				<%-- <td><input type="text" placeholder="Student ID" class="box" name="sid" 
					value="<%=sessionDomain.getFacultyId()%>" readonly="readonly"></td> --%>
			</tr>
			<tr>
				<td> Marks</td><td>:</td><td><%=sessionDomain.getMarks()%></td>
				<%-- <td><input type="text" placeholder="Marks" name="marks" class="box" id="marks"
					value="<%=sessionDomain.getMarks()%>" readonly="readonly"></td> --%>
			</tr>
			<tr>
				<td>Status</td><td>:</td><td><%=sessionDomain.getStatus()%></td>
				<%-- <td><input type="text" placeholder="Status" name="status" class="box" id="status"
					value="<%=sessionDomain.getStatus()%>" readonly="readonly"></td> --%>
			</tr>
			<tr>
				<td>Remarks</td><td>:</td><td><%=sessionDomain.getReMarks()%></td>
				<%-- <td><input type="text" placeholder="ReMarks" name="remarks" class="box" id="remarks"
					value="<%=sessionDomain.getReMarks()%>" readonly="readonly"></td> --%>
			</tr>
			
			<tr>
			<td><!-- <a style="color: #c2c2c2;" href="../JSP/StudentReports.jsp">Back</a> --></td>
			
			
			</tr>
			
	</table>
	
	</div>
	<%@ include file="../JSP/FooterViews.jsp"%>
</div>
</body>
</html>