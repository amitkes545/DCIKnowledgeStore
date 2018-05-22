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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script> 
$(document).ready(function(){

    $(".buy_btn").click(function(){
        /* $(".sem_i").slideToggle(20); */
        
        $(".unit_div").hide();
        $(".newdiv").show();
    });
    });
    </script>


</head>
<body onload="timera();">
<%@ include file="../JSP/WebViewHeader.jsp" %>
<div class="body-mtr"> <!--  body content start -->
<div class="container">
<p style="color: #c2c2c2; font-size:18px; margin-top: 50px; margin-bottom: 20px;">Courses > Engineering > <span style="color:#ba8c1c;">Computer Science and Engineering</span></p>
<div class="sem_main">
<div class="sem-left">
<img style="border: 1px solid #c2c2c2;" src="../imagelist/java.png" />

<p><input class="buy_btn" type="button" value="$100" /> </p>
</div><!-- sem_left finished -->

<div class="sem-right">
<div class="newdiv" style="display: none;">
<h1 style="margin-left: 150px; margin-top: 100px;">Payment Mode Under Construction </h1>
</div>

<div class="unit_div">
<form name="SubjectDesc" method="post">
<ul class="unit_list">
<p class="sbject_name_head"> <%=request.getAttribute("Subjects") %> </p>

<%
SubjectDescriptionDomain dd1;
SubjectDescriptionDomain dd;
ArrayList<SubjectDescriptionDomain> arl=(ArrayList<SubjectDescriptionDomain>)request.getAttribute("departm");

	Iterator<SubjectDescriptionDomain> it= arl.iterator();
	dd1=arl.get(0);
	%>
	<p class="auther_heading">- <%=dd1.getAuthorName() %></p>
	<%
	while(it.hasNext())
    {
		 dd=it.next();
		 %>
		
		<%
		String[] s1=dd.getSubjectDescription().split("#");
		for(String s:s1){
    %>
		<li><%=s %></li> 
	  <%  
		}
  }

%>


</ul>
</form>
</div>



</div><!-- right side end -->


</div>

</div><!-- container close -->
</div><!--  body content close -->

</body>
</html>