function Validateadmin() {
	//alert("hello");
	var adminid = document.getElementById('adminid').value;
	var adminname = document.getElementById('adminname').value;
	
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var email = document.getElementById('email').value;
	var address = document.getElementById('address').value;
	var phone = document.getElementById('phone').value;
	if (adminname == "") {
		alert("Enter Name");
		document.getElementById("adminname").focus();
		return false;
	} else if (adminid == "") {
		alert("Enter ID");
		document.getElementById("adminid").focus();
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
	} else if (address == "") {
		alert("Enter Address");
		document.getElementById("address").focus();
		return false;
	} else if (phone == "") {
		alert("Enter Mobile Number");
		document.getElementById("phone").focus();
		return false;
	} else if(phone.length < 10){
		alert("Enter valid 10 digit mobile number");
		document.getElementById("phone").focus();
		return false;
	}

	 else {
		//alert("hello");
		document.adminform.action="/KODE_DEV/ControllerServlet/AdminServlet";
		document.adminform.submit();
	}
}
function runScript(e) {
	if (e.keyCode == 13) {
		Validateadmin();
	}
}