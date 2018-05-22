<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
	
<%@page import="com.kes.kote.util.*" %>
<%@page import="com.kes.kote.dao.*" %>
<%@page import="com.kes.kote.interfaces.*" %>
<%@page import="com.kes.kote.domain.*" %>
<%@page import="java.util.*" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>CCSE - Course Certificate Setup (STACS)</title>
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

List<StacsCertConfigDomain> CertificateDetails=ExcelSheetUtil.getCertificateDetails(session);

LookupInterface dao=new LookupDao();

List<DepartmetInfoDomain> DeptList=dao.getDeptList(session);

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
      <h3>CCSE - Course Certificate Setup <span>(STACS)</span></h3>
    </div>
    <form class="cine-setup" action="/KOTE/ControllerServlet/StacsService?From=CertiConfig" method="post">
    <div class="dwrapper">
      <table id="fixed_hdr2">
        <thead>
          <tr>
            <th class="header-cell col1">Course ID</th>
            <th class="header-cell col1">Greeting Text</th>
            <th class="header-cell col1">Body Text</th>
            <th class="header-cell col1">Wishing Text</th>
            <th class="header-cell col1">Footer Text</th>
            <th class="header-cell col1">Authorized Signatory</th>
            <th class="header-cell col1">Digital Signature</th>
            <th class="header-cell col1">Certificate Size</th>
            <th class="header-cell col1">Certificate Layout</th>
            <th class="header-cell col1">Border Image</th>
            <th class="header-cell col1">Corner Image</th>
            <th class="header-cell col1">Water Mark Image</th>
          </tr>
        </thead>
        <tbody>
        <%
        if(CertificateDetails!=null && CertificateDetails.size()>0)
        {
        	for(int i=0;i<CertificateDetails.size();i++)
        	{
        		StacsCertConfigDomain row=CertificateDetails.get(i);
        		
        		String CourseIDRK="CourseID"+i;
				String GreetingTextRK="GText"+i;
				String BodyTextRK="BText"+i;
				String WishingTextRK="WText"+i;
				String FooterTextRK="FText"+i;
				String AuthorizedSignRK="ASign"+i;
				String DigitalSignRK="DSign"+i;
				String CertificateSizeRK="CSize"+i;
				String CertificateLayoutRK="CLayout"+i;
				String BorderImageRK="BImage"+i;
				String CornerImageRK="CImage"+i;
				String WaterMarkImageRK="WImage"+i;
				
				String CourseID="";if(row.getCourseID()!=null)CourseID=row.getCourseID();
				String GreetingText=""; if(row.getGreetingText()!=null) GreetingText=row.getGreetingText();
				String BodyText=""; if(row.getBodyText()!=null) BodyText=row.getBodyText();
				String WishingText="";if(row.getWishingText()!=null) WishingText=row.getWishingText();
				String FooterText=""; if(row.getFooterText()!=null)FooterText=row.getFooterText();
				String AuthorizedSign=""; if(row.getAuthorizedSign()!=null)AuthorizedSign=row.getAuthorizedSign();
				String DigitalSign=""; if(row.getDigitalSign()!=null) DigitalSign=row.getDigitalSign();
				String CertificateSize=""; if(row.getCertificateSize()!=null) CertificateSize=row.getCertificateSize();
				String CertificateLayout=""; if(row.getCertificateLayout()!=null) CertificateLayout=row.getCertificateLayout();
				String BorderImage=""; if(row.getBorderImage()!=null)BorderImage=row.getBorderImage();
				String CornerImage=""; if(row.getCornerImage()!=null)CornerImage=row.getCornerImage();
				String WaterMarkImage=""; if(row.getWaterMarkImage()!=null) WaterMarkImage=row.getWaterMarkImage();
				%>
				
			
			<tr class="light-bg">
            <td class="body-cell col1">
            <select name="<%=CourseIDRK %>" >
                <option value="" visible>Choose Course ID*</option>
                <%
                if(DeptList!=null && DeptList.size()>0)
                {
                	for(int l=0;l<DeptList.size();l++)
                	{
                		DepartmetInfoDomain dinfo=DeptList.get(l);
                		
                		if(CourseID.trim().equalsIgnoreCase(dinfo.getDeptID()) ) 
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
            <td class="body-cell col1"><input type="text" placeholder="Greeting Text" name="<%=GreetingTextRK %>" value="<%= GreetingText%>" /></td>
           <td class="body-cell col1"><input type="text" class="txtOnly" placeholder="Body Text" name="<%=BodyTextRK %>" value="<%= BodyText%>"  /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" placeholder="Wishing Text" name="<%=WishingTextRK %>" value="<%= WishingText%>"   /></td>
           <td class="body-cell col1"><input type="text" class="txtOnly" placeholder="Footer Text" name="<%=FooterTextRK %>" value="<%= FooterText%>"   /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" placeholder="Authorized Signatory" name="<%=AuthorizedSignRK %>" value="<%= AuthorizedSign%>"   /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" placeholder="Digital Signature" name="<%=DigitalSignRK %>" value="<%= DigitalSign%>"   /></td>
            <td class="body-cell col1">
            <select name="<%=CertificateSizeRK %>">
                <option value="" visible>Choose Certificate Size*</option>
                <option value="Letter" <% if(CertificateSize.trim().startsWith("Letter")) { %> selected <%} %>  >Letter 8.5" x 11"</option>
                <option value="Tabloid"  <% if(CertificateSize.trim().startsWith("Tabloid")) { %> selected <%} %> >Tabloid 11" x 17"</option>
                <option value="Legal"  <% if(CertificateSize.trim().startsWith("Legal")) { %> selected <%} %>  >Legal 8.5" x 14"</option>
                <option value="Executive"  <% if(CertificateSize.trim().startsWith("Executive")) { %> selected <%} %>  >Executive 7.25 x 10.5"</option>
                <option value="A4"  <% if(CertificateSize.trim().startsWith("A4")) { %> selected <%} %>  >A4 8.27" x 11.69"</option>
                <option value="A5"  <% if(CertificateSize.trim().startsWith("A5")) { %> selected <%} %>  >A5 5.83" x 8.27"</option>
              </select>
             </td>
            <td class="body-cell col1">  <select name="<%=CertificateLayoutRK %>">
                <option value="" visible>Choose Certificate Layout*</option>
                <option value="Portrait"  <% if(CertificateLayout.trim().equalsIgnoreCase("Portrait")) { %> selected <%} %> >Portrait</option>
                <option value="Landscape"  <% if(CertificateLayout.trim().equalsIgnoreCase("Landscape")) { %> selected <%} %>  >Landscape</option>
            </select></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" placeholder="Border Image" name="<%=BorderImageRK %>" value="<%= BorderImage%>" /></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" placeholder="Corner Image" name="<%=CornerImageRK %>" value="<%= CornerImage%>" /></td>
           <td class="body-cell col1"><input type="text" class="txtOnly" placeholder="Water Mark Image" name="<%=WaterMarkImageRK %>" value="<%= WaterMarkImage%>" /></td>
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
			{ width: 50, align: 'left' },
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
	value: 0.84,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(84 * progress) + '<span>% STACS</span>');
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