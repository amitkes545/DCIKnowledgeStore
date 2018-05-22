<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.kds.KODE_DEV.domain.AdminDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.CertiftStudentDao" %>
    <%@page import="java.util.Iterator" %>
     <%@page import="java.util.ArrayList" %>
     <%@page import="java.io.File" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<link href="../CSS/kedu.css" rel="stylesheet"/>
</head>
<script type="text/javascript">
function clickAssessmentID(){
	var assessid=document.getElementById("asID").value;
	if(assessid == "" || assessid == null){
		alert("Select Assessment ID");
		return false;
	}else {
		//alert("else");
		document.assessForm.action="/KODE_DEV/ControllerServlet/StudentReportsServlet";
		document.assessForm.submit();
	}
}
</script>
<%
/* HttpSession mess = request.getSession();
String msg = "";

	String username=(String)mess.getAttribute("username"); */
   String facultyid=(String)session.getAttribute("userid");
   String orgid=(String)session.getAttribute("orgid");%>
<body>
<div class="container">
		 <%@include file="headers.jsp"%> 
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%=username%>" /> 
         </jsp:include> --%>
         
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		
		<div class="owner_setup_faculty">
		<p class="strong">Students Reports</p>
		<form  name="assessForm" method="post">

<table>
<tr class="select_tr">
		<!-- <td align="left">
							<font>Assessment ID </font>
		</td>
		<td>:</td> -->
		<td>
							 <% CertiftStudentDao asdao=new CertiftStudentDao();
		
                             ArrayList<AdminDomain>al=new ArrayList<AdminDomain>();
		                   al=asdao.assessMentReportsDetails();
		                      Iterator<AdminDomain> it= al.iterator();%>
		                      <select name="asID" class="box_lng" id="asID" onfocus='this.size=5;' onblur='this.size=1;' onchange="this.size=1; this.blur();">
		                      <option value="">--select Assessment ID--</option>
		                    <% while(it.hasNext())
		                     {
		    	             String id=it.next().getAssignment_name();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	                
		</td>
</tr>
	
<tr class="select_tr">
						
						<td><input type="submit"  class="add_btn1" name="submit" value="Submit" onclick="clickAssessmentID()"></td>
						<td><!-- <a href="../JSP/Home.jsp">Back</a> -->
						</td>
</tr>
					
					
</table>
</form>
 
    	</div>	


<%@ include file="../JSP/FooterViews.jsp"%>   
</div>
    
</body>
</html>