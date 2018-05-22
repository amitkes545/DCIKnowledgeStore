<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kds.KODE_DEV.domain.AdminDomain" %>
<%@page import="com.kds.KODE_DEV.dao.CollaborateDao" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../JS/ValidateCollaborate.js"></script>
<script type="text/javascript" src="../JS/addStudent.js"></script>
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<link href="../CSS/design-common.css" rel="stylesheet"></link>
<link href="../CSS/notification-new.css" rel="stylesheet"></link>
<link rel="shortcut icon" href="Image/title_icon.ico" type="image/x-icon">
<script src="../JS/jquery1.11.3.js"></script>
<script src="../JS/MessageFadeOut.js"></script>
</head>
<style type="text/css">
		.su{
		    color: #008000;
    font-size: 15px;
    font-weight: bold;
    top:6%;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
.faculty_mod tr td:last-child > input {
    width: 0px;
    margin: 5px 0px 6px;
    padding: 7px;
    border-radius: 3px;
    border: 1px solid #C2C2C2;
    font-family: regular;
}
.table_outer{
height: 250px;
overflow-y: auto;
margin: 15px;
border: 1px solid #c2c2c2;
top: 6px;
position: relative;
}
#display{text-align: center;color: red;position: relative; top:-11px;}
.collaborateStudent_mod
{
position: inherit;
top: 60px;
float:right;
box-shadow: 5px 5px 5px 5px #FFF;
border: 3px solid #C2C2C2;
background-color: #FCF7F7;
border-radius: 4px;
opacity: 0.9;
right: 20px;
margin:60px 0px 0px 0px;
}

.collaborateStudent_mod tr td:last-child > input {
    /* width: 0px; */
    margin: 5px 0px 6px;
    padding: 7px;
    border-radius: 3px;
    border: 1px solid #C2C2C2;
    font-family: regular;
}

.collaborateStudent_mod tr td:first-child {
    color: #000;
    font-size: 12px;
    font-family: sans-serif;
}
.collaborateStudent_mod tr td:nth-child(2) {
    color: #000;
    text-align: center;
    font-size: 12px;
}
.b{border: 1px solid red;}



		</style>
		
		<script type="text/javascript">
		$(document).ready(function() {
      $(function(){  
      $('.textnumber').keydown(function(e) {
            if (e.shiftKey || e.ctrlKey || e.altKey) {
              e.preventDefault();
            } else {
              var key = e.keyCode;
              if (!((key == 8) || (key == 32) || (key == 190) || (key == 46) || (key >= 48 && key <= 57) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
                e.preventDefault();
              }
            }
          });
      });
		});
      </script>
		
		
		
<script type="text/javascript">

$(document).ready(function() {
		  $( ".submit_btn" ).click(function(e) {
			  
			  var isValid = true;
		        $('input[type="text"]#groupname').each(function() {
		            if ($.trim($(this).val()) == '') {
		                isValid = false;
		                $(this).css({
		                    "border": "1px solid red",
		                    //"background": "#FFCECE"
		                });
		            }
		            else {
		                $(this).css({
		                    "border": "1px solid #c2c2c2",
		                   // "background": ""
		                });
		            }
		        });
		        
		        if($('.chekbox_student:checkbox:checked').length < 2)
	        	{
		        	isValid = false;
		            $(".a").addClass('b');
		            $('#display').slideDown().html('<span  id="error">Choose atleast two student to create group</span>');
		            setTimeout( function(){$('#display').hide();} , 5000);
		            return false;
		            
		        }
		        else
		        	{
		        	$(".a").removeClass('b');
		            $("#display").hide();
		        	}
		        
		        if (isValid == false){
		        	
		            e.preventDefault();
		           
		        }
		        else {
		        	 //alert('Thank you for submitting')
				        document.collaborateform.action="/KODE_DEV/ControllerServlet/CollaborateServlet";
						document.collaborateform.submit();
		        }
		        
		       
		        
		  
	   });
	});
	

</script>
<%
		
		String username=(String)session.getAttribute("username");
		String message = (String) session.getAttribute("MsgValue");
		String organizationId=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		//System.out.println("organization id:"+organizationId+"created id:"+userid);
		//}
	%>

<body>

<div class="container"style="position:inherit;">
		<jsp:include page="../JSP/headers.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>

		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="collaborateStudent_mod" style="width:308px; top: 140px; height: auto;margin-right:15px;">

			<p class="strong">Create Group</p>
		<!-- action="/KODE_DEV/ControllerServlet/CollaborateServlet -->
<form  name="collaborateform" method="post">
<% if (request.getAttribute("FacultySuccess")!=null) { 
					//System.out.println(" faculty success message value in if:"+request.getAttribute("FacultySuccess"));
					%>
					<p class="success"><%= request.getAttribute("FacultySuccess") %></p>					
					<%
				} else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
					<p class="failure"><%= request.getAttribute("FacultyFailure") %></p>
					<%
				} 
				
			%>
<p class="white margin_tb" style="text-align: center;">
<input  class="textnumber" style="padding: 7px; width:249px; border:1px solid #c2c2c2 !important;border-radius:4px" type="text" name="groupname" maxlength="50" placeholder="Group Name" title="Should not contain special characters" id="groupName">
</p>
<p id="display"></p>
<div class="table_outer">
						<table border="1">	
				<tr>

					<th style="width: 175px;">Student</th>
					<th class="tab" style="width:85px;">Action</th>
				</tr>
						
							 <%CollaborateDao dao= new CollaborateDao();
							 AdminDomain adminDomain=new AdminDomain();
					           ArrayList<AdminDomain>al=new ArrayList<AdminDomain>();
							    al=dao.getStudentId(userid,organizationId);
							    Iterator<AdminDomain> it= al.iterator();
							    
							  while(it.hasNext()) 
							    {
								  adminDomain=it.next();
								  String name=adminDomain.getAdminName();
								  String Id=adminDomain.getAdminId();
								  String idAndName=name+" ("+Id+")";
							    	%>
							    	<tr>
									<td><%=idAndName%></td> 
									<td class="a"><input class="chekbox_student" type="checkbox" name="selectstudent" id="selectstudent" value="<%=Id%>"></td>
									
							   </tr>
							 <%   }%> 
							   
</table>
</div>
<p style="margin: 15px 0px; margin-left: 15px;border-radius:4px; text-align: center;">
<!-- onclick="validateStudent()" -->
<input class="add_btn1 submit_btn" type="submit" value="Add">
<!-- <a class="back" style="color: #c2c2c2; font-weight: bold;" href="../JSP/Home.jsp">Back</a> -->
</p>
<input type="hidden" name="userid" value="<%=userid %>">
<input type="hidden" name="orgid" value="<%= organizationId%>">
</form>
		</div>
		<%@ include file="../JSP/FooterViews.jsp"%>
	</div>

</body>
<style>
.autohide,#error {
position:absolute;
top:180px;
right:350px;
}
#display {
position:static;}
</style>

</html>