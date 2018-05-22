<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.AdminDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.AssignmentDao" %>
    <%@page import="java.util.Iterator" %>
     <%@page import="java.util.ArrayList" %>
     <%@page import="java.io.File" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />

<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<link href="../CSS/kedu.css" rel="stylesheet"/>
</head>
<script type="text/javascript">
function clickAssignmentID(){
	var assignid=document.getElementById("assignID").value;
	if(assignid == "" || assignid == null){
		alert("Select Assignment ID");
		return false;
	}else {
		//alert("else");
		document.assignForm.action="/KODE_DEV/ControllerServlet/StudentReportsAssignServlet";
		document.assignForm.submit();
	}
}
</script>
<%
/* HttpSession mess = request.getSession();
String msg = ""; */

	String username=(String)session.getAttribute("username");
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
    <form  name="assignForm" method="post">
 
<table><tr class="select_tr"style="float: left;
    width: 100%;">
<!-- <td align="left">
							<font>Assignment ID </font>
						</td>
						
						<td>:</td> -->
						<td>
							 <% AssignmentDao asdao=new AssignmentDao();
		
                             ArrayList<AdminDomain>al=new ArrayList<AdminDomain>();
		                         al=asdao.assignMentReportsDetails();
		                      Iterator<AdminDomain> it= al.iterator();%>
		                      <select name="assignID"  class="box_lng" id="assignID" onfocus='this.size=10;' onblur='this.size=1;' onchange="this.size=1; this.blur();">
		                      <option value="">--Select Assignment ID--</option>
		                    <% while(it.hasNext())
		                     {
		    	             String id=it.next().getAssignment_name();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	                
						</td>
					</tr>
					<tr class="select_tr">
   
						
						<td><input type="submit"  class="box_lng" style="color:blue";  name="submit" value="Submit" onclick="clickAssignmentID()"></td>
					<td><!-- <a href="../JSP/Home.jsp">Back</a> -->
					</td>
					</tr>
</table>
</form>
    
    
    </div>
    
    </div>
	<%@ include file="../JSP/FooterViews.jsp"%>
</div>
</div>    
     
</body>
</html>