package com.kds.KODE_DEV.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.JsonObject;
import com.kds.KODE_DEV.dao.CreateSessionDao;
import com.kds.KODE_DEV.dao.CreateStudentDao;
import com.kds.KODE_DEV.dao.InsertStudentDao;
import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.AdminDomain;

public class CreateStudentFromExcelUploadService extends CommonService {
	static final Logger LOGGER = Logger.getLogger(CreateStudentFromExcelUploadService.class);
/*    private static final long serialVersionUID = 1L;
    private static final String TMP_DIR_PATH = "/MyTempFiles";
    private File tmpDir;
    private static final String DESTINATION_DIR_PATH ="/MySavedFiles";
    private File destinationDir;

    public CreateStudentFromExcelUpload() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {

        super.init(config);
        String realPath = getServletContext().getRealPath(TMP_DIR_PATH);
        tmpDir = new File(realPath);
        if(!tmpDir.isDirectory()) {
            throw new ServletException(TMP_DIR_PATH + " is not a directory");
        }

        realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
        destinationDir = new File(realPath);
        if(!destinationDir.isDirectory()) {
            throw new ServletException(DESTINATION_DIR_PATH+" is not a directory");
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {*/
	
	 @SuppressWarnings("null")
	public void run() throws Exception {
//System.out.println("in do post");

session=request.getSession(true);
String orgid=(String)session.getAttribute("orgid");
String userid=(String)session.getAttribute("userid");

        //PrintWriter to send the JSON response back
        PrintWriter out = response.getWriter();
    	InsertStudentDao insertStudentDao=new InsertStudentDao();
    	
        //set content type and header attributes
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");

        DiskFileItemFactory  fileItemFactory = new DiskFileItemFactory ();

        //Set the size threshold, above which content will be stored on disk.
        fileItemFactory.setSizeThreshold(1*1024*1024); //1 MB
        

        ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
        JsonObject myObj = new JsonObject();

        int successrow=0;
        String exception=null;
       // String orgid=request.getParameter("orgid").toString().trim();
       // System.out.println("orgid="+orgid);

        try {
        	//System.out.println("in try");
            //Parse the request
        	CreateStudentDao cStudentDao=new CreateStudentDao();
        	
        	 AdminDomain aDomain=new AdminDomain();
        	FileOutputStream output=null;
			FileInputStream input=null;
			
          	DiskFileItemFactory factory = new DiskFileItemFactory();
	    	
        	ServletContext servletContext = request.getSession().getServletContext();
    		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
    	
    		factory.setRepository(repository);
    		ServletFileUpload upload = new ServletFileUpload(factory);
    		factory.setSizeThreshold(20480000);
    		upload.setSizeMax(51500000);
    		
    		List<FileItem> items = upload.parseRequest(request);
    		java.util.Iterator<FileItem> iter = items.iterator();
    		InputStream inputStream = null;
    		long sizeInBytes=0;
    		String sql = null,fileName="",string1="",sql1="";
    		while (iter.hasNext()) {
    		    FileItem item = iter.next();

    		    if (item.isFormField()) {
    		    	
    		    }
    		    else {
    		    	
    		        fileName = item.getName();
    		        
    		        inputStream=item.getInputStream();
    		        string1=item.getString();
    		      
    		    }
    		}
    		String OS =   System.getProperty("os.name"); 
    		     // return OS;
    		//System.out.println("OS="+OS);
    		   
    		 if(OS.contains("Windows"))
    		 {
    			// System.out.println("in windows");
    			 output = new FileOutputStream("E:\\PRITY\\KES\\"+fileName); 
    	
    		 }
    		 else
    		 {
    			// System.out.println("in linux");
    			 output = new FileOutputStream("//tmp//"+fileName);
    			
    		 }
    		int k=inputStream.read();
    		while(k!=-1){
    		output.write(k);
    		k=inputStream.read();
    		}
    		output.close();
      
           if(OS.contains("Windows"))
    		 {
        	   input = new FileInputStream("E:\\PRITY\\KES\\"+fileName);
    		 }
           else
           {
        	 
        	 input = new FileInputStream("//tmp//"+fileName);
           }
         

           String fileExtn = GetFileExtension(fileName);
    
    		XSSFWorkbook wbx=null;
    		HSSFWorkbook wbh=null;
    		XSSFSheet sheetx=null;
    		HSSFSheet sheeth=null;
    		Sheet sheet=null;
     
    		if (fileExtn.equalsIgnoreCase("xlsx"))
    	      {
    			 wbx = new XSSFWorkbook(input);
	            sheet = wbx.getSheetAt(0);
    	      }
    		if (fileExtn.equalsIgnoreCase("xls"))
    	      {
    		
            POIFSFileSystem fs = new POIFSFileSystem( input );
             wbh = new HSSFWorkbook(fs);
            sheet = wbh.getSheetAt(0);
    	      }
    	
            Row row;
            String studID="",firstname="",concatemp="",lastname="",email="",gender="",country="",state="",city="",address="",postalcode="";
		String dob="";
			String mobile="";
            double sales_inc=0.0;
  
            session=request.getSession(false);
           
            Iterator rows = sheet.rowIterator();
            System.out.println("row="+sheet.getLastRowNum());
            for(int i=1; i<=sheet.getLastRowNum(); i++){
            	 ArrayList<AdminDomain> arrayList=new ArrayList<AdminDomain>();
                row = sheet.getRow(i);
                
                XSSFCell studidx=null,fnamex=null,lnamex=null,mobilex=null,emailx=null,dobx=null,countryx=null,statex=null,cityx=null,genderx=null,postalcodex=null,addressx=null;
                HSSFCell studidh=null,fnameh=null,lnameh=null,mobileh=null,emailh=null,dobh=null,countryh=null,stateh=null,cityh=null,genderh=null,postalcodeh=null,addressh=null;
                int studIDTypeh=0,lnameTypeh=0,fnameTypeh=0,mobileTypeh=0,emailTypeh=0,dobTypeh=0,countryTypeh=0,stateTypeh=0,cityTypeh=0,genderTypeh=0,addressTypeh=0;
                if (fileExtn.equalsIgnoreCase("xlsx"))
	    	      {
                	studidx=(XSSFCell)row.getCell(0);
                	 System.out.println("studidx"+studidx);
                	 studIDTypeh = studidx.getCellType();
                	 fnamex = (XSSFCell)row.getCell(1);
                	 System.out.println("fname"+fnamex);
                	 lnamex = (XSSFCell)row.getCell(2);
 	               //System.out.println("lname"+lnamex);
 	              mobilex=(XSSFCell)row.getCell(3);
 	              // System.out.println("mobile_no"+mobilex);
 	                fnameTypeh = fnamex.getCellType();
 	           //    System.out.println("fnameTypeh"+fnameTypeh);
 	                lnameTypeh = lnamex.getCellType();
                //System.out.println("lnameTypeh"+lnameTypeh);
                mobileTypeh = mobilex.getCellType();
                emailx=(XSSFCell)row.getCell(4);
                emailTypeh = emailx.getCellType();
                dobx=(XSSFCell)row.getCell(5);
                dobTypeh = dobx.getCellType();
                
                genderx=(XSSFCell)row.getCell(6);
                genderTypeh = genderx.getCellType();
                countryx=(XSSFCell)row.getCell(7);
                countryTypeh = countryx.getCellType();
                statex=(XSSFCell)row.getCell(8);
                stateTypeh = statex.getCellType();
                cityx=(XSSFCell)row.getCell(9);
                cityTypeh = cityx.getCellType();
                postalcodex=(XSSFCell)row.getCell(11);
               // pcodeTypeh = postalcodex.getCellType();
                addressx=(XSSFCell)row.getCell(10);
                addressTypeh = addressx.getCellType();
                System.out.println("dobTypeh="+dobx.getCellType());
	    	      }else if (fileExtn.equalsIgnoreCase("xls"))
	    	      {
	    	    	  studidh=(HSSFCell)row.getCell(0);
	                	 studIDTypeh = studidh.getCellType();
	    	    	  fnameh = (HSSFCell)row.getCell(1);
	                	// System.out.println("fname"+fnamex);
	                	 lnameh = (HSSFCell)row.getCell(2);
	 	               //System.out.println("lname"+lnamex);
	 	              mobileh=(HSSFCell)row.getCell(3);
	 	              // System.out.println("mobile_no"+mobilex);
	 	                fnameTypeh = fnameh.getCellType();
	 	           //    System.out.println("fnameTypeh"+fnameTypeh);
	 	                lnameTypeh = lnameh.getCellType();
	                //System.out.println("lnameTypeh"+lnameTypeh);
	                mobileTypeh = mobileh.getCellType();
	                emailh=(HSSFCell)row.getCell(4);
	                emailTypeh = emailh.getCellType();
	                dobh=(HSSFCell)row.getCell(5);
	                dobTypeh = dobh.getCellType();
	                genderh=(HSSFCell)row.getCell(6);
	                genderTypeh = genderh.getCellType();
	                countryh=(HSSFCell)row.getCell(7);
	                countryTypeh = countryh.getCellType();
	                stateh=(HSSFCell)row.getCell(8);
	                stateTypeh = stateh.getCellType();
	                cityh=(HSSFCell)row.getCell(9);
	                cityTypeh = cityh.getCellType();
	                postalcodeh=(HSSFCell)row.getCell(11);
	               // pcodeTypeh = postalcodeh.getCellType();
	                addressh=(HSSFCell)row.getCell(10);
	                addressTypeh = addressh.getCellType();
         
              //  System.out.println("Imc"+cellTsalesh);
	    	      }
                System.out.println("before if");
                if ((studIDTypeh == XSSFCell.CELL_TYPE_STRING || studIDTypeh == HSSFCell.CELL_TYPE_STRING) 
                	&&	(fnameTypeh == XSSFCell.CELL_TYPE_STRING || fnameTypeh == HSSFCell.CELL_TYPE_STRING) 
                	&& (lnameTypeh == XSSFCell.CELL_TYPE_STRING || lnameTypeh == HSSFCell.CELL_TYPE_STRING) 
                	&& (emailTypeh == XSSFCell.CELL_TYPE_STRING || emailTypeh == HSSFCell.CELL_TYPE_STRING) 
                	&& (mobileTypeh == XSSFCell.CELL_TYPE_NUMERIC || mobileTypeh == HSSFCell.CELL_TYPE_NUMERIC)
                /*	&& (dobTypeh == XSSFCell.CELL_TYPE_STRING || dobTypeh == HSSFCell.CELL_TYPE_STRING) 
                */	&& (genderTypeh == XSSFCell.CELL_TYPE_STRING || genderTypeh == HSSFCell.CELL_TYPE_STRING) 
                	&& (countryTypeh == XSSFCell.CELL_TYPE_STRING || countryTypeh == HSSFCell.CELL_TYPE_STRING) 
                	&& (stateTypeh == XSSFCell.CELL_TYPE_STRING || stateTypeh == HSSFCell.CELL_TYPE_STRING) 
                	&& (cityTypeh == XSSFCell.CELL_TYPE_STRING || cityTypeh == HSSFCell.CELL_TYPE_STRING) 
                	&& (addressTypeh == XSSFCell.CELL_TYPE_STRING || addressTypeh == HSSFCell.CELL_TYPE_STRING) 
               ) {
                	System.out.println("in if");
              
            	     
                	System.out.println("in xlsx");
                	
            	     studID = new DataFormatter().formatCellValue(studidx);//row.getCell(0).getStringCellValue();
            	     if(studID=="")
            	    	   studID = new DataFormatter().formatCellValue(studidh);
                	 firstname = row.getCell(1).getStringCellValue();
                	 lastname = row.getCell(2).getStringCellValue();
                	// System.out.println(new DataFormatter().formatCellValue(mobilex));
                	/* if(mobilex.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                		 mobilex.setCellType(Cell.CELL_TYPE_STRING);
                	}*/
                	// Double d=row.getCell(2).getNumericCellValue();
                	 mobile = new DataFormatter().formatCellValue(mobilex);
                	 if(mobile=="")
                		 mobile = new DataFormatter().formatCellValue(mobileh);
                	 email = row.getCell(4).getStringCellValue();
                	 System.out.println("in mobile="+mobile);
                  	 boolean email_conflict=insertStudentDao.email_conflict(email);
            	     if(email_conflict==true)
            	        {  
            	    	// String Studentymsg="Email ID already exist.";
            				/*request.setAttribute("FacultyFailure",Studentymsg);
            				RequestDispatcher rd=request.getRequestDispatcher("../JSP/Student.jsp");
            				rd.forward(request, response);*/
            				continue;
            	        }
            	     boolean phone_conflict=insertStudentDao.phone_conflict(mobile);
            	     if(phone_conflict==true)
            	        {  
            	    	 //String Studentymsg="Phone No. already exist.";
            				/*request.setAttribute("FacultyFailure",Studentymsg);
            				RequestDispatcher rd=request.getRequestDispatcher("../JSP/Student.jsp");
            				rd.forward(request, response);*/
            	    	 continue;
            	        }
//System.out.println("in mobile="+mobile.toString());
                	 
                	// dob = row.getCell(4).getNumericCellValue();
                	 dob=new DataFormatter().formatCellValue(dobx);
                	 if(dob=="")
                		 dob=new DataFormatter().formatCellValue(dobh);
                //	 System.out.println("in dob="+dob);
                	 gender = row.getCell(6).getStringCellValue();
                	 country = row.getCell(7).getStringCellValue();
                	 state = row.getCell(8).getStringCellValue();
                	 city = row.getCell(9).getStringCellValue();
                	 address = row.getCell(10).getStringCellValue();
                	// System.out.println("in address="+address);
                	 postalcode = row.getCell(11).getStringCellValue();
                	 System.out.println("in postalcode="+postalcode);
                	// System.out.println("in mobile="+mobile);
                	// System.out.println("in lastname="+lastname);
                	 
                	 aDomain.setAdminName(firstname+" "+lastname);
                	/* if(studID=="")
                		 aDomain.setAdminId(cStudentDao.generateUserID(firstname,email,mobile));
                	 else
                	*/	 aDomain.setAdminId(studID);
                	 aDomain.setPhone(String.valueOf(mobile));
                	 aDomain.setEmail(email);
                	 aDomain.setDateofbirth(cStudentDao.changeStringtoSqlDate(String.valueOf(dob)));
                	 aDomain.setGender(gender);
                	 aDomain.setCountry(country);
                	 aDomain.setState(state);
                	 aDomain.setCity(city);
                	 aDomain.setAddress(address);
                	 aDomain.setPostalCode(postalcode);
                	 aDomain.setPwd(cStudentDao.randomSeriesForThreeCharacter());
                	 aDomain.setOrgid(orgid);
                	 aDomain.setCreated_by(userid);
                	 // String subname=getSubjectName();
                	// String course_id=getCourseId();
                	// String course=request.getParameter("stream");
                	 // if(!(subname.equals(subject)))
                	//System.out.println("postalcode="+postalcode);
                	 arrayList.add(aDomain);
                	 int count=cStudentDao.insertStudentsInfo(arrayList);
                    	System.out.println("count="+count);
                    	if(count==1)
                    		successrow++;
                	 
                }else
                {
                	System.out.println("in else");
                		throw new IOException(); 
                }
            }
            System.out.println("successrow="+successrow);
            input.close();
         
       }catch(IOException e){
        	exception="Incorrect File format: 1st column-employee id(Text), 2nd- Sales Incentive(Numeric)";
        	
            System.out.println(e);
        }catch(Exception ioe){
        	//exception=ioe;
        	System.out.println("Exception occured as below:"+ioe);
            ioe.printStackTrace();
        } 
        RequestDispatcher rd=null;
        if(successrow>0){
        	
	    	   String studetnStatus=successrow+" Students created successfully.";
	    	  request.setAttribute("SuccessMessage",studetnStatus);
        rd =request.getRequestDispatcher("../JSP/create-student.jsp");
        
        }
        else{
        	String studetnStatus="Failed to create student.";
 	    	  request.setAttribute("FailureMessage",studetnStatus);
        	rd =request.getRequestDispatcher("../JSP/create-student.jsp");
        }
        rd.forward(request, response);

    }

	/*private int processExcelFile(File file){


        int count = 0;

        try{
            // Creating Input Stream 
            FileInputStream myInput = new FileInputStream(file);

            // Create a workbook using the File System 
            XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);

            // Get the first sheet from workbook 
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);

            *//** We now need something to iterate through the cells.**//*
            Iterator<Row> rowIter = mySheet.rowIterator();
            while(rowIter.hasNext()){

                XSSFRow myRow = (XSSFRow) rowIter.next();
                Iterator<Cell> cellIter = myRow.cellIterator();
                while(cellIter.hasNext()){

                    XSSFCell myCell = (XSSFCell) cellIter.next();
                    //get cell index
                    System.out.println("Cell column index: " + myCell.getColumnIndex());
                    //get cell type
                    System.out.println("Cell Type: " + myCell.getCellType());

                    //get cell value
                    switch (myCell.getCellType()) {
                    case XSSFCell.CELL_TYPE_NUMERIC :
                        System.out.println("Cell Value: " + myCell.getNumericCellValue());
                        break;
                    case XSSFCell.CELL_TYPE_STRING:   
                        System.out.println("Cell Value: " + myCell.getStringCellValue());
                        break;
                    default:   
                        System.out.println("Cell Value: " + myCell.getRawValue());
                        break;   
                    }
                    System.out.println("---");

                    if(myCell.getColumnIndex() == 0 && 
                            !myCell.getStringCellValue().trim().equals("") &&
                            !myCell.getStringCellValue().trim().equals("Item Number")){
                        count++;
                    }

                }

            }
        }
        catch (Exception e){
            e.printStackTrace(); 
        }

        return count;

    }*/
   

    private static String GetFileExtension(String fname2)
    {
        String fileName = fname2;
        String fname="";
        String ext="";
        int mid= fileName.lastIndexOf(".");
        fname=fileName.substring(0,mid);
        ext=fileName.substring(mid+1,fileName.length());
        return ext;
    }
   }