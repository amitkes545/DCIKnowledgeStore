<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Registration Form</title>
<link rel="stylesheet" href="../CSS/stylesheet.css" type="text/css" />
<link rel="stylesheet" href="../CSS/stylesheet_enroll.css" type="text/css" />

<script src="https://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<script src="../JS/enjquery.min.js"></script>
<script src="../JS/enjquery.validate.js"></script>
<link href="../CSS/notification-new.css" rel="stylesheet"></link>
<script src="../JS/MessageFadeOut.js"></script>
</head>
<body class="enrollment_body">
<div class="header1" style="margin-bottom:20px;">
<div id="left"><a href="http://www.softsourcetechnologies.com" target="_blank"> <img src="/KODE_DEV/Image/TNOU_logo.png" alt="Soft Source Technologies"></a></div>
  <div id="center" style="padding-left:225px;"><h3 style="color:#3264AF;">Enrollment Form</h3></div>
  <div id="right"><a href="https://www.skillsoftacademy.in:8443/KODE_DEV_TEST/#" target="_blank"> <img src="../Image/kode.png" alt="" /></a></div>
</div>
 	<div class="container row">
		<div class="student_form_outer row">
			<form class="userform" name="userForm" id="userForm" method="post"
				action="/KODE_DEV/ControllerServlet/DCIRegistrationService">
				
				<% 
				String status="";
				if(request.getParameter("status")!=null && request.getParameter("status").trim().equalsIgnoreCase("Failed"))
				{
					status="User is already registered for the course";
					System.out.println("status="+status);
				}
				%>
				
				<%if (status != null && status.length()>0) { 
					
					%>
					<p class="failure"><%=status %></p>					
					<%
				} 
				
			%>
				<div class="student_profile row">
					<div class="program_list">
						<ol>
							<li><label>Certificate in Basic Computer</label><input
								type="checkbox" name="program1" id="program1" onchange="checkBox(this.id)"/>
								<input type="hidden" name="dept1" id="dept1" value="deptid28" />
								<input type="hidden" name="CIDTS1" id="CIDTS1" value="CBCC" />
								</li>
							<li><label>Certificate in Office Automation</label><input
								type="checkbox" name="program2" id="program2" onchange="checkBox(this.id)"/>
								<input type="hidden" name="dept2" id="dept2" value="deptid29" />
								<input type="hidden" name="CIDTS2" id="CIDTS2" value="CIOA" />
								</li>
							<li><label>Certificate in Programing Techniques</label><input
								type="checkbox" name="program3" id="program3" onchange="checkBox(this.id)"/>
								<input type="hidden" name="dept3" id="dept3" value="deptid30" />
								<input type="hidden" name="CIDTS3" id="CIDTS3" value="CIPT" />
								</li>
							<li><label>Certificate in Computer Application</label><input
								type="checkbox" name="program4" id="program4" onchange="checkBox(this.id)"/>
								<input type="hidden" name="dept4" id="dept4" value="deptid31" />
								<input type="hidden" name="CIDTS4" id="CIDTS4" value="CICA" />
								</li>
							<li><label>Certificate in Multimedia System</label><input
								type="checkbox" name="program5" id="program5" onchange="checkBox(this.id)"/>
								<input type="hidden" name="dept5" id="dept5" value="deptid32" />
								<input type="hidden" name="CIDTS5" id="CIDTS5" value="CIMS" />
								</li>
							<li><label>Certificate in Communication</label><input
								type="checkbox" name="program6" id="program6" onchange="checkBox(this.id)"/>
								<input type="hidden" name="dept6" id="dept6" value="deptid33" />
								<input type="hidden" name="CIDTS6" id="CIDTS6" value="CICC" />
								</li>
						</ol>
					</div>
					<div class="profile_photo">
						<img src="../Images/default.png" class="image" />
					</div>
					<div class="upload-button1" id="profile_image">
						<input type="file" id="upload" value="Upload"
							style="padding-top: 5px; width: 74px; padding-left: 0px;">
							<span style="margin-left: 25px;"><a href=""	id="upload_link">Edit Image</a></span> </input>
					</div>
					<h1>Student Details</h1>

					<div class="form_inner">
						<div class="student_form_left form_input">
							<ul>
								<li><label>Name of the Applicant*</label><input type="text"
									class="auto" id="name1" name="name" maxlength="50"  title="Should not contain special characters" value=""
									placeholder="fullname" required/></li>
								<li><label>Email*</label><input type="text"
									placeholder="Email" id="email" maxlength="50" name="email" title="Should be a valid email" value="" required/></li>
								<li><label>Mobile No*</label><input type="text"
									placeholder="Mobile No" id="mobile" name="mobile" minlength=10 maxlength="10" title="Should contain 10 numbers" onkeypress='return event.charCode >= 48 && event.charCode <= 57'
									value="" required/></li>
								<li><label>Date Of Birth*</label><input type="text"
									placeholder="DD / MM /YYYY" id="dob" name="dob" title="Should be DD/MM/YYYY"
									value="" maxlength=10 required /></li>
								<li><label>Nationality*</label><input type="text"
									placeholder="nationality" id="nationality" name="nationality"  title="Should not contain special characters"
									value="" required/></li>
								<li><label>Caste*</label> <select name="caste" value="" required>
										<option value="">Select your Caste*</option>
										<option>MBC</option>
										<option>BC</option>
										<option>SC</option>
										<option>ST</option>
										<option>OBC</option>
										<option>Others</option>
								</select></li>
								<li><label>District*</label><input type="text"
									id="district1" name="district1" maxlength="25" placeholder="District" title="Enter your district*" required/></li>
								<li><label>Pincode*</label><input type="text" id="pincode1"
									name="pincode1" placeholder="Pincode"  maxlength="10" title="Enter valid pincode" onkeypress='return event.charCode >= 48 && event.charCode <= 57' value="" required/></li>
							</ul>
						</div>
						<div class="student_form_right form_input">
							<ul>
								<li><label>Name of the Parent / Guardian*</label><input
									type="text" placeholder="Name" maxlength="50" id="parent_name"
									name="parent_name"  value="" title="Enter your Parent / Guardian name*" required/></li>
								<li><label>Aadhaar Number</label><input type="text" name="adhar1" id="adhar1"
									placeholder="Aadhaar Number" id="Aadhaar_Number" maxlength="12" onkeypress='return event.charCode >= 48 && event.charCode <= 57'  title="Should be valid adhar number*"
									name="Aadhaar_Number" value="" /></li>
								<li><label>Phone No*</label><input type="text"
									placeholder="Phone No" id="Phone_No" name="Phone_No"  title="Should be valid phone number*" minlength=10 maxlength="10" onkeypress='return event.charCode >= 48 && event.charCode <= 57'
									 required /></li>
							
								<li class="extra_space"><label>Gender</label><input
									type="radio" name="gender" value="male" 
									onClick="javascript:makeChoice()" "/><span> Male</span>
									</td>
									<td>
										<input type="radio" name="gender" value="female"
										value="female"  onClick="javascript:makeChoice()" />
										<span>Female</span> 
								</td>
									<td>
										 <input type="radio" value="" name="gender" name="gender"
										value="other"  onClick="javascript:makeChoice()" /><span>
										Others</span></li>
								       

								<li><label>Mother Tongue*</label> <select
									name="Mother_Tongue" required>
										<option value="">Select your Mother Tongue*</option>
										<option value="Tamil" >Tamil</option>
										<option value="Telugu">Telugu</option>
										<option value="Kannada">Kannada</option>
										<option value="English">English</option>
										<option value="Malayalam">Malayalam</option>
										<option value="Hindi">Hindi</option>
								</select></li>
								<li><label>Address Of Comunication*</label> <textarea
										type="text" placeholder="address" maxlength="200"  title="Should be valid address*" name="address" required>  </textarea></li>

							</ul>
						</div>
					</div>
					<h1>student education details</h1>
					<div class="student_form_left form_input">
						<ul>
							<li><label>Name of the School*</label><input type="text"  id="schoolName"
								placeholder="Name" title="Should not contain special characters" name="school" minlength="5" maxlength="100" value="" required/></li>
							<li><label>District*</label><input type="text" id="district_edu"
								placeholder="District"  title="Should not contain special characters" maxlength="25" name="district" value="" required/></li>
							<li><label>Pincode*</label><input type="text"
								placeholder="Pincode"  title="Enter valid pincode" name="pincode" minlength=6 maxlength="10" onkeypress='return event.charCode >= 48 && event.charCode <= 57' value="" required/></li>
							<li><label>Class Currently Studying*</label><input
								type="text" id="class_study" maxlength="10" placeholder="Class Currentely Studying"  
								name="class_study" title="Should not contain special characters" value="" required /></li>
						</ul>
					</div>
					<div class="student_form_right form_input">
						<ul>
							<li><label>Address Of School*</label> <textarea
									name="address_school" title="Should be a valid address*" maxlength="200" placeholder="Address" required> </textarea></li>
							<li><label>Admission No*</label><input type="text" title="Admission No should be valid"
								placeholder="Admission No" maxlength="15"   name="admission_no" onkeypress='return event.charCode >= 48 && event.charCode <= 57' value="" required/></li>
						</ul>
					</div>
					<%-- <div class="opt_input_box"> 
                <center>
                <% if(OTPStatus.trim().equalsIgnoreCase("Generated"))
                        {
                        	%>
                        	
                            <input type="text" id="OTPText"	name="OTPText" maxlength="4" title="Enter your OTP*" class="auto" value="" placeholder="Enter your OTP*" class="input_pincode" />
                            
                       <%  }
                        %>
                 </center>
                 </div> --%>
					<div class="button_area row">
						<input type="submit" value="submit" onclick="return checkChkBox()"/>
					</div>
			</form>
		</div>
	</div>
</body>

<script type="text/javascript">

function checkChkBox()
{
	
	if ($('#program1').is(":checked") || $('#program2').is(":checked") || $('#program3').is(":checked")   
			|| $('#program4').is(":checked") || $('#program5').is(":checked") || $('#program6').is(":checked"))
	{  
		return true; 
	} 

	return false;
}
	$(document).ready(function() {
		
		$('#dob').bind('keyup','keydown', function(event) {
			
			   var inputLength = event.target.value.length;
			    if (event.keyCode != 8){
			      if(inputLength === 2 || inputLength === 5){
			        var thisVal = event.target.value;
			        thisVal += '/';
			        $(event.target).val(thisVal);
			     }
			    }
			  })
			  
			  document.getElementById("adhar1").disabled = true;
			  
		$(".autohide").delay(5000).fadeOut("slow");
		$(".reset_email_login").hide();
		
		$(".reset_in_mail").click(function() {
			$(".reset_email_login").toggle();
			});
		
		$(".reset_mail_close a").click(function() {
			$(".reset_email_login").hide();
		});
		
		$('.flag_selecting_place').css('cursor', 'default');
		if (sessionStorage.length === 1) {
			$('.flag_selecting_place').html(sessionStorage.getItem("country"));
		} else {
			$('.flag_selecting_place').html(content);
		}
		$(".enrollment_flash_msg").css( "display", "inline" ).fadeOut( 5000 ); 	
		$(".enrollment_failed_flash_msg").css( "display", "inline" ).fadeOut( 5000 ); 
		
		
	});
	
	function checkBox(chkID)
	 {
	  
	  $("#program1").prop('checked', false);
	  $("#program2").prop('checked', false);
	  $("#program3").prop('checked', false);
	  $("#program4").prop('checked', false);
	  $("#program5").prop('checked', false);
	  $("#program6").prop('checked', false);
	  
	  $("#"+chkID).prop('checked', true);
	 }
</script>



<script>

	$(document)
			.ready(
					function() {
						$.validator.addMethod("anyDate",
					            function(value, element) {
					        
					                //return value.match(/^(0?[1-9]|[12][0-9]|3[0-1])[/., -](0?[1-9]|1[0-2])[/., -](19|20)?\d{2}$/);
					          //return value.match(/^(0?[1-9]|[12][0-9]|3[0-1])[/., -](0?[1-9]|1[0-2])[/., -](19|20)?\d{2}$/);
					          var dateRegex = /^(?=\d)(?:(?:31(?!.(?:0?[2469]|11))|(?:30|29)(?!.0?2)|29(?=.0?2.(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(?:\x20|$))|(?:2[0-8]|1\d|0?[1-9]))([-.\/])(?:1[012]|0?[1-9])\1(?:1[6-9]|[2-9]\d)?\d\d(?:(?=\x20\d)\x20|$))?(((0?[1-9]|1[012])(:[0-5]\d){0,2}(\x20[AP]M))|([01]\d|2[0-3])(:[0-5]\d){1,2})?$/;
					                /* if(value.match(/^(0?[1-9]|[12][0-9]|3[01])[\/](0?[1-9]|1[012])[\/]\d{4}$/)) */
					                if(value.match(dateRegex))
					                {
					                 
					                 var dsplit = value.split("/");
					                 var d=new Date(dsplit[2],dsplit[1]-1,dsplit[1]);
					                 var curDate = new Date();
					                    var inputDate = d;
					                    if (inputDate < curDate)
					                     return true;
					                 }
					              
					                 return false;
					            },
					            "Please enter a date in the format!"
					        );
						$.validator.addMethod("pincodeformat", function(value,
								element) {
							return value
									.match(/^[a-z0-9]+[a-z0-9-]+[a-z0-9]+$/);
						}, "Please enter a pincode in the format!");
						$.validator.addMethod("lettersonly", function(value,
								element) {
							return this.optional(element)
									|| /^[a-zA-Z\s]+$/.test(value);
						}, "Letters only please");
						$.validator
								.addMethod(
										"myCustomRule",
										function(value, element) {
											return /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
													.test(value);
										},
										"Custom message for this rule Email id");

						$("#userForm").validate({
							rules : {
								name : {
									required : true,
									lettersonly : true

								},
								email : {
									required : true,
									email : true,
									myCustomRule : true

								},
								mobile : {
									required : true,
									number : true,
									minlength : 10,
									maxlength : 10

								},
								adhar1 : {
                                    required : true,
                                    number : true,
                                    minlength : 12,
                                    maxlength : 12
 
                                },
								dob : {
									required : true,
									anyDate : true

								},
								nationality : {
									required : true

								},
								caste : {
									required : true

								},
								admission_no : {
									required : true,
								//number: true

								},
								parent_name : {
									required : true,
									lettersonly : true

								},
								district : {
									required : true,
									lettersonly : true

								},
								pincode : {
									required : true,
									//pincodeformat:true,
									number : true,
									minlength : 4,
									maxlength : 12

								},

								Phone_No : {
									required : true,
									number : true,
									minlength : 10,
									maxlength : 10

								},

								Mother_Tongue : {
									required : true

								},
								address : {
									required : true,
									maxlength : 200

								},
								school : {
									required : true,
									lettersonly : true

								},
								class_study : {
									required : true

								},
								address_school : {
									required : true,
									maxlength : 200

								},

								district1 : {
									required : true,
									lettersonly : true

								},
								pincode1 : {
									required : true,
									//pincodeformat:true,
									minlength : 4,
									number : true,
									maxlength : 12

								},
								gender : {
									required : true,
								},

								/* program : {
									required : true

								},
								program1 : {
									required : true

								}, */
							},
							messages : {
								name : {
									required : "",
									lettersonly : ""
								},
								parent_name : {
									required : "",
									lettersonly : ""
								},

								email : {
									required : "",
									email : "",
									myCustomRule : ""
								},
								mobile : {
									required : "",
									number : "",
									minlength : "",
									maxlength : ""
								},
								adhar1 : {
                                    required : "",
                                    number : "",
                                    minlength : "",
                                    maxlength : ""
                                },
								dob : {
									required : "",
									anyDate : ""
								},
								district1 : {
									required : "",
									lettersonly : ""
								},
								district : {
									required : "",
									lettersonly : ""
								},
								address : {
									required : "",
									maxlength : ""
								},
								address_school : {
									required : "",
									maxlength : ""
								},
								Aadhaar_Number : {
									required : "",
									number : "",
									minlength : "",
									maxlength : ""

								},

								school : {
									required : "",
									lettersonly : ""

								},
								pincode : {
									required:"",
									//pincodeformat:"",
									minlength : "",
									number : "",
									maxlength : ""
								},
								pincode1 : {
									required:"",
									//pincodeformat:"",
									minlength : "",
									number : "",
									maxlength : ""
								},
								Phone_No : {
									required : "",
									number : "",
									minlength : "",
									maxlength : ""

								},
								class_study:{
									required : "",
									
								},
								admission_no:{
									required : "",
								}

							}
						});
					});
</script>
<script>
$(".enrollment_registration").css("display", "inline").fadeOut(5000);
$(".enrollment_login_success_msg").css("display", "inline").fadeOut(4000);
$(".enrollment_failed_msg").css("display", "inline").fadeOut(5000);
</script>
<script>
	$(function() {
		$('#profile_image').change(function(e) {

			var img = URL.createObjectURL(e.target.files[0]);
			$('.image').attr('src', img);
		});
	});
</script>

<script>
	$(function() {
		$("#upload_link").on('click', function(e) {
			e.preventDefault();
			$("#upload:hidden").trigger('click');
		});
	});
</script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$('.flag_selecting_place').css('cursor', 'default');
				if (sessionStorage.length === 1) {
					$('.flag_selecting_place').html(
							sessionStorage.getItem("country"));
				} else {

					 $('.flag_selecting_place').html(
							'<img src="../images/flag_img.png"  /><span class="selected_text">'
									+ India + '</span>'); 
				}

			});
</script>
<script>
$("input,textarea").on("keypress", function(e) {
    if (e.which === 32 && !this.value.length)
        e.preventDefault();
});
$(function() {
	$('#district1')
			.keydown(
					function(e) {
						if (e.altKey) {
							e.preventDefault();
						} else {
							var key = e.keyCode;
							if (!((key == 8) || (key == 9) || (key == 32)
									|| (key == 46)
									|| (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
								e.preventDefault();
							}
						}
					});
});

$(function() {
	document.getElementById("adhar1").disabled = true;
	$('#parent_name,#name1,#nationality')
			.keydown(
					function(e) {
						if (e.altKey) {
							e.preventDefault();
						} else {
							var key = e.keyCode;
							if (!((key == 8) || (key == 9) || (key == 32)
									|| (key == 46)
									|| (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
								e.preventDefault();
							}
						}
					});
	$('#schoolName')
	.keydown(
			function(e) {
				if (e.altKey) {
					e.preventDefault();
				} else {
					var key = e.keyCode;
					if (!((key == 8) || (key == 9) || (key == 32)
							|| (key == 46)
							|| (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
						e.preventDefault();
					}
				}
			});
	$('#district_edu')
	.keydown(
			function(e) {
				if (e.altKey) {
					e.preventDefault();
				} else {
					var key = e.keyCode;
					if (!((key == 8) || (key == 9) || (key == 32)
							|| (key == 46)
							|| (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
						e.preventDefault();
					}
				}
			});
	/* $('#class_study')
	.keydown(
			function(e) {
				if (e.altKey) {
					e.preventDefault();
				} else {
					var key = e.keyCode;
					if (!((key == 8) || (key == 9) || (key == 32)
							|| (key == 46)
							|| (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
						e.preventDefault();
					}
				}
			}); */
});

</script>
<style>
/* validation code */
input.error {
	border: 1px solid red !important;
}

textarea.error {
	border: 1px solid red !important;
}

select.error {
	border: 1px solid red !important;
}

input[type="checkbox"].error {
	border: 1px solid red !important;
}

input[type="checkbox"].error {
	border: 1px solid red !important;
}

input[type="radio"].error {
	border: 1px solid red !important;
}

.form_input ul li input[type="radio"].error {
	/*outline: 1px solid red !important;*/
	box-shadow: 0px 0px 4px red;
}

.image {
	width: 150px;
	height: 150px
}

.upload-button1 {
	width: 181px;
	font-size: 12px;
	color: #fff;
	float: right;
}

#upload_link {
	text-decoration: none;
	text-decoration: none;
	color: #fff;
	text-align: center;
	padding-left: 52px;
	font-weight: bold;
}

#upload {
	display: none
}
.auto {
text-transform: capitalize;
}
#dob-error {
display:none!important;
}
#pincode-error {
display:none!important;
}
.enrollment_body #OTPText {
    background: #c4d4da none repeat scroll 0 0;
    border: medium none;
    height: 25px;
    padding: 0 10px 0 10px;
    color: #000;
    font-size: 12px;
    width: 160px;
    position: absolute;
    bottom:-592px;
    left: 0px;
    right: 0px;
    margin: auto;
}
.header1
{
 width:100%;
 float:left;
 background-color: #fff;
 border-bottom: 3px solid #999;
}
#left {
    float: left;
    padding-left: 15px;
}
#center {
    display: inline-block;
    margin: 0 auto;
    padding-left: 100px;
}
#right {
    float: right;
    padding-top: 13px;
    padding-right: 30px;
}
#right img {
max-width:101px;
height:47px;
}
</style>



</html>
