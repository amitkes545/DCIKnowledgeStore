<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.kds.KODE_DEV.dao.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="../JS/FacilitatorKnowPublish.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>
<!-- <link href="../CSS/owner.css" rel="stylesheet"/>
<link href="../CSS/global.css" rel="stylesheet"/> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>  -->
 <script src="../JS/jquery-ui.min_1.8.js"></script>
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
<!-- <script>
	function mfunction1() {
		if (valid()) {
			var block3 = document.getElementById("unblock_btn").value;
			var block4 = 'UnBlock';
			if (block3 == block4) {
				//alert("hiu");
				document.AcAccessControll.action = "/KODE_DEV/ControllerServlet/FacilitatorAccessControlServlet?status=Active"
				document.AcAccessControll.submit();

			}

			else {
				return false;
			}
		}
	}

	function myFunction() {

		if (valid()) {
			var block = document.getElementById("block").value;
			var block1 = 'Block';

			if (block == block1) {

				document.AcAccessControll.action = "/KODE_DEV/ControllerServlet/FacilitatorAccessControlServlet?status=InActive"
				document.AcAccessControll.submit();
			} else {

				return false;
			}

		}

	}
</script> -->
<script>

	 function myFunction() 
	{
		//alert("hi");
		if(validF1())
		{	
		var block =document.getElementById("block").value;
		//var block1='Block';
		//alert(block);
			if( block=='InActive')
			{
			//alert(block);
			document.AcAccessControll.action ="/KODE_DEV/ControllerServlet/FacilitatorAccessControlServlet?status=InActive"
			document.AcAccessControll.submit();
			//document.getElementById(block).style.display = 'none';
			}
			else if(block=='Active'){
				//alert(block);
			document.AcAccessControll.action ="/KODE_DEV/ControllerServlet/FacilitatorAccessControlServlet?status=Active"
			document.AcAccessControll.submit();
			//	document.getElementById(block).style.display = 'none';
		}
		else{	
			//alert("hello");
		return false;
		}

		}

	}
	var request;
	function getID()
	{
	var v=document.AcAccessControll.ksid.value;
	var url="CommonStatusKnowStore.jsp?val="+v;

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
	 /* function getInfo(){
	 if(request.readyState==4){
	 var val=request.responseText;
	 document.getElementById('amit').innerHTML=val;
	}
	} */
	
	function getInfo(){
		if(request.readyState==4){
		var val=request.responseText;
		if(val.search('InActive')>0){
			//alert(val);
			document.getElementById('block').value='Active';
		}
		else{
			//alert(val);
			document.getElementById('block').value='InActive';
		}
		document.getElementById('amit').innerHTML=val;
		}
		}
</script>
</head>

<%
String username = (String)session.getAttribute("username");
String userid = (String)session.getAttribute("userid");
String orgid = (String)session.getAttribute("orgid");
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
		<p class="strong">Modify Knowledge Store</p>
			<form name="AcAccessControll" method="post">
				<%-- <%!FacilitatorKnowPublishDomain oDomain=new FacilitatorKnowPublishDomain();%> --%>
				<%
						if (session.getAttribute("FacultySuccess") != null) {
					%>
					<p class="fadehide" style="color: green; text-align: center; margin-left: 54px;">
						<%=session.getAttribute("FacultySuccess")%>
					</p>
					<%
						} else if(session.getAttribute("FacultyFailure") != null) {
					%>
					<p class="fadehide" style="color: green; text-align: center; margin-left: 54px;">
						<%=session.getAttribute("FacultyFailure")%>
					</p>
					<%
						}
					%>
						<div>
						<select name="ksid" id="ksid" class="opt" onchange="getID()">

								<option value="default">Select Knowledge Store ID</option>
								<%
									FacilitatorSelectKsidKStoreDao dobj2 = new FacilitatorSelectKsidKStoreDao();

									ArrayList<String> al = dobj2.fetchValue(orgid,userid);

									Iterator<String> itr = al.iterator();

									while (itr.hasNext()) {
										String ksId = itr.next();
										String userId = itr.next();
										String ksIdAndUserId = (ksId+" ("+userId+")"); 
								%>


								<option value="<%=ksId%>"><%=ksIdAndUserId%></option>

								<%
									}
								%>
						</select>
						</div>
						<div class="search_div" style="text-align: center;">
		<input class="box1" id="search" type="text" placeholder="Search">
		</div>
		<div class="search_result">
						<span id="amit"></span>
						
						<div><input class="add_btn1"
							style="width: 95px!important;margin-right: 59px;margin-top: 10px" type="button" id="block"
							value="Block" onclick="myFunction()" /> <!-- <input class="add_btn1"
							style="width: 95px!important; margin-top: 10px" id="unblock_btn" type="button" id="unblock"
							value="UnBlock" onclick="mfunction1()" /></td> -->
</div>
</div>
			</form>

	</div>
		</div>
		<div>
		<%@include file="FooterViews.jsp" %>
		</div>
</div>
</body>
</html>