function validFLib() {
	/*var ksid = document.getElementById("ksid").value;
	if (ksid == "default" || ksid == "") {
		alert("Please select know Store ID !");
		return false;
	}*/
	var libname = document.getElementById("libname").value;
	if (libname == "") {
		alert("Please enter library name !");
		return false;
	}
	var libesize = document.getElementById("libesize").value;
	if (libesize == "") {
		alert("Please enter library size !");
		return false;
	} else if (isNaN(libesize) || libesize.indexOf(" ") != -1) {
		alert("Please enter digits only");
		return false;

	} else if (libesize.length > 2) {
		alert("Please enter only two digits");
		return false;

	}
	return true;
}