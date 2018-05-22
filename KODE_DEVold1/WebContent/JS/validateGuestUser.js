function validateGuestUser(){
	var userName=document.getElementById("userName").value;
	var mobile=document.getElementById("mobile").value;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var email=document.getElementById("email").value;
	var country=document.getElementById("country").value;
	var state=document.getElementById("state").value;
	var city=document.getElementById("city").value;
	var postalCode=document.getElementById("pcode").value;
	var address=document.getElementById("address").value;
	
	if(userName == null || userName == ""){
		alert("Enter user name");
		document.getElementById("userName").focus();
		return false;
	}else if(mobile == "" || mobile == null){
		  alert("Enter mobile number");
		  document.getElementById("mobile").focus();
		  return false;
	  }else if(mobile.length < 10){
			alert("Enter valid 10 digit mobile number");
			document.getElementById("mobile").focus();
			return false;
		}
	else if(email == "" || email == null){
		alert("Enter email ID");
		document.getElementById("email").focus();
		return false;
	} else if (!(email.match(mailformat))) {
		alert("Enter valid email ID");
		document.getElementById("email").value="";
		document.getElementById("email").focus();
		return false;
  }else if(country == "-1"){
		alert("Select Country");
		return false;
	}else if(state == ""){
		alert("Select State");
		return false;
	}else if(city == ""){
		alert("Select City");
		document.getElementById("city").focus();
		return false;
	}else if(postalCode == "" || postalCode == null){
		alert("Enter Postal Code");
		document.getElementById("postalCode").focus();
		return false;
	}else if(postalCode.length < 6){
		alert("Enter valid 6 digit postal code");
		document.getElementById("postalCode").focus();
		return false;
	}
	else if(address == null || address == ""){
	  alert("Enter address");
	  document.getElementById("address").focus();
	  return false;
  } else {
		document.guestUser.action="/KODE_DEV/ControllerServlet/GuestUserRegistration";
		document.guestUser.submit();
	}
}