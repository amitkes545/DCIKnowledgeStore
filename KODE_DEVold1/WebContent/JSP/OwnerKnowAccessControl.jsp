<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.kds.KODE_DEV.dao.*"%>
<%@ page import="com.kds.KODE_DEV.domain.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<link href="../CSS/owner.css" rel="stylesheet"></link>
<link href="../CSS/global.css" rel="stylesheet"></link>
<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
<script src="../JS/OwnerKnowAccessControl.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="../JS/MessageFadeOut.js"></script>
<script>
/* function mfunction1()
{
	if(valid())
	{	
	var block3=document.getElementById("unblock").value;
	var block4='UnBlock';
	if(block3==block4){
		//alert("hiu");
	document.AcAccessControll.action ="/KODE_DEV/ControllerServlet/OwnerAControlKStoreServlet?status=Active"
	document.AcAccessControll.submit();
	
	}
	else{
		return false;
	}
	}
}
 */
 function myFunction() 
{
	//alert("hi");
	if(valid())
	{	
	var block =document.getElementById("block").value;
	//var block1='Block';
	//alert(block);
		if( block=='InActive')
		{
		//alert(block);
		document.AcAccessControll.action ="/KODE_DEV/ControllerServlet/OwnerAControlKStoreServlet?status=InActive"
		document.AcAccessControll.submit();
		//document.getElementById(block).style.display = 'none';
		}
		else if(block=='Active'){
			//alert(block);
		document.AcAccessControll.action ="/KODE_DEV/ControllerServlet/OwnerAControlKStoreServlet?status=Active"
		document.AcAccessControll.submit();
		//	document.getElementById(block).style.display = 'none';
	}
	else{	
		//alert("hello");
	return false;
	}

	}

}

var request;
function getID()
{
var v=document.AcAccessControll.ksid.value;
var url="CommonStatusKnowStore.jsp?val="+v;

if(window.XMLHttpRequest){
request=new XMLHttpRequest();
}
else if(window.ActiveXObject){
request=new ActiveXObject("Microsoft.XMLHTTP");
}

try
{
request.onreadystatechange=getInfo;
request.open("GET",url,true);
request.send();
}
catch(e)
{
alert("Unable to connect to server");
}
}
function getInfo(){
if(request.readyState==4){
var val=request.responseText;
if(val.search('InActive')>0){
	//alert(val);
	document.getElementById('block').value='Active';
}
else{
	//alert(val);
	document.getElementById('block').value='InActive';
}
document.getElementById('amit').innerHTML=val;
}
}


</script>

</head>
<%
	

	String username=(String)session.getAttribute("username");
	String	userid=(String)session.getAttribute("userid");
	//System.out.println("userid is:"+userid);
	String orgid=(String)session.getAttribute("orgid");
	
	%>
<body>
	<div class="container">

		<%-- <%@ include file="../JSP/all_one_header.jsp" %> --%>
		<jsp:include page="../JSP/all_one_header.jsp">
			<jsp:param name="username" value="<%=username%>" />
		</jsp:include>

		<div style="clear: both;"></div>
		<div class="owner_setup"
			style="height: 200px; width: 480px !important;">

			<p class="strong">Know Store Access Control</p>
			<form name="AcAccessControll" method="post">
				<%-- <%! OwnerManageKnowStoreDomain oDomain=new  OwnerManageKnowStoreDomain();%> --%>
				<table>
				
				<%
						if (request.getAttribute("OwnerSuccess") != null) {
					%>
					<p class="fadehide" style="color: green; text-align: center; margin-left: 54px;">
						<%=request.getAttribute("OwnerSuccess")%>
					</p>
					<%
						}
					%>
					<tr>
						<td>Know Store ID</td>
						<td>:</td>
						<td style="position: absolute;margin-top: -8px;"><select name="ksid" id="ksid" onfocus='this.size=10;' onblur='this.size=1;' onchange="getID();this.size=1; this.blur();">

								<option value="default">--select KS ID--</option>
								<%
								OwnerSelectKnowStoreIdDao ownerSelectDao=new OwnerSelectKnowStoreIdDao();
	
   							 ArrayList<String> arrayList=ownerSelectDao.fetchKsIdAndUserId(session.getAttribute("userid").toString());
 
   										 Iterator<String> iterator=arrayList.iterator();

 										   while(iterator.hasNext())
   												 {
												String ksId=iterator.next();
												String userId = iterator.next();
												String ksIdAndUserId = (ksId+" ("+userId+")");
												
	
										  %>


								<option value="<%=ksId%>"><%=ksIdAndUserId%></option>

								<%

										}
  								%>
						</select></td>
						<span id="amit" class="disable-btn" style="position: absolute;right: 107px;top: 63px;"></span>
					</tr>
					
					
					<%-- <%
									String status1=null; 
								AdminSetUpKnowStoreFieldSelectorDao adminSetupDao = new AdminSetUpKnowStoreFieldSelectorDao();

									ArrayList<String> arrayList = adminSetupDao.fetchKnowStoreStatus(userid);
											

									Iterator<String> iterator = arrayList.iterator();

									while (iterator.hasNext()) {
								    status1 = iterator.next();
										
										//System.out.println("hello:"+arrayList.toString());
													
								%>
								  <% } %>  --%>
											
					<tr>
						<td></td>
						<td></td>
						<td style="padding-top: 10px;">
						<input class="submit_btn"
							style="width: 90px" type="button" id="block" value="Active"
							onclick="myFunction()" /> 
						
				 	<%-- <% if(status1.equalsIgnoreCase("Active")){ %> 
					
					
						<input class="submit_btn"
							style="width: 90px" type="button" id="block" value="Block"
							onclick="myFunction()" /> 
						
					 <% }else{ %>
					 
							<input class="submit_btn"
							style="width: 90px" type="button" id="unblock" value="UnBlock"
							onclick="mfunction1()" />
							<% } %> --%>
							<a class="back" style="color: #c2c2c2; text-align: left;" href="../JSP/Home.jsp">Back</a>
							</td>
							
							
						 	
					</tr>
				</table>
				<p></p>
			</form>
		</div>
		<%@ include file="../JSP/all_one_footer.jsp"%>
	</div>
</body>
</html>