<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="../CSS/owner.css" rel="stylesheet"></link>
		<link href="../CSS/global.css" rel="stylesheet"></link>
		<link rel="shortcut icon" href="Image/title_icon.ico" type="image/x-icon">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
		<script>
		$(document).ready(function(){ 
			 
			setTimeout(function() {
			    $('.su').fadeOut('slow');
			}, 6000);	
		});
		</script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
		<style type="text/css">
		.su{
    color: #468847;
    font-weight: bold;
    top: 60%;
    position: absolute;
    background: none repeat scroll 0% 0% #DFF0D8;
    left: 30%;
    border: 1px solid #D6E9C6;
    right: 30%;
    text-align: center;
    font-size: 18px;
    padding: 10px 0px;
    font-family: arial;
    border-radius: 10px;
}
.container{overflow: hidden;}
		</style>
	</head>
	<%
	
	/* HttpSession mess = request.getSession();	
    String msg = ""; */
    
	String username=(String)session.getAttribute("username");
	String	userid=(String)session.getAttribute("userid");
	//System.out.println("userid is:"+userid);
	String orgid=(String)session.getAttribute("orgid");
	
	//System.out.println("organization id:"+orgid);
	String message = (String) session.getAttribute("MsgValue");
	session.setAttribute("orgid", orgid);
	session.setAttribute("userid", userid);
	session.setAttribute("username",username);
	//}
%>
	<body>
	<div class="container">
		<article>
		<div style="clear: both;"></div>

		<jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%=username%>" /> 
         </jsp:include> 
			
			 <%if("owner".equalsIgnoreCase(message)) 
		    { %>
			<div class="dropdown">
				<button>KODE_DEV</button>
				<ul class="dropdown-menu">
				    <li><a href="../JSP/SetupCustomer.jsp">Setup Customer</a></li>
					<li><a href="../JSP/CreateCustomer.jsp">Create User</a></li>
					<li><a href="../JSP/Search.jsp">Manage Customer</a></li>
		
				</ul>
			</div> 
			<div class="dropdown1">
				<button>SuperAdmin</button>
				<ul class="dropdown-menu1">
				<!-- 	<li><a href="../JSP/SuperAdmin.jsp">Create</a></li> -->
					<li><a href="../JSP/SuperAdminSearch.jsp">Manage</a></li>
					<li><a href="../JSP/AccessSuperAdminOwner.jsp">Access</a></li>
					<li><a href="../JSP/SuperadminReports.jsp">Details</a></li>
				</ul>
			</div> 
			<div class="dropdown2">
				<button>Knowledge Store</button>
				<ul class="dropdown-menu2">
					<li><a href="../JSP/OwnerKnowSetup.jsp">Create</a></li>
					<li><a href="../JSP/OwnerKnowStoreManage.jsp">Manage</a></li>
					<li><a href="../JSP/OwnerKnowAccessControl.jsp">Access</a></li>
					<li><a href="../JSP/OwnerKnowReports.jsp">Details</a></li>
				</ul>
			</div> 
			
			<div class="dropdown3">
				<button>Management</button>
				<ul class="dropdown-menu3">
					<li><a href="../JSP/ProductAcl.jsp">Product ACL</a></li>
					<li><a href="../JSP/Reports.jsp">Details</a></li>
					<li><a href="../JSP/DatabaseBackup.jsp">Database Backup</a></li>
					<li><a href="../JSP/Session_reset.jsp">Session Management</a></li>
				</ul>
			</div>
			
			 <%
				if (request.getAttribute("OwnerSuccess")!= null) { 
					
					%>
					<p class="su"><%= request.getAttribute("OwnerSuccess") %></p>					
					<%
				} 
				else  if (request.getAttribute("OwnerFailure")!= null) { 
					
					%>
					<p style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("OwnerFailure") %></p>					
					<%
				} 
				
			%>
			<% } else if("superadmin".equalsIgnoreCase(message))
				{ %>
				<div class="dropdown">
				<button>Admin</button>
				<ul class="dropdown-menu">
					<li><a href="../JSP/Admin.jsp">Create</a></li>
					<li><a href="../JSP/AdminSearch.jsp">Manage</a></li>
					<li><a href="../JSP/AccessSuperAdmin.jsp">Access</a></li>
					<li><a href="../JSP/AdminReports.jsp">Details</a></li>
					
				</ul>
			</div> 
			<div class="dropdown1">
				<button>Management</button>
				<ul class="dropdown-menu1">
					<li><a href="../JSP/SuperadminProductAcl.jsp">Product ACL</a></li>
					<li><a href="../JSP/SuperadminAllReports.jsp">Details</a></li>
					<!-- <li><a href="../JSP/DatabaseBackup.jsp">Database Backup</a></li> -->
					<li><a href="../JSP/Session_reset.jsp">Session Management</a></li>
				</ul>
			</div>
			<div class="dropdown2">
				<button style="padding-left: 10px;">Knowledge Store</button>
				<ul class="dropdown-menu2">
					<li><a href="../JSP/SuperAdminKnowSetup.jsp">Create</a></li>
					<li><a href="../JSP/SuperAdminKnowManage.jsp">Manage</a></li>
					<li><a href="../JSP/SuperAdminKnowAccessControl.jsp">Access</a></li>
					<li><a href="../JSP/SuperAdminDataBackupKnowStore.jsp">Data Backup</a></li>
					<li><a href="../JSP/SuperAdminKnowReports.jsp">Details</a></li>
					
				</ul>
			</div> 
			
			<%
			 
				if (request.getAttribute("SuperAdminSuccess")!= null) { 
					
					%>
					<p class="su"><%= request.getAttribute("SuperAdminSuccess") %></p>					
					<%
				} else  if (request.getAttribute("SuperAdminFailure")!= null) { 
					
					%>
					<p style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("SuperAdminFailure") %></p>					
					<%
				} 	
			%>
				<% } 
				 else if("admin".equalsIgnoreCase(message))
				{ %>
				<div class="dropdown">
				<button>Facilitator</button>
				<ul class="dropdown-menu">
					<li><a href="../JSP/Faculty.jsp">Create</a></li>
					<li><a href="../JSP/FacultySearch.jsp">Manage</a></li>
					<li><a href="../JSP/AccessFaculty.jsp">Access</a></li>
					<li><a href="../JSP/Konnect.jsp">Konnect</a></li>
					<li><a href="../JSP/facilitatorReports.jsp">Details</a></li>
				</ul>
			</div> 
			<div class="dropdown1">
				<button style="padding-left: 10px;">Knowledge Store</button>
				<ul class="dropdown-menu1">
				<li><a href = "../JSP/AdminKnowSetup.jsp">Create</a></li>
        		<li><a href = "../JSP/AdminKnowManage.jsp">Manage</a></li>
        		<li><a href = "../JSP/AdminFileShareknowStore.jsp">Personalize</a></li>
        		<li><a href = "../JSP/AdminDataBackupKnowStore.jsp">Data Backup</a></li>
        		<li><a href = "../JSP/AdminKnowReports.jsp">Details</a></li>
				</ul>
			</div> 
			<%
				if (request.getAttribute("AdminSuccess")!= null) { 
					////System.out.println("message value in if:"+Facultymsg);
					%>
					<p class="su"><%= request.getAttribute("AdminSuccess") %></p>					
					<%
				} else  if (request.getAttribute("AdminFailure")!= null) { 
					
					%>
					<p style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("AdminFailure") %></p>					
					<%
				} 	
			%>
			<% }else if("faculty".equalsIgnoreCase(message))
			   { %>
				<div>
		      <%@ include file= "../JSP/menu.jsp" %>
		      </div>
				<!-- <div class="dropdown">
				<button>Participants</button>
				<ul class="dropdown-menu">
					<li><a href="../JSP/Student.jsp">Create</a></li>
					<li><a href="../JSP/CollaborateStudent.jsp">Create Group</a></li>
					<li><a href="../JSP/AccessStudent.jsp">Access</a></li>
					<li><a href="../JSP/Assessment.jsp">Assessment</a></li>
					<li><a href="../JSP/Assignment.jsp">Assignment</a></li>
					<li><a href="../JSP/CertifyStudent.jsp">Certify</a></li>
					<li><a href="../JSP/TrackStudent.jsp">Track</a></li>
					<li><a href="../JSP/StudentReports.jsp">Details</a></li>
					<li><a href="../JSP/ProductAcl.jsp">ProductACL</a></li>
					<li><a href="#">Reports</a></li>
				</ul>
			</div> 
			<div class="dropdown1">
				<button style="padding-left: 10px;">Knowledge Assets</button>
				<ul class="dropdown-menu1">
					<li><a href="../JSP/FacilitatorKnowSetup.jsp">Create KAsset</a></li>
					<li><a href="../JSP/FacilitatorKnowPublish.jsp">Publish</a></li>
					<li><a href="../JSP/FacilitatorKAssetsReports.jsp">Details</a></li>
				</ul>
			</div> 
			<div class="dropdown2">
				<button style="padding-left: 10px;">Knowledge Store</button>
				<ul class="dropdown-menu2">
					<li><a href="../JSP/FacilitatorBuildKnowLib.jsp">Build Library</a></li>
					<li><a href="../JSP/FacilitatorManageLib.jsp">Manage</a></li>
					<li><a href="../JSP/FacilitatorFileShareKnowStore.jsp">Share</a></li>
					<li><a href="../JSP/AdminDataBackupKnowStore.jsp">Data Backup</a></li>
					<li><a href="../JSP/FacilitatorKnowReport.jsp">Details</a></li>
				</ul>
			</div> 
			<div class="dropdown3">
				<button>Session Management</button>
				<ul class="dropdown-menu3">
					<li><a href="../JSP/CreateSession.jsp">Create</a></li>
					<li><a href="../JSP/PostPoneSession.jsp">Postpone</a></li>
					<li><a href="../JSP/ManageSessionSearch.jsp">Manage</a></li>
					<li><a href="../JSP/ShareFileToFTP.jsp">Share</a></li>
					<li><a href="../JSP/FacilitatorSessionCancel.jsp">Cancel</a></li>
					<li><a href="../JSP/FacilitatorGetSessionBackup.jsp">Session Backup</a></li>
					<li><a href="../JSP/FacilitatorSessionReport.jsp">Details</a></li>
					
				</ul>
			</div>  -->
			<%
				if (request.getAttribute("FacultySuccess")!= null) { 
					////System.out.println("message value in if:"+Facultymsg);
					%>
					<p class="su"><%= request.getAttribute("FacultySuccess") %></p>					
					<%
				} else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
					<p style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("FacultyFailure") %></p>					
             	<%
				} 	
			%>
			<%} %>
			
		</article>
		
		<div style="clear: both;"></div>
		<%@include file="all_one_footer.jsp" %>
		</div>
	</body>
</html>