package com.aspose.showcase.qrcodegen.web.api.util;

import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class StringEncryptor {

	 
     
	public  static String encrypt(String Data, String password) throws Exception {
		
		Security.addProvider(new BouncyCastleProvider());
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
		
		final Random r = new SecureRandom();
		byte[] salt = new byte[8];
		r.nextBytes(salt);
		
		SecretKeyFactory fact = SecretKeyFactory.getInstance("PBEWITHMD5AND128BITAES-CBC-OPENSSL", "BC");
		c.init(Cipher.ENCRYPT_MODE, fact.generateSecret(new PBEKeySpec(password.toCharArray(), salt, 100)));

		 	byte[] encVal = c.doFinal(Data.getBytes());
		 	
		 	ByteArrayOutputStream bos = new ByteArrayOutputStream();
		 	//writing encrypted data along with the salt in the format readable by open ssl api
		 	bos.write("Salted__".getBytes());
		 	bos.write(salt);
		 	bos.write(encVal);
		    String encryptedValue = new BASE64Encoder().encode(bos.toByteArray());
		    bos.close();
		    
		    return encryptedValue;
		
	    
	}

	public static  String decrypt(String encryptedData, String password) throws Exception {
		
		Security.addProvider(new BouncyCastleProvider());

		byte[] encrypted = new BASE64Decoder().decodeBuffer(encryptedData);

		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");

		// Openssl puts SALTED__ then the 8 byte salt at the start of the file.  We simply copy it out.
		byte[] salt = new byte[8];
		System.arraycopy(encrypted, 8, salt, 0, 8);
		
		SecretKeyFactory fact = SecretKeyFactory.getInstance("PBEWITHMD5AND128BITAES-CBC-OPENSSL", "BC");
		c.init(Cipher.DECRYPT_MODE, fact.generateSecret(new PBEKeySpec(password.toCharArray(), salt, 100)));
		
		// Decrypt the rest of the byte array (after stripping off the salt)
		byte[] decValue = c.doFinal(encrypted, 16, encrypted.length-16);
		
		String decryptedValue = new String(decValue);
	    return decryptedValue;
	
	}

	    
    public static void main(String[] args) throws Exception {
        	
    		String text = "512";
    		
    		String encryptedText = StringEncryptor.encrypt(text, "farooq");
    		
    		System.out.println("basae64 encryptedText:  " + encryptedText);
    		
    		System.out.println("OpenSSL generated hex decryptText:  " + StringEncryptor.decrypt("U2FsdGVkX19zSChkdHrQ+yWgzjzT2GKOh3vJEabBuoE=", "farooq"));
    		
    		System.out.println("OpenSSL generated hex decryptText:  " + StringEncryptor.decrypt(encryptedText,"farooq"));
    		
    		
    		
    	}
}
