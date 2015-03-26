/**
 * 
 * Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
 * 
 * THE MIT LICENSE (MIT)
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT ANY WARRENTY OF ANY KIND, EXPRESS OR IMPLIED.
 * 
 */

package com.aspose.showcase.qrcodegen.web.api.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * Provides common services required by all controllers.
 * 
 * @author <ul>
 * <li>Farooq Sheikh (farooq.sheikh@aspose.com)</li>
 */
public abstract class BaseController {

    private static final Log LOGGER = LogFactory.getLog(BaseController.class);

    @Resource(name = "messageSource")
    protected MessageSource messageSource;

    protected String getLocalizedMessage(String messageKey,
            HttpServletRequest request) {

        LOGGER.debug("BaseController::getLocalizedMessage");

        return messageSource.getMessage(messageKey,
                ArrayUtils.EMPTY_STRING_ARRAY, messageKey,
                RequestContextUtils.getLocale(request));
    }

}
