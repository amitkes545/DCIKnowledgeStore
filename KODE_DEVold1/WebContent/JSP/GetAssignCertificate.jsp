<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KODE_DEV</title>

<style type="text/css">
.ww{
text-transform: capitalize;
}
input{
border: none;
}
.h44
{
font-size: 22px;
text-transform: uppercase;
}
.up-load
{
margin-top: -40px;
margin-left: 150px;
position: absolute;
}
.sub_mit
{
margin-top: -10px;
margin-left: 150px;
position: absolute;
width: 100px;
background: #3366FF;
padding: 10px;
color: #fff;
}

.sub_mit:hover
{
background: #CC33FF;

}
.m-auto
{
margin:auto;
width:80%;
}
</style>
</head>
<body>
<%@ include file="../JSP/ParticipantViewHeader.jsp" %>
<div class="body-mtr"><!--  body content start -->
<div class="container m-top center">
<div style="clear: both;"></div>
<div class="m-auto">

<div style="width:800px; margin:50px auto;height:435px; padding:20px; text-align:center; border: 10px solid #787878">
<div style="width:750px; margin:auto; height:385px; padding:20px; text-align:center; border: 5px solid #787878">
       <span style="font-size:50px; font-weight:bold">Certificate of Completion</span>
       <br><br>
       <span style="font-size:25px"><i>This is to certify that</i></span>
       <br><br>
       <span style="font-size:30px"><b><%=session.getAttribute("userId") %></b></span><br/><br/>
       <span style="font-size:25px">Assignment ID &nbsp;<b><%=request.getParameter("id") %></b></span> <br/><br/>
       <span style="font-size:30px">Marks &nbsp;<b><%=request.getParameter("mark") %></b> </span> <br/><br/>
       <span style="font-size:20px">Result &nbsp;<b><%=request.getParameter("result") %></b></span><br/><br/> 
       <span style="font-size:30px">Status &nbsp;<b><%=request.getParameter("status") %></b></span> <br/><br/>
       <br/><br/><br/><br/>
       
</div>
</div>
</div>
</div>
</div>
<%@ include file="../JSP/FooterView.jsp" %>
</body>
</html>