<%@ page errorPage="/JSP/error.jsp?errmsg=Session Expired" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="com.kds.KODE_DEV.services.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%  
/* System.out.println("session="+session.getAttribute("previlege"));
ClassRoomOut cro=new ClassRoomOut();
if("success".equals(cro.endSession(session.getAttribute("userid").toString(),session.getAttribute("previlege").toString())))
{} */
SessionMaintenance s2=new SessionMaintenance();
if("success".equals(s2.endSession(session.getAttribute("userid").toString(),session.getAttribute("previlege").toString())))

session.invalidate();
response.sendRedirect("../JSP/LoginWebView.jsp");
%>
</body>
</html>