/**
 * 
 */
var donateModule = angular.module('donateModule');
donateModule.controller('DonateController', function($scope,$rootScope,donateService) {
	$scope.message = "This is donate";
	 var donateCtrl = this;
	 
	 $scope.data=$rootScope.category;
	 
	 donateCtrl.id=$rootScope.globals.userSession.id;
	 donateCtrl.name=$rootScope.globals.userSession.name;
	 
	 donateCtrl.donate={
			 amount:"",
			 cardno:"",
			 cvv:"",
			 baddress:"",
			 city:""
			 
	 };
		
	 
	 donateCtrl.pay = function(datum){
		 console.log(datum);
		 console.log(donateCtrl.id);
		 console.log(donateCtrl.donate);
		 
		
			donateService.addAmount(datum,donateCtrl.id,donateCtrl.donate,callbackSuccess,callbackError);
		};
		var callbackSuccess = function(data,headers) {
	        
			donateCtrl.message =  "Amount paid successfully";
			
	};

	var callbackError = function(data) {
		
		donateCtrl.message = data;
		
	};
	

});

donateModule.factory('donateService', function($rootScope, $http,
		$timeout, $cookieStore, $window, APP_CONSTANT, AUTH_EVENTS) {
	var donateService = {};

	donateService.addAmount = function(pid,uid,data,callback, callbackError) {
		if (!APP_CONSTANT.DEMO) {

			$http.post(APP_CONSTANT.REMOTE_HOST +'/donate/project/'+pid+'/'+uid, data

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
	
	return donateService;

});