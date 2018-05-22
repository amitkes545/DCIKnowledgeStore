<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.*"%>
    <%@page import="java.util.*"%>
<%@page import="java.io.InputStream"%>
<%@page import="com.kds.KODE_DEV.dao.SearchDao" %>
<%@page import="java.io.OutputStream"%>
<%@page import="com.kds.KODE_DEV.dbconnection.DBTransaction" %>
<%@page import="com.kds.KODE_DEV.domain.CreateDomain" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script> 
<script type="text/javascript" src="../JS/ImagePreview.js"></script>
<script type="text/javascript" src="../JS/country.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!--  for country code select css -->
<link rel="stylesheet" href="../CSS/intlTelInput.css">

<link href="../CSS/owner.css" rel="stylesheet"></link>
		<link href="../CSS/global.css" rel="stylesheet"></link>
		<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
		<script>
		 $(document).ready(function() {
			  $(".eedit").click(function(){
			 	$("#new_btn").css("display", "none");
			 	
			 	$(".js_country").css("display", "block");
			 	$(".db_country").css("display", "none");
			 	  populateCountries("country", "state");
			      populateCountries("country2");
			      
			  });
			  });
		//JQuery code for allowed organization name has a characters
		$(function () {
			 $('#orgName').keydown(function (e) {
			 if (e.altKey) {
			 e.preventDefault();
			 } else {
			 var key = e.keyCode;
			 if (!((key == 8) || (key == 9) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
			 e.preventDefault();
			 }
			 }
			 });
			 });
		//JQuery code for allowed organization type has a characters
		$(function () {
			 $('#orgType').keydown(function (e) {
			 if (e.altKey) {
			 e.preventDefault();
			 } else {
			 var key = e.keyCode;
			 if (!((key == 8) || (key == 9) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
			 e.preventDefault();
			 }
			 }
			 });
			 });
		//JQuery code for allowed city name has a characters
		$(function () {
			 $('#city').keydown(function (e) {
			 if (e.altKey) {
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
.search_result{padding: 0px 10px;margin-bottom: 25px;max-height: 250px;
.customer_search tr td:nth-child(2)
{
width: 30px;
color: #fff;
text-align: center;
}
.customer_search{ padding-left: 100px; width: 610px;}
overflow-y: scroll;}
.su{
		    color: #008000;
    font-size: 14px;
    font-weight: bold;
    top:44px;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
.container{
	position:absolute;
	width:100%;
	height:100%;
	/*  background:url("../Image/1elearning.jpg") center / cover no-repeat; */ 
	 overflow: hidden;

background: url("../Image/body.png") center center no-repeat;
}

.postpon_mod{
width: 85% !important;
    box-shadow: 5px 5px 5px 5px #FFF;
    margin: 15px auto 0px;
    padding: 15px 1px 0px 0px;
    border: 3px solid #C2C2C2;
    background-color: #FCF7F7;
    border-radius: 4px;
    opacity: 0.7;
    margin-top: 75px;
    
}

.faculty_mod a.dt-pick {
    float: left;
    position: absolute;
    right: 70px;
    top: 47%;
}
.faculty_mod a.dt-pickw {
    float: left;
    position: absolute;
    right: 70px;
    top: 63%;
}
.search_div{
margin-top: 12px;
}
.opt{
width: 265px !important;
padding: 7px !important;
border: 1px solid #C2C2C2;
font-family: regular;
border-radius: 4px;
}
.box1
{
width: 250px!important;
    padding: 7px!important;
    border: 1px solid #c2c2c2;
    border-radius: 4px;
}
.row_head{
    background: none repeat scroll 0% 0% #009AE1;
    color: #FFF;
    font-weight: bold;
    }
    .row_head td{
    padding: 5px;
    }	
.row_head .col_2{float: left; padding: 0px 15px;}
.row_result .col_2{float: left; padding: 0px 15px;}
.result_row_tr td input{border: none; background: none; width: 99.5%; color: #000;}
.starttime{width: 130px;}
.endtime{width: 120px;}
.duration_r{width: 120px;}
.cost_r{width: 80px;}
.result_row_tr td{border: 1px solid #000;padding: 4px;}

.btnscolor{background: #1d87da;
    border: none;
    color: #fff;
    padding: 5px 15px;
    margin-top: 10px;
    margin-right: 10px;
    margin-left: 10px;}
    #errmsg
{
color: red;
}
</style>


</head>
<script type="text/javascript">
function isnumeric(elem,helperMsg) 
{
	var number = /^[0-9]\d*(\.\d+)?$/;
	if( elem.match(number) ) {  
		return true;
	}
	else{
		//alert(helperMsg);
		//elem.focus();
		return false;
	}
}
function validateManageCustomer()
{
	var number = /^[0-9]\d*(\.\d+)?$/;
	var pcode=document.getElementById("pcode").value;
	var phone=document.getElementById("phone").value;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var email=document.getElementById("email").value;
	 var yof=document.getElementById("yof").value;
	// var phone=document.getElementById("phone").value;
	// var ecno=document.getElementById("ecno").value;
	
	 if(pcode.length < 6){
		alert("Enter valid 6 digits postal code");
		document.getElementById("pcode").value="";
		document.getElementById("pcode").focus();
		return false;
	} 
      else if (phone.length < 10) {
		alert("please enter valid 10 digit mobile number");
		document.getElementById("phone").focus();
		return false;
	}
	else if(!(email.match(mailformat))){
		alert("Enter Valid Email ID");
		document.getElementById("email").value="";
		document.getElementById("email").focus();
		return false;
	}else if(yof.length < 4){
		alert("Enter valid 4 digits year");
		document.getElementById("yof").focus();
		return false;
	}
	else {
		document.manageCustomer.action = "/KODE_DEV/ControllerServlet/UpdateCusromerServlet?update=update";
		document.manageCustomer.submit();
	}
}
function clickDelete(){
	document.manageCustomer.action = "/KODE_DEV/ControllerServlet/UpdateCusromerServlet?delete=delete";
	document.manageCustomer.submit();
}
function clickKsId(){
	var org_id=document.getElementById("org_id").value;
	//alert("ksid:"+ksid);
	document.manageCustomer.action="/KODE_DEV/ControllerServlet/ManageCustomerServlet?org_id="+org_id+"";
	document.manageCustomer.submit();
}
</script>

<%
	ArrayList<CreateDomain> crtDomain=new ArrayList<CreateDomain>();
	crtDomain = (ArrayList<CreateDomain>)request.getAttribute("MsgValue");
	////System.out.println("crtDomain="+crtDomain);
%>
<body>
<div class="container">
		<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/owner_menu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="postpon_mod" style="text-align: center;">
		<div>
		<p class="strong">Modify Customer</p>
<form name="manageCustomer" method="post" enctype="multipart/form-data">

<div>
							 <%SearchDao dao= new SearchDao();
					           ArrayList<CreateDomain>al=new ArrayList<CreateDomain>();
							    al=dao.getOrg_id();
							    Iterator<CreateDomain> it= al.iterator();%>
							    <select name="org_id" id="org_id" class="opt" onchange="clickKsId()">
							    <option value="">--select Institute Name--</option>
							    <option>All</option>
							    <% String organizationName="";
							    String OrganizationID="";
							    String idAndName="";
							    while(it.hasNext())
							    {
							    	CreateDomain domain=it.next();
							    	 organizationName=domain.getOrg_name();
							    	 OrganizationID=domain.getOrg_id();
							    	////System.out.println("organization id:"+OrganizationID+ "organizationName:"+organizationName);
							     idAndName=organizationName+" ("+OrganizationID+")";
							 %>
							 <option value="<%= OrganizationID %>"><%=idAndName%></option>
							 <% }%>	
							   </select>
							   </div>
					<div class="search_div" style="text-align: center;">
		<input class="box1" id="search" type="text" placeholder="Search">
		</div>
		<div class="search_result">
		<!-- heading starts here  -->
		<table width="100%" id="tableId">
		 <tr></tr>
			 <tr class="row_head">	 
		<td>Customer ID</td>
		<td>Customer Name</td>
		<td>Customer Type</td>
		<td>Address</td>
		<td>Country</td>
		<td>State</td>
		<td>City</td>
		<td>Postal Code</td>
		<td>Mobile #</td>
		<td>Email ID</td>
		<td>URL</td>
		<td>Logo</td>
		<td>Year Of Foundation</td>
		<td>Action</td>
		</tr>
		</table>
		<%
		
		CreateDomain createDomain=null;//itc.next();
		
		 //CreateDomain createDomain = new CreateDomain();

createDomain = (CreateDomain) session.getAttribute("organizationValues");
		if(crtDomain!=null){
			Iterator<CreateDomain> itc=crtDomain.iterator();
			while(itc.hasNext()){
				//CreateDomain
				createDomain=itc.next();
	%>
<table align="center">
                   
                    <!-- <center>Customer Details</center> --> 
                    <tr>
        				 <td>Customer ID</td><td>:</td>

        				<td><input type="text" name="orgId" id="orgId"
        					value="<%=createDomain.getOrg_id()%>" readonly="readonly"></td>
        			</tr>
                    	
                    	<tr>
        				<td>Customer Name</td><td>:</td>

        				<td><input type="text" name="orgName" id="orgName"
        					value="<%=createDomain.getOrg_name()%>"></td>
        			</tr>
        			<tr>
        				<td>Customer Type</td><td>:</td>

        				<td><input type="text" name="orgType" id="orgType"
        					value="<%=createDomain.getOrg_type()%>"></td>
        			</tr>
        			<tr>
        				<td> <%=createDomain.getOrg_type()%> Name</td><td>:</td>

        				<td><input type="text" name="orgtypename" id="orgtypename"
        					value="<%=createDomain.getOrg_type()%>"></td>
        			</tr>
        			<tr>
        				<td>Address</td><td>:</td>

        				<td><textarea rows="5" cols="25" name="address" id="address"><%=createDomain.getAddress()%>
        					</textarea></td>
        			</tr><tr>
        				
                     <td> Country</td><td>:</td>
				<td>
				 <select class="js_country" style="padding: 5px;margin: 5px 0px; display: none;" id="country" name="country">
 				 <option value="<%=createDomain.getCountry() %>" selected="selected" > </option>
 				 </select>
 				 
				 <select class="db_country" style="padding: 5px;margin: 5px 0px;" id="country" name="country">
 				 <option value="<%=createDomain.getCountry() %>" selected="selected" > <%=createDomain.getCountry()%> </option>
 				 </select>
 <span style="position: absolute;">
<button style="position: absolute;margin-top: 20px;margin-left: -16px;" id="new_btn" type="button" class="eedit"></button>
</span>

				</td></tr>
				<tr>
				<td> State</td><td>:</td>
				<td><select name="state" id="state"><option value="<%=createDomain.getState() %>" selected="selected" > <%=createDomain.getState()%> </option></select></td></tr>
				
				
        				<%-- <td><select name="country" id="country" onclick="populateCountries();">
        					<option selected="selected" value="<%=ad.getCountry() %>"><%=ad.getCountry()%></option></select></td>
        					<td><span>
                               <button id="new_btn" type="button" class="eedit"></button>
                            </span></td> --%>
                            
				
        			
        			<%-- <tr>
        				<td>State</td><td>:</td>

        				<td><input type="text" name="state" id="state"
        					value="<%=ad.getState()%>"></td>
        			</tr> --%>
        			<tr>
        				<td>City</td><td>:</td>

        				<td><input type="text" name="city" id="city" 
        					value="<%=createDomain.getCity()%>"></td>
        			</tr>
                    	<tr>
        				<td>Postal Code</td><td>:</td>

        				<td><input type="text" name="pcode" id="pcode"
        					value="<%=createDomain.getPcode()%>" maxlength="6"></td>
        			</tr><tr>
        				<td>Mobile #</td><td>:</td>

        				<td><input type="text" name="phone" id="phone"
        					value="<%=createDomain.getPhone()%>" maxlength="10">
        					<input type="hidden" name="fax" id="fax" value="<%=createDomain.getFax()%>" maxlength="11">
        					<input type="hidden" name="ecno" id="ecno" value="<%=createDomain.getEcno()%>" maxlength="10">
        					</td>
        					
        			</tr>
        		<!-- <tr>
        				<td>Fax</td><td>:</td>

        				<td><input type="text" name="fax" id="fax"
        					value="<%=createDomain.getFax()%>" maxlength="11"></td>
        			</tr><tr>
        				<td>Emergency Contact #</td><td>:</td>

        				<td><input type="text" name="ecno" id="ecno"
        					value="<%=createDomain.getEcno()%>" maxlength="10"></td>
        			</tr> -->	
        			<tr>
        				<td>Email ID</td><td>:</td>

        				<td><input type="text" name="email" id="email"
        					value="<%=createDomain.getEmail_id()%>"></td>
        			</tr><tr>
        				<td>URL</td><td>:</td>

        				<td><input type="text" name="url" id="url"
        					value="<%=createDomain.getUrl()%>"></td>
        			</tr><tr>
        				<td>Logo</td><td>:</td>
        				<%-- <td><script> $("#blah").load("../JSP/RetriveImage.jsp?org_id=<%=rs.getString(1)%>");readURL1("../JSP/RetriveImage.jsp?org_id=<%=rs.getString(1) %>")</script></td>
						 --%>	<td align="justify" style='width:150px; height:125px;'>
						 <img id="old_img" src="../JSP/RetriveImage.jsp?org_id=<%=createDomain.getOrg_id() %>" style='width:150px; height:105px;'/>
						 </td>
        				
        				 
        			</tr>
        			<tr>
        			<td></td>
        			<td></td>
        				
                        <td><input type='file' name="logo" accept="image/*" onchange="readURL1(this)" />
                       <img id="blah" /></td>
                    
        			</tr>
                    	<tr>
        				<td>Year Of Foundation</td><td>:</td>

        				<td><input type="text" name="yof" id="yof"
        					value="<%=createDomain.getYof()%>" maxlength="4"></td>
        			</tr><%-- <tr>
        				<td>Date And Time</td><td>:</td>

        				<td><input type="text" name="date" id="date"
        					value="<%=rs.getString("date_time")%>" readonly="readonly"></td>
        			</tr> --%>
        			
        			<tr height="15px;"></tr>
        			<%}} %>
        			 </table>
        			 <!-- 
        			 <table>
        			<tr>
        			<td></td>
        			<td></td>
			    	<td>
			    	<input style="width: 90px;  float: left;" class="up_date_btn" type="button" name="update" value="update" onclick="validateManageCustomer()">
        			<!-- <input style="width: 90px;margin-right: 0px !important; " class="del_btn" style="margin-left: 25px;" type="button" name="delete" value="delete" onclick="clickDelete()"> 
        			</tr>
        			<tr>
        			<td></td>
        			<td></td>
        			<td class="back" style="color: #c2c2c2;"><a href="Home.jsp" style="margin-top: 10px;">Back</a></td>
        			</tr>
                    </table>
                     -->
                    </div>
</form>

	</div>
		</div>
		<div>
		<%@include file="FooterViews.jsp" %>
		</div>
</div>
<script src="../JS/intlTelInput.js"></script>
<script>
$("#phone").intlTelInput({
  utilsScript: "../JS/utils.js"
});
</script>
</body>
</html>