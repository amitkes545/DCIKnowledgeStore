<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.kds.KODE_DEV.dao.CollaborateDao" %>
<%@page import="com.kds.KODE_DEV.domain.*"%>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorSelectKnowStoreIdDao"%>
<%@page import="java.util.*" %>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorSelectKsidKStoreDao" %>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorManageLibDao"%>

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
		 function toggle(source) {
			// alert("in f");
		        var aInputs = document.getElementsByTagName('input');
		        for (var i=0;i<aInputs.length;i++) {
		            if (aInputs[i] != source && aInputs[i].className == source.className) {
		                aInputs[i].checked = source.checked;
		            }
		        }
		    }
		function clickKsId(){
			//alert("in");
			
			document.ksStore.action="/KODE_DEV/ControllerServlet/StudentClassWorkViewServlet";
			//alert("in");
			document.ksStore.submit();
			//alert("in1");
		}
		
		
		</script>
<%
 String msg=request.getParameter("message");
		String organizationId="srm"; //(String)session.getAttribute("orgid");
		String userid="faculty@srm";//(String)session.getAttribute("userid");
		String username="Faculty";//(String)session.getAttribute("username");
		if(username==null)
			response.sendRedirect("../JSP/error.jsp");
		FacilitatorSelectKnowStoreIdDao KsIdDynamic = new FacilitatorSelectKnowStoreIdDao();
		//String newKsid =KsIdDynamic.fetchDynamicKsid(userid);
		
		FacultyShareFileDomain setKsid = new FacultyShareFileDomain();
		//setKsid.setKsId(newKsid);

		ArrayList<FacultyShareFileDomain> reportDomain=new ArrayList<FacultyShareFileDomain>();
		reportDomain = (ArrayList<FacultyShareFileDomain>)request.getAttribute("aValue");
		System.out.println("rd="+reportDomain);
	   
	%>
<body>
<%=msg %>
<input type="button" id="but" onclick="clickKsId()" value="View" /><!-- style="display:none;" -->
	<div class="container">
		<div class="postpon_mod">
		<div style="text-align: center;">
		<p class="strong">Share files to Participants</p>
		 <form name="ksStore" method="post">
		<div class="search_result">
		<!-- heading starts here  -->
		<table width="50%" id="tableId">
			 <tr height="25px"></tr>
			 <tr class="row_head">

		<td>
		File Name
		</td>
		<td>
		Action
		</td>
		</tr>
	<%	 if(reportDomain!=null)
			 {
			Iterator<FacultyShareFileDomain> iterator1 =reportDomain.iterator();
			 
				while(iterator1.hasNext())
				{
					FacultyShareFileDomain assetsDomain1=iterator1.next();
					//String[] s1=reportDomain1.getFilePath().split("/");
					//System.out.println("reportDomain1.getFileName()....="+assetsDomain1.getLibId());
					 String filesname=assetsDomain1.getFileName();
					 //System.out.println("filesname="+filesname);
					 String ffname=filesname.substring(1);
					 //System.out.println("ffname="+ffname);
					 String lfname=ffname.substring(0,ffname.length()-1);
					 //System.out.println("lfname="+lfname);
					 String[] file=lfname.split(",");
					 int filelength=file.length;
					 //System.out.println(file.length);
			 %>
			
		<tr class="result_row_tr">
	<% if(assetsDomain1.getFilePath()!=null) {%>
		<input type="hidden" id="filepath" name="filepath" value="<%=assetsDomain1.getFilePath()%>" />
		<%} %>
		<% for(int i=0;i<filelength;i++){ %>
		<td>
		<% if(assetsDomain1.getFilePath()!=null) {%>
		<a href="/KODE_DEV/ControllerServlet/StudentClassRoomFileDownloadServlet?filepath=<%=assetsDomain1.getFilePath()%>&filename=<%=file[i]%>&action=<%=assetsDomain1.getAction()%>" id="filelink" name="filelink"  ><%=file[i]%></a>
		<%}else{ %>
		<%=file[i]%>
		<%} %>
		</td>
		<td><input type="text" id="action" name="action" value="<%=assetsDomain1.getAction()%>"></td>
		</tr>
		 <%}} }%>
		
		</table>

		</div>
		</form>
		</div>
	</div>
	</div>
</body>
<script>
</script>
</html>