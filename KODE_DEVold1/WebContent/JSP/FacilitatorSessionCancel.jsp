<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.dao.FacilitatorSessionReportDao"%>
<%@ page import="com.kds.KODE_DEV.domain.SessionDomain"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	var count = 0;
	function myFunction() {
		if (count == 0) {
			document.getElementById("myCheck").innerHTML = "Available";
			count = 1;
		} else {
			document.getElementById("myCheck").innerHTML = "Cancelled";
			count = 0;
		}
	}
	function getSessionStatus(){
		
		var sessionName=document.getElementById("sessionName").value;
		if(sessionName == ""){
			alert("select session name");
			return false;
		}else {
			document.KnowStoreReport.action = "/KODE_DEV/ControllerServlet/FacilitatorSessionCancelServlet";
			document.KnowStoreReport.submit();
		}
		
	}
	function onload(){
		<% String sessionNameOnLoad=(String)request.getAttribute("sessionName");
		//System.out.println(" sessionName in an onload:"+sessionNameOnLoad);
		if(sessionNameOnLoad!=null && sessionNameOnLoad.length()>0) {
			//System.out.println("sessionNameOnLoad id in onload:"+sessionNameOnLoad);
		 %> 
		 var selectBox = document.getElementById("sessionName");
		 for(var i=0;i<selectBox.options.length;i++){
				 if(selectBox.options[i].value == '<%=sessionNameOnLoad%>')
				{
		 selectBox.options[i].selected=true;
					 break;
				 }
			 }
					
			<%}%>
	}
	function validation1() {
		var sessionName=document.getElementById("sessionName").value;
		if (sessionName == "") {
			alert("Select session name");
			return false;
		}else {
			document.KnowStoreReport.action = "/KODE_DEV/ControllerServlet/FacilitatorSessionCancelServlet";
			document.KnowStoreReport.submit();
		}
	} 
</script>

<script type="text/javascript" src="../JS/OwnerKnowReports.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<!-- <script type="text/javascript">
	/* function validation1() {
		if (valid()) {
			document.KnowStoreReport.action = "/KODE_DEV/ControllerServlet/FacilitatorSessionCancelServlet";
			document.KnowStoreReport.submit();
		}
	} */
</script> -->
</head>
<style type="text/css">
		.su{
		    color: #008000;
    font-size: 14px;
    /* font-weight: bold; */
    top: 45px;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
</style>
<%
	String username = (String) session.getAttribute("username");
	String userid = (String) session.getAttribute("userid");
	String orgid = (String) session.getAttribute("orgid");
	String sessionStatus=(String)request.getAttribute("sessionStatus");
	//System.out.println("sessionStatus in jsp"+sessionStatus);
%>
<body>
	<div class="container">
		<%@ include file="../JSP/all_one_header_knowstore.jsp"%>

		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menu.jsp" %>
		</div>
		<div class="faculty_mod">

			<p class="strong">Session Cancellation</p>

			<form name="KnowStoreReport" action="/KODE_DEV/ControllerServlet/FacilitatorSessionCancelServlet" method="post">
			<% if (request.getAttribute("FacultySuccess")!=null) { 
					//System.out.println(" faculty success message value in if:"+request.getAttribute("FacultySuccess"));
					%>
					<p class="su"><%= request.getAttribute("FacultySuccess") %></p>					
					<%
				} else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
					<p style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("FacultyFailure") %></p>					
					<%
				} 
				
			%>
				<table align="center">
					<tr>
						<!-- <td>Session Name</td>
						<td>:</td> -->
						<td><select name="sessionName" id="sessionName" class="box_lng" style="margin-right: 20px" onchange="getSessionStatus()">
								<option value="">--Select Session Name--</option>
								<%
									FacilitatorSessionReportDao dobj2 = new FacilitatorSessionReportDao();
									ArrayList<SessionDomain> al = dobj2.fetchValue(orgid, userid);

									Iterator<SessionDomain> itr = al.iterator();

									while (itr.hasNext()) {
										SessionDomain sessionID = itr.next();
								%>
								<option value="<%=sessionID%>"><%=sessionID%></option>
								<%
									}
								%>
						</select></td>
					</tr>


					<%-- 	<tr>
				<td style="width: 150px;">Recipient Type</td>
				<td>:</td>
				<td><select name="recipienttype" id="recipienttype">
						<option value="default">--select recipient type--</option>
						 <%
						FacilitatorSessionReportDao dobj3 = new FacilitatorSessionReportDao();
							ArrayList<String> al2 = dobj3.fetchRecipientType(userid);
	
							Iterator<String> itr1 = al.iterator();

							while (itr.hasNext()) {
								String recipientType = itr.next();
						%>
						<option value="<%=recipientType%>"><%=recipientType%></option>
						<%
							}
						%> 
				</select></td>
			</tr> --%>
					<!-- <tr>
						<td>Status</td>
						<td>:</td>
						<td><select name="status" id="status">
								<option value="default">--select Status--</option>
								<option value="Available">Available</option>

								<option value="Cancelled">Cancelled</option>
						</select></td>
					</tr> -->

					
						<td><input type="hidden" name="orgid" value="<%=orgid%>">
							<input type="hidden" name="userid" value="<%=userid%>">
							<%if(sessionStatus==null){
								//System.out.println("sessionStatus in if null:"+sessionStatus);
							}
							else if(sessionStatus.equalsIgnoreCase("Available")){ %>
							
						 <input class="add_btn1"  
							type="submit" name="cancel" value="cancel" onclick="validation1()">
							<%}else if(sessionStatus.equalsIgnoreCase("cancel")){ %>
								<input class="add_btn1"
							style="width: 95px; margin-right: 0px !important;" 
							type="submit" name="available" value="ReArrange" onclick="validation1()"> 
							<%} %>
							<!-- <a class="back" style="color: #c2c2c2; text-align: left;"
							href="../JSP/Home.jsp">Back</a> --></td>
					
				</table>

			</form>
		</div>
		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
<script>
onload();
</script>
</html>