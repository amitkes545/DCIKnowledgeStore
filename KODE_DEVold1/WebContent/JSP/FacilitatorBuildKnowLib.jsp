
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.dao.FacilitatorSelectKnowStoreIdDao"%>
<%@ page import="com.kds.KODE_DEV.dao.FacilitatorBuildKnowLibDao"%>

<%@ page import="com.kds.KODE_DEV.domain.FacilitatorBuildKnowLibDomain"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/OwnerKnowReports.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<link href="../CSS/notification-new.css" rel="stylesheet"></link>
<script type="text/javascript" src="../JS/FacilitatorBuildLib.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>
<script src="../JS/jquery.validate.js"></script>
<script src="../JS/MessageFadeOut.js"></script>
<script type="text/javascript">
	function validation1() {
		
			/* document.KnowStoreReport.action = "/KODE_DEV/ControllerServlet/FacilitatorBuildKnowServlet1";
			document.KnowStoreReport.submit();
		 */
	}
</script>
<script>
$(document).ready(function() {
	 
$("#signupForm").validate({

    debug: false,
    rules: {
		libname: "required",
		libsize: "required",
		spaceuom: "required"
	},
	messages: {
		libname: "",
		libsize: "",
		spaceuom: "",
	}
	
});


 /* $( "#libname" ).keypress(function() {
	//alert("in");
	var libesizeval = $('#libname').val();
	//alert(libesizeval.trim());
	alert(libesizeval);
	var libname_trim=libesizeval.trim();
	
	//if ($.trim($(this).val()) == '') {
	 if (libname_trim == '') {
		alert("in if");
		 $(this).css({
	                    "border": "1px solid red",
	                    //"background": "#FFCECE"
	                });
		 return false;
	}

	}); */
	
});  

function SubmitIfValid()
{// alert("in");
	var isValid = true;

	 $('input[type="text"].box').each(function() {
			//alert("in 1");
			if ($.trim($(this).val()) == '') {
				 isValid = false;
			
		      //  alert("in 11");
		         $(this).css({
		             "border": "1px solid red",
		             //"background": "#FFCECE"
		         }); 
		        // return false;  
		     }
			 else {
	                $(this).css({
	                    "border": "",
	                   // "background": ""
	                });
	            }
		});
	 $('.fac_sel select.required').each(function() {
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
	 //alert(isValid);
	 if(!isValid) {
		 //alert("in f...");
		 return false;
	 }
	 else if(!$("#signupForm").valid() )
    	{return false;
    	//alett("in if");  
    	}
    return true;
	 
}

</script>


<style>
.error{color:#f00; float:left; width:250px;}
input.box.error {
    border: 1px solid red !important;
} .owner_setup_faculty{width: 22%; margin-top: 70px;}
.pp{    z-index: 1;
  /*   position: absolute; */
    right: 33px;
    margin-top: -14px;}
    .aa{margin-bottom: 25px;}
</style>
</head>
<%
   // HttpSession mess = request.getSession();
	
	String username=(String)session.getAttribute("username");
	if(username==null)
		response.sendRedirect("../JSP/error.jsp");
	String	userid=(String)session.getAttribute("userid");
	
	String orgid=(String)session.getAttribute("orgid");
	System.out.println("orgid is:"+orgid);
	
	FacilitatorSelectKnowStoreIdDao dobj2 = new FacilitatorSelectKnowStoreIdDao();
	//ArrayList<String> al = dobj2.fetchKsid(userid);
	ArrayList<String> al = dobj2.fetchKsid(orgid);
	System.out.println("al="+al);
	Iterator<String> iterator = al.iterator();
	System.out.println("iterator="+iterator);
		String ksid = iterator.next();
		System.out.println("ksid="+ksid);
	FacilitatorBuildKnowLibDao fbkDAo = new FacilitatorBuildKnowLibDao();		
	
	String allLibSize2 = fbkDAo.selectAllLibSpace(userid);
	System.out.println("allLibSize"+allLibSize2);
	
    //String knowStoreSize = fbkDAo.selectFacultyKnowSpace(userid);
   // int knowStoreSize = fbkDAo.selectFacultyKnowSpace(orgid);
	FacilitatorBuildKnowLibDomain fbkld= fbkDAo.selectFacultyKnowSpace(orgid,userid);
	int knowStoreSize=fbkld.getLibSize();
	String spaceUom=fbkld.getSpaceUom();
    System.out.println("knowStoreSize="+knowStoreSize);
    String availableSpace="";
    availableSpace=fbkDAo.getAvailableLibSpaceForFaculty(userid, orgid);
    System.out.println("knowStoreSize="+availableSpace);
    /* if(knowStoreSize==0){
    	
    	knowStoreSize= 0;
    	
    } */
    
    //System.out.println("know space faculty"+knowStoreSize);
	
	%>
<body>
	<div class="container">

		<%@include file="../JSP/headers.jsp"%>
		
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
      </div>
		<div style="clear: both; position: absolute;"></div>
		
	<div class="faculty_mod" style="width: 300px;position:static;float:right;margin-right:15px;margin-top:70px;">
	<%
						if (request.getAttribute("LibSize") != null) {
					%>
					<p class="failure">
						<%=request.getAttribute("LibSize")%>
					</p>
					
					<% } %>
					<%
						if(request.getAttribute("FaceLibNew") != null) {
					%>
					<p class="failure">
						<%=request.getAttribute("FaceLibNew")%>
					</p>
					
					<% } %>
					<%
						if(request.getAttribute("FacultySuccess") != null) {
					%>
					<p class="success">
						<%=request.getAttribute("FacultySuccess")%>
					</p>
					<%
						} else if(request.getAttribute("FacultyFailure") != null) {
					%>
					<p class="failure">
						<%=request.getAttribute("FacultyFailure")%>
					</p>
					<%
						}
					%>
			<p class="strong aa">Build Library </p>
			<p class="pp">  Total space allocated: <%= knowStoreSize%> <%=spaceUom %></p><br>
		<!--  	<p class="pp">  Available Space : <%= ((knowStoreSize) - Integer.parseInt(allLibSize2)) %> GB</p> -->
		     <p class="pp">  Available Space : <%= availableSpace %>
			<form name="KnowStoreReport" id="signupForm" action = "/KODE_DEV/ControllerServlet/FacilitatorBuildKnowServlet1" method="post">

				<table align="center">
					<%-- <%! FacilitatorKnowReportDomain oDomain=new  FacilitatorKnowReportDomain();%> --%>
					
					
						<tr>
						<td><input type="hidden" name="ksid" id="ksid" class="box_lng" value=<%=ksid%>>
					<tr>
						<td><input type="text" maxlength="30" placeholder="Enter Library Name*" class="box" name="libname" id="libname" ></td>
					</tr>
					<tr>
						<td><input type="text" maxlength="2" name="libsize" class="box" id="libesize" min="1" onkeypress='return event.charCode >= 48 && event.charCode <= 57' placeholder="Enter Size*"/></td>
					</tr>
					<tr>
						<td class="fac_sel"><select name="spaceuom" id="spaceuom" class="required">
							<option value="">Select Space UOM</option>
							<option value="KB">KB</option>
							<option value="MB">MB</option>
							<option value="GB">GB</option>
							<option value="TB">TB</option>
						</select></td>
					</tr>
					<tr>
						<td><input class="add_btn1"
							style="width: 265px!important" type="submit" value="Build" onclick="return SubmitIfValid();"> 
							</td>
					</tr>
				</table>
			</form>
		</div>
<%@ include file="../JSP/FooterViews.jsp"%>
	</div>
</body>
<style>
.fadehide {
position:absolute;
top:180px;
font-fimaly:arial;
right:360px;
}
</style>
</html>