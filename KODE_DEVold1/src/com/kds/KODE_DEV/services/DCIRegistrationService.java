package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileUploadException;

import com.kds.KODE_DEV.dao.DCIRegistrationDao;
import com.kds.KODE_DEV.domain.EnrollmentDomain;


public class DCIRegistrationService extends CommonService{

	@Override
	public void run() throws ServletException, IOException, FileUploadException, Exception {
		EnrollmentDomain transactionEnrollmentProcessDomain = new EnrollmentDomain();		
		DCIRegistrationDao storage = new DCIRegistrationDao();

		String name = null, gender = null,  country = null, state = null, city = null, email = null;
		String  userNameKs=null;
		String existingUserByCourse=null,  universityName=null,universityId=null,dateOfBirth=null;
		String courseName=null;
		String userEnrolledName=null,userCity=null;
		
		
		
		String pincodeOfSchool = null, applicantName = null, phoneNumber = null, userNamename = null, mobileNumber = null,
				pincode = null, adharNumber = null,  classPresentelyStudying = null, currentUserEmail = null,
				userId = null, districtOfSchool = null, addmissionNumber = null, addressOfSchool = null,
				nameOfSchool = null, addressOfCommunication = null, caste = null, district = null, motherTongue = null,
				 nameOfschool = null, certificate = null,
				nameOfParent = null;
		int status = 0;
		Date  dob = null;
		String  adharNo = null, nationality = null;
		String  mobile = null, parentname = null,  pincodeofSchool = null,
				classStudy = null, admissionNo = null, motherToung = null, username = null, address = null, school = null,
				 OTPTextByUser = null,program6=null,program1=null,program2=null,program3=null,program4=null,program5=null,
						 dept1=null,dept2=null,dept3=null,dept4=null,dept5=null,dept6=null,
								 CIDTS1=null,CIDTS2=null,CIDTS3=null,CIDTS4=null,CIDTS5=null,CIDTS6=null;
		String phone="";
	//	userNameKs=(String) session.getAttribute("userName");

		try {
			//userEnrolledName = request.getParameter("name");
			 
			email = request.getParameter("email");
			dateOfBirth = request.getParameter("dob");
			gender = request.getParameter("gender");
			nationality = request.getParameter("nationality");
			country = request.getParameter("country");
			state = request.getParameter("state");
			
			userCity = request.getParameter("city");
			
			certificate = request.getParameter("program1");
			username = request.getParameter("name");
			System.out.println("User name"+username);
			email = request.getParameter("email");
			mobile = request.getParameter("mobile");
			nationality = request.getParameter("nationality");
			caste = request.getParameter("caste");
			district = request.getParameter("district1");
			pincode = request.getParameter("pincode1");
			parentname = request.getParameter("parent_name");
			adharNo = request.getParameter("Aadhaar_Number");
			 phone=request.getParameter("Phone_No");
			
	        //convert data string to 
			long  phoneno = Long.parseLong(phone);
		
			gender = request.getParameter("gender");
			motherToung = request.getParameter("Mother_Tongue");
			address = request.getParameter("address");
			// get user form data from student education details
			nameOfschool = request.getParameter("school");
			districtOfSchool = request.getParameter("district");
			pincodeofSchool = request.getParameter("pincode");
			classStudy = request.getParameter("class_study");
			addressOfSchool = request.getParameter("address_school");
			admissionNo = request.getParameter("admission_no");

			
			
			
			transactionEnrollmentProcessDomain.setCourseName(courseName);
			transactionEnrollmentProcessDomain.setCertificate(certificate);
			transactionEnrollmentProcessDomain.setApplicantName(username);
			transactionEnrollmentProcessDomain.setEmail(email);
			transactionEnrollmentProcessDomain.setMobileNumber(mobile);
			transactionEnrollmentProcessDomain.setDateOfBirth(dateOfBirth);
			transactionEnrollmentProcessDomain.setNationality(nationality);
			transactionEnrollmentProcessDomain.setCaste(caste);
			transactionEnrollmentProcessDomain.setDistrict(district);
			transactionEnrollmentProcessDomain.setPincode(Long.parseLong(pincode));
			transactionEnrollmentProcessDomain.setNameOfParent(parentname);
			if(adharNo != null) {
				transactionEnrollmentProcessDomain.setAdharNumber(adharNo);
			}
			transactionEnrollmentProcessDomain.setPhoneNumber(phoneno);
			transactionEnrollmentProcessDomain.setGender(gender);
			transactionEnrollmentProcessDomain.setMotherTongue(motherToung);
			transactionEnrollmentProcessDomain.setAddressOfCommunication(address);
			transactionEnrollmentProcessDomain.setUserName(userNameKs);
			transactionEnrollmentProcessDomain.setUniversityName(universityName);
			
			// Student Education details

			transactionEnrollmentProcessDomain.setNameOfSchool(nameOfschool);
			transactionEnrollmentProcessDomain.setDistrictOfSchool(districtOfSchool);
			transactionEnrollmentProcessDomain.setPincodeOfSchool(pincodeofSchool);
			transactionEnrollmentProcessDomain.setClassPresentelyStudying(classStudy);
			transactionEnrollmentProcessDomain.setAddressOfSchool(addressOfSchool);
			transactionEnrollmentProcessDomain.setAddmissionNumber(admissionNo);
			
			program1=request.getParameter("program1");
			program2=request.getParameter("program2");
			program3=request.getParameter("program3");
			program4=request.getParameter("program4");
			program5=request.getParameter("program5");
			program6=request.getParameter("program6");
			System.out.println("check box status == "+program1+program2+program3+program4+program5+program6);
			
			String enrId=storage.enrGenerate(nameOfschool);
			String streamId="";
			String courseId="";
			String CourseName="";
			if(program1!=null && program1.trim().equalsIgnoreCase("on"))
			{
				streamId=request.getParameter("dept1");
				courseId=request.getParameter("CIDTS1");
				CourseName="Certificate in Basic Computer";
			}
			else if(program2!=null && program2.trim().equalsIgnoreCase("on"))
			{
				streamId=request.getParameter("dept2");
				courseId=request.getParameter("CIDTS2");
				CourseName="Certificate in Office Automation";
			}
			else if(program3!=null && program3.trim().equalsIgnoreCase("on"))
			{
				streamId=request.getParameter("dept3");
				courseId=request.getParameter("CIDTS3");
				CourseName="Certificate in Programing Techniques";
			}
			else if(program4!=null && program4.trim().equalsIgnoreCase("on"))
			{
				streamId=request.getParameter("dept4");
				courseId=request.getParameter("CIDTS4");
				CourseName="Certificate in Computer Application";
			}
			else if(program5!=null && program5.trim().equalsIgnoreCase("on"))
			{
				streamId=request.getParameter("dept5");
				courseId=request.getParameter("CIDTS5");
				CourseName="Certificate in Multimedia System";
			}
			else if(program6!=null && program6.trim().equalsIgnoreCase("on"))
			{
				streamId=request.getParameter("dept6");
				courseId=request.getParameter("CIDTS6");
				CourseName="Certificate in Communication";
			}
			
			System.out.println("streamId : "+streamId +courseId);
			transactionEnrollmentProcessDomain.setCourseId(courseId);
			String statusDCI=storage.createRegistrationStudent(transactionEnrollmentProcessDomain,streamId,enrId,courseId,CourseName,request);
			//storage.createEnrollmentStudent(transactionEnrollmentProcessDomain, streamId, streamId);
			
			if(statusDCI.equals("Success"))
			{
				response.sendRedirect("/KODE_DEV/JSP/LoginWebView.jsp?status="+statusDCI);
				
			}
		 else
			response.sendRedirect("/KODE_DEV/JSP/registration_form.jsp?status="+statusDCI);


		} catch (Exception e) {		
			e.printStackTrace();
			//session.setAttribute("failedmsg", "You have already Registered for this course");
			response.sendRedirect("/KODE_DEV/JSP/registration_form.jsp?status=failure");
		}
	}

}
