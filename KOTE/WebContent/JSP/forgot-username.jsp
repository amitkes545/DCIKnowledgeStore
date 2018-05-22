<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Forgot Username / UserID</title>
<link type="text/css" href="../css/style.css" rel="stylesheet" />
<link type="text/css" href="../css/responsive.css" rel="stylesheet" />
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/custom.js"></script>
</head>

<body class="login" onload='document.forgotpswd.usernm.focus()'>
<header>
  <div class="container row">
    <div class="logo left"><a href="#"><img src="../images/logo.png" alt="KES Logo" /></a></div>
  </div>
</header>
<section class="middle-container">
  <div class="container row">
    <div class="login-form forgot-username">
      <h1>Forgot Username / UserID</h1>
      <form class="forgotpswd">
        <div class="forgotpswd-box">
          <input type="email" name="usernm" placeholder="Enter Username" id="email" autofocus />
        </div>
        <div class="btnblock">
          <input type="submit" value="Submit" name="Login" class="button" onClick="forgotpswdval()" />
        </div>
        <!--<div class="btnblock"> <a href="login.html">Back To Login</a> </div>-->
      </form>
    </div>
  </div>
</section>
<footer>
  <center>
    <div class="container">
      <p>Copyright &copy; 2017. Kompac Education Systems Pvt. Ltd. All Rights Reserved.</p>
    </div>
  </center>
</footer>
</body>
</html>