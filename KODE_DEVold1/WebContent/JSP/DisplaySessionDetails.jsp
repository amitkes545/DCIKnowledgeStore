<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.kds.KODE_DEV.domain.SessionDomain"%>
      <%@page import="com.kds.KODE_DEV.domain.AdminDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.CreateSessionDao" %>
    <%@page import="java.util.Iterator" %>
     <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>

<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript">
function getSelectedTextBox(student_id){
	
	//alert("hello");
	var selectedText=student_id.options[student_id.selectedIndex].innerHTML;
	var selectedValue = student_id.value;
	document.getElementById("recipient").value=selectedValue;
	//alert("selected value:"+selectedValue);
}
</script>
<script type="text/javascript">
function getSelecTextBox()
{
	//alert("hii");
	var all=document.getElementById("r3").value;
	document.getElementById("recipient").value=all;
	}
</script>

<script>
$(document).ready(function () { 
	$(".group_tr").hide();
	 $(".select_tr").hide();

	 $("#r1").click(function() {
	 $(".select_tr").show();
	 $(".group_tr").hide();
	 });
	
	
	$("#r2").click(function() {
		 $(".group_tr").show();
		 $(".select_tr").hide();
		 });

	$("#r3").click(function() {
		 $(".group_tr").hide();
		 $(".select_tr").hide();
		 });

	});
</script>

<style type="text/css">
.search_details tr td:last-child > input
{
width: 90%;
}
.search_details tr td:last-child > select
{
width: 97%;
}
</style>
</head>
<%!SessionDomain sessionDomain = new SessionDomain();%>
	<%
	sessionDomain = (SessionDomain) session.getAttribute("SesssionDetails");
		String adminid = sessionDomain.getSessionId();
		/* HttpSession mess = request.getSession();
		String msg = ""; */
		//msg = (String) mess.getAttribute("MsgValue");
		String orgid=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		//System.out.println("organization id:"+orgid+"created id:"+userid);
	%>
<body>
<div class="container">
<%@include file="all_one_header_knowstore.jsp"%>
			  <%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> --%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="faculty_mod">

 <p class="strong">Session Details</p>
	<form action="/KODE_DEV/ControllerServlet/UpdateSessionServlet" method="post">
	
		<table align="center">
			<tr>
				<td>ID</td><td>:</td>
				<td>
					<%
						if (sessionDomain == null) {
					%> <input type="text" name="sid" id="sid" class="box_lng"
					value=""> <%
 	} else {
 %> <input type="text" name="sid" class="box_lng"
					id="sid" value="<%=sessionDomain.getSessionId()%>" readonly="readonly">
				</td>
			</tr>
			<%
              }
			%>
			<tr>
				<td>Session Name</td><td>:</td>
				<td><input type="text" name="sname" id="sname" class="box_lng"
					value="<%=sessionDomain.getSessionName()%>"></td>
			</tr>
			
			<tr>
				<td>Category</td><td>:</td>
				<td><input type="text" name="category" id="category" class="box_lng"
					value="<%=sessionDomain.getCategory()%>" readonly="readonly"></td>
			</tr>
			<tr>
			<td>Cost of Session</td><td>:</td>
				<td><input type="text" name="cost" id="cost" class="box_lng"
					value="<%=sessionDomain.getCostOfSession()%>"></td>
			</tr>
			<tr>
			<td>Faculty Name</td><td>:</td>
				<td><input type="text" name="fid" id="fid" class="box_lng"
					value="<%=sessionDomain.getFacultyId()%>" readonly="readonly"></td>
			</tr>
			<tr>
			<td>Organization ID</td><td>:</td>
				<td><input type="text" name="orgid" id="orgid" class="box_lng"
					value="<%=sessionDomain.getOrgId()%>" readonly="readonly"></td>
			</tr>
			<%String recipientType=sessionDomain.getRecipientType();
			
			%>
			<tr><td>ToSend</td><td>:</td>
			<%if(recipientType.contains("#")){ %>
			<td class="r123">
			<input id="r1" type="radio" value="Individual" name="group">Individual 
             <input id="r2" type="radio" value="Group" name="group" checked="checked">Group
             <input id="r3" type="radio" value="All" name="group" onchange="getSelecTextBox()">All
			</td>
			<%} else {%>
			<td class="r123">
			<input id="r1" type="radio" value="Individual" name="group" checked="checked">Individual 
             <input id="r2" type="radio" value="Group" name="group">Group
             <input id="r3" type="radio" value="All" name="group" onchange="getSelecTextBox()">All
			</td>
			<%} %>
			
 
 <!-- <input id="r1" type="radio" value="Individual" name="group">Individual 
 <input id="r2" type="radio" value="Group" name="group">Group
 <input id="r3" type="radio" value="All" name="group" onchange="getSelecTextBox()">All
 </td> -->
 <td class="r123"></td>
 <td class="r123"></td>
 </tr>

  <tr class="select_tr">
 
 
						<td align="left">
							<font>Student ID </font>
						</td>
						
						<td>:</td>
						<td>
							 <% CreateSessionDao asdao=new CreateSessionDao();
		
                             ArrayList<AdminDomain> sl=new ArrayList<AdminDomain>();
                             sl=asdao.sendIndualGroup(userid,orgid);
		                      Iterator<AdminDomain> it1= sl.iterator();%>
		                      <select name="student_id" id="student_id" onchange="getSelectedTextBox(this)">
		                      <option value="select">Select Student ID</option>
		                    <% while(it1.hasNext())
		                     {
		    	             String id=it1.next().getAdminId();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	                
						</td>
					</tr>
					 <tr class="group_tr">
						<td align="left">
							<font>Group ID</font>
						</td>
						<td>:</td>
						
						<td>
							 <% 
		
                             ArrayList<AdminDomain>al1=new ArrayList<AdminDomain>();
		                   al1=asdao.sendGroupId(userid,orgid);
		                      Iterator<AdminDomain> it2= al1.iterator();%>
		                      <select name="group_id" id="group_id" onchange="getSelectedTextBox(this)">
		                      <option value="select">Select Group ID</option>
		                    <% while(it2.hasNext())
		                     {
		    	             String id=it2.next().getGroup_name();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	                
						</td>
					</tr>
					 <tr>
			<td>Recipient Type</td><td>:</td>
				<td><input type="text" name="recipient" id="recipient" class="box_lng"
					value="<%=sessionDomain.getRecipientType()%>" readonly="readonly"></td>
			</tr>
					
					<tr>
					<td></td>
					<td></td>
					<td>
					<input class="add_btn1" type="submit" name="update" value="Update" style="width:282px!important">
					<!-- <a class="	" style="color: #c2c2c2;" style="margin-left: 15px;" href="../JSP/ManageSessionSearch.jsp">Back</a>
				 -->	</td>
					</tr>
					
					
					
			<!-- <tr><td><input type="submit" name="update" value="Update"></td>
			
			<td><a href="../JSP/ManageSessionSearch.jsp">Back</a> -->
			</table>
			</form>
			
		
	
		</div>

<%@include file="all_one_footer.jsp" %>	

</div>

</body>
</html>