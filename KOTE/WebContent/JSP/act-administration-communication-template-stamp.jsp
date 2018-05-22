<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.kes.kote.util.*" %>
<%@page import="com.kes.kote.domain.*" %>
<%@page import="com.kes.kote.dao.*" %>
<%@page import="com.kes.kote.interfaces.*" %>
<%@page import="java.util.*" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>ACT - Administration Communication Template (STAMP)</title>
<link type="text/css" href="../css/font-awesome.css" rel="stylesheet" />
<link type="text/css" href="../css/font-awesome.min.css" rel="stylesheet" />
<link type="text/css" href="../css/style.css" rel="stylesheet" />
<link type="text/css" href="../css/responsive.css" rel="stylesheet" />
<script src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/custom.js"></script>
<script type="text/javascript" src="../js/scroll_table.js"></script>
<script type="text/javascript" src="../js/fixed_table_rc.js"></script>


<%
if(session.getAttribute("username")==null)
	response.sendRedirect("../JSP/sessionerror.jsp");

List<StampCMTDomain> CMTDetails=ExcelSheetUtil.getCMTDetails(session);
LookupInterface lookup=new LookupDao();
List<UsersInfoDomain> AllUsersList=lookup.getAllUsersList(session);
HashMap<String, String> UidMapping=lookup.getUidMapping(session);

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
<div class="show-status0 common"><div class="circle1"><strong></strong></div></div>
<div class="show-status1 common"><div class="circle2"><strong></strong></div></div>
<div class="show-status2 common"><div class="circle3"><strong></strong></div></div>

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
<section class="middle-column">
<form class="cine-setup" action="/KOTE/ControllerServlet/StampService?From=CMT" method="post">
  <div class="container">
    <div class="page-title">
      <h3>ACT - Administration Communication Template <span>(STAMP)</span></h3>
    </div>
    <div class="dwrapper">
      <table id="fixed_hdr2">
        <thead>
          <tr>
            <th class="header-cell col1">Communication Type</th>
            <th class="header-cell col1">Subject</th>
            <th class="header-cell col1">Header</th>
            <th class="header-cell col1">Body</th>
            <th class="header-cell col1">Footer</th>
            <th class="header-cell col1">UID of Sender</th>
            <th class="header-cell col1">Key Words for Reference</th>
          </tr>
        </thead>
        <tbody>
        <% 
        if(CMTDetails!=null && CMTDetails.size()>0)
        {
        	util.print(""+CMTDetails.size());
        	
        	for(int i=0;i<CMTDetails.size();i++)
        	{
        		StampCMTDomain domain =CMTDetails.get(i);
        		
        		String CommType="CommType"+i;
				String Subject="Subject"+i;
				String Header="Header"+i;
				String Body="Body"+i;
				String Footer="Footer"+i;
				String UIDofSender="UID"+i;
				String KeyWords="KeyWords"+i;
				
				String CommTypeval="";if(domain.getCommunicationType()!=null)CommTypeval=domain.getCommunicationType();
				String Subjectval="";if(domain.getSubject()!=null)Subjectval=domain.getSubject();
				String Headerval="";if(domain.getHeader()!=null)Headerval=domain.getHeader();
				String Bodyval="";if(domain.getBody()!=null)Bodyval=domain.getBody();
				String Footerval="";if(domain.getFooter()!=null)Footerval=domain.getFooter();
				String UIDofSenderval="";if(domain.getUIDOfSender()!=null)UIDofSenderval=domain.getUIDOfSender();
				String KeyWordsval="";if(domain.getKeyValForRef()!=null)KeyWordsval=domain.getKeyValForRef();
				
        		
        %>
        
        <tr class="light-bg">  <!-- <tr class="dark-bg"> -->
            <!-- <td class="body-cell uspcol4">1</td> -->
            <td class="body-cell col1 nomargpad"><div class="fillbox"><%=CommTypeval %> <input type="hidden" class="txtOnly" value="<%=CommTypeval %>" id="<%=CommType%>" name="<%=CommType%>" readonly/></div></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" placeholder="Enter Subject" id="<%=Subject%>" name="<%=Subject%>" value="<%=Subjectval%>"/></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" placeholder="Enter Header"  id="<%=Header%>" name="<%=Header%>" value="<%=Headerval %>" /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" placeholder="Enter Body" class="number-only"  id="<%=Body%>" name="<%=Body%>" value="<%=Bodyval %>"  /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" placeholder="Enter Footer" class="number-only"  id="<%=Footer%>" name="<%=Footer%>" value="<%=Footerval %>" /></td>
            <td class="body-cell col1"><select id="<%=UIDofSender%>" name="<%=UIDofSender%>" >
            		<option value="" selected>Approver User ID*</option>
            	<% 
            	if(AllUsersList!=null && AllUsersList.size()>0)
            	{
            		for(int k=0;k<AllUsersList.size();k++)
            		{
            			UsersInfoDomain user=AllUsersList.get(k);
            			
            			String id=user.getUserID();
            			String name=user.getUserName();
            			String selected="";
            			String uid_ts="";
            			if(UidMapping.containsKey(id))
            			{
            				uid_ts=UidMapping.get(id);
            				if(uid_ts.trim().equalsIgnoreCase(UIDofSenderval))
            					selected="selected";
            				
            			}
            		%>	<option value="<%=id %>" <%=selected%>  ><%=uid_ts %></option> <%
            		}
            	}
            	%>
              </select>
              </td>
              <td><input type="text" class="txtOnly" placeholder="Enter key words for reference" class="number-only" id="<%=KeyWords%>" name="<%=KeyWords%>" value="<%=KeyWordsval %>" /></td>
          </tr>
          		
        <%	}
        }
        %>
        
          
        </tbody>
      </table>
      <div class="btnblock margtop20">
        <input type="submit" value="Submit" class="button"/>
      </div>
    </div>
  </div>
  </form>
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
			{ width: 220, align: 'left' },
			{ width: 180, align: 'left' },
			{ width: 180, align: 'left' },
			{ width: 120, align: 'left' },
			{ width: 100, align: 'left' },
			{ width: 150, align: 'left' },
			{ width: 200, align: 'left' }
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
	value: 1.0,
    size: 90,
    fill: { color: "#DAA520" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(100 * progress) + '<span>% CINE</span>');
  });
  $('.circle2').circleProgress({
	value: 1.0,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(100 * progress) + '<span>% USE</span>');
  });
  $('.circle3').circleProgress({
	value: 0.50,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(50 * progress) + '<span>% STAMP</span>');
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