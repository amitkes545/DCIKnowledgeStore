<%@ page errorPage="../JSP/error.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 
<%@page import="java.util.*" %> 
<%@page import="com.kds.KODE_DEV.dao.FeesModuleDao" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
<link href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/start/jquery-ui.css"
          rel="Stylesheet" type="text/css" />

<link rel="stylesheet" href="../CSS/fees_styles.css" type="text/css" />
<link rel="stylesheet" href="../CSS/design-common.css" type="text/css" />

<link rel="stylesheet" href="../CSS/notification-new.css" type="text/css" />
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>


<%
String OrgID=(String)session.getAttribute("orgid");
String userid=(String)session.getAttribute("userid");
String username=(String)session.getAttribute("username");
if(username==null)
	response.sendRedirect("../JSP/error.jsp?errmsg=Session Expired");

String FeeFailureMsg="";
if(session.getAttribute("FeeFailureMsg")!=null)
	FeeFailureMsg=(String)session.getAttribute("FeeFailureMsg");

String FeeSuccessMsg="";
if(session.getAttribute("FeeSuccessMsg")!=null)
	FeeSuccessMsg=(String)session.getAttribute("FeeSuccessMsg");

FeesModuleDao dao=new FeesModuleDao();

ArrayList<HashMap<String,String>> OrgList=dao.getAllInstitutes();
ArrayList<HashMap<String,String>> OrgFeesList=dao.getAllFeesComponentsByOder("");



%>
<script>
function onload(){
<%	String SearchVal=(String)request.getAttribute("OrgID");
		if(SearchVal!=null && SearchVal.length()>0) { 
		%>
		
		var SearchValselectBox = document.getElementById("OrgID");
		 for(var i=0;i<SearchValselectBox.options.length;i++){
				 if(SearchValselectBox.options[i].value == '<%=SearchVal%>')
				{
					 SearchValselectBox.options[i].selected=true;
		 			 i=SearchValselectBox.options.length;
				 }
			 } 
					
			<%}
		%>
}

</script>
</head>
<body>
<!-- <div class="page_wrapper"> -->
<!--<div class="header_top"><img src="../images/header_img.png" /></div>-->
	<div class="container">
		<%@include file="../JSP/headers.jsp"%>
			<div style="clear: both;"></div>
			<div> 
			<%@ include file= "../JSP/owner_menu.jsp" %>
			</div>
		<div style="clear: both;"></div>
		
    	<div class="fee_form_outer row" style="margin-top:100px;">
        	<div class="fee_form_inner">
            <h3>Institutional Fee Configuration</h3>
            	<form id="fees_form" action="/KODE_DEV/ControllerServlet/FeesModuleService?PartName=InstituteFeesConfig" method="post">
            	
            	<input type="hidden" name="instid" id="instid">
      			 <input type="hidden" name="kesfeesid" id="kesfeesid">
       		    <input type="hidden" name="institutefeesid" id="institutefeesid">
       		    
                <ul>
                	<li>
                    <label>Organization Name</label>
                    <select  name="Organization" id="OrgID" >
                    <option value="">Choose Organization name*</option>
                    <%
                    if(OrgList!=null && OrgList.size()>0)
                    {
                    	for(int i=0;i<OrgList.size();i++)
                    	{
                    		HashMap<String,String> info=OrgList.get(i);
                        	String organization_id=info.get("organization_id").toString().trim();
                        	String FullName=info.get("FullName").toString().trim();
                        	%>
                        <option value='<%= organization_id%>' > <%=FullName %> </option>	
                        	
                    <%	}
                    }
                    %>
                    </select>
                    </li>
                    <li>
                    <label>Fee ID</label>
                    <select name="FeeID" id="FeesID" >
                    <option value="">Choose Fee ID*</option>
                    	<%
                    if(OrgFeesList!=null && OrgFeesList.size()>0)
                    {
                    	for(int i=0;i<OrgFeesList.size();i++)
                    	{
                    		HashMap<String,String> info=OrgFeesList.get(i);
                        	String fee_code=info.get("fee_code").toString().trim();
                        	String fee_name=info.get("fee_name").toString().trim();
                        	String FullName=info.get("FullName").toString().trim();
                        	%>
                        <option value='<%= fee_code%>' > <%=FullName %> </option>	
                        	
                    <%	}
                    }
                    %>
                    </select>
                    </li>
                    <li>
                    <label>Prefix</label>
                    <input type="text" name="Prefix" placeholder="Enter Prefix*" id="Prefix" maxlength=5 />
                    </li>
                    <li>
                    <label>Body</label>
                    <input type="text" name="Body" placeholder="Enter Body*" id="Body" maxlength=5 />
                    </li>
                    <li>
                    <label>Suffix</label>
                    <input type="text" name="Suffix" placeholder="Enter Suffix*" id="Suffix" maxlength=5 />
                    </li>
                    <li>
                    <label>Fee Description</label>
                    <input type="text" name="FeesDesc" placeholder="Enter Fee Description*" id="FeesDesc" maxlength=50/>
                    </li>
                     <li>
                    <label>Institution Fee ID</label>
                    <input type="text" placeholder="Enter Fee Id*" class="result_box" name="InstFeesID" id="InstFeesID" readonly/>
                    </li>
                    <li><input type="submit" value="submit" /></li>
                    
                    <% if(FeeSuccessMsg.length()>0) { %>
                  		<li><div id="SuccessMsg" class="success"> ${sessionScope.FeeSuccessMsg}</div></li>
                  	<%
                  		session.removeAttribute("FeeSuccessMsg");
                  	} %>
                  	
                  	<% if(FeeFailureMsg.length()>0) { %>
                  		<li><div id="FailureMsg" class="failure"> ${sessionScope.FeeFailureMsg}</div></li>
                  	<%
                  	session.removeAttribute("FeeFailureMsg");
                  	} %>  
                    
                </ul>
                </form>
                
            </div>
        </div>
        
    </div>
   <%@ include file="../JSP/FooterViews.jsp"%>
<script src="../JS/jquery.min.js"></script>
<script src="../JS/jquery.validate.js"></script>
<script>

function setInstituteID()
{
	$("#instid").val($("#OrgID").val());
	$("#kesfeesid").val($("#FeesID").val());
	$("#institutefeesid").val($("#InstFeesID").val());
	
	//alert($("#instid").val() + ' ' + $("#kesfeesid").val() +' ' + $("#institutefeesid").val());
}
$(document).ready(function() {
	
	$('input[name=Prefix]').change(function() { 
	
		$("#Prefix").val($("#Prefix").val().toUpperCase());
		
		var FinalVal=$("#Prefix").val().trim()+"-"+$("#Body").val().trim()+"-"+$("#Suffix").val().trim();
		
		$("#InstFeesID").val(FinalVal.toUpperCase());
		setInstituteID();
	});
	$('input[name=Body]').change(function() { 
		
		$("#Body").val($("#Body").val().toUpperCase());
		
		var FinalVal=$("#Prefix").val().trim()+"-"+$("#Body").val().trim()+"-"+$("#Suffix").val().trim();
		
		$("#InstFeesID").val(FinalVal.toUpperCase());
		setInstituteID();
		
	});
	$('input[name=Suffix]').change(function() { 
		
		$("#Suffix").val($("#Suffix").val().toUpperCase());
		
		var FinalVal=$("#Prefix").val().trim()+"-"+$("#Body").val().trim()+"-"+$("#Suffix").val().trim();
		
		$("#InstFeesID").val(FinalVal.toUpperCase());
		
		setInstituteID();
	});
	$('input[name=InstFeesID]').change(function() { 
		
		$("#InstFeesID").val($("#institutefeesid").val());
		
	});
	
$("#fees_form").validate({
rules: {
	Prefix: {
		required: true,
	},
	Body: {
		required: true,
	},
	Suffix: {
		required: true,
	},
	FeesDesc: {
		required: true,
	},
	Organization: {
		required:true,
	},
	FeeID: {
		required:true,
	},
	InstFeesID: {
		required:true,
	},
	
},
	messages: {
		Prefix: {
		required:"",
		},
		Body:{
		required:"",	
		},
		Suffix:{
		required:"",	
		},
		FeesDesc:{
		required:"",	
		},
		Organization: {
		required:"",
		},
		FeeID: {
		required:"",
		},
		InstFeesID: {
			required:"",
		},
	}
});
});
</script>
</body>

<script>
onload();
</script>
<style>
.header_top {
	width:100%;
	height:auto;
}
.header_top img {
	max-width:100%;
	height:auto;
	border-bottom: 1px solid #ddd;
}
.footer img {
	bottom: 0;
    height: auto;
    left: 0;
    max-width: 100%;
    position: fixed;
}
.container {
	padding-bottom:25px;
}
.footer {
	padding-bottom:50px;
}
input.error {
	border:1px solid red!important;
}
select.error {
	border:1px solid red!important;
}</style>

<script>

$("#ErrorMsg").css("display", "inline").fadeOut(5000);

function changeColor(color)
{
	$("#ErrorMsg").css("color", color)
}

</script>
<style>
#ErrorMsg {
width: 100%;
    margin: auto;
    padding: 4px 10px;
    color: green;
    font-size: 18px;
    text-align: center;
    float: left;
  
    left: 0px;
    bottom: -100px;

}
</style>
</html>
