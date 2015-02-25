/**
 * Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved. * 
 * The MIT License (MIT)
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED.
 * 
 * @author <ul><li>Farooq Sheikh (farooq.sheikh@aspose.com)</li>
 * 
 * This file is the application bootstrap. It contains RequireJS configuration and Backbone bootstrap.
 */
require.config({
	
  /**
   * Require 2.0 introduced shim config which allows to configure dependencies for
   * scripts that do not call define() to register a module
   */
  shim:{
    "underscore":{
      exports:"_"
    },
    "backbone":{
      deps:[
        "underscore",
        "jquery"
      ],
      exports:"Backbone"
    },
    "bootstrap":{
      deps:[
        "jquery"
      ],
      exports:"jQuery"
    },
    "bootstrap_colorpicker":{
        deps:[
          "jquery"
        ],
        exports:"jQuery"
      },
      "bootstrap_datetimepicker":{
            deps:[
              "jquery",
              "moment"
            ],
            exports:"jQuery"
       },
       "moment":{
           deps:[
             "jquery"
           ],
           exports:"jQuery"
      },
      "mouse_core":{
          deps:[
            "jquery"
          ],
          exports:"jQuery"
        },
        "touch_punch":{
            deps:[
              "jquery"
            ],
            exports:"jQuery"
          },
        "bootstrapslider":{
            deps:[
              "jquery",
              "mouse_core",
              "touch_punch"
            ],
            exports:"jQuery"
          }
  },
  /**
   * Shortcut configuration for libs
   */
  paths:{
    jquery:"../libs/jquery/1.11.2/jquery-min",
    mouse_core:'../libs/jquery/ui/mouse/jquery-ui-1.10.3.mouse_core',
    touch_punch:'../libs/jquery/ui/touchpunch/jquery.ui.touch-punch',
    bootstrap:"../libs/bootstrap/3.3.2/js/bootstrap-min",
    bootstrap_colorpicker:"../libs/bootstrap/colorpicker/js/bootstrap-colorpicker",
    bootstrap_datetimepicker:"../libs/bootstrap/datetimepicker/js/bootstrap-datetimepicker.min",
    moment:"../libs/bootstrap/datetimepicker/js/moment",
    bootstrapslider:"../libs/bootstrap/slider/bootstrapslider",
    underscore:"../libs/underscore/1.7.0/underscore",
    backbone:"../libs/backbone/1.1.2/backbone",
    text:"../libs/require/2.1.15/text"
  },
  config:{
	    'bootstrapslider':{'jquery':'jquery',
	                       'mouse_core':'mouse_core',
	                       'touch_punch':'bootstrapslider'}
	    }
});

require([
         // Load our app module and pass it to our definition function
         'app',
       ], function(App){
         // The "app" dependency is passed in as "App"
         App.initialize();
       });
