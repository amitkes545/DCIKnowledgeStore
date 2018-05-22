<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.kds.KODE_DEV.domain.*" %>
        <%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DashBoard</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<!-- <link href="../CSS/AssessmentView.css" rel="stylesheet"></link> -->
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



.dashboar-main {
    margin: auto;
    text-align: left;
    width: 80%;
}

.dboard {
    margin: 10px 0;
    text-transform: uppercase;
}
.sec{margin-left:15px;}

.sub-catg{margin-left:55px;}
.sub-catg li {
    list-style-type: square;
}
.sub-catg > ul {
    padding: 0;
}
</style>
</head>
<body>
<div class="container">
		 <%@include file="all_one_header_knowstore.jsp"%> 
		
<div style="clear: both;"></div>
<div>
		<%@ include file= "../JSP/menu.jsp" %>
		</div>

		<div class="owner_setup_faculty">
<%-- <%@ include file="../JSP/FacultyViewHeader.jsp" %>
<div class="body-mtr">
<div class="container from-top">
			<div class="dashboar-main"> --%>
				
				<div class="dboard">1. knowlegeAssets</div>
									<%
									ArrayList<RetriveImagesDomain> arl=(ArrayList<RetriveImagesDomain>)request.getAttribute("knowlegeAssets");
									Iterator<RetriveImagesDomain> it= arl.iterator();
									ArrayList<RetriveImagesDomain> arl1=(ArrayList<RetriveImagesDomain>)request.getAttribute("assessments");
									Iterator<RetriveImagesDomain> it1= arl1.iterator();
									ArrayList<RetriveImagesDomain> arl2=(ArrayList<RetriveImagesDomain>)request.getAttribute("assignments");
									Iterator<RetriveImagesDomain> it2= arl2.iterator();
									while(it.hasNext())
									{
										RetriveImagesDomain add=it.next();
										  %>
										  <%=add.getCourseName() %>
									<%} %>
									
				<div class="dboard sec">2. Assessments</div>
							<div class="sub-catg">
								<ul>
									<li>	
									<%
									while(it1.hasNext())
									{
										RetriveImagesDomain add=it1.next();
										  %>
										  <%=add.getCourseName() %>
									<%} 
									%>
									</li>
								</ul>	
								</div>	
				<div class="dboard sec">3. Assignments</div>
							<div class="sub-catg">
										
									<%
									while(it2.hasNext())
									{
										RetriveImagesDomain add=it2.next();
										  %>
										  <%=add.getCourseName() %>
									<%} %>
										
							</div>
									
				</div>


</div>				

<%@ include file="../JSP/FooterView.jsp" %>
</body>
</html>