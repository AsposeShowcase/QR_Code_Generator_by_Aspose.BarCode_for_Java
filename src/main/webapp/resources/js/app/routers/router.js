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
  'jquery',
  'underscore',
  'backbone',
  'views/types'
], function ($, _, Backbone, TypesView) { 
  /**
   * Url router for the applications. Defines routes with url and handlers
   */
  var AppRouter  = Backbone.Router.extend({
    // List all the routes possibles and bind them to a handler
    routes:{
      'type/:page':'types',
    	// Default
       '*actions': 'defaultAction'
 }

  });

  var initialize = function(){
	    var app_router = new AppRouter;
	    var typesView = new TypesView();
	    
	    app_router.on('route:types', function(page){
	      // Call render on the module we loaded in via the dependency array
	      // 'views/projects/list'
	      console.log("router types ", page);
	      typesView.render({
	    		  page : page 
	      	});
	    });
	      // As above, call render on our loaded module
	      // 'views/users/list'
	
	    app_router.on('route:defaultAction', function(actions){
	      // We have no matching route, lets just log what the URL was
	    	app_router.navigate('/type/url', {
				trigger : true
			});
	      console.log('No route:', actions);
	    });
	    Backbone.history.start();
	  };
	  return {
	    initialize: initialize
	  };
	});