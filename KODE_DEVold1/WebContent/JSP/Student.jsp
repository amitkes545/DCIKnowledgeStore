<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/Validatestudent.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<link href="../CSS/kedu.css" rel="stylesheet"/>
<!-- <link href="../CSS/design-common.css" rel="stylesheet"/> -->
<link href="../CSS/notification-new.css" rel="stylesheet"></link> 
<!--  for country code select css -->
<link rel="stylesheet" href="../CSS/intlTelInput.css">
<script src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>
<script src="../JS/countries.js" type="text/javascript"></script>
<!--  added  for date picker -->
<script src="../JS/jqueryuidate.js"></script>
<script src="../JS/jquerydate.js"></script>
<!-- <link rel="stylesheet" href="../CSS/datepickerui.css"> -->
<!--  needs to be remove -->
<script src="../JS/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">    

<!-- ended -->  
</head>
<style type="text/css">
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
#display #error{margin-top: -11px}
		.su{
    color: #008000;
    font-size: 15px;
    font-weight: bold;
    top: 8%;
    position: absolute;
     right:0px;
    padding: 0px 36px;
}
.su1{

    color: #008000;
    font-size: 10px;
    font-weight: bold;
    top: 12%;
    position: absolute;
     right:0px;
    padding: 0px 36px;
}
	span#amit 
{
    float: right;
    margin-left: 10px;
    margin-top: 10px;
    position: absolute;
}
span.astr 
{
    color: #f63a4c;
    margin: 3px 5px 0 !important;
}	

	.genders span{
	width:12px;
	color:#000!important;
	}
	.genders input {
position:relative;
z-index:9;
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
.genders span input{

margin-top: -2px;
vertical-align: middle;
width: 20px;
}
#error{color: red !important;}
	
		</style>
<script><!-- calling ajax code from jsp-->

var request;
function getID()
{
var v=document.studentform.sid.value;
var url="customerID.jsp?val="+v;

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
//applying this jquery code to text box for enter only numbers

$(document).ready(function() {
  // for date picker /
  //var objDate = new Date();
 // var Presentyear = objDate.getFullYear();
  $("#stdate").datepicker({
   //yearRange : '1900:' + Presentyear,
   changeMonth : true,
   changeYear : true,
   minDate : '1d',
   dateFormat: "yy-mm-dd",
  });
});
  $(document).ready(function() {
	  $("#datepicker").datepicker({
		  
		   changeMonth : true,
		   changeYear : true,
		   maxDate : '-1d',
		  // minDate : '-60d',
		//   dateFormat: "yy-mm-dd",
		   dateFormat: 'dd/mm/yy',
	        yearRange:"1940:2017",
		  });
	  });
  
$(document).ready(function() {
	$("#phone").keydown(function (e) {
    	
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
$("#pcode").keydown(function (e) {
    	
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
//JQuery code for allowed organization name has a characters
$(function () {
	 $('#studentName').keydown(function (e) {
	 if (e.altKey) {
	 e.preventDefault();
	 } else {
	 var key = e.keyCode;
	 if (!((key == 8) || (key == 9) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
	 e.preventDefault();
	 }
	 }
	 });
	
$('#lastName').keydown(function (e) {
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
//applying this code to text box for enter characters 
$(function () {
	 $('#sname').keydown(function (e) {
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
	 
$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
	
});
</script>

<script>
$(function() {
    $( "#datepicker" ).datepicker();
  });
function ValidateFile(){
	
var fileName=document.getElementById("students").value;
if(fileName == ""){
	alert("Please attach .csv file");
	return false;
}
}

 $(document).ready(function() {
	 
	 $('#country').change(function()
			 {
			  var option = $(this).find('option:selected').val();
			  $('#country_text').val(option);
			 });
		 
    $('#btnSubmit').click(function(e) {
    	var isValid = true;
    	 $('#pcode').each(function() {
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
        $('input[type="text"].required').each(function() {
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
        $('input[type="email"].required').each(function() {
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
             
        $('textarea.required').each(function() {
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
        if($("input:radio[name='gender']").is(":checked")) {
	    	  
	    	   $("#display").hide();
  		  }
	       else
	    	   {
	    	   isValid = false;
	    	   $('#display').slideDown().html('<span id="error1"></span><span id="error2"></span><span id="error3"></span>');
		        return false;
	    	   }  
        
        if (isValid == false) 
            e.preventDefault();
        else {
        	 ////alert('Thank you for submitting')
        	 $("#studentform").submit();
        	 	document.studentform.action="/KODE_DEV/ControllerServlet/StudentServlet";
     			document.studentform.submit();
        }
           
    });
}); 

</script>
<%
String username=(String)session.getAttribute("username");
if(username==null){%>
	<%response.sendRedirect("../JSP/error.jsp"); %>
	<%} %>
<%
	
		/* HttpSession mess = request.getSession();
		String msg = ""; */
		//String username=(String)session.getAttribute("username");
		//msg = (String) mess.getAttribute("MsgValue");
		String organizationId=(String)session.getAttribute("orgId");
		String userid=(String)session.getAttribute("userId");
		String organizationName=(String)session.getAttribute("organizationName");
		////System.out.println("organization id in student.jsp page:= "+organizationId+"created id:"+userid+"\n created by"+session.getAttribute("createdBy").toString());
		
		//}
	%>
	
<body>
<div class="container">
		<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="faculty_mod" style="width:300px;margin-top: 80px;height:450px;overflow-x:hidden;overflow-y:scroll">
			<p class="strong">Create Participants</p>
          <!-- action="/KODE_DEV/ControllerServlet/Student" enctype="multipart/form-data" -->
         <form name="studentform" id="studentform" action="/KODE_DEV/ControllerServlet/StudentServlet"  method="post"><!--  action="/KODE_DEV/ControllerServlet/StudentServlet" -->
          
           <table align="center">
           <%
			 
				if (request.getAttribute("MsgValue")!= null) { 
					%>
				<p class="autohide" style="color:red; font-size:12px; font-weight: bold; top: 10%; left: 30%; position: absolute;">	<%= request.getAttribute("MsgValue") %>	</p>				
					<%
				}
				
			%>
			
			 <%if (request.getAttribute("FacultySuccessMail")!= null) { 
				String msg2=(String)request.getAttribute("FacultySuccessMail");
			/* 	 String str[]=msg2.split("#");
				 String valid=str[0];
				 String valid1=str[1];
			 */	%>
			<%-- 	<p class="su autohide"><%= valid %></p><br> --%>
				<p class="success"><%=msg2 %></p>		
				<%
			}
			 
			else  if (request.getAttribute("FacultySuccess")!=null) { 
					//System.out.println(" faculty success message value in if:"+request.getAttribute("FacultySuccess"));
					%>
					<p class="success"><%= request.getAttribute("FacultySuccess") %></p>					
					<%
				} else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
					<p class="failure"><%= request.getAttribute("FacultyFailure") %></p>					
					<%
				} 
				
			%>
			<tr>
              <td><input type="text" name="studentID" placeholder="Student ID" id="studentID" class="required"></td>
              </tr>
			<tr>
              <td><input type="text" name="studentName" placeholder="First Name" id="studentName" class="required"></td>
              </tr>
               <tr><!-- <td>Name<span class="astr">*</span></td><td>:</td> -->
              <td><input type="text" name="lastName" placeholder="Last Name" id="lastName" class="required"></td>
              </tr>        
               
              
              <tr><!-- <td>Mobile # <span class="astr">*</span></td><td>:</td> -->
                  <td><input type="text" name="phone" placeholder="Mobile Number" id="phone" minlength="10" maxlength="10" class="required"></td>
                  </tr>
              <tr>
                <td><input type="email" name="email"  placeholder="Email ID" id="email" class="required"></td>
                </tr>
                
                <tr><!-- <td>Name<span class="astr">*</span></td><td>:</td> -->
              <td><input type="text" placeholder="Date of Birth" name="datepicker"  id="datepicker" class="required" readonly></td>
              </tr> 
            <!--  <form name="Genderform" method="post">
			<table> -->
			<tr>
			
			<td class="genders">
			<span style="width: 12px">
			<label>Gender*</label>
			<input type="radio" name="gender" id="gender" value="Male" required>Male
			<input type="radio" name="gender" id="gender" value="Female" required>Female
			<input type="radio" name="gender" id="gender" value="Others" required>Others</span>
			<p id="display"></p>
			
			</td>
			</tr>
			<!-- </table>			
			</form>  --> 
                
                  
                  <tr>
				<!-- <td> Country<span class="astr">*</span></td><td>:</td> -->
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
				<td><input type="text" name="city"  placeholder="City" id="city" class="required"></td>
				</tr>
				<tr><!-- <td>Address<span class="astr">*</span></td><td>:</td> -->
                  <td><textarea rows="4" cols="37" name="address" placeholder="Address" id="address" class="required"></textarea></td>
                 
                  </tr>
                  
				<tr>
					<!-- <td>Postal Code<span class="astr">*</span></td><td>:</td> -->
					<td><input type="text" name="pcode"  placeholder="Postal Code" id="pcode" maxlength="10" onkeypress='return event.charCode >= 48 && event.charCode <= 57' class="box" style="width:251px;"></td>
				</tr>
                
                  
                  <%-- <tr>
                   <td><input type="hidden" name="organizationId" id="organizationId" value="<%=organizationId %>" readonly="readonly"></td>
                  <td><input type="hidden" name="createdid" id="createdid" value="<%= userid%>" readonly="readonly"></td>
                  </tr> --%>
                                   
                 
                   <tr>
                  <td><input style="width: 263px;" class="add_btn1" id="btnSubmit" type="button" value="Create"><!--  onclick="Validatestudent()" -->
                
                  
                  </tr>
                 <!--  <tr> <td>  
                  <input type="file" id="files" name="files" onchange="uploadStudent()" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel" />
                  </td>
                  </tr> -->
           </table>
                
         </form>
         
        
		</div>
		<%@ include file="../JSP/FooterViews.jsp"%>
	</div>
<script src="../JS/intlTelInput.js"></script>
<script>
$("#phone").intlTelInput({
  utilsScript: "../JS/utils.js"
});
</script>
</body>
</html>