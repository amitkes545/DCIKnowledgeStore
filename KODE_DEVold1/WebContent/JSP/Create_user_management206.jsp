<%@ page language="java" contentType="text/html;  charset=ISO-8859-1" pageEncoding="UTF-8"%>
	<%@ page import="com.kds.KODE_DEV.dao.*"%>
<%@ page import="com.kds.KODE_DEV.domain.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/Validatestudent.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<link href="../CSS/kedu.css" rel="stylesheet"/>
<!--  for country code select css -->
<link rel="stylesheet" href="../CSS/intlTelInput.css">
<script src="../JS/jquery1.11.3.js"></script>
<script src="../JS/countries.js" type="text/javascript"></script>
<!--  added  for date picker -->
<script src="../JS/jqueryuidate.js"></script>
<script src="../JS/jquerydate.js"></script>
<!-- <link rel="stylesheet" href="../CSS/datepickerui.css"> -->
<!--  needs to be remove -->
<script src="../JS/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">    
</head>
<script>

$(document).ready(function() {
//alert("in1");	
	 $('#country').change(function()
			 {
			  var option = $(this).find('option:selected').val();
			  $('#country_text').val(option);
			 });
	// alert("after country");
	 $("#btnSubmit").click(function(e) {
	    	var isValid = true;
	    	// alert("isValid="+isValid);
	    	//alert("in btn submit");
	    	  $('.sel select.opt').each(function() {
	    		  //alert("isValid select="+isValid);
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
		         //   alert("isValid after select="+isValid);
		        });
	        
	    	  /* $('.search_result input[type="text"]').each(function() { */
		        $('input[type="text"].box').each(function() {
		        	// alert("isValid test="+isValid);		        	
		            if ($.trim($(this).val()) == '') {
		                isValid = false;
		                $(this).css({
		                    "border": "1px solid red",
		                    //"background": "#FFCECE"
		                });
		            }
		            else { 
		            	lettersonly:true
		            
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
		            else if ($.trim($(this).val()).length<6) {
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
		        	// alert("isValid tarea="+isValid);        	
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
		/*         $('.country_sel select').each(function() {
		        	// alert("isValid cunt="+isValid);	        	
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
	       // alert("isValid="+isValid);
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
//alert("in...");	
   if(!$("#setupform").valid()) return false;  
   return true;
}

// --ajax code for checking userid is exit or not --

var request;
/* function getOrgID()
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
} */

/* function getInfo(){
if(request.readyState==4){
var val=request.responseText;
document.getElementById('amit').innerHTML=val;
}
} */
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

 function ValidateEmail() {

	//alert("hi this is alert ");
	/* var orgName=document.getElementById("orgname").value;
	var address=document.getElementById("address").value;
	var phone=document.getElementById("phone").value; */
	var email=document.getElementById("email").value;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	//alert("in");
	
	
	/* if(orgName == "" || orgName == null){
		alert("Enter  Name");
		document.getElementById("orgname").focus();
		return false;
	}else if(address == "" || address == null){
		alert("Enter Address ");
		document.getElementById("address").focus();
		return false;
	}else */ 
	if(email == "" || email == null){
		alert("Enter Email ID");
		document.getElementById("email").focus();
		return false;
	}else if(!(email.match(mailformat))){
		alert("Enter Valid Email ID");
		document.getElementById("email").value="";
		document.getElementById("email").focus();
		return false;
	}/* else if(phone == "" || phone == null){
		alert("Enter Mobile Number");
		document.getElementById("phone").focus();
		return false;
	}else if(phone.length < 10){
		alert("Enter valid 10 digit mobile number");
		document.getElementById("phone").focus();
		return false;
	}
	else {
		document.setupform.action="/KODE_DEV/ControllerServlet/CreateUserManagement";
		document.setupform.submit();
	} */
	
	} 
/* function getID()
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
} */

/* function getInfoValue(){
if(request.readyState==4){
var val=request.responseText;
document.getElementById('amit1').innerHTML=val;
}
} */
//JQuery code for allowed organization name has a characters
$(function ()
		{  
	$('#orgname').keydown(function (e) { 
		if (e.altKey) { 
			e.preventDefault();  }
		else { 
			var key = e.keyCode; 
			if (!((key == 8) || (key == 9) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) { 
				e.preventDefault();  
				}  }  }); 
	});
		
$(function() {
    $("#pcode").keydown(function (e) {
    	//alert("in"+e.keyCode)
    	 // Allow: -,backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [189, 46, 8, 9, 27, 13, 110, 190]) !== -1 ||
            (e.keyCode == 65 && ( e.ctrlKey === true || e.metaKey === true ) ) || 
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 return;
        }
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)))
        {
            e.preventDefault();
        }
    });
});
//JQuery code for allowed super admin name has a characters
/* $(function () {
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
	 }); */
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
    font-size: 17px;
    font-weight: bold;
    margin-top: -40px;
     top: 4px;
     /*
    left:6%;
    position: absolute;
 */
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
    font-size: 15px;
    font-weight: bold;
    position: absolute;
    top: 14px;
    /*
    position: absolute;
     background: #fff;
    opacity: 0.7; 
    right:0px;
    padding: 0px 36px;*/
}
		
		
</style>
<script>
$(function () {
    $("#datepicker").datepicker({
        changeMonth: true,
        changeYear: true,
        minDate: '-75Y',
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

  </script>
<script type="text/javascript">
	 

		</script>
		<script type="text/javascript">
		
<%-- 		function changeDiscipline()
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
			
} --%>
/* 		function changeTopic()
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
		} */
	<%-- 	function changeSubject()
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
<%-- 
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
	
 --%>
		</script>
<body>
<div class="container">
<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/owner_menu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="faculty_mod" style="width:300px;margin-top: 80px;height:422px;overflow-x:hidden;overflow-y:scroll">
		<p class="strong" >Create User</p>
       	<form name='setupform' id='setupform' method="post" action="/KODE_DEV/ControllerServlet/CreateUserManagement" enctype="multipart/form-data">
         <div class="info">
			<table align="center" width="100%">
			<tr>
			<td>
			<% 
				if (request.getAttribute("OwnerSuccessMAil")!= null) { 
					String msg2=(String)request.getAttribute("OwnerSuccessMAil");
				/* 	 String str[]=msg2.split("#");
					 String valid=str[0];
					 String valid1=str[1];
				 */	%>
					<%-- <p class="su autohide"><%= valid %></p> --%>
					<p class="su1 autohide"><%=msg2 %></p>					
					<%
				}
				else if (request.getAttribute("OwnerSuccess")!= null) { 
					
					%>
					
				<%-- 	<p class="su autohide"><%= request.getAttribute("OwnerSuccess") %></p>	 --%>
					<p class="" style="color:red; font-size:14px;top:4px;left:0px;right:0px;text-align:center; position: absolute;"><%= request.getAttribute("OwnerSuccess") %></p>				
					<%
				} 
				else  if (request.getAttribute("OwnerFailure")!= null) { 
					
					%>
					<p class="" style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("OwnerFailure") %></p>					
					<%
				} 
				
			%>
			</td>
			</tr>
			<tr><td><input type="text" class="box" placeholder="User Name" name="orgname" id="orgname"></td></tr>
			<tr>
			<td class="sel">
			<select name="orgid" id="orgid" class="opt" required placeholder="Choose Customer Details" >
								<option value="">Choose Customer</option>
								<%
									OwnerSelectOrgIDKStoreDao dobj1 = new OwnerSelectOrgIDKStoreDao();
									ArrayList<String> alo = dobj1.fetchValue();
									//HashSet set =(HashSet)al.fetchValue();
									Iterator<String> itro = alo.iterator();

									while (itro.hasNext()) {
										String orgid1 = itro.next();
										String orgname= itro.next();
										String orgnameid =(orgname+" ("+orgid1+")");
								%>
								<option value="<%=orgid1%>"><%=orgnameid%></option>
								<%
									}
								%>
						</select>
						</td></tr>
				  <tr style="display:none">
					<td>
					<select name="orgtype" id="orgtype" required placeholder="Choose privilege" onchange="changeName()">
					          <OPTION value="">Choose privilege</OPTION>
					          <!--  <option value="Owner">Owner</option>
                             <option value="Admin">Admin</option>
                             <option value="SuperAdmin">SuperAdmin</option>
                             <option value="Participant">Participant</option>
                              -->
                             <option value="Super Admin">Super Admin</option>
                             
                    </select></td>
				</tr>
				<tr>
					<td>
					 <input  type="text" class="box" placeholder="Mobile Number" name="phone" id="phone" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<td><input type="text" class="box" placeholder="Email ID" name="email" id="email" onchange="ValidateEmail()"></td>
				</tr>
				<tr><!-- <td>Name<span class="astr">*</span></td><td>:</td> -->
              <td><input type="text" placeholder="Date of Birth" name="datepicker"  id="datepicker" class="box" readonly></td>
              </tr> 
            <!--  <form name="Genderform" method="post">
			<table> -->
			<tr><td class="genders">
									<span style="width: 12px">
				<label>Gender*</label>
			<input type="radio" name="gender" id="gender" value="Male" required>Male
			<input type="radio" name="gender" id="gender" value="Female" required>Female
			<input type="radio" name="gender" id="gender" value="Other" required>Others</span>
			<p id="display"></p></td></tr>
			<!-- <tr>
			
			<td class="genders">
			<span style="width: 12px">
			<input type="radio" name="gender" id="gender" value="Male" required>Male
			<input type="radio" name="gender" id="gender" value="Female" required>Female</span>
			<input type="radio" name="gender" id="gender" value="Others" required>Others</span>
			<p id="display"></p>
			
			</td>
			</tr> -->
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
				
                <!--   <tr>
				<td class="country_sel">
				<select id="country" name="country"  class="opt">
				</select>
				<input type="hidden" id="country_text" name="country_text">
				</td>
				</tr>
				<tr>
				<td class="state_sel">
				<select name="state" id="state" class="opt">
				<option>Select state</option>
				</select></td></tr> -->
				<script language="javascript">
					populateCountries("country", "state");
					//populateCountries("country2");
				</script>
				<tr><!-- <td>City</td><td>:</td> -->
				<td><input type="text" name="city"  placeholder="City" id="city" class="box"></td>
				</tr>
				<tr>
					<td><textarea rows="5" class="required box1" maxlength="500" placeholder="Address" cols="25" name="address" id="address"></textarea></td>
				</tr>
                  
				<tr>
					<!-- <td>Postal Code<span class="astr">*</span></td><td>:</td> -->
					<td><input type="text" name="pcode"  placeholder="Postal Code" id="pcode" maxlength="10" minlength="4" class="box"></td>
				</tr>
				
	
				<tr>
					<td><input class="btn1" id="btnSubmit" type="button" value="Create" onClick="return SubmitIfValid()" style="width:261px";> 
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
<!-- <script src="../JS/intlTelInput.js"></script> -->
<script>
$("#phone").intlTelInput({
  utilsScript: "../JS/utils.js"
});


</script>
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
    width: 17px;
    float: left;
    height: 16px;
    border-radius: 100%;
    position: absolute;
    left: 125px;
    margin-top: -16px!important;
}
#display span#error2 {
width: 17px;
    height: 16px;
    background: red;
    float: left;
    border-radius: 100%;
    position: absolute;
    left: 189px;
    margin-top: -16px;
}
#display span#error3 {
width: 17px;
    height: 16px;
    background: red;
    float: left;
    border-radius: 100%;
    position: absolute;
    left:75px;
    margin-top: -16px;
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
</style>
</body>
</html>