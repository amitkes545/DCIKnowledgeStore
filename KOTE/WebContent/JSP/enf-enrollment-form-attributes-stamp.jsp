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
<title>ENF - Enrollment Form Attributes (STAMP)</title>

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

<style type="text/css">
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

TaskControlDao taskDao=new TaskControlDao();
taskDao.saveTaskControl(CLModuleTaskControlUtil.STAMPENROLLFORM,"Completed",session);
response.sendRedirect("../JSP/act-administration-communication-template-stamp.jsp");

List<StampEnrollFormDomain> EnrollFormDetails=ExcelSheetUtil.getEnrollFormDetails(session);

LookupInterface dao=new LookupDao();
List<EnrAttrCaptionsDomain> EnrAttCaptions=dao.getEnrAttCaptions(session);

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
  <div class="container">
    <div class="page-title">
      <h3> ENF - Enrollment Form Attributes <span>(STAMP)</span></h3>
    </div>
    <form class="cine-setup" action="/KOTE/ControllerServlet/StampService?From=EnrollForm" method="post">
    <div class="dwrapper">
      <table id="fixed_hdr2">
        <thead>
          <tr>
            <th class="header-cell col1">Attribute ID</th>
            <th class="header-cell col1">Attribute Name</th>
            <th class="header-cell col1">Required Yes/No</th>
            <th class="header-cell col1">Caption ID</th>
            <th class="header-cell col1">Caption Description</th>
            <th class="header-cell col1">Group Number</th>
            <th class="header-cell col1">Group Title</th>
            <th class="header-cell colnew">Attribute Sequence in Group</th>
            <th class="header-cell col1">TAB No.</th>
            <th class="header-cell col1">TAB Title</th>
            <th class="header-cell colnew">Attribute Sequence in TAB</th>
            <th class="header-cell colnew">Group Sequence in TAB</th>
            <th class="header-cell colnew">Grid Number</th>
            <th class="header-cell colnew">Attribute Sequence in Grid</th>
            <th class="header-cell colnew">Sequence in Screen</th>
            <th class="header-cell col1">Text Box Size</th>
            <th class="header-cell col1">Caption Font</th>
            <th class="header-cell col1">Caption Size</th>
            <th class="header-cell col1">Caption Color</th>
            <th class="header-cell col1">Bolded Caption</th>
            <th class="header-cell col1">Italic Caption</th>
            <th class="header-cell col1">Text Font</th>
            <th class="header-cell col1">Text Size</th>
            <th class="header-cell col1">Text Color</th>
            <th class="header-cell col1">Bolded Text</th>
            <th class="header-cell col1">Italic Text</th>
            <th class="header-cell col1">Mandatory</th>
            <th class="header-cell col1">Enabled</th>
            </tr>
        </thead>
        <tbody>
        <%
        if(EnrollFormDetails!=null && EnrollFormDetails.size()>0)
        {
        	for(int i=0;i<EnrollFormDetails.size();i++)
        	{
        		StampEnrollFormDomain row= EnrollFormDetails.get(i);
        		
        		String attributeId="";if(row.getAttributeId()!=null)attributeId=row.getAttributeId();
        		String attributeName="";if(row.getAttributeName()!=null)attributeName=row.getAttributeName();
        		String isRequired="";if(row.getIsRequired()!=null)isRequired=row.getIsRequired();
        		String captionId="";if(row.getCaptionId()!=null)captionId=row.getCaptionId();
        		String captionDesc="";if(row.getCaptionDescription()!=null)captionDesc=row.getCaptionDescription();
        		String groupNo="";if(row.getGroupNo()!=null)groupNo=row.getGroupNo();
        		String groupTitle="";if(row.getGroupTitle()!=null)groupTitle=row.getGroupTitle();
        		String arrtibuteSeqinGrp="";if(row.getArrtibuteSeqinGrp()!=null)arrtibuteSeqinGrp=row.getArrtibuteSeqinGrp();
        		String tabNo="";if(row.getTabNo()!=null)tabNo=row.getTabNo();
        		String tabTitle="";if(row.getTabTitle()!=null)tabTitle=row.getTabTitle();
        		String attributeSeqinTab="";if(row.getAttributeSeqinTab()!=null)attributeSeqinTab=row.getAttributeSeqinTab();
        		String groupSeqinTab="";if(row.getGroupSeqinTab()!=null)groupSeqinTab=row.getGroupSeqinTab();
        		String gridNo="";if(row.getGridNo()!=null)gridNo=row.getGridNo();
        		String attributeSeqinGrid="";if(row.getAttributeSeqinGrid()!=null)attributeSeqinGrid=row.getAttributeSeqinGrid();
        		String seqinScreen="";if(row.getSeqinScreen()!=null)seqinScreen=row.getSeqinScreen();
        		String textBoxSize="";if(row.getTextBoxSize()!=null)textBoxSize=row.getTextBoxSize();
        		String captionFont="";if(row.getCaptionFont()!=null)captionFont=row.getCaptionFont();
        		String captionSize="";if(row.getCaptionSize()!=null)captionSize=row.getCaptionSize();
        		String captionColor="";if(row.getCaptionColor()!=null)captionColor=row.getCaptionColor();
        		String boldedCaption="";if(row.getBoldedCaption()!=null)boldedCaption=row.getBoldedCaption();
        		String italicCaption="";if(row.getItalicCaption()!=null)italicCaption=row.getItalicCaption();
        		String textFont="";if(row.getTextFont()!=null)textFont=row.getTextFont();
        		String textSize="";if(row.getTextSize()!=null)textSize=row.getTextSize();
        		String textColor="";if(row.getTextColor()!=null)textColor=row.getTextColor();
        		String boldedText="";if(row.getBoldedText()!=null)boldedText=row.getBoldedText();
        		String italicText="";if(row.getItalicText()!=null)italicText=row.getItalicText();
        		String isMandatory="";if(row.getIsMandatory()!=null)isMandatory=row.getIsMandatory();
        		String isEnabled="";if(row.getIsEnabled()!=null)isEnabled=row.getIsEnabled();
        		
        		
        		%>
        		
       
          <tr class="light-bg">
            <td class="body-cell col1"><input type="text" class="textnumber"  name="attributeId<%=i%>" value="<%=attributeId %>" placeholder="Enter Attribute ID" readonly /> </td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="attributeName<%=i %>" value="<%=attributeName %>" placeholder="Enter Attribute Name" readonly /> </td>
            <td class="body-cell col1">
            	<select name="isRequired<%=i%>">
                	<option value="" selected>Select Required</option>
                	<option value="YES" <%if(isRequired.trim().equalsIgnoreCase("YES")) { %> selected <%} %> >YES</option>
                	<option value="NO" <%if(isRequired.trim().equalsIgnoreCase("NO")) { %> selected <%} %> >NO</option>
                </select></td>
            
            <td class="body-cell col1">
            	<select name="captionId<%=i %>">
                	<option value="">Select Caption ID</option>
                	<%
                	if(EnrAttCaptions!=null && EnrAttCaptions.size()>0)
                	{
                		for(int j=0;j<EnrAttCaptions.size();j++)
                		{
                			EnrAttrCaptionsDomain caption=EnrAttCaptions.get(j);
                			String id=caption.getCaption_id();
                			String Name=caption.getCaption_description();
                			%> <option title="<%=Name %>" value="<%=id%>"  <%if(captionId.trim().equalsIgnoreCase(id)) { %> selected <%} %> ><%=id%></option> <%
                		}
                	}
                	%>
                    
                </select>
            </td>
            <td class="body-cell col1"><input type="text" class="number-only" name="captionDesc<%=i %>" value="<%=captionDesc%>" placeholder="Enter Group Number"/></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="groupNo<%=i %>" value="<%=groupNo %>" placeholder="Enter Group Number"/></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="groupTitle<%=i %>" value="<%=groupTitle %>"  placeholder="Enter Group Title"/></td>
            <td class="body-cell colnew"><input type="text" class="number-only" name="arrtibuteSeqinGrp<%=i %>" value="<%=arrtibuteSeqinGrp %>" placeholder="Enter Attri. Seq. in Group"/></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="tabNo<%=i %>" value="<%=tabNo %>" placeholder="Enter TAB No."/></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="tabTitle<%=i %>" value="<%=tabTitle %>" placeholder="Enter TAB Title"/></td>
            <td class="body-cell colnew"><input type="text" class="number-only" name="attributeSeqinTab<%=i %>" value="<%=attributeSeqinTab %>" placeholder="Enter Attri. Seq. in TAB" /></td>
            <td class="body-cell colnew"><input type="text" class="number-only" name="groupSeqinTab<%=i %>" value="<%=groupSeqinTab %>" placeholder="Enter Group Seq. in TAB"/></td>
            <td class="body-cell colnew"><input type="text" class="number-only" name="gridNo<%=i %>" value="<%=gridNo %>" placeholder="Enter Sequence in Screen"/></td>
            <td class="body-cell colnew"><input type="text" class="number-only" name="attributeSeqinGrid<%=i %>" value="<%=attributeSeqinGrid%>" placeholder="Enter Sequence in Screen"/></td>
            <td class="body-cell colnew"><input type="text" class="number-only" name="seqinScreen<%=i %>" value="<%=seqinScreen %>" placeholder="Enter Sequence in Screen"/></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="textBoxSize<%=i %>" value="<%=textBoxSize %>" placeholder="Enter Text Box Size"/></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="captionFont<%=i %>" value="<%=captionFont %>" placeholder="Enter Caption Font"/></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="captionSize<%=i %>" value="<%=captionSize %>" placeholder="Enter Caption Size"/></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="captionColor<%=i %>" value="<%=captionColor %>" placeholder="Enter Caption Color"/></td>
            <td class="body-cell col1">
            	<select name="boldedCaption<%=i %>">
                	<option value=""  selected>Select Bolded Caption</option>
                	<option value="TRUE" <%if(boldedCaption.trim().equalsIgnoreCase("TRUE")) { %> selected <%} %> >TRUE</option>
                	<option value="FALSE" <%if(boldedCaption.trim().equalsIgnoreCase("FALSE")) { %> selected <%} %> >FALSE</option>
                </select>
            </td>
            <td class="body-cell col1">
            	<select name="italicCaption<%=i %>">
                	<option value=""  selected>Select Italic Caption</option>
                	<option value="TRUE" <%if(italicCaption.trim().equalsIgnoreCase("TRUE")) { %> selected <%} %> >TRUE</option>
                	<option value="FALSE" <%if(italicCaption.trim().equalsIgnoreCase("FALSE")) { %> selected <%} %> >FALSE</option>
                </select>
            </td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="textFont<%=i %>" value="<%=textFont %>" placeholder="Enter Text Font"/></td>
            <td class="body-cell col1"><input type="text" class="number-only" name="textSize<%=i %>" value="<%=textSize %>" placeholder="Enter Text Size"/></td>
            <td class="body-cell col1"><input type="text" class="txtOnly" name="textColor<%=i %>" value="<%=textColor %>"  placeholder="Enter Text Color"/></td>
            <td class="body-cell col1">
            	<select name="boldedText<%=i %>">
                	<option value="" selected>Select Bolded Text</option>
                	<option value="TRUE" <%if(boldedText.trim().equalsIgnoreCase("TRUE")) { %> selected <%} %> >TRUE</option>
                	<option value="FALSE" <%if(boldedText.trim().equalsIgnoreCase("FALSE")) { %> selected <%} %> >FALSE</option>
                </select>
            </td>
            <td class="body-cell col1">
            	<select name="italicText<%=i %>">
                	<option value="" selected>Select Italic Text</option>
                	<option value="TRUE" <%if(italicText.trim().equalsIgnoreCase("TRUE")) { %> selected <%} %> >TRUE</option>
                	<option value="FALSE" <%if(italicText.trim().equalsIgnoreCase("FALSE")) { %> selected <%} %> >FALSE</option>
                </select>
            </td>
            <td class="body-cell col1">
            	<select name="isMandatory<%=i %>">
                	<option value="" selected>Select Mandatory</option>
                	<option value="TRUE" <%if(isMandatory.trim().equalsIgnoreCase("TRUE")) { %> selected <%} %> >TRUE</option>
                	<option value="FALSE" <%if(isMandatory.trim().equalsIgnoreCase("FALSE")) { %> selected <%} %> >FALSE</option>
                </select>
            </td>
            <td class="body-cell col1">
            	<select name="isEnabled<%=i %>">
                	<option value="" selected>Select Enabled</option>
                	<option value="TRUE" <%if(isEnabled.trim().equalsIgnoreCase("TRUE")) { %> selected <%} %> >TRUE</option>
                	<option value="FALSE" <%if(isEnabled.trim().equalsIgnoreCase("FALSE")) { %> selected <%} %> >FALSE</option>
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
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' },
			{align: 'left' }
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
	$("#datepicker").datepicker();
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
	value: 1.0,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(100 * progress) + '<span>% USE</span>');
  });
  $('.circle3').circleProgress({
	value: 0.25,
    size: 90,
    fill: { color: "#9ACD32" }
  }).on('circle-animation-progress', function(event, progress) {
    $(this).find('strong').html(Math.round(25 * progress) + '<span>% STAMP</span>');
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