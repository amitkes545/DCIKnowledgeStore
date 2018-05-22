<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.kds.KODE_DEV.domain.AdminReportKnowStoreDomain"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/OwnerKnowReports.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<script type="text/javascript">
	function validation1() {
		if (valid()) {
			document.KnowStoreReport.action = "/KODE_DEV/ControllerServlet/SuperAdminReportKnowStoreServlet";
			document.KnowStoreReport.submit();
		}
	}
</script>
<style type="text/css">
.search_details tr td {
	color: #fff;
	padding: 5px;
}
</style>
</head>
<body>
	<div class="container">
		<div style="overflow-y: scroll; height: 100%">
			<%@ include file="../JSP/all_one_header.jsp"%>

			<div style="clear: both;"></div>
			<div class="search_details"
				style="height: 350px; width: 590px; padding-right: 0px; padding-left: 0px;">
				<p class="strong_left">Know Store Report</p>

				<%!AdminReportKnowStoreDomain rDomain = new AdminReportKnowStoreDomain();%>
				<%
					rDomain = (AdminReportKnowStoreDomain) session
							.getAttribute("MsgValues");
				%>
				<form style="padding-left: 87px;">
					<table align=center border="1">
						<tr>
							<td>Know Store ID</td>
							<td style="width: 180px;"><%=rDomain.getKsId()%></td>
						</tr>
						<tr>
							<td>Org ID</td>
							<td><%=rDomain.getOrgId()%></td>
						</tr>
						<tr>
							<td>User ID</td>
							<td><%=rDomain.getUserId()%></td>
						</tr>
						<tr>
							<td>Created By</td>
							<td><%=rDomain.getCreatedBy()%></td>
						</tr>
						<tr>
							<td>Know Store Space</td>
							<td><%=rDomain.getKnowSpace()%></td>
						</tr>
						<tr>
							<td>Status</td>
							<td><%=rDomain.getStatus()%></td>
						</tr>

					</table>
					<p
						style="text-align: center; margin-left: -87px; padding-top: 15px;"></p>
					<a class="back" style="color: #c2c2c2;"
						href="../JSP/AdminKnowReports.jsp">Back</a>
				</form>
			</div>
		</div>
		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
</html>