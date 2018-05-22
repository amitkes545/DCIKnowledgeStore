<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.kes.kote.util.*" %>
<%@page import="java.util.*" %>
<%@page import="com.kes.kote.domain.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

<title>CINE - Customer Information Environment Setup - SPOCS</title>

<link type="text/css" href="../css/style.css" rel="stylesheet" />
<link type="text/css" href="../css/responsive.css" rel="stylesheet" />
<link type="text/css" href="../css/font-awesome.css" rel="stylesheet" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/custom.js"></script>
<script type="text/javascript" src="../js/scroll_table.js"></script>
<script type="text/javascript" src="../js/fixed_table_rc.js"></script>


<style type="text/css">
@font-face {
	font-family: 'FontAwesome';
	src: url('fonts/fontawesome-webfont.eot?v=4.7.0');
	src: url('fonts/fontawesome-webfont.eot?#iefix&v=4.7.0') format('embedded-opentype'), url('fonts/fontawesome-webfont.woff2?v=4.7.0') format('woff2'), url('fonts/fontawesome-webfont.woff?v=4.7.0') format('woff'), url('fonts/fontawesome-webfont.ttf?v=4.7.0') format('truetype'), url('fonts/fontawesome-webfont.svg?v=4.7.0#fontawesomeregular') format('svg');
	font-weight: normal;
	font-style: normal
}
@font-face {
	font-family: 'robotomedium';
	src: url('fonts/roboto-medium.woff2') format('woff2'), url('fonts/roboto-medium.woff') format('woff');
	font-weight: normal;
	font-style: normal;
}
</style>

<%
if(session.getAttribute("username")==null)
	response.sendRedirect("../JSP/sessionerror.jsp");

List<CineSpocsDomain> spocDetails=ExcelSheetUtil.getSpocsDetails(session); // result

SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");

%>
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
</div>

<header>
  <div class="container">
    <div class="client_logo right"> <a href="#"><img src="../images/logo.png" alt="logo" /></a> </div>
  </div>
  <div class="header_banner">
    <div class="container">
    	<div class="main_menu">
            	<div class="menu_btn">
                	<a class="menubar1" onclick="openNav()">
                    	<i class="fa fa-bars" aria-hidden="true"></i>
                    </a>
                    <div id="mySidenav" class="left-nave-menu sidenav">
                      <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                      <ul>
         				<li><a href="#"><span><i class="fa fa-sliders resize-icon" aria-hidden="true"></i></span>Configuration</a>

</li>
                        <li><a href="#">
                        <span><i class="fa fa-user resize-icon" aria-hidden="true"></i></span>Customer</a>

                        	<!--<ul class="submenu">
                            	<li><a href="#">Setup Customer</a></li>
                            </ul>-->
                        </li>
                        <li><a href="#">
                        <span><i class="fa fa-users resize-icon" aria-hidden="true"></i></span>User Management</a>

                        	<!--<ul class="submenu">
                            	<li><a href="#">Setup Knowledge Store</a></li>
                            </ul>-->
                        </li>
                        <li><a href="#">
                        <span><i class="fa fa-list-alt resize-icon" aria-hidden="true"></i></span>Setup Curriculum</a>

                        	<!--<ul class="submenu">
                            	<li><a href="#">Create</a></li>
                            </ul>-->
                        </li>
                        <li><a href="#">
                        <span><i class="fa fa-snowflake-o resize-icon" aria-hidden="true"></i></span>Knowledge Store</a>

                        	<!--<ul class="submenu">
                            	<li><a href="#">Setup Knowledge Store</a></li>
                            </ul>-->
                        </li>
                        <li><a href="#">
                        <span><i class="fa fa-filter resize-icon" aria-hidden="true"></i></span>Fees Config</a>

                        	<!--<ul class="submenu">
                            	<li><a href="#">Fees Creation</a></li>
                            	<li><a href="#">Inst. Fees Config</a></li>
                            	<li><a href="#">Coursewise Fees</a></li>
                            	<li><a href="#">Fees Group</a></li>
                            	<li><a href="#">Fees Receipt Template Design</a></li>                                
                            </ul>-->
                        </li>
                        
                        <li><a href="#"><span><i class="fa fa-briefcase resize-icon" aria-hidden="true"></i></span>My Account</a>
</li>
						<li><a href="../JSP/change-password.jsp"><span><i class="fa fa-unlock-alt resize-icon" aria-hidden="true"></i>
</span>Change Password</a>
</li> 
						<li><a href="#"><span> <i class="fa fa-question-circle resize-icon" aria-hidden="true"></i></span>FAQ</a>
</li>             
						<li><a href="#"><span><i class="fa fa-info-circle resize-icon" aria-hidden="true"></i></span>Help</a>
</li> 
						<li><a href="../JSP/logout.jsp"><span><i class="fa fa-sign-out resize-icon" aria-hidden="true"></i></span>Logout</a>
</li>
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
      <h3><span>CINE - </span>Customer Information Environment Setup - SPOCS</h3>
    </div>
    <form class="cine-setup" action="/KOTE/ControllerServlet/CineService?From=SpocsDetails" method="post">
    <div class="dwrapper">
      <table id="fixed_hdr2">
        <thead>
          <tr>
            <th class="header-cell col1">Department Name</th>
            <th class="header-cell col2">SPOCS Name</th>
            <th class="header-cell col3">Designation</th>
            <th class="header-cell col4">Mobile Number</th>
            <th class="header-cell col5">Land Line Number</th>
            <th class="header-cell col6">Extension Number</th>
            <th class="header-cell col7">Fax Number</th>
            <th class="header-cell col8">E-Mail Address</th>
            <th class="header-cell col9"><input type="checkbox" name="chkbox" id="select_all" /></th>
          </tr>
        </thead>
        <tbody>
        <% if(spocDetails!=null && spocDetails.size()>0)
        	for(int i=0;i<spocDetails.size();i++)
        	{
        		CineSpocsDomain domain=spocDetails.get(i);
        		
        		String DeptName="";if(domain.getDeptName()!=null)DeptName=domain.getDeptName();
				String SPOCName="";if(domain.getSPOCName()!=null)SPOCName=domain.getSPOCName();
				String Disgn="";if(domain.getDisgn()!=null)Disgn=domain.getDisgn();
				String MobNo="";if(domain.getMobNo()!=null)MobNo=domain.getMobNo();
				String LLNo="";if(domain.getLLNo()!=null)LLNo=domain.getLLNo();
				String ExtNo="";if(domain.getExtNo()!=null) ExtNo=domain.getExtNo();
				String FaxNo="";if(domain.getFaxNo()!=null)FaxNo=domain.getFaxNo();
				String Email="";if(domain.getEmail()!=null)Email=domain.getEmail();
	
				boolean odd=true;
        		int cnt=i+1;
        		int even=cnt%2;
        		if(even==0)
        			odd=false;
        		else
        			odd=true;
        		if(odd) {%>
    			<tr class="light-bg">
    		<%	} else  { %>
    			<tr class="dark-bg">
    		<% } %> 
        			<td class="body-cell col1"><input type="text" name="deptnm<%=i%>" class="txtOnly" placeholder="Enter Department Name" id="deptnm<%=i%>" value="<%= DeptName%>" maxlength="50" /></td>
            		<td class="body-cell col2"><input type="text" name="spocnm<%=i%>" class="txtOnly" placeholder="Enter SPOCS Name" id="spocnm<%=i%>"  value="<%= SPOCName%>" maxlength="100" /></td>
           	 		<td class="body-cell col3"><input type="text" name="desig<%=i%>" class="txtOnly" placeholder="Enter Designation" id="desig<%=i%>"  value="<%= Disgn%>" maxlength="100" /></td>
            		<td class="body-cell col4"><input type="text" name="mobnumbr<%=i%>" class="number-only" placeholder="Enter Mobile Number" id="mobnumbr<%=i%>" class="number-only" maxlength="10"  value="<%= MobNo%>" /></td>
            		<td class="body-cell col5"><input type="text" name="llnumbr<%=i%>" class="number-only" placeholder="Enter Land Line Number" id="llnumbr<%=i%>" class="number-only" maxlength="13" value="<%= LLNo%>" /></td>
            		<td class="body-cell col6"><input type="text" name="extnumbr<%=i%>" class="number-only" placeholder="Enter Extension Number" id="extnumbr<%=i%>" class="number-only" maxlength="3" value="<%= ExtNo%>" /></td>
            		<td class="body-cell col7"><input type="text" name="faxnumbr<%=i%>" class="number-only" placeholder="Enter Fax Number" id="faxnumbr<%=i%>" class="number-only" maxlength="10" value="<%= FaxNo%>" /></td>
            		<td class="body-cell col8"><input type="email" name="emladdr<%=i%>" placeholder="Enter E-Mail Address" class="email_valid"  id="emladdr<%=i%>" value="<%= Email%>" maxlength="50" /></td>
            		<td class="body-cell col9"><input type="checkbox" name="check1<%=i%>" class="children_checkbox" id="check1<%=i%>" /></td>
        	</tr>
        <%	}
        	%>
          
        </tbody>
      </table>
      <div class="btnblock margtop20">
        <input type="submit" value="Submit" class="button" onclick="skipFunction();"/>
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
</body>
<script type="text/javascript">
function skipFunction() {
 disableScreen();
 document.getElementById('spinner').style.visibility = 'visible';
}

function disableScreen() {
    var div= document.createElement("div");
    div.className += "overlay";
    document.body.appendChild(div);
}
</script>

<script type="text/javascript">
	$(function () {
		$('#fixed_hdr1').fxdHdrCol({
			fixedCols:0,
			width:     '100%',
			height:    400,
			colModal: [
			]					
		});		
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 400,
			colModal: [
			{ width: 25, align: 'left' },
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
	});
	$(document).on('click', '#select_all', function(){
		if($(this).is(':checked')){
			$('.children_checkbox').prop('checked', true);
		} else {
			$('.children_checkbox').prop('checked', false);
		}
	});	
</script>

<script>
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
	value: 0.5,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(50 * progress) + '<span>% CINE</span>');
});
</script> 

<script>
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