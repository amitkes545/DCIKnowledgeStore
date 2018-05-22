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

ArrayList<HashMap<String,String>> MemberFeesList=null;
if(request.getAttribute("MemberFeesList")!=null)
	MemberFeesList=(ArrayList<HashMap<String,String>>)request.getAttribute("MemberFeesList");

ArrayList<HashMap<String,String>> GroupFeesList=null;
if(request.getAttribute("GroupFeesList")!=null)
	GroupFeesList=(ArrayList<HashMap<String,String>>)request.getAttribute("GroupFeesList");

ArrayList<HashMap<String,String>> MemberFeesTypeOptions=dao.getMemberFeesTypeOptions();

String hiddenOrgID="";	if(request.getAttribute("hiddenOrgID")!=null ) { hiddenOrgID=(String)request.getAttribute("hiddenOrgID"); }
String hiddenCourseID="";	if(request.getAttribute("hiddenCourseID")!=null ) { hiddenCourseID=(String)request.getAttribute("hiddenCourseID"); }
String hiddenGroupFeesID="";	if(request.getAttribute("hiddenGroupFeesID")!=null ) { hiddenGroupFeesID=(String)request.getAttribute("hiddenGroupFeesID"); }
String hiddenMemberFeeID="";	if(request.getAttribute("hiddenMemberFeeID")!=null ) { hiddenMemberFeeID=(String)request.getAttribute("hiddenMemberFeeID"); }
String hiddenMemberFeesType="";	if(request.getAttribute("hiddenMemberFeesType")!=null ) { hiddenMemberFeesType=(String)request.getAttribute("hiddenMemberFeesType"); }


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
		
		String CourseVal=(String)request.getAttribute("CourseID");
		//System.out.println("CourseVal == "+CourseVal);
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
			<%@ include file= "../JSP/owner_menu.jsp" %>
			</div>
		<div style="clear: both;"></div>
		
    	<div class="fee_form_outer row" style="margin-top:100px;">
        	<div class="fee_form_inner">
            <h3> Fees Group Setup</h3>
            	<form id="fees_form" action="/KODE_DEV/ControllerServlet/FeesModuleService?PartName=FeesGroupConfig" method="post">
            	
            	<input type="hidden" name="hiddenOrgID" id="hiddenOrgID">
      			 <input type="hidden" name="hiddenCourseID" id="hiddenCourseID" value='<%=hiddenCourseID %>'>
       		    <input type="hidden" name="hiddenGroupFeesID" id="hiddenGroupFeesID" value='<%=hiddenGroupFeesID %>'>
       		    <input type="hidden" name="hiddenMemberFeeID" id="hiddenMemberFeeID" value='<%=hiddenMemberFeeID %>'>
       		    <input type="hidden" name="hiddenMemberFeesType" id="hiddenMemberFeesType" value='<%=hiddenMemberFeesType %>'>
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
                    <select name="CourseID" id="CourseID" onchange="setCourseID()">
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
                    <label>Group Fee ID</label>
                    <select name="GroupFeesID" id="GroupFeesID" onchange="setGroupFeesID()">
                    <option value="">Choose Group Fee ID</option>
                    	 <%-- <%
                    if(GroupFeesList!=null && GroupFeesList.size()>0)
                    {
                    	for(int i=0;i<GroupFeesList.size();i++)
                    	{
                    		HashMap<String,String> info=GroupFeesList.get(i);
                        	String mlt_course_fee_code=info.get("mlt_course_fee_code").toString().trim();
                        	String mlt_fee_name=info.get("mlt_fee_name").toString().trim();
                        	String FullName=info.get("FullName").toString().trim();
                        	%>
                        <option value='<%= mlt_course_fee_code%>' > <%=FullName %> </option>	
                        	
                    <%	}
                    }
                    %> --%>
                    </select>
                    </li>
                    <li>
                    <label>Group Sequence</label>
                    <input type="text" name="GroupSequence" placeholder="Text" id="GroupSequence" maxlength=5 />
                    </li>
                    <li>
                    <label>Member Fee ID</label>
                    <select name="MemberFeeID" id="MemberFeeID" onchange="setMemberFeeID()" >
                    <option value="">Choose Member Fee ID</option>
                    	<%-- <%
                    if(MemberFeesList!=null && MemberFeesList.size()>0)
                    {
                    	for(int i=0;i<MemberFeesList.size();i++)
                    	{
                    		HashMap<String,String> info=MemberFeesList.get(i);
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
                    <label>Member Fee Value</label>
                    <input type="text" name="MemberFeesValue" placeholder="Text" id="MemberFeesValue" />
                    </li>
                   <li>
                    <label>Member Fee Type</label>
                    <select name="MemberFeesType" id="MemberFeesType" onchange="setMemberFeesType()" >
                    <option value="">Choose Member Fee Type</option>
                    	<%
                    if(MemberFeesTypeOptions!=null && MemberFeesTypeOptions.size()>0)
                    {
                    	for(int i=0;i<MemberFeesTypeOptions.size();i++)
                    	{
                    		HashMap<String,String> info=MemberFeesTypeOptions.get(i);
                        	String member_fee_type=info.get("member_fee_type").toString().trim();
                       %>
                       
                        <option value='<%= member_fee_type%>' > <%=member_fee_type %> </option>	
                        	
                    <%	}
                    }
                    %>
                    </select>
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

function setInstituteID()
{
	
	$("#hiddenOrgID").val($("#OrgID").val());
	$("#hiddenCurrentStatus").val("OrgIDChanged");
	
	var hiddenOrgID=$("#OrgID").val();
	
	$.ajax({  
   	    type: "POST",  
   	 	url: '../JSP/getDataForFeesPayAckModule.jsp?partname=CourseListForFeesGroup&hiddenOrgID='+hiddenOrgID,  
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
				  // 	alert(crsarray[i]);
				   	if(crsarray[i].length>0)
				   		{
				   		var option1 = document.createElement("option");
					    var stuinfo=crsarray[i].split("~");
					    option1.text = stuinfo[0];
					    option1.value=stuinfo[1];
					    CourseSelectBox.add(option1);	
				   		}
				    
				}
			    
			    
				
			}
   	 	  }
   	 	 
	});
	
	
	//$("#fees_form").submit()
	
}
function setCourseID()
{
	$("#hiddenCourseID").val($("#CourseID").val());
	$("#hiddenCurrentStatus").val("CourseIDChanged");
	
	var hiddenOrgID=$("#OrgID").val();
	var hiddenCourseID=$("#CourseID").val();
	$.ajax({  
   	    type: "POST",  
   	 	url: '../JSP/getDataForFeesPayAckModule.jsp?partname=GroupFeesNamesList&hiddenOrgID='+hiddenOrgID+'&hiddenCourseID='+hiddenCourseID,  
   	   	success: function(msg){
   	   	var result = msg.trim();
   	   	if(result.length>0)
   	   		{
   	   	var resultarry=msg.split('#');
		var CourseList=resultarry[0];
		var FeesList=resultarry[1];
		
   	   		
   	   		// alert(result);
			var columns= CourseList.split(';');
			var ElementID="GroupFeesID";
			$('#'+ElementID).empty()
			var ColumnSelectBox=document.getElementById(ElementID);
			
			var option = document.createElement("option");
		    option.text = "Choose Group Fee ID";
		    option.value="";
		    ColumnSelectBox.add(option);
			
		    for (i = 0; i < columns.length; i++) { 
			   	// alert(columns[i]);
			   	if(columns[i].length>0)
			   		{
			   		var option1 = document.createElement("option");
				    var stuinfo=columns[i].split("~");
				    option1.text = stuinfo[1];
				    option1.value=stuinfo[0];
				    ColumnSelectBox.add(option1);	
			   		}
			    
			}
		    
		    var FeesElemID="MemberFeeID";
			$('#'+FeesElemID).empty()
			var FeesSelectBox=document.getElementById(FeesElemID);
			
			var option = document.createElement("option");
		    option.text = "Choose Fees ID";
		    option.value="";
		    FeesSelectBox.add(option);
			
		    feesarray=FeesList.split('@');
		    
		  //  alert("feesarray =="+feesarray)
		    for (i = 0; i < feesarray.length; i++) { 
			 //  	alert(feesarray[i]);
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
function setGroupFeesID()
{
	$("#hiddenGroupFeesID").val($("#GroupFeesID").val());
}
function setMemberFeeID()
{
	$("#hiddenMemberFeeID").val($("#MemberFeeID").val());
}
function setMemberFeesType()
{
	$("#hiddenMemberFeesType").val($("#MemberFeesType").val());
	$("#hiddenCurrentStatus").val("MemberFeesTypeChanged");
}


$(document).ready(function() {
	

$("#fees_form").validate({
rules: {
	OrgID: {
		required: true,
	},
	CourseID: {
		required: true,
	},
	GroupFeesID: {
		required: true,
	},
	GroupSequence: {
		required: true,
	},
	MemberFeeID: {
		required: true,
	},
	MemberFeesValue: {
		required: true,
	},
	MemberFeesType: {
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
		GroupFeesID: {
			required: "",
		},
		GroupSequence: {
			required: "",
		},
		MemberFeeID: {
			required: "",
		},
		MemberFeesValue: {
			required: "",
		},
		MemberFeesType: {
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
</style>
</html>
