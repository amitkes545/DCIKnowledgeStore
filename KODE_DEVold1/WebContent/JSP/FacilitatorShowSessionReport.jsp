<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.kds.KODE_DEV.domain.FacilitatorSessionReportDomain"%>

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
			document.KnowStoreReport.action = "/KODE_DEV/ControllerServlet/FacilitatorShowSessionReportServlet";
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
			<%@ include file="../JSP/all_one_header_knowstore.jsp"%>
<div>
		<%@ include file= "../JSP/menu.jsp" %>
		</div>
			<div style="clear: both;"></div>
<div class="owner_setup_faculty">

<!-- <div class="faculty_mod" style="height: 550px;"> -->
			<!-- <div class="search_details_faculty"
				style="height: 500px; width: 560px; padding-right: 0px; padding-left: 0px;"> -->
				<p class="strong_left">Session Details</p>



				<%!FacilitatorSessionReportDomain rDomain = new FacilitatorSessionReportDomain();%>
				<%
					rDomain = (FacilitatorSessionReportDomain) session
							.getAttribute("MsgValues");
				%>


				<form style="padding-left: 30px;">
					<table>
						<tr>
							<td>ID</td>
							<td>:</td>
							<td style="width: 180px;"><%=rDomain.getSessionId()%></td>
						</tr>
						<tr>
							<td>Name</td>
							<td>:</td>
							<td><%=rDomain.getSessionName()%></td>
						</tr>
						<tr>
							<td>Category</td>
							<td>:</td>
							<td><%=rDomain.getCategory()%></td>
						</tr>
						
						<tr>
							<td> Start Time</td>
							<td>:</td>
							<td><%=rDomain.getSessionStartTime()%></td>
						</tr>
						<tr>
							<td>End Time</td>
							<td>:</td>
							<td><%=rDomain.getSessionEndTime()%></td>
						</tr>
						<tr>
							<td>Duration</td>
							<td>:</td>
							<td><%=rDomain.getDuration()%></td>
						</tr>
						<tr>
							<td> Cost</td>
							<td>:</td>
							<td><%=rDomain.getCostOfSession()%></td>
						</tr>
						<tr>
							<td>Faculty Name</td>
							<td>:</td>
							<td><%=rDomain.getFacultyName()%></td>
						</tr>
						<tr>
							<td>Organization ID</td>
							<td>:</td>
							<td><%=rDomain.getOrgId()%></td>
						</tr>
						<tr>
							<td>Recipient Type</td>
							<td>:</td>
							<td><%=rDomain.getRecipientType()%></td>
						</tr>
						
						<tr>
							<td>Session Status</td>
							<td>:</td>
							<td><%=rDomain.getSessionStatus()%></td>
						</tr>

					</table>
					<!-- <p
						style="text-align: center; margin-left: -87px; padding-top: 15px;">
						<a class="back" style="color: #c2c2c2;" href="../JSP/FacilitatorSessionReport.jsp">Back</a>
					</p> -->
				</form>
			</div>
		</div>
		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
</html>