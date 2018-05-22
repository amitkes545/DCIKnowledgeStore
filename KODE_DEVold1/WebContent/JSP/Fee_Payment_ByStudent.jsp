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

  /* Enumeration<String> e=session.getAttributeNames(); // .getAttributeNames();
while (e.hasMoreElements()) {
     String key = (String) e.nextElement();
     System.out.println(key+"="+session.getAttribute(key));
}  */ 


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

FeesModuleDao dao=new FeesModuleDao();

HashMap<String,String> CourseInfo=dao.getCourseDescID(userid, OrgID);
ArrayList<HashMap<String,String>> TemplateTypeList=dao.getTemplateTypeOptions();
ArrayList<HashMap<String,String>> stuAdmFees=dao.getStudentPendingFees(userid, OrgID);

String course_discription_id="";
String department_id="";
String department_name="";
String course_id="";

if(CourseInfo!=null)
{
	course_discription_id=CourseInfo.get("course_discription_id");
	department_id=CourseInfo.get("department_id");
	department_name=CourseInfo.get("department_name");
	course_id=CourseInfo.get("course_id");
}
%>

<script>


function onload(){
	
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
			<%@ include file= "../JSP/Participant_menu.jsp" %>
			</div>
		<div style="clear: both;"></div>
		
    	<div class="fee_form_outer payment_outer row" style="margin-top:100px;">
        	<div class="fee_form_inner">
            <h3>Payment Details</h3>
            	<form id="fees_form" action="/KODE_DEV/ControllerServlet/FeesModuleService?PartName=FeesPaymentByStudent" method="post">
                <div class="payment_left">
                	<input type="hidden" name="FeeCode" id="FeeCode">
                	<input type="hidden" name="FeeSeq" id="FeeSeq">
                	<ul>
                        <li>
                        <label>Course ID</label>
                        <input type="text" name="CourseID" id="CourseID" placeholder="Text" class="disable_box" value="<%=course_id%>"/>
                        </li>
                        <li>
                        <label>Fee Name</label>
                       <select name="FeeName" id="FeeName" onchange="actionFeeNameChanged(this.value)">
                       <option value='' > Choose Fee Name</option>
                            	<%
                    				if(stuAdmFees!=null && stuAdmFees.size()>0)
                    				{
                    					for(int i=0;i<stuAdmFees.size();i++)
                    					{
                    						HashMap<String,String> info=stuAdmFees.get(i);
                        					String mlt_course_fee_code=info.get("mlt_course_fee_code").toString().trim();
                        					String mlt_fee_name=info.get("mlt_fee_name").toString().trim();
                        					String FullName=mlt_fee_name +" ("+mlt_course_fee_code+")";
                        					String fee_amount=info.get("fee_amount").toString().trim();
                        					String fee_sequence=info.get("fee_sequence").toString().trim();
                        					String Key=mlt_course_fee_code+"~"+fee_amount+"~"+fee_sequence;
                        				%>
                        					<option value='<%= Key%>' > <%=FullName %> </option>	
                        	  	<%		}
                    				}
                    			%>
                    			
                    			
                            </select>
                        </li>
                        <li>
                        <label>Payment Mode</label>
                        	<select  name="PaymentMode" id="PaymentMode" onchange="actionPaymentModeChanged(this.value)" > <!-- onchange="actionPaymentModeChanged(this.value)"  -->
                            	<option value="">Choose Payment Mode</option>
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
                        <input type="text" name="InstrumentNo" id="InstrumentNo" placeholder="No. of Instrument" maxlength=10 />
                        </li>
                        <li>
                        <label>Issuer Account No.</label>
                        <input type="text" name="AccountNo" id="AccountNo" placeholder="Account No." maxlength=16 />
                        </li>
                        <!-- <li>
                        <label>Expiry Month</label>
                        <select  name="ExpMonth" id="ExpMonth" >
                            	<option value="">Choose Exp Month</option>
                            	<option value="01">01 (Jan)</option>
                            	<option value="02">02 (Feb)</option>
                            	<option value="03">03 (Mar)</option>
                            	<option value="04">04 (Apr)</option>
                            	<option value="05">05 (May)</option>
                            	<option value="06">06 (Jun)</option>
                            	<option value="07">07 (Jul)</option>
                            	<option value="08">08 (Aug)</option>
                            	<option value="09">09 (Sep)</option>
                            	<option value="10">10 (Oct)</option>
                            	<option value="11">11 (Nov)</option>
                            	<option value="12">12 (Dec)</option>
                            	
                            </select>
                        
                        </li> -->
                        <input type="hidden" name="ExpMonth" id="ExpMonth" value="" placeholder="Expiry Month" maxlength=2/>
                      
                  </ul>
                </div>
                <div class="payment_right">
                	<ul>
                        <li>
                        <label>Course Name</label>
                        <input type="text" name="CourseName" id="CourseName" placeholder="Course Name" class="disable_box" value="<%=department_name%>"/>
                        </li>
                        <li>
                        <label>Fee Amount</label>
                        <input type="text" name="FeeAmount" id="FeeAmount" placeholder="Fee Amount" class="disable_box"/>
                        </li>
                        <li>
                        <label>Bank Name</label>
                        <input type="text" name="BankName" id="BankName" placeholder="Bank Name" />
                        </li>
                        <li>
                        <label>Instrument Date</label>
                        <input type="text" name="InstrumentDate" id="InstrumentDate" placeholder="DD/MM/YYYY" maxlength=10 />
                        </li>
                        <li>
                        <label>Issuer Name</label>
                        <input type="text" name="IssuerName" id="IssuerName" placeholder="Issuer Name" />
                        </li>
                        <%-- <li>
                        <label>Expiry year</label>
                        <select  name="ExpYear" id="ExpYear" >
                            	<option value="">Choose Exp Year</option>
                            	 <%
                            	 	int year = Calendar.getInstance().get(Calendar.YEAR);
                            	 	int endyear=year+20;
                    				for(int i=year;i<endyear;i++)
                    					{
                    			%>
                        					<option value='<%= i%>' > <%=i %> </option>	
                        	  	<%	}	%>
                            </select>
                            
                        
                        </li> --%>
                        <!-- <li><input type="submit" value="Show Fee Structure" /></li> -->
                        <input type="hidden" name="ExpYear" id="ExpYear" value="" placeholder="Exp Year" maxlength=4/>
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
                <div><ul> <li> <input type="submit" value="submit" /> </li> </ul> </div>
                </form>
            </div>
        </div>
        
    </div>
    <%@ include file="../JSP/FooterViews.jsp"%>
<script src="../JS/jquery.min.js"></script>
<script src="../JS/jquery.validate.js"></script>
<script>
function actionFeeNameChanged(FeeKey)
{
	//alert(FeeKey);
	var feesval=FeeKey.split('~');
	var feecode=feesval[0];
	var feeamt=feesval[1];
	var feeseq=feesval[2];
	
	$("#FeeAmount").val(feeamt);
	$("#FeeCode").val(feecode);
	$("#FeeSeq").val(feeseq);
}
function actionPaymentModeChanged(SelectedVal)
{

	if(SelectedVal=='Cash')
	{
		
		$('#InstrumentNo').val('');
		$('#AccountNo').val('');
		/* $('#ExpMonth').val(''); */
		$('#BankName').val('');
		$('#InstrumentDate').val('');
		/* $('#ExpYear').val(''); */
		$('#IssuerName').val('');
		
		$('#InstrumentNo').attr('readonly', 'true');
		$('#AccountNo').attr('readonly', 'true');
		/* $('#ExpMonth').attr('readonly', 'true'); */
		$('#BankName').attr('readonly', 'true');
		$('#InstrumentDate').attr('readonly', 'true');
		/* $('#ExpYear').attr('readonly', 'true'); */
		$('#IssuerName').attr('readonly', 'true');
		
		$( '#InstrumentNo' ).rules( "remove", "required" );
		$( '#AccountNo' ).rules( "remove", "required" );
		/* $( '#ExpMonth' ).rules( "remove", "required" ); */
		$( '#BankName' ).rules( "remove", "required" );
		$( '#InstrumentDate' ).rules( "remove", "required" );
		/* $( '#ExpYear' ).rules( "remove", "required" ); */
		$( '#IssuerName' ).rules( "remove", "required" );
		
	}else if(SelectedVal=='Transfer')
	{
		$('#BankName').removeAttr('readonly');
		$('#IssuerName').removeAttr('readonly');
		$('#AccountNo').removeAttr('readonly');
		
		$( '#BankName' ).rules( "add", {required: true,messages: { required: ""} });
		$( '#IssuerName' ).rules( "add", {required: true,messages: { required: ""} });
		$( '#AccountNo' ).rules( "add", {required: true,messages: { required: ""} });
	}else
	{
		$('#InstrumentNo').removeAttr('readonly');
		$('#AccountNo').removeAttr('readonly');
		/* $('#ExpMonth').removeAttr('readonly'); */
		$('#BankName').removeAttr('readonly');
		$('#InstrumentDate').removeAttr('readonly');
		/* $('#ExpYear').removeAttr('readonly'); */
		$('#IssuerName').removeAttr('readonly');
		
		$( '#InstrumentNo' ).rules( "add", {required: true,messages: { required: ""} });
		$( '#AccountNo' ).rules( "add", {required: true,messages: { required: ""} });
		/* $( '#ExpMonth' ).rules( "add", {required: true,messages: { required: ""} }); */
		$( '#BankName' ).rules( "add", {required: true,messages: { required: ""} });
		$( '#InstrumentDate' ).rules( "add", {required: true,messages: { required: ""} });
		/* $( '#ExpYear' ).rules( "add", {required: true,messages: { required: ""} }); */
		$( '#IssuerName' ).rules( "add", {required: true,messages: { required: ""} });
	}
}

$(document).ready(function() {

	$('#InstrumentNo').attr('readonly', 'true');
	$('#AccountNo').attr('readonly', 'true');
	/* $('#ExpMonth').attr('readonly', 'true'); */
	$('#BankName').attr('readonly', 'true');
	$('#InstrumentDate').attr('readonly', 'true');
	/* $('#ExpYear').attr('readonly', 'true'); */
	$('#IssuerName').attr('readonly', 'true');
	
	 $("#fees_form").validate({
	  
	rules: {
	CourseID: {
		required: true,
	},
	FeeName: {
		required: true,
	},
	PaymentMode: {
		required: true,
	},
	InstrumentNo: {
		number: true,
		minlength: 5,
		maxlength: 10
	},
	AccountNo: {
		number: true,
		minlength: 5,
		maxlength: 16
	},
	CourseName: {
		required: true,
	},
	FeeAmount: {
		required: true,
		number: true,
		
	},
	ExpMonth: {
		number: true,
		minlength: 2,
		maxlength: 2
	},
	ExpYear: {
		number: true,
		minlength: 4,
		maxlength: 4
	},
},
	messages: {
		CourseID: {
			required: "",
		},
		FeeName: {
			required: "",
		},
		PaymentMode: {
			required: "",
		},
		InstrumentNo: {
			number: "",
			minlength: "",
			maxlength: ""
		},
		AccountNo: {
			number: "",
			minlength: "",
			maxlength: ""
		},
		CourseName: {
			required: "",
		},
		FeeAmount: {
			required: "",
			number: "",
			
		},
		ExpMonth: {
			number: "",
			minlength: "",
			maxlength: ""
		},
		
		ExpYear: {
			number: "",
			minlength: "",
			maxlength: ""
		},
		
	}
}); 
	  
	 $('#InstrumentDate').bind('keyup','keydown', function(event) {
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
}</style>

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
.fee_form_inner ul li:last-child {
width:100%;
}
</style>
</html>
