function validNow(){
	/*var ksid=document.getElementById("ksid").value;
	if(ksid=="default"){
		alert("Please select Know Store ID");
		document.getElementById("ksid").focus();
		return false;
	}*/
	var departments=document.getElementById("departments").value;
	if(departments==""){
		alert("Please enter department name");
		document.getElementById("departments").focus();
		return false;	
	}
	var subjects=document.getElementById("subjects").value;
	if(subjects==""){
		alert("Please enter subject name");
		document.getElementById("subjects").focus();
		return false;	
	}
	var description=document.getElementById("description").value;
	if(description==""){
		alert("Please give description");
		document.getElementById("description").focus();
		return false;
	}
	var brows=document.getElementById("brows").value;
	if(brows==""){
		alert("Please choose a file");
		document.getElementById("brows").focus();
		return false;	
	}
	return true;
}