<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.kds.KODE_DEV.domain.*"%>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorSelectKnowStoreIdDao"%>
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

height: 33px;
padding: 6px !important;
font-family: regular;
border-radius: 4px;
}
.opt{
width: 265px !important;
padding: 3px !important;
height: 31px;
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
  /*   color: #FFF; */
    font-weight: bold;
    }
    .row_head td{
  /*   padding: 5px; */
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
.result_row_tr td{padding: 4px 4px 4px 10px;}
.row_head {
background:#a5caf6;/* e0ecfa; */
}
.table_layout_scroll tr:nth-child(even) {background: #f7f7f7}
.table_layout_scroll tr:nth-child(odd) {background: #ffffff}
/* .table_layout_scroll {
background:#bbb;
} */

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
	var libid=document.getElementById("libid").value;
	//alert("ksid:"+ksid);
	document.ksStore.action="/KODE_DEV/ControllerServlet/FacilitatorLibKnowStoreListViewService?libID="+libid+"";
	document.ksStore.submit();
}
</script>

<script>
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
		String organizationId=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		ArrayList<FacilitatorManageKnowStoreDomain> reportDomain1=new ArrayList<FacilitatorManageKnowStoreDomain>();
		
		reportDomain1 = (ArrayList<FacilitatorManageKnowStoreDomain>)request.getAttribute("msgvalue");
	   
		%>
<body>

	<div class="container" style="position:static;">
		<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="postpon_mod">
		<div style="text-align: center;">
		<p class="strong">View Library</p>
		 <form name="ksStore" method="post">
		 
						<select name="libid" id="libid" class="opt" onchange="clickKsId()">
								<option value="">Choose Library</option>
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
		<div class="search_div">
		<input class="box1" type="text" id="search" placeholder="Search">
		</div>
		<div class="search_result">
			
		<!-- heading starts here  -->
		<div class="table_section1">
		<div class="table_outer_row">
		<table width="100%" id= "tableId" >
<thead>
			 <tr class="row_head">
<th  style="width:25%;">Sl #<div class="header_stop" style="width:25%;">Sl #</div></th>
		
		<th width="25%">
		Library ID
		<div class="header_stop" style="width:25%;">Library ID</div>
		</th>
		<th width="25%">
		Library Name
		<div class="header_stop" style="width:25%;">Library Name</div>
		</th>
		<th width="25%">
		Library Size
		<div class="header_stop" style="width:25%;">Library Size</div>
		</th>
		</tr>
		
		</thead>
		<% int count=1;
		if(reportDomain1!=null){
			Iterator<FacilitatorManageKnowStoreDomain> it=reportDomain1.iterator();
			while(it.hasNext()){
				FacilitatorManageKnowStoreDomain reportDomain=it.next();
			
			%>
		<tr class="result_row_tr row">
		<td width="200px" style="text-align:center;"><%=count++ %></td>
		<td width="200px" align="left">
		<%=reportDomain.getLibId() %>
		</td>
		<td width="200px" align="left">
		<%=reportDomain.getLibName()%>
		</td>
		<td width="200px" style="text-align:center;">
		<%=reportDomain.getLibSize()%> <%=reportDomain.getSpaceUom() %>
		</td>
		</tr>
		 <%}} %>
		
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
onload();
</script>
</html>