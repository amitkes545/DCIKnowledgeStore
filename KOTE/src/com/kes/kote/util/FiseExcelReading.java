package com.kes.kote.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.kes.kote.domain.FiseCourseFeeMapDomain;
import com.kes.kote.domain.FiseFCSDomain;
import com.kes.kote.domain.FiseFPMDomain;
import com.kes.kote.domain.FiseFeeMapDomain;
import com.kes.kote.domain.FiseMemberFeesDomain;

public class FiseExcelReading {
	
	static ReadExcelBySheetName excelRead=new ReadExcelBySheetName();
	
	
	public static List<FiseFeeMapDomain> getFeeMapDetails(HttpSession sess)
	{
		List<FiseFeeMapDomain> ret=new  ArrayList<FiseFeeMapDomain>();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				excelRead.processBySheet(ExcelSheetUtil.FEEMAP,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						String feeCode=rowinfo.get(cols[0]);
						String feeDesc=rowinfo.get(cols[1]);
						String prefix=rowinfo.get(cols[2]);
						String body=rowinfo.get(cols[3]);
						String suffix=rowinfo.get(cols[4]);
						String instFeeDesc=rowinfo.get(cols[5]);
						String instFeeCode=rowinfo.get(cols[6]);
						
						FiseFeeMapDomain row=new FiseFeeMapDomain();
						row.setFeeCode(feeCode);
						row.setFeeDesc(feeDesc);
						row.setPrefix(prefix);
						row.setBody(body);
						row.setSuffix(suffix);
						row.setInstFeeDesc(instFeeDesc);
						row.setInstFeeCode(instFeeCode);
						
						ret.add(row);
					}
				}
			}else //if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.MANUAL))
			{
				int spocsRowCount=ExcelSheetUtil.FEEMAPROWCOUNT-1;
				
				String[] feeIds={"FEE_001","FEE_002","FEE_003","FEE_004","FEE_005","FEE_006","FEE_007","FEE_008","FEE_009","FEE_010","FEE_011","FEE_012","FEE_013","FEE_014"};
				String[] feeNames={"Admission Fees","Tution Fees","Development Fee","Library Fee","Special Fees","Exam Fees","Arrear Exam Fees","Download Fees","Insuffecient Attendance Fees","Faculty Registration Fees","Late Fees","Certificate Fees","Postal Charges","Security Deposit"};
				for(int i=0;i<spocsRowCount;i++)
				{
					FiseFeeMapDomain row=new FiseFeeMapDomain();
					if(i<13)
					{
						row.setFeeCode(feeIds[i]);
						row.setFeeDesc(feeNames[i]);
					}
					
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);
			}
		}catch(Exception ex){}
		
		return ret;
	}
	
	public static List<FiseCourseFeeMapDomain> getCrsFeeMapDetails(HttpSession sess)
	{
		List<FiseCourseFeeMapDomain> ret=new  ArrayList<FiseCourseFeeMapDomain>();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				excelRead.processBySheet(ExcelSheetUtil.CFMAP,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						String deptId=rowinfo.get(cols[0]);
						String feeSequence=rowinfo.get(cols[1]);
						String instFeeId=rowinfo.get(cols[2]);
						String group=rowinfo.get(cols[3]);
						String feeAmount=rowinfo.get(cols[4]);
						String crsFeeId=rowinfo.get(cols[5]);
						String feeType=rowinfo.get(cols[6]);
						String feeRecurring=rowinfo.get(cols[7]);
						
						FiseCourseFeeMapDomain row=new FiseCourseFeeMapDomain();
						row.setDeptId(deptId);
						row.setFeeSequence(convertToInt(feeSequence));
						row.setInstFeeId(instFeeId);
						row.setGroup(group);
						row.setFeeAmount(convertToDouble(feeAmount));
						row.setCrsFeeId(crsFeeId);
						row.setFeeType(feeType);
						row.setFeeRecurring(convertToInt(feeRecurring));
						
						
						ret.add(row);
					}
				}
			}else //if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.MANUAL))
			{
				int spocsRowCount=ExcelSheetUtil.CFMAPROWCOUNT-1;
				for(int i=0;i<spocsRowCount;i++)
				{
					FiseCourseFeeMapDomain row=new FiseCourseFeeMapDomain();
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);
			}
		}catch(Exception ex){}
		
		return ret;
	}
	
	public static List<FiseMemberFeesDomain> getMemberFeeDetails(HttpSession sess)
	{
		List<FiseMemberFeesDomain> ret=new  ArrayList<FiseMemberFeesDomain>();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				excelRead.processBySheet(ExcelSheetUtil.MEMBERFEE,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						String deptId=rowinfo.get(cols[0]);
						String groupFeeId=rowinfo.get(cols[1]);
						String groupSeq=rowinfo.get(cols[2]);
						String memberFeeId=rowinfo.get(cols[3]);
						String memberFeeVal=rowinfo.get(cols[4]);
						String memberFeeType=rowinfo.get(cols[5]);
						
						
						FiseMemberFeesDomain row=new FiseMemberFeesDomain();
						row.setDeptId(deptId);
						row.setGroupFeeId(groupFeeId);
						row.setGroupSeq(convertToInt(groupSeq));
						row.setMemberFeeId(memberFeeId);
						row.setMemberFeeVal(convertToDouble(memberFeeVal));
						row.setMemberFeeType(memberFeeType);
						
						ret.add(row);
					}
				}
			}else //if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.MANUAL))
			{
				int spocsRowCount=ExcelSheetUtil.MEMBERFEEROWCOUNT-1;
				for(int i=0;i<spocsRowCount;i++)
				{
					FiseMemberFeesDomain row=new FiseMemberFeesDomain();
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);
			}
		}catch(Exception ex){}
		
		return ret;
	}
	
	public static List<FiseFPMDomain> getFPMDetails(HttpSession sess)
	{
		List<FiseFPMDomain> ret=new  ArrayList<FiseFPMDomain>();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				excelRead.processBySheet(ExcelSheetUtil.FPM,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						String gatewayId=rowinfo.get(cols[0]);
						String gatewayName=rowinfo.get(cols[1]);
						String gatewayURL=rowinfo.get(cols[2]);
						String parameterList=rowinfo.get(cols[3]);
												
						FiseFPMDomain row=new FiseFPMDomain();
						row.setGatewayId(gatewayId);
						row.setGatewayName(gatewayName);
						row.setGatewayURL(gatewayURL);
						row.setParameterList(parameterList);
						
						ret.add(row);
					}
				}
			}else //if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.MANUAL))
			{
				int spocsRowCount=ExcelSheetUtil.FPMROWCOUNT-1;
				for(int i=0;i<spocsRowCount;i++)
				{
					FiseFPMDomain row=new FiseFPMDomain();
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);
			}
		}catch(Exception ex){}
		
		return ret;
	}
	
	public static List<FiseFCSDomain> getFCSDetails(HttpSession sess)
	{
		List<FiseFCSDomain> ret=new  ArrayList<FiseFCSDomain>();
		try
		{
			SessionUtil util=(SessionUtil) sess.getAttribute("SessionUtil");
			
			String ManualorUpload=util.getConfigLayerbyManualorUpload();
			
			if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.UPLOAD))
			{
				excelRead.processBySheet(ExcelSheetUtil.FCMS,sess);
				
				ArrayList<HashMap<String,String>> info=util.getSheetDetails();
				if(info!=null && info.size()>0)
				{
					for(int i=0;i<info.size();i++)
					{
						HashMap<String,String> rowinfo=info.get(i);
						String[] cols=util.getSheetCols();
						
						String category=rowinfo.get(cols[0]);
						String subCategory=rowinfo.get(cols[1]);
						String assign=rowinfo.get(cols[2]);
						/*String gbp=rowinfo.get(cols[3]);
						String euro=rowinfo.get(cols[4]);
						String lcy=rowinfo.get(cols[5]);
						String others=rowinfo.get(cols[6]);*/
						
						FiseFCSDomain row=new FiseFCSDomain();
						row.setCategory(category);
						row.setSubCategory(subCategory);
						row.setAssign(assign);
						/*row.setUsd(usd);
						row.setGbp(gbp);
						row.setEuro(euro);
						row.setLcy(lcy);
						row.setOthers(others);*/
						
						ret.add(row);
					}
				}
			}else //if(ManualorUpload.trim().equalsIgnoreCase(ExcelSheetUtil.MANUAL))
			{
				String[] category={"Cash","Cheque/Draft","Cheque/Draft","Online","Online","Online","Online","Wallet"};
				String[] subcategory={"Cash","Cheque","Draft","Debit Card","Credit Card","Internet Banking","ATM Pay Debit","Wallet"};
				
				int spocsRowCount=ExcelSheetUtil.FCMSROWCOUNT-1;
				for(int i=0;i<spocsRowCount;i++)
				{
					FiseFCSDomain row=new FiseFCSDomain();
					if(i<9)
					{
						row.setCategory(category[i]);
						row.setSubCategory(subcategory[i]);
					}
					ret.add(row);
				}
				util.setTotRow(spocsRowCount);
			}
		}catch(Exception ex){}
		
		return ret;
	}
	

	static int convertToInt(String val)
	{
		try
		{
			return Integer.parseInt(val);
		}catch(Exception ex){ }
		return -1;
	}
	
	static double convertToDouble(String val)
	{
		try
		{
			return Double.parseDouble(val);
		}catch(Exception ex){}
		return 0.0;
	}
}
