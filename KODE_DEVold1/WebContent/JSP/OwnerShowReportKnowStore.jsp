<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.kds.KODE_DEV.domain.OwnerReportKnowStoreDomain"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<style type="text/css">
.owner_setup tr td:last-child {
	color: #fff;
}table {
    width: 100%;
    margin: 0px 0 0 -45px;
    
}
tr {
    background-color: #222;
}
td {
    padding: 5px;
}
</style>
</head>
<%
	HttpSession mess = request.getSession();
	String msg = "";
	String username = (String) mess.getAttribute("username");
	String userid = (String) mess.getAttribute("userid");
	//System.out.println("userid is:" + userid);
	String orgid = (String) mess.getAttribute("orgid");
%>
<body>

	<div class="container">
		<%-- <%@ include file="../JSP/all_one_header.jsp" %> --%>
		<jsp:include page="../JSP/all_one_header.jsp">
			<jsp:param name="username" value="<%=username%>" />
		</jsp:include>

		<div style="clear: both;"></div>
		<div class="owner_setup"
			style="height: 270px; width: 430px !important;">

			<p class="strong">Knowledge Store Report</p>
			<%!OwnerReportKnowStoreDomain rDomain = new OwnerReportKnowStoreDomain();%>
			<%
				rDomain = (OwnerReportKnowStoreDomain) request
						.getAttribute("MsgValue");
			%>
			<form>
				<table align=center border="1">
					<tr>
						<td>Know Store ID</td>
						<!-- <td>:</td> -->
						<td><%=rDomain.getKsId()%></td>
					</tr>
					<tr>
						<td>Institute ID</td>
						<!-- <td>:</td> -->
						<td><%=rDomain.getOrgId()%></td>
					</tr>
					<tr>
						<td>User ID</td>
						<!-- <td>:</td> -->
						<td><%=rDomain.getUserId()%></td>
					</tr>
					<tr>
						<td>Created By</td>
						<!-- <td>:</td> -->
						<td><%=rDomain.getCreatedBy()%></td>
					</tr>
					<tr>
						<td>Know Store Size</td>
						<!-- <td>:</td> -->
						<td><%=rDomain.getKnowSpace()+"GB"%></td>
					</tr>
					<tr>
						<td>Status</td>
						<!-- <td>:</td> -->
						<td><%=rDomain.getStatus()%></td>
					</tr>

				</table>
				<td style="padding-top: 10px;"><a class="back"
					style="color: #c2c2c2; float: left; width: 100%; text-align: center!important;  
    margin: 3px 0px 10px -56px;" href="../JSP/Home.jsp">Back</a></td>
			</form>
		</div>
		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
</html>