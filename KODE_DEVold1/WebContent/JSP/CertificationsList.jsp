<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.kds.KODE_DEV.domain.AdminDomain"%>
     <%@page import="com.kds.KODE_DEV.domain.AssessmentDomain"%>
     <%@page import="com.kds.KODE_DEV.dao.CertiftStudentDao"%>
  	 <%@page import="java.util.Iterator"%>
     <%@page import="java.util.ArrayList"%>
     <%@page import="java.io.File" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>  -->
<script src="../JS/jquery.min.js"></script> 
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
.search_div{
margin-top: -33px;
position: absolute;
left: 1018px;
}
.opt{
width: 265px !important;
padding: 7px !important;
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

	    String[] stuId=(String[])session.getAttribute("assessResultDomain");
	    String  studentId[]=(String[])session.getAttribute("ResultDomain") ;
	    String filepath1=(String)session.getAttribute("FilePath");
	    String uploadedid1=(String)session.getAttribute("uploadedId1");
	    //System.out.println("file path:"+filepath +"uploadedid:"+uploadedid);
	    //1st form
        ArrayList<AssessmentDomain> assessmentDomainForAssessment = new ArrayList<AssessmentDomain>();
		
        assessmentDomainForAssessment = (ArrayList<AssessmentDomain>)request.getAttribute("recipientTypeForAssessment");
		//2nd form
		ArrayList<AssessmentDomain> assessmentDomainForAssignment = new ArrayList<AssessmentDomain>();
		
		assessmentDomainForAssignment = (ArrayList<AssessmentDomain>)request.getAttribute("recipientTypeForAssignment");
	   
 	%>
 	<script type="text/javascript">
 	
 	function clickAssessmentSubmit(){
 		
 		document.cerify.action="/KODE_DEV/ControllerServlet/AssessmentCerifyStudentServlet";
 		document.cerify.submit();	
 			
 	}
 	
 	function clickAssignmentSubmit(){
 		
 		document.AssignCertify.action="/KODE_DEV/ControllerServlet/AssignmentCertifyStudent";
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
	
	var assessid=document.getElementById("asessID").value;
	//alert("assessment id:"+assessid);
	document.cerify.action="/KODE_DEV/ControllerServlet/CertificationStudentServlet?assessid="+assessid+"";
	document.cerify.submit();
}
	
	function onloadAssess(){
		<% String assesid=(String)request.getAttribute("asessID");
		//System.out.println("assessment id in an onload:"+assesid);
		if(assesid!=null && assesid.length()>0) {
			//System.out.println("assessment id in onload:"+assesid);
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
	document.AssignCertify.action="/KODE_DEV/ControllerServlet/CertificationStudentForAssignment?assignid="+assignid+"";
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
//System.out.println("tabmsg="+Tabmsg);
String noDataFound =(String)request.getAttribute("noDataFoundMsg");
//System.out.println("JSP MSG :::::::::::"+noDataFound);
 %>


<body>

	<div class="container">
		<%-- <%@include file="all_one_header_knowstore.jsp"%> --%>
		<%@include file="../JSP/headers.jsp"%>
		
		
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/Participant_menu.jsp" %>
		</div>
		<div style="clear: both;"></div>
	<%if(successMsg!=null){ %>
<p class="autohide" style="position: absolute; color: green"><%=successMsg %></p>
	<%} %>
	
	
		<%-- <div class="tabmain <%=Tabmsg%>">
		<!-- <ul>
		<li id="ass_tab">Assessment</li>
		<li id="assi_tab">Assignment</li>
		</ul> -->
		</div> --%>
		
		<div class="postpon_mod <%=Tabmsg%>ind" id="first_part">
		<div style="text-align: center;">
		<p class="strong">Evaluate</p>
		
		<form name="cerify" method="post">

								 <% CertiftStudentDao certifySDao = new CertiftStudentDao();
		
                           	    ArrayList<AdminDomain> arrayListForAssessment = new ArrayList<AdminDomain>();
                          	   	arrayListForAssessment = certifySDao.retriveStudentAssessMentDetails(facultyId,orgId);
		                      	Iterator<AdminDomain> iteratorForAssessmentId= arrayListForAssessment.iterator();%>
		                      	
		                      <select name="asessID" id="asessID"  class="box_lng" onchange="clickAssessmentID();" style="margin-left: 0px">
		                      
		                      <option value="">Choose Assignment</option>
		                      <option value="All">All</option>
		                      
		                    <% while(iteratorForAssessmentId.hasNext())
		                     {
		                    	AdminDomain adomain=iteratorForAssessmentId.next();
		    	             String Id = adomain.getAssignment_ID();
		    	             String name = adomain.getAssignment_name();
		    	             String idname=name+" ("+Id+")";
		    	             //System.out.println("ID="+Id);%>
		                      <option  value="<%= Id %>"><%=idname%></option>	
		                         <%  }%>
		                      </select>
	                
		
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
<th width="16.16%">Sl #<div class="header_stop">Sl #</div></th>
		<th width="16.16%">
		Assignment ID
		<div class="header_stop">Assignment ID</div>
		</th>
		<th width="16.16%">
		Assignment Name
		<div class="header_stop">Assignment Name</div>
		</th>
		<th width="16.16%">
		Marks
		<div class="header_stop">Marks</div>
		</th>
		<th width="16.16%">
		Status
		<div class="header_stop">Status</div>
		</th>
		<th width="16.16%">
		Result
		<div class="header_stop">Result</div>
		</th>
		<!-- <td>Certificate</td> -->
		</tr>
		</thead>
		 <% int count=1;
		if(assessmentDomainForAssessment!=null){
			Iterator<AssessmentDomain> iteratorForAssessment = assessmentDomainForAssessment.iterator();
			int i=1;
			while(iteratorForAssessment.hasNext()){
				AssessmentDomain assessmentDomainWithVlaue = iteratorForAssessment.next();
			
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
			     <%} %> 
			     
			<tr class="result_row_tr row">
			 <td align="right"><%=count++ %></td>
			<td>
		   <input type ="text" name="assessmentId<%=assessmentDomainWithVlaue.getRecipientType()%>" readonly="readonly" value="<%=assessmentDomainWithVlaue.getAssessmentId()%>" class="ss<%=i%>"/>
			</td>
			<td align="left">
	        <%=assessmentDomainWithVlaue.getAssessmentName() %>
			</td>
			<td align="right">
			<input class="ss<%=i%>" type="text" name="marks<%=assessmentDomainWithVlaue.getRecipientType()%>" id="assessMarks" value="<%=assessmentDomainWithVlaue.getMark()%>">
			</td>
			<td>
			<input class="ss<%=i%>" type="text" name="status<%=assessmentDomainWithVlaue.getRecipientType()%>" id="status" value="<%=assessmentDomainWithVlaue.getStatus()%>">
			
			<input type="hidden" name="uploadedid" value="<%=uploadedid %>"></td>
	
			<td><input class="ss<%=i%>" type="text" name="remarks<%=assessmentDomainWithVlaue.getRecipientType()%>" id="remarks" value="<%=assessmentDomainWithVlaue.getReMarks()%>">
			</td>
			
			
			<%-- if(assessmentDomainWithVlaue.getMark()==""){ %>
			<td></td>
			<%} else{ %>
			<td><a href="../JSP/GetCertificate.jsp?id=<%=assessmentDomainWithVlaue.getAssessmentId() %>&mark=<%=assessmentDomainWithVlaue.getMark() %>&result=<%=assessmentDomainWithVlaue.getReMarks() %>&status=<%=assessmentDomainWithVlaue.getStatus() %>">Get Certificate </a></td>
			<%}  --%>
		<%	
		//System.out.println(assessmentDomainWithVlaue.getMark());
		i++;
			}} %>	
</table>
</div>
</div>
<%if(noDataFound!=null){ %>
	 <p class="nodata autohide"> <%=noDataFound %></p>
	<%} %>

			<p style="text-align: center;">
			<input type="button"   class="add_btn1" value="submit" onclick="clickAssessmentSubmit();">
			</p>
</div>
</form>
</div>
</div>	
	<!-- second part starts here -->
	
<%-- 	<div class="postpon_mod <%=Tabmsg%>second" id="second_part">
	<div style="text-align: center;">
		<p class="strong">Certify Assignment</p>
		
	   <form name="AssignCertify" method="post">

         
							 <% CertiftStudentDao certifyStudentDao = new CertiftStudentDao();
		
                              ArrayList<AdminDomain> arrayListForAssignment = new ArrayList<AdminDomain>();
                              arrayListForAssignment=certifyStudentDao.retriveStudentAssignMentDetails(facultyId,orgId);
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
		Marks
		</td>
		<td>
		Status
		</td>
		<td>
		Result
		</td>
		<td>
		Certificate
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
			<input class="<%=assignmentDomainWithValue.getAssessmentId()%>" type="text" name="marks<%=assignmentDomainWithValue.getAssessmentId()%>" id="assessMarks" value="<%=assignmentDomainWithValue.getMark()%>">
			</td>
			<td>
			<input class="<%=assignmentDomainWithValue.getAssessmentId()%>" type="text" name="status<%=assignmentDomainWithValue.getAssessmentId()%>" id="status"  value="<%=assignmentDomainWithValue.getStatus()%>">
			
			<input type="hidden" name="uploadedid" value="<%=uploadedid %>"></td>
	
			<td><input class="<%=assignmentDomainWithValue.getAssessmentId()%>" type="text" name="remarks<%=assignmentDomainWithValue.getAssessmentId()%>" id="remarks"  value="<%=assignmentDomainWithValue.getReMarks()%>">
			</td>
			<%
			//System.out.println(assessmentDomainWithVlaue.getMark());
			if(assignmentDomainWithValue.getMark()==""){ %>
			<td></td>
			<%} else{ %>
	<a href="../JSP/GetAssignCertificate.jsp?id=<%=assignmentDomainWithValue.getAssessmentId() %>&mark=<%=assignmentDomainWithValue.getMark() %>&result=<%=assignmentDomainWithValue.getReMarks() %>&status=<%=assignmentDomainWithValue.getStatus() %>">Get Certificate </a></td>

			<%}}} %>	
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
				var tablea = $('#tblDataA');
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
						 */
						//if(found == true)$('tr').show();else $(row).hide();
						 if(found == true)$(row).show();else $(row).hide();
						/* alert("message"); */
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
						 */
						//if(found == true)$('tr').show();else $(row).hide();
						 if(found == true)$(row).show();else $(row).hide();
						/* alert("message"); */
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
onloadAssign();

</script>
</html>