<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.kds.KODE_DEV.domain.CourseDomain" %>
<%@page import="com.kds.KODE_DEV.dao.CourseDao" %>
<%@page import="com.kds.KODE_DEV.dao.CreateSessionDao" %>
<%@page import="com.kds.KODE_DEV.domain.AdminDomain" %>
<%@page import="com.kds.KODE_DEV.domain.CourseFacultyDomain" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script src="../JS/jquery1.11.3.js"></script>
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<script type="text/javascript" src="../JS/ValidateCreateSession.js"></script>
<!-- <link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<link href="../CSS/kedu.css" rel="stylesheet"></link>
<link href="../CSS/jquery.datetimepicker.css" rel="stylesheet"></link>
<link href="../CSS/design-common.css" rel="stylesheet"/>
<link href="../CSS/notification-new.css" rel="stylesheet"></link>
<script type="text/javascript" src="../JS/jquery.js"></script>
<script src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.datetimepicker.full.min.js"></script>
<script src="../JS/MessageFadeOut.js"></script>
  
<script>

 $(document).ready(function() {
	$(".autohide,.dis1").delay(5000).fadeOut("slow");
}); 
$(document).ready(function () { 
	$(".group_tr").hide();
	 $(".select_tr").hide();

	 $("#r1").click(function() {
		 
		
			 var select = $('#student_id');
			 var selectedValue=$('#category option:selected').val();
			
	 		select.find('option').remove();
     	     var ctx = "${pageContext.request.contextPath}";
		   //  alert(ctx);
		     $.get('${pageContext.request.contextPath}/getSudentsForFacultyOnCourse', {selectedValue : selectedValue},
		    		  function(jsonResponse) {
		    			//alert(jsonResponse);
		    			//alert("inside");
		    			//console.log("json response :",jsonResponse);
	    	     		//var select = $('#subjects');
	    	     		//select.find('option').remove();
	    	     		jsonResponse = $.parseJSON(jsonResponse);
	    	     		//console.log("parsed json response :", jsonResponse);
	    	     		$('<option>').val('').text('Select Participants*').appendTo(select);
	    	     		$.each(jsonResponse, function(index, value) {
			    	 		  // console.log('Index value here :',index);
			    	 		  // console.log('Actual obj value here :',value.subject);
			    	 		 $('<option>').val(value.userId).text(value.userName).appendTo(select);
			    	      }); 
		    			
		    		});
		 
	 $(".select_tr").show();
	 $(".group_tr").hide();
	 });
	
	
	$("#r2").click(function() {
		 $(".group_tr").show();
		 $(".select_tr").hide();
		 });

	$("#r3").click(function() {
		 $(".group_tr").hide();
		 $(".select_tr").hide();
		 });

	
	
	/*  $("#watch-me").click(function(){
	 if($('watch-me').prop('checked')===false) {
	    $('#show-me').hide();}
	    }); */
	});
</script>

</head>
<style type="text/css">

.su{
color: #008000;
    font-size: 12px;
    font-weight: bold;
    top: 7%;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
.su1{
		    color: #008000;
    font-size: 10px;
    font-weight: bold;
    top: 10%;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
.r123 input {
    width: 15px!important;
}
</style>
<script>

var request;
function getSessionTime()
{
var sessionTime=document.sessionform.sSTime.value;
var url="sessionID.jsp?val="+sessionTime;

if(window.XMLHttpRequest){
request=new XMLHttpRequest();
}
else if(window.ActiveXObject){
request=new ActiveXObject("Microsoft.XMLHTTP");
}

try
{
request.onreadystatechange=getInfo;
request.open("GET",url,true);
request.send();
}
catch(e)
{
alert("Unable to connect to server");
}
}

function getInfo(){
if(request.readyState==4){
var val=request.responseText;
document.getElementById('amit').innerHTML=val;
}
}


$(document).ready(function() {
	  / for date picker /
	  var objDate = new Date();
	 
	  var Presentyear = objDate.getFullYear();
	
	  
	  
	   
	  $("#sSTime").datetimepicker({
		
	   yearRange : '1900:' + Presentyear,
	 /*  dateFormat: 'dd/mm/yy',  */
	  timeFormat:  "hh:mm:ss",
	   changeMonth : true,
	   changeYear : true,
	   step:1,
	   minDate : '0d',
	    showMinute: true, showHour: true
	  // dateFormat: "yy-mm-dd", 
	  
	  });
});
$(document).ready(function() {
	  / for date picker /
	  var objDate = new Date();
	 
	  var Presentyear = objDate.getFullYear();
	
	  
	  
	   
	  $("#sETime").datetimepicker({
		
	   yearRange : '1900:' + Presentyear,
	 /*  dateFormat: 'dd/mm/yy',  */
	  timeFormat:  "hh:mm:ss",
	   changeMonth : true,
	   changeYear : true,
	   step:1,
	   minDate : '0d',
	    showMinute: true, showHour: true
	  // dateFormat: "yy-mm-dd", 
	  
	  });
});

  $(document).ready(function() {
	  
	  $('#category').change(function () {
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
		   
		 });
	  $('#topic').change(function () {
		  	 var selectedValue = this.selectedOptions[0].value;
		     var selectedText  = this.selectedOptions[0].text;
		    // alert(selectedText);
		     // alert(selectedValue);
		     var ctx = "${pageContext.request.contextPath}";
		   //  alert(ctx);
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
		     
		     
		     
		 
	     
			 


		   
		 });
	  
	  
	 
		
	  
	  
	  
	/*   $( "#category" ).blur(function()
			  {
		 
	            if ($.trim($(this).val()) == '') {
	                isValid = false;
	                $(this).css({
	                    "border": "1px solid red",
	                 });
	            }
	            else {
	                $(this).css({
	                    "border": "",
	                });
	            }


  }); */
	  	  
	  $( "#sessionName" ).blur(function()
			  {
		  /* $('input[type="text"].required').each(function() { */
	            if ($.trim($(this).val()) == '') {
	                isValid = false;
	                $(this).css({
	                    "border": "1px solid red",
	                 });
	            }
	            else {
	                $(this).css({
	                    "border": "",
	                });
	            }
/* 	        }); */

  });
	  $( "#sSTime").blur(function()
			  {
		  
		  var time1 = $('#sSTime');
				  var d1 =toDate(time1.val());
		 	
		 
			if ($.trim($(this).val()) == ''||d1.getTime()<new Date().getTime()) {
				console.log('UI time='+d1.getTime());
				console.log('current time='+new Date().getTime());
				$('#dis1').slideDown().html('start date should be greater than current time');
	                isValid = false;
	                $('#sSTime').val('');
	                $(this).css({
	                    "border": "1px solid red",
	                   
	                 });
	               
	            } 
	            else {
	            	 $("#dis1").hide();
	                $(this).css({
	                    "border": "",
	                });
	            }
  });
	  $( "#sETime" ).blur(function()
			  {
		 
	            if ($.trim($(this).val()) == '') {
	                isValid = false;
	                $(this).css({
	                    "border": "1px solid red",
	                 });
	            }
	            else {
	                $(this).css({
	                    "border": "",
	                });
	            }
  });
	  $( "#duration" ).blur(function()
			  {
		 
	            if ($.trim($(this).val()) == '') {
	                isValid = false;
	                $(this).css({
	                    "border": "1px solid red",
	                 });
	            }
	            else {
	                $(this).css({
	                    "border": "",
	                });
	            }
  });
	 
	  $( "#costOfSession" ).blur(function()
			  {
		 
	            if ($.trim($(this).val()) == '') {
	                isValid = false;
	                $(this).css({
	                    "border": "1px solid red",
	                 });
	            }
	            else {
	                $(this).css({
	                    "border": "",
	                });
	            }
  });
	  $( "#student_id" ).blur(function()
			  {
		 
	            if ($.trim($(this).val()) == '') {
	                isValid = false;
	                $(this).css({
	                    "border": "1px solid red",
	                 });
	            }
	            else {
	                $(this).css({
	                    "border": "",
	                });
	            }


  });
	  $( "#group_id" ).blur(function()
			  {
		 
	            if ($.trim($(this).val()) == '') {
	                isValid = false;
	                $(this).css({
	                    "border": "1px solid red",
	                 });
	            }
	            else {
	                $(this).css({
	                    "border": "",
	                });
	            }


  });
	  
	 /*  $('#buttonSubmit').click(function(e) {
	  
	  
	  }); */
	  
	  
	
  	var time1 = $('#sSTime'), //cache selectors
      time2 = $('#sETime'),
      result = $("#duration");
//  alert(time1);
function toDate(dateStr) {
	console.log("in to date="+dateStr);
	  var parts_time = dateStr.split(" ");
	  var time=parts_time[1].split(":");
	  
	  var only_date=parts_time[0];
    var parts = only_date.split("/");
   console.log('parts[2]='+parts[2]);
   console.log('parts[2]='+parts[1]);
   console.log('parts[2]='+parts[0]);
    //alert(parts[1]);
    //alert(parts[0]);
    //var year_time=parts[0]+" "+ parts_time[1];
    //alert(year_time);
   //new Date( Date.parse("05/12/05 11:11:11") );
   console.log('final date='+new Date(parts[0], parts[1] - 1, parts[2], time[0], time[1]));
  // return new Date(parts[2], parts[1] - 1, parts[0], time[0], time[1]);
return new Date(parts[0], parts[1] - 1, parts[2], time[0], time[1]);
}
  	$( "#sETime" ).blur(function()
		{
  		//alert("time1="+time1.val());
  		//alert("timee2="+time2);
  		//alert("in end time"+time1.val());
  		var d1 =toDate(time1.val()); //convert to date object
  		//alert(d1); 
         var d2 = toDate(time2.val()); //convert to date object
         // alert(d2);
         //alert(d1);
        /*   msec = d2.getTime() - d1.getTime(), //get difference in milliseconds
          hh = Math.floor(msec / 1000 / 60 / 60), //get hours
          dd = Math.floor(hh / 24); //calculate days */
          
             msec = d2.getTime() - d1.getTime();
         // alert(msec);
          //get difference in milliseconds
          hh = Math.floor(msec / 1000 / 60 / 60); //get hours
         // alert(hh);
          dd = Math.floor(hh / 24); //calculate days 
          
          //alert("d1="+d1.getDate());
          //alert(d1.getFullYear()+""+d1.getMonth()+""+d1.getDate());
    		//alert("d2="+d2.getDate());
    		
      msec -= hh * 1000 * 60 * 60; //remove hours from msec
      hh -= dd * 24; //remove days from hours
      var mm = Math.floor(msec / 1000 / 60); //get minutes
      //result.val(dd + ' days ' + hh + ' Hrs ' + mm + ' minutes');
      $('#error').val('');
      
      if(d2 <= d1 ){
    	  $('#dis').slideDown().html('End time should be greater than start time');
    	 // alert("End time is greater than start time");
    	  $('#duration').val('');
    	  $('#sETime').val('');
      }
      else if(d1.getFullYear()+""+d1.getMonth()+""+d1.getDate() != d2.getFullYear()+""+d2.getMonth()+""+d2.getDate()){
    	  $('#dis').slideDown().html('Start date & end date must be on same day');
     	  $('#duration').val('');
     	  $('#sETime').val(''); 
      }
      
      else {
    	  result.val( hh + ' hrs ' + mm + ' mins');
    	  $("#dis").hide();
      }
      
  });    	
  });
  
 
  
  $(document).ready(function() {
	    $("#buttonSubmit").click(function(e) {
	    	
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
	        
	         $('.sel1 select.required').each(function() {
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
	        
	        // here end working ok 
	        
	        if($("input:radio[id='r1']").is(":checked")) {
		    	
	    	   $('.student_sel select.required').each(function() {
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
	    	  }
	        
	        if($("input:radio[id='r2']").is(":checked")) {
		    	
		    	   $('.group_sel select.required').each(function() {
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
		    	  }
		        
	        
	        
	        
	        
	        
	       if($("input:radio[name='group']").is(":checked")) {
	    	  
	    	   $("#display").hide();
      		  }
	       else
	    	   {
	    	   isValid = false;
	    	   $('#display').slideDown().html('<span id="error"><br/>Select receipient type</span>');
		        return false;
	    	   }
	        
	      
	        
	        if (isValid == false){
	        	
	            e.preventDefault();
	           
	        }
	        else {
	        	 //alert('Thank you for submitting')
	        	 $("#sessionform").submit();
	        }
	           
	    });
	});           
  </script>
 
  <script>
  $(function(){
				
				$("#costOfSession").keyup(function()
				{
					var yourInput = $(this).val();
					re = /[`~!@#%^&*()|+\-=?;:'",<>\{\}\[\]\\\/]/gi;
					var isSplChar = re.test(yourInput);
					if(isSplChar)
					{	
						
						var no_spl_char = yourInput.replace(/[`~!@#%^&*()|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
						$(this).val(no_spl_char);
						 $('#special').slideDown().html('<span id="error">Special characters not allowed except $ .</span>');
						/* alert("Special characters not allowed except $ ."); */
					}else {
						 $("#special").hide();
					}
				});
			});
  
</script>
<!-- <script type="text/javascript">
       $(function() {
              // $("#datepicker").datepicker({ dateFormat: "yy-mm-dd" }).val();
               
               $.datetimepicker.setLocale('en');
               $('#sETime').datetimepicker({
               dayOfWeekStart : 1,
               lang:'en',
               disabledDates:['1986/01/08','1986/01/09','1986/01/10'],
               startDate:	'1986/01/05'
               });
            //   $('#sETime').datetimepicker({value:'2017/09/13 00:00',step:1});
               
               
               
       });
   </script> -->
<style type="text/css">
		.su{
		    color: #008000;
    font-size:15px;
    font-weight: bold;
    top:7%;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
.su1{
		    color: #008000;
    font-size: 15px;
    font-weight: bold;
    top: 9%;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
.su2{
		    color: #008000;
    font-size: 15px;
    font-weight: bold;
    top: 7%;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:10px;
    padding: 0px 36px;
}
//color:red; font-size:12px; font-weight: bold; top: 10%; left: 30%; position: absolute;
		</style>
<%
		/* HttpSession mess = request.getSession();
		String msg = ""; */
	    //msg = (String) mess.getAttribute("MsgValue");
		
		String organizationId=(String)session.getAttribute("orgid");
		String organizationName=(String)session.getAttribute("organizationName");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		if(username==null)
			 response.sendRedirect("../JSP/error.jsp?errmsg=Session Expired");
		//System.out.println("organization id:"+organizationId+"created id:"+userid);
		//System.out.println("success message"+request.getAttribute("FacultySuccessMail"));
		
		//}
	%>
<body>
<%if (request.getAttribute("FacultySuccessMail")!= null) { 
				String messsage=(String)request.getAttribute("FacultySuccessMail");
				/*  String str[]=messsage.split("#");
				 String insertSuccessMessage=str[0];
				 String mailSuccessMessage=str[1];
				 String displaySessionid=str[2]; */
				%>
				<%-- <p class="su autohide"><%= insertSuccessMessage %></p><br>
				<p class="su1 autohide"><%=mailSuccessMessage %></p>	--%>
				<p class ="success"><%=messsage %>	 
				<%
			}
			 else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
					<p class="failure"><%= request.getAttribute("FacultyFailure") %></p>					
					<%
				} 
				
			%>
	<div class="container">
		 <%@include file="headers.jsp"%> 
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> --%>

		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="faculty_mod" style="width: 300px; margin-top:70px;top:0px;height:370px;overflow-y:scroll;">

			


 <!-- <strong>Create Session</strong> -->
 <p class="strong">Create Session</p>
<form name="sessionform" id="sessionform" action="/KODE_DEV/ControllerServlet/CreateSessionServlet" method="post">
<table>

					<tr>
						<td class="sel1">
						<select name="category" class="box_lng required" id="category">
							    <option value="">Choose Course*</option>
							    <%
									//	GetCourseForAdminDao getCourseForAdminDao = new GetCourseForAdminDao();
									//  List<CourseAdminDomain> courses = getCourseForAdminDao.getCourseForAdminFaculty(orgid, userid);
										//HashSet set =(HashSet)al.fetchValue();
									//  for(CourseAdminDomain course : courses) {
									//	System.out.println("course :"+course.getCourseId());
									//  String result = course.getCourseId();
										String result="";
										
										GetCourseForFacultyDao getCourseForFacultyDao = new GetCourseForFacultyDao();
										List<CourseFacultyDomain> courses = getCourseForFacultyDao.getCourseForFaculty(userid);
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
						</td>
					</tr>
					<tr>
						<td class="sel">
							    <select name="subject" class="box_lng required" id="subject">
							    	<option value="">Choose Subject</option>
							    </select>
						</td>
					</tr>
					<tr>
						<td class="sel">
							    <select name="topic" class="box_lng required" id="topic">
							    	<option value="">Choose Topic</option>
							    </select>
						</td>
					</tr>
					<tr>
						<td class="sel">
							    <select name="subtopic" class="box_lng required" id="subtopic">
							    	<option value="">Choose SubTopic</option>
							    </select>
						</td>
					</tr>
<tr><!-- <td>Session Name</td><td>:</td>  -->




<td><input type="text" name="sessionName"  placeholder="Session Name" class="box required" id="sessionName"></td></tr>



<tr><!-- <td>Session Start Time</td><td>:</td> -->
<td><input type="text" name="sSTime" class="box required" placeholder="Session Start Time" id="sSTime" readonly="readonly" onblur="getSessionTime()" >
<label id="dis1" style="width:200px; color: red"></label><br>
<span id="amit" style="display:none;"></span></td>

</tr>
<tr><!-- <td>Session End Time</td><td>:</td> -->
<td><input type="text" name="sETime"  placeholder="Session End Time" id="sETime" readonly="readonly" class="required">
<label id="dis" style="width:250px; color: red"></label><br>

</td>
</tr>
<tr><!-- <td>Duration</td><td>:</td> --><td><input type="text" name="duration"  placeholder="Duration" id="duration" class="required" readonly="readonly"></td></tr>
<%-- <%if(request.getAttribute("Duration")!=null){ 
//System.out.println("duration in jsp:"+request.getAttribute("Duration"));%>
<tr><td>Duration</td><td>:</td><td><input type="text" name="duration" id="duration" value="<%=request.getAttribute("Duration") %>"></td></tr>
<%} %> --%>
<tr><!-- <td>Cost Of Session</td><td>:</td> -->
<td><input type="hidden" name="costOfSession"  placeholder="Cost Of Session" id="costOfSession" value="0">
<label id="special" style="width:250px; color: red"></label><br></td></tr>

<%-- <tr><!-- <td>Faculty ID</td><td>:</td> -->
<td><input type="text"  placeholder="Faculty ID" name="facultyId" value="<%=userid %>" readonly="readonly"></td></tr> --%>
<%-- <tr><!-- <td>Organization Name</td><td>:</td> -->
<td><input type="text" placeholder="Organization Name"  name="orgId" value="<%=organizationName %>" readonly="readonly"></td></tr> --%>
<tr><!-- <td>To Send</td><td>:</td> -->
 <td class="r123" class="required" >
 <input id="r1" type="radio" value="Individual" name="group">Individual 
 <input id="r2" type="radio" value="Group" name="group">Group
 <input id="r3" type="radio" value="All" name="group">All
 <label id="display" style="width:250px; color: red"></label><br>
 </td>
 <td class="r123"></td>
 <td class="r123"></td>
 </tr>
  <tr class="select_tr">
 
 
						<!-- <td align="left">
							<font>Student ID </font>
						</td>
						
						<td>:</td> -->
						<td class="student_sel">
							 <% CreateSessionDao asdao=new CreateSessionDao();
		
                             ArrayList<AdminDomain> sl=new ArrayList<AdminDomain>();
                             sl=asdao.sendIndualGroup(userid,organizationId);
		                      Iterator<AdminDomain> it1= sl.iterator();%>
		                      <select name="student_id" class="required" id="student_id">
		                      <option value="">Choose participant</option>
		                    <% while(it1.hasNext())
		                     {
		                    	AdminDomain adom=it1.next();
		    	             String id=adom.getAdminId();
		    	             String name=adom.getAdminName();
		    	             String idname=name+" ("+id+")"; %>
		                      <option  value="<%= id %>"><%=idname%></option>	
		                         <%  }%>
		                      </select>
	                
						</td>
					</tr>
					 <tr class="group_tr">
						<!-- <td align="left">
							<font>Group ID</font>
						</td>
						<td>:</td> -->
						
						<td class="group_sel">
							 <% 
		
                             ArrayList<AdminDomain>al1=new ArrayList<AdminDomain>();
		                   al1=asdao.sendGroupId(userid,organizationId);
		                      Iterator<AdminDomain> it2= al1.iterator();%>
		                      <select name="group_id" class="required" id="group_id">
		                      <option value="">Choose group</option>
		                    <% while(it2.hasNext())
		                     {
		    	             String id=it2.next().getGroup_name();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	                
						</td>
					</tr>
					<tr>
					<td><input type="hidden" name="organizationId" id="organizationId" value="<%=organizationId %>"></td>
					<td><input type="hidden" name="facultyId" id="facultyId" value="<%=userid %>"></td></tr>
					<!-- <tr><td>Status</td><td>:</td>
					<td><input type="text" name="status" value="Available" readonly="readonly"></td></tr> -->

<tr>
<td>
<!-- onclick="ValidateCreateSession()" -->
<input class="add_btn1" style="width: 268px;" type="button" id="buttonSubmit" value="Create"/>
<!-- <a class="back_txt" href="../JSP/Home.jsp">Back</a> -->

</table>
</form>

		</div>
		<%@ include file="../JSP/FooterViews.jsp"%>
	</div>
	
</body>
<style>
.autohide {
position:absolute;
width:600px;
font-size:14px;
text-align:right;
font-weight:bold;
top:-15px;
right:350px;}
</style>
</html>