
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ page import="com.kds.KODE_DEV.dao.*"%>

<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<!-- <link rel="stylesheet" href="../Css/kstyle.css"/> -->
<!-- <link rel="stylesheet" href="../Css/kstyle.css"/>
 -->
 <title>Insert title here</title>
<style type="text/css">
@font-face 
{
    font-family:bold;
    src: url("../font/OpenSans-Bold.ttf")
}
@font-face 
{
    font-family: regular;
    src: url("../font/OpenSans-Regular.ttf");
}
*
{
	padding: 0px;
	margin: 0px;
	
}

.header1
{
	width:100%;
	float:left;
	background-color: #eae9ea;
	border: 1px solid #D1D1D1;
	height:60px;
	box-shadow: 5px 3px 5px #ccc;
	
}
.kdslogo
{
	width: auto;
    float: left;
    height: 60px;
    position: relative;
    margin-left: 5px;
}
.kdslogo img {
position:absolute;
left:0px;
right:0px;
top:0px;
bottom:0px;
margin:auto;
max-width:120px;
height: 50px;
}
.kedulogo
{
	width:45%;
    float:right;
    text-align: right;
    margin-right: 10px;
 }

.header2
{
	width:100%;
	float:left;
	background-color: #fff;
	height: 40px;
	opacity:0.9;
	border: 1px solid #eae9ea;
}
.wmsg
{
	margin-top: 12px;
	color:#0027FF;
	font-family:regular;
	margin-left: 15px;
	font-size: 15px;
	margin-top:8px;
}
.smsg
{
	width:50%;
	float:left;
	
}

.logbtn
{
	width:7%;
	float:right;
	text-align:right;
	margin-right: 25px;
}

.lgout
{
	
	margin-top:6px;
	color:#000;
	float:left;
	text-align: right;
	width: 100%;
}


</style>
</head>
<body>

<div class="header">
<div class="header1">
<div class="kdslogo">
<img src="../Image/<%=(String) session.getAttribute("logoname")%>"/>
</div>
<div class="kedulogo">
<img src="/KODE_DEV/Image/kode.png" style="max-width:101px;heigth:47px; margin-top:3px"/>
</div>
</div>
<div class="header2">
<div class="smsg">
<p class="wmsg">Welcome <%=session.getAttribute("userName") %></p>
</div>


<%-- ../Image/<%=logoName --%>

<div class="logbtn">
<a href="../JSP/logout_knowstore.jsp">
<!-- <p class="lgout"><img src="../Image/logout.png"/> </p> -->
<p class="lgout" style="margin:0px;margin-left:10px"><img src="../Image/logout.png" style="width: 95px;vertical-align: middle;position: relative;margin-top: 3px;"/></p>
</a>

</div>
</div>
<!-- <div>
<a href="../JSP/ParticipantView.jsp">
<p class="myFolder"><span>MyFolder</span></p></a>
</div>
 --></div>
</body>
</html>