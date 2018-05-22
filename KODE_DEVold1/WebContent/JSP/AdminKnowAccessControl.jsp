<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.dao.*"%>
<%@ page import="com.kds.KODE_DEV.domain.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="../JS/AdminAccessControl.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>

<script>
	function mfunction1() {
		if (valid4()) {
			var block3 = document.getElementById("unblock").value;
			var block4 = 'UnBlock';
			if (block3 == block4) {
				//alert("hiu");
				document.AcAccessControll.action = "/KODE_DEV/ControllerServlet/AdminAccessControlServlet?status=Active";
				document.AcAccessControll.submit();

			} else {
				return false;
			}
		}
	}
	function myFunction() {

		if (valid4()) {
			var block = document.getElementById("block").value;
			var block1 = 'Block';

			if (block == block1) {

				document.AcAccessControll.action = "/KODE_DEV/ControllerServlet/AdminAccessControlServlet?status=InActive";
				document.AcAccessControll.submit();
			} else {

				return false;
			}

		}

	}
</script>
</head>
<%
	HttpSession mess = request.getSession();

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
			style="height: 200px; width: 380px !important;">

			<p class="strong">Knowledge Store Access Control</p>
			<form name="AcAccessControll" method="post">
				<%-- <%! OwnerManageKnowStoreDomain oDomain=new  OwnerManageKnowStoreDomain();%> --%>
				<table>
					<tr>
						<td>Know Store ID</td>
						<td>:</td>
						<td><select name="ksid" id="ksid">

								<option value="default">--select KS ID--</option>
								<%
									AdminSelectKnowStoreIdDao dobj2 = new AdminSelectKnowStoreIdDao();

									ArrayList<String> al = dobj2.fetchValue(session.getAttribute(
											"userid").toString());

									Iterator<String> itr = al.iterator();

									while (itr.hasNext()) {
										String ksid = itr.next();
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
							style="width: 90px" type="button" id="block" value="Block"
							onclick="myFunction()" /> <input class="submit_btn"
							style="width: 90px" type="button" id="unblock" value="UnBlock"
							onclick="mfunction1()" /></td>
					</tr>

					<tr>
						<td></td>
						<td></td>
						<td style="padding-top: 10px;"><a class="back"
							style="color: #c2c2c2; text-align: left;" href="../JSP/Home.jsp">Back</a></td>
					</tr>
				</table>
				<p></p>
			</form>
		</div>
		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
</html>