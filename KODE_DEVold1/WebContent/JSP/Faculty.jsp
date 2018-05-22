<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.kds.KODE_DEV.domain.CreateDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.SearchAdminDao" %>
    <%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/Validatefaculty.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<!--  for country code select css -->
<link rel="stylesheet" href="../CSS/intlTelInput.css">
<script src="../JS/jquery1.11.3.js"></script>
		<link href="../CSS/global.css" rel="stylesheet"></link>
		<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
		<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
		<style type="text/css">
		/* Access Report Details*/

.faculity_setup{
position: relative;
top: 130px;
width: 500px;
/* height: 380px; */
box-shadow: 5px 5px 5px #000;
margin: 15px auto 0px;
padding: 15px 87px;
border: 2px solid #A52A2A;
background-color:rgba(0, 0, 0, 0.9);
}
.faculity_setup tr td:first-child
{
	color: #fff;
	font-size: 18px;	
	font-family: sans-serif;
}
.faculity_setup tr td span{
color: #f63a4c;
}
.faculity_setup tr td:nth-child(2)
{
	color: #fff;
	width: 40px;
	text-align: center;
	margin-left: 15px;
}



.faculity_setup tr td:last-child > input
{width: 249px; margin: 5px 0px; padding: 5px; }
.faculity_setup tr td:last-child > select
{width: 199px; padding: 5px; margin: 5px 0px;}

.faculity_setup tr td:last-child > textarea
{width: 249px; padding: 5px; margin: 5px 0px;}


		</style>
	
</head>
<style type="text/css">

.su{
		    color: #008000;
    font-size: 17px;
    font-weight: bold;
    top: 9%;
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
    top: 13%;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
span#amit {
    float: right;
    margin-left: 10px;
    margin-top: 10px;
    position: absolute;
}
</style>
<script><!-- calling ajax code from jsp-->
var request;
function getID()
{
var v=document.facultyform.fid.value;
var url="customerID.jsp?val="+v;

if(window.XMLHttpRequest){
request=new XMLHttpRequest();
}
else if(window.ActiveXObject){
request=new ActiveXObject("Microsoft.XMLHTTP");
}

try
{
request.onreadystatechange=getInfo;
request.open("GET",url,true);
request.send();
}
catch(e)
{
alert("Unable to connect to server");
}
}

function getInfo(){
if(request.readyState==4){
var val=request.responseText;
document.getElementById('amit').innerHTML=val;
}
}
//applying this jquery code to text box for enter only numbers
$(document).ready(function() {
	$("#phone").keydown(function (e) {
    	
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
            (e.keyCode == 65 && ( e.ctrlKey === true || e.metaKey === true ) ) || 
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 return;
        }
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105))
        {
            e.preventDefault();
        }
    });
});
//applying this code to text box for enter characters
$(function () {
	 $('#fname').keydown(function (e) {
	 if (e.shiftKey || e.altKey) {
	 e.preventDefault();
	 } else {
	 var key = e.keyCode;
	 if (!((key == 8) || (key == 9) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
	 e.preventDefault();
	 }
	 }
	 });
	 });
$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
});
</script>
<%
		
	String	message = (String) session.getAttribute("MsgValue");
		String username=(String)session.getAttribute("username");
		//System.out.println("msg value is:"+request.getAttribute("MsgValue"));
		String organizationId=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String organizationName=(String)session.getAttribute("organizationName");
		//System.out.println("organization id:"+organizationId+"created id:"+userid);
		
	%>
<body>



 <div class="container" style="height: 850px;">

<%-- <%@include file="all_one_header.jsp" %> --%>
<%@ include file="../JSP/all_one_header.jsp" %>
         <%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>  --%>
		
<div style="clear: both;"></div>
			<div class="faculity_setup">
			 
 <p class="strong_left">Create Faculty</p>			

         <form name="facultyform" method="post">
         <%if (request.getAttribute("AdminSuccessMail")!= null) { 
				String msg2=(String)request.getAttribute("AdminSuccessMail");
				/*  String str[]=msg2.split("#");
				 String valid=str[0];
				 String valid1=str[1];
				 */%>
			<%-- 	<p class="su autohide"><%= valid %></p><br> --%>
				<p class="su1 autohide"><%=msg2 %></p>		
				<%
			}
			else if (request.getAttribute("AdminSuccess")!= null) { 
					////System.out.println("message value in if:"+Facultymsg);
					%>
					<p class="su autohide"><%= request.getAttribute("AdminSuccess") %></p>					
					<%
				} else  if (request.getAttribute("AdminFailure")!= null) { 
					
					%>
					<p class="autohide" style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("AdminFailure") %></p>					
					<%
				} 	
			%>
           <table align="center">
           <%
			 
				if (request.getAttribute("MsgValue")!= null) { 
					%>
					<span style="color: red; right: 25px; position: absolute;top: 60px;"><%= request.getAttribute("MsgValue") %></span>					
					<%
				}
				
			%>
           <tr><td>Name<span>*</span></td><td>:</td>
              <td><input type="text" name="fname" id="fname"></td>
              </tr>
              <tr><td>ID<span>*</span></td><td>:</td>
                <td><input type="text" name="fid" id="fid" onblur="getID()"><span id="amit"></span></td>
                </tr>
                <!--  <tr><td>Password<span>*</span></td><td>:</td>
                  <td><input type="password" name="pwd" id="pwd"></td>
                  </tr>
                  <tr><td>Conform Password<span>*</span></td><td>:</td>
                  <td><input type="password" name="cfpwd" id="cfpwd"></td>
                  </tr> -->
                <tr><td>Email ID<span>*</span></td><td>:</td>
                <td><input type="text" name="email" id="email"></td>
                </tr>
                <tr><td>Address<span>*</span></td><td>:</td>
                  <td><textarea rows="5" cols="25" name="address" id="address"></textarea></td>
                  </tr>
                  <tr><td>Mobile #<span>*</span></td><td>:</td>
                  <td><input type="text" name="phone" id="phone" maxlength="10"></td>
                  </tr>
                  <!-- <tr>
					<td>Privilege<span>*</span></td><td>:</td>
					<td><input type="text" name="privilege" value="Faculty" readonly="readonly"></td>
					
				</tr> -->
                  <tr><td>Institute ID</td><td>:</td>
                  <td><input type="text" name="orgid" id="orgid" value="<%= organizationName%>" readonly="readonly"></td>
                  </tr>
                  
                   <tr><td><input type="hidden" name="createdid" value="<%=userid %>"></td>
                   <td><input type="hidden" name="organizationId" value="<%=organizationId %>"></td></tr>
                    <tr>
                  <td></td>
                  <td></td>
                  <td style="padding-right: 15px;">
                  
                  <input class="submit_btn" style="width: 100px; margin-right: 15px;" type="button" value="ADD" onclick="Validatefaculty()">
                  <a style="color: #c2c2c2; font-weight: bold;" href="../JSP/Home.jsp">Back</a></td>
                  </tr>
                <tr height="15px;"></tr>
               
           </table>
         </form>

		</div>

   <%@include file="all_one_footer.jsp" %>
</div>
<script src="../JS/intlTelInput.js"></script>
<script>
$("#phone").intlTelInput({
  utilsScript: "../JS/utils.js"
});
</script>
</body>
</html>