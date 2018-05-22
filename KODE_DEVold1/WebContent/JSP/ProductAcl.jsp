<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.CreateDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.AccessDao" %>
    <%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
		<link href="../CSS/global.css" rel="stylesheet"></link>
		<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
</head>
<script type="text/javascript">
function validateProductAcl(){
	var orgid=document.getElementById("orgid").value;
	var privilege=document.getElementById("privilege").value;
	if(orgid == ""){
		alert("Select Institute ID");
		return false;
	}else if(privilege == ""){
		alert("Select Privilege");
		return false;
	}else {
		document.productAcl.action="/KODE_DEV/ControllerServlet/CreateProductAcl";
		document.productAcl.submit();
	}
}
</script>
<%
		/* HttpSession mess = request.getSession();
		String msg = ""; */
		String orgid=(String)session.getAttribute("orgid");
		//msg = (String) session.getAttribute("msgvalue");
		String username=(String)session.getAttribute("username");
		
	%>
<body>

<div class="container">
		
			
			  <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>

		<div style="clear: both;"></div>
		<div class="search_details" style="height: 235px;">

 <p class="strong">ProductAcl</p>
<!--  action="/KODE_DEV/ControllerServlet/CreateProductAcl" -->
<form  name="productAcl" method="post">
     <table align="center">
     <%
			 
				if (request.getAttribute("msgvalue")!= null) { 
					%>
					<%= request.getAttribute("msgvalue") %>					
					<%
				} 
				
			%>
   
					<tr>
     <td>Institute Name <span style="color: #fff; margin: 0px 5px;">:</span></td>
        <td>
							 <%AccessDao sdao= new AccessDao();
                              ArrayList<CreateDomain>ad=new ArrayList<CreateDomain>();
							    ad=sdao.selectAccessId();
							    Iterator<CreateDomain> it= ad.iterator();%>
							    <select name="orgid" id="orgid" >
							    <option value="">--Select Institute Name--</option>
							    <% while(it.hasNext())
							    {
							    	CreateDomain domain=it.next();
							    	String organizationId=domain.getOrg_id();
							    	String organizationName=domain.getOrg_name();
							    	String idAndName=organizationId+" ("+organizationName+")";%>
							    <option value="<%= organizationId %>"><%=idAndName%></option>	
							   <% }%>
							   </select>
						</td>
					</tr>
					 <tr>
					<td>Privilege<span style="color: #fff; margin: 0px 5px;">:</span>
					
					<td><select name="privilege" id="privilege">
					          <OPTION value="" selected>- Select privilege -</OPTION>
					         <!--  <option value="Client">client</option> -->
                             <option value="SuperAdmin">SuperAdmin</option>
                          <option value="Admin">Admin</option>
                         <option value="Faculty">Faculty</option>
                         <option value="Student">Student</option>
                    </select></td>
				</tr>
				 <tr height="20px;"></tr>

				 <tr>
						<td></td>
						<td style=""><input style="width: 100px; margin-left: -30px;"
							class="search_search_btn" type="button" value="search" onclick="validateProductAcl()"> <a
							class="back" style="color: #c2c2c2;" style="margin-left: 15px;"
							href="../JSP/Home.jsp">Back</a></td>
					</tr>
				
				 
				 
				</table>
				</form>
			
		
	
		</div>

<%@include file="all_one_footer.jsp" %>	

</div>



   

</body>
</html>
     