
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="../CSS/kedu.css" rel="stylesheet"/>
		<link href="../CSS/design-common.css" rel="stylesheet"/>
		<link rel="shortcut icon" href="Image/title_icon.ico" type="image/x-icon">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
		<script>
		$(document).ready(function(){ 
			 
			setTimeout(function() {
			    $('.su').fadeOut('slow');
			}, 6000);	
		});
		</script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
		<style type="text/css">
		.su{
    color: #468847;
    font-weight: bold;
    top: 60%;
    position: absolute;
    background: none repeat scroll 0% 0% #DFF0D8;
    left: 30%;
    border: 1px solid #D6E9C6;
    right: 30%;
    text-align: center;
    font-size: 18px;
    padding: 10px 0px;
    font-family: arial;
    border-radius: 10px;
}
.container{overflow: hidden;}
		</style>
	</head>
	<%
	
    
	String username=(String)session.getAttribute("userName");
	String	userid=(String)session.getAttribute("userId");
	
	String orgid=(String)session.getAttribute("orgId");
	

	String message = (String) session.getAttribute("MsgValue");
	session.setAttribute("orgid", orgid);
	session.setAttribute("userid", userid);
	session.setAttribute("username",username);
	
%>
	<body>
	<div class="container">
		<article>
		<div style="clear: both;"></div>
		<div>
		<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>

			
				<div>
		      <%@ include file= "../JSP/SuperAdminMenu.jsp" %>
		      </div>
				<%
				if (request.getAttribute("FacultySuccess")!= null) { 
				%>
					<p class="su"><%= request.getAttribute("FacultySuccess") %></p>					
					<%
				} else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
					<p style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("FacultyFailure") %></p>					
             	<%
				} 	
			%>
			
			
		</article>
		
		<div style="clear: both;"></div>
		<%@include file="FooterViews.jsp" %>
		</div>
	</body>
</html>