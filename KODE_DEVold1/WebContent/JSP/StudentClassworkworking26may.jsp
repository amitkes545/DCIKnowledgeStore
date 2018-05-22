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
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../CSS/owner.css" rel="stylesheet"/>
<link href="../CSS/global.css" rel="stylesheet"/>
<script src="../JS/jquery.validate.js"></script>
 <style>
input.box.error {
    border: 1px solid red !important;
} 
</style>
</head>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	 $("#submit_btn").click(function(e) {
	    	var isValid = true;
		    // alert(isValid);
		        $('input[type="file"].box').each(function() {
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
	       
	        // here end working ok 
	       // alert(isValid);
     if (isValid == false){
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
			document.ksStore.action="/KODE_DEV/ControllerServlet/StudentClassWorkViewServlet?ses_id="+sesid+"";
			document.getElementById("studentview").style.display="block";
			//alert("in1");
			document.ksStore.submit();
			//alert("in1");
		}
	/* 	function sendfile()
		{
			//var sesid=document.getElementById("sesid").value;
			//alert(sesid);
			
		}
	 */	function clickUpload()
		{
			document.getElementById("studentview").style.display="none";
		}
		
	
$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
	
	 
	    });
</script>
<%
		String organizationId=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
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
<form name="ksStore" id="ksStore" method="post" enctype="multipart/form-data">
<div class="student_view_outer">
<h3>student View</h3>
<div class="class_text">
<a href="#"><img src="../Image/classwork.jpg" /></a>
</div>
	<div >
    	<div >
    	 <input type="text" id="sesid" name="sesid" value="<%=sesid%>" /> 
        	<input type="button" class="upload" value="Upload" onclick="clickUpload()"/>
               <input type="button"  onclick="clickKsId()" value="View"  class="view"/>
        </div>
        	<div class="student_upload_box">
            	<!-- <ul>
                	<li> -->
                	<input type="file" id="filename" name="filename" class="box" />
					<!-- <input type="file" name="file-7[]" id="file-7" class="inputfile inputfile-6" data-multiple-caption="{count} files selected" multiple />
					<label for="file-7">
					<span>Choose a file</span> <strong>
					Browse</strong></label> -->
				<!-- </li>
                    <li> -->
                    
<input id="submit_btn" type="button" value="Send" /></li>
                    
					<!--<li><button type="submit" 
					style="background: #3998ff;padding: 8px 25px;color: #fff;font-size: 14px;margin: auto;
					display: table;text-transform: uppercase;margin-top: 20px;text-decoration: none;">send</li>-->
              <!--   </ul> -->
            </div>
            <% %>
            <div id="studentview">
           
	<%	if(reportDomain!=null)
	 { 
		%>
		<table width="50%" id="tableId">
			 <tr class="row_head">

		<td>
		File Name
		</td>
		<td>
		Action
		</td>
		</tr>
		<%
			Iterator<FacultyShareFileDomain> iterator1 =reportDomain.iterator();
			 
				while(iterator1.hasNext())
				{
					FacultyShareFileDomain assetsDomain1=iterator1.next();
					//String[] s1=reportDomain1.getFilePath().split("/");
					//System.out.println("reportDomain1.getFileName()....="+assetsDomain1.getLibId());
					 String filesname=assetsDomain1.getFileName();
					 //System.out.println("filesname="+filesname);
					 String ffname=filesname.substring(1);
					 //System.out.println("ffname="+ffname);
					 String lfname=ffname.substring(0,ffname.length()-1);
					 //System.out.println("lfname="+lfname);
					 String[] file=lfname.split(",");
					 int filelength=file.length;
					 //System.out.println(file.length);
			 %>
			
		<tr class="result_row_tr">
	<% if(assetsDomain1.getFilePath()!=null) {%>
		<input type="hidden" id="filepath" name="filepath" value="<%=assetsDomain1.getFilePath()%>" />
		<input type="hidden" id="filename" name="filename" value="<%=assetsDomain1.getFileName()%>" />
		
		<%} %>
		<% for(int i=0;i<filelength;i++){ 
		System.out.println("filename="+file[i]);
		String filename=file[i].replace("\"", "");
		System.out.println("new filename="+filename);
		%>
		<td>
		<% if(assetsDomain1.getFilePath()!=null) {
		//System.out.println("in if");%>
		<a href="/KODE_DEV/ControllerServlet/StudentClassRoomFileDownloadServlet?filepath=<%=assetsDomain1.getFilePath()%>&filename=<%=filename%>&action=<%=assetsDomain1.getAction()%>"><%=filename%></a>
		<%}else{
			System.out.println("in elsre");%>
		<%=file[i]%>
		<%} %>
		</td>
		<td><input type="text" id="action" name="action" value="<%=assetsDomain1.getAction()%>"></td>
		</tr>
		 <%}} }%>
		
		</table>
            	
                	
            </div>
            <%-- <%} %> --%>
    </div>
</div>
</form>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $(".class_text").click(function(){
        $(".share_view_outer").show(100);
		$(".student_upload_box").hide(100);
		$(".student_table_box").hide(100);
		
    });
	 $(".upload").click(function(){
        $(".student_upload_box").show(100);
		$(".student_table_box").hide(100);
    });
	$(".view").click(function(){
        $(".student_table_box").show(100);
		$(".student_upload_box").hide(100);
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
		
</html>
