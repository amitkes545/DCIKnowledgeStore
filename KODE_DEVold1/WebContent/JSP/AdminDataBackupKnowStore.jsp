<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCom To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
</head>
<%
	//HttpSession mess = request.getSession();
	String username=(String)session.getAttribute("username");
	String	userid=(String)session.getAttribute("userid");
	//System.out.println("userid is:"+userid);
	String orgid=(String)session.getAttribute("orgid");
	%>
<body>
	<div class="container">
		<%-- <%@ include file="../JSP/all_one_header.jsp" %> --%>
		<jsp:include page="../JSP/all_one_header_knowstore.jsp">
			<jsp:param name="username" value="<%=username%>" />
		</jsp:include>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menu.jsp" %>
		</div>
		<div class="faculty_mod">
			<p class="strong">DataBase Backup</p>
			<form action="/KODE_DEV/ControllerServlet/AdminDataBackupKSService" method="post">
				
				<%if(request.getAttribute("AdminSuccess") != null) {%>
				<p style="color: green; text-align: center; margin-left: 54px;">
				<%=request.getAttribute("AdminSuccess") %>
				</p>
				<% } %>

				<p style="text-align: center;">
					<input class="add_btn1" type="submit" value="Choose Folder" style="width:250px!important;margin-right: 20px;" />
				</p>
				<p style="text-align: center; margin-top: 15px;">
					<!-- <a class="back"
						style="color: #c2c2c2; margin-left: -10px; text-align: left;"
						href="../JSP/Home.jsp">Back</a> -->
			</form>
		</div>
		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
</html>