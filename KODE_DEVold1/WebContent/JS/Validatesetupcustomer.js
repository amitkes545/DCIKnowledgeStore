
function Validatesetupcustomer() {

	//alert("hi this is alert ");
	var orgid=document.getElementById("orgid").value;
	var orgName=document.getElementById("orgname").value;
	var orgType=document.getElementById("orgtype").value;
	var superAdminName=document.getElementById("sName").value;
	var superAdminId=document.getElementById("sID").value;
	var address=document.getElementById("address").value;
	var country=document.getElementById("country").value;
	var state=document.getElementById("state").value;
	var city=document.getElementById("city").value;
	var pcode=document.getElementById("pcode").value;
	var phone=document.getElementById("phone").value;
	var email=document.getElementById("email").value;
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var url=document.getElementById("url").value;
	var logo=document.getElementById("logo").value;
	var yof=document.getElementById("yof").value;
	
	if(orgid == "" || orgid == null){
		alert("Enter  ID");
		document.getElementById("orgid").focus();
		return false;
	} else if(orgName == "" || orgName == null){
		alert("Enter  Name");
		document.getElementById("orgname").focus();
		return false;
	} else if(orgType == ""){
		alert("select organization type");
		return false;
	}
	else if(superAdminName == ""){
		alert("Enter Super Admin Name");
		document.getElementById("sName").focus();
		return false;
	}else if(superAdminId == ""){
		alert("Enter Super Admin ID");
		document.getElementById("sID").focus();
		return false;
	}
	else if(address == "" || address == null){
		alert("Enter Address ");
		document.getElementById("address").focus();
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
	}else if(pcode == "" || pcode == null){
		alert("Enter Postal Code");
		document.getElementById("pcode").focus();
		return false;
	}else if(pcode.length < 6){
		alert("Enter valid 6 digit postal code");
		document.getElementById("pcode").focus();
		return false;
	}else if(email == "" || email == null){
		alert("Enter Email ID");
		document.getElementById("email").focus();
		return false;
	}else if(!(email.match(mailformat))){
		alert("Enter Valid Email ID");
		document.getElementById("email").value="";
		document.getElementById("email").focus();
		return false;
	}
	else if(phone == "" || phone == null){
		alert("Enter Mobile Number");
		document.getElementById("phone").focus();
		return false;
	}else if(phone.length < 10){
		alert("Enter valid 10 digit mobile number");
		document.getElementById("phone").focus();
		return false;
	}
	
	else if(url == "" || url == null){
		alert("Enter URL");
		document.getElementById("url").focus();
		return false;
	}else if(logo == ""){
		alert("Select Logo");
		return false;
	}else if(yof == "" || yof == null){
		alert("Enter Year Of Foundation");
		document.getElementById("yof").focus();
		return false;
	}
	else {
		document.setupform.action="/KODE_DEV/ControllerServlet/CreateSetupCustomer";
		document.setupform.submit();
	}
	
	}

