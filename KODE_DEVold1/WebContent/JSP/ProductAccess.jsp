<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kds.KODE_DEV.domain.AdminDomain"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
		<link href="../CSS/owner.css" rel="stylesheet"></link>
		<link href="../CSS/global.css" rel="stylesheet"></link>
		<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
		<script type="text/javascript" src="../JS/jquery1.11.3.js"></script>
		
		<style type="text/css">
		.product_access tr td:first-child{
		width: 160px;
		}
		.edit
		{
		background: url(../Image/file_edit.png) no-repeat;
		
		width: 25px;
		 background-size: 25px 25px;
		 border: none;
		}
		.product_access tr td:last-child > input
		{
		width: 27px;
		}
		.del
		{
		background: url(../Image/remove.png) no-repeat;
		
		width: 25px;
		 background-size: 25px 25px;
		 border: none;
		}
		.edit_save{display: none;background: url(../Image/save.png) no-repeat;
		width: 25px;float:left;
		background-size: 25px 25px;} 
		#edit_save{
		background: url(../Image/save.png) no-repeat;
		width: 25px;
		background-size: 25px 25px;
		display: none;
		float: left;
		}
		.save
		{
		background: url(../Image/save.png) no-repeat;
		width: 25px;
		 background-size: 25px 25px;
		 border: none;
		}
		
		.product_access tr td:last-child{
		width: 40px !important;
		}
		.product_access tr td:nth-child(2){
		color: #fff;
		}
		#access
		{
		width: 20px;
		}
		.product_access tr td:nth-child(2) > input
		{
		padding: 0px;width: 90%;
		}
		.product_access tr td:nth-child(2){width: 115px;}
		#save_btn
		{
		width: 90%;
		padding: 0px;
		background: url(../Image/del_img.png) no-repeat; 
		}
		.product_access{
width: 450px; /* singal change */
}
		</style>
		
		<script type="text/javascript">
		$(document).ready(function() {
			$(".autohide").delay(5000).fadeOut("slow");
		});
		
$(document).ready(function() {

			
    $("button.c").hide();
    $("button.b").show();
   $("button.b").click(function() {
       var id = $(this).attr("id");
       var id1 = $(this).attr("id");
       var sectionId = id.replace("b", "c");
       $("button.b").show();
       $("button.c").hide();
       $("#" + id1).hide();
       $("button#" + sectionId).fadeIn("slow");
   });
    
   $("button.c").click(function() {
      var cid = $(this).attr("id");
         var cid1 = $(this).attr("id");
         var ccid = cid1.replace("c", "b");
         $("button.b").show();
         $("#" + cid).hide();
         $("button#" + ccid).fadeIn("slow");
   });
   
   $("#addrowsave").click(function() {
	   var addname = document.getElementById("addName").value;
	   var addid = document.getElementById("addId").value;
	   if(addname == ""){
	    	  alert("Enter Process Name");
				return false;
	      }
	      else if(addid == ""){
				alert("Enter Process ID");
				return false;
			}
	      else { 
	    		document.pName1.action="/KODE_DEV/ControllerServlet/ProductAccessServlet";
	    		 document.pName1.submit();
	    	}
	      
	   });
    
}); 
</script>
		
		
</head>
<%
	ArrayList<AdminDomain> msg,access;
	AdminDomain pdomain = new AdminDomain();
	AdminDomain pdomain1 = new AdminDomain();
    msg =(ArrayList<AdminDomain>) session.getAttribute("AccessValues");
   // access=(ArrayList<AdminDomain>) request.getAttribute("AccessIdInDB");
	//String userid =(String)session.getAttribute("userid");
	//HttpSession mess = request.getSession();
	String ownersuccess=(String)request.getAttribute("OwnerSuccess");
	//System.out.println("owner success:"+ownersuccess);
	String message = "";
	/* if(!"".equals(msg)){ */
	message = (String) session.getAttribute("msgvalue");
	String userid=(String)session.getAttribute("userid");
	String username=(String)session.getAttribute("username");
	String orgid=(String)session.getAttribute("orgid");
	String privilege=(String)session.getAttribute("privilege");
	//System.out.println("user id is"+userid+ "orgid: "+orgid+ "privilege:"+privilege);
	//session.setAttribute("listfromdb", access);
	
%>
<script>

function clickedEdit(id)
{
	var td_id=id+"_id";
    var td_name=id+"_name";
    var sv_bt=id+"_save";
    var tdIdValue = document.getElementById(td_id).innerHTML;
    var tdNameValue=document.getElementById(td_name).innerHTML;
    //alert("tdvalue" +tdIdValue+ "tdNameValue"+tdNameValue);
    var tdIDObj = document.getElementById(td_id);
    var tdNameObj = document.getElementById(td_name);
    //alert("tdIDObj"+tdIDObj+ "tdNameObj"+tdNameObj);
    
    document.getElementById(td_id).innerHTML="";
    document.getElementById(td_name).innerHTML="";
   
    var element = document.createElement("input");
    element.setAttribute("type", "text");
    element.setAttribute("id", id+"_ txt");
    element.setAttribute("name", "editId");
    element.setAttribute("value", tdIdValue);
    tdIDObj.appendChild(element);
    
    var element1 = document.createElement("input");
    element1.setAttribute("type", "text");
    element1.setAttribute("id", id+"_ txt");
    element1.setAttribute("name", "editName");
    element1.setAttribute("value", tdNameValue);
    tdNameObj.appendChild(element1);
    
    var element2 = document.createElement("input");
    element2.setAttribute("type", "hidden");
    element2.setAttribute("id", id+"_ txt");
    element2.setAttribute("name", "oldId");
    element2.setAttribute("value", tdIdValue);
    tdNameObj.appendChild(element2);
    document.getElementById(id).style.display = "none";
    document.getElementById(id+"_save").style.display = "block";
    
   
}
/* function addRow(id) {
	var addName = document.getElementById(addName).value;
	var addid = document.getElementById(addId).value;
	alert(addName);
	
	if(addName == ""){
		alert("Enter Process Name");
		return false;
	}else if(addid == ""){
		alert("Enter Process ID");
		return false;
	}
	
	else { 
	document.pName1.action="/KODE_DEV/ControllerServlet/ProductAccessServlet";
	 document.pName1.submit();
}
} */

function deleteRow(id) {
alert("Are you sure want to delete");
{
	 document.pName1.action="/KODE_DEV/ControllerServlet/ProductAccessServlet?Delid="+id;
	 document.pName1.submit();
}
}
</script>

<script type="text/javascript">



	 
   /*  var table = document.getElementById("mtable");

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    var cell1 = row.insertCell(0);
    var element1 = document.createElement("input");
    element1.type = "text";
    element1.name="PAddName";
    cell1.appendChild(element1);

    var cell1 = row.insertCell(1);
    var element1 = document.createElement("input");
    element1.type = "text";
    element1.name="PAddId";
    cell1.appendChild(element1);
    
    var cell1 = row.insertCell(2);
    cell1.innerHTML="<input type='button' name='Add' value='' id=' save_btn' onClick="this.style.background='../Image/file_edit.png'" /> ";*/
    /* var element1 = document.createElement("input"); 
    element1.type = "button";
    element1.id = "save_btn";
    element1.name="Add";
    element1.onClick="/KODE_DEV/ControllerServlet/ProductAccessServlet";
    cell1.appendChild(element1);  */ 
    
    /* var cell1 = row.insertCell(2);
    <img  "id="irow" alt="edit" src="../Image/file_edit.png" width="27px"></img> */


           //confirm("are sure want delete this row");
  </script>

<body>
<div class="container">
<%@include file="all_one_header.jsp" %>	
	<%-- <jsp:include page="../JSP/all_one_header.jsp"> 
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include> --%>
			<div style="clear: both;"></div>
			<div class="product_access" style="min-height: 280px; max-height:400px; width:550px; overflow-y:scroll ">
			 
 <p class="strong">ProductAcl Details</p>
<form action="/KODE_DEV/ControllerServlet/ProductAccessServlet" name="pName1" method="post">
	<%  if(request.getAttribute("OwnerSuccess")!= null){%>
					<p class="autohide" style="color:green; margin-bottom: 5px"> <%= request.getAttribute("OwnerSuccess") %> </p>
					
				<% }
				else  if (request.getAttribute("OwnerFailure")!= null) { %>
					<p class="autohide" style="color:red; font-size:28px; font-weight: bold; top: 50%; left: 30%; position: absolute;"><%= request.getAttribute("OwnerFailure") %></p>					
					<%} %>

			<table border="2" id="mtable">
			<tbody id="nk">
				<tr>

					<th>Process Name</th>
					<th>Process ID</th>
					<th>Action</th>
				</tr>
				
				<%
				int im=0;
				// Iterator it1=access.iterator();
				Iterator it = msg.iterator();
				
				while (it.hasNext()) {
					
				
					pdomain = (AdminDomain) it.next();	
					//System.out.println(pdomain.getProcessid());
					
				%>
				
				<tr>
				
					<td id="<%=pdomain.getProcessid()%>_name"><%=pdomain.getProcessname()%></td> 
					<td id="<%=pdomain.getProcessid()%>_id"><%=pdomain.getProcessid()%></td> 
      				<td style="text-align: center;">
      				<input type="button" class="edit" id="<%=pdomain.getProcessid()%>" onClick="clickedEdit(this.id)"/>
      				
      				<input type="submit" class="edit_save <%=pdomain.getProcessid()%>" id="<%=pdomain.getProcessid()%>_save" value=" " onClick="clickedEdit(this.id)"/>
      				
      				<%-- <img alt="edit" src="../Image/file_edit.png" id="<%=pdomain.getProcessid()%>" onClick="clickedEdit(this.id)" width="27px"> --%>
      				<input type="submit" class="del" value=" &nbsp; " id="<%=pdomain.getProcessid()%>" onClick="deleteRow(this.id)" />
      				
      				<%-- <img alt="delete" src="../Image/del_img.png" name="Delid" id="<%=pdomain.getProcessid()%>" onclick="deleteRow(this.id)" width="27px"> --%>
      				
      				</td></tr>
         
         <%
         im=im+1;
     }
			%>
          <tr><td><input type="text" name="addName" id="addName" ></td><td><input type="text" name="addId" id="addId"></td><td><input type="button" id="addrowsave" class="save" value=" &nbsp; " /> </td></tr>
		</tbody>
			</table>
			<table>
			<tbody>
			<tr>
			<td></td>
			<td></td>
			
			<td>
			<p style="text-align: right; margin-right: 110px; margin-top: 7px;">
			<!-- <img onclick="addRow('dataTable')" id="irow" alt="edit" src="../Image/add_row.png" width="27px"> </p> -->
			</td>
			</tr>
			</tbody>
			</table>
			<div style="clear: both; height: 20px;"></div>
			<p style="text-align: center; margin-bottom: 15px;"> 
			<!-- <input class="submit_btn" style="margin-left: -125px; padding: 5px;" type="submit" name="submit" value="submit"> -->
			<a style="color: #c2c2c2;" href="../JSP/ProductAcl.jsp">Back</a>
			</p>
               <%-- <input type="hidden" name="userid" value="<%=userid%>">
               <input type="hidden" name="privilege" value="<%=privilege%>">
               <input type="hidden" name="orgid" value="<%=orgid%>"> --%>
	</form>
	

		</div>
		<%@include file="all_one_footer.jsp" %>	
</div>
</body>
</html>