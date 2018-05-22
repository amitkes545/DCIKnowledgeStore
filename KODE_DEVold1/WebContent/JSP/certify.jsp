<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.kds.KODE_DEV.domain.AdminDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.AssessmentDao" %>
    <%@page import="com.kds.KODE_DEV.dao.AssignmentDao" %>
    
     <%@page import="com.kds.KODE_DEV.dao.SearchDao" %>
    <%@page import="java.util.Iterator" %>
     <%@page import="java.util.ArrayList" %>
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
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>


<%
//SearchDao sdao=new SearchDao();
/* String sequence=sdao.getSequenceValue();//selecting sequence value from dao
String assessid="assess"+sequence;
//System.out.println("sequence value in jsp:"+sequence+" assessid:"+assessid); */
	String username=(String)session.getAttribute("username");
   String facultyid=(String)session.getAttribute("userid");
   String orgid=(String)session.getAttribute("orgid");
   String assessmentId=(String)request.getAttribute("MsgExit");
   //System.out.println("faculty id in assessment jsp:"+facultyid+"orgid:"+orgid+ "assessment ID:"+assessmentId);
%>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
<script src="../JS/jquery.min.js"></script> 
<script>
$(document).ready(function () { 
	
	$(".assignment_tab_div").hide();
	$("#ass_tab").addClass("addbg");
	
	$("#r1").click(function() {
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
position: absolute;
top: 60px;
box-shadow: 5px 5px 5px 5px #FFF;
margin: 15px auto 0px;
padding: 15px 1px 32px 20px;
border: 3px solid #C2C2C2;
background-color: #FCF7F7;
border-radius: 4px;
opacity: 0.9;
right: 20px;
height: 330px;
margin-top: 121px;
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
.tabmain ul li{float: left; width: 48.95%;list-style: none; text-align: center; padding: 10px 0.1px;}
.su{
	color: #008000;
    font-size: 12px;
    font-weight: bold;
    top: 12%;
    position: absolute;
    right:0px;
    padding: 0px 36px;
}
.su1{
		    color: #008000;
    font-size: 10px;
    font-weight: bold;
    top: 15%;
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
});
</script>
<body>

<div class="container">
		
		<jsp:include page="../JSP/headers.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>

		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		
		<div class="tabmain">
		<ul>
		<li id="ass_tab">Assessment</li>
		<li id="assi_tab">Assignment</li>
		</ul>
		
		</div>
		
		<div class="assessment_tab_div">
		
		<div class="assessment_mod fiveeight">

			<p class="strong">Create Assessment </p>
			<% if (request.getAttribute("FacultySuccess")!=null) { 
					//System.out.println(" faculty success message value in if:"+request.getAttribute("FacultySuccess"));
					String message=(String)request.getAttribute("FacultySuccess");
					/*  String str[]=message.split("#");
					 String valid=str[0];
					 String valid1=str[1];
					 */%>
					<%-- <p class="su autohide"><%= valid %></p><br> --%>
					<p class="su1 autohide"><%=message %></p>		
					<%
				} else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
					<p class="autohide" style="color:red; font-size:12px; font-weight: bold; top: 10%; left: 30%; position: absolute;"><%= request.getAttribute("FacultyFailure") %></p>					
					<%
				} 
				
			%>
			
<form name="assessment" id="assForm" method="post" action="/KODE_DEV/ControllerServlet/AssessmentServlet" enctype="multipart/form-data">
<table>
<%if (request.getAttribute("MsgExit")!= null) {  %>
			
<p class="autohide" style="color:red; font-size:12px; font-weight: bold; top: 10%; left: 30%; position: absolute;"><%= request.getAttribute("MsgExit") %></p>					
					<%}%>
<tr>
<td><input class="assess" type="text" name="assessmentname" placeholder="Assessment Name" id="assessmentname"></td>
</tr>
<tr>
<td><textarea class="assess" name="Description" placeholder="Description" rows="5" cols="25"></textarea></td></tr>

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
		                      <select name="student_id" class="required">
		                      <option value="select">--select--</option>
		                    <% while(it.hasNext())
		                     {
		    	             String id=it.next().getAdminId();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	                
		</td>
		</tr>
		<tr class="group_tr group_tr_click">
		<td>
							 <% 
		
                             ArrayList<AdminDomain>al1=new ArrayList<AdminDomain>();
		                   al=asdao.sendGroupId(facultyid);
		                      Iterator<AdminDomain> it1= al.iterator();%>
		                      <select name="group_id">
		                      <option value="select">--select--</option>
		                    <% while(it1.hasNext())
		                     {
		    	             String id=it1.next().getGroup_name();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	                
		</td>
		</tr>
		<tr>
					<td><input class="assess" type="text" name="marks" id="marks" maxlength="3" style="margin-bottom: 10px"></td></tr>
					
					<tr>
					<td>
					<input id="assessment_btn" class="add_btn1" type="button" value="submit">
					<!-- <a class="back_txt" href="../JSP/Home.jsp">Back</a> --></td>
					<td><input type="hidden" name="facultyid" value=<%= facultyid %>></td>
					<td><input type="hidden" name="orgid" value=<%=orgid %>></td></tr>
					
</table>
</form>
		</div>
		
		</div>
		<!-- assessment_tab_div Ends here -->
		
		<div class="assignment_tab_div" style="display: none;">
		
		<div class="assessment_mod fiveeight">

			<p class="strong">Create Assignment</p>
			<% if (request.getAttribute("FacultySuccess")!=null) { 
					//System.out.println(" faculty success message value in if:"+request.getAttribute("FacultySuccess"));
					String message=(String)request.getAttribute("FacultySuccess");
					/*  String str[]=message.split("#");
					 String valid=str[0];
					 String valid1=str[1];
					 */%>
					<%-- <p class="su autohide"><%= valid %></p><br> --%>
					<p class="su1 autohide"><%=message %></p>						
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
		                      <option value="select">--select--</option>
		                    <% while(itq.hasNext())
		                     {
		    	             String id=itq.next().getAdminId();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	                
						</td>
					</tr>
					 <tr class="group_tr">
						
						<td class="assignment5">
							 <% 
		
                             ArrayList<AdminDomain>al11=new ArrayList<AdminDomain>();
		                   al11=asdaoq.sendGroupId(facultyid,orgid);
		                      Iterator<AdminDomain> it11= al.iterator();%>
		                      <select name="group_id" class="assignment2">
		                      <option value="select">--select--</option>
		                    <% while(it11.hasNext())
		                     {
		    	             String id=it11.next().getGroup_name();%>
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
		</div>
		
		
		</div>
		
		
		<%@ include file="../JSP/FooterViews.jsp"%>
	</div>
<script type="text/javascript">
$(document).ready(function() {
	
	 $("#assignment_btn").click(function(e) {
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
	      
         
         
         if($("input:radio[name='group']").is(":checked")) {
	    	  
	    	   $("#display1").hide();
   		  }
	       else
	    	   {
	    	   isValid = false;
	    	   $('#display1').slideDown().html('<span id="error"><br/>Select any one option</span>');
		        return false;
	    	   }  
         
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
	    	   $('#display').slideDown().html('<span id="error"><br/>Select any one option</span>');
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
</body>
</html>
