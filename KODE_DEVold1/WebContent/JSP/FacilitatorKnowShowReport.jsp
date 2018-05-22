<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.kds.KODE_DEV.domain.FacilitatorKnowReportDomain"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/FacilitatorKnowSetup.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>

<script type="text/javascript">
	function validation3() {
		if (valid()) {
			document.KnowStore.action = "/KODE_DEV/ControllerServlet/FacilitatorKnowReportServlet";
			document.KnowStore.submit();
		}
	}
</script>
<style type="text/css">
.owner_setup tr td {
	color: #000 !important;
	padding: 5px;
}
</style>
</head>
<body>
	<div class="container" style="height: 815px;">


		<%@ include file="../JSP/all_one_header_knowstore.jsp"%>

		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menu.jsp" %>
		</div>
		<div class="owner_setup_faculty">

			<p class="strong">View</p>


			<%!FacilitatorKnowReportDomain fDomain = new FacilitatorKnowReportDomain();%>
			<%
				fDomain = (FacilitatorKnowReportDomain) request
						.getAttribute("MsgValue");
			%>


			<form>
				<table>
				
					<tr>
						<td>Library ID</td><td>:</td>
						<td><%=fDomain.getLibId()%></td>
					</tr>
					<tr>
						<td>Know Store ID</td><td>:</td>
						<td><%=fDomain.getKsId()%></td>
					</tr>
					<tr>
						<td>Library Name</td><td>:</td>
						
						<td><%=fDomain.getLibName()%></td>
					</tr>
					<tr>
						<td>Library Size</td><td>:</td>
						
						<td><%=fDomain.getLibSize()+" "+"GB"%></td>
					</tr>
					
				</table>

		</form>

		</div>
		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
</html>