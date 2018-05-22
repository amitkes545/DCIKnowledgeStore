function Validatesuperadmin() {
	//alert("hello");
	var sid=document.getElementById("sid").value;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var sname=document.getElementById("sname").value;
	var pwd=document.getElementById("pwd").value;
	var cfpwd=document.getElementById("cfpwd").value;
	var email=document.getElementById("email").value;
	var address=document.getElementById("address").value;
	var phone=document.getElementById("phone").value;
	 var phoneno = /^\d{10}$/;  
	var privilege=document.getElementById("privilege").value;
	var orgid=document.getElementById("orgid").value;
	//alert(privilege);
	//alert(orgid);
	
	if (sname == "") {
		alert(" Enter Name ");
		document.getElementById("sname").focus();
		return false;
	} 
		else if (sid == "") {
		alert("Enter ID");
		document.getElementById("sid").focus();
		return false;
	} 
		else if (pwd == "") {
		alert("Enter Password");
		document.getElementById("pwd").focus();
	     return false;
	} 
		else if (cfpwd == "") {
			alert("Enter Conform Password");
			document.getElementById("cfpwd").focus();
		     return false;
		} else if (pwd!=cfpwd) {
			alert("Conform Password should match with Password");
			document.getElementById("cfpwd").value="";
			document.getElementById("cfpwd").focus();
		     return false;
		} 
		else if (email == "") {
		alert("Enter Email ID");
		document.getElementById("email").focus();
	    return false;
   } else if (!(email.match(mailformat))) {
		alert("Enter valid email ID");
		document.getElementById("email").focus();
		return false;
  }else if(address == ""){
	  alert("Enter address");
	  document.getElementById("address").focus();
	  return false;
  }
		else if (phone == "") {
		alert("Enter Mobile number");
		document.getElementById("phone").focus();
		return false;
	} else if(phone.length < 11){
		alert("Enter valid 10 digit mobile number");
		document.getElementById("phone").focus();
		return false;
	}
		
	else if(privilege == ""){
		alert("Select Privilege");
		document.getElementById("privilege").focus();
		return false;
	} else if(orgid == ""){
		alert("Select Institute ID");
		document.getElementById("orgid").focus();
		return false;	
	}
	else {
		document.superadminform.action="/KODE_DEV/ControllerServlet/CreateSuperAdminServlet";
		document.superadminform.submit();
	}
}
function runScript(e) {
	if (e.keyCode == 13) {
		login();
	}
	
}

	