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
	    /* 	  $('.sel select.opt').each(function() {
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
		        }); */
	        
		        $('input[type="file"].inputfile').each(function() {
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
function createStudent()
{
	document.KnowStore.action = "../JSP/Student.jsp";
	document.KnowStore.submit();
}
	 </script>
<script type="text/javascript">
	/*  function validation() {
		// alert("in");
		//if (valid5()) {
			//alert("in1");
			//var orgid=document.getElementById("orgid").value;
			var tgt="/KODE_DEV/ControllerServlet/OwnerSetUpKnowStoreServlet";//?orgid="+orgid;
			document.KnowStore.action = tgt;
			//alert(document.getElementById("orgid").value);
			document.KnowStore.submit();
				
	}
	  */
/* 	function getUser(){
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
	 */
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
	 
	// var request;
/* 	 function getID()
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
	 } */
/* 	 function getInfo(){
	 if(request.readyState==4){
	 var val=request.responseText;
	 document.getElementById('amit').innerHTML=val;
	} 
	}*/
	function uploadStudent()
	{
		//alert("in");
		var orgid=document.KnowStore.orgid.value;
		//alert(orgid);
		var files=document.KnowStore.files.value;
		var fileExtn = files.split(".");
		if(fileExtn[1]=="xls"  || fileExtn[1]=="xlsx" ){
	//	alert(fileExtn[1]);
			 $('.inputfile').css({
			       "border": "",
			   });
			document.KnowStore.action="/KODE_DEV/ControllerServlet/CreateStudentFromExcelUploadService?orgid="+orgid;
			
			document.KnowStore.submit();
		}
		else
			{alert("File extension should be .xls");
			 $('.inputfile').css({
			       "border": "1px solid red",
			  
			       //"background": "#FFCECE"
			   });
			// return false;
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
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="faculty_mod" style="width:300px;margin-top: 50px;height:auto;position:static;float:right;margin-right:15px;padding:0px 0px 50px 35px;">
		<p class="strong" style="padding-left:0px;margin-bottom:0px;">Create Student</p>
			<form name="KnowStore" id="SubmitForm" method="post" enctype="multipart/form-data">
			<input name="data" id="data" type="hidden" value=""/>
         

						<%
							if (request.getAttribute("SuccessMessage") != null) {
						%>
						<p class="success"
							style=" background:#none;">
							<%=request.getAttribute("SuccessMessage")%>
						</p>
						<%
							} else if (request.getAttribute("FailureMessage") != null) {
						%>
						<p class="failure"
							style=" background:#none;">
							<%=request.getAttribute("FailureMessage")%>
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
						<!-- <td>File</td>
						<td>:</td> -->
						<td>
						<span>Upload Excel </span>
						<!--  <input name="brows" id="brows" class="box inputfile" style="width: 248px; color:#000;" type="file" name="upload" /></td> --> 
						 <input type="file" id="files" class="box inputfile" name="files" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel" />
						 <input type="hidden" id="orgid"  name="orgid" value="<%=orgid%>" /> 
					</tr>
					<tr>
						<!-- <td>File</td>
						<td>:</td> -->
						<td>
						<input class="box" id="uplode1" style="width: 265px; color:#000;" type="button" onclick="uploadStudent()" value="Upload" name="Upload" /></td>
					</tr>
					<tr><td>&#160;</td></tr>
					<tr>
						<!-- <td>File</td>
						<td>:</td> -->
						<td style="text-align:center;padding-right:40px;">Or</td>
					</tr>
					<tr><td>&#160;</td></tr>
					<tr>
						<td>
						<input class="btn1" id="uplode2" value="Create Manually" type="button" style="width: 265px;" onclick="createStudent()"> 
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