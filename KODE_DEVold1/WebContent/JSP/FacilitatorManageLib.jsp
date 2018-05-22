<%@ page errorPage="../JSP/error.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.kds.KODE_DEV.domain.*"%>
<%@page import="com.kds.KODE_DEV.dao.FacilitatorSelectKsidKStoreDao"%>
<%@page import="com.kds.KODE_DEV.dao.*"%>
<%@page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../JS/FacilitatorKnowSetup.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
<script type="text/javascript" src="../JS/jquery.js"></script>

<!-- <link href="../CSS/owner.css" rel="stylesheet"/>
<link href="../CSS/global.css" rel="stylesheet"/> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<script src="../JS/datetimepicker_css.js"></script>
<script type="text/javascript" src="../JS/validatePostSession.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>
<link href="../CSS/jquery.datetimepicker.css" rel="stylesheet"></link>
<link href="../CSS/table_scroll.css" rel="stylesheet"/>
<link href="../CSS/design-common.css" rel="stylesheet"/>
<link href="../CSS/notification-new.css" rel="stylesheet"></link>
<script src="../JS/MessageFadeOut.js"></script>
<!-- <script type="text/javascript" src="../JS/jquery.datetimepicker.js">
</script> -->

<style type="text/css">
.search_result{padding: 0px 10px;margin-bottom: 25px;max-height: 310px;
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

.postpon_mod{
width: 95% !important;
    box-shadow: 5px 5px 5px 5px #FFF;
    margin: 15px auto 0px;
    padding: 15px 1px 0px 0px;
    border: 3px solid #C2C2C2;
    background-color: #FCF7F7;
    border-radius: 4px;
    /* opacity: 1; */
    margin-top: 72px;
    
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
margin-top: -33px;
position: absolute;
left: 1018px;
}
.opt{
width: 265px !important;
padding: 3px !important;
height: 31px;
border: 1px solid #C2C2C2;
font-family: regular;
border-radius: 4px;
}
.box1
{
width: 250px!important;
    padding: 7px!important;
    border: 1px solid #c2c2c2;
    border-radius: 4px;
}
.row_head{
    background: none repeat scroll 0% 0% #009AE1;
    color: #FFF;
    font-weight: bold;
    }
    .row_head td{
    padding: 5px;
    }	
.row_head .col_2{float: left; padding: 0px 15px;}
.row_result .col_2{float: left; padding: 0px 15px;}
.result_row_tr td input{border: none; background: none; width: 99.5%; color: #000;}
.starttime{width: 130px;}
.endtime{width: 120px;}
.duration_r{width: 120px;}
.cost_r{width: 80px;}
.result_row_tr td{padding: 4px 4px 4px 10px;}

.btnscolor{background: #1d87da;
    border: none;
    color: #fff;
    padding: 5px 15px;
    margin-top: 10px;
    margin-right: 10px;
    margin-left: 10px;}
    #errmsg
{
color: red;
}
</style>
</head>
<script>
$(document).ready(function() {
   
	$('.btnscolor').hide();
	
	$('.row input').each(function()
	{
	$(this).attr("disabled",true);
	});
	$('.row select').each(function()
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
</script>
<script>
function onload(){
	<% String libid=(String)request.getAttribute("libid");
	////System.out.println("ks id on onload:"+libid);
	if(libid!=null && libid.length()>0) {
		////System.out.println("ks id on onload:"+libid);
	 %> 
	 var selectBox = document.getElementById("libid");
	 for(var i=0;i<selectBox.options.length;i++){
			 if(selectBox.options[i].value == '<%=libid%>')
			{
	 selectBox.options[i].selected=true;
				 break;
			 }
		 }
				
		<%}%>
}
function clickKsId(){
	var libid=document.getElementById("libid").value;
	//alert("ksid:"+ksid);
	document.ksStore.action="/KODE_DEV/ControllerServlet/FacilitatorManageLibServlet?libID="+libid+"";
	document.ksStore.submit();
}
$(document).ready(function() {
	$(".autohide").delay(5000).fadeOut("slow");
});

</script>

<script>

function Delete(){
	document.ksStore.action="/KODE_DEV/ControllerServlet/FacilitatorDeleteLib";
	document.ksStore.submit();
	
}
	$(document).ready(function() {
		//var isValid = true;
			
    	   	
    	 
	    $("#update_btn").click(function(e) {
	    	//alert("in ip");
	    	 var isValid = true;
	        if($('input:checkbox').is(":checked"))
	        {
	          $('.search_result input[type="text"]').each(function() {
	          if ($.trim($(this).val()) == '') {
	                isValid = false;
	                $(this).css({
	                    "border": "1px solid red",
	                    //"background": "#FFCECE"
	                });
	            }
	          else if ($.trim($(this).val())== 0) {
	                 isValid = false;
	                //alert("in if 0...");
	                $(this).css({
                     "border": "1px solid red",
                     //"background": "#FFCECE"
                 });
	               //  alert("after if 0...");
	             }
	            else {
	                $(this).css({
	                    "border": "",
	                   // "background": ""
	                });
	            }
	        });
	        }
	        
	       
	        // here end working ok 
	        //alert(isValid);
        if (isValid == false){
	        	
	            e.preventDefault();
	           
	        }
	        else {
	        	 //alert('Thank you for submitting')
	        	 $("#ksStore").submit(); 
	        }
	           
	    });
	});     
</script>
<script type="text/javascript">
/* $(document).ready(function() {
	//alert("in");
	 $(".libSize").keydown(function (e) {
		alert("in"+e.keyCode);
   	 // Allow: -,backspace, delete, tab, escape, enter and .
       if ($.inArray(e.keyCode, [ 46, 8, 9, 27, 13, 110]) !== -1 ||
           (e.keyCode == 65 && ( e.ctrlKey === true || e.metaKey === true ) ) || 
           (e.keyCode >= 35 && e.keyCode <= 40)) {
                return;
       }
       if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105))
       {
           e.preventDefault();
       }
   });
}); */
$(document).ready(function () {
	  //called when key is pressed in textbox
	   /* $("#quantity").keypress(function (e) { 
		//  var ksid=document.getElementById("quantity");
		  
		  //alert("in"+e.keyCode);
	     //if the letter is not digit then display error and don't type anything
	      if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	        //display error message
	      //  alert("in...");
	        $("#errmsg").html("Digits Only").show().fadeOut("slow");
	               return false;
	    } 
	    });  */
	 $(".input-for-gb input").keypress(function (e) {
	     //if the letter is not digit then display error and don't type anything
	    // alert(e.which);
	     if (e.which != 8 && e.which != 0  && (e.which < 48 || e.which > 57)) {
	        //display error message
	      //  alert("in");
	        $(this).html("Digits Only").css("border","1px solid red");
	        
	               return false;
	    }else
	    {
	    	$(this).html("Digits Only").css("border","none");
            
	    }
	     
	     
	   });
	});
</script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function()
	{
		$('#search').keyup(function()
		{
			searchTable($(this).val());
		});
		$('#search').keypress(function(event) {  
			     if (event.keyCode == 13) {
			    	 event.preventDefault(); 
			    	 }  
			     });
	});
	function searchTable(inputVal)
	{
		var table = $('#tableId');
		table.find('tr').each(function(index, row)
		        {
		            var allCells = $(row).find("td,input");


		            if(allCells.length > 0)
		            {
		                var found = false;
		                allCells.each(function(index, td, input)
		                {
		                    var regExp = new RegExp(inputVal, 'i');
		                    var t_value = $(td).text();
		                    var input_value = $(td).children('input').val();
		                    if(regExp.test(t_value) || regExp.test(input_value))
		                    {
		                        found = true;
		                        return false;
		                    }
		                });
		                if(found == true)$(row).show();else $(row).hide();
		            }

		        });
	}
</script>

<%
		String organizationId=(String)session.getAttribute("orgid");
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		if(username==null)
		 response.sendRedirect("../JSP/error.jsp");
		ArrayList<FacilitatorManageKnowStoreDomain> reportDomain1=new ArrayList<FacilitatorManageKnowStoreDomain>();
		
		reportDomain1 = (ArrayList<FacilitatorManageKnowStoreDomain>)request.getAttribute("msgvalue");
		System.out.println("msgvalue in store="+reportDomain1);
		
	%>
<body>

	<div class="container" style="position:static;">
		<%@include file="../JSP/headers.jsp"%>
		<div style="clear: both;"></div>
		<div>
		<%@ include file= "../JSP/menus.jsp" %>
		</div>
		<div style="clear: both;"></div>
		<div class="postpon_mod" style="text-align: center;">
		<div>
		
		
		<%
						if (request.getAttribute("FacultySuccess11") != null) {
					%>
					<p class="success">
						<%=request.getAttribute("FacultySuccess11")%>
					</p>
					
					<% } 
					else if(request.getAttribute("FacultyFailure11") != null) {
					%>
					<p class="failure">
						<%=request.getAttribute("FacultyFailure11")%>
					</p>
					
					<% } 
					else	if (request.getAttribute("FacultySuccess") != null) {
					%>
					<p class="success">
						<%=request.getAttribute("FacultySuccess")%>
					</p>
					
					<% } %>
					
					<%
						//if(session.getAttribute("FacultySuccess") != null) {
					%>
					<%-- <p class="fadehide" style="color: red; text-align: center; margin-left: 54px;">
						<%=request.getAttribute("FacultySuccess")%>
					</p> --%>
					
					<% //} %>
					<p class="strong">Modify Library</p>
		 <form name="ksStore" id="ksStore" method="post" action="/KODE_DEV/ControllerServlet/FacilitatorUpdateLib">
		 
		<div>
						<select name="libid" id="libid" class="opt" onchange="clickKsId()">
								<option value="">Choose Library ID</option>
								<option value="All">All</option>
								<%
									//FacilitatorManageKnowStoreDomain al = new FacilitatorManageKnowStoreDomain();
									FacilitatorManageLibDao dobj2 = new FacilitatorManageLibDao();
								    ArrayList<String>	arrayList = dobj2.fetchValue(userid,organizationId);
								    Iterator<String> iterator = arrayList.iterator();
									while(iterator.hasNext()){
										
										String libId = iterator.next();
										String libName = iterator.next();
										String libIdAndName  = (libId+"("+libName+")");%>
									
								<option value="<%=libId%>"><%=libIdAndName%></option>
								<%}%>
					</select>
					</div>
		<div class="search_div" style="text-align: center;">
		<input class="box1" id="search" type="text" placeholder="Search">
		</div>
		<div class="search_result">
		<!-- heading starts here  -->
		<div class="table_section1">
		<div class="table_outer_row">
		<table width="100%" id="tableId">
		<thead>
			 <tr class="row_head">	 
		<th width="25%">
		Library ID
		<div class="header_stop" style="width:25%;">Library ID</div>
		</th>
		<th width="25%">
		Library Name
		<div class="header_stop" style="width:25%;">Library Name</div>
		</th>
		<th width="25%">
		Library Size
		<div class="header_stop" style="width:25%;">Library Size</div>	
		</th>
		<th width="25%">
		Action
		<div class="header_stop" style="width:25%;">Action</div>	
		</th>
		</tr>	
		</thead>	
		 <%
		if(reportDomain1!=null){
			Iterator<FacilitatorManageKnowStoreDomain> it=reportDomain1.iterator();
			while(it.hasNext()){
				FacilitatorManageKnowStoreDomain reportDomain=it.next();
				//System.out.println("LibID:"+reportDomain.getLibId());
			%>
			 <tr class="result_row_tr row">	 
				<td>
				<input type="text" name="libId<%=reportDomain.getLibId()%>" readonly value="<%=reportDomain.getLibId()%>" class="<%=reportDomain.getLibId()%>"/>
				</td>
				<td>
				<input type="text" maxlength="50" name="libName<%=reportDomain.getLibId()%>" value="<%=reportDomain.getLibName()%>" class="<%=reportDomain.getLibId()%>"/>
				<input type="hidden" readonly name="oldlibName<%=reportDomain.getLibId()%>" value="<%=reportDomain.getLibName()%>" class="<%=reportDomain.getLibId()%>"/>
				</td>
				<td class="input-for-gb">
				<input type="text" id="quantity" maxlength="2" name="libSize<%=reportDomain.getLibId()%>" value="<%=reportDomain.getLibSize()%>"  class="<%=reportDomain.getLibId()%>" style="width:50px;float:left;padding-top:10px;"/>
				
				<select name="spaceuom<%=reportDomain.getLibId()%>" id="spaceuom" class="<%=reportDomain.getLibId()%>" 
				style="width:240px;
    padding: 3px !important;
    height: 31px;
    border: 1px solid #C2C2C2;
    font-family: regular;
    border-radius: 4px;">
							<option value="">Select Space UOM</option>
							<option <%= (reportDomain.getSpaceUom().equals("KB")?"selected='selected'":"")%> value="KB">KB</option>
							<option <%= (reportDomain.getSpaceUom().equals("MB")?"selected='selected'":"")%> value="MB">MB</option>
							<option <%= (reportDomain.getSpaceUom().equals("GB")?"selected='selected'":"")%> value="GB">GB</option>
							<option <%= (reportDomain.getSpaceUom().equals("TB")?"selected='selected'":"")%> value="TB">TB</option>
						</select>
						
				<input type="hidden" readonly name="oldlibSize<%=reportDomain.getLibId()%>" value="<%=reportDomain.getLibSize()%>" class="<%=reportDomain.getLibId()%>"/>
				<input type="hidden" readonly name="oldSpaceUom<%=reportDomain.getLibId()%>" value="<%=reportDomain.getSpaceUom()%>" class="<%=reportDomain.getLibId()%>"/>
				<span id="errmsg"></span>
				</td>
				<td>
				<a href="#">
				<input id="<%=reportDomain.getLibId()%>" type="checkbox" name="checkboxGroup" value="<%=reportDomain.getLibId()%>"/>
				</a>
				
				<input type="hidden" readonly="readonly" type="text" name="ksid"
							value="<%=reportDomain.getKsId()%>" class="<%=reportDomain.getLibId()%>" />
							
				<input type="hidden" readonly="readonly" type="text" name="userid"
							value="<%=reportDomain.getUserId()%>" class="<%=reportDomain.getLibId()%>" />
							
				<input type="hidden" readonly="readonly" type="text" name="orgid"
							value="<%=reportDomain.getOrgId()%>" class="<%=reportDomain.getLibId()%>"/>					
				</td>
				</tr> 
		 <%}}%>
		</table>
		</div>
		</div>
		<a>
		<input class="btnscolor" type="button" value="Update" id="update_btn" />
		<input class="btnscolor" type="button" value="Delete" onclick="Delete()"/>
		</a>
		</div>
		</form>
	</div>
	</div>
	 <%@ include file="../JSP/FooterViews.jsp"%>
	</div>
</body>
<script>
onload();
</script>
<style>
.result_row_tr td {
border-right:1px solid #bbb;}
.fadehide {
position:absolute;
top:285px;
font-size:14px;
font-weight:bold;
right:600px;
}
</style>
</html>