<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="com.kds.KODE_DEV.dao.*"%>
<%@ page import="com.kds.KODE_DEV.domain.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/Validatestudent.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<link href="../CSS/kedu.css" rel="stylesheet"/>
<link href="../CSS/design-common.css" rel="stylesheet"></link>
<!--  for country code select css -->
<link rel="stylesheet" href="../CSS/intlTelInput.css">
<script src="../JS/jquery1.11.3.js"></script>
<script src="../JS/country.js" type="text/javascript"></script>
<!--  added  for date picker -->
<script src="../JS/jqueryuidate.js"></script>
<script src="../JS/jquerydate.js"></script>
<!-- <link rel="stylesheet" href="../CSS/datepickerui.css"> -->
<!--  needs to be remove -->
<script src="../JS/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">  
<link href="../CSS/notification-new.css" rel="stylesheet"></link>
<script src="../JS/MessageFadeOut.js"></script>  
</head>
<script>

function getCourseImagePath() {
     
 var img=document.getElementById('courseicon');
 var brows=document.getElementById("courseicon").value;
var course_name =document.setupform.coursename.value;
var orgid =document.setupform.orgid.value;

/* if(course_name.length==0 && orgid.length==0)
{
	alert("Please choose Customer Name and fill Course name");
	return false;
} */
	var ext=brows.substring(brows.lastIndexOf('.')+1/*, fileName.length()*/);
	if(ext=="png")// || ext=="jpg" || ext=="jpeg")
	{
		var data = new FormData();
		jQuery.each(jQuery('#courseicon')[0].files, function(i, file) {
		    data.append('file-'+i, file);
		});
		data.append('course',course_name);
		data.append('orgid',orgid); 
		jQuery.ajax({
		    url: '${pageContext.request.contextPath}/uploadImage',
		    data: data,
		    cache: false,
		    contentType: false,
		    processData: false,
		    type: 'POST',
		    success: function(data){
		        console.log(data);
		    }
		});
	}
	//	return true;
	else
		{
		// alert("Please select PNG format only");
		document.getElementById("courseicon").value="";
		return false;
		}
	
	
}
function getStreamImagePath() {
 var brows=document.getElementById("streamicon").value;
 var stream_name =document.setupform.streamname.value;
 var orgid =document.setupform.orgid.value;
 /* if(stream_name.length==0 && orgid.length==0)
 {
 	alert("Please choose Customer Name and fill Subject name");
 	return false;
 }
 */ 
	var ext=brows.substring(brows.lastIndexOf('.')+1/*, fileName.length()*/);
	if(ext=="png")// || ext=="jpg" || ext=="jpeg")
	{
	var data = new FormData();
	jQuery.each(jQuery('#streamicon')[0].files, function(i, file) {
	    data.append('file-'+i, file);
	});
	data.append('stream',stream_name);
	data.append('orgid',orgid);
	jQuery.ajax({
	    url: '${pageContext.request.contextPath}/uploadImage',
	    data: data,
	    cache: false,
	    contentType: false,
	    processData: false,
	    type: 'POST',
	    success: function(data){
	        console.log(data);
	    }
	});
	}
	else
		{
		// alert("Please select PNG format only");
		document.getElementById("streamicon").value="";
		return false;
		}
}


$(document).ready(function() {
//alert("in1");	

var _URL = window.URL || window.webkitURL;
$("#courseicon").change(function(e) {
	
    var file, img, width, height;


    if ((file = $('#courseicon')[0].files[0])) {
        img = new Image();
        img.onload = function() {
		width=this.width;
		height=this.height;
		if(width>108 && height>104){
			alert("please select Course file dimension as 108*104");
		 document.getElementById("courseicon").value="";}
			
        };
        img.onerror = function() {
            alert( "Please choose png only urs is: " + file.type);
        };
        img.src = _URL.createObjectURL(file);
    }
});
$("#streamicon").change(function(e) {
	
    var file, img, width, height;

    if ((file = $('#streamicon')[0].files[0])) {
        img = new Image();
        img.onload = function() {
		width=this.width;
		height=this.height;
		if(width>108 && height>104){
			alert("please select Stream file dimension as 108*104");
		 document.getElementById("streamicon").value="";}
			
        };
        img.onerror = function() {
            alert( "Please choose png only urs is: " + file.type);
        };
        img.src = _URL.createObjectURL(file);
    }
});

	 $("#btnSubmit").click(function(e) {
	    	var isValid = true;
	    	// alert("isValid="+isValid);
	    	//alert("in btn submit");
	    	  $('.sel select.opt').each(function() {
	    		  //alert("isValid select="+isValid);
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
		         //   alert("isValid after select="+isValid);
		        });
	        
	    	  /* $('.search_result input[type="text"]').each(function() { */
		        $('input[type="text"].box').each(function() {
		        	// alert("isValid test="+isValid);		        	
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
	
		       /*  $('#files').css({
     		       "border": "1px solid red",
     		  
     		       //"background": "#FFCECE"
     		   }); */
     		  $('input[type="file"].box').each(function() {      
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
     	    
     		   if($('#courseicon').val().length>0)
     			{
     			  	var fsize = $('#courseicon')[0].files[0].size;
     			  	if(fsize>4000) //do something if file size more than 1 kb (1000 bytes)
     	  	        {
     	  	            alert("Course file side should not be exceed 4 KB urs is "+fsize +" bites!");
     	  	            document.getElementById("courseicon").value="";
     	  	        }
     			 }
	        
     		   if($('#streamicon').val().length>0)
     			{
     			  var stmsize = $('#streamicon')[0].files[0].size;
       	         if(stmsize>4000) //do something if file size more than 1 kb (1000 bytes)
       	        {
       	            alert("Stream file side should not be exceed 4 KB urs is "+stmsize +" bites!");
       	            document.getElementById("streamicon").value="";
       	        }	   
     			}
  	         
  	    
    if (isValid == false){
	            e.preventDefault();
	        }
	        else {
	        	
	        	var orgid=document.setupform.orgid.value;
	        	var disid=document.setupform.disid.value;
	        	var progid=document.setupform.progid.value;
	        	var courseid=document.setupform.courseid.value;
	        	var stream=document.setupform.stream.value;
	        	var strmid=document.setupform.strmid.value;
	        	var tpattern=document.setupform.tpattern.value;
	        	var datepicker=document.setupform.datepicker.value;
	        	
	        	var durationType=document.setupform.durationType.value;
	        	var parAttempt=document.setupform.parAttempt.value;
	           	var duration=document.setupform.duration.value;
	        	var nsession=document.setupform.nsession.value;
	        	
	        	var coursename=document.setupform.coursename.value;
	        	var courseicon=document.setupform.courseicon.value;
	        	
	        	var streamname=document.setupform.streamname.value;
	        	var streamicon=document.setupform.streamicon.value;
	        	
	        	var crsiconname="";
	        	if(courseicon.length>0)
	        	{
	        		//alert(courseicon);
	        		if (courseicon.substring(3,11) == 'fakepath') {
	        			crsiconname = courseicon.substring(12);
	                }
					
				}
	        	
	        	var stremiconname="";
				if(streamicon.length>0)
				{
					//alert(streamicon);
					if (streamicon.substring(3,11) == 'fakepath') {
						stremiconname = streamicon.substring(12);
	                }
					
				}
				
				//alert("crsiconname="+crsiconname+"	stremiconname="+stremiconname);
				
	        	var files=document.setupform.files.value;
	        	var fileExtn = files.split(".");
	        	if(fileExtn[1]=="xls"/*  || fileExtn[1]=="xlsx" */){
	        	//alert(fileExtn[1]);
	        		 $('#files').css({
	        		       "border": "",
	        		   });
	        		document.setupform.action="/KODE_DEV/ControllerServlet/Add_ExcelSubjectTopic?orgid="+orgid+"&disid="+disid+"&progid="+progid+"&courseid="+courseid
	        				+"&stream="+stream+"&strmid="+strmid+"&tpattern="+tpattern+"&duration="+duration+"&nsession="+nsession
	        				+"&coursename="+coursename+"&courseicon="+crsiconname+"&streamname="+streamname+"&streamicon="+stremiconname
	        				+"&durationType="+durationType+"&parAttempt="+parAttempt+"&datepicker="+datepicker;
	        		
	        		
	        		document.setupform.submit();
	        	}
	        	else
	        		{//alert("File extension should be .xls");
	        		 $('#files').css({
	        		       "border": "1px solid red",
	        		  
	        		       //"background": "#FFCECE"
	        		   });
	        		// return false;
	        		}
	        	}
	        
	        	 //alert('Thank you for submitting')
	        	 // $("#setupform").submit();  
	        
	           
	    });
});
function SubmitIfValid()
{
	var orgid=document.setupform.orgid.value;

	var courseicon=document.setupform.courseicon.value;
	var coursename=document.setupform.coursename.value;
	
	var streamicon=document.setupform.streamicon.value;
	var streamname=document.setupform.streamname.value;
	
	if(orgid.length>0 && coursename.length>0 && courseicon.length>0)
		getCourseImagePath();
	
	if(orgid.length>0 && streamname.length>0 && streamicon.length>0)
		getStreamImagePath();
	
//alert("in...");	
   if(!$("#setupform").valid())
	   return false;  
   return true;
}


//JQuery code for allowed organization name has a characters
$(function () {
	 $('#orgname').keydown(function (e) {
	 if (e.altKey) {
	 e.preventDefault();
	 } else {
	 var key = e.keyCode;
	 if (!((key == 8) || (key == 9) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
	 e.preventDefault();
	 }
	 }
	 });
	 });
//JQuery code for allowed super admin name has a characters
/* $(function () {
	 $('#sName').keydown(function (e) {
	 if (e.altKey) {
	 e.preventDefault();
	 } else {
	 var key = e.keyCode;
	 if (!((key == 8) || (key == 9) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
	 e.preventDefault();
	 }
	 }
	 });
	 }); */
	 
//JQuery code for allowed city name has a characters
$(function () {
	 $('#city').keydown(function (e) {
	 if (e.altKey) {
	 e.preventDefault();
	 } else {
	 var key = e.keyCode;
	 if (!((key == 8) || (key == 9) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
	 e.preventDefault();
	 }
	 }
	 });
	 });
//JQuery code for allowed organization type has a characters
$(function () {
	 $('#orgtype').keydown(function (e) {
	 if (e.altKey) {
	 e.preventDefault();
	 } else {
	 var key = e.keyCode;
	 if (!((key == 8) || (key == 9) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
	 e.preventDefault();
	 }
	 }
	 });
	 });


function changeName() {
	var nm = document.getElementById('orgtype').value + " Name";
	document.getElementById('lblName').innerHTML = nm;
	    }

</script>



<style type="text/css">
#blah{
position: absolute;
margin-left:15px;
width: 125px;
height: 150px;
margin-top: -40px;
}
# #error{margin-top: -11px}
	#error{color: red !important;}	
.su{
	color: #008000;
    font-size: 17px;
    font-weight: bold;
    margin-top: -40px;
/*     top: 7%;
    left:6%;
    position: absolute;
 */
 }
 .genders span{
	width:12px;
	color:#000!important;
	}
.genders span input{

margin-top: -2px;
vertical-align: middle;
width: 20px;
}

.su1{
		    color: #008000;
    font-size: 15px;
    font-weight: bold;
    position: absolute;
    /*top: 10%;
    position: absolute;
     background: #fff;
    opacity: 0.7; 
    right:0px;
    padding: 0px 36px;*/
}
		
		
</style>
<script>
$(document).ready(function() {
	  $("#datepicker").datepicker({
		  
		   changeMonth : true,
		   changeYear : true,
		  // maxDate : '1d',
		   minDate : 0,
		   dateFormat: "yy-mm-dd",
		  });
	  });
$(function() {
    $( "#datepicker" ).datepicker();
  });

  </script>
<script type="text/javascript">
	 
$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
});
		</script>
		<script type="text/javascript">
		
<%-- function test()
{
	<% String onloadOrgid=(String)session.getAttribute("orgid");
	////System.out.println(" Organizationid in an onload:"+onloadOrgid);
	if(onloadOrgid!=null && onloadOrgid.length()>0) {
	 %> 
	 var selectBox = document.getElementById("orgid");
	 for(var i=0;i<selectBox.options.length;i++){
			 if(selectBox.options[i].value == '<%=onloadOrgid%>')
			{
	 selectBox.options[i].selected=true;
				 break;
			 }
		 }
		<%}%>
} --%>

		</script>
<% 
DCItoKSMappingDao mappingDao=new DCItoKSMappingDao();
HashMap<String,ArrayList<HashMap<String,String>>> lookups=mappingDao.fetchLookups();

ArrayList<HashMap<String,String>> DisciplineList=lookups.get("DisciplineList");
ArrayList<HashMap<String,String>> ProgramList=lookups.get("ProgramList");
ArrayList<HashMap<String,String>> CourseList=lookups.get("CourseList");
ArrayList<HashMap<String,String>> StreamList=lookups.get("StreamList");

%>
		
<body>
		
<div class="container" style="height:auto;position:static;">
<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/owner_menu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<% 
			System.out.println("return value="+request.getAttribute("OwnerSuccessMAil"));
				if (request.getAttribute("OwnerSuccessMAil")!= null) { 
					
				
					String msg2=(String)request.getAttribute("OwnerSuccessMAil");
					/*  String str[]=msg2.split("#");
					 String valid=str[0];
					 String valid1=str[1];
					 */%>
				<%-- 	<p class="su autohide"><%= valid %></p> --%>
					<p class="success"><%=msg2 %></p>					
					<%
				}
				else if (request.getAttribute("OwnerSuccess")!= null) { 
					
					%>
					<p class="success"><%= request.getAttribute("OwnerSuccess") %></p>					
					<%
				} 
				else  if (request.getAttribute("FailureMessage")!= null) { 
					
					%>
					<p class="failure"><%= request.getAttribute("OwnerFailure") %></p>					
					<%
				} 
				
			%>
		<div class="faculty_mod" style="width:300px;top:0px;height:430px;margin-bottom:10px;overflow-x:hidden;overflow-y:scroll">
		<p class="strong" >Course Setup</p>
       	<form name='setupform' id='setupform' method="post" enctype="multipart/form-data">
         <div class="info">
			<table align="center" width="100%">
			<%
			String dis_id="",prog_id="",crs_id="",strm_id="";
			%>
			<tr>
			<td class="sel">
			<select name="orgid" id="orgid" class="opt" required placeholder="Choose Customer">
								<%
									OwnerSelectOrgIDKStoreDao dobj1 = new OwnerSelectOrgIDKStoreDao();
									ArrayList<String> alo = dobj1.fetchValueCourse();
									//HashSet set =(HashSet)al.fetchValue();
									Iterator<String> itro = alo.iterator();
									String orgname=null;
									while (itro.hasNext()) {
										String orgid1 = itro.next();
										 orgname= itro.next();
										String orgnameid =(orgname+" ("+orgid1+")");
								%>
								<option value="<%=orgid1%>"><%=orgnameid%></option>
								<%
									}
								%>
						</select>
						<select name="disid" id="disid" class="opt" required placeholder="Choose Discipline"  >
								<option value="">Choose Discipline*</option>
								<%
								if(DisciplineList!=null && DisciplineList.size()>0)
								{
									for(int i=0;i<DisciplineList.size();i++)
									{
										HashMap<String,String> ht=DisciplineList.get(i);
										
										String discipline_id=ht.get("discipline_id");
										String discipline_name=ht.get("discipline_name");
									%>
									<option value="<%=discipline_id %>"><%=discipline_name %></option>	
								<%	}
								}
								%>
						</select>
						<select name="progid" id="progid" class="opt" required placeholder="Choose Program"  >
								<option value="">Choose Program*</option>
								<%
								if(ProgramList!=null && ProgramList.size()>0)
								{
									for(int i=0;i<ProgramList.size();i++)
									{
										HashMap<String,String> ht=ProgramList.get(i);
										String program_name=ht.get("program_name");
									%>
									<option value="<%=program_name %>"><%=program_name %></option>	
								<%	}
								}
								%>
								
						</select>
						<select name="courseid" id="courseid" class="opt" required placeholder="Choose Course" onchange="validateCourse(this.value)"> <!-- onchange="validateCourse(this.value)" -->
								<option value="">Choose Course*</option>
								<%
								if(CourseList!=null && CourseList.size()>0)
								{
									for(int i=0;i<CourseList.size();i++)
									{
										HashMap<String,String> ht=CourseList.get(i);
										String course_name=ht.get("course_name");
									%>
									<option value="<%=course_name %>"><%=course_name %></option>	
								<%	}
								}
								%>
								<option value="other">Other</option>
						</select>
						<input  type="text" class="" placeholder="Course Name" name="coursename" id="coursename"	maxlenght=”25” />
						<input  type="file" class="" name="courseicon" id="courseicon"  accept="image/*" /> <!-- onchange="getCourseImagePath();" -->
						<tr>
					<td>
					 <input  type="text" class="box" placeholder="Stream ID *" name="stream" id="stream" maxlength="15" />
					</td>
				</tr>
				<tr><td class="sel">
						<select name="strmid" id="strmid" class="opt" required placeholder="Choose Stream" onchange="validateStream(this.value)"> <!-- onchange="validateStream(this.value)" -->
								<option value="">Choose Stream*</option>
								<%
								if(StreamList!=null && StreamList.size()>0)
								{
									for(int i=0;i<StreamList.size();i++)
									{
										HashMap<String,String> ht=StreamList.get(i);
										String department_name=ht.get("department_name");
									%>
									<option value="<%=department_name %>"><%=department_name %></option>	
								<%	}
								}
								%>
								<option value="other">Other</option>
								
								
						</select>
						<input  type="text" class="" placeholder="Stream Name" name="streamname" id="streamname"	maxlenght=”25” />
						<input  type="file" class="" name="streamicon" id="streamicon" accept="image/*"/>  <!-- onchange="getStreamImagePath();" -->
						</td></tr>
				<tr>
				<td class="sel">
				<select name="tpattern" id="tpattern" class="opt" required placeholder="Choose Teaching Pattern"  >
								<option value="">Teaching Pattern*</option>
								<option value="semester">Semester</option>
								<option value="trimester">Trimester</option>
								<option value="yearly">Yearly</option>
								<option value="nonsemester">Non Semester</option>
								</select>
				</td></tr>
				
					<tr><!-- <td>Name<span class="astr">*</span></td><td>:</td> -->
              <td><input type="text" placeholder="Start Date*" name="datepicker"  id="datepicker" class="box" readonly onkeyup="checkDate()"></td>
              </tr> 
				
				<tr>
				<td class="sel">
				<select name="parAttempt" id="parAttempt" class="opt" required placeholder="Choose parallel Attempt"  >
								<option value="">Parallel Attempt*</option>
								<option value="Yes">Yes</option>
								<option value="No">No</option>
								
								</select>
				</td></tr>
			
					<tr>
				<td class="sel">
				<select name="durationType" id="durationType" class="opt" required placeholder="Choose duaration type"  >
								<option value="">Duration Type*</option>
								<option value="Yearly">Yearly</option>
								<option value="Month">Month</option>
								<option value="Month">Week</option>
								<option value="Month">Day</option>
								</select>
				</td></tr>	
				
				
				
				<tr>
					<td><input type="text" class="box zeronotfirst number-only" placeholder="Duration*" maxlength="4" name="duration" id="duration" /></td>
				</tr>
				<!-- <tr><td>Name<span class="astr">*</span></td><td>:</td>
              <td><input type="text" placeholder="Subject" name="subject"  id="subject" class="box"></td>
              </tr> 
				<tr>
					<td><input type="text" name="topic"  placeholder="Topic" id="topic" class="box"></td>
				</tr>
				<tr>
					<td><input type="text" name="subtopic"  placeholder="Sub-Topic" id="subtopic" class="box"></td>
				</tr> -->
				<tr>
					<td><input type="text" name="nsession" maxlength="4"  placeholder="Number of Session*" id="nsession" class="box zeronotfirst number-only" /></td>
				</tr>
<tr><td>Export File            <a href="http://www.kompacdigit.com:8080/KODE_DEV/Curriculam.xls" class="bookmark_line"   >Sample File</a></td></tr>
<tr> <td>  <input type="file" id="files" name="files" class="box" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel" />
Supported Format:[.xls]
   </td></tr>
    <!-- <tr>
					<td><input type="button" value="Import" onclick="addexcelfile()"/>
					</td>
					</tr>  -->
 			
 <tr>
					<td><input class="btn1" id="btnSubmit" type="button" value="Create" onClick="return SubmitIfValid()"style="width:261px" > 
					<!--	<a class="back" style="color: #c2c2c2;" href="../JSP/Home.jsp">Back</a></td>
						  	<td><a href="../JSP/Search.jsp">Update/Delete</a></td>-->

				</tr>
				<tr></tr>
				<tr></tr>
			</table>
			</div>
		</form>
	</div>
		</div>
		<div>
		<%@include file="FooterViews.jsp" %>
		</div>
</div>
<script>
function sampleFile()
{
	alert('hello');
	
}
</script>
	<script>
	var currentdate = new Date(); 
	var datetime = + currentdate.getDate() + "/"
	                + (currentdate.getMonth()+1)  + "/" 
	                + currentdate.getFullYear() + "  "  
	                + currentdate.getHours() + ":"  
	                + currentdate.getMinutes() + ":" 
	                + currentdate.getSeconds(); 
	                document.getElementById("date").value=datetime;
	                
	</script>
<script src="../JS/intlTelInput.js"></script>
<script>
$("#phone").intlTelInput({
  utilsScript: "../JS/utils.js"
});


</script>
 
</body>

<script type="text/javascript">

function validateCourse(SelectedCourse)
{
	if(SelectedCourse=="other")
	{
		$("#coursename").show();
		$("#coursename").addClass("box");
		
		$("#courseicon").show();
		$("#courseicon").addClass("box");
	}else
	{
		$("#coursename").hide();
		$("#coursename").removeClass("box");
		
		$("#courseicon").hide();
		$("#courseicon").removeClass("box");
	}
	
}

function validateStream(SelectedStream)
{
	if(SelectedStream=="other")
	{
		$("#streamname").show();
		$("#streamname").addClass("box");
		
		$("#streamicon").show();
		$("#streamicon").addClass("box");
	}else
	{
		$("#streamname").hide();
		$("#streamname").removeClass("box");
		
		$("#streamicon").hide();
		$("#streamicon").removeClass("box");
	}
}
$(document).ready(function() {

	$("#coursename").hide();
	$("#courseicon").hide();
	
	$("#streamname").hide();
	$("#streamicon").hide();
	
});

</script>
		
<style type="text/css">
.autohide,.su  {
position:ablosute;
top:220px;
font-size:14px;
font-weight:bold;
right:400px;
}

</style>
<script type="text/javascript">
$(".zeronotfirst").keyup(function(){
    var value = $(this).val();
    value = value.replace(/^(0*)/,"");
    $(this).val(value);
});
$(function(){       $('.number-only').keyup(function(e) {  if(this.value!='-')    while(isNaN(this.value))   this.value = this.value.split('').reverse().join('').replace(/[\D]/i,'')           .split('').reverse().join('');}).on("cut copy paste",function(e){ e.preventDefault();});});
$(function(){  $('.txtOnly').keydown(function(e) {      if (e.altKey) {        e.preventDefault();      } else {        var key = e.keyCode;        if (!((key == 8) || (key == 9) || (key == 190) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {          e.preventDefault();        }      }    });});
</script>

<!-- Stream ID validation Script start -->
<script type="text/javascript">
$(function(){
    $("#stream").keypress(function(event){
        var ew = event.which;
        if(ew == 8 )
            return true;
        if(ew == 0 )
            return true;
        if(48 <= ew && ew <= 57)
            return true;
        if(65 <= ew && ew <= 90)
            return true;
        if(97 <= ew && ew <= 122)
            return true;
        return false;
    });
});
</script>
<script type="text/javascript"> 
	   $(document).ready(function(){
	        sortDropDownListByText();
	    });        
	
	function sortDropDownListByText() {
	// Loop for each select element on the page.
	$("#orgid").each(function() {
       // Keep track of the selected option.
	   var selectedValue = $(this).val();
	   // Sort all the options by text. I could easily sort these by val.
	   $(this).html($("option", $(this)).sort(function(a, b) {
    	  return a.text.toUpperCase() == b.text.toUpperCase() ? 0 : a.text.toUpperCase() < b.text.toUpperCase() ? -1 : 1
      }));
	   // Select one option.
       $(this).val(selectedValue);
	   $("#orgid option:selected").insertBefore('#ddlList option[0]');
	  // Adding Choose Customer* to top
	   $("#orgid").prepend("<option  selected=selected value>Choose Customer*</option>");
	});
	}
</script>
<!-- Stream ID validation Script end -->
</html>