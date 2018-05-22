
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.domain.AdminReportKnowStoreDomain"%>
<%@ page
	import="com.kds.KODE_DEV.dao.AdminSetUpKnowStoreFieldSelectorDao"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/AdminKnowStoreManage.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<script type="text/javascript">
	function validation() {
		if (valid()) {
			document.KnowStoreReport.action = "/KODE_DEV/ControllerServlet/AdminReportKnowStoreServlet";
			document.KnowStoreReport.submit();
		}
	}
</script>
</head>
<%
	/* HttpSession mess = request.getSession();
	String msg = ""; */
	String username = (String)session.getAttribute("username");
	String userid = (String)session.getAttribute("userid");
	//System.out.println("userid is:" + userid);
	String orgid = (String)session.getAttribute("orgid");
%>
<body>
	<div class="container">
		 <%@ include file="../JSP/all_one_header.jsp" %> 
		
		<div style="clear: both;"></div>
		<div class="search_details" style="height: 200px; width: 445px">

			<p class="strong_left">Know Store Details</p>

			<form name="KnowStoreReport" method="post">
				<table align="center">
					<%-- <%!AdminReportKnowStoreDomain oDomain=new  AdminReportKnowStoreDomain();%> --%>
					<tr>
						<td style="width: 150px;">Know Store ID</td>
						<td>:</td>
						<td><select name="ksid" id="ksid">
								<option value="default">--select Know Store ID--</option>
								<%
									AdminSetUpKnowStoreFieldSelectorDao dobj2 = new AdminSetUpKnowStoreFieldSelectorDao();
									ArrayList<String> arrayList = dobj2.fetchKsIdAndUserId(orgid, userid);

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
					<tr height="10px;"></tr>
					<tr>
						<td></td>
						<td></td>
						<td><input class="submit_btn" style="width: 85px"
							type="button" value="Get Details" onclick="validation()">
							<a class="back" style="color: #c2c2c2; text-align: left;"
							href="../JSP/Home.jsp">Back</a></td>
					</tr>
				</table>
			</form>
		</div>
		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
</html>