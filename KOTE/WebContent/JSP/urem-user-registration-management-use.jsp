<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.kes.kote.util.*" %>
<%@page import="com.kes.kote.dao.*" %>
<%@page import="com.kes.kote.interfaces.*" %>
<%@page import="java.util.*" %>
<%@page import="com.kes.kote.domain.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>URem - User Registration Management (USE)</title>

<link type="text/css" href="../css/font-awesome.css" rel="stylesheet" />
<link type="text/css" href="../css/font-awesome.min.css" rel="stylesheet" />
<link type="text/css" href="../css/style.css" rel="stylesheet" />
<link type="text/css" href="../css/responsive.css" rel="stylesheet" />
<link type="text/css" href="../css/jquery-ui.css" rel="stylesheet" />

<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/custom.js"></script>
<script type="text/javascript" src="../js/scroll_table.js"></script>
<script type="text/javascript" src="../js/fixed_table_rc.js"></script>


<style>
@font-face {
	font-family: 'FontAwesome';
	src: url('../JSP/fonts/fontawesome-webfont.eot?v=4.7.0');
	src: url('../JSP/fonts/fontawesome-webfont.eot?#iefix&v=4.7.0') format('embedded-opentype'), url('../JSP/fonts/fontawesome-webfont.woff2?v=4.7.0') format('woff2'), url('../JSP/fonts/fontawesome-webfont.woff?v=4.7.0') format('woff'), url('../JSP/fonts/fontawesome-webfont.ttf?v=4.7.0') format('truetype'), url('../JSP/fonts/fontawesome-webfont.svg?v=4.7.0#fontawesomeregular') format('svg');
	font-weight: normal;
	font-style: normal
}
</style>


<%

if(session.getAttribute("username")==null)
	response.sendRedirect("../JSP/sessionerror.jsp");

List<UseUserDomain> paramDetails=ExcelSheetUtil.getUseUsers(session); 

LookupInterface lookup=new LookupDao();
List<MailserverDomain> MailserverDetails=lookup.getMailserverDetails(session);

SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
%>
<script type="text/javascript">    
   window.history.forward();
   function noBack() { 
       window.history.forward(); 
   }
</script>
</head>
<body  onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">

<div class="show-status0 common"><div class="circle1"><strong></strong></div></div>
<div class="show-status1 common"><div class="circle2"><strong></strong></div></div>

<header>
  <div class="container">
    <div class="client_logo right"> <a href="#"><img src="../images/logo.png" alt="logo" /></a> </div>
  </div>
  <div class="header_banner">
    <div class="container">
      <div class="main_menu">
        <div class="menu_btn"> <a class="menubar1" onclick="openNav()"> <i class="fa fa-bars" aria-hidden="true"></i> </a>
          <div id="mySidenav" class="left-nave-menu sidenav"> <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <ul>
              <li><a href="#"><span><i class="fa fa-sliders resize-icon" aria-hidden="true"></i></span>Configuration</a> </li>
              <li><a href="#"> <span><i class="fa fa-user resize-icon" aria-hidden="true"></i></span>Customer</a> </li>
              <li><a href="#"> <span><i class="fa fa-users resize-icon" aria-hidden="true"></i></span>User Management</a> </li>
              <li><a href="#"> <span><i class="fa fa-list-alt resize-icon" aria-hidden="true"></i></span>Setup Curriculum</a> </li>
              <li><a href="#"> <span><i class="fa fa-snowflake-o resize-icon" aria-hidden="true"></i></span>Knowledge Store</a> </li>
              <li><a href="#"> <span><i class="fa fa-filter resize-icon" aria-hidden="true"></i></span>Fees Config</a> </li>
              <li><a href="../JSP/change-password.jsp"><span><i class="fa fa-briefcase resize-icon" aria-hidden="true"></i></span>My Account</a> </li>
              <li><a href="#"><span><i class="fa fa-unlock-alt resize-icon" aria-hidden="true"></i> </span>Change Password</a> </li>
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
<section class="middle-column">
  <div class="container">
    <div class="page-title">
      <h3> URem - User Registration Management <span>(USE)</span></h3>
    </div>
    <form class="paramForm" id="paramForm" action="/KOTE/ControllerServlet/UseService?From=usersReg" method="post">
    <div class="dwrapper">
      <table id="fixed_hdr2">
        <thead>
          <tr>
            <th class="header-cell col1">User ID</th>
            <th class="header-cell col1">User First Name</th>
            <th class="header-cell col1">User Last Name</th>
            <th class="header-cell col1">Designation</th>
            <th class="header-cell col1">Department</th>
            <th class="header-cell col1">Address</th>
            <th class="header-cell col1">City</th>
            <th class="header-cell col1">State</th>
            <th class="header-cell col1">Country</th>
            <th class="header-cell col1">Postal Code</th>
            <th class="header-cell col1">Date of Birth</th>
            <th class="header-cell col1">Gender</th>
            <th class="header-cell col1">Mobile Number</th>
            <th class="header-cell col1">Land Line Number</th>
            <th class="header-cell col1">Extension No.</th>
            <th class="header-cell col1">E-Mail Address</th>
            <th class="header-cell col1">Mail Server ID</th>
            <th class="header-cell col1">Space Allotment</th>
            <th class="header-cell col1">Space UOM</th>
            <th class="header-cell col1"><input name="chkbox" id="select_all" type="checkbox" /></th>
          </tr>
        </thead>
        <tbody>
        
        <%if(paramDetails!=null && paramDetails.size()>0){
        	for(int i=0;i<paramDetails.size();i++) {
        		UseUserDomain useParam = paramDetails.get(i);
        		String userID="";if(useParam.getUserId()!=null)userID=useParam.getUserId();
        		String userFName="";if(useParam.getFirstName()!=null)userFName=useParam.getFirstName();
        		String userLName="";if(useParam.getLastName()!=null)userLName=useParam.getLastName();
        		String design="";if(useParam.getDesignation()!=null)design=useParam.getDesignation();
        		String dept="";if(useParam.getDepartment()!=null)dept=useParam.getDepartment();
        		String address="";if(useParam.getAddress()!=null)address=useParam.getAddress();
        		String city="";if(useParam.getCity()!=null)city=useParam.getCity();
        		String state="";if(useParam.getState()!=null)state=useParam.getState();
        		String country="";if(useParam.getCountry()!=null)country=useParam.getCountry();
        		String postal="";if(useParam.getPostal()!=null)postal=useParam.getPostal();
        		String dob="";if(useParam.getDob()!=null)dob=useParam.getDob();
        		String gender="";if(useParam.getGender()!=null)gender=useParam.getGender();
        		String mob="";if(useParam.getMobile()!=null)mob=useParam.getMobile();
        		String llno="";if(useParam.getLandline()!=null)llno=useParam.getLandline();
        		String ext="";if(useParam.getExtension()!=null)ext=useParam.getExtension();
        		String email="";if(useParam.getEmail()!=null)email=useParam.getEmail();
        		String mailserv="";if(useParam.getMailServer()!=null)mailserv=useParam.getMailServer();
        		String space="";if(useParam.getSpace()!=null)space=useParam.getSpace();
        		String spaceuom="";if(useParam.getUom()!=null)spaceuom=useParam.getUom();
        		spaceuom=spaceuom.trim().toUpperCase();
        		boolean odd=true;
        		int cnt=i+1;
        		int even=(cnt%2);
        		if(even==0)
        			odd=false;
        		else
        			odd=true;
        		if(odd) {%>
    			<tr class="light-bg">
    		<%	} else  { %>
    			<tr class="dark-bg">
    		<% } %> 

            <td class="body-cell col1"><input type="text" class="txtOnly"  name="userid<%=i%>" id="userid<%=i%>" placeholder="Enter User ID" value="<%=userID%>"  maxlength="30" /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="userfname<%=i%>" id="userfname<%=i%>" placeholder="Enter User First Name" value="<%=userFName%>"  maxlength="30" /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="userlname<%=i%>" id="userlname<%=i%>" placeholder="Enter User Last Name" value="<%=userLName%>"  maxlength="30" /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="designation<%=i%>" id="designation<%=i%>" placeholder="Enter Designation" value="<%=design%>"  maxlength="50" /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="department<%=i%>" id="department<%=i%>" placeholder="Enter Department" value="<%=dept%>"  maxlength="50" /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="address<%=i%>" id="address<%=i%>" placeholder="Enter Address" value="<%=address%>"/></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="city<%=i%>" id="city<%=i%>" placeholder="Enter City" value="<%=city%>"  maxlength="50" /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="state<%=i%>" id="state<%=i%>" placeholder="Enter State" value="<%=state%>"  maxlength="50" /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="country<%=i%>" id="country<%=i%>" placeholder="Enter Country" value="<%=country%>"  maxlength="50" /></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="postalcode<%=i%>" id="postalcode<%=i%>" placeholder="Enter Postal Code" value="<%=postal%>"  maxlength="20" /></td>
            <td class="body-cell col1"><input type="text" class="dob-only" name="dateofbirth<%=i%>" id="dateofbirth<%=i%>" placeholder="Enter Date of Birth" value="<%=dob%>"  maxlength="10" /></td>
            <td class="body-cell col1">
            <select name="gender<%=i%>">
                <option value="">Choose Gender*</option>
                <option value="Male"  <% if(gender.trim().equalsIgnoreCase("Male")) { %> selected <%} %> >Male</option>
                <option value="Female"  <% if(gender.trim().equalsIgnoreCase("Female")) { %> selected <%} %>  >Female</option>
                <option value="Others"  <% if(gender.trim().equalsIgnoreCase("Others")) { %> selected <%} %>  >Others</option>
              </select></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="mobilenumber<%=i%>" id="mobilenumber<%=i%>" placeholder="Enter Mobile Number" value="<%=mob%>"/></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="landlinenumber<%=i%>" id="landlinenumber<%=i%>" placeholder="Enter Land Line Number" value="<%=llno%>" /></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="extension<%=i%>" id="extension<%=i%>" placeholder="Enter Extension No" value="<%=ext%>" /></td>
            <td class="body-cell col1"><input type="text" class="email_valid" name="email<%=i%>" id="email<%=i%>" placeholder="Enter E-Mail Address" value="<%=email%>" /></td>
            <td class="body-cell col1"><select name="mailserverid<%=i %>" id="userid" >
                	<option value="">Select Mail Server ID</option>
                	<% if(MailserverDetails!=null && MailserverDetails.size()>0)
                	{
                		for(int j=0;j<MailserverDetails.size();j++)
                		{
                			MailserverDomain mserver=MailserverDetails.get(j);
                			
                			String id=mserver.getServerId();
                			String name=mserver.getServerName();
                		%>	<option value="<%=id %>"  <% if(mailserv.trim().equalsIgnoreCase(name)) { %> selected <%} %>  ><%=name %></option> <%		
                		}
                	}%>
                    
                   
                </select></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="space<%=i%>" id="space<%=i%>" placeholder="Enter Space" maxlength="2" value="<%=space%>" /></td>
            <td class="body-cell col1">
            <input type="text" class="textonly" name="spaceuom<%=i%>" id="spaceuom<%=i%>" placeholder="Enter Space UOM in GB/MB/KB" maxlength="2" value="<%=spaceuom%>" /></td>
            <td class="body-cell col9" style="text-align: center;"><input name="check1" class="children_checkbox" type="checkbox"></td>

        	<% }%>
        <%}%>
        
          
        </tbody>
      </table>
      <div class="btnblock margtop20">
        <input type="submit" value="Submit" class="button"/>
      </div>
    </div>
    </form>
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
<div class="body-overlay"></div>
</body>

<script type="text/javascript">
	$(function () {
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 400,
			colModal: [
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 10, align: 'left' },
			{ width: 5, align: 'center' }
			],
			sort: false
		});
		
		$(".dob-only").datepicker({maxDate: '+0d' , dateFormat: 'dd/mm/yy',changeMonth: true,changeYear: true,showButtonPanel: true,yearRange: '-70:-15' });
		
	});
	
	
	$(document).on('click', '#select_all', function(){
		if($(this).is(':checked')){
			$('.children_checkbox').prop('checked', true);
		} else {
			$('.children_checkbox').prop('checked', false);
		}
	});	
</script>

<script type="text/javascript">
function openNav() {
    document.getElementById("mySidenav").style.width = "320px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
</script>
<script src="../js/circle-progress.js"></script>
<script type="text/javascript">
  $('.circle1').circleProgress({
	value: 1.0,
    size: 90,
    fill: { color: "#DAA520" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(100 * progress) + '<span>% CINE</span>');
  });
  $('.circle2').circleProgress({
	value: 0.28,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.floor(28 * progress) + '<span>% USE</span>');
  });
</script>
<script type="text/javascript">
$(document).ready(function() {
	$(".menubar1").click(function(){
		$(".sidenav,.body-overlay").css("display","block").show();
    });
	$(".closebtn").click(function(){
		$(".body-overlay").hide();
    });
	$(".body-overlay").click(function(){
		 document.getElementById("mySidenav").style.width = "0";
		 $(".body-overlay").hide();
    })
});
</script>

</html>