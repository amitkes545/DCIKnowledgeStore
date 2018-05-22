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
<link rel="stylesheet" href="../CSS/dci-style.css" >
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
.b{border: 1px solid red;}
.bfile{border: 1px solid red;}
#display{text-align: center;color: red;}
#displaymode{text-align: center;color: red;}
#displayfileerror{text-align: center;color: red;}
</style>
</head>

	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
		<script type="text/javascript">
		$(document).ready(function() {
			  $( ".submit_btn" ).click(function(e) {
				  var isValid = true;
				  if($("input:radio[name='ksfile']").is(":checked")) {
			    	   $("#displaymode").hide();
		  		  }
			       else
			    	   {
			    	   isValid = false;
			    	   $('#displaymode').slideDown().html('<span id="error"><br/>select gender</span>');
				        return false;
			    	   }  
		       
			        if($('.chekbox_student:checkbox:checked').length < 1)
		         	{
			        	isValid = false;
			            $(".a").addClass('b');
			            $('#display').slideDown().html('<span id="error">Choose any One Participant</span>');
			            return false;
			        }
			        else
			        	{
			        	$(".a").removeClass('b');
			            $("#display").hide();
			        	}
			        if($('.chekbox_file:checkbox:checked').length < 1)
		         	{
			        	isValid = false;
			            $(".afile").addClass('bfile');
			            $('#displayfileerror').slideDown().html('<span id="error">Choose any One File</span>');
			            return false;
			        }
			        else
			        	{
			        	$(".afile").removeClass('bfile');
			            $("#displayfileerror").hide();
			        	}
			        if (isValid == false){
			            e.preventDefault();
			        }
			        else {
			        	// alert('Thank you for submitting')
			        	 document.ksStore.action="/KODE_DEV/ControllerServlet/FacultyShareFileServlet";
			 			document.ksStore.submit();
			        }
			        
			        
			        
			  
		   });
		});
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
			
			document.ksStore.action="/KODE_DEV/ControllerServlet/FacultyShareViewServlet";
			//alert("in");
			document.ksStore.submit();
			//alert("in1");
		}
		function viewfile()
		{
			document.ksStore.action="/KODE_DEV/ControllerServlet/FacultyClassWorkViewServlet";
			//alert("in");
			document.ksStore.submit();
		}
	
		
		function downloadfile(id)
		{
			var filepathview=document.getElementById("filepathview").value;
			//alert(filepathview);
			var fname=id;//document.getElementById("fname").value;
			//alert(id);
			document.ksStore.action="/KODE_DEV/ControllerServlet/FacultyViewFileDownloadServlet?fname="+fname+"&filepathview"+filepathview;
			document.ksStore.submit();
		}
		</script>
		<script>

$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow"); 
	    });
</script>
<%
 String msg=request.getParameter("message");
		String organizationId="srm"; //(String)session.getAttribute("orgid");
		String userid="faculty@srm";//(String)session.getAttribute("userid");
		String username="Faculty";//(String)session.getAttribute("username");
		if(username==null)
			response.sendRedirect("../JSP/error.jsp");
		FacilitatorSelectKnowStoreIdDao KsIdDynamic = new FacilitatorSelectKnowStoreIdDao();
		String newKsid =KsIdDynamic.fetchDynamicKsid(userid);
		
		FacilitatorKAssetsReportDomain setKsid = new FacilitatorKAssetsReportDomain();
		setKsid.setKsId(newKsid);

		ArrayList<FacilitatorKAssetsReportDomain> reportDomain=new ArrayList<FacilitatorKAssetsReportDomain>();
		reportDomain = (ArrayList<FacilitatorKAssetsReportDomain>)request.getAttribute("aValue");
		System.out.println("rd="+reportDomain);
		
		ArrayList<ClassWorkFileDomain> CWDomain=new ArrayList<ClassWorkFileDomain>();
		CWDomain = (ArrayList<ClassWorkFileDomain>)request.getAttribute("CWValue");
		System.out.println("rd1="+CWDomain);
	   
	%>
<body>
<%=msg %>
<div class="class_text">
<a href="#"><img src="../Image/classwork.jpg" /></a>
</div>
	<div class="student_view_inner row">
<div class="share_view_outer row">
        	<ul>
            	<li><a href="#" id="but" class="share">share</a></li>
                <li><a href="#" id="view" onclick="clickKsId()" onclick="viewfile()" class="view">view</a></li>
            </ul>
        </div>
	<div class="container">
		<div class="postpon_mod">
		<div style="text-align: center;">
		<p class="strong">Share files to Participants</p>
		 <form name="ksStore" method="post">
		<div class="search_result">
		<p id="displaymode"></p>
				<table width="50%" id="tableId1">
			 <tr height="25px"></tr>
			 <tr class="row_head">

		<td>
		File Name
		</td>
		<td>
		Student Name
		</td>
		</tr>
	<%	 if(CWDomain!=null)
			 {
			Iterator<ClassWorkFileDomain> iterator2 =CWDomain.iterator();
			 
				while(iterator2.hasNext())
				{
					ClassWorkFileDomain CWDomain1=iterator2.next();
					//String[] s1=reportDomain1.getFilePath().split("/");
					//System.out.println("reportDomain1.getFileName()....="+assetsDomain1.getLibId());
			 %>
		<tr class="result_row_tr">
	
		<input type="text" id="filepathview" name="filepathview" value="<%=CWDomain1.getFilePath()%>" />
		<td>
		<a href="#" id="fname" name="fname" onclick="downloadfile(this.innerHTML)"><%=CWDomain1.getFileName()%></a>
		</td>
		<td><%=CWDomain1.getUploadedBy()%></td>
		</tr>
		 <%} }%>
		
		</table>
		<p id="displayfileerror"></p>
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
			Iterator<FacilitatorKAssetsReportDomain> iterator1 =reportDomain.iterator();
			 
				while(iterator1.hasNext())
				{
					FacilitatorKAssetsReportDomain assetsDomain1=iterator1.next();
					//String[] s1=reportDomain1.getFilePath().split("/");
					//System.out.println("reportDomain1.getFileName()....="+assetsDomain1.getLibId());
			 %>
		<tr class="result_row_tr">
	
		<input type="hidden" id="filepath" name="filepath" value="<%=assetsDomain1.getFilePath()%>" />
		<td>
		<%=assetsDomain1.getFileName()%>
		</td>
		<td class="afile"><input class="chekbox_file" type="checkbox" name="filename" id="filename" value="<%=assetsDomain1.getFileName()%>"></td>
		</tr>
		 <%} }%>
		
		<tr class="select_mode"><td>
		<input type="radio" id="open" name="ksfile" value="Open" onclick="ksclick()">Open
             	<input type="radio" id="download" name="ksfile" value="Downloadable" onclick="ksclick()">Downloadable
             	</td></tr>
             	</table>
		<div class="table_outer">
		<p id="display"></p>
						<table border="1">	
					
				<tr>

					<th style="width: 175px;">Student</th>
					<th class="tab" style="width:85px;">Action</th>
				</tr>
						
							 <%CollaborateDao dao= new CollaborateDao();
							 AdminDomain adminDomain=new AdminDomain();
					           ArrayList<AdminDomain>al=new ArrayList<AdminDomain>();
							    al=dao.getStudentId(userid,organizationId);
							    Iterator<AdminDomain> it= al.iterator();
							    %>
							    <tr><td>All</td>
							    	<td class="a"><input class="chekbox_student" type="checkbox" onClick='toggle(this)'></td>
							    	</tr>
							    <%
							  while(it.hasNext()) 
							    {
								  adminDomain=it.next();
								  String name=adminDomain.getAdminName();
								  String Id=adminDomain.getAdminId();
								  String idAndName=name+" ("+Id+")";
								 // System.out.println("name="+name);
								 // System.out.println("Id="+Id);
							    	%>
							    	
							    	<tr>
									<td><%=idAndName%></td> 
									<td class="a"><input class="chekbox_student" type="checkbox" name="selectstudent" id="selectstudent" value="<%=Id%>"></td>
									
							   </tr>
							 <%   }%> 
							   
</table>
<input class="add_btn1 submit_btn" type="button" value="Send"  />
</div>
		</div>
		</form>
		</div>
		</div>
	</div>
	</div>
</body>
<script>
</script>
</html>