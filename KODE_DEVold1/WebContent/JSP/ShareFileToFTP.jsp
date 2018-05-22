<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.kds.KODE_DEV.domain.AdminDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.CreateSessionDao" %>
    <%@page import="java.util.Iterator" %>
     <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
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
	function clicked(){
		var file1=document.getElementById("file").value;
		if(file1 == ""){
			alert("choose any file");
			return false;
		}else {
			document.sharefile.action="/KODE_DEV/ControllerServlet/ShareFileToFTPServlet";
			document.sharefile.submit();
		}
	}
	
	$(document).ready(function() {
		$(".autohide").delay(5000).fadeOut("slow");
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
</head>
<style type="text/css">
		.su{
		    color: #008000;
    font-size: 12px;
    font-weight: bold;
    top: 18%;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
.su1{
		    color: #008000;
    font-size: 12px;
    font-weight: bold;
    top: 14%;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
		
		
		</style>
<%
	
		String message = (String) session.getAttribute("MsgValue");
		String orgid=(String)session.getAttribute("orgid");
		String facultyid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		//System.out.println("organization id:"+orgid+"created id:"+facultyid);
		
		//}
	%>
<body>

<div class="container">
		<%@include file="all_one_header_knowstore.jsp"%>
			
			 <%--  <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> --%>

		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="faculty_mod">

 <p class="strong">Share files to FTP server</p>
 <!-- action="/KODE_DEV/ControllerServlet/ShareFileToFTPServlet" -->
<form name="sharefile" method="post"  enctype="multipart/form-data">

<table>
<%if (request.getAttribute("FacultySuccessMail")!= null) { 
				String msg2=(String)request.getAttribute("FacultySuccessMail");
			/* 	 String str[]=msg2.split("#");
				 String valid=str[0];
				 String valid1=str[1];
			 */	%>
		<%-- 		<p class="su autohide"><%= valid %></p><br> --%>
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
			<tr>
					<!-- 	<td>Session Name</td>
						<td>:</td> -->
						
						<td>
							 <% 
							 CreateSessionDao createSessionDao=new CreateSessionDao();
                             ArrayList<String> arryList=new ArrayList<String>();
                             arryList=createSessionDao.retriveSessionName(facultyid,orgid);
		                      Iterator<String> iterator1= arryList.iterator();%>
		                      <select name="sessionName"class="box_lng">
		                      <option value="select">--select--</option>
		                    <% while(iterator1.hasNext())
		                     {
		    	             String id=iterator1.next();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	                
						</td>
					</tr>
<tr>
<!-- <td>Select File</td>
<td>:</td> -->
<td><input type="file" name="file" id="file" style="padding:7px;width:251px"></td></tr>

<tr><!-- <td>To Send</td><td>:</td> -->
 <td class="r123">
 <input id="r1" type="radio" value="Individual" name="group">Individual 
 <input id="r2" type="radio" value="Group" name="group">Group
 <input id="r3" type="radio" value="All" name="group">All
 </td>
 <td class="r123"></td>
 <td class="r123"></td>
 </tr>
  <tr class="select_tr" style="display: none;">
	<!-- <td>Student ID</td>
	<td>:</td> -->
	<td>
							 <%
		
                             ArrayList<AdminDomain> sl=new ArrayList<AdminDomain>();
		                   sl=createSessionDao.sendIndualGroup(facultyid,orgid);
		                      Iterator<AdminDomain> iterator= sl.iterator();%>
		                      <select name="student_id" class="box_lng">
		                      <option value="select">--select--</option>
		                    <% while(iterator.hasNext())
		                     {
		    	             String id=iterator.next().getAdminId();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	                
						</td>
					</tr>
					 <tr class="group_tr" class="box_lng" style="display: none; ">
						<!-- <td>
							Group ID
						</td>
						<td>:</td> -->
						
						<td>
							 <% 
		
                             ArrayList<AdminDomain>al1=new ArrayList<AdminDomain>();
		                   al1=createSessionDao.sendGroupId(facultyid,orgid);
		                      Iterator<AdminDomain> it2= al1.iterator();%>
		                      <select name="group_id" style="width: 100%">
		                      <option value="select">--select--</option>
		                    <% while(it2.hasNext())
		                     {
		    	             String id=it2.next().getGroup_name();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	                
						</td>
					</tr>
										
					<tr>
					<td>
					<input style="width: 265px;" class="add_btn1" type="submit" name="send" value="send" onclick="clicked()" >
					<!-- <a class="back" style="color: #c2c2c2;" style="margin-left: 15px;" href="../JSP/Home.jsp">Back</a> -->
					<input type="hidden" name="facultyid" value=<%= facultyid %> />
					<input type="hidden" name="orgid" value=<%= orgid %> />
					
					
					
<!-- 					
					<input style="width: 100px; margin-left: -30px;" class="search_search_btn" type="submit" value="search">
 -->					
					</td>
					</tr>
</table>
</form>
</div>
<%@include file="all_one_footer.jsp" %>	
</div>
</body>
</html>