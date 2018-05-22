<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.domain.FacilitatorManageKnowStoreDomain"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>

<script type="text/javascript">
	function update() {
		document.UpdateDeleteForm.action = "/KODE_DEV/ControllerServlet/FacilitatorUpdateLib";
		document.UpdateDeleteForm.submit();
	}
	function delete1() {
		document.UpdateDeleteForm.action = "/KODE_DEV/ControllerServlet/FacilitatorDeleteLib";
		document.UpdateDeleteForm.submit();
	}
</script>
</head>
<body>
	<div class="container">
		<%@ include file="../JSP/all_one_header_knowstore.jsp"%>

<div>
		<%@ include file= "../JSP/menu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="owner_setup_faculty">

			<p class="strong">Library Management</p>

			<%
			FacilitatorManageKnowStoreDomain fDomain = null;
				fDomain = (FacilitatorManageKnowStoreDomain) request
						.getAttribute("msgvalue");
			%>
			<form name="UpdateDeleteForm" method="post">
				<table>
				
				    <tr>
						<td>Library ID</td>
						<td>:</td>
						<td><input readonly="readonly" type="text" name="libid"
							value="<%=fDomain.getLibId()%>" /></td>
					<!-- </tr>
					<tr>
						<td>Know Store ID</td>
						<td>:</td> -->
						<tr><td><input readonly="readonly" type="hidden" name="ksid"
							value="<%=fDomain.getKsId()%>" /></td>
					</tr>
					<tr>
						<td>Library Name</td>
						<td>:</td>
						<td><input type="text" name="libname"
							value="<%=fDomain.getLibName()%>" /></td>
					</tr>
					<tr>
						<td>Library Size</td>
						<td>:</td>
						<td><input type="text" name="libsize" placeholder="Enter In GB"
							value="<%=fDomain.getLibSize() +" "+"GB"%>" /></td>
					 </tr>
					<!-- <tr>
						<td style="width: 150px">User ID</td>
						<td>:</td> -->
						<tr><td><input type="hidden" name="userid"
							value="<%=fDomain.getUserId()%>" /></td>
					</tr>
					<!-- <tr>
						<td>Institute ID</td>
						<td>:</td> -->
						<tr><td><input type="hidden" name="orgid"
							value="<%=fDomain.getOrgId()%>" /></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td><input class="submit_btn" style="width: 90px;"
							type="button" value="Update" onclick="update()"> <input
							class="submit_btn" style="width: 90px;" type="button"
							value="Delete" onclick="delete1()"> 
						</td>
					</tr>
					<tr height="15px"></tr>
					<!-- <tr>
					<td></td>
					<td></td>
					<td><a class="back" style="color: #c2c2c2; text-align: left;" href="../JSP/Home.jsp">Back</a></td> -->
					
				</table>
			</form>
		</div>
		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
</html>