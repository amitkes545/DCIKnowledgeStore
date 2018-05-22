<%@ page errorPage="../JSP/error.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.kds.KODE_DEV.domain.AdminDomain"%>
      <%@page import="com.kds.KODE_DEV.domain.SessionDomain"%>
    <%@page import="com.kds.KODE_DEV.dao.CreateSessionDao"%>
    <%@page import="java.util.Iterator"%>
     <%@page import="java.util.ArrayList"%>
      <%@ page import="java.util.HashSet"%>
         <%@page import="java.util.Date"%>
       <%@page import="java.util.*"%>
       <%@page import="java.text.*" %>  
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
<link href="../CSS/notification-new.css" rel="stylesheet"></link>
<script src="../JS/MessageFadeOut.js"></script>

<style type="text/css">
.search_result{padding: 0px 10px;
margin-bottom: 15px;
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
background: url("../Image/body_dark.png") center center no-repeat;
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
     /*  height: 410px; */
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
margin-top: -33px;
position: absolute;

}
.opt{
width: 265px !important;
padding: 6px !important;
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
    color: #000000;
    font-weight: bold;
    }
    .row_head td{
  /*   padding: 5px; */
     border-right: 1px solid #bbb;
    }	
.row_head .col_2{float: left; padding: 0px 15px;}
.row_result .col_2{float: left; padding: 0px 15px;}
.result_row_tr td input{border: none; background: none; width: 99.5%; color: #000;}
.starttime{width: 130px;}
.endtime{width: 120px;}
.duration_r{width: 120px;}
.cost_r{width: 80px;}
.result_row_tr td{padding: 4px 4px 4px 10px;}
 .table_layout_scroll {
   width:100%;
     max-height:251px;  
    overflow-y:scroll;
    border-bottom:1px solid #ddd;
    }
.btnscolor{background: #1d87da;
    border: none;
    color: #fff;
    padding: 5px 15px;
    margin-top: 18px;
    margin-right: 10px;
    margin-left: 10px;}
    .row_head {
background:#a5caf6;/* e0ecfa; */
}

.table_layout_scroll tr:nth-child(even) {background: #f7f7f7}
.table_layout_scroll tr:nth-child(odd) {background: #ffffff}

.table_layout_scroll tr
{
    border:1px solid #ddd;
}
table { border-collapse: collapse; }

.result_row_tr td {
border-right: 1px solid #bbb;
}
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
		  dateFormat: 'dd/mm/yy', 
		  timeFormat:  "hh:mm:ss",
		   changeMonth : true,
		   changeYear : true,
		   step:1,
		   minDate : '0d',
		    showMinute: true, showHour: true
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
	  	   step:1,
	  	   dateFormat: 'd-m-yy mm',
	  });
 });
$(document).ready(function() {
var time1 = $('.startTime'), //cache selectors
time2 = $('.endTime'),
result = $("#duration");
//alert(time1);
function toDate(dateStr) {
	//alert("in to date"+dateStr);
	  var parts_time = dateStr.split(" ");
	  var time=parts_time[1].split(":");
	  
	//  alert(time[1]);
	  //alert(time[0]);
	  var only_date=parts_time[0];
    var parts = only_date.split("/");
  //  alert(parts[2]);
    //alert(parts[1]);
    //alert(parts[0]);
    //var year_time=parts[0]+" "+ parts_time[1];
    //alert(year_time);
   //new Date( Date.parse("05/12/05 11:11:11") );
    return new Date(parts[2], parts[1] - 1, parts[0], time[0], time[1]);
}












/* $( ".endTime" ).blur(function()
	{
	var d1 =toDate(time1.val()); //convert to date object
		//alert(d1); 
     var d2 = toDate(time2.val()); //convert to date object
	//var d1 = new Date(time1.val()), //convert to date object
   // d2 = new Date(time2.val()), //convert to date object
    msec = d2.getTime() - d1.getTime(), //get difference in milliseconds
    hh = Math.floor(msec / 1000 / 60 / 60), //get hours
    dd = Math.floor(hh / 24); //calculate days
msec -= hh * 1000 * 60 * 60; //remove hours from msec
hh -= dd * 24; //remove days from hours
var mm = Math.floor(msec / 1000 / 60); //get minutes
//result.val(dd + ' days ' + hh + ' Hrs ' + mm + ' minutes');
$('#error').val('');
if(d1 >= d2 ){
	  $('#dis').slideDown().html('End time should be greater than start time');
	 
	  $('#duration').val('');
	  $('#endTime').val('');
}
else {
	  result.val( hh + ' Hrs ' + mm + ' minutes');
	  $("#dis").hide();
}

});  */  

//changes by manish
/* $( ".startTime").blur(function()  {

	var st=$('.startTime');
	var ft=todate(st.val());
	if(ft.getTime()<new Date().getTime())
		{
		alert('hello');
		return false;
		}
}); */

	
	


$( ".startTime" ).blur(function()  {
	
	var elementname = $(this).attr("name"); 
	var sesid=elementname.split("startTime"); 

	var ses_id=sesid[1]; 
	var end_time="endTime"+ses_id;  
	var time11=$("input[name="+elementname+"]").val(); 
	
	var time21=$("input[name="+end_time+"]").val(); 

	var d2=toDate(time11);
    var d1=toDate(time21);   
	
	msec = d1.getTime() - d2.getTime(), //get difference in milliseconds
	hh = Math.floor(msec / 1000 / 60 / 60), 
	
	dd = Math.floor(hh / 24); 
	
	msec -= hh * 1000 * 60 * 60;
	
	hh -= dd * 24; 
	
	var mm = Math.floor(msec / 1000 / 60); 


		 if(d2.getTime()<new Date().getTime() )
		{
			 $('#duration'+ses_id).val(''); 
		 $('.failure').slideDown().html('Start time should be greater than current time');
		 setTimeout( function(){$('.failure').hide();} , 4000);
		 $(this).css({      
				"border": "1px solid red",    
				});
		 return;
		}
	else
		{
		 $(this).css({      
				"border": "",    
				});
		} 
	//alert(d1.getFullYear()+""+d1.getMonth()+""+d1.getDate());
	//alert(d2.getFullYear()+""+d2.getMonth()+""+d2.getDate());
	
	
	if(d1 <= d2 ){   
		
	
		$('#duration'+ses_id).val(''); 
			} 
	else if(d1.getFullYear()+""+d1.getMonth()+""+d1.getDate() != d2.getFullYear()+""+d2.getMonth()+""+d2.getDate()){   
		   	$('.failure').slideDown().html('Start date & end date must be on same day');
		   	setTimeout( function(){$('.failure').hide();} , 4000);
			$(this).css({                  
				"border": "1px solid red",        
				});  
			$('#duration'+ses_id).val(''); 
			} else { 
				$('#duration' +ses_id).val(hh + ' Hrs ' + mm + ' minutes'); 
				//$('.dis'+ses_id).hide(); 
$(this).css({  
	"border": "",  
	});
} 
	});
$( ".endTime" ).blur(function() { var elementname = $(this).attr("name"); 
var sesid=elementname.split("endTime"); var ses_id=sesid[1];
var start_time="startTime"+ses_id;
var time11=$("input[name="+elementname+"]").val();
var time21=$("input[name="+start_time+"]").val(); 
var d2=toDate(time11);  var d1=toDate(time21);  
var d22 = toDate(time2.val()); 
msec = d2.getTime() - d1.getTime(), 
hh = Math.floor(msec / 1000 / 60 / 60), 
dd = Math.floor(hh / 24); 
msec -= hh * 1000 * 60 * 60; 
hh -= dd * 24; 
var mm = Math.floor(msec / 1000 / 60); 
$('#error').val('');
if(d1 >= d2 )
{ 
	 $('.failure').slideDown().html('End time should greater than start time');  
	 setTimeout( function(){$('.failure').hide();} , 4000);
$(this).css({                   
	"border": "1px solid red",                  
	});  
	$('#duration'+ses_id).val('');  
	}
else if(d1.getFullYear()+""+d1.getMonth()+""+d1.getDate() != d2.getFullYear()+""+d2.getMonth()+""+d2.getDate()){   
 	 $('.failure').slideDown().html('Start date & end date must be on same day');  
	 setTimeout( function(){$('.failure').hide();} , 4000);
	$(this).css({                  
		"border": "1px solid red",        
		});  
	$('#duration'+ses_id).val(''); 
	
}
else { 
		//var dur='duration '+ses_id; 
		$(this).css({   
		"border": "",    
	});
		$('#endtime').css({   
			"border": "",    
		});
	$('#duration' +ses_id).val(hh + ' Hrs ' + mm + ' minutes'); 
	
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

//onclick function for session id
function clickSessionId(){
	var sessionid=document.getElementById("sessionID").value;
	
	document.postsession.action="/KODE_DEV/ControllerServlet/PostPoneServlet?sessionid="+sessionid+"";
	document.postsession.submit();
}
function selectrcpt(opt)
{
	// alert("in"+opt);
	 /*alert("in"+opt.id); */
	var rtype=document.getElementById("rtype").value;
	//alert("in"+rtype);
	if(rtype=="dummy"){
		alert("Please select a valid Recipients");
		document.getElementById("rtype").focus();
		
	}
	
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
  
    /* 	var time1=$('#startTime');
    	var d1=toDate(time1.val());
    	var time2=$('endtime');
    	var d2=toDate(time2) */
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
       // alert('hi');
        
        /*  if(d1.getTime()<new Date().getTime()) {
        	$('#dis1').slideDown().html('Start time should be current or future time');
        	  isValid = false;
        	  ('#startTime').val();
              $('#startTime').css({
                  "border": "1px solid red",
                
                  //"background": "#FFCECE"
              });
              return false;
          }
          else {
              $('#startTime').css({
                  "border": "",
                 // "background": ""
              });
          }
        
        if(d2.getTime()<d1.getTime()) {
        	$('#dis1').slideDown().html('End time should be greater than start time');
        	  isValid = false;
        	  ('#endtime').val();
              $('#endtime').css({
                  "border": "1px solid red",
                  
                  //"background": "#FFCECE"
              });
              return false;
          }
          else {
              $('#endtime').css({
                  "border": "",
                 // "background": ""
              });
          } 
        
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
      alert(isValid); */
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
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Calendar cal = Calendar.getInstance();
		String date=dateFormat.format(cal.getTime());
		Date todayDate=null;
		ArrayList<SessionDomain> sessionDomain=new ArrayList<SessionDomain>();
		//msg = (String) mess.getAttribute("MsgValue");
		String organizationId=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		if(username==null)
			response.sendRedirect("../JSP/error.jsp?errmsg=Session Expired");
		sessionDomain = (ArrayList<SessionDomain>)request.getAttribute("sessionDetails");
		//System.out.println("organization id:"+organizationId+"created id:"+userid);
		
	%>
<body>

	<div class="container" style="position:static;">
		<%-- <%@include file="all_one_header_knowstore.jsp"%> --%>
		<%@include file="../JSP/headers.jsp"%>
		
		
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="postpon_mod">
		<div style="text-align: center;">
		<label class="failure"></label><br>
		<p class="strong">Modify Session(s)</p>
		<!-- <label id="dis" style="width:200px; color: red"></label><br> -->
		 <form name="postsession" id="postsession" action="/KODE_DEV/ControllerServlet/ManageSessionServlet" method="post">
		 <%if (request.getAttribute("FacultySuccess")!= null) { 
				String messsage=(String)request.getAttribute("FacultySuccess");
				
				%>
				
				<p class="success"><%= request.getAttribute("FacultySuccess") %></p>
				<%
			}
			 else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
					<p class="failure"><%= request.getAttribute("FacultyFailure") %></p>					
					<%
				} 
				
			%>
		 <div>
						
		<select name="sessionID" placeholder="Session Name"  class="opt" id="sessionID" onchange="clickSessionId()">
		<option value="">Choose Session </option>
		<option value="All">All</option>
								<%CreateSessionDao createSessionDao= new CreateSessionDao();
					           	ArrayList<SessionDomain> al=new ArrayList<SessionDomain>();
					           	al=createSessionDao.getSessionCategory(userid,organizationId);
					         	Iterator<SessionDomain> iterator =al.iterator();
								while(iterator.hasNext())
									{
									SessionDomain domain=iterator.next();
										String sessionName1=domain.getSessionName();
										String sessionID=domain.getSessionId();
										String IdAndName=sessionName1+" ("+sessionID+")";
										////System.out.println("value of category"+sessionName1);
									%>
										<option value="<%=sessionID%>"><%=IdAndName%></option>
									<%}%>
				    
		</select>
		</div>

		<div class="search_div">
		<input class="box1" type="text" id="search" placeholder="Search">
		</div>
		<div class="search_result">

		<!-- heading starts here  -->
		<div class="table_section1">
		<div class="table_outer_row" style="height:215px;">
		<table width="100%" id="tblData">	
		<thead>
			 <tr class="row_head">
       <th width="5%">Sl #<div class="header_stop" style="width:5%;">Sl #</div></th>
		<th width="10%">
		Session ID<div class="header_stop" style="width:10%;">Session ID</div>
		</th>
		
		<th width="10%">
		Session Name
		<div class="header_stop" style="width:10%;">Session Name</div>
		</th>
		<th width="10%">
		Course Name
		<div class="header_stop" style="width:10%;">Course Name</div>
	   </th>
		<th width="10%">
		Start Time
		<div class="header_stop" style="width:10%;">Start Time</div>
		</th>
		<th width="10%">
		End Time
		<div class="header_stop" style="width:10%;">End Time</div>
		</th>
		<th width="10%">
		Duration
		<div class="header_stop" style="width:10%;">Duration</div>
		</th>
		<th width="15%">
		Recipient Information
		<div class="header_stop" style="width:15%;">Recipient Information</div>
		</th>
		<th width="10%">
		 Status
		<div class="header_stop" style="width:10%;">Status</div>
		</th>
		<th width="10%">
		Action
		<div class="header_stop" style="width:10%;">Action</div>
		</th>
		</tr>
		</thead>
		<!-- 
			</table>
		</div> 
		<div class="table_layout_scroll" id="demo" style="width:1258px;">
		<table width="100%"> -->
		<% 
		int count=1;
		if(sessionDomain!=null)
			 {
			Iterator<SessionDomain> iterator1 =sessionDomain.iterator();
			 
				while(iterator1.hasNext())
				{
				SessionDomain sessionDomain1=iterator1.next();
				
				String datefromdb=sessionDomain1.getSessionStartTime();
				String sesid=sessionDomain1.getSessionId();
				Date dateFromdb = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(datefromdb);
				//System.out.println("todayDate:"+dateFromdb);
				//System.out.println("getStatus:"+sessionDomain1.getStatus());
				//if(dateFromdb.after(todayDate) && sessionDomain1.getStatus().equalsIgnoreCase("Available")){
					//System.out.println("session values in if::"+dateFromdb.equals(todayDate));
					
					String start_dt=sessionDomain1.getSessionStartTime();
					String end_dt=sessionDomain1.getSessionEndTime();
					DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
					//System.out.println("formatterf::"+formatter);
Date date_st = (Date)formatter.parse(start_dt);
Date date_end = (Date)formatter.parse(end_dt);
//System.out.println("date_st::"+date_st);
//System.out.println("date_end::"+date_end);
SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
SimpleDateFormat newFormattime = new SimpleDateFormat("HH:mm");
//System.out.println("newFormat::"+newFormat);
String start_date = newFormat.format(date_st);
String end_date = newFormat.format(date_end);
String start_time = newFormattime.format(date_st);
String end_time = newFormattime.format(date_end);
String startdate_time=start_date+" "+start_time;
String enddate_time=end_date+" "+end_time;
//System.out.println("start_date::"+start_date);
//System.out.println("end_date::"+end_date);
//System.out.println("start_time::"+start_time);
//System.out.println("end_time::"+end_time);

				//System.out.println("sesid="+sessionDomain1.getSessionId());
			 %>
			
			 <tr class="result_row_tr row">
			   <td style="text-align:center;"><%=count++ %></td>
				<td>
				<input type="text" name="sessionId<%=sessionDomain1.getSessionId() %>" id="sessionId" value="<%=sessionDomain1.getSessionId() %>" class="<%=sessionDomain1.getSessionId() %> required" readonly="readonly">
				</td>
				<td>
				<input type="text" name="sessionName<%=sessionDomain1.getSessionId() %>" value="<%=sessionDomain1.getSessionName() %>" class="<%=sessionDomain1.getSessionId() %> required">
				</td>
				<%-- <td class="sel">
							 <%CreateSessionDao dao= new CreateSessionDao();
					           ArrayList<AdminDomain>al1=new ArrayList<AdminDomain>();
							    al1=dao.getSubjectId(userid);
							    Iterator<AdminDomain> it= al1.iterator();%>
							    <select name="category<%=sessionDomain1.getSessionId() %>" class="<%=sessionDomain1.getSessionId() %> required">
							    <option value="<%=sessionDomain1.getCategory() %>"><%=sessionDomain1.getCategory() %></option>
							    <% while(it.hasNext())
							    {
							    	String id=((AdminDomain)it.next()).getSubject_id();%>
							    <option value="<%= id %>"><%=id%></option>	
							   <% }%>
							   </select>
						</td> --%>
				 <td>
				<input type="text" name="category<%=sessionDomain1.getSessionId() %>" value="<%=sessionDomain1.getCategory() %>" class="<%=sessionDomain1.getSessionId() %> required" onblur="getSessionTime()" readonly="readonly"><span id="amit"></span>
				</td> 
				<td>
				<input class="starttime <%=sessionDomain1.getSessionId() %> required startTime" id="startTime" type="text" value="<%=startdate_time%>" name="startTime<%=sessionDomain1.getSessionId() %>" readonly="readonly">
				</td>
				<td  style="text-align:center;">
				<input class="endtime <%=sessionDomain1.getSessionId() %> required endTime" id="endTime<%=sessionDomain1.getSessionId() %>" type="text" value="<%=enddate_time%>" name="endTime<%=sessionDomain1.getSessionId() %>" readonly="readonly">
				<label class="dis<%=sessionDomain1.getSessionId() %>" id="dis" style="width:250px; color: red"></label><br>
				</td>
				<td>
				<input class="duration_r <%=sessionDomain1.getSessionId() %> required duration" id="duration<%=sessionDomain1.getSessionId() %>" type="text" value="<%=sessionDomain1.getDuration() %>" name="duration<%=sessionDomain1.getSessionId() %>" readonly="readonly">
				<input type="hidden" value="0" name="costOfsession<%=sessionDomain1.getSessionId() %>" id="costOfSession">
				<label id="special" style="width:250px; color: red"></label><br>
				</td>
				<td>
				<%-- <input type="text" class="<%=sessionDomain1.getSessionId() %>" value="<%=sessionDomain1.getRecipientType() %>" name="recipientType<%=sessionDomain1.getSessionId() %>" readonly=""> --%>
				<%-- <select class="<%=sessionDomain1.getSessionId() %>" name="status<%=sessionDomain1.getRecipientType() %>">
				<option value="<%=sessionDomain1.getRecipientType() %>"><%=sessionDomain1.getRecipientType() %></option>
				<%if(sessionDomain1.getStatus().equalsIgnoreCase("Available")){ %>
				<option value="cancel">Cancel</option>
				<%} else  if(sessionDomain1.getStatus().equalsIgnoreCase("Cancel")){%>
				<option value="available">Available</option>
				<%} %>
				</select> --%>
				<select  class="<%=sessionDomain1.getSessionId() %> required" name="recipientType<%=sessionDomain1.getSessionId()%>" id="rtype">
				<%-- <option value="<%=sessionDomain1.getRecipientType() %>"><%=sessionDomain1.getRecipientType() %></option> --%>
			<% 
			String RType=sessionDomain1.getRecipientType();
			//System.out.println("RType="+RType);
			if(RType.contains("#")){
        		String groupValue=sessionDomain1.getRecipientType();
        		String groupName=createSessionDao.getGroupName(userid,organizationId,groupValue);
				}
			if(RType.equalsIgnoreCase("all")){
			%>
			<option  id="all" class="all" selected="selected">All </option>
			<%}else{ %>
			<option  id="all" class="all">All </option>
			<%} %>
				<option Value="dummy">-----------Individual----------- </option>
							 <% CreateSessionDao asdao=new CreateSessionDao();
                             ArrayList<AdminDomain> sl=new ArrayList<AdminDomain>();
                             sl=asdao.sendIndualGroup(userid,organizationId);
		                      Iterator<AdminDomain> it1= sl.iterator();
								while(it1.hasNext())
		                     {
									AdminDomain domain=it1.next();
		    	             String id=domain.getAdminId();
		    	             String name=domain.getAdminName();
		    	             String idname=name+" ("+id+")";
		    	             if(RType.equalsIgnoreCase(id)){
		    	             %>
		                      <option  value="<%= id %>" selected="selected"><%=id%></option>	
		                         <%} else {%>
		                      <option  value="<%= id %>"><%=idname%></option>
		                      <%}
		    	             }%>
		    	             
		    	             <option Value="dummy">-------------Group------------- </option>
							<%
                             ArrayList<AdminDomain>al2=new ArrayList<AdminDomain>();
		                   al2=asdao.sendGroupNameAndStuId(userid,organizationId);
		                      Iterator<AdminDomain> it2= al2.iterator();
		                    while(it2.hasNext())
		                     {
		                    	AdminDomain adom=it2.next();
		    	             String id=adom.getGroup_name();
		    	             String grp=adom.getNew_Group_name();
		    	             String indid=adom.getIndivialStudentId();
		    	             //System.out.println("grpname="+id+";indid="+indid);
		    	             if(RType.equalsIgnoreCase(indid) || RType.equalsIgnoreCase(grp+"_group") || RType.equalsIgnoreCase(grp)){
		    	             %>
		    	             <option  value="<%= id %>" selected="selected"><%=grp%></option>
		    	             <%} else{ %>
		                      <option  value="<%= id %>"><%=grp%></option>	
		                      <%}
		    	             }
		    	             
		    	             %>

	        	
			
			</select>
				</td>
				<td>
				<select class="<%=sessionDomain1.getSessionId() %>" name="status<%=sessionDomain1.getSessionId() %>">
				<option value="<%=sessionDomain1.getStatus() %>"><%=sessionDomain1.getStatus() %></option>
				<%if(sessionDomain1.getStatus().equalsIgnoreCase("Available")){ %>
				<option value="Cancel">Cancel</option>
				<%} else  if(sessionDomain1.getStatus().equalsIgnoreCase("Cancel")){%>
				<option value="Available">Available</option>
				<%} %>
				</select>
				<%-- <input type="text" class="<%=sessionDomain1.getSessionId() %>" value="<%=sessionDomain1.getStatus() %>" name="status<%=sessionDomain1.getSessionId() %>"> --%>
				</td>
				<td>
				<a href="#">
				<input id="<%=sessionDomain1.getSessionId() %>" type="checkbox" name="checkboxGroup" value="<%=sessionDomain1.getSessionId() %>"/>
				</a>
				</td>
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
		                    <% while(it1.hasNext())
		                     {
		    	             String id=it1.next().getAdminId();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
	                
						</td>
					</tr>
					 <tr class="group_tr" style="display:none">
						 <td align="left">
							<font>Group ID</font>
						</td>
						<td>:</td> 
						
						<td class="group_sel">
							 <% 
		
                             ArrayList<AdminDomain>al1=new ArrayList<AdminDomain>();
		                   al1=asdao.sendGroupId(userid,organizationId);
		                      Iterator<AdminDomain> it2= al1.iterator();%>
		                      <select name="group_id" class="required" id="group_id">
		                      <option value="">Choose Group ID</option>
		                    <% while(it2.hasNext())
		                     {
		    	             String id=it2.next().getGroup_name();%>
		                      <option  value="<%= id %>"><%=id%></option>	
		                         <%  }%>
		                      </select>
						</td>
					</tr>
					 -->
		</table>
		</div>
		</div>
		<a>
		<input class="btnscolor" type="submit" name="updateButton" onclick="clickSave()" id="updateButton" value="Update" /></a> 
	<!-- 	<input class="btnscolor" type="button" value="Cancel" id="buttonCancel" onclick="clickCancel()"/></a> -->
		
		</div>
		</form>
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
				$('#search').keypress(function(event) { 
					
					if (event.keyCode == 13) {  
						event.preventDefault();
						} 
					
				});
				
			});
			function searchTable(inputVal)
			{
				 var table = $('#tblData');
			        table.find('tr').each(function(index, row)
			        {
			            var allCells = $(row).find("td,input");


			            if(allCells.length > 0)
			            {
			                var found = false;
			                allCells.each(function(index, td, input)
			                {
			                    var regExp = new RegExp(inputVal, 'i');
			                    var t_value = $(td).text();
			                    var input_value = $(td).children('input').val();
			                    if(regExp.test(t_value) || regExp.test(input_value))
			                    {
			                        found = true;
			                        return false;
			                    }
			                });
			                if(found == true)$(row).show();else $(row).hide();
			            }

			        });	
			
				/* var table = $('#tblData');
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
						 
						if(found == true)$('tr').show();else $(row).hide();
						/* alert("message"); 
					}
				}); */
			}
		</script>
</body>
<script>
onload();
</script>
<style>
.autohide {
position:absolute;
top:197px;
right:580px;
font-size:14px;
font-weight:bold;
}
.failure:empty {
display:none;}
</style>
</html>