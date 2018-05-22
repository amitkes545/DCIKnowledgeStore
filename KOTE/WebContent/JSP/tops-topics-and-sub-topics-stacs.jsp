<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
	
<%@page import="com.kes.kote.util.*" %>
<%@page import="com.kes.kote.dao.*" %>
<%@page import="com.kes.kote.interfaces.*" %>
<%@page import="com.kes.kote.domain.*" %>
<%@page import="java.util.*" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>TOPS - Topics & Sub Topics (STACS)</title>

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

List<StacsTopicsSubTopicDomain> TopicsSubTopicDetails=ExcelSheetUtil.getTopicsSubTopicDetails(session);

LookupInterface dao=new LookupDao();
List<SubjectDomain> SubjectsList=dao.getSubjectsList(session);

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
<div class="show-status2 common"><div class="circle3"><strong></strong></div></div>
<div class="show-status3 common"><div class="circle4"><strong></strong></div></div>

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
  <div class="container">
    <div class="page-title">
      <h3>TOPS - Topics & Sub Topics <span>(STACS)</span></h3>
    </div>
    <form class="cine-setup" action="/KOTE/ControllerServlet/StacsService?From=TopicSubTopic" method="post">
    <div class="dwrapper">
      <table id="fixed_hdr2">
        <thead>
          <tr>
            <th class="header-cell col2">Subject ID</th>
            <th class="header-cell col2">Topic ID</th>
            <th class="header-cell col2">Topic Name</th>
            <th class="header-cell col2">Sub Topic ID</th>
            <th class="header-cell col2">Sub Topic Name</th>
          </tr>
        </thead>
        <tbody>
        <%
        if(TopicsSubTopicDetails!=null && TopicsSubTopicDetails.size()>0)
        {
        	for(int i=0;i<TopicsSubTopicDetails.size();i++)
        	{
        		StacsTopicsSubTopicDomain row=TopicsSubTopicDetails.get(i);
        		
        		String SubjectIdRowKey="SubID"+i;
				String TopicIdRowKey="TopicID"+i;
				String TopicNameRowKey="TopicName"+i;
				String SubTopicIdRowKey="SubTopicID"+i;
				String SubTopicNameRowKey="SubTopicName"+i;
				
				String SubjectId="";if(row.getSubjectId()!=null) SubjectId=row.getSubjectId();
				String TopicId="";if(row.getTopicId()!=null)TopicId=row.getTopicId();
				String TopicName="";if(row.getTopicName()!=null) TopicName=row.getTopicName();
				String SubTopicId=""; if(row.getSubTopicId()!=null) SubTopicId=row.getSubTopicId();
				String SubTopicName=""; if(row.getSubTopicName() !=null ) SubTopicName=row.getSubTopicName();
				
				%>
			<tr class="light-bg">
            <td class="body-cell col2">
            <select name="<%=SubjectIdRowKey%>"> 
                <option value="" visible="">Choose Subject ID*</option>
                <%if(SubjectsList!=null && SubjectsList.size()>0)
                	{
                	for(int j=0;j<SubjectsList.size();j++)
                	{
                		SubjectDomain subname=SubjectsList.get(j);
                		String SubId=subname.getSubjectId();
                		if(SubId.trim().equalsIgnoreCase(SubjectId))
                		{
                			%> <option value="<%=SubId%>" selected ><%=SubId %></option> <%			
                		}else
                		{
                			%> <option value="<%=SubId%>"><%=SubId %></option> <%
                		}
                	}
                	}%>
                
             </select>
            </td>
            <td class="body-cell col2"><input type="text" name="<%=TopicIdRowKey %>" class="txtOnly" placeholder="Topic ID" value="<%=TopicId %>"/></td>
            <td class="body-cell col2"><input type="text" name="<%= TopicNameRowKey%>"class="txtOnly" placeholder="Topic Name" value="<%=TopicName %>"/></td>
            <td class="body-cell col2"><input type="text" name="<%=SubTopicIdRowKey %>" class="txtOnly" placeholder="Sub Topic ID"  value="<%=SubTopicId %>"/></td>
            <td class="body-cell col2"><input type="text" name="<%=SubTopicNameRowKey %>" class="txtOnly" placeholder="Sub Topic Name" value="<%=SubTopicName %>" /></td>
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
			{ width: 180, align: 'left' },
			{ width: 180, align: 'left' },
			{ width: 180, align: 'left' },
			{ width: 180, align: 'left' },
			{ width: 180, align: 'left' }
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
    fill: { color: "#9ACD32" }
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
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(100 * progress) + '<span>% STAMP</span>');
  });
  $('.circle4').circleProgress({
	value: 0.28,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(28 * progress) + '<span>% STACS</span>');
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