<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.kds.KODE_DEV.domain.*"%>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorSelectKsidKStoreDao"%>
<%@page import="com.kds.KODE_DEV.dao.*"%>
<%@page import="java.util.*" %>
<%@ page import="com.kds.KODE_DEV.dao.FacilitatorManageLibDao"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/FacilitatorKnowSetup.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.js"></script>

<!-- <link href="../CSS/owner.css" rel="stylesheet"/>
<link href="../CSS/global.css" rel="stylesheet"/> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<script src="../JS/datetimepicker_css.js"></script>
<script type="text/javascript" src="../JS/validatePostSession.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>
<link href="../CSS/jquery.datetimepicker.css" rel="stylesheet"></link>
<link href="../CSS/table_scroll.css" rel="stylesheet"/>
<link href="../CSS/design-common.css" rel="stylesheet"/>
<link href="../CSS/notification-new.css" rel="stylesheet"></link>
<script src="../JS/MessageFadeOut.js"></script>

<!-- <script type="text/javascript" src="../JS/jquery.datetimepicker.js">
</script> -->

<style type="text/css">
.table_outer_row
{height: 215px;
}
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
.table_layout_scroll tr
{
    border:1px solid #ddd;
}
 .table_layout_scroll {
   width:100%;
     max-height:251px;  
    overflow-y:scroll;
    border-bottom:1px solid #ddd;
    }
table { border-collapse: collapse; }
.container{
background: url("../Image/body_dark.png") center center no-repeat;
}

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
.table_layout_scroll tr:nth-child(even) {background: #f7f7f7}
.table_layout_scroll tr:nth-child(odd) {background: #ffffff}
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
/* padding: 7px !important; */
border: 1px solid #C2C2C2;
height: 31px;
/*font-family: regular;*/
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
   border-right: 1px solid #bbb;
    }	
.row_head .col_2{float: left; padding: 0px 15px;}
.row_result .col_2{float: left; padding: 0px 15px;}
.result_row_tr td input{border: none; background: none; width: 99.5%; }
.starttime{width: 130px;}
.endtime{width: 120px;}
.duration_r{width: 120px;}
.cost_r{width: 80px;}
.result_row_tr td{padding: 4px 4px 4px 10px;}

.btnscolor{background: #1d87da;
    border: none;
    color: #fff;
    padding: 5px 15px;
    margin-top: 10px;
    margin-right: 10px;
    margin-left: 10px;}
    #errmsg
{
color: red;
}
.result_row_tr td {
border-right: 1px solid #bbb;
}
.row_head {
background:#a5caf6;/* e0ecfa; */
}
</style>
</head>
<script>
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
		//alert(id);
		if($('#' + id).is(":checked"))
		{
			//alert("checked");
		$("." + id).attr("disabled",false);
		//alert("disabled false");
		$('.btnscolor').show();
		}
	else{
		
		$("." + id).attr("disabled",true);	
		$('.btnscolor').hide();
	}
		btnscolor;
	});
	
	});
</script>
<script>
<%-- function onload(){
	<% String libid=(String)request.getAttribute("libid");
	 String courseId=(String)request.getAttribute("courseId");
	System.out.println("ks id on onload libid:"+libid);
	System.out.println("ks id on onload courseId:"+courseId);
	if(libid!=null && libid.length()>0) { 
		System.out.println("ks id on onload libid:"+libid);
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
	System.out.println("ks id on onload:"+courseId); %>
	 var selectBox = document.getElementById("choose_assignment");
	 for(var i=0;i<selectBox.options.length;i++){
		 if(selectBox.options[i].value == '<%=courseId%>')
		 {
			 selectBox.options[i].selected=true;
						 break;
		}
<%	}	%> 
}
 --%>
 function onload(){
		<% String libid=(String)request.getAttribute("libid");
		String courseId=(String)request.getAttribute("courseid");
		String subjectId=(String)request.getAttribute("subjectid");
		////System.out.println("ks id on onload:"+libid);
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
					////System.out.println("ks id on onload:"+libid);
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



function clickKsId()
{

var libId=document.getElementById("libid").value;
//alert("libId "+libId);
var courseId=document.getElementById("choose_assignment").value;
var subjectId=document.getElementById("choose_subject").value;	
document.ksStore.action="/KODE_DEV/ControllerServlet/FacilitatorAssetKnowServlet?libID="+libId+"&courseId="+courseId+"&subjectId="+subjectId+"";
//document.ksStore.action="/KODE_DEV/ControllerServlet/FacilitatorAssetKnowServlet?libID="+libId+"";
//alert("in");
document.ksStore.submit();
}

$(document).ready(function() 
{
	$(".autohide").delay(5000).fadeOut("slow");
});

</script>

<script>

function Delete(){
	document.ksStore.action="/KODE_DEV/ControllerServlet/FacilitatorAssetsDeleteLib";
	document.ksStore.submit();
	
}
	$(document).ready(function() {
	    $("#update_btn").click(function(e) {
	    	
	        var isValid = true;
	        if($('input:checkbox').is(":checked"))
	        {
	          $('.search_result input[type="text"]').each(function() {
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
	        }
	        
	       
	        // here end working ok 
        if (isValid == false){
	        	
	            e.preventDefault();
	           
	        }
	        else {
	        	 //alert('Thank you for submitting')
	        	 $("#ksStore").submit(); 
	        }
	           
	    });
	});     
</script>
<script type="text/javascript">
$(document).ready(function () {
	  //called when key is pressed in textbox
	  $("#quantity").keypress(function (e) {
	     //if the letter is not digit then display error and don't type anything
	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	        //display error message
	        $("#errmsg").html("Digits Only").show().fadeOut("slow");
	               return false;
	    }
	   });
	});
</script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
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
		/* var table = $('#tableId');
		table.find('tr').each(function(index, row)
		{
			var allCells = $(row).find("td");
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
			}
		}); */
		 var table = $('#tableId');
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
	}
</script>

<%
		String organizationId=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		if(username==null)
		 response.sendRedirect("../JSP/error.jsp?errmsg=Session Expired");
		ArrayList<FacilitatorKAssetsReportDomain> reportDomain1=new ArrayList<FacilitatorKAssetsReportDomain>();
		reportDomain1 = (ArrayList<FacilitatorKAssetsReportDomain>)request.getAttribute("aValue");	//System.out.println("msgvalue in store="+reportDomain1);
	
		
		//newly added by dinesh for audit trail		
		
		FacilitatorSelectKsidKStoreDao dobjAudit = new FacilitatorSelectKsidKStoreDao();		
			String ksidHidden = dobjAudit.fetchValue1(organizationId, userid);		
			System.out.println("ksidHidden"+ksidHidden);		
	%>
<body>

	<div class="container" style="position:static;">
	<%@include file="../JSP/PopupReadOnly.jsp"%>
		<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="postpon_mod" style="text-align: center;">
		<div>
		
		
		<%
		
						if (request.getAttribute("FacultySuccess11") != null) {
					%>
					<p class="success">
						<%=request.getAttribute("FacultySuccess11")%>
					</p>
					
					<% } %>
					
					<%
						if(request.getAttribute("FacultyFailure11") != null) {
					%>
					<p class="failure" >
						<%=request.getAttribute("FacultyFailure11")%>
					</p>
					
					<% } %>
			
					<%
											
					if (request.getAttribute("FacultySuccess")!= null) { 
				%>
				<p class="success">
				<%= request.getAttribute("FacultySuccess") %></p>
				<%
			}
			
			%>
					
					<%
						if(request.getAttribute("FacultyFailure") != null) {
					%>
					<p class="failure">
						<%=request.getAttribute("FacultyFailure")%>
					</p>
					
					<% } %>
					<%
						if(request.getAttribute("failureMessage") != null) {
					%>
					<p class="failure">
						<%=request.getAttribute("failureMessage")%>
					</p>
					
					<% } %>
					<p class="strong">Modify Knowledge Assets</p>
				
		 <form name="ksStore" id="ksStore" method="post" action="/KODE_DEV/ControllerServlet/FacilitatorAssetsAccessControlServlet">
		
		   <div style="width:100%;">
							 <!-- CHOOSE COURSE -->
		    
		    <div style="display:inline-block;padding-right:20px">
		       <select name="choose_assignment"  id="choose_assignment" style="width: 248px !important;padding: 0px !important;height: 31px;border: 1px solid #c2c2c2;border-radius: 4px;">
                    <option value="">Choose Course</option>
		            
		            	<%
									FacilitatorManageKnowStoreDomain al = new FacilitatorManageKnowStoreDomain();
		            				FacilitatorManageCourseDao dobj3= new FacilitatorManageCourseDao();
		            				System.out.println("user id "+userid);
		            				String courseid =null;
		            				String coursename =null;
		            				System.out.println("organizationId "+organizationId);
								    ArrayList<String>	arrayList1 = dobj3.fetchValue(userid,organizationId);
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
		     
		     		 <!-- CHOOSE SUBJECT -->
		     
		     <div style="display:inline-block;padding-right:20px">
		       <select name="choose_subject" id="choose_subject"  style="width: 248px !important;padding: 0px !important;height: 31px;border: 1px solid #c2c2c2;border-radius: 4px;">
                    <option value="">Choose Subject</option>
		            
		             <%
									//FacilitatorManageKnowStoreDomain al = new FacilitatorManageKnowStoreDomain();
		            FacilitatorManageSubjectDao dobj4= new FacilitatorManageSubjectDao();
					String subjectid=null;			   
		            ArrayList<String>	arrayList2 = dobj4.fetchValue(userid,organizationId,courseid);
								    Iterator<String> iterator12 = arrayList2.iterator();
									while(iterator12.hasNext()){
										
										String subid = iterator12.next();
										String subname = iterator12.next();
										String subidAndName  = (subid+" ("+subname+")");%>
									
						<option value="<%=subid%>"><%=subidAndName%></option>
							<%}%>
                </select>
		     </div>
			<div style="display:inline-block;padding-right:20px">
						<select name="libid" id="libid" class="opt" onchange="clickKsId()">
								<option value="">Choose Library</option>
								<%
									//FacilitatorManageKnowStoreDomain al = new FacilitatorManageKnowStoreDomain();
									FacilitatorManageLibDao dobj2 = new FacilitatorManageLibDao();
								    ArrayList<String>	arrayList = dobj2.fetchValue(userid,organizationId);
								    Iterator<String> iterator = arrayList.iterator();
								    String libId =null;
								    while(iterator.hasNext()){
										
										libId = iterator.next();
										String libName = iterator.next();
										String libIdAndName  = (libId+" ("+libName+")");%>
									
								<option value="<%=libId%>"><%=libIdAndName%></option>
								<%}%>
					</select>
					</div>
		     
		     
		     
		    <div style="display:inline-block;padding-right:20px">
		        <input class="box1" id="search" type="text" placeholder="Search">
		    </div>
		</div>
		<div class="search_result">
		<!-- heading starts here  -->
		<div class="table_section1">
		<div class="table_outer_row">
		<table id="tableId" width="100%">
	<thead>
			 <tr class="row_head">	
			  <th width="7.5%">
		Sl #<div class="header_stop" style="width:7.5%;">Sl #</div></th> 
		 <th width="12.5%">
		Topic Name
		<div class="header_stop" style="width:12.5%;">Topic Name</div>
		</th>
		 <th width="14.5%">
		Sub-Topic Name
		<div class="header_stop" style="width:12.5%;">Sub-Topic Name</div>
		</th>
		 <th width="13.5%">
		Asset Name
		<div class="header_stop" style="width:12.5%;">Asset Name</div>
		</th>
		 <th width="11.5%">		 
		Asset Size
		<div class="header_stop" style="width:12.5%;">Asset Size</div>
		</th>
		<th width="17.5%">
		Asset Path
		<div class="header_stop" style="width:17.5%;">Asset Path</div>
		</th>
		 <th width="12.5%">
		Status
		<div class="header_stop" style="width:12.5%;">Status</div>
		</th>
		 <th width="10.5%">
		Action
		<div class="header_stop" style="width:10.5%;">Action</div>
		</th>
		</tr>	
	</thead>
		 <% int count=1;
		if(reportDomain1!=null)
		 {
		Iterator<FacilitatorKAssetsReportDomain> iterator1 =reportDomain1.iterator();
		 
			while(iterator1.hasNext())
			{
				FacilitatorKAssetsReportDomain reportDomain=iterator1.next();
				String[] s1=reportDomain.getFilePath().split("/");
				String unqid= reportDomain.getLibId()+reportDomain.getUploadedBy()+reportDomain.getFileName();
				String unqid2=libId+"|"+userid+"|"+reportDomain.getFileName();
				System.out.println("unqid2 "+unqid2);
			//	String unqid= reportDomain.getTopic()+reportDomain.getSubTopic()+reportDomain.getFileName();
				String unqid1= courseid+reportDomain.getTopicId()+reportDomain.getSubTopicId();
				
				unqid=unqid.replaceAll("\\s+", "");
				unqid=unqid.replaceAll("\\.","");
				unqid=unqid.replaceAll("\\@","");
				unqid=unqid.replaceAll("\\)","");
				unqid=unqid.replaceAll("\\(","");
				//unqid=unqid.indexOf(".");
				System.out.println("LibID:"+unqid2);
				
				double file_size=reportDomain.getFileSize();
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
							System.out.println("actual_file_size="+actual_file_size);
					file_size_unit=(Math.round(actual_file_size*100.0)/100.0)+" KB";}
								}else{
					actual_file_size=file_size;
				file_size_unit=(Math.round(actual_file_size*100.0)/100.0)+" Bytes";}
				
				
			%>
			 
			 <tr class="result_row_tr row">	 
			 <input type="hidden" readonly="readonly" type="text" name="ksid"
							value="<%=reportDomain.getKsId()%>" class="<%=unqid%>" />
							
				<input type="hidden" readonly="readonly" type="text" name="userid"
							value="<%=reportDomain.getUserId()%>" class="<%=unqid%>" />
							
				<input type="hidden" readonly="readonly" type="text" name="orgid"
							value="<%=reportDomain.getOrgId()%>" class="<%=unqid%>"/>
								<input type="hidden" name="Upload<%=unqid%>" readonly value="<%=reportDomain.getUploadedBy()%>" class="<%=unqid%>"/>
									<input type="hidden" name="courseid" readonly value="<%=courseid%>" />		
		 				<input type="hidden" name="topicid" readonly value="<%=reportDomain.getTopicId()%>" class="<%=unqid%>" />
		 				<input type="hidden" name="subtopicid" readonly value="<%=reportDomain.getSubTopicId()%>" class="<%=unqid%>" />
						<input type="hidden" name="Filename<%=unqid%>" readonly value="<%=reportDomain.getFileName()%>" class="<%=unqid%>" />
							<input type="hidden" name="uploadby<%=unqid%>" readonly value="<%=userid%>" class="<%=unqid%>" />
								<input type="hidden" name="newlibid<%=unqid%>" readonly value="<%=libId%>" class="<%=unqid%>" />					
					 
			 <td width="40px " style="text-align:center;"><%=count++%></td>
				<td width="200px " align="center">
				
				<input type="text" name="libId<%=unqid%>" readonly value="<%=reportDomain.getTopic()%>" class="<%=unqid%>"/>
				</td>
				<td width="350px ">
				<input type="text" name="libName<%=unqid%>" value="<%=reportDomain.getSubTopic()%>" class="<%=unqid%>"/>
				</td>
				<td width="300px ">
				<input type="text" name="Filename<%=unqid%>" value="<%=reportDomain.getFileName()%>" class="<%=unqid%>"/>
				</td>
				<td align="center" width="100px ">
		<input type="text" name="libSize<%=unqid%>" class="<%=unqid%>" readonly value="<%=file_size_unit%>" />
		</td>
		<td align="left" width="300px ">
		<%-- <a href="/KODE_DEV/ControllerServlet/FacilitatorAssetsKnowDownloadServlet?filePath=<%=reportDomain.getFilePath()%>"><%=s1[s1.length-1] %></a> --%>
		<a href="#" onclick="popup_read_only('<%=reportDomain.getFileName()%>','<%=reportDomain.getFilePath()%>')"><%=reportDomain.getFileName()%></a>
		</td>
		<td width="150px" style="text-align:center;">
		<!-- <input type="hidden" name="status<%=unqid%>" readonly value="<%=reportDomain.getStatus()%>" class="<%=unqid%>" />  -->
		<select class="<%=unqid%>" name="status<%=unqid %>">
				<%if(reportDomain.getStatus().equalsIgnoreCase("Active")){ %>
				<option value="Active" selected="selected">Active</option>
				<option value="Inactive">Inactive</option>
				<%} else  if(reportDomain.getStatus().equalsIgnoreCase("Inactive")){%>
				<option value="Active">Active</option>
				<option value="Inactive" selected="selected">Inactive</option>
				<%}else{ %>
				<option value="Active">Active</option>
				<option value="Inactive" selected="selected">Inactive</option>
				<%} %>
				</select>
		</td>
				<td width="150px ">
				<a href="#">
				<input id="<%=unqid%>" type="checkbox" name="checkboxGroup" value="<%=unqid%>" />
				</a>
				</td>
				</tr> 
		 <%}}%>
		</table>
		</div>
		</div>
		
		<a>
		<input class="btnscolor" type="button" value="Update" id="update_btn" />
		<input class="btnscolor" type="button" value="Delete" onclick="Delete()"/>
		</a>
		</div>
		</form>
	</div>
	</div>
	 <%@ include file="../JSP/FooterViews.jsp"%>
	</div>
</body>
<script>
onload();
</script>
</html>