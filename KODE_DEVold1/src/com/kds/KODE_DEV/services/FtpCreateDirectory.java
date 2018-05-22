package com.kds.KODE_DEV.services;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;

import com.kds.KODE_DEV.util.PropertiesUtil;

            /**
             *
             * @author USER
             */
            public class FtpCreateDirectory {

                public static void main(String[] args) throws IOException {
                    FTPClient ftpClient = new FTPClient();

                    boolean result;
                    try {
                        FileInputStream fis = null;

                        // Connect to the localhost
                        //ftpClient.connect("61.12.87.139");
                        //String ftpServerAddress = "ftp://220.225.125.221/";
                       // String ftpServerAddress = "localhost";
                        String ftpServerAddress = PropertiesUtil.getProperty("FTP_IP");
                        ftpClient.connect(ftpServerAddress);

                        String userName =PropertiesUtil.getProperty("user1");
            			String password =PropertiesUtil.getProperty("password1");	
                        
                        
                  //      String userName = "ftpuser1";
            	//		String password = "ftp@123#1";
            			
                        
                        
                        // login to ftp server
                        result = ftpClient.login(userName,password );

                        if (result == true) {
                            //System.out.println("Logged in Successfully !");

                            String str = "KnowStore/SRM/";

                            String pathElements1[] = str.split("/");
                            if (pathElements1 != null && pathElements1.length > 0) {
                                for (String singleDir : pathElements1) {
                                    boolean existed = ftpClient.changeWorkingDirectory(singleDir);
                                    //System.out.println("CurrentDir: " + singleDir);

                                    if (!existed) {
                                        boolean created = ftpClient.makeDirectory(singleDir);
                                        ftpClient.changeWorkingDirectory(singleDir);
                                        //System.out.println("CurrentDir: " + singleDir);
                                        if (created) {

                                            //System.out.println("sandeep::" + singleDir);

                                            //System.out.println("Directory created successfully !");

                                        } else {
                                            //System.out.println("Error in creating directory !");
                                        }

                                    }
                                }

                                File file = new File("/home/lavanya/Documents/Downloads/Downloadfile.java");

                                String testName = file.getName();
                                fis = new FileInputStream(file);

                                // Upload file to the ftp server
                                result = ftpClient.storeFile(testName, fis);
                            }

                        } else {
                            //System.out.println("Login Fail !");

                        }
                    } catch (FTPConnectionClosedException exp) {
                        exp.printStackTrace();
                    } finally {
                        try {
                            ftpClient.disconnect();
                        } catch (FTPConnectionClosedException exe) {
                            //System.out.println(exe);
                        }
                    }
                }

            }