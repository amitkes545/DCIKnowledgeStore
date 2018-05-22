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
<title>EFW - Enrollment Process Workflow (STAMP)</title>
<link type="text/css" href="../css/font-awesome.css" rel="stylesheet" />
<link type="text/css" href="../css/font-awesome.min.css" rel="stylesheet" />
<link type="text/css" href="../css/style.css" rel="stylesheet" />
<link type="text/css" href="../css/responsive.css" rel="stylesheet" />

<script src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/scroll_table.js"></script>
<script type="text/javascript" src="../js/fixed_table_rc.js"></script>
<script type="text/javascript" src="../js/custom.js"></script>


<% 
if(session.getAttribute("username")==null)
	response.sendRedirect("../JSP/sessionerror.jsp");

List<StampEPWorkFlowDomain> EPWorkFlowDetails=ExcelSheetUtil.getEPWorkFlowDetails(session);
LookupInterface dao=new LookupDao();
List<UsersInfoDomain> AllUsersList=dao.getAllUsersList(session);
HashMap<String, String> UidMapping=dao.getUidMapping(session);

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
              <li><a href="#"> <span><i class="fa fa-user resize-icon" aria-hidden="true"></i></span>Customer</a> </li>
              <li><a href="#"> <span><i class="fa fa-users resize-icon" aria-hidden="true"></i></span>User Management</a> </li>
              <li><a href="#"> <span><i class="fa fa-list-alt resize-icon" aria-hidden="true"></i></span>Setup Curriculum</a> </li>
              <li><a href="#"> <span><i class="fa fa-snowflake-o resize-icon" aria-hidden="true"></i></span>Knowledge Store</a> </li>
              <li><a href="#"> <span><i class="fa fa-filter resize-icon" aria-hidden="true"></i></span>Fees Config</a> </li>
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
<form class="cine-setup" action="/KOTE/ControllerServlet/StampService?From=EPWrokFlow" method="post">
  <div class="container">
    <div class="page-title">
      <h3>EFW - Enrollment Process Workflow  <span>(STAMP)</span></h3>
    </div>
    <div class="dwrapper">
      <table id="fixed_hdr2">
        <thead>
          <tr>
            <!-- <th class="header-cell uspcol4" style="width:130px;">Workflow Stage ID</th> -->
            <th class="header-cell uspcol4">Workflow Item Name</th>
            <th class="header-cell uspcol4">Stage Name</th>
            <th class="header-cell uspcol4">Approver User ID</th>
          </tr>
        </thead>
        <tbody>
        <% 
        if(EPWorkFlowDetails!=null && EPWorkFlowDetails.size()>0)
        {
        	for(int i=0;i<EPWorkFlowDetails.size();i++)
        	{
        		StampEPWorkFlowDomain domain =EPWorkFlowDetails.get(i);
        		String rowItemKey="ItemName"+i;
        		String rowStageKey="StageName"+i;
        		String rowUIDKey="ApprovUID"+i;
        		
        		String rowItem="";if(domain.getItemName()!=null)rowItem=domain.getItemName();
        		String rowStage="";if(domain.getStageName()!=null)rowStage=domain.getStageName();
        		String rowUID="";if(domain.getApprovedUsrID()!=null)rowUID=domain.getApprovedUsrID();
        		
        %>
        
        <tr class="light-bg">  <!-- <tr class="dark-bg"> -->
            <!-- <td class="body-cell uspcol4">1</td> -->
            <td class="body-cell uspcol4"><input type="text" class="txtOnly" placeholder="Item Name" id="<%=rowItemKey %>" name="<%=rowItemKey %>" value="<%=rowItem %>" /> </td>
            <td class="body-cell uspcol4"><input type="text" class="txtOnly" placeholder="Stage Name" id="<%=rowStageKey %>" name="<%=rowStageKey %>" value="<%=rowStage %>" /></td>
            <td class="body-cell uspcol4">
           	<select id="<%=rowUIDKey %>"  name="<%=rowUIDKey %>" >
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
            				if(uid_ts.trim().equalsIgnoreCase(rowUID))
            					selected="selected";
            				
            			}
            		%>	<option value="<%=id %>" <%=selected%>  ><%=uid_ts %></option> <%
            		}
            	}
            	%>
              </select>
              </td>
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
			{ width: 303, align: 'left' },
			{ width: 303, align: 'left' },
			{ width: 303, align: 'left' }
			],
			sort: true
		});		
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
	value: 1.0,
    size: 90,
    fill: { color: "#DAA520" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(100 * progress) + '<span>% STAMP</span>');
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