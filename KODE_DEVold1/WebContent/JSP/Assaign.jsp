<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.kds.KODE_DEV.domain.AdminDomain" %>
     <%@page import="com.kds.KODE_DEV.domain.CreateDomain" %>
    <%@page import="com.kds.KODE_DEV.dao.SearchAdminDao" %>
    <%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Faculty Page</title>
<link href="../CSS/owner.css" rel="stylesheet"></link>
		<link href="../CSS/global.css" rel="stylesheet"></link>
		<link href="../CSS/CreateCustomer.css" rel="stylesheet"></link>
		<style type="text/css">
		/* Access Report Details*/

.faculity_setup{position: relative; top: 130px;
width: 375px;
/* height: 380px; */
box-shadow: 5px 5px 5px #000;
margin: 15px auto 0px;
padding: 15px 87px;
border: 2px solid #A52A2A;
background-color: rgba(25, 25, 55, 0.5);

}
a{color: #c2c2c2;}
.faculity_setup tr td:first-child
{
	color: #fff;
	font-size: 18px;	
	font-family: sans-serif;
}
.faculity_setup tr td span{
color: #f63a4c;
}
.faculity_setup tr td:last-child > input
{width: 184px; margin: 5px 0px; padding: 5px; }
.faculity_setup tr td:last-child > select
{width: 199px; padding: 5px; margin: 5px 0px;}

		</style>
</head>
<%
		//HttpSession session = request.getSession(false);
		HttpSession mess = request.getSession();
	
		String msg = "";
	
		/* if(!"".equals(msg)){ */
			String username=(String)mess.getAttribute("username");
		msg = (String) mess.getAttribute("MsgValue");
		String orgid=(String)mess.getAttribute("orgid");
		String userid=(String)mess.getAttribute("userid");
		//System.out.println("organization id:"+orgid+"created id:"+userid);
		
		//}
	%>
<body>
<div class="container">
	<%-- 	<%@include file="all_one_header.jsp" %> --%>
	<jsp:include page="../JSP/all_one_header.jsp">  			
         <jsp:param name="username" value="<%= username%>" /> 
         </jsp:include>
			<!-- <div id="anim_img1"></div>
			<div id="kds_logo"></div>
			<div id="heading">Welcome xxxxxx</div>
			<div class="right_menu">
			<ul>
			<li id="cpwd-li"><a href="#" id="cpwd">change Password</a></li>
			
			<li><a href="../JSP/Login.jsp"><button id="logout">Logout</button></a></li>
			</ul>
			</div> -->
<div style="clear: both;"></div>
			<div class="faculity_setup">
			 
 <p class="strong">Assign Subject Faculty</p>			

  <form action="/KODE_DEV/ControllerServlet/AssaignFacultyServlet" method="post" name="facultyform">
     <table align="center">
     
    <tr><td align="left">
        <font>Select FacultyID  </font></td><td>:</td>
        <td>
							 <%SearchAdminDao sdao= new SearchAdminDao();
							   String id=null;
					           ArrayList<AdminDomain>od=new ArrayList<AdminDomain>();
							    od=sdao.selectFacultyId(orgid,userid);
							    Iterator<AdminDomain> it= od.iterator();%>
							    <select name="facultyid" >
							    <% while(it.hasNext())
							    {
							    	 id=((AdminDomain)it.next()).getAdminId();%>
							    <option value="<%= id %>"><%=id%></option>	
							   <%}%>
							   </select>
						</td>
					</tr>
				         <tr><td>SubjectId</td><td>:</td>
				         <td><input type="text" name="subid" id="subid"></td></tr>
				         <tr><td>Stream</td><td>:</td>
				         <td><input type="text" name="stream"></td>
				         <td><input type="hidden" name="orgid" value=<%=orgid %>></td>
				         </tr>
				</table>
				<p style="margin-left: 80px; margin-top: 15px"> <input type="submit" value="submit">  <a style="margin-left: 10px;color: #c2c2c2; font-weight: bold; " href="../JSP/Home.jsp">Back</a></p>
				
				</form>
		
</div>
</div>

  

</body>
</html>