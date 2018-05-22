<%@ page errorPage="../JSP/error.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.kds.KODE_DEV.domain.*"%>
     <%@page import="com.kds.KODE_DEV.dao.*"%>
  	 <%@page import="java.util.Iterator" %>
     <%@page import="java.util.ArrayList" %>
     <%@page import=" java.io.File" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/Validatestudent.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<!--  <link href="../CSS/kedu.css" rel="stylesheet"/>
 for country code select css -->
<link rel="stylesheet" href="../CSS/intlTelInput.css">
<script src="../JS/jquery1.11.3.js"></script>
<script src="../JS/country.js" type="text/javascript"></script>
<!--  added  for date picker -->
<!-- <script src="../JS/jqueryuidate.js"></script>
<script src="../JS/jquerydate.js"></script> -->
<!-- <link rel="stylesheet" href="../CSS/datepickerui.css"> -->
<!--  needs to be remove -->
<!-- <script src="../JS/jquery-1.10.2.js"></script> -->
<script src="../JS/MessageFadeOut.js"></script>
<link href="../CSS/table_scroll.css" rel="stylesheet"/>
<link href="../CSS/design-common.css" rel="stylesheet"/>
 <link href="../CSS/notification-new.css" rel="stylesheet"></link> 
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> 
<style type="text/css">
.tabmain
{
    position: absolute;
   right: 38%;
    top: 164px;
    width: 315px;
    z-index: 1;
    border: 1px solid #c2c2c2;
    border-bottom: none;
    BORDER-RADIUS: 5PX;
    
}
.tabmain ul li{float: left; width: 48.9%;list-style: none; text-align: center; padding: 9.3px 0.1px;border-radius: 5px; cursor: pointer;}
.ss{background: FCF7F7;
border: 2px solid #c2c2c2;
border-bottom: none;}
/*
.no_search_result{

align: center;
padding: 0px 10px;
margin-left: 75px;
margin-bottom: 10px;
height: 148px !important;
border: 1px solid #c2c2c2;
width: 400px !important;
overflow-y: scroll;}
*/
.headere{

align: center;
padding: 0px 10px;
margin-left: 75px;
margin-bottom: 10px;
//border: 1px solid #c2c2c2;
width: 400px !important;
}

.search_result{
padding: 0px 10px;
margin-bottom: 25px;
max-height: 150px;
overflow-y: scroll;
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

.tab2ind{display: none;}
/* .tab2second{display: block !important;} */

.postpon_mod{
width: 92% !important;
max-height: 400px !important;
    box-shadow: 5px 5px 5px 5px #FFF;
    margin: 15px auto 0px;
    padding: 15px px 0px 0px;
    border: 3px solid #C2C2C2;
    background-color: #FCF7F7;
    border-radius: 4px;
    opacity:1;
    margin-top: 72px;
    overflow-y: scroll;
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
padding: 3px !important;
height:31px;
border: 1px solid #C2C2C2;
font-family: regular;
border-radius: 4px;
    z-index: 999;
    margin-left: 411px;
    margin-bottom: 12px;
}
.tabsbg
{
background: #fff;
border-radius: 5px;
}

.tab1bg1{    background-color: #FCF7F7;
    border-radius: 4px;
    /* opacity: 0.9; */
    border: 3px solid #C2C2C2;
    border-bottom: none;
    color:#000!important;
    }
    
.tab2bg2{    background-color: #FCF7F7;
    border-radius: 4px;
    /* opacity: 0.9; */
    border: 3px solid #C2C2C2;
    border-bottom: none;
    color:#000!important;
    }
    
.addbg{
    background-color: #FCF7F7 !important;
    border-radius: 4px;
    border: 3px solid #C2C2C2;
    border-bottom: none;
    color:#000!important;
    }
.addbg1{
    background-color: #fff !important;
    padding: 9px 0px !important;
    }
        
select{
color: #000;
}
.box1
{
width: 250px!important;
    padding: 7px!important;
    border: 1px solid #c2c2c2;
    border-radius: 4px;
    position: relative;
    left: 120px;
    top:1px;
    
}
.row_head{
    background: none repeat scroll 0% 0% #009AE1;
    color: #FFF;
    font-weight: bold;
    }
    . td{
    padding: 3px;
    }	
.row_head .col_2{float: left; padding: 0px 15px;}

.row_head_grp{
    background: none repeat scroll 0% 0% #009AE1;
    color: #FFF;
    font-weight: bold;
    }
.row_headgrp td{
    padding: 3px;
    }

.row_result .col_2{float: left; padding: 0px 15px;}
.result_row_tr td input{border: none; background: none; width: 99.5%; color: #000;}

.starttime{width: 130px;}
.endtime{width: 120px;}
.duration_r{width: 120px;}
.cost_r{width: 80px;}
.result_row_tr td{padding: 4px;}
.result_row_tr row{border: 1px solid #000;padding: 4px;}
.btnscolor{background: #1d87da;
    border: none;
    color: #fff;
    padding: 5px 15px;
    margin-top: 10px;
    margin-right: 10px;
    margin-left: 10px;}

.additional_result_row_tr row{border: 1px solid #000;padding: 4px; display: none;}
.additional_result_row_tr td{padding: 4px; border-right:1px solid #bbb;}
.additional_result_row_tr td input{border: none; background: none; width: 99.5%; color: #000;}

.btnscolor{background: #1d87da;
    border: none;
    color: #fff;
    padding: 5px 15px;
    margin-top: 10px;
    margin-right: 10px;
    margin-left: 10px;}

.btnsaction{background: #1d87da;
    border: none;
    color: #fff;
    padding: 5px 15px;
    margin-top: 1px;
    margin-right: 10px;
    margin-left: 10px;}

.btnscancel{background: #1d87da;
    border: none;
    color: #fff;
    padding: 5px 15px;
    margin-top: 10px;
    margin-right: 10px;
    margin-left: 10px;}

.btnsdelete{background: #1d87da;
    border: none;
    color: #fff;
    padding: 5px 15px;
    margin-top: 10px;
    margin-right: 10px;
    margin-left: 10px;}
        
.editchkbox{background: #FCF7F7;
    border: none;
    color: #ggg;
    padding: 5px 15px;
    display: none;
    }

.editchkboxadditional{background: #FCF7F7;
    border: none;
    color: #ggg;
    padding: 5px 15px;
    display: none}
    
.icondelete{width: 20px!important;
background: #FCF7F7;
}

.iconadd{width: 20px!important;
background: #FCF7F7;
}         

.iconminus{width: 20px!important;
background: #FCF7F7;
}       

    .tab1 ul li:first-child
    {
    background: #FCF7F7;
    }
        .tab1 ul li:last-child
    {
    background: #fff;
    }
    
    .tab2 ul li:first-child
    {
    background: #fff !important;
    border: none;
    }
        .tab2 ul li:last-child
    {
    background: #FCF7F7;
    border: 3px solid #c2c2c2;
    border-bottom: none;
    }
</style>
<script>
$(document).ready(function() {
$(function(){  
$('.textnumber').keydown(function(e) {
      if (e.shiftKey || e.ctrlKey || e.altKey) {
        e.preventDefault();
      } else {
        var key = e.keyCode;
        if (!((key == 8) || (key == 32) || (key == 190) || (key == 46) || (key >= 48 && key <= 57) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
          e.preventDefault();
        }
      }
    });
});
});
</script>



<script>
function onload(){
	//("in fff");
	<% String ViewID=(String)request.getAttribute("ViewID");
	//System.out.println("ViewID="+ViewID);
	if(ViewID!=null && ViewID.length()>0) {
	 %> 
	
	 selectBox = document.getElementById("ViewIDG");
	// alert(selectBox);
	 for(var i=0;i<selectBox.options.length;i++){
		 if(selectBox.options[i].value == '<%=ViewID%>')
		{
			// alert("in if");
 selectBox.options[i].selected=true;
 

			 break;
		 }
	 }			
		<%}%>
}
function clickEdit()
{
	//alert("in");
	document.getElementById("action").style.display='block';
	}
function clickViewIdUser(obj){
   var  selvalue=obj;  
   document.formpartipantsviewUser.action="/KODE_DEV/ControllerServlet/ParticipantModifyUserDetailsService?idvalue="+selvalue;
    document.formpartipantsviewUser.submit();
}
function clickSave(){
	//alert("in clickSave()");
	   document.formpartipantsviewGroup.action="/KODE_DEV/ControllerServlet/ParticipantUpdateGroupDetailsService?action=update";
	    document.formpartipantsviewGroup.submit();
}
function clickSaveGroup(){
	var tot=document.getElementsByName("checkboxGroup").length;
	var checked=0;
	var view=document.getElementById("ViewIDG").value;
	var newgroupname=document.getElementById("newgroupname").value; 
		$('#newgroupname').css({   
		"border": "", 
		});
		if(view==newgroupname.trim() && newgroupname!='') 
		{  
			  
			$('#newgroupname').css({  
				"border": "1px solid red",
				}); 
			return;
			} 
		/* else if(newgroupname=='') 
		{ 
			
			$('#newgroupname').css({ 
				"border": "1px solid red", 
				});  
			return; 
			} */
		
	//alert(document.getElementsByName("checkboxGroup").length);
	for(var i=0;i<tot;i++)
	{
		if(document.formpartipantsviewGroup["checkboxGroup"][i].checked)
			checked++;
	}
	if(checked < 2){
		alert("There must be atleast 2 members in the group!");
		return;
	}
	//alert("checked:"+checked);
	   document.formpartipantsviewGroup.action="/KODE_DEV/ControllerServlet/ParticipantUpdateGroupDetailsService?action=save";
	   document.formpartipantsviewGroup.submit();
}
function clickDeleteGroup()
{
	//alert("in");
	var view=document.getElementById("ViewIDG").value;
	//alert(view);
	if(view=="")
		alert("Please Select Group");
	else{
	 document.formpartipantsviewGroup.action="/KODE_DEV/ControllerServlet/ParticipantUpdateGroupDetailsService?action=delete";
	    document.formpartipantsviewGroup.submit();	
	}
}
$(document).ready(function() {
	  $("#dob").datepicker({
		   changeMonth : true,
		   changeYear : true,
		   maxDate : '-1d',
		  // minDate : '-60d',
		   dateFormat: "yy-mm-dd",
		  });
	  });
$(function() {
    $( "#dob" ).datepicker();
  });
$(document).ready(function() { 
	/* $('.btnsaction').hide(); */
	//$('.btnscancel').hide();
	//$('.editchkboxadditional').hide();
	//$('.editchkbox').attr("checked",false);
	$('.editchkbox').hide();
	//$('.icondelete').hide();
	$('.btnscolor').hide();
	//$('.btnsdelete').hide();
	$(".btnsaction").click(function() {
		var id = $(this).attr("id");
		//alert(id);
		/*if(id=='editButton'){
			//$('.editchkbox').show();
			$('.btnsaction').hide();
			$('.btnscancel').show();
		}
		if(id=='removeButton'){
			$('.icondelete').show();
			$('.btnsaction').hide();
			$('.btnscancel').show();
			$('.btnsdelete').show();
			$('.no_search_result').show();
			$('.editchkbox').attr("checked",true);
			$('.userid').attr("disabled",false);
		} */
		});
	$(".btnscancel").click(function() {
			//$('.editchkbox').hide();
			//$('.btnsaction').show();
			//$('.btnscancel').hide();
			//$('.btnscolor').hide();
			//$('.btnsdelete').hide();
			//$('.icondelete').hide();
			$('.result_row_tr').show();
			$('.editchkbox').prop("checked",true);
			$('.editchkboxadditional').prop("checked",false);
			$('.additional_result_row_tr').hide();
		});
	
	$(".icondelete").click(function() {
		var id = $(this).attr("id");
		var myid='my'+id;
		var rowid='row'+id;
		$('#' + myid).prop('checked',false);
		$('#' + rowid).hide();
		$('#' + 'restrowadd'+ id).show();
		return false;
	});

	$(".iconadd").click(function() {
		var id = $(this).attr("id");
		var idnum= id.substring(9);
		var myid='my'+idnum;
		$('#' + 'restrowadd'+ idnum).hide();
		$('#' + myid).prop('checked',true);
		$('#' + idnum).show();
		$('#' + 'row' + idnum).show();
		return false;
	});
	
	$('.row input').each(function()
			{
			$(this).attr("disabled",true);
			});
	$(".row a input").attr("disabled",false);
	
	$(".row a input").click(function() {
		var id = $(this).attr("id");
		if($('#' + id).is(":checked"))
		{
		$("." + id).attr("disabled",false);
		
		$('.btnscolor').show();
		}
	else{
		$("." + id).attr("disabled",true);	
		$('.btnscolor').hide();
	}
		btnscolor;
	});
});




$(document).ready(function() {
    $("#updateButtonUsermodify").click(function(e) {
        var isValid = true;
        $('input[type="text"].required').each(function() {
            if ($.trim($(this).val()) == '') {
                isValid = false;
                $(this).css({
                    "border": "1px solid red",
                    //"background": "#FFCECE"
                });
            }
            else {
                $(this).css({
                    "border": "",
                   // "background": ""
                });
            }
        });
        
        $('.sel select.required').each(function() {
            if ($.trim($(this).val()) == '') {
                isValid = false;
                $(this).css({
                    "border": "1px solid red",
                    //"background": "#FFCECE"
                });
            }
            else {
                $(this).css({
                    "border": "",
                   // "background": ""
                });
            }
        });
      
        if (isValid == false){
        	
            e.preventDefault();
           
        }
        else {
        	 alert('User! Thank you for submitting');
        	 $("#formpartipantsviewUser").submit();
        }
           
    });
});       
</script>
<script type="text/javascript">
$(document).ready(function() {
	$("#phone").keydown(function (e) {
    	
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
            (e.keyCode == 65 && ( e.ctrlKey === true || e.metaKey === true ) ) || 
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 return;
        }
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105))
        {
            e.preventDefault();
        }
    });
});

$(document).ready(function() {
	$('input[type="text"].required').each(function() {
        if ($.trim($(this).val()) == '') {
            isValid = false;
            $(this).css({
                "border": "1px solid red",
                //"background": "#FFCECE"
            });
        }
        else {
            $(this).css({
                "border": "",
               // "background": ""
            });
        }
    });
    $('input[type="email"].required').each(function() {
        if ($.trim($(this).val()) == '') {
            isValid = false;
            $(this).css({
                "border": "1px solid red",
                //"background": "#FFCECE"
            });
        }
        else {
            $(this).css({
                "border": "",
               // "background": ""
            });
        }
    });
});
</script>
<script>
function clickViewId(obj){
   var  selvalue=obj;  
   document.formpartipantsviewGroup.action="/KODE_DEV/ControllerServlet/ParticipantModifyUserGroupDetailsService?idvalue="+selvalue;
    document.formpartipantsviewGroup.submit();
}

$(document).ready(function() { 
	$('.btnscolor').hide();
	$('.row input').each(function()
			{
			$(this).attr("disabled",true);
			});
	$(".row a input").attr("disabled",false);
	$(".userid").attr("disabled",false);
	$(".row a input").click(function() {
		var id = $(this).attr("id");
		if($('#' + id).is(":checked"))
		{
		$("." + id).attr("disabled",false);
		
		$('.btnscolor').show();
		}
	else{
		$("." + id).attr("disabled",true);	
		$('.btnscolor').hide();
	}
		btnscolor
	});
});
$(document).ready(function() {
    $("#updateButton").click(function(e) {
    	
        var isValid = true;
        $('input[type="text"].required').each(function() {
            if ($.trim($(this).val()) == '') {
                isValid = false;
                $(this).css({
                    "border": "1px solid red",
                    //"background": "#FFCECE"
                });
            }
            else {
                $(this).css({
                    "border": "",
                   // "background": ""
                });
            }
        });
        
        $('.sel select.required').each(function() {
            if ($.trim($(this).val()) == '') {
                isValid = false;
                $(this).css({
                    "border": "1px solid red",
                    //"background": "#FFCECE"
                });
            }
            else {
                $(this).css({
                    "border": "",
                   // "background": ""
                });
            }
        });
      
        if (isValid == false){
        	e.preventDefault();
        }
        else {
        	// alert('Group! Thank you for submitting');
        	 $("#formpartipantsviewGroup").submit();
        }
           
    });
});       

$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
});
</script>
</head>
<%ArrayList<UsersInfoDomain> studentDetails=null,studentDetails1=null,studentDetails2=null,reststudentDetails2=null ;
		//HttpSession mess = request.getSession();
		String message = (String) session.getAttribute("MsgValue");
		String orgid=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		String responseErrMsgValue=(String)request.getAttribute("responseErrMsg");
		if(username==null)
			response.sendRedirect("../JSP/error.jsp");
		String temp1="";
		int existi=0;
		studentDetails=(ArrayList<UsersInfoDomain>)request.getAttribute("studentdetail");
		studentDetails2=(ArrayList<UsersInfoDomain>)request.getAttribute("studentdetail1");
		reststudentDetails2=(ArrayList<UsersInfoDomain>)request.getAttribute("reststudentdetail1");
	%>
	<%    
UsersInfoDomain uid=new UsersInfoDomain();
uid.setCreatedBy(session.getAttribute("userId").toString());
uid.setOrgId(session.getAttribute("orgId").toString());
String responseMsgValue=(String)request.getAttribute("responseMsg");
String Tabmsg=request.getParameter("tabvalue");
String msg=request.getParameter("msg"); %>
<body>
<div class="container" style="position:static;">
		<jsp:include page="../JSP/headers.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>

		<div style="clear: both;"></div>
		<div>
		
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<!-- <div style="clear: both;"></div> -->
	<%-- 	<div class="tabmain <%=Tabmsg%>">
		
		</div> --%>
		<%-- <div class="postpon_mod <%=Tabmsg%>ind" id="first_part">
		<div style="text-align: center;"> --%>
		<%-- <form name="formpartipantsviewUser" id="formpartipantsviewUser" action="/KODE_DEV/ControllerServlet/ParticipantUpdateUserDetailsService" method="post">
   <div>
   <div>  
   <div>
		<select name="ViewID" placeholder="ViewName"  class="opt" id="ViewID" onchange="clickViewIdUser(this.value);">
		<option value="">Select View</option>
		<option value="All">All</option>
							  	<%StudentGroupNameDao createSessionDao= new StudentGroupNameDao();
					           	ArrayList<AllGroupBean> al=new ArrayList<AllGroupBean>();
					           	al=createSessionDao.selectAllDetailParticipantsIndividual(userid,orgid);
					         	Iterator<AllGroupBean> iterator =al.iterator();
					         	String name="", id="",iid="";
				 				while(iterator.hasNext())
									{									
				 					AllGroupBean allbean=iterator.next();
				 					id=allbean.getUserid();
				 					name=allbean.getUsername();
				 					iid=name+" ("+id+")";
									%>
										<option value="<%=id%>"><%=iid%></option>
									<%}%>
		</select>  
		<div class="search_div">
		<input class="box1" type="text" id="search" placeholder="Search">
		</div>
		<div class="search_result">
		<!-- heading starts here  -->
		<table width="100%" id="tblData">
		 <tr height="5px"></tr>
			 <tr class="row_head">
		<td>UserID</td><td>Name</td>
		<td>Address</td><td>ContactNo</td>
		<td>DateofBirth</td><td>Email</td>
		<td>Status</td><td>Edit</td>
		</tr>
		<%if(msg!=null){ %>
		<p><%=msg %></p>
		<%}else if(studentDetails!=null)
			 {
			int i=0, j=0;
			Iterator<UsersInfoDomain> iterator1 =studentDetails.iterator();
				while(iterator1.hasNext())
				{
					UsersInfoDomain UsersInfoDomain=iterator1.next();
					i++;
		 %>
			 <tr class="result_row_tr row">
				<td>
				<input type="text" name="userid<%=UsersInfoDomain.getUserId() %>" id="sessionId" value="<%=UsersInfoDomain.getUserId() %>" class="my<%=i %>" readonly="readonly">
				</td>
				<td>
				<input type="text" name="name<%=UsersInfoDomain.getUserId()%>" value="<%=UsersInfoDomain.getUserName() %>" class="my<%=i %>">
				</td>
				<td>
				<input type="text" name="address<%=UsersInfoDomain.getUserId()%>" value="<%=UsersInfoDomain.getAddress() %>" class="my<%=i %>">
				</td>
				<td>
				<input type="text" name="contact<%=UsersInfoDomain.getUserId()%>" id="phone" value="<%=UsersInfoDomain.getContactno() %>" class="my<%=i %>" maxlength="10">
				</td>
				<td>
				<input type="text" name="dob<%=UsersInfoDomain.getUserId() %>" id="dob" value="<%=UsersInfoDomain.getDateofbirth() %>" class="my<%=i %>" readonly>
				</td>
				<td>
				<input type="email" name="email<%=UsersInfoDomain.getUserId() %>" value="<%=UsersInfoDomain.getEmail() %>" class="my<%=i %>">
				</td>
				<td>
				<input class="my<%=i %>" type="text" value="<%=UsersInfoDomain.getStatus() %>" name="costOfsession<%=UsersInfoDomain.getStatus() %>">
				<select class="my<%=i %>" name="status<%=UsersInfoDomain.getUserId() %>">
				<option value="<%=UsersInfoDomain.getStatus() %>"><%=UsersInfoDomain.getStatus() %></option>
				
				<% System.out.println("Status="+UsersInfoDomain.getStatus()+";"); 
				String participantStatus ="";
				if(UsersInfoDomain.getStatus() == null) 
					participantStatus ="";
				else 
				participantStatus =UsersInfoDomain.getStatus();
				%>
				<% if(participantStatus.equalsIgnoreCase("Active")){ %>
				<option value="Active" selected="selected">Active</option>
				<option value="Inactive">Inactive</option>
				<%} else if(participantStatus.equalsIgnoreCase("Inactive")){%>
				<option value="Active">Active</option>
				<option value="Inactive" selected="selected">Inactive</option>
				<%}else{ %>
				<option value="Active">Active</option>
				<option value="Inactive" selected="selected">Inactive</option>
				<%} %>
				</select>
				</td>
				<td>
				<a href="#">
				<input id="my<%=i %>" type="checkbox" name="checkboxGroup" value="<%=UsersInfoDomain.getUserId()%>"/>
				</a>
				</td>
				</tr> 
		 <%}}%>
	</table>  
		<a>
		<input class="btnscolor" type="submit" name="updateButton" onclick="clickSave()" id="updateButtonUsermodifyUsermodify" value="Update" /></a> 
</div>
</div>
</div>
</div>
</form> --%>
<!-- </div>
</div> -->
<%
%>
<!-- second part starts here -->
	
	<div class="postpon_mod <%=Tabmsg%>second">
	<%-- <%if (responseMsgValue!= null) {  %>
<table><tr><td><p class="autohide"></p>
</td></tr></table>					
					<%}%> --%>
					
					
					<%if (responseMsgValue!= null) {  %>
	<p class="success"><%=responseMsgValue%></p></div>
	<%}	else if (responseErrMsgValue!= null) {  %>
	<p class="failure"><p><%=responseErrMsgValue%></p></div>
						
						<%}					
					%>				
	<p class="strong">Modify Group</p>
	<form name="formpartipantsviewGroup" id="formpartipantsviewGroup"  method="post">
   <div id="godl">
   <div>  
		 <div style="text-align: center;">
		<select name="ViewID" placeholder="ViewName"  class="opt" id="ViewIDG" onchange="clickViewId(this.value);">
		<option value="">Select Group</option>
							  	<%StudentGroupNameDao createSessionDao1= new StudentGroupNameDao();
					           	ArrayList<AllGroupBean> al1=new ArrayList<AllGroupBean>();
					           	al1=createSessionDao1.selectAllDetailParticipantsGroup(userid,orgid);
					         	Iterator<AllGroupBean> iterator11 =al1.iterator();
					         	String name1="", id1="",iid1="";
				 				while(iterator11.hasNext())
									{									
				 					AllGroupBean allbean1=iterator11.next();
				 					id1=allbean1.getGroupname();
				 					String grp=allbean1.getNewGroupname();
				 					name1=allbean1.getStudentid();
									%>
										<option value="<%=id1%>"><%=id1%></option>
									<%}%>
		</select>  
		
		 <input class="box1 textnumber" type="text" id="newgroupname" name="newgroupname" maxlength="50" placeholder="Enter to rename group"  style="margin-right:130px;">
		
									
										<div class="grout_table_outer">
										<div class="no_search_result table_group1" id="reee">
										<h4>Existing group members</h4>
										<div class="table_section1" style="margin-top:10px;">
		                                 <div class="table_outer_row" style="height:150px;">
											<table width="100%" id="tblData">
											<thead>
											<tr>
												<th width="40%" style="font-size:1px;">User ID<div class="header_stop" style="width:40%;">User ID</div></th>
												<th width="40%" style="font-size:1px;">Name<div class="header_stop"style="width:40%;">Name</div></th>
												<th width="20%" style="font-size:1px;">Action<div class="header_stop"style="width:20%;">Action</div></th>
												<%
													/* int count=1; */
													studentDetails1 = (ArrayList<UsersInfoDomain>) request.getAttribute("studentdetail1");
													//System.out.println("studentDetails1="+studentDetails1);
													//System.out.println("studentDetails2="+studentDetails2);
													if (msg != null) {
												%>
												<p><%=msg%></p>

												<%
													} else if (studentDetails2 != null) {
														//	System.out.println("in if");
														int i = 0, j = 0;
														Iterator<UsersInfoDomain> iterator2 = studentDetails2.iterator();
														while (iterator2.hasNext()) {
															//	System.out.println("in while");
															UsersInfoDomain userInfoDom = iterator2.next();
															i++;
												%>
												</tr>
												</thead>
												<tr class="result_row_tr row" id="row<%=i%>">
													<%-- <td style="width:26px;"><%=count++%></td> --%>
													<td><input type="text"
														name="userid<%=userInfoDom.getUserId()%>" id="sessionId"
														value="<%=userInfoDom.getUserId()%>" class="userid"
														readonly="readonly"></td>
													<td><input type="text"
														name="name<%=userInfoDom.getUserId()%>"
														value="<%=userInfoDom.getUserName()%>" class="my<%=i%>">
													</td>
													<td align="center"><a style="width: 100%;text-align: center;float: left;">
													 <input class="icondelete"
															id="<%=i%>" type="image" name="icondel"
															onclick="iconclick()"
															value="<%=userInfoDom.getUserId()%>"
															src="../Image/cross1.png" /> <input class="editchkbox"
															id="my<%=i%>" type="checkbox" checked="checked"
															name="checkboxGroup" value="<%=userInfoDom.getUserId()%>" />
													</a></td>
												</tr>
												<%
													} //while loop end
														existi = i;
													} //if studentlist2 end
														//}  //else msg!=null end
														// List of Remaining students for existing table starts from here
														//System.out.println("studentDetails1="+studentDetails1);
														//	System.out.println("reststudentDetails2="+reststudentDetails2);
													if (reststudentDetails2 != null) {
														int i = existi, j = 0;
														Iterator<UsersInfoDomain> iterator2 = reststudentDetails2.iterator();
														while (iterator2.hasNext()) {
															//System.out.println("in 2 while");
															UsersInfoDomain userInfoDom = iterator2.next();
															i++;
												%>
												<tr class="additional_result_row_tr row" id="row<%=i%>">

													<td><input type="text"
														name="userid<%=userInfoDom.getUserId()%>" id="sessionId"
														value="<%=userInfoDom.getUserId()%>" class="userid"
														readonly="readonly"></td>
													<td><input type="text"
														name="name<%=userInfoDom.getUserId()%>"
														value="<%=userInfoDom.getUserName()%>"
														class="restmy<%=i%>"></td>
													<td><a> <input class="icondelete" id="<%=i%>"
															type="image" name="icondel" onclick="iconclick()"
															value="<%=userInfoDom.getUserId()%>"
															src="../Image/cross1.png" /> <input
															class="editchkboxadditional" id="my<%=i%>"
															type="checkbox" name="checkboxGroup"
															value="<%=userInfoDom.getUserId()%>" />
													</a></td>
												</tr>
												<script type="text/javascript">
$(document).ready(function() {
 $('#'+'row<%=i%>').hide();
  });
</script>

												<%
													} //while loop end
													} //if reststudentlist2 end

													// List of remaining students for existing table end here
												%>
											</table>
										</div>
										</div>
										</div>
									
										<%
											// List of Remaining students for addition table start from here
										%>



									
										<div class="no_search_result table_group2" id="reee">
										<h4>Select user from below list to add in group</h4>
										<div class="table_section1" style="margin-top:10px;">
		<div class="table_outer_row" style="height:150px;">
											<table width="100%" id="tblData">
											<thead>
												<tr class="row_head">
													<!-- <td>Sl #</td> -->
													<th width="40%">User ID
													<div class="header_stop" style="width:40%;">User ID</div></th>
													<th width="40%">Name
													<div class="header_stop" style="width:40%;">Name</div></th>
													<!-- 	<td>Address</td><td>ContactNo</td>
		<td>DateofBirth</td><td>Email</td>
		<td>Status</td>  -->
													<th width="20%">Action
													<div class="header_stop" style="width:20%;">Action</div></th>
												</tr>
												<thead>
												<%
													//int count1=1;
													//------------ Current goup members list for addition table start here

													if (studentDetails2 != null) {
														int i = 0, j = 0;
														Iterator<UsersInfoDomain> iterator2 = studentDetails2.iterator();
														while (iterator2.hasNext()) {
															//System.out.println("in 3 while");
															UsersInfoDomain userInfoDom = iterator2.next();
															i++;
												%>
												<tr class="result_row_tr row" id="restrowadd<%=i%>">

													<td><input type="text"
														name="userid<%=userInfoDom.getUserId()%>" id="sessionId"
														value="<%=userInfoDom.getUserId()%>" class="userid"
														readonly="readonly"></td>
													<td><input type="text"
														name="name<%=userInfoDom.getUserId()%>"
														value="<%=userInfoDom.getUserName()%>" class="my<%=i%>">
													</td>
													<td style="text-align:center;"><a> <input class="iconadd"
															id="restmyadd<%=i%>" type="image" name="iconadd"
															onclick="iconaddclick()"
															value="fdfdfd<%=userInfoDom.getUserId()%>"
															src="../Image/plus.png" /> <!-- <input class="editchkbox" id="my<%=i%>" type="checkbox" checked="checked" name="checkboxGroup" value="<%=userInfoDom.getUserId()%>"/> -->
													</a></td>
												</tr>
												<script type="text/javascript">
$(document).ready(function() {
 $('#'+'restrowadd<%=i%>').hide();
  });
</script>
												<%
													} //while loop end
														existi = i;
													} //if studentlist2 end
														//------------ current group members list end
													if (reststudentDetails2 != null) {
														int i = existi, j = 0;
														Iterator<UsersInfoDomain> iterator2 = reststudentDetails2.iterator();
														while (iterator2.hasNext()) {
															UsersInfoDomain userInfoDom = iterator2.next();
															i++;
															//System.out.println(i+".userid="+userInfoDom.getUserId());
												%>
												<tr class="result_row_tr row" id="restrowadd<%=i%>">
													<%-- <td>
		 <%=count1++%></td> --%>
													<td><input type="text"
														name="userid<%=userInfoDom.getUserId()%>" id="sessionId"
														value="<%=userInfoDom.getUserId()%>" class="userid"
														readonly="readonly"></td>
													<td><input type="text"
														name="name<%=userInfoDom.getUserId()%>"
														value="<%=userInfoDom.getUserName()%>"
														class="restmy<%=i%>"></td>
													<td><a> <input class="iconadd"
															id="restmyadd<%=i%>" type="image" name="iconadd"
															onclick="iconaddclick()"
															value="<%=userInfoDom.getUserId()%>"
															src="../Image/plus.png" /> <!-- <input class="editchkbox" id="restmy<%=i%>" type="checkbox" name="checkboxGroup" value="<%=userInfoDom.getUserId()%>"/>-->
													</a></td>
												</tr>
												<%
													} //while loop end
													} //if reststudentlist2 end
													// List of remaining students for addition table end here
												%>
											</table>
											</div>
											</div>
										</div>
										</div>
										<!--<a><input class="btnscolor" type="submit" name="updateButton" onclick="clickSave()" id="updateButton" value="Update" /></a>-->
										<!--<a><input class="btnsaction" type="hidden" name="editButton" onclick="clickEdit()" id="editButton" value="Edit" /></a>-->
										
							</table>
						</div>
						<div class="multiple_button">
							<a><input class="btnsaction" type="button"
											name="deleteButton" onclick="clickDeleteGroup()"
											id="deleteButton" value="Delete" style="" /></a>
										<!-- <a><input class="btnsaction" type="button" name="removeButton" onclick="clickRemove()" id="removeButton" value="Add \ Remove" /></a> -->
										<a><input class="btnsdelete" type="button"
											name="deleteButton" onclick="clickSaveGroup()"
											id="deleteButton" value="Update" /></a>
											<a><input
											class="btnscancel" type="button" name="cancelButton"
											onclick="clickCancel()" id="cancelButton" value="Reset" /></a>
						</div>
</div>
</div>
</form>	
</div>
	

     <%@ include file="../JSP/FooterViews.jsp"%>
	</div>
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
						if(found == true)$('tr').show();else $(row).hide();
					}
				});
			}
		</script>
</body>
<script>
onload();
</script>
<script type="text/javascript">
$(document).ready(function () { 

	/* $("#second_part").hide(); */
	$("#ass_tab").addClass("addbg");
	$("#second_part").css("display","none");
	$(".tab2second").show();
	
	
	$("#ass_tab").click(function() {
		$(this).addClass("addbg");
		$("#assi_tab").removeClass("addbg");
		$("#ass_tab").removeClass("addbg1");
		/* $("#second_part").hide(); */
		$("#tab2second").hide();
		// $("#first_part").show();
		// $("#second_part").hide();
		// $(".tabmain").removeClass("tab2");
		 
		 });
	$("#assi_tab").click(function() {
		
		$(this).addClass("addbg");
		$("#ass_tab").addClass("addbg1");
		$("#ass_tab").removeClass("addbg");
		 $("#second_part").show();
		// $("#first_part").css("display","none");
			// $(".select_tr").hide();
		 });
	
});
</script>
<style>
.table_group1 {
width:400px;
float:left;
height:auto;
}
.table_group2 {
width:400px;
float:right;
height:auto;
}
.multiple_button {
    width: 100%;
    float: left;
    padding: 30px 0px;
    text-align: center;
}
.grout_table_outer {
width:1000px;
margin:auto;
padding-bottom:30px;
}
.search_div {
    margin-top: 12px;
    height: 35px;
    /* float: left; */
    width: 100%;
    margin-top: -26px;
}
.table_group1 .header_stop {
margin-left:0px;
}
.table_group1 table tr {
    border: 1px solid #bbb!important;
}
.table_group2 table tr {
    border: 1px solid #bbb!important;
}
.autohide {
    color: green;
    font-size: 14px;
    font-weight: bold;
    text-align: center;
    position: absolute;
    left: 0px;
    right: 0px;
    top: 270px;
}
</style>
</html>