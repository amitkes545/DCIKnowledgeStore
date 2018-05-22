<%@ page errorPage ="../JSP/error.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@page import="com.kds.KODE_DEV.domain.UsersInfoDomain" %>
    <%@page import="com.kds.KODE_DEV.domain.AllGroupBean" %>
    <%@page import="com.kds.KODE_DEV.dao. StudentGroupNameDao" %>
    <%@page import="java.util.Iterator" %>
    <%@page import="java.util.ArrayList" %>
    <%@page import="java.io.File" %>   
    <%@page import="java.util.Date"%>       <%@page import="java.util.*"%>       <%@page import="java.text.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <title>WelCome To KODE_DEV</title> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<link href="../CSS/kedu.css" rel="stylesheet"/>
<link href="../CSS/table_scroll.css" rel="stylesheet"/>
<link href="../CSS/design-common.css" rel="stylesheet"/>
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
background: url("../Image/body.png") center center no-repeat;
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
    max-height: 540px;
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

}
.opt {
    width: 250px !important;
    padding: 7px !important;
    border: 1px solid #C2C2C2;
    font-family: regular;
    border-radius: 4px;
    z-index: 9;
    position: relative;
    margin-top: -3px;
    height: 267px;
}
.box1
{
width: 250px!important;
    padding: 7px!important;
    border: 1px solid #c2c2c2;
    border-radius: 4px;
}
.row_head{
   
    color: #FFF;
    font-weight: bold;
    margin-top:0px;
    }
    .row_head td{
    padding: 5px;
    border: 1px solid #ddd;
    }	
.row_head .col_2{float: left; padding: 0px 15px;}
.row_result .col_2{float: left; padding: 0px 15px;}

.starttime{width: 130px;}
.endtime{width: 120px;}
.duration_r{width: 120px;}
.cost_r{width: 80px;}
.result_row_tr td{border-right: 1px solid #bbbbbb;padding: 4px 4px 4px 10px;word-wrap: break-word;text-align:left;}

.result_row_tr {
border: 1px solid #ddd;
}
</style>
<!-- <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script> -->
<script type="text/javascript" src="../JS/jquery.min.js"></script>

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
		/* var table = $('#tblData');
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
				}); */
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
	}
</script>

<script>
function onload(){
	<% String sessionName=(String)request.getAttribute("SessionName");
	System.out.println("session name in an onload:"+sessionName);
	if(sessionName!=null && sessionName.length()>0) {
		System.out.println("session name  in onload:"+sessionName);
	 %> 
	 var selectBox = document.getElementById("sessionID");
	 for(var i=0;i<selectBox.options.length;i++){
			 if(selectBox.options[i].value == '<%=sessionName%>')
			{
	 selectBox.options[i].selected=true;
				 break;
			 }
		 }
				
		<%}%>
}
function clickViewId(obj){
   var  selvalue=obj;  
if(selvalue!="")
   {document.formpartipantsview.action="/KODE_DEV/ControllerServlet/getViewDetailServlet?idvalue="+selvalue;
    document.formpartipantsview.submit();
}
}
	 



</script>
</head>
<%
		//HttpSession mess = request.getSession();
		//String msg = "";
		String ddmmyyyy_format="";
		String message = (String) session.getAttribute("MsgValue");
		String orgid=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		String Viewid = (String)session.getAttribute("Participant_viewid");
		////System.out.println("Viewid :"+Viewid);
		session.setAttribute("Participant_viewid",null);
		//System.out.println("username id:"+username+"message id:"+message);
		
		//String temp1="";
		
		ArrayList<UsersInfoDomain> studentDetails=(ArrayList<UsersInfoDomain>)session.getAttribute("studentdetail");
		////System.out.println("studentDetails"+studentDetails);
		session.setAttribute("studentdetail",null);
		
	   
UsersInfoDomain uid=new UsersInfoDomain();
uid.setCreatedBy(session.getAttribute("userId").toString());
//uid.setCreatedBy((String)session.getAttribute("userId"));
uid.setOrgId(session.getAttribute("orgId").toString());
DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
Calendar cal = Calendar.getInstance();
String date=dateFormat.format(cal.getTime());Date todayDate=null;
%> 
<%String msg=request.getParameter("msg"); %>
<body>
<div class="container">
 <%@include file="headers.jsp"%>
<div style="clear: both;"></div>
<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>		
		<div style="clear: both;"></div>
		
		<div style="clear: both;"></div>
		<div class="postpon_mod">
		<div style="text-align: center;"> 
        <div style="text-align: center;">  
		<p class="strong">Participant(s) View</p>  
		 <form name="formpartipantsview" method="post">
<!-- <select onfocus='this.size=9;' 
onblur='this.size=1;' 
onchange='this.size=1; this.blur();'>
   
  </select> -->
		  <div class="select_box_height">
		  	<label id='choose' for='options'>Select options</label>
            <br clear='all' />
		<select  name="ViewID" placeholder="ViewName"  class="opt" id="options" size="10" onchange="clickViewId(this.value);" style='display:none;'>
		
	<!-- 	<option value="">Choose Participant</option> -->
		<option  Value="">-------------------Individual---------------------- </option>
		<%StudentGroupNameDao createSessionDao= new StudentGroupNameDao();
					           	ArrayList<AllGroupBean> al=new ArrayList<AllGroupBean>();
					           	al=createSessionDao.selectAllDetailParticipantsIndividual(userid,orgid);
					         	Iterator<AllGroupBean> iterator =al.iterator();
					         	String name="", id="",iid="",u_id="ID",u_id1="";
					         
				 				while(iterator.hasNext())
									{									
				 					AllGroupBean allbean=iterator.next();
				 					id=allbean.getUserid();
				 					name=allbean.getUsername();
				 					iid=name+" ("+id+")";
				 					u_id1=u_id+id;
				 					////System.out.println("u_id1=  "+u_id1+ " iid= "+iid);
				 					if(Viewid != null && u_id1.equals(Viewid)){
				 						%>
										<option value="<%=u_id1%>" selected="selected"><%=iid%></option>
									<%	
				 					}else{
									%>
										<option value="<%=u_id1%>"><%=iid%></option>
									<%}}%>
									<option  Value="">----------------------Group----------------------- </option>
							  	<%StudentGroupNameDao createSessionDao1= new StudentGroupNameDao();
					           	ArrayList<AllGroupBean> al1=new ArrayList<AllGroupBean>();
					           	al1=createSessionDao.selectAllDetailParticipantsGroup(userid,orgid);
					         	Iterator<AllGroupBean> iterator11 =al1.iterator();
					         	String name1="", id1="",gname="GR";
					         
				 				while(iterator11.hasNext())
									{									
				 					AllGroupBean allbean1=iterator11.next();
				 					id1=allbean1.getGroupname();
				 					String new_grp_name=allbean1.getNewGroupname();
				 					name1=allbean1.getStudentid();
				 					gname="GR"+id1;
				 					if(Viewid != null && gname.equals(Viewid)){
									%>
									<option value="<%=gname%>" selected="selected"><%=id1%></option>
									<%}else{ %>
										<option value="<%=gname%>"><%=id1%></option>
									<%}}%>
	
							  <%-- 	<%StudentGroupNameDao createSessionDao= new StudentGroupNameDao();
					           	ArrayList<AllGroupBean> al=new ArrayList<AllGroupBean>();
					           	al=createSessionDao.selectAllDetails(userid,orgid);
					         	Iterator<AllGroupBean> iterator =al.iterator();
					         	String id="",iid="";
					         	String strGroupid="GR";
					         	String  strStuid="ID";
					         	String strKeyval="";
				 				while(iterator.hasNext())
									{
				 					AllGroupBean allbean=iterator.next();			// change by jitendra						
									id=allbean.getGroupname();
									//System.out.println("group id=  "+id);
									iid=id;
									strKeyval=strGroupid+"+"+id;									 
									if(id.equals("")){
										String temp=allbean.getUsername();					//by jitendra	
										temp1 =allbean.getUserid();						// by jitendra
										String studienval=(temp+"<"+temp1+">");  
										// id=studienval;  							//by jitendra
										iid=(temp+"("+temp1+")");
										//System.out.println("In Student report jsp value=== username=== "+temp+" user id= "+temp1+" iid= "+iid);
										// strKeyval=strStuid+"-"+id;				// by jitendra
										strKeyval=strStuid+"-"+temp1;
									 }
									//System.out.println("In jsp"+strKeyval);
									%>
										<option value="<%=strKeyval%>"><%=iid%></option>
									<%}%>
 --%>				   
		</select>  
		</div>
		
		
		<div class="search_div">
		<input class="box1" type="text" id="search" placeholder="Search">
		</div>
		<div class="search_result">
		<!-- heading starts here  -->
		<div class="table_section1">
		<div class="table_outer_row">
		<table width="100%" id="tblData">
		<thead>
		 <!--  <tr height="25px"></tr>-->
			 <tr class="row_head">
		<th width="12.5%">
		SL. #
		<div class="header_stop" style="width:12.5%;">SL. #</div></th>
		<th width="12.5%">
		Enrollment ID
		<div class="header_stop" style="width:12.5%;">Enrollment ID</div>
		</th>
		<th width="12.5%">
		Student(s) Name
		<div class="header_stop" style="width:12.5%;">Student(s) Name</div>
		</th>
		<th width="12.5%">
		Address
		<div class="header_stop" style="width:12.5%;">Address</div>
		</th>
		<th width="12.5%">
		Contact No.
		<div class="header_stop" style="width:12.5%;">Contact No.</div>
		</th>
		<th width="12.5%">
		Date of Birth
		<div class="header_stop" style="width:12.5%;">Date of Birth</div>
		</th>
		<th width="12.5%">
		Email ID
		<div class="header_stop" style="width:12.5%;">Email ID</div>
		</th>
		<th width="12.5%">
		Status
		 <div class="header_stop" style="width:12.5%;">Status</div>
		</th>
		
		</tr>
		</thead>
		<tbody>
		<%
		int count=1;
		if(msg!=null){ %>
		<p><%=msg %></p>
		<%}else{ %>
		<% if(studentDetails!=null)
			 {
			Iterator<UsersInfoDomain> iterator1 =studentDetails.iterator();
			 
				while(iterator1.hasNext())
				{
					UsersInfoDomain UsersInfoDomain=iterator1.next();
					////System.out.println("Date:=== "+UsersInfoDomain.getDateofbirth());
					System.out.println("reportDomain1.getDateofbirth()="+UsersInfoDomain.getDateofbirth());   
					String dob=UsersInfoDomain.getDateofbirth();
					if(dob!=null){
					String datefromdb=UsersInfoDomain.getDateofbirth().toString();     
					//System.out.println("date from db:"+datefromdb);    
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");   
					//System.out.println("formatterf::"+formatter);
					Date date_display = (Date)formatter.parse(datefromdb); 
					//System.out.println("date_display::"+date_display); 
					SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy"); 
					//System.out.println("newFormat::"+newFormat); 
					ddmmyyyy_format = newFormat.format(date_display);
					System.out.println("ddmmyyyy_format::"+ddmmyyyy_format);
					}else
						ddmmyyyy_format="";
			 %>
			
			 <tr class="result_row_tr">
			 <td style="text-align:center;"><%=count++%></td>
				<td>
				<%=UsersInfoDomain.getUserId() %>
				
				</td>
				<td>
				<%=UsersInfoDomain.getUserName() %>
				
				</td>
				<td>
				<%=UsersInfoDomain.getAddress() %>
				
				</td>
				<td>
				<%=UsersInfoDomain.getContactno() %>
				
				</td>
				<td style="text-align:center;">
				<%=ddmmyyyy_format %>
				
				</td>
				<td>
				<%=UsersInfoDomain.getEmail() %>
				
				</td>
				<td>
				<%=UsersInfoDomain.getStatus() %>
				
				</td>
				</tr> 
		 <%}}}%>
	
	
	</tbody>
		</table>
		</div>
		</div>
		
	</div></form></div></div></div>


<%@ include file="../JSP/FooterViews.jsp"%>   
</div>

</body>
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<!--  <script src="../JS/jquery.phancy.js"></script>-->
<script>
    $('#choose').click(function (e) {
        e.stopPropagation();
        $(this).siblings('select').css('width', $(this).outerWidth(true)).toggle();
    });

    $('#options').change(function (e) {
        e.stopPropagation();
        var val = this.value || 'Select options';
        $(this).siblings('#choose').text(val);
        $(this).hide();
    });

    $('select').find('option').on({
        'mouseover': function () {
            $('.hover').removeClass('hover');
            $(this).addClass('hover');
        },
            'mouseleave': function () {
            $(this).removeClass('hover');
        }
    });
</script>
<script>

$(function() {
	$( "#demo").customScroll({ scrollbarWidth: 5 });
});
</script>
<style>
label {
    background: url(../Images/arrow.png) no-repeat;
    padding: 0 20px 0 10px;
    height: 35px!important;
    line-height: 34px;
    width: 86%;
    float: left;
    text-align: left;
    background-position: right 14px;
}
table {
    border-collapse: collapse;
}
.select_box_height {
    width: 250px;
    height: 35px;
    border: 1px solid #bbb;
    margin: auto;
    border-radius: 4px;
    background:#fff;
    height: 31px;
}

</style>
<script>
onload();
</script>
</html>