<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ page import="com.kds.KODE_DEV.dao.*"%>
<%@ page import="com.kds.KODE_DEV.domain.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />-->
<link href="../CSS/jquery-ui.css" rel="stylesheet" type="text/css" />-->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/design-common.css" rel="stylesheet"></link>
<!--  for country code select css -->
<link rel="stylesheet" href="../CSS/intlTelInput.css">
<!--  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script> -->
<script src="../JS/jquery.min.js">
<script type="text/javascript" src="../JS/Validatesetupcustomer.js"></script> 

<script src="../JS/jquery-ui.min.js"></script>
<script src="../JS/jquery.js"></script>
 <script type="text/javascript" src="../JS/countries.js"></script> 
<script type="text/javascript" src="../JS/PreViewImage.js"></script>
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>
<link href="../CSS/notification-new.css" rel="stylesheet"></link>
<script src="../JS/MessageFadeOut.js"></script>
<!-- <script src="../JS/country.js" type="text/javascript"></script> -->

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
	    	 
	    	  $('.state_country select.opt').each(function() {
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
	    	  $('.state_sel select.opt').each(function() {
		            if ($.trim($(this).val()) == 'Select State') {
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
		            if ($.trim($(this).val()) == ''||$.trim($(this).val()) == 0) {
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
		        $('#yof').each(function() {  
		        	var d = new Date();   
		        	var year = d.getFullYear();   
		        	if ($.trim($(this).val()) == '') {  
		        		isValid = false          
		        		$(this).css({              
		        			"border": "1px solid red",      
		        			//"background": "#FFCECE"      
		        			});               
		        		} else if ($.trim($(this).val())>year) {    
		        			//alert(year);           
		        			isValid = false            
		        			$(this).css({           
		        				"border": "1px solid red",   
		        				//"background": "#FFCECE"   
		        				});              
		        			}                 
		        		else if ($.trim($(this).val()).length<4) {   
		        			isValid = false                
		        			$(this).css({               
		        				"border": "1px solid red",         
		        				//"background": "#FFCECE"         
		        				});       
		        			}
		        		else if ($.trim($(this).val())==0) {   
		        			isValid = false                
		        			$(this).css({               
		        				"border": "1px solid red",         
		        				//"background": "#FFCECE"         
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
		            if ($.trim($(this).val()) == '') {
		                isValid = false
		               
		                $(this).css({
		                    "border": "1px solid red",
		                    //"background": "#FFCECE"
		                });
		            }	            
		            else if ($.trim($(this).val()).length<4) {
		                isValid = false
		               
		                $(this).css({
		                    "border": "1px solid red",
		                    //"background": "#FFCECE"
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
		        var files=document.setupform.files.value;  
		         var fileExtn = files.split("."); 
		                  $('input[type="file"].box').each(function() { 
		             if ($.trim($(this).val()) == '') {   
		           //   alert('hi');   
		            isValid = false; 
		                 $(this).css({    
		                  "border": "1px solid red",     
		                 //"background": "#FFCECE" 
		                 });    
		          }      
		        else if(fileExtn[1]=="jpg"  || fileExtn[1]=="jpeg" || fileExtn[1]=="png" || fileExtn[1]=="bmp"){     
		             $(this).css({  
		                    "border": "",        
		             // "background": ""   
		               });   
		           }   
		           else    
		           {  
		             isValid = false;  
		                $(this).css({   
		                   "border": "1px solid red", 
		                                   }); 
		              }     
		     });
		        $('.country_sel select').each(function() {
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
		        
		        $('#email').each(function() {
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
		            var email=$(this).val();
		            
		            var validEmails = 1;
		        	// Get email parts
		            var emailParts = email.split('@');
		        	  // There must be exactly 2 parts
		        	 if(emailParts.length !== 2) {
		        		 isValid = false;
		        	    	 $("#email").css({"border": "1px solid red" });
		        	    }
	        	    // Name the parts
	        	     var emailName = emailParts[0];
	        	     var emailDomain = emailParts[1];
	        	    
	        	     // === Validate the parts === \\
	        	    
	        	    // Must be at least one char before @ and 3 chars after
	        	    if(emailName.length < 1 || emailDomain.length < 3) {
	        	        //alert("Wrong number of characters before or after @ sign");
	        	           isValid = false;
	        	    	 $("#email").css({"border": "1px solid red" });
	        	      }
	        	    
	        	     // Define valid chars
	        	     var validChars = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','.','0','1','2','3','4','5','6','7','8','9','_','-'];

	        	     // Domain's last . should be 2 chars or more from the end
	        	     var emailDomainParts = emailDomain.split('.');
	        	
	        	    if((emailDomainParts[emailDomainParts.length - 1].length < 2) || (emailDomainParts[emailDomainParts.length - 1].length > 3)) {
	        	      //  alert("Domain's last . should be 2 chars or more from the end");
	        	         isValid = false;
	        	    	 $("#email").css({"border": "1px solid red" }); 
	        	     }
	        		 for (var s = 0; s < emailDomainParts.length; s+=1) {
	        	    
	        	        if ((emailDomainParts[s])===(emailDomainParts[s+1]))
	        	         {
	        	          var email_check=1;
	        	          // alert("Please write valid email address with correct domain");
	        	             isValid = false;
	        	          $("#email").css({"border": "1px solid red" });
	        	          }
	        	     }
	        		
	        	   var last=emailDomainParts.length;
	        	   var m=last-1;
	               if(((emailDomainParts[m-1])==="com") || ((emailDomainParts[m-1])==="in")){
	        		  var email_check=1;
	        		 // alert("Please write valid email address with correct domain");
	        		 // $('#email').css('border', '1px solid red');
	        		    isValid = false;
	        		  $("#email").css({"border": "1px solid red" });
	        		}
	        			
	              if(email_check!=1)
	          	   {
	        	   }
		        });
		        
		        
	       
	        // here end working ok         
	        var theurl=document.setupform.url.value;   
		        var tomatch= /[A-Za-z0-9\.-]{3,}\.[A-Za-z]{3}/;   
		        if (tomatch.test(theurl))    
		        {           
		        $('#url').css({           
		        	"border": "",       
		        	});    
		        }    
		        else          {      
		        isValid = false;      
		        $('#url').css({     
		        	"border": "1px solid red",       
		        	});         
		        }
		        
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

/* function check_it() {
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
} */

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
	 $('#orgname,#orgtypename').keydown(function (e) {
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
    font-size: 17px;
    font-weight: bold;
    margin-top: -40px;
    top: 12px;
    /*
    left:6%;
    position: absolute;
 */
 }
.su1{
		    color: #008000;
    font-size: 15px;
    font-weight: bold;
    position: absolute;
    top: 15px;
  /*   position: absolute;
     background: #fff;
    opacity: 0.7; 
    right:0px;
    padding: 0px 36px; */
}
		
		
</style>
<script>
function ValidateEmail() {
//	alert("in");
		var email=document.getElementById("email").value;
		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	 if(email == "" || email == null){
			alert("Enter Email ID");
			document.getElementById("email").focus();
			return false;
		}else if(!(email.match(mailformat))){
			alert("Enter Valid Email ID");
			document.getElementById("email").value="";
			document.getElementById("email").focus();
			return false;
		}
		} 
		</script>
<script type="text/javascript">


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
</head>
<body>
<div class="container" style="position:relative;">
<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/owner_menu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<% 
				if (request.getAttribute("OwnerSuccessMAil")!= null) { 
					String msg2=(String)request.getAttribute("OwnerSuccessMAil");
					/*  String str[]=msg2.split("#");
					 String valid=str[0];
					 String valid1=str[1];
					 */%>
					<%-- <p class="su autohide"><%= valid %></p> --%>
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
					<p class="failure" ><%= request.getAttribute("OwnerFailure") %></p>					
					<%
				} 
				
			%>
		<div class="faculty_mod" style="width:300px;top:0px;height:430px;overflow-x:hidden;margin-bottom:10px;overflow-y:scroll">
			<p class="strong">Setup Customer</p>
	<form name='setupform' id='setupform' method="post" action="/KODE_DEV/ControllerServlet/CreateSetupCustomer" enctype="multipart/form-data">
         <div class="info">
			<table align="center" width="100%">
			
			
				  <tr>
					<td class="sel">
					<input type="hidden" value="" name="orgid" id="orgid" >
					<select name="orgtype" class="opt" required placeholder="Select Customer Type" id="orgtype" onchange="changeName()">
					          <OPTION value="" >Choose Customer*</OPTION>
					          
					          <%
									CustomerDao dobj1 = new CustomerDao();
									ArrayList<String> alo = dobj1.fetchValueCustomerType();
									//HashSet set =(HashSet)al.fetchValue();
									Iterator<String> itro = alo.iterator();
									String orgname=null;
									while (itro.hasNext()) {
										String orgid1 = itro.next();
										// orgname= itro.next();
										//String orgnameid =(orgname+" ("+orgid1+")");
								%>
								<option value="<%=orgid1%>"><%=orgid1%></option>
								<%
									}
								%>
								
                       <!--    <option value="University">University</option>
                         <option value="Institute">Institute</option>
                         <option value="College">College</option> -->
                    </select></td>
				</tr>
				<tr>
					<td><input type="text" name="orgname" class="box" placeholder=" Customer Name*" id="orgname" maxlength="25"></td>
				</tr>
				<tr>
					<td><input type="text" class="box" placeholder="Name*" name="orgtypename" id="orgtypename"maxlength="25"></td>
					
				</tr>
				<tr>
					<td>
					<input type="hidden" name="sName" id="sName" value="">
				<input type="hidden" name="sID" id="sID" value="">
					 <input  type="text" class="box" placeholder="Contact Number*" name="phone" id="phone" minlength="10" maxlength="10" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
					</td>
				</tr>
				<tr>
					<td><input class="box" type="text" placeholder="Email ID*" name="email" id="email" onchange=""></td>
				</tr>
			<!-- 	<tr>
				<td class="state_country">
				<select name="country" id="country" class="opt">
				<option>Select Country</option>
				</select></td>
				</tr>
				<tr>
				<td class="state_sel">
				<select name="state" id="state" class="opt">
				<option>Select State*</option>
				
				</select></td></tr> -->
				
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
				<td><input type="text" name="city"  placeholder="City*" id="city" class="box" maxlength="25"></td>
				</tr>
				
				
				<!-- <td class="country_sel"><select class="opt" required placeholder="Select Country" id="country" name="country">
				<option value="">Choose Country</option></select></td></tr>
				<tr>
				<td><select class="opt" required placeholder="Select State" name="state" id="state">
				<option value="">Choose state</option></select></td></tr>
				<script language="javascript">
					populateCountries("country", "state");
					populateCountries("country2");
				</script>
				<tr>
				<td><input class="box" required placeholder="Select City" type="text" name="city" id="city"></td>
				</tr> -->
				<tr>
					<td><textarea placeholder="Address*" maxlength="500" rows="5" cols="25" name="address" id="address" class="required box1"></textarea></td>
				</tr>
				<tr>
					<td><input type="text" class="box" placeholder="Postal Code*" name="pcode" id="pcode" maxlength="6" minlength="4">
					<input type="hidden" name="ecno" id="ecno" value="" >
					<input type="hidden" name="fax" id="fax" value="" >
					</td>
				</tr>
				<tr>
					<td><input type="text" class="box" placeholder="url*" name="url" id="url" onchange="check_it()"></td>
				</tr>
				<tr>
					<td><!-- <input style="color: #fff;" class="box" type="file" name="logo" id="logo" accept="image/*" />
  					   <img id="box_img" /> -->
  					   <input class="box" type="file" id="files" name="files" accept="image/*" />
 Supported Formats:[.jpg, .jpeg, .png, .bmp]
 <output id="list"></output>
  					   
  					    <!-- <input type="file" name="logo" accept="image/*">src="#" alt="your image"-->
  					    </td>
				<tr>
					<td><input type="text" class="box" placeholder="Year of foundation*" name="yof" id="yof" class="date-picker-year" maxlength="4"></td>
				</tr>
				<tr>
					<td><input class="btn1" id="btnSubmit" type="button" value="Create" onClick="return SubmitIfValid()" style="width:261px;"> 
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
<script>
$("#phone").intlTelInput({
  utilsScript: "../JS/utils.js"
});
</script>
 <script>
if (window.File && window.FileReader && window.FileList && window.Blob) {
  function handleFileSelect(evt) {
    var files = evt.target.files; // FileList object

    // Loop through the FileList and render image files as thumbnails.
    for (var i = 0, f; f = files[i]; i++) {

      // Only process image files.
      if (!f.type.match('image.*')) {
        continue;
      }

      var reader = new FileReader();

      // Closure to capture the file information.
      reader.onload = (function(theFile) {
        return function(e) {
          // Render thumbnail.
          var span = document.createElement('span');
           span.innerHTML = ['<img class="thumb" src="', e.target.result,
                            '" title="', escape(theFile.name), '" style="position: relative; width: 76px;"/>'].join('');
          var list=document.getElementById('list');
          //alert(list.childNodes.length);
          if(list.childNodes.length>0)
        	  {
        	  list.replaceChild(span, list.childNodes[0]);
        	  //list.insertBefore(span, null);
        	  }
          else{
          list.insertBefore(span, null);
          //document.getElementById('list').insertBefore(span, null);
          }
                 };
      })(f);

      // Read in the image file as a data URL.
      reader.readAsDataURL(f);
    }
  }

  document.getElementById('files').addEventListener('change', handleFileSelect, false);
} else {
	  alert('The File APIs are not fully supported in this browser.');
	}
</script>

</body>
<script>
function	 ValidateEmail() {
	// alert("in");
	var  email=document.getElementById(	"email" ).value;
	var  mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if (email == 	""  || email == 	null ){
	alert( "Enter Email ID"	);
	document.getElementById(	"email" ).focus();
	return	false ;
	} else if (!(email.match(mailformat))){
	alert( "Enter Valid Email ID"	);
	document.getElementById(	"email" ).value=	"" ;
	document.getElementById(	"email" ).focus();
	return	false ;
	}
	}); 
</script>
<style>
.autohide,.su,.su1 {
position:ablosute;
top:180px;
font-size:14px;
font-weight:bold;
right:400px;
}
</style>
</html>