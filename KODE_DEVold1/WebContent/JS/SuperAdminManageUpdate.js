function validManage(){
	var ksid=document.getElementById("userid").value;
	if(ksid== "default"){
		alert("Please select KS ID");
		return false;
	}
	return true;
}