package com.kes.kote.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kes.kote.dao.CommonDao;
import com.kes.kote.domain.CineOrgDetailsDomain;
import com.kes.kote.util.CLModuleTaskControlUtil;
import com.kes.kote.util.ExcelSheetUtil;
import com.kes.kote.util.PropertiesUtil;
import com.kes.kote.util.SessionUtil;

public class UploadExcelService extends CommonService
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 100;  // 100MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 100; // 100MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 100; // 100MB
    

	@Override
	public void run() throws ServletException, IOException, FileUploadException, Exception {
		// TODO Auto-generated method stub
		
		session=request.getSession(true);
		
		String From=request.getParameter("From");
		
		if(From.trim().equalsIgnoreCase("Manually"))
			callConfigLayerbyManually();
		else if(From.trim().equalsIgnoreCase("ConfigUpload"))
			ConfigUpload();
		else if(From.trim().equalsIgnoreCase("StudentUpload"))
			StudentUpload();
		else if(From.trim().equalsIgnoreCase("TeachingSource"))
			TeachingSource();
		
		
	}

	public void callConfigLayerbyManually()
	{
		try
		{  
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			util.setConfigLayerbyManualorUpload("Manual");
			response.sendRedirect("../JSP/cine-setup-middle-layer.jsp");
			
		}catch(Exception ex){ex.printStackTrace();}
	}
	@SuppressWarnings("unchecked")
	public void ConfigUpload()
	{
		SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
		
		try
		{
			util.setConfigLayerbyManualorUpload("Upload");
			try
			{
				String fileName="";
				
				FileOutputStream output;
				// configures upload settings
				DiskFileItemFactory factory = new DiskFileItemFactory();
		    	
	        	ServletContext servletContext = request.getSession().getServletContext();
	    		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
	    		if(repository==null)
	    		{
	    			repository = new File(System.getProperty("java.io.tmpdir"));
	    		}
	    		factory.setRepository(repository);
	    		
	    		ServletFileUpload upload = new ServletFileUpload(factory);
	    		// sets memory threshold - beyond which files are stored in disk
	            factory.setSizeThreshold(MEMORY_THRESHOLD);
	            
	    		// sets maximum size of upload file
	            upload.setFileSizeMax(MAX_FILE_SIZE);
	             
	            // sets maximum size of request (include file + form data)
	            upload.setSizeMax(MAX_REQUEST_SIZE);
	    			
	    		List<FileItem> items = upload.parseRequest(request);
	    		java.util.Iterator<FileItem> iter = items.iterator();
	    		InputStream inputStream = null;
	    		
	    		if (iter.hasNext()) {
	    		    FileItem item = iter.next();

	    		    if (item.isFormField()) {
	    		    	
	    		    }
	    		    else {
	    		    	
	    		        fileName = item.getName();
	    		        util.print("FileName here...:"+fileName);
	    		        inputStream=item.getInputStream();
	    		       }
	    		}
	    	
	    		Random rand = new Random();
				String id = String.format("%05d", rand.nextInt(100000));
				fileName=id+fileName;
	    		
				util.print("fileName == "+fileName);
				
	    		File f=new File(servletContext.getRealPath("/")+fileName);
	    		util.print("setWritable == "+f.setWritable(true));
	    		
    			 
	    		util.print("getRealPath == "+servletContext.getRealPath("/"));
	    		util.print("F path == "+f.getAbsolutePath());
	    		util.print("File Status == "+f.exists());
	    		
	    		if(f.exists())
    				 f.delete();
    				 
    			 f.createNewFile();
    			 output = new FileOutputStream(f);
    			 int k=0;
    			 while((k=inputStream.read())!=-1){
    				 output.write(k);
    			 }
    			 output.close();
    			 inputStream.close();
    			 
    			 util.setExcelPath(f.getAbsolutePath());
    			 
    			 
	    		
			}catch(Exception ex){ex.printStackTrace(); util.print(ex.getMessage()); }
			    		
			
			util.setsuccessmsg(PropertiesUtil.getMessageProperty("uploadexcel.success"));
			checkModuleTaskforOrg();
			
		}catch(Exception ex){ex.printStackTrace();	util.print(ex.getMessage());	}
	}
	
	public void checkModuleTaskforOrg()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			CineOrgDetailsDomain  domain= ExcelSheetUtil.getOrgDetails(session);
			CommonDao commndao=new CommonDao();
			String OrgID=commndao.checkAndGetOrgId(domain,session);
			String lastCompTask=commndao.getLastCompletedTaskIdbyOrgId(OrgID,session); // 5 
			
			String NxtTaskFile=CLModuleTaskControlUtil.getNxtTaskFile(lastCompTask); // 6th jsp name
			
			util.print("redirect to .... "+NxtTaskFile);
			
			response.sendRedirect("../JSP/"+NxtTaskFile);
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void StudentUpload()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			util.setfailedmsg(PropertiesUtil.getMessageProperty("studentmodule.failed"));
			response.sendRedirect("../JSP/upload-excel.jsp");
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void TeachingSource()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			String Selected=request.getParameter("Selected").toString().trim();
			util.setTeachingSource(Selected); // VTI, Corporate, university , professional
			
			response.sendRedirect("../JSP/upload-excel.jsp");
		}catch(Exception ex){ex.printStackTrace();}
	}
}
