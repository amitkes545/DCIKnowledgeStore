<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.domain.*"%>
<%@ page import="com.kds.KODE_DEV.dao.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome TO KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<script type="text/javascript">
function update(){
	//alert("hi");
	var kssize = document.getElementById('ksize').value;
	//alert("hi");
    if(kssize==""){
 	   alert("Please enter size ");
 	   return false;
}else if(isNaN(kssize) || kssize.indexOf(" ") != -1) {
		alert("Please enter digits only");
		return false;	
} else if (kssize.length > 3) {
		alert("Please enter only three digits");
		return false;	
}
	document.UpdateDeleteForm.action = "/KODE_DEV/ControllerServlet/OwnerUpdateKStore";
	document.UpdateDeleteForm.submit();
	return true;
}
function delete1(){
	document.UpdateDeleteForm.action = "/KODE_DEV/ControllerServlet/OwnerDeleteKStore";
	document.UpdateDeleteForm.submit();
}
</script>
<style type="text/css">
.owner_setup td select{width: 250px;}

</style>
</head>
<%	
	String msg = "";
	String username=(String)session.getAttribute("username");
	String	userid=(String)session.getAttribute("userid");
	//System.out.println("userid is:"+userid);
	String orgid=(String)session.getAttribute("orgid");
	%>
<body>
	<div class="container">
		<%-- <%@ include file="../JSP/all_one_header.jsp"%> --%>
		<jsp:include page="../JSP/all_one_header.jsp">
			<jsp:param name="username" value="<%= username%>" />
		</jsp:include>
		<%
	OwnerReportKnowStoreDomain rDomain=null;
rDomain=(OwnerReportKnowStoreDomain)request.getAttribute("msgvalue");
%>

		<div class="owner_setup owner-up"
			style="height: 200px; width: 430px !important;">
			 <p class="strong_left">KnowStore Details </p>
			 
			<form name="UpdateDeleteForm" method="post">
			
				<table>
					<tr>
						<td>Know Store ID</td>
						<td>:</td>
						<td><input readonly="readonly" type="text" name="ksid"
							value="<%=rDomain.getKsId() %>" /></td>
					</tr>
					<tr>
						<td>Institute ID</td>
						<td>:</td>
						<td><input readonly="readonly" type="text" name="orgid"
							value="<%=rDomain.getOrgId() %>" /></td>
					</tr>
					<tr>
						<td>User ID</td>
						<td>:</td>
						<td><input readonly="readonly" type="text" name="userid" id="userid"
							value="<%=rDomain.getUserId() %>" /></td>
							</tr>
						
						
								<%-- <option value="<%=rDomain.getUserId() %>"><%=rDomain.getUserId() %>
								</option> --%>
<%-- 								<%
							OwnerSelectUserIDKStoreDao dobj3 = new OwnerSelectUserIDKStoreDao();
							ArrayList<String> al1 = dobj3.fetchUser();

							Iterator<String> itr1 = al1.iterator();

							while (itr1.hasNext()) {
								String userid1 = itr1.next();
						%>
								<option value="<%=userid1%>"><%=userid1%></option>
								<%
							}
						%> --%>
						
					
				<tr>
						<td>Know Store Size</td>
						<td>:</td>
						<td><input id="ksize" type="text" name="kssize" value="<%=rDomain.getKnowSpace() %>" />
						</td>
						</tr>
						<tr>
						<td>
						 Status
						 </td>
						 <td>:</td>
						 <td>
						 <select name="status">
						<%
						if (rDomain.getStatus().toString().equalsIgnoreCase("Active")) {
					%>
						<option value="Active">Active</option>
						<option value="InActive">InActive</option>
						<%
 	} else {
 %>
						<option value="InActive">InActive</option>
						<option value="Active">Active</option>
						<%
 	}
 %>
					</select>
					</td>

</tr>
						<tr>
							<td></td>
							<td></td>
							<td>
							<input style="width: 90px; float: l" type="button" class="submit_btn" value="Update" onclick="update()">
							<input style="width: 90px" type="button" class="submit_btn" value="Delete" onclick="delete1()"></td>
						</tr>
						<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><a class="back" style="color: #c2c2c2;margin-top: 20px;
    float: left;" href="../JSP/OwnerKnowStoreManage.jsp">Back</a></td>
						</tr>
					</table>
				
			</form>
		</div>
	</div>
	<%@ include file="../JSP/all_one_footer.jsp"%>
</body>
</html>