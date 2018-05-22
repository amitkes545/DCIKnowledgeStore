package com.kds.KODE_DEV.services;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.AdSetUpKnowStoreDao;
import com.kds.KODE_DEV.dao.AdminSelectKsIDEmailIDDao;
import com.kds.KODE_DEV.dao.OwnerSelectUserIDKStoreDao;
import com.kds.KODE_DEV.dao.SenderEmailDetailsDAO;
import com.kds.KODE_DEV.domain.AdminSetUpKnowStoreDomain;
import com.kds.KODE_DEV.domain.SenderEmailDetailsDomain;
import com.kds.KODE_DEV.util.PropertiesUtil;

@SuppressWarnings("serial")
public class AdSetUpKnowStoreServlet extends CommonService {

	static final Logger LOGGER = Logger
			.getLogger(AdSetUpKnowStoreServlet.class);

	String userid = "";
	String emailid = "";
	String password = "";
	String host = "";
	String pop = "";
	int port;
	String ksId = "";

	AdminSelectKsIDEmailIDDao askDao = new AdminSelectKsIDEmailIDDao();

	AdminSetUpKnowStoreDomain aksDomain = new AdminSetUpKnowStoreDomain();
	AdSetUpKnowStoreDao ksDao = new AdSetUpKnowStoreDao();

	@Override
	public void run() throws ServletException, IOException {

		session = request.getSession();

		SenderEmailDetailsDAO senderDao = new SenderEmailDetailsDAO();
		SenderEmailDetailsDomain senderDom = (SenderEmailDetailsDomain) senderDao
				.senderdetails();
		emailid = senderDom.getEmailid();
		password = senderDom.getPassword();
		host = senderDom.getHost();
		pop = senderDom.getPop();
		port = senderDom.getPort();

		// session.removeAttribute("MsgValue");
		OwnerSelectUserIDKStoreDao selectIdDao = new OwnerSelectUserIDKStoreDao();

		// String orgID = request.getParameter("orgid");
		String userID = request.getParameter("userid1");
		String ksSize = request.getParameter("kssize");
		int ksSize1 = Integer.parseInt(ksSize);
		//System.out.println("ksSize1:" + ksSize1);
		// String ksInt = ksSize + "GB";
		String staTus = request.getParameter("status");

		aksDomain.setOrgId(session.getAttribute("orgid").toString());
		aksDomain.setUserId(userID);
		aksDomain.setCreatedBy(session.getAttribute("userid").toString());
		aksDomain.setKsSize(ksSize1);
		aksDomain.setStatus(staTus);

		session.setAttribute("usersID", userID);
		boolean isDuplicate = selectIdDao.adminDuplicate(aksDomain);
		LOGGER.info("duplicate value is" + isDuplicate);

		if (isDuplicate) {
			String msg3 = "User exit ! please try another";
			session.setAttribute("MsgValue5", msg3);
			response.sendRedirect("../JSP/AdminKnowSetup.jsp");
		} else {

			// String reStatus = ksDao.insertAllInformationOfFaculty(aksDomain);

			String user1 = session.getAttribute("userid").toString();
			String allFacultySize = ksDao.selectAllFacultySpace(user1);
			//System.out.println("all Faculty Size:" + allFacultySize);

			if (allFacultySize == null) {

				String ksId = (String) askDao.fetchKnowStoreID(aksDomain);
				String emailId = (String) askDao.fetchEmailId(aksDomain);

				LOGGER.info("KsId is:" + ksId);
				LOGGER.info("Email Id :" + emailId);

				// /

				FTPClient ftpclient = new FTPClient();

				String ftpServerAddress = PropertiesUtil.getProperty("FTP_IP");
				
			//	String userName = "ftpuser1";
	     	//	String password = "ftp@123#1";
	     		
				//String ftpServerAddress = "ftp://220.225.125.221/";
				String userName =PropertiesUtil.getProperty("user1");
				String password =PropertiesUtil.getProperty("password1");		

				try {
					boolean result;
					ftpclient.connect(ftpServerAddress);
					result = ftpclient.login(userName, password);

					// boolean result;

					/*
					 * FTPConnection ftpConnection = new FTPConnection();
					 * FTPClient ftpClient = ftpConnection.connect();
					 */
					if (result == true)

						ftpclient.setFileType(FTP.BINARY_FILE_TYPE);
					String direPath = "/home/ftpkds1/KnowStore/"
							+ session.getAttribute("orgid") + '/'
							+ session.getAttribute("userid") + '/'
							+ request.getParameter("userid1") + "'";

					LOGGER.info(direPath);
					String[] pathElements = direPath.split("/");
					if (pathElements != null && pathElements.length > 0) {
						for (String direPath1 : pathElements) {

							boolean existed = ftpclient
									.changeWorkingDirectory(direPath1);
							LOGGER.info(direPath1 + "exists");
							if (!existed) {
								boolean created = ftpclient
										.makeDirectory(direPath1);
								LOGGER.info(direPath1 + "created");
								if (created) {
									LOGGER.info("Directory created successfully !");

								} else {
									LOGGER.info("Error in creating directory !");

								}
							}
						}
					}
				} catch (FTPConnectionClosedException e1) {
					e1.printStackTrace();

				} finally {
					try {
						ftpclient.logout();

						ftpclient.disconnect();
					} catch (FTPConnectionClosedException e2) {
						LOGGER.info(e2);
					}

					String reStatus = ksDao
							.insertAllInformationOfFaculty(aksDomain);

					if ("success".equals(reStatus)) {

						String to = aksDomain.getEmailId();
						sendMail(host, to);

						String msg = "Knowledge Store created successfully"
								+ "#" + "Know Store ID sent to your Email ID !";

						request.setAttribute("AdminSuccess", msg);
						RequestDispatcher requestDispatcher = request
								.getRequestDispatcher("../JSP/AdminKnowSetup.jsp");
						requestDispatcher.forward(request, response);

					} else {

						String msg = "Failed to create knowledge store ! Please try again !! ";

						request.setAttribute("AdminFailure", msg);
						RequestDispatcher requestDispatcher = request
								.getRequestDispatcher("../JSP/AdminKnowSetup.jsp");
						requestDispatcher.forward(request, response);

					}

				}
			}
			 int allFacultySizeInt = 0;
			 if(allFacultySize != null){
			 allFacultySizeInt = Integer.parseInt(allFacultySize);
			 }
			String allAdminSize = ksDao.selectAdminSpace(user1);
			int adminSizeInt = Integer.parseInt(allAdminSize);
			//System.out.println("AdminSize:" + adminSizeInt);

			int remainingSize = (adminSizeInt - allFacultySizeInt);
			//System.out.println("the operation is"	+ (remainingSize = (adminSizeInt - allFacultySizeInt)));
			//System.out.println("remainingSize:" + remainingSize);

			if (remainingSize >= ksSize1) {

				String ksId = (String) askDao.fetchKnowStoreID(aksDomain);
				String emailId = (String) askDao.fetchEmailId(aksDomain);

				LOGGER.info("KsId is:" + ksId);
				LOGGER.info("Email Id :" + emailId);

				// /

				FTPClient ftpclient = new FTPClient();

				String ftpServerAddress = PropertiesUtil.getProperty("FTP_IP");
				//String ftpServerAddress = "ftp://220.225.125.221/";
		//		String userName = "ftpuser1";
	     //		String password = "ftp@123#1";
				
				String userName =PropertiesUtil.getProperty("user1");
				String password =PropertiesUtil.getProperty("password1");		

				try {
					boolean result;
					ftpclient.connect(ftpServerAddress);
					result = ftpclient.login(userName, password);

					// boolean result;

					/*
					 * FTPConnection ftpConnection = new FTPConnection();
					 * FTPClient ftpClient = ftpConnection.connect();
					 */
					if (result == true)

						ftpclient.setFileType(FTP.BINARY_FILE_TYPE);
					String direPath = "/home/ftpkds1/KnowStore/"
							+ session.getAttribute("orgid") + '/'
							+ session.getAttribute("userid") + '/'
							+ request.getParameter("userid1") + "'";

					LOGGER.info(direPath);
					String[] pathElements = direPath.split("/");
					if (pathElements != null && pathElements.length > 0) {
						for (String direPath1 : pathElements) {

							boolean existed = ftpclient
									.changeWorkingDirectory(direPath1);
							LOGGER.info(direPath1 + "exists");
							if (!existed) {
								boolean created = ftpclient
										.makeDirectory(direPath1);
								LOGGER.info(direPath1 + "created");
								if (created) {
									LOGGER.info("Directory created successfully !");

								} else {
									LOGGER.info("Error in creating directory !");

								}
							}
						}
					}
				} catch (FTPConnectionClosedException e1) {
					e1.printStackTrace();

				} finally {
					try {
						ftpclient.logout();

						ftpclient.disconnect();
					} catch (FTPConnectionClosedException e2) {
						LOGGER.info(e2);
					}

					String reStatus = ksDao
							.insertAllInformationOfFaculty(aksDomain);

					if ("success".equals(reStatus)) {

						String to = aksDomain.getEmailId();
						sendMail(host, to);

						String msg = "Knowledge Store created successfully"
								+ "#" + "Know Store ID sent to your Email ID !";

						request.setAttribute("AdminSuccess", msg);

					} else {

						String msg = "Failed to create knowledge store ! Please try again !! ";

						request.setAttribute("AdminFailure", msg);

					}

					RequestDispatcher requestDispatcher = request
							.getRequestDispatcher("../JSP/AdminKnowSetup.jsp");
					requestDispatcher.forward(request, response);
				}
			} else {

				String msg = "Sorry You don't have enough space !";
				session.setAttribute("AdminSize", msg);
				response.sendRedirect("../JSP/AdminKnowSetup.jsp");

			}
		}
	}

	public void sendMail(String host1, String to) {

		Properties props = new Properties();
		props.put("mail.smtp.auth", pop);
		props.put("mail.smtp.starttls.enable", pop);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(emailid, password);
					}
				});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailid));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("Know Store ID");
			message.setText("Hi your Know Store ID created successfuly!" + "\n"
					+ "Know Store ID is:" + askDao.fetchKnowStoreID(aksDomain));

			Transport.send(message);
			LOGGER.info("send successfully");
			/*
			 * try { response.sendRedirect("../JSP/success.jsp"); } catch
			 * (IOException e) { e.printStackTrace(); }
			 */
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}