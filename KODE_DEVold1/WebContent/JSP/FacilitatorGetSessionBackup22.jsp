<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.kds.KODE_DEV.dao.FacilitatoreSessionBackupSelectPathDao"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />

<%
	HttpSession session1 = request.getSession();

	String msg = "";
	String msg1 = "";

	String username = (String) session1.getAttribute("username");
	String userid = (String) session1.getAttribute("userid");
	String orgid = (String) session1.getAttribute("orgid");
%>

</head>
<body>
	<p style="text-align: center; margin-top: 100px">Click Here For
		Sessoin Backup !!</p>
	<p style="text-align: center; margin-top: 15px">


		<%
			FacilitatoreSessionBackupSelectPathDao fDao = new FacilitatoreSessionBackupSelectPathDao();

			ArrayList<String> arl = fDao.fetchValue();
		%>


		<a style="text-align: center;"
			href="/KODE_DEV/ControllerServlet/FacilitatorGetSessionBackupServlet">Download</a>
	</p>
</body>
</html>