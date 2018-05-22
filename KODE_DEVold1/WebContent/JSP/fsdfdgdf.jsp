
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.kds.KODE_DEV.domain.RetriveImagesDomain"%>
     <%@page import="com.kds.KODE_DEV.domain.AssessmentDomain"%>
     <%@page import="com.kds.KODE_DEV.domain.DisplayCoursesDomain"%>
     <%@page import="com.kds.KODE_DEV.domain.AssessmentsDetailsDomain"%>
          <%@page import="com.kds.KODE_DEV.domain.CourseFacultyDomain" %>
<%@page import="com.kds.KODE_DEV.dao.CollaborateDao" %>
     
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
	    
	    
	    System.out.println("values is greater");
	    //1st form
        ArrayList<AssessmentsDetailsDomain> assessmentDomainForAssessment = new ArrayList<AssessmentsDetailsDomain>();
		
        assessmentDomainForAssessment = (ArrayList<AssessmentsDetailsDomain>)request.getAttribute("arl");
        
        if(assessmentDomainForAssessment!=null)
        	System.out.println("assessmentDomainForAssessment==========="+assessmentDomainForAssessment.size());
        else
        	System.out.println("value is null");
		//2nd form
	 	ArrayList<AssessmentsDetailsDomain> assessmentDomainForAssignment = new ArrayList<AssessmentsDetailsDomain>();
		
		assessmentDomainForAssignment = (ArrayList<AssessmentsDetailsDomain>)request.getAttribute("recipientTypeForAssignment");
	    
 	%>
 	<script type="text/javascript">
 	
 	function clickAssessmentSubmit(){
 		//var checkboxgroup=document.getElementById("checkboxGroup").value;
 		//alert(checkboxgroup);
 		alert(clickAssessmentSubmit);
 		document.cerify.action="/KODE_DEV/ControllerServlet/FileUploadService";
 		document.cerify.submit();	
 			
 	}
 	
 	function clickAssignmentSubmit(){
 		alert(clickAssessmentSubmit);
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
	
	  $('#courses').change(function () {
		  	 var selectedValue = this.selectedOptions[0].value;
		     var selectedText  = this.selectedOptions[0].text;
		   
		     var ctx = "${pageContext.request.contextPath}";
		   	$.get('${pageContext.request.contextPath}/getSubjectsForFaculty', {selectedValue : selectedValue},
		    		 
		    		 function(jsonResponse) {
		    			console.log("json response :",jsonResponse);
	    	     		var select = $('#subjects');
	    	     		select.find('option').remove();
	    	     		jsonResponse = $.parseJSON(jsonResponse);
	    	     		console.log("parsed json response :", jsonResponse);
	    	     		$('<option>').val('').text('Select Subject*').appendTo(select);
	    	     		$.each(jsonResponse, function(index, value) {
			    	 	
			    	 			 $('<option>').val(value.subjectId).text(value.subject).appendTo(select);
			    	      }); 
		    			
		    		});
		    		
		 });
	  
	  $('#subjects').change(function () {
		  	 var selectedValue = this.selectedOptions[0].value;
		     var selectedText  = this.selectedOptions[0].text;
		   
		  	var courseid=document.getElementById("courses").value;
			var subjectid=document.getElementById("subjects").value;
			     var ctx = "${pageContext.request.contextPath}";
		   	     $.get('${pageContext.request.contextPath}/getAssignmentsForSubjectCourse', {selectedValue : selectedValue,courseid : courseid,subjectid : subjectid},
		    		 function(jsonResponse){
		    		    console.log("json response :",jsonResponse);
	    	     		var select = $('#asessID');
	    	     		select.find('option').remove();
	    	     		jsonResponse = $.parseJSON(jsonResponse);
	    	     		console.log("parsed json response :", jsonResponse);
	    	     		$('<option>').val('').text('Choose Assignment').appendTo(select);
	    	     		$('<option>').val('All').text('All').appendTo(select);
	    	     			$.each(jsonResponse, function(index, value)
	    	     			{
			    	 		  	console.log('Index value here :',index);
			    	 		 	console.log('Actual obj value here :',value.subject);
			    	 			$('<option>').val(value.Assignment_ID).text(value.Assignment_name).appendTo(select);
			    	 			 
			    	    	}); 
		    		   });
		   
		 	  });
});

	  


	  
	  
	  
	  
	  
function clickAssessmentID(){
	
	var assessid=document.getElementById("asessID").value;
	var uploadby=document.getElementById("uploadby").value;
	var courses=document.getElementById("courses").value;
	var subject=document.getElementById("subjects").value;
 	alert("assessment id:"+assessid);
	alert("uploadby id:"+uploadby);
	alert("course="+courses);
	alert("subject:"+subject); 
	
	
	document.cerify.action="/KODE_DEV/ControllerServlet/ParticipantAssesmentsViewService?assessmentId="+assessid+"&facultyName="+uploadby+"&courses="+courses+"&subject="+subject+"";
	
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
	
	alert(assignid);
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
	$(this).attr("disabled",true);
	});
	$('.row select').each(function()
			{
			$(this).attr("disabled",true);
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
	
	$("." + id).attr("disabled",true);	
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
		<p class="strong">View Assignment</p>
		
		<form name="cerify" method="post" enctype="multipart/form-data">
		
		          <select id="courses" name="courses" class="select-box box_lng" style="margin-bottom:10px;margin-left:0px;">
	<option value="">Select Course*</option>
									<%
									//	GetCourseForAdminDao getCourseForAdminDao = new GetCourseForAdminDao();
									//  List<CourseAdminDomain> courses = getCourseForAdminDao.getCourseForAdminFaculty(orgid, userid);
										//HashSet set =(HashSet)al.fetchValue();
									//  for(CourseAdminDomain course : courses) {
									//	System.out.println("course :"+course.getCourseId());
									//  String result = course.getCourseId();
										String result="";
										
										GetCourseForFacultyDao getCourseForFacultyDao = new GetCourseForFacultyDao();
										List<CourseFacultyDomain> courses = getCourseForFacultyDao.getCourseInAssignmentForFaculty(facultyId, orgId);
										for(CourseFacultyDomain course : courses)
										{
											System.out.println("course :"+course.getCourseId());
											result = course.getCourseId();
									%>
											<option value="<%=result%>"><%=result%></option>
									<%
										}
									%>
									
</select>
<select id="subjects" name="subjects" class="select-box box_lng" style="margin-left:0px;">
	<option  value="">Select Subjects</option>
</select> 

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
		                      <option  value="<%= name %>"><%=idname%></option>	 --%>
		                         <%  }%>
		                      </select>
	               
		
		<div class="">
		<input class="box1" type="text" id="search" placeholder="Search">
		</div>
		<div class="search_result">
		<!-- heading starts here  -->
		<div class="table_section1">
		<div class="table_outer_row">
		<table width="100%" id="tblData">
		 <thead>
			 <tr class="row_head">
<th width="6%">Sl #<div class="header_stop" style="width:6%;">Sl #</div></th>
		<th width="10%">
		Recipient Type
		<div class="header_stop" style="width:10%;">Recipient Type</div>
		</th>
		<th width="12.5%">
		Assignment Name
		<div class="header_stop" style="width:12.5%;">Assignment Name</div>
		</th>
		
		<th width="11%">
		Assignment Type
		<div class="header_stop" style="width:11%;">Assignment Type</div>
		</th>
		
		<th width="12.5%">
		Description
		<div class="header_stop" style="width:12.5%;">Description</div>
		</th>
		<th width="10%">
		Date Created
		<div class="header_stop" style="width:10%;">Date Created</div>
		</th>
		
		<th width="10%">
		Due Date
		<div class="header_stop" style="width:10%;">Due Date</div>
		</th>
		
		
		<th width="8%">
		Total Marks
		<div class="header_stop" style="width:8%;">Total Marks</div>
		</th>
		<th width="8%">
		Status
		<div class="header_stop" style="width:8%;">Status</div>
		</th>
		<th width="8%">
		File
		<div class="header_stop" style="width:8%;">File</div>
		</th>
		<!-- <th width="12.5%">Upload<div class="header_stop">Upload</div></th> -->
		<!-- <th width="12.5%">Action<div class="header_stop">Action</div></th> -->
		</tr>
		<thead>
		 <% int count=1;
		 System.out.print("first step");
		if(assessmentDomainForAssessment==null)
			System.out.println("is getting null");
		if(assessmentDomainForAssessment!=null){
			Iterator<AssessmentsDetailsDomain> iteratorForAssessment = assessmentDomainForAssessment.iterator();
			int i=1;
			while(iteratorForAssessment.hasNext()){
				AssessmentsDetailsDomain assessmentDomainWithVlaue = iteratorForAssessment.next();
				String[] s1=assessmentDomainWithVlaue.getFilePath().split("/");
				
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

String dueDate=assessmentDomainWithVlaue.getDueDate();
System.out.println("dueDate="+dueDate);
DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
Date endDate=(Date)formatter1.parse(dueDate);
SimpleDateFormat newFormat1 = new SimpleDateFormat("dd/MM/yyyy");
String endDateDisplay=newFormat1.format(endDate); 



System.out.println("endDateDisplay="+endDateDisplay);


				if(assessmentDomainWithVlaue.getFileUploadPath()!=null)
					s2=assessmentDomainWithVlaue.getFileUploadPath().split("/");
				//else
					//s2=[];
				//System.out.println(s2.toString());
				String time=assessmentDomainWithVlaue.getUploadedTime();
				String time_format=time.substring(0,5);
				//System.out.println("time_format="+time_format);
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
			 <td align="right" style="text-align:center;"><%=count++ %></td>
			<td>
		           	<%=assessmentDomainWithVlaue.getRecipientType()%>
		         
		           	
			</td>
			<td align="left">
			  <input type ="hidden" name="assessmentId<%=assessmentDomainWithVlaue.getAssessmentId()%>" readonly="readonly" value="<%=assessmentDomainWithVlaue.getAssessmentId()%>" class="<%=assessmentDomainWithVlaue.getAssessmentId()%>"/>
			<%=assessmentDomainWithVlaue.getAssessmentName() %>
	        
			</td>
			
			<td>
			<input class="<%=assessmentDomainWithVlaue.getAssessmentId()%>" type="text" name="marks<%=assessmentDomainWithVlaue.getAssessmentId()%>" id="assessMarks" value="<%=assessmentDomainWithVlaue.getAssessment_type()%>">
			</td>
			<td>
			<input class="<%=assessmentDomainWithVlaue.getAssessmentId()%>" type="text" name="marks<%=assessmentDomainWithVlaue.getAssessmentId()%>" id="assessMarks" value="<%=assessmentDomainWithVlaue.getAssessmentDescription()%>">
			</td>
			
			<td>
			 <input class="<%=assessmentDomainWithVlaue.getAssessmentId()%>" type="text" name="status<%=assessmentDomainWithVlaue.getAssessmentId()%>" id="status" value="<%=endDateDisplay%>"
			style="text-align:center;">
			<input type="hidden" name="uploadedid" value="<%=uploadedid %>"></td>	 		
			<td>
			<input class="<%=assessmentDomainWithVlaue.getAssessmentId()%>" type="text" name="status<%=assessmentDomainWithVlaue.getAssessmentId()%>" id="status" value="<%=ddmmyyyy_format%>"
			style="text-align:center;">
			
			<input type="hidden" name="uploadedid" value="<%=uploadedid %>"></td>
	<td>
	<input class="<%=assessmentDomainWithVlaue.getAssessmentId()%>" type="text" name="marks<%=assessmentDomainWithVlaue.getAssessmentId()%>" id="marks" value="<%=assessmentDomainWithVlaue.getTotalMarks()%>" style="text-align:center;">
	</td>
	<td>
	<% 
	     String status="No Status";
	     if(assessmentDomainWithVlaue.getStatus()!=null )
	    	 status=assessmentDomainWithVlaue.getStatus();
	
	%>
	<input class="<%=assessmentDomainWithVlaue.getAssessmentId()%>" type="text" name="status<%=assessmentDomainWithVlaue.getAssessmentId()%>" id="status" value="<%=status%>" style="text-align:center;">
	</td>
			<td align="left"><a href="/KODE_DEV/ControllerServlet/AssignDownloadfileInView?filePath=/home/ftpuser1/<%=assessmentDomainWithVlaue.getFilePath()%>"><%=s1[s1.length-1] %></a>
			</td>
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
			}} %>	
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
</div>	
	<!-- second part starts here -->
	
<%-- 	<div class="postpon_mod <%=Tabmsg%>second" id="second_part">
	<div style="text-align: center;">
		<p class="strong">Certify Assignment</p>
		
	   <form name="AssignCertify" method="post" enctype="multipart/form-data">

         
							 <%
							 
							 ParticipantAssignmentsDao PAssignDao = new ParticipantAssignmentsDao();
		
                              ArrayList<RetriveImagesDomain> arrayListForAssignment = new ArrayList<RetriveImagesDomain>();
                              arrayListForAssignment=PAssignDao.selectCourses(ri);
		                      Iterator<RetriveImagesDomain> iteratorForAssignment= arrayListForAssignment.iterator();%>
		                      <select name="asignID" id="asignID" class="box_lng" onchange="clickAssignmentID();" style="margin-left: 0px">
		                      <option value="">Choose Assignment ID </option>
		                      <option value="All">All</option>
		                      
		                    <% while(iteratorForAssignment.hasNext())
		                     {
		                    	RetriveImagesDomain adomain=iteratorForAssignment.next();
		    	             String Id=adomain.getCourseName();
		    	             String name=adomain.getCourseImageSize();
		    	             String idname=Id+"("+name+")";%>
		                      <option  value="<%= name %>"><%=idname%></option>	
		                         <%  }%>
		                      </select>
	    <div class="search_div">
		<input class="box1" type="text" id="search" placeholder="Search">
		</div>
		<div class="search_result">	
	    	<table width="100%" id="tblDataA">
		 <tr height="25px"></tr>
			 <tr class="row_head">

		<td>
		Assignment ID
		</td>
		<td style="width: 15%">
		Assignment Name
		</td>
		<td>
		Description
		</td>
		<td>
		Date
		</td>
		<td>
		File
		</td>
		<td>Upload</td>
		<td>Action</td>
		</tr>
		<% 
		
		if(assessmentDomainForAssignment!=null){
			Iterator<AssessmentsDetailsDomain> iteratorForAssign = assessmentDomainForAssignment.iterator();
			while(iteratorForAssign.hasNext()){
				AssessmentsDetailsDomain assignmentDomainWithValue = iteratorForAssign.next();
				String[] s1=assignmentDomainWithValue.getFilePath().split("/");
				String[] s2=null;
				if(assignmentDomainWithValue.getFileUploadPath()!=null)
				 	 s2=assignmentDomainWithValue.getFileUploadPath().split("/");
				
			%>
			
			<%if(request.getAttribute("FacultySuccess")!=null){ 
			//System.out.println("message value:"+request.getAttribute("FacultySuccess"));%>
			  <p class="su autohide">  <%= request.getAttribute("FacultySuccess")%></p>
			     <%} else if(request.getAttribute("FacultyFailure")!=null) { %>
			     <p class="autohide" style="color:red; font-size:17px; font-weight: bold; top: 11%; left: 41%; position: absolute;"><%= request.getAttribute("FacultyFailure") %></p>
			     <%} 
			    // System.out.println("asign="+assignmentDomainWithValue.getAssessmentId());
			     %>
			<tr class="result_row_tr row">
			<td>
		   <input type ="text" name="assignmentId<%=assignmentDomainWithValue.getAssessmentId()%>" readonly	value="<%=assignmentDomainWithValue.getAssessmentId()%>" class="<%=assignmentDomainWithValue.getAssessmentId()%>"/>
			</td>
			<td>
	        <%=assignmentDomainWithValue.getAssessmentName() %>
			</td>
			<td>
			 <input class="<%=assignmentDomainWithValue.getAssessmentId()%>" type="text" name="marks<%=assignmentDomainWithValue.getAssessmentId()%>" id="assessMarks" value="<%=assignmentDomainWithValue.getAssessmentDescription()%>">  
			</td>
			<td>
			<input class="<%=assignmentDomainWithValue.getAssessmentId()%>" type="text" name="status<%=assignmentDomainWithValue.getAssessmentId()%>" id="status"  value="<%=assignmentDomainWithValue.getUploadedDate() %>">
			
			<input type="hidden" name="uploadedid" value="<%=uploadedid %>"></td>
	
			<td>
			<a href="/KODE_DEV/ControllerServlet/AssignDownloadfile?filePath=<%=assignmentDomainWithValue.getFilePath()%>"><%=s1[s1.length-1] %></a>
			</td>
			
			<td>
			<%if(s2!=null){ %>
	 <!-- <input class="<%=assignmentDomainWithValue.getAssessmentId()%>" type="file" name="upload file" />  -->
	 <%=s2[s2.length-1] %>
	 <%}else{ %>
	 <input class="<%=assignmentDomainWithValue.getAssessmentId()%>" type="file" name="upload file" />
	 <%} %>
</td>
<td>
			<a href="javascript:void(0)">
			<input id="<%=assignmentDomainWithValue.getAssessmentId()%>" class="aa" type="checkbox" name="checkboxGroup" value="<%=assignmentDomainWithValue.getAssessmentId()%>">
			</a>
			</td>
			<%}} %>	
</table>

<%if(noDataFound!=null){ %>
	 <p class="nodata autohide"> <%=noDataFound %></p>
	<%} %>


			<p>
			<input type="button" class="add_btn1"  value="submit" onclick="clickAssignmentSubmit();">
			</p>
			</div>
</form>
         
         </div> 
         </div> --%>
         
		
	</div>
	
<!-- Second parts end here -->	

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
				/* var tablea = $('#tblDataA');
				tablea.find('tr').each(function(index, row)
				{
					var allCells = $(row).find('td');
					if(allCells.length > 0)
					{
						var found = false;
						allCells.each(function(index, td)
						{
							var regExp = new RegExp(inputVal, 'i');
							if(regExp.test($(td).text()))
							{
								found = true;
								return false;
							}
						});
						/* if(found == true)
							{
							alert("message test");
							
							}
						 
						//if(found == true)$('tr').show();else $(row).hide();
						 if(found == true)$(row).show();else $(row).hide();
						/* alert("message"); 
					}
				});
			
				var table = $('#tblData');
				table.find('tr').each(function(index, row)
				{
					var allCells = $(row).find('td');
					if(allCells.length > 0)
					{
						var found = false;
						allCells.each(function(index, td)
						{
							var regExp = new RegExp(inputVal, 'i');
							if(regExp.test($(td).text()))
							{
								found = true;
								return false;
							}
						});
						/* if(found == true)
							{
							alert("message test");
							
							}
						 
						//if(found == true)$('tr').show();else $(row).hide();
						 if(found == true)$(row).show();else $(row).hide();
						/* alert("message"); 
					}
				}); */
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
</html>