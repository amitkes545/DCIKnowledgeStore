<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.dao.*"%>
<%@ page import="com.kds.KODE_DEV.domain.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/OwnerKnowSetup.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/kedu.css" rel="stylesheet"/>
<link href="../CSS/design-common.css" rel="stylesheet"/>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<link href="../CSS/notification-new.css" rel="stylesheet"></link>
<link href="../CSS/table_scroll.css" rel="stylesheet"/>
<script src="../JS/MessageFadeOut.js"></script>

<!-- <script type="text/javascript" src="../JS/MessageFadeOut.js"></script> -->

<script type="text/javascript">
/* function callStudentService1()
{
	document.studentMapping.action="/KODE_DEV/ControllerServlet/StudentsViewService";
	document.studentMapping.submit();
	} */
	function saveCourse()
	{
		//alert("in");
		//var studentid=document.getElementById("studID").value;
		//alert(studentid);
		document.studentMapping.action="/KODE_DEV/ControllerServlet/StudentToCourseServlet";//?studentid="+studentid+"";
		document.studentMapping.submit();
	}
	/* function ShowImage()
	{
		alert("in");
		//var cr="course"+1;
	var i=1;
	var course=document.studentMapping.course1.value;
	alert(course);
		var course=document.getElementById("course"+i).value;
		alert(course);
		var strUser =e.options[e.selectedIndex].value;
		 var elementname = course.attr("name");
         alert(elementname);
		//var course=document.studentMapping.course.value;
		
		alert(course);
	} */
	</script>
</head>

<%
	//HttpSession mess = request.getSession();
	String msg = "";
	String username = (String)session.getAttribute("username");
	String userid = (String)session.getAttribute("userid");
	System.out.println("userid is:...." + userid);
	String orgid = (String)session.getAttribute("orgId");
	String[] resultUserid=(String[])session.getAttribute("resultUserId");
	
	ArrayList<AdminDomain> studentDomain = new ArrayList<AdminDomain>();
	//ArrayList<AdminDomain> studentUpdatedDomain = new ArrayList<AdminDomain>();
	
	studentDomain = (ArrayList<AdminDomain>)request.getAttribute("studentDetails");
	
	ActivateStudentDao createSessionDao1= new ActivateStudentDao();
   	ArrayList<ActiveSessionDomain> al1=new ArrayList<ActiveSessionDomain>();
   
   	al1=createSessionDao1.getCourseList(orgid);
   	System.out.println("after modify="+studentDomain);
   //	studentUpdatedDomain = (ArrayList<AdminDomain>)request.getAttribute("StudentUpdatedDetails");

%>
<body onload="ShowImage()">
	<div class="container" style="position:static;height:600px;">
<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="postpon_mod">
		<p class="strong">Existing Students - Email Delivery Status</p>
		<form name="studentMapping" method="post" >
			<%
							if (request.getAttribute("StudentSuccess") != null) {
						%>
						<p class="success"
							style=" background:#none;">
							<%=request.getAttribute("StudentSuccess")%>
						</p>
						<%
							} else if (request.getAttribute("StudentFailure") != null) {
						%>
						<p class="failure"
							style=" background:#none;">
							<%=request.getAttribute("StudentFailure")%>
						</p>
						<%
							} 
						%>
		<div class="search_result">
		<!-- heading starts here  -->
		<div class="table_section1">
		<div class="table_outer_row">
		<table width="100%" id="tblData">
		<thead>
		 <!--  <tr height="25px"></tr>-->
			 <tr class="row_head">
			 <th width="16%">
		Sl No.
		<div class="header_stop" style="width:16%;">Sl No.</div></th>
		<th width="16%">
		User ID
		<div class="header_stop" style="width:16%;">User ID</div></th>
		<th width="16%;">
		User Name
		<div class="header_stop" style="width:16%;">User Name</div>
		</th>
		<th width="26%;">
		Email ID
		<div class="header_stop" style="width:26%;">Email ID</div>
		</th>
		<th width="16%;">
		Course
		<div class="header_stop" style="width:16%;">Course</div>
		</th>
		<th width="10%;">
		Email Status
		<div class="header_stop" style="width:10%;">Email Status</div>
		</th>
		</tr>
		</thead>
		<tbody>
		 <% int count=1;
		 System.out.println("studentDomain="+studentDomain);
		if(studentDomain!=null){
			Iterator<AdminDomain> iteratorForStudent = studentDomain.iterator();
			int i=1;
			while(iteratorForStudent.hasNext()){
				AdminDomain studentDomainWithValue = iteratorForStudent.next();
				
				//System.out.println("s2="+assessmentDomainWithVlaue.getFileUploadPath());
				//System.out.println(s2.toString());
				System.out.println("studentDomainWithValue="+studentDomainWithValue);
			%>
			
			 <tr class="result_row_tr">
			 <td style="text-align:center;">
			 <input type ="text" name="count" id="count" readonly="readonly" value="<%=count++%>" class="<%=studentDomainWithValue.getAdminId()%>"/>
			 </td>
			 <td>
			<input type ="text" name="studID<%=count%>" id="studID" readonly="readonly" value="<%=studentDomainWithValue.getAdminId()%>" class="<%=studentDomainWithValue.getAdminId()%>"/>
			<input type ="hidden" name="password<%=count%>" readonly="readonly" value="<%=studentDomainWithValue.getPwd()%>" class="<%=studentDomainWithValue.getAdminId()%>"/>
			 </td>
				<td>
				<input type ="text" name="studName<%=count%>" readonly="readonly" value="<%=studentDomainWithValue.getAdminName()%>" class="<%=studentDomainWithValue.getAdminId()%>"/>
				</td>
				<td>
				<input type ="text" name="email<%=count%>" readonly="readonly" value="<%=studentDomainWithValue.getEmail()%>" class="<%=studentDomainWithValue.getAdminId()%>"/>	
				</td>
				<td>
				<select style="width:100%;" name="course<%=count%>" id="course<%=count%>" class="<%=studentDomainWithValue.getAdminId()%>">
				<%
				//System.out.println("course="+studentDomainWithValue.getCity());
				String course=studentDomainWithValue.getCity();
				String coursename=studentDomainWithValue.getCountry();
				if(course!="" && course!=null){ %>
									<option selected="selected" value="<%=course%>"><%=course%> (<%=coursename%>)</option>
									<%}else{ %>
				<option value="">Select Course</option>
		<%			
		
					         	Iterator<ActiveSessionDomain> iterator2 =al1.iterator();
		//String coursename="";
								while(iterator2.hasNext())
									{
									ActiveSessionDomain domain1=iterator2.next();
										String courseidforlist=domain1.getCourseid();
										coursename=domain1.getCategory();
										String id=courseidforlist+"Ç"+coursename;
										//System.out.println("id="+id);
										%>
										<option value="<%=id%>"><%=coursename%> (<%=courseidforlist%>)</option>
									<%}}
									//System.out.println("course="+coursename);
									%>
				  
				</select>			
				</td>
				<td style="text-align:center;">
				<%
				System.out.println("course="+course);
				if(course!="" && course!=null){ %>
				<a href=""><img src="../Images/check.png" style="max-width:24px;height:auto;"/></a>	
				<%}else{ %>
				<!-- <a href=""><img src="../Images/close-icon.png" style="max-width:24px;height:auto;"/></a> -->	
				<%} %>		
				</td>
					<%}} 
					%>
				</tr>
			
	</tbody>
		</table>
		<input type="hidden" value="<%=count-1%>" name="counter" id="counter">
		</div>
		
		</div>
		<div class="button"><input type="button" value="Submit" onclick="saveCourse()" /></div>
		
	</div>
	</form>
	</div>
	
		 </div>
		<div>
		<%@include file="FooterViews.jsp" %>
		</div>
</body>
<script>
onload();
</script>
<style>
.faculty_mod .strong {
padding-right:18px;
}
.fadehide {
position:ablosute;
top:180px;
right:380px;
}
.button {
width:100%;
height:auto;
}
.button input {
width:auto;
background:#2291ce;
color:#fff;
margin:auto;
display:table;
border:none;
padding:7px 15px;
cursor:pointer;
font-size:14px;
margin-top:10px;
}
</style>
</html>