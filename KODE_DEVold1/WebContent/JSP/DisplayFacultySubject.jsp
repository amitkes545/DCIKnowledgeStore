<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.AdminDomain"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
		<link href="../CSS/global.css" rel="stylesheet"></link>
		<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
		<style type="text/css">
		/* Access Report Details*/

.faculity_setup{position: relative; top: 130px;
width: 375px;
/* height: 380px; */
box-shadow: 5px 5px 5px #000;
margin: 15px auto 0px;
padding: 15px 87px;
border: 2px solid #A52A2A;
background-color: rgba(25, 25, 55, 0.5);

}

.faculity_setup tr td:first-child
{
	color: #fff;
	font-size: 18px;	
	font-family: sans-serif;
	width: 170px;
}
.faculity_setup tr td span{
color: #f63a4c;
}
.faculity_setup tr td:last-child > input
{width: 184px; margin: 5px 0px; padding: 5px; }
.faculity_setup tr td:last-child > select
{width: 199px; padding: 5px; margin: 5px 0px;}
</style>
		
</head>
<style type="text/css">

.su{
		    color: #008000;
    font-size: 22px;
    font-weight: bold;
    top: 23%;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
.su1{
		    color: #008000;
    font-size: 12px;
    font-weight: bold;
    top: 14%;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
</style>
<%
	ArrayList<AdminDomain> msg;
	AdminDomain pdomain = new AdminDomain();
	String facultyid=(String)session.getAttribute("facultyid");
	//System.out.println("faculty id is"+facultyid);
    msg =(ArrayList<AdminDomain>) session.getAttribute("success");
    //System.out.println("subject values are"+msg);
    HttpSession mess = request.getSession();
		String username=(String)mess.getAttribute("username");
    %>
<body>
<div class="container">
<div style="clear: both;"></div>
 <%@ include file="../JSP/all_one_header.jsp" %> 
<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="userid" value="<%= username%>" /> 
         </jsp:include>
		 --%>
		<!-- 	<div id="anim_img1"></div>
			<div id="kds_logo"></div>
			<div id="heading">Welcome xxxxxx</div>
			<div class="right_menu">
			<ul>
			<li id="cpwd-li"><a href="#" id="cpwd">change Password</a></li>
			
			<li><a href="../JSP/Login.jsp"><button id="logout">Logout</button></a></li>
			</ul>
			</div> -->
<div style="clear: both;"></div>
			<div class="faculity_setup">
			 
 <p class="strong">Faculty Subject Details</p>			
          <form action="/KODE_DEV/ControllerServlet/UpdateFacultySubject"
		method="post">
	          <% if (request.getAttribute("AdminSuccess")!= null) { 
					////System.out.println("message value in if:"+Facultymsg);
					%>
					<p class="su"><%= request.getAttribute("AdminSuccess") %></p>					
					<%
				} else  if (request.getAttribute("AdminFailure")!= null) { 
					
					%>
					<p style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("AdminFailure") %></p>					
					<%
				} 	
			%>
			<table border="2">
				<tr>

					<th>Subject ID</th>
					<th>Access</th>
					</tr>
					<% Iterator it = msg.iterator();
				while (it.hasNext()) {
					pdomain = (AdminDomain) it.next();	
					//System.out.println(pdomain.getSubject_id()); 
				
				%><tr><td><%=pdomain.getSubject_id()%></td> 
				 <td><input type="checkbox" id="subjectid" name="subjectid" value="<%=pdomain.getSubject_id()%>" checked></td></tr>
				 <%} %>
					</table>
					
						<p style="margin-left: 104px; margin-top: 15px"> <input class="setup_add_btn" style="width: 100px;" type="submit" value="update">  <a style="margin-left: 10px;color: #c2c2c2; font-weight: bold; " href="../JSP/ManageFaculty.jsp">Back</a></p>
					<input type="hidden" name="facultyid" value="<%=facultyid%>">
				 </form>
		</div>
		<%@ include file="../JSP/all_one_footer.jsp" %>
</div>
</body>
</html>