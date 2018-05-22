package com.kds.KODE_DEV.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.kds.KODE_DEV.dbconnection.DBTransaction;
import com.kds.KODE_DEV.dbconnection.DBTransactionForDCI;
import com.kds.KODE_DEV.domain.EnrollmentDomain;
import com.kds.KODE_DEV.services.Mailer;

public class DCIRegistrationDao {
	java.sql.Date datefordci;
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	public String createRegistrationStudent(EnrollmentDomain request, String streamId, String enrId, String courseId,
			String CourseName, HttpServletRequest req) throws SQLException {
		Connection conn = null;
		int status = 0;
		String statusDCI = null;

		PreparedStatement ps = null;
		try {
			java.util.Date date = formatter.parse(request.getDateOfBirth());
			System.out.println("date=" + date);
			datefordci = new java.sql.Date(date.getTime());

			String existingUserByCourse = getExistingUserByCourse(request, streamId);

			if (existingUserByCourse != null) {
				// logger.debug("user has already registered for the course " +
				// courseId);
				statusDCI = "Failed";
				
			} 

			conn = DBTransaction.connect();
			String query = "insert into transaction_registration_process (full_name,email,phone,landline,dob,gender,nationality,present_address,country,state,city,pincode,username) values (?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			ps = conn.prepareStatement(query);
			ps.setString(1, request.getApplicantName());
			ps.setString(2, request.getEmail());
			ps.setLong(3, Long.parseLong(request.getMobileNumber()));
			ps.setLong(4, request.getLandline());
			ps.setDate(5, datefordci);
			ps.setString(6, request.getGender());
			ps.setString(7, request.getNationality());
			ps.setString(8, request.getPermanentAddress());
			ps.setString(9, "India");
			ps.setString(10, "");
			ps.setString(11, "");
			ps.setLong(12, request.getPincode());
			ps.setString(13, "");

			status = ps.executeUpdate();
			if (status > 0) {
				String regId = getRegistrationProcessId(request.getEmail(), request.getMobileNumber());
				saveInLogin(Long.parseLong(request.getMobileNumber()), passGenerate(), regId);
				createEnrollmentStudent(request, streamId, enrId);
				saveEnrollmentDCI(request, enrId, "UniTNOSof", courseId, CourseName);
				statusDCI = "Success";
				

			}
		} catch (Exception e) {
			// logger.error("Error while Saving User information " +
			// e.getMessage());
			statusDCI = "Failed";
		} finally {
			// DBTransaction.closeStatementResultSet(ps, null);
			DBTransaction.closeConnection(conn);
			if (ps != null) {
				ps.close();
			}
		}
		return statusDCI;
	}

	private void saveInLogin(long username, String password, String regId) {
		// logger.info(" Start of save() for saving User credential >>");
		int status = 0;
		Connection conn = null;
		PreparedStatement ps1 = null;

		try {
			conn = DBTransaction.connect();
			String query = "insert into login (username,password,registration_process_id) values (?,?,?) ";
			ps1 = conn.prepareStatement(query);
			ps1.setString(1, String.valueOf(username));
			ps1.setString(2, password);
			ps1.setString(3, regId);

			status = ps1.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// logger.error("Error while Saving Credential for User" +
			// e.getMessage());
		} finally {
			// DBTransaction.closeStatementResultSet(ps1, rs);
			DBTransaction.closeConnection(conn);
		}
		// logger.info(" End of save() for saving User credential >>");
		// return status;

	}

	public String getRegistrationProcessId(String email, String mobile) {
		String registrationProcessId = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBTransaction.connect();
			String query = " select registration_process_id from transaction_registration_process where email='" + email
					+ "' and phone=" + mobile + "";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				registrationProcessId = rs.getString("registration_process_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// logger.error("Error while getting User Registration_process_id "
			// + e.getMessage());
		} finally {
			DBTransaction.closeConnection(conn);
		}
		// logger.info(" End of getRegistrationProcessId() >>");
		return registrationProcessId;
	}

	public void createEnrollmentStudent(EnrollmentDomain transactionEnrollmentProcessDomain, String streamId,
			String enrId) {

		// logger.info(" Start of save() for saving Enrollment Information
		// Information >>");
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DBTransaction.connect();

			java.util.Date date = formatter.parse(transactionEnrollmentProcessDomain.getDateOfBirth());
			System.out.println("date=" + date);
			datefordci = new java.sql.Date(date.getTime());

			// String query =
			// "insert into transaction_enrollment_process
			// (course_discription_id,full_name,country,state,city,present_address,permanent_address,dob,gender,extra_curricular_activity,extra_qualification,email,phone)
			// values (?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			String queryforEnr = "INSERT INTO transaction_enrollment_process(enrollment_process_id,course_id,full_name, present_address, dob, gender, email, phone,username, nationality,guardian_name, mother_tongue, caste,  pincode,aadhar_id,district,middle_layer_teaching_source_name) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = conn.prepareStatement(queryforEnr);
			// presist data in database
			ps.setString(1, enrId);
			ps.setString(2, streamId);
			ps.setString(3, transactionEnrollmentProcessDomain.getApplicantName());
			ps.setString(4, transactionEnrollmentProcessDomain.getAddressOfCommunication());
			ps.setDate(5, datefordci);

			ps.setString(6, transactionEnrollmentProcessDomain.getGender());
			ps.setString(7, transactionEnrollmentProcessDomain.getEmail());
			ps.setLong(8, Long.parseLong(transactionEnrollmentProcessDomain.getMobileNumber()));
			ps.setString(9, transactionEnrollmentProcessDomain.getMobileNumber());
			ps.setString(10, transactionEnrollmentProcessDomain.getNationality());
			ps.setString(11, transactionEnrollmentProcessDomain.getNameOfParent());
			ps.setString(12, transactionEnrollmentProcessDomain.getMotherTongue());
			ps.setString(13, transactionEnrollmentProcessDomain.getCaste());
			ps.setLong(14, transactionEnrollmentProcessDomain.getPincode());
			ps.setString(15, transactionEnrollmentProcessDomain.getAdharNumber());
			ps.setString(16, transactionEnrollmentProcessDomain.getDistrict());
			ps.setString(17, "Soft Source Technologies");
			System.out.println("DAO  " + ps);
			int status = ps.executeUpdate();
			System.out.println("DAO  " + status);

			if (status != 0) {
				// System.out.println("enrid " + enrid);
				String queryForStEducationDetails = "insert into student_credentials (enrollment_id,credential_id,admission_ref,instn_name,instn_address,district,pincode,creation_date,last_update,user_id,status) values (?,?,?,?, ?, ?, ?,?,?,?,?)";
				ps = conn.prepareStatement(queryForStEducationDetails);

				ps.setString(1, enrId);
				ps.setString(2, transactionEnrollmentProcessDomain.getClassPresentelyStudying());
				ps.setString(3, transactionEnrollmentProcessDomain.getAddmissionNumber());
				ps.setString(4, transactionEnrollmentProcessDomain.getNameOfSchool());
				ps.setString(5, transactionEnrollmentProcessDomain.getAddressOfSchool());
				ps.setString(6, transactionEnrollmentProcessDomain.getDistrictOfSchool());
				ps.setString(7, transactionEnrollmentProcessDomain.getPincodeOfSchool());
				ps.setTimestamp(8, getTimeStamp());
				ps.setTimestamp(9, getTimeStamp());
				ps.setString(10, transactionEnrollmentProcessDomain.getUserName());
				ps.setString(11, "Active");
				System.out.println(ps);
				result = ps.executeUpdate();

				// logger.info("result of insert " + result);

			}

			System.out.println("Course name inserted in db" + status);

			/*
			 * ps1.setArray(10,conn.createArrayOf("text",
			 * transactionEnrollmentProcessDomain
			 * .getExtraCurricularActivity()));
			 * ps1.setArray(11,conn.createArrayOf("text",
			 * transactionEnrollmentProcessDomain.getExtraQualification()));
			 * ps1.
			 * setString(6,transactionEnrollmentProcessDomain.getPresentAddress
			 * ()); ps1.setString(7,transactionEnrollmentProcessDomain.
			 * getPermanentAddress()); ps1.setLong(5,
			 * transactionEnrollmentProcessDomain.getLandline());
			 * ps1.setLong(13, transactionEnrollmentProcessDomain.getPincode());
			 */

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// DBTransaction.closeStatementResultSet(ps, null);
			DBTransaction.closeConnection(conn);
		}

		// return result;

	}

	public int saveEnrollmentDCI(EnrollmentDomain enrollmentidtodci, String enrId, String orgId, String courseId,
			String CourseName) throws SQLException {
		// logger.info(" Start of EnrollmentToDCIDAO saveEnrollmentDCI() >>");

		Connection conn = null;
		PreparedStatement ps1 = null;
		int status = 0;

		try {

			String Password = passGenerate();
			java.util.Date date = formatter.parse(enrollmentidtodci.getDateOfBirth());
			datefordci = new java.sql.Date(date.getTime());
			conn = DBTransactionForDCI.dbConnection();
			String query = "insert into users_info (username,user_id,password,email,address,contact_no,privilege,organization_id,created_by,country,state,city,postal_code,users_status,gender,dateofbirth,course_id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			ps1 = conn.prepareStatement(query);
			ps1.setString(1, enrollmentidtodci.getApplicantName());
			ps1.setString(2, enrId);
			ps1.setString(3, Password);
			ps1.setString(4, enrollmentidtodci.getEmail());
			ps1.setString(5, enrollmentidtodci.getAddressOfCommunication());
			ps1.setString(6, String.valueOf(enrollmentidtodci.getPhoneNumber()));
			ps1.setString(7, "Student");
			ps1.setString(8, orgId);
			ps1.setString(9, enrollmentidtodci.getApplicantName());
			ps1.setString(10, "India");
			ps1.setString(11, "");
			ps1.setString(12, "");
			ps1.setString(13, String.valueOf(enrollmentidtodci.getPincode()));
			ps1.setString(14, "Inactive");
			ps1.setString(15, enrollmentidtodci.getGender());
			ps1.setDate(16, datefordci);
			ps1.setString(17, courseId);

			status = ps1.executeUpdate();
			if (status != 0) {
				// logger.info(" Data inserted in users_info table >>");
				System.out.println("Data inserted in users_info table");

				String subject = "Login credential for Soft Source Technologies ";
				String msg = "Hello " + enrollmentidtodci.getApplicantName() + "," + "<br />"
						+ "<br /><br />You have successfully enrolled for " + CourseName + " "
						+ "in  Soft Source Technologies. " + "<br />Your DCI User Id is <strong>" + enrId + "</strong> "
						+ "and the password is <strong>" + Password + "</strong>"
						+ "<br /><br />Thanks and Regards<br />Knowledge Store Technical Team<br /><br />"
						+ "For any technical assistance please contact on the details provided\nbelow:" + "<br /><br />"
						+ "<strong>Toll Free No:</strong> 1800123456<br />" + "<strong>SMS:</strong> 1234567891<br />"
						+ "<strong>Email ID:</strong> techicalsupport@kompaceducation.com";

				Mailer.send(enrollmentidtodci.getEmail(), subject, msg);

			} else {
				// logger.info(" problem in inserting data in users_info table
				// >>");
				System.out.println("problem in inserting data in users_info table");
			}
			/*
			 * status=0; String query1=
			 * "insert into student_faculty_mapping (organization_id,student_id,faculty_id)values(?,?,?)"
			 * ; ps1 = conn.prepareStatement(query1); ps1.setString(1,
			 * enrollmentidtodci.getOrganizationId()); ps1.setString(2,
			 * enrollmentidtodci.getUserId()); ps1.setString(3,
			 * enrollmentidtodci.getCreatedBy()); status=ps1.executeUpdate();
			 * logger.info(" Data inserted in student_faculty_mapping table >>"
			 * ); System.out.println("student_faculty_mapping inserted");
			 */
		} catch (Exception e) {
			e.printStackTrace();
			// logger.error("Error Saving Data in to the KODE_DEV Database" +
			// e.getMessage());

		} finally {
			DBTransactionForDCI.closeStatementResultSet(ps1, null);
			DBTransactionForDCI.closeConnection(conn);
		}
		// logger.info(" END of EnrollmentToDCIDAO saveEnrollmentDCI() >>");
		return status;
	}

	public java.sql.Timestamp getTimeStamp() {
		java.sql.Timestamp ret = null;
		try {
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			ret = new java.sql.Timestamp(now.getTime());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ret;
	}

	public String passGenerate() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 9) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		return salt.toString();
	}

	public String enrGenerate(String SchoolName) {
		String ret = "";
		Connection conn = null;
		PreparedStatement ps1 = null;
		ResultSet result = null;
		try {
			String nxtEnr = "";
			conn = DBTransactionForDCI.dbConnection();
			String sql = "select nextval('enr') as enr";
			ps1 = conn.prepareStatement(sql);
			result = ps1.executeQuery();
			while (result.next()) {
				nxtEnr = result.getString("enr");
			}
			if (nxtEnr.trim().length() == 2)
				nxtEnr = "0000" + nxtEnr;
			if (nxtEnr.trim().length() == 3)
				nxtEnr = "000" + nxtEnr;
			if (nxtEnr.trim().length() == 4)
				nxtEnr = "00" + nxtEnr;
			if (nxtEnr.trim().length() == 5)
				nxtEnr = "0" + nxtEnr;

			ret = SchoolName.substring(0, 3) + nxtEnr;
			System.out.println("New ENR == " + ret);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (ps1 != null)
					ps1.close();
				if (result != null)
					result.close();
			} catch (Exception ex) {
			}

		}
		return ret;
	}

	public String getExistingUserByCourse(EnrollmentDomain transactionEnrollmentProcessDomain, String streamID) {
		Connection conn = null;
		// logger.info(" Start of getRegistrationProcessId() >>");
		String enrollment_process_id = null;
		String courseId = transactionEnrollmentProcessDomain.getCourseId();
		String userName = transactionEnrollmentProcessDomain.getUserName();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBTransaction.connect();

			String query = " select enrollment_process_id from transaction_enrollment_process  where course_id='"
					+ streamID + "' and username='" + userName + "'";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				enrollment_process_id = rs.getString("enrollment_process_id");
			}
		} catch (Exception e) {
			// logger.error("Error while getting User Registration_process_id "
			// + e.getMessage());
		} finally {
			DBTransaction.closeConnection(conn);
		}
		// logger.info(" End of getRegistrationProcessId() >>");
		return enrollment_process_id;
	}

}
