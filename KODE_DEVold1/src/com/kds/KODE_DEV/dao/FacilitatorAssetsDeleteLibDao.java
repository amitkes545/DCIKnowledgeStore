package com.kds.KODE_DEV.dao;
	
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorManageKnowStoreDomain;

	public class FacilitatorAssetsDeleteLibDao{

		public String deleteValues(ArrayList<FacilitatorManageKnowStoreDomain> arl) {

		String status=null;
		Connection con=null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		//PreparedStatement pstmt1 = null;

		try {
	        
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date date = new java.sql.Date(utilDate.getTime());
			System.out.println("date :"+date);
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Time currentTime = new java.sql.Time(now.getTime());
			System.out.println("time:"+currentTime);

			con = DBTransaction.connect();
	                if(arl.size()>0)
	                {
	                	Iterator<FacilitatorManageKnowStoreDomain> it=arl.iterator();
	                    while(it.hasNext())
	                    {
	                    	FacilitatorManageKnowStoreDomain mksDomain=it.next();	
	                
					//PreparedStatement pstmt = null;
					//PreparedStatement pstmt1 = null;
	                    	System.out.println("mksDomain.getLibId() "+mksDomain.getLibId());
	                    	System.out.println("mksDomain.getFileName() "+mksDomain.getFileName());
	                    	System.out.println("mksDomain.getUploadedBy() "+mksDomain.getUploadedBy());
	                    	System.out.println("mksDomain.ksid() "+mksDomain.getKsId());
	                    	System.out.println("mksDomain.getorgid() "+mksDomain.getOrgId());
	                    	
					String sql = "delete from knowledgeassets_info where lib_id='"+ mksDomain.getLibId() +"' and file_name='"+mksDomain.getFileName()+"' and uploaded_by='"+mksDomain.getUploadedBy()+"' ";
					//String sql = "delete from knowledgestorelib_info where lib_id='"+ mksDomain.getLibId() +"'";
					//String update ="update knowledgestore_info set updated_know_store_space=updated_know_store_space+"+mksDomain.getLibSize()+" where user_id='"+mksDomain.getUserId()+"'";
				    pstmt = con.prepareStatement(sql);
				   // pstmt1 = con.prepareStatement(update);
					
					System.out.println("the query is:"+sql);
					int i = pstmt.executeUpdate();
					//int u = pstmt1.executeUpdate();
					
					//System.out.println(i+"row is Deleted successfully");
					//System.out.println(u+"row is added successfully");
					System.out.println("Deleted query "+i);
					if (i==1)//dinesh added by dinesh for audit trail  public delete 
					{	
					status = "success";
					String filename = mksDomain.getFileName();
					String ksid = mksDomain.getKsId();
					System.out.println("mksDomain.getKsId() "+ksid);
					System.out.println("action_type Deleted");
					String action_type ="Deleted";
					System.out.println("File name "+filename);
					
										
					String audit_sql = "INSERT INTO public.asset_tracking(ksid, lib_id, file_name, action_type, action_datetime,action_by, organization_id) VALUES (?,?,?,?,?,?,?)";
					pstmt1 = con.prepareStatement(audit_sql);
				
					System.out.println("audit_sql Query  " +audit_sql);
					
					pstmt1.setString(1, ksid);
					System.out.println("ksid  "  +ksid);
					
					pstmt1.setString(2,mksDomain.getLibId());
					System.out.println("course  "  +mksDomain.getLibId());
					
					pstmt1.setString(3,mksDomain.getFileName());
					System.out.println("FileName  "   +mksDomain.getFileName());
					
					pstmt1.setString(4,"Deleted");
					System.out.println("Knowledge Assets File uploaded");
					
					pstmt1.setTimestamp(5,mksDomain.getTimeStamp());
					System.out.println("getTimeStamp  "   +mksDomain.getTimeStamp());
					
					pstmt1.setString(6,mksDomain.getUploadedBy());
					System.out.println("getUploadedBy  "   +mksDomain.getUploadedBy());
					
					
					pstmt1.setString(7, mksDomain.getOrgId());
					System.out.println("getOrgId  "   +mksDomain.getOrgId());
					System.out.println("the query is:" + pstmt1);
					int n1 = pstmt1.executeUpdate();
					System.out.println("audit_sql query is inserted successfully  "+n1);
					/*
					String filename = mksDomain.getFileName();
					System.out.println("File name "+filename);
					String audit_sql = "INSERT INTO public.asset_tracking(ksid, lib_id, file_name, action_type, action_datetime,action_by, organization_id) VALUES ("+mksDomain.getKsId() +", "+mksDomain.getLibId()+"," +filename+","+"'Delete'"+","+ date+""+currentTime+","+mksDomain.getUploadedBy()+","+mksDomain.getOrgId()+")";
					pstmt1 = con.prepareStatement(audit_sql);
					System.out.println("audit_sql Query  " +audit_sql);
					System.out.println("the query is:" + pstmt1);
					
					int n1 = pstmt1.executeUpdate();
					System.out.println("audit_sql query is inserted successfully"+n1);
					}*///dinesh added by dinesh for audit trail  public delete 
	                    }
					else
					{	
						status = "failure";
					
	                 }
	                }
	                }
		}
				
		catch(Exception e){
					e.printStackTrace();
			}
		finally
		   {
			   try{
			   con.close();
			
			  
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
				return status;
			}
	}
