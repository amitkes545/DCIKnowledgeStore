function Validatestudent()
{
	
	
	var sname=document.getElementById('sname').value;
	var sid=document.getElementById('sid').value;
	
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var email=document.getElementById('email').value;
	var address=document.getElementById('address').value;
	var phone=document.getElementById('phone').value;
	
if(sname=="")
		{
		  alert("Enter Name");
		  document.getElementById("sname").focus();
		  return false;
		}
	else if(sid==""){
		alert("Enter ID");
		document.getElementById("sid").focus();
		return false;
	}
	
	else if(email== ""){
		alert("Enter Email ID");
		document.getElementById("email").focus();
		return false;
	} else if (!(email.match(mailformat))) {
		alert("Enter valid email ID");
		document.getElementById("email").focus();
		return false;
  }
	else if(address==""){
		alert("Enter Address");
		document.getElementById("address").focus();
		return false;
	} else if(phone==""){
		alert("Enter Mobile number");
		document.getElementById("phone").focus();
		return false;
	}else if(phone.length < 10){
		alert("Enter valid 10 digit mobile number");
		document.getElementById("phone").focus();
		return false;
	}
	
	else {
		document.studentform.action="/KODE_DEV/ControllerServlet/StudentServlet";
		document.studentform.submit();
	}
	}
function runScript(e) {
	if (e.keyCode == 13) {
		Validatestudent();
	}
}