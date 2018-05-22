<%@ page errorPage="../JSP/error.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.dao.FacilitatorSelectKsidKStoreDao"%>
<%@ page import="com.kds.KODE_DEV.dao.FacilitatorManageLibDao"%>

<%@ page import="com.kds.KODE_DEV.dao.GetCourseForFacultyDao"%>
<%@page import="com.kds.KODE_DEV.domain.CourseFacultyDomain" %>

<%@page import="java.util.List" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/FacilitatorKnowSetup.js"></script>
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"/>
<link href="../CSS/global.css" rel="stylesheet"/>
<link href="../CSS/design-common.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<link href="../CSS/notification-new.css" rel="stylesheet"></link>
<script src="../JS/jquery.validate.js"></script>
<script src="../JS/MessageFadeOut.js"></script>

<script>

$(document).ready(function() {
	
	 $('#courses').change(function () {
	  	 var selectedValue = this.selectedOptions[0].value;
	     var selectedText  = this.selectedOptions[0].text;
	   // alert(selectedText);
	     // alert("Result Test"+selectedValue);
	      //$("#courseid").val(selectedValue);
	      $('#courseid').attr('value', selectedValue);
	      
	      //$("#courseid").text(selectedValue);
	     var ctx = "${pageContext.request.contextPath}";
	     // alert(ctx);
	     $.get('${pageContext.request.contextPath}/getSubjectsForFaculty', {selectedValue : selectedValue},
	    		  function(jsonResponse) {
	    		//	alert(jsonResponse);
	    		//	 alert("inside");
	    			console.log("json response :",jsonResponse);
	    			
    	     		var select = $('#subjects');
    	     		select.find('option').remove();
    	     		jsonResponse = $.parseJSON(jsonResponse);
    	     		console.log("parsed json response :", jsonResponse);
    	     		$('<option>').val('').text('Select Subject*').appendTo(select);
    	     		$.each(jsonResponse, function(index, value) {
		    	 		  console.log('Index value here :',index);
		    	 		 console.log('Actual obj value here :',value.subject);
		    	 		//alert("Value of Subject "+value.subject );
		    	 		 $("#subjectid").val(value.subject);
		    	 		 $('<option>').val(value.subjectId).text(value.subject).appendTo(select);
		    	 	 
		    	      }); 
	    		});
	 });
	 
	 $('#libid').change(function () {
	  	 var selectedValue = this.selectedOptions[0].value;
	     var selectedText  = this.selectedOptions[0].text;
	   // alert(selectedText);
	     // alert("Result Test"+selectedValue);
	      //$("#courseid").val(selectedValue);
	      $('#txtlibid').attr('value', selectedValue);
	      
	      //$("#courseid").text(selectedValue);
	     var ctx = "${pageContext.request.contextPath}";
	     // alert(ctx);
	     $.get('${pageContext.request.contextPath}/getSubjectsForFaculty', {selectedValue : selectedValue},
	    		  function(jsonResponse) {
	    		//	alert(jsonResponse);
	    		//	 alert("inside");
	    			console.log("json response :",jsonResponse);
	    			
    	     		var select = $('#subjects');
    	     		select.find('option').remove();
    	     		jsonResponse = $.parseJSON(jsonResponse);
    	     		console.log("parsed json response :", jsonResponse);
    	     		$('<option>').val('').text('Select Subject*').appendTo(select);
    	     		$.each(jsonResponse, function(index, value) {
		    	 		  console.log('Index value here :',index);
		    	 		 console.log('Actual obj value here :',value.subject);
		    	 		//alert("Value of Subject "+value.subject );
		    	 		 $("#subjectid").val(value.subject);
		    	 		 $('<option>').val(value.subjectId).text(value.subject).appendTo(select);
		    	 	 
		    	      }); 
	    		});
	 });
	 
	 /// newly added here by dinesh
	 /* $('#libid').change(function (){
		var selectedValue = this.selectedOptions[0].value;
		alert("selectedValue "+selectedValue);
		$('#libid').val(selectedValue).value;
		$('#libid').attr('value', selectedValue);
	}); */
	 
	 $("#subjects").change(function () 
	{
		 var selectedValue = this.selectedOptions[0].value;
	     var selectedText  = this.selectedOptions[0].text;
	     var courseid = $("#courseid").val();
	     var subjectid = $("#subjectid").val();
	  //   alert(selectedText);
	 //    alert("courseid "+courseid);
	  //   alert("subjectid "+subjectid);
	 //     alert("new code selected value "+selectedValue);
	   //   alert("new code selectedText "+selectedText);
	      var ctx = "${pageContext.request.contextPath}";
	 //     alert(ctx);
	      $.get('${pageContext.request.contextPath}/getTopicForFaculty', {selectedValue : selectedValue,courseid : courseid,subjectid : subjectid},
	    		  function(jsonResponse) {
	    		///	alert("vinod "+jsonResponse);
	    		//	 alert("subjects inside");
	    			console.log("json response :",jsonResponse);
	    			var select = $('#departments');
	    			select.find('option').remove();
	    			jsonResponse = $.parseJSON(jsonResponse);
	    			//alert("jsonResponse"+jsonResponse);
	    			$('<option>').val('').text('Select Topic*').appendTo(select);
	    			$.each(jsonResponse, function(index, value) {
		    	 		  console.log('Index value here :',index);
		    	 		 console.log('Actual obj value here :',value.topic);
		    	 		$("#departmentid").val(value.topic);
		    	 		$("#dummy").val(selectedValue);
		    	 		
		    	 		$('<option>').val(value.topicId).text(value.topic).appendTo(select); 
		    	 	//	alert("DINESH "+val(value.topic));
		    	 		$('#selectvalue1').val(value.topicId);
	    			 }); 
	    }); 
	    		});
	 
   
	    		 $('#departments').change(function () {
	    		  	 var selectedValue = this.selectedOptions[0].value;
	    		     var selectedText  = this.selectedOptions[0].text;
	    		   //   alert(selectedText);
	    		      //alert("Result Test"+selectedValue);
	    		      var courseid = $("#courseid").val();
	    		      var subjectid = $("#subjectid").val();
	    		      var departmentid =   $("#departmentid").val();
	    		      //$("#departmentid").val(selectedValue);
	    		     // var departmentid = $("#departmentid").val();  
	    			//     alert(selectedText);
	    			 //    alert("courseid "+courseid);
	    			   //  alert("Vindo course id "+courseid);
	    			  //   alert("subjectid "+subjectid);
	    			  //   alert("departmentid "+departmentid);
	    			     
	    			     var subject =   $("#dummy").val()
	    		     var ctx = "${pageContext.request.contextPath}";
	    		     // alert(ctx);
	    		     $.get('${pageContext.request.contextPath}/getSubTopicForFaculty', {selectedValue : selectedValue,courseid : courseid,subjectid : subjectid,departmentid : departmentid ,subject : subject},
	    		    		  function(jsonResponse) {
	    		    		//	alert(jsonResponse);
	    		    			//alert("vinod departments"+jsonResponse);
	    		    			console.log("json response :",jsonResponse);
	    		    			
	    	    	     		var select = $('#sub-subjects');
	    	    	     		select.find('option').remove();
	    	    	     		jsonResponse = $.parseJSON(jsonResponse);
	    	    	     	//	alert("jsonResponse -Topic "+jsonResponse)
	    	    	     		console.log("parsed json response :", jsonResponse);
	    	    	     		$('<option>').val('').text('Select Sub-Topic*').appendTo(select);
	    	    	     		
	    	    	     		$.each(jsonResponse, function(index, value) {
	    			    	 		  console.log('Index value here :',index);
	    			    	 		 console.log('Actual obj value here :',value.subject);
	    			    	 		//alert((value.subject));
	    			    	 	
	    			    	 		 $("#sub-subjects").val(value.subject);
	    			    	 		//$("#dummy1").val(selectedValue);
	    			    	 		$('<option>').val(value.sutopicId).text(value.subtopic).appendTo(select);
	    			    	 		
	    			    	 		 
	    			    	 		$('#selectvalue1').val(selectedValue).value;
	    			    	 	
	    			    	 		  $('#selectvalue1').attr('value', selectedValue);
	    			    	 		$('#selectvalue2').val(value.sutopicId).value;
	    			    	 		  $('#selectvalue2').attr('value', value.sutopicId);
	    			    	      }); 
	    		    		});
	    		 });
		
	    		 $('#sub-subjects').change(function () {
	    			 var selectedValue = this.selectedOptions[0].value;
	    			 
	    		     var selectedText  = this.selectedOptions[0].text; 
	    		  
	    		        $('#selectvalue2').val(selectedValue);
	    		        $('#selectvalue2').attr('value', selectedValue);
	    		 });
	    			

	 
	 $("#uplode1").click(function(e) {
	    	var isValid = true;
	    	  $('.sel select.opt').each(function() {
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
	        
	    	  /* $('.search_result input[type="text"]').each(function() { */
		        $('input[type="text"].box').each(function() {
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
		        
	    	  
		        $('textarea').each(function() {
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
	        
	       /*  if($('input:checkbox').is(":checked"))
	        {
	          $('.search_result input[type="text"]').each(function() {
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
	        } */
	        
	       
	        // here end working ok 
     if (isValid == false){
	        	
	            e.preventDefault();
	           
	        }
	        else {
	        	 //alert('Thank you for submitting')
	        	 /* $("#ksStore").submit();  */
	        }
	           
	    });
	
	
	
/* $("#SubmitForm").validate({

    debug: false,
    rules: {
    	departments:"required",
    	subjects:"required",
    	description:"required",
    	brows:"required",
	},
	messages: {
		departments: "Please enter departments name",
		subjects:"Please enter subject name",
		description:"Please give some description",
		brows:"Please choose a file",
	}
	}); */
	

});
function checkPDF()  { 
    var brows=document.getElementById("brows").value; 
    // alert(brows);  
    var ext=brows.substring(brows.lastIndexOf('.')+1/*, fileName.length()*/); 
    //alert(ext);
      if(ext=="pdf" ||ext=="mp4") 
    	return true;  
    else   {
    	alert("Please select PDF & mp4 file only"); 

 document.getElementById("brows").value=""; 
 return false; 
 }
    }

<%
String fullPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
System.out.println("fullPath in jsp"+fullPath);
%>
function SubmitIfValid() {
	
	if(!$("#SubmitForm").valid())
		
		return false;  
	
    return true;

}


</script>


<style>
.error{color:#f00; float:left; width:250px;}
input.box.error {
    border: 1px solid red !important;
} .owner_setup_faculty{width: 22%; margin-top: 70px;}
.pp{    z-index: 1;
    position: absolute;
    right: 33px;
    margin-top: -14px;}
    .aa{margin-bottom: 25px;}
</style>

</head>
<%
	//HttpSession mess = request.getSession();
	 
	String message = (String) session.getAttribute("MsgValue");
	String orgid = (String) session.getAttribute("orgid");
	String facultyid = (String) session.getAttribute("userid");
	String username = (String) session.getAttribute("username");
	if(username==null)
		response.sendRedirect("../JSP/error.jsp?errmsg=Session Expired");
	//System.out.println("organization id:" + orgid + "created id:" + facultyid);
	FacilitatorSelectKsidKStoreDao dobj2 = new FacilitatorSelectKsidKStoreDao();
	String ksidHidden = dobj2.fetchValue1(orgid, facultyid);
	System.out.println("ksidHidden"+ksidHidden);
%>
<body>
<%
					System.out.println("succsss="+request.getAttribute("FacultySuccess"));
						if (request.getAttribute("FacultySuccess") != null) {
					%>
					<p class="success">
						<%=request.getAttribute("FacultySuccess")%>
					</p>
					<%
						} else if(request.getAttribute("FacultyFailure") != null) {
					%>
					<p class="failure">
						<%=request.getAttribute("FacultyFailure")%>
					</p>
					<%
						}
					%>
	<div class="container" style="position:relative;">
		<%@include file="../JSP/headers.jsp"%>
		
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="faculty_mod" style="width: 300px;height:390px;margin-top:70px;overflow-y: scroll;top:0px;margin-bottom:0px;position:relative;float:right;">

			
					
				<!-- 		   -->
					
					<p class="strong" style="padding-left:0px;padding-bottom:0px;padding-top:0px!important;">Create & Upload</p>
			<form name="KnowStore" id="SubmitForm"  action="/KODE_DEV/ControllerServlet/FacilitatorKAssetsKnowSetupServiceFTP"  method="post" enctype="multipart/form-data">
				<table align="center">
					<%-- <%!FacilitatorKnowSetUpDomain fDomain = new FacilitatorKnowSetUpDomain();%> --%>
					<tr>
					<tr>
						<!-- <td>Department</td>
						<td>:</td> -->
						<tr>
						<td><input type="hidden" name="ksid" id="ksid" value="<%=ksidHidden%>"></td></tr>
						
						<tr>
						<tr>
						<td><input type="hidden" name="coursesid" id="courseid"  ></td></tr>
						<tr>
						<td><input type="hidden" name="subjectid" id="subjectid"></td></tr>
						<tr>
						<td><input type="hidden" name="departmentid" id="departmentid" ></td></tr>
							<tr>
						<td><input type="hidden" name="dummy" id="dummy"></td></tr>
							<tr>
						<td><input type="hidden" name="dummy1" id="dummy1"></td></tr>
						<tr>
						<tr>
						<td><input type="hidden" name="selectvalues1" id="selectvalue1" ></td></tr>
						<tr>
						<tr>
						<td><input type="hidden" name="selectvalues2" id="selectvalue2" ></td></tr>
						<tr>
						<tr>
						<td><input type="hidden" name="txtlibid" id="txtlibid"></td></tr>
						<tr>
						<!-- <td>Know Store ID</td>
						<td>:</td> -->
					<td class="sel">
					<select name="libid" id="libid" class="opt box" >
								<option value="">Choose Library*</option>
								<%
									//FacilitatorManageKnowStoreDomain al = new FacilitatorManageKnowStoreDomain();
									FacilitatorManageLibDao dobj3 = new FacilitatorManageLibDao();
								    ArrayList<String>	arrayList = dobj3.fetchValue(facultyid,orgid);
								    Iterator<String> iterator = arrayList.iterator();
									while(iterator.hasNext()){
										
										String libId = iterator.next();
										String libName = iterator.next();
										String libIdAndName  = (libId+" ("+libName+")");%>
									
								<option value="<%=libId%>"><%=libIdAndName%></option>
								<%}%>
					</select>	
					
					</td>
					<tr>
						<td class="sel"><select name="courses" id="courses" class="opt box" >
								<option value="">Select Course*</option>
								<%
								    String result="";
									GetCourseForFacultyDao getCourseForFacultyDao = new GetCourseForFacultyDao();
										//List<CourseFacultyDomain> courses = getCourseForFacultyDao.getCourseForFaculty(facultyid,orgid);
										List<CourseFacultyDomain> courses = getCourseForFacultyDao.getCourseForFaculty(facultyid);
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
					    	<td class="sel"><select name="subjects" id="subjects" class="opt box" >
								<option value="">Select Subject*</option>
					
					</tr>
					
					</tr>
						
						<td class="sel"><select name="departments" id="departments" class="opt box" >
						<option value="">Select Topic*</option>
						
						<!--  <input type="text" name="departments" class="box"  id="departments" placeholder="Enter topic*">  -->
						</select>	
						</td>
					</tr>

					<tr>
						
						
						<td class="sel"><select name="sub-subjects" id="sub-subjects" class="opt box" >
						<option value="">Select Sub-Topic*</option>
						<!-- <td><input type="text" name="sub-subjects" id="sub-subjects" class="box" placeholder="Enter sub-topic*"></td> -->
						</select>	
						</td>
					</tr>
					<tr>
						<!-- <td>Description</td>
						<td>:</td> -->
						<td><textarea name="description" id="description" class="box" style="width:255px;border-radius:4px;font-family:regular;font-size:14px; border:1px solid #c2c2c2"rows="6" cols="8"
								placeholder="Enter description here*"></textarea></td>
					</tr>
					<tr>
						<!-- <td>File</td>
						<td>:</td> -->
						<td><input name="brows" id="brows" class="box" style="width: 248px; color:#000;" type="file" name="upload" onchange="checkPDF()" accept="application/pdf"/></td>
					</tr>
					<tr>
							<td style="padding-top: 10px;">
							<input class="add_btn1" id="uplode1" style="width: 262px;" type="submit" value="Upload" onclick="return SubmitIfValid()"><!-- <a class="back"
							style="color: #c2c2c2; text-align: left;" href="../JSP/Home.jsp">Back</a> -->
						</td>
					</tr>
				</table>
				<!-- <p class="cre_p"></p> -->
			</form>
			<%-- <%@ include file="../JSP/all_one_footer.jsp" %> --%>
		</div>
		<%@ include file="../JSP/FooterViews.jsp"%>
	</div>
</body>
<style>
.autohide {
/* position:absolute; */
top:225px;
right:350px;
}
</style>
</html>