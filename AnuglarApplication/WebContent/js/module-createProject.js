/**
 * 
 */
var createProjectModule = angular.module('createProjectModule');
createProjectModule.controller('createController', function($location,$scope,$rootScope,createService,createService1) {
	$scope.message = "This is create Projects";
	
	var createCtrl = this;
	
	createCtrl.init = function(){
		
		console.log('category init called')
		createService1.listCategories(null,
		function(response) {
			$scope.message = "Success";
    		$scope.error = false;
			console.log(response);
			createCtrl.categories = response;
		},
		function(reponseData) {
			$scope.message = "Failed to load data";
    		$scope.error = true;   
		}
		);
	}
	
	
	
	createCtrl.project={
			title :"",
			goal :"",
			description:"",
			category :"",
			endDate :"",
			shortdesc:"",
			service1:"",
			service2:"",
			service3:"",
			amount1:"",
			amount2:"",
			amount3:""
			
	};
	
	createCtrl.submit = function() {
		console.log("create project function called");
		console.log($rootScope.userSession.id)
		createService.create($rootScope.userSession.id,createCtrl.project, callbackSuccess,
				callbackError);

	}
	
	createCtrl.error = false;
	createCtrl.message = "";

	var callbackSuccess = function(data, headers) { // Status
		
		createCtrl.message = "The project has been successfully created";
			

		
		

	};

	var callbackError = function(data, headers) {
		createCtrl.message = data.message;
		createCtrl.error = true;

	};
	

});


createProjectModule.factory('createService', function($rootScope, $http,
		$timeout, $cookieStore, $window, APP_CONSTANT, AUTH_EVENTS) {
	var crtService = {};

	crtService.create = function(id,data, callback, callbackError) {
		console.log("Log for create project console called");
		if (APP_CONSTANT.DEMO) {

			/*
			 * Dummy authentication for testing, uses $timeout to simulate api
			 * call ----------------------------------------------
			 */
			$timeout(function() {

				var response;
				if (data.username === 'test' && data.password === 'test') {
					response = {
						success : true,
					};
				} else {
					response = {
						success: false,
						message : 'Registration was not successful'
					};
				}

				callback(response);
			}, 1000);
		} else {

			/*
			 * Use this for real authentication
			 * ----------------------------------------------
			 */
			$http.post(APP_CONSTANT.REMOTE_HOST +'/user/'+id+'/project/insert', data

			)
			// On Success of $http call
			.success(function(data, status, headers, config) {
				callback(data, headers);
			}).error(function(data, status, headers, config) { // IF
				// STATUS
				// CODE
				// NOT
				// 200
				callbackError(data, headers);
			});
		}

	};
	
	return crtService;

});

createProjectModule.factory('createService1', function($http,$timeout,APP_CONSTANT) {
	var createService1 = {};
	
	createService1.listCategories = function (data,callback,callbackError) {
	
	if(!APP_CONSTANT.DEMO){
	
			 $http.get(
					 APP_CONSTANT.REMOTE_HOST+'/user/category/check'
         			).success(function (data, status, headers, config) {
    					callback(data,headers);
        			})
        			.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
        					if(status== 422){
        						callbackError(data);
        					}
        			});
			
		}
	};
	
	
	return createService1;
});

