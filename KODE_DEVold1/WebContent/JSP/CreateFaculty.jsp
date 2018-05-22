<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="com.kds.KODE_DEV.dao.*"%>
<%@ page import="com.kds.KODE_DEV.domain.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/Validatestudent.js"></script>
<script type="text/javascript" src="../JS/countries.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
 <script type="text/javascript" src="../JS/Validatesetupcustomer.js"></script>  
<!--  <script src="../JS/jquery-ui.min.js"></script>  -->
 <script src="../JS/jquery.js"></script>  
  <script type="text/javascript" src="../JS/ImagePreview.js"></script>  

<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<link href="../CSS/kedu.css" rel="stylesheet"/>
<link href="../CSS/notification-new.css" rel="stylesheet"></link>
<!--  for country code select css -->
<link rel="stylesheet" href="../CSS/intlTelInput.css">
<link rel="stylesheet" href="../CSS/design-common.css">
<!-- <script src="../JS/jquery1.11.3.js"></script> -->
<!--  added  for date picker -->
<script src="../JS/jqueryuidate.js"></script>
<script src="../JS/jquerydate.js"></script>
<!-- <link rel="stylesheet" href="../CSS/datepickerui.css"> -->
<!--  needs to be remove -->
<script src="../JS/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">    
<script src="../JS/MessageFadeOut.js"></script>
</head>
<script>

$(document).ready(function() {
	 $('#country').change(function()
			 {
			  var option = $(this).find('option:selected').val();
			
			  $('#country_text').val(option);
			 });
	 
	 $("#btnSubmit").click(function(e) {
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
		        $('#phone').each(function() {
		            if ($.trim($(this).val()) == '') {
		                isValid = false
		               
		                $(this).css({
		                    "border": "1px solid red",
		                    //"background": "#FFCECE"
		                });
		            }	            
		            else if ($.trim($(this).val()).length<9) {
		                isValid = false
		               
		                $(this).css({
		                    "border": "1px solid red",
		                    //"background": "#FFCECE"
		                });
		            }else {
		            	required: true
		            	number: true		            	
		            	maxlength: 10
		                $(this).css({
		                    "border": "",
		                   // "background": ""
		                });
		            }
		        });
		        $('#pcode1').each(function() {
		             if ($.trim($(this).val()).length<4 && $.trim($(this).val()) != ''){
		                  isValid = false
		                 
		                  $(this).css({
		                      "border": "1px solid red",
		                    //  "background": "#FFCECE"
		                  });
		              }else {
		               required: true
		               number: true               
		               maxlength:10
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
		         $('.state_selection select').each(function() {
		        
		             if ($.trim($(this).val()) == 'Select your State*') {
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
	                
	                $('.country_sel select').each(function() {
			        	
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
		        
	       
		        
		    

		        
		        if($("input:radio[name='gender']").is(":checked")) {
			    	  
			    	   $("#display").hide();
		  		  }
			       else
			    	   {
			    	   isValid = false;
			    	   $('#display').slideDown().html('<span id="error1"></span><span id="error2"></span><span id="error3"></span>');
				        return false;
			    	   }  
		       
	        // here end working ok 
    if (isValid == false){
	            e.preventDefault();
	        }
	        else {
	        	 //alert('Thank you for submitting')
	        	  $("#setupform").submit();  
	        }
	           
	    });
});
	 
	 
function SubmitIfValid()
{
//alert("in");	
   if(!$("#setupform").valid()) return false;  
   return true;
}

// --ajax code for checking userid is exit or not --

var request;
function getOrgID()
{
var v=document.setupform.orgid.value;
var url="organizationId.jsp?val="+v;

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
<script>
//your function
function checkDate(){
    var idate = document.getElementById("date"),
        resultDiv = document.getElementById("datewarn"),
        dateReg = /(0[1-9]|[12][0-9]|3[01])[\/](0[1-9]|1[012])[\/]201[4-9]|20[2-9][0-9]/;
    
    if(dateReg.test(idate.value)){
        if(isFutureDate(idate.value)){
            resultDiv.innerHTML = "Entered date is a future date";
            resultDiv.style.color = "red";
        } else {
            resultDiv.innerHTML = "It's a valid date";
            resultDiv.style.color = "green";
        }
    } else {
        resultDiv.innerHTML = "Invalid date!";
        resultDiv.style.color = "red";
    }
</script>


<script>
var request;

function check_it() {
//	alert("in");
    var theurl=document.setupform.url.value;
    //var tomatch= /http:\/\/[A-Za-z0-9\.-]{3,}\.[A-Za-z]{3}/;
    var tomatch= /[A-Za-z0-9\.-]{3,}\.[A-Za-z]{3}/;
    //var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
   // alert(tomatch);
    if (tomatch.test(theurl))
    {
       WINDOW.alert("URL OK.");
       // return true;
    }
    else
    {
    	alert("Please enter valid URL.");
       //WINDOW.alert("URL invalid. Try again.");
        //return false; 
    }
}

function ValidateEmail() 
{
// alert("in");  
var email=document.getElementById("email").value;
 var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

if(email == "" || email == null)
{ 
  document.getElementById("email").focus();  
 return false;
  }
else if(!(email.match(mailformat)))
{
   alert("Enter Valid Email ID"); 
   
   $('#email').css({
       "border": "1px solid red",
    
       //"background": "#FFCECE"
   });
  document.getElementById("email").value="";
 document.getElementById("email").focus();  
 return false;  }  }

function Validatesetupcustomer() {

	//alert("hi this is alert ");
	var orgName=document.getElementById("orgname").value;
	var address=document.getElementById("address").value;
	var phone=document.getElementById("phone").value;
	var email=document.getElementById("email").value;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	//alert("in");
	
	
	if(orgName == "" || orgName == null){
		alert("Enter  Name");
		document.getElementById("orgname").focus();
		return false;
	}else if(address == "" || address == null){
		alert("Enter Address ");
		document.getElementById("address").focus();
		return false;
	}else if(email == "" || email == null){
		alert("Enter Email ID");
		document.getElementById("email").focus();
		return false;
	}else if(!(email.match(mailformat))){
		alert("Enter Valid Email ID");
		document.getElementById("email").value="";
		document.getElementById("email").focus();
		return false;
	}else if(phone == "" || phone == null){
		alert("Enter Mobile Number");
		document.getElementById("phone").focus();
		return false;
	}else if(phone.length < 10){
		alert("Enter valid 10 digit mobile number");
		document.getElementById("phone").focus();
		return false;
	}
	else {
		//document.setupform.action="/KODE_DEV/ControllerServlet/CreateUserManagement";
		//document.setupform.submit();
	}
	
	}
function getID()
{
var v=document.setupform.sID.value;
var url="customerID.jsp?val="+v;

if(window.XMLHttpRequest){
request=new XMLHttpRequest();
}
else if(window.ActiveXObject){
request=new ActiveXObject("Microsoft.XMLHTTP");
}

try
{
request.onreadystatechange=getInfoValue;
request.open("GET",url,true);
request.send();
}
catch(e)
{
alert("Unable to connect to server");
}
}

function getInfoValue(){
if(request.readyState==4){
var val=request.responseText;
document.getElementById('amit1').innerHTML=val;
}
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
$(function () {
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
	 });
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
#display #error{margin-top: -11px}
	#error{color: red !important;}	
.su{
	color: #008000;
    font-size: 14px;
    font-weight: bold;
    margin-top: -58px;
    text-align: center;
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
    font-size: 14px;
    font-weight: bold;
    position: relative;
    text-align: center;
}
		
		
</style>
<script>
$(function () {
    $("#datepicker").datepicker({
        changeMonth: true,
        changeYear: true,
        minDate: '-75Y',
        maxDate: '-1d',
        dateFormat: 'dd/mm/yy',
        yearRange:"1940:2017",
       /*  onClose: function (dateText, inst) {
          if (dateText != $.datepicker.formatDate($.datepicker.regional[''].dateFormat, new Date(dateText))) 
          {
              alert("The date you specified is not a valid date.");
              this.value = '';
          }
        } */
    });
});
  </script>
  
 
 <script type="text/javascript">

	 
$(document).ready(function() {
	$(".autohide").delay(500000).fadeOut("slow");
});
		</script>
		<script type="text/javascript">
		
		<%-- function changeDiscipline()
		{
			var discipline= document.getElementById("discipline").value;
			
			var select = document.getElementById('subject');
			select.options.length=0;
			document.getElementById("topic").length=0;
			document.getElementById("topic").options[0]=new Option('Choose Topic','Blank');
			
			select.options[0]=new Option('Choose Subject','Blank');
			select.options[1]=new Option('Other','Other');
			
			if(discipline=="Other"){
				document.getElementById("txtsubject").style.display='block';
				document.getElementById("txttopic").style.display='block';
				document.getElementById("subject").style.display='none';
				document.getElementById("topic").style.display='none';
				document.getElementById("txtdisp").style.display='block';
			}
			else{
				document.getElementById("subject").style.display='block';
			document.getElementById("txtsubject").style.display='none';
			document.getElementById("txttopic").style.display='none';
			//document.getElementById("topic").style.display='none';
			document.getElementById("txtdisp").style.display='none';
			}
			  <%
		        SubjectMasterDao sobj = new SubjectMasterDao();
						ArrayList<String> als = sobj.fetchValue();
						//HashSet set =(HashSet)al.fetchValue();
						Iterator<String> itrs = als.iterator();

						while (itrs.hasNext()) {
							String sub = itrs.next();
							String subid=itrs.next();
							String dispid=itrs.next();
							////System.out.println("cid="+custid);
					%>
					if(discipline=='<%=dispid%>'){
					select.options[select.options.length] = new Option('<%=sub%>','<%=subid%>');
					}
					<%}%>
			
}
		function changeTopic()
		{
			var topic= document.getElementById("topic").value;
			if(topic=="Other"){
				document.getElementById("txttopic").style.display='block';
				//document.getElementById("topic").style.display='none';
			}
			else
				{
				document.getElementById("txttopic").style.display='none';
				//document.getElementById("topic").style.display='block';
				}
		}
		function changeSubject()
		{
			var subject= document.getElementById("subject").value;
			//var txtsubject= document.getElementById("txtsubject").value;
			//alert(subject);
			//alert(txtsubject);
			/*if(txtsubject=="")
				alert("blank="+txtsubject);
			else if(txtsubject==null)
				alert("null="+txtsubject);
			else
			alert("---="+txtsubject);*/
			
			if(subject=="Other"){// ||subject=="Blank") && (subject=="Blank" || txtsubject=="Blank")  && (subject=="Other" || txtsubject=="") ){
				document.getElementById("txttopic").style.display='block';
				document.getElementById("txtsubject").style.display='block';
				document.getElementById("topic").style.display='none';
				//document.getElementById("topic").options.length=0;
				
			}
			else{
				document.getElementById("topic").style.display='block';
				document.getElementById("txtsubject").style.display='none';
			document.getElementById("txttopic").style.display='none';
			document.getElementById("txtsubject").value="";
			}
			var select = document.getElementById("topic");
			select.options.length=0;
			select.options[0]=new Option('Choose Topic','Blank');
			select.options[1]=new Option('Other','Other');
	        <%
	        TopicMasterDao tobj = new TopicMasterDao();
					ArrayList<String> alt = tobj.fetchValue();
					//HashSet set =(HashSet)al.fetchValue();
					Iterator<String> itrt = alt.iterator();

					while (itrt.hasNext()) {
						String topic_id=itrt.next();
						String subject_id=itrt.next();
						String topic = itrt.next();
						////System.out.println("cid="+custid);
				%>
				if(subject=='<%=subject_id%>'){
				select.options[select.options.length]=new Option('<%=topic%>','<%=topic%>');
				}
				<%}%>
		} --%>
function test()
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
}

function changeCustomer()
{
	var org= document.getElementById("orgid").value;
	//alert("changing Customer: "+org);
	var select = document.getElementById('discipline');
	select.options.length=0;
	select.options[0]=new Option('Choose Discipline','Blank');
	select.options[1]=new Option('Other','Other');
	
    <%
    DisciplineMasterDao dobj2 = new DisciplineMasterDao();
			ArrayList<String> al = dobj2.fetchValue();
			//HashSet set =(HashSet)al.fetchValue();
			Iterator<String> itr = al.iterator();
			while (itr.hasNext()) {
				String orgid = itr.next();
				String dispname = itr.next();
				String dispid = itr.next();
				////System.out.println("orgid="+orgid);
				//String dispnameid =(orgname+"("+orgid1+")");
				////System.out.println("cid="+custid);
		%>
		//alert("orgid="+'<%=orgid%>');
		if(org=='<%=orgid%>'){
		select.options[select.options.length] = new Option('<%=dispname%>','<%=dispid%>');
		}
		<%}%>

}
	

		</script><body>
<div class="container" style="height:auto;position:static;">
<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/Admin_menu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<% 
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
				else  if (request.getAttribute("OwnerFailure")!= null) { 
					
					%>
					<p class="failure"><%= request.getAttribute("OwnerFailure") %></p>					
					<%
				} 
				
			%>
		<div class="faculty_mod" style="width:300px;height:430px;overflow-x:hidden;margin-bottom:10px;top:0px;overflow-y:scroll">
		<p class="strong" style="margin-top:15px;">Create Faculty</p>
		
       	<form name='setupform' id='setupform' method="post" action="/KODE_DEV/ControllerServlet/CreateFacultyManagement" enctype="multipart/form-data">
         <div class="info">
			<table align="center" width="100%">
			
			<tr><td><input type="text" class="box" placeholder="Faculty Name*" name="orgname" id="orgname"></td></tr>
							
			<tr>
					<td><input type="text" class="box" placeholder="Email ID*" name="email" id="email" maxlength="25" onchange="ValidateEmail()"></td>
				</tr>	
				<tr>
					<td>
					 <input  type="text" class="box" placeholder="Mobile Number*" name="phone" id="phone" minlength="10" maxlength="10" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
					</td>
				</tr>
					<tr><!-- <td>Name<span class="astr">*</span></td><td>:</td> -->
              <td><input type="text" placeholder="Date of Birth*" name="datepicker"  id="datepicker" class="box" readonly onkeyup="checkDate()"></td>
              </tr> 			
							
				<td class="genders">
				
				
			<span style="width: 12px">
			<label>Gender*</label>
			<input type="radio" name="gender" id="gender" value="Male" required>Male
			<input type="radio" name="gender" id="gender" value="Female" required>Female
			<input type="radio" name="gender" id="gender" value="Others" required>Others</span>
			<p id="display"></p>
			
			</td>
			</tr>
			
            <!--  <form name="Genderform" method="post">
			<table> -->
			<tr>
			
			
			<!-- </table>			
			</form>  --> 
                 <tr>
				<td class="sel">
				<select class="opt" required placeholder="Select Country" id="country" name="country">
				<option value="">Choose Country</option>
				</select></td></tr>
				<tr>
				<td class="sel">
				<select class="opt" required placeholder="Select State" name="state" id="state">
				<option value="">Select your State*</option>
				</select></td></tr>
				
				<script language="javascript">  
				populateCountries("country", "state");
			  </script>
				<tr><!-- <td>City</td><td>:</td> -->
				<td><input type="text" name="city"  placeholder="City*" id="city" class="box" maxlength="50"></td>
				</tr>
				<tr>
					<td><textarea rows="5" class="required box1" placeholder="Address*" cols="25" name="address" id="address" maxlength="100"></textarea></td>
				</tr>
                  
				<tr>
					<!-- <td>Postal Code<span class="astr">*</span></td><td>:</td> -->
					<td><input type="text1" name="pcode"  placeholder="Postal Code" id="pcode1" maxlength="6" class="box" style="width:251px;"></td>
				</tr>
				
				
				<tr>
					<td><input class="btn1" id="btnSubmit" type="button" value="Create" onClick="return SubmitIfValid()" style="width:270px;"> 
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
 <style>
#ui-datepicker-div {
width:255px!important;
}
.ui-datepicker .ui-datepicker-title select {
font-size:12px!important;
}
.ui-datepicker th {
font-size:12px!important;
}
.ui-datepicker td span, .ui-datepicker td a {
font-size:12px!important;
text-align:center!important;
}
.intl-tel-input input, .intl-tel-input input[type=text], .intl-tel-input input[type=tel] {
width:261px;
}
.faculty_mod tr td:last-child > select {
width:261px;
}
.box1{
width:248px;
}
 .gender_list ul input {
    margin-top: -2px;
    vertical-align: middle;
    width: 20px;
}
#ui-datepicker-div {
width:255px!important;
}
.ui-datepicker .ui-datepicker-title select {
font-size:12px!important;
}
.ui-datepicker th {
font-size:12px!important;
}
.ui-datepicker td span, .ui-datepicker td a {
font-size:12px!important;
text-align:center!important;
}
.genders label.error {
    background: red;
    width: 14px;
    float: left;
    height: 14px;
    border-radius: 100%;
    position: absolute;
    left: 30px;
    margin-top: -1px;
}
#display span#error1 {
background: red;
    width: 16px;
    float: left;
    height: 15px;
    border-radius: 100%;
    position: absolute;
    left: 137px;
    margin-top: -15px!important;
}
#display span#error2 {
width: 16px;
    height: 16px;
    background: red;
    float: left;
    border-radius: 100%;
    position: absolute;
    left: 208px;
    margin-top: -15px;
}
#display span#error3 {
width: 16px;
    height: 15px;
    background: red;
    float: left;
    border-radius: 100%;
    position: absolute;
    left:83px;
    margin-top: -15px;
}
.country_sel .error {
border:1px solid red;
}
.country_sel label.error {
display:none!important;
}
.genders input {
position:relative;
z-index:9;
}
.autohide {
position:absolute;
font-size:14px;
font-weight:bold;
font-family:arial;
top:180px;
right:400px;}
</style>
</body>
</html>
