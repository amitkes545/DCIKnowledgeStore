package com.kds.KODE_DEV.services;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dbconnection.DBTransaction;



@SuppressWarnings("serial")
public class DisplayCustomerServlet extends CommonService {
	//private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(DisplayCustomerServlet.class);
	@Override
	public void run() throws Exception {
		
		  LOGGER.info("display page");
	        PrintWriter out = response.getWriter();
	        //Connection con;
	        PreparedStatement stat;
	        try {
	            /*Class.forName("org.postgresql.Driver");
	            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KODE_DEV", "postgres", "root");*/
	        	//DBTransaction DBT = new DBTransaction();
	        	Connection con = DBTransaction.connect();
	        	 LOGGER.info("inside display page");
	            stat = con.prepareStatement("select * from org_details where organization_id='"+request.getParameter("org_id")+"'");
	            ResultSet rs = stat.executeQuery();
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>Servlet DisplayData</title>");
	            out.println("</head>");
	            out.println("<body>");
	            out.println("<table style='width:100%; height:auto;'>");
	            out.println("<thead><td>organization_id</td><td>organization_name</td><td>org_type</td><td>address</td><td>city</td><td>country</td><td>postal_code</td><td>telephone</td><td>fax</td><td>emergency_contact_no</td><td>email</td><td>url</td><td>logo</td><td>year_of_foundation</td><td>belongs</td><td>date_time</td></thead>");
	            while (rs.next()) {
	                String id = rs.getString(1);
	                out.println("<td>" +rs.getString(1) + "</td>");
	                out.println("<td>" + rs.getString(2) + "</td>");
	                out.println("<td>" + rs.getString(3) + "</td>");
	                out.println("<td>" + rs.getString(4) + "</td>");
	                out.println("<td>" +rs.getString(5) + "</td>");
	                out.println("<td>" + rs.getString(6) + "</td>");
	                out.println("<td>" + rs.getString(7) + "</td>");
	                out.println("<td>" + rs.getString(8) + "</td>");
	                out.println("<td>" +rs.getString(9) + "</td>");
	                out.println("<td>" + rs.getString(10) + "</td>");
	                out.println("<td>" + rs.getString(11) + "</td>");
	                out.println("<td>" + rs.getString(12) + "</td>");
	                out.println("<td>"+rs.getString(13)+"</td>");
	                
	                LOGGER.info("logo value is");
	                //out.println("<td style='width:150px; height:125px;'><img src='" + "retriveImage?" + id + "' style='width:150px; height:125px;'/></td></tr>");
	                out.println("<td>" +rs.getString(14) + "</td>");
	                out.println("<td>" + rs.getString(15) + "</td>");
	                out.println("<td>" + rs.getString(16) + "</td>");
	               
	            }
	            out.println("</table>");
	            out.println("</body>");
	            out.println("</html>");
	        } catch (ClassNotFoundException ex) {
	            out.println(ex.getMessage());
	        } catch (SQLException ex) {
	            out.println(ex.getMessage());
	        } /*catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
	    }
}
