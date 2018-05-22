function valid(){
	var ksid=document.getElementById("ksid").value;
	if(ksid=="default"){
		alert("Please select KS ID");
		return false;
	}
	return true;
}
