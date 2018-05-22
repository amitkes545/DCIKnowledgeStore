<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.dao.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/AdminFileShareFtpKnowStore.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>  -->
<script src="../JS/jquery.min.js"></script> 
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>

<script type="text/javascript">
	function validation3() {
		if (validAdmin()) {
			document.KnowStore.action = "/KODE_DEV/ControllerServlet/AdminFileShareKnowStoreServlet";
			document.KnowStore.submit();
		}
	}
</script>
</head>
<%
	//HttpSession mess = request.getSession();
	String msg = "";
	String msg1 = "";
	String username = (String)session.getAttribute("username");
	String userid = (String)session.getAttribute("userid");
	String orgid = (String)session.getAttribute("orgid");
	//System.out.println(orgid);
%>

<body>
	<div class="container" style="height: 800px">
		<%-- <%@ include file="../JSP/all_one_header.jsp" %> --%>
		<jsp:include page="../JSP/all_one_header.jsp">
			<jsp:param name="username" value="<%=session%>" />
		</jsp:include>

		<div style="clear: both;"></div>
		<div class="owner_setup" style="height: 520px;">

			<p class="strong">Sharing file to multiple users</p>
			<form name="KnowStore" method="post" enctype="multipart/form-data">
				<table align="center">
					<%-- <%! FacilitatorKnowSetUpDomain fDomain=new  FacilitatorKnowSetUpDomain();%> --%>
					
					
					 <%if(session.getAttribute("AdminSuccess")!=null){ %>
					<p class="fadehide" style="color: green; text-align: center; margin-left: 54px;">
					<%=session.getAttribute("AdminSuccess") %>
					</p>
					<% } %>
					
				 <%if(session.getAttribute("AdminFtpSuccess")!=null){ %>
					<p class="fadehide" style="color: green; text-align: center; margin-left: 54px;">
					<%=session.getAttribute("AdminFtpSuccess") %>
					</p>
					<% } %>
					
					
					<tr>
						<td>Know Store ID</td>
						<td>:</td>
						<td><select name="ksid" id="ksid">
								<option value="default">--select Know Store ID--</option>
								<%
									FacilitatorSelectKsidKStoreDao dobj2 = new FacilitatorSelectKsidKStoreDao();
									ArrayList<String> al1 = dobj2.fetchValue(orgid,userid);
									Iterator<String> itr = al1.iterator();
									while (itr.hasNext()) {
										String ksid = itr.next();
								%>
								<option value="<%=ksid%>"><%=ksid%></option>
								<%
									}
								%>
						</select></td>
					</tr>

					<tr>
						<td>Department</td>
						<td>:</td>
						<td><input type="text" name="departments" id="departments"
							placeholder="Enter Department"></td>
					</tr>

					<tr>
						<td>Subject</td>
						<td>:</td>
						<td><input type="text" name="subjects" id="subjects"
							placeholder="Enter Subject Name"></td>
					</tr>
					<tr>
						<td>Description</td>
						<td>:</td>
						<td><textarea name="description" rows="6" cols="8" id="description"
								placeholder="Write Description Here"></textarea></td>
					</tr>
					<tr>
						<td>File</td>
						<td>:</td>
						<td><input id="brows" style="width: 200px; color: #fff;"
							type="file" name="upload" /></td>
					</tr>
                     <tr>
					<td>Select ID</td>
					<td>:</td>
					<td><select name="usersid" id="usersid" multiple>
							<option value="default">--select recipient ID--</option>
							<%
								FacilitatorSelectUsersIDDao dobj3 = new FacilitatorSelectUsersIDDao();
								ArrayList<String> al2 = dobj3.fetchValue(orgid);
								Iterator<String> itr1 = al2.iterator();

								while (itr1.hasNext()) {
									String usersID = itr1.next();
							%>
							<option value="<%=usersID%>"><%=usersID%></option>
							<%
								}
							%>
					</select></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td style="padding-top: 10px;"><input class="submit_btn"
							style="width: 136px;" type="button" value="Share Now"
							onclick="validation3()"> <a class="back"
							style="color: #c2c2c2; text-align: left;" href="../JSP/Home.jsp">Back</a>
						</td>
					</tr>
				</table>
				<p class="cre_p"></p>
			</form>
			
		</div>
		<%@ include file="../JSP/all_one_footer.jsp" %> 
	</div>
</body>
</html>