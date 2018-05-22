<%-- <%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.kds.keducom.domain.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="../CSS/AssessmentView.css" rel="stylesheet"></link>
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

.from-top
{
margin-top:50px!important;
}
 
.sub-catg{margin-left:55px;}
.sub-catg li {
    list-style-type: square;
}
.sub-catg > ul {
    padding: 0;
}
.dboard {
    float: left;
    margin: 10px 0;
    text-transform: uppercase;
    width: 100%;
}
</style>
</head>
<body>


<%@ include file="../JSP/FacultyViewHeader.jsp" %>
<div class="body-mtr">
<div class="container from-top">
						
					<div class="sem_main">
					<div class="sem_list1">
					<ul class="sem_list1_ul">	
						
						<%
						ResultSet rs=(ResultSet)request.getAttribute("studentinfo");
						while(rs.next()){
							%>
							<br/><%=rs.getString("username") %>
							<br/><%=rs.getString("email") %>
							<br/><%=rs.getString("contact_no") %>
							<br/><%=rs.getString("address") %>
							<br/><%=rs.getString("organization_id") %>
							
							<%
						}
						ArrayList<RetriveImagesDomain> arl=(ArrayList<RetriveImagesDomain>)request.getAttribute("assesments");
						Iterator<RetriveImagesDomain> it=arl.iterator();
						%>
					<div class="dboard">Assesments</div><br>
						<div class="sub-catg">
								<ul>
									<li><%
						while(it.hasNext()){
							RetriveImagesDomain rid=it.next();
							%>
							
							<%=rid.getCourseName() %>
							<%
						}
						ArrayList<RetriveImagesDomain> arl1=(ArrayList<RetriveImagesDomain>)request.getAttribute("assignments");
						Iterator<RetriveImagesDomain> it1=arl1.iterator();
						%>
						</li>
						</ul>
						</div>
						
						<div class="dboard">Assignments</div>
						<div class="sub-catg">
								<ul>
									<li>
						<%
						while(it1.hasNext()){
							RetriveImagesDomain rid=it1.next();
							%>
							
							<%=rid.getCourseName() %>
							<%
						}
						%>
						</li>
						</ul>
						</div>
					</ul>
			</div>	
			</div>
						
</div>
</div>
<%@ include file="../JSP/FooterView.jsp" %>
</body>
</html> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.*"%>
    <%@page import="java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<link href="../CSS/kedu.css" rel="stylesheet"/>
</head>
 <%-- <%!SessionDomain sessionDomain = new SessionDomain();%>
<%
sessionDomain = (SessionDomain) session.getAttribute("SessionDomain");
       String assesmentid = sessionDomain.getAssessmentId();
       //System.out.println("assessment id in jsp of cetify:"+assesmentid);
       String username=(String)session.getAttribute("username");  --%>
<%	/* HttpSession mess = request.getSession();
		String msg = "";
      String result=(String)mess.getAttribute("SessionDomain"); */
      // ResultSet rs=(ResultSet)request.getAttribute("studentinfo");
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

		<div class="owner_setup_faculty">
	<p class="strong">Student Details</p>

	<table>
	<%
						ResultSet rs=(ResultSet)request.getAttribute("studentinfo");
						while(rs.next()){
							%>
	<tr><td>Student Name</td><td>:</td><td><%=rs.getString("username")%></td>
	<%-- <td><input type="text" name="studentName" 
					value="<%=rs.getString("username")%>" readonly="readonly"></td> --%>
			</tr>
			<tr>
				<td>Mobile Number</td><td>:</td><td><%=rs.getString("contact_no")%></td>
				<%-- <td><input type="text" td="Student ID" name="sid" 
					value="<%=rs.getString("email")%>" readonly="readonly"></td> --%>
			</tr>
			<tr>
				<td> Email ID</td><td>:</td><td><%=rs.getString("email")%></td>
				<%-- <td><input type="text" placeholder="Marks" name="marks" id="marks"
					value="<%=rs.getString("contact_no")%>" readonly="readonly"></td> --%>
			</tr>
			<tr>
				<td>Address</td><td>:</td><td><%=rs.getString("address")%></td>
				<%-- <td><input type="text" placeholder="Status" name="status" id="status"
					value="<%=rs.getString("address")%>" readonly="readonly"></td> --%>
			</tr>
	<tr>
				<td>Organization ID</td><td>:</td><td><%=rs.getString("organization_id")%></td>
				<%-- <td><input type="text" placeholder="Assessment ID" name="asname" 
					value="<%=rs.getString("organization_id")%>" readonly="readonly"></td> --%>
			</tr>
				<%
						}%>
		<%-- 	<tr>
				<!-- <td>Student ID</td><td>:</td> -->
				<td><input type="text" placeholder="Assessment ID" name="asname" 
					value="<%=sessionDomain.getAssessmentId()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<!-- <td> Marks</td><td>:</td> -->
				<td><input type="text" placeholder="Marks" name="marks" id="marks"
					value="<%=sessionDomain.getMarks()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<!-- <td>Status</td><td>:</td> -->
				<td><input type="text" placeholder="Status" name="status" id="status"
					value="<%=sessionDomain.getStatus()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<!-- <td>ReMarks</td><td>:</td> -->
				<td><input type="text"  placeholder="ReMarks" name="remarks" id="remarks"
					value="<%=sessionDomain.getReMarks()%>" readonly="readonly"></td>
			</tr>
			 --%>
			<tr><td><!-- <a style="color: #c2c2c2;" href="../JSP/StudentReports.jsp">Back</a> --></td></tr>
			
	</table>
	</div>
	<%@ include file="../JSP/FooterViews.jsp"%>
</div>
</body>
</html>