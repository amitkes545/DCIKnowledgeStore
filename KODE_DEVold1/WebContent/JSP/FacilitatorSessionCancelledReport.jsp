<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="com.kds.KODE_DEV.dao.FacilitatorSessionReportDao"%>
      <%@ page import="com.kds.KODE_DEV.domain.SessionDomain"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
 <%@page import="java.util.*"%>
 <%@page import="java.util.Date"%>

  <%@page import="java.io.*"%>
  <%@page import="java.text.*" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.js"></script>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<script src="../JS/datetimepicker_css.js"></script>
<script type="text/javascript" src="../JS/validatePostSession.js"></script>
<link href="../CSS/jquery.datetimepicker.css" rel="stylesheet"></link>
<link href="../CSS/table_scroll.css" rel="stylesheet"/>
<link href="../CSS/design-common.css" rel="stylesheet"/>
<!-- <script type="text/javascript" src="../JS/jquery.datetimepicker.js">
</script> -->
<style type="text/css">
.search_result{padding: 0px 10px;margin-bottom: 25px;max-height: 250px;

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
background: url("../Image/DCI water mark 40.png") center center no-repeat;
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
.result_row_tr td{padding: 4px 4px 4px 10px;}
</style>
</head>

<script type="text/javascript">
        
        $(document).ready(function() {
        	$(".autohide").delay(5000).fadeOut("slow");
        });    
</script>

<%
Date todayDate=null;
String username = (String) session.getAttribute("username");
String userId = (String) session.getAttribute("userid");
String priv=session.getAttribute("previlege").toString(); //23 march
String organizationId = (String) session.getAttribute("orgid");
DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
Calendar cal = Calendar.getInstance();

//System.out.println(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22


String date=dateFormat.format(cal.getTime());

try {
	 todayDate = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(date);
	//System.out.println("date1 in try:"+todayDate);
} catch (ParseException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
String[] DateAndTime1=date.split("");
String fromDbDate1=DateAndTime1[0];
String fromDbTime1=DateAndTime1[1];
//System.out.println("current date:"+fromDbDate1);
//System.out.println("current time:"+fromDbTime1);

/* Date today;
String result;
//SimpleDateFormat formatter;
formatter = new SimpleDateFormat("yyyy/MM/dd : HH/mm/ss");
today = new Date();
result = formatter.format(today);

//System.out.println("Result: " + result); */
/* SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
sdf.format(new Date());
//System.out.println("today date:"+sdf.format(new Date())); */
/* Date dateobj = new Date();
//System.out.println(dateobj); */
	%>
<body>

	<div class="container" style="position:static;">
		<%-- <%@include file="all_one_header_knowstore.jsp"%> --%>
		<%@include file="../JSP/headers.jsp"%>
		
		
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="postpon_mod">
		<div style="text-align: center;">
		<p class="strong">Session Details</p>
		<form name="sessionDetails" method="post">
		 <div>
		
				
								
						
		</div>

		<div class="search_div">
		<input class="box1" type="text" id="search" placeholder="Search here">
		</div>
		<div class="search_result">
		
		<!-- heading starts here  -->
		<div class="table_section1">
		<div class="table_outer_row">
		<table width="100%" id="tblData">
		 <thead>
			 <tr class="row_head">

		<th width="10%">
		Session ID
		<div class="header_stop" style="width:10%;">Session ID</div>
		</th>
		
		<th width="10%">
		Session Name
		<div class="header_stop" style="width:10%;">Session Name</div>
		</th>
		<th width="10%">
		Category
		<div class="header_stop" style="width:10%;">Category</div>
		</th>
		<th width="10%">
		Start Date
		<div class="header_stop" style="width:10%;">Start Date</div>
		</th>
		<th width="10%">
		Start Time
		<div class="header_stop" style="width:10%;">Start Time</div>
		</th>
		<th width="10%">
		End Time
		<div class="header_stop" style="width:10%;">End Time</div>
		</th>
		<th width="10%">
		Duration
		<div class="header_stop" style="width:10%;">Duration</div>
		</th>
		
		<th width="10%">
		Recipient Type
		<div class="header_stop" style="width:10%;">	Recipient Type</div>
		</th>
		<th width="10%">
		 Status
		 <div class="header_stop" style="width:10%;">Status</div>
		</th>
		<th width="10%">
		 
		 <div class="header_stop" style="width:10%;"></div>
		</th>
		
		
		</tr>
		<thead>
		<% FacilitatorSessionReportDao facilitatorSessionDao = new FacilitatorSessionReportDao();
		ArrayList<SessionDomain> sessionDomain= facilitatorSessionDao.facultySessionUpcomingDetails(organizationId, userId); 	
		Iterator<SessionDomain> iterator1 =sessionDomain.iterator();
		//System.out.println("domain value is:"+sessionDomain.size());
		
		if(sessionDomain.size()> 0){
			int first=0;
				while(iterator1.hasNext())
				{
				SessionDomain sessionDomain1=iterator1.next();
				String datefromdb=sessionDomain1.getSessionStartTime();
				String sesid=sessionDomain1.getSessionId();
				Date dateFromdb = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(datefromdb);
				//System.out.println("todayDate:"+dateFromdb);
				//System.out.println("getStatus:"+sessionDomain1.getStatus());
				String start_dt=sessionDomain1.getSessionStartTime();
					String end_dt=sessionDomain1.getSessionEndTime();
					DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
					//System.out.println("formatterf::"+formatter);
Date date_st = (Date)formatter.parse(start_dt);
Date date_end = (Date)formatter.parse(end_dt);
//System.out.println("date_st::"+date_st);
//System.out.println("date_end::"+date_end);
SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
SimpleDateFormat newFormattime = new SimpleDateFormat("HH:mm");
//System.out.println("newFormat::"+newFormat);
String start_date = newFormat.format(date_st);
String end_date = newFormat.format(date_end);
String start_time = newFormattime.format(date_st);
String end_time = newFormattime.format(date_end);
//System.out.println("start_date::"+start_date);
//System.out.println("end_date::"+end_date);
//System.out.println("start_time::"+start_time);
//System.out.println("end_time::"+end_time);

				if(dateFromdb.after(todayDate) && (sessionDomain1.getStatus().equalsIgnoreCase("Cancel") || sessionDomain1.getStatus().equalsIgnoreCase("Open"))){
					//System.out.println("session values in if::"+dateFromdb.equals(todayDate));
					
					


			 %>
		 <tr class="result_row_tr">
				<td style="text-align:center;">
				<%=sessionDomain1.getSessionId() %>
				</td>
				<td>
				<%=sessionDomain1.getSessionName() %>
				</td>
				<td>
				<%=sessionDomain1.getCategory() %>
				</td>
				<td style="text-align:center;">
				<%=start_date %>
				</td>
				<td style="text-align:center;">
				<%=start_time %>
				</td>
				<td style="text-align:center;">
				<%=end_time%>
				
				</td>
				<td>
				<%=sessionDomain1.getDuration() %>
				</td>
				<%-- <td>
				<%=sessionDomain1.getCostOfSession() %>
				</td> --%>
				<td style="text-align:center;">
				<%String rtype=sessionDomain1.getRecipientType();
				String gname=sessionDomain1.getGroupName();
				System.out.println("rtype="+rtype);
				if(rtype!=null){
				if(rtype.contains("#")){%>
				<%=gname %>
				<%}else{ %>
				<%-- <input type="text" value=" --%><%=sessionDomain1.getRecipientType() %>
				<%} }%>
				
				
				</td>
				<td style="text-align:center;">
				<%=sessionDomain1.getStatus() %>
				</td>
				<td style="text-align:center;">
				
				</td>
				</tr> 
		 
			<%} //else if(!(dateFromdb.equals(todayDate))) { 
				
				//System.out.println("STATUS : "+sessionDomain1.getStatus());
			//System.out.println("session values in else::"+dateFromdb.equals(todayDate));%>
		<!--  	 <tr class="result_row_tr">
				<td>
				<%=sessionDomain1.getSessionId() %>
				</td>
				<td>
				<%=sessionDomain1.getSessionName() %>
				</td>
				<td>
				<%=sessionDomain1.getCategory() %>
				</td>
				<td style="text-align:center;">
				<%=start_date %>
				</td>
				<td style="text-align:center;">
				<%=start_time %>
				</td>
				<td style="text-align:center;">
				<%=end_time%>
				
				</td>
				<td>
				<%=sessionDomain1.getDuration() %>
				</td>
				<%-- <td>
				<%=sessionDomain1.getCostOfSession() %>
				</td> --%>
				<td>
				<%-- <%=sessionDomain1.getRecipientType() %> --%>
				<%String rtype=sessionDomain1.getRecipientType();
				String gname=sessionDomain1.getGroupName();
				System.out.println("rtype="+rtype);
				if(rtype!=null){
				if(rtype.contains("#")){%>
				<%=gname %>
				<%}else{ %>
				<%-- <input type="text" value=" --%><%=sessionDomain1.getRecipientType() %>
				<%} }%>
				</td>
				<td>
				<%=sessionDomain1.getStatus() %>
				</td>
				<td></td>
				</tr> 
		 <%}}
		else {
			 //System.out.println(" No upcoming sessions:");
		String message="No cancelled sessions";
		%>
			<p class="autohide" style="color:red; font-size:18px; font-weight: bold; top: 39%; left: 44%; position: absolute;"><%=message %></p>
		<% } %>
		<!--  -->
		</table>
		</div>
		</div>
		</div>
		</form>
		</div>
		
	</div>
		<%@ include file="../JSP/FooterViews.jsp"%>
	</div>
	<script type="text/javascript" src="../JS/jquery.min.js"></script>
	<script type="text/javascript">
			$(document).ready(function()
			{
				$("#search").keyup(function()
				{
					
					searchTable($(this).val());
				});
			});
			function searchTable(inputVal)
			{
				 var table = $('#tblData');
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
				/* var table = $("#tblData");
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
						/* if(found == true)
							{
							alert("message test");
							
							}
						 
						if(found == true)$('tr').show();else $(row).hide();
						/* alert("message"); 
					}
				}); */
			}
		</script>
</body>
<script>
onload();
</script>
</html>