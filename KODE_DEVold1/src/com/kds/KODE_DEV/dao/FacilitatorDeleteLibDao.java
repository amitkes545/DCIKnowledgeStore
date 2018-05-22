package com.kds.KODE_DEV.dao;
	
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorManageKnowStoreDomain;

	public class FacilitatorDeleteLibDao {
		Connection con=null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		
		public String deleteValues(ArrayList<FacilitatorManageKnowStoreDomain> arl) {

		String status=null;

		try {
	                con = DBTransaction.connect();
	                if(arl.size()>0){
	                	Iterator<FacilitatorManageKnowStoreDomain> it=arl.iterator();
	                    while(it.hasNext()){
	                    	FacilitatorManageKnowStoreDomain mksDomain=it.next();	
	                
					//PreparedStatement pstmt = null;
					//PreparedStatement pstmt1 = null;
					
					String sql = "delete from knowledgestorelib_info where lib_id='"+ mksDomain.getLibId() +"'";
				//	String update ="update knowledgestore_info set updated_know_store_space=updated_know_store_space+"+mksDomain.getLibSize()+" where user_id='"+mksDomain.getUserId()+"'";
				    pstmt = con.prepareStatement(sql);
				  //  pstmt1 = con.prepareStatement(update);
					
					//System.out.println("the query is:"+sql);
					int i = pstmt.executeUpdate();
				//	int u = pstmt1.executeUpdate();
					
					System.out.println(i+"row is Deleted successfully");
					//System.out.println(u+"row is added successfully");
					
					if (i==1)
						status = "success";
					else
						status = "failure";
	                    }
	                }
				}catch(Exception e){
					e.printStackTrace();
				}
		finally
		   {
			   try{
			   con.close();
			   pstmt.close();
			   //pstmt1.close();
			   
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
				return status;
			}
	}
