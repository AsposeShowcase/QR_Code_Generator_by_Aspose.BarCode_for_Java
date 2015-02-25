/*
* Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
* 
* The MIT License (MIT)
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED.
* 
*/
package com.aspose.showcase.qrcodegen.web.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.License;

/**
 * 
 * @author <ul><li>Farooq Sheikh (farooq.sheikh@aspose.com)</li>
 */

@Configuration
@Import(AppConfigProperties.class)
public class AppConfig {
	
	private @Value("${license.file}") String licenseFile;
	 
	 @Bean
     public BarCodeBuilder barCodeBuilder() {
		 License license = new License();
		 try {
			 
			 File alicenseFile = new File(licenseFile); 
			 
			 if(alicenseFile.exists())
			 	license.setLicense(alicenseFile);
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return new BarCodeBuilder();
     }
	
}