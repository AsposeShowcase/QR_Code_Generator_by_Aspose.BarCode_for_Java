/**
 * Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved. * 
 * The MIT License (MIT)
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED.
 * 
 * @author <ul><li>Farooq Sheikh (farooq.sheikh@aspose.com)</li>
 * 
 * Define Require module with dependencies
 * 
 */
define([
  'bootstrap',
  'bootstrap_colorpicker',
  'bootstrap_datetimepicker',
  'bootstrapslider',  
  'underscore',
  'backbone',
  'text!templates/types.html'
], function ($,$,$,$,_, Backbone, TypesTemplate) {
  
  var TypesView = Backbone.View.extend({
    el:'div.QRCodeGenMain',
    events : {
    	'submit .qr-code-form' : 'previewQRCode',
    	'changeColor .fcolorpicker': 'onFColorChange',
    	'changeColor .bgcolorpicker': 'onBGColorChange',
    	'sliderchange .imgSizeSlider': 'onImgSizeSliderChange',
    	'dp.change .datepickerEventFrom': 'onEventFromChangeDate',
    	'dp.change .datepickerEventTo': 'onEventToChangeDate',
    	'change .evenTimeZone': 'onTimeZoneChangeDate'
    	
    },    
    template:_.template(TypesTemplate),
    onImgSizeSliderChange : function(e,result) {
    	var imgSize = Math.round(result.value);
    	this.$('.input-size').val("" + imgSize + "x" +  imgSize);
    	this.$('.imgSizeSliderCaption').text("" + imgSize + "x" +  imgSize);
    },
    onFColorChange : function(ev) {
    	this.$('.input-fcolor').val(ev.color.toHex());
    },
    onBGColorChange : function(ev) {
    	this.$('.input-bgcolor').val(ev.color.toHex());
    },
    onTimeZoneChangeDate : function(ev) {
    	
    	var eventFromDate = this.$('.datepickerEventFrom').data("DateTimePicker").date();
    	var eventToDate = this.$('.datepickerEventTo').data("DateTimePicker").date();
    	
    	this.$('.input-event-fromdate').val(eventFromDate.utcOffset(this.$('.evenTimeZone option:selected').text()).format('YYYYMMDDTHHmmss'));
    	this.$('.input-event-todate').val(eventToDate.utcOffset(this.$('.evenTimeZone option:selected').text()).format('YYYYMMDDTHHmmss'));
    	
    },
    onEventFromChangeDate : function(ev) {
    	
    	this.$('.input-event-fromdate').val(ev.date.utcOffset(this.$('.evenTimeZone option:selected').text()).format('YYYYMMDDTHHmmss'));
    },
    
    onEventToChangeDate : function(ev) {
    	this.$('.input-event-todate').val(ev.date.utcOffset(this.$('.evenTimeZone option:selected').text()).format('YYYYMMDDTHHmmss'));
    },
    updateDownloadTag : function(requestString){
    	
    	this.$('.download-jpeg').attr('href','/qrcodegen/api/qrcode/generate?data='+ requestString + '&download=true&format=jpeg');
		this.$('.download-png').attr('href','/qrcodegen/api/qrcode/generate?data='+ requestString + '&download=true&format=png');
		this.$('.download-gif').attr('href','/qrcodegen/api/qrcode/generate?data='+ requestString + '&download=true&format=gif');
		this.$('.download-tiff').attr('href','/qrcodegen/api/qrcode/generate?data='+ requestString + '&download=true&format=tiff');
		
		this.$('.qr-code-download').removeClass('hide');
	
    },
    generateEvent : function(){
    	 var beginEvent = "BEGIN:VEVENT\r\n";
		 var endEvent = "END:VEVENT";
		 
		 var eventDesc = "SUMMARY:" + this.$('.input-event-description').val() + "\r\n";
		 var eventStart = "DTSTART:" + this.$('.input-event-fromdate').val() + "\r\n";
		 var eventEnd = "DTEND:" + this.$('.input-event-todate').val() + "\r\n";
		 
		 var generatedEvent = beginEvent + eventDesc + eventStart + eventEnd + endEvent;
		 return generatedEvent;
    },
    generateVCard : function(){
    	
		 var beginVCard = "BEGIN:VCARD\r\nVERSION:3.0\r\n";
		 var endVCard = "END:VCARD";
		
		 	var firstName = this.$('.input-vcard-firstname').val();
		 	var lastName = this.$('.input-vcard-lastname').val();
		 	
		 	if (firstName == '' || firstName == null || lastName == '' || lastName == null) {
		 		console.log("First and Lastname are required");
		 		alert("First and Lastname are required");
		 		return null;
		 	}
		 
		 	var vcardN = "N:" + firstName + ";" + lastName + ";;;\r\n";
		 	var vcardFN = "FN:" + firstName + " " + lastName + "\r\n";
		 	
		 	var generatedVCard = beginVCard + vcardN + vcardFN;
		 	
		 	var org = this.$('.input-vcard-organization').val();
		 	
		 	if (org != "undefined" && org != '' && org != null ){
		 		generatedVCard = generatedVCard + "ORG:" + org + "\r\n";
		 	}
		 	
		 	var title = this.$('.input-vcard-title').val();
		 	
		 	if (title != "undefined" && title != '' && title != null ){
		 		generatedVCard = generatedVCard + "TITLE:" + title + "\r\n";
		 	}
		 	
		 	var emailHome = this.$('.input-vcard-email-personal').val();
		 	
		 	if (emailHome != "undefined" && emailHome != '' && emailHome != null ){
		 		generatedVCard = generatedVCard + "EMAIL;type=INTERNET;type=HOME:" + emailHome + "\r\n";
		 	}
		 	
		 	var emailWork = this.$('.input-vcard-email-business').val();
		 	
		 	if (emailWork != "undefined" && emailWork != '' && emailWork != null ){
		 		generatedVCard = generatedVCard + "EMAIL;type=INTERNET;type=WORK:" + emailWork + "\r\n";
		 	}
		 	
		 	var phoneCell = this.$('.input-vcard-phone-mobile').val();
		 	
		 	if (phoneCell != "undefined" && phoneCell != '' && phoneCell != null ){
		 		generatedVCard = generatedVCard + "TEL;TYPE=CELL,VOICE:" + phoneCell + "\r\n";
		 	}

		 	var phoneHome = this.$('.input-vcard-phone-home').val();
		 	
		 	if (phoneHome != "undefined" && phoneHome != '' && phoneHome != null ){
		 		generatedVCard = generatedVCard + "TEL;TYPE=HOME,VOICE:" + phoneHome + "\r\n";
		 	}
		 	
		 	var addressVCard=";;";
		 	
		 	var street = this.$('.input-vcard-address-street').val();
		 	
		 	if (street != "undefined" && street != '' && street != null ){
		 		addressVCard = addressVCard + street + ";"
		 		
		 	}else{addressVCard = addressVCard + ";";}
		 	
		 	var city = this.$('.input-vcard-address-city').val();
		 	
		 	if (city != "undefined" && city != '' && city != null ){
		 		addressVCard = addressVCard + city + ";"
		 		
		 	}else{addressVCard = addressVCard + ";";}
		 	
		 	//for region
		 	addressVCard = addressVCard + ";"
		 	
		 	var postalcode = this.$('.input-vcard-address-postalcode').val();
		 	if (postalcode != "undefined" && postalcode != '' && postalcode != null ){
		 		addressVCard = addressVCard + postalcode + ";"
		 		
		 	}else{addressVCard = addressVCard + ";";}
		 	
		 	var country = this.$('.input-vcard-address-country').val();
		 	if (country != "undefined" && country != '' && country != null ){
		 		addressVCard = addressVCard + country + ";"
		 		
		 	}else{addressVCard = addressVCard + ";";}
		 	
		 	if(addressVCard !=null && addressVCard != ";;;;;;;"){
		 		
		 		addressVCard = "ADR:" + addressVCard  + "\r\n";
		 		generatedVCard = generatedVCard + addressVCard ;
		 	}
		 	
		 	
		 	var weburl = this.$('.input-vcard-url').val();
		 	
		 	if (weburl != "undefined" && weburl != '' && weburl != null ){
		 		generatedVCard = generatedVCard + "URL:" + weburl + "\r\n";
		 	}
		 	
		 	generatedVCard = generatedVCard + endVCard;
		 	
		 	
		 	return generatedVCard;
	
   },
    previewQRCode : function(ev) {

    	console.log("ev.currentTarget.page) " + ev.currentTarget.page.value);
    	
    	this.$('.qrcode-preview-processing').removeClass('hide');
    	this.$('.qrcode-preview-image').addClass('hide');
    	
    	var page = ev.currentTarget.page.value;
    	if(page == 'url'){
    		
    		var requestString= this.$('.input-url').val();
    		requestString = this.addRequestSettings(requestString)
    		
    		var that = this;
    		
    		this.$('.qrcode-preview-image').load(function(){
    			that.$('.qrcode-preview-processing').addClass('hide');
    			that.$('.qrcode-preview-image').removeClass('hide');
    		})
    		.error(function(){
    			that.$('.qrcode-preview-processing').attr('src', '/qrcodegen/resources/imgs/error.png');
    		})
    		.attr('src', '/qrcodegen/api/qrcode/generate?data='+ requestString);
    		
    		this.updateDownloadTag(requestString);
    		
    	}
    	
    	
    	if(page == 'text'){
    		
    		var requestString= this.$('.input-textarea').val();
    		requestString = this.addRequestSettings(requestString)
    		
    		var that = this;
    		
    		this.$('.qrcode-preview-image').load(function(){
    			that.$('.qrcode-preview-processing').addClass('hide');
    			that.$('.qrcode-preview-image').removeClass('hide');
    		})
    		.error(function(){
    			that.$('.qrcode-preview-processing').attr('src', '/qrcodegen/resources/imgs/error.png');
    		})
    		.attr('src', '/qrcodegen/api/qrcode/generate?data='+ requestString);
    		
    		this.updateDownloadTag(requestString);
    		
    	}
    	
    	
    	if(page == 'geo'){
    		
    		var requestString= "geo:";
    		
    		if("S" == this.$("input[name='geo-ns-radio']:checked").val())
    		    requestString = requestString + "-" + this.$('.input-geo-ns').val() + ",";
    		 else
    			 requestString = requestString + this.$('.input-geo-ns').val() + ",";
    		
    		if("W" == this.$("input[name='geo-ew-radio']:checked").val())
    		    requestString = requestString + "-" + this.$('.input-geo-ew').val();
    		 else
    			 requestString = requestString + this.$('.input-geo-ew').val();
    		
    		requestString = encodeURIComponent(requestString);
    		requestString = this.addRequestSettings(requestString)
    		
    		var that = this;
    		
    		this.$('.qrcode-preview-image').load(function(){
    			that.$('.qrcode-preview-processing').addClass('hide');
    			that.$('.qrcode-preview-image').removeClass('hide');
    		})
    		.error(function(){
    			that.$('.qrcode-preview-processing').attr('src', '/qrcodegen/resources/imgs/error.png');
    		})
    		.attr('src', '/qrcodegen/api/qrcode/generate?data='+ requestString);
    		
    		this.updateDownloadTag(requestString);
    		
    	}
    	
    	if(page == 'vcard'){
    		var requestString= this.generateVCard();
    		
    		if(requestString == null){
    			this.$('.qrcode-preview-processing').addClass('hide');
    			this.$('.qrcode-preview-image').removeClass('hide');
    			return false;
    		}
    		
    		requestString = encodeURIComponent(requestString);
    		requestString = this.addRequestSettings(requestString)
    		
    		var that = this;
    		
    		this.$('.qrcode-preview-image').load(function(){
    			that.$('.qrcode-preview-processing').addClass('hide');
    			that.$('.qrcode-preview-image').removeClass('hide');
    		})
    		.error(function(){
    			that.$('.qrcode-preview-processing').attr('src', '/qrcodegen/resources/imgs/error.png');
    		})
    		.attr('src', '/qrcodegen/api/qrcode/generate?data='+ requestString);
    		
    		this.updateDownloadTag(requestString);
    		
    	}
    	
    	if(page == 'event'){
    		
    		var requestString= this.generateEvent();
    		requestString = encodeURIComponent(requestString);    		
    		requestString = this.addRequestSettings(requestString)
    		
    		var that = this;
    		
    		this.$('.qrcode-preview-image').load(function(){
    			that.$('.qrcode-preview-processing').addClass('hide');
    			that.$('.qrcode-preview-image').removeClass('hide');
    		})    		
    		.error(function(){
    			that.$('.qrcode-preview-processing').attr('src', '/qrcodegen/resources/imgs/error.png');
    		})
    		.attr('src', '/qrcodegen/api/qrcode/generate?data='+ requestString);
    		
    		this.updateDownloadTag(requestString);
    	}
    	
    	if(page == 'call'){
    		
    		var requestString= "tel:";
    		requestString = requestString + this.$('.input-phone-countrycode').val() + this.$('.input-phone-areacode').val() +  this.$('.input-phone-phonenumber').val();
    		requestString = encodeURIComponent(requestString);
    		requestString = this.addRequestSettings(requestString)
    		
    		var that = this;
    		
    		this.$('.qrcode-preview-image').load(function(){
    			that.$('.qrcode-preview-processing').addClass('hide');
    			that.$('.qrcode-preview-image').removeClass('hide');
    		})    		
    		.error(function(){
    			that.$('.qrcode-preview-processing').attr('src', '/qrcodegen/resources/imgs/error.png');
    		})
    		.attr('src', '/qrcodegen/api/qrcode/generate?data='+ requestString);
    		
    		this.updateDownloadTag(requestString);
    	}
    	
    	
    	if(page == 'sms'){
    		
    		var requestString= "SMSTO:";
    		requestString = requestString + this.$('.input-sms-countrycode').val() + this.$('.input-sms-areacode').val() +  this.$('.input-sms-phonenumber').val();
    		requestString = requestString + ":" + this.$('.input-sms-content').val();
    		requestString = encodeURIComponent(requestString);
    		requestString = this.addRequestSettings(requestString)
    		
    		
    		var that = this;
    		
    		this.$('.qrcode-preview-image').load(function(){
    			that.$('.qrcode-preview-processing').addClass('hide');
    			that.$('.qrcode-preview-image').removeClass('hide');
    		})    		
    		.error(function(){
    			that.$('.qrcode-preview-processing').attr('src', '/qrcodegen/resources/imgs/error.png');
    		})
    		.attr('src', '/qrcodegen/api/qrcode/generate?data='+ requestString);
    		
    		this.updateDownloadTag(requestString);
    	}
    	
    	if(page == 'wifi'){
    		
    		var requestString= "WIFI:";
    		requestString = requestString + "T:" + this.$('.input-wifi-encryption-type option:selected').val() + ";" + "S:" + this.$('.input-wifi-ssid').val() +  ";" + "P:"+ this.$('.input-wifi-pass').val() +";" ;
    	
    		 if(this.$('.input-wifi-hidden').is(':checked'))
    			 requestString = requestString + "H:true;"
    			 else
    				 requestString = requestString + ";"
    		 
    		requestString = encodeURIComponent(requestString);
    		requestString = this.addRequestSettings(requestString)
    		
    		var that = this;
    		
    		this.$('.qrcode-preview-image').load(function(){
    			that.$('.qrcode-preview-processing').addClass('hide');
    			that.$('.qrcode-preview-image').removeClass('hide');
    		})    		
    		.error(function(){
    			that.$('.qrcode-preview-processing').attr('src', '/qrcodegen/resources/imgs/error.png');
    		})
    		.attr('src', '/qrcodegen/api/qrcode/generate?data='+ requestString);
    		
    		this.updateDownloadTag(requestString);
    	}
    	
    	
    	if(page == 'encryption'){
    		
    		var passKey = this.$('.input-encryption-password').val();
		 	
		 	if (passKey == '' || passKey == null ) {
		 		console.log("Password is required");
		 		alert("Password is required");
		 		this.$('.qrcode-preview-processing').addClass('hide');
    			this.$('.qrcode-preview-image').removeClass('hide');
		 		return false;
		 	}
    		
    		var requestString= this.$('.input-encryption-message').val();
    		
    		if (requestString == '' || requestString == null ) {
		 		console.log("Message Text is required");
		 		alert("Message Text is required");
		 		this.$('.qrcode-preview-processing').addClass('hide');
    			this.$('.qrcode-preview-image').removeClass('hide');
		 		return false;
		 	}
    		
    		requestString = requestString + '&passKey=' + passKey;
    		requestString = this.addRequestSettings(requestString);
    		
    		var that = this;
    		
    		this.$('.qrcode-preview-image').load(function(){
    			that.$('.qrcode-preview-processing').addClass('hide');
    			that.$('.qrcode-preview-image').removeClass('hide');
    		})    		
    		.error(function(){
    			that.$('.qrcode-preview-processing').attr('src', '/qrcodegen/resources/imgs/error.png');
    		})
    		.attr('src', '/qrcodegen/api/qrcode/generate?data='+ requestString);
    		
    		this.updateDownloadTag(requestString);
    	}
    	
    	
    	return false;
    	
	},
	  initialize: function(){
		  if ( typeof this.$('.imgSizeSlider').slider != "undefined")
			 	this.$('.imgSizeSlider').slider();

	  },
 
	render : function(data) {
		 
		var that = this;
			var template = this.template({
						data : data
					});
			that.$el.html(template);
			that.$('.datatype-'+data.page).toggleClass('hide');
			that.$('.type-'+data.page).toggleClass('selected');
			that.$('.fcolorpicker').colorpicker({color:"#000000"});
			that.$('.bgcolorpicker').colorpicker({color:"#FFFFFF"});
			that.$('.bgcolorpicker').colorpicker({color:"#FFFFFF"});
			
			if(data.page == 'event'){
				that.$('.datepickerEventFrom').datetimepicker({useCurrent: false, defaultDate : new Date()});
				that.$('.datepickerEventTo').datetimepicker({useCurrent: false, defaultDate : new Date()});
			}
			
			 if ( typeof that.$('.imgSizeSlider').slider != "undefined")
				 	that.$('.imgSizeSlider').slider();			
		
	},
	addRequestSettings : function(requestString){
		
		requestString = requestString + "&ecc=" + this.$('.errorCorrectionCode option:selected').text();
		requestString = requestString + "&foreColor=" + encodeURIComponent(this.$('.input-fcolor').val());
		requestString = requestString + "&bgColor=" + encodeURIComponent(this.$('.input-bgcolor').val());
		
		if(this.$('.input-size').val() != "100x100")
			requestString = requestString + "&size=" + this.$('.input-size').val();
		
		return requestString;
		
	},
	close: function(){
	      this.remove();
	      this.unbind();
	      this.model.unbind("change", this.modelChanged);
	    }
	
	 
  });

  // Return the view as the Require module
  return TypesView;

});
