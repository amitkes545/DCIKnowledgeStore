<%@ page errorPage="../JSP/error.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.*" %> 
<%@page import="com.kds.KODE_DEV.dao.FeesModuleDao" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV_TEST</title>
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

FeesModuleDao dao=new FeesModuleDao();

ArrayList<HashMap<String,String>> OrgList=dao.getFeesInstitutes(); 

ArrayList<HashMap<String,String>> CourseList=null;
if(request.getAttribute("CourseList")!=null)
	CourseList=(ArrayList<HashMap<String,String>>)request.getAttribute("CourseList");

ArrayList<HashMap<String,String>> OrgFeesList=null;
if(request.getAttribute("OrgFeesList")!=null)
	OrgFeesList=(ArrayList<HashMap<String,String>>)request.getAttribute("OrgFeesList");

ArrayList<HashMap<String,String>> GroupedOptions=dao.getGroupedOptions();
ArrayList<HashMap<String,String>> FeesTypeOptions=dao.getFeesTypeOptions();

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
		 setInstituteID();	
			<%}
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
			<%@ include file= "../JSP/owner_menu.jsp" %>
			</div>
		<div style="clear: both;"></div>
		
    	<div class="fee_form_outer row" style="margin-top:100px;">
        	<div class="fee_form_inner">
            <h3>Coursewise Fees</h3>
            	<form id="fees_form" action="/KODE_DEV/ControllerServlet/FeesModuleService?PartName=CourseFeesConfig" method="post">
            	
            	<input type="hidden" name="hiddenOrgID" id="hiddenOrgID">
      			 <input type="hidden" name="hiddenCourseID" id="hiddenCourseID">
       		    <input type="hidden" name="hiddenFeesID" id="hiddenFeesID">
       		    <input type="hidden" name="hiddenGroup" id="hiddenGroup">
       		    <input type="hidden" name="hiddenCourseFeesID" id="hiddenCourseFeesID">
       		    <input type="hidden" name="hiddenCurrentStatus" id="hiddenCurrentStatus" value="">
       		    
                <ul>
                	<li>
                    <label>Organization Name</label>
                    <select  name="OrgID" id="OrgID" onchange="setInstituteID()" >
                    <option value="">Choose Organization name</option>
                    <%
                    if(OrgList!=null && OrgList.size()>0)
                    {
                    	for(int i=0;i<OrgList.size();i++)
                    	{
                    		HashMap<String,String> info=OrgList.get(i);
                        	String organization_id=info.get("organization_id").toString().trim();
                        	
                        	String FullName=info.get("FullName").toString().trim();
                        	%>
                        <option value='<%= organization_id%>' > <%=FullName %> </option>	
                        	
                    <%	}
                    }
                    %>
                    </select>
                    </li>
                    <li>
                    <label>Course Name</label>
                    <select name="CourseID" id="CourseID" onchange="setFeeCode()">
                    <option value="">Choose Course Name</option>
                    	<%-- <%
                    if(CourseList!=null && CourseList.size()>0)
                    {
                    	for(int i=0;i<CourseList.size();i++)
                    	{
                    		HashMap<String,String> info=CourseList.get(i);
                        	String course_id_declared_by_teaching_source=info.get("course_id_declared_by_teaching_source").toString().trim();
                        	String course_name=info.get("course_name").toString().trim();
                        	String department_id=info.get("department_id").toString().trim();
                        	String FullName=info.get("FullName").toString().trim();
                        	%>
                        <option value='<%=department_id%>' > <%=FullName %> </option>	
                        	
                    <%	}
                    }
                    %> --%>
                    </select>
                    </li>
                    <li>
                    <label>Fees Sequence</label>
                    <input type="number" name="Sequence" placeholder="Text" id="Sequence" onchange="setFeeCode()" pattern="[0-9]{2}" maxlength=2 onkeydown="return FilterInput(event)"/>
                    </li>
                    <li>
                    <label>Fee ID</label>
                    <select name="FeesID" id="FeesID" onchange="setFeeCode()">
                    <option value="">Choose Fee ID</option>
                   <%--  	<%
                    if(OrgFeesList!=null && OrgFeesList.size()>0)
                    {
                    	for(int i=0;i<OrgFeesList.size();i++)
                    	{
                    		HashMap<String,String> info=OrgFeesList.get(i);
                        	String mlt_fee_code=info.get("mlt_fee_code").toString().trim();
                        	String mlt_fee_name=info.get("mlt_fee_name").toString().trim();
                        	String FullName=info.get("FullName").toString().trim();
                        	%>
                        <option value='<%= mlt_fee_code%>' > <%=FullName %> </option>	
                        	
                    <%	}
                    }
                    %> --%>
                    </select>
                    </li>
                    <li>
                    <label>Group</label>
                    <select name="Group" id="Group" onchange="setGroup()" >
                    <option value="">Choose Group</option>
                    	<%
                    if(GroupedOptions!=null && GroupedOptions.size()>0)
                    {
                    	for(int i=0;i<GroupedOptions.size();i++)
                    	{
                    		HashMap<String,String> info=GroupedOptions.get(i);
                        	String group_option=info.get("group_option").toString().trim();
                       %>
                       
                        <option value='<%= group_option%>' > <%=group_option %> </option>	
                        	
                    <%	}
                    }
                    %>
                    </select>
                    </li>
                    
                    <li>
                    <label>Fee Amount</label>
                    <input type="number" name="FeesAmount" placeholder="Text" id="FeesAmount" pattern="[0-9]{4}" maxlength=8 onkeydown="return FilterInput(event)"/>
                    </li>
                    <li>
                    <label>Fee Type</label>
                    <select name="FeeType" id="FeeType" onchange="validateFeeType(this.value)">
                    <option value="">Choose Fees Type</option>
                    	<%
                    if(FeesTypeOptions!=null && FeesTypeOptions.size()>0)
                    {
                    	for(int i=0;i<FeesTypeOptions.size();i++)
                    	{
                    		HashMap<String,String> info=FeesTypeOptions.get(i);
                        	String fee_type=info.get("fee_type").toString().trim();
                       %>
                       
                        <option value='<%= fee_type%>' > <%=fee_type %> </option>	
                        	
                    <%	}
                    }
                    %>
                    </select>
                    </li>
                    <li>
                    <label>Fees Frequency</label>
                    <input type="hidden" value="" name="FeeFreq" id="FeeFreq" ></input>
                    <select name="FeeFreq1" id="FeeFreq1" onchange="validateFeeSeq(this.value)">
                    <option value="">Choose Frequency</option>
                    	<%
                    	for(int i=1;i<13;i++)
                    	{
                    	%>
                       
                        <option value='<%=i%>' > <%=i %> </option>	
                        	
                    <%
                    }
                    %>
                    </select>
                    </li>
                    
                     <li>
                    <label>Course Fee ID</label>
                    <input type="text" placeholder="Text" class="result_box" name="CourseFeesID" id="CourseFeesID" readonly/>
                    </li>
                    <li><input type="submit" value="submit" /></li>
                    
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
                </form>
            </div>
        </div>
        
    </div>
   <%@ include file="../JSP/FooterViews.jsp"%>
<script src="../JS/jquery.min.js"></script>
<script src="../JS/jquery.validate.js"></script>
<script>

function FilterInput(event) {
    var keyCode = ('which' in event) ? event.which : event.keyCode;

    isNotWanted = (keyCode == 69 ); //|| keyCode == 101
    return !isNotWanted;
}

function validateFeeType(FeeType)
{

	if(FeeType=="One Time")
	{
		$('#FeeFreq1').attr("disabled", true); 
		$("#FeeFreq1").val("1");
		validateFeeSeq("1")
	}else
	{
		$('#FeeFreq1').attr("disabled", false);
	}
}
function validateFeeSeq(feeSeq)
{
	$("#FeeFreq").val(feeSeq);
}
function setInstituteID()
{
	
	$("#hiddenOrgID").val($("#OrgID").val());
	$("#hiddenCurrentStatus").val("OrgIDChanged");
	var hiddenOrgID=$("#OrgID").val();
	
	$.ajax({  
   	    type: "POST",  
   	 	url: '../JSP/getDataForFeesPayAckModule.jsp?partname=CourseList&hiddenOrgID='+hiddenOrgID,  
   	   	success: function(msg){
   	   		var result = msg.trim();
   	   		
			if(result.length>0)
			{
				// alert("result= "+result);
				var resultarry=result.split('#');
				var CourseList=resultarry[0];
				var FeesList=resultarry[1];
				
				// alert("CourseList= "+CourseList);
				// alert("FeesList= "+FeesList);
				
				var CourseElemID="CourseID";
				$('#'+CourseElemID).empty()
				var CourseSelectBox=document.getElementById(CourseElemID);
				
				var option = document.createElement("option");
			    option.text = "Choose Course Name";
			    option.value="";
			    CourseSelectBox.add(option);
				
			    var crsarray=CourseList.split('@');
			    for (i = 0; i < crsarray.length; i++) { 
				   //	alert(crsarray[i]);
				   	if(crsarray[i].length>0)
				   		{
				   		var option1 = document.createElement("option");
					    var stuinfo=crsarray[i].split("~");
					    option1.text = stuinfo[0];
					    option1.value=stuinfo[1];
					    CourseSelectBox.add(option1);	
				   		}
				    
				}
			    
			    var FeesElemID="FeesID";
				$('#'+FeesElemID).empty()
				var FeesSelectBox=document.getElementById(FeesElemID);
				
				var option = document.createElement("option");
			    option.text = "Choose Fees ID";
			    option.value="";
			    FeesSelectBox.add(option);
				
			   /*  var feedatastatus = FeesList.includes("@");
			    
			    var feesarray=FeesList;
			    if(feedatastatus==true) */
			    	feesarray=FeesList.split('@');
			    
			   // alert("feesarray =="+feesarray)
			    for (i = 0; i < feesarray.length; i++) { 
				  // 	alert(feesarray[i]);
				   	if(feesarray[i].length>0)
				   		{
				   		var option1 = document.createElement("option");
					    var stuinfo=feesarray[i].split("~");
					    option1.text = stuinfo[0];
					    option1.value=stuinfo[1];
					    FeesSelectBox.add(option1);	
				   		}
				    
				}
				
			}
   	 	  }
   	 	 
	});
	
	//$("#fees_form").submit()
	
}
function sethiddenVals()
{
	$("#hiddenOrgID").val($("#OrgID").val());
	$("#hiddenCourseID").val($("#CourseID").val());
	$("#hiddenFeesID").val($("#FeesID").val());
	$("#hiddenCourseFeesID").val($("#CourseFeesID").val());
	$("#hiddenGroup").val($("#Group").val());
	
	
	$("#hiddenCurrentStatus").val("CourseFeesIDFilled");
	
	//alert($("#instid").val() + ' ' + $("#kesfeesid").val() +' ' + $("#institutefeesid").val());
}
function setGroup()
{
	$("#hiddenGroup").val($("#Group").val());	
}
function setFeeCode()
{
	var FinalVal=$("#CourseID").val()+"-"+$("#Sequence").val()+"-"+$("#FeesID").val();
	
	$("#CourseFeesID").val(FinalVal);
	
	sethiddenVals();
}
$(document).ready(function() {
	

	/* $('#FeesID').change(function() { 
		
		var FinalVal=$("#CourseID").val()+"-"+$("#Sequence").val()+"-"+$("#FeesID").val();
		
		$("#CourseFeesID").val(FinalVal);
		
		sethiddenVals();
	}); */
$("#fees_form").validate({
rules: {
	OrgID: {
		required: true,
	},	
	CourseID: {
		required: true,
	},
	Sequence: {
		required: true,
		number: true,
		maxlength: 3,
	},
	FeesID: {
		required: true,
	},	
	Group: {
		required: true,
	},
	FeesAmount: {
		required: true,
		number: true,
		
	},
	FeeType: {
		required: true,
	},	
	FeeFreq: {
		required: true,
	},
	CourseFeesID: {
		required: true,
	},
},
	messages: {
		OrgID: {
			required: "",
		},	
		CourseID: {
			required: "",
		},
		Sequence: {
			required: "",
		},
		FeesID: {
			required: "",
		},	
		Group: {
			required: "",
		},
		FeesAmount: {
			required: "",
		},
		FeeType: {
			required: "",
		},	
		FeeFreq: {
			required: "",
		},
		CourseFeesID: {
			required: "",
		},
		
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
input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}
input[type="number"] {
    -moz-appearance: textfield;
</style>
</html>
