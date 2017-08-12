
/**
 * 
 */
'use strict';
// Step 1: declare modules
 angular.module("authModule",[]);
 angular.module("dashboardModule",[]);
 angular.module("personalModule",[]);
 angular.module("categoryModule",[]);
 angular.module("projectModule",[]);
 angular.module("homeModule",[]);
 angular.module("registrationModule",[]);
 angular.module("createProjectModule",[]);
 angular.module("ViewProjectModule",[]);
 angular.module("singleProjectModule",[]);
 angular.module("donateModule",[]);
 angular.module("cartModule",[]);
 angular.module("chatModule",[]);

 



 angular.module('appCoreModule', [
	 'ngRoute',
     'ngCookies'
 ]);
//Step 2: Register App
var app = angular.module("app", 
		[
		'appCoreModule',
		 'homeModule',
		 'authModule',
		 'dashboardModule',
		 'personalModule',
		 'categoryModule',
		 'projectModule',
		 'registrationModule',
		 'createProjectModule',
		 'ViewProjectModule',
		 'singleProjectModule',
		 'donateModule',
		 'cartModule',
		 'chatModule'
		 ]);