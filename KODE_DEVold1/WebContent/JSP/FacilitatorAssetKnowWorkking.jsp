<%@ page errorPage="/JSP/error.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.kds.KODE_DEV.domain.*"%>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorSelectKsidKStoreDao" %>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorManageLibDao"%>
<%@page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/FacilitatorKnowSetup.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.js"></script>
<!-- <link href="../CSS/owner.css" rel="stylesheet"/>
<link href="../CSS/global.css" rel="stylesheet"/> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<script src="../JS/datetimepicker_css.js"></script>
<script type="text/javascript" src="../JS/validatePostSession.js"></script>
<link href="../CSS/jquery.datetimepicker.css" rel="stylesheet"></link>

<!-- <script type="text/javascript" src="../JS/jquery.datetimepicker.js">
</script> -->
<style type="text/css">
.search_result{padding: 0px 10px;margin-bottom: 25px;max-height: 250px;
overflow-y: scroll;}
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
width: 85% !important;
    box-shadow: 5px 5px 5px 5px #FFF;
    margin: 15px auto 0px;
    padding: 15px 1px 0px 0px;
    border: 3px solid #C2C2C2;
    background-color: #FCF7F7;
    border-radius: 4px;
    opacity: 0.7;
    margin-top: 75px;
    
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
text-align: center;
}
.opt{
width: 265px !important;
padding: 7px !important;
border: 1px solid #C2C2C2;
font-family: regular;
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
    color: #FFF;
    font-weight: bold;
    }
    .row_head td{
    padding: 5px;
    }	
.row_head .col_2{float: left; padding: 0px 15px;}
.row_result .col_2{float: left; padding: 0px 15px;}
.result_row_tr td input{border: none; background: none; width: 99.5%;}
.starttime{width: 130px;}
.endtime{width: 120px;}
.duration_r{width: 120px;}
.cost_r{width: 80px;}
.result_row_tr td{border: 1px solid #000;padding: 4px;}
</style>
</head>
<script>

function publish(){
	
	document.ksStore.action="/KODE_DEV/ControllerServlet/FacilitatorAssetsAccessControlServlet";
	document.ksStore.submit();
}
</script>

<script>
$(document).ready(function() {
	$('.btnscolor').hide();
	
	$('.row input').each(function()
	{
	$(this).attr("disabled",true);
	
	});
	$(".row a input").attr("disabled",false);

$("#qq").click(function() {
		var id = $(this).attr("id");
		if($('#qq').is(":checked"))
		{
		$('.btnscolor').show();
		}
	else{
		$('.btnscolor').hide();
	}
		btnscolor
	});
	});
</script>

<script>
function onload(){
	<% String libid=(String)request.getAttribute("libid");
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
				
		<%}%>
}
function clickKsId(){
	//alert("in");
	var libId=document.getElementById("libid").value;
	
	document.ksStore.action="/KODE_DEV/ControllerServlet/FacilitatorAssetKnowServlet?libID="+libId+"";
	//alert("in");
	document.ksStore.submit();
	//alert("in1");
}

$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
	
	 $("chkbox_click").click(function(){
	        alert("The paragraph was clicked.");
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
			}
		});
	}
</script>

<%
		
		
		//msg = (String) mess.getAttribute("MsgValue");
		String organizationId=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		if(username==null)
		{
			response.sendRedirect("../JSP/error.jsp");
		}
		ArrayList<FacilitatorKAssetsReportDomain> reportDomain=new ArrayList<FacilitatorKAssetsReportDomain>();
		reportDomain = (ArrayList<FacilitatorKAssetsReportDomain>)request.getAttribute("aValue");
		System.out.println("avalue in asset="+reportDomain);
		
	%>
<body>

	<div class="container">
		<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="postpon_mod">
		<div style="text-align: center;">
		<p class="strong">Publish Control</p>
		 <form name="ksStore" method="post">
		 
					 	<%
						if (session.getAttribute("FacultySuccess") != null) {
						%>
						<p class="fadehide" style="color: green; text-align: center; margin-left: 54px;">
						<%=session.getAttribute("FacultySuccess")%>
						</p>
					
						<% }
					 	else  if (request.getAttribute("FacultyFailure")!= null) { 
					
					%>
					<p class="autohide" style="color:red; font-size:12px; font-weight: bold; top: 37%; left: 44%; position: absolute;">
					<%= request.getAttribute("FacultyFailure") %>
					</p>					
					<%
				} %>
				
					 <div>
						<select name="libid" id="libid" class="opt" onchange="clickKsId()">
								<option value="">Choose Library ID</option>
								<option value="All">All</option>
								<%
									//FacilitatorManageKnowStoreDomain al = new FacilitatorManageKnowStoreDomain();
									FacilitatorManageLibDao dobj2 = new FacilitatorManageLibDao();
								    ArrayList<String>	arrayList = dobj2.fetchValue(userid,organizationId);
								    Iterator<String> iterator = arrayList.iterator();
									while(iterator.hasNext()){
										
										String libId = iterator.next();
										String libName = iterator.next();
										String libIdAndName  = (libId+" ("+libName+")");%>
									
								<option value="<%=libId%>"><%=libIdAndName%></option>
								<%}%>
					</select>
					</div>				
		
		<div class="search_div">
		<input class="box1" id="search" type="text" placeholder="Search">
		</div>
		<div class="search_result">
		<!-- heading starts here  -->
		<table width="100%"  id ="tableId">
		
		
			 <tr height="25px"></tr>
			 <tr class="row_head">
		<td>
		Library ID
		</td>
		<td>
		Assets Name
		</td>
		<td>
		Assets Size
		</td>
		<td>
		Assets Path
		</td>
		<td>
		Status
		</td>
		<td>
		Action
		</td>
		</tr>
		<% if(reportDomain!=null)
			 {
			Iterator<FacilitatorKAssetsReportDomain> iterator1 =reportDomain.iterator();
			 
				while(iterator1.hasNext())
				{
					FacilitatorKAssetsReportDomain reportDomain1=iterator1.next();
					String[] s1=reportDomain1.getFilePath().split("/");
					//System.out.println("reportDomain1.getFileName()="+reportDomain1.getLibId());
					//System.out.println("libId=libId"+reportDomain1.getLibId()+";");
			 %>
		<!--  -->
		
		
		<tr class="result_row_tr row">
		
		
		<td>
		<input type="hidden" name="Upload<%=reportDomain1.getLibId()%>" id="Upload" readonly value="<%=reportDomain1.getUploadedBy()%>" />
		<input type="text" name="libId<%=reportDomain1.getLibId()%>" readonly value="<%=reportDomain1.getLibId()%>" class="<%=reportDomain1.getLibId()%>"/>
		</td>
		<td>
		<input type="text" name="libName<%=reportDomain1.getLibId()%>" readonly value="<%=reportDomain1.getFileName()%>" class="<%=reportDomain1.getLibId()%>"/>
		</td>
		<td>
		<input type="text" name="libSize<%=reportDomain1.getLibId()%>" class="<%=reportDomain1.getLibId()%>" readonly value="<%=reportDomain1.getFileSize()+" "+"Bytes"%>" />
		</td>
		<td>
		<a href="/KODE_DEV/ControllerServlet/FacilitatorAssetsKnowDownloadServlet?filePath=<%=reportDomain1.getFilePath()%>"><%=s1[s1.length-1] %></a>
		</td>
		<td>
		<input type="text" name="status<%=reportDomain1.getLibId()%>" readonly value="<%=reportDomain1.getStatus()%>" class="<%=reportDomain1.getLibId()%>" />
		</td>
		<td>
		<a href="#">
				<input id="<%=reportDomain1.getLibId()%>" type="checkbox" name="checkboxGroup" value="<%=reportDomain1.getLibId()%>"/>
				</a>
		</td>
		</tr>
		 <%}} %>
		<!--  -->
		</table>
		<a>
		<!-- <input class="btnscolor" type="button" value="Update" />
		<input class="btnscolor" type="button" value="Delete" /> -->
		<p style="text-align: center;">
		<input class="btnscolor1 add_btn1" type="button" value="Publish" onclick="publish()"/>
		</p>
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