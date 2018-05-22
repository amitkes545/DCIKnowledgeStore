<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.dao.*"%>
<%@ page import="com.kds.KODE_DEV.domain.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/OwnerKnowStoreManage.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>


<script type="text/javascript">
	function validation1() {
		if (valid()) {
			document.KnowStoreManage.action = "/KODE_DEV/ControllerServlet/FacilitatorLibManageKnowStore";
			document.KnowStoreManage.submit();
		}
	}
</script>
</head>
<body>

	<div class="container">
		<%@ include file="../JSP/all_one_header.jsp"%>

		<div style="clear: both;"></div>
		<div class="owner_setup" style="height: 200px; width: 370px;">

			<p class="strong">Facilitator Knowledge Store Manage</p>
			<form name="KnowStoreManage" method="post">
				<table align="center">

					<%-- <%OwnerManageKnowStoreDomain oDomain=new  OwnerManageKnowStoreDomain();%> --%>

					<tr>
						<td>Know Store ID</td>
						<td>:</td>
						<td><select name="ksid" id="ksid">

								<option value="default">--Select KS ID--</option>
								<%
									OwnerSelectKnowStoreIdDao dobj2 = new OwnerSelectKnowStoreIdDao();

									ArrayList<String> al = dobj2.fetchKsIdAndUserId(session.getAttribute(
											"userid").toString());

									Iterator<String> itr = al.iterator();

									while (itr.hasNext()) {
										String ksid = itr.next();

										////System.out.println(oDomain.getKsId());
								%>
								<option value="<%=ksid%>"><%=ksid%></option>

								<%
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td style="padding-top: 10px;"><input class="submit_btn"
							style="width: 100px" type="button" value="DisplayReport"
							onclick="validation1()"> <a class="back"
							style="color: #c2c2c2; text-align: left;"
							href="../JSP/HomeFacilitatorKStore.jsp">Back</a></td>
					</tr>
				</table>
				<!-- <p><input class="cret_new_btn" type="button" value="DisplayReport" onclick="validation1()"></p> -->
			</form>
		</div>
		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
</html>