<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
	
<%@page import="com.kes.kote.util.*" %>
<%@page import="com.kes.kote.dao.*" %>
<%@page import="com.kes.kote.domain.*" %>
<%@page import="com.kes.kote.interfaces.*" %>
<%@page import="java.util.*" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>FCMS - Fee Collection Management System (FISE)</title>
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

List<FiseFCSDomain> FCSDetails=ExcelSheetUtil.getFCSDetails(session);

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
<div class="show-status3 common"><div class="circle4"><strong></strong></div></div>
<div class="show-status4 common"><div class="circle5"><strong></strong></div></div>

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
      <h3>FCMS - Fee Collection Management System <span>(FISE)</span></h3>
    </div>
    <form class="cine-setup" action="/KOTE/ControllerServlet/FiseService?From=FCS" method="post">
    <div class="dwrapper">
      <table id="fixed_hdr2">
        <thead>
          <tr>
            <th class="header-cell col1">Category</th>
            <th class="header-cell col1">Sub Category</th>
            <th class="header-cell col1">Assign</th>
            <!-- <th class="header-cell col1">GBP</th>
            <th class="header-cell col1">EURO</th>
            <th class="header-cell col1">LOCAL</th>
            <th class="header-cell col1">OTHERS</th> -->
          </tr>
        </thead>
        <tbody>
        <%
        if(FCSDetails!=null && FCSDetails.size()>0)
        {
        	for(int i=0;i<FCSDetails.size();i++)
        	{
        		FiseFCSDomain row=FCSDetails.get(i);
        		
        		String category="category"+i;
				String subCategory="subCategory"+i;
				String usd="usd"+i;
				/* String gbp="gbp"+i;
				String euro="euro"+i;
				String lcy="lcy"+i;
				String others="others"+i; */
				
				String categoryval="";if(row.getCategory()!=null)categoryval=row.getCategory();
				String subCategoryval="";if(row.getSubCategory()!=null)subCategoryval=row.getSubCategory();
				String assign="";if(row.getAssign()!=null)assign=row.getAssign();
				
				%>
		<tr class="light-bg">
           		<td class="body-cell col1 nomargpad">
            	<div class="fillbox">
                	<select name="<%=category%>">
                        <option value="" selected >Select Option</option>
                        <option value="Cash" <%if(categoryval.trim().equalsIgnoreCase("Cash")) { %> selected <%} %> >Cash</option>
                        <option value="Cheque/Draft"  <%if(categoryval.trim().equalsIgnoreCase("Cheque/Draft")) { %> selected <%} %> >Cheque/Draft</option>
                        <option value="Online" <%if(categoryval.trim().equalsIgnoreCase("Online")) { %> selected <%} %> >Online</option>
                        <option value="Wallet"  <%if(categoryval.trim().equalsIgnoreCase("Wallet")) { %> selected <%} %> >Wallet</option>
                    </select>
                </div>
            </td>
            <td class="body-cell col1 nomargpad"><div class="fillbox">
            <select name="<%=subCategory %>" >
                        <option value="" selected>Select Option</option>
                        <option value="Cash" <%if(subCategoryval.trim().equalsIgnoreCase("Cash")) { %> selected <%} %> >Cash</option>
                        <option value="Cheque"	<%if(subCategoryval.trim().equalsIgnoreCase("Cheque")) { %> selected <%} %> >Cheque</option>
                        <option value="Draft"	<%if(subCategoryval.trim().equalsIgnoreCase("Draft")) { %> selected <%} %> >Draft</option>
                        <option value="Debit Card"	<%if(subCategoryval.trim().equalsIgnoreCase("Debit Card")) { %> selected <%} %> >Debit Card</option>
                        <option value="Credit Card"	<%if(subCategoryval.trim().equalsIgnoreCase("Credit Card")) { %> selected <%} %> >Credit Card</option>
                        <option value="Internet Banking"	<%if(subCategoryval.trim().equalsIgnoreCase("Internet Banking")) { %> selected <%} %> >Internet Banking</option>
                        <option value="ATM Pay Debit"	<%if(subCategoryval.trim().equalsIgnoreCase("ATM Pay Debit")) { %> selected <%} %> >ATM Pay Debit</option>
                        <option value="Wallet"	<%if(subCategoryval.trim().equalsIgnoreCase("Wallet")) { %> selected <%} %>>Wallet</option>
                    </select></div></td>
            <td class="body-cell col1"><select name="<%=usd %>">
                <option value="" selected>Select</option>
                <option value="Yes"	<%if(assign.trim().equalsIgnoreCase("Yes")) { %> selected <%} %> >Yes</option>
                <option value="No"	<%if(assign.trim().equalsIgnoreCase("No")) { %> selected <%} %> >No</option>
              </select></td>
            <%-- <td class="body-cell col1"><select name="<%=gbp %>">
                <option value="" selected>Select</option>
                <option value="Yes"	<%if(gbpval.trim().equalsIgnoreCase("Yes")) { %> selected <%} %> >Yes</option>
                <option value="No"	<%if(gbpval.trim().equalsIgnoreCase("No")) { %> selected <%} %> >No</option>
              </select></td>
            <td class="body-cell col1"><select name="<%=euro %>">
                <option value="" selected>Select</option>
                <option value="Yes"	<%if(euroval.trim().equalsIgnoreCase("Yes")) { %> selected <%} %> >Yes</option>
                <option value="No"	<%if(euroval.trim().equalsIgnoreCase("No")) { %> selected <%} %> >No</option>
              </select></td>
            <td class="body-cell col1"><select name="<%=lcy %>">
                <option value="" selected>Select</option>
                <option value="Yes"	<%if(lcyval.trim().equalsIgnoreCase("Yes")) { %> selected <%} %> >Yes</option>
                <option value="No"	<%if(lcyval.trim().equalsIgnoreCase("No")) { %> selected <%} %> >No</option>
              </select></td>
            <td class="body-cell col1"><select name="<%=others %>">
                <option value="" selected>Select</option>
                <option value="Yes"	<%if(othersval.trim().equalsIgnoreCase("Yes")) { %> selected <%} %> >Yes</option>
                <option value="No"	<%if(othersval.trim().equalsIgnoreCase("No")) { %> selected <%} %> >No</option>
              </select></td> --%>
          </tr>
        	<% }
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
				{ width: 310, align: 'left' },
				{ width: 315, align: 'left' },
				{ width: 300, align: 'left' },
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
	value: 1.0,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(100 * progress) + '<span>% STACS</span>');
  });
  $('.circle5').circleProgress({
	value: 0.80,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(80 * progress) + '<span>% FISE</span>');
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