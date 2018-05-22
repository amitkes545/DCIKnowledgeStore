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
 <script language="javascript" type="text/javascript"
 src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js">
 </script>
 <script type="text/javascript" language="javascript">
        $(function() {
            $(this).bind("contextmenu", function(e) {
                e.preventDefault();
            });
        }); 
       document.querySelector("button").addEventListener("click", function(){
            document.querySelector("div").style.display = "block";
        });

</script>
<%
String filename=request.getParameter("filename");
System.out.println("filename="+filename);
%>
<body>
<div>

 <!-- <video width="720" height="650" src="http://www.kompacdigit.com/<%=filename%>" type="video/mp4" controls> 
 <video width="720" height="650" src="http://www.kompacdigit.com:8080/KODE_DEV/<%=filename%>" type="video/mp4" controls> 
 
</video> -->
<pdf width="720" height="650" src="C:/Users/PrityMukesh/Downloads/1.pdf" type="pdf" controls>
</pdf>
</div>
</body>
</html>