<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.kds.KODE_DEV.domain.*" %>
<%@page import="java.util.*" %>
     
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>keDuCom</title>

<style type="text/css">
.postpon_mod{
width: 95% !important;
height: 95% !important;
    box-shadow: 5px 5px 5px 5px #FFF;
    margin: 15px auto 0px;
    padding: 15px 1px 0px 0px;
    border: 3px solid #C2C2C2;
    background-color: #FCF7F7;
    border-radius: 4px;
    opacity: 0.7;
    margin-top: 55px;
    
}
.container{
background: url("../Image/body.png") center center no-repeat;
}
</style>


</head>
<div class="container">
<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
	</div>
	<%@ include file= "../JSP/CameraFrame.jsp" %>	
<!-- <div class="postpon_mod"> -->	
		<!-- <iframe height="150px" width="80%" style="border:2px solid grey;" name="vcamera" src="../JSP/ViewCamera.jsp" frameborder="0" ></iframe>
		<iframe height="100%" width="20%" style="border:2px solid grey;" name="scamera" src="../JSP/SelectCamera.jsp" frameborder="0" ></iframe>
		 -->
		<iframe  height="51px" scrolling="no" width="100%" style="border:0px solid grey;" name="blnk"  ></iframe>
		<iframe  height="580px" scrolling="no"  width="100%" style="border:2px solid grey;" name="scamera" src="../JSP/CameraFrame.jsp" frameborder="0" ></iframe>
		<%@ include file="../JSP/FooterViews.jsp"%>
	</div>
<!-- 	</div> -->
</html>