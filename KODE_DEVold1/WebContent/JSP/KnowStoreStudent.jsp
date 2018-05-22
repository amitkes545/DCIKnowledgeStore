<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.kds.KODE_DEV.domain.*"%>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorSelectKnowStoreIdDao"%>
<%@page import="java.util.*"%>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorSelectKsidKStoreDao"%>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorManageLibDao"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.*"%>
<%@page import="java.io.File"%>
<%@page import="java.text.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/FacilitatorKnowSetup.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.js"></script>
<link href="../CSS/table_scroll.css" rel="stylesheet" />
<!--<link rel="stylesheet" href="../CSS/dci-style.css">-->
<!-- <link href="../CSS/owner.css" rel="stylesheet"/>
<link href="../CSS/global.css" rel="stylesheet"/> -->

<link href="../CSS/CreateCustomer.css" rel="stylesheet" />
<script src="../JS/pdf.js"></script>
<script src="../JS/datetimepicker_css.js"></script>
<script type="text/javascript" src="../JS/validatePostSession.js"></script>
<link href="../CSS/jquery.datetimepicker.css" rel="stylesheet"></link>
<link href="../CSS/design-common.css" rel="stylesheet" />

<!-- <script type="text/javascript" src="../JS/jquery.datetimepicker.js">
</script> -->

<style type="text/css">
.search_result {
	padding: 0px 10px;
	margin-bottom: 25px;
	font-family: arial;
	font-size: 14px;
}

.su {
	color: #008000;
	font-size: 14px;
	font-weight: bold;
	top: 44px;
	position: absolute;
	/* background: #fff;
    opacity: 0.7; */
	right: 0px;
	padding: 0px 36px;
}

.toolbar {
	display: none;
	/* added by prity to hide the download option from pdf */
}

paper-toolbar {
	display: none;
	/* added by prity to hide the download option from pdf */
}

.container {
	background: url("../Image/body.png") center center no-repeat;
}
</style>
<style type="text/css">
.postpon_mod {
	width: 95% !important;
	box-shadow: 5px 5px 5px 5px #FFF;
	margin: 15px auto 0px;
	padding: 15px 1px 0px 0px;
	border: 3px solid #C2C2C2;
	background-color: #FCF7F7;
	border-radius: 4px;
	opacity: 1;
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

.search_div {
	margin-top: -36px;
	position: absolute;
}

.opt {
	width: 265px !important;
	padding: 7px !important;
	border: 1px solid #C2C2C2;
	font-family: regular;
	border-radius: 4px;
}

.box1 {
	width: 250px !important;
	padding: 7px !important;
	border: 1px solid #c2c2c2;
	border-radius: 4px;
}

.row_head {
	background: none repeat scroll 0% 0% #009AE1;
	color: #FFF;
	font-weight: bold;
}

.row_head td {
	padding: 5px;
}

.row_head .col_2 {
	float: left;
	padding: 0px 15px;
}

.row_result .col_2 {
	float: left;
	padding: 0px 15px;
}

.result_row_tr td input {
	border: none;
	background: none;
	width: 99.5%;
}

.starttime {
	width: 130px;
}

.endtime {
	width: 120px;
}

.duration_r {
	width: 120px;
}

.cost_r {
	width: 80px;
}

.result_row_tr td {
	padding: 4px 4px 4px 10px;
}
</style>
</head>
<script>

$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
	
	 
	    });
</script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript">
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
	function viewPDF() {
		//alert("in");
		var PdfReader = require("pdfreader").PdfReader;
		//alert("in1"+PdfReader);
		new PdfReader().parseFileItems("..//1.pdf", function(err, item) {
			if (item && item.text)
				console.log(item.text);
		});
	}
	function clickKsId() {
		//alert("in");
		var libId = document.getElementById("libid").value;
		var subjectId = document.getElementById("subject").value;
		var coursetId = document.getElementById("course").value;

		//alert(subjectId);
		//alert(coursetId);
		//alert(libId);

		document.ksStore.action = "/KODE_DEV/ControllerServlet/StudentAssetKnowViewServlet?libID="
				+ libId
				+ "&subjectId="
				+ subjectId
				+ "&courseId="
				+ coursetId
				+ "";
	//	alert("in");
		document.ksStore.submit();
	//	alert("in1");
	}
	$(document).ready(function() {
		
		$('#search').keyup(function() {
			searchTable($(this).val());
		});
	});
	function searchTable(inputVal) {
		var table = $('#tableId');
		table.find('tr').each(function(index, row) {
			var allCells = $(row).find('td');
			if (allCells.length > 0) {
				var found = false;
				allCells.each(function(index, td) {
					var regExp = new RegExp(inputVal, 'i');
					if (regExp.test($(td).text())) {
						found = true;
						return false;
					}
				});
				if (found == true)
					$(row).show();
				else
					$(row).hide();
				//if(found == true)$('tr').show();else $(row).hide();
			}
		});
	}

	function loadSubject() {
		
		var selected = document.getElementById("course");
		var selectedValue = selected.options[selected.selectedIndex].value;
		
		$.get('${pageContext.request.contextPath}/GCFS', {
			
			
			selectedValue : selectedValue
			
			
		}, function(jsonResponse) {
			console.log("json response :", jsonResponse);
			var select = $('#subject');

			select.find('option').remove();
			jsonResponse = $.parseJSON(jsonResponse);
			console.log("parsed json response :", jsonResponse);
			$('<option>').val('').text('Select Subject').appendTo(select);
			$('<option>').val('All').text('All').appendTo(select);
			$.each(jsonResponse, function(index, value) {
				console.log('Index value here :', index);
				console.log('Actual obj value here :', value.subjectId);
				$('<option>').val(value.subjectId).text(value.subject+" ("+value.subjectId+")").appendTo(select);
			});
			

		});

	}

	function loadCourse() {
		
	
      //   alert("hiiiiiii");
		$.get('${pageContext.request.contextPath}/coursedetails', {
		//	selectedValueCourse : selectedValueCourse,
		//	selectedValueSubject : selectedValueSubject
		}, function(jsonResponse) {
			console.log("json response :", jsonResponse);
			var select = $('#course');
			select.find('option').remove();
			jsonResponse = $.parseJSON(jsonResponse);
			console.log("parsed json response :", jsonResponse);
			$('<option>').val('').text('Select Subject').appendTo(select);
			$.each(jsonResponse, function(index, value) {
				console.log('Index value here :', index);
				console.log('Actual obj value here :', value.libId);
				$('<option>').val(value.libId).text(value.libId).appendTo(
						select);
			});

			$('<option>').val('All').text('All').appendTo(select);

		});

	}
</script>
<%
	String organizationId = (String) session.getAttribute("orgid");
	String userid = (String) session.getAttribute("userid");
	String username = (String) session.getAttribute("username");
	if (username == null)
		response.sendRedirect("../JSP/error.jsp");
	FacilitatorSelectKnowStoreIdDao KsIdDynamic = new FacilitatorSelectKnowStoreIdDao();
	String newKsid = KsIdDynamic.fetchDynamicKsid(organizationId);

	FacilitatorKAssetsReportDomain setKsid = new FacilitatorKAssetsReportDomain();
	setKsid.setKsId(newKsid);

	ArrayList<FacilitatorKAssetsReportDomain> reportDomain = new ArrayList<FacilitatorKAssetsReportDomain>();
	reportDomain = (ArrayList<FacilitatorKAssetsReportDomain>) request.getAttribute("aValue");
	System.out.println("rd=" + reportDomain);
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	Calendar cal = Calendar.getInstance();
	String date = dateFormat.format(cal.getTime());
	Date todayDate = null;
	try {
		todayDate = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(date);
		//System.out.println("date1 in try:"+todayDate);
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
%>
<body>

	<div class="container" style="position: static">
		<%@include file="../JSP/PopupReadOnly.jsp"%>
		<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
			<%@ include file="../JSP/Participant_menu.jsp"%>
		</div>
		<div style="clear: both;"></div>
		<div class="postpon_mod">
			<div style="text-align: center;">
				<p class="strong">Knowledge Store</p>
				<form name="ksStore" method="post">

					<div>

						<select name="libid" id="libid" class="opt" onchange="loadCourse()">
							<option value="">Choose Library*</option>
							
							
							
							<%
								String organizationId1 = (String) session.getAttribute("orgid");
								String result = "";
								String result1 = "";
								
								//String userid=(String)session.getAttribute("userId");
								

								GetCourseForStudentDao getlibForStudent = new GetCourseForStudentDao();
								//	List<CourseStudentDomain> courses = getCourseForStudent.getCourseForStudent(userid);

								List<CourseStudentDomain> library = getlibForStudent.getAllLibrary(organizationId1);

								for (CourseStudentDomain course : library) {
									System.out.println("course :" + course.getCourseId());
									result = course.getCourseId();
									result1 = course.getCourseName();
									 String libIdAndName  = (result+" ("+result1+")");%>
							%>


							<option value="<%=result%>"><%=libIdAndName%>
							</option>
							<%
								}
							%>
							
							<option value="All">All</option>

						</select> <select name="course" class="box_lng required" id="course"
							onchange="loadSubject()" style="margin-left: 0px;">
							<option value="">Select Course*</option>			
						</select> <select name="subject" class="box_lng required" id="subject"
							  onchange="clickKsId()" style="margin-left: 0px;">
							<option value="">Select Subject</option>
							<option value="all">Select Subject</option>

						</select>

					</div>
					<div class="">
						<input class="box1" type="text" id="search" placeholder="Search">
					</div>
					<div class="search_result">
						<!-- heading starts here  -->
						<div class="table_section1">
							<div class="table_outer_row">
								<table width="100%" id="tableId">
									<thead>
										<tr class="row_head">
											<th width="12.5%">Sl #
												<div class="header_stop" style="width: 12.5%;">Sl #</div>
											</th>

											<th width="12.5%">Topic
												<div class="header_stop" style="width: 12.5%;">Topic</div>
											</th>
											<th width="12.5%">Sub-topic
												<div class="header_stop" style="width: 12.5%;">Sub-topic</div>
											</th>
											<th width="12.5%">Descriptions
												<div class="header_stop" style="width: 12.5%;">Descriptions</div>
											</th>
											<th width="12.5%">Assets Name
												<div class="header_stop" style="width: 12.5%;">Assets
													Name</div>
											</th>

											<th width="12.5%">Assets Size
												<div class="header_stop" style="width: 12.5%;">Assets
													Size</div>
											</th>
											<th width="12.5%">Uploaded Date
												<div class="header_stop" style="width: 12.5%;">Uploaded
													Date</div>
											</th>
											<th width="12.5%">Uploaded Time
												<div class="header_stop" style="width: 12.5%;">Uploaded
													Time</div>
											</th>

										</tr>
									</thead>
									<%
										int count = 1;
										if (reportDomain != null) {
											Iterator<FacilitatorKAssetsReportDomain> iterator1 = reportDomain.iterator();

											while (iterator1.hasNext()) {
												FacilitatorKAssetsReportDomain assetsDomain1 = iterator1.next();
												//String[] s1=reportDomain1.getFilePath().split("/");
												System.out.println("reportDomain1.getFileName()=" + assetsDomain1.getLibId());
												String datefromdb = assetsDomain1.getUploadDate().toString();
												//System.out.println("date from db:"+datefromdb);
												DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
												//System.out.println("formatterf::"+formatter);
												Date date_display = (Date) formatter.parse(datefromdb);
												//System.out.println("date_display::"+date_display);
												SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
												//System.out.println("newFormat::"+newFormat);
												String ddmmyyyy_format = newFormat.format(date_display);
									%>

									<tr class="result_row_tr">
										<td align="right" style="text-align: center;"><%=count%></td>
										<td align="left"><%=assetsDomain1.getDepartment()%></td>
										<td align="left"><%=assetsDomain1.getSubject()%></td>
										<td align="left"><%=assetsDomain1.getDescription()%></td>
										<%
											String extension = "";
													int ind = assetsDomain1.getFilePath().lastIndexOf(".");
													if (ind != -1)
														extension = assetsDomain1.getFilePath().substring(ind);
													System.out.println("ext=" + extension);
													if (extension.equalsIgnoreCase(".mp3")) {
														System.out.println("in if=" + assetsDomain1.getFilePath());
														//String link=http://www.kompacdigit.com/""
										%>
										<td align="left">
											<!--	<a href="http://www.kompacdigit.com/<%=assetsDomain1.getFileName()%>" target="_blank"><%=assetsDomain1.getFileName()%></a>
		<a href="../JSP/video_session.jsp" target="_blank"><%=assetsDomain1.getFileName()%></a>-->
											<a
											href="../JSP/video_session.jsp?filename=<%=assetsDomain1.getFileName()%>"
											target="_blank"><%=assetsDomain1.getFileName()%></a>
										</td>
										<%
											} else {
														System.out.println("in else=");
										%>
										<td align="left">
											<%-- <a href="/KODE_DEV/ControllerServlet/DownloadVideo?filePath=<%=assetsDomain1.getFileName()%>"><%=assetsDomain1.getFileName()%></a> --%>
											<a href="#"
											onclick="popup_read_only('<%=assetsDomain1.getFileName()%>','<%=assetsDomain1.getFilePath()%>')"><%=assetsDomain1.getFileName()%></a>
										</td>

										<%
											}
										%>
										<!-- <td>
		<a href="../web/viewerpdf.jsp" target="_blank">view</a>
		</td> -->
										<td align="left"><%=assetsDomain1.getFileSize() + " " + "KB"%>
										</td>
										<td align="right"><%=ddmmyyyy_format%></td>
										<td align="right"><%=assetsDomain1.getUploadTime().getHours()%>:<%=assetsDomain1.getUploadTime().getMinutes()/*+"  "+"min" */%>
										</td>

									</tr>
									<%
										count++;
											}
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
	onload();
</script>
</html>