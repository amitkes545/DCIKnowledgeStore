<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.kds.KODE_DEV.domain.*"%>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorSelectKnowStoreIdDao"%>
<%@page import="java.util.*" %>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorSelectKsidKStoreDao" %>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorManageLibDao"%>
<%@page import="android.telephony.TelephonyManager"%>

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

$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
	
	 
	    });
</script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
		<script type="text/javascript">
		
		function findimei(){
			alert("in");
		/*TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		// get IMEI
		String imei = tm.getDeviceId();
		String phone = tm.getLine1Number();
		
		Activity activity
		private String getMyPhoneNumber(){*/
		   TelephonyManager mTelephonyMgr;
		alert("in1");
		    mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE); 
		    //return mTelephonyMgr.getLine1Number();
		    alert(mTelephonyMgr.getLine1Number());
		/*}

		private String getMy10DigitPhoneNumber(){
		    String s = getMyPhoneNumber();
		    return s.substring(2);
		}*/
		}
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
			
			document.ksStore.action="/KODE_DEV/ControllerServlet/StudentAssetKnowViewServlet?libID="+libId+"";
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

	<div class="container">
		<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/Participant_menu.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="postpon_mod">
		<div style="text-align: center;">
		<p class="strong">Knowledge Store</p>
		 <form name="ksStore" method="post">
	
	 <div>
						<select name="libid" id="libid" class="opt" onchange="clickKsId()">
								<option value="">Choose Library ID</option>
								<option value="All">All</option>
								<%
									//FacilitatorManageKnowStoreDomain al = new FacilitatorManageKnowStoreDomain();
								FacilitatorSelectKnowStoreIdDao dobj2 = new FacilitatorSelectKnowStoreIdDao();
								    ArrayList<FacilitatorKnowReportDomain>	arrayList = dobj2.fetchStudentAllLibInfo(userid,organizationId);
								    Iterator<FacilitatorKnowReportDomain> iterator = arrayList.iterator();
									while(iterator.hasNext()){
										FacilitatorKnowReportDomain fdomain=iterator.next();
										String libId = fdomain.getLibId();
										String libName = fdomain.getLibName();
										String libIdAndName  = (libId+" ("+libName+")");%>
									
								<option value="<%=libId%>"><%=libIdAndName%></option>
								<%}%>
					</select>
					</div>
		<div class="search_div">
		<input class="box1" type="text" id="search" placeholder="Search">
		</div>
		<a href="#" onclick="findimei()">find imei</a>
		<a href="/KODE_DEV/ControllerServlet/findimei">imei find</a>
		<div class="search_result">
		<!-- heading starts here  -->
		<table width="100%" id="tableId">
			 <tr height="25px"></tr>
			 <tr class="row_head">

		
		<td>
		Departments
		</td>
		<td>
		Subjects
		</td>
		<td>
		Descriptions
		</td>
		<td>
		Assets Name
		</td>
		<td>
		Assets Size
		</td>
		<td>
		Uploaded Date
		</td>
		<td>
		Uploaded Time
		</td>
		
		</tr>
	<%	 if(reportDomain!=null)
			 {
			Iterator<FacilitatorKAssetsReportDomain> iterator1 =reportDomain.iterator();
			 
				while(iterator1.hasNext())
				{
					FacilitatorKAssetsReportDomain assetsDomain1=iterator1.next();
					//String[] s1=reportDomain1.getFilePath().split("/");
					System.out.println("reportDomain1.getFileName()="+assetsDomain1.getLibId());
			 %>
		<tr class="result_row_tr">
	
		<td>
		<%=assetsDomain1.getDepartment()%>
		</td>
		<td>
		<%=assetsDomain1.getSubject()%>
		</td>
		<td>
		<%=assetsDomain1.getDescription()%>
		</td>
		<%String extension="";
		int ind=assetsDomain1.getFilePath().lastIndexOf(".");
			if(ind!=-1) 
			extension = assetsDomain1.getFilePath().substring(ind); 
			System.out.println("ext="+extension);
			if(extension.equalsIgnoreCase(".mp4")) {
				System.out.println("in if="+assetsDomain1.getFilePath());
				//String link=http://www.kompacdigit.com/""
			 %>
			 <td>
		<a href="http://www.kompacdigit.com/<%=assetsDomain1.getFileName()%>" target="_blank"><%=assetsDomain1.getFileName()%></a>
		</td>
		<%}else{
			System.out.println("in else=");%>
		<td>
		<!-- <a href="/KODE_DEV/ControllerServlet/DownloadVideo?filePath=<%=assetsDomain1.getFilePath()%>"><%=assetsDomain1.getFileName()%></a> 
		src="../web/viewer.html" height="398px" width="100%;"
		-->
		<a href="../web/PDFViewer.html?filePath=<%=assetsDomain1.getFilePath()%>"><%=assetsDomain1.getFileName()%></a>
		
		</td>
		<%} %>
		<td>
		<%=assetsDomain1.getFileSize()+" "+"KB"%>
		</td>
		<td>
		<%=assetsDomain1.getUploadDate()%>
		</td>
		<td>
		<%=assetsDomain1.getUploadTime().getHours()%>:<%=assetsDomain1.getUploadTime().getMinutes()+" "+"min"%>
		</td>
		
		</tr>
		 <%} }%>
		
		</table>
		
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