<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.domain.*"%>
<%@ page import="com.kds.KODE_DEV.dao.*"%>
<%@ page import="java.util.*"%>

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
			document.KnowStoreReport.action = "/KODE_DEV/ControllerServlet/OwnerReportKnowStoreServlet";
			document.KnowStoreReport.submit();
		}
	}
</script>
</head>
<%
	
	String username = (String)session.getAttribute("username");
	String userid = (String)session.getAttribute("userid");
	//System.out.println("userid is:" + userid);
	String orgid = (String)session.getAttribute("orgid");
%>
<body>
	<div class="container">

		<%-- <%@ include file="../JSP/all_one_header.jsp" %> --%>
		<jsp:include page="../JSP/all_one_header.jsp">
			<jsp:param name="username" value="<%=username%>" />
		</jsp:include>
		<div style="clear: both;"></div>
		<div class="owner_setup" style="height: 200px;">

			<p class="strong">Know Store Details</p>
			<form name="KnowStoreReport" method="post">
				<table align="center">
					<%!OwnerReportKnowStoreDomain oDomain = new OwnerReportKnowStoreDomain();%>
					<tr>
						<td> Know Store ID</td>
						<td>:</td>
						<td style="position: absolute;margin-top: -8px;" ><select name="ksid" id="ksid" onfocus='this.size=10;' onblur='this.size=1;' onchange="this.size=1; this.blur();">
								<option value="default">--select KS ID--</option>
								<%
									OwnerSelectKnowStoreIdDao ownerSelectDao = new OwnerSelectKnowStoreIdDao();
									ArrayList<String> arrayList = ownerSelectDao.fetchKsIdAndUserId(session.getAttribute("userid")
											.toString());
									//HashSet set =(HashSet)al.fetchValue();
									Iterator<String> iterator = arrayList.iterator();

									while (iterator.hasNext()) {
										String ksId = iterator.next();
										String userId = iterator.next();
										String ksIdAndUserId = (ksId+" ("+userId+")");
								%>
								<option value="<%=ksId%>"><%=ksIdAndUserId%></option>
								<%
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td style="padding-top: 10px;"><input class="submit_btn"
							style="width: 100px" type="button" value="Get Details"
							onclick="validation1()"> <a class="back"
							style="color: #c2c2c2; text-align: left;" href="../JSP/Home.jsp">Back</a></td>
					</tr>
				</table>

			</form>
		</div>
		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
</html>