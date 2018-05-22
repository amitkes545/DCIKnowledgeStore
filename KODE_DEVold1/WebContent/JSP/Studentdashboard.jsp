	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.kds.KODE_DEV.domain.*" %>
<%@page import="java.util.*" %>
     
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../CSS/kedu.css" rel="stylesheet"/>
<title>keDuCom</title>
<style type="text/css">
p{margin: 0px}
.para1
{
	text-align: center;
	font-family: bold;
	padding: 10px;
	background-color: #1d87da;
	color:#fff;
	font-size: 16px;
	margin: 0px;
}

.section
{
width:1000px;
margin:0 auto;
    margin-top: 10px;
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

margin-top: 75px;
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
	height: 225px;
	overflow:hidden;
}
.session_tab
{
height:199px;
overflow: auto;
}

.assess
{
	border: 1px solid #888388;
	border-radius:4px;
	height: 225px;
	overflow:hidden;
}
.assess_tab
{
height:210px;
overflow: auto;
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
width: 33%;
}
.cols_40{
width: 33%;
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
				/* $(".click").click(function() {
					    	
					    	  var libraryId= $(this).attr('id');
					    	  //alert(libraryId);
					    	  
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
			});	     */
						
						
						
});

					</script>
<body><!--  -->
<div class="container">
<div class="main_div">
<div>
<%@include file="headers.jsp" %>
</div>
<div style="clear: both;"></div>
<div>
<%@include file="Participant_menu.jsp" %>
</div>
<%
					//ArrayList<RetriveImagesDomain> arl=(ArrayList<RetriveImagesDomain>)request.getAttribute("knowlegeAssets");
						//			Iterator<RetriveImagesDomain> it= arl.iterator();
									ArrayList<AssessmentDomain> arl1=(ArrayList<AssessmentDomain>)request.getAttribute("assessments");
									Iterator<AssessmentDomain> it1= arl1.iterator();
									ArrayList<AssessmentDomain> arl2=(ArrayList<AssessmentDomain>)request.getAttribute("assignments");
									Iterator<AssessmentDomain> it2= arl2.iterator();
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
/* $(document).ready(function () {
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
}); */
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
<%-- <div class="sec_1_left">
<div class="library">
<p class="para1">Library</p>
<div class="lib_tab">
<%ArrayList<FacilitatorKnowReportDomain> libDomain=(ArrayList<FacilitatorKnowReportDomain>)request.getAttribute("libraryDetails");
									Iterator<FacilitatorKnowReportDomain> iteratorLibrary= libDomain.iterator();
 while(iteratorLibrary.hasNext())
									{
	FacilitatorKnowReportDomain add=iteratorLibrary.next();
										  %>
										 
										  <input class="click" type="button" id="<%=add.getLibId()%>" value="<%=add.getLibId()%>">
										  <span class="ww"><%=add.getLibName()%></span>
										  
										  
									<%} 
									%>
</div>


</div>
</div> --%>
<div class="sec_1_right">
<div class="session">       
<p class="para1">Session</p>
<div class="session_tab">
<table border="1px" width="100%">
<tr>
<td class="cols_20">ID</td>
<td class="cols_40" align="center">Session Name</td>
<td class="cols_40" align="center">Start Time</td>
</tr>
</table>
<div style="height: 120px; overflow-y: auto;">
<table border="1px" width="100%">
<tr>
<%
while(iteratorSession.hasNext())
{
	SessionDomain add=iteratorSession.next();
	//System.out.println("session id::"+add.getSessionId());
%>
<td class="cols_20"><%=add.getSessionId() %></td>
<td class="cols_40"><%=add.getSessionName() %></td>
<td class="cols_40"><%=add.getSessionStartTime()%></td>
</tr>
<%} %>
</table>
</div>
</div>
</div>
</div>
</div>
<div style="clear: both;"></div>

<div class="section_2">
 <div class="sec_2_left">
<div class="assess">
<p class="para1">Assessment</p>
<div class="assess_tab">
<table border="1px" width="100%">
<tr>
<td class="cols_20" align="center">Assessment Name</td>
<td class="cols_40" align="center">Date</td>
<td class="cols_40" align="center">Time</td>
</tr>
</table>
<div style="height: 120px; overflow-y: auto;">
<table border="1px" width="100%">
<tr>
<%
while(it1.hasNext())
{
	AssessmentDomain add=it1.next();
%>
<td class="cols_20"><%=add.getAssessmentName()%></td>
<td class="cols_40"><%=add.getUploadedDate() %></td>
<td class="cols_40"><%=add.getUploadedTime() %></td>
</tr>
<%} %>
</table>
</div>
</div>


</div>

</div> 

<div class="sec_2_right">
 
<div class="assign">
<p class="para1">Assignment</p>
<div class="assign_tab">
<table border="1px" width="100%">
<tr>
<td class="cols_20" align="center">Assignment Name</td>
<td class="cols_40" align="center">Date</td>
<td class="cols_40" align="center">Time</td>
</tr>
</table>
<div style="height: 120px; overflow-y: auto;">
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
</tr>
<%} %>
</table>
</div>
</div>

</div>
</div>
</div>
<div style="clear: both;"></div>

		</div>		

</div>

</div>
</body>
</html>