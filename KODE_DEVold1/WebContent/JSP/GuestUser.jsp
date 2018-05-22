<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Guest Registration Done Here</title>
<link rel="stylesheet" href="../CSS/kstyle.css"/>
<script src="../JS/jquery1.11.3.js"></script>
<script src="../JS/country.js" type="text/javascript"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> -->
<script type="text/javascript" src="../JS/validateGuestUser.js"></script> 
<!--include jQuery -->
<script src="../JS/jquery-1.8.0.min.js"
type="text/javascript"></script>
 
<!--include jQuery Validation Plugin-->
<script src="../JS/jquery.validate.min.js" type="text/javascript"></script>
 
<!--Optional: include only if you are using the extra rules in additional-methods.js -->
<script src="../JS/additional-methods.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
//applying this jquery code to text box for enter only numbers
$(document).ready(function() {
	$("#mobile").keydown(function (e) {
    	
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
/* $(function() {
$("#guestUser").validate(
	      {
	        rules:
	        {
	        	userName:
	          {
	            required: true
	          },
	          email:
	          {
	            required: true,
	            email: true
	          }
	        },
	        messages:
	        {
	        	userName:
	          {
	            required: "Please enter your name"
	          },
	          email:
	          {
	            required: "Please enter your email address."
	          }
	        }
	      });   
}); */
</script>
<script type="text/javascript">
$(document).ready(function() {
	
	    $('#signup').click(function(e) {
	
	        var isValid = true;
	
	        $('input[type="text"]').each(function() {
	
	            if ($.trim($(this).val()) == '') {
	
	                isValid = false;
	
	                $(this).css({
	
	                    "border": "1px solid red",
	
	                    "background": "#FFCECE"
	
	                });
	
	            }
	
	            else {
	
	                $(this).css({
	
	                    "border": "",
	
	                    "background": ""
	
	                });
	
	            }
	
	        });
	
	        if (isValid == false)
	
	            e.preventDefault();
	
	        else
	
	            //alert('Thank you for submitting')
	
	    });
	
	});​

</script>
<body>
<div class="main">
<div class="container">
<div class="header">
<div class="header1">
<div class="kdslogo">
<img src="../Image/kds.png"/>
</div>
<div class="kedulogo">
<img src="../Image/Keducom_logo.png" style="width:256px;margin-top: 23px"/>
</div>
</div>
<div class="header2">
<div class="smsg">
</div>
</div>
<!-- action="/KODE_DEV/ControllerServlet/GuestUserRegistration" -->
<form action="" name="guestUser" id="guestUser">
<center>
<span>Guest User Registration</span>
<table>

<tr><td>User Name</td><td>:</td>
<td><input type="text" name="userName" id="userName"></td></tr>
<tr><td>Mobile Number</td><td>:</td>
<td><input type="text" name="mobile" id="mobile" maxlength="10"></td></tr>
<tr><td>Email</td><td>:</td>
<td><input type="text" name="email" id="email"></td></tr>
<tr>
				<td> Country</td><td>:</td>
				<td><select id="country" name="country"></select></td></tr>
				<tr>
				<td> State</td><td>:</td>
				<td><select name="state" id="state"></select></td></tr>
				<br />
				<script language="javascript">
					populateCountries("country", "state");
					populateCountries("country2");
				</script>
				<tr><td>City</td><td>:</td>
				<td><input type="text" name="city" id="city"></td>
				</tr>
				<tr><td>Address</td><td>:</td>
<td><textarea cols="30" rows="5" name="address" id="address"></textarea></td></tr>
				
				<tr>
					<td>Postal Code</td><td>:</td>
					<td><input type="text" name="pcode" id="pcode" maxlength="6"></td>
				</tr>


<!-- onclick="validateGuestUser()" -->
<tr><td><input type="button" id="signup" value="sign up"></td></tr>
</table>
</center>
</form>
<!-- <div class="logbtn">
<a href="../JSP/logout_knowstore.jsp">
<p class="lgout"><img src="../Image/logout.png" style="width:30px;vertical-align: middle;"/> <span>Logout</span></p></a>
</div> -->
</div>
</div>



<div style="clear:both;"></div>
<div>
		<%-- <%@ include file="../JSP/footer.jsp" %> --%>
		</div>
		</div>
		<script src="../JS/intlTelInput.js"></script>
<script>
$("#phone").intlTelInput({
  utilsScript: "../JS/utils.js"
});
</script>
</body>
</html>