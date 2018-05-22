function valid(){
	var ksid=document.getElementById("ksid").value;
	if(ksid=="default"){
		alert("Please select Know Store ID");
		return false;
	}
	else{
	return true;
	}
}