/*function validatePostSession(){
	//alert("hello");
	var sname=document.getElementById("sessionname").value;
	if(sname == ""){
		//alert("Select Session Name");
		return false;
	}
	else {
		document.postsession.action="/KODE_DEV/ControllerServlet/PostPoneServlet";
		document.postsession.submit();
	}
}*/
function validatePostPoneSession(){
	//alert("hello");
	var sessionName=document.getElementById("sessionname").value;
	var startTime=document.getElementById("sSTime").value;
	var endTime=document.getElementById("sETime").value;
	//alert(startTime);
	if(sessionName == "" || sessionName == null){
		alert("Select Session Name");
		return false;
	}
	else if(startTime > endTime){
		alert("End Time is Greater Than Start Time");
		return false;
	}
	else {
		document.postsession.action="/KODE_DEV/ControllerServlet/PostPoneServlet";
		document.postsession.submit();
	}
}