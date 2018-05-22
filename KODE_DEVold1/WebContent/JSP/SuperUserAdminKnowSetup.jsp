<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.apache.poi.util.StringUtil"%>
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
		        $('button').each(function() {   
		        	$('#kssize').each(function() {        
		        		if ($.trim($(this).val()) == '') {    
		        			
		        	isValid = false         
		        	// alert("in if");         
		        	$(this).css({       
		        		"border": "1px solid red",    
		        		//"background": "#FFCECE"   
		        		});     
		        	}        
		        		else if ($.trim($(this).val())==0) {    
		        			isValid = false         
		        			//  alert("in if 1");    
		        			$(this).css({          
		        				"border": "1px solid red",   
		        				//"background": "#FFCECE"    
		        				});      
		        			}else {        
		        				required: true        
		        				number: true      
		        				maxlength:2           
		        				$(this).css({         
		        					"border": "",     
		        					// "background": ""   
		        					});    
		        		}         
		        		});
		              if ($.trim($("#admin option:selected").val()) == '') {
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
	  var selected = $("#admin option:selected");
      var message = "";
      selected.each(function () {
          message += $(this).val()+",";
         // message += $(this).text() + "\n";
      });
      //alert(message);
      var selectString = document.getElementById("data");
      console.log("in :"+selectString);
      selectString.value = message;
	
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
alert("orgid="+orgid);
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
	int totalAdminSpace = 0;
%>

<%
SuperAdminSetupknowStoreDao superAdminSetupknowStoreDao  = new SuperAdminSetupknowStoreDao();
String supera_space=superAdminSetupknowStoreDao.selectSuperAdminSpace(userid);
System.out.println("supera_space="+supera_space);
int superAdminSpace=0;
if(!StringUtils.isEmpty(supera_space))  {
	System.out.println("in if");
	superAdminSpace = Integer.parseInt(supera_space);
	System.out.println("superAdminSpace="+superAdminSpace);
}
String totalSpace = superAdminSetupknowStoreDao.selectAllAdminSpace(userid);
System.out.println("totalSpace="+totalSpace);
if(totalSpace != null) {
	totalAdminSpace = Integer.parseInt(totalSpace);
}


%>
<body>
	<div class="container">
<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/SuperAdminMenu.jsp" %>
		</div>
		<div style="clear: both;"></div>
			<%
			System.out.println("request.getAttribute"+request.getAttribute("SuperAdmin"));
						if (request.getAttribute("SuperAdmin") != null) {
					%>
					<p class="success">
						<%=request.getAttribute("SuperAdmin")%>
					</p>
					<%
						} else if((String) session.getAttribute("SuperAdminSize") != null) {
					%>
					<p class="failure">
						<%=request.getAttribute("SuperAdminSize")%>
					</p>
					<%
						}
					%>
		<div class="faculty_mod" style="width:300px;height:300px;">
		<p class="strong" style="padding-left:0px;">Setup Knowledge Store</p>
		<div class="paragraph_div">
		<p style="font-size: 12px;float: right;">Total Space Allocated : <%= superAdminSpace%> GB</p>
        <p>Available Space :<%= ((superAdminSpace) - totalAdminSpace)%> GB</p>
		</div>
			<form name="KnowStore" id="SubmitForm" method="post" action="/KODE_DEV/ControllerServlet/SuperAdminKnowStoreSetupServlet">
			<input name="data" id="data" type="hidden" value=""/>
         <div class="info">
				<table align="center">
					
					<tr>
						<td class="sel"><select name="admin" id="admin"  class="opt" required placeholder="Select Admin" multiple="multiple">
						<!-- <option value="">Choose Customer</option> -->
									<%
										GetAdminsForOrgDao getAdminsForOrgDao = new GetAdminsForOrgDao();
										List<UsersInfoDomain> adminUsers = getAdminsForOrgDao.getAdminsForOrganization(orgid);
										//HashSet set =(HashSet)al.fetchValue();
										for(UsersInfoDomain usersInfoDomain : adminUsers) {
											System.out.println("users :"+usersInfoDomain.getUserId());
											String user = usersInfoDomain.getUserId();
											
										
										Iterator<UsersInfoDomain> itr = adminUsers.iterator();

										/* while (itr.hasNext())  */
									%>
									<option value="<%=user%>"><%=usersInfoDomain.getUserName()+" ("+user+")"%></option>
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
						<td><input type="text" name="kssize" id="kssize" placeholder=" Knowledge Store size in GB*" class="box" maxlength="2" onkeypress='return event.charCode >= 48 && event.charCode <= 57'>
							</td>
					<tr style="display:none">
						<td><select name="status" id="status">
								<option value="Active" selected="selected">Active</option>

								<option value="InActive">InActive</option>
						</select></td>
					</tr>
					<tr>
						<td style="padding-top: 10px;">
						<input class="btn1" id="uplode1" value="Create" type="button" onclick="return SubmitIfValid()"> 
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
<script type="text/javascript">
    $(function () {
        $('#admin').multiselect({
            includeSelectAllOption: true,
            nonSelectedText:"Select Admin*"
        });
    });
</script>
<style>
.paragraph_div {
width:100%;
padding-bottom:20px;
height:auto;
}
.paragraph_div p {
width:100%;
margin-bottom:0px;
font-size:12px;
}
button.multiselect.dropdown-toggle.btn.btn-default {
    width: 245px;
}
.faculty_mod .strong {
padding-right:20px;
}
.multiselect-container {
    overflow-y: scroll;
    max-height: 200px;
    }

 .fadehide {
 color: red;
    /* position: absolute; */
    /* padding: 5px; */
    /* text-align: center; */
    /* top: 0px; */
    /* font-size: 14px; */
    position: absolute;
    top: 180px;
    right: 350px;
 }
</style>

</html>