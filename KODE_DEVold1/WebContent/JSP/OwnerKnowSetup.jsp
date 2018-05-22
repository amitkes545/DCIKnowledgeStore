<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.dao.OwnerSetUpKnowStoreDao"%>
<%@ page import="com.kds.KODE_DEV.dao.OwnerSelectOrgIDKStoreDao"%>

<%@ page import="com.kds.KODE_DEV.domain.*"%>
<%@ page import="com.kds.KODE_DEV.domain.*" %>
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
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<link href="../CSS/notification-new.css" rel="stylesheet"></link>
<script src="../JS/MessageFadeOut.js"></script>
<!-- <script type="text/javascript" src="../JS/MessageFadeOut.js"></script> -->
<style>
.pp{    z-index: 1;
    /* position: absolute; */
    right: 104px;
    margin-top: -12px;}
    </style>
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
		            if ($.trim($(this).val()) == '' ||$.trim($(this).val()) == 0) {
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
$(function () {
    $("#kssize").keydown(function (e) {
    	
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
	String orgid = (String)session.getAttribute("orgid");
	String[] resultUserid=(String[])session.getAttribute("resultUserId");
	OwnerSetUpKnowStoreDao fbkDAo = new OwnerSetUpKnowStoreDao();
	int knowStoreSize = fbkDAo.selectOwnerKnowSpace();
	String user1 = session.getAttribute("userid").toString();  
	int allLibSize = fbkDAo.selectAllKnowSpace(user1);
%>
<body>
	<div class="container">
<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/owner_menu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<%
						if (request.getAttribute("OwnerSuccess") != null) {
					%>
					<p class="success">
						<%=request.getAttribute("OwnerSuccess")%>
					</p>
					<%
						} else if(request.getAttribute("OwnerFailure") != null) {
					%>
					<p class="failure" >
						<%=request.getAttribute("OwnerFailure")%>
					</p>
					<%
						}
					%>
		<div class="faculty_mod" style="width:260px;height:auto;padding:15px;position:static;float:right;margin-right:15px;">
		<p class="strong">Setup Knowledge Store</p>
		<p class="pp">  Total space allocated: <%= knowStoreSize%> GB</p><br>
			<p class="pp">  Available space : <%= (knowStoreSize - allLibSize) %> GB</p>
			<form name="KnowStore" id="SubmitForm" method="post" action="/KODE_DEV/ControllerServlet/OwnerSetUpKnowStoreServlet">
         <div class="info">
				<table align="center">
					
					
					
					<tr>
						<td class="sel"><select name="orgid" id="orgid"  class="opt" required placeholder="Select Customer Type">
						<option value="">Choose Customer*</option>
								<%
									OwnerSelectOrgIDKStoreDao dobj2 = new OwnerSelectOrgIDKStoreDao();
									ArrayList<String> al = dobj2.fetchNewOrg();
									//HashSet set =(HashSet)al.fetchValue();
									Iterator<String> itr = al.iterator();

									while (itr.hasNext()) {
										String orgid1 = itr.next();
										String orgname=itr.next();
										String orgnameid =(orgname+" ("+orgid1+")");
								%>
								<option value="<%=orgid1%>"><%=orgnameid%></option>
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
						<td><input type="text" name="kssize" id="kssize" placeholder="Knowledge Store size in GB*" maxlength="3" min="1" onkeypress='return event.charCode >= 48 && event.charCode <= 57' class="box">
							</td>
					<tr style="display:none">
						<td><select name="status" id="status">
								<option value="Active" selected="selected">Active</option>

								<option value="InActive">InActive</option>
						</select></td>
					</tr>
					<tr>
						<td style="padding-top: 10px;">
						<input class="btn1" id="uplode1" value="Create" type="button" style="width: 262px;" onclick="return SubmitIfValid()"> 
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
$("#kssize").intlTelInput({
  utilsScript: "../JS/utils.js"
});
</script>
</body>

<script>
onload();
</script>
<style>
.fadehide  {

    position: absolute;
font-weight:bold;
font-family:arial;
    top: 180px;
    right: 400px;
}

</style>
</html>