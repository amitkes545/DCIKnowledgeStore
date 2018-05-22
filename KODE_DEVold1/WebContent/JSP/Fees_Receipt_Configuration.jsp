<%@ page errorPage="../JSP/error.jsp" %>
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
<link rel="stylesheet" href="../CSS/table_scroll.css" type="text/css" />
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

ArrayList<HashMap<String,String>> OrgList=dao.getAllInstitutes();
ArrayList<HashMap<String,String>> CourseList=null;
if(request.getAttribute("CourseList")!=null)
	CourseList=(ArrayList<HashMap<String,String>>)request.getAttribute("CourseList");

ArrayList<HashMap<String,String>> TemplateTypeList=dao.getTemplateTypeOptions();

ArrayList<HashMap<String,String>> LineTypeList=dao.getLineTypeOptions();
ArrayList<HashMap<String,String>> TextTypeList=dao.getTextTypeOptions();
ArrayList<HashMap<String,String>> DataTableList=dao.getAllDBTableNames();

ArrayList<HashMap<String,String>> ColumnNamesList=null;
if(request.getAttribute("ColumnNamesList")!=null)
	ColumnNamesList=(ArrayList<HashMap<String,String>>)request.getAttribute("ColumnNamesList");


ArrayList<HashMap<String,String>> TemplateDataRows=null;
if(request.getAttribute("TemplateDataRows")!=null)
	TemplateDataRows=(ArrayList<HashMap<String,String>>)request.getAttribute("TemplateDataRows");


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
					 $("#hiddenOrgID").val( SearchValselectBox.options[i].value );
		 			 i=SearchValselectBox.options.length;
				 }
			 } 
					
			<%}
		
		String CourseID=(String)request.getAttribute("CourseID");
		if(CourseID!=null && CourseID.length()>0) { 
		%>
		
		var CourseIDselectBox = document.getElementById("CourseID");
		 for(var i=0;i<CourseIDselectBox.options.length;i++){
				 if(CourseIDselectBox.options[i].value == '<%=CourseID%>')
				{
					 CourseIDselectBox.options[i].selected=true;
					 $("#hiddenCourseID").val( CourseIDselectBox.options[i].value );
		 			 i=CourseIDselectBox.options.length;
				 }
			 } 
					
			<%}
		
		String TemplateType=(String)request.getAttribute("TemplateType");
		if(TemplateType!=null && TemplateType.length()>0) { 
		%>
		
		var TemplateTypeselectBox = document.getElementById("TemplateType");
		 for(var i=0;i<TemplateTypeselectBox.options.length;i++){
				 if(TemplateTypeselectBox.options[i].value == '<%=TemplateType%>')
				{
					 TemplateTypeselectBox.options[i].selected=true;
					 $("#hiddenTemplateType").val( TemplateTypeselectBox.options[i].value );
		 			 i=TemplateTypeselectBox.options.length;
				 }
			 } 
					
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
		<div class="configuration_outer" style="width:1200px; margin:auto;">	
    	<div class="configuration_inner" style="margin-top:100px;">
    	

            	<form id="fees_form" action="/KODE_DEV/ControllerServlet/FeesModuleService?PartName=FeesReceiptTemplate" method="post">
            	
            	<input type="hidden" name="hiddenOrgID" id="hiddenOrgID">
      			<input type="hidden" name="hiddenCourseID" id="hiddenCourseID">
       		    <input type="hidden" name="hiddenTemplateType" id="hiddenTemplateType">
       		    <input type="hidden" name="hiddenCurrentStatus" id="hiddenCurrentStatus" value="">
       		    <input type="hidden" name="hiddenRowCount" id="hiddenRowCount" value="">
       		    
       		    
        	<div class="combo_box_row">
            	<div class="combo_box_left">
                	<ul>
                    	<li><label>Organization Name</label>
                    	<select  name="OrgID" id="OrgID" onchange="setInstituteID()">
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
                    </ul>
                </div>
                <div class="combo_box_left" style="float:right;">
                	<ul>
                    	<li><label>Course Name</label>
                    	<select name="CourseID" id="CourseID" onchange="setCourseID()">
                    <option value="">Choose Course Name</option>
                    	<%
                    if(CourseList!=null && CourseList.size()>0)
                    {
                    	for(int i=0;i<CourseList.size();i++)
                    	{
                    		HashMap<String,String> info=CourseList.get(i);
                        	String course_id_declared_by_teaching_source=info.get("course_id_declared_by_teaching_source").toString().trim();
                        	String course_name=info.get("course_name").toString().trim();
                        	String course_discription_id=info.get("course_discription_id").toString().trim();
                        	String FullName=info.get("FullName").toString().trim();
                        	%>
                        <option value='<%=course_discription_id%>' > <%=FullName %> </option>	
                        	
                    <%	}
                    }
                    %>
                    </select>
                    </li>
                    </ul>
                </div>
                <div class="combo_box_left" style="width:100%;float:left;margin-top:20px;margin-bottom:30px;">
                	<ul>
                    	<li><label>Template Name</label>
                    	<select  name="TemplateType" id="TemplateType" onchange="setTemplateType()">
                    <option value="">Choose Template Type</option>
                    <%
                    if(TemplateTypeList!=null && TemplateTypeList.size()>0)
                    {
                    	for(int i=0;i<TemplateTypeList.size();i++)
                    	{
                    		HashMap<String,String> info=TemplateTypeList.get(i);
                        	String template_name=info.get("template_name").toString().trim();
                        	%>
                        <option value='<%= template_name%>' > <%=template_name %> </option>	
                        	
                    <%	}
                    }
                    %>
                    </select>

						</li>
                        <li><input type="button" value="Get Template" onclick="retriveTemplatesDatas()" /></li>
                        <li><input type="button" value="Add Row" onclick="addRowToTable()"  /></li>
                    </ul>
                </div>
            </div>
            <div class="config_table_column">
            	<div class="table_section1">
				<div class="table_outer_row">
                <table width="100%" id="tblData">
		<thead>
		<tr class="row_head">
			<th width="14.28%">Line Type <div class="header_stop">Line Type</div></th>
			<th width="14.28%">Line number<div class="header_stop">Line number</div></th>
			<th width="14.28%">Text sequence<div class="header_stop">Text Sequence</div></th>
			<th width="14.28%">Text type<div class="header_stop">Text Type</div></th>
			<th width="14.28%">Text Value<div class="header_stop">Text Value</div></th>
			<th width="14.28%">Data Table<div class="header_stop">Data Table</div></th>
			<th width="14.28%">Data Column<div class="header_stop">Data Column</div></th>		
		</tr>
		</thead>
		<tbody>
        <% 
        if(TemplateDataRows!=null && TemplateDataRows.size()>0)
        {
        	for(int i=0;i<TemplateDataRows.size();i++)
        	{
        		HashMap<String,String> info=TemplateDataRows.get(i);
        		String line_type=info.get("line_type").toString().trim();
        		String line_no=info.get("line_no").toString().trim();
        		String text_seq=info.get("text_seq").toString().trim();
        		String text_type=info.get("text_type").toString().trim();
        		String text_value=info.get("text_value").toString().trim();
        		String data_table=info.get("data_table").toString().trim();
        		String data_column=info.get("data_column").toString().trim();
        		
        		String LineTypeIDName="LineType_"+i;
        		String LineNoIDName="LineNo_"+i;
        		String TextSeqIDName="TextSeq_"+i;
        		String TextTypeIDName="TextType_"+i;
        		String TextValueIDName="TextValue_"+i;
        		String DataTableIDName="DataTable_"+i;
        		String DataColumnIDName="DataColumn_"+i;
        %>
        <tr>
        	<td>
        		<select id='<%= LineTypeIDName %>' name='<%= LineTypeIDName %>'>
        		<option value='' >Choose Line Type </option>
        		<%
				if(LineTypeList!=null && LineTypeList.size()>0)
				{
					for(int j=0;j<LineTypeList.size();j++)
					{
						HashMap<String,String> info2=LineTypeList.get(j);
						String line_type1=info2.get("line_type").toString().trim();
						if(line_type.trim().equalsIgnoreCase(line_type1))
						{ %>
						<option value='<%=line_type1 %>' selected><%=line_type1 %> </option>
						<% } else
						{ %>
							<option value='<%=line_type1 %>' ><%=line_type1 %> </option>
					<%	}
						
						
					}
				}%>
        		</select>
        	</td>
        	<td>
        		<input type="number" id="<%=LineNoIDName%>" name="<%=LineNoIDName%>" placeholder="Line No" value="<%=line_no%>" maxlength="2" onkeydown="return FilterInput(event)"/>
        	</td>
        	<td>
        		<input type="number" id="<%=TextSeqIDName%>" name="<%=TextSeqIDName%>" placeholder="Text Seq" value="<%=text_seq%>" maxlength="2" onkeydown="return FilterInput(event)"/>
        	</td>
        	<td>
        		<select id='<%= TextTypeIDName %>' name='<%= TextTypeIDName %>' onchange='callTextTypeChanged(this.id,this.value,<%=i%>);'>
        		<option value='' >Choose Text Type </option>
        		<%
				if(TextTypeList!=null && TextTypeList.size()>0)
				{
					for(int j=0;j<TextTypeList.size();j++)
					{
						HashMap<String,String> info2=TextTypeList.get(j);
						String text_type1=info2.get("text_type").toString().trim();
						if(text_type.trim().equalsIgnoreCase(text_type1))
						{ %>
						<option value='<%=text_type1 %>' selected><%=text_type1 %> </option>
						<% } else
						{ %>
							<option value='<%=text_type1 %>' ><%=text_type1 %> </option>
					<%	}
						
						
					}
				}%>
        		</select>
        	</td>
        	<td>
        		<input readonly type="text" class="alphaonly textonly" id="<%=TextValueIDName%>" name="<%=TextValueIDName%>" placeholder="Text Value" value="<%=text_value%>" onchange='callTxtValChanged(this.value);' />
        	</td>
        	<td>
        		<select id='<%= DataTableIDName %>' name='<%= DataTableIDName %>' onchange='callTableNameChanged(this.value,<%=i%>)'>
        		<option value="" >Choose Table Name </option>
        		<%
				if(DataTableList!=null && DataTableList.size()>0)
				{
					for(int j=0;j<DataTableList.size();j++)
					{
						HashMap<String,String> info2=DataTableList.get(j);
						String data_table1=info2.get("table_name").toString().trim();
						if(data_table.trim().equalsIgnoreCase(data_table1))
						{ %>
						<option value='<%=data_table1 %>' selected><%=data_table1 %> </option>
						<% } else
						{ %>
							<option value='<%=data_table1 %>' ><%=data_table1 %> </option>
					<%	}
						
						
					}
				}%>
        		</select>
        	</td>
        	<td>
        		<select id="<%=DataColumnIDName%>" name="<%=DataColumnIDName%>" placeholder="Column Name" value="<%=data_column%>"  onchange='callDataColumnChanged(this.value);' />
        		<%
        		if(data_column.trim().length()>0)
        		{ %>
        		 <option value='<%=data_column %>' ><%=data_column %> </option>
        		<% }%>
        	</td>
        </tr>		
        
        		
       <% 	}
        }
        %>
        
        </tbody>
         </table>
                </div>
                </div>
            </div>
           <div class="config_button row">
                	<input type="button" value="SAVE" onclick="callSaveAction()" class="btnSave" />
                	
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
                </div>
            </form>
        </div>
        </div>
    </div>
    <%@ include file="../JSP/FooterViews.jsp"%>
</body>
<script>
onload();
$(".alphaonly").on("keydown", function(event){
	  // Allow controls such as backspace, tab etc.
	  var arr = [8,9,16,17,20,32,35,36,37,38,39,40,45,46];

	  // Allow letters
	  for(var i = 65; i <= 90; i++){
	    arr.push(i);
	  }

	  // Prevent default if not in array
	  if(jQuery.inArray(event.which, arr) === -1){
	    event.preventDefault();
	  }
	});
	
$('.alphaonly').on("input",function(){ 
	  // Allow controls such as backspace, tab etc.
	  var arr = [8,9,16,17,20,32,35,36,37,38,39,40,45,46];

	  // Allow letters
	  for(var i = 65; i <= 90; i++){
	    arr.push(i);
	  }

	  // Prevent default if not in array
	  if(jQuery.inArray(event.which, arr) === -1){
	    event.preventDefault();
	  }    
  });
  

</script>
<script>

function FilterInput(event) {
    var keyCode = ('which' in event) ? event.which : event.keyCode;

    isNotWanted = (keyCode == 69); //|| keyCode == 101
    return !isNotWanted;
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
				
				// alert("CourseList= "+CourseList);
								
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
			       
			}
   	 	  }
   	 	 
	});
	
	//$("#fees_form").submit()
	
}
function setCourseID()
{
	$("#hiddenCourseID").val($("#CourseID").val());
}

function setTemplateType()
{
	$("#hiddenTemplateType").val($("#TemplateType").val());
}

function retriveTemplatesDatas()
{
	$("#hiddenCurrentStatus").val("LoadTemplateDatas");
	var org=$("#hiddenOrgID").val();
	var course=$("#hiddenCourseID").val();
	var type=$("#hiddenTemplateType").val();
	if(org.length>0 && course.length>0 && type.length>0)
		$("#fees_form").submit()	
}
function addRowToTable()
{
	var table = document.getElementById('tblData');
	$("#hiddenRowCount").val(table.rows.length);
	
	$("#hiddenCurrentStatus").val("AddRow");
	$("#fees_form").submit()	
}
function callSaveAction()
{
	
	var table = document.getElementById('tblData');
	$("#hiddenRowCount").val(table.rows.length);
	
	$("#hiddenCurrentStatus").val("CallSaveAction");
	if(table.rows.length>1)
		$("#fees_form").submit()	

}
function callTextTypeChanged(TextTypeID,TextTypeValue,Row)
{
	
	var TextValueIDName="TextValue_"+Row;
	var DataTableIDName="DataTable_"+Row;
	var DataColumnIDName="DataColumn_"+Row;
	
	//alert(TextValueIDName+'  == '+DataTableIDName + ' == '+DataColumnIDName);
		if(TextTypeValue=="")
		{
			$("#"+TextValueIDName).val("");
			$("#"+TextValueIDName).attr("readonly","true");
			
			$("#"+DataTableIDName).val("");
			$("#"+DataColumnIDName).val("");
			$("#"+DataTableIDName).attr("disabled","true");
			$("#"+DataColumnIDName).attr("disabled","true");
		}
		else if(TextTypeValue=="Text")
		{
			
			$("#"+TextValueIDName).removeAttr("readonly");
			
			$("#"+DataTableIDName).val("");
			$("#"+DataColumnIDName).val("");
			$("#"+DataTableIDName).attr("disabled","true");
			$("#"+DataColumnIDName).attr("disabled","true");
		}else if(TextTypeValue=="Data" || TextTypeValue=="Convert")
		{
			$("#"+TextValueIDName).val("");
			$("#"+TextValueIDName).attr("readonly","true");
			
			$("#"+DataTableIDName).removeAttr("disabled");
			$("#"+DataColumnIDName).removeAttr("disabled");
		}
	
}

function callTxtValChanged(TxtVal)
{
	if(TxtVal.length>0)
	{
		// addRowToTable();
	}
}
function callTableNameChanged(table_name,RowVal)
{
//	alert("table_name == "+table_name);
	$.ajax({  
   	    type: "POST",  
   	 	url: '../JSP/getTableColumnsByTableName.jsp?table_name='+table_name,  
   	   	success: function(msg){
   	   		var result = msg.trim();
   	   		//alert(result);
			var columns= result.split(';');
			var ElementID="DataColumn_"+RowVal;
			$('#'+ElementID).empty()
			var ColumnSelectBox=document.getElementById(ElementID);
			
			var option = document.createElement("option");
		    option.text = "Choose Column Name";
		    option.value="";
		    ColumnSelectBox.add(option);
			
		    for (i = 0; i < columns.length; i++) { 
			   // alert(columns[i]);
			    var option1 = document.createElement("option");
			    option1.text = columns[i];
			    option1.value=columns[i];
			    ColumnSelectBox.add(option1);
			}
   	 	  }
   	 	 
	});
}
function callDataColumnChanged(Column)
{
	if(Column.length>0)
	{
		// addRowToTable();
	}
}
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
	margin:auto;
	height:auto;
}
.table_section1 {
	margin-top:0px;
	float:left;
}
.header_stop {
	height:36px;
}
table tr td {
	padding: 4px 4px 4px 10px;
	    border-right: 1px solid #bbbbbb;
}
.header_stop {
	margin-left:-6px;
}
.table_outer_row {
	border:1px solid #bbb;
}

input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}
input[type="number"] {
    -moz-appearance: textfield;
}
</style>
<script>
$(function(){  
$('.textonly').keydown(function(e) {
      if (e.shiftKey || e.ctrlKey || e.altKey) {
        e.preventDefault();
      } else {
        var key = e.keyCode;
        if (!((key == 8) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
          e.preventDefault();
        }
      }
    });
});
</script>
</html>

