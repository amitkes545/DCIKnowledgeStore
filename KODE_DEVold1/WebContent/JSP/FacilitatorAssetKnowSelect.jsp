<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.dao.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="../JS/FacilitatorKnowPublish.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>

<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<script>
	function myFunction() {
		
				document.AcAccessControll.action = "/KODE_DEV/ControllerServlet/FacilitatorAssetKnowServlet"
				document.AcAccessControll.submit();

		}
	
</script>

</head>

<%
String username = (String)session.getAttribute("username");
String userid = (String)session.getAttribute("userid");
String orgid = (String)session.getAttribute("orgid");
%>


<body>
	<div class="container">
		<%-- <%@ include file="../JSP/all_one_header.jsp" %> --%>
		<jsp:include page="../JSP/all_one_header_knowstore.jsp">
			<jsp:param name="username" value="<%=username%>" />
		</jsp:include>

		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menu.jsp" %>
		</div>
		<div class="owner_setup_faculty">

			<p class="strong" style="font-size: 15px;">
				Publish Control</p>
			<form name="AcAccessControll" method="post">
				<table>
				
					<tr>
					<td>Know Store ID</td>
						<td>:</td>
						<td><select name="ksid" id="ksid" >

								<option value="default">Select KnowledgeStore ID</option>
								<%
									FacilitatorSelectKsidKStoreDao dobj2 = new FacilitatorSelectKsidKStoreDao();

									ArrayList<String> al = dobj2.fetchValue(orgid,userid);

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
						</select><br>
						
						<!-- <span id="amit"></span></td> -->
					</tr>
					
					<tr>
						
						<td><input class="add_btn1"
							style="width: 95px!important;margin-right: 59px;margin-top: 10px" type="submit" id="block"
							value="Submit" onclick="myFunction()" /> <!-- <input class="add_btn1"
							style="width: 95px!important; margin-top: 10px" id="unblock_btn" type="button" id="unblock"
							value="UnBlock" onclick="mfunction1()" /></td> -->
					</tr>
									</table>

			</form>

		</div>
		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
</html>