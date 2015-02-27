# QR Code Generator by Aspose.BarCode for Java
---
QR Code Generator by Aspose.BarCode for Java is a web application to generate various types of QR Code using [Aspose.BarCode for Java].

[Aspose.BarCode for Java] is a robust and reliable barcode generation and recognition component, written in Java, it allows developers to quickly and easily add barcode generation and recognition functionality to their Java applications. It is available for the Java SE, Java EE and Java ME platforms.

This application highlights commonly used features of [Aspose.BarCode for Java] and demonstrates how to use them to generate various types of QR Code including Text, Url, Email, Phone number, Contact (VCard), Event, Geolocation, Wifi  and SMS. 

![aspose-showcase-qrcodegen_png]

# Highlights
  - Spring MVC 4.0 RESTFul API base web application for QR code generation using [Aspose.BarCode for Java] library
  - Mobile friendly [responsive and flat UI]  using Bootstrap 3+ , RequireJS, Backbone.js on top of restful APIs.
  - Generate various types of (scan able) QR codes using of Aspose.BarCode for Java
   - URL
   - Text
   - Vcard
   - SMS
   - Phone
   - Event
   - Wifi
   - Geolocation
   - Encrypted Text
  - Support various QR Code customization including 
   - Resizing
   - Selection of Level of Reed-Solomon error correction. From low to high: LevelL, LevelM, LevelQ, LevelH. 
   - Foreground and Background color selection
  - Support to save generated QR Code  in different image formats (Bmp, Gif, Jpeg, Png, Tiff)
  - Support to generate encrypted QR Code from Plain Text using password-based encryption (PBE)- PBEWITHMD5AND128BITAES-CBC-OPENSSL with OpenSSL Compatibility. 
   
# Steps to generate QR Code
1.  Select the QR Code Type
2.  Input Type Context
3.  Customize QR Code Settings
4.  Generate/Preview QR Code
5.  Save QR Code

![aspose-showcase-qrcodegen-generation-steps]

# Export/Save Generated QR Code
The following formats are supported for export generated QR Code by the application. 
- PNG
- JPEG
- GIF
- TIFF
- BMP

# Embed Anywhere
Application also supports to directly embed generated QR Code in any website of your choice using direct URL. Here are the steps.

 # Steps to generate QR Code
1.  Select the QR Code Type
2.  Input Type Context
3.  Customize QR Code Settings
4.  Generate/Preview QR Code
5.  Copy QR Code Save URL
6.  Change URL query string ‘download’ to false. 
7.  That’s it.  URL is now ready to embed as an image. 

```sh
<img width="100%" src="http://localhost:8080/qrcodegen/api/qrcode/generate?data=http://aspose.com&ecc=L&foreColor=%23000000&bgColor=%23FFFFFF&download=false&format=png">
```

# API Documentation
Application also exports its Restful APIs documentation using [swagger specification]. You can also view the application APIs swagger base documentation by accessing following URL after running the project.
##### http://localhost:8080/qrcodegen/public/docs/  

![aspose-showcase-qrcodegen-swagger-api-docs_png]

# To get the code
Clone the repository:
```sh
$ git clone https://github.com/AsposeShowcase/QR_Code_Generator_by_Aspose.BarCode_for_Java.git
```

If this is your first time using Github, review http://help.github.com to learn the basics.

#To run the application:
**From the command line with Maven:**
```sh
$ cd QR_Code_Generator_by_Aspose.BarCode_for_Java
$ mvn jetty:run  
```
**From IDE such as NetBeans IDE, Eclipse, IDEA or others:**
- [Download] or [clone] the project Github repository
- Import [QR_Code_Generator_by_Aspose.BarCode_for_Java] as a Maven Project
- Server to run, such as [Glassfish]/[Tomcat]

*Note: Set the application context path to ‘qrcodegen’ (if required).*

#####Access the deployed application at: http://localhost:8080/qrcodegen/
<br>
#version
1.0

#Aspose License
The project works without a license, with limitations. To remove limitations, you can acquire a free [temporary license] or [buy a full license].

##To apply aspose lincese

Simply change <code>license.file</code> property value to the path to your aspose license file in the project configuration <code>src/main/resources/config.properties</code>. 

```sh
license.file=C:\\aspose\\Aspose.Total.Java.lic
```

# Technologies 

The project uses a number of open source projects to work properly:
* [Spring Framework]
* [swagger-springmvc]
* [Swagger UI]
* [Twitter Bootstrap]
* [RequireJS]
* [Backbone.js]
* [jQuery]



[temporary license]:http://www.aspose.com/corporate/purchase/temporary-license.aspx
[buy a full license]:http://www.aspose.com/purchase/default.aspx
[QR_Code_Generator_by_Aspose.BarCode_for_Java]:https://github.com/AsposeShowcase/QR_Code_Generator_by_Aspose.BarCode_for_Java/
[Aspose.BarCode for Java]:http://www.aspose.com/java/barcode-component.aspx
[aspose-showcase-qrcodegen_png]: http://asposeshowcase.github.io/QR_Code_Generator_by_Aspose.BarCode_for_Java/images/docs/aspose-showcase-qrcodegen.png  "QR Code Generator by Aspose.BarCode for Java"
[aspose-showcase-qrcodegen-swagger-api-docs_png]: http://asposeshowcase.github.io/QR_Code_Generator_by_Aspose.BarCode_for_Java/images/docs/aspose-showcase-qrcodegen-swagger-api-docs.png  "QR Code Generator by Aspose.BarCode for Java - APIs Documentation using Swagger"
[aspose-showcase-qrcodegen-ios_iPhone-6_8.0_portrait_png]: http://asposeshowcase.github.io/QR_Code_Generator_by_Aspose.BarCode_for_Java/images/docs/aspose-showcase-qrcodegen-ios_iPhone-6_8.0_portrait.jpg  "QR Code Generator by Aspose.BarCode for Java - Responsive and flat UI"
[aspose-showcase-qrcodegen-generation-steps]: http://asposeshowcase.github.io/QR_Code_Generator_by_Aspose.BarCode_for_Java/images/docs/aspose-showcase-qrcodegen-generation-steps.png  "QR Code Generator by Aspose.BarCode for Java - Steps"
[aspose-showcase-qrcodegen-eclipse-ide]: http://asposeshowcase.github.io/QR_Code_Generator_by_Aspose.BarCode_for_Java/images/docs/aspose-showcase-qrcodegen-eclipse-ide.png  "QR Code Generator by Aspose.BarCode for Java - Eclipse IDE"
[responsive and flat UI]:http://asposeshowcase.github.io/QR_Code_Generator_by_Aspose.BarCode_for_Java/images/docs/aspose-showcase-qrcodegen-ios_iPhone-6_8.0_portrait.jpg
[Swagger UI]:https://github.com/swagger-api/swagger-ui
[Spring Framework]:http://projects.spring.io/spring-framework/
[swagger-springmvc]:https://github.com/martypitt/swagger-springmvc

[Twitter Bootstrap]:http://twitter.github.com/bootstrap/
[jQuery]:http://jquery.com
[RequireJS]:http://requirejs.org/
[Backbone.js]:http://backbonejs.org/
[swagger specification]:https://github.com/swagger-api/swagger-spec
[download]:https://github.com/AsposeShowcase/QR_Code_Generator_by_Aspose.BarCode_for_Java/archive/master.zip
[clone]:github-windows://openRepo/https://github.com/AsposeShowcase/QR_Code_Generator_by_Aspose.BarCode_for_Java
[Glassfish]:https://glassfish.java.net/
[Tomcat]:http://tomcat.apache.org/

