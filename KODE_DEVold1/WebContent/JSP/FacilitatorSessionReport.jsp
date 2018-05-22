<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.dao.FacilitatorSessionReportDao"%>
<%@ page import="com.kds.KODE_DEV.domain.SessionDomain"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/OwnerKnowReports.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<script type="text/javascript">
	function validation1() {
		var sessionID=document.getElementById("sessionid").value;
		if (sessionID == "") {
			alert("Select session ID");
			return false;
		}else {
			document.KnowStoreReport.action = "/KODE_DEV/ControllerServlet/FacilitatorShowSessionReportServlet";
			document.KnowStoreReport.submit();
		}
	}
</script>
</head>
<%
	
	String username = (String) session.getAttribute("username");
	String userid = (String) session.getAttribute("userid");
	String orgid = (String) session.getAttribute("orgid");
%>
<body>
	<div class="container">
		<%@ include file="../JSP/all_one_header_knowstore.jsp"%>

		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menu.jsp" %>
		</div>
		<div class="faculty_mod">

			<p class="strong">Session Report</p>

			<form name="KnowStoreReport" method="post">
				<table align="center">
					<tr>
						<!-- <td >Session ID</td>
						<td>:</td> -->
						<td><select name="sessionid" id="sessionid" class="box_lng">
								<option value="">--select Session ID--</option>
								<%
									FacilitatorSessionReportDao dobj2 = new FacilitatorSessionReportDao();
									ArrayList<SessionDomain> al = dobj2.fetchValue(orgid, userid);

									Iterator<SessionDomain> itr = al.iterator();

									while (itr.hasNext()) {
										SessionDomain sessionId = itr.next();
								%>
								<option value="<%=sessionId%>"><%=sessionId%></option>
								<%
									}
								%>
						</select></td>
					</tr>

					
					<tr>
						
						<td>
							<%-- <input type="hidden" name="orgid" value="<%=orgid %>"> --%>
							<input class="add_btn1" style="width:265px!important; margin-right: 25px" id="block"
							type="submit" value="Get Report" onclick="validation1()">
							<!-- <a class="back" style="color: #c2c2c2; text-align: left;"
							href="../JSP/Home.jsp">Back</a> -->
						</td>
					</tr>
				</table>

			</form>
		</div>
		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
</html>