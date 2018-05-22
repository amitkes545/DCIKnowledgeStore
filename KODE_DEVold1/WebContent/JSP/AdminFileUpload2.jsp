<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome To KODE_DEV</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
</head>


<body>
	<center>
		<form method="post" name="FileUpload" action="/KODE_DEV/ControllerServlet/ExtenalFileUploadServlet" enctype="multipart/form-data">
			Select file to upload: <input type="file" name="upload" /> <br /> <br />
			<input type="submit" value="Upload" />
		</form>
	</center>
</body>
</html>