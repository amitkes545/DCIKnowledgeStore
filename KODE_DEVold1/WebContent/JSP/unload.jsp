<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript">
    <%!
    public void jspInit() {
    	System.out.println("hi init");
    }
    %>
    <%!
    public void jspDestroy() {
    	System.out.println("hi destroy");
    }
    %>
        function hi() {
        	//console.log("hi");
        	alert("hi");
            //return "All data that you have entered will be lost!";
        }
    </script>
</head>
<body onbeforeunload="hi()">
    <b>Close this window or press F5 to reload the page.</b>
    <br /><br />
    <form>
        User name: <input type="text" name="username" />
        <br />
        City: <input type="text" name="city" />
    </form>
</body>
</body>
</html>