/*
 * Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
 * 
 * The MIT License (MIT)
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED.
 *
 */
package com.aspose.showcase.qrcodegen.web.api.controller;

import static com.aspose.showcase.qrcodegen.web.api.cons.MasterAPIConstantsUtil.ACCEPT_HEADER;
import static com.aspose.showcase.qrcodegen.web.api.cons.MasterAPIConstantsUtil.MEDIATYPE_IMAGE_BMP;
import static com.aspose.showcase.qrcodegen.web.api.cons.MasterAPIConstantsUtil.MEDIATYPE_IMAGE_BMP_VALUE;
import static com.aspose.showcase.qrcodegen.web.api.cons.MasterAPIConstantsUtil.MEDIATYPE_IMAGE_TIFF;
import static com.aspose.showcase.qrcodegen.web.api.cons.MasterAPIConstantsUtil.MEDIATYPE_IMAGE_TIFF_VALUE;
import static com.aspose.showcase.qrcodegen.web.api.cons.MasterAPIConstantsUtil.PIXEL_MULTIPLIER;
import static com.aspose.showcase.qrcodegen.web.api.cons.MasterAPIConstantsUtil.SIZE_LENGTH;
import static com.aspose.showcase.qrcodegen.web.api.cons.MasterAPIConstantsUtil.URI_PREFIX;
import static com.aspose.showcase.qrcodegen.web.api.cons.MasterAPIConstantsUtil.MEDIATYPE_IMAGE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.CodeLocation;
import com.aspose.barcode.GraphicsUnit;
import com.aspose.barcode.ImageFormat;
import com.aspose.barcode.ImageQualityMode;
import com.aspose.barcode.QRErrorLevel;
import com.aspose.barcode.Symbology;
import com.aspose.showcase.qrcodegen.web.api.dto.ImageFormatDTO;
import com.aspose.showcase.qrcodegen.web.api.util.StringEncryptor;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * QR Code Generation Controller
 * 
 * 
 * @author <ul>
 *         <li>Farooq Sheikh (farooq.sheikh@aspose.com)</li>
 */
@RestController
@RequestMapping(QRCodeManagementController.API_URI)
@Api(value = "QR Code Generator", description = "Operations to generate QR Codes using Aspose.BarCode for Java", position = 1)
public class QRCodeManagementController extends BaseController {

    public static final String API_URI = URI_PREFIX + "/qrcode";

    private static final Log LOGGER = LogFactory
            .getLog(QRCodeManagementController.class);

    @Autowired
    private BarCodeBuilder builder;

    /**
     * generateQRCode - Main API to generate QR Code
     * 
     * @throws Exception
     * 
     * 
     */
    @RequestMapping(value = "generate", method = RequestMethod.GET, produces = {
            MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_GIF_VALUE, MEDIATYPE_IMAGE_TIFF_VALUE,
            MEDIATYPE_IMAGE_BMP_VALUE })
    @ApiOperation(value = "Generate QR Code.")
    public ResponseEntity<byte[]> generateQRCode(
            @ApiParam(value = "data", name = "data", required = true) @RequestParam("data") String data,
            @ApiParam(value = "A user-chosen password that can be used with password-based encryption (PBE) Algo PBEWITHMD5AND128BITAES-CBC-OPENSSL)", name = "passKey", required = false) @RequestParam(required = false, value = "passKey") String passKey,
            @ApiParam(value = "ForeColor e.g #000000 (Black - RGB(hex))", name = "foreColor", required = false) @RequestParam(required = false, value = "foreColor") String foreColor,
            @ApiParam(value = "BackgroundColor e.g #FFFFFF (White - RGB(hex))", name = "bgColor", required = false) @RequestParam(required = false, value = "bgColor") String bgColor,
            @ApiParam(value = "L|M|Q|H - Reed-Solomon error correctionCode Level(from low to high) default=Low", name = "ecc", required = false) @RequestParam(required = false, value = "ecc") String ecc,
            @ApiParam(value = "Image Size e.g #150x150", name = "size", required = false) @RequestParam(required = false, value = "size") String size,
            @ApiParam(value = "jpeg|tiff|gif|png|bmp - default=png", name = "format", required = false) @RequestParam(required = false, value = "format") String format,
            @ApiParam(value = "true|false default=false", name = "download", required = false) @RequestParam(required = false, value = "download") boolean download,
            @ApiIgnore @Value("#{request.getHeader('" + ACCEPT_HEADER + "')}") String acceptHeaderValue)
            throws Exception {

        Assert.isTrue(StringUtils.isNotEmpty(data),
                "Please provide valid data param.");

        LOGGER.debug("Accept Header::" + acceptHeaderValue);

        HttpHeaders responseHeaders = new HttpHeaders();

        if (!(StringUtils.isBlank(passKey))) {
            builder.setCodeText(StringEncryptor.encrypt(data, passKey));
        } else {
            builder.setCodeText(data);
        }

        builder.setImageQuality(ImageQualityMode.Default);
        builder.setSymbologyType(Symbology.QR);
        builder.setCodeLocation(CodeLocation.None);

        builder.setForeColor(getColorValue(foreColor, "#000000"));

        builder.setBackColor(getColorValue(bgColor, "#FFFFFF"));

        builder.setQRErrorLevel(getErrorCorrectCode(ecc, QRErrorLevel.LevelL));

        Dimension imageDimention = geCustomImageSizeDimention(size);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageFormatDTO responseImageTypeDto = getRequestedImageFormat(
                responseHeaders, acceptHeaderValue, format);

        long startTime = System.currentTimeMillis();

        LOGGER.debug("builder.save Start @  " + startTime);
        byte[] imageInByte;

        if (imageDimention != null) {

            // Set graphics unit
            builder.setGraphicsUnit(GraphicsUnit.Millimeter);

            // Set margins
            builder.getMargins().set(0);

            builder.setForeColor(getColorValue(foreColor, "#000000"));

            builder.setBackColor(getColorValue(bgColor, "#FFFFFF"));

            LOGGER.debug("builder.getGraphicsUnit() ::"
                    + builder.getGraphicsUnit());

            // Get BufferedImage with exact bar code only
            BufferedImage img = builder.getOnlyBarCodeImage();

            LOGGER.debug("img.getWidth() : :" + img.getWidth());
            LOGGER.debug("img.getHeight() :: " + img.getHeight());

            if (imageDimention.getWidth() < img.getWidth()) {
                imageDimention.width = img.getWidth();
            }

            if (imageDimention.getHeight() < img.getHeight()) {
                imageDimention.height = img.getHeight();
            }

            BufferedImage img2 = builder.getCustomSizeBarCodeImage(
                    imageDimention, true);

            MediaType responseType = responseImageTypeDto.getMediaType();
            ImageIO.write(img2, responseType.getSubtype(), baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();

        } else {

            builder.setxDimension(1.0f);
            builder.setyDimension(1.0f);

            builder.save(baos, responseImageTypeDto.getBarCodeImageFormat());
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
        }

        long endTime = System.currentTimeMillis();

        LOGGER.debug("builder.save took " + (endTime - startTime)
                + " milliseconds");

        if (download) {

            MediaType responseType = responseImageTypeDto.getMediaType();
            responseHeaders.setContentType(responseType);
            responseHeaders.add("Content-Disposition", "attachment; filename="
                    + "Aspose_BarCode_QRCodeGen." + responseType.getSubtype());
        }

        return new ResponseEntity<byte[]>(imageInByte, responseHeaders,
                HttpStatus.CREATED);

    }

    private Dimension geCustomImageSizeDimention(String imgSize) {

        try {

            if (StringUtils.isBlank(imgSize)) {
                return null;
            }

            String size = imgSize.trim();

            String[] parts = size.split("x");

            if (parts.length < SIZE_LENGTH) {
                return null;
            }

            int width = Integer.parseInt(parts[0]);
            int height = Integer.parseInt(parts[1]);

            Double dwidth = width * PIXEL_MULTIPLIER;
            Double dheight = height * PIXEL_MULTIPLIER;

            return new Dimension(dwidth.intValue(), dheight.intValue());

        } catch (Exception e) {
            LOGGER.info(e);
            return null;
        }

    }

    private int getErrorCorrectCode(String errorCorrectionCode, int defaultLevel) {

        if (StringUtils.isBlank(errorCorrectionCode)) {
            return defaultLevel;
        }

        String ecc = errorCorrectionCode.trim();

        if ("L".equalsIgnoreCase(ecc)) {
            return QRErrorLevel.LevelL;
        } else if ("M".equalsIgnoreCase(ecc)) {
            return QRErrorLevel.LevelM;
        } else if ("Q".equalsIgnoreCase(ecc)) {
            return QRErrorLevel.LevelQ;
        } else if ("H".equalsIgnoreCase(ecc)) {
            return QRErrorLevel.LevelH;
        }

        return QRErrorLevel.LevelL;
    }

    private Color getColorValue(String foreColor, String defautlValue) {

        if (StringUtils.isBlank(foreColor)) {
            return Color.decode(defautlValue);
        } else {
            return Color.decode(foreColor);
        }
    }

    private ImageFormatDTO getRequestedImageFormat(HttpHeaders responseHeaders,
            String acceptHeaderValue, String format) {

        ImageFormatDTO generatedImageFormat = new ImageFormatDTO(
                ImageFormat.getPng(), MediaType.IMAGE_PNG,
                com.aspose.barcode.BarCodeImageFormat.Png);
        responseHeaders.setContentType(MediaType.IMAGE_PNG);

        if (StringUtils.isBlank(acceptHeaderValue)
                && StringUtils.isBlank(format)) {
            return new ImageFormatDTO(ImageFormat.getPng(),
                    MediaType.IMAGE_PNG,
                    com.aspose.barcode.BarCodeImageFormat.Png);
        }

        String requestedFormat = "Png";

        if (StringUtils.isNotBlank(format)) {
            requestedFormat = format.trim();

        } else if (StringUtils.isNotBlank(acceptHeaderValue)) {
            requestedFormat = StringUtils.removeStartIgnoreCase(
                    acceptHeaderValue.trim(), "image/");
        }

        if ("Jpeg".equalsIgnoreCase(requestedFormat)) {

            generatedImageFormat = new ImageFormatDTO(ImageFormat.getJpeg(),
                    MediaType.IMAGE_JPEG,
                    com.aspose.barcode.BarCodeImageFormat.Jpeg);
            responseHeaders.setContentType(MediaType.IMAGE_JPEG);

        } else if ("Png".equalsIgnoreCase(requestedFormat)) {

            generatedImageFormat = new ImageFormatDTO(ImageFormat.getPng(),
                    MediaType.IMAGE_PNG,
                    com.aspose.barcode.BarCodeImageFormat.Png);
            responseHeaders.setContentType(MediaType.IMAGE_PNG);

        } else if ("Gif".equalsIgnoreCase(requestedFormat)) {

            generatedImageFormat = new ImageFormatDTO(ImageFormat.getGif(),
                    MediaType.IMAGE_GIF,
                    com.aspose.barcode.BarCodeImageFormat.Gif);
            responseHeaders.setContentType(MediaType.IMAGE_GIF);

        } else if ("Tiff".equalsIgnoreCase(requestedFormat)) {

            generatedImageFormat = new ImageFormatDTO(ImageFormat.getTiff(),
                    new MediaType(MEDIATYPE_IMAGE, MEDIATYPE_IMAGE_TIFF),
                    com.aspose.barcode.BarCodeImageFormat.Tiff);
            responseHeaders.setContentType(new MediaType("image",
                    MEDIATYPE_IMAGE_TIFF));

        } else if ("Bmp".equalsIgnoreCase(requestedFormat)) {

            generatedImageFormat = new ImageFormatDTO(ImageFormat.getBmp(),
                    new MediaType(MEDIATYPE_IMAGE, MEDIATYPE_IMAGE_BMP),
                    com.aspose.barcode.BarCodeImageFormat.Bmp);
            responseHeaders.setContentType(new MediaType("image",
                    MEDIATYPE_IMAGE_BMP));

        } else {

            generatedImageFormat = new ImageFormatDTO(ImageFormat.getPng(),
                    MediaType.IMAGE_PNG,
                    com.aspose.barcode.BarCodeImageFormat.Png);
            responseHeaders.setContentType(MediaType.IMAGE_PNG);
        }

        return generatedImageFormat;
    }

}
