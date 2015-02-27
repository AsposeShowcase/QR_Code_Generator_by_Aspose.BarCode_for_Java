/*
* Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
* 
* The MIT License (MIT)
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED.
* 
*/
package com.aspose.showcase.qrcodegen.web.api.controller;


import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.CodeLocation;
import com.aspose.barcode.GraphicsUnit;
import com.aspose.barcode.ImageFormat;
import com.aspose.barcode.ImageQualityMode;
import com.aspose.barcode.QRErrorLevel;
import com.aspose.barcode.Symbology;
import com.aspose.showcase.qrcodegen.web.api.dto.ImageFormatDTO;
import com.aspose.showcase.qrcodegen.web.api.util.StringEncryptor;

import static com.aspose.showcase.qrcodegen.web.api.cons.MasterAPIConstantsUtil.*;

import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * QR Code Generation Controller  
 * 
 * 
 * @author <ul><li>Farooq Sheikh (farooq.sheikh@aspose.com)</li>
 */
@SuppressWarnings("unused")
@RestController
@RequestMapping(QRCodeManagementController.API_URI)
@Api(value="QR Code Generator", description="Operations to generate QR Codes using Aspose.BarCode for Java" , position=1)
public class QRCodeManagementController extends BaseController{
    
    public static final String API_URI = URI_PREFIX +"/qrcode";
    
	private static final Log logger = LogFactory.getLog(QRCodeManagementController.class);
	
	@Autowired 
	private ServletContext servletContext;
	
	@Autowired 
	private BarCodeBuilder builder;
	
    /**
     * Provides login authentication token for the clients trying to login to
     * the system.
     *
     * Users are expected to use PUT method to provide the user id and password.
     *
     * @param credentials   user security credentials.
     * @param request       client's HTTP request.
     * 
     * @return              A <code>Result</code> object containing the hashcode 
     *                      if credentials have been authenticated.
     * @throws Exception 
     */    
    @RequestMapping(value = "generate", method = RequestMethod.GET, 
    		produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType_IMAGE_TIFF_VALUE, MediaType_IMAGE_BMP_VALUE})
    @ApiOperation(value = "Generate QR Code.")
    //public @ResponseBody byte[] generateQRCode(
    public ResponseEntity<byte[]>  generateQRCode(
    		@ApiParam( value = "data", name="data" , required = true) 
    		@RequestParam("data") String data,
        HttpServletRequest request,HttpServletResponse response,
        @ApiParam( value = "A user-chosen password that can be used with password-based encryption (PBE) Algo PBEWITHMD5AND128BITAES-CBC-OPENSSL)", name="passKey", required=false) @RequestParam(required=false, value="passKey") String passKey,
        @ApiParam( value = "ForeColor e.g #000000 (Black - RGB(hex))", name="foreColor", required=false) @RequestParam(required=false, value="foreColor") String foreColor,
        @ApiParam( value = "BackgroundColor e.g #FFFFFF (White - RGB(hex))", name="bgColor", required=false) @RequestParam(required=false, value="bgColor") String bgColor,
        @ApiParam( value = "L|M|Q|H - Reed-Solomon error correctionCode Level(from low to high) default=Low", name="ecc", required=false) @RequestParam(required=false, value="ecc") String ecc,
        @ApiParam( value = "Image Size e.g #150x150", name="size", required=false) @RequestParam(required=false, value="size") String size,
        @ApiParam( value = "jpeg|tiff|gif|png|bmp - default=png", name="format", required=false) @RequestParam(required=false, value="format") String format,
        @ApiParam( value = "true|false default=false", name="download", required=false) @RequestParam(required=false, value="download") boolean download,
        @ApiIgnore @Value("#{request.getHeader('" +  ACCEPT_HEADER + "')}") String acceptHeaderValue) throws Exception {
        
    	Assert.isTrue(StringUtils.isNotEmpty(data), "Please provide valid data param.");
    	
    	 
    	logger.debug("Accept Header::" + acceptHeaderValue);
    	 
    	
    	HttpHeaders responseHeaders = new HttpHeaders();
    	
    	if(!(StringUtils.isBlank(passKey)))
    		builder.setCodeText(StringEncryptor.encrypt(data, passKey));
    	else
    		builder.setCodeText(data);
    	
    	
    	builder.setImageQuality(ImageQualityMode.Default);
    	builder.setSymbologyType(Symbology.QR);		
		builder.setCodeLocation(CodeLocation.None);
		
		builder.setForeColor(getColorValue(foreColor, "#000000"));
		
		builder.setBackColor(getColorValue(bgColor, "#FFFFFF"));
		
		builder.setQRErrorLevel(getErrorCorrectCode(ecc, QRErrorLevel.LevelL));
		
		
		Dimension imageDimention = geCustomImageSizeDimention(size);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageFormatDTO responseImageTypeDto = getRequestedImageFormat(builder, responseHeaders, acceptHeaderValue, format);
		ImageFormat responseImageType = responseImageTypeDto.getAsposeImageFormat();
		
		long startTime = System.currentTimeMillis();
		
		logger.debug("builder.save Start @  " + startTime );
		byte[] imageInByte;
		
		if(imageDimention !=null){
			
			  // Set graphics unit
			builder.setGraphicsUnit(GraphicsUnit.Millimeter);
			
		    // Set margins
		    builder.getMargins().set(0);
		    
		    builder.setForeColor(getColorValue(foreColor, "#000000"));
			
			builder.setBackColor(getColorValue(bgColor, "#FFFFFF"));
			
			logger.debug("builder.getGraphicsUnit() ::" + builder.getGraphicsUnit());
			
		    // Get BufferedImage with exact bar code only		    
		    BufferedImage img = builder.getOnlyBarCodeImage();
		    
		    logger.debug("img.getWidth() : :" + img.getWidth());
		    logger.debug("img.getHeight() :: " + img.getHeight());
		    
		    if(imageDimention.getWidth() < img.getWidth())
		    	imageDimention.width = img.getWidth();
		    
		    if(imageDimention.getHeight() < img.getHeight())
		    	imageDimention.height = img.getHeight();
		    
		    BufferedImage img2 = builder.getCustomSizeBarCodeImage(imageDimention, true);
		 
		    MediaType responseType = responseImageTypeDto.getMediaType();
		    ImageIO.write(img2, responseType.getSubtype(), baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
			
		}else{

			builder.setxDimension(1.0f);
			builder.setyDimension(1.0f);

			builder.save(baos,com.aspose.barcode.BarCodeImageFormat.Jpeg);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		}
		
		long endTime = System.currentTimeMillis();
		
		logger.debug("builder.save took " + (endTime - startTime) + " milliseconds");
		
		if(download){
			MediaType responseType = getMediaTypeFromGeneratedImageFormat(responseImageType);
	        responseHeaders.setContentType(responseType);
	        responseHeaders.add("Content-Disposition", "attachment; filename="+"Aspose_BarCode_QRCodeGen." + responseType.getSubtype());
		}
         
         return new ResponseEntity<byte[]>(imageInByte, responseHeaders, HttpStatus.CREATED);
         
         
    
    } 



	private Dimension geCustomImageSizeDimention(String size) {
		
		try{
			if(StringUtils.isBlank(size)){
				return null;
			}
			
			size = size.trim();
			
			String[] parts = size.split("x");
			
			if(parts.length < 2)
				return null;
			
			
			 int  width = Integer.parseInt(parts[0]);
			 int height = Integer.parseInt(parts[1]);
			 
			 
			   Double dwidth = width * 0.26458;
			   Double dheight = height * 0.26458;
			 
			 
			 Dimension dimension = new Dimension(dwidth.intValue(), dheight.intValue());
			 return dimension;
			
		}catch(Exception e){
			return null;
			
		}
		
			
	}


	private int getErrorCorrectCode(String ecc, int defaultLlevel) {

		if(StringUtils.isBlank(ecc))
			return defaultLlevel;
		
		ecc = ecc.trim();
		
		if("L".equalsIgnoreCase(ecc))
			return QRErrorLevel.LevelL;
		else if("M".equalsIgnoreCase(ecc))
			return QRErrorLevel.LevelM;
		else if("Q".equalsIgnoreCase(ecc))
			return QRErrorLevel.LevelQ;
		else if("H".equalsIgnoreCase(ecc))
			return QRErrorLevel.LevelH;
		
		return QRErrorLevel.LevelL;
	}


	private Color getColorValue(String foreColor, String defautlValue) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(foreColor))
				return Color.decode(defautlValue);
		else
			return Color.decode(foreColor);
	}


	private Color getColorValue(Integer foreColor, int defaultValue) {
		// TODO Auto-generated method stub
		if(foreColor == null)
			return new Color(defaultValue);
		
		return new Color(foreColor);
	}
	


	private MediaType getMediaTypeFromGeneratedImageFormat(ImageFormat responseImageType) {
		
		if(responseImageType == null)
			return MediaType.IMAGE_PNG;
		
		
		if("Jpeg".equalsIgnoreCase(responseImageType.toString()))
			return MediaType.IMAGE_JPEG;
		else if("Png".equalsIgnoreCase(responseImageType.toString()))
			return MediaType.IMAGE_PNG;
		else if("Gif".equalsIgnoreCase(responseImageType.toString()))
			return MediaType.IMAGE_GIF;
		else if("Tiff".equalsIgnoreCase(responseImageType.toString()))
			return new MediaType("image", MediaType_IMAGE_TIFF);
		else if("Bmp".equalsIgnoreCase(responseImageType.toString()))			
			return new MediaType("image", MediaType_IMAGE_BMP);
		else
			return MediaType.IMAGE_JPEG;
	}

	private ImageFormatDTO getRequestedImageFormat(BarCodeBuilder builder,
			HttpHeaders responseHeaders, String acceptHeaderValue, String format) {

		ImageFormatDTO generatedImageFormat = new ImageFormatDTO(ImageFormat.getPng(),MediaType.IMAGE_PNG);
		responseHeaders.setContentType(MediaType.IMAGE_PNG);
		
		if(StringUtils.isBlank(acceptHeaderValue) 
				&& StringUtils.isBlank(format))
			return new ImageFormatDTO(ImageFormat.getPng(),MediaType.IMAGE_PNG);
		
		String requestedFormat = "Png";
		
		if(StringUtils.isNotBlank(format)){
			requestedFormat = format.trim();
			
		}else if(StringUtils.isNotBlank(acceptHeaderValue)){
			acceptHeaderValue = acceptHeaderValue.trim();
			requestedFormat = StringUtils.removeStartIgnoreCase(acceptHeaderValue,"image/");
		}
		
		if("Jpeg".equalsIgnoreCase(requestedFormat)){
			
			generatedImageFormat = new ImageFormatDTO(ImageFormat.getJpeg(),MediaType.IMAGE_JPEG);
			responseHeaders.setContentType(MediaType.IMAGE_JPEG);
			
		}else if("Png".equalsIgnoreCase(requestedFormat)){
			
			generatedImageFormat = new ImageFormatDTO(ImageFormat.getJpeg(),MediaType.IMAGE_PNG);
			responseHeaders.setContentType(MediaType.IMAGE_PNG);
			
		}else if("Gif".equalsIgnoreCase(requestedFormat)){
			
			generatedImageFormat = new ImageFormatDTO(ImageFormat.getGif(),MediaType.IMAGE_GIF);
			responseHeaders.setContentType(MediaType.IMAGE_GIF);
			
		}else if("Tiff".equalsIgnoreCase(requestedFormat)){
			
			generatedImageFormat = new ImageFormatDTO(ImageFormat.getTiff(),new MediaType("image", MediaType_IMAGE_TIFF));
			responseHeaders.setContentType(new MediaType("image", MediaType_IMAGE_TIFF));
			
		}else if("Bmp".equalsIgnoreCase(requestedFormat)){
			
			generatedImageFormat = new ImageFormatDTO(ImageFormat.getBmp(),new MediaType("image", MediaType_IMAGE_BMP));
			responseHeaders.setContentType(new MediaType("image", MediaType_IMAGE_BMP));
			
		}else{
			
			generatedImageFormat = new ImageFormatDTO(ImageFormat.getJpeg(),MediaType.IMAGE_PNG);
			responseHeaders.setContentType(MediaType.IMAGE_PNG);
		}
		
		
		return generatedImageFormat;
	}
    
    
    
    }
