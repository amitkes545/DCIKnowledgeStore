<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.kds.KODE_DEV.dao.FacilitatorSelectUsersIDDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<html>
<head>

<%
		HttpSession mess = request.getSession();
		
		String username=(String)mess.getAttribute("username");
		
		String orgid=(String)mess.getAttribute("orgid");
		String userid=(String)mess.getAttribute("userid");
		//System.out.println("organization id:"+orgid+"created id:"+userid);
	%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WelCome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
</head>
<body>
	<center>
		<form method="post" name="FileUpload"
			action="/KODE_DEV/ControllerServlet/ExtenalFileUploadServlet"
			enctype="multipart/form-data">
			Select file to upload: <input type="file" name="upload" /> <br /> <br />
			<input type="submit" value="Upload" /><br/>
			<table>
			<tr>
			<td>ID</td>
			<td>:</td>
			<td><select name="usersid" id="usersid" multiple>
					<option value="default">--select recipient ID--</option>
					<%
						FacilitatorSelectUsersIDDao dobj2 = new FacilitatorSelectUsersIDDao();
						ArrayList<String> al = dobj2.fetchValue(orgid);
						//HashSet set =(HashSet)al.fetchValue();
						Iterator<String> itr = al.iterator();

						while (itr.hasNext()) {
							String usersID = itr.next();
					%>
					<option value="<%=usersID%>"><%=usersID%></option>
					<%
						}
					%>
				
			</select></td>
			</tr>
			</table>
		</form>
	</center>
</body>
</html>