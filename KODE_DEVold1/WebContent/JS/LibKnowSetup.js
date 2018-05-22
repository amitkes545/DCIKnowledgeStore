function valid100(){
	var lid=document.getElementById("libid").value;
	if(lid=="default" || lid==""){
		alert("Please select library ID !! ");
		return false;
	}
	return true;
}