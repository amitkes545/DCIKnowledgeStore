<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
div{
display: none;
}
</style>

<button>see videos</button>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
 <script type="text/javascript" src="../JS/jquery.min.js">
 </script>

<%
/* String filename=request.getParameter("filename");
System.out.println("filename="+filename); */
%>
<body>
<div>
<iframe   width="720" height="650" src="http://www.kompacdigit.com:8080/KODE_DEV/sam.mp4" >
</iframe>
</div>

</body>
</html>