package com.kes.kote.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.kes.kote.dao.LookupDao;

import com.kes.kote.domain.EnrAttributesDomain;
import com.kes.kote.domain.StampCMTDomain;
import com.kes.kote.domain.StampEPWorkFlowDomain;
import com.kes.kote.domain.StampEnrollFormDomain;
import com.kes.kote.domain.StampNMTDomain;
import com.kes.kote.interfaces.LookupInterface;

public class StampExcelReading {
	
	public static List<StampEPWorkFlowDomain> getEPWorkFlowDetails(HttpSession sess)
	{
		List<StampEPWorkFlowDomain> ret=new  ArrayList<StampEPWorkFlowDomain>();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				ReadExcelBySheetName excelRead=new ReadExcelBySheetName();
				excelRead.processBySheet(ExcelSheetUtil.ENROLLWORKFLOW,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						StampEPWorkFlowDomain row=new StampEPWorkFlowDomain();
						row.setItemName(rowinfo.get(cols[1]));
						row.setStageName(rowinfo.get(cols[2]));
						row.setApprovedUsrID(rowinfo.get(cols[3]));
						
						//util.print("adding time : "+row.toString());
						ret.add(row);
					}
					
				}	
			}else //if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.MANUAL))
			{
				int spocsRowCount=ExcelSheetUtil.ENROLLWORKFLOWROWCOUNT-1;
				for(int i=0;i<spocsRowCount;i++)
				{
					StampEPWorkFlowDomain row=new StampEPWorkFlowDomain();
					if(i<10)
						row.setItemName("Enrollment Process");
					
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);
			}
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}
	
	public static List<StampEnrollFormDomain> getEnrollFormDetails(HttpSession sess)
	{
		List<StampEnrollFormDomain> ret=new ArrayList<StampEnrollFormDomain>();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			LookupInterface lookup=new LookupDao();
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				ReadExcelBySheetName excelRead=new ReadExcelBySheetName();
				excelRead.processBySheet(ExcelSheetUtil.ENROLLFORM,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						StampEnrollFormDomain row=new StampEnrollFormDomain();
						row.setAttributeId(rowinfo.get(cols[0]));
						row.setAttributeName(rowinfo.get(cols[1]));
						row.setIsRequired(rowinfo.get(cols[2]));
						row.setCaptionId(rowinfo.get(cols[3]));
						row.setCaptionDescription(rowinfo.get(cols[4]));
						row.setGroupNo(rowinfo.get(cols[5]));
						row.setGroupTitle(rowinfo.get(cols[6]));
						row.setArrtibuteSeqinGrp(rowinfo.get(cols[7]));
						row.setTabNo(rowinfo.get(cols[8]));
						row.setTabTitle(rowinfo.get(cols[9]));
						row.setAttributeSeqinTab(rowinfo.get(cols[10]));
						row.setGroupSeqinTab(rowinfo.get(cols[11]));
						row.setGridNo(rowinfo.get(cols[12]));
						row.setAttributeSeqinGrid(rowinfo.get(cols[13]));
						row.setSeqinScreen(rowinfo.get(cols[14]));
						row.setTextBoxSize(rowinfo.get(cols[15]));
						row.setCaptionFont(rowinfo.get(cols[16]));
						row.setCaptionSize(rowinfo.get(cols[17]));
						row.setCaptionColor(rowinfo.get(cols[18]));
						row.setBoldedCaption(rowinfo.get(cols[19]));
						row.setItalicCaption(rowinfo.get(cols[20]));
						row.setTextFont(rowinfo.get(cols[21]));
						row.setTextSize(rowinfo.get(cols[22]));
						row.setTextColor(rowinfo.get(cols[23]));
						row.setBoldedText(rowinfo.get(cols[24]));
						row.setItalicText(rowinfo.get(cols[25]));
						row.setIsMandatory(rowinfo.get(cols[26]));
						row.setIsEnabled(rowinfo.get(cols[27]));
						
						
						ret.add(row);
						
					}
				}
				
			}
			else
			{
				int spocsRowCount=ExcelSheetUtil.ENROLLFORMROWCOUNT-1;
				
				List<EnrAttributesDomain> EnrAttributes=lookup.getEnrAttributes(sess);
				
				for(int i=0;i<spocsRowCount;i++)
				{
					StampEnrollFormDomain row=new StampEnrollFormDomain();
					
					EnrAttributesDomain att=EnrAttributes.get(i);
					String attId=att.getAttribute_id();
					String attName=att.getAttribute_name();
					
					if(attId!=null)
						row.setAttributeId(attId);
					if(attName!=null)
						row.setAttributeName(attName);
					
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);
			}
			
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}

	public static List<StampCMTDomain> getCMTDetails(HttpSession sess)
	{
		List<StampCMTDomain> ret=new  ArrayList<StampCMTDomain>();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String[] commtype={"Login Credentials","Enrollment Credentials","Course Time Table","Group Setup","Current Status of Student",
								"Create Session by Faculty","Modify Session by Faculty","Create Assignment by Faculty","Evaluate Assignment by Faculty",
								"Submit Assignment by Student","Modify Assets by Faculty","Modify Group by Faculty"
							  };
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				ReadExcelBySheetName excelRead=new ReadExcelBySheetName();
				excelRead.processBySheet(ExcelSheetUtil.ACT,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						StampCMTDomain row=new StampCMTDomain();
						row.setCommunicationType(rowinfo.get(cols[0]));
						row.setSubject(rowinfo.get(cols[1]));
						row.setHeader(rowinfo.get(cols[2]));
						row.setBody(rowinfo.get(cols[3]));
						row.setFooter(rowinfo.get(cols[4]));
						row.setUIDOfSender(rowinfo.get(cols[5]));
						row.setKeyValForRef(rowinfo.get(cols[6]));
						
						ret.add(row);
					}
					
				}	
			}else //if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.MANUAL))
			{
				int spocsRowCount=ExcelSheetUtil.ACTROWCOUNT-1;
				for(int i=0;i<spocsRowCount;i++)
				{
					StampCMTDomain row=new StampCMTDomain();
					row.setCommunicationType(commtype[i]);
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);
			}
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}

	public static List<StampNMTDomain> getNMTDetails(HttpSession sess)
	{
		List<StampNMTDomain> ret=new  ArrayList<StampNMTDomain>();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String[] notitype={"Session","Assignment Allocation","Assignment Result","Assignment Status","Fee Reminder","Fee Receipt","Course Completion","Certificate Issue"};
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				ReadExcelBySheetName excelRead=new ReadExcelBySheetName();
				excelRead.processBySheet(ExcelSheetUtil.ANT,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						StampNMTDomain row=new StampNMTDomain();
						row.setNotificationType(rowinfo.get(cols[0]));
						row.setSubject(rowinfo.get(cols[1]));
						row.setHeader(rowinfo.get(cols[2]));
						row.setBody(rowinfo.get(cols[3]));
						row.setFooter(rowinfo.get(cols[4]));
						row.setUIDOfSender(rowinfo.get(cols[5]));
						row.setKeyValForRef(rowinfo.get(cols[6]));
						
						ret.add(row);
					}
					
				}	
			}else //if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.MANUAL))
			{
				int spocsRowCount=ExcelSheetUtil.ANTROWCOUNT-1;
				for(int i=0;i<spocsRowCount;i++)
				{
					StampNMTDomain row=new StampNMTDomain();
					row.setNotificationType(notitype[i]);
					
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);
			}
		}catch(Exception ex){ex.printStackTrace();}
		return ret;
	}
	

}
