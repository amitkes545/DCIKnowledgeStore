<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.IOException,java.util.ArrayList,org.apache.commons.net.ftp.FTPClient,org.apache.commons.net.ftp.FTPFile,org.apache.commons.net.ftp.FTPReply" %>
    <%@page import= "javax.servlet.jsp.JspWriter"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FTPFILE</title>
<link rel="shortcut icon" type="image/png" href="../Image/favicon.ico" />
<!-- <link href="../CSS/AssessmentView.css" rel="stylesheet"></link> -->
<link href="../CSS/CreateCustomer.css" rel="stylesheet"/>
<style type="text/css">
.subject-ul{text-align: center;}
.subject-ul li
	{
	margin-top:50px;
	margin-left:50px;
	text-align: center;
	list-style: none;
	float: left;
	width: 205px;
	}
.subject-ul li:first-child{margin-left: 0px;}
.subject-ul li a{text-decoration: none;color: #ba8c1c; font-size: 18px; font-weight: 800; font-family: Roboto-Regular;}

  ul.tree, ul.tree ul {
    list-style: none;
     margin: 0;
     padding: 0;
   } 
   ul.tree ul {
     margin-left: 10px;
   }
   ul.tree li {
     margin: 0;
     padding: 0 7px;
     line-height: 20px;
     color: #369;
     font-weight: bold;
     border-left:1px solid rgb(100,100,100);

   }
   ul.tree li:last-child {
       border-left:none;
   }
   ul.tree li:before {
      position:relative;
      top:-0.3em;
      height:1em;
      width:12px;
      color:white;
      border-bottom:1px solid rgb(100,100,100);
      content:"";
      display:inline-block;
      left:-7px;
   }
   ul.tree li:last-child:before {
      border-left:1px solid rgb(100,100,100);   
   }

.from-top
{
margin-top:50px!important;
}

li.tree-first:before {
    border: medium none !important;
}


</style>
</head>

<body>
<div class="container">
		 <%@include file="all_one_header_knowstore.jsp"%> 
		
<div style="clear: both;"></div>
<div>
		<%@ include file= "../JSP/menu.jsp" %>
		</div>

		<div class="owner_setup_faculty">

<%-- <%@ include file="../JSP/FacultyViewHeader.jsp" %>
<div class="body-mtr">
<div class="container from-top"> --%>
								<%
								ArrayList<String> arl1=new ArrayList<String>();
								//String server = "localhost";
								String server = "ftp://220.225.125.221/";
								int port = 21;
								String user = "ftpkds1";
								String pass = "kompac@123#1";
								FTPClient ftpClient = new FTPClient();
								try {
									JspWriter o=pageContext.getOut();
								    ftpClient.connect(server, port);
								    int replyCode = ftpClient.getReplyCode();
								    if (!FTPReply.isPositiveCompletion(replyCode)) 
								    {
								        //System.out.println("Connect failed");
								       
								    }
								    ftpClient.enterLocalPassiveMode();
								    boolean success = ftpClient.login(user, pass);
								    if (!success) 
								    {
								        //System.out.println("Could not login to the server");
								        
								    }
								    String dirToList = "/home/ftpkds1/KnowStore/SRM/Admin/KS106";
								    %>
								    <ul class="tree">
								    <li class="tree-first">Admin
								    <ul>
								    <%
								 	listDirectory(ftpClient, dirToList, "", 0,o);
								    %>
								 	</ul>
								 	</li>
								 	</ul>
								    <%
								   
								    
								   // //System.out.println("arl1 contains "+arl1);
								    
								   
								  
								} catch (IOException ex) {
								    //System.out.println("Oops! Something wrong happened");
								 
								    ex.printStackTrace();
								} finally {
								   
								    try {
								        if (ftpClient.isConnected()) {
								            ftpClient.logout();
								            ftpClient.disconnect();
								       
								        }
								    } catch (IOException ex) {
								        ex.printStackTrace();
								    }
								}
								
								
								%>
								<%!  public void listDirectory(FTPClient ftpClient, String parentDir,String currentDir, int level,JspWriter o) throws IOException {
									
								    String dirToList = parentDir;
								    if (!currentDir.equals("")) 
								    {
								        dirToList += "/" + currentDir;
								    }
								    FTPFile[] subFiles = ftpClient.listFiles(dirToList);
								    if (subFiles != null && subFiles.length > 0) {
								        for (FTPFile aFile : subFiles) {
								            String currentFileName = aFile.getName();
								            if (currentFileName.equals(".")
								                    || currentFileName.equals("..")) {
								                // skip parent directory and directory itself
								                continue;
								            }
								            for (int i = 0; i < level; i++) {
								//            	o.print("\t");
								                System.out.print("\t");
								            }
								            if (aFile.isDirectory()) 
								            {
								            	
								            		 if(level == 0)
								                     	o.println("<li>[" + currentFileName + "]<ul>");
								                     	else
								                      		o.println("<li><ul><li>[" + currentFileName + "]<ul>");	
								               
								            	
								                //System.out.println("[" + currentFileName + "]");
								                listDirectory(ftpClient, dirToList, currentFileName, level + 1,o);
								            } 
								            else 
								            {
								
								            	o.println("<li>"+currentFileName+"</li>");
								                //System.out.println(currentFileName);
								            
								            }
								        }
								        /*  for (int i = 0; i < level; i++) 
								            o.print("</ul>");   */
								    }
								     for (int i = 0; i < level; i++) 
								    	 if(level==1)
								    	 o.print("</ul></li>"); 
								     if(level==2){
								    	 o.print("</ul></li></ul>"); 
								     }
								     if(level==3){
								    	 o.print("</ul></li></ul></li>"); 
								     }
								     if(level==4){
								    	 o.print("</li></ul></li></ul>"); 
								     }
								   
								}
								
								
								%>
								
								
								
								
</div>
</div> 
<%@ include file="../JSP/FooterView.jsp" %>
</body>
</html>