<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
	
<%@page import="com.kes.kote.util.*" %>
<%@page import="com.kes.kote.dao.*" %>
<%@page import="com.kes.kote.domain.*" %>
<%@page import="java.util.*" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>FMS - Institution Fee Mapping (FISE)</title>

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

List<FiseFeeMapDomain> FeeMapDetails=ExcelSheetUtil.getFeeMapDetails(session);
SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
util.printsheetdata();


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
      <h3>FMS - Institution Fee Mapping <span>(FISE)</span></h3>
    </div>
    <form class="cine-setup" action="/KOTE/ControllerServlet/FiseService?From=FeeMap" method="post">
    <div class="dwrapper">
      <table id="fixed_hdr2">
        <thead>
          <tr>
            <th class="header-cell col2">Fee ID</th>
            <th class="header-cell col2">Fee Name</th>
            <th class="header-cell col2">Prefix</th>
            <th class="header-cell col2">Body</th>
            <th class="header-cell col2">Suffix</th>
            <th class="header-cell col2">Fee Description</th>
            <th class="header-cell col2">Institution Fee ID</th>
          </tr>
        </thead>
        <tbody>
        <%
        if(FeeMapDetails!=null && FeeMapDetails.size()>0)
        {
        	for(int i=0;i<FeeMapDetails.size();i++)
        	{
        		FiseFeeMapDomain row=FeeMapDetails.get(i);
        		
        		String feeCode="feeCode"+i;
				String feeDesc="feeDesc"+i;;
				String prefix="prefix"+i;
				String body="body"+i;
				String suffix="suffix"+i;
				String instFeeDesc="instFeeDesc"+i;
				String instFeeCode="instFeeCode"+i;
				
				String feeCodeVal="";if(row.getFeeCode()!=null)feeCodeVal=row.getFeeCode();
				String feeDescVal="";if(row.getFeeDesc()!=null)feeDescVal=row.getFeeDesc();
				String prefixVal="";if(row.getPrefix()!=null)prefixVal=row.getPrefix();
				String bodyVal="";if(row.getBody()!=null) bodyVal=row.getBody();
				String suffixVal=""; if(row.getSuffix()!=null) suffixVal=row.getSuffix();
				String instFeeDescVal=""; if(row.getInstFeeDesc()!=null) instFeeDescVal=row.getInstFeeDesc();
				String instFeeCodeVal="";if(row.getInstFeeCode()!=null) instFeeCodeVal=row.getInstFeeCode();
				if(prefixVal.trim().length()>0 && bodyVal.trim().length()>0 && suffixVal.trim().length()>0)
					instFeeCodeVal=prefixVal+"-"+bodyVal+"-"+suffixVal;
				
				//util.print(row.toString());
				
				%>
				
		<tr class="light-bg">
            <td class="body-cell col2"><input type="hidden" name="<%=feeCode %>" value="<%=feeCodeVal %>" /> <%=feeCodeVal%> </td>
            <td class="body-cell col2"><input type="hidden" name="<%=feeDesc %>" value="<%=feeDescVal %>" /> <%=feeDescVal%></td>
            <td class="body-cell col2"><input type="text" class="textnumber" maxlength="5" name="<%=prefix %>" id="<%=prefix %>" placeholder="Enter Prefix" value="<%=prefixVal %>" onchange="generateFeeID('<%=prefix %>','<%=body %>','<%=suffix %>','<%=instFeeCode %>')" /></td>
            <td class="body-cell col2"><input type="text" class="textnumber" maxlength="5" name="<%=body %>" id="<%=body %>" placeholder="Enter Body" value="<%=bodyVal %>"  onchange="generateFeeID('<%=prefix %>','<%=body %>','<%=suffix %>','<%=instFeeCode %>')" /></td>
            <td class="body-cell col2"><input type="text" class="textnumber" maxlength="5" name="<%=suffix %>" id="<%=suffix %>" placeholder="Enter Suffix" value="<%=suffixVal %>"  onchange="generateFeeID('<%=prefix %>','<%=body %>','<%=suffix %>','<%=instFeeCode %>')" /></td>
            <td class="body-cell col2"><input type="text" name="<%=instFeeDesc %>" placeholder="Enter Fee Description" value="<%=instFeeDescVal %>"  /></td>
            <td class="body-cell col2"><input type="text" name="<%=instFeeCode %>" id="<%=instFeeCode %>" placeholder="Enter Institution Fee ID" value="<%=instFeeCodeVal %>"  readonly/></td>
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

function generateFeeID(prefix,body,suffix,instfeeid)
{
	//alert(prefix+body+suffix+instfeeid);
	
	var p=$('#'+prefix).val();
	var b=$('#'+body).val();
	var s=$('#'+suffix).val();
	
	var idval=p+'-'+b+'-'+s;
	$('#'+prefix).val(p.toUpperCase())
	$('#'+body).val(b.toUpperCase())
	$('#'+suffix).val(s.toUpperCase())
	
	$('#'+instfeeid).val(idval.toUpperCase());
	
}
	$(function () {
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 400,
			colModal: [
			{ width: 150, align: 'left' },
			{ width: 150, align: 'left' },
			{ width: 150, align: 'left' },
			{ width: 150, align: 'left' },
			{ width: 150, align: 'left' },
			{ width: 150, align: 'left' },
			{ width: 150, align: 'left' }
			],
			sort: false
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
	value: 1.0,
    size: 90,
    fill: { color: "#DAA520" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(100 * progress) + '<span>% FISE</span>');
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