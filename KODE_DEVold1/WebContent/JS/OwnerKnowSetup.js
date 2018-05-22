function valid5(){
	var orgid=document.getElementById("orgid").value;
	if(orgid==null || orgid==""){
		alert("Please select Org ID ! ");
		return false;
	}
	var userid=document.getElementById("userid").value;
	if(userid==""){
		alert("Please select User ID !");
		return false;
	}
	var kssize = document.getElementById('kssize').value;
       if(kssize==""){
    	   alert("Please enter size !");
    	   return false;
   }else if(isNaN(kssize) || kssize.indexOf(" ") != -1) {
		alert("Enter Know Store size in digits only !");
		return false;
   } else if (kssize.length > 3) {
		alert("Please enter only three digits !");
		return false;
   }
	var status=document.getElementById("status").value;
	if(status=="default"){
		 alert("Please select status !");
		 return false;
   }
	return true;
}