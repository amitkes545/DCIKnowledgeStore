<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.*" %>
<%@page import="com.kds.KODE_DEV.dao.MISReportsDao" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.js"></script>

<script type="text/javascript" src="../JS/validatePostSession.js"></script>

<link href="../CSS/table_scroll.css" rel="stylesheet"></link>
<link href="../CSS/MISReports.css" rel="stylesheet"></link>
<link href="../CSS/design-common.css" rel="stylesheet"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"
            type="text/javascript"></script>
    <link href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/start/jquery-ui.css"
          rel="Stylesheet" type="text/css" />
          
<style type="text/css">
.search_result{padding: 0px 0px;margin-bottom: 25px;max-height: 210px;
overflow-y: scroll;}
.strong
{
font-size: 20px;
color: #0094DC;
text-align: center;
margin: 7px -41px 10px 5px;
font-family: bold;
}

.container{
background: url("../Image/body.png") center center no-repeat;
}
</style>
<style type="text/css">
.postpon_mod{
width:95% !important;
    box-shadow: 5px 5px 5px 5px #FFF;
    margin: 15px auto 0px;
    padding: 15px 1px 0px 0px;
    border: 3px solid #C2C2C2;
    background-color: #FCF7F7;
    border-radius: 4px;
    opacity:1;
    margin-top: 75px;
    height: 350px;
    
}

.search_div{
margin-top: 12px;
text-align: center;
}
.opt{
width: 265px !important;

border: 1px solid #C2C2C2;
font-family: regular;
border-radius: 4px;

}

#Get_List{
background: #009AE1;color: #fff;	

width: 120px;

}
#Export{
background: #009AE1;color: #fff;	

width: 100px;

}
.combo_box_column {
	width:100%;
	margin:0px;
	list-style:none;
	padding-bottom:20px;
	float:left;
}

.box1
{

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
.result_row_tr td input{border: none; background: none; width: 99.5%;}
.result_row_tr td{border: 1px solid #000;padding: 4px;}
</style>
</head>
<script>

$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
	
	validateDates();
});

function validateDates()
{
	
	 $("#EDate").datepicker({ dateFormat: 'dd/mm/yy' });
    $("#SDate").datepicker({ dateFormat: 'dd/mm/yy' }).bind("change",function(){
        var minValue = $(this).val();
        minValue = $.datepicker.parseDate("dd/mm/yy", minValue);
        minValue.setDate(minValue.getDate()+1);
        $("#EDate").datepicker( "option", "minDate", minValue );
    })
   
	
   

}
</script>

<script>
function onload(){
				
		<%
		String sessionId=(String)request.getAttribute("sessionId");
		if(sessionId !=null && sessionId.length()>0) { 
		%>
		
		var selectBox = document.getElementById("sessionId");
		 for(var i=0;i<selectBox.options.length;i++){
				 if(selectBox.options[i].value == '<%=sessionId%>')
				{
		 			selectBox.options[i].selected=true;
					i=selectBox.options.length;
				 }
			 } 
					
			<%}
		
			String startDate=(String)request.getAttribute("startDate");
			String endDate=(String)request.getAttribute("endDate");
			String ExpRepFormat=(String)request.getAttribute("ExpRepFormat");
			
			String ReportPath="None";
			if(ExpRepFormat!=null && !ExpRepFormat.trim().equalsIgnoreCase("None"))
				ReportPath=(String)request.getAttribute("ReportPath");

			
			if(startDate==null){
			%>
				document.getElementById("SDate").value='';
			<% } else { %>
				document.getElementById("SDate").value='<%=startDate%>';
			<% } 
			if(endDate==null){
				%>
					document.getElementById("EDate").value='';
				<% } else { %>
					document.getElementById("EDate").value='<%=endDate%>';
				<% } %>
			
			var ExpSelectBox=document.getElementById("exportval");
			for(var i=0;i<ExpSelectBox.options.length;i++){
				 if(ExpSelectBox.options[i].value == '<%=ExpRepFormat%>')
				{
					 ExpSelectBox.options[i].selected=true;
					 i=ExpSelectBox.options.length;
				 }
			 }
			validateDates();
			
}
$(document).ready(function() {
	 $("#Get_List").click(function(e) {
	    	var isValid = true;
	    	  $('.opt').each(function() {
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
	        
		        $('input[type="text"].box1').each(function() {
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
		        
	    	  
	        // here end working ok 
 if (isValid == false){
	        	
	            e.preventDefault();
	           
	        }
	        else {
	        	 //alert('Thank you for submitting')
	        	 actionGetList();
	        }
	    });
});
function actionGetList(){
	
	var sessionId=document.getElementById("sessionId").value;
	
	var startDate=document.getElementById("SDate").value;
	var endDate=document.getElementById("EDate").value;
	
	document.ksStore.action="/KODE_DEV/ControllerServlet/MISReportListService?RepID=MIS_UA_011&startDate="+startDate+"&endDate="+endDate+"&sessionId="+sessionId+"&ExpRepFormat=None";
	document.ksStore.submit();
}

function resetDownloadPath()
{
	var div=document.getElementById("ExportDiv");
	div.innerHTML="";
}

function actionRunExport(){
	
	var sessionId=document.getElementById("sessionId").value;
	
	var startDate=document.getElementById("SDate").value;
	var endDate=document.getElementById("EDate").value;
	
	var exportval=document.getElementById("exportval").value;
var x = document.getElementById("tableColumn").rows.length;
	
	if(x>1)
		if(exportval=="PDF") 
		{
			document.ksStore.action="/KODE_DEV/ControllerServlet/MISReportListService?RepID=MIS_UA_011&startDate="+startDate+"&endDate="+endDate+"&sessionId="+sessionId+"&ExpRepFormat="+exportval;
			document.ksStore.submit();		
		}else if(exportval=="Excel")
		{
			
			<% ArrayList<HashMap<String,String>> tabledata=(ArrayList<HashMap<String,String>>)session.getAttribute("tabledata"); 
			if(tabledata!=null && tabledata.size()>0)
			{%>
			
			var div=document.getElementById("ExportDiv");
			div.innerHTML="<a id='ExportLink' href='../JSP/DownloadExportFile.jsp' > Click here to Download Excel </a>";
			
			
		<%	} %>
			
		}
	
}
</script>

<script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>

		<%
		String organizationId=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		
		MISReportsDao MISdao=new MISReportsDao();
		ArrayList<HashMap<String,String>> sessionList= MISdao.getSessionDetailsByOrgIdByrole(userid, username, organizationId);
		
		
		ArrayList<HashMap<String,String>> reportDomain1=null;
		reportDomain1 = (ArrayList<HashMap<String,String>>)request.getAttribute("tabledata");
	   	
		
		%>
<body>

	<div class="container">
		<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/Admin_menu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="postpon_mod">
		<div style="text-align: center;">
		<p class="strong">Details of Disconnected Classrooms</p>
		 <form name="ksStore" method="post">
		 <div class="combo_box_column row">
		 
		 <input class="box1" type="text" value=" " id="SDate" placeholder="Start Date*" >
		 <input class="box1" type="text"  value=" " id="EDate" placeholder="End Date*" >
		 <select name="sessionId" id="sessionId" class="opt"  >
		 <option value=" " selected>Choose SessionID*</option>
		 <option value="All" >All</option>
		 <% if(sessionList!=null && sessionList.size()>0)
		 {
			 for(int i=0;i<sessionList.size();i++)
			 {
				 HashMap<String,String> info=sessionList.get(i);
				 String SessionID=info.get("session_id").toString().trim();
				 String SessionName=info.get("session_name").toString().trim();
				 
				 String SessionFullName=SessionName +"("+SessionID+")";
				
				 %>
				<option value='<%=SessionID%>'><%=SessionFullName %> </option>
			<% }
		 }
		 %>
		 </select>		
		 	<input id="Get_List" type="submit" value="Get Records" />
	</div>
		
		<!-- heading starts here  -->
	<div class="table_section1">
		<div class="table_outer_row">
		<table width="100%" id= "tableColumn" >
		<thead>
		 <tr class="row_head">
			<th width="20%">Date	<div class="header_stop" style="width:20%;">Date</div></th>
			<th width="20%">Session ID<div class="header_stop" style="width:20%;">Session ID</div></th>
			<th width="20%">Session Name<div class="header_stop" style="width:20%;">Session Name</div></th>
			<th width="20%">Student ID <div class="header_stop" style="width:20%;">Student ID</div></th>
			<th width="20%">Student Name<div class="header_stop" style="width:20%;">Student Name</div></th>
			
		</tr>
		</thead>

		
		<%
		if(reportDomain1!=null && reportDomain1.size()>0)
		{
			for(int i=0;i<reportDomain1.size();i++)
			{
				HashMap<String,String> stuinfo=reportDomain1.get(i);
			
			
			%>
		<tr class="result_row_tr">
			<td width="20%" style="text-align:center;">
			<%=stuinfo.get("Date").toString().trim() %>
			</td>
			<td width="20%" style="text-align:center;">
			<%=stuinfo.get("SessionID").toString().trim()%>
			</td>
			<td width="20%">
			<%=stuinfo.get("SessionName").toString().trim()%>
			</td>
			<td width="20%" style="text-align:center;">
			<%=stuinfo.get("StudentID").toString().trim()%>
			</td>
			<td width="20%">
			<%=stuinfo.get("StudentName").toString().trim()%>
			</td>
		</tr>
		 <%}
		} else if (reportDomain1!=null && reportDomain1.size()==0)
		{
		%>
		<br><h2> No Records Found</h2>
		<%} %>
		
		</table>
		</div>
		</div>
		</form>
		</div>
	</div>
	<br>
	<div class="wrapper row" style="text-align:center">
	<select name="exportval" id="exportval" class="opt" onchange="resetDownloadPath()">
	 <option value="None">None</option>
	 <option value="PDF">PDF</option>
	 <option value="Excel">Excel</option>
	 </select>
	<input id="Export" type="button" value="Export" onclick="actionRunExport()" />
	<div id="ExportDiv"> 
			<% if(!ReportPath.trim().equalsIgnoreCase("None"))
				{ %>
					<a href='../JSP/ShowFile.jsp?ReportPath=<%=ReportPath %>' > Click here to Download PDF</a>
				<%	}%>
	 	</div>
	</div>
	
		<%@ include file="../JSP/FooterViews.jsp"%>
	</div>
</body>
<script>
onload();
</script>
<style>
.table_section1 {
margin-top:0px;
float:left;}
.table_outer_row {
height:220px;
}
</style>
</html>