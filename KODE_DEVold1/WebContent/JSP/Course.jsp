<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KODE_DEV</title>
<link href="../CSS/all-one-stylesheet.css" rel="stylesheet"></link>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>  -->
<script src="../JS/jquery.min.js"></script> 
<!-- <script> 
$(document).ready(function(){
    $("#courses_menu").click(function(){
        $("#submenu_div").slideToggle(20);
    });
});
</script> -->
<style> 
#submenu_div, #courses_menu {
    padding: 5px;
    text-align: center;
    background-color: #6599ff;
    border: solid 1px #6599ff;
}

#submenu_div {
    padding: 5px;
    display: none;
    width: 200px;
    position: absolute;
    left:37%;
    top:103px;
    height: 160px;
}
.menu1 li
{
list-style: none;
font-size: 18px;
text-align: left;
padding-left: 5px;
padding: 3px;

}

.menu1 li a{
text-decoration: none;
color: #fff;
text-align: left;
padding-left: 15px;
font-family: OpenSans-Regular;
}
</style>
<script>
window.location.hash="no-back-button";
window.location.hash="Again-No-back-button";//again because google chrome don't insert first hash into history
window.onhashchange=function(){window.location.hash="";}
</script>

</head>
<body>
<div class="main"><!-- start div overall main -->

<%@ include file="../JSP/WebViewHeader.jsp"  %>


<div style="clear: both;"></div>

<div class="body-mtr"> <!-- body content start -->
<div class="container">
<div class="subject-div">
<ul class="subject-ul">
<li><a href="#"><img src="../Image/course1.jpg" /><br/><span>Technology</span></a></li>
<li><a href="../JSP/WebView.jsp"><img src="../Image/course2.jpg" /><br/><span>Engineering</span></a></li>
<li><a href="#"><img src="../Image/course3.jpg" /><br/><span>Mathematics</span></a></li>
<li><a href="#"><img src="../Image/course4.jpg" /><br/><span>commerce</span></a></li>
</ul>
</div>
<div style="clear: both;"></div>
</div>
</div><!-- body content close -->

<%@ include file="../JSP/FooterView.jsp"  %>
</div><!-- close div overall main -->
</body>
</html>