<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
  <HEAD>
    <TITLE>Using jspInit and jspDestroy</TITLE>
  </HEAD>

  <BODY>
    <H1>Using jspInit and jspDestroy</H1>
    <%!
    int number;

    public void jspInit()
    {
      number = 5;
    }

    public void jspDestroy()
    {
      number = 0;
    }
    %>

    <%
    out.println("The number is " + number + "<BR>");
    %>
  </BODY>
</HTML>

