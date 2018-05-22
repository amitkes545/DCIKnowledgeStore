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

<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
<link href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/start/jquery-ui.css"
          rel="Stylesheet" type="text/css" />

<link rel="stylesheet" href="../CSS/fees_styles.css" type="text/css" />
<link rel="stylesheet" href="../CSS/design-common.css" type="text/css" />

<link rel="stylesheet" href="../CSS/notification-new.css" type="text/css" />
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>
<%
String organizationId=(String)session.getAttribute("orgid");
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
String NxtFeesID=dao.getNxtFeesID();

String FeesID=NxtFeesID;
if(request.getAttribute("FeesID")!=null)
	FeesID=(String)request.getAttribute("FeesID");

String FeesName="";
if(request.getAttribute("FeesName")!=null)
	FeesName=(String)request.getAttribute("FeesName");

%>

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
            <h3>Fee Creation</h3>
            	<form id="fees_form" action="/KODE_DEV/ControllerServlet/FeesModuleService?PartName=CreateFees" method="post" >
                <ul>
                	<li>
                    <label>Fee ID</label>
                    <input type="text" value='<%=NxtFeesID %>' name="FeesID" id="FeesID" placeholder="Enter Description*" class="disable_box"/>
                    </li>
                    <li>
                    <label>Fee Description</label>
                    <input type="text" value='<%=FeesName %>' name="FeesName" id="FeesName" placeholder="Text" />
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
        <%@ include file="../JSP/FooterViews.jsp"%>
    </div>
    <!--<div class="footer"><img src="../images/footer_img.png" /></div>-->
<!-- </div> -->
<script src="../JS/jquery.min.js"></script>
<script src="../JS/jquery.validate.js"></script>
<script>
$(document).ready(function() {
$("#fees_form").validate({
rules: {
	name: {
		required: true
	},
	FeesName: {
		required: true
	},
},
messages: {
name: {
required:"",
lettersonly:""
},
FeesName: {
required:"",
lettersonly:""
},
}
});
});
</script>
</body>
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
</style>


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
