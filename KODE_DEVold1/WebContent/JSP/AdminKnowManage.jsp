<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.dao.*"%>
<%-- <%@ page import="com.kds.KODE_DEV.domain.*"%> --%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/AdminKnowStoreManage.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> -->
<script src="../JS/jquery.min.js"></script> 
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
			document.KnowStoreManage.action = "/KODE_DEV/ControllerServlet/AdminUpdateManageKnowStore";
			document.KnowStoreManage.submit();
		}
	}
	
	var request;
	 function getID()
	 {
	 var v=document.KnowStoreManage.ksid.value;
	 var url="CommonUserNameKnowStore.jsp?val="+v;

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
			<jsp:param name="username" value="<%=username%>" />
		</jsp:include>

		<div style="clear: both;"></div>
		<div class="search_details" style="height: 200px;">

			<p class="strong_left">Knowledge Store Management</p>
			<form name="KnowStoreManage" method="post">
				<table align="center">

					<%-- <%!AdminSetUpKnowStoreDomain oDomain = new AdminSetUpKnowStoreDomain();%> --%>
					
					<%
						if (request.getAttribute("AdminSuccess") != null) {
					%>
					<p class="fadehide" style="color: green; text-align: center; margin-left: 54px;">
						<%=request.getAttribute("AdminSuccess")%>
					</p>
					<%
						} else if(request.getAttribute("AdminFailure") != null) {
					%>
					<p class="fadehide" style="color: red; text-align: center; margin-left: 54px;">
						<%=request.getAttribute("AdminFailure")%>
					</p>
					<%
						}
					%>
					<tr>
						<td style="width: 150px;">Know Store ID</td>
						<td>:</td>
						<td><select name="ksid" id="ksid" onchange="getID()">

								<option value="default">--select KS ID--</option>
								<%
									AdminSetUpKnowStoreFieldSelectorDao dobj = new AdminSetUpKnowStoreFieldSelectorDao();

									ArrayList<String> al = dobj.fetchKsIdAndUserId(orgid,userid);

									Iterator<String> itr = al.iterator();

									while (itr.hasNext()) {
										String ksId = itr.next();
										String userId = itr.next();
										String ksIdAndUserId = (ksId+" ("+userId+")");
								%>


								<option value="<%=ksId%>"><%=ksIdAndUserId%></option>

								<%
									}
								%>
						</select><span id="amit"></span></td>
					</tr>
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