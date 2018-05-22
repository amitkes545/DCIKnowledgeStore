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

<title>WelCome To KODE</title>
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
function getStudentForAdmin(){
	var streamid=document.getElementById("sessionID3").value;
	//alert(streamid);
	//var selectstudent=document.getElementById("sessionID").value;
	//alert(selectstudent);
	//var status=document.getElementById("sessionID2").value;
	//alert(status);
/* if(status=='')
	status="All";
	if(selectstudent=='')
		selectstudent="All"; */
	
	
	
	//document.postsession.action="/KODE_DEV/ControllerServlet/getStudentForDocVerification?streamid="+streamid+"&status="+status+"&selectstudent="+selectstudent;
	
		document.postsession.action="/KODE_DEV/ControllerServlet/getStudentForDocVerification?streamid="+streamid;
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

/* $(document).ready(function() {
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
 */	//hiding drop down by default
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
		//ArrayList<ActiveSessionDomain> sessionDomain=new ArrayList<ActiveSessionDomain>();
		//sessionDomain = (ArrayList<ActiveSessionDomain>)request.getAttribute("sessionDetails");
		
		
		 ArrayList<ActiveSessionDomain> sessionDomain = new ArrayList<ActiveSessionDomain>();
		
		 if(request.getAttribute("sessionDetails")!=null)
		 	sessionDomain = (ArrayList<ActiveSessionDomain>)request.getAttribute("sessionDetails");
		
		 System.out.println("sessionDomain in JSP :: "+sessionDomain.size());
		
		
		
		//msg = (String) mess.getAttribute("MsgValue");
		String organizationId=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		System.out.println("userid---"+userid);
		 if(username==null)
			response.sendRedirect("../JSP/error.jsp?errmsg=Session Expired"); 
	
		System.out.println("organization id:"+organizationId+"created id:"+userid);
		
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
		<p class="strong">Document Verification</p>
		 <form name="postsession" id="postsession" action="/KODE_DEV/ControllerServlet/AdminUpdateStudent" method="post">
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
		 <select name="sessionID1" placeholder="Session Name"  class="opt" id="sessionID1" onchange="loadPrgram()">
		<option value="">Choose Discipline*</option>
							
								<%
								String courseid="",getorgid="";
								ActivateStudentDao createSessionDao1= new ActivateStudentDao();
								ArrayList<String> alo=createSessionDao1.getDecipline();
					        //  System.out.println("size="+alo.size());
					          Iterator<String> itro = alo.iterator();
								String orgname=null;
								while (itro.hasNext()) {
									String decid = itro.next();
									
							%>
							<option value="<%=decid%>"><%=decid%></option>
							<%
								}
								%>
									
				    
		</select> 
		</td></tr>
			
						
		<!-- <select name="sessionID" placeholder="Session Name"  class="opt" id="sessionID" onchange="clickSessionId()"> -->
	<select name="sessionID" placeholder="Session Name"  class="opt" id="sessionID" onchange="loadCourse()">
		 <option value="">Choose Program*</option>
			<option value=""> </option> 
			
				
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
		
		<select name="sessionID2" placeholder="Session Name"  class="opt" id="sessionID2" onchange="loadStream()">
		<option value="">Choose Course*</option>
			</select>
		
		<select name="sessionID3" placeholder="Session Name"  class="opt" id="sessionID3" onchange="getStudentForAdmin()">
		<option value="">Choose Stream*</option>
			    
		</select>
		<!-- -------- -->
		<!--<span class="common_button">
			<input type="button" value="Find" id="find" onclick="clickSessionId();"style="font-size:20px">
			
		</span>-->
		<span>
		<input type="text" id="search" placeholder="Search" style="padding: 5px !important;border: 1px solid #c2c2c2;border-radius: 4px;">
		</span>
		
		<!-- <div class="search_div">
		<input class="box1" type="text" id="search" placeholder="Search">
		</div>-->
		<!-- -------- -->
		<!-- <a style="font-size:20px">Find</a> -->
		</div>

		<div class="search_result">
		
			<%
		    
	     if(request.getAttribute("successmsg")!=null){ 
	    	// System.out.println("in if success");  	 
	%>
	  <p class="autohide" style="color:green; font-size:17px; font-weight: bold; top: 11%; left: 41%; position: absolute;">  <%= request.getAttribute("successMessage")%></p>
	     <%} else if(request.getAttribute("failuremsg")!=null) {
	    	// System.out.println("in else failure");
	    	 %>
	     <p class="autohide" style="color:red; font-size:17px; font-weight: bold; top: 11%; left: 41%; position: absolute;"><%= request.getAttribute("failureMessage") %></p>
	     <%}
	     
	     %>
		
		
		<!-- heading starts here  -->
		<div class="table_section1">
		<div class="table_outer_row" style="height:215px;">
		<table width="100%" id="tblData">
		 <thead>
			 <tr class="row_head">

		<th width="16.66%">
		Sl.No.
		<div class="header_stop" style="width:16.66%;">Sl.No.</div>
		</th>
		
		<th width="16.66%">
		ENR ID
		<div class="header_stop" style="width:16.66%;">ENR ID</div>
		</th>
		<th width="16.66%">
		Applicant Name
		<div class="header_stop" style="width:16.66%;">Applicant Name</div>
		</th>
		<th width="16.66%">
		Documents Submitted
		<div class="header_stop" style="width:16.66%;">Documents Submitted</div>
		</th>
		<th width="16.66%">
		 Verification Status 
		<div class="header_stop" style="width:16.66%;">Verification Status</div>
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
		int count=1;
	//	System.out.println("sessionDetails Size="+sessionDomain.size());
		if(sessionDomain!=null)
			 {
			System.out.println("hiiii");
			Iterator<ActiveSessionDomain> iterator1 =sessionDomain.iterator();
			 
				for(ActiveSessionDomain sessionDomain1 : sessionDomain)
				{
				
				/* String []date=sessionDomain1.getEnrdate().split(" ");
				dataforenr=date[0];
				System.out.println("dataforenr"+dataforenr);
				String dd[]=dataforenr.split("-");
				System.out.println(dd[2]+"/"+dd[1]+"/"+dd[0]);
				finaldate=dd[2]+"/"+dd[1]+"/"+dd[0];
				System.out.println("finaldate "+finaldate);
				String []time=date[1].split(":");
				System.out.println(time.length);
				timeforenr=time[0]+":"+time[1];
				System.out.println(timeforenr);  */
				
				System.out.println("------------------------");
				
			 %>
			
			 <tr class="result_row_tr row">
			 	 <td align="right" style="text-align:center;"><%=count++ %></td>
			 
				<td>
				<%-- <input type="text" name="enrid<%=sessionDomain1.getEnrid() %>" id="enrid" value="<%=sessionDomain1.getEnrid() %>" class="<%=sessionDomain1.getEnrid() %> required" readonly="readonly" style="text-align:center;"> --%>
			<input type="text" name="enrid<%=count%>" id="enrid" value="<%=sessionDomain1.getEnrid() %>" class="<%=sessionDomain1.getEnrid() %> required" readonly="readonly" style="text-align:center;"> 
			
				</td>
					<td>
				<input type="text" name="studentname<%=sessionDomain1.getEnrid() %>" value="<%=sessionDomain1.getStudentname() %>" class="<%=sessionDomain1.getEnrid() %> required">
				</td>
				
			<%-- 	 	<td>
				<input type="text" name="studentname<%=sessionDomain1.getEnrid() %>" value="<%=sessionDomain1.getDocName() %>" class="<%=sessionDomain1.getEnrid() %> required">
				</td> 
				
				<td align="left"><a href="/KODE_DEV/ControllerServlet/AssignDownloadfile?filePath=/home/ftpuser1<%=sessionDomain1.getDocName()%>"></a>
			</td>
				 --%>
				 
				 
				 	<td align="center" width="100px">
			<%if(sessionDomain1.getDocName() !=null){ %>
			<a style="color: #000; padding-top:5px;" href="/KODE_DEV/ControllerServlet/FTPDownloadFileDemoForDoc?filePath=<%=sessionDomain1.getDocName()%>">Download File</a>
			<%} %>
			</td>
				
			
			 			<td>
				<select style="text-align:center;" class="<%=sessionDomain1.getEnrid() %>" name="status<%=count %>">
				
					<option value="Completed" 
					 <%if(sessionDomain1.getStatus().equalsIgnoreCase("Completed")){ 
						 System.out.println("sessionDomain1.getStatus()="+sessionDomain1.getStatus()); 
					 %> selected <%
					 }
					 %> >Completed</option>
					 
					<option value="In Completed" <%if(sessionDomain1.getStatus().equalsIgnoreCase("In Completed")){ %> selected <%}%> >In Completed</option>
					<option value="In Progress" <%if(sessionDomain1.getStatus().equalsIgnoreCase("In Progress")){ %> selected <%}%>>In Progress</option>
					<option value="On Hold" <%if(sessionDomain1.getStatus().equalsIgnoreCase("On Hold")){ %> selected <%}%>>On Hold</option>		
				</select>

								
				
				<td width="100px">
			<a href="javascript:void(0)">
			<input id="<%=count%>" class="aa" type="checkbox" name="checkboxGroup" value="<%=count%>">
			</a>
			</td>
				
				
				
			</tr> 
			
			  


			
		 <%}} %>
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
function loadPrgram() {
      var selected = document.getElementById("sessionID1");
   console.log('getProgram');
   var selectedValue = selected.options[selected.selectedIndex].value; 
     
     $.get('${pageContext.request.contextPath}/GetProgram', {selectedValue : selectedValue},
     function(jsonResponse) {        
         console.log("json response :",jsonResponse);   
         var select = $('#sessionID');  
            select.find('option').remove();
            jsonResponse = $.parseJSON(jsonResponse); 
          console.log("parsed json response :", jsonResponse); 
          $('<option>').val('').text('Choose Program').appendTo(select); 
           
        
          $.each(jsonResponse, function(index, value) {
            console.log('Index value here :',index);  
         console.log('Actual obj value here :',value.programName);   
       
         
     	$('<option>').val(value.programName).text(value.programName+" ("+value.programId+") ").appendTo(select);
          
          });        
    });   
        		  
}


function loadCourse() {
	
	
    var selected = document.getElementById("sessionID");
 
 var selectedValue = selected.options[selected.selectedIndex].value; 
 var decsid = $("#sessionID1").val();
 var pid = $("#sessionID").val();
 
  	   $.get('${pageContext.request.contextPath}/GetCourseForAdmin', {selectedValue : selectedValue,decsid : decsid,pid : pid},
   function(jsonResponse) {        
       console.log("json response :",jsonResponse);   
       var select = $('#sessionID2');  
          select.find('option').remove();
          jsonResponse = $.parseJSON(jsonResponse); 
        console.log("parsed json response :", jsonResponse); 
        $('<option>').val('').text('Choose Course').appendTo(select); 
       
      
        $.each(jsonResponse, function(index, value) {
          console.log('Index value here :',index);  
       console.log('Actual obj value here :',value.courseName);   
     
       
   	$('<option>').val(value.courseName).text(value.courseName+" ("+value.courseId+") ").appendTo(select);
        
        });        
  });   
      		  
}


function loadStream() {
	//alert("stream");
	
    var selected = document.getElementById("sessionID");
 
 var selectedValue = selected.options[selected.selectedIndex].value; 
 var decsid = $("#sessionID1").val();
 var pid = $("#sessionID").val();
 
  	   $.get('${pageContext.request.contextPath}/GetStreamForAdminTest', {selectedValue : selectedValue,decsid : decsid,pid : pid},
   function(jsonResponse) {        
       console.log("json response :",jsonResponse);   
       var select = $('#sessionID3');  
          select.find('option').remove();
          jsonResponse = $.parseJSON(jsonResponse); 
        console.log("parsed json response :", jsonResponse); 
        $('<option>').val('').text('Choose Stream').appendTo(select); 
             
        $.each(jsonResponse, function(index, value) {
         // console.log('Index value here :',index);  
       console.log('Actual obj value here :',value.streamName);   
     
       
  	$('<option>').val(value.streamId).text(value.streamName+" ("+value.streamId+") ").appendTo(select);
   	
	//$('<option>').val(value.streamName).text(value.streamName+" ("+value.streamId+") ").appendTo(select);
        
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
