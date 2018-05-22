package com.kes.kote.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileUploadException;

import com.kes.kote.domain.FiseCourseFeeMapDomain;
import com.kes.kote.domain.FiseFCSDomain;
import com.kes.kote.domain.FiseFPMDomain;
import com.kes.kote.domain.FiseFeeMapDomain;
import com.kes.kote.domain.FiseMemberFeesDomain;
import com.kes.kote.interfaces.FiseInterface;
import com.kes.kote.interfaces.FiseInterfaceImpl;
import com.kes.kote.util.SessionUtil;

public class FiseService extends CommonService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	FiseInterface FiseIF=new FiseInterfaceImpl();
	
	
	@Override
	public void run() throws ServletException, IOException, FileUploadException, Exception {
		// TODO Auto-generated method stub
		
		session=request.getSession(true);
		
		String From=request.getParameter("From").toString().trim();
		if(From.trim().equalsIgnoreCase("FeeMap"))
			actionFeeMapDetails();
		else if(From.trim().equalsIgnoreCase("CrsFeeMap"))
			actionCrsFeeMapDetails();
		else if(From.trim().equalsIgnoreCase("MemberFee"))
			actionMemberFeeDetails();
		else if(From.trim().equalsIgnoreCase("FPM"))
			actionFPMDetails();
		else if(From.trim().equalsIgnoreCase("FCS"))
			actionFCSDetails();
	}

	void actionFeeMapDetails()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			int TotRow=util.getTotRow();
			List<FiseFeeMapDomain> feeMapDetails=new ArrayList<FiseFeeMapDomain>();
			for(int i=0;i<TotRow;i++)
			{
				String feeCode="feeCode"+i;
				String feeDesc="feeDesc"+i;;
				String prefix="prefix"+i;
				String body="body"+i;
				String suffix="suffix"+i;
				String instFeeDesc="instFeeDesc"+i;
				String instFeeCode="instFeeCode"+i;
				
				feeCode=request.getParameter(feeCode);
				feeDesc=request.getParameter(feeDesc);
				prefix=request.getParameter(prefix);
				body=request.getParameter(body);
				suffix=request.getParameter(suffix);
				instFeeDesc=request.getParameter(instFeeDesc);
				instFeeCode=request.getParameter(instFeeCode);
				
				FiseFeeMapDomain row=new FiseFeeMapDomain();
				
				row.setFeeCode(feeCode);
				row.setFeeDesc(feeDesc);
				row.setPrefix(prefix);
				row.setBody(body);
				row.setSuffix(suffix);
				row.setInstFeeCode(instFeeCode);
				row.setInstFeeDesc(instFeeDesc);
				
				if(row.isValidforInst())
					feeMapDetails.add(row);
			}
			
			int status=FiseIF.saveFeeMapDetails(feeMapDetails,session);
			if(status==1)
				response.sendRedirect("../JSP/cfa-course-fee-mapping-fise.jsp");
			else
				response.sendRedirect("../JSP/fms-institution-fee-mapping-fise.jsp");
			
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	void actionCrsFeeMapDetails()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			int TotRow=util.getTotRow();
			List<FiseCourseFeeMapDomain> CourseFeeMapDetails=new ArrayList<FiseCourseFeeMapDomain>();
			for(int i=0;i<TotRow;i++)
			{
				
				String deptId="deptId"+i;
				String feeSequence="feeSequence"+i;
				String instFeeId="instFeeId"+i;
				String group="group"+i;
				String feeAmount="feeAmount"+i;
				String crsFeeId="crsFeeId"+i;
				String feeType="feeType"+i;
				String feeRecurring="feeRecurring"+i;
				
				
				deptId=request.getParameter(deptId);
				feeSequence=request.getParameter(feeSequence);
				instFeeId=request.getParameter(instFeeId);
				group=request.getParameter(group);
				feeAmount=request.getParameter(feeAmount);
				crsFeeId=request.getParameter(crsFeeId);
				feeType=request.getParameter(feeType);
				feeRecurring=request.getParameter(feeRecurring);
				
				
				FiseCourseFeeMapDomain row=new FiseCourseFeeMapDomain();
				
				row.setDeptId(deptId);
				row.setFeeSequence(converttoInt(feeSequence));
				row.setInstFeeId(instFeeId);
				row.setGroup(group);
				row.setFeeAmount(converttoDouble(feeAmount));
				row.setCrsFeeId(crsFeeId);
				row.setFeeType(feeType);
				row.setFeeRecurring(converttoInt(feeRecurring));
				
				//System.out.println(""+row.toString());
				if(row.isValid())
					CourseFeeMapDetails.add(row);
				
			}
			
			int status=FiseIF.saveCrsFeeMapDetails(CourseFeeMapDetails,session);
			if(status==1)
				response.sendRedirect("../JSP/mfm-member-fee-mapping-fise.jsp");
			else
				response.sendRedirect("../JSP/cfa-course-fee-mapping-fise.jsp");
			
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	
	int converttoInt(String val)
	{
		try
		{
			int ret=1;
			ret = Integer.parseInt(val.trim());
			if(ret==-1)
				ret=1;
			
			return ret;
		}catch(Exception ex){return 1;}
		
	}
	Double converttoDouble(String val)
	{
		try
		{
			return Double.parseDouble(val.trim());
		}catch(Exception ex){return 0.0;}
	}
	void actionMemberFeeDetails()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			int TotRow=util.getTotRow();
			List<FiseMemberFeesDomain> MemberFeesdetails=new ArrayList<FiseMemberFeesDomain>();
			
			for(int i=0;i<TotRow;i++)
			{
				String deptId="deptId"+i;
				String groupFeeId="groupFeeId"+i;
				String groupSeq="groupSeq"+i;
				String memberFeeId="memberFeeId"+i;
				String memberFeeVal="memberFeeVal"+i;
				String memberFeeType="memberFeeType"+i;
				
				deptId=request.getParameter(deptId);
				groupFeeId=request.getParameter(groupFeeId);
				groupSeq=request.getParameter(groupSeq);
				memberFeeId=request.getParameter(memberFeeId);
				memberFeeVal=request.getParameter(memberFeeVal);
				memberFeeType=request.getParameter(memberFeeType);
				
				FiseMemberFeesDomain row=new FiseMemberFeesDomain();
				row.setDeptId(deptId);
				row.setGroupFeeId(groupFeeId);
				row.setGroupSeq(converttoInt(groupSeq));
				row.setMemberFeeId(memberFeeId);
				row.setMemberFeeVal(converttoDouble(memberFeeVal));
				row.setMemberFeeType(memberFeeType);
				
				if(row.isvalid())
					MemberFeesdetails.add(row);
			}
			
			int status=FiseIF.saveMemberFeeDetails(MemberFeesdetails,session);
			if(status==1)
				response.sendRedirect("../JSP/fpm-fee-payment-system-fise.jsp");
			else
				response.sendRedirect("../JSP/mfm-member-fee-mapping-fise.jsp");
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	void actionFPMDetails()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			int TotRow=util.getTotRow();
			List<FiseFPMDomain> FPMDetails=new ArrayList<FiseFPMDomain>();
			
			for(int i=0;i<TotRow;i++)
			{
				String gatewayId="gatewayId"+i;
				String gatewayName="gatewayName"+i;
				String gatewayURL="gatewayURL"+i;
				String parameterList="parameterList"+i;
				
				gatewayId=request.getParameter(gatewayId);
				gatewayName=request.getParameter(gatewayName);
				gatewayURL=request.getParameter(gatewayURL);
				parameterList=request.getParameter(parameterList);
				
				FiseFPMDomain row=new FiseFPMDomain();
				row.setGatewayId(gatewayId);
				row.setGatewayName(gatewayName);
				row.setGatewayURL(gatewayURL);
				row.setParameterList(parameterList);
				
				if(row.isValid())
					FPMDetails.add(row);
				
			}
			
			int status=FiseIF.saveFPMDetails(FPMDetails,session);
			if(status==1)
				response.sendRedirect("../JSP/fcms-fee-collection-management-system-fise.jsp");
			else
				response.sendRedirect("../JSP/fpm-fee-payment-system-fise.jsp");
			
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	void actionFCSDetails()
	{
		try
		{
			SessionUtil util=(SessionUtil) session.getAttribute("SessionUtil");
			
			int TotRow=util.getTotRow();
			List<FiseFCSDomain> FCSDetails=new ArrayList<FiseFCSDomain>();
			
			for(int i=0;i<TotRow;i++)
			{
				String category="category"+i;
				String subCategory="subCategory"+i;
				String usd="usd"+i;
				String gbp="gbp"+i;
				String euro="euro"+i;
				String lcy="lcy"+i;
				String others="others"+i;
				
				category=request.getParameter(category);
				subCategory=request.getParameter(subCategory);
				usd=request.getParameter(usd);
				/*gbp=request.getParameter(gbp);
				euro=request.getParameter(euro);
				lcy=request.getParameter(lcy);
				others=request.getParameter(others);*/
				
				FiseFCSDomain row=new FiseFCSDomain();
				row.setCategory(category);
				row.setSubCategory(subCategory);
				row.setAssign(usd);
				/*row.setGbp(gbp);
				row.setEuro(euro);
				row.setLcy(lcy);
				row.setOthers(others);*/
				
				if(row.isValid())
					FCSDetails.add(row);
					
			}
			
			int status=FiseIF.saveFCSDetails(FCSDetails,session);
			if(status==1)
				response.sendRedirect("../JSP/image-image-loading.jsp");
			else
				response.sendRedirect("../JSP/fcms-fee-collection-management-system-fise.jsp");
			
			
		}catch(Exception ex){ex.printStackTrace();}
	}
}
