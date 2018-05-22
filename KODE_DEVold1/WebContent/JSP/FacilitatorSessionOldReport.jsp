<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.kds.KODE_DEV.dao.FacilitatorSessionReportDao"%>
<%@ page import="com.kds.KODE_DEV.domain.SessionDomain"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@page import="com.kds.KODE_DEV.domain.CourseFacultyDomain" %>
<%@page import="com.kds.KODE_DEV.dao.CollaborateDao" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="java.util.*"%>
  <%@page import="java.io.*"%>
  <%@page import="java.text.*" %>  
  <%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.js"></script>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<script src="../JS/datetimepicker_css.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript" src="../JS/validatePostSession.js"></script>
<link href="../CSS/jquery.datetimepicker.css" rel="stylesheet"></link>
<link href="../CSS/table_scroll.css" rel="stylesheet"/>
<link href="../CSS/design-common.css" rel="stylesheet"/>
<!-- <script type="text/javascript" src="../JS/jquery.datetimepicker.js">
</script> -->
<style type="text/css">
.search_result{padding: 0px 10px;margin-bottom: 25px;max-height: 250px;
}
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
    opacity:1;
    margin-top: 75px;
    
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
margin-top: 12px;
}
.opt{
width: 265px !important;
padding: 7px !important;
border: 1px solid #C2C2C2;
font-family: regular;
border-radius: 4px;
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
.result_row_tr td input{border: none; background: none; width: 99.5%;}
.starttime{width: 130px;}
.endtime{width: 120px;}
.duration_r{width: 120px;}
.cost_r{width: 80px;}
.result_row_tr td{padding: 4px 4px 4px 10px;}
</style>
</head>

<script type="text/javascript" language="javascript">
      
        $(document).ready(function() {
        	$(".autohide").delay(5000).fadeOut("slow");
        	
        	
      	  $('#courses').change(function () {
 		  	 var selectedValue = this.selectedOptions[0].value;
 		     var selectedText  = this.selectedOptions[0].text;
 		     // alert(selectedText);
 		     // alert(selectedValue);
 		     var ctx = "${pageContext.request.contextPath}";
 		     // alert(ctx);
 		     $.get('${pageContext.request.contextPath}/getSubjectsForFaculty', {selectedValue : selectedValue},
 		    		  function(jsonResponse) {
 		    			// alert(jsonResponse);
 		    			// alert("inside");
 		    			console.log("json response :",jsonResponse);
 	    	     		var select = $('#subject');
 	    	     		select.find('option').remove();
 	    	     		jsonResponse = $.parseJSON(jsonResponse);
 	    	     		console.log("parsed json response :", jsonResponse);
 	    	     		$('<option>').val('').text('Choose Subject').appendTo(select);
 	    	     		$.each(jsonResponse, function(index, value) {
 			    	 		  console.log('Index value here :',index);
 			    	 		 console.log('Actual obj value here :',value.subject);
 			    	 			 $('<option>').val(value.subjectId).text(value.subject).appendTo(select);
 			    	      }); 
 		    			
 		    		});
 		    $.get('${pageContext.request.contextPath}/getSessionsForCourse', {selectedValue : selectedValue},
		    		  
 		    		
 		    		function(jsonResponse)
 		    		{
 		    			var select = $('#tblData');
 		    			
 		    			jsonResponse = $.parseJSON(jsonResponse);
 		    			select.find('tr').remove();
 		    			var headTable='<thead><tr class="row_head"><th width="12.5%">Session ID'+
 		    			'<div class="header_stop" style="width:12.5%;">Session ID</div>'+
 		    			'</th><th width="12.5%">Session Name<div class="header_stop" style="width:12.5%;">Session Name</div>'+
 		    			'</th><th width="12.5%">Category<div class="header_stop" style="width:12.5%;">Category</div></th>'+
 		    			'<th width="12.5%">Start Date<div class="header_stop" style="width:12.5%;">Start Date</div></th>'+
 		    			'<th width="12.5%">Start Time<div class="header_stop" style="width:12.5%;">Start Time</div></th>'+
 		    			'<th width="12.5%">End Time<div class="header_stop" style="width:12.5%;">End Time</div></th>'+
 		    			'<th width="12.5%">Duration<div class="header_stop" style="width:12.5%;">Duration</div></th>'+
 		    			'<th>Recipient Type<div class="header_stop"  style="width:12.5%;">Recipient Type</div></th>'+
 		    			'</tr></thead>'	;	
 		    			select.append(headTable);
 		    			$.each(jsonResponse, function(index, value) 
 		    			{
	    	     					var sessionId=value.sessionId;
	    	     					var sessionName=value.sessionName;
	    	     					var category=value.category;
	    	     					var date=value.date;
	    	     					
	    	     					
	    	     					var startTime=value.sessionStartTime;
	    	     					//alert(startTime.getTime());
	    	     					//var d = new Date(startTime);
	    	     					//alert(d.getTime());
	    	     					
	    	     					    var d = new Date(startTime),
	    	     					        month = '' + (d.getMonth() + 1),
	    	     					        day = '' + d.getDate(),
	    	     					        year = d.getFullYear();

	    	     					    if (month.length < 2) month = '0' + month;
	    	     					    if (day.length < 2) day = '0' + day;
										var hours=d.getHours();
										var mins=d.getMinutes();
										  if(hours<10)
		    	     						  hours='0'+hours;
									      mins=d.getMinutes();
									      if(mins<10)
									    	  mins='0'+mins;
	    	     					    var newDate=day+'-'+month+'-'+year;
	    	     					   startTime=hours+':'+mins;
	    	     					   //alert(newDate);
	    	     					
	    	     					var endTime=value.sessionEndTime;
	    	     					  d = new Date(endTime);
	    	     					  hours=d.getHours();
	    	     					  if(hours<10)
	    	     						  hours='0'+hours;
								      mins=d.getMinutes();
								      if(mins<10)
								    	  mins='0'+mins;
	    	     					   
	    	     					   endTime=hours+':'+mins;
	    	     					
	    	     					var duration = value.duration;
	    	     					var recipientType=value.recipientType;
								   //	alert(enrollementId); 
								   	var fullName="dd";
								   	var str='<tr class="result_row_tr"><td>'+sessionId+'</td><td>'+sessionName+'</td><td>'+category+'</td><td>'+newDate+'</td><td>'+startTime+'</td><td>'+endTime+'</td><td>'+duration+'</td><td>'+recipientType+'</td></tr>';
		   	     			 		$("#tblData").append(str);
			    	    }); 
 		    		});
 		 });
 	  $('#subject').change(function () {
 		  	 var selectedValue = this.selectedOptions[0].value;
 		     var selectedText  = this.selectedOptions[0].text;
 		   //  alert(selectedText);
 		     //alert(selectedValue);
 		     var ctx = "${pageContext.request.contextPath}";
 		    //  alert(ctx);
 		     $.get('${pageContext.request.contextPath}/getTopics', {selectedValue : selectedValue},
 		    		  function(jsonResponse) {
 		    			//alert(jsonResponse);
 		    		//	alert("inside");
 		    			console.log("json response :",jsonResponse);
 	    	     		var select = $('#topic');
 	    	     		select.find('option').remove();
 	    	     		jsonResponse = $.parseJSON(jsonResponse);
 	    	     		console.log("parsed json response :", jsonResponse);
 	    	     		$('<option>').val('').text('Choose Topic').appendTo(select);
 	    	     		$.each(jsonResponse, function(index, value) {
 			    	 		  console.log('Index value here :',index);
 			    	 		 console.log('Actual obj value here :',value.subject);
 			    	 			 $('<option>').val(value.topicId).text(value.topic).appendTo(select);
 			    	      }); 
 		    			
 		    		});
 $.get('${pageContext.request.contextPath}/getSessionsForSubject', {selectedValue : selectedValue},
		    		  
 		    		
 		    		function(jsonResponse)
 		    		{
 		    			var select = $('#tblData');
 		    			
 		    			jsonResponse = $.parseJSON(jsonResponse);
 		    			select.find('tr').remove();
 		    			var headTable='<thead><tr class="row_head"><th width="12.5%">Session ID'+
 		    			'<div class="header_stop" style="width:12.5%;">Session ID</div>'+
 		    			'</th><th width="12.5%">Session Name<div class="header_stop" style="width:12.5%;">Session Name</div>'+
 		    			'</th><th width="12.5%">Category<div class="header_stop" style="width:12.5%;">Category</div></th>'+
 		    			'<th width="12.5%">Start Date<div class="header_stop" style="width:12.5%;">Start Date</div></th>'+
 		    			'<th width="12.5%">Start Time<div class="header_stop" style="width:12.5%;">Start Time</div></th>'+
 		    			'<th width="12.5%">End Time<div class="header_stop" style="width:12.5%;">End Time</div></th>'+
 		    			'<th width="12.5%">Duration<div class="header_stop" style="width:12.5%;">Duration</div></th>'+
 		    			'<th>Recipient Type<div class="header_stop"  style="width:12.5%;">Recipient Type</div></th>'+
 		    			'</tr></thead>'	;	
 		    			select.append(headTable);
 		    			
 		    			$.each(jsonResponse, function(index, value) 
 		    			{
	    	     					var sessionId=value.sessionId;
	    	     					var sessionName=value.sessionName;
	    	     					var category=value.category;
	    	     					var date=value.date;
	    	     					
	    	     					
	    	     					var startTime=value.sessionStartTime;
	    	     					//alert(startTime.getTime());
	    	     					//var d = new Date(startTime);
	    	     					//alert(d.getTime());
	    	     					
	    	     					    var d = new Date(startTime),
	    	     					        month = '' + (d.getMonth() + 1),
	    	     					        day = '' + d.getDate(),
	    	     					        year = d.getFullYear();

	    	     					    if (month.length < 2) month = '0' + month;
	    	     					    if (day.length < 2) day = '0' + day;
										var hours=d.getHours();
										var mins=d.getMinutes();
										  if(hours<10)
		    	     						  hours='0'+hours;
									      mins=d.getMinutes();
									      if(mins<10)
									    	  mins='0'+mins;
	    	     					    var newDate=day+'-'+month+'-'+year;
	    	     					   startTime=hours+':'+mins;
	    	     					   //alert(newDate);
	    	     					
	    	     					var endTime=value.sessionEndTime;
	    	     					  d = new Date(endTime);
	    	     					  hours=d.getHours();
	    	     					  if(hours<10)
	    	     						  hours='0'+hours;
								      mins=d.getMinutes();
								      if(mins<10)
								    	  mins='0'+mins;
	    	     					   
	    	     					   endTime=hours+':'+mins;
	    	     					
	    	     					var duration = value.duration;
	    	     					var recipientType=value.recipientType;
								   //	alert(enrollementId); 
								   	var fullName="dd";
								   	var str='<tr class="result_row_tr"><td>'+sessionId+'</td><td>'+sessionName+'</td><td>'+category+'</td><td>'+newDate+'</td><td>'+startTime+'</td><td>'+endTime+'</td><td>'+duration+'</td><td>'+recipientType+'</td></tr>';
		   	     			 		$("#tblData").append(str);
			    	    }); 
 		    		
 		    		});

 		   
 		 });
 	  
 	
 	  $('#topic').change(function () {
 		  	 var selectedValue = this.selectedOptions[0].value;
 		     var selectedText  = this.selectedOptions[0].text;
 		    // alert(selectedText);
 		     // alert(selectedValue);
 		     var ctx = "${pageContext.request.contextPath}";
 		 
 		     $.get('${pageContext.request.contextPath}/getSubTopics', {selectedValue : selectedValue},
 		    		  function(jsonResponse) {
 		    			// alert(jsonResponse);
 		    			//alert("inside");
 		    			console.log("json response :",jsonResponse);
 	    	     		var select = $('#subtopic');
 	    	     		select.find('option').remove();
 	    	     		jsonResponse = $.parseJSON(jsonResponse);
 	    	     		console.log("parsed json response :", jsonResponse);
 	    	     		$('<option>').val('').text('Choose SubTopic').appendTo(select);
 	    	     		$.each(jsonResponse, function(index, value) {
 			    	 		  console.log('Index value here :',index);
 			    	 		 console.log('Actual obj value here :',value.subject);
 			    	 			 $('<option>').val(value.subTopicId).text(value.subTopic).appendTo(select);
 			    	      }); 
 		    			
 		    		});
 		   
 		 
 	  
 	 $.get('${pageContext.request.contextPath}/getSessionsForTopic', {selectedValue : selectedValue},
   		  
	    		
	    		function(jsonResponse)
	    		{
	    			var select = $('#tblData');
	    		
	    			jsonResponse = $.parseJSON(jsonResponse);
	    			select.find('tr').remove();
	    			var headTable='<thead><tr class="row_head"><th width="12.5%">Session ID'+
	    			'<div class="header_stop" style="width:12.5%;">Session ID</div>'+
	    			'</th><th width="12.5%">Session Name<div class="header_stop" style="width:12.5%;">Session Name</div>'+
	    			'</th><th width="12.5%">Category<div class="header_stop" style="width:12.5%;">Category</div></th>'+
	    			'<th width="12.5%">Start Date<div class="header_stop" style="width:12.5%;">Start Date</div></th>'+
	    			'<th width="12.5%">Start Time<div class="header_stop" style="width:12.5%;">Start Time</div></th>'+
	    			'<th width="12.5%">End Time<div class="header_stop" style="width:12.5%;">End Time</div></th>'+
	    			'<th width="12.5%">Duration<div class="header_stop" style="width:12.5%;">Duration</div></th>'+
	    			'<th>Recipient Type<div class="header_stop"  style="width:12.5%;">Recipient Type</div></th>'+
	    			'</tr></thead>'	;	
	    			select.append(headTable);
	    			$.each(jsonResponse, function(index, value) 
	    			{
 	     					var sessionId=value.sessionId;
 	     					var sessionName=value.sessionName;
 	     					var category=value.category;
 	     					var date=value.date;
 	     					
 	     					
 	     					var startTime=value.sessionStartTime;
 	     					//alert(startTime.getTime());
 	     					//var d = new Date(startTime);
 	     					//alert(d.getTime());
 	     					
 	     					    var d = new Date(startTime),
 	     					        month = '' + (d.getMonth() + 1),
 	     					        day = '' + d.getDate(),
 	     					        year = d.getFullYear();

 	     					    if (month.length < 2) month = '0' + month;
 	     					    if (day.length < 2) day = '0' + day;
									var hours=d.getHours();
									var mins=d.getMinutes();
									  if(hours<10)
	    	     						  hours='0'+hours;
								      mins=d.getMinutes();
								      if(mins<10)
								    	  mins='0'+mins;
 	     					    var newDate=day+'-'+month+'-'+year;
 	     					   startTime=hours+':'+mins;
 	     					 
 	     					
 	     					var endTime=value.sessionEndTime;
 	     					  d = new Date(endTime);
 	     					  hours=d.getHours();
 	     					  if(hours<10)
 	     						  hours='0'+hours;
							      mins=d.getMinutes();
							      if(mins<10)
							    	  mins='0'+mins;
 	     					   
 	     					   endTime=hours+':'+mins;
 	     					
 	     					var duration = value.duration;
 	     					var recipientType=value.recipientType;
							   //	alert(enrollementId); 
							   	var fullName="dd";
							   	var str='<tr class="result_row_tr"><td>'+sessionId+'</td><td>'+sessionName+'</td><td>'+category+'</td><td>'+newDate+'</td><td>'+startTime+'</td><td>'+endTime+'</td><td>'+duration+'</td><td>'+recipientType+'</td></tr>';
	   	     			 		$("#tblData").append(str);
		    	    }); 
	    		});
 	 });
 	  $('#subtopic').change(function(){
  		 var selectedValue = this.selectedOptions[0].value;
 		     var selectedText  = this.selectedOptions[0].text;
 		    // alert(selectedText);
 		     // alert(selectedValue);
 		     var ctx = "${pageContext.request.contextPath}";
  	
  		 $.get('${pageContext.request.contextPath}/getSessionsForSubTopic', {selectedValue : selectedValue},
 	    		  
 		    		
 		    		function(jsonResponse)
 		    		{
 		    			var select = $('#tblData');
 		    		
 		    			jsonResponse = $.parseJSON(jsonResponse);
 		    			select.find('tr').remove();
 		    			var headTable='<thead><tr class="row_head"><th width="12.5%">Session ID'+
 		    			'<div class="header_stop" style="width:12.5%;">Session ID</div>'+
 		    			'</th><th width="12.5%">Session Name<div class="header_stop" style="width:12.5%;">Session Name</div>'+
 		    			'</th><th width="12.5%">Category<div class="header_stop" style="width:12.5%;">Category</div></th>'+
 		    			'<th width="12.5%">Start Date<div class="header_stop" style="width:12.5%;">Start Date</div></th>'+
 		    			'<th width="12.5%">Start Time<div class="header_stop" style="width:12.5%;">Start Time</div></th>'+
 		    			'<th width="12.5%">End Time<div class="header_stop" style="width:12.5%;">End Time</div></th>'+
 		    			'<th width="12.5%">Duration<div class="header_stop" style="width:12.5%;">Duration</div></th>'+
 		    			'<th>Recipient Type<div class="header_stop"  style="width:12.5%;">Recipient Type</div></th>'+
 		    			'</tr></thead>'	;	
 		    			select.append(headTable);
 		    			$.each(jsonResponse, function(index, value) 
 		    			{
 	    	     					var sessionId=value.sessionId;
 	    	     					var sessionName=value.sessionName;
 	    	     					var category=value.category;
 	    	     					var date=value.date;
 	    	     					
 	    	     					
 	    	     					var startTime=value.sessionStartTime;
 	    	     					//alert(startTime.getTime());
 	    	     					//var d = new Date(startTime);
 	    	     					//alert(d.getTime());
 	    	     					
 	    	     					    var d = new Date(startTime),
 	    	     					        month = '' + (d.getMonth() + 1),
 	    	     					        day = '' + d.getDate(),
 	    	     					        year = d.getFullYear();

 	    	     					    if (month.length < 2) month = '0' + month;
 	    	     					    if (day.length < 2) day = '0' + day;
 										var hours=d.getHours();
 										var mins=d.getMinutes();
 										  if(hours<10)
 		    	     						  hours='0'+hours;
 									      mins=d.getMinutes();
 									      if(mins<10)
 									    	  mins='0'+mins;
 	    	     					    var newDate=day+'-'+month+'-'+year;
 	    	     					   startTime=hours+':'+mins;
 	    	     					   //alert(newDate);
 	    	     					
 	    	     					var endTime=value.sessionEndTime;
 	    	     					  d = new Date(endTime);
 	    	     					  hours=d.getHours();
 	    	     					  if(hours<10)
 	    	     						  hours='0'+hours;
 								      mins=d.getMinutes();
 								      if(mins<10)
 								    	  mins='0'+mins;
 	    	     					   
 	    	     					   endTime=hours+':'+mins;
 	    	     					
 	    	     					var duration = value.duration;
 	    	     					var recipientType=value.recipientType;
 								   //	alert(enrollementId); 
 								   	var fullName="dd";
 								   	var str='<tr class="result_row_tr"><td>'+sessionId+'</td><td>'+sessionName+'</td><td>'+category+'</td><td>'+newDate+'</td><td>'+startTime+'</td><td>'+endTime+'</td><td>'+duration+'</td><td>'+recipientType+'</td></tr>';
 		   	     			 		$("#tblData").append(str);
 			    	    }); 
 		    		});
  		
  	  });
 	 
 	
        	
        	
        });  
</script>

<%
String username = (String) session.getAttribute("username");
if(username==null)
	response.sendRedirect("../JSP/error.jsp?errmsg=Session Expired");
String userId = (String) session.getAttribute("userid");

String organizationId = (String) session.getAttribute("orgid");

DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
Calendar cal = Calendar.getInstance();
//System.out.println(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22
String date=dateFormat.format(cal.getTime());
//System.out.println("date:"+date);
/* Date today;
String result;
//SimpleDateFormat formatter;
formatter = new SimpleDateFormat("yyyy/MM/dd : HH/mm/ss");
today = new Date();
result = formatter.format(today);

//System.out.println("Result: " + result); */
/* SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
sdf.format(new Date());
//System.out.println("today date:"+sdf.format(new Date())); */
/* Date dateobj = new Date();
//System.out.println(dateobj); */
	%>
<body>

	<div class="container" style="position:static;">
		<%-- <%@include file="all_one_header_knowstore.jsp"%> --%>
		<%@include file="../JSP/headers.jsp"%>
		
		
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="postpon_mod">
		<div style="text-align: center;">
		<p class="strong">Completed Session Details</p>
		<form name="sessionDetails" method="post">
		 <div>
		
				<select id="courses" class="box_lng required" style="margin-bottom:10px;margin-left:0px;">
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
										List<CourseFacultyDomain> courses = getCourseForFacultyDao.getCourseForFaculty(userId);
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
			
						
							    <select name="subject" class="box_lng required" id="subject" style="margin-left:0px;">
							    	<option value="">Choose Subject</option>
							    </select>
					
							    <select name="topic" class="box_lng required" id="topic" style="margin-left:0px;">
							    	<option value="">Choose Topic</option>
							    </select>
						
							    <select name="subtopic" class="box_lng required" id="subtopic" style="margin-left:0px;">
							    	<option value="">Choose SubTopic</option>
							    </select>
									
						
		</div>

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

		<th width="12.5%">
		Session ID
		<div class="header_stop" style="width:12.5%;">Session ID</div>
		</th>
		
		<th width="12.5%">
		Session Name
		<div class="header_stop" style="width:12.5%;">Session Name</div>
		</th>
		<th width="12.5%">
		Category
		<div class="header_stop" style="width:12.5%;">Category</div>
		</th>
		<th width="12.5%">
		Start Date
		<div class="header_stop" style="width:12.5%;">Start Date</div>
		</th>
		<th width="12.5%">
		Start Time
		<div class="header_stop" style="width:12.5%;">Start Time</div>
		</th>
		<th width="12.5%">
		End Time
		<div class="header_stop" style="width:12.5%;">End Time</div>
		</th>
		<th width="12.5%">
		Duration
		<div class="header_stop" style="width:12.5%;">Duration</div>
		</th>
		<!-- <td>
		Cost
		</td> -->
		<th>
		Recipient Type
		<div class="header_stop"  style="width:12.5%;">Recipient Type</div>
		</th>		
		</tr>
		</thead>
		<% FacilitatorSessionReportDao facilitatorSessionDao = new FacilitatorSessionReportDao();
		ArrayList<SessionDomain> sessionDomain= facilitatorSessionDao.facultySessionOldDetails(organizationId, userId); 	
		Iterator<SessionDomain> iterator1 =sessionDomain.iterator();
		//System.out.println("domain value is:"+sessionDomain.size());
			 if(sessionDomain.size()>0){
				

				
		 
			 }
		 else {
			 //System.out.println(" No old sessions:");
			 String message="No completed sessions";
			 %>
			 <p class="autohide" style="color:red; font-size:18px; font-weight: bold; top: 41%; left: 44%; position: absolute;"><%=message %></p>
		<% }%> 
		 
		<!--  -->
		</table>
		</div>
		</div>
		</div>
		</form>
		</div>
		
	</div>
		<%@ include file="../JSP/FooterViews.jsp"%>
	</div>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
	<script type="text/javascript">
			$(document).ready(function()
			{
				$("#search").keyup(function()
				{
					searchTable($(this).val());
				});
			});
			function searchTable(inputVal)
			{
				var table = $("#tblData");
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
						 */
						//if(found == true)$('tr').show();else $(row).hide();
						 if(found == true)$(row).show();else $(row).hide();
						/* alert("message"); */
					}
				});
			}
		</script>
</body>
<script>
onload();
</script>
</html>