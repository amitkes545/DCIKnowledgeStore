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
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<!--  for country code select css -->
<link rel="stylesheet" href="../CSS/intlTelInput.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script type="text/javascript" src="../JS/Validatesetupcustomer.js"></script> 
<script src="../JS/jquery-ui.min.js"></script>
<script src="../JS/jquery.js"></script>
<script type="text/javascript" src="../JS/PreViewImage.js"></script>
<script src="../JS/country.js" type="text/javascript"></script>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!--  for country code select css -->
<link rel="stylesheet" href="../CSS/intlTelInput.css">
<script src="../JS/country.js" type="text/javascript"></script>

<script type="text/javascript" src="../JS/FacilitatorKnowSetup.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.js"></script>

<script src="../JS/datetimepicker_css.js"></script>
<script type="text/javascript" src="../JS/validatePostSession.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>
<link href="../CSS/jquery.datetimepicker.css" rel="stylesheet"></link>



<link href="../CSS/owner.css" rel="stylesheet"></link>
		<link href="../CSS/global.css" rel="stylesheet"></link>
		<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
		<script>
		
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
<script type="text/javascript">
	$(document).ready(function()
	{
		$('#search').keyup(function()
		{
			searchTable($(this).val());
		});
	});
	function searchTable(inputVal)
	{
		var table = $('#tableId');
		table.find('tr').each(function(index, row)
		{
			var allCells = $(row).find("td");
			if(allCells.length > 0)
			{
				var found = false;
				allCells.each(function(index, td)
				{
					var regExp = new RegExp(inputVal, 'i');
					if(regExp.test($(td).text()))
					{
						found = true;
						return false;
					}
				});
				if(found == true)$(row).show();else $(row).hide();
			}
		});
	}
</script>
<script>
$(document).ready(function() {
	$('.btnscolor').hide();
	
	$('.row input').each(function()
	{
	$(this).attr("disabled",true);
	
	});
	$(".row a input").attr("disabled",false);

$(".row a input").click(function() {
		var id = $(this).attr("id");
		if($('#' + id).is(":checked"))
		{
		$("." + id).attr("disabled",false);
		$('.btnscolor').show();
		}
	else{
		$("." + id).attr("disabled",true);	
		$('.btnscolor').hide();
	}
		
	});
	});
</script>

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
	//alert("org_id:"+org_id);
	document.manageCustomer.action="/KODE_DEV/ControllerServlet/ManageCustomerServlet?org_id="+org_id+"";
	document.manageCustomer.submit();
}
function showImage()
{
	var org_id=document.getElementById("org_id").value;
	document.manageCustomer.action="../JSP/RetriveImage.jsp?org_id="+org_id;
	document.manageCustomer.submit();
}
</script>

<%
	ArrayList<CreateDomain> crtDomain=new ArrayList<CreateDomain>();
	crtDomain = (ArrayList<CreateDomain>)request.getAttribute("msgvalue");
	//System.out.println("crtDomain="+crtDomain);
	
	
	CreateDomain createDomain=null;//itc.next();
	createDomain = (CreateDomain) session.getAttribute("organizationValues");
	//System.out.println("createDomain ID in jsp:"+createDomain);
	String select=request.getParameter("select");
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
							    <option value="<%=createDomain.getOrg_id()%>"><%=createDomain.getOrg_name()+" ("+createDomain.getOrg_id()+")"%></option>
							    <option>All</option>
							    <% String organizationName="";
							    String OrganizationID="";
							    String idAndName="";
							    ////System.out.println("organizationName="+organizationName);
							    while(it.hasNext())
							    {
							    	CreateDomain domain=it.next();
							    	 organizationName=domain.getOrg_name();
							    	 OrganizationID=domain.getOrg_id();
							    	////System.out.println("organization id:"+OrganizationID+ "organizationName:"+organizationName);
							     idAndName=organizationName+" ("+OrganizationID+")";
							    // //System.out.println("idAndName="+idAndName);
							 %>
							 <option value="<%=OrganizationID %>"><%=idAndName%></option>
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
		<td><%=createDomain.getOrg_type()%> Name</td>
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
		
                    <!-- <center>Customer Details</center> --> 
                    <%
                    if(crtDomain!=null){
			Iterator<CreateDomain> it1=crtDomain.iterator();
			while(it1.hasNext()){
				CreateDomain reportDomain=it1.next();
				////System.out.println("cntry="+reportDomain.getCountry());
		%>
                    <tr class="result_row_tr row">
        				<td><input type="text" name="orgId" id="orgId" value="<%=reportDomain.getOrg_id()%>" readonly="readonly"></td>
        				<td><input type="text" name="orgName" id="orgName" value="<%=reportDomain.getOrg_name()%>" class="<%=reportDomain.getOrg_id()%>"></td>
        				<td>
        				<select name="orgtype" id="orgtype" onchange="changeName()">
					          <OPTION value="<%=reportDomain.getOrg_type()%>" selected><%=reportDomain.getOrg_type()%></OPTION>
					          <!-- <option value="Client">client</option>
                             <option value="SuperAdmin">SuperAdmin</option> -->
                          <option value="University">University</option>
                         <option value="Institute">Institute</option>
                         <option value="College">College</option>
                         
                    </select></td>
        				<td><input type="text" name="orgtypename" id="orgtypename" value="<%=reportDomain.getOrg_Type_Name()%>" class="<%=reportDomain.getOrg_id()%>"></td>
        				<td><textarea rows="5" cols="25" name="address" id="address"><%=reportDomain.getAddress()%>
        					</textarea></td>
				 
 				
				<td><select name="country" id="country"><option value="<%=reportDomain.getCountry() %>" selected="selected" > <%=reportDomain.getCountry()%> </option>
				</select></td>
				<td><select name="state" id="state"><option value="<%=reportDomain.getState() %>" selected="selected" > <%=reportDomain.getState()%> </option>
				
				</select>
				<script language="javascript">
					populateCountries("country", "state");
					populateCountries("country2");
				</script></td>
				
        				<td><input type="text" name="city" id="city" value="<%=reportDomain.getCity()%>" class="<%=reportDomain.getOrg_id()%>"></td>
        				<td><input type="text" name="pcode" id="pcode" value="<%=reportDomain.getPcode()%>" maxlength="6" class="<%=reportDomain.getOrg_id()%>"></td>
        				<td><input type="text" name="phone" id="phone" value="<%=reportDomain.getPhone()%>" maxlength="10" class="<%=reportDomain.getOrg_id()%>">
        					<input type="hidden" name="fax" id="fax" value="<%=reportDomain.getFax()%>" maxlength="11">
        					<input type="hidden" name="ecno" id="ecno" value="<%=reportDomain.getEcno()%>" maxlength="10">
        					</td>
        				<td><input type="text" name="email" id="email" value="<%=reportDomain.getEmail_id()%>" class="<%=reportDomain.getOrg_id()%>"></td>
        				<td><input type="text" name="url" id="url" value="<%=reportDomain.getUrl()%>" class="<%=reportDomain.getOrg_id()%>"></td>
        				<td><a href="#" onclick="showImage()" id="old_img">Show/Change image</a></td>
        			<!-- 	<td align="justify" style='width:150px; height:125px;'>
						 <img id="old_img" src="../JSP/RetriveImage.jsp?org_id=<%=reportDomain.getOrg_id() %>" style='width:150px; height:105px;'/>
                        <input type='file' name="logo" accept="image/*" onchange="readURL1(this)" />
                       <img id="blah" /></td>
                     -->
        				<td><input type="text" name="yof" id="yof" class="<%=reportDomain.getOrg_id()%>" value="<%=reportDomain.getYof()%>" maxlength="4"></td>
        				<td>
        				<a href="#">
				<input id="<%=reportDomain.getOrg_id()%>" type="checkbox" name="checkboxGroup" value="<%=reportDomain.getOrg_id()%>"/>
				</a>
				</td>
        			</tr>
        			<tr height="15px;"></tr>
        			<%}}%>
        			 </table>
			    	<a>
			    	<input class="btnscolor" type="button" value="Update" onclick="validateManageCustomer()">
        			<input class="btnscolor"  type="button" value="Delete" onclick="clickDelete()">
        			</a>
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