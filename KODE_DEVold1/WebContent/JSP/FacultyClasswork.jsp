<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.kds.KODE_DEV.dao.CollaborateDao" %>
<%@page import="com.kds.KODE_DEV.domain.*"%>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorSelectKnowStoreIdDao"%>
<%@page import="java.util.*" %>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorSelectKsidKStoreDao" %>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorManageLibDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../CSS/dci-style.css" type="text/css" />
<title>Untitled Document</title>
</head>
<!-------This code only 26-5-2017 working for scroll option---------- -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script>
$(document).ready(function()
			{
				$('#faculty_search1').keyup(function()
				{
					searchTable1($(this).val());
				});
				
				$('#faculty_search2').keyup(function()
				{
					searchTable2($(this).val());
				});
				$('#faculty_search3').keyup(function()
						{
							searchTable3($(this).val());
						});
				
			});
			function searchTable1(inputVal)
			{
				var table = $('#faculty_table1');
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
						//if(found == true)$('tr').show();else $(row).hide();
					}
				});
			}
			function searchTable2(inputVal)
			{
				var table = $('#faculty_table2');
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
						//if(found == true)$('tr').show();else $(row).hide();
					}
				});
			}
			function searchTable3(inputVal)
			{
				var table = $('#faculty_table3');
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
						//if(found == true)$('tr').show();else $(row).hide();
					}
				});
			}
			
</script>



		<script type="text/javascript">
		$(document).ready(function() {
			  $( ".submit_btn" ).click(function(e) {
				//  alert("in");
				  var isValid = true;
				/*   if($("input:radio[name='ksfile']").is(":checked")) {
					 // alert("in radio");
			    	   $("#displaymode").hide();
		  		  }
			       else
			    	   {
			    	   isValid = false;
			    	   $('#displaymode').slideDown().html('<span id="error"><br/>Select Mode</span>');
				        return false;
			    	   }  */ 
		       
			        if($('.chekbox_student:checkbox:checked').length < 1)
		         	{
			        	//alert("in checkbox student");
			        	isValid = false;
			            $(".a").addClass('b');
			            $('#display').slideDown().html('<span id="error">Choose any One Participant</span>');
			            return false;
			        }
			        else
			        	{
			        	$(".a").removeClass('b');
			            $("#display").hide();
			        	}
			        if($('.chekbox_file:checkbox:checked').length < 1)
		         	{
			        	//alert("in checkbox if");
			        	isValid = false;
			            $(".afile").addClass('bfile');
			            $('#displayfileerror').slideDown().html('<span id="error">Choose any One File</span>');
			            return false;
			        }
			        else
			        	{
			        	$(".afile").removeClass('bfile');
			            $("#displayfileerror").hide();
			        	}
			        //alert("isValid="+isValid);
			        if (isValid == false){
			            e.preventDefault();
			        }
			        else {
			        	var sesid=document.getElementById("sesid").value;
			        	// alert('Thank you for submitting')
			        	 document.ksStore.action="/KODE_DEV/ControllerServlet/FacultyShareFileServlet?ses_id="+sesid;
			 			document.ksStore.submit();
			        }
			        
			        
			        
			  
		   });
		}); function toggle(source) {
			// alert("in f");
		        var aInputs = document.getElementsByTagName('input');
		        for (var i=0;i<aInputs.length;i++) {
		            if (aInputs[i] != source && aInputs[i].className == source.className) {
		                aInputs[i].checked = source.checked;
		            }
		        }
		    }
		//share Code
		function clickKsId(){
			//alert("in");
			var sesid=document.getElementById("sesid").value;
			document.ksStore.action="/KODE_DEV/ControllerServlet/FacultyShareViewServlet?ses_id="+sesid+"";
			//alert("in1");
			document.getElementById("studentList").style.display="block";
			//document.getElementById("buttonSend").style.display="block";
			//alert("in111");
			//document.getElementById("radioShow").style.display="block";
			//document.getElementById("download").style.display="block";
			
			//alert("in13");
			document.ksStore.submit();
			//alert("in1");
		}
		//view Code
		function viewfile()
		{
			document.getElementById("studentList").style.display="none";
			var sesid=document.getElementById("sesid").value;
			//document.getElementById("buttonSend").style.display="none";
			//document.getElementById("radioShow").style.display="none";
			//document.getElementById("download").style.display="none";
		//	alert("in f"+sesid);
			document.ksStore.action="/KODE_DEV/ControllerServlet/FacultyClassWorkViewServlet?ses_id="+sesid+"";
			//alert("in");
			document.ksStore.submit();
		}
	
		
		function downloadfile(id)
		{
			//var filepathview=document.getElementById("filepathview").value;
			//alert(filepathview);
			var fname=id;//document.getElementById("fname").value;
			//alert(id);
			document.ksStore.action="/KODE_DEV/ControllerServlet/FacultyViewFileDownloadServlet?fname="+fname;
			document.ksStore.submit();
		}
		</script>
		<script>

$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow"); 
	    });
</script>
<%

		String organizationId=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		String selectedaction=request.getParameter("selected");
		System.out.println("viewselected="+selectedaction);
		if(username==null)
			response.sendRedirect("../JSP/error.jsp");
		
		 String sesid=request.getParameter("ses_id");
		 System.out.println("sesid in jsp=="+sesid);
		//FacilitatorSelectKnowStoreIdDao KsIdDynamic = new FacilitatorSelectKnowStoreIdDao();
		//String newKsid =KsIdDynamic.fetchDynamicKsid(userid);
		
		//FacilitatorKAssetsReportDomain setKsid = new FacilitatorKAssetsReportDomain();
		//setKsid.setKsId(newKsid);

		ArrayList<FacilitatorKAssetsReportDomain> reportDomain=new ArrayList<FacilitatorKAssetsReportDomain>();
		reportDomain = (ArrayList<FacilitatorKAssetsReportDomain>)request.getAttribute("aValue");
		System.out.println("rd="+reportDomain);
		
		ArrayList<ClassWorkFileDomain> CWDomain=new ArrayList<ClassWorkFileDomain>();
		CWDomain = (ArrayList<ClassWorkFileDomain>)request.getAttribute("CWValue");
		System.out.println("rd1="+CWDomain);
	   
	%>
<body>

 <form name="ksStore" method="post">
<input type="hidden" id="sesid" name="sesid" value="<%=sesid%>" />
<div class="student_view_outer">

<div class="pop_header">
		<div class="pop_logo"><img src="../Images/book_icon.png"/></div>
		<div class="pop_logo_text">KLASSWORK</div>
	</div>
<h3>faculty View</h3>
<%if (request.getAttribute("FacultySuccess")!= null) { 
	 String msg=(String)request.getAttribute("FacultySuccess");
	 //System.out.println("msg=="+msg);
	 %>
	 <p class="su autohide"><%= request.getAttribute("FacultySuccess") %></p>					
	 <%
	 } %>
	<div class="student_view_inner row">
	
    	<div class="faculty_share_view_btn">
    	<%if(selectedaction == null){ %>
        	<input id="share_button" type="button" value="Share" onclick="clickKsId()" class="faculty_share_btn" ></a>
            <input id="view_button" type="button" Value="View" onclick="viewfile()" class="faculty_view_bnt"></a>
    	<%}else if(selectedaction.equals("view")){ %>
        	<input id="share_button" type="button" value="Share" onclick="clickKsId()" class="faculty_share_btn"></a>
            <input id="view_button" type="button" Value="View" onclick="viewfile()" class="faculty_view_bnt" style="background-color:#e0ecfa;" ></a>
		<%}else if(selectedaction.equals("share")){ %>
        	<input id="share_button" type="button" value="Share" onclick="clickKsId()" class="faculty_share_btn" style="background-color:#e0ecfa;" ></a>
            <input id="view_button" type="button" Value="View" onclick="viewfile()" class="faculty_view_bnt"></a>
		<%}else{ %>
        	<input id="share_button" type="button" value="Share" onclick="clickKsId()" class="faculty_share_btn" ></a>
            <input id="view_button" type="button" Value="View" onclick="viewfile()" class="faculty_view_bnt"></a>
		<%} %>
        </div>
        	<div id="studentList">   
        	 
                	
	<%// System.out.println("reportDomain="+reportDomain);	 
	if(reportDomain!=null)
			 {
		%>
		<!-------This code only 24-5-2017 working for scroll option---------- -->
		
		<div class="table_cover_div">
		<p id="displayfileerror" style="font-size: 12px;color: red;"></p>	
		<div class="table_row_one">
		<div class="table_search_filter" style="padding-left:0px;padding-top:0px;">
			<ul>
				<li><label>Search:</label><input id="faculty_search1" type="text" placeholder="Search File"></li>
			</ul>
		</div>
		  
		<div class="table_header">
			<table width="100%"  border="1" id="tableId">
			 <tr class="row_head">

		<td style="width:200px;font-weight:bold;color:#000;border-right:1px solid #b6b9bc;background:#e0ecfa;padding:9px 5px 9px 15px;">
		File Name
		</td>
		<td style="width:70px;font-weight:bold;color:#000;border-right:1px solid #b6b9bc;background:#e0ecfa;padding:9px 5px 9px 15px;">
		Select
		</td>
		</tr>
		</table>
		</div>
		<div class="table_scroll_option" id="demo2">
		<table width="100%" id="faculty_table1">
		<%
		//System.out.println("in if");
			Iterator<FacilitatorKAssetsReportDomain> iterator1 =reportDomain.iterator();
			 
				while(iterator1.hasNext())
				{
					//System.out.println("in while");
					FacilitatorKAssetsReportDomain assetsDomain1=iterator1.next();
					//String[] s1=reportDomain1.getFilePath().split("/");
					//System.out.println("reportDomain1.getFileName()....="+assetsDomain1.getLibId());
			 %>
		<tr class="result_row_tr">
	<%
	String filepath_name=assetsDomain1.getFilePath();
	String filepath=filepath_name.substring(0, filepath_name.lastIndexOf('/'));
	%>
		<input type="hidden" id="filepath" name="filepath" value="<%=filepath%>" />
		<td style="width:158px;min-height:31px;float:left;">
		<%=assetsDomain1.getFileName()%>
		</td>
		<td class="afile" style="width:70px;text-align:center;">
		<%-- <input class="chekbox_file" type="checkbox" name="filename" id="filename" value="<%=assetsDomain1.getFileName()%>"> --%>
		<input class="chekbox_file" type="checkbox" name="filename" id="filename" value="<%=filepath_name%>">
		</td>
		</tr>
		 <%} %>
             	</table> 
		</div> 
		</div>   
		<p id="display" style="font-size: 12px;color: red;"></p>  
             	<div class="table_row_two">
             	<div class="table_search_filter" style="padding-left:0px;padding-top:0px;">
					<ul>
						<li><label>Search:</label><input id="faculty_search2" type="text" placeholder="Search Student"></li>
					</ul>
				</div>
				
             		<div class="table_header">
             		<table width="100%"  border="1" >	
				<tr>
					<th style="width:200px;background:#e0ecfa;padding:9px 5px 9px 15px;color:#000;text-align:left;border-right: 1px solid #b6b9bc;">Student Name</th>
					<th class="tab" style="width:70px;text-align:center;background:#e0ecfa;padding:9px 5px 9px 15px;color:#000;text-align:left;">Select</th>
				</tr>
						</table>
             		</div>
						<div class="table_scroll_option" id="demo3">
							<table width="100%" id="faculty_table2">
							 <%CollaborateDao dao= new CollaborateDao();
							 AdminDomain adminDomain=new AdminDomain();
					           ArrayList<AdminDomain>al=new ArrayList<AdminDomain>();
							    al=dao.getStudentId(userid,organizationId);
							    Iterator<AdminDomain> it= al.iterator();
							    %>
							    <tr><td style="width:158px;float:left;min-height:31px;">All</td>
							    	<td class="a" style="width: 100%;text-align:center;"><input class="chekbox_student" type="checkbox" onClick='toggle(this)'></td>
							    	</tr>
							    <%
							  while(it.hasNext()) 
							    {
								  adminDomain=it.next();
								  String name=adminDomain.getAdminName();
								  String Id=adminDomain.getAdminId();
								  String idAndName=name+"("+Id+")";
								 // System.out.println("name="+name);
								 // System.out.println("Id="+Id);
							    	%>
							    	
							    	<tr>
									<td style="width:158px;float:left;min-height:31px;"><%=idAndName%></td> 
									<td class="a" style="text-align:center;"><input class="chekbox_student" type="checkbox" name="selectstudent" id="selectstudent" value="<%=Id%>"></td>
									
							   </tr>
							 <%   }%> 
							   
</table>
						</div>
             	</div>
             	<div class="radio_btn_list">
			<input type="radio" id="open" name="ksfile"  value="Read-Only" onclick="ksclick()" /><label>Read-Only</label>
	    <input type="radio" id="download" name="ksfile" value="Downloadable" name="student" onclick="ksclick()"/>
	    <label>Downloadable</label>
		</div>
			<div class="send_button" style="margin-top:-13px;">
				<input class="add_btn1 submit_btn" id="buttonSend" type="button" value="Send" />
			</div>
		</div>
            
 <!-------This code only 23-05-2017 working for scroll option---------- -->
                    		
	<%	} if(CWDomain!=null)
			 {
		%>
		
		<!-------This code only 23-05-2017 working for scroll option---------- -->
		
		<div class="table_header">
		<div class="table_search_filter" style="padding-left:0px;">
			<ul>
				<li><label>Search:</label><input id="faculty_search3" type="text" placeholder="Search File"></li>
			</ul>
		</div>
		 <!--  <p id="displaymode"></p>  --> 
			<table width="100%"  border="1" id="tableId1">
			 <tr height="25px"></tr>
			 <tr class="row_head">

		<td style="width:187px;font-weight:bold;color:#000;background:#e0ecfa;text-align:left;border-right: 1px solid #b6b9bc;padding: 9px 5px 9px 15px;">
		File Name
		</td>
		<td style="width:137px;font-weight:bold;color:#000;background:#e0ecfa;text-align:left;border-right: 1px solid #b6b9bc;padding: 9px 5px 9px 15px;">
		File Type
		</td>
		<td style="width:190px;font-weight:bold;color:#000;background:#e0ecfa;text-align:left;border-right: 1px solid #b6b9bc;padding: 9px 5px 9px 15px;">
		Student Name
		</td>
		</tr>
		</table>
		</div>
		
		<!-------This code only 23-5-2017 working for scroll option---------- -->
		
		<!-------This code only 23-5-2017 working for scroll option---------- -->
		<div class="table_scroll_option2" id="demo4" style="width:499px;height: 214px;">
			<table width="100%" id="faculty_table3" style="width: 497px;">
		<%
			Iterator<ClassWorkFileDomain> iterator2 =CWDomain.iterator();
			 
				while(iterator2.hasNext())
				{
					ClassWorkFileDomain CWDomain1=iterator2.next();
					//String[] s1=reportDomain1.getFilePath().split("/");
					//System.out.println("reportDomain1.getFileName()....="+assetsDomain1.getLibId());
					String filename=CWDomain1.getFileName();
					String file_name = filename.substring(0, filename.lastIndexOf('.'));
			String ext = filename.substring(filename.lastIndexOf('.')+1);
			 %>
		<tr class="result_row_tr">
	
		<input type="hidden" id="filepathview" name="filepathview" value="<%=CWDomain1.getFilePath()%>" />
		<td style="width:180px;float:left;border-right: 1px solid #b6b9bc;">
		<a href="#" id="fname" name="fname" onclick="downloadfile('<%=CWDomain1.getFilePath()%>')"><%=file_name%></a>
		</td>
		<td style="width:100%;border-right: 1px solid #b6b9bc;">
		<a href="#" id="ext" name="ext" onclick="downloadfile(this.innerHTML)"><%=ext%></a>
		</td>
		<td style="width:182px;float:left;"><%=CWDomain1.getUploadedBy()%></td>
		</tr>
		 <%} }%>
		
		</table>
		</div>
		<!-------This code only 23-5-2017 working for scroll option---------- -->
		
		<!-- <div id="radioShow" style="display:none;"> -->
		
            </div>
           <!--  <div class="student_table_box">
            	
            </div> -->
			
			
    </div>
</div>
</form>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <script src="../JS/jquery.phancy.js"></script> 
<script>
$(document).ready(function(){
    $(".class_text").click(function(){
        $(".share_view_outer").show(100);
		$(".student_upload_box11").hide(100);
		$(".student_table_box").hide(100);
    });
	 
	$(function() {
		$( "#demo2,#demo3,#demo4" ).customScroll({ scrollbarWidth: 5 });
	});
});
</script>


<script>
'use strict';

;( function ( document, window, index )
{
	var inputs = document.querySelectorAll( '.inputfile' );
	Array.prototype.forEach.call( inputs, function( input )
	{
		var label	 = input.nextElementSibling,
			labelVal = label.innerHTML;

		input.addEventListener( 'change', function( e )
		{
			var fileName = '';
			if( this.files && this.files.length > 1 )
				fileName = ( this.getAttribute( 'data-multiple-caption' ) || '' ).replace( '{count}', this.files.length );
			else
				fileName = e.target.value.split( '\\' ).pop();

			if( fileName )
				label.querySelector( 'span' ).innerHTML = fileName;
			else
				label.innerHTML = labelVal;
		});

		// Firefox bug fix
		input.addEventListener( 'focus', function(){ input.classList.add( 'has-focus' ); });
		input.addEventListener( 'blur', function(){ input.classList.remove( 'has-focus' ); });
	});
}( document, window, 0 ));
</script>
       <script>
		(function(e,t,n){var r=e.querySelectorAll("html")[0];r.className=r.className.replace(/(^|\s)no-js(\s|$)/,"$1js$2")})(document,window,0);
		</script>
		<style>
		.radio_btn_list{
		display:block;
		width:235px;
		}
		.send_button{
		width: auto;
		float:right;
		margin-top:-8px;
		}
		</style>
		
</html>
