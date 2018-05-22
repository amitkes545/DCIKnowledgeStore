
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
.faculty_mod tr td:last-child > input{width: 217px}
</style>
</head>
<%
String message = request.getParameter("message");
if (message == null) {
	message = "";
}

	else if(message.equals("UserID is wrong")){
		message="Your User Id is Wrong";
	}
	else if(message.equals("Emailid is wrong")){
		message="Your Email Id is Wrong";
	}
%> 
<script type="text/javascript">
function validateFPWD(){
	var userid=document.getElementById("uid").value;
	var emailid=document.getElementById("eid").value;
	if(userid == ""||userid ==null){
		alert("Enter User ID");
		return false;
	}else if(emailid == ""||emailid == null){
		alert("Enter Email ID");
		return false;
	}else {
		document.fpwd.action="/KODE_DEV/ControllerServlet/ForgotPasswordServlet";
		document.fpwd.submit();
	}
}
</script>
<%
		
		String messsge = (String) session.getAttribute("MsgValue");
		String orgid=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		//System.out.println("organization id:"+orgid+"created id:"+userid);
		
	%>
<body>
<div class="container">
		<%-- <%@include file="all_one_header.jsp"%> --%>
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%=username%>" /> 
         </jsp:include> --%>
         
			<div id="anim_img1"></div>
			<div id="kds_logo"></div>
			<div class="right_menu">
			<p><img class="kk" alt="KeDucomlogo" src="../Image/kode.png" /></p>
			</div>	


<!-- <strong>Forgot Password</strong> -->
<div style="clear: both;"></div>
		<div class="faculty_mod" style="height: 210px; width:400px; padding:15px 0px 15px 35px!important;">
		<span style="color:#fff;text-transform:uppercase; text-align:center;margin:10px 0px;   font-family: sans-serif;
    font-size: 18px; font-weight:bold;   display: block;">Forgot Password</span>

<%-- <b style="color:red; font-family:arial; font-size:12px;"><%=message %></b> --%>
<%if(request.getAttribute("successMessage")!=null) {%>
       <span style="color:green; font-family:arial; font-size:12px; position:absolute;left:159px; top:149px;">  <%=request.getAttribute("successMessage")%> </span>
         <%} else if(message!=null){ %>
        <span style="color:red; position:absolute;left:159px; top:149px; font-family:arial; font-size:12px;"> <%=message %></span><%} %>
        <div class="track-assement">
        <form action="#" name="fpwd" method="post">
        <table>
<tr><td>User ID</td><td>:</td><td><input type="text" name="uid" id="uid"></td></tr>
<tr><td>Email ID</td><td>:</td><td><input type="text" name="eid" id="eid"></td></tr>
<tr height="20px"></tr>
<tr>
<td></td><td></td>
<td><input style="width: 100px" class="submit_btn" type="button" name="fpwd" id="fpwd" value="submit" onclick="validateFPWD();">
 <a href="../JSP/Login.jsp">Back</a></td>
</table>
</form>
</div>
		</div>

<%@ include file="../JSP/all_one_footer.jsp"%>
</div>

</body>
</html>