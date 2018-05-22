<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>


<%@page import="com.kes.kote.util.*" %>
<%@page import="com.kes.kote.domain.*" %>
<%@page import="java.util.*" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>CINE - Customer Information Environment Setup - Middle Layer</title>
<link type="text/css" href="../css/style.css" rel="stylesheet" />
<link type="text/css" href="../css/responsive.css" rel="stylesheet" />
<link href="../css/font-awesome.css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>


<%

if(session.getAttribute("username")==null)
	response.sendRedirect("../JSP/sessionerror.jsp");

	SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");

		CineOrgDetailsDomain domain=ExcelSheetUtil.getOrgDetails(session);
		String orgName="";if(domain.getOrgName()!=null)orgName=domain.getOrgName();
		String orgLastName="";if(domain.getOrgLastName()!=null)orgLastName=domain.getOrgLastName();
		String address="";if(domain.getAddress()!=null)address=domain.getAddress();
		String city="";if(domain.getCity()!=null)city=domain.getCity();
		String state="";if(domain.getState()!=null)state=domain.getState().trim();
		String country="";if(domain.getCountry()!=null)country=domain.getCountry().trim();
		String YearofEst="";if(domain.getYearofEst()!=null)YearofEst=domain.getYearofEst();
		String LogoName="";if(domain.getLogoName()!=null)LogoName=domain.getLogoName();
		String PinCode="";if(domain.getPinCode()!=null)PinCode=domain.getPinCode();
		String Website="";if(domain.getWebsite()!=null)Website=domain.getWebsite();
		String LogoSize="";if(domain.getLogoSize()!=null)LogoSize=domain.getLogoSize();
		String ksSpace="";if(domain.getKsSpace()!=null)ksSpace=domain.getKsSpace();
		String spaceUOM="";if(domain.getSpaceUOM()!=null)spaceUOM=domain.getSpaceUOM();
		String institute=util.getTeachingSource();if(domain.getTeachingType()!=null)institute=domain.getTeachingType();
		String currencyCode="";if(domain.getCurrencyCode()!=null)currencyCode=domain.getCurrencyCode();
		String dateFormat="";if(domain.getDateFormat()!=null)dateFormat=domain.getDateFormat();
		
		util.setOrgCurrencyCode(currencyCode);
		
		if(Website.trim().length()>0)
		{
			if(!Website.startsWith("http"))
				Website="http://"+Website;
		}
%>

<style type="text/css">
@font-face {
 font-family:'FontAwesome';
 src:url('fonts/fontawesome-webfont.eot?v=4.7.0');
 src:url('fonts/fontawesome-webfont.eot?#iefix&v=4.7.0') format('embedded-opentype'), url('fonts/fontawesome-webfont.woff2?v=4.7.0') format('woff2'), url('fonts/fontawesome-webfont.woff?v=4.7.0') format('woff'), url('fonts/fontawesome-webfont.ttf?v=4.7.0') format('truetype'), url('fonts/fontawesome-webfont.svg?v=4.7.0#fontawesomeregular') format('svg');
 font-weight:normal;
 font-style:normal
}
@font-face {
 font-family: 'robotoregular';
 src: url('fonts/roboto-regular.woff2') format('woff2'), url('fonts/roboto-regular.woff') format('woff');
 font-weight: normal;
 font-style: normal;
}
</style>


<script type="text/javascript">    
   window.history.forward();
   function noBack() { 
       window.history.forward(); 
   }
</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">

<div class="full-progress">
<div class="show-status0 common"><div class="circle1"><strong></strong></div></div>
<div class="show-status1 common"><div class="circle2"><strong></strong></div></div>
<div class="show-status2 common"><div class="circle3"><strong></strong></div></div>
<div class="show-status3 common"><div class="circle4"><strong></strong></div></div>
<div class="show-status4 common"><div class="circle5"><strong></strong></div></div>
<div class="show-status5 common"><div class="circle6"><strong></strong></div></div>
</div>

<header>
  <div class="container">
    <div class="client_logo right"> <a href="#"><img src="../images/logo.png" alt="logo" /></a></div>
  </div>
  <div class="header_banner">
    <div class="container">
      <div class="main_menu">
        <div class="menu_btn"> <a class="menubar" onclick="openNav()"> <i class="fa fa-bars" aria-hidden="true"></i> </a>
          <div id="mySidenav" class="left-nave-menu sidenav"> <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <ul>
              <li><a href="#"><span><i class="fa fa-sliders resize-icon" aria-hidden="true"></i></span>Configuration</a> </li>
              <li><a href="#"> <span><i class="fa fa-user resize-icon" aria-hidden="true"></i></span>Customer</a> 
                
                <!--<ul class="submenu">
                            	<li><a href="#">Setup Customer</a></li>
                            </ul>--> 
              </li>
              <li><a href="#"> <span><i class="fa fa-users resize-icon" aria-hidden="true"></i></span>User Management</a> 
                
                <!--<ul class="submenu">
                            	<li><a href="#">Setup Knowledge Store</a></li>
                            </ul>--> 
              </li>
              <li><a href="#"> <span><i class="fa fa-list-alt resize-icon" aria-hidden="true"></i></span>Setup Curriculum</a> 
                
                <!--<ul class="submenu">
                            	<li><a href="#">Create</a></li>
                            </ul>--> 
              </li>
              <li><a href="#"> <span><i class="fa fa-snowflake-o resize-icon" aria-hidden="true"></i></span>Knowledge Store</a> 
                
                <!--<ul class="submenu">
                            	<li><a href="#">Setup Knowledge Store</a></li>
                            </ul>--> 
              </li>
              <li><a href="#"> <span><i class="fa fa-filter resize-icon" aria-hidden="true"></i></span>Fees Config</a> 
                
                <!--<ul class="submenu">
                            	<li><a href="#">Fees Creation</a></li>
                            	<li><a href="#">Inst. Fees Config</a></li>
                            	<li><a href="#">Coursewise Fees</a></li>
                            	<li><a href="#">Fees Group</a></li>
                            	<li><a href="#">Fees Receipt Template Design</a></li>                                
                            </ul>--> 
              </li>
              <li><a href="#"><span><i class="fa fa-briefcase resize-icon" aria-hidden="true"></i></span>My Account</a> </li>
              <li><a href="../JSP/change-password.jsp"><span><i class="fa fa-unlock-alt resize-icon" aria-hidden="true"></i> </span>Change Password</a> </li>
              <li><a href="#"><span> <i class="fa fa-question-circle resize-icon" aria-hidden="true"></i></span>FAQ</a> </li>
              <li><a href="#"><span><i class="fa fa-info-circle resize-icon" aria-hidden="true"></i></span>Help</a> </li>
              <li><a href="../JSP/logout.jsp"><span><i class="fa fa-sign-out resize-icon" aria-hidden="true"></i></span>Logout</a> </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</header>
<section class="middle-column row">
  <div class="container">
    <div class="cine-setup-info customer-information-setup ">
      <h3><span>CINE - </span>Customer Information Environment Setup - Middle Layer</h3>
      <form class="cine-setup" action="/KOTE/ControllerServlet/CineService?From=OrgDetails" method="post">
      	<div class="cine-fullwidth">
		  <ul>
            <li>
              <input type="text" name="fname" id="fname" placeholder="Institution Name (First Name if Professional)" class="margin20 txtOnly" tabindex="1" value="<%=orgName %>" maxlength="100" />
            </li>
            <li>
              <input type="text" name="lname" id="lname" placeholder="Last Name (Only for Professionals)" class="margin20 txtOnly" tabindex="2" value="<%=orgLastName %>" maxlength="100" />
            </li>
            <li>
              <input type="text" name="yearestd" placeholder="Year Estd." maxlength="4" class="margin20 number-only" tabindex="3" value="<%=YearofEst %>" />
            </li>
            <li>
              <textarea name="address" id="address" placeholder="Address" class="margin20" tabindex="4" ><%=address %></textarea>
            </li>
          </ul>
        </div>
        <div class="cine-form-box-left">
          <ul>
            <li class="dropdown">
              <select id="country" name="country" required tabindex="5">
                <option value="">Select Country</option>
              </select>
              <img src="../images/arrow.png" /> 
            </li>
            <li>
              <input type="text" name="city" placeholder="City" id="" class="margin20" tabindex="7" value="<%=city %>" maxlength="100"/>
            </li>
            <li>
            	<input type="text" placeholder="Website - http://www.google.com" name="website" id="" class="margin20" tabindex="9" value="<%=Website %>" maxlength="100" />
            </li>
            <li>
              <input type="text" placeholder="Logo Name" name="logonm" id="" class="margin20 txtOnly" tabindex="11" value="<%=LogoName %>" maxlength="25" />
            </li>
            <li>
              <input type="text" placeholder="Knowledge Store Space Size" maxlength="2" name="ksspacesize" id="" class="margin20 number-only" tabindex="13" value="<%= ksSpace%>"/>
            </li>
            <li>
              <input type="text" placeholder="Currency Code" name="currencycode" id="" class="margin20 number-only" tabindex="13" value="<%=currencyCode %>"/>
            </li>
            
          </ul>
        </div>
        <div class="cine-form-box-right">
          <ul>
            <li class="dropdown">
              <select name="state" id ="state" required tabindex="6">
                <option value="">Select State </option>
              </select>
              <img src="../images/arrow.png" />
            </li>            
            <li>
              <input type="text" name="Pincode" placeholder="Pincode" id="" class="margin20 number-only" tabindex="8" value="<%=PinCode %>"  maxlength="20"/>
            </li>
            <li>
            	<input type="text" placeholder="Institution Type" name="institute" id="institute" class="margin20 txtOnly" tabindex="10" readonly="readonly" value="<%=institute %>" maxlength="100"/>
            </li>
            <li>
              <input type="text" name="logosize" placeholder="Logo Size" id="" class="margin20 textnumber" tabindex="12" value="<%= LogoSize%>" maxlength="50"/>
            </li>
            <li>
              <input type="text" placeholder="Space UOM TB/GB/MB/KB" maxlength="2" name="spaceuom" id="" class="margin20 txtOnly" tabindex="14" value="<%=spaceUOM %>" />
            </li>
            <li>
              <input type="text" placeholder="Date Format" name="dateft" id="dpdtfrmt" class="margin20 txtOnly" tabindex="14" value="<%=dateFormat %>" />
            </li>
            <div class="btnblock">
              <input type="submit" value="Submit" class="button submit-btn-cine" />
            </div>
          </ul>
        </div>
      </form>
    </div>
  </div>
</section>
<footer>
  <center>
    <div class="container">
      <p>Copyright &copy; 2017. Kompac Education Systems Pvt. Ltd. All Rights Reserved.</p>
    </div>
  </center>
</footer>
<%  String SuccessMsg=""; if(util.getsuccessmsg()!=null) SuccessMsg=util.getsuccessmsg();
String FailureMsg=""; if(util.getfailedmsg()!=null) FailureMsg=util.getfailedmsg();   

	
 if(SuccessMsg.trim().length()>0)
{ %>
	<div class="success-notification"><%=SuccessMsg %></div>
<%	util.resetmsgs();} %>
	
<% if(FailureMsg.trim().length()>0)
{ %>
	<div class="failure-notification"><%=FailureMsg %></div>
<%	util.resetmsgs(); } %>

<!--<div class="success-notification">Success-notification</div>
<div class="failure-notification">Failure-notification</div>-->
<div class="body-overlay"> </div>
</body>

<script src="../js/jquery-1.12.4.js" type="text/javascript"></script>
<script src="../js/jquery-ui.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/countries.js"></script>
<script type="text/javascript" src="../js/custom.js"></script>
<script type="text/javascript">
function openNav() {
    document.getElementById("mySidenav").style.width = "320px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
</script>
<script type="text/javascript">
$(document).ready(function() {
	$(".menubar").click(function(){
		$(".sidenav,.body-overlay ").css("display","block").show();
    });
	$(".closebtn").click(function(){
		$(".body-overlay").hide();
    });
	$(".body-overlay").click(function(){
		 document.getElementById("mySidenav").style.width = "0";
		 $(".body-overlay").hide();
    });
	
	
});

var inst=$('#institute').val();

if(inst=="Professionals")
	$('#lname').rules( "add", {required: true,messages: { required: ""} });

</script>
<script language="javascript" type="text/javascript">
	//populateCountries("country", "state"); // first parameter is id of country drop-down and second parameter is id of state drop-down
	
	populateCountries("country", "state","<%=country%>","<%=state%>");
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="../js/circle-progress.js" type="text/javascript"></script>
<script type="text/javascript">
  $('.circle1').circleProgress({
	value: 1.0,
    size: 90,
    fill: { color: "#DAA520" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(100 * progress) + '<span>% CINE</span>');
  });
</script> 
<style type="text/css">

</style>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript">
$(document).ready(function() {
$(".cine-setup").validate({
rules: {
	fname: {
		required: true,
	},
	address: {
		required: true,
	},
	website:{
		required: true,
		url:true,
	},
	institute:{
		required: true,
	},
	logonm:{
		required: true,
	},
	yearestd:{
		required: true,
	},
	country:{
		required: true,
	},
	state:{
		required: true,
	},
	city:{
		required: true,
	},
	Pincode:{
		required: true,
		minlength:4,
		maxlength:8
	},
	ksspacesize:{
		required: true,
	},
	spaceuom:{
		required: true,
	}
},
messages: {
	fname: {
		required:"",
	},
	yearestd: {
		required:"",
	},
	website: {
		required:"",
	},
	city:{
		required:"",
	},
	country:{
		required:"",
	},
	state:{
		required:"",
	},
	Pincode:{
		required:"",
	},
	address:{
		required:"",
	},
	logonm:{
		required:"",
	},
	ksspacesize:{
		required: "",
	},
	spaceuom:{
		required: "",
	}
	}
	});
});
</script>
</html>