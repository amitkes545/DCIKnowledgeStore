<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.SessionDomain"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%!SessionDomain ad = new SessionDomain();%>
<%
      ad = (SessionDomain) session.getAttribute("SessionDomain");
       String assesmentid = ad.getAssessmentId();
		/* HttpSession mess = request.getSession();
		String msg = "";
      String result=(String)mess.getAttribute("SessionDomain"); */
	%>
<body>
<center>
<strong>Student Details</strong>
	<table>
	
	<tr>
				<td>Assessment ID</td><td>:</td>
				<td><input type="text" name="asname" 
					value="<%=ad.getAssessmentId()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>Student ID</td><td>:</td>
				<td><input type="text" name="sid" 
					value="<%=ad.getFacultyId()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td> Marks</td><td>:</td>
				<td><input type="text" name="marks" id="marks"
					value="<%=ad.getMarks()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>Status</td><td>:</td>
				<td><input type="text" name="status" id="status"
					value="<%=ad.getStatus()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>ReMarks</td><td>:</td>
				<td><input type="text" name="remarks" id="remarks"
					value="<%=ad.getReMarks()%>" readonly="readonly"></td>
			</tr>
			
			<tr>
			<td></td>
			<td></td>
			<td><a style="color: #c2c2c2;" href="../JSP/StudentReports.jsp">Back</a></td>
			
			
			</tr>
			
	</table>
	</center>
	
</body>
</html>