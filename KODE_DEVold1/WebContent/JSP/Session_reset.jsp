<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kds.KODE_DEV.dao.SessionDao"%>
<%@page import="com.kds.KODE_DEV.domain.SessionDomain"%>
<%@page import="com.kds.KODE_DEV.dao.SessionDao"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>

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
<%
	
	String	message=(String)request.getAttribute("message");
	String message1=(String)request.getAttribute("Loginstatus");
	//System.out.println("message1 value:"+message1);
	//System.out.println("message info:"+message);
		String username=(String)session.getAttribute("username");
		//System.out.println("user name:"+username);
		String userid=(String)session.getAttribute("userid");
		String orgid=(String)session.getAttribute("orgid");
		String[] StusentId=(String[])session.getAttribute("result");
		String sResult=(String)request.getAttribute("msg");
		String sesid=(String)session.getAttribute("sesId");
		String stuid=(String)session.getAttribute("stuID");
		//System.out.println("sesid in jsp:"+sesid+ "stuid in jsp:"+stuid+ "orgid in jsp:"+orgid);
		%>
		
		<script>
		function onload(){
			<%String sessionId=(String)session.getAttribute("sesId");
			////System.out.println("sessionid in an onload:"+sessionId);
			if(sessionId!=null && sessionId.length()>0) {
			//	//System.out.println("session id in onload:"+sessionId);
			 %> 
			 var selectBox = document.getElementById("sessionid");
			 selectBox.value='<%=sessionId%>';
			 for(var i=0;i<selectBox.options.length;i++){
				 if(selectBox.options[i].value == '<%=sessionId%>')
				{
		
		 selectBox.options[i].selected=true;
					 break;
				 }
			 }
					
			<%}%>
			<%--  <%if(studentId!=null){
	             //System.out.println("student id in onload:"+studentId);%>
	             var selectBox1=document.getElementById("studentid");
	             for(var i=0;i<selectBox1.options.length;i++){
	            	 if(selectBox1.options[i].value == '<%=studentId%>'){
	            		 selectBox1.options[i].selected=true;
	            	 }
	             }
	                <%}%> --%>
		}
		function onloadStudentId(){
			<%String studentId=(String)session.getAttribute("stuID");
			   //System.out.println("student id in onload:"+studentId);
			   if(studentId!=null){
		             //System.out.println("student id in onload:"+studentId);%>
		             var selectBox1=document.getElementById("studentid");
		             for(var i=0;i<selectBox1.options.length;i++){
		            	 if(selectBox1.options[i].value == '<%=studentId%>'){
		            		 selectBox1.options[i].selected=true;
		            	 }
		             }
		                <%}%> 
		}
		
/* function mfunction1()
{
	var sesid=document.getElementById("sessionid").value;
	
	if(sesid==""){
	    alert("Select Session ID");
	    return false;
	
	}else{
		
		 document.sessionform.action ="/KODE_DEV/ControllerServlet/SessionResetServlet";
		 document.sessionform.submit(); 
	}
} */
function clickSessionId(){
	var sesId=document.getElementById("sessionid").value;
	document.sessionform.action ="/KODE_DEV/ControllerServlet/SessionResetServlet?sesID="+sesId+"";
	document.sessionform.submit();
	
}
function clickStudent(){
	var studentid=document.getElementById("studentid").value;
	//alert(studentid);
	document.sessionform.action ="/KODE_DEV/ControllerServlet/SessionResetServlet?studentId="+studentid+"";
	document.sessionform.submit();
}
function clickSubmit(){
	var sesid=document.getElementById("sessionid").value;
	var studentid=document.getElementById("studentid").value;
	if(sesid == ""){
		alert("Select Session ID");
		return false;
	}else if(studentid == null){
		alert("Select Student ID");
		return false;
	}
	else {
		document.sessionform.action ="/KODE_DEV/ControllerServlet/SessionResetServlet";
		document.sessionform.submit();
	}
}
</script>
<body>
	<div class="container">
<%@include file="all_one_header.jsp"%>
		<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>  --%>

		<div style="clear: both;"></div>
		<div class="search_details" style="height: 270px; width: 421px">

			<p class="strong_left">Session Reset</p>
			<!-- action="/KODE_DEV/ControllerServlet/SessionResetServlet" -->
			<form name="sessionform" method="post">
          <% if (request.getAttribute("OwnerSuccess")!=null) { 
					//System.out.println(" owner success message value in if:"+request.getAttribute("OwnerSuccess"));
					%>
					<p class="su"><%= request.getAttribute("OwnerSuccess") %></p>					
					<%
				} else  if (request.getAttribute("OwnerFailure")!= null) { 
					
					%>
					<p style="color:red; font-size:16px; font-weight: bold; top: 22%; left: 30%; position: absolute;"><%= request.getAttribute("OwnerFailure") %></p>					
					<%
				} %>
				<table>
					<tr>
						<td style=" float: left;line-height: 45px; width: 170px;">Session ID 
						<span style="color: #fff; margin: 0px 25px;">:</span></td>
						<td>
							<%SessionDao dao= new SessionDao();
					           ArrayList<SessionDomain>al=new ArrayList<SessionDomain>();
							    al=dao.retriveSessionId();
							    Iterator<SessionDomain> it= al.iterator();
							   %> 
							   <!--  onchange="document.getElementById('sessionid').value=this.options[this.selectedIndex].text;" -->
							    <select name="sessionid" id="sessionid" onchange="clickSessionId();">
							     <option value="">--Select Session ID--</option> 
								<% while(it.hasNext())
							    {
							  String id=((SessionDomain)it.next()).getSessionId();%> 
								<option id="ddd" value="<%= id %>"><%=id%></option>
								<% }%>
								 <%-- <%if(sesid!=null && sesid.length()>0) %>
								<option  value=""><%=sessionid%></option> --%>
						 
						</select>
						</td>
					    </tr>
					    <tr>
					    <td style=" float: left;line-height: 45px; width: 170px;">Student ID<span style="color: #fff; margin: 0px 25px;">:</span></td>
					    <td><select id="studentid" name="studentid" onchange="clickStudent()">
					    <option value="">--Select Student ID--</option> 
					 <!--  <script type="text/javascript">print_studentid("studentid");</script>  -->
					 <% if(StusentId!=null){
					    for(String s1:StusentId){%> 
					    <option value="<%=s1 %>"><%=s1 %></option> 
					    <%} }%> 
					   </select>
					    </td></tr>
					   <%if (request.getAttribute("message")!= null) { 
					//System.out.println("message value in if:"+request.getAttribute("message"));
					%>
				      <p style="color:red; font-size:19px; font-weight: bold; top: 16%; left: 16%; position: absolute;"><%= request.getAttribute("message") %></p>				
					<%
				} else if(request.getAttribute("Loginstatus")!=null) {  %> 
				     <%if (message1.equalsIgnoreCase("Student not login this session")) { %> 
					    <tr><td>Status</td>
					    <td><select id="status" name="status">
					    <option value=""selected>--Select Status--</option>
					    <option value="Allow">Allow</option>
					    <option value="Block">Block</option>
					    </select></td></tr>
					    <%}} %>
					   <%--  <%} %> --%>
					    <tr height="15px"></tr>
					    <tr>
						<td></td>
						<td>
						<input style="width: 90px; margin-right:10px; float: left;" type="button" id="block" class="submit_btn"  value="Submit" onclick="clickSubmit()"/>
						<input type="hidden" name="sesid" value="<%=sesid%>">
						<input type="hidden" name="stuid" value="<%=stuid %>">
						<a style="margin-left: -65px; color: #c2c2c2; position: absolute;margin-top: 15px;" href="../JSP/Home.jsp">Back</a>
						</td>
						<td align="left"></td>

					</tr>
				</table>
			</form>
</div>
		<%@include file="all_one_footer.jsp"%>
	</div>
</body>
<script>
onload();
onloadStudentId();
</script>

</html>