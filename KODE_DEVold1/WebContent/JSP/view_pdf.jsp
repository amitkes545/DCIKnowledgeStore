<%@page import ="org.apache.commons.fileupload.FileItem"%>
<%@page import ="org.apache.commons.fileupload.FileUploadException"%>
<%@page import ="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import ="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import ="org.apache.commons.net.ftp.FTP"%>
<%@page import ="org.apache.commons.net.ftp.FTPClient"%>
<%@page import ="org.apache.commons.net.ftp.FTPConnectionClosedException"%>
<%@ page import ="com.kds.KODE_DEV.util.*" %>
<%@page import ="org.apache.log4j.Logger"%>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
/* div{
display: none;
} */
</style>

</head>
<script type="text/javascript" language="javascript">
        $(function() {
            $(this).bind("contextmenu", function(e) {
                e.preventDefault();
            });
        }); 
            document.querySelector("div").style.display = "block";
    //        document.addEventListener("contextmenu", function(e){    e.preventDefault();}, false);

</script>
<%
String filename=request.getParameter("filename");
String filepath=request.getParameter("filepath");
System.out.println("filename..="+filename);
System.out.println("filepath...="+filepath);
//String ftpServerAddress = "localhost";
String ftpServerAddress = PropertiesUtil.getProperty("FTP_IP");
//String userName = "ftpuser1";
//String password = "ftp@123#1";
	String userName =PropertiesUtil.getProperty("user");
	String password =PropertiesUtil.getProperty("password");	
%>
<body onload="disableClick()">
<%
FTPClient ftpclient = new FTPClient();
boolean result;
try {
				ftpclient.connect(ftpServerAddress);
				result = ftpclient.login(userName, password);
				System.out.println("after connection result="+result);
				if (result) {%>
					<%--  <embed src="/home/ftpkds1/<%=filepath%>#toolbar=0&navpanes=0&scrollbar=0" type="application/pdf"  height="450" width="500" /> --%>
				 <embed src="<%=filename%>#toolbar=0&navpanes=0&scrollbar=0" type="application/pdf"  height="600" width="850" />  
<!-- 					 <embed src="http://www.kompacdigit.com:8080/KODE_DEV/pdf.pdf#toolbar=0&navpanes=0&scrollbar=0" type="application/pdf"  height="450" width="500" /> -->
				<%
				}} catch (FTPConnectionClosedException e1) {
				e1.printStackTrace();
			} finally 
			{
				try {
					ftpclient.disconnect();
				} catch (FTPConnectionClosedException e2) {
				}
			}%>
      

 
</body>
 <script type="text/javascript">
 function disableClick() {
	 $(document.body).css('pointer-events','none');
 }
 
 </script>

</html>