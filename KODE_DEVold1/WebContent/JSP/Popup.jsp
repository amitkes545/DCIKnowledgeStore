<!DOCTYPE html>
<html>
  <head>
    <meta charset=utf-8 />
    <title>ColorBox demo</title>
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
   
      function student_classwork_popup(session_id){
    	 // alert("in");
              $.colorbox({iframe:true, width:"50%", height:"90%",scrolling: false, href: "../JSP/StudentClasswork.jsp?ses_id="+session_id+""});
      }
      function faculty_classwork_popup(session_id){
    	//  alert(session_id);
    	 // alert("../JSP/FacultyClasswork.jsp?ses_id="+session_id+"");
               $.colorbox({iframe:true, width:"50%", height:"90%", scrolling:false, href: "../JSP/FacultyClasswork.jsp?ses_id="+session_id+""});
       }
      
      //setTimeout(openColorBox, 2000);
    </script>
    
  </body>
</html>
