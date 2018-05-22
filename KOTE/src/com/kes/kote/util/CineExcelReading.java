package com.kes.kote.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.kes.kote.domain.CineOrgDetailsDomain;
import com.kes.kote.domain.CineSpocsDomain;

public class CineExcelReading {
	
	static ReadExcelBySheetName excelRead=new ReadExcelBySheetName();
	public static CineOrgDetailsDomain getOrgDetails(HttpSession sess)
	{
		CineOrgDetailsDomain ret=new CineOrgDetailsDomain();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				
				excelRead.processBySheet(ExcelSheetUtil.ORGDETAILS,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				HashMap<String,String> rowinfo=new HashMap<String,String>();
				if(info!=null && info.size()>0)
					rowinfo=info.get(0);

				String[] cols=util.getSheetCols();
				
				ret.setOrgName(rowinfo.get(cols[0]));
				ret.setOrgLastName(rowinfo.get(cols[1]));
				ret.setYearofEst(rowinfo.get(cols[2]));
				ret.setAddress(rowinfo.get(cols[3]));
				ret.setCountry(rowinfo.get(cols[4]));
				ret.setState(rowinfo.get(cols[5]));
				ret.setCity(rowinfo.get(cols[6]));
				ret.setPinCode(rowinfo.get(cols[7]));
				ret.setWebsite(rowinfo.get(cols[8]));
				ret.setLogoName(rowinfo.get(cols[9]));
				ret.setLogoSize(rowinfo.get(cols[10]));
				ret.setKsSpace(rowinfo.get(cols[11]));
				ret.setSpaceUOM(rowinfo.get(cols[12]));
				ret.setTeachingType(rowinfo.get(cols[13]));
				ret.setCurrencyCode(rowinfo.get(cols[14]));
				ret.setDateFormat(rowinfo.get(cols[15]));
		
			}
					
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}

	public static List<CineSpocsDomain> getSpocsDetails(HttpSession sess)
	{
		List<CineSpocsDomain> ret=new  ArrayList<CineSpocsDomain>();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				
				excelRead.processBySheet(ExcelSheetUtil.SPOCDETAILS,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i); // <Department_Name,Administration> , <SPOC_Name,akash> , ....
						String[] cols=util.getSheetCols();
						
						String DeptName=rowinfo.get(cols[0]); // cols[0] = "Department_Name" 
						String SPOCName=rowinfo.get(cols[1]); //  cols[1] = "SPOC_Name"
						String Disgn=rowinfo.get(cols[2]);
						String MobNo=rowinfo.get(cols[3]);
						String LLNo=rowinfo.get(cols[4]);
						String ExtNo=rowinfo.get(cols[5]);
						String FaxNo=rowinfo.get(cols[6]);
						String Email=rowinfo.get(cols[7]);
						
						
						CineSpocsDomain row=new CineSpocsDomain();
						row.setDeptName(DeptName);
						row.setSPOCName(SPOCName);
						row.setDisgn(Disgn);
						row.setMobNo(MobNo);
						row.setLLNo(LLNo);
						row.setExtNo(ExtNo);
						row.setFaxNo(FaxNo);
						row.setEmail(Email);
							
						//SessionUtil.print("adding time : "+row.toString());
						ret.add(row);	
						
							
						
					}
					
				}	
			}else //if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.MANUAL))
			{
				int spocsRowCount=ExcelSheetUtil.SPOCROWCOUNT-1;
				for(int i=0;i<spocsRowCount;i++)
				{
					CineSpocsDomain row=new CineSpocsDomain();
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);
			}
			
			/*for(int i=0;i<ret.size();i++)
			{
				SessionUtil.print("after adding == "+ret.get(i).toString());
			}*/
			
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}
	

}
