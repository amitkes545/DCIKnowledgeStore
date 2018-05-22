function validate() {

	 var uid = document.getElementById("logid").value.trim();
	//var uid = document.loginform['logid'].value;
      
	var pwd = document.getElementById("pwdid").value.trim();
	//var pwd = document.loginform['pwdid'].value;

	if (uid == "") {

		alert(' Please enter user ID');
		// document.getElementById('logid').focuse();
		return false;

	} else if (pwd == "") {
		alert(' Please enter password');
		return false;
	
	}
	else {
		
            document.loginform.action="/KODE_DEV/ControllerServlet/LoginServlet";
			document.loginform.submit();
		
}
	
}


function runScript(e) {
	if (e.keyCode == 13) {
		login();


}
}