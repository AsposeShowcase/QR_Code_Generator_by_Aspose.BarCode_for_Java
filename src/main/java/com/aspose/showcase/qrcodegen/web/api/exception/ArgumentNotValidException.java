/*
 * Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
 * 
 * The MIT License (MIT)
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED.
 * 
 */
package com.aspose.showcase.qrcodegen.web.api.exception;

/**
 * 
 * @author <ul><li>Farooq Sheikh (farooq.sheikh@aspose.com)</li>
 */
public class ArgumentNotValidException extends RuntimeException {



    private static final long serialVersionUID = 1L;

    /**
     * Creates an instance of the exception.
     * @param message 
     */
    public ArgumentNotValidException(String message) {
        super(message);
    }

    /**
     * Creates an instance of exception.
     * @param message
     * @param throwable 
     */
    public ArgumentNotValidException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
