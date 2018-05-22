package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.domain.FacilitatorKAssetsReportDomain;

public class FacilitatorKAssetsReportKnowDao {
	Connection con = null;
	
	PreparedStatement pstmt = null;

	FacilitatorKAssetsReportDomain rDomain = new FacilitatorKAssetsReportDomain();

	public FacilitatorKAssetsReportDomain fetchValue(FacilitatorKAssetsReportDomain rDomain) {

		try {
			con = DBTransaction.connect();
			
			//PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = " select * from knowledgeassets_info where ksid = '"+ rDomain.getKsId() + "'";
			pstmt = con.prepareStatement(sql);

			//System.out.println("The query is:" + sql);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				String kID = rs.getString("ksid");

				System.out.println("User ID is:" + kID);

				rDomain.setKsId(rs.getString("ksid"));
				rDomain.setDepartment(rs.getString("departments"));
				rDomain.setSubject(rs.getString("subject"));
				rDomain.setDescription(rs.getString("description"));
				rDomain.setUploadedBy(rs.getString("uploaded_by"));
				rDomain.setFileName(rs.getString("file_name"));
				//rDomain.setFileType(rs.getString("file_type"));
				rDomain.setFileSize(rs.getInt("file_size"));
				rDomain.setUploadDate(rs.getDate("upload_date"));
				rDomain.setUploadTime(rs.getTimestamp("upload_time"));
				rDomain.setFilePath(rs.getString("path_of_file"));
				rDomain.setStatus(rs.getString("status"));
	
			}
			System.out.println("The return value is:" + rDomain);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		   {
			   try{
			   con.close();
			   pstmt.close();
			   }
			   catch(Exception e){
					e.printStackTrace();
		   }
		   }
		return rDomain;
	}
}