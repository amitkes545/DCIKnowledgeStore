function valid9(){
	
	var userid=document.getElementById("userid").value;
	if(userid=="default"){
		alert("Please select user ID");
		return false;
	}
	var kssize = document.getElementById('kssize').value;
       if(kssize==""){
    	   alert("Please enter size ");
    	   return false;
   }else if(isNaN(kssize) || kssize.indexOf(" ") != -1) {
		alert("Please enter digits only");
		return false;	
   } else if (kssize.length > 3) {
		alert("Please enter only three digits");
		return false;	
   }
	var status=document.getElementById("status").value;
	if(status=="default"){
		 alert("Please Select Status");
		 return false;
   }
	return true;
}