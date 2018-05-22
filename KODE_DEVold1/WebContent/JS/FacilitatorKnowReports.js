function valid505(){
	
var libid=document.getElementById("libid").value;
	if(libid=="default" || libid==""){
		alert("Please select library ID");
		return false;
	}
	return true;
}