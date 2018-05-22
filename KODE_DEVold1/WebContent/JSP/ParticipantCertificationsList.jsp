<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.kds.KODE_DEV.dao.*" %>
    <%@ page import="com.kds.KODE_DEV.domain.*" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Certifications list</title>
<style type="text/css">
.ww{
text-transform: capitalize;
}
input{
border: none;
}
.h44
{
font-size: 22px;
text-transform: uppercase;
}
.up-load
{
margin-top: -40px;
margin-left: 150px;
position: absolute;
}
.sub_mit
{
margin-top: -10px;
margin-left: 150px;
position: absolute;
width: 100px;
background: #3366FF;
padding: 10px;
color: #fff;
}
.sub_mit:hover
{
background: #CC33FF;

}
.m-top{
margin-top:50px!important;
}
.center{text-algin:center;}
.certi td {

    padding: 10px;
}
.certi table {
margin-top:100px; 
    width: 100%;
}
.certi tr 
{
    background: none repeat scroll 0 0 #eee;
    width: 100%;
    color:#333;
}
.certi tr.title {
    background: none repeat scroll 0 0 #5699fa;
    color: #fff;
    text-align: center;
}
.certi td a{text-align: center;}
</style>

</head>
<body>
<%@ include file="../JSP/ParticipantViewHeader.jsp" %>
<!-- <div class="body-mtr">
	<div class="container m-top center"> -->
			<div style="clear: both;"></div>
	<div class="certi">

	<table>
		<tr class="title">
			<td>Assessment ID</td>
			<td>Marks</td>
			<td>Status</td>
			<td>Result</td>
			<td>Certificate</td>
		</tr>
	
	
					<%
					ParticipantCertificationList pcl=new ParticipantCertificationList();
					ArrayList<DisplayCoursesDomain> arl=pcl.selectCertifications(session.getAttribute("userId").toString());
					Iterator<DisplayCoursesDomain> it=arl.iterator();
					while(it.hasNext()){
						DisplayCoursesDomain dc=it.next();
					%>
				<tr class="first-row">	
				<td><%=(dc.getCourseName()==null)?"":dc.getCourseName() %></td>
	<td><%=(dc.getImagePath()==null)?"":dc.getImagePath() %></td>
	<td><%=(dc.getCourseFee()==null)?"":dc.getCourseFee() %></td>
	<td><%=(dc.getCourseDetails()==null)?"":dc.getCourseDetails()%></td>
<% if(dc.getImagePath()==null){ %>
	<td></td>
	<%}else{ %>
				<td><a href="../JSP/GetCertificate.jsp?id=<%=dc.getCourseName() %>&mark=<%=dc.getImagePath() %>&result=<%=dc.getCourseFee() %>&status=<%=dc.getCourseDetails() %>">Get Certificate </a></td>
				<%} %>
				<%
				}
				%>
				</tr>
	</table>
	</div>
<%@ include file="../JSP/FooterView.jsp" %>
</body>
</html>