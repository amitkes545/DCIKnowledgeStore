   	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
   	<%@ page import="com.kds.KODE_DEV.dao.*"  %>
   	<%@page import="com.kds.KODE_DEV.domain.*" %>
   	<%@page import="java.util.*" %>
   	<%@page import="java.sql.*"%>
	<%@page import="java.io.InputStream"%>
	<%@page import="java.io.OutputStream"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="/KODE_DEV/JS/Validateform1.js"></script>
		
<script type="text/javascript" src="/KODE_DEV/JS/Coursedetails.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KODE_DEV - Login</title>
<link href="/KODE_DEV/CSS/LoginWebView.css" rel="stylesheet"/>
<link href="/KODE_DEV/CSS/kedu.css" rel="stylesheet"/>


<script> 
function formsubmittt()
{
alert("in submit");
var frm = document.getElementsByName("loginform");
alert(frm);
frm.submit();
alert("submitted");

	}
</script> 

</head>
<body >
<form name="loginform" method="post" action="../KODE_DEV/JSP/LoginWebView.jsp">
<p><input id="sig_btn" type="submit" value="Submit"/></p>
</form>
</body>
<script>
//alert("in submit");
var frm = document.getElementsByName("loginform");
//alert(frm);
frm.submit();
alert("submitted");
</script>
</html>