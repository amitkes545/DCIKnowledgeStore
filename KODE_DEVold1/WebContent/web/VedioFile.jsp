<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!--
Copyright 2012 Mozilla Foundation

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Adobe CMap resources are covered by their own copyright but the same license:

    Copyright 1990-2015 Adobe Systems Incorporated.

See https://github.com/adobe-type-tools/cmap-resources
-->
<html dir="ltr" mozdisallowselectionprint moznomarginboxes>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="google" content="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>PDF.js viewer</title>

<!--    <link rel="stylesheet" href="viewer.css"> -->

<!-- This snippet is used in production (included from viewer.html) -->
<link rel="resource" type="application/l10n"
	href="locale/locale.properties">
<script src="l10n.js"></script>
<script src="JS/jquery-3.1.1.slim.min.js"></script>
<script src="JS/videopopup.js"></script>
<script src="../build/pdf.js"></script>
<%
String filename=request.getParameter("filename");
System.out.println("filename in load pdf="+filename);
filename="../Assets/"+filename;
System.out.println(filename);
%>

</head>


 <script type="text/javascript">

   var filenamelocal="<%=filename%>";
    alert('filenamelocal sarita '+ filenamelocal);
     sessionStorage.setItem("file  ",filenamelocal);
 </script>
  <body class="loadingInProgress">
  <div id="vidBox">
	<!-- 	<source	src="../Assets/sample1.mp4"  type="video/mp4"></video>  --> 
	<div id="videCont">
		 <video id="v1" width="800px" autoplay loop controls controlsList="nodownload">
		<!-- <video id="v1" width="800px" autoplay loop controls > -->
		 	<source src=<%=filename%>  type="video/mp4">
		 </video>
	</div>
</div>

<script type="text/javascript">
   $(function () {
      $('#vidBox').VideoPopUp({
       	backgroundColor: "#17212a",
       	opener: "video1",
           maxweight: "340",
           idvideo: "v1"
       });
   });
</script>
</body>