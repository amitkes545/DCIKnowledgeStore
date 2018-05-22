<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCom To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
</head>
<%
	String faculyid = request.getParameter("userId");
	//System.out.println("facultyid is:" + faculyid);
	HttpSession mess = request.getSession();
	String msg = "";
	String username = (String) mess.getAttribute("username");
%>
<body>
	<div class="container">
		<%-- <%@ include file="../JSP/all_one_header.jsp" %> --%>
		<jsp:include page="../JSP/all_one_header.jsp">
			<jsp:param name="username" value="<%=username%>" />
		</jsp:include>

		<div style="clear: both;"></div>
		<div class="search_details" style="height: 200px;">
			<p class="strong_left">DataBase Backup</p>
			<form action="/KODE_DEV/ControllerServlet/FacilitatorDataBackupLibKnowstore"
				method="post">

				<p style="text-align: center;">
					<input class="submit_btn" type="submit" value="GetData" />
				</p>
				<p style="text-align: center; margin-top: 15px;">
					<a class="back"
						style="color: #c2c2c2; margin-left: -10px; text-align: left;"
						href="../JSP/Home.jsp">Back</a>
			</form>
		</div>
		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
</html>