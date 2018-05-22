<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/Validatestudent.js"></script>
<script type="text/javascript" src="../JS/countries.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>
<script type="text/javascript" src="../JS/Validatesetupcustomer.js"></script> 
<script src="../JS/jquery-ui.min.js"></script>
<script src="../JS/jquery.js"></script>
 <script type="text/javascript" src="../JS/PreViewImage.js"></script> 

<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<link href="../CSS/kedu.css" rel="stylesheet"/>
<link href="../CSS/design-common.css" rel="stylesheet"/>
<link href="../CSS/notification-new.css" rel="stylesheet"></link>

<!--  for country code select css -->
<link rel="stylesheet" href="../CSS/intlTelInput.css">
<script src="../JS/jquery1.11.3.js"></script>
<!--  added  for date picker -->
<script src="../JS/jqueryuidate.js"></script>
<script src="../JS/jquerydate.js"></script>
<!-- <link rel="stylesheet" href="../CSS/datepickerui.css"> -->
<!--  needs to be remove -->
<script src="../JS/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="../JS/MessageFadeOut.js"></script>

</head>
<script>
$(document).ready(function() {
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
	        
		        
		        $('#pcode').each(function() {
		             if ($.trim($(this).val()).length<=4 && $.trim($(this).val()) != ''){
		                  isValid = false
		                 
		                  $(this).css({
		                      "border": "1px solid red",
		                    //  "background": "#FFCECE"
		                  });
		              }else {
		               required: true
		               number: true               
		               maxlength:6
		                  $(this).css({
		                      "border": "",
		                     // "background": ""
		                  });
		              }
		          });  
		        $('#pcode').each(function() {      
		        	if ($.trim($(this).val()) === '') {  
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
		        $('.state_sel select').each(function() {
		            if ($.trim($(this).val()) == '-1') {
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
		  /*       $('.country_sel select').each(function() {
		            if ($.trim($(this).val()) == '-1') {
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
		        if($("input:radio[name='gender']").is(":checked")) {
		        	 //alert("isValid gener="+isValid);
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
	 
	 $(".fadehide").delay(600000).fadeOut("slow");  
});
function SubmitIfValid()
{
   if(!$("#setupform").valid()) return false;  
   return true;
}
// --ajax code for checking userid is exit or not -
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

function validateEmail() 
{
// alert("in");  
var email=document.getElementById("emailid").value;
 var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

if(email == "" || email == null)
{ 
  $(email).css({
      "border": "1px solid red",
      //"background": "#FFCECE"
  });
  document.getElementById("email").focus();  
 return false;
  }
else if(!(email.match(mailformat)))
{
//	alert("Enter Valid Email ID"); 
   $('#emailid').css({
	   "border": "1px solid red",
      // "background": ""
   });
  document.getElementById("email").value="";
 document.getElementById("email").focus();  
 return false;  }  }

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
	 $('#adminname').keydown(function (e) {
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
	document.getElementById('orgtypename').placeholder = nm;
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
.container{

background: url("../Image/body.png") center center no-repeat;
}
.su{
	color: #008000;
    font-size: 12px;
    font-weight: bold;
    /* margin-top: -48px; */
    text-align: center;
/*     top: 7%;
    left:6%;
    position: absolute;
 */
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

input#btnSubmit {
    width: 266px;
}
		
		
</style>
<script type="text/javascript">
$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
});

$(document).ready(function() {
	
        $(function() {
            $('.date-picker-year').datepicker({
                changeYear: true,
                showButtonPanel: true,
                dateFormat: 'yy',
                onClose: function(dateText, inst) { 
                    var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
                    $(this).datepicker('setDate', new Date(year, 1));
                }
            });
        $(".date-picker-year").focus(function () {
                $(".ui-datepicker-month").hide();
            });
        });

        $(function () {
       	 $('#adminname').keydown(function (e) {
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

		$(function() {
    $('#yof').datepicker( {
        changeMonth: true,
        changeYear: true,
        showButtonPanel: true,
        dateFormat: 'MM yy',
        onClose: function(dateText, inst) { 
            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
            $(this).datepicker('setDate', new Date(year, month, 1));
        }
    });
});
		</script>
		<script>
		$(function () {
		    $("#datepicker").datepicker({
		        changeMonth: true,
		        changeYear: true,
		        minDate: '-75Y',
		        maxDate: '-1d',
		        yearRange:"1940:2017",
		        onClose: function (dateText, inst) {
		          if (dateText != $.datepicker.formatDate($.datepicker.regional[''].dateFormat, new Date(dateText))) 
		          {
		              alert("The date you specified is not a valid date.");
		              this.value = '';
		          }
		        }
		    });
		});
		 
		$(function() {
		    $( "#datepicker" ).datepicker();
		  });

  </script><body>
<div class="container">
<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/SuperAdminMenu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<% 
				if (request.getAttribute("OwnerSuccess")!= null) { 
					String msg2=(String)request.getAttribute("OwnerSuccess");
					 String valid=msg2;
					%>
					<p class="success"><%= valid %></p>
					<%
				}
				else if (request.getAttribute("UserExists")!= null) { 
					
					%>
					<p class="failure"><%= request.getAttribute("UserExists") %></p>					
					<%
				} 
				else  if (request.getAttribute("OwnerFailure")!= null) { 
					
					%>
					<p class="failure"><%= request.getAttribute("OwnerFailure") %></p>					
					<%
				} 
				
			%>
		<div class="faculty_mod" style="width:300px;height:430px;top:0px;overflow-x:hidden;overflow-y:scroll">
			<p class="strong">Create Admin</p>


	<form name='setupform' id='setupform' method="post" action="/KODE_DEV/ControllerServlet/CreateSetupAdmin">
         <div class="info">
			<table align="center" width="100%">
			
			<tr>
			<td>
			
			</td>
			</tr>
				 
				<tr>
					<td><input type="text" name="adminname" class="box" maxlength="50" placeholder="Admin name*" id="adminname"></td>
				</tr>
				<tr>
					<td><input type="text" class="box" placeholder="Email ID*" name="emailid" id="emailid" onchange="validateEmail()"></td>
					
				</tr>
				<tr style="display:none;"><td>
				
								   <input type="text" value="01/01/2017"  name="datepicker" id="datepicker" placeholder="Date of Birth*" class="box" readonly></td>
									
									</tr>
									
									<tr style="display:none;"><td class="genders">
									<span style="width: 12px">
			<label>Gender*</label>
			<input type="radio" name="gender" id="gender" value="Male" required>Male
			<input type="radio" name="gender" id="gender" value="Female" required>Female
			<input type="radio" name="gender" id="gender" value="Other" checked="checked" required>Others</span>
			<p id="display"></p></td></tr>
				<tr>
					<td>
					<input type="hidden" name="sName" id="sName" value="">
				<input type="hidden" name="sID" id="sID" value="">
					 <input  type="text" class="box" placeholder="Mobile Number*" name="phone" id="phone" maxlength="10" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
					</td>
				</tr>
				<tr>
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
					//populateCountries("country2");
				</script>
				<tr>
				<td><input class="box" required placeholder="Enter your City*" type="text" name="city" id="city" maxlength="50"></td>
				</tr>
				<tr>
					<td><textarea  placeholder="Address*" rows="5" cols="25" name="address" id="address" class="required box1" maxlength="100"></textarea></td>
				</tr>
				<tr>
					<td><input type="text" placeholder="Postal Code" name="pcode" id="pcode" maxlength="6">
					<input type="hidden" name="ecno" id="ecno" value="" >
					<input type="hidden" name="fax" id="fax" value="" >
					</td>
				</tr>
				
				<tr>
					<td><input class="btn1" id="btnSubmit" type="button" value="Create" onClick="return SubmitIfValid()"> 
					<!-- 	<a class="back" style="color: #c2c2c2;" href="../JSP/Home.jsp">Back</a></td>
					 -->
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
<script language="javascript">
$("#phone").intlTelInput({
  utilsScript: "../JS/utils.js"
});
</script>
<style>
.faculty_mod .strong {
padding-top:15px;
}
</style>
<style>
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
    height: 16px;
    border-radius: 100%;
    position: absolute;
    left: 116px;
    margin-top: -17px!important;
}
#display span#error2 {
width: 17px;
    height: 15px;
    background: red;
    float: left;
    border-radius: 100%;
    position: absolute;
    left: 172px;
    margin-top: -17px;
}
#display span#error3 {
width: 17px;
    height: 16px;
    background: red;
    float: left;
    border-radius: 100%;
    position: absolute;
    left:73px;
    margin-top: -17px;
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
.fadehide {
position:absolute;
top:180px;
right:370px;
font-size:14px;
font-weight:bold;
font-family:arial;}

</style>
</body>
</html>