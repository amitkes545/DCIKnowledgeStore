<%@ page errorPage="../JSP/error.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.kds.KODE_DEV.domain.AdminDomain"%>
      <%@page import="com.kds.KODE_DEV.domain.SessionDomain"%>
       <%@page import="com.kds.KODE_DEV.domain.ActiveSessionDomain"%>
    <%@page import="com.kds.KODE_DEV.dao.CreateSessionDao"%>
    <%@page import="com.kds.KODE_DEV.dao.ActivateStudentDao"%>
    
    <%@page import="java.util.Iterator"%>
     <%@page import="java.util.ArrayList"%>
      <%@ page import="java.util.HashSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.js"></script>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<script src="../JS/datetimepicker_css.js"></script>
<script type="text/javascript" src="../JS/validatePostSession.js"></script>
<link href="../CSS/jquery.datetimepicker.css" rel="stylesheet"></link>
<script src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.datetimepicker.js"></script>
<link href="../CSS/table_scroll.css" rel="stylesheet"/>
<link href="../CSS/design-common.css" rel="stylesheet"/>
<style type="text/css">
.search_result{
padding: 0px 10px;
margin-bottom: 25px;
max-height: 310px;
font-family: arial;
font-size: 14px;
}
.su{
		    color: #008000;
    font-size: 14px;
    font-weight: bold;
    top:44px;
    position: absolute;
    /* background: #fff;
    opacity: 0.7; */
    right:0px;
    padding: 0px 36px;
}
.container{
background: url("../Image/body.png") center center no-repeat;
}
</style>
<style type="text/css">
.postpon_mod{
width: 95% !important;
    box-shadow: 5px 5px 5px 5px #FFF;
    margin: 15px auto 0px;
    padding: 15px 1px 0px 0px;
    border: 3px solid #C2C2C2;
    background-color: #FCF7F7;
    border-radius: 4px;
    opacity:1;
    margin-top: 72px;
    
}

.faculty_mod a.dt-pick {
    float: left;
    position: absolute;
    right: 70px;
    top: 47%;
}
.faculty_mod a.dt-pickw {
    float: left;
    position: absolute;
    right: 70px;
    top: 63%;
}
.search_div{
margin-top: 12px;
}
.opt{
width: 265px !important;
padding: 3px !important;
height: 31px;
border: 1px solid #C2C2C2;
font-family: regular;
border-radius: 4px;
}
select{
color: #000;
}
.box1
{
width: 250px!important;
    padding: 7px!important;
    border: 1px solid #c2c2c2;
    border-radius: 4px;
}
.row_head{
    background: none repeat scroll 0% 0% #009AE1;
    color: #FFF;
    font-weight: bold;
    }
    .row_head td{
    padding: 5px;
    }	
.row_head .col_2{float: left; padding: 0px 15px;}
.row_result .col_2{float: left; padding: 0px 15px;}
.result_row_tr td input{border: none; background: none; width: 99.5%; color: #000;}
.starttime{width: 130px;}
.endtime{width: 120px;}
.duration_r{width: 120px;}
.cost_r{width: 80px;}
.result_row_tr td{border: 1px solid #000;padding: 4px;}

.btnscolor{background: #1d87da;
    border: none;
    color: #fff;
    padding: 5px 15px;
    margin-top: 10px;
    margin-right: 10px;
    margin-left: 10px;}
</style>
</head>
<script>
//jquery code for display calenders
$(document).ready(function() {
	  / for date picker /
	  var objDate = new Date();
	 
	  var Presentyear = objDate.getFullYear();
	  $(".startTime").datetimepicker({
	   yearRange : '1900:' + Presentyear,
	   changeMonth : true,
	   changeYear : true,
	   minDate : '0d',
	   dateFormat: "yy-mm-dd",
	  });
});

$(document).ready(function() {
	  / for date picker /
	  var objDate = new Date();
	  var Presentyear = objDate.getFullYear();
	  $(".endTime").datetimepicker({
	   yearRange : '1900:' + Presentyear,
	   changeMonth : true,
	   changeYear : true,
	   minDate : '0d',
	   dateFormat: "yy-mm-dd",
	  });
 });
$(document).ready(function() {
var time1 = $('.startTime'), //cache selectors
time2 = $('.endTime'),
result = $("#duration");
$( ".endTime" ).blur(function()
	{
	
	var d1 = new Date(time1.val()), //convert to date object
    d2 = new Date(time2.val()), //convert to date object
    msec = d2.getTime() - d1.getTime(), //get difference in milliseconds
    hh = Math.floor(msec / 1000 / 60 / 60), //get hours
    dd = Math.floor(hh / 24); //calculate days
msec -= hh * 1000 * 60 * 60; //remove hours from msec
hh -= dd * 24; //remove days from hours
var mm = Math.floor(msec / 1000 / 60); //get minutes
//result.val(dd + ' days ' + hh + ' Hrs ' + mm + ' minutes');
$('#error').val('');
if(d1 >= d2 ){
	  $('#dis').slideDown().html('End time is greater than start time');
	 
	  $('#duration').val('');
	  $('#endTime').val('');
}
else {
	  result.val( hh + ' Hrs ' + mm + ' minutes');
	  $("#dis").hide();
}

});    	
});
//onload function for in drop down after click session id again need to display same value
function onload(){
	<% String sessionName=(String)request.getAttribute("SessionName");
	////System.out.println("session name in an onload:"+sessionName);
	if(sessionName!=null && sessionName.length()>0) {
		////System.out.println("session name  in onload:"+sessionName);
	 %> 
	 var selectBox = document.getElementById("sessionID");
	 for(var i=0;i<selectBox.options.length;i++){
			 if(selectBox.options[i].value == '<%=sessionName%>')
			{
	 selectBox.options[i].selected=true;
				 break;
			 }
		 }
				
		<%}%>
}
//jquery code for message hiding
$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
	$(".autohide").click(function()
	{
	//alert("dfsfsf");	
	});
});
//onclick function for session id
function clickSessionId(){
	var courseid=document.getElementById("sessionID1").value;
	//alert(courseid);
	var selectstudent=document.getElementById("sessionID").value;
	//alert(selectstudent);
	var status=document.getElementById("sessionID2").value;
	//alert(status);
if(status=='')
	status="All";
	if(selectstudent=='')
		selectstudent="All";
	
	
	
	document.postsession.action="/KODE_DEV/ControllerServlet/AdminActivateStudentServlet?courseid="+courseid+"&status="+status+"&selectstudent="+selectstudent;
	document.postsession.submit();
}
function clickSessionIdForCourse(){
	var sessionid="all";
	alert(sessionid);
	document.postsession.action="/KODE_DEV/ControllerServlet/AdminActivateStudentServlet?sessionid="+sessionid+"";
	document.postsession.submit();
}


</script>
<script>
var request;
function getSessionTime()
{
var sessionTime=document.sessionform.sSTime.value;
var url="sessionID.jsp?val="+sessionTime;

if(window.XMLHttpRequest){
request=new XMLHttpRequest();
}
else if(window.ActiveXObject){
request=new ActiveXObject("Microsoft.XMLHTTP");
}

try
{
request.onreadystatechange=getInfo;
request.open("GET",url,true);
request.send();
}
catch(e)
{
alert("Unable to connect to server");
}
}

function getInfo(){
if(request.readyState==4){
var val=request.responseText;
document.getElementById('amit').innerHTML=val;
}
}

$(document).ready(function() {
	$('.btnscolor').hide();
	
	$('.row input').each(function()
	{
	$(this).attr("disabled",true);
	});
	$('.row select').each(function()
			{
			$(this).attr("disabled",true);
			});
	$(".row a input").attr("disabled",false);

$(".row a input").click(function() {
		var id = $(this).attr("id");
		if($('#' + id).is(":checked"))
		{
		$("." + id).attr("disabled",false);
		
		$('.btnscolor').show();
		}
	else{
		$("." + id).attr("disabled",true);	
		$('.btnscolor').hide();
	}
		btnscolor;
	});
	});
	//hiding drop down by default
/* $(document).ready(function () { 
	//alert("in");
 
	$(".group_tr").hide();
	 $(".select_tr").hide();
	 $("#rtype").click(function(e) {
		// alert("in1");
	 $(".individual").click(function() {
		 alert("in");
		 $(".select_tr").show();
		 $(".group_tr").hide();
		 });
	 $(".group").click(function() {
		 $(".select_tr").hide();
		 $(".group_tr").show();
		 });
	 $(".all").click(function() {
		 $(".select_tr").hide();
		 $(".group_tr").hide();
		 });
});
}); */
$(document).ready(function() {
    $("#updateButton").click(function(e) {
    	
        var isValid = true;
        $('input[type="text"].required').each(function() {
            if ($.trim($(this).val()) == '') {
                isValid = false;
                $(this).css({
                    "border": "1px solid red",
                    //"background": "#FFCECE"
                });
            }
            else {
                $(this).css({
                    "border": "",
                   // "background": ""
                });
            }
        });
        
        $('.sel select.required').each(function() {
            if ($.trim($(this).val()) == '') {
                isValid = false;
                $(this).css({
                    "border": "1px solid red",
                    //"background": "#FFCECE"
                });
            }
            else {
                $(this).css({
                    "border": "",
                   // "background": ""
                });
            }
        });
      
        if (isValid == false){
        	
            e.preventDefault();
           
        }
        else {
        	 //alert('Thank you for submitting')
        	 $("#postsession").submit();
        }
           
    });
});           
</script>
<script>
  $(function(){
				
				$("#costOfSession").keyup(function()
				{
					var yourInput = $(this).val();
					re = /[`~!@#%^&*()|+\-=?;:'",<>\{\}\[\]\\\/]/gi;
					var isSplChar = re.test(yourInput);
					if(isSplChar)
					{	
						
						var no_spl_char = yourInput.replace(/[`~!@#%^&*()|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
						$(this).val(no_spl_char);
						 $('#special').slideDown().html('<span id="error">Special characters not allowed except $ .</span>');
						/* alert("Special characters not allowed except $ ."); */
					}else {
						 $("#special").hide();
					}
				});
			});
</script>
<%
		/* HttpSession mess = request.getSession();
		String msg = ""; */
		int i=0;
		ArrayList<ActiveSessionDomain> sessionDomain=new ArrayList<ActiveSessionDomain>();
		//msg = (String) mess.getAttribute("MsgValue");
		String organizationId=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		System.out.println("userid---"+userid);
		if(username==null)
			response.sendRedirect("../JSP/error.jsp?errmsg=Session Expired");
		sessionDomain = (ArrayList<ActiveSessionDomain>)request.getAttribute("sessionDetails");
		//System.out.println("organization id:"+organizationId+"created id:"+userid);
		
	%>
<body>

	<div class="container">
		<%-- <%@include file="all_one_header_knowstore.jsp"%> --%>
		<%@include file="../JSP/headers.jsp"%>
		
		
		<div style="clear: both;"></div>
		<div>
	 	<%@ include file= "../JSP/Admin_menu.jsp" %>
 		</div>
		<div style="clear: both;"></div>
		<div class="postpon_mod">
		<div style="text-align: center;">
		<p class="strong">Student Enrollment Status</p>
		 <form name="postsession" id="postsession" action="/KODE_DEV/ControllerServlet/ActiveManageSessionServlet" method="post">
		 <%if (request.getAttribute("FacultySuccess")!= null) { 
				String messsage=(String)request.getAttribute("FacultySuccess");
				
				%>
				
				<p class="autohide success_message" ><%= request.getAttribute("FacultySuccess") %></p>
				<%
			}
			 else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
					<p class="autohide" style="color:red; font-size:12px; font-weight: bold; top: 37%; left: 44%; position: absolute;"><%= request.getAttribute("FacultyFailure") %></p>					
					<%
				} 
				
			%>
		 <div>
			
			<tr>
		<td>
				<!--  <select name="sessionID1" placeholder="Session Name"  class="opt" id="sessionID1" onchange="clickSessionIdForCourse()"> -->
		 <select name="sessionID1" placeholder="Session Name"  class="opt" id="sessionID1" onchange="loadStudent()">
		<option value="">Choose Course*</option>
	
								<%
								String courseid="",getorgid="";
								ActivateStudentDao createSessionDao1= new ActivateStudentDao();
					           	ArrayList<ActiveSessionDomain> al1=new ArrayList<ActiveSessionDomain>();
					           String getorgid1=createSessionDao1.getOrgId(userid);
					           
					           String said1=createSessionDao1.getSuperAdminID(userid);
					           
					            session.setAttribute("courseid", courseid);
					            System.out.println("courseid="+courseid);
					            session.setAttribute("orgid", getorgid1);
					           	al1=createSessionDao1.getCourseIdForList(userid);
					         	Iterator<ActiveSessionDomain> iterator2 =al1.iterator();
								while(iterator2.hasNext())
									{
									ActiveSessionDomain domain1=iterator2.next();
										String courseidforlist=domain1.getCourseid();
								String coursename=domain1.getCategory();
										
									%>
										<option value="<%=courseidforlist%>"><%=coursename%> (<%=courseidforlist%>)</option>
									<%}%>
				    
		</select> 
		</td></tr>
			
						
		<!-- <select name="sessionID" placeholder="Session Name"  class="opt" id="sessionID" onchange="clickSessionId()"> -->
	<select name="sessionID" placeholder="Session Name"  class="opt" id="sessionID">
		<!-- <option value=""></option>
			<option value=""> </option> -->
			
				
								<%-- <%
								ActivateStudentDao createSessionDao= new ActivateStudentDao();
					           	ArrayList<ActiveSessionDomain> al=new ArrayList<ActiveSessionDomain>();
					           getorgid=createSessionDao.getOrgId(userid);
					           String said=createSessionDao.getSuperAdminID(userid);
					            courseid=createSessionDao.getCourseId(userid);
					            session.setAttribute("courseid", courseid);
					            session.setAttribute("orgid", getorgid);
					           	al=createSessionDao.getStudentName(userid,getorgid,said,courseid);
					         	Iterator<ActiveSessionDomain> iterator =al.iterator();
								while(iterator.hasNext())
									{
									ActiveSessionDomain domain1=iterator.next();
										String enrid=domain1.getEnrid();
								
										String studentName=domain1.getStudentname();
										SessionDomain domain=new SessionDomain();
										String sessionName1=domain.getSessionName();
										String sessionID=domain.getSessionId();
										String IdAndName=sessionName1+"("+sessionID+")";
										//String IdAndName=sessionName1+"("+sessionID+")";
										////System.out.println("value of category"+sessionName1);
									%>
										<option value="<%=enrid%>"><%=studentName+" ("+enrid+")"%></option>
									<%}%>
									 --%>

		</select>
		
		<select name="sessionID2" placeholder="Session Name"  class="opt" id="sessionID2"">
		<option value="">Choose Status</option>
		<option value="All">All </option>
		<option value="Active">Active </option>
		<option value="Inactive">Inactive </option>
			
			
						
				    
		</select>
		<!-- -------- -->
		<span class="common_button">
			<input type="button" value="Find" id="find" onclick="clickSessionId();"style="font-size:20px">
		</span>
		<!-- -------- -->
<!-- <a style="font-size:20px">Find</a> -->
		</div>

		
		<div class="search_result">
		<!-- heading starts here  -->
		<div class="table_section1">
		<div class="table_outer_row" style="height:215px;">
		<table width="100%" id="tblData">
		 <thead>
			 <tr class="row_head">

		<th width="16.66%">
		Enrollment ID
		<div class="header_stop" style="width:16.66%;">Enrollment ID</div>
		</th>
		
		<th width="16.66%">
		Student Name
		<div class="header_stop" style="width:16.66%;">Student Name</div>
		</th>
		<th width="16.66%">
		Enrollment Date
		<div class="header_stop" style="width:16.66%;">Enrollment Date</div>
		</th>
		<th width="16.66%">
		Enrollment Time
		<div class="header_stop" style="width:16.66%;">Enrollment Time</div>
		</th>
		<th width="16.66%">
		 Status 
		<div class="header_stop" style="width:16.66%;">Status</div>
		</th>
		<th width="16.66%">
		Action
		<div class="header_stop" style="width:16.66%;">Action</div>
		</th>
		</tr>
		<thead>
		<% 
	String dataforenr="",timeforenr="",finaldate="";
		System.out.println("started");
		
		if(sessionDomain!=null)
			 {
			Iterator<ActiveSessionDomain> iterator1 =sessionDomain.iterator();
			 
				for(ActiveSessionDomain sessionDomain1 : sessionDomain)
				{
				String []date=sessionDomain1.getEnrdate().split(" ");
				dataforenr=date[0];
				System.out.println("dataforenr"+dataforenr);
				String dd[]=dataforenr.split("-");
				System.out.println(dd[2]+"/"+dd[1]+"/"+dd[0]);
				finaldate=dd[2]+"/"+dd[1]+"/"+dd[0];
				System.out.println("finaldate "+finaldate);
				String []time=date[1].split(":");
				System.out.println(time.length);
				timeforenr=time[0]+":"+time[1];
				System.out.println(timeforenr);
				
				System.out.println("------------------------");
				
			 %>
			
			 <tr class="result_row_tr row">
				<td>
				<input type="text" name="enrid<%=sessionDomain1.getEnrid() %>" id="enrid" value="<%=sessionDomain1.getEnrid() %>" class="<%=sessionDomain1.getEnrid() %> required" readonly="readonly" style="text-align:center;">
				</td>
					<td>
				<input type="text" name="studentname<%=sessionDomain1.getEnrid() %>" value="<%=sessionDomain1.getStudentname() %>" class="<%=sessionDomain1.getEnrid() %> required">
				</td>
				<td>
				<input style="text-align:center;" type="text" name="enrdate<%=sessionDomain1.getEnrid() %>" value="<%=finaldate %>" class="<%=sessionDomain1.getEnrid() %> required">
				</td>
				<td>
				<input style="text-align:center;" type="text" name="enrdate<%=sessionDomain1.getEnrid() %>" value="<%=timeforenr
				%>" class="<%=sessionDomain1.getEnrid() %> required">
				</td>
					<td>
				<select style="text-align:center;" class="<%=sessionDomain1.getEnrid() %>" name="status<%=sessionDomain1.getEnrid() %>">
				<option value="<%=sessionDomain1.getStatus() %>"><%=sessionDomain1.getStatus() %></option>
				<%if(sessionDomain1.getStatus().equalsIgnoreCase("Active")){ %>
				<option value="Inactive">Inactive</option>
				<%} else  if(sessionDomain1.getStatus().equalsIgnoreCase("Inactive")){%>
				<option value="Active">Active</option>
				<%} %>
				</select>
				<%-- <td>
				<input type="text" name="status<%=sessionDomain1.getEnrid() %>" value="<%=sessionDomain1.getStatus() %>" class="<%=sessionDomain1.getEnrid() %> required">
				</td>
				 --%>
				<td>
				<a href="#">
				<input id="<%=sessionDomain1.getEnrid() %>" type="checkbox" name="checkboxGroup" value="<%=sessionDomain1.getEnrid() %>"/>
				</a>
				</td>
			</select>
				
							</tr> 
		 <%} }%>
	<!--  <tr class="select_tr" style="display:none">
						<td class="student_sel">
							 <% CreateSessionDao asdao=new CreateSessionDao();
		
                             ArrayList<AdminDomain> sl=new ArrayList<AdminDomain>();
                             sl=asdao.sendIndualGroup(userid,organizationId);
		                      Iterator<AdminDomain> it1= sl.iterator();%>
		                      <select name="student_id" class="required" id="student_id">
		                      <option value="">Choose Participant ID</option>
		                   <%--  <% while(it1.hasNext())
		                     {
		    	             String id=it1.next().getAdminId();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%> --%>
		                      </select>
	                
						</td>
					</tr>
					 <tr class="group_tr" style="display:none">
						 <td align="left">
							<font>Group ID</font>
						</td>
						<td>:</td> 
						
						<td class="group_sel">
									                      </select>
						</td>
					</tr>
					 -->
		</table>
		</div>
		</div>
		
	<!-- 	<input class="btnscolor" type="button" value="Cancel" id="buttonCancel" onclick="clickCancel()"/></a> -->
		
		</div>
		</form>
		</div>
		<div class="update_button">
		<a>
		<input class="btnscolor" type="submit" name="updateButton" onclick="clickSave()" id="updateButton" value="Update" /></a> 
	</div>
	</div>
	
		<%@ include file="../JSP/FooterViews.jsp"%>
	</div>
	<script type="text/javascript">
			$(document).ready(function()
			{
				$('#search').keyup(function()
				{
					searchTable($(this).val());
				});
			});
			function searchTable(inputVal)
			{
				var table = $('#tblData');
				table.find('tr').each(function(index, row)
				{
					var allCells = $(row).find('td');
					if(allCells.length > 0)
					{
						var found = false;
						allCells.each(function(index, td)
						{
							var regExp = new RegExp(inputVal, 'i');
							if(regExp.test($(td).text()))
							{
								found = true;
								return false;
							}
						});
						/* if(found == true)
							{
							alert("message test");
							
							}
						 */
						if(found == true)$('tr').show();else $(row).hide();
						/* alert("message"); */
					}
				});
			}
		</script>
		<script>  
function loadStudent() {
      var selected = document.getElementById("sessionID1");
   var selectedValue = selected.options[selected.selectedIndex].value; 
     //alert(selectedValue);  
            $.get('${pageContext.request.contextPath}/getStudents', {selectedValue : selectedValue}, 
         function(jsonResponse) {         //alert(jsonResponse);   
         console.log("json response :",jsonResponse);   
         var select = $('#sessionID');  
       //  alert(select);
            //var subselect= $('#subdeptid'); 
         select.find('option').remove();
           //subselect.find('option').remove();   
        jsonResponse = $.parseJSON(jsonResponse); 
          console.log("parsed json response :", jsonResponse); 
          $('<option>').val('').text('Choose Student').appendTo(select); 
           $('<option>').val('').text("All").appendTo(select); 
        
          $.each(jsonResponse, function(index, value) {
            console.log('Index value here :',index);  
         console.log('Actual obj value here :',value.studentname);   
         $('<option>').val(value.enrid).text(value.studentname+" ("+value.enrid+")").appendTo(select);  
           });        
    });   
  }
</script>
</body>
<script>
onload();
</script>
<style>
.common_button input {
    background: #009AE1;
    color: #fff;
    padding: 4px 0px;
    border: none;
    width: 88px;
    cursor:pointer;
    border-radius: 4px;
}
.update_button {
width:100%;
height:auto;
text-align:center;
margin-top:-12px;
}
.update_button input {
    background: #009AE1;
    padding: 5px 0px;
    border-radius: 4px;
    width: 84px;
    font-size: 15px;
    margin-bottom: 10px;
    margin-top:0px;
    cursor: pointer;
}
.success_message {
color: green;
    font-size: 16px;
    font-weight: bold;
    top: -53px;
    left: 0px;
    position: relative;
}
</style>
</html>
