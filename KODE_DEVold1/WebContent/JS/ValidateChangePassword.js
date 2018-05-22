function Validatepwd()
{
	//alert("hello");
	var oldpwd1=document.getElementById("oldpwd").value;
	var Npwd=document.getElementById("npwd").value;
	var Cnpwd=document.getElementById("cnpwd").value;
	//alert("password"+pwd);
	
	if(oldpwd1 == ""){
		alert("Enter old password");
		document.getElementById("cpwd").focus();
		return false;
	}
	else if(Npwd== ""){
		alert("Enter your new password");
		document.getElementById("npwd").focus();
		return false;
	}
	else if(Cnpwd==""){
		alert("Conform password");
		document.getElementById("cnpwd").focus();
		return false;
	}
	else if(!(Npwd==Cnpwd)){
		alert("Password mismatched try again");
		document.getElementById("cnpwd").focus();
		return false;
	}
	else
	{
		document.passwordform.action="/KODE_DEV/ControllerServlet/ChangePasswordServlet";
		document.passwordform.submit();
	
	}
}