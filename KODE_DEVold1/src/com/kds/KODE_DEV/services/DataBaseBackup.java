package com.kds.KODE_DEV.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dbconnection.DBTransaction;

@SuppressWarnings("serial")
public class DataBaseBackup<users_info> extends CommonService { 
	

	static final Logger LOGGER = Logger.getLogger(DataBaseBackup.class);
	public void run() throws Exception
	{
		//char[] csvAsString = null;
		//String UPLOAD_DIRECTORY = "";
		//String dirname="";
		//PrintWriter pw=response.getWriter();
		//usual database connection part
		PrintWriter out=response.getWriter();
		 LOGGER.info("database connection");
       String fileName="usersInfo.csv";
        FileWriter fw ;
        String s =System.getProperty ("os.name");
         LOGGER.info("os name= "+s);
	         String [] win= s.split(" ");
	         String winname=win[0];
	         LOGGER.info("windows name= "+winname);
	        String download_path = System.getProperty("user.home") + "/Downloads";
	        String uploadPath;
        try{
        	//DBTransaction DBT = new DBTransaction();
			 Connection con = DBTransaction.connect();
        Statement st = con.createStatement();
         LOGGER.info("connection is "+con);
 
        ResultSet res = st.executeQuery("select * from users_info");
         LOGGER.info("the quary is:"+res);
              int colunmCount = getColumnCount(res);
             // int i=0;
             //download_path = System.getProperty("user.home") + "/Downloads";
               try {
                 //String users_info = null;
				//fw = new FileWriter("/home/lavanya/sample.csv");
            	   String s1 =System.getProperty ("os.name");
  		          LOGGER.info("os name= "+s);
  		         win= s1.split(" ");
  		          winname=win[0];
  		         LOGGER.info("windows name= "+winname);
  		      
  		        if(winname.equalsIgnoreCase("Windows")){
  		        	//UPLOAD_DIRECTORY=download_path;
  		        	 uploadPath =  "/"+ download_path;
  		        	  LOGGER.info("upload path:"+uploadPath);
  		        }else{
  		        	//UPLOAD_DIRECTORY="/home";
  		        	uploadPath =  "/"+ download_path;
  		        	 LOGGER.info("uploadpath in linux:"+uploadPath);
  		        }
  		      /*response.setContentType("text/csv"); // Tell browser what content type the response body represents, so that it can associate it with e.g. MS Excel, if necessary.
          	   response.setHeader("Content-Disposition", "attachment; filename=fw"); // Force "Save As" dialogue
                 response.getWriter().write(csvAsString);*/
            	   /*JFileChooser chooser = new JFileChooser();
       			chooser.setCurrentDirectory(new java.io.File("."));
       			chooser.setDialogTitle("Chose location");
       			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
       			chooser.setAcceptAllFileFilterUsed(true);
       			int i=JFileChooser.CANCEL_OPTION;
       			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
       				 LOGGER.info("getCurrentDirectory(): "
       						+ chooser.getCurrentDirectory());
       				 LOGGER.info("getSelectedFile() : "
       						+ chooser.getSelectedFile());

       				dirname = chooser.getSelectedFile() + "/Downloads/";
       				 LOGGER.info(dirname);
       				File dir = new File(dirname);
       				dir.mkdirs();*/
            	    // Write CSV file to response. This will be saved in the location specified by the user.
       				/* for (int i = 0; i < files.length; i++) { */
       				//OutputStream outputStream1 = null;
       			 
       				
  				
 				
			fw = new FileWriter(uploadPath+"/usersInfo.csv");
                  //this loop is used to add column names at the top of file , if you do not need it just comment this loop
                  for(int k=1 ; k<= colunmCount ;k++)
                  {
                      fw.append(res.getMetaData().getColumnName(k));
                      fw.append(",");
                      // LOGGER.info("the column value is:"+i);
           
                  }
                   
                  fw.append(System.getProperty("line.separator"));
                   
                  while(res.next())
                  {
                      for(int k=1;k<=colunmCount;k++)
                      {
                           
                          //you can update it here by using the column type but i am fine with the data so just converting
                          //everything to string first and then saving
                          if(res.getObject(k)!=null)
                          {
                          String data= res.getObject(k).toString();
                         //  LOGGER.info("the string value is:"+data);
                          fw.append(data) ;
                          fw.append(",");
                          }
                          else
                          {
                              String data= "null";
                              fw.append(data) ;
                              fw.append(",");
                          }
                           
                      }
                      //new line entered after each row
                      fw.append(System.getProperty("line.separator"));
                  }

                  /*response.setContentType("text/csv"); // Tell browser what content type the response body represents, so that it can associate it with e.g. MS Excel, if necessary.
           	   response.setHeader("Content-Disposition", "attachment; filename=fw"); // Force "Save As" dialogue
                  response.getWriter().write(csvAsString);*/
                   fw.flush();
                    fw.close();
                  
		 			String uploadPathName=uploadPath+"/"+fileName;
		 			LOGGER.info("uploadPathName:"+uploadPathName);
		 			//System.out.println("uploadPathName:"+uploadPathName);
		 			File file = new File(uploadPathName);
		 			LOGGER.info("file::"+file);
		 			//System.out.println("file::"+file);
		 			if(!file.exists()){
		 				LOGGER.info("File doesn't exists on server.");
		 				//System.out.println("File doesn't exists on server.");
		 				throw new ServletException("File doesn't exists on server.");
		 			}
		 			LOGGER.info("File location on server::"+file.getAbsolutePath());
		 			//System.out.println("File location on server::"+file.getAbsolutePath());
		 			//ServletContext ctx = getServletContext();
		 			InputStream fis = new FileInputStream(file);
		 			//String mimeType = ctx.getMimeType(file.getAbsolutePath());
		 			response.setContentType("text/csv Charset=UTF-8");
		 			response.setContentLength((int) file.length());
		 			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

			      	
		 			FileInputStream inStream = new FileInputStream(uploadPathName);
			          LOGGER.info("inStream"+inStream);
			          FileInputStream fileInputStream = new FileInputStream(file);  
			          LOGGER.info("fileInputStream"+fileInputStream);
			          
			          int k;   
			          while ((k=inStream.read()) != -1) {  
			          out.write(k);   
			          }   
			          fileInputStream.close();   
			          out.close();   
                    String dataStatus="Data is stored in sample file";
               		request.setAttribute("Success",dataStatus);
               		RequestDispatcher requestDiapatcher=request.getRequestDispatcher("../JSP/DatabaseBackup.jsp");
               		requestDiapatcher.forward(request, response);
              
       			
               }
               catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              }
               
          
               
       // con.close();
        
        
      	  
        }
         
        catch (ClassNotFoundException e){
        System.err.println("Could not load JDBC driver");
        e.printStackTrace();
        }
        catch(SQLException ex){
        	ex.printStackTrace();
        System.err.println("SQLException information");
        } /*catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
       
       // pw.println("data is stored in home folder sample file");
        
        /*if(winname.equalsIgnoreCase("Windows")){
	        	UPLOAD_DIRECTORY=download_path;
	        	 uploadPath =  "/"+ download_path;
	        	  LOGGER.info("upload path:"+uploadPath);
	        	 String dataStatus="Data is stored in sample file";
	     		request.setAttribute("Success",dataStatus);
	     		RequestDispatcher rd=request.getRequestDispatcher("../JSP/DatabaseBackup.jsp");
	     		rd.forward(request, response);
	        }else{
	        	UPLOAD_DIRECTORY="/home";
	        	uploadPath =  "/"+ download_path;
	        	 LOGGER.info("uploadpath in linux:"+uploadPath);
	        	String dataStatus="Data is stored in sample file";
	    		request.setAttribute("Success",dataStatus);
	    		RequestDispatcher rd=request.getRequestDispatcher("../JSP/DatabaseBackup.jsp");
	    		rd.forward(request, response);
	        }*/
       /* String dataStatus="Data is Stored in Home Folder Sample File";
		request.setAttribute("OwnerSuccess",dataStatus);
		RequestDispatcher rd=request.getRequestDispatcher("../JSP/Home.jsp");
		rd.forward(request, response);*/
        
       
        
}
	
	
  //to get numbers of rows in a result set
  public static int  getRowCount(ResultSet res) throws SQLException
  {
        res.last();
        int numberOfRows = res.getRow();
        res.beforeFirst();
        return numberOfRows;
  }

  //to get no of columns in result set
   
  public static int  getColumnCount(ResultSet res) throws SQLException
  {
      return res.getMetaData().getColumnCount();
  }
   
	
}
