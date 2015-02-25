/**
 * Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved. * 
 * The MIT License (MIT)
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED.
 * 
 * @author <ul><li>Farooq Sheikh (farooq.sheikh@aspose.com)</li>
 * 
 * Define Require module with dependencies
 * app.js
 */
define([
  'jquery',
  'underscore',
  'backbone',
  'routers/router', // Request router.js
], function($, _, Backbone, Router){
  var initialize = function(){
    // Pass in our Router module and call it's initialize function
    Router.initialize();
  }

  return {
    initialize: initialize
  };
});