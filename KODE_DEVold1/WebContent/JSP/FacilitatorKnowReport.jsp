<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.kds.KODE_DEV.dao.FacilitatorSelectKnowStoreIdDao"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/FacilitatorKnowReports.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>

<script type="text/javascript">
	function validation1() {
		//if (valid505()) {
			document.KnowStoreReport.action = "/KODE_DEV/ControllerServlet/FacilitatorLibInfoListViewKnowServlet";
			document.KnowStoreReport.submit();
		//}
	}
</script>
</head>

<%
String username=(String)session.getAttribute("username");
String	userid=(String)session.getAttribute("userid");
//System.out.println("userid is:"+userid);
String orgid=(String)session.getAttribute("orgid");
	%>

<body>
	<div class="container">
		<%-- <%@ include file="../JSP/all_one_header.jsp"%> --%>
		<jsp:include page="../JSP/all_one_header_knowstore.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>

		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="faculty_mod">

			<p class="strong">Library View </p>
			<form name="KnowStoreReport" method="post">
				<table align="center">
					
					<tr>
						<td>
						<input class="add_btn1" style="width: 265px!important;margin-right: 20px;" type="button" value="View" onclick="validation1()">
						<!-- <a class="back" style="color: #c2c2c2; text-align: left;" href="../JSP/Home.jsp">Back</a> --></td>
					</tr>
				</table>
			</form>
		</div>
	<%@ include file="../JSP/all_one_footer.jsp" %>
	</div>
</body>
</html>