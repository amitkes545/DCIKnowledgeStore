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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <title>WelCome To KODE_DEV</title> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<link href="../CSS/kedu.css" rel="stylesheet"/>
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
.result_row_tr td input{border: none; background: none; width: 99.5%; color: #000;}
.starttime{width: 130px;}
.endtime{width: 120px;}
.duration_r{width: 120px;}
.cost_r{width: 80px;}
.result_row_tr td{border: 1px solid #000;padding: 4px;}


</style>
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
		var table = $('#tblData');
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

<script>
function clickViewId(obj){
   var  selvalue=obj;  

   document.formpartipantsview.action="/KODE_DEV/ControllerServlet/getViewDetailServlet?idvalue="+selvalue;
    document.formpartipantsview.submit();
}
   
	 



</script>
</head>
<%
		//HttpSession mess = request.getSession();
		//String msg = "";
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
		<p class="strong">Participants View Details</p>  
		 <form name="formpartipantsview" method="post">

		<select name="ViewID" placeholder="ViewName"  class="opt" id="ViewID" onchange="clickViewId(this.value);">
		<option value="">Select View</option>
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
							  	<%StudentGroupNameDao createSessionDao1= new StudentGroupNameDao();
					           	ArrayList<AllGroupBean> al1=new ArrayList<AllGroupBean>();
					           	al1=createSessionDao.selectAllDetailParticipantsGroup(userid,orgid);
					         	Iterator<AllGroupBean> iterator11 =al1.iterator();
					         	String name1="", id1="",gname="GR";
					         
				 				while(iterator11.hasNext())
									{									
				 					AllGroupBean allbean1=iterator11.next();
				 					id1=allbean1.getGroupname();
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
		
		
		<div class="search_div">
		<input class="box1" type="text" id="search" placeholder="Search">
		</div>
		<div class="search_result">
		<!-- heading starts here  -->
		<table width="100%" id="tblData">
		 <tr height="25px"></tr>
			 <tr class="row_head">

		<td>
		UserID
		</td>
		<td>
		Name
		</td>
		<td>
		Address
		</td>
		<td>
		ContactNo
		</td>
		<td>
		DateofBirth
		</td>
		<td>
		Email
		</td>
		<td>
		 Status
		</td>
		
		</tr>
		<%if(msg!=null){ %>
		<p><%=msg %></p>
		<%}else{ %>
		<% if(studentDetails!=null)
			 {
			Iterator<UsersInfoDomain> iterator1 =studentDetails.iterator();
			 
				while(iterator1.hasNext())
				{
					UsersInfoDomain UsersInfoDomain=iterator1.next();
					////System.out.println("Date:=== "+UsersInfoDomain.getDateofbirth());
			 %>
			
			 <tr class="result_row_tr row">
				<td>
				<input type="text" name="sessionId<%=UsersInfoDomain.getUserId() %>" id="sessionId" value="<%=UsersInfoDomain.getUserId() %>" class="<%=UsersInfoDomain.getUserId() %>" readonly="readonly">
				</td>
				<td>
				<input type="text" name="sessionName<%=UsersInfoDomain.getUserName() %>" value="<%=UsersInfoDomain.getUserName() %>" class="<%=UsersInfoDomain.getUserName() %>" readonly="readonly">
				</td>
				<td>
				<input type="text" name="category<%=UsersInfoDomain.getAddress() %>" value="<%=UsersInfoDomain.getAddress() %>" class="<%=UsersInfoDomain.getAddress() %>" readonly="readonly">
				</td>
				<td>
				<input class="starttime<%=UsersInfoDomain.getContactno() %>" type="text" value="<%=UsersInfoDomain.getContactno() %>" name="startTime<%=UsersInfoDomain.getContactno() %>" readonly="readonly">
				</td>
				<td>
				<input class="endtime<%=UsersInfoDomain.getDateofbirth() %>" type="text" value="<%=UsersInfoDomain.getDateofbirth() %>" name="endTime<%=UsersInfoDomain.getDateofbirth() %>" readonly="readonly">
				
				</td>
				<td>
				<input class="duration_r <%=UsersInfoDomain.getEmail() %>" type="text" value="<%=UsersInfoDomain.getEmail() %>" name="duration<%=UsersInfoDomain.getEmail() %>" readonly="readonly">
				</td>
				<td>
				<input class="cost_r <%=UsersInfoDomain.getStatus() %>" type="text" value="<%=UsersInfoDomain.getStatus() %>" name="costOfsession<%=UsersInfoDomain.getStatus() %>" readonly="readonly">
				</td>
				<%-- <td>
				<input type="text" class="<%=UsersInfoDomain.getSessionId() %>" value="<%=UsersInfoDomain.getRecipientType() %>" name="recipientType<%=UsersInfoDomain.getSessionId() %>" readonly="readonly">
				<select class="<%=UsersInfoDomain.getSessionId() %>" name="status<%=UsersInfoDomain.getRecipientType() %>">
				<option value="<%=UsersInfoDomain.getRecipientType() %>"><%=UsersInfoDomain.getRecipientType() %></option>
				<%if(UsersInfoDomain.getStatus().equalsIgnoreCase("Available")){ %>
				<option value="cancel">Cancel</option>
				<%} else  if(UsersInfoDomain.getStatus().equalsIgnoreCase("Cancel")){%>
				<option value="available">Available</option>
				<%} %>
				</select>
				</td>
				<td>
				<select class="<%=UsersInfoDomain.getSessionId() %>" name="status<%=UsersInfoDomain.getSessionId() %>">
				<option value="<%=UsersInfoDomain.getStatus() %>"><%=UsersInfoDomain.getStatus() %></option>
				<%if(UsersInfoDomain.getStatus().equalsIgnoreCase("Available")){ %>
				<option value="cancel">Cancel</option>
				<%} else  if(UsersInfoDomain.getStatus().equalsIgnoreCase("Cancel")){%>
				<option value="available">Available</option>
				<%} %>
				</select>
				<input type="text" class="<%=UsersInfoDomain.getSessionId() %>" value="<%=UsersInfoDomain.getStatus() %>" name="status<%=UsersInfoDomain.getSessionId() %>">
				</td> --%>
				<%-- <td>
				<a href="#">
				<input id="<%=UsersInfoDomain.getUserId() %>" type="checkbox" name="checkboxGroup" value="<%=UsersInfoDomain.getUserId() %>"/>
				</a>
				</td> --%>
				</tr> 
		 <%}}}%>
	
	
	
		</table>  
	</div></form></div></div></div>


<%@ include file="../JSP/FooterViews.jsp"%>   
</div>

</body>
</html>