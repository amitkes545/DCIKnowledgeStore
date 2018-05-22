<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
window.history.forward();
function noBack() { window.history.forward(); }
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to KODE_DEV</title>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">

          <center><h2 style="color:red;">Opps ! some problem Occured</h2></center>
          <%
          String msg=request.getParameter("errmsg");
          %>
          <center><h3 style="color:red;">Error : <%=msg%></h3></center>
          <center>Please &nbsp<a href ="../JSP/LoginWebView.jsp">click here</a> to Login again!</center>
</body>
</html>