/*
* Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
* 
* The MIT License (MIT)
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED.
* 
*/
package com.aspose.showcase.qrcodegen.web.api.dto;

import org.springframework.http.MediaType;
import com.aspose.barcode.ImageFormat;

/**
 * 
 * @author <ul><li>Farooq Sheikh (farooq.sheikh@aspose.com)</li>
 */

public class ImageFormatDTO {
	
	ImageFormat asposeImageFormat;
	MediaType mediaType;
	
		
	public ImageFormatDTO(ImageFormat asposeImageFormat, MediaType mediaType) {
		super();
		this.asposeImageFormat = asposeImageFormat;
		this.mediaType = mediaType;
	}
	
	public ImageFormat getAsposeImageFormat() {
		return asposeImageFormat;
	}
	public MediaType getMediaType() {
		return mediaType;
	}
	
	
	

	

}
