function validFacilitator(){
	var ksid=document.getElementById("ksid").value;
	if(ksid=="default"){
		alert("Please select Know Store ID");
		return false;
	}
	var departments=document.getElementById("departments").value;
	if(departments==""){
		alert("Please enter department name");
		return false;	
	}
	var subjects=document.getElementById("subjects").value;
	if(subjects==""){
		alert("Please enter subject name");
		return false;	
	}
	var description=document.getElementById("description").value;
	if(description==""){
		alert("Please give description");
		return false;
	}
	var brows=document.getElementById("brows").value;
	if(brows==""){
		alert("Please choose a file");
		return false;	
	}
	var userid=document.getElementById("usersid").value;
	if(userid==""){
	  alert("Please select atleast one User ID")
	  return false;
	}
	return true;
}