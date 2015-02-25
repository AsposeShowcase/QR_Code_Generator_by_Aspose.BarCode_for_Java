/*
* Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
* 
* The MIT License (MIT)
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED.
* 
*/
package com.aspose.showcase.qrcodegen.web.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.aspose.barcode.BarCodeException;

/**
 * 
 * @author <ul><li>Farooq Sheikh (farooq.sheikh@aspose.com)</li>
 */

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { BarCodeException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        
    	String bodyOfResponse = "Internal Server Error";
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        
         String detailMessage = ex.getLocalizedMessage();
         
        if(detailMessage == null){
        	bodyOfResponse = "Internal Server Error";
        	httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        	
        }else if(detailMessage.contains("evaluation version")){
        	
        	bodyOfResponse = "Please upgrade to paid license to avail this feature. \n Internal Error - " + ex.getMessage();
        	httpStatus = HttpStatus.PAYMENT_REQUIRED;
        	
        }else{
        	bodyOfResponse = ex.getMessage();
        	httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        	
        }
        
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        
        return handleExceptionInternal(ex, bodyOfResponse, 
        		headers, httpStatus, request);
    }
    
    protected <T> ResponseEntity<T> response(T body, HttpStatus status) {
    	return new ResponseEntity<T>(body, new HttpHeaders(), status);
    	} 
}