<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.kds.KODE_DEV.dao.*"%>
<%@page import="com.kds.KODE_DEV.domain.*"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.OutputStream"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
 --><link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
</head>
<body>
	<div class="container">
		<%@ include file="../JSP/all_one_header_knowstore.jsp"%>

		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="faculty_mod">

			<p class="strong">Session BackUP</p>


	<form action="/KODE_DEV/ControllerServlet/FacilitatorGetSessionBackupServlet">
		<%
			//HttpSession mess = request.getSession();
			DisplayCoursesDomain ri = new DisplayCoursesDomain();
			ri.setCourseName(session.getAttribute("userid").toString());
			//System.out.println("ri" + ri);
			ri.setCourseDetails(session.getAttribute("orgid").toString());
			//ri.setCourseFee(session.getAttribute("createdBy").toString());
			FacilitatorGetSessionBackupDao dc = new FacilitatorGetSessionBackupDao();
			ArrayList<RetriveImagesDomain> arl = new ArrayList<RetriveImagesDomain>();
			arl = dc.selectCourses(ri);
			Iterator<RetriveImagesDomain> it = arl.iterator();
		%>
<table>
		<%-- <tr>
		
		<td>Session name</td>
		<td>:</td>
		<td>
		<select name="filePath" id="projectid">

			<%
				while (it.hasNext()) {
					RetriveImagesDomain dd = it.next();
			%>
			<option value='<%=dd.getCourseImageLocation()%>'><%=dd.getCourseImageSize()%></option>

			<%
				}
			%>
		</select>
		</td>
		</tr>
		<tr height="15px"></tr> --%>
		
			



<p style="font-size: 15px; margin-right:20px;margin-bottom:20px; color: #000; text-align: center;">Session Values Stored on one folder if click Backup</p>




<!--   <table align="center">
     <tr><td>
        DataBase Stored on one folder if click submit </td></tr>
        <tr><td></td>
        
        
        <td></td>
        </tr></table> -->
      <tr>
		<td>
		 <input class="add_btn1" type="submit" name="submit" value="Backup" style="margin-left: 70px">
		<!--  <a class="back" style="color: #c2c2c2; text-align: left;"
							href="../JSP/Home.jsp">Back</a> -->
		 </td>
		 </tr>
	</table>
	</form>

	</div>
	</div>
</body>
</html>





