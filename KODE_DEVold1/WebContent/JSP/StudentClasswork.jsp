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
<link rel="stylesheet" href="../CSS/pdftest.css" type="text/css" />
<title>Untitled Document</title>
</head>
<!-------This code only 26-5-2017 working for scroll option---------- -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script>
$(document).ready(function() {
	 $("#submit_btn").click(function(e) {
	    	var isValid = true;
		     //alert(isValid);
		        $('input[type="file"]#file-7').each(function() {
		            if ($.trim($(this).val()) == '') {
		                isValid = false;
		              //  alert(isValid);
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
	       
	        // here end working ok 
	      //  alert("out="+isValid);
    if (isValid == false){
    	alert("Please select file");
	            e.preventDefault();
	        }
	        else {
	        	// alert('Thank you for submitting');
	        	 document.ksStore.action="/KODE_DEV/ControllerServlet/StudentClassWorkSendFileServlet";//?ses_id="+sesid+"";
	 		//	alert("in");
	 			document.ksStore.submit();
	        }
	    });
});

$(document).ready(function()
			{
				$('#search_table').keyup(function()
				{
					searchTable($(this).val());
				});
			});
			function searchTable(inputVal)
			{
				var table = $('#table_filter');
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
<!-------This code only 26-5-2017 working for scroll option---------- -->
<script type="text/javascript">
		 function toggle(source) {
			// alert("in f");
		        var aInputs = document.getElementsByTagName('input');
		        for (var i=0;i<aInputs.length;i++) {
		            if (aInputs[i] != source && aInputs[i].className == source.className) {
		                aInputs[i].checked = source.checked;
		            }
		        }
		    }
		function clickKsId(){
			//alert("in");
			 	var sesid=document.getElementById("sesid").value;
			 //alert("in1");
			document.ksStore.action="/KODE_DEV/ControllerServlet/StudentClassWorkViewServlet?ses_id="+sesid+"";
			//alert("in2");
			document.getElementById("studentview").style.display="block";
			//alert("in11");
			document.ksStore.submit();
			
			//alert("in13");
		}
		/* function sendfile()
		{
			document.ksStore.action="/KODE_DEV/ControllerServlet/StudentClassWorkSendFileServlet";
		//	alert("in");
			document.ksStore.submit();
		} */
		function clickUpload()
		{
			document.getElementById("studentview").style.display="none";
			document.getElementById("view").style="background-color:#e0eft;";
			document.getElementById("upload_button").style="background-color:#e0ecfa;";
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
		String viewselected=request.getParameter("selected");
		System.out.println("viewselected="+viewselected);
		if(username==null)
			response.sendRedirect("../JSP/error.jsp");
		 String sesid=request.getParameter("ses_id");
		 System.out.println("sesid in student=="+sesid);
		FacilitatorSelectKnowStoreIdDao KsIdDynamic = new FacilitatorSelectKnowStoreIdDao();
		//String newKsid =KsIdDynamic.fetchDynamicKsid(userid);
		
		FacultyShareFileDomain setKsid = new FacultyShareFileDomain();
		//setKsid.setKsId(newKsid);

		ArrayList<FacultyShareFileDomain> reportDomain=new ArrayList<FacultyShareFileDomain>();
		reportDomain = (ArrayList<FacultyShareFileDomain>)request.getAttribute("aValue");
		System.out.println("rd="+reportDomain);
	   
	%>
<body>

<form name="ksStore" id="ksStore" method="post" enctype="multipart/form-data">
<div class="student_view_outer">
<%@include file="../JSP/PopupReadOnly.jsp"%>
<h3>student View</h3>

<div class="class_text">
<a href="#"><img src="../Image/classwork.jpg" /></a>
</div>
	<div >
	
	<div class="pop_header">
		<div class="pop_logo"><img src="../Images/book_icon.png"/></div>
		<div class="pop_logo_text">KLASSWORK</div>
	</div>
	<%if (request.getAttribute("FacultySuccess")!= null) { 
	 String msg=(String)request.getAttribute("FacultySuccess");
	 System.out.println("msg=="+msg);
	 %>
	 <p class="su autohide"><%= request.getAttribute("FacultySuccess") %></p>					
	 <%
	 }else if (request.getAttribute("FacultyFailure")!= null) { 
		 String msg1=(String)request.getAttribute("FacultyFailure");
		 System.out.println("msg1=="+msg1);
		 %>
		 <p class="su autohide"><%= request.getAttribute("FacultyFailure") %></p>					
		 <%
		 } %>
    	<div class="upload_and_view_btn">
    	 <input type="hidden" id="sesid" name="sesid" value="<%=sesid%>" /> 
        	<input id="upload_button" type="button" class="upload" value="Upload" onclick="clickUpload()"/>
        	<%  if(viewselected!=null){
        		//System.out.println("viewselected in 1 if="+viewselected);
        		if(viewselected.equalsIgnoreCase("view")){
        			//System.out.println("viewselected in 2 if="+viewselected);
        		%>
               <input type="button" style="background-color:#e0ecfa;" onclick="clickKsId();"  value="View"  class="view" id="view"/>
               <%}else{
           		 %>
           		 <input type="button"  onclick="clickKsId();"  value="View"  class="view" id="view"/>
           		 <%} }else{%>
           		  <input type="button"  onclick="clickKsId();" value="View"  class="view" id="view"/>
           		  <%} %>
        </div>
        	<div class="student_upload_box">
            	  <div class="student_upload_inner">
            	 	<ul>
                	<li>  
                	
                	<!-- <input type="file" id="filename" name="filename" class="inputfile inputfile-6" /> -->
					  <input type="file" name="file-7" id="file-7" class="inputfile inputfile-6" data-multiple-caption="{count} files selected" multiple />
					<label  for="file-7">
					<span >No files selected</span> <strong>
					Browse</strong></label> 
				 </li>
                    
<!-- <input id="submit_btn" type="button" value="Send"  /></li> -->
                    
					
                  </ul>
            	 </div> 
                 <div class="upload_send_btn">
                 	<button id="submit_btn" type="submit">send</button>
                 </div>
            </div>
            <% %>
            <div id="studentview">
           
	<%	if(reportDomain!=null)
	 { 
		%>
		<div class="table_search_filter">
			<ul>
				<li><label>Search:</label><input id="search_table" type="text" placeholder="Search File" /></li>
			</ul>
		</div>
		<!-------This code only 26-5-2017 working for scroll option---------- -->
		
		<div class="table_header" style="width:505px;margin-left:17px;">
		<table width="100%" id="tableId" class="popup_view_table">
			 <tr class="row_head">

		<td style="font-weight:bold;width:187px;">
		File Name
		</td>
		<td style="font-weight:bold;width:137px;">
		File Type
		</td>
		<td style="font-weight:bold;width:190px;">
		Properties
		</td>
		</tr>
		</table>
		</div>
		
		<!-------This code only 26-5-2017 working for scroll option---------- -->
		
		
		<!-------This code only 26-5-2017 working for scroll option---------- -->
		<div class="table_scroll_option3" id="demo" style="width:499px!important;">
			<table width="100%" id="table_filter">
		<%
			Iterator<FacultyShareFileDomain> iterator1 =reportDomain.iterator();
			 
				while(iterator1.hasNext())
				{
					System.out.println("in while i njsp");
					FacultyShareFileDomain assetsDomain1=iterator1.next();
					//String[] s1=reportDomain1.getFilePath().split("/");
					//System.out.println("reportDomain1.getFileName()....="+assetsDomain1.getLibId());
					 String filesname=assetsDomain1.getFileName();
					 System.out.println("file path="+assetsDomain1.getFilePath());
					 String ffname=filesname.substring(1);
					 //System.out.println("ffname="+ffname);
					 String lfname=ffname.substring(0,ffname.length()-1);
					 //System.out.println("lfname="+lfname);
					 String[] file=lfname.split(",");
					 int filelength=file.length;
					 //System.out.println(file.length);

					 String filespath=assetsDomain1.getFilePath();
					 System.out.println("file path="+assetsDomain1.getFilePath());
					 String ffpath=filespath.substring(1);
					 //System.out.println("ffname="+ffname);
					 String lfpath=ffpath.substring(0,ffpath.length()-1);
					 //System.out.println("lfname="+lfname);
					 String[] path=lfpath.split(",");
					 //int filelength=file.length;
					 //System.out.println(file.length);
			 %>
			 
			
		<tr class="result_row_tr">
	<% if(assetsDomain1.getFilePath()!=null) {
	System.out.println("in if ");
	 System.out.println("file path........="+assetsDomain1.getFilePath());
	 System.out.println("file name........="+assetsDomain1.getFileName());%>
	<!-- 
		<input type="text" id="filepath" name="filepath" value="<%=assetsDomain1.getFilePath().replace("\"", "")%>" />
		<input type="text" id="filename" name="filename" value="<%=assetsDomain1.getFileName().replace("\"", "")%>" />
	 -->	
		<%} %>
		<% for(int i=0;i<filelength;i++){ 
			String filename=file[i].replace("\"", "");
			System.out.println("new filename="+filename);
			String filepath=((path[i].replace("\"", "")).replace("{", "")).replace("}", "");
			String file_name = filename.substring(0, filename.lastIndexOf('.'));
			String ext = filename.substring(filename.lastIndexOf('.')+1);
			//System.out.println("filename without extension:="+file_name);
			//System.out.println("filename extension:="+filename);
	//	System.out.println("filename="+file[i]);%>
		<td style="font-size:12px;width:179px;float:left;min-height:34px;word-wrap: break-word;">
		<div class="table_space"></div>
		<% if(assetsDomain1.getFilePath()!=null) {
		//System.out.println("in if");
		System.out.println("dom FilePath:"+assetsDomain1.getFilePath());%>
		<%=file_name%>
		<%}else{
			System.out.println("in elsre");%>
		<%=file[i]%>
		<%} %>
		</td>
		<td style="font-size:12px;width:100%;">
		<%=ext%>
		
		</td>
<!-- //Show alway downlodable -->
		<%-- <td style="font-size:12px;width:33.33%;">
		<input style="width:154px;float:left;"type="text" id="action" name="action" value="<%=assetsDomain1.getAction()%>">
		<input style="width:154px;float:left;"type="text" id="action" readonly="readonly" name="action" value="Downloadable" >
		<a href="/KODE_DEV/ControllerServlet/StudentClassRoomFileDownloadServlet?filepath=<%=filepath%>&filename=<%=filename%>&action=<%=assetsDomain1.getAction()%>&ses_id=<%=sesid%>"><img src="../Images/download_icon.png" /></a>
		</td> --%>
		<td style="font-size:12px;width:33.33%;">
		<%-- <input style="width:154px;float:left;"type="text" id="action" name="action" value="<%=assetsDomain1.getAction()%>"> --%>
		<%
		String action=assetsDomain1.getAction();
		System.out.println("Action....:"+action);
		if(action!=null && !action.equalsIgnoreCase("null")){
		%>
		<input style="width:154px;float:left;"type="text" id="action" readonly="readonly" name="action" value="<%=assetsDomain1.getAction()%>" >
		<% }else{
		System.out.println("in else Action....:"+action);%>
		<input style="width:154px;float:left;"type="text" id="action" readonly="readonly" name="action"  >
		<%}
		
		
		//
		if(action!=null){
			//System.out.println("in if ............. null");
		if(action.equalsIgnoreCase("Downloadable")){
			//System.out.println("in if");
		%>
		<a href="/KODE_DEV/ControllerServlet/StudentClassRoomFileDownloadServlet?filepath=<%=filepath%>&filename=<%=filename%>&action=<%=assetsDomain1.getAction()%>&ses_id=<%=sesid%>">
		<img src="../Images/download_icon.png" />
		</a>
		<%}else if(action.equalsIgnoreCase("Read-Only")){ 
		
	//	System.out.println("in getFileName"+assetsDomain1.getFileName());
		//System.out.println("in getFilePath"+assetsDomain1.getFilePath());
		%>
		<a href="#" onclick="popup_read_only('<%=filename%>','<%=filepath%>')">
		<img src="../Images/readonly_icon.png" /></a>
		<%} }%>
	
		</td>
<%-- //Comment action from db
		<td style="font-size:12px;width:33.33%;">
 		<input style="width:154px;"type="text" id="action" name="action" value="<%=assetsDomain1.getAction()%>">
		<%
		String action_value=assetsDomain1.getAction();
		if(action_value.equalsIgnoreCase("Read-Only")){ %>
		<a href="#"><img src="../Images/readonly_icon.png" /></a>
		<%}else if(action_value.equalsIgnoreCase("Downloadable")){ %>
		<a href="#"><img src="../Images/download_icon.png" /></a>
		<%} %>
		</td>
//End		
 --%>		
 		</tr>
		 <%}} }%>
		
		</table>
		</div>
		
		<!-------This code only 26-5-2017 working for scroll option---------- -->
            	
                	
            </div>
            <%-- <%} %> --%>
    </div>
</div>
</form>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <script src="../JS/jquery.phancy.js"></script> 
<script>
$(document).ready(function(){
    $(".class_text").click(function(){
        $(".share_view_outer").show(500);
		$(".student_upload_box").hide(500);
		$(".student_table_box").hide(500);
		
    });
	 $(".upload").click(function(){
		 //$(".upload").css("background-color", "#51a6c5");
		 //$("#view").css("background-color", "#9d9da1");
        $(".student_upload_box").show(500);
		$(".student_table_box").hide(500);
    });
	
	$(function() {
		$( "#demo" ).customScroll({ scrollbarWidth: 5 });
	});
});
</script>
<script>

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
		
</html>
