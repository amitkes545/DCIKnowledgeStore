<!DOCTYPE html>
<html>
  <head>
    <meta charset=utf-8 />
    <title>ColorBox demo</title>
    <link rel="stylesheet" href="https://www.jacklmoore.com/colorbox/example1/colorbox.css" />
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://www.jacklmoore.com/colorbox/jquery.colorbox.js"></script>
  </head>
  <body >
    <h1>Hello, there!</h1>
    <h2>This is some content</h2>
    <p>The popup will open in five seconds</p>
    <script>
      function openColorBox(){
        $.colorbox({iframe:true, width:"80%", height:"80%", href: "../JSP/popop.jsp"});
      }
      
      //setTimeout(openColorBox, 2000);
    </script>
    
    <input type="button" value="popup" onclick="openColorBox()" >
  </body>
</html>
