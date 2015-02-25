/*
* Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
* 
* The MIT License (MIT)
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED.
* 
*/
package com.aspose.showcase.qrcodegen.web.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

/**
 * 
 * @author <ul><li>Farooq Sheikh (farooq.sheikh@aspose.com)</li>
 */
public class AppConfigProperties {

	@Bean
	    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
	        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
	        propertyPlaceholderConfigurer.setLocation(new ClassPathResource("config.properties"));
	        // Allow for other PropertyPlaceholderConfigurer instances.
	        propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
	        return propertyPlaceholderConfigurer;
	    }

}
