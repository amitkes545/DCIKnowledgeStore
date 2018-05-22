<!DOCTYPE html>
<html>

  <head>
    <meta charset=utf-8 />
    <title>KnoWStore</title>
    <!-- <link rel="stylesheet" href="http://www.jacklmoore.com/colorbox/example1/colorbox.css" />
     <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://www.jacklmoore.com/colorbox/jquery.colorbox.js"></script> -->
     <link rel="stylesheet" href="../CSS/colorbox.css" />
     <script src="../JS/jquery.min.js"></script>
    <script src="../JS/colorbox.js"></script> 
   <!--  <link rel="stylesheet" href="https://www.jacklmoore.com/colorbox/example1/colorbox.css" />
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://www.jacklmoore.com/colorbox/jquery.colorbox.js"></script> -->
  
  </head>
  <body >
  <script>
   function getExtension(filename) {
    var parts = filename.split('.');
    return parts[parts.length - 1];
   }
  </script>
  
    <script>
   
      function popup_read_only(filename,filepath){
    	  alert("hii")
    	 alert("in="+filename);
    	 alert("filepath="+filepath);
    	 var ext = getExtension(filename);
    	 alert("ext:::"+ext)
    	 
    	if(ext == "pdf")
        	{
            	 parent.$.colorbox({iframe:true, width:"900", height:"600", scrolling: false, href: "../web/LoadPDF.jsp?filename="+filename+"&filepath="+filepath+""});
        	} 
        	 //System.out.println("heloooooooooooooooooooooooooo");
        if(ext == "mp4")
        	{
        	 parent.$.colorbox({iframe:true, width:"900", height:"600", scrolling: false, href: "../web/VedioFile.jsp?filename="+filename+"&filepath="+filepath+""});
        	}
              /* $.colorbox({iframe:true, width:"50%", height:"90%", scrolling: false, href: "../JSP/view_pdf.jsp"}); */
    	 //parent.$.colorbox({iframe:true, width:"900", height:"600", scrolling: false, href: "../JSP/view_pdf.jsp?filename="+filename+"&filepath="+filepath+""});
    	// parent.$.colorbox({iframe:true, width:"900", height:"600", scrolling: false, href: "../web/LoadPDF.jsp?filename="+filename+"&filepath="+filepath+""});
      }
     
     
      //setTimeout(openColorBox, 2000);
    </script>
    
  </body>
  <style>
  
  /*----colorbox popup----*/
.cboxIframe {
	background:none;
}
#cboxLoadedContent {
	margin-top:23px;
}

#cboxClose {
top:6px!important;
}
#cboxLoadedContent {
overflow:hidden!important;
}
#cboxLoadedContent{overflow-:hidden !important;}
#cboxMiddleLeft,#cboxBottomLeft,#cboxBottomCenter,#cboxBottomRight,#cboxTopLeft,#cboxTopCenter,#cboxTopRight,#cboxMiddleRight {
display:none;
}
#cboxContent {
background:none;
}
#cboxClose {
    background: rgba(0, 0, 0, 0) url("../Images/black_close_icon.png") no-repeat scroll 0 0 / 20px auto;
    height: 18px;
    width: 19px;
}
#cboxClose:hover {
	background: rgba(0, 0, 0, 0) url("../Images/black_close_icon.png") no-repeat scroll 0 0 / 20px auto;
	 height: 18px;
    width: 19px;
}
#cboxClose {
	background-color:#fff;
	border-radius:10px;
	background: rgba(0, 0, 0, 0) url("../Images/black_close_icon.png") no-repeat scroll 0 0 / 20px auto;
}
#cboxClose:hover {
	background-color:#fff;
	border-radius:10px;
	background: rgba(0, 0, 0, 0) url("../Images/black_close_icon.png") no-repeat scroll 0 0 / 20px auto;
}
#colorbox {
position:fixed!important;
top:0px!important;
}
/*----colorbox popup----*/
  </style>
</html>
