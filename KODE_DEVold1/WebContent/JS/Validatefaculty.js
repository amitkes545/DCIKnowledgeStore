function Validatefaculty()
{
	//alert("hello");
	var fname=document.getElementById('fname').value;
	var fid=document.getElementById('fid').value;
	
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var email=document.getElementById('email').value;
	var address=document.getElementById('address').value;
	var phone=document.getElementById('phone').value;
	
	
	if(fname=="")
		{
		  alert("Enter Name");
		  document.getElementById("fname").focus();
		  return false;
		}
	else if(fid==""){
		alert("Enter ID");
		  document.getElementById("fid").focus();
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
		
		document.facultyform.action="/KODE_DEV/ControllerServlet/FacultyServlet";
		document.facultyform.submit();
	}
	}
function runScript(e) {
	if (e.keyCode == 13) {
		Validatefaculty();
	}
}