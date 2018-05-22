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


<script type="text/javascript" src="../JS/validatePostSession.js"></script>

<link href="../CSS/table_scroll.css" rel="stylesheet"></link>
<link href="../CSS/MISReports.css" rel="stylesheet"></link>
<link href="../CSS/design-common.css" rel="stylesheet"/>
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

.opt{
width: 265px !important;

border: 1px solid #C2C2C2;
font-family: regular;
border-radius: 4px;

}

.btn_class{
background: #009AE1;color: #fff;	

width: 120px;

}

.combo_box_column {
	width:100%;
	margin:0px;
	list-style:none;
	padding-bottom:20px;
	float:left;
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

</script>

<script>
function onload(){
	
		<%
		String courseval=(String)request.getAttribute("Course");
		if(courseval!=null && courseval.length()>0) { 
		%>
		
		var selectBox = document.getElementById("courseval");
		 for(var i=0;i<selectBox.options.length;i++){
				 if(selectBox.options[i].value == '<%=courseval%>')
				{
		 			selectBox.options[i].selected=true;
					i=selectBox.options.length;
				 }
			 } 
					
			<%} 
			String ExpRepFormat=(String)request.getAttribute("ExpRepFormat");
			
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
	
	var courseval=document.getElementById("courseval").value;
	if(courseval=="")
		alert("Please choose Course");
	else
		{
				
		document.ksStore.action="/KODE_DEV/ControllerServlet/MISReportListService?RepID=MIS_AC_010&Course="+courseval+"&ExpRepFormat=None";
		document.ksStore.submit();	
		}
	
}
function resetDownloadPath()
{
	var div=document.getElementById("ExportDiv");
	div.innerHTML="";
}

function actionRunExport(){
	
	var courseval=document.getElementById("courseval").value;
	
	var ExportVal=document.getElementById("exportval").value;
var x = document.getElementById("tableColumn").rows.length;
	
	if(x>1)
		if(ExportVal=="PDF")
		{
			 document.ksStore.action="/KODE_DEV/ControllerServlet/MISReportListService?RepID=MIS_AC_010&&Course="+courseval+"&ExpRepFormat="+ExportVal;
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
</script>

<script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>

		<%
		String organizationId=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		String previlege = session.getAttribute("previlege").toString().trim();
		ArrayList<HashMap<String,String>> reportDomain1=null;
		
		MISReportsDao MISdao=new MISReportsDao();
		ArrayList<HashMap<String,String>> courseList= MISdao.getCourseDetailsByOrgId(organizationId);
		
		
		reportDomain1 = (ArrayList<HashMap<String,String>>)request.getAttribute("tabledata");
		
	   	%>
<body>

	<div class="container">
		<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<% if(previlege.trim().equalsIgnoreCase("Super Admin"))
		{
		%>
		<%@ include file= "../JSP/SuperAdminMenu.jsp" %>
		<%
		} else if(previlege.trim().equalsIgnoreCase("Student"))
		{
		%>
		<%@ include file= "../JSP/Participant_menu.jsp" %>
		<% } %>
		</div>
		<div style="clear: both;"></div>
		<div class="postpon_mod">
			<div style="text-align: center;">
				<p class="strong">Students Exam Not-Eligible</p>
		 		<form name="ksStore" method="post">
		 			<div class="combo_box_column row">
						<select name="courseval" id="courseval" class="opt"  >
						 <option value=" " selected>Choose Course*</option>
						 <!-- <option value="All" >All</option> -->
						 <% if(courseList!=null && courseList.size()>0)
						 {
							 for(int i=0;i<courseList.size();i++)
							 {
								 HashMap<String,String> info=courseList.get(i);
								 String TS_course_id=info.get("TS_course_id").toString().trim();
								 String course_name=info.get("course_name").toString().trim();
								 String course_id=info.get("course_id").toString().trim();
								 
								 String CourseFullName=course_name +" ("+TS_course_id+")";
								
								 %>
								<option value='<%=course_id%>'><%=CourseFullName %> </option>
							<% }
						 }
						 %>
						 </select>		
						
		 				<input id="Get_List" type="submit" value="Get Records"  class="btn_class" />
					</div>
		
		<!-- heading starts here  -->
		<div class="table_section1">
		<div class="table_outer_row">
		<table width="100%" id= "tableColumn" >
		<thead>
		 <tr class="row_head">
			<th width="20%">Student ID<div class="header_stop" style="width:20%;">Student ID</div></th>
			<th width="20%">Student Name<div class="header_stop" style="width:20%;">Student Name</div></th>
			<th width="20%">Contact No<div class="header_stop" style="width:20%;">Contact No</div></th>
			<th width="20%">Email<div class="header_stop" style="width:20%;">Email</div></th>
			<th width="20%">Reason<div class="header_stop" style="width:20%;">Reason</div></th>
		</tr>
		</thead>
		
		
		<%
		if(reportDomain1!=null && reportDomain1.size()>0)
		{
			System.out.println("reportDomain1 size ::  "+reportDomain1.size());
			for(int i=0;i<reportDomain1.size();i++)
			{
				HashMap<String,String> stuinfo=reportDomain1.get(i);
			
			
			%>
		<tr class="result_row_tr">
		<td width="20%">
		<%=stuinfo.get("enrollment_process_id").toString().trim()%>
		</td>
		<td width="20%">
		<%=stuinfo.get("full_name").toString().trim()%>
		</td>
		<td width="20%" style="text-align:center;">
		<%=stuinfo.get("phone").toString().trim()%>
		</td>
		<td width="20%">
		<%=stuinfo.get("email").toString().trim()%>
		</td>
		<td width="20%">
		<%=stuinfo.get("reason").toString().trim()%>
		</td>
		</tr>
		 <%}
		}
		else if (reportDomain1!=null && reportDomain1.size()==0)
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
	<input id="Export" type="button" value="Export" onclick="actionRunExport()" class="btn_class" />
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
height:220px;}
</style>
</html>