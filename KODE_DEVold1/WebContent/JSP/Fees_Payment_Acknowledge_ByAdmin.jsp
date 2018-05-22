<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.*" %> 
<%@page import="com.kds.KODE_DEV.dao.FeesModuleDao" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
<link href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/start/jquery-ui.css"
          rel="Stylesheet" type="text/css" />

<link rel="stylesheet" href="../CSS/fees_styles.css" type="text/css" />
<link rel="stylesheet" href="../CSS/design-common.css" type="text/css" />

<link rel="stylesheet" href="../CSS/notification-new.css" type="text/css" />
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>

<%
String OrgID=(String)session.getAttribute("orgid");
String userid=(String)session.getAttribute("userid");
String username=(String)session.getAttribute("username");
if(username==null)
	response.sendRedirect("../JSP/error.jsp?errmsg=Session Expired");


String FeeFailureMsg="";
if(session.getAttribute("FeeFailureMsg")!=null)
	FeeFailureMsg=(String)session.getAttribute("FeeFailureMsg");

String FeeSuccessMsg="";
if(session.getAttribute("FeeSuccessMsg")!=null)
	FeeSuccessMsg=(String)session.getAttribute("FeeSuccessMsg");

String failedmsg = (String) session.getAttribute("failedmsg");
FeesModuleDao dao=new FeesModuleDao();

ArrayList<HashMap<String,String>> DeptList=dao.getDeptListByOrg(OrgID);
ArrayList<HashMap<String,String>> TemplateTypeList=dao.getTemplateTypeOptions();
ArrayList<HashMap<String,String>> PayStatusList=dao.getPaymentStatusOptions();


%>

<script>


function onload(){

<%	String SearchVal=(String)request.getAttribute("OrgID");
		if(SearchVal!=null && SearchVal.length()>0) { 
		%>
		
		var SearchValselectBox = document.getElementById("OrgID");
		 for(var i=0;i<SearchValselectBox.options.length;i++){
				 if(SearchValselectBox.options[i].value == '<%=SearchVal%>')
				{
					 SearchValselectBox.options[i].selected=true;
		 			 i=SearchValselectBox.options.length;
				 }
			 } 
					
			<%} 
		
		String CourseVal=(String)request.getAttribute("CourseID");
		System.out.println("CourseVal == "+CourseVal);
		if(CourseVal!=null && CourseVal.length()>0) { 
		%>
		
		var CourseValselectBox = document.getElementById("CourseID");
		 for(var i=0;i<CourseValselectBox.options.length;i++){
				 if(CourseValselectBox.options[i].value == '<%=CourseVal%>')
				{
					 CourseValselectBox.options[i].selected=true;
		 			 i=CourseValselectBox.options.length;
				 }
			 } 
					
			<%} 
		 	
		//
		%>
}

</script>
</head>
<body>
<!-- <div class="page_wrapper"> -->
<!--<div class="header_top"><img src="../images/header_img.png" /></div>-->
	<div class="container">
		<%@include file="../JSP/headers.jsp"%>
			<div style="clear: both;"></div>
			<div> 
			<%@ include file= "../JSP/Admin_menu.jsp" %>
			</div>
		<div style="clear: both;"></div>
		
		<div class="fee_form_outer payment_outer row" style="margin-top:100px;">
        	<div class="fee_form_inner">
            <h3>Payment Acknowledgement</h3>
            	<form id="fees_form" action="/KODE_DEV/ControllerServlet/FeesModuleService?PartName=FeesAckByAdmin" method="post">
                <div class="payment_left">
                	<ul>
                        <%-- <li>
                        <label>Discipline ID</label>
                        	<select  name="DiscID" id="DiscID" >
                            	<option value="">Choose Discipline</option>
                            	<%
                    				if(DiscList!=null && DiscList.size()>0)
                    				{
                    					for(int i=0;i<DiscList.size();i++)
                    					{
                    						HashMap<String,String> info=DiscList.get(i);
                        					String discipline_id=info.get("discipline_id").toString().trim();
                        					String discipline_name=info.get("discipline_name").toString().trim();
                        					String FullName=discipline_name +" ("+discipline_id+")";
                        				%>
                        					<option value='<%= discipline_id%>' > <%=FullName %> </option>	
                        	  	<%		}
                    				}
                    			%>
                            </select>
                        </li>
                        <li>
                        <label>Program ID</label>
                       	 	<select  name="ProgID" id="ProgID" >
                            	<option value="">Choose Program</option>
                            	<%
                    				if(ProgList!=null && ProgList.size()>0)
                    				{
                    					for(int i=0;i<ProgList.size();i++)
                    					{
                    						HashMap<String,String> info=ProgList.get(i);
                        					String info_id=info.get("program_id").toString().trim();
                        					String info_name=info.get("program_name").toString().trim();
                        					String FullName=info_name +" ("+info_id+")";
                        				%>
                        					<option value='<%= info_id%>' > <%=FullName %> </option>	
                        	  	<%		}
                    				}
                    			%>
                            </select>
                        </li>
                        <li>
                        <label>Course ID</label>
                        	<select  name="CourseID" id="CourseID" >
                            	<option value="">Choose Course</option>
                            	<%
                    				if(CourseList!=null && CourseList.size()>0)
                    				{
                    					for(int i=0;i<CourseList.size();i++)
                    					{
                    						HashMap<String,String> info=CourseList.get(i);
                        					String info_id=info.get("course_id").toString().trim();
                        					String info_name=info.get("course_name").toString().trim();
                        					String FullName=info_name +" ("+info_id+")";
                        				%>
                        					<option value='<%= info_id%>' > <%=FullName %> </option>	
                        	  	<%		}
                    				}
                    			%>
                            </select>
                        </li> --%>
                        <li>
                        <label>Course ID</label>
                        	<select  name="DeptID" id="DeptID" onchange="callDeptIDChanged(this.value)">
                            	<option value="">Choose Course</option>
                            	<%
                    				if(DeptList!=null && DeptList.size()>0)
                    				{
                    					for(int i=0;i<DeptList.size();i++)
                    					{
                    						HashMap<String,String> info=DeptList.get(i);
                        					String info_id=info.get("department_id").toString().trim();
                        					String info_name=info.get("department_name").toString().trim();
                        					String dept_id=info.get("dept_id").toString().trim();
                        					String course_id_declared_by_teaching_source=info.get("course_id_declared_by_teaching_source").toString().trim();
                        					
                        					String FullName=info_name +" ("+info_id+")";
                        					
                        					String AllVals=OrgID+"~"+info_id+"~"+info_name+"~"+dept_id;
                        				%>
                        					<option value='<%= AllVals%>' > <%=course_id_declared_by_teaching_source %> </option>	
                        	  	<%		}
                    				}
                    			%>
                            </select>
                        </li>
                        <li>
                        <label>Enrollment ID</label>
                       <select  name="Enrolment" id="Enrolment" onchange="callEnrollmentChanged(this.value)">
                            	<option value="">Choose Enrollment ID</option>
                            	
                            </select>
                        </li>
                        <li>
                        <label>Payment Mode</label>
                        	<select  name="PaymentMode" id="PaymentMode" onchange="callPayModeChanged()" >
                            	<option value="">Choose Payment Type</option>
                            	<%
                    				if(TemplateTypeList!=null && TemplateTypeList.size()>0)
                    				{
                    					for(int i=0;i<TemplateTypeList.size();i++)
                    					{
                    						HashMap<String,String> info=TemplateTypeList.get(i);
                        					String template_name=info.get("template_name").toString().trim();
                        				%>
                        					<option value='<%= template_name%>' > <%=template_name %> </option>	
                        	  	<%		}
                    				}
                    			%>
                            </select>
                        </li>
                        <li>
                        <label>Instrument No.</label>
                        <input type="text" name="InstNo" id="InstNo"placeholder="Text" readonly/>
                        </li>
                        <li>
                        <label>Issuer Account No.</label>
                        <input type="text" name="IssuerAccNo" id="IssuerAccNo"placeholder="Text" readonly/>
                        </li>
                        <li style="width:180px;">
                        <label>Expiry Month</label>
                        <input type="text" name="ExpMonth" id="ExpMonth"placeholder="Text" readonly/>
                        </li>
                         <li style="width:180px;float:right;">
                        <label>Expiry year</label>
                        <input type="text" name="ExpYear" id="ExpYear"placeholder="Text" readonly/>
                        </li>
                        
                        <li>
                        <label>Transaction Ref.No.</label>
                        <input type="text" name="TxnRefNo" id="TxnRefNo" placeholder="Text" readonly/>
                        </li>
                        <li>
                        <label>Status</label>
                        	<select  name="PaymentStatus" id="PaymentStatus" class="disable_box">
                            	<option value="">Choose Status</option>
                            	<%
                    				if(PayStatusList!=null && PayStatusList.size()>0)
                    				{
                    					for(int i=0;i<PayStatusList.size();i++)
                    					{
                    						HashMap<String,String> info=PayStatusList.get(i);
                        					String Status=info.get("Status").toString().trim();
                        				%>
                        					<option value='<%= Status%>' > <%=Status %> </option>	
                        	  	<%		}
                    				}
                    			%>
                            </select>
                        </li>
                        
                  </ul>
                </div>
                <div class="payment_right">
                	<ul>
                        <!-- <li>
                        <label>Discipline Name</label>
                        <input type="text" name="DiscName" id="DiscName" placeholder="Text" class="disable_box"/>
                        </li>
                        <li>
                        <label>Program Name</label>
                        <input type="text" name="ProgName" id="ProgName" placeholder="Text" class="disable_box"/>
                        </li>
                        <li>
                        <label>Course Name</label>
                        <input type="text" name="CourseName" id="CourseName" placeholder="Text" class="disable_box"/>
                        </li>-->
                        <li> 
                        <label>Course Name</label>
                        <input type="text" name="DeptName" id="DeptName" placeholder="Text" class="disable_box"/>
                        </li>
                        <li>
                        <label>Student Name</label>
                        <input type="text" name="StuName" id="StuName"placeholder="Text" class="disable_box"/>
                        </li>
                        <li>
                        <label>Fee Name</label>
                        	<select  name="FeeName" id="FeeName" onchange="callFeeNameChanged(this.value)">
                            	<option value="">Choose Fee Name</option>
                            	
                            </select>
                        </li>
                        <li>
                        <label>Instrument Date</label>
                        <input type="text" name="InstDate" id="InstDate" placeholder="Text" readonly/>
                        </li>
                        <li>
                        <label>Issuer Name</label>
                        <input type="text" name="IssuerName" id="IssuerName" placeholder="Text" readonly/>
                        </li>
                       
                        <li>
                        <label>Fee Amount</label>
                        <input type="text" name="FeeAmt" id="FeeAmt" placeholder="Text" readonly/>
                        </li>
                        <li>
                        <label>Transaction Ref.Date</label>
                        <input type="text" name="TxnRefDate" id="TxnRefDate" placeholder="Text" readonly/>
                        </li>
                        <!-- <li><label style="color: transparent;">Transaction Ref.Date</label><input type="submit" value="View receipt" /></li> -->
                        
                        <% if(FeeSuccessMsg.length()>0) { %>
                  		<li><div id="SuccessMsg" class="success"> ${sessionScope.FeeSuccessMsg}</div></li>
                  	<%
                  		session.removeAttribute("FeeSuccessMsg");
                  	} %>
                  	
                  	<% if(FeeFailureMsg.length()>0) { %>
                  		<li><div id="FailureMsg" class="failure"> ${sessionScope.FeeFailureMsg}</div></li>
                  	<%
                  	session.removeAttribute("FeeFailureMsg");
                  	} %> 
                  	
                  </ul>
                  
                </div>
                <div style="width:100%;float:left;text-align:center;margin-top:15px;">
                  <input class="submit_btn" type="submit" value="submit" class="disable_box"/>
                  </div>
                </form>
            </div>
        </div>
        
    </div>
    <%@ include file="../JSP/FooterViews.jsp"%>
<script src="../JS/jquery.min.js"></script>
<script src="../JS/jquery.validate.js"></script>
<script>

function callDeptIDChanged(DeptID)
{
	// alert("DeptID == "+DeptID);
	
	var vals=DeptID.split('~');
	var OrgID=vals[0];
	var DeptID=vals[1];
	var DeptName=vals[2];
	var cdid=vals[3];
	
	// alert(OrgID +"  "+DeptID+"	"+DeptName+"	"+cdid);
	$("#DeptName").val(DeptName);
	
	$.ajax({  
   	    type: "POST",  
   	 	url: '../JSP/getDataForFeesPayAckModule.jsp?partname=StudentList&OrgID='+OrgID+'&DeptID='+cdid,  
   	   	success: function(msg){
   	   		var result = msg.trim();
   	   		//alert(result);
			var columns= result.split(';');
			var ElementID="Enrolment";
			$('#'+ElementID).empty()
			var ColumnSelectBox=document.getElementById(ElementID);
			
			var option = document.createElement("option");
		    option.text = "Choose Student Name";
		    option.value="";
		    ColumnSelectBox.add(option);
			
		    for (i = 0; i < columns.length; i++) { 
			   	//alert(columns[i]);
			   	if(columns[i].length>0)
			   		{
			   		var option1 = document.createElement("option");
				    var stuinfo=columns[i].split("~");
				    option1.text = stuinfo[1];
				    option1.value=columns[i];
				    ColumnSelectBox.add(option1);	
			   		}
			    
			}
   	 	  }
   	 	 
	});
}

function callEnrollmentChanged(ENRID)
{
	var DeptID=$('#DeptID').val();
	
	var vals=DeptID.split('~');
	var OrgID=vals[0];
	var DeptID=vals[1];
	var DeptName=vals[2];
	var cdid=vals[3];
	
	var ENRID=$('#Enrolment').val();
	
	var stuval=ENRID.split('~');
	var stuid=stuval[1];
	var stuname=stuval[0];
	
	$('#StuName').val(stuname);
	
	$.ajax({  
   	    type: "POST",  
   	 	url: '../JSP/getDataForFeesPayAckModule.jsp?partname=PayModeList&OrgID='+OrgID+'&ENRID='+stuid+'&DeptID='+cdid,  
   	   	success: function(msg){
   	   		var result = msg.trim();
   	   		//alert(result);
			var columns= result.split(';');
			var ElementID="PaymentMode";
			$('#'+ElementID).empty()
			var ColumnSelectBox=document.getElementById(ElementID);
			
			var option = document.createElement("option");
		    option.text = "Choose Payment Mode";
		    option.value="";
		    ColumnSelectBox.add(option);
			
		    for (i = 0; i < columns.length; i++) { 
			   //	alert(columns[i]);
			    var option1 = document.createElement("option");
			    var payinfo=columns[i];
			    option1.text = payinfo;
			    option1.value=payinfo;
			    ColumnSelectBox.add(option1);
			}
   	 	  }
   	 	 
	});
}

function callPayModeChanged()
{
	
	var DeptID=$('#DeptID').val();
	
	var vals=DeptID.split('~');
	var OrgID=vals[0];
	var DeptID=vals[1];
	var DeptName=vals[2];
	var cdid=vals[3];
	
	var ENRID=$('#Enrolment').val();
	
	var stuval=ENRID.split('~');
	var stuid=stuval[1];
	var stuname=stuval[0];
	
	// alert(OrgID +"  "+DeptID+"	"+DeptName+"	"+cdid+"		"+stuid+""+stuname);
	
	
	$.ajax({  
   	    type: "POST",  
   	 	url: '../JSP/getDataForFeesPayAckModule.jsp?partname=FeesNamesList&OrgID='+OrgID+'&ENRID='+stuid+'&DeptID='+cdid,  
   	   	success: function(msg){
   	   		var result = msg.trim();
   	   		//alert(result);
			var columns= result.split(';');
			var ElementID="FeeName";
			$('#'+ElementID).empty()
			var ColumnSelectBox=document.getElementById(ElementID);
			
			var option = document.createElement("option");
		    option.text = "Choose Fees Name";
		    option.value="";
		    ColumnSelectBox.add(option);
			
		    for (i = 0; i < columns.length; i++) { 
			   //	alert(columns[i]);
			    var option1 = document.createElement("option");
			    var stuinfo=columns[i].split("~");
			    option1.text = stuinfo[2];
			    option1.value=columns[i];
			    ColumnSelectBox.add(option1);
			}
   	 	  }
   	 	 
	});
}

function callFeeNameChanged(FeesCodeWithSeq)
{
//	alert("table_name == "+table_name);

	$( "#PaymentStatus" ).removeClass( "disable_box" )
	var DeptID=$('#DeptID').val();
	
	var vals=DeptID.split('~');
	var OrgID=vals[0];
	var DeptID=vals[1];
	var DeptName=vals[2];
	var cdid=vals[3];
	
	var ENRID=$('#Enrolment').val();
	
	var stuval=ENRID.split('~');
	var stuid=stuval[1];
	var stuname=stuval[0];
	
	var stuinfo=FeesCodeWithSeq.split('~');
	
	$.ajax({  
   	    type: "POST",  
   	 	url: '../JSP/getDataForFeesPayAckModule.jsp?partname=FeePaymentDetails&OrgID='+OrgID+'&ENRID='+stuid+'&DeptID='+cdid+'&FeesCode='+stuinfo[0]+'&FeesSeq='+stuinfo[1],  
   	   	success: function(msg){
   	   		var result = msg.trim();
   	   		if(result.length>0)
   	   		{
   	   			// alert(result);
   	   			var paydatas= result.split('~');
   	   			
   	   		var instrument_number=paydatas[0];
   	   		var instrument_date=paydatas[1];
   	   		var issuer_account_number=paydatas[2];
   	   		var issuing_bank=paydatas[3];
   	   		var expiry_month=paydatas[4];
   	   		var expiry_year=paydatas[5];
   	   		var amount_paid=paydatas[6];
   	   		var txn_reference_no=paydatas[7];
   	   		var txn_datetime=paydatas[8];
   	   		var payer_name=paydatas[9];
			
   				$('#InstNo').val(instrument_number); 				$('#InstDate').val(instrument_date);
   				$('#IssuerName').val(payer_name);	   				$('#IssuerAccNo').val(issuer_account_number);
   				$('#ExpMonth').val(expiry_month);	   				$('#ExpYear').val(expiry_year);
   				$('#FeeAmt').val(amount_paid);		   				$('#TxnRefNo').val(txn_reference_no);
   				
   				$('#TxnRefDate').val(txn_datetime);
   				
   	   		}
   	   		
			
   	 	  }
   	 	 
	});
}

//

$(document).ready(function() {
	

$("#fees_form").validate({
rules: {
	PaymentStatus: {
		required: true,
	}
	
},
	messages: {
		PaymentStatus: {
		required:"",
		}
		
	}
});

$('#InstDate').bind('keyup','keydown', function(event) {
	   var inputLength = event.target.value.length;
	    if (event.keyCode != 8){
	      if(inputLength === 2 || inputLength === 5){
	        var thisVal = event.target.value;
	        thisVal += '/';
	        $(event.target).val(thisVal);
	     }
	    }
	  });
});
</script>
</body>

<script>
onload();
</script>
<style>
.header_top {
	width:100%;
	height:auto;
}
.header_top img {
	max-width:100%;
	height:auto;
	border-bottom: 1px solid #ddd;
}
.footer img {
	bottom: 0;
    height: auto;
    left: 0;
    max-width: 100%;
    position: fixed;
}
.container {
	padding-bottom:25px;
}
.footer {
	padding-bottom:50px;
}
input.error {
	border:1px solid red!important;
}
select.error {
	border:1px solid red!important;
}
.submit_btn {
padding-top:20px;
}
.submit_btn {

	background: #51a6c5;
    border: 1px solid #c8c8c8;
    color: #fff;
    text-transform: uppercase;
    font-size: 16px;
    display: table;
    padding: 5px 15px;
    cursor: pointer;
    margin:auto;
}
</style>

<script>

$("#ErrorMsg").css("display", "inline").fadeOut(5000);

function changeColor(color)
{
	$("#ErrorMsg").css("color", color)
}

</script>
<style>
#ErrorMsg {
width: 100%;
    margin: auto;
    padding: 4px 10px;
    color: green;
    font-size: 18px;
    text-align: center;
    float: left;
  
    left: 0px;
    bottom: -100px;

}
</style>
</html>
