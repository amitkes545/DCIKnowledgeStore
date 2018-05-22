<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.dao.*"%>
<%@ page import="com.kds.KODE_DEV.domain.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/OwnerKnowStoreManage.js"></script>
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
			document.KnowStoreManage.action = "/KODE_DEV/ControllerServlet/OwnerUpdateManageKnowStore";
			document.KnowStoreManage.submit();
		}
	}
	 var request;
	 function getID()
	 {
	 var v=document.KnowStoreManage.ksid.value;
	 var url="CommonUserIDForKSID.jsp?val="+v;

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
	//HttpSession session = request.getSession(false);
	//HttpSession mess = request.getSession();

	String msg = "";
    
	String userName=(String)session.getAttribute("username");
	String	userId=(String)session.getAttribute("userid");
	//System.out.println("userid is:"+userId);
	String orgId=(String)session.getAttribute("orgid");
	%>
<body>

	<div class="container">
		<%-- <%@ include file="../JSP/all_one_header.jsp"%> --%>
		<jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%=session.getAttribute(userName)%>" /> 
         </jsp:include>

		<div style="clear: both;"></div>
		<div class="owner_setup"
			style="height: 200px; width: 450px !important;">

			<p class="strong">Know Store Management</p>
			<form name="KnowStoreManage" method="post">
				<table align="center">

					<%-- <%!OwnerManageKnowStoreDomain oDomain = new OwnerManageKnowStoreDomain();%> --%>

					<tr>
						<td>Know Store ID</td>
						<td>:</td>
						<td style="position: absolute;margin-top: -8px;"><select name="ksid" id="ksid" onfocus='this.size=10;' onblur='this.size=1;' onchange="getID();this.size=1; this.blur();">

								<option value="default">--select KS ID--</option>
								<%
									OwnerSelectKnowStoreIdDao dobj2 = new OwnerSelectKnowStoreIdDao();

									ArrayList<String> arrayList = dobj2.fetchKsIdAndUserId(session.getAttribute(
											"userid").toString());

									Iterator<String> iterator = arrayList.iterator();

									while (iterator.hasNext()) {
										String ksId = iterator.next();
										String userIdOfDb = iterator.next();
										String KsIdAndUserId =(ksId+" ("+userIdOfDb+")");
								%>
								<option value="<%=ksId%>"><%=KsIdAndUserId%></option>

								<%
									}
								%>
						</select></td>
						
					</tr>
					<tr height="10px"></tr>
					
					<%
						if (request.getAttribute("OwnerSuccess") != null) {
					%>
					<p class="fadehide" style="color: green; text-align: center; margin-left: 54px;">
						<%=request.getAttribute("OwnerSuccess")%>
					</p>
					<%
						} else if(request.getAttribute("OwnerFailure") != null) {
					%>
					<p class="fadehide" style="color: red; text-align: center; margin-left: 54px;">
						<%=request.getAttribute("OwnerFailure")%>
					</p>
					<%
						}
					%>
					
					<tr>
					<td></td>
					<td></td>
					<td><span id="amit"></span></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td style="padding-top: 10px;"><input class="submit_btn"
							style="width: 100px" type="button" value="Display"
							onclick="validation1()"> <a class="back"
							style="color: #c2c2c2; text-align: left;" href="../JSP/Home.jsp">Back</a></td>
					</tr>
				</table>
			</form>
		</div>
		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
</html>