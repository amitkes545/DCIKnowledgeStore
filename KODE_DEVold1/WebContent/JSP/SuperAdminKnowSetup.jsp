<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.kds.KODE_DEV.dao.AdminSetUpKnowStoreFieldSelectorDao"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.kds.KODE_DEV.domain.SuperAdminSetupKnowStoreDomain"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/SuperAdminKnowSetup.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<style type="text/css">
.search_details tr td:last-child > input {
    width: 234px;
    margin: 5px 0px;
    padding: 5px;
}
</style>
<script type="text/javascript">
function validation(){
	if(valid10())
	{
		document.SuperAdKnowStore.action="/KODE_DEV/ControllerServlet/SuperAdminKnowStoreSetupServlet";
		document.SuperAdKnowStore.submit();
	}
}

var request;
function getKSize()
{
var v=document.SuperAdKnowStore.userid.value;
var url="CommonKsSizeSuperAdmin.jsp?val="+v;

if(window.XMLHttpRequest){
request=new XMLHttpRequest();
}
else if(window.ActiveXObject){
request=new ActiveXObject("Microsoft.XMLHTTP");
}

try
{
request.onreadystatechange=getInfo;
request.open("GET",url,true);
request.send();
}
catch(e)
{
alert("Unable to connect to server");
}
}
function getInfo(){
if(request.readyState==4){
var val=request.responseText;
document.getElementById('amit').innerHTML=val;
}
}
</script>
</head>
<%
	//HttpSession mess = request.getSession();
	String username=(String)session.getAttribute("username");
	String	userid=(String)session.getAttribute("userid");
	//System.out.println("userid is:"+userid);
	String orgid=(String)session.getAttribute("orgid");
	%>
<body>
	<div class="container">
	<%@include file="all_one_header.jsp" %>
	

		<div style="clear: both;"></div>
		<div class="search_details" style="width:450px;">

			<p class="strong_left">Know Store Setup</p>
			<form name="SuperAdKnowStore" method="post">
				<table align="center">
					
					<%-- <tr>
						<td style="width: 150px;">Org ID</td>
						<td>:</td>
						<td><select name="orgid" id="orgid">
								<option value="default">--select Org ID--</option>
								<%
						AdminSetUpKnowStoreFieldSelectorDao dobj2 = new AdminSetUpKnowStoreFieldSelectorDao();
							ArrayList<String> al1 = dobj2.fetchValue2(userid);

							Iterator<String> itr1 = al1.iterator();

							while (itr1.hasNext()) {
								String organization_id = itr1.next();
						%>
								<option value="<%=organization_id%>"><%=organization_id%></option>
								<%
							}
						%>
						</select></td>
						
						
					</tr> --%>
					
					<%-- <%SuperAdminSetupKnowStoreDomain aksDomain = new  SuperAdminSetupKnowStoreDomain();
					  
					aksDomain.setUserId(session.getAttribute("userid").toString());
					
					%> --%>
					
					<%
						if (session.getAttribute("SuperAdminSize") != null) {
					%>
					<p class="fadehide" style="color: red; text-align: center; margin-left: 54px;">
						<%=session.getAttribute("SuperAdminSize")%>
					</p>
					
					<%}%>
					
					
					
					
					
					<%
						if (session.getAttribute("MsgValue5") != null) {
					%>
					<p class="fadehide" style="color: red; text-align: center;margin-left: 0px;
    margin-bottom: 10px;">
						<%=session.getAttribute("MsgValue5")%>
					</p>
					
					<%
						}
					%>
					
					
					<%
						if (request.getAttribute("SuperAdminSuccess") != null) {
					%>
					<p class="fadehide" style="color: green; text-align: center; margin-left: 54px;">
						<%=request.getAttribute("SuperAdminSuccess")%>
					</p>
					<%
						} else if(request.getAttribute("SuperAdminFailure") != null) {
					%>
					<p class="fadehide" style="color: red; text-align: center; margin-left: 54px;">
						<%=request.getAttribute("SuperAdminFailure")%>
					</p>
					<%
						}
					%>
					<tr>
						<td>User ID</td>
						<td>:</td>
						<td><select name="userid1" id="userid" onchange="getKSize()">
								<option value="default">--select User ID--</option>
								<%
						AdminSetUpKnowStoreFieldSelectorDao dobj3 = new AdminSetUpKnowStoreFieldSelectorDao();
							ArrayList<String> al2 = dobj3.fetchValue4(orgid);

							Iterator<String> itr2 = al2.iterator();

							while (itr2.hasNext()) {
								
								String users_id = itr2.next();
						%>
								<option value="<%=users_id%>"><%=users_id%></option>
								<%
							}
						%>
						</select><span style="position: absolute;top: 60px;width: 272px;right: 0px;" id="amit"></span></td>
					</tr>

					<tr>
						<td>Know Store Size</td>
						<td>:</td>
						<td><input type="text" name="kssize" id="kssize"
							placeholder="Enter Size In GB">
					<tr>
						<td>Status</td>
						<td>:</td>
						<td><select name="status" id="status">
								<option value="default">--select Status--</option>
								<option value="Active">Active</option>

								<option value="InActive">InActive</option>
						</select></td>
					</tr>
					<tr height="10px;"></tr>
					<tr>
						<td></td>
						<td></td>
						<td><input class="submit_btn" style="width: 100px;"
							type="button" value="Create Now" onclick="validation()" /> <a
							class="back" style="color: #c2c2c2; text-align: left;"
							href="../JSP/Home.jsp">Back</a></td>
					</tr>
				</table>
				<p class="cre_p"></p>
			</form>
		</div>
		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
</html>