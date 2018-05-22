<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Individual Students</title>
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
					UsersInfoDomain uid=new UsersInfoDomain();
					uid.setCreatedBy(session.getAttribute("userId").toString());
					uid.setOrgId(session.getAttribute("orgId").toString());
					FacultyKnowledgeAssetsNameDao dc=new FacultyKnowledgeAssetsNameDao();
					ArrayList<RetriveImagesDomain> arl=new ArrayList<RetriveImagesDomain>();
					arl=dc.selectKnowledgeAssetsName(uid);
						Iterator<RetriveImagesDomain> it= arl.iterator();
						while(it.hasNext())
					    {
							RetriveImagesDomain dd=it.next();
					     %>
					     <li class="nn-part">
					     <a href="/KODE_DEV/ControllerServlet/FacultyKnowledgeAssetsDetailsService?ksId=<%=dd.getCourseName()%>">
					     <img class="dbimg" src="../imagelist/bend.png"/><br/><span><%=dd.getCourseName()%></span></a>
					     </li>
					     
					  <% } %>
					</ul>
					</div>
					</div>
					
</div>
</div>					
<%@ include file="../JSP/FooterView.jsp" %>				
</body>
</html>