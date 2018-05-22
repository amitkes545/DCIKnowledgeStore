<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.domain.AdminReportKnowStoreDomain"%>
<%@ page import="com.kds.KODE_DEV.dao.AdminSelectUseridKSDao"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<script type="text/javascript">
function update(){
	
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
	document.UpdateDeleteForm.action = "/KODE_DEV/ControllerServlet/AdminUpdateKStore";
	document.UpdateDeleteForm.submit();
	return true ;
}
function delete1(){
	document.UpdateDeleteForm.action = "/KODE_DEV/ControllerServlet/AdminDeleteKStore";
	document.UpdateDeleteForm.submit();
}
</script>
</head>
<%
	//HttpSession mess = request.getSession();
	String msg = "";
	String username=(String)session.getAttribute("username");
	String	userid=(String)session.getAttribute("userid");
	//System.out.println("userid is:"+userid);
	String orgid=(String)session.getAttribute("orgid");
	%>
<body>
	<div class="container">
		 <%@ include file="../JSP/all_one_header.jsp"%>
		
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> --%>
	
		<div style="clear: both;"></div>
		<div class="owner_setup" style="height: 350px; width: 500px;">

			<p class="strong">Admin Knowledge Store Manage</p>

			<%
	AdminReportKnowStoreDomain rDomain = null; 
	
	       rDomain =  (AdminReportKnowStoreDomain)request.getAttribute("msgvalue");
%>
			<form name="UpdateDeleteForm" method="post">
				<table>
					<tr>
						<td>KS ID</td>
						<td>:</td>
						<td><input readonly="readonly" type="text" name="ksid"
							value="<%=rDomain.getKsId() %>" /></td>
					</tr>
					<tr>
						<td>Org ID</td>
						<td>:</td>
						<td><input readonly="readonly" type="text" name="orgid"
							value="<%=rDomain.getOrgId() %>" />
							</td>
					</tr>
					<tr>
					<td>User ID</td>
						<td>:</td>
						<td><input readonly="readonly" name="userid" id="userid"
						  value="<%=rDomain.getUserId() %>" /></td>
						
						
						
								<%-- <option value="<%=rDomain.getUserId() %>"><%=rDomain.getUserId() %>
								</option> --%>
							<%-- 	<%
								AdminSelectUseridKSDao dobj3 = new AdminSelectUseridKSDao();
							ArrayList<String> al1 = dobj3.fetchUsers(orgid);

							Iterator<String> itr1 = al1.iterator();

							while (itr1.hasNext()) {
								String userid1 = itr1.next();
						%>
								<option value="<%=userid1%>"><%=userid1%></option>
								<%
							}
						%> --%>
						
						</tr>
					<tr>
						<td style="width: 150px">Enter Size In GB</td>	
						<td>:</td>
						<td><input type="text" id="ksize" name="kssize"
							value="<%=rDomain.getKnowSpace() %>" /></td>
					</tr>
					<tr>
						<td>Status</td>
						<td>:</td>
						<td><select name="status" style="width: 204px;">
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
								<!-- <option value="Active">Active</option>
    						
    						<option value="InActive">InActive</option> -->
						</select></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td><input class="submit_btn" style="width: 90px;"
							type="button" value="Update" onclick="update()"> <input
							class="submit_btn" style="width: 90px;" type="button"
							value="Delete" onclick="delete1()"></td>
						<td style="padding-top: 5px;"><a class="back" style="color: #c2c2c2; text-align: left;" href="../JSP/Home.jsp">Back</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
</html>