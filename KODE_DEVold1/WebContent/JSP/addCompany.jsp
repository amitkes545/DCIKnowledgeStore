<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.TreeMap" %>

<%@ page import="java.sql.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Company</title>
<link rel="shortcut icon" href="../images/payroll.jpg" />
<link rel="stylesheet" type="text/css"  href="../CSS/stylesheet.css"/>
<script language="javascript" type="text/javascript" src="../JS/date_picker_css.js"></script>
<script language="javascript" type="text/javascript"src="../JS/datetimepicker_css.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">
<script type="text/javascript" src="../JS/formValid.js"></script>

<style>
  .thumb {
    height: 100px;
    border: 1px solid #000;
    margin: 10px 5px 0 0;
  }
</style>
<style type="text/css">
 
.file-wrapper {
    position: relative;
    display: inline-block;
    overflow: hidden;
    cursor: pointer;
}
.file-wrapper input {
    position: absolute;
    top: 0;
    right: 0;
    filter: alpha(opacity=1);
    opacity: 0.01;
    -moz-opacity: 0.01;
    cursor: pointer;
}
.file-wrapper .button {
    color: #fff;
    background: #545454;
    padding: 4px 18px;
    margin-right: 5px;  
    border-radius: 5px;
    -moz-border-radius: 5px;
    -webkit-border-radius: 5px;
    display: inline-block;
    font-weight: bold;
    cursor: pointer;
}
.file-holder{
    color: #000;
}
</style>
<script type="text/javascript">
$(
		function() 
		{
		    $('.date-picker').datepicker( 
		    {
		        changeMonth: true,
		        changeYear: true,
		        showButtonPanel: true,
		        dateFormat: 'MM yy',
		        onClose: function(dateText, inst) 
		        {
		            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
		            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
		           $(this).datepicker('setDate', new Date(year, month, 1));
		             }
		    });
		}
		);



function addDetails()
      	{	
      		if(add_comp_valid())
      		{
      			if(validateFile()){
      			//alert("in add details");
      			document.addCompany.action="/PayrollManagementSystem/Controller/addCompany";
      			document.addCompany.submit();
      			}
      		}
      	}
function validateFile() 
{
	//alert("in functionval");
    var allowedExtension = ['jpeg', 'jpg', 'png', 'gif', 'bmp'];
    var fileExtension = document.getElementById('files').value.split('.').pop().toLowerCase();
  //  alert("=="+fileExtension);
   
    var isValidFile = false;

        for(var index in allowedExtension) {

            if(fileExtension === allowedExtension[index]) {
                return true; 
                //break;
            }
        }
		if(fileExtension!="")
			{
        if(!isValidFile) {
            alert('Allowed Extensions are : *.' + allowedExtension.join(', *.'));
        }
			}
		else 
			return true;

        return false;
}
      	function add_comp_valid()
      	{
      		//alert("in valid");
    		var comp_name = document.addCompany.comp_name.value;
    		var start =  document.addCompany.start.value;
    		var owner_name=  document.addCompany.owner_name.value;
    		var address =  document.addCompany.address.value;
    		var comp_type =  document.addCompany.comp_type.value;
    		//var numericExpression = /^[0-9]/;
    		if(notEmpty(comp_name,"Company Name should not be empty")){
    			if(notEmpty(start,"Start Date should not be empty")){
    				if(notEmpty(owner_name,"Owner Name should not be empty")){
    					if(notEmpty(address,"Address Should not be empty")){
    						if(notEmpty(comp_type,"Company Type should not be empty")){
    			    			
    						return true;	
    					
    						}
        					
        				}			
        			}
        			
        		}	
    		}return false;
    	}

      	function refreshparent()
      	{
      		//alert("refreshing");
      		window.parent.parent.topFrame.location="../JSP/header.jsp";
      		window.parent.payroll_company.location="../JSP/mng_side_comp.jsp";
      	}
      	
</script>  
<% 

String comp_name=(String) session.getAttribute("company_name");

			String message=request.getParameter("message");
			
			if(message==null)
			{
				message="";
			}
			else if (message.equals("success"))
			{
				comp_name=(String) session.getAttribute("company_name");
				message="Successfully Created";
			}
			else if (message.equals("error"))
			{
				message="This Company Name already exist";
			}
			
	%>
</head>
<body onload="refreshparent();">
<center>
<form name ="addCompany" method="post" 
enctype="multipart/form-data" >

 <center><h3 style ="color:red"><%=message %></h3></center>
 <h3>Add Company Details</h3>
	<div class="privilagemain">
			
		<div class="privilageinnerdiv">
<div class="privilageleft" style="text-align:left;">Company Name *</div>
<div class="privlageright"><Input name="comp_name" id="comp_name" value="<%=comp_name %>" readonly="readonly" style="width:205px; height:28px; border:1px solid #d2d4d4; border-radius:5px 5px 5px 5px; background:#eceeee; ">
</div>
        </div>

<div class="privilageinnerdiv">
<div class="privilageleft" style="text-align:left;">Started At *</div>
<div class="privlageright">
<a href="javascript:DatePick('start','yyyyMMdd')"><img src="../images/calendar_icon.gif" width="16" height="16" border="0" alt="Pick a date"></a>
<input type="text" name="start" id="start" readonly="readonly" style="height:28px; width:155px; border:1px solid #d2d4d4; border-radius:5px 5px 5px 5px; background:#eceeee;">
</div>
</div>

<div class="privilageinnerdiv">
<div class="privilageleft" style="text-align:left;">Owner Name *</div>
<div class="privlageright"><input name="owner_name" id="owner_name" type="text" style="width:205px; height:28px; border:1px solid #d2d4d4; border-radius:5px 5px 5px 5px; background:#eceeee; "/></div>
</div>
		
<div class="privilageinnerdiv">
<div class="privilageleft" style="text-align:left;">Address *</div>
<div class="privlageright"><input name="address" id="address" type="text" style="width:205px; height:28px; border:1px solid #d2d4d4; border-radius:5px 5px 5px 5px; background:#eceeee; "/></div>
</div>	
		
<div class="privilageinnerdiv">
<div class="privilageleft" style="text-align:left;">Description of Company *</div>
<div class="privlageright"><input name="comp_type" id="comp_type" type="text" style="width:205px; height:28px; border:1px solid #d2d4d4; border-radius:5px 5px 5px 5px; background:#eceeee; "/></div>
</div>	
		
<div class="privilageinnerdiv">
<div class="privilageleft" style="text-align:left;">Image of Company </div>
<div class="privlageright">

 
    <div class="file-wrapper">
    <input type="file" id="files" name="files" accept="image/*" />
     <span class="button">Choose image</span>
</div>
</div>
<div class="privilageleft">
 <output id="list"></output>
 </div>
</div>

<br/><br/>

<div class="privilageinnerdiv">
<div class="privilageleft">&nbsp;</div>
<div class="privlageright">
<div class="submitbutton"><input type="button" value="" class="submitbtn" onclick="addDetails()" /></div>


</div>
</div>
</div> 		
 		
</form>
</center>
<script>
if (window.File && window.FileReader && window.FileList && window.Blob) {
  function handleFileSelect(evt) {
    var files = evt.target.files; // FileList object

    // Loop through the FileList and render image files as thumbnails.
    for (var i = 0, f; f = files[i]; i++) {

      // Only process image files.
      if (!f.type.match('image.*')) {
        continue;
      }

      var reader = new FileReader();

      // Closure to capture the file information.
      reader.onload = (function(theFile) {
        return function(e) {
          // Render thumbnail.
          var span = document.createElement('span');
          span.innerHTML = ['<img class="thumb" src="', e.target.result,
                            '" title="', escape(theFile.name), '"/>'].join('');

          var list=document.getElementById('list');
          //alert(list.childNodes.length);
          if(list.childNodes.length>0)
        	  {
        	  list.replaceChild(span, list.childNodes[0]);
        	  //list.insertBefore(span, null);
        	  }
          else{
          list.insertBefore(span, null);
          //document.getElementById('list').insertBefore(span, null);
          }
                 };
      })(f);

      // Read in the image file as a data URL.
      reader.readAsDataURL(f);
    }
  }

  document.getElementById('files').addEventListener('change', handleFileSelect, false);
} else {
	  alert('The File APIs are not fully supported in this browser.');
	}
</script>

</body>
</html>