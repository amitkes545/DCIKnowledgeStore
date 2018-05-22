<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.kds.KODE_DEV.domain.*"%>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorSelectKnowStoreIdDao"%>
<%@page import="java.util.*" %>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorSelectKsidKStoreDao" %>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorManageLibDao"%>
 <%@page import="java.util.Date"%>
       <%@page import="java.util.*"%>
     <%@page import="java.io.File" %>
       <%@page import="java.text.*" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/FacilitatorKnowSetup.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<!-- <link href="../CSS/owner.css" rel="stylesheet"/>
<link href="../CSS/global.css" rel="stylesheet"/> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<script src="../JS/datetimepicker_css.js"></script>
<script type="text/javascript" src="../JS/validatePostSession.js"></script>
<link href="../CSS/jquery.datetimepicker.css" rel="stylesheet"></link>
<link href="jquery.phancy.css" rel="stylesheet" />
<!--  <link rel="stylesheet" href="../CSS/dci-style.css" type="text/css" />-->
<link href="../CSS/table_scroll.css" rel="stylesheet"/>
<link href="../CSS/design-common.css" rel="stylesheet"/>
<!-- <script type="text/javascript" src="../JS/jquery.datetimepicker.js">
</script> -->
<style type="text/css">

.search_result{
padding: 0px 10px;
margin-bottom: 25px;
max-height: 310px;
font-family: arial;
font-size: 14px;
/* overflow-y: scroll; */
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
margin-top: -37px;
position: absolute;
right: 107px !important;
height: 33px;
padding: 6px !important;
font-family: regular;
border-radius: 4px;
}
.opt{
width: 265px !important;
padding: 6px !important;
height: 31px;
border: 1px solid #C2C2C2;
/* font-family: regular; */
border-radius: 4px;
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
   /*  padding: 5px; */
        border-right: 1px solid #bbb;
    }	
     .table_layout_scroll {
   width:100%;
     max-height:251px;  
    overflow-y:scroll;
    border-bottom:1px solid #ddd;
    }
.row_head .col_2{float: left; padding: 0px 15px;}
.row_result .col_2{float: left; padding: 0px 15px;}
.result_row_tr td input{border: none; background: none; width: 99.5%;}
.starttime{width: 130px;}
.endtime{width: 120px;}
.duration_r{width: 120px;}
.cost_r{width: 80px;}
.result_row_tr td{padding: 4px;}

.row_head {
background:#a5caf6;/* e0ecfa; */
}

.table_layout_scroll tr:nth-child(even) {background: #f7f7f7}
.table_layout_scroll tr:nth-child(odd) {background: #ffffff}

.result_row_tr td {
padding:7px 4px;
}
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

$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
	
	 
	    });
</script>
<script src="../JS/jquery.phancy.js"></script> 
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			$('#choose_subject').change(function () {
				//alert("hai");
			  	 var selectedValue = this.selectedOptions[0].value;
			     var selectedText  = this.selectedOptions[0].text;
			 //    alert(selectedText);
			   //   alert("Result Test"+selectedValue);
			      var courseid = $("#choose_assignment").val();
			      var subjectid = $("#choose_subject").val();
						     
				     var subject =   $("#dummy").val()
			     var ctx = "${pageContext.request.contextPath}";
			  
			     $.get('${pageContext.request.contextPath}/getLibarayForFaculty', {selectedValue : selectedValue,courseid : courseid,subjectid : subjectid},
			    		  function(jsonResponse) {
			   			    			console.log("json response :",jsonResponse);
			    			
			    			var select = $('#libid');
		    	     		select.find('option').remove();
		    	     		jsonResponse = $.parseJSON(jsonResponse);
		    	     		console.log("parsed json response :", jsonResponse);
		    	     		$('<option>').val('').text('Choose Library').appendTo(select);
		    	     		$.each(jsonResponse, function(index, value) 
		    	     		{
		    	     		
		    	     			console.log('Index value here :',index);
				    	 		 console.log('Actual obj value here :',value);
				    	 		$('<option>').val(value.libId).text(value.libId+" ("+value.libName+") ").appendTo(select);
				    	 		$("#sub").val(subjectid);
		    				 });     		
		    				
				    	 		 //$("#libid").val(value.libid);
				    	 	
				    	 		
				    	 	//	$('<option>').val(value.topicId).text(value.libid).appendTo(select); 
				    	 	//	alert("DINESH "+val(value.topic));
				    	 		//$('#selectvalue1').val(value.topicId);
			    	     		
			    			
			    			
    	     	    	      
			    		});
			 });
		});
		function onload(){
			
			<% String libid=(String)request.getAttribute("libid");
			String courseId=(String)request.getAttribute("courseid");
			String subjectId=(String)request.getAttribute("subjectid");
			
			System.out.println("ks id on onload:"+libid);
			System.out.println("ks id on onload:"+courseId);
			System.out.println("ks id on onload:"+subjectId);
			
			if(libid!=null && libid.length()>0) {
				////System.out.println("ks id on onload:"+libid);
			 %> 
			 var selectBox = document.getElementById("libid");
			 
			 for(var i=0;i<selectBox.options.length;i++){
					 if(selectBox.options[i].value == '<%=libid%>')
					{
			 selectBox.options[i].selected=true;
						 break;
					 }
				 }
						
				<%}
				if(courseId!=null && courseId.length()>0) {
					////System.out.println("ks id on onload:"+libid);
					 %> 
					 var selectBox = document.getElementById("choose_assignment");
					 for(var i=0;i<selectBox.options.length;i++)
					 {
							 if(selectBox.options[i].value == '<%=courseId%>')
						{
					 selectBox.options[i].selected=true;
								 break;
							 }
						 }
								
						<%}
						if(subjectId!=null && subjectId.length()>0) {
							System.out.println("ks id on onload subjectId:"+subjectId);
						 %> 
						 var selectBox = document.getElementById("choose_subject");
						 for(var i=0;i<selectBox.options.length;i++)
						 {
								 if(selectBox.options[i].value == '<%=subjectId%>')
							{
						 selectBox.options[i].selected=true;
									 break;
								 }
							 }
									
							<%}%>
							
				
		}
		
	
		


		function clickKsId(){
			//alert("in");
			var libId=document.getElementById("libid").value;
			var courseId=document.getElementById("choose_assignment").value;
			var subjectId=document.getElementById("choose_subject").value;
			//alert(" subjectId "+subjectId);
			//document.ksStore.action="/KODE_DEV/ControllerServlet/FacilitatorAssetKnowViewServlet?libID="+libId+"";
			document.ksStore.action="/KODE_DEV/ControllerServlet/FacilitatorAssetKnowViewServlet?libID="+libId+"&courseId="+courseId+"&subjectId="+subjectId+"";
			//alert("in");
			document.ksStore.submit();
			//alert("in1");
		}
			$(document).ready(function()
			{
				$('#search').keyup(function()
				{
					searchTable($(this).val());
				});
			});
			function searchTable(inputVal)
			{
				var table = $('#tableId');
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
						if(found == true)$(row).show();else $(row).hide();
						//if(found == true)$('tr').show();else $(row).hide();
					}
				});
			}
		</script>
<%
DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
Calendar cal = Calendar.getInstance();
String date=dateFormat.format(cal.getTime());
Date todayDate=null;
try {
	 todayDate = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(date);
	//System.out.println("date1 in try:"+todayDate);
} catch (ParseException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
		String organizationId=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		if(username==null)
			response.sendRedirect("../JSP/error.jsp");
		FacilitatorSelectKnowStoreIdDao KsIdDynamic = new FacilitatorSelectKnowStoreIdDao();
		String newKsid =KsIdDynamic.fetchDynamicKsid(userid);
		
		FacilitatorKAssetsReportDomain setKsid = new FacilitatorKAssetsReportDomain();
		setKsid.setKsId(newKsid);

		ArrayList<FacilitatorKAssetsReportDomain> reportDomain=new ArrayList<FacilitatorKAssetsReportDomain>();
		reportDomain = (ArrayList<FacilitatorKAssetsReportDomain>)request.getAttribute("aValue");
		System.out.println("rd="+reportDomain);
	   
	%>
<body>

	<div class="container" style="position:relative;">
	<%@include file="../JSP/PopupReadOnly.jsp"%>
		<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="postpon_mod">
		<div style="text-align: center;">
		<p class="strong">View Knowledge Assets</p>
		 <form name="ksStore" method="post">
	    <div style="width:100%;">
		
		    <div style="display:inline-block;padding-right:20px">
		       <select name="choose_assignment" id="choose_assignment" style="width: 248px !important;padding: 0px !important;height: 31px;border: 1px solid #c2c2c2;border-radius: 4px;">
                    <option value="">Choose Course</option>
		       	<%
									FacilitatorManageKnowStoreDomain al = new FacilitatorManageKnowStoreDomain();
		            				FacilitatorManageCourseDao dobj3= new FacilitatorManageCourseDao();
		            				System.out.println("user id "+userid);
		            				String courseid =null;
		            				String coursename =null;
		            				System.out.println("organizationId "+organizationId);
								    ArrayList<String>	arrayList1 = dobj3.viewValue(userid,organizationId);
								    Iterator<String> iterator11 = arrayList1.iterator();
									while(iterator11.hasNext()){
										
										courseid = iterator11.next();
										coursename = iterator11.next();
										String courseidAndName  = (courseid+" ("+coursename+")");%>
										System.out.println("Dinesh--- courseid "+courseid);
						<option value="<%=courseid%>"><%=courseidAndName%></option>
							<%}%>
                </select>
		     </div>
		     <div style="display:inline-block;padding-right:20px">
		       <select name="choose_subject" id="choose_subject"  style="width: 248px !important;padding: 0px !important;height: 31px;border: 1px solid #c2c2c2;border-radius: 4px;">
                    <option value="">Choose Subject</option>
		         <%
									//FacilitatorManageKnowStoreDomain al = new FacilitatorManageKnowStoreDomain();
		            FacilitatorManageSubjectDao dobj4= new FacilitatorManageSubjectDao();
					String subjectid=null;	
					String subid =null;
		            ArrayList<String>	arrayList2 = dobj4.viewValue(userid,organizationId,courseid);
								    Iterator<String> iterator12 = arrayList2.iterator();
									while(iterator12.hasNext()){
										
										 subid = iterator12.next();
										String subname = iterator12.next();
										String subidAndName  = (subid+" ("+subname+")");%>
									
						<option value="<%=subid%>"><%=subidAndName%></option>
							<%}%>
							<input type ="hidden" id="sub">
                </select>
		     </div>
		     <div style="display:inline-block;padding-right:20px">
		        	<select name="libid" id="libid" class="opt" onchange="clickKsId()">
								<option value="">Choose Library</option>
								<%
									//FacilitatorManageKnowStoreDomain al = new FacilitatorManageKnowStoreDomain();
								FacilitatorSelectKnowStoreIdDao dobj2 = new FacilitatorSelectKnowStoreIdDao();
								System.out.println("subjectId "+subjectId);	
								subid=subjectId;
								
								System.out.println("subid "+subid);
									System.out.println("courseid "+courseid);
								    ArrayList<FacilitatorKnowReportDomain>	arrayList = dobj2.viewLibInfo(userid,organizationId,subid,courseid);
									System.out.println("ArrayList "+arrayList);
								    Iterator<FacilitatorKnowReportDomain> iterator = arrayList.iterator();
									while(iterator.hasNext()){
										FacilitatorKnowReportDomain fdomain=iterator.next();
										String libId = fdomain.getLibId();
										String libName = fdomain.getLibName();
										String libIdAndName  = (libId+" ("+libName+")");%>
										System.out.println("libId "+libId);
									System.out.println("libName "+libName);
									System.out.println("libIdAndName "+libIdAndName);
									
								<option value="<%=libId%>"><%=libIdAndName%></option> 
								  <%}%>  
					</select>
		     </div>
		     <div style="display:inline-block;padding-right:20px">
		         <input class="box1" type="text" id="search" placeholder="Search">
		     </div> 
	 </div>

		<div class="search_div">
		
		</div>
		<div class="search_result">
		<!-- heading starts here  -->
		<!------------------------ table header fix bor scrolling  --------24-5-2017-------------->
		<div class="table_section1">
		<div class="table_outer_row">
		<table  width="100%" id="tableId" >
			<thead>
			 <tr class="row_head">
			 <th width="5.11%">
		Sl #
		<div class="header_stop" style="width:11.11%;">Sl #</div>
		</th>
		<th width="14.11%">
		Topic
		<div class="header_stop" style="width:11.11%;">Topic Name</div>
		</th>
		<th width="14.11%">
		Sub Topic
		<div class="header_stop" style="width:11.11%;">Sub Topic Name</div>
		</th>
		<!--  <th width="11.11%">
		Descriptions
		<div class="header_stop" style="width:11.11%;">Descriptions</div>
		</th>-->
		<th width="11.11%">
		Assets Name
		<div class="header_stop" style="width:11.11%;">Asset Name</div>
		</th>
		<th width="11.11%">
		Assets Size
		<div class="header_stop" style="width:11.11%;">Asset Size</div>
		</th>
		<th width="12.11%">
		Uploaded Date
		<div class="header_stop" style="width:11.11%;">Uploaded Date</div>
		</th>
		<th width="13.11%">
		Uploaded Time
		<div class="header_stop" style="width:11.11%;">Uploaded Time</div>
		</th>
		<th width="8.11%">
		Status
		<div class="header_stop" style="width:11.11%;">Status</div>
		</th>
		</tr>
		</thead>
	<%
	
	int count=1;
	
	if(reportDomain!=null)
			 {
		System.out.println("cheecking null="+reportDomain.size());
			Iterator<FacilitatorKAssetsReportDomain> iterator1 =reportDomain.iterator();
			 
			
			if(reportDomain.size()!=0)
				
			{
			
				while(iterator1.hasNext())
				{
					FacilitatorKAssetsReportDomain assetsDomain1=iterator1.next();
					//String[] s1=reportDomain1.getFilePath().split("/");
					System.out.println("reportDomain1.getFileName()="+assetsDomain1.getLibId());
					String datefromdb=assetsDomain1.getUploadDate().toString();
					System.out.println("date from db:"+datefromdb);
						DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						//System.out.println("formatterf::"+formatter);
	Date date_display = (Date)formatter.parse(datefromdb);
	//System.out.println("date_display::"+date_display);
	SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
	//System.out.println("newFormat::"+newFormat);
	String ddmmyyyy_format = newFormat.format(date_display);
	double file_size=assetsDomain1.getFileSize();
	double actual_file_size=0.0;
	String file_size_unit="";
	
	if(file_size>1024){
		if(file_size>1024*1024){
			if(file_size>1024*1024*1024){
					if(file_size>1024*1024*1024*1024){
							/* if(file_size>1024*1024*1024*1024*1024)
									{ 
								actual_file_size=file_size/((1024*1024*1024*1024*1024));
									file_size_unit=(Math.round(actual_file_size*100.0)/100.0)+" CB";}
							else{
								actual_file_size=file_size/((1024*1024*1024*1024));
							file_size_unit=(Math.round(actual_file_size*100.0)/100.0)+" TB";} */
					}else{
						actual_file_size=file_size/(1024*1024*1024);
					file_size_unit=(Math.round(actual_file_size*100.0)/100.0)+" GB";}
					}else{
					actual_file_size=file_size/(1024*1024);
			file_size_unit=(Math.round(actual_file_size*100.0)/100.0)+" MB";}
					}else{
						
				actual_file_size=file_size/1024;
				//System.out.println("actual_file_size="+actual_file_size);
		file_size_unit=(Math.round(actual_file_size*100.0)/100.0)+" KB";}
					}else{
		actual_file_size=file_size;
	file_size_unit=(Math.round(actual_file_size*100.0)/100.0)+" Bytes";}
	
			 %>
			
		
		<tr class="result_row_tr">
	<td width="11.11%" style="text-align:center;"><%=count++%></td>
		<td width="11.11%">
		<%-- <%=assetsDomain1.getDepartment()%> --%>
	<%=assetsDomain1.getTopic()%> 
		</td>
		<td width="11.11%">
		<%-- <%=assetsDomain1.getSubject()%> --%>
		<%=assetsDomain1.getSubTopic()%> 
		</td>
		<%-- <td width="11.11%">
		<%=assetsDomain1.getDescription()%>
		</td>--%>
		<%-- <%=assetsDomain1.getFileName()%> --%>
		
	 <td width="11.11%">
	 <a href="#" onclick="popup_read_only('<%=assetsDomain1.getFileName()%>','<%=assetsDomain1.getFileName()%>')"><%=assetsDomain1.getFileName()%></a>
	 </td>
		<td width="11.11%">
		<%-- <%=assetsDomain1.getFileSize()+" "+"KB"%> --%>
		<%=file_size_unit%>
		</td>
		<td width="11.11%">
		<%=ddmmyyyy_format%>
		</td>
		<td width="11.11%">
		<%=assetsDomain1.getUploadTime().getHours()%>:<%=assetsDomain1.getUploadTime().getMinutes()%>
		</td>
		<td width="11.11%"  style="text-align:center;">
		<%=assetsDomain1.getStatus()%>
		</td>
		</tr>
		 <%}}
			else {
				String message="No Data Available";
				%>
					<p class="autohide" style="color:red; font-size:18px; font-weight: bold; top: 39%; left: 44%; position: absolute;"><%=message %></p>
				<% }
					
			 }
	
	%>
		 
	</table>
		</div>
		</div>
		</div>
		</form>
		</div>
	</div>
		<%@ include file="../JSP/FooterViews.jsp"%>
	</div>
</body>
<script>
$(document).ready(function(){
	$(function() {
		//alert("in");
		$("#demo").customScroll({ scrollbarWidth: 5 });
		//alert("in f");
	});
	});
	</script>
<script>
onload();
</script>
</html>