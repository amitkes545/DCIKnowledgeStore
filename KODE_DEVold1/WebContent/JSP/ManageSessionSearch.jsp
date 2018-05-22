<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.SessionDomain" %>
    
    <%@page import="com.kds.KODE_DEV.dao.CreateSessionDao" %>
    <%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link> -->
<script type="text/javascript" src="../JS/jquery.js"></script>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
</head>
<style type="text/css">
		.su{
		    color: #008000;
    font-size: 11px;
    font-weight: bold;
    top: 44px;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
.su1{
		    color: #008000;
    font-size: 17px;
    font-weight: bold;
    top: 53px;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}

		</style>
<script type="text/javascript">
function validateSession(){
	var sesid=document.getElementById("sessionname").value;
	if(sesid == ""){
		alert("Select Session Name");
		return false;
	}else {
		document.sesName.action="/KODE_DEV/ControllerServlet/ManageSessionServlet";
		document.sesName.submit();
	}
}

$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
});
</script>
<%
	/* HttpSession mess = request.getSession();
	String msg = ""; */
		String organizationId=(String)session.getAttribute("orgid");
		String username=(String)session.getAttribute("username");
		String userid=(String)session.getAttribute("userid");%>

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

 <p class="strong">Search Session Name</p>
			<!-- action="/KODE_DEV/ControllerServlet/ManageSessionServlet" -->
			<%if (request.getAttribute("FacultySuccessMail")!= null) { 
				String msg2=(String)request.getAttribute("FacultySuccessMail");
			/* 	 String str[]=msg2.split("#");
				 String valid=str[0];
				 String valid1=str[1];
			 */	%>
				<%-- <p class="su autohide"><%= valid %></p><br> --%>
				<p class="su1 autohide"><%=msg2 %></p>		
				<%
			}
			 
			else if (request.getAttribute("FacultySuccess")!=null) { 
					//System.out.println(" faculty success message value in if:"+request.getAttribute("FacultySuccess"));
					%>
					<p class="su autohide"><%= request.getAttribute("FacultySuccess") %></p>					
					<%
				} else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
					<p class="autohide" style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("FacultyFailure") %></p>					
					<%
				} 
				
			%>
<form name="sesName" method="post">
<center>
 <table>
   <tr>
						
							<!-- <td>Session Name</td>
							<td>:</td> -->
							<td><select name="sessionname" id="sessionname" class="box_lng" style="margin-right:20px" >
							<option value="">Select SessionName--</option>
						<%CreateSessionDao createSessionDao= new CreateSessionDao();
					           ArrayList<SessionDomain> al=new ArrayList<SessionDomain>();
					           al=createSessionDao.getSessionCategory(userid,organizationId);
					          /* HashSet set= new HashSet();
								set.addAll(al);
								al.removeAll(al);
								al.addAll(set);  */
				 
								Iterator<SessionDomain> iterator =al.iterator();
								while(iterator.hasNext())
									{
										SessionDomain domain=iterator.next();
										String sessionName=domain.getSessionName();
										String sessionId=domain.getSessionId();
										String IdAndName=sessionName+" ("+sessionId+")";
										////System.out.println("value of category"+sessionId);
							%>
										<option value="<%=sessionId%>"><%=IdAndName%></option>
									<%}%>
				    
											</select></td>
						
					</tr>
					
					<tr>
					<td>
					<input class="add_btn1" type="button" value="search" onclick="validateSession()" style="width:263px!important">
					<input type="hidden" name="facultyid" value="<%=userid %>">
					<!-- <a class="back" style="color: #c2c2c2;" style="margin-left: 15px;" href="../JSP/Home.jsp">Back</a> -->
					</td>
					</tr>
                  </table>
</center>
</form>		
</div>

<%@include file="all_one_footer.jsp" %>	

</div>
</body>
</html>