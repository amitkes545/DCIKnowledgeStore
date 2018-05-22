 function ValidateCollaborate(){
	
	
	var deptname=document.getElementById("groupname").value;
	var facultyid=document.getElementById("studentid").value;
	
	if(facultyid == ""){
		alert("facultyid should not empty!Enter faculty id");
		return false;
	}
	/*else if(deptname === ""){
		alert("department name should not empty!Enter dept name");
		return false;
	}*/
	else {
		document.collaborateform.action="/KODE_DEV/ControllerServlet/CollaborateServlet";
		document.collaborateform.submit();
	}
}