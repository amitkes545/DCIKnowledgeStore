package com.kes.kote.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {
	
	
    private static byte[] key = {
        0x2d, 0x2a, 0x2d, 0x42, 0x55, 0x49, 0x4c, 0x44, 0x41, 0x43, 0x4f, 0x44, 0x45, 0x2d, 0x2a, 0x2d
    };
 
    public static String encrypt(String plainText) {
    	
    	if(plainText.trim().length()>0)
    		return plainText; // for testing purpose as on 071217 by ganesh
    	
        try {
            Cipher cipher = Cipher.getInstance("AES");
        
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
       
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
        
            cipher.getOutputSize(10);  
            byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
            //Util.println("cipherText::::    "+cipherText);
            
            String encryptedString = new String(Base64.getEncoder().encode(cipherText),"UTF-8");
      
            return encryptedString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String decrypt(String encryptedText) {
    	
    	if(encryptedText.trim().length()>0)
    		return encryptedText; // for testing purpose as on 071217 by ganesh
    	
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] cipherText = Base64.getDecoder().decode(encryptedText.getBytes("UTF8"));
            String decryptedString = new String(cipher.doFinal(cipherText),"UTF-8");
            return decryptedString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    

}
