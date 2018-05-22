<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.dao.GetCourseForFacultyDao" %>
    <%@page import="com.kds.KODE_DEV.domain.AdminDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.AssessmentDao" %>
    <%@page import="com.kds.KODE_DEV.dao.AssignmentDao" %>
	<%@page import="com.kds.KODE_DEV.dao.SearchDao" %>
	<%@page import="com.kds.KODE_DEV.domain.CourseFacultyDomain" %>
	<%@page import="com.kds.KODE_DEV.dao.CollaborateDao" %>
	<%@page import="com.kds.KODE_DEV.dao.CertiftStudentDao" %>
	
	<%@page import="java.util.Iterator" %>
	<%@page import="java.util.ArrayList" %>
	<%@page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<link href="../CSS/kedu.css" rel="stylesheet"/>
<link href="../CSS/design-common.css" rel="stylesheet"/>
<link href="../CSS/notification-new.css" rel="stylesheet"></link>


<!-- Changed code by senthil on 28-11-2017 start -->
<link href="../CSS/jquery.datetimepicker.css" rel="stylesheet"></link>
<!-- Changed code by senthil on 28-11-2017 end -->



<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->

<!-- Changed code by senthil on 28-11-2017 start -->
<script type="text/javascript" src="../JS/jquery.datetimepicker.full.min.js"></script>
<!-- Changed code by senthil on 28-11-2017 end -->


<script src="../JS/MessageFadeOut.js"></script>


<%
//SearchDao sdao=new SearchDao();
/* String sequence=sdao.getSequenceValue();//selecting sequence value from dao
String assessid="assess"+sequence;
//System.out.println("sequence value in jsp:"+sequence+" assessid:"+assessid); */
	String username=(String)session.getAttribute("username");
   String facultyid=(String)session.getAttribute("userid");
   String orgid=(String)session.getAttribute("orgid");
   String assessmentId=(String)request.getAttribute("MsgExit");
   String Tabmsg=request.getParameter("tabvalue");
  /*  String success=(String)request.getAttribute("FacultySuccess");
   System.out.println("success==>"+success); */
   //System.out.println("faculty id in assessment jsp:"+facultyid+"orgid:"+orgid+ "assessment ID:"+assessmentId);
%>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->

<script>
$(document).ready(function () { 
	
	$(".assignment_tab_div").hide();
	$("#ass_tab").addClass("addbg");
	
	$("#r1").click(function() {
		
		 var select = $('#studentids');
		 var selectedValue=$('#courses option:selected').val();
		
 		select.find('option').remove();
 	
	     var ctx = "${pageContext.request.contextPath}";
	
	     $.get('${pageContext.request.contextPath}/getSudentsForFacultyOnCourse', {selectedValue : selectedValue},
	    		  function(jsonResponse) {
	    		
    	     		jsonResponse = $.parseJSON(jsonResponse);
    	     	
    	     		$('<option>').val('').text('Select Participants*').appendTo(select);
    	     		$.each(jsonResponse, function(index, value) {
		    	 		 
		    	 		
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
	$("#ass_tab").click(function() {
		$(this).addClass("addbg");
		$("#assi_tab").removeClass("addbg");
		 $(".assignment_tab_div").hide();
		 $(".assessment_tab_div").show();
		
		 });
	$("#assi_tab").click(function() {
		
		$(this).addClass("addbg");
		$("#ass_tab").removeClass("addbg");
		 $(".assessment_tab_div").hide();
		 $(".assignment_tab_div").show();
		 // $(".select_tr").hide();
		 });
	
});
$(document).ready(function () { 
	$("#asir1").click(function() {
	 $(".select_tr").show();
	 $(".group_tr").hide();
	 });
	$("#asir2").click(function() {
		 $(".group_tr").show();
		 $(".select_tr").hide();
		 });
	$("#asir3").click(function() {
		 $(".group_tr").hide();
		 $(".select_tr").hide();
		 });
	});

</script>


<style type="text/css">

.assessment_mod{
position: static;
top: 19px;
box-shadow: 5px 5px 5px 5px #FFF;
margin: 15px auto 0px;
padding: 15px 1px 32px 20px;
border: 3px solid #C2C2C2;
background-color: #FCF7F7;
border-radius: 4px;
opacity: 0.9;
float:right;
margin-right:15px;
right: 20px;
height: 380px;
margin-top:70px;
overflow-y: scroll;
width: 295px;
}


.strong
{
font-size: 18px;
color: #0094DC;
text-align: center;
margin: 7px 3px 10px 5px;
font-family: bold;
}

.assessment_mod .strong
{
font-size: 18px;
color: #0094DC;
text-align: center;
margin: 7px 5px 10px 5px;
font-family: bold;
}
.assessment_mod tr td:first-child
{
	color: #000;
	font-size: 12px;	
	font-family: sans-serif;
	/* display: none; */
	
	
	
}
.ff
{
	font-size: 18px;
color: #0094DC;
text-align: center;
margin: 7px -41px 10px 5px;
font-family: bold;
}
.assessment_mod tr td:nth-child(2)
{
	color: #000;
	width: 40px;
	text-align: center;
	font-size: 12px;
	/* display: none; */
}
.assessment_mod tr td span{
color: #f63a4c;
}
.assessment_mod tr td:last-child > input
{width: 246px; margin: 5px 0px; padding: 7px; border-radius: 3px; margin-bottom: 6px; border: 1px solid #c2c2c2; font-family: regular; }
.tab
{
	width:113px;
}
.assessment_mod tr td:last-child > select
{width: 265px; padding: 5px; margin: 5px 0px;}
.assessment_mod tr td:last-child > textarea
{
    width: 249px;
    padding: 5px;
    margin: 5px 0px;
    font-family: regular;
    font-size: 13px;
    border-radius: 4px;
    border: 1px solid #c2c2c2;
}
.assessment_mod tr th{color: #000;font-family:regular;
font-size:14px;}
.white{color: #000 !important;
font-family:regular;
font-size:15px;
}
.add_btn1{margin-bottom: 15px;}
.select_tr, .group_tr
{
display: none;
}
.addbg{    background-color: #FCF7F7;
    border-radius: 4px;
    /* opacity: 0.9; */
    border: 3px solid #C2C2C2;
    border-bottom: none;
    color:#000!important;
    }
.r123 input {
    width: 15px!important;
}
.tabmain
{
    position: absolute;
    right: 22px;
    top: 140px;
    width: 315px;
    z-index: 1;
    border: 1px solid #c2c2c2;
    border-bottom: none;
    BORDER-RADIUS: 5PX;
    
}
.tabmain ul li{float: left; width: 48.95%;list-style: none; text-align: center; padding: 10px 0.1px;}
.su{
	color: #008000;
    font-size: 15px;
    font-weight: bold;
    top: 12%;
    position: absolute;
    right:0px;
    padding: 0px 36px;
}
.su1{
		    color: #008000;
    font-size: 15px;
    font-weight: bold;
    top: 2%;
    position: absolute;
    right:0px;
    padding: 0px 36px;
}
</style>

</head>
<script type="text/javascript">
/* function validateAssessment(){
	//var asid=document.getElementById("assessId").value;
	var asName=document.getElementById("assessmentname").value;
	var file1=document.getElementById("document").value;
	var marks=document.getElementById("marks").value;
	 if(asName == ""){
		alert("Enter Assessment Name");
		document.getElementById("assessmentname").focus();
		return false;
	}else if(file1 == ""){
		alert("Select document for assessment");
		document.getElementById("document").focus();
		return false;
	}else if(marks == ""){
		alert("Enter marks");
		document.getElementById("marks").focus();
		return false;
	}
	else {
		document.assessment.action="/KODE_DEV/ControllerServlet/AssessmentServlet";
		document.assessment.submit();
	}
} */
$(document).ready(function() {
	$("#marks").keydown(function (e) {
    	
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

$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
	 $('#courses').change(function () {
	  	 var selectedValue = this.selectedOptions[0].value;
	     var selectedText  = this.selectedOptions[0].text;
	     // alert(selectedText);
	     // alert(selectedValue);
	     var ctx = "${pageContext.request.contextPath}";
	     //alert(ctx);
	     $.get('${pageContext.request.contextPath}/getSubjectsForFaculty', {selectedValue : selectedValue},
	    		  function(jsonResponse) {
	    			//alert(jsonResponse);
	    			//alert("inside");
	    			console.log("json response :",jsonResponse);
    	     		var select = $('#subjects');
    	     		select.find('option').remove();
    	     		jsonResponse = $.parseJSON(jsonResponse);
    	     		console.log("parsed json response :", jsonResponse);
    	     		$('<option>').val('').text('Select Subject*').appendTo(select);
    	     		$.each(jsonResponse, function(index, value) {
		    	 		  // console.log('Index value here :',index);
		    	 		  // console.log('Actual obj value here :',value.subject);
		    	 			 $('<option>').val(value.subjectId).text(value.subject).appendTo(select);
		    	      }); 
	    			
	    		});
	     
	     /**
	     $.ajax({
	            type : "POST",
	            url : "GetSubjects.jsp",
	            data : "coursename="+selectedText+"&courseid="+selectedValue,
	            success : function(data) {
	                //alert("success"+data);
	              $("#subjects").empty();
	                //$('#subjects').text("Maths");
	                //$('#subjects').text("Maths2");
	                // $('select[name^="subjects"] option[value="Bruce Jones"]');
	               
	                $("#subjects").append('<option value=1>test</option>');
	                $("#subjects").append('<option value=2>test2</option>');
	            }
	        });
	     **/
	 });
});
</script>
<body>
 <% if (request.getAttribute("FacultySuccess")!=null) { 
					//System.out.println(" faculty success message value in if:"+request.getAttribute("FacultySuccess"));
					String message=(String)request.getAttribute("FacultySuccess");
					/*  String str[]=message.split("#");
					 String valid=str[0];
					 String valid1=str[1];
					 */%>
					
					<p class="success"><%=message %></p>		
					<%
				} else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
					<p class="failure"><%= request.getAttribute("FacultyFailure") %></p>					
					<%
				} 
				
			%>

<div class="container">
		
		<jsp:include page="../JSP/headers.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>
<%if(username==null){ 
	response.sendRedirect("../JSP/error.jsp");
} %>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		
		<div class="tabmain <%=Tabmsg%>">
		<!-- <ul>
		<li id="ass_tab">Assessment</li>
		<li id="assi_tab">Assignment</li>
		</ul> -->
		
		</div>
		
		<div class="assessment_tab_div">
		
		<div class="assessment_mod fiveeight">

		<%-- 
			<% if (request.getAttribute("FacultySuccess")!=null) { 
					//System.out.println(" faculty success message value in if:"+request.getAttribute("FacultySuccess"));
					String message=(String)request.getAttribute("FacultySuccess");
					/*  String str[]=message.split("#");
					 String valid=str[0];
					 String valid1=str[1];
					 */%>
					<p class="su autohide"><%= valid %></p><br>
					<p class="success"><%=message %></p>		
					<%
				} else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
					<p class="failure"><%= request.getAttribute("FacultyFailure") %></p>					
					<%
				} 
				
			%> --%>
				<p class="strong">Create Assignment </p>
<form name="assessment" id="assForm" method="post" action="/KODE_DEV/ControllerServlet/AssessmentServlet" enctype="multipart/form-data">
<table>
<%if (request.getAttribute("MsgExit")!= null) {  %>
			
<p class="autohide" style="color:red; font-size:12px; font-weight: bold;"><%= request.getAttribute("MsgExit") %></p>					
					<%}%>
<tr>
<td><input class="assess" type="text" name="assessmentname" placeholder="Assignment Name*" id="assessmentname"></td>
</tr>

<tr>
	<td>
	<input type="text" name="datetimepicker2" class="box assess" id="datetimepicker2" readonly="readonly" placeholder="Due Date" >
	</td>
</tr>
<tr>
	<td class="sel">
		<select name="category" class="assess" id="category">
			<option value="">Assignments Type</option>
			<% 
CertiftStudentDao certifySDao = new CertiftStudentDao();
 ArrayList<String> alo = certifySDao.getAssessmentType();
 Iterator<String> itro = alo.iterator();
 String orgname=null; 
while (itro.hasNext())
 { 
String assessmentTyp = itro.next();
 %>
 <option value="<%=assessmentTyp%>"><%=assessmentTyp%></option> 
<% }%>
	</td>
</tr>


<tr>
<td>
<select id="courses" name="courseid" class="select-box" style="margin-bottom:10px;">
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
										List<CourseFacultyDomain> courses = getCourseForFacultyDao.getCourseInAssignmentForFaculty(facultyid, orgid);
										for(CourseFacultyDomain course : courses)
										{
											System.out.println("course :"+course.getCourseId());
											result = course.getCourseId();
									%>
											<option value="<%=result%>"><%=result%></option>
									<%
										}
									%>
									
</select></td>
</tr>
<tr>
<td>
<select id="subjects" name="subjectid" class="select-box">
	<option  value="">Select Subjects</option>
</select>
</td>
</tr>
<tr>
<td><textarea class="assess" name="Description" placeholder="Description*" rows="5" cols="25"></textarea></td></tr>

 <tr>
 <td><input class="assess" style="color: #000;" type="file" name="document" id="document" placeholder="Select Question Paper"></td></tr>
  <tr><!-- <td>To send</td><td>:</td> -->
 <td class="r123">
 <input id="r1" type="radio" value="Individual" name="radiogroup">Individual 
 <input id="r2" type="radio" value="Group" name="radiogroup">Group
 <input id="r3" type="radio" value="All" name="radiogroup">All
 <label id="display" style="width:250px; color: red"></label><br>
 </td>

 </tr>
		<tr class="select_tr">
		<td class="student_sel">
							 <% AssessmentDao asdao=new AssessmentDao();
		
                             ArrayList<AdminDomain>al=new ArrayList<AdminDomain>();
		                   al=asdao.sendIndualGroup(facultyid,orgid);
		                      Iterator<AdminDomain> it= al.iterator();%>
		                      <select id="studentids" name="student_id" class="required">
		                      <option value="select">Choose Participant ID</option>
		                    <% while(it.hasNext())
		                     {
		                    	AdminDomain domain=it.next();
			    	             String id=domain.getAdminId();
			    	              String name=domain.getAdminName();
			    	             String idname=name+" ("+id+")";
			    	            // System.out.println(idname);
			    	             %>
		                      <option  value="<%= id %>"><%=idname%></option>	
		                         <%  }%>
		                      </select>
	                
		</td>
		</tr>
		<tr class="group_tr group_tr_click">
		<td>
							 <% 
		
                             ArrayList<AdminDomain>al1=new ArrayList<AdminDomain>();
		                   al=asdao.sendGroupId(facultyid,orgid);
		                      Iterator<AdminDomain> it1= al.iterator();%>
		                      <select name="group_id">
		                      <option value="select">Choose Group ID</option>
		                    <% while(it1.hasNext())
		                     {
		                    	AdminDomain adom=it1.next();
			    	             String grp_name=adom.getGroup_name();
			    	               String new_grp_name=adom.getNew_Group_name();%>
			                      <option  value="<%=grp_name%>"><%=new_grp_name%></option>	
		                         <%  }%>
		                      </select>
	                
		</td>
		</tr>
		<tr>
					<td><input class="assess" Placeholder="Marks*" type="text" name="marks" id="marks" maxlength="3" style="margin-bottom: 10px"></td></tr>
					
					<tr>
					<td>
					<input id="assessment_btn" class="add_btn1" type="button" value="Submit" style="width: 262px;">
					<!-- <a class="back_txt" href="../JSP/Home.jsp">Back</a> --></td>
					<td><input type="hidden" name="facultyid" value=<%= facultyid %>></td>
					<td><input type="hidden" name="orgid" value=<%=orgid %>></td></tr>
					
</table>
</form>
		</div>
		
		</div>
		<!-- assessment_tab_div Ends here -->
		
		<div class="assignment_tab_div" style="display: none;">
		
<%-- 		<div class="assessment_mod fiveeight">

			<p class="strong">Create Assignment</p>
			<% if (request.getAttribute("FacultySuccess")!=null) { 
					//System.out.println(" faculty success message value in if:"+request.getAttribute("FacultySuccess"));
					String message=(String)request.getAttribute("FacultySuccess");
					 String str[]=message.split("#");
					 String valid=str[0];
					 String valid1=str[1];
					%>
					<p class="su autohide"><%= valid %></p><br>
					<p class="su1 autohide"><%=valid1 %></p>						
					<%
				} else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
					<p class="autohide" style="color:red; font-size:23px; font-weight: bold; top: 11%; left: 46%; position: absolute;"><%= request.getAttribute("FacultyFailure") %></p>					
					<%
				} 
				
			%>
			<!-- action="/KODE_DEV/ControllerServlet/AssignmentServlet" -->
<form name="assignment" id="assiForm" action="/KODE_DEV/ControllerServlet/AssignmentServlet" method="post" enctype="multipart/form-data">
<table>
<%if (request.getAttribute("MsgExit")!= null) {  %>
			
<p class="autohide" style="color:red; font-size:15px; font-weight: bold; top: 12%; left: 30%; position: absolute;"><%= request.getAttribute("MsgExit") %></p>					
					<%}%>

<tr><!-- <td>Assignment name</td><td>:</td> -->
<td><input class="assignment1"  type="text" name="assignmentname" id="assignmentname" placeholder="Assignment Name"></td>
</tr>
<tr><!-- <td>Description</td><td>:</td> -->
<td><textarea class="assignment1" name="Description" rows="5" cols="25" placeholder="Description"></textarea></td></tr>

 <tr><!-- <td>Select question paper</td><td>:</td> -->
 <td><input class="assignment1" style="color: #000;" type="file" name="document" id="document"></td></tr>
 
  <tr>
  <!-- <td>To send</td>
  <td>:</td> -->
   <td class="r123">
 <input id="asir1" type="radio" value="Individual" name="group">Individual 
 <input id="asir2" type="radio" value="Group" name="group">Group
 <input id="asir3" type="radio" value="All" name="group">All
 <label id="display1" style="width:250px; color: red"></label><br>
 </td>
 </tr>
 <tr class="select_tr">
 
						<td class="assignment4">
							 <% AssignmentDao asdaoq=new AssignmentDao();
		
                             ArrayList<AdminDomain>alq=new ArrayList<AdminDomain>();
		                   alq=asdaoq.sendIndualGroup(facultyid,orgid);
		                      Iterator<AdminDomain> itq= alq.iterator();%>
		                      <select name="student_id" class="assignment1">
		                      <option value="select">Choose Participant ID</option>
		                    <% while(itq.hasNext())
		                     {
		                    	AdminDomain domain=itq.next();
		    	             String id=domain.getAdminId();
		    	              String name=domain.getAdminName();
		    	             String idname=name+"("+id+")";
		    	            // System.out.println(idname);
		    	             %>
		                      <option  value="<%= id %>"><%=idname%></option>	
		                         <%  }%>
		                      </select>
						</td>
					</tr>
					 <tr class="group_tr">
						
						<td class="assignment5">
							 <% 
		
                             ArrayList<AdminDomain>al11=new ArrayList<AdminDomain>();
		                   al11=asdaoq.sendGroupId(facultyid,orgid);
		                      Iterator<AdminDomain> it11= al11.iterator();%>
		                      <select name="group_id" class="assignment2">
		                      <option value="select">Choose Group ID</option>
		                    <% while(it11.hasNext())
		                     {
		                    	AdminDomain domain=it11.next();
		    	             String id=domain.getGroup_name();
		    	             //System.out.println(id);%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	                
						</td>
					</tr>
	
		
					<tr>
					<td><input id="assignment_btn" class="add_btn1" type="button" value="submit">
					<td><input type="hidden" name="facultyid" value=<%= facultyid %>></td>
					<td><input type="hidden" name="orgid" value=<%=orgid %>></td>
					</tr>
					
</table>
</form>
		</div> --%>
		
		
		</div>
		
		
		<%@ include file="../JSP/FooterViews.jsp"%>
	</div>
<script type="text/javascript">
$(document).ready(function() {
	
	 $("#assignment_btn").click(function(e) {
		 var isValid = true;
		 
		 $('input[type="text"].assignment1').each(function() {
             if ($.trim($(this).val()) == '') {
                 isValid = false;
                 $(this).css({
                     "border": "1px solid red",
                 });
             }
             else {
                 $(this).css({
                     "border": "",
                    // "background": ""
                 });
             }
         });

		 
		 $('textarea.assignment1').each(function() {
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
         $('input[type="file"].assignment1').each(function() {
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
	      
         
         
         if($("input:radio[id='asir1']").is(":checked")) {
        	 
        	 
       	  $('select.assignment1').each(function() {
	    	   
		            if ($.trim($(this).val()) == 'select') {
		                isValid = false;
		                $(this).css({
		                    "border": "1px solid red",
		                    //"background": "#FFCECE"
		                });
		            }
		            else {
		            	isValid = true;
		            	$(this).css({
		                    "border": "",
		                   // "background": ""
		                });
		            }
		        });
	    	  }
         
         if($("input:radio[id='asir2']").is(":checked")) {
          	  $('select.assignment2').each(function() {
   	    	   
   		            if ($.trim($(this).val()) == 'select') {
   		                isValid = false;
   		                $(this).css({
   		                    "border": "1px solid red",
   		                    //"background": "#FFCECE"
   		                });
   		            }
   		            else {
   		            	isValid = true;
   		            	$(this).css({
   		                    "border": "",
   		                   // "background": ""
   		                });
   		            }
   		        });
   	    	  }
         
         if($("input:radio[name='group']").is(":checked")) {
	    	  
	    	   $("#display1").hide();
 		  }
	       else
	    	   {
	    	   isValid = false;
	    	   $('#display1').slideDown().html('<span id="error"><br/>Select any one receipient</span>');
		        return false;
	    	   }  

         
         if (isValid == false){

             e.preventDefault();
         }
         else {
           //alert('Thank you for submitting')
           $("#assiForm").submit();
           
         }
	 });
	 $("#assessment_btn").click(function(e) {
	      var isValid = true;
	      /* $('.search_result input[type="text"]').each(function() { */
          $('input[type="text"].assess').each(function() {
              if ($.trim($(this).val()) == '') {
                  isValid = false;
                  $(this).css({
                      "border": "1px solid red",
                  });
              }
              else {
                  $(this).css({
                      "border": "",
                     // "background": ""
                  });
              }
          });
	      
          $('textarea.assess').each(function() {
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
          $('select.assess').each(function() {
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
          
          $('input[type="file"].assess').each(function() {
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
	        
          if($("input:radio[id='r1']").is(":checked")) {
        	  $('.student_sel select').each(function() {
	    	   
		            if ($.trim($(this).val()) == 'select') {
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
		    	
		    	   $('.group_tr_click select').each(function() {
			            if ($.trim($(this).val()) == 'select') {
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
          if($("input:radio[name='radiogroup']").is(":checked")) {
	    	  
	    	   $("#display").hide();
     		  }
	       else
	    	   {
	    	   isValid = false;
	    	   $('#display').slideDown().html('<span id="error"><br/>Select any one receipient</span>');
		        return false;
	    	   }  
	         // here end working ok 
	     if (isValid == false){
	          
	             e.preventDefault();
	         }
	         else {
	           //alert('Thank you for submitting')
	           $("#assForm").submit();
	         }
	      });
	});

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
</body>
<style>
.autohide {
position:absolute;
top:180px;
right:350px;
}
</style>
</html>