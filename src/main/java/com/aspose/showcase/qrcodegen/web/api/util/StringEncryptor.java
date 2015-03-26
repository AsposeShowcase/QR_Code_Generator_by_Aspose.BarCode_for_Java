/**
 * 
 * Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
 * 
 * THE MIT LICENSE (MIT)
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT ANY WARRENTY OF ANY KIND, EXPRESS OR IMPLIED.
 *
 */
package com.aspose.showcase.qrcodegen.web.api.util;

import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

public class StringEncryptor {

    private static final Log LOGGER = LogFactory.getLog(StringEncryptor.class);
    
    private static final int SALT_SIZE = 8;
    private static final int PBE_KEY_SALE_SIZE = 100;
    private static final int SALT_STRIP_LENGTH = 16;
    private StringEncryptor() {
        super();
    }

    public static String encrypt(String data, String password) throws Exception {

        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");

        final Random r = new SecureRandom();
        byte[] salt = new byte[SALT_SIZE];
        r.nextBytes(salt);

        SecretKeyFactory fact = SecretKeyFactory.getInstance(
                "PBEWITHMD5AND128BITAES-CBC-OPENSSL", "BC");
        
        cipher.init(Cipher.ENCRYPT_MODE, fact.generateSecret(new PBEKeySpec(
                password.toCharArray(), salt, PBE_KEY_SALE_SIZE)));

        byte[] encVal = cipher.doFinal(data.getBytes());

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // writing encrypted data along with the salt in the format readable by
        // open ssl api
        bos.write("Salted__".getBytes());
        bos.write(salt);
        bos.write(encVal);
        String encryptedValue = new String(Base64.encode(bos.toByteArray()));
        bos.close();

        return encryptedValue;

    }

    public static String decrypt(String encryptedData, String password)
            throws Exception {

        Security.addProvider(new BouncyCastleProvider());

        byte[] encrypted = Base64.decode(encryptedData);

        Cipher cipher0 = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");

        // Openssl puts SALTED__ then the 8 byte salt at the start of the file.
        // We simply copy it out.
        byte[] salt = new byte[SALT_SIZE];
        System.arraycopy(encrypted, SALT_SIZE, salt, 0, SALT_SIZE);

        SecretKeyFactory fact = SecretKeyFactory.getInstance(
                "PBEWITHMD5AND128BITAES-CBC-OPENSSL", "BC");
        cipher0.init(Cipher.DECRYPT_MODE, fact.generateSecret(new PBEKeySpec(
                password.toCharArray(), salt, PBE_KEY_SALE_SIZE)));

        // Decrypt the rest of the byte array (after stripping off the salt)
        byte[] decValue = cipher0.doFinal(encrypted, SALT_STRIP_LENGTH, encrypted.length - SALT_STRIP_LENGTH);

        return new String(decValue);
    }

    public static void main(String[] args) throws Exception {

        String text = "512";
        String passKey = "farooq";
        String encryptedText = StringEncryptor.encrypt(text, passKey);
        String sslEncryptedText = "U2FsdGVkX19zSChkdHrQ+yWgzjzT2GKOh3vJEabBuoE=";

        LOGGER.info("basae64 encryptedText:  " + encryptedText);

        LOGGER.info("OpenSSL generated hex decryptText:  "
                + StringEncryptor.decrypt(sslEncryptedText, passKey));

        LOGGER.info("OpenSSL generated hex decryptText:  "
                + StringEncryptor.decrypt(encryptedText, passKey));

    }
}
