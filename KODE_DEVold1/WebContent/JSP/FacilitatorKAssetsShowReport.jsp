<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@page import="com.kds.KODE_DEV.domain.FacilitatorKAssetsReportDomain"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/FacilitatorKnowSetup.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>

<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
		
<script type="text/javascript">
function validation3(){
	if(valid())
	{
		document.KnowStore.action="/KODE_DEV/ControllerServlet/FacilitatorKAssetsReportServlet.java";
		document.KnowStore.submit();
	}
}
</script>
<style type="text/css">
.owner_setup tr td{color: #fff !important; padding: 5px;}

</style>
</head>

<%
	//HttpSession mess = request.getSession();
	String msg = "";
	String username=session.getAttribute("username").toString();
	String	userid=session.getAttribute("userid").toString();
	//System.out.println("userid is:"+userid);
	String orgid=session.getAttribute("orgid").toString();
	%>
<body>
	
		<div class="container">
		 <%@include file="all_one_header_knowstore.jsp"%> 
		

<div style="clear: both;"></div>
<div>
		<%@ include file= "../JSP/menu.jsp" %>
		</div>

		<!-- <div class="faculty_mod"> -->
		<div class="owner_setup_faculty">
			<p class="strong">View</p>	
	
              <%!FacilitatorKAssetsReportDomain rDomain = new FacilitatorKAssetsReportDomain();%>
	          <%
		      rDomain =(FacilitatorKAssetsReportDomain)request.getAttribute("MsgValue");
	          %>
	          
           <form>
           <table>
				<tr>
				<td>KnowledgeStore ID</td>
				<td>:</td>
				<td><%=rDomain.getKsId()%></td>
				</tr>
				<tr>
				<td>Department</td><td>:</td>
				<td><%=rDomain.getDepartment()%></td>
				</tr>
				<tr>
				<td>Subject</td><td>:</td>
				<td><%=rDomain.getSubject()%></td>
				</tr>
				<tr>
				<td>Description</td><td>:</td>
				<td><%=rDomain.getDescription()%></td>
				</tr>
				<%-- <tr>
				<td>Uploaded By</td><td>:</td>
				<td><%=rDomain.getUploadedBy()%></td>
				</tr> --%>
				<tr>
				<td>File Name</td><td>:</td>
				<td><%=rDomain.getFileName()%></td>
				</tr>
				<%-- <tr>
				<td>File Type</td>
				<!-- <td>:</td> -->
				<td><%=rDomain.getFileType()%></td>
				</tr> --%>
				<tr>
				<td>File Size</td><td>:</td>
				<td><%=rDomain.getFileSize()+" "+"GB"%></td>
				</tr>
				<tr>
				<td>Uploaded Date</td><td>:</td>
				<td><%=rDomain.getUploadDate()%></td>
				</tr>
				<tr>
				<td>Uploaded Time</td><td>:</td>
				<td><%=rDomain.getUploadTime().getHours()%>:<%=rDomain.getUploadTime().getMinutes()+" "+"min"%></td>
			
				</tr>
				<%-- <tr>
				<td>File Path</td><td>:</td>
				<td><%=rDomain.getFilePath()%></td>
				</tr> --%>
				<tr>
				<td>Status</td><td>:</td>
				<td><%=rDomain.getStatus()%></td>
				</tr>
							
			</table>
			<!-- <p></p>
			<p style="text-align: center; margin-left: -90px; padding-top: 10px">
			<a class="back" style="color: #c2c2c2; text-align: left;" href="../JSP/FacilitatorKAssetsReports.jsp">Back</a></p> -->
	</form>
	</div>
	
	
	</div>
	<%@ include file="../JSP/all_one_footer.jsp" %>
</body>
</html>