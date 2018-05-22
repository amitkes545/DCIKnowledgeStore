<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
</head>
<body>

	<div class="container">

		<div id="anim_img1"></div>
		<div id="kds_logo"></div>
		<div id="heading">Data Backup</div>
		<div class="right_menu">
			<ul>
				<li id="cpwd-li"><a href="#" id="cpwd">change Password</a></li>

				<li><a href="../JSP/Login.jsp"><button id="logout">Logout</button></a></li>
			</ul>
		</div>

		<div style="clear: both;"></div>
		<div class="store_manager" style="height: 200px; margin-top: 150px;">

			<p class="strong">DataBase Backup</p>
			<form
				action="/KODE_DEV/ControllerServlet/FacilitatorKStoreDataBackupServlet"
				method="post">
				l
				<table align="center">

					<tr>
					</tr>
					<tr>
						<td><input type="submit" value="GetData" /></td>
					</tr>
				</table>
			</form>

			<%-- <%@ include file="../JSP/all_one_footer.jsp" %> --%>

		</div>
	</div>
</body>
</html>