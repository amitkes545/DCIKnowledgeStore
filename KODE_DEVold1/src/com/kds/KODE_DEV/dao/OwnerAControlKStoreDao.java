package com.kds.KODE_DEV.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Calendar;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorKAssetsReportDomain;
import com.kds.KODE_DEV.domain.OwnerAControlKStoreDomain;

public class OwnerAControlKStoreDao {
	// public String updateValues(ArrayList<OwnerAControlKStoreDomain> arl) {
		public String updateValues(ArrayList<FacilitatorKAssetsReportDomain> arl) {

		// DBTransaction dbT = new DBTransaction();
			PreparedStatement pstmt = null;
		String status = null;

		try {
			Connection con = DBTransaction.connect();

			String sql = "update knowledgeassets_info set status=? where file_name=? and lib_id=? and uploaded_by=?";
			
			PreparedStatement ps = con.prepareStatement(sql);

			java.util.Date utilDate = new java.util.Date();
			java.sql.Date date = new java.sql.Date(utilDate.getTime());
			System.out.println("date :"+date);
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Time currentTime = new java.sql.Time(now.getTime());
			System.out.println("time:"+currentTime);
		
			
			for(FacilitatorKAssetsReportDomain s:arl){
				System.out.println("updateValues the query is s.getStatus():" + s.getStatus());	
				System.out.println("updateValues the query is s.getStatus():" + s.getFileName());	
				System.out.println("updateValues the query is s.getStatus():" + s.getLibId());	
				System.out.println("updateValues the query is s.getStatus():" + s.getUploadedBy());	
			ps.setString(1,s.getStatus().trim());	
			ps.setString(2, s.getFileName().trim());
			ps.setString(3, s.getLibId().trim());
			ps.setString(4, s.getUploadedBy().trim());
			System.out.println("updateValues the query is:" + sql);
			ps.addBatch();

			int[] count=ps.executeBatch();
			
			//System.out.println("the query is:" + ps);
			//System.out.println(Arrays.toString(count)+ "row is updated successfully");

			if (count!= null)
				{
				status = "success";
				
			//dinesh added by dinesh for audit trail  public update 
				
				String filename = s.getFileName();
				String action_type ="Published";
				System.out.println("File name "+filename);
				
				String audit_sql = "INSERT INTO public.asset_tracking(ksid, lib_id, file_name, action_type, action_datetime,action_by, organization_id) VALUES (?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(audit_sql);
			
				System.out.println("audit_sql Query  " +audit_sql);
				
				pstmt.setString(1, s.getKsId());
				System.out.println("ksid  "  +s.getKsId());
				
				pstmt.setString(2,s.getLibId());
				System.out.println("course  "  +s.getLibId());
				
				pstmt.setString(3,s.getFileName());
				System.out.println("FileName  "   +s.getFileName());
				
				pstmt.setString(4,"Published");
				System.out.println("Knowledge Assets File uploaded");
				
				pstmt.setTimestamp(5,s.getTimeStamp());
				System.out.println("getTimeStamp  "   +s.getTimeStamp());
				
				pstmt.setString(6,s.getUploadedBy());
				System.out.println("getUploadedBy  "   +s.getUploadedBy());
				
				pstmt.setString(7, s.getOrgId());
				System.out.println("getOrgId  "   +s.getOrgId());
				
				System.out.println("the query is:" + pstmt);
				int n1 = pstmt.executeUpdate();
				System.out.println("audit_sql query is inserted successfully"+n1);
			/*String filename = s.getFileName();
			System.out.println("File name "+filename);
			String audit_sql = "INSERT INTO public.asset_tracking(ksid, lib_id, file_name, action_type, action_datetime,action_by, organization_id) VALUES ("+s.getKsId() +", "+s.getLibId()+"," +filename+","+"'Publish'"+","+ date+""+currentTime+","+s.getUploadedBy()+","+s.getOrgId()+")";
			pstmt = con.prepareStatement(audit_sql);
			System.out.println("audit_sql Query  " +audit_sql);
			System.out.println("the query is:" + pstmt);
			
			int n1 = pstmt.executeUpdate();
			System.out.println("audit_sql query is inserted successfully"+n1);*/
			}//dinesh added by dinesh for audit trail  public update 
			else
				status = "failure";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	

	public String updateValuesInActive(OwnerAControlKStoreDomain mksDomain) {

		// DBTransaction dbT = new DBTransaction();

		String status = "";

		try {
			Connection con = DBTransaction.connect();

			String sql = "update knowledgeassets_info set status='Inactive' where ksid='"
					+ mksDomain.getKsId() + "'";
			PreparedStatement ps = con.prepareStatement(sql);

			//System.out.println("the quary is:" + sql);
			int i = ps.executeUpdate();
			int allUpdatedRow = i;
			//System.out.println(i + "row is updated successfully");

			if (i == allUpdatedRow)
				status = "success";
			else
				status = "failure";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public String updateKnowStoreStatusInActive(
			OwnerAControlKStoreDomain mksDomain) {

		// DBTransaction dbT = new DBTransaction();

		String status = "";

		try {
			Connection con = DBTransaction.connect();

			String sql = "update knowledgestore_info set status='Inactive' where ksid='"
					+ mksDomain.getKsId() + "'";
			PreparedStatement ps = con.prepareStatement(sql);

			//System.out.println("the quary is:" + sql);
			int i = ps.executeUpdate();
			//System.out.println(i + "row is updated successfully");

			if (i == 1)
				status = "success";
			else
				status = "failure";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public String updateKnowStoreStatusInActiveAssess(
			OwnerAControlKStoreDomain mksDomain) {

		// DBTransaction dbT = new DBTransaction();

		String status = "";

		try {
			Connection con = DBTransaction.connect();

			String sql = "update knowledgestore_info set status='Inactive' where ksid='"
					+ mksDomain.getKsId() + "'";
			PreparedStatement ps = con.prepareStatement(sql);

			//System.out.println("the quary is:" + sql);
			int i = ps.executeUpdate();
			//System.out.println(i + "row is updated successfully");

			if (i == 1)
				status = "success";
			else
				status = "failure";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
