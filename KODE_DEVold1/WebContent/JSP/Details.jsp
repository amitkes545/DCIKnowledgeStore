<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.kds.KODE_DEV.domain.CreateDomain"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
</head>
<body>
	<%!CreateDomain cdom = new CreateDomain();%>
	<%
		cdom = (CreateDomain) session.getAttribute("CreateDomain");
	%>
	<%
		String org_id = cdom.getOrg_id();
	%>

  
	<form action="/KODE_DEV/ControllerServlet/UpdateServlet" method="post">



		<table align=center>


			<tr>
				<td>Organization id</td>
				<td>
					<%
						if (cdom == null) {
				%> <input type="text" name="org_id" id="org_id"
					value=""> <%
 	} else {
 %> <input type="text" name="org_id"
					id="org_id" value="<%=cdom.getOrg_id()%>">
				</td>
			</tr>
			<%
				}
			%>
			<tr>
				<td>Organization name:</td>

				<td><input type="text" name="org_name" id="org_name"
					value="<%=cdom.getOrg_name()%>"></td>
			</tr>

			<tr>
				<td>Organization Type:</td>

				<td><input type="text" name="org_type" id="org_type"
					value="<%=cdom.getOrg_type()%>"></td>
			</tr>

			<tr>
				<td>Address:</td>

				<td><input type="text" name="address" id="address"
					value="<%=cdom.getAddress()%>"></td>
			</tr>
			<tr>
				<td>City:</td>

				<td><input type="text" name="city" id="city"
					value="<%=cdom.getCity()%>"></td>
			</tr>
			<tr>
				<td>Country:</td>

				<td><input type="text" name="country" id="country"
					value="<%=cdom.getCountry()%>"></td>
			</tr>
			<tr>
				<td>Postal Code:</td>

				<td><input type="text" name="pcode" id="pcode"
					value="<%=cdom.getPcode()%>"></td>
			</tr>
			<tr>
				<td>TelephoneNo:</td>

				<td><input type="text" name="phone" id="phone"
					value="<%=cdom.getPhone()%>"></td>
			</tr>
			<tr>
				<td>Fax:</td>

				<td><input type="text" name="fax" id="fax"
					value="<%=cdom.getFax()%>"></td>
			</tr>
			<tr>
				<td>Email Id:</td>

				<td><input type="text" name="email" id="email"
					value="<%=cdom.getEmail_id()%>"></td>
			</tr>
			<tr>
				<td>Url:</td>

				<td><input type="text" name="url" id="url"
					value="<%=cdom.getUrl()%>"></td>
			</tr>
			<!--   %><tr><td>Logo:</td>
			  <td><input type="text" name="logo" id="logo" 
			      value=" %>"></td>
			  </tr>-->
			  			<tr>

				<td><input type="text" name="yof" id="yof"
					value="<%=cdom.getYof()%>"></td>
			</tr>
			<tr>
				<td>Belongs:</td>

				<td><input type="text" name="belongs" id="belongs"
					value="<%=cdom.getBelongs()%>"></td>
			</tr>
			<tr>
				<td>Date and Time:</td>

				<td><input type="text" name="date" id="date"
					value="<%=cdom.getDate()%>"></td>
			</tr>

			<tr>
				
				<td><input type="submit" name="update" value="update"></td>
			    <td><input type="submit" name="delete" value="delete"></td>
			</tr>
			
			</table>
	</form>
</body>
</html>