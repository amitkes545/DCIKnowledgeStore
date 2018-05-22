<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../Css/kstyle.css"/>
<title>Insert title here</title>
</head>
<body>
<div class="header">
<div class="header1">
<div class="kdslogo">
<img src="../Image/kds.png"/>
</div>
<div class="kedulogo">
<img src="../Image/Keducom_logo.png" style="width:256px;margin-top: 23px"/>
</div>
</div>
<div class="header2">
<div class="smsg">
<p class="wmsg">Welcome <%=session.getAttribute("userName") %></p>
</div>

<div class="logbtn">
<a href="../JSP/logout_knowstore.jsp">
<p class="lgout"><img src="../Image/logout.png" style="width:10px;vertical-align: middle;"/> <span>Logout</span></p></a>
</div>

</div>
<div>
<a href="../JSP/ParticipantView.jsp">
<p class="myFolder"><span>MyFolder</span></p></a>
</div>
</div>

</body>
</html>