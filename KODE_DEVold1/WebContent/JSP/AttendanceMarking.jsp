<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kds.KODE_DEV.domain.CourseDomain"%>
<%@page import="com.kds.KODE_DEV.domain.AssessmentDomain"%>
<%@page import="com.kds.KODE_DEV.dao.*"%>

<%@page import="com.kds.KODE_DEV.domain.AdminDomain"%>
<%@page import="com.kds.KODE_DEV.domain.CourseFacultyDomain"%>
<%@page import="com.kds.KODE_DEV.domain.GetSessionForFacultyDomain"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>WelCome To KODE</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>  -->
<script src="../JS/jquery.min.js"></script>
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.js"></script>
<link href="../CSS/CreateCustomer.css" rel="stylesheet" />
<link href="../CSS/jquery.datetimepicker.css" rel="stylesheet"></link>
<script src="../JS/datetimepicker_css.js"></script>
<script type="text/javascript" src="../JS/validatePostSession.js"></script>
<link href="../CSS/jquery.datetimepicker.css" rel="stylesheet"></link>
<script src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.datetimepicker.js"></script>
<script type="text/javascript"
	src="../JS/jquery.datetimepicker.full.min.js"></script>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<link href="../CSS/kedu.css" rel="stylesheet" />
<link href="../CSS/table_scroll.css" rel="stylesheet" />
<link href="../CSS/design-common.css" rel="stylesheet" />
<link href="../CSS/notification-new.css" rel="stylesheet"></link>
<script src="../JS/MessageFadeOut.js"></script>
<style type="text/css">
.tabmain {
	position: absolute;
	right: 38%;
	top: 174px;
	width: 315px;
	z-index: 1;
	border: 1px solid #c2c2c2;
	border-bottom: none;
	BORDER-RADIUS: 5PX;
}

.tabmain ul li {
	float: left;
	width: 48.99%;
	list-style: none;
	text-align: center;
	padding: 10px 0.1px;
}

.su {
	color: #008000;
	font-size: 14px;
	font-weight: bold;
	top: 44px;
	position: absolute;
	/* background: #fff;
    opacity: 0.7; */
	right: 0px;
	padding: 0px 36px;
}
</style>
<style type="text/css">
.postpon_mod {
	width: 95% !important;
	box-shadow: 5px 5px 5px 5px #FFF;
	margin: 15px auto 0px;
	padding: 15px 1px 0px 0px;
	border: 3px solid #C2C2C2;
	background-color: #FCF7F7;
	border-radius: 4px;
	opacity: 1;
	margin-top: 72px;
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

.addbg {
	background-color: #FCF7F7;
	border-radius: 4px;
	/* opacity: 0.9; */
	border: 3px solid #C2C2C2;
	border-bottom: none;
	color: #000 !important;
}

select {
	color: #000;
}

.box1 {
	width: 250px !important;
	padding: 7px !important;
	border: 1px solid #c2c2c2;
	border-radius: 4px;
}

.row_result .col_2 {
	float: left;
	padding: 0px 15px;
}

.result_row_tr td input {
	border: none;
	background: none;
	width: 99.5%;
	color: #000;
}

.starttime {
	width: 130px;
}

.endtime {
	width: 120px;
}

.duration_r {
	width: 120px;
}

.cost_r {
	width: 80px;
}

.result_row_tr td {
	padding: 4px 4px 4px 10px;
}

.btnscolor {
	background: #1d87da;
	border: none;
	color: #fff;
	padding: 5px 15px;
	margin-top: 10px;
	margin-right: 10px;
	margin-left: 10px;
}

.tabsbg {
	background: #fff;
	border-radius: 5px;
}

.tab1bg1 {
	background-color: #FCF7F7;
	border-radius: 4px;
	/* opacity: 0.9; */
	border: 3px solid #C2C2C2;
	border-bottom: none;
	color: #000 !important;
}

.tab2bg2 {
	background-color: #FCF7F7;
	border-radius: 4px;
	/* opacity: 0.9; */
	border: 3px solid #C2C2C2;
	border-bottom: none;
	color: #000 !important;
}

.addbg {
	background-color: #FCF7F7 !important;
	border-radius: 4px;
	border: 3px solid #C2C2C2;
	border-bottom: none;
	color: #000 !important;
}

.addbg1 {
	background-color: #fff !important;
	padding: 9px 0px !important;
}

.tab1 ul li:first-child {
	background: #FCF7F7;
}

.tab1 ul li:last-child {
	background: #fff;
}

.tab2 ul li:first-child {
	background: #fff !important;
	border: none;
}

.tab2 ul li:last-child {
	background: #FCF7F7;
	border: 3px solid #c2c2c2;
	border-bottom: none;
}

.nodata {
	color: #008000;
	font-size: 14px;
	font-weight: bold;
	top: 44px;
	/* position: absolute; */
	right: 0px;
	padding: 0px 36px;
	margin-top: 15px;
}

.tab2ind {
	display: none;
}

.search_result {
	padding: 0px 10px;
	margin-bottom: 25px;
	max-height: 273px;
	font-family: arial;
	font-size: 14px;
}

.su {
	/*     color: #008000;
    font-size: 14px;
    font-weight: bold;
    top:44px;
    position: absolute;
    background: #fff;
    opacity: 0.7; 
    right:0px;
    padding: 0px 36px;
     */
	color: #008000;
	font-size: 15px;
	font-weight: bold;
	top: 6px;
	left: 10px;
}

.container {
	background: url("../Image/body_dark.png") center center no-repeat;
}
</style>
<style type="text/css">
.postpon_mod {
	width: 95% !important;
	box-shadow: 5px 5px 5px 5px #FFF;
	margin: 15px auto 0px;
	padding: 15px 1px 0px 0px;
	border: 3px solid #C2C2C2;
	background-color: #FCF7F7;
	border-radius: 4px;
	/*  opacity:1; */
	margin-top: 72px;
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

.search_div {
	margin-top: -37px;
	position: absolute;
	height: 33px;
	padding: 6px !important;
	font-family: regular;
	border-radius: 4px;
}

.opt {
	width: 265px !important;
	padding: 7px !important;
	height: 31px;
	border: 1px solid #C2C2C2;
	font-family: regular;
	border-radius: 4px;
}

.box1 {
	width: 250px !important;
	padding: 7px !important;
	border: 1px solid #c2c2c2;
	border-radius: 4px;
}

.row_head {
	background: none repeat scroll 0% 0% #009AE1;
	/*   color: #FFF; */
	font-weight: bold;
}

.row_head td {
	/*   padding: 5px; */
	border-right: 1px solid #bbb;
}

.table_layout_scroll {
	width: 100%;
	max-height: 251px;
	overflow-y: scroll;
	border-bottom: 1px solid #ddd;
}

.row_head .col_2 {
	float: left;
	padding: 0px 15px;
}

.row_result .col_2 {
	float: left;
	padding: 0px 15px;
}

.result_row_tr td input {
	border: none;
	background: none;
	width: 99.5%;
}

.starttime {
	width: 130px;
}

.endtime {
	width: 120px;
}

.duration_r {
	width: 120px;
}

.cost_r {
	width: 80px;
}

.result_row_tr td {
	padding: 4px 4px 4px 10px;
}

.row_head {
	background: #a5caf6; /* e0ecfa; */
}

.table_layout_scroll tr:nth-child(even) {
	background: #f7f7f7
}

.table_layout_scroll tr:nth-child(odd) {
	background: #ffffff
}
/* .table_layout_scroll {
background:#bbb;
} */
.result_row_tr td {
	padding: 7px 4px;
}

.row_tr td {
	padding: 4px 0px;
}

.table_layout_scroll tr {
	border: 1px solid #ddd;
}

table {
	border-collapse: collapse;
}

.result_row_tr td {
	border-right: 1px solid #bbb;
}
</style>
</head>

<script type="text/javascript">
        
        $(document).ready(function() {
        	$(".autohide").delay(5000).fadeOut("slow");
        }); 
        
        function loadSession()
        {	  
        	
        	
        	
      	     var selected = document.getElementById("choose_course");
      	   var date = $("#datetimepicker2").val();
      	    // alert(selected);
      var selectedValue = selected.options[selected.selectedIndex].value; 
    //  alert(selectedValue);
      
      	    $.get('${pageContext.request.contextPath}/getSessionForFaculty', {selectedValue : selectedValue,date : date},
      	    		  function(jsonResponse) {
      	    		console.log("json response :",jsonResponse);
          	     		var select = $('#choose_session');
          	     	
          	     		select.find('option').remove();
          	     		jsonResponse = $.parseJSON(jsonResponse);
          	     		console.log("parsed json response :", jsonResponse);
          	     		$('<option>').val('').text('Session Name').appendTo(select);
          				/* $('<option>').val('All').text('All').appendTo(select); */
          	     		$.each(jsonResponse, function(index, value) {
      		    	 		  console.log('Index value here :',index);
      		    	 		 console.log('Actual obj value here :',value.sessionName);
      		    	 		$('<option>').val(value.sessionId).text(value.sessionName+" ("+value.sessionId+") ").appendTo(select);
      		    	 	//	$('<option>').val(value.sessionName).text(value.sessionName).appendTo(select);
      		    	 		
      		    	      }); 
      	    			
      	    		});
      	          }

</script>


<script type="text/javascript">
        
function loadStudentDetails(){
	//alert("in");
	var courseId=document.getElementById("choose_course").value;
	var sessionId=document.getElementById("choose_session").value;
//	alert(sessionId);
	var date=document.getElementById("datetimepicker2").value;
	//alert(" subjectId "+subjectId);
	//document.ksStore.action="/KODE_DEV/ControllerServlet/FacilitatorAssetKnowViewServlet?libID="+libId+"";
	document.cerify.action="/KODE_DEV/ControllerServlet/GetStudentDetailsServlet?courseId="+courseId+"&sessionId="+sessionId+"&date="+date+"";
	//alert("in");
	document.cerify.submit();
	//alert("in1");
}
	$(document).ready(function()
	{
		$('#search').keyup(function()
		{
			searchTable($(this).val());
		});
	});

</script>




<!-- +" ("+value.sessionName+")" -->
<%


String organizationId=(String)session.getAttribute("orgid");
String organizationName=(String)session.getAttribute("organizationName");
String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
if(username==null)
	response.sendRedirect("../JSP/error.jsp");
	    String facultyId=(String)session.getAttribute("userid");
	    String orgId=(String)session.getAttribute("orgid");
	   /*  String filepath=(String)session.getAttribute("filePath");
	    String uploadedid=(String)session.getAttribute("uploadedId");

	    String[] stuId=(String[])session.getAttribute("assessResultDomain");
	    String  studentId[]=(String[])session.getAttribute("ResultDomain") ;
	    String filepath1=(String)session.getAttribute("FilePath");
	    String uploadedid1=(String)session.getAttribute("uploadedId1"); */
	    //System.out.println("file path:"+filepath +"uploadedid:"+uploadedid);
	    //1st form
        ArrayList<GetSessionForFacultyDomain> assessmentDomainForAssessment = new ArrayList<GetSessionForFacultyDomain>();
		
        assessmentDomainForAssessment = (ArrayList<GetSessionForFacultyDomain>)request.getAttribute("getStudentDetails");
		System.out.println("assessmentDomainForAssessment");
        
 	%>
<script type="text/javascript">
 	
 	function submitStudentAttandace(){
 		
 		var sessionId=document.getElementById("choose_session").value;
 		
 	//	alert("sessionId==>"+sessionId);
 		
 		document.cerify.action="/KODE_DEV/ControllerServlet/PresentAttendanceDetailsService";
 		document.cerify.submit();	
 			
 	}
 	
 	/* function clickAssignmentSubmit(){
 		
 		document.AssignCertify.action="/KODE/ControllerServlet/AssignmentCertifyStudent";
 		document.AssignCertify.submit();
 		
 	} */
 
 	</script>






<!-- <script>

 	

	$(document).ready(function() {
		 $(".input-for-gb input").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		    // alert(e.which);
		     if (e.which != 8 && e.which != 0  && (e.which < 48 || e.which > 57)) {
		        //display error message
		      //  alert("in");
		        $(this).html("Digits Only").css("border","1px solid red");
		               return false;
		    }else
		    {
		    	$(this).html("Digits Only").css("border","none");
		    }
		   });
/* 		 $(":checkbox").click(function(){
		        var id = $(this).attr('id');
	//	alert(id);
		       // $.post("index.php", { id: id });
		       //return false to ensure the page doesn't refresh
		      // return false;
		 $('#marks'+id).css({
		    "border": "1px solid red",
		}); 
		 $('#remarks'+id).css({
			    "border": "1px solid red",
			}); 
		    });  */   
	    $("#update_btn").click(function(e) {
	    	 var isValid = true;
	    	 var cnt=0;  	    	 //alert('button clicked');
	    	 $('.search_result input[type="checkbox"]').each(function() {
	    		 var id = $(this).attr("id");
	    		 if($(this).is(":checked"))
	        //if($('input:checkbox').is(":checked"))
	        {
	        	cnt++;  	        	//alert("checkbox id="+id);
	          if ($.trim($('#marks'+id).val()) == '') {
	                isValid = false;
	                 $('#marks'+id).css({
	                    "border": "1px solid red",
	                }); 
	            }
	            else {
	                $('#marks'+id).css({
	                    "border": "",
	                });
	            }
	          if ($.trim($('#remarks'+id).val()) == '') {
	                isValid = false;
	                 $('#remarks'+id).css({
	                    "border": "1px solid red",
	                }); 
	            }
	            else {
	                $('#remarks'+id).css({
	                    "border": "",
	                });
	            }
	          }
	        }); 
        if (isValid == false){
	            e.preventDefault();
	            //alert("failed");
	        }
	    else if(cnt>0){
	        	 //alert('Thank you for submitting');
	        	document.cerify.action="/KODE/ControllerServlet/AttendanceMarkingServlet";
	     		document.cerify.submit();	
	        }
	    else {alert("Please select assignment to evaluate!");}
	         //alert(cnt);  
	    });
	});     
</script>
 -->
<script type="text/javascript">
/* function submitStudentAttandace(){
	alert("data submit");
	document.cerify.action="/KODE/ControllerServlet/AttendanceMarkingServlet";
	document.cerify.submit();
}
 */






</script>

<%-- <script>
$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
	//$(".autohide").click(function()
	//{
	//});
});

function clickAssessmentID(){
	
	var assessid=document.getElementById("asessID").value;
	//alert("assessment id:"+assessid);
	document.cerify.action="/KODE/ControllerServlet/AttendanceMarkingServlet?assessid="+assessid+"";
	document.cerify.submit();
}
	
	function onloadAssess(){
		//alert("in f");
		<% String assesid=(String)request.getAttribute("asessID");
		//System.out.println("assessment id in an onload:"+assesid);
		if(assesid!=null && assesid.length()>0) {
			//System.out.println("assessment id in onload:"+assesid);
		 %> 
		 var selectBox = document.getElementById("asessID");
		 //alert("selectBox="+selectBox);
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
	
</script> --%>
<script type="text/javascript">
<%--function clickAssignmentID(){
	
	var assignid=document.getElementById("asignID").value;
	document.AssignCertify.action="/KODE/ControllerServlet/CertifyStudentForAssignment?assignid="+assignid+"";
	document.AssignCertify.submit();
}
 function onloadAssign(){
	//alert("in");
	<% String assignid=(String)request.getAttribute("assignId");
	////System.out.println("assignment id in an onload:"+assignid);
	if(assignid!=null && assignid.length()>0) {
		////System.out.println("assignment id in onload:"+assignid);
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
} --%>


</script>

<script>

$(document).ready(function() {
	/* $('.add_btn1').hide(); */
	
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
	//alert(id);
	if($('#' + id).is(":checked"))
	{
		//alert("in checked");
	//$(".marks" + id).attr("disabled",false);
	$('#marks'+id).attr("disabled",false);
	$("#remarks" + id).attr("disabled",false);
	$("#status" + id).attr("disabled",false);
	$("#id" + id).attr("disabled",false);
	$("#assess" + id).attr("disabled",false);
	/* $('.add_btn1').show(); */
	}
	else{
	//alert("in unchecked");
	$("#marks" + id).attr("disabled",true);
	$("#remarks" + id).attr("disabled",true);	
	$("#status" + id).attr("disabled",true);
	$("#id" + id).attr("disabled",true);	
	$("#assess" + id).attr("disabled",true);
	/* $('.add_btn1').hide(); */
}
	/* if($(".row a input:checkbox").is(":checked")) {
	/* 	$('.add_btn1').show(); 
	  }	 */
	
	});
	
	});
	//hiding drop down by default
$(document).ready(function () { 
	$(".group_tr").hide();
	 $(".select_tr").hide();
	 
	 $(".individual").click(function() {
		 $(".select_tr").show();
		 $(".group_tr").hide();
		 });
	 $(".group").click(function() {
		 $(".select_tr").hide();
		 $(".group_tr").show();
		 });
	 $(".all").click(function() {
		 $(".select_tr").hide();
		 $(".group_tr").hide();
		 });
	 
	 
});
/* $(document).ready(function() {
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
});            */
</script>

<%
	String Tabmsg = request.getParameter("tabValue");
	String successMsg = request.getParameter("sessionStatusSuccess");
	//System.out.println("successMsg="+successMsg);
	String noDataFound = (String) request.getAttribute("noDataFoundMsg");
	//System.out.println("JSP MSG :::::::::::"+noDataFound);
%>


<%

String attendanceDetails = (String)session.getAttribute("successmsg");

//out.println("success  "+attendanceDetails);

%>


<body>
	<%
		System.out.println("FacultySuccess=" + request.getAttribute("FacultySuccess"));
		if (request.getAttribute("FacultySuccess") != null) {
	%>
	<p class="success">
		<%=request.getAttribute("FacultySuccess")%></p>
	<%
		} else if (request.getAttribute("FacultyFailure") != null) {
	%>
	<p class="failure"><%=request.getAttribute("FacultyFailure")%></p>
	<%
		}
	%>
	<div class="container">
		<%-- <%@include file="all_one_header_knowstore.jsp"%> --%>
		<%@include file="../JSP/headers.jsp"%>


		<div style="clear: both;"></div>
		<div>
			<%@ include file="../JSP/menus.jsp"%>
		</div>
		<div style="clear: both;"></div>
		<%-- 	 --%>

		<%-- 	--%>


	 <div class="tabmain <%=Tabmsg%>">
		<%if(attendanceDetails!=null){ %>
		<p style="position: absolute; color: green"><%=attendanceDetails %></p>
		<%} 
		%> 
	
		<!-- <ul>
		<li id="ass_tab">Assessment</li>
		<li id="assi_tab">Assignment</li>
		</ul> -->
		</div> 

		<div class="postpon_mod <%=Tabmsg%>ind" id="first_part">
			<div style="text-align: center;">

				<p class="strong">Attendance Marking</p>

				<form name="cerify" method="post" >

					<%
						CertiftStudentDao certifySDao = new CertiftStudentDao();

						ArrayList<AdminDomain> arrayListForAssessment = new ArrayList<AdminDomain>();
						arrayListForAssessment = certifySDao.retriveAssessMentDetails(facultyId, orgId);
						Iterator<AdminDomain> iteratorForAssessmentId = arrayListForAssessment.iterator();
					%>

					<!--  <select name="asessID" id="asessID"  class="box_lng" onchange="" style="margin-left: 0px">
		                      <option value="">Course Name</option>
		                      <option value="">All</option>
		                      </select>
	                
	                 		  <select name="asessID" id="asessID"  class="box_lng" onchange="" style="margin-left: 0px">
		                      <option value="">Session Name</option>
		                      <option value="All">All</option>
		                      </select>
		                      
		                       <select name="asessID" id="asessID"  class="box_lng" onchange="" style="margin-left: 0px">
		                      <option value="">Course Name</option>
		                      <option value="">All</option>
		                      </select>
		                       -->

					<div style="width: 100%;">
						<div style="display: inline-block; padding-right: 20px">
							<select name="choose_class" id="choose_course"
								style="width: 208px !important; padding: 0px !important; height: 31px; border: 1px solid #c2c2c2; border-radius: 4px;">
								<option value="">Course Name*</option>

								<%
									String result = "";

									GetCourseForFacultyDao getCourseForFacultyDao = new GetCourseForFacultyDao();
									List<CourseFacultyDomain> courses = getCourseForFacultyDao.getCourseForFacultyMarkingAttedance(userid,organizationId);
									for (CourseFacultyDomain course : courses) {
										System.out.println("course :" + course.getCourseId());
										result = course.getCourseId();
								%>
								<option value="<%=result%>"><%=result%></option>
								<%
									}
								%>


								<option value="All">All</option>
							</select>
						</div>
						<div style="display: inline-block; padding-right: 20px">
							<!--  <input style="width: 208px !important;padding: 0px !important;height: 31px;border: 1px solid #c2c2c2;border-radius: 4px;" type="text" id="search" placeholder="Search">-->
							<input type="text" name="datetimepicker2" id="datetimepicker2"
								class="box assess1" id="demo" readonly="readonly"
								placeholder="Session Date" onchange="loadSession()"
								style="width: 208px !important; padding-left: 10px !important; height: 31px; border: 1px solid #c2c2c2; border-radius: 4px;">


						</div>
						<div style="display: inline-block; padding-right: 20px">
							<select name="choose_session" id="choose_session"
								onchange="loadStudentDetails()"
								style="width: 208px !important; padding: 0px !important; height: 31px; border: 1px solid #c2c2c2; border-radius: 4px;">
								<option value="">Session Name*</option>
								<option value="All">All</option>
							</select>
						</div>
						<!--   <div style="display:inline-block;padding-right:20px">
		       <select name="choose_subject" id="ch_subject" style="width: 208px !important;padding: 0px !important;height: 31px;border: 1px solid #c2c2c2;border-radius: 4px;">
                    <option value="">Choose Subject *</option>
		            <option value="All">All</option>
                </select>
		   </div>-->

					</div>






					<!--<div class="search_div">
		<input class="box1" type="text" id="search" placeholder="Search">
		</div>-->
					<div class="search_result">

						<%
							// System.out.println("message value:"+request.getAttribute("FacultySuccess"));
							//System.out.println("successMessage value:"+request.getAttribute("successMessage"));
							//System.out.println("failureMessage value:"+request.getAttribute("failureMessage"));
							// System.out.println("before if success");

							if (request.getAttribute("successMessage") != null) {
								// System.out.println("in if success");
						%>
						<p class="autohide"
							style="color: green; font-size: 17px; font-weight: bold; top: 11%; left: 41%; position: absolute;">
							<%=request.getAttribute("successMessage")%></p>
						<%
							} else if (request.getAttribute("failureMessage") != null) {
								// System.out.println("in else failure");
						%>
						<p class="autohide"
							style="color: red; font-size: 17px; font-weight: bold; top: 11%; left: 41%; position: absolute;"><%=request.getAttribute("failureMessage")%></p>
						<%
							}
						%>
						<!-- heading starts here  -->
						<div class="table_section1 input-disabled ">
							<div class="table_outer_row">
								<table id="tblData" width="100%">
									<thead>
										<tr class="row_head">
											<th width="15%">Sl #
												<div class="header_stop" style="width: 15%;">Sl #</div>
											</th>
											<th width="20%">Enrollment ID
												<div class="header_stop" name="enrId" style="width: 20%;">Enrollment
													ID</div>
											</th>
											<th width="30%">Student Name
												<div class="header_stop" style="width: 30%;">Student
													Name</div>
											</th>
											<th width="15%">Class
												<div class="header_stop" style="width: 15%;">Class</div>
											</th>
											<th width="9%">Action
												<div class="header_stop" style="width:9%;">Action </div>
											</th>
										</tr>
										</thead>
									
									<%
										int count = 1;
																			
										if (assessmentDomainForAssessment != null) {
											Iterator<GetSessionForFacultyDomain> iteratorForAssessment = assessmentDomainForAssessment.iterator();
											
											while (iteratorForAssessment.hasNext()) {
												GetSessionForFacultyDomain assessmentDomainWithVlaue = iteratorForAssessment.next();
									%>
									<tr class="result_row_tr "> <!-- class="result_row_tr row" --> 
										<%
											int counter = count++;
										%>
										<td width="40px" align="center" style="text-align: center;"><%=counter%></td>
										<td width="100px">
											<span name="enrId<%=counter%>"><%=assessmentDomainWithVlaue.getEnid()%> </span>
											<input	type="hidden" name="enrId<%=counter%>" value="<%=assessmentDomainWithVlaue.getEnid()%>" />
											
										</td>
										<td width="100px" style="text-align: left;">
											<input	type="text" name="name<%=counter%>" value="<%=assessmentDomainWithVlaue.getStudentName()%>" readonly />
										</td>
										<td align="center" width="200px">
											<input type="text" maxlength="5" style="text-align: center;"  name="class<%=counter%>"	value="<%=assessmentDomainWithVlaue.getClass1()%>" readonly />
										</td>
										<td width="100px">
											<input type="checkbox" id="<%=counter%>" class="aa"  name="checkboxGroup<%=counter%>"  checked />
										</td>
									</tr>
									<%
										
											}
										System.out.println("tableSize = "+count);
										
									}
									%>
									
								</table>
							</div>
						</div>
						<input type="hidden" value="<%=count%>" name="tableSize" />
						<%
							if (noDataFound != null) {
						%>
						<p class="nodata autohide">
							<%=noDataFound%></p>
						<%
							} else if ((String) request.getAttribute("asessID") == null) {
							} else {
						%>

						<p style="text-align: center;">
							<input type="button" id="update_btn" class="add_btn1"
								value="Submit">
						</p>
						<%
							}
						%>



						<p style="text-align: center;">
							<input id="update_btn" class="add_btn1" value="Submit"
								type="button"  onclick="submitStudentAttandace()">
						</p>

					</div>
				</form>
			</div>
		</div>
		<!-- second part starts here -->

		<%-- <div class="postpon_mod <%=Tabmsg%>second" id="second_part">
	<div style="text-align: center;">
		<p class="strong">Certify Assignment</p>
		
	   <form name="AssignCertify" method="post">

         
							 <% CertiftStudentDao certifyStudentDao = new CertiftStudentDao();
		
                              ArrayList<AdminDomain> arrayListForAssignment = new ArrayList<AdminDomain>();
                              arrayListForAssignment=certifyStudentDao.retriveAssignMentDetails(facultyId,orgId);
		                      Iterator<AdminDomain> iteratorForAssignment= arrayListForAssignment.iterator();%>
		                      <select name="asignID" id="asignID" class="box_lng" onchange="clickAssignmentID();" style="margin-left: 0px">
		                      <option value="">Choose Assignment ID </option>
		                      <option value="All">All</option>
		                      
		                    <% while(iteratorForAssignment.hasNext())
		                     {
		                    	AdminDomain adomain=iteratorForAssignment.next();
		    	             String Id=adomain.getAssignment_ID();
		    	             String name=adomain.getAssignment_name();
		    	             String idname=name+"("+Id+")";%>
		                      <option  value="<%= Id %>"><%=idname%></option>	
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
		Student ID
		</td>
		<td style="width: 15%">
		Student Name
		</td>
		<td style="width: 15%">
		File 
		</td>
		<td>
		Marks
		</td>
		<td>
		Remarks
		</td>
		<td>
		Status
		</td>
		<td>
		Action
		</td>
		</tr>
		<% 
		if(assessmentDomainForAssignment!=null){
			Iterator<AssessmentDomain> iteratorForAssign = assessmentDomainForAssignment.iterator();
			while(iteratorForAssign.hasNext()){
				AssessmentDomain assignmentDomainWithValue = iteratorForAssign.next();
			
			%>
			
			<%if(request.getAttribute("FacultySuccess")!=null){ 
			//System.out.println("message value:"+request.getAttribute("FacultySuccess"));%>
			  <p class="su autohide">  <%= request.getAttribute("FacultySuccess")%></p>
			     <%} else if(request.getAttribute("FacultyFailure")!=null) { %>
			     <p class="autohide" style="color:red; font-size:17px; font-weight: bold; top: 11%; left: 41%; position: absolute;"><%= request.getAttribute("FacultyFailure") %></p>
			     <%} %>
			<tr class="result_row_tr row">
			<td>
		   <input type ="text" name="assignmentId<%=assignmentDomainWithValue.getAssessmentId()%>" readonly	value="<%=assignmentDomainWithValue.getAssessmentId()%>" class="<%=assignmentDomainWithValue.getAssessmentId()%>"/>
			</td>
			<td>
	        <%=assignmentDomainWithValue.getAssessmentName() %>
			</td>
			<td>
			<input type="text"	name="recipient<%=assignmentDomainWithValue.getAssessmentId()%>" readonly value="<%=assignmentDomainWithValue.getRecipientType()%>" class="<%=assignmentDomainWithValue.getAssessmentId()%>"/>
			</td>
			<td>
			<%=assignmentDomainWithValue.getUserId() %>
			</td>
			
			<td>
			<%if(assignmentDomainWithValue.getPathOfFile() !=null){ %>
			<a style="color: #000; padding-top:5px;" href="/KODE/ControllerServlet/FTPDownloadFileDemo?StudentID=<%=uploadedid%>&filePath=<%=filepath1%>">Download File</a>
			<%} %>
			</td>
			
			<td>
			<input class="<%=assignmentDomainWithValue.getAssessmentId()%>" type="text" name="marks<%=assignmentDomainWithValue.getAssessmentId()%>" id="assessMarks" value="<%=assignmentDomainWithValue.getMark()%>">
			</td>
			<td>
			<input class="<%=assignmentDomainWithValue.getAssessmentId()%>" type="text" name="status<%=assignmentDomainWithValue.getAssessmentId()%>" id="status"  value="<%=assignmentDomainWithValue.getStatus()%>">
			
			<input type="hidden" name="uploadedid" value="<%=uploadedid %>"></td>
	
			<td><input class="<%=assignmentDomainWithValue.getAssessmentId()%>" type="text" name="remarks<%=assignmentDomainWithValue.getAssessmentId()%>" id="remarks"  value="<%=assignmentDomainWithValue.getReMarks()%>">
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
			<input type="button"   class="add_btn1" value="submit" onclick="clickAssignmentSubmit();">
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
				}); */
			
		/* 		var table = $('#tblData');
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
/* onloadAssign(); */

</script>

<script>
$('#datetimepicker2').datetimepicker({
		yearRange : '1900:',
		  // dateFormat: 'dd/mm/yy',
		  format:'d/m/Y',
		  timepicker:false,
		   changeMonth : true,
		   changeYear : true,
		   minDate : '0d',
});
</script>
<!-- Textbox Default Dte start -->
<script>
var date = new Date();
document.getElementById("demo").value = date.getDate() + '/' + (date.getMonth() + 1)+ '/' + date.getFullYear();
</script>
<!-- Textbox Default Dte end -->
<style>
.page_footer1 {
	display: none;
}

.input-disabled input[type=text]:disabled {
	border: none !important;
}
</style>
</html>