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
<title>CFA - Course Fee Mapping (FISE)</title>
<link type="text/css" href="../css/style.css" rel="stylesheet" />
<link type="text/css" href="../css/font-awesome.css" rel="stylesheet" />
<link type="text/css" href="../css/font-awesome.min.css" rel="stylesheet" />
<link type="text/css" href="../css/responsive.css" rel="stylesheet" />

<script src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/scroll_table.js"></script>
<script type="text/javascript" src="../js/fixed_table_rc.js"></script>
<script type="text/javascript" src="../js/custom.js"></script>
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

LookupInterface dao=new LookupDao();
List<DepartmetInfoDomain> DeptList=dao.getDeptList(session);
List<InstFeeDomain> InstFeeDetails=dao.getInstFeeDetails(session);

List<FiseCourseFeeMapDomain> CrsFeeMapDetails=ExcelSheetUtil.getCrsFeeMapDetails(session);

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
  <div class="container">
    <div class="page-title">
      <h3>CFA - Course Fee Mapping <span>(FISE)</span></h3>
    </div>
     <form class="cine-setup" action="/KOTE/ControllerServlet/FiseService?From=CrsFeeMap" method="post">
    <div class="dwrapper">
      <table id="fixed_hdr2">
        <thead>
          <tr>
            <th class="header-cell col1">Department ID</th>
            <th class="header-cell col1">Fee Sequence</th>
            <th class="header-cell col1">Instn. Fee ID</th>
            <th class="header-cell col1">Group</th>
            <th class="header-cell col1">Fee Amount</th>
            <th class="header-cell col1">Course Fee ID</th>
            <th class="header-cell col1">Fee Type</th>
            <th class="header-cell col1">Recurring Frequency</th>
          </tr>
        </thead>
        <tbody>
        <%
        if(CrsFeeMapDetails!=null && CrsFeeMapDetails.size()>0)
        {
        	for(int i=0;i<CrsFeeMapDetails.size();i++)
        	{
        		FiseCourseFeeMapDomain row=CrsFeeMapDetails.get(i);
        		
        		String deptId="deptId"+i;
				String feeSequence="feeSequence"+i;
				String instFeeId="instFeeId"+i;
				String group="group"+i;
				String feeAmount="feeAmount"+i;
				String crsFeeId="crsFeeId"+i;
				String feeType="feeType"+i;
				String feeRecurring="feeRecurring"+i;
				
				String deptIdval="";if(row.getDeptId()!=null)deptIdval=row.getDeptId();
				String feeSequenceval="";if(row.getFeeSequence()>0)feeSequenceval=""+row.getFeeSequence();
				String instFeeIdval="";if(row.getInstFeeId()!=null)instFeeIdval=row.getInstFeeId();
				instFeeIdval=CommonDao.getStringWithoutSpaces(instFeeIdval); 
				String groupval="";if(row.getGroup()!=null)groupval=row.getGroup();
				String feeAmountval="";if(row.getFeeAmount()>0)feeAmountval=""+row.getFeeAmount();
				String crsFeeIdval="";if(row.getCrsFeeId()!=null)crsFeeIdval=row.getCrsFeeId();
				String feeTypeval=""; if(row.getFeeType()!=null)feeTypeval=row.getFeeType();
				String feeRecurringval="";if(row.getFeeRecurring()>0) feeRecurringval=""+row.getFeeRecurring();
				
				//SessionUtil.print(row.toString());
				%>
				
			<tr class="light-bg">
            <td class="body-cell col1">
            <select name="<%=deptId %>"  id="<%=deptId %>" onchange="generateFeeID('<%=deptId %>','<%=feeSequence %>','<%=instFeeId %>','<%=crsFeeId %>')" >
                	<option value="" >Select Department ID*</option>
                	
                <%
                if(DeptList!=null && DeptList.size()>0)
                {
                	for(int l=0;l<DeptList.size();l++)
                	{
                		DepartmetInfoDomain dinfo=DeptList.get(l);
                		
                		if(deptIdval.trim().equalsIgnoreCase(dinfo.getDeptID()) ) 
                		{
                			%> <option value="<%=dinfo.getDeptID() %>" selected ><%=dinfo.getDeptID() %></option> <%
                		}else
                		{
                			%> <option value="<%=dinfo.getDeptID() %>" ><%=dinfo.getDeptID() %></option> <%
                		}
                	}
                }
                %>
            </select>
            </td>
            <td class="body-cell col1"><input type="text" class="numberOnly" name="<%=feeSequence %>" id="<%=feeSequence %>" placeholder="Fee Sequence" value="<%=feeSequenceval %>"  maxlength="3" onchange="generateFeeID('<%=deptId %>','<%=feeSequence %>','<%=instFeeId %>','<%=crsFeeId %>')" /></td>
            <td class="body-cell col1">
            <select name="<%=instFeeId %>" id="<%=instFeeId %>" onchange="generateFeeID('<%=deptId %>','<%=feeSequence %>','<%=instFeeId %>','<%=crsFeeId %>')" >
                	<option value="" selected>Select Instn. Fee ID*</option>
                	<%
                	if(InstFeeDetails!=null && InstFeeDetails.size()>0)
                	{
                		for(int j=0;j<InstFeeDetails.size();j++)
                		{
                			InstFeeDomain instfee=InstFeeDetails.get(j);
                			
                			String instfeeid=instfee.getInstFeeId();
                			String instfeename=instfee.getInstFeeName();
                			
                			if(instFeeIdval.trim().equalsIgnoreCase(instfeeid))
                			{
                				%><option value="<%=instfeeid %>" selected><%=instfeeid %> </option> <%
                			}else
                			{
                				%><option value="<%=instfeeid %>" > <%=instfeeid %></option> <%
                			}
                		}
                	}
                	%>
            </select></td>
            <td class="body-cell col1">
            <select name="<%=group %>">
                	<option value="" selected>Select Group*</option>
                	<option value="Yes" <%if(groupval.trim().equalsIgnoreCase("Yes")) { %> selected <%} %> >Yes</option>
                    <option value="No" <%if(groupval.trim().equalsIgnoreCase("No")) { %> selected <%} %> >No</option>
            </select></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="<%=feeAmount %>" placeholder="Fee Amount" value="<%=feeAmountval %>" maxlength=7 /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="<%=crsFeeId %>" id="<%=crsFeeId %>" placeholder="Course Fee ID" value=<%=crsFeeIdval %> readonly/></td>
            <td class="body-cell col1">
            <select name="<%=feeType %>" >
                	<option value="" selected>Select Fee Type*</option>
                	<option value="Recurring"  <%if(feeTypeval.trim().equalsIgnoreCase("Recurring")) { %> selected <%} %> >Recurring</option>
                    <option value="One Time" <%if(feeTypeval.trim().equalsIgnoreCase("One Time")) { %> selected <%} %> >One Time</option>
            </select></td>
            <td class="body-cell col1">
            <select name="<%=feeRecurring %>" >
            <option value="-1" selected >Select Recurring Frequency</option>
            <%
            for(int h=1;h<13;h++)
            {
            	%><option value="<%=h %>" <%if(feeRecurringval.trim().equalsIgnoreCase(""+h)) { %> selected <%} %>> <%=h %> </option><%
            }
            %>
            </select>
            <%-- <input type="text" maxlength="2" class="number-only" name="<%=feeRecurring %>" placeholder="Recurring Frequency" value="<%=feeRecurringval %>" /> --%>
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
	
	
	$('#'+instfeeid).val(idval);
	
}

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
			{ width: 5, align: 'left' },
			{ width: 25, align: 'left' },
			{ width: 25, align: 'left' },
			{ width: 25, align: 'left' },
			{ width: 25, align: 'left' },
			{ width: 25, align: 'left' },
			{ width: 25, align: 'left' },
			{ width: 25, align: 'left' },
			{ width: 25, align: 'left' },
			{ width: 25, align: 'left' },
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
	value: 0.20,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(20 * progress) + '<span>% FISE</span>');
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