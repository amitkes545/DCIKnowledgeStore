<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.dao.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="../JS/FacilitatorFileShareOnFtpKnow.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>

<script type="text/javascript">
	function validation3() {
		if (validFacilitator()) {
			document.KnowStore.action = "/KODE_DEV/ControllerServlet/FacilitatorFileShareKnowStoreServlet";
			document.KnowStore.submit();
		}
	}
</script>
</head>
<%
	//HttpSession mess = request.getSession();
	//String msg = "";
	//String msg1 = "";
	String username = (String) session.getAttribute("username");
	String userid = (String) session.getAttribute("userid");
	String orgid = (String) session.getAttribute("orgid");
	//System.out.println(orgid);
%>

<body>
	<div class="container" style="height: 800px">
		<%-- <%@ include file="../JSP/all_one_header.jsp" %> --%>
		<jsp:include page="../JSP/all_one_header_knowstore.jsp">
			<jsp:param name="username" value="<%=username%>" />
		</jsp:include>
		
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menu.jsp" %>
		</div>
		<div class="faculty_mod">

			<p class="strong">File For Multiple Users</p>
			
			<% if(session.getAttribute("FacultySuccess")!=null){ %>
			<p class="fadehide" style="color: green; text-align: center; margin-left: 54px;">
			<%=session.getAttribute("FacultySuccess") %>
			</p>
			<% } %>
			<form name="KnowStore" method="post" enctype="multipart/form-data">
				<table align="center">
					<%-- <%! FacilitatorKnowSetUpDomain fDomain=new  FacilitatorKnowSetUpDomain();%> --%>
					<tr>
						<!-- <td>Know Store ID</td>
						<td>:</td> -->
						<td><select name="ksid" id="ksid" class="box_lng">
								<option value="default">--select Know Store ID--</option>
								<%
									FacilitatorSelectKsidKStoreDao dobj2 = new FacilitatorSelectKsidKStoreDao();
									ArrayList<String> al1 = dobj2.fetchValue(orgid,userid);
									Iterator<String> itr = al1.iterator();
									while (itr.hasNext()) {
										String ksId = itr.next();
										String userId = itr.next();
										String KsIdAndUserId = (ksId+" ("+userId+")");
								%>
								<option value="<%=ksId%>"><%=KsIdAndUserId%></option>
								<%
									}
								%>
						</select></td>
					</tr>

					<tr>
						<!-- <td>Department</td>
						<td>:</td> -->
						<td><input type="text" name="departments" id="departments"
							placeholder="Enter Department"></td>
					</tr>

					<tr>
						<!-- <td>Subject</td>
						<td>:</td> -->
						<td><input type="text" name="subjects" id="subjects"
							placeholder="Enter Subject Name"></td>
					</tr>
					<tr>
						<!-- <td>Description</td>
						<td>:</td> -->
						<td><textarea name="description" rows="6" cols="8" id="description"
								placeholder="Write Description Here"></textarea></td>
					</tr>
					<tr>
						<!-- <td>File</td>
						<td>:</td> -->
						<td><input id="brows" style="width: 250px; color: #000;"
							type="file" name="upload" /></td>
					</tr>
                     <tr>
					<!-- <td>Select ID</td>
					<td>:</td> -->
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
							<td><input class="add_btn1"
							style="width: 265px;" type="button" value="Share Now"
							onclick="validation3()"> <!-- <a class="back"
							style="color: #c2c2c2; text-align: left;" href="../JSP/Home.jsp">Back</a> -->
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