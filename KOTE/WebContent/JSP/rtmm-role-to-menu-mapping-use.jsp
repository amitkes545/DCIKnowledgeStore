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
<title>RTMM - Role to Menu Mapping (USE)</title>
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

List<UseAccessDomain> paramDetails=ExcelSheetUtil.getUseAccessRights(session);

LookupInterface lookup=new LookupDao();
List<UseRoleDomain> RoleList=lookup.getRoleList(session);
List<MenuDomain> MenuNameListLevel1=lookup.getMenuNameListByLevel(1,session);
List<MenuDomain> MenuNameListLevel2=lookup.getMenuNameListByLevel(2,session);
List<MenuDomain> MenuNameListLevel3=lookup.getMenuNameListByLevel(3,session);

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
      <h3>RTMM - Role to Menu Mapping <span>(USE)</span></h3>
    </div>
    <form class="paramForm" id="paramForm" action="/KOTE/ControllerServlet/UseService?From=useAccess" method="post">
    <div class="dwrapper">
      <table id="fixed_hdr2">
        <thead>
          <tr>
            <th class="header-cell colcust1">Role ID</th>
            <th class="header-cell colcust1">Module ID</th>
            <th class="header-cell colcust1">Menu Name - Level 1</th>
            <th class="header-cell colcust1">Menu Name - Level 2</th>
            <th class="header-cell colcust1">Menu Name - Level 3</th>
          </tr>
        </thead>
        <tbody>
          <%if(paramDetails!=null && paramDetails.size()>0){
        	  
        	  
        	for(int i=0;i<paramDetails.size();i++) {
        		UseAccessDomain useParam = paramDetails.get(i);

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
        		 
            <td class="body-cell colcust1">
            	<select name="roleId<%=i%>">
                	<option value="" selected>Select Role ID</option>
                	<% 
            	if(RoleList!=null && RoleList.size()>0)
            	{
            		for(int l=0;l<RoleList.size();l++)
            		{
            			UseRoleDomain role=RoleList.get(l);
            			
            			String id=role.getRoleId();
            			
            			
            		%>	<option value="<%=id %>" <% if(id.trim().equalsIgnoreCase(useParam.getRoleId())) { %> selected <%} %> ><%=id %></option> <%
            		}
            	}
            	%>
                </select>
            </td>
            <td class="body-cell colcust1">
            	<select name="moduleId<%=i%>">
                	<option value="" selected>Select Module ID</option>
                	<option value="Knowledge Store"  <% if(useParam.getModuleId().trim().equalsIgnoreCase("Knowledge Store")) { %> selected <%} %> >Knowledge Store</option>
                    <option value="DCI"  <% if(useParam.getModuleId().trim().equalsIgnoreCase("DCI")) { %> selected <%} %> >DCI</option>
                </select>
            </td>
            <td class="body-cell colcust1">
            	<select name="menulevel1<%=i%>">
                	<option value="" selected>Select Menu ID</option>
                	<% 
            	if(MenuNameListLevel1!=null && MenuNameListLevel1.size()>0)
            	{
            		for(int l=0;l<MenuNameListLevel1.size();l++)
            		{
            			MenuDomain role=MenuNameListLevel1.get(l);
            			
            			String id=role.getMenuName();
            			
            			
            		%>	<option value="<%=id %>" <% if(id.trim().equalsIgnoreCase(useParam.getMenuNamelevel1())) { %> selected <%} %> ><%=id %></option> <%
            		}
            	}
            	%>
                </select>
            </td>
            <td class="body-cell colcust1">
            	<select name="menulevel2<%=i%>">
                	<option value="" selected>Select Menu ID</option>
                	<% 
            	if(MenuNameListLevel2!=null && MenuNameListLevel2.size()>0)
            	{
            		for(int l=0;l<MenuNameListLevel2.size();l++)
            		{
            			MenuDomain role=MenuNameListLevel2.get(l);
            			
            			String id=role.getMenuName();
            			
            			
            		%>	<option value="<%=id %>" <% if(id.trim().equalsIgnoreCase(useParam.getMenuNamelevel2())) { %> selected <%} %> ><%=id %></option> <%
            		}
            	}
            	%>
                </select>
            </td>
            <td class="body-cell colcust1">
            	<select name="menulevel3<%=i%>">
                	<option value="" selected>Select Menu ID</option>
                	<% 
            	if(MenuNameListLevel3!=null && MenuNameListLevel3.size()>0)
            	{
            		for(int l=0;l<MenuNameListLevel3.size();l++)
            		{
            			MenuDomain role=MenuNameListLevel3.get(l);
            			
            			String id=role.getMenuName();
            			
            			
            		%>	<option value="<%=id %>" <% if(id.trim().equalsIgnoreCase(useParam.getMenuNamelevel3())) { %> selected <%} %> ><%=id %></option> <%
            		}
            	}
            	%>
                </select>
            </td>
            <% }%>
        <%}%>
          </tr>
         
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
			{ width: 10, align: 'left' }
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
	value: 0.70,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.floor(70 * progress) + '<span>% USE</span>');
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