<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.kds.KODE_DEV.dao.MISReportsDao"%>
<%@page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.js"></script>
<script src="../JS/datetimepicker_css.js"></script>
<script type="text/javascript" src="../JS/validatePostSession.js"></script>
<link href="../CSS/jquery.datetimepicker.css" rel="stylesheet"></link>
<link href="../CSS/table_scroll.css" rel="stylesheet"></link>
<link href="../CSS/MISReports.css" rel="stylesheet"></link>
<link href="../CSS/design-common.css" rel="stylesheet"/>
<script type="text/javascript" src="../JS/jquery.datetimepicker.js"></script>

<style type="text/css">
.search_result{padding: 0px 0px;margin-bottom: 25px;max-height: 260px;
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
width: 95% !important;
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
font-size: 18px;
width: 100px;
height:35px;
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
	height:20px;
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

});
function resetDownloadPath()
{
	var div=document.getElementById("ExportDiv");
	div.innerHTML="";
}

function actionRunExport(){
	
	var ExportVal=document.getElementById("exportval").value;
var x = document.getElementById("tableColumn").rows.length;
	
	if(x>1)
		if(ExportVal=="PDF")
		{
			document.ksStore.action="/KODE_DEV/ControllerServlet/MISReportListService?RepID=MIS_AD_001&ExpRepFormat="+ExportVal;
			document.ksStore.submit();		
		}else if(ExportVal=="Excel")
		{
			
			<% ArrayList<HashMap<String,String>> tabledata=(ArrayList<HashMap<String,String>>)session.getAttribute("tabledata"); 
			if(tabledata!=null && tabledata.size()>0)
			{%>
			
			var div=document.getElementById("ExportDiv");
			div.innerHTML="<a id='ExportLink' href='../JSP/DownloadExportFile.jsp' > Click here to Download Excel </a>";
			
			
		<%	} %>
			
		}
	
}

function onload()
{
	<% String ExpRepFormat=(String)request.getAttribute("ExpRepFormat"); 
	
	String ReportPath="None";
	if(ExpRepFormat!=null && !ExpRepFormat.trim().equalsIgnoreCase("None"))
		ReportPath=(String)request.getAttribute("ReportPath");

	%>
	var ExpSelectBox=document.getElementById("exportval");
	for(var i=0;i<ExpSelectBox.options.length;i++){
		 if(ExpSelectBox.options[i].value == '<%=ExpRepFormat%>')
		{
			 ExpSelectBox.options[i].selected=true;
			 i=ExpSelectBox.options.length;
		 }
	 }
}
</script>


<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>

		<%
		String organizationId=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		ArrayList<HashMap<String,String>> reportDomain1=null;
		MISReportsDao dao=new MISReportsDao();
		reportDomain1=dao.getPendingRegistrationsByOrgID(organizationId);
		
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
		<p class="strong">Details of Pending Registrations</p>
		  <form name="ksStore" method="post">		
		<!-- heading starts here  -->
		<div class="table_section1">
		<div class="table_outer_row">
		<table width="100%" id= "tableColumn" >
		<thead>
		 <tr class="row_head"  >
			<th width="20%">Student ID	<div class="header_stop" style="width:20%;">Student ID</div></th>
			<th width="20%">Student Name <div class="header_stop" style="width:20%;">Student Name</div></th>
			<th width="20%">Email ID<div class="header_stop" style="width:20%;">Email ID</div></th>
			<th width="20%">Contact No.	<div class="header_stop" style="width:20%;">Contact No.</div></th>
			<th width="20%">Country<div class="header_stop" style="width:20%;">Country</div></th>
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
		<td width="20%" style="text-align:center">
		<%=stuinfo.get("UserId").toString().trim() %>
		</td>
		<td width="20%">
		<%=stuinfo.get("UserName").toString().trim()%>
		</td>
		<td width="20%">
		<%=stuinfo.get("Email").toString().trim()%>
		</td>
		<td width="20%" style="text-align:center">
		<%=stuinfo.get("contact_no").toString().trim()%>
		</td>
		<td width="20%">
		<%=stuinfo.get("country").toString().trim()%>
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
float:left;
margin-top:0px;}
.table_outer_row {
height:270px;
}
</style>
</html>