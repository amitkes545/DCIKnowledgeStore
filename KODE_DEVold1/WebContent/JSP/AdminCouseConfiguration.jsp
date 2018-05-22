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
<link href="../CSS/kedu.css" rel="stylesheet"/>
<link href="../CSS/design-common.css" rel="stylesheet"/>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<link href="../CSS/notification-new.css" rel="stylesheet"></link>
<script src="../JS/MessageFadeOut.js"></script>

<!-- <script type="text/javascript" src="../JS/MessageFadeOut.js"></script> -->
<script>
$(document).ready(function() {
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
	<div class="container" style="position:static;height:600px;">
<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/SuperAdminMenu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="faculty_mod" style="width:300px;margin-top: 50px;height:200px;position:static;float:right;margin-right:15px;padding:0px 0px 50px 35px;">
		<p class="strong" style="padding-left:0px;">Course Mapping</p>
			<form name="KnowStore" id="SubmitForm" method="post" action="/KODE_DEV/ControllerServlet/AdminCourseMappingServlet">
			<input name="data" id="data" type="hidden" value=""/>
         

						<%
							if (request.getAttribute("SuperAdminSuccess") != null) {
						%>
						<p class="success"
							style=" background:#none;">
							<%=request.getAttribute("SuperAdminSuccess")%>
						</p>
						<%
							} else if (request.getAttribute("mapFailure") != null) {
						%>
						<p class="failure"
							style=" background:#none;">
							<%=request.getAttribute("mapFailure")%>
						</p>
						<%
							} else if (request.getAttribute("mapFailureConstraint") != null) {
						%>
						<p class="failure"
							>
							<%=request.getAttribute("mapFailureConstraint")%>
						</p>
						<%
								}
						%>
<div class="info">
				<table align="center">
						<tr>
						<td class="sel"><select name="admin" id="admin"  class="opt" required placeholder="Select Admin">
						<option value="">Select Admin*</option>
						
						        <%
										GetAdminsForOrgDao getAdminsForOrgDao = new GetAdminsForOrgDao();
										List<UsersInfoDomain> adminUsers = getAdminsForOrgDao.getAdminsForOrganizationMapping(orgid);
										//HashSet set =(HashSet)al.fetchValue();
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
							    System.out.println("resultUserid="+resultUserid);
 					 
			  		 if(resultUserid!=null)
			  	 		{
							for(String uid:resultUserid){%>
						
				       <option value="<%=uid%>"><%=uid%></option>
						<% }}%>
						</select><span id="amit"></span></td>
					</tr>
					<tr>
						<td class="sel"><select name="course" id="course"  class="opt" required placeholder="Select Course">
						 <option value="">Select Course*</option>
									<%
										GetCourseForAdminDao getCourseForAdminDao = new GetCourseForAdminDao();
										List<CourseAdminDomain> courses = getCourseForAdminDao.getCourseForAdmin(orgid);
										//HashSet set =(HashSet)al.fetchValue();
										for(CourseAdminDomain course : courses) {
											System.out.println("course :"+course.getCourseId());
											String result = course.getCourseId();
											String name = course.getCourseName();
										
									%>
									<option value="<%=result%>"><%=name%> (<%=result%>)</option>
									<%
										}
									%>
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
						<input class="btn1" id="uplode1" value="Create" type="button" style="width: 265px;" onclick="return SubmitIfValid()"> 
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
</body>
<script>
onload();
</script>
<style>
.faculty_mod .strong {
padding-right:18px;
}
.fadehide {
position:ablosute;
top:180px;
right:380px;
}
</style>
</html>