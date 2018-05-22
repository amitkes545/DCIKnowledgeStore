function Validatecustomer() {
	
	//alert("hello");
	var cid=document.getElementById("cid").value;
	var cname=document.getElementById("cname").value;
	
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var email=document.getElementById("email").value;
	var addres=document.getElementById("address").value;
	var phone=document.getElementById("phone").value;
	var privilege=document.getElementById("privilege").value;
	var orgid=document.getElementById("orgid").value;
	//alert("cname");
	if(cname == ""){
		alert("Enter Name");
		document.getElementById("cname").focus();
		return false;
	} else if(cid == ""){
		alert("Enter ID");
		document.getElementById("cid").focus();
		return false;
	}
	
	else if (email == "") {
		alert("Enter Email ID ");
		document.getElementById("email").focus();
	    return false;
   } else if (!(email.match(mailformat))) {
		alert("Enter valid email ID");
		document.getElementById("email").value="";
		document.getElementById("email").focus();
		return false;
  }
	else if(addres ==""){
		alert("Enter Address");
		document.getElementById("address").focus();
		return false;
	} else if(phone == ""){
		alert("Enter Mobile Number");
		document.getElementById("phone").focus();
		return false;
	} else if(phone.length < 10){
		alert("Enter valid 10 digit mobile number");
		document.getElementById("phone").focus();
		return false;
	}
	else if(privilege == ""){
		alert("Select Privilege");
		document.getElementById("privilege").focus();
		return false;
	} 
	else if(orgid == ""){
		alert("Select Institute ID");
		document.getElementById("orgid").focus();
		return false;
		}
	      else {
		
		document.customerform.action="/KODE_DEV/ControllerServlet/CreateCustomerServlet";
		document.customerform.submit();
	}
}
function runScript(e) {
	if (e.keyCode == 13) {
		login();
	}
}


