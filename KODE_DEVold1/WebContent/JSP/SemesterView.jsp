   <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
   <%@ page import="com.kds.KODE_DEV.dao.*"  %>
   <%@page import="com.kds.KODE_DEV.domain.*" %>
   <%@page import="java.util.*" %>
   <%@page import="java.sql.*"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.OutputStream"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/SubjectDetails.js"></script>
<script type="text/javascript" src="../JS/SubjectDescription.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KODE_DEV</title>
<link href="../CSS/SemesterView.css" rel="stylesheet"></link>
<script type="text/javascript">
function institutions(){

	document.Semesters.action="/KODE_DEV/ControllerServlet/Subject1";
	document.Semesters.submit(); 
	
	//window.location.href = document.getElementById('Institutions').href;
}

function timera()
{

$(".testingdiv").slideDown(1500);
	}


</script>




</head>
<body onload="timera();">
<%@ include file="../JSP/WebViewHeader.jsp" %>
<div class="body-mtr"> <!--  body content start -->
<div class="container">
<p style="color: #c2c2c2; font-size:18px; margin-top: 50px; margin-bottom: 20px;">Courses > Engineering > <span style="color:#ba8c1c;">Computer Science and Engineering</span></p>
<div class="sem_main">
<div class="sem-left">
<img src="../imagelist/cse.jpg" />
</div><!-- sem_left finished -->

<div class="sem-right">
<div class="sem_list_div">
<form name="Semesters" method="post" > <!--  action="/KODE_DEV/ControllerServlet/Subject1" -->
<ul class="subject-ul">
<%
//HttpSession ses=request.getSession(false);
ArrayList<DepartmentsDomain> arl=(ArrayList<DepartmentsDomain>)request.getAttribute("depart12");
Iterator<DepartmentsDomain> it= arl.iterator();
while(it.hasNext())
{
	DepartmentsDomain dd=it.next();
	 //System.out.println(dd.getDepartmentsNmae());
%>
	<input type="submit" class="sem_list" id="<%=dd.getDepartmentsNmae()%>" name="SemesterName" value="<%=dd.getDepartmentsNmae()%>" onclick="institutions()" /></input><br/>
<%  
 } %>
</ul>
</form>
</div><!-- right 1st dive finished -->

<div class="cont_sem"> 
<div class="testingdiv">
<% if(request.getAttribute("depart")!=null) {%>

<form name="SubjectDesc" method="post" >
<p class="semheading"><%=request.getAttribute("DepartName") %> </p>
<%

ArrayList<DepartmentsDomain> arl1=(ArrayList<DepartmentsDomain>)request.getAttribute("depart");
 		
	Iterator<DepartmentsDomain> it1= arl1.iterator();
	while(it1.hasNext())
    {
		DepartmentsDomain dd1=it1.next();
		String[] s1=dd1.getDepartmentsNmae().split("#");
		
		for(String s:s1){
    %>
		<input type="submit" name="Subjects" value="<%=s%>" onclick="Description()" /><br/>
  <%  
		}
  }
}

%>
</form>

</div><!-- slide down subject -->
</div><!-- cont_sem end -->

</div><!-- right side end -->


</div>

</div><!-- container close -->
</div><!--  body content close -->

</body>
</html>