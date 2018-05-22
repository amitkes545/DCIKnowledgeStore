<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.dao.*"%>
<%-- <%@ page import="com.kds.KODE_DEV.domain.*"%> --%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/AdminKnowStoreManage.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<script type="text/javascript">
	function validation1() {
		if (valid()) {
			document.KnowStoreManage.action = "/KODE_DEV/ControllerServlet/SuperAdminUpdateManageKnowStore";
			document.KnowStoreManage.submit();
		}
	}
</script>

</head>
<%
	String userName=session.getAttribute("username").toString();
	String	userId=session.getAttribute("userid").toString();
	//System.out.println("userid is:"+userId);
	String orgId=session.getAttribute("orgid").toString();
	%>
<body>
	<div class="container">
		<%-- <%@ include file="../JSP/all_one_header.jsp"%> --%>
		<%@include file="all_one_header.jsp" %>
		
		<div style="clear: both;"></div>
		<div class="search_details" style="height: 200px; width: 425px">

			<p class="strong_left">Know Store Management</p>
			<form name="KnowStoreManage" method="post">
				<table align="center">

					<%-- <%!AdminSetUpKnowStoreDomain oDomain = new AdminSetUpKnowStoreDomain();%> --%>

					<tr>
						<td style="width: 150px;">Know Store ID</td>
						<td>:</td>
						<!-- <td><select name="ksid" id="ksid" tabindex="5"> -->
						<td style="position: absolute;margin-top: -8px;"><select name="ksid" id="ksid" onfocus='this.size=10;' onblur='this.size=1;' onchange="this.size=1; this.blur();"> 

								<option value="default">--select KS ID--</option>
								<%
									AdminSetUpKnowStoreFieldSelectorDao adminSetupDao = new AdminSetUpKnowStoreFieldSelectorDao();

									ArrayList<String> arrayList = adminSetupDao.fetchKsIdAndUserId(orgId,userId);

									Iterator<String> iterator = arrayList.iterator();

									while (iterator.hasNext()) {
										String ksId = iterator.next();
										String userIdOFDb = iterator.next();
										String ksIduserId =(ksId+ " ("+userIdOFDb+")"); 
								%>


								<option value="<%=ksId%>"><%=ksIduserId%></option>

								<%
									}
								%>
						</select></td>
					</tr>
					
					<%
						if (request.getAttribute("SuperAdminSuccess") != null) {
					%>
					<p class="fadehide" style="color: green; text-align: center; ;margin-left: 0px;
    margin-bottom: 10px;">
						<%=request.getAttribute("SuperAdminSuccess")%>
					</p>
					<%
						} else if(request.getAttribute("SuperAdminFailure") != null) {
					%>
					<p class="fadehide" style="color: red; text-align: center;;margin-left: 0px;
    margin-bottom: 10px;">
						<%=request.getAttribute("SuperAdminFailure")%>
					</p>
					<%
						}
					%>
					
					<%
						if (request.getAttribute("SuperAdminSuccess11") != null) {
					%>
					<p class="fadehide" style="color: green; text-align: center;;margin-left: 0px;
    margin-bottom: 10px;">
						<%=request.getAttribute("SuperAdminSuccess11")%>
					</p>
					<%
						} else if(request.getAttribute("SuperAdminFailure111") != null) {
					%>
					<p class="fadehide" style="color: red; text-align: center; ;margin-left: 0px;
    margin-bottom: 10px;">
						<%=request.getAttribute("SuperAdminFailure111")%>
					</p>
					<%
						}
					%>
					<tr height="10px"></tr>
					<tr>
						<td></td>
						<td></td>
						<td><input class="submit_btn" style="width: 100px;"
							type="button" value="Display" onclick="validation1()" /> <a
							class="back" style="color: #c2c2c2; text-align: left;"
							href="../JSP/Home.jsp">Back</a></td>
				</table>
				<p class="cre_p"></p>
			</form>
		</div>

		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
</html>