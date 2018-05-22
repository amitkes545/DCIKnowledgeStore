function ValidateCreateSession(){
	//alert("hello");
	var category=document.getElementById("category").value;
	var sessionname=document.getElementById("sessionName").value;
	var startTime=document.getElementById("sSTime").value;
	var endTime=document.getElementById("sETime").value;
	var costofsession=document.getElementById("costOfSession").value;
		if(category == ""){
		alert("Select category of session");
		document.getElementById("category").focus();
		return false;
	} else if(sessionname == ""){
		alert("Enter sessionname ");
		document.getElementById("sessionName").focus();
		return false;
	} 
	else if(startTime == ""){
		alert("Select session start time");
		document.getElementById("sSTime").focus();
		return false;
	}
	else if(endTime == ""){
		alert("Select Session End Time");
		document.getElementById("sETime").focus();
		return false;
	} else if (startTime > endTime) {

        alert("End time is greater than start time");
        document.getElementById("sETime").value="";
        document.getElementById("sETime").focus();
        return false;
	}
	else if(costofsession == ""){
		alert("Enter CastOfSession");
		document.getElementById("costOfSession").focus();
		return false;
	} 
	else {
		
		document.sessionform.action="/KODE_DEV/ControllerServlet/CreateSessionServlet";
		document.sessionform.submit();
	}
}
