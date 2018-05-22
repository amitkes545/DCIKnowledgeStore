
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.kds.KODE_DEV.domain.RetriveImagesDomain"%>
     <%@page import="com.kds.KODE_DEV.domain.AssessmentDomain"%>
     <%@page import="com.kds.KODE_DEV.domain.DisplayCoursesDomain"%>
     <%@page import="com.kds.KODE_DEV.domain.AssessmentsDetailsDomain"%>
     
     <%@page import="com.kds.KODE_DEV.dao.ParticipantAssessmentsDao"%>
     <%@page import="com.kds.KODE_DEV.dao.ParticipantAssignmentsDao"%>
     
  	 <%@page import="java.util.Iterator"%>
     <%@page import="java.util.ArrayList"%>
      <%@page import="java.util.Date"%>
       <%@page import="java.util.*"%>
     <%@page import="java.io.File" %>
       <%@page import="java.text.*" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.js"></script>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<script src="../JS/datetimepicker_css.js"></script>
<script type="text/javascript" src="../JS/validatePostSession.js"></script>
<link href="../CSS/jquery.datetimepicker.css" rel="stylesheet"></link>
<script src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.datetimepicker.js"></script>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<link href="../CSS/kedu.css" rel="stylesheet"/>
<link href="../CSS/table_scroll.css" rel="stylesheet"/>
<link href="../CSS/design-common.css" rel="stylesheet"/>
<style type="text/css">
.tabmain
{
    position: absolute;
   right: 38%;
    top: 174px;
    width: 315px;
    z-index: 1;
    border: 1px solid #c2c2c2;
    border-bottom: none;
    BORDER-RADIUS: 5PX;
    
}
.tabmain ul li{float: left; width: 48.99%;list-style: none; text-align: center; padding: 10px 0.1px;}

.search_result{padding: 0px 10px;
margin-bottom: 25px;
max-height: 310px;

font-family: arial;
font-size: 14px;}
.su{
		    color: #008000;
    font-size: 14px;
    font-weight: bold;
    top:44px;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
.container{
background: url("../Image/body.png") center center no-repeat;
}
</style>
<style type="text/css">
.postpon_mod{
width: 95% !important;
    box-shadow: 5px 5px 5px 5px #FFF;
    margin: 15px auto 0px;
    padding: 15px 1px 0px 0px;
    border: 3px solid #C2C2C2;
    background-color: #FCF7F7;
    border-radius: 4px;
    opacity: 1; 
    margin-top: 72px;
     /*  height: 410px; */
}

.faculty_mod a.dt-pick {
    float: left;
    position: absolute;
    right: 70px;
    top: 47%;
}
.faculty_mod a.dt-pickw {
    float: left;
    position: absolute;
    right: 70px;
    top: 63%;
}
.search_div{
margin-top: -37px;
position: absolute;
right: 21px !important;
height: 33px;
padding: 6px !important;
font-family: regular;
border-radius: 4px;
}
.opt{
width: 265px !important;
padding: 6px !important;
height: 31px;
border: 1px solid #C2C2C2;
font-family: regular;
border-radius: 4px;
}
.addbg{    background-color: #FCF7F7;
    border-radius: 4px;
    /* opacity: 0.9; */
    border: 3px solid #C2C2C2;
    border-bottom: none;
    color:#000!important;
    }
select{
color: #000;
}
.box1
{
width: 250px!important;
    padding: 7px!important;
    border: 1px solid #c2c2c2;
    border-radius: 4px;
}
.row_head{
    background: none repeat scroll 0% 0% #009AE1;
    color: #FFF;
    font-weight: bold;
    }
    .row_head td{
    padding: 5px;
    }	
.row_head .col_2{float: left; padding: 0px 15px;}
.row_result .col_2{float: left; padding: 0px 15px;}
.result_row_tr td input{border: none; background: none; width: 99.5%; color: #000;}
.starttime{width: 130px;}
.endtime{width: 120px;}
.duration_r{width: 120px;}
.cost_r{width: 80px;}
.result_row_tr td{padding: 4px 4px 4px 10px;}

.btnscolor{background: #1d87da;
    border: none;
    color: #fff;
    padding: 5px 15px;
    margin-top: 10px;
    margin-right: 10px;
    margin-left: 10px;}
    
 .tabsbg
{
background: #fff;
border-radius: 5px;
}

.tab1bg1{    background-color: #FCF7F7;
    border-radius: 4px;
    /* opacity: 0.9; */
    border: 3px solid #C2C2C2;
    border-bottom: none;
    color:#000!important;
    }
    
.tab2bg2{    background-color: #FCF7F7;
    border-radius: 4px;
    /* opacity: 0.9; */
    border: 3px solid #C2C2C2;
    border-bottom: none;
    color:#000!important;
    }
    
.addbg{
    background-color: #FCF7F7 !important;
    border-radius: 4px;
    border: 3px solid #C2C2C2;
    border-bottom: none;
    color:#000!important;
    }
.addbg1{
    background-color: #fff !important;
    padding: 9px 0px !important;
    }
     .tab1 ul li:first-child
    {
    background: #FCF7F7;
    }
        .tab1 ul li:last-child
    {
    background: #fff;
    }
    
    .tab2 ul li:first-child
    {
    background: #fff !important;
    border: none;
    }
        .tab2 ul li:last-child
    {
    background: #FCF7F7;
    border: 3px solid #c2c2c2;
    border-bottom: none;
    }
    .nodata
    {
        color: #008000;
    font-size: 14px;
    font-weight: bold;
    top: 44px;
    /* position: absolute; */
    right: 0px;
    padding: 0px 36px;
    margin-top: 15px;
    }
    .tab2ind{display: none;}   
</style>
</head>

<%

		String username=(String)session.getAttribute("username");
if(username==null)
	response.sendRedirect("../JSP/error.jsp?errmsg=Session Expired");
	    String facultyId=(String)session.getAttribute("userid");
	    String orgId=(String)session.getAttribute("orgid");
	    String filepath=(String)session.getAttribute("filePath");
	    String uploadedid=(String)session.getAttribute("uploadedId");
	    
	    System.out.println("file path:"+username +"uploadedid:"+facultyId);
	    String[] stuId=(String[])session.getAttribute("assessResultDomain");
	    String  studentId[]=(String[])session.getAttribute("ResultDomain") ;
	    String filepath1=(String)session.getAttribute("FilePath");
	    String uploadedid1=(String)session.getAttribute("uploadedId1");
	    System.out.println("file path:"+filepath1 +"  uploadedid:"+uploadedid1); 
	    //1st form
        ArrayList<AssessmentDomain> assessmentDomainForAssessment = new ArrayList<AssessmentDomain>();
		
        assessmentDomainForAssessment = (ArrayList<AssessmentDomain>)request.getAttribute("recipientTypeForAssessment");
		//2nd form
	
 	%>
 	<script type="text/javascript">
 	
 	function clickAssessmentSubmit(){
 	
 		
 		document.cerify.action="/KODE_DEV/ControllerServlet/FileUploadService";
 		document.cerify.submit();	
 			
 	}
 	
 	function clickAssignmentSubmit(){
 		
 		document.AssignCertify.action="/KODE_DEV/ControllerServlet/AssignmentFileUploadService";
 		document.AssignCertify.submit();
 		
 	}
 
 	</script>
 	
<script>
$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
	$(".autohide").click(function()
	{
	
	});
});

function clickAssessmentID(){
	//alert("in");
	var assessid=document.getElementById("asessID").value;
	var uploadby=document.getElementById("uploadby").value;
	//alert("assessment id:"+assessid);
	//alert("uploadby id:"+uploadby);
	document.cerify.action="/KODE_DEV/ControllerServlet/DeleteAssignmentsServlet?assessmentId="+assessid+"&facultyName="+uploadby+"";
	document.cerify.submit();
}
	
	function onloadAssess(){
		<% String assesid=(String)request.getAttribute("asessID");
	
		if(assesid!=null && assesid.length()>0) {
			
		 %> 
		 var selectBox = document.getElementById("asessID");
		 for(var i=0;i<selectBox.options.length;i++){
				 if(selectBox.options[i].value == '<%=assesid%>')
				{
		 selectBox.options[i].selected=true;
					 break;
				 }
			 }
					
			<%}%>
}
	
	$(document).ready(function() {
		$("#assessMarks").keydown(function (e) {
	    	
	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
	            (e.keyCode == 65 && ( e.ctrlKey === true || e.metaKey === true ) ) || 
	            (e.keyCode >= 35 && e.keyCode <= 40)) {
	                 return;
	        }
	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105))
	        {
	            e.preventDefault();
	        }
	    });
	});
	//applying this code to text box for enter characters
	$(function () {
		 $('#status').keydown(function (e) {
		 if (e.shiftKey || e.altKey) {
		 e.preventDefault();
		 } else {
		 var key = e.keyCode;
		 if (!((key == 8) || (key == 9) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
		 e.preventDefault();
		 }
		 }
		 });
		 });
	//applying this code to text box for enter characters
	$(function () {
		 $('#remarks').keydown(function (e) {
		 if (e.shiftKey || e.altKey) {
		 e.preventDefault();
		 } else {
		 var key = e.keyCode;
		 if (!((key == 8) || (key == 9) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
		 e.preventDefault();
		 }
		 }
		 });
		 });
	
</script>
<script type="text/javascript">
function clickAssignmentID(){
	
	var assignid=document.getElementById("asignID").value;
	var uploadby=document.getElementById("uploadby").value;
	
	//alert(assignid);
	document.AssignCertify.action="/KODE_DEV/ControllerServlet/ParticipantAssignmentDetailsService?assessmentId="+assignid+"&facultyName="+uploadby+"";
	document.AssignCertify.submit();
}
function onloadAssign(){
	//alert("in");
	<% String assignid=(String)request.getAttribute("assignId");
	//System.out.println("assignment id in an onload:"+assignid);
	if(assignid!=null && assignid.length()>0) {
		 System.out.println("assignment id in onload:"+assignid);
	 %> 
	 var selectBox = document.getElementById("asignID");
	 for(var i=0;i<selectBox.options.length;i++){
			 if(selectBox.options[i].value == '<%=assignid%>')
			{
	 selectBox.options[i].selected=true;
				 break;
			 }
		 }
	<%}%>
}


</script>

<script>

$(document).ready(function() {
	$('.add_btn1').hide();
	
	$('.row input').each(function()
	{
	$(this).attr("disabled",false);
	});
	$('.row select').each(function()
			{
			$(this).attr("disabled",false);
			});
	$(".row a input").attr("disabled",false);

$(".row a input").click(function() {
	
	var id = $(this).attr("id");
	if($('#' + id).is(":checked"))
	{
	$("." + id).attr("disabled",false);
	$('.add_btn1').show();
	}
	else{
	
	$("." + id).attr("disabled",false);	
	$('.add_btn1').hide();
}
	if($(".row a input:checkbox").is(":checked")) {
		$('.add_btn1').show();
	  }	
	
	});
	
	});
	//hiding drop down by default

$(document).ready(function() {
    $("#updateButton").click(function(e) {
    	
        var isValid = true;
        $('input[type="text"].required').each(function() {
            if ($.trim($(this).val()) == '') {
                isValid = false;
                $(this).css({
                    "border": "1px solid red",
                    //"background": "#FFCECE"
                });
            }
            else {
                $(this).css({
                    "border": "",
                   // "background": ""
                });
            }
        });
        
        $('.sel select.required').each(function() {
            if ($.trim($(this).val()) == '') {
                isValid = false;
                $(this).css({
                    "border": "1px solid red",
                    //"background": "#FFCECE"
                });
            }
            else {
                $(this).css({
                    "border": "",
                   // "background": ""
                });
            }
        });
      
        if (isValid == false){
        	
            e.preventDefault();
           
        }
        else {
        	 //alert('Thank you for submitting')
        	 $("#postsession").submit();
        }
           
    });
});           
</script>

<%
String Tabmsg=request.getParameter("tabValue");
String successMsg = request.getParameter("sessionStatusSuccess");
String fileMessage = (String)request.getAttribute("fileMessage");
System.out.println("fileMessage="+fileMessage);
String noDataFound =(String)request.getAttribute("noDataFoundMsg");
System.out.println("JSP MSG :::::::::::"+noDataFound);
 %>


<body>

	<div class="container">
		<%-- <%@include file="all_one_header_knowstore.jsp"%> --%>
		<%@include file="../JSP/headers.jsp"%>
		
		
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
	<%if(successMsg!=null){ %>
<!-- <p class="autohide" style="position: absolute; color: green"><%=successMsg %></p> -->
	<%}%>
	
	
		<%-- <div class="tabmain <%=Tabmsg%>">
		<!-- <ul>
		<li id="ass_tab">Assessment</li>
		<li id="assi_tab">Assignment</li>
		</ul> -->
		</div> --%>
		
		<div class="postpon_mod <%=Tabmsg%>ind" id="first_part">
		<div style="text-align: center;">
		<p class="strong">Delete Assignment</p>
		
		<form name="cerify" method="post" enctype="multipart/form-data">

								 <% 
								 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
								 Calendar cal = Calendar.getInstance();
								 String date=dateFormat.format(cal.getTime());
								 Date todayDate=null;
								 try {
									 todayDate = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(date);
									//System.out.println("date1 in try:"+todayDate);
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								 DisplayCoursesDomain ri=new DisplayCoursesDomain();
								 ri.setCourseName(session.getAttribute("userId").toString());
								 ri.setCourseDetails(session.getAttribute("orgId").toString());
								 ri.setCourseFee(session.getAttribute("createdBy").toString());
								 
								 ParticipantAssessmentsDao PAssessDao = new ParticipantAssessmentsDao();
		
                           	    ArrayList<RetriveImagesDomain> arrayListForAssessment = new ArrayList<RetriveImagesDomain>();
                          	   	arrayListForAssessment = PAssessDao.selectViewCourses(ri);
		                      	Iterator<RetriveImagesDomain> iteratorForAssessmentId= arrayListForAssessment.iterator();
		                      	 String uploadby="";%>
		                      <select name="asessID" id="asessID"  class="box_lng" onchange="clickAssessmentID();" style="margin-left: 0px">
		                      
		                      <option value="">Choose Assignment</option>
		                      <option value="All">All</option>
		                      
		                    <% while(iteratorForAssessmentId.hasNext())
		                     {
		                    	RetriveImagesDomain adomain=iteratorForAssessmentId.next();
		    	             String Id = adomain.getCourseName();
		    	             String name = adomain.getCourseImageSize();
		    	             uploadby=adomain.getCourseImageLocation();
		    	             String idname=Id+" ("+name+")";
		    	            // System.out.println("uploadby="+uploadby);%>
		                      <option  value="<%= name %>"><%=idname%></option>	
		                         <%  }%>
		                      </select>
	                <input type="hidden" value="<%=uploadby%>" id="uploadby" name="uploadby" />
		
		<div class="search_div">
		<input class="box1" type="text" id="search" placeholder="Search">
		</div>
		<div class="search_result">
		<!-- heading starts here  -->
		<div class="table_section1">
		<div class="table_outer_row">
		<table width="100%" id="tblData">
		 <thead>
			 <tr class="row_head">
			 
			 <!-- Changed code  by senthil on 16-11-2017 start -->
			 <th width="10.3%"><div class="header_stop" style="width:10.3%;" style="text-align: center;width: 10.0%;margin-left: -6px;">
			 <input type="checkbox" id="master" name="parent_chk" >
			 </div></th>
			 <!-- Changed code by senthil on 16-11-2017 end -->
			 
		<th width="12.5%">Sl #<div class="header_stop" style="width:12.5%;">Sl #</div></th>
		<th width="12.5%">
		Assignment ID
		<div class="header_stop" style="width:12.5%;">Assignment ID</div>
		</th>
		<th width="12.5%">
		Assignment Name
		<div class="header_stop" style="width:12.5%;">Assignment Name</div>
		</th>
		<th width="12.5%">
		Description
		<div class="header_stop" style="width:12.5%;">Description</div>
		</th>
		<th width="12.5%">
		Date
		<div class="header_stop" style="width:12.5%;">Date</div>
		</th>
		<th width="12.5%">
		Time
		<div class="header_stop" style="width:12.5%;">Time</div>
		</th>
		<th width="12.5%">
		File
		<div class="header_stop" style="width:12.5%;">File</div>
		</th>
		<!-- <th width="12.5%">Upload<div class="header_stop">Upload</div></th> -->
		<!-- <th width="12.5%">Action<div class="header_stop">Action</div></th> -->
		</tr>
		<thead>
		 <% int count=1;
		if(assessmentDomainForAssessment!=null){
			Iterator<AssessmentDomain> iteratorForAssessment = assessmentDomainForAssessment.iterator();
			int i=1;
			while(iteratorForAssessment.hasNext()){
				AssessmentDomain assessmentDomainWithVlaue = iteratorForAssessment.next();
				//String[] s1=assessmentDomainWithVlaue.getFilePath().split("/");
				
				//System.out.println("s2="+assessmentDomainWithVlaue.getFileUploadPath());
				String[] s2=null;
				String datefromdb=assessmentDomainWithVlaue.getUploadedDate();
				//System.out.println("date from db:"+datefromdb);
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					//System.out.println("formatterf::"+formatter);
Date date_display = (Date)formatter.parse(datefromdb);
//System.out.println("date_display::"+date_display);
SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
//System.out.println("newFormat::"+newFormat);
String ddmmyyyy_format = newFormat.format(date_display);


			
			%>
			
			<%if(request.getAttribute("FacultySuccess")!=null){ 
			//System.out.println("message value:"+request.getAttribute("FacultySuccess"));%>
			  <p class="su autohide">  <%= request.getAttribute("FacultySuccess")%></p>
			     <%} else if(request.getAttribute("FacultyFailure")!=null) { %>
			     <p class="autohide" style="color:red; font-size:17px; font-weight: bold; top: 11%; left: 41%; position: absolute;"><%= request.getAttribute("FacultyFailure") %></p>
			     <%} %> 
			     
			     <%if(request.getAttribute("successMessage")!=null){ 
			//System.out.println("message value:"+request.getAttribute("successMessage"));%>
			  <p class="su autohide">  <%= request.getAttribute("successMessage")%></p>
			     <%} else if(request.getAttribute("failureMessage")!=null) { %>
			     <p class="autohide" style="color:red; font-size:17px; font-weight: bold; top: 11%; left: 41%; position: absolute;"><%= request.getAttribute("failureMessage") %></p>
			     <%} 
			     //System.out.println("ases="+assessmentDomainWithVlaue.getAssessmentId());
			     
			     %> 
			     
			<tr class="result_row_tr row">
			
			 <!-- Changed code by senthil on 16-11-2017 start -->
			<td width="9.3%" > <input type="checkbox" class="sub_chk" name="sub_chk"></td>
			 <!-- Changed code by senthil on 16-11-2017 end -->
			
			 <td align="right" style="text-align:center;"><%=count++ %></td>
			<td>
		   <input type ="text" name="assessmentId<%=assessmentDomainWithVlaue.getAssessmentId()%>" value="<%=assessmentDomainWithVlaue.getAssessmentId()%>" class="<%=assessmentDomainWithVlaue.getAssessmentId()%>"/>
			</td>
			<td align="left">
	        <%=assessmentDomainWithVlaue.getAssessmentName() %>
			</td>
			<td>
			<input class="<%=assessmentDomainWithVlaue.getAssessmentId()%>" type="text" name="marks<%=assessmentDomainWithVlaue.getAssessmentId()%>" id="assessMarks" value="<%=assessmentDomainWithVlaue.getAssessmentType()%>">
			</td>
			<td>
			<input class="<%=assessmentDomainWithVlaue.getAssessmentId()%>" type="text" name="status<%=assessmentDomainWithVlaue.getAssessmentId()%>" id="status" value="<%=ddmmyyyy_format%>"
			style="text-align:center;">
			
			<input type="hidden" name="uploadedid" value="<%=uploadedid %>"></td>
	<td>
	
			<%-- <td align="left">
			<%if(s2!=null){ %>
			<!-- <input class="<%=assessmentDomainWithVlaue.getAssessmentId()%>" type="file" name="upload file" />  -->
			<%=s2[s2.length-1] %>
			<%}else{ %>
			<input class="<%=assessmentDomainWithVlaue.getAssessmentId()%>" type="file" name="upload file" />
			<%} %>
			</td> --%>
			<%-- <td>
			<a href="javascript:void(0)">
			<input id="<%=assessmentDomainWithVlaue.getAssessmentId()%>" class="aa" type="checkbox" name="checkboxGroup" value="<%=assessmentDomainWithVlaue.getAssessmentId()%>">
			</a>
			</td> --%>
			<%
			i++;
			}} %></tr>	
</table>
</div>
</div>

<%if(noDataFound!=null){ %>
	 <p class="nodata autohide"> <%=noDataFound %></p>
	<%} %>

			<p style="text-align: center;">
			<input type="button" class="add_btn1" value="submit" onclick="clickAssessmentSubmit();">
			</p>
</div>
</form>
</div>

<!-- Changed code by senthil on 16-11-2017 start -->
<div class="wrapper row" style="text-align:center;height: 34px;">
		<input id="btn-sdel" type="button" style="background: #1d87da;color: #fff;width: 94px;font-size: 12px;padding: 5px;border-radius: 4px;border: none;cursor: pointer;" value="Delete"  />
		<input id="btn-mdel" type="button" style="background: #1d87da;color: #fff;width: 94px;font-size: 12px;padding: 5px;border-radius: 4px;border: none;cursor: pointer;" value="Delete All"  />
</div>
<!-- Changed code by senthil on 16-11-2017 end -->

</div>	




     <%@ include file="../JSP/FooterViews.jsp"%>
	<script type="text/javascript">
			$(document).ready(function()
			{
				$('#search').keyup(function()
				{
					searchTable($(this).val());
				});
			});
			function searchTable(inputVal)
			{
			
				 var table = $('#tblDataA');
			        table.find('tr').each(function(index, row)
			        {
			            var allCells = $(row).find("td,input");


			            if(allCells.length > 0)
			            {
			                var found = false;
			                allCells.each(function(index, td, input)
			                {
			                    var regExp = new RegExp(inputVal, 'i');
			                    var t_value = $(td).text();
			                    var input_value = $(td).children('input').val();
			                    if(regExp.test(t_value) || regExp.test(input_value))
			                    {
			                        found = true;
			                        return false;
			                    }
			                });
			                if(found == true)$(row).show();else $(row).hide();
			            }

			        });
			        var table = $('#tblData');
			        table.find('tr').each(function(index, row)
			        {
			            var allCells = $(row).find("td,input");


			            if(allCells.length > 0)
			            {
			                var found = false;
			                allCells.each(function(index, td, input)
			                {
			                    var regExp = new RegExp(inputVal, 'i');
			                    var t_value = $(td).text();
			                    var input_value = $(td).children('input').val();
			                    if(regExp.test(t_value) || regExp.test(input_value))
			                    {
			                        found = true;
			                        return false;
			                    }
			                });
			                if(found == true)$(row).show();else $(row).hide();
			            }

			        });
			}
		</script>
		<%
			if(fileMessage!=null){ %>
<p class="autohide" style="color:green; font-size:18px; font-weight: bold; top: 50%; left: 43%; position: absolute;"><%=fileMessage %></p>
	<%} %>
		
		
</body>

<script type="text/javascript">
$(document).ready(function () { 
	
	$("#second_part").hide();
	$("#ass_tab").addClass("addbg");
	$("#second_part").css("display","none");
	$(".tab2second").show();
	
	
	$("#ass_tab").click(function() {
		$(this).addClass("addbg");
		$("#assi_tab").removeClass("addbg");
		$("#ass_tab").removeClass("addbg1");
		/* $("#second_part").hide(); */
		$("#tab2second").hide();
		 $("#first_part").show();
		 $("#second_part").hide();
		 $(".tabmain").removeClass("tab2");
		 
		 });
	$("#assi_tab").click(function() {
		
		$(this).addClass("addbg");
		$("#ass_tab").addClass("addbg1");
		$("#ass_tab").removeClass("addbg");
		 $("#second_part").show();
		 $("#first_part").css("display","none");
			// $(".select_tr").hide();
		 });
	
});
</script>
<script>
onloadAssess();
onloadAssign();
</script>

<!-- Changed code by senthil 16-11-2017 start -->
<script type="text/javascript">
  $(document).ready(function(){
   $('#btn-sdel').hide();
   $('#btn-mdel').hide();
   $('input[name="sub_chk"]').click(function(){
    getSelectedCheckboxes('sub_chk');
   });
   var getSelectedCheckboxes=function(groupName){
    var result=$('input[name="' +groupName+ '"]:checked');
    if(result.length>0){
     $('#btn-sdel').show();
     $('#btn-mdel').hide();
    }
    else{
     $('#btn-sdel').hide();
    }
   }
  });
 </script>
  <script type="text/javascript">
  $(document).ready(function(){
    $('input[name="parent_chk"]').change(function(){
      if($(this).is(':checked',true)){
        $('input[name="sub_chk"]').prop('checked', true); 
        $('#btn-mdel').show(); 
        $('#btn-sdel').hide();
      }  
      else {  
        $('input[name="sub_chk"]').prop('checked',false);  
        $('#btn-mdel').hide(); 
      }  
    });
    // Changing state of CheckAll checkbox 
    $(".sub_chk").click(function(){
       if($(".sub_chk").length == $(".sub_chk:checked").length) {
	         $("#master").prop("checked", true);
	         $('#btn-sdel').hide();
       } else {
	         $("#master").removeAttr("checked");   
       }
    });
  });
 </script>


</html>