<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.CreateDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.CustomerDao" %>
    <%@page import="java.util.Iterator" %>
     <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="../JS/Validatecustomer.js"></script>
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<script src="../JS/jquery1.11.3.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!--  for country code select css -->
<link rel="stylesheet" href="../CSS/intlTelInput.css">
<script type="text/javascript"> 
$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
});

var request;
function getID()
{
var v=document.customerform.cid.value;
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
//JQuery code to text box for enter only numbers
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
//JQuery code for allowed customer name has a characters
$(function () {
	
	 $('#cname').keydown(function (e) {
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
</script>
<style type="text/css"> 
<!--this design code for display message -->
	.su{
	color: #008000;
    font-size: 17px;
    font-weight: bold;
    margin-top: -40px;
/*     top: 7%;
    left:6%;
    position: absolute;
 */
 }
.su1{
		    color: #008000;
    font-size: 15px;
    font-weight: bold;
    position: absolute;
    /*top: 10%;
    position: absolute;
     background: #fff;
    opacity: 0.7; 
    right:0px;
    padding: 0px 36px;*/
}	
/* .su1{
	color: #008000;
    font-size: 15px;
    font-weight: bold;
    top: 12%;
    position: absolute;
    right:0px;
    padding: 0px 36px;
}
.new1
{
	color: #008000;
    font-size: 16px;
    font-weight: bold;
    top: 9%;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}  */
 span#amit {
    float: right;
    margin-left: 10px;
    margin-top: 10px;
    position: absolute;
}
		</style>

</head>
<%
		String userid=(String)session.getAttribute("userid");
		//System.out.println("created id in jsp:"+userid);
	
	%>
	
<body>

<div class="container" style="height: 850px">
	<%@include file="all_one_header.jsp" %>	
			
 <%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> --%>
<div style="clear: both;"></div>
			<div class="customer_create">
			 
 <p class="strong_left">Create Customer</p>
 

<!--       /KODE_DEV/ControllerServlet/CreateCustomerServlet
 -->         <form name="customerform" method="post">
           <table align="center">
           <%-- <%
			 
				if (request.getAttribute("MsgValue")!= null) { 
					%>
					<p style="color: red;"><%= request.getAttribute("MsgValue") %></p>					
					<%
				} 
				
			%> --%>
			<% 
				if (request.getAttribute("OwnerSuccessMAil")!= null) { 
					String msg2=(String)request.getAttribute("OwnerSuccessMAil");
				/* 	 String str[]=msg2.split("#");
					 String valid=str[0];
					 String valid1=str[1];
				 */	%>
					<%-- <p class="su autohide"><%= valid %></p><br> --%>
					<p class="su1 autohide"><%=msg2 %></p>					
					<%
				}
				else if (request.getAttribute("OwnerSuccess")!= null) { 
					
					%>
					<p class="su autohide"><%= request.getAttribute("OwnerSuccess") %></p>					
					<%
				} 
				else  if (request.getAttribute("OwnerFailure")!= null) { 
					
					%>
					<p class="autohide" style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("OwnerFailure") %></p>					
					<%
				} 
				
			%>
			
           <tr><td>Name<span>*</span></td><td>:</td>
              <td><input type="text" name="cname" id="cname"></td>
              </tr>
              <tr><td>ID<span>*</span></td><td>:</td>
                <td><input type="text" name="cid" id="cid" onblur="getID()"><span id="amit"></span></td>
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
                  <td>
	                 <!--  <input type="text" value="+91" disabled="disabled" style=" width:30px!important; float:left;"> -->
	                  <input  type="text" name="phone" id="phone" maxlength="10" onfocus="this.placeholder = ''" onblur="this.placeholder = '09123456789'" />
	                 </td> 
                  </tr>
                  
                  <tr>
					<td>Privilege<span>*</span></td><td>:</td>
					<!-- <td><input type="text" value="Client" name="client" readonly="readonly"></td> -->
					<td><select name="privilege" id="privilege">
					          <OPTION value="" selected>- Select privilege -</OPTION>
					          <!-- <option value="Client">client</option>
                             <option value="SuperAdmin">SuperAdmin</option> -->
                          <option value="Admin">Admin</option>
                         <option value="Faculty">Faculty</option>
                         <option value="Student">Student</option>
                    </select></td>
                    
				</tr>
				<!-- <tr><td>Organization ID<span>*</span></td><td>:</td>
				<td><input type="text" name="orgid" id="orgid"></td>
				
				</tr> -->
				<tr>
						
							<td>Institution Name<span>*</span></td><td>:</td>
							<!-- <span style="margin-left: 10px; margin-right: 10px; color: #fff">:</span> -->
							
						
						
						<td>
							 <%CustomerDao dao= new CustomerDao();
					           ArrayList<CreateDomain>al=new ArrayList<CreateDomain>();
							    al=dao.getRetriveid();
							    Iterator<CreateDomain> it= al.iterator();%>
							    <select name="orgid" id="orgid" >
							    <option value="">--select Institution Name--</option>
							    
							    <%String organizationName="";
							    String OrganizationID="";
							    String idAndName="";
							    while(it.hasNext())
							    {
							    	CreateDomain domain=it.next();
							    	 organizationName=domain.getOrg_name();
							    	 OrganizationID=domain.getOrg_id();
							    	//System.out.println("organization id:"+OrganizationID+ "organizationName:"+organizationName);
							     idAndName=organizationName+" ("+OrganizationID+")";
							    	%>
							    	 
							    <option value="<%= OrganizationID %>"><%=idAndName%></option>	
							   <% }%>
							   </select>
						</td>
					</tr>
				<tr><td><input type="hidden" name="createdid" value="<%=userid %>"></td></tr>
				
                  <tr height="5px;"></tr>
                
                  <tr><td></td>
                  <td></td>
                  <td style="width: 100px;">
                  <input style="width: 100px;" class="create_add_btn" type="button"  value="ADD" onclick="Validatecustomer()">
                     <a class="back" style="color: #c2c2c2;" href="../JSP/Home.jsp">Back</a></td>
                  </tr>
                                    <tr height="5px;"></tr>
                  </table></form>
        
		</div>	
			
		
		<div id="all_page_footer">
<p id="footer-text">&copy; 2015 All Rights Reserved <a href="www.kompaceducation.com" target="_newwindow" style="color: #fff;text-decoration: none">Kompac Education Systems Pvt. Ltd</a></p>
</div>
		
		<%-- <%@include file="all_one_footer.jsp" %> --%>
			
		</div>	
<script src="../JS/intlTelInput.js"></script>
<script>
$("#phone").intlTelInput({
  utilsScript: "../JS/utils.js"
});
</script>
</body>
</html>