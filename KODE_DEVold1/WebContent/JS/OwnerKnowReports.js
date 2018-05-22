function valid(){
	var ksid=document.getElementById("ksid").value;
	if(ksid=="default"||ksid==null||ksid==""){
		alert("Please select KS ID");
		return false;
	}
	return true;
}