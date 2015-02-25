/*
* Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
* 
* The MIT License (MIT)
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED.
* 
*/
package com.aspose.showcase.qrcodegen.web.api.controller;


import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * Provides common services required by all controllers.
 * 
 * @author <ul><li>Farooq Sheikh (farooq.sheikh@aspose.com)</li>
 */
public abstract class BaseController {

	protected static final Logger logger = Logger.getLogger(
			BaseController.class.getName());
	@Resource(name = "messageSource")
	protected MessageSource messageSource;

	
	protected String getLocalizedMessage(String messageKey, HttpServletRequest request) {
		return messageSource.getMessage(
				messageKey, ArrayUtils.EMPTY_STRING_ARRAY,
				messageKey, RequestContextUtils.getLocale(request));
	}

	
}
