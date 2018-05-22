<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.kds.KODE_DEV.domain.*" %>
<%@page import="java.util.*" %>
      <%@page import="java.util.Date"%>
     <%@page import=" java.io.File" %>
       <%@page import="java.text.*" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../CSS/kedu.css" rel="stylesheet"/>
<link href="../CSS/design-common.css" rel="stylesheet"/>
<title>Welcome to KODE_DEV</title>
<style type="text/css">
p{margin: 0px}
.para1
{
	text-align: center;
	font-family: bold;
	padding: 10px;
	background-color: #a5caf6;
	color:#000;
	font-size: 16px;
	margin: 0px;
}

.section
{
width:1000px;
margin:0 auto;
    margin-top: 0px;
    margin-bottom: 25px;
}
/* .section_1,.section_2
{
width:1000px;
margin:0 auto;
} */
.sec_1_left,.sec_1_right,.sec_2_left,.sec_2_right
{
	width:40%;
	float:left;
	margin-left: 5%;
	margin-right: 5%;

}
.sec_1_left,.sec_1_right
{

margin-top: 65px;
}

.sec_2_left,.sec_2_right
{
margin-top: 25px;
}
.sec_1_left .library
{
	border: 1px solid #888388;
	border-radius:4px;
	height: 225px; 
	overflow:hidden;
}
.lib_tab
{
height:210px;
overflow: auto;
}

.session
{
	border: 1px solid #888388;
	border-radius:4px;
	height: 401px;
	overflow:hidden;
	position: relative;
	top: -64px;
	width: 450px;
}
.session_tab
{
height:377px;
/* overflow: auto; */
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
     height: 417px;
}
.assess
{
	border: 1px solid #888388;
	border-radius:4px;
	height: 401px;
	overflow:hidden;
	position: relative;
	top: -64px;
	width: 450px;
	left: -75px;
}
.assess_tab
{
height:377px;
/* overflow: auto; */
}
.assign
{
	border: 1px solid #888388;
	border-radius:4px;
	height: 225px;
	overflow:hidden;
	
}
.assign_tab
{
height:210px;
overflow: auto;
}
.set1
{
width:50%;

}
.left1
{
margin-top:110px;
border:1px solid #c2c2c2;
width:100%;
}
.set2
{
width:50%;
}
.cols_20{
width: 19%;
padding:7px 4px;
}
.cols_40{
width: 19%;
padding:7px 4px;
}
.right1
{

margin-top:110px;
border:1px solid #c2c2c2;
width:100%;
float:left;

}

.left2
{
margin-top:110px;
border:1px solid #c2c2c2;
width:100%;
}
</style>
</head>
  <script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script>
//ajax code for retrive session details
$(document).ready(function(){
	
			$(".mra").click(function() {
				  var sessionId= $(this).attr('id');
					 	 $.ajax({  
					   	    type: "POST",  
					   	    url: "/KODE_DEV/ControllerServlet/FacilitatorSessionDetailsService",  
					   	    data: "sessionId=" + sessionId,  
					   	 dataType: "json",
					   	    success: function(result){
					   	    	
					   	    	$("#aa").html('');
					   	    
					   	     for(var i=0; i<result.length; i++){
                               
                               var seesion=result[i].sessionId;
                               
                               var sessionName=result[i].sessionName;
                               var startTime=result[i].sessionStartTime;
                               var endTime=result[i].sessionEndTime;
                               var duration=result[i].duration;
                               var cost=result[i].costOfSession;
                               $("#aa").append("<br/>"+seesion);
                               $("#aa").append("<br/>"+sessionName);
                               $("#aa").append("<br/>"+startTime);
                               $("#aa").append("<br/>"+endTime);
                               $("#aa").append("<br/>"+duration);
                               $("#aa").append("<br/>"+cost);
                             
                          }
                       },
                     error: function(e){  
					   	      alert('Error: ' + e);  
					   	    }  
					   	  });  
					   	});  
			//ajax code for retrive library files
				$(".library").click(function() {
					    	  
					    	 
					    	  var libraryId= $(this).attr('id');
					    	  
			    $.ajax({
			          type: "POST",
			         url: "/KODE_DEV/ControllerServlet/FacilitatorLibraryDetailsService",
			         data: "libraryId=" + libraryId,
			         dataType: "json",
			           success: function(result) {
			        	   
			        	   $("#lib").html('');
			        	   // alert("data is :"+result.length); 
					   	     for(var i=0; i<result.length; i++){
                               
                             var fileName=result[i].filePath;
                             $("#lib").append("<br/>"+fileName);
                            // alert(fileName);
                             } 
			         },
			         error: function(e){
			        	 $("#lib").html('');
			         
			        	 $("#lib").append("<br/>Library doesn't contain any files");
			        
			            //alert("error"+e.toSource());
			         }
			    });
			});	    
						
						
						
});

					</script>
<body>
<div class="container" style="height:auto;">
<div class="main_div">
<div>
<%@include file="headers.jsp" %>
</div>
<div style="clear: both;"></div>
<div>
<%@include file="menus.jsp" %>
</div>
<div class="postpon_mod">
		<div style="text-align: center; height:395px;">
<%
String priv=session.getAttribute("previlege").toString();
String userId = (String) session.getAttribute("userid");
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
					ArrayList<RetriveImagesDomain> arl=(ArrayList<RetriveImagesDomain>)request.getAttribute("knowlegeAssets");
					Iterator<RetriveImagesDomain> it= arl.iterator();
					
									ArrayList<AssessmentDomain> arl1=(ArrayList<AssessmentDomain>)request.getAttribute("assessments");
									Iterator<AssessmentDomain> it1= arl1.iterator();
									/* ArrayList<AssessmentDomain> arl2=(ArrayList<AssessmentDomain>)request.getAttribute("assignments");
									Iterator<AssessmentDomain> it2= arl2.iterator(); */
									String totalspace=request.getAttribute("totalspace").toString();
									String usedspace=request.getAttribute("usedspace").toString();
									int availablespace=Integer.parseInt(totalspace)-Integer.parseInt(usedspace);
									ArrayList<SessionDomain> sdomain=(ArrayList<SessionDomain>)request.getAttribute("sessionDetails");
									Iterator<SessionDomain> iteratorSession= sdomain.iterator();
									
                                     
									%>
<div style="clear: both;"></div>

<script type="text/javascript">
$(document).ready(function () {
    size_li = $("#myAssignment p").size();
    
    x=5;
    
   // $('#myAssignment p:lt('+x+')').show();
    /* $('#myAssignment p').not(':lt('+x+')').hide(); */
    
    $('#loadMore').click(function () {
        x= (x+5 <= size_li) ? x+5 : size_li;
        $('#myAssignment p:lt('+x+')').show();
    });
    $('#showLess').click(function () {
        x=(x-5<0) ? 5 : x-5;
        $('#myAssignment p').not(':lt('+x+')').hide();
    });
});
//jquery code for read more assessment
$(document).ready(function () {
    size_li1 = $("#myValue p").size();
    x=3;
    
    x=(x-5<0) ? 3 : x-5;
        $('#myValue p').not(':lt('+x+')').hide();
        
    $('#assess').click(function () {
        x= (x+5 <= size_li1) ? x+6 : size_li1;
        $('#myValue p:lt('+x+')').show();
    });
    $('#showLess').click(function () {
        x=(x-5<0) ? 3 : x-5;
        $('#myValue p').not(':lt('+x+')').hide();
    });
});
//jquery code for read more session value
$(document).ready(function () {
    size_li2 = $("#sessionValue p").size();
    x=3;
    
    x=(x-5<0) ? 3 : x-5;
        $('#sessionValue p').not(':lt('+x+')').hide();
        
    $('#session').click(function () {
        x= (x+5 <= size_li2) ? x+6 : size_li2;
        $('#sessionValue p:lt('+x+')').show();
    });
    $('#showLess').click(function () {
        x=(x-5<0) ? 3 : x-5;
        $('#sessionValue p').not(':lt('+x+')').hide();
    });
});
//jquery code for read more library
$(document).ready(function () {
    size_li3 = $("#libraryValue p").size();
    x=3;
    
    x=(x-5<0) ? 3 : x-5;
        $('#libraryValue p').not(':lt('+x+')').hide();
        
    $('#libraryDetails').click(function () {
        x= (x+5 <= size_li3) ? x+6 : size_li3;
        $('#libraryValue p:lt('+x+')').show();
    });
    $('#showLess').click(function () {
        x=(x-5<0) ? 3 : x-5;
        $('#libraryValue p').not(':lt('+x+')').hide();
    });
});
</script>


<div class="odash">
<div class="set1">
<!-- jquery code for read more assignment -->
</div>
<div>
<%-- <%@include file="FooterViews.jsp" %> --%>
</div>
</div>
<!-- stars here -->
<div class="section">
<div class="section_1">
<div class="sec_1_left">
<div class="assess">
<p class="para1">Assignment</p>
<div class="assess_tab" style="height:400px;overflow-y:scroll;">
<table border="1px" width="450px">
<tr>
<td class="cols_20" align="center" style="width:150px;">Assignment Name</td>
<td class="cols_40" align="center" style="width:150px;">Date</td>
<!-- <td class="cols_40" align="center" style="width:150px;">Time</td> -->
</tr>


<tr>
<%
while(it1.hasNext())
{
	AssessmentDomain add=it1.next();
	String datefromdb=add.getUploadedDate().toString();
	String timefromdb=add.getUploadedTime().toString();
	String time_format=timefromdb.substring(0,5);
	System.out.println("timefromdb from db:"+time_format);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		//System.out.println("formatterf::"+formatter);
Date date_display = (Date)formatter.parse(datefromdb);
//System.out.println("date_display::"+date_display);
SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
//SimpleDateFormat newFormattime = new SimpleDateFormat("HH:mm");
//System.out.println("newFormat::"+newFormat);
String ddmmyyyy_format = newFormat.format(date_display);
//String time_format = newFormattime.format(timefromdb);
%>
<td class="cols_20" style="text-align:left;width:150px"><%=add.getAssessmentName()%></td>
<td class="cols_40" style="width:150px;"><%=ddmmyyyy_format %></td>
<%-- <td class="cols_40" style="width:150px;"><%=time_format %></td> --%>
</tr>
<%} %>
</table>

</div>


</div>
<%-- <div class="library">
<p class="para1">Library</p>
<div class="lib_tab">
			 <table border="1px" width="100%">
<tr>
		<td class="cols_20" align="center">Library Name</td>	</tr>
<%ArrayList<FacilitatorKnowReportDomain> libDomain=(ArrayList<FacilitatorKnowReportDomain>)request.getAttribute("libraryDetails");
									Iterator<FacilitatorKnowReportDomain> iteratorLibrary= libDomain.iterator();
 while(iteratorLibrary.hasNext())
									{
	FacilitatorKnowReportDomain add=iteratorLibrary.next();
										  %>
														 
										  <input type="button" id="<%=add.getLibId()%>" value="<%=add.getLibId()%>">
								<tr><td>		  <span class="ww"><%=add.getLibName()%></span>
										  </td></tr>
									<%} 
									%>
									</table>
</div>


</div> --%>
</div>
<div class="sec_1_right">
<div class="session">       
<p class="para1">Session</p>
<div class="session_tab" style="height:400px;overflow-y:scroll;">
<table border="1px" width="100%" >
<tr>
<td class="cols_20" align="center" style="width:20%;">ID</td>
<td class="cols_40" align="center"  style="width:20%;">Session Name</td>
<td class="cols_40" align="center" style="width:20%;">Start Date</td>
<td class="cols_40" align="center" style="width:20%;">Start Time</td>
<td class="cols_40" align="center" style="width:20%;">End Time</td>
</tr>

<tr>
<%
int first=0;
while(iteratorSession.hasNext())
{
	SessionDomain add=iteratorSession.next();
	//System.out.println("session id::"+add.getSessionId());
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
						System.out.println("formatterf::"+formatter);
						
						String start_date=add.getSessionStartTime();//.toString();
						String end_date=add.getSessionEndTime();//.toString();
	Date start_date_display = (Date)formatter.parse(start_date);
	Date end_date_display = (Date)formatter.parse(end_date);
	System.out.println("date_display::"+end_date_display);
	SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat newFormattime = new SimpleDateFormat("HH:mm");
	System.out.println("newFormat::"+newFormat);
	//String ddmmyyyy_format = newFormat.format(date_display);
	String start_date_only = newFormat.format(start_date_display);
//	String end_date_ = newFormat.format(date_end);
	String start_time = newFormattime.format(start_date_display);
	String end_time = newFormattime.format(end_date_display);
%>
<td class="cols_20" style="width:20%;"><%=add.getSessionId() %></td>
<td class="cols_40" style="text-align:left;width:20%;"><%=add.getSessionName() %></td>
<td class="cols_40" style="width:20%;"><%=start_date_only%></td>
<%-- <td class="cols_40" style="width:20%;"><%=start_time%></td> --%>
<%if(first==0){ %>
 <td class="cols_40"><a href="/KODE_DEV/ControllerServlet/AllInOne?sesid=<%=add.getSessionId()%>&ID=<%=userId%>&privilege=<%=priv%>" ><%=start_time%></a>
</td>
<%first=1;}else{ %>
<td class="cols_40"><%=start_time%></td>
<%} %>
<td class="cols_40" style="width:20%;"><%=end_time%></td>
</tr>
<%} %>
</table>

</div>
</div>
</div>
</div>
<div style="clear: both;"></div>
<div class="section_2">
 <div class="sec_2_left">


</div> 

<div class="sec_2_right">
 
<%-- <div class="assign">
<p class="para1">Knowledge Store</p>
<div class="assign_tab">
<table border="1px" width="100%">
<tr>
<td class="cols_20" align="center">Total Space</td>
<td class="cols_40" align="center">Available Space</td>
<td class="cols_40" align="center">Used Space</td>
</tr>
</table>
<div style="height: 115px; overflow-y: auto;">
<table border="1px" width="100%">
<tr>
<% 
while(it2.hasNext())
{
	AssessmentDomain add=it2.next();
%>
<td class="cols_20"><%=add.getAssessmentName() %></td>
<td class="cols_40"><%=add.getUploadedDate() %></td>
<td class="cols_40"><%=add.getUploadedTime() %></td>
<td class="cols_20"><%=totalspace %> GB</td>
<td class="cols_40"><%=availablespace %> GB</td>
<td class="cols_40"><%=usedspace %> GB</td>
</tr>
<%} %>
</table>
</div>
</div>
</div> --%>
</div>
</div>
<div style="clear: both;"></div>
</div>
</div></div>
</div>
<%@ include file="../JSP/FooterViews.jsp"%>
</div>
</body>
</html>