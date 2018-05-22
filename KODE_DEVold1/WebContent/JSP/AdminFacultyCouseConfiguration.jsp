<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.dao.*"%>
<%@ page import="com.kds.KODE_DEV.domain.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/OwnerKnowSetup.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.3/css/bootstrap.min.css"
    rel="stylesheet" type="text/css" />
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>

<link href="../CSS/kedu.css" rel="stylesheet"/>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<link href="../CSS/custom.css" rel="stylesheet"></link>
<link href="../CSS/design-common.css" rel="stylesheet"></link>
<link href="../CSS/notification-new.css" rel="stylesheet"></link>
<link href="https://cdn.rawgit.com/davidstutz/bootstrap-multiselect/master/dist/css/bootstrap-multiselect.css"
	    rel="stylesheet" type="text/css" />
	    <script src="https://cdn.rawgit.com/davidstutz/bootstrap-multiselect/master/dist/js/bootstrap-multiselect.js"
    type="text/javascript"></script>
     <script src="../JS/MessageFadeOut.js"></script>
	

<!-- <script type="text/javascript" src="../JS/MessageFadeOut.js"></script> -->
<!-- <script type="text/javascript">
$(function() {
    $('#topic').multiselect({
        includeSelectAllOption: true
    });
});
</script> -->
<script>
$(document).ready(function() {
	
	/* $(function () {
        $('#topic').multiselect({
            includeSelectAllOption: true
        });
        
    }); */
	
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
		        $('button').each(function() {
		              if ($.trim($("#topic option:selected").val()) == '') {
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
		    
		    $('button').each(function() {
		              if ($.trim($("#subtopic option:selected").val()) == '') {
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
	        	  $("#SubmitForm").submit();  
	        }
	           
	    });
});
function SubmitIfValid()
{
	 var selectedTopic = $("#topic option:selected");
	 var selectedSubTopic = $("#subtopic option:selected");
     var messageTopic = "";
     var messageSubTopic = "";
     selectedTopic.each(function () {
    	 messageTopic += $(this).val()+",";
        // message += $(this).text() + "\n";
     });
     var selectTopic = document.getElementById("topicdata");
     console.log("in :"+selectTopic);
     selectTopic.value = messageTopic;
     
     selectedSubTopic.each(function () {
    	 messageSubTopic += $(this).val()+",";
        // message += $(this).text() + "\n";
     });
     var selectSubTopic = document.getElementById("subtopicdata");
     console.log("in :"+selectSubTopic);
     selectSubTopic.value = messageSubTopic; 
	
	
	
	//alert("in");
    if(!$("#SubmitForm").valid()) return false;  
    return true;
}




	 </script>
<script type="text/javascript">
	 function validation() {
		// alert("in");
		//if (valid5()) {
			//alert("in1");
			//var orgid=document.getElementById("orgid").value;
			var tgt="/KODE_DEV/ControllerServlet/OwnerSetUpKnowStoreServlet";//?orgid="+orgid;
			document.KnowStore.action = tgt;
			//alert(document.getElementById("orgid").value);
			document.KnowStore.submit();
			
		
	}
	 
	function getUser(){
		var orgid=document.getElementById("orgid").value;
//alert("orgid="+orgid);
		if(orgid == null || orgid == ""){
			alert("Select Organization ID");
			return false;
		}else {
			document.KnowStore.action="/KODE_DEV/ControllerServlet/OwnerSetUpKnowStoreServlet?orgid="+orgid;
			document.KnowStore.submit();
		}
	}
	
	 function onload(){
			<% String onloadOrgid=(String)session.getAttribute("orgid");
			////System.out.println(" Organizationid in an onload:"+onloadOrgid);
			if(onloadOrgid!=null && onloadOrgid.length()>0) {
				////System.out.println("organization id in onload:"+onloadOrgid);
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
		}
	 
	 var request;
	 function getID()
	 {
	 var v=document.KnowStore.userid.value;
	 var url="CommonUserIDKnowStore.jsp?val="+v;

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
	 
</script>

</head>

<%
	//HttpSession mess = request.getSession();
	String msg = "";
	String username = (String)session.getAttribute("username");
	String userid = (String)session.getAttribute("userid");
	////System.out.println("userid is:" + userid);
	String orgid = (String)session.getAttribute("orgId");
	String[] resultUserid=(String[])session.getAttribute("resultUserId");
%>
<body>
	<div class="container" style="position:static;">
<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/Admin_menu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<%
					System.out.println("Success %%%%%%%%%"+request.getAttribute("AdminFacultySuccess"));
					System.out.println("Failure %%%%%%%%%"+request.getAttribute("AdminFacultyFail"));
					
							if (request.getAttribute("AdminFacultySuccess") != null) {
						%>
						<p class="success"
							style=" background:#none;">
							<%=request.getAttribute("AdminFacultySuccess")%>
						</p>
						<%
							} else if (request.getAttribute("AdminFacultyFail") != null) {
						%>
						<p class="failure"
							style=" background:#none;">
							<%=request.getAttribute("AdminFacultyFail")%>
						</p>
						<%
							} else if (request.getAttribute("mapFailureConstraint") != null) {
						%>
						<p class="failure"
							style=" background:#none;">
							<%=request.getAttribute("mapFailureConstraint")%>
						</p>
						<%
								}
						%>
		<div class="faculty_mod" style="width:314px;height:auto;margin-bottom:70px;">
		<p class="strong">Subject Mapping</p>
			<form name="KnowStore" id="SubmitForm" method="post" action="/KODE_DEV/ControllerServlet/AdminFacultyCourseMappingServlet">
			<input name="topicdata" id="topicdata" type="hidden" value=""/>
			<input name="subtopicdata" id="subtopicdata" type="hidden" value=""/>
         <div class="info">
				<table align="center">
					
					
					
					<tr>
						<td class="sel"><select name="faculty" id="faculty"  class="opt" required placeholder="Select Admin">
						<option value="">Select Faculty*</option>
						
						        <%
										GetAdminsForOrgDao getAdminsForOrgDao = new GetAdminsForOrgDao();
						        System.out.println("&&&*********&&&& "+orgid);
										List<UsersInfoDomain> adminUsers = getAdminsForOrgDao.getFacultyForAdmin(orgid, userid);
										//HashSet set =(HashSet)al.fetchValue();
										System.out.println("List object :"+adminUsers);
										for(UsersInfoDomain usersInfoDomain : adminUsers) {
											System.out.println("users :"+usersInfoDomain.getUserId());
											String user = usersInfoDomain.getUserId();
											String user_name=usersInfoDomain.getUserName();

										/* while (itr.hasNext())  */
									%>
									<option value="<%=user%>"><%=user_name%> (<%=user%>)</option>
									<%
										}
									%>
								
						</select>
						</td>
					</tr>
					<tr style="display:none">
						<td><select name="userid" id="userid" onchange="getID()" >
							    <option value="default">select</option>
							    <%				 
 					 
			  		 if(resultUserid!=null)
			  	 		{
							for(String uid:resultUserid){%>
						
				       <option value="<%=uid%>"><%=uid%></option>
						<% }}%>
						</select><span id="amit"></span></td>
					</tr>
					<tr>
						<td class="sel"><select name="course" id="course"  class="opt" required placeholder="Select Course" onchange="loadSubjects()">
						 <option value="">Select Course*</option>
									<%
										GetCourseForAdminDao getCourseForAdminDao = new GetCourseForAdminDao();
										List<CourseAdminDomain> courses = getCourseForAdminDao.getCourseForAdminFaculty(orgid, userid);
										//HashSet set =(HashSet)al.fetchValue();
										for(CourseAdminDomain course : courses) {
											System.out.println("course :"+course.getCourseId());
											String result = course.getCourseId();
										
									%>
									<option value="<%=result%>"><%=result%></option>
									<%
										}
									%>
							</select>
						</td>
					</tr>
					
					<tr>
						<td class="sel"><select name="subject" id="subject"  class="opt" required placeholder="Select Subject*" onchange="loadTopics()">
						 <option value="">Select Subject*</option>
									 
							</select>
						</td>
					</tr>
					
					<tr>
						<td class="sel"><select name="topic" id="topic"  class="opt" required placeholder="Select Course" multiple="multiple" onchange="loadSubTopics()">
						 
							</select>
						</td>
					</tr>
					
					<tr>
						<td class="sel"><select name="subtopic" id="subtopic"  class="opt" required placeholder="Select Course" multiple="multiple">
						 
							</select>
						</td>
					</tr>

					<tr style="display:none">
						<td><select name="status" id="status">
								<option value="Active" selected="selected">Active</option>

								<option value="InActive">InActive</option>
						</select></td>
					</tr>
					<tr>
						<td style="padding-top: 10px;">
						<input class="btn1" id="uplode1" value="Create" type="button" style="width: 264px;" onclick="return SubmitIfValid()"> 
							<!-- <a class="back" style="color: #c2c2c2; text-align: left;" href="../JSP/Home.jsp">Back</a></td>  -->
					</tr>
					
				</table>
				</div>
				
			</form>
			</div>
		 </div>
		<div>
		<%@include file="FooterViews.jsp" %>
		</div>
		<script>
		function loadSubjects() {
			
			var selected = document.getElementById("course");
			var selectedValue = selected.options[selected.selectedIndex].value;
		    //alert(selectedValue);
		     
		     $.get('${pageContext.request.contextPath}/getSubjects', {selectedValue : selectedValue},
		    		  function(jsonResponse) {
		    			//alert(jsonResponse);
		    			
		    			console.log("json response :",jsonResponse);
		    	     		var select = $('#subject');
		    	     		//var subselect= $('#subdeptid');
		    				select.find('option').remove();
		    					//subselect.find('option').remove();
		    					jsonResponse = $.parseJSON(jsonResponse);
		    					console.log("parsed json response :", jsonResponse);
		    					$('<option>').val('').text('Select Subject*').appendTo(select);
		    	 	  $.each(jsonResponse, function(index, value) {
		    	 		  console.log('Index value here :',index);
		    	 		 console.log('Actual obj value here :',value.subject);
		    	 			 $('<option>').val(value.subjectId).text(value.subject).appendTo(select);
		    	      }); 	  
		    		});
		    
			}
			
			function loadTopics() {
				var selected = document.getElementById("subject");
				var selectedValue = selected.options[selected.selectedIndex].value;
			   
			    
			     $.get('${pageContext.request.contextPath}/getTopics', {selectedValue : selectedValue},
			    		  function(jsonResponse) {
			    			//alert(jsonResponse);
			    			
			    			console.log("json response :",jsonResponse);
			    	     		var select = $('#topic');
			    	     		//var subselect= $('#subdeptid');
			    				select.find('option').remove();
			    					//subselect.find('option').remove();
			    					jsonResponse = $.parseJSON(jsonResponse);
			    					console.log("parsed json response :", jsonResponse);
			    					//$('<option>').val('').text('Select Topic*').appendTo(select);
			    	 	  $.each(jsonResponse, function(index, value) {
			    	 		  console.log('Index value here :',index);
			    	 		 console.log('Actual obj value here :',value.topic);
			    	 			 $('<option>').val(value.topicId).text(value.topic).appendTo(select);
			    	 			
			    	      });
			    	 	 $('#topic').multiselect('rebuild');
			    		});
			     
			}
			
			function loadSubTopics() {
				var selected = document.getElementById("topic");
				var selectedValue = selected.options[selected.selectedIndex].value;
			    //alert("Existing : "+selectedValue);
			    var selectedTopic = $("#topic option:selected");
			    var messageTopic = "";
			    selectedTopic.each(function () {
			    	 messageTopic += $(this).val()+",";
			        // message += $(this).text() + "\n";
			     });
			    //alert("New :"+messageTopic);
			    var actualString = messageTopic.substring(0, messageTopic.length-1)
			    var arrString = actualString.split(',');
			    $('#subtopic').find('option').remove();
			    for (var i=0; i < arrString.length; i++) {
			    
			     
			     $.get('${pageContext.request.contextPath}/getSubTopics', {selectedValue : arrString[i]},
			    		  function(jsonResponse) {
			    			//alert(jsonResponse);
			    			
			    			console.log("json response :",jsonResponse);
			    	     		var select = $('#subtopic');
			    	     		//var subselect= $('#subdeptid');
			    				//select.find('option').remove();
			    					//$('#subtopic').find('option').remove();
			    					jsonResponse = $.parseJSON(jsonResponse);
			    					console.log("parsed json response :", jsonResponse);
			    					
			    	 	  $.each(jsonResponse, function(index, value) {
			    	 		  console.log('Index value here :',index);
			    	 		 console.log('Actual obj value here :',value.subTopic);
			    	 			 $('<option>').val(value.subTopicId).text(value.subTopic).appendTo(select);
			    	      }); 	
			    	 	 $('#subtopic').multiselect('rebuild');
			    		});
			 }
			}
		</script>
		
		
</body>

<script>
onload();

</script>
<script type="text/javascript">
$(function () {
    $('#topic').multiselect({
        includeSelectAllOption: true,
        nonSelectedText:"Choose Topic*"
    });
    $('#subtopic').multiselect({
        includeSelectAllOption: true,
        nonSelectedText:"Choose Sub Topic*"
    });
    
});
</script>
<style>
.faculty_mod .strong {
padding-right:25px;
}
.btn{
font-size:12px;}
button, input, select, textarea
{
font-family: arial !important;}
.fadehide {
position:absolute!important;
right:350px;
top:180px;}
</style>
</html>