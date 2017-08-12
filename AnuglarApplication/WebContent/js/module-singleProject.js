/**
 * 
 */
var singleProjectModule = angular.module('singleProjectModule');
singleProjectModule.controller('singleController', function($scope,$rootScope,singleService,singleService1,$location) {
	
	var singleCtrl = this;
	$scope.message = "This is single";
	$scope.data=$rootScope.categoryProject;
	singleCtrl.datum=$rootScope.categoryProject;
	
	
	singleCtrl.donate= function (data)  {
		console.log("donate called");
		console.log(data);
		

		
		$rootScope.category=data;
		
		$location.path('/donate');

	};
	
	//console.log(singleCtrl.dataum.projectID);
	//console.log(data.projectID);
	singleCtrl.cancel = function() {
		
		$location.path('/project/viewprojects');
	}
	
	singleCtrl.init = function(data){
		
		console.log('init called')
		console.log(data.projectID);
		singleService.listMessage(data.projectID,
				
		function(data) {
			console.log('success is called')
			$scope.message = "Success";
    		$scope.error = false;
			console.log(data);
			singleCtrl.message = data;
			$scope.datum = data;
			
			
		},
		function(reponseData) {
			$scope.message = "Failed to load data";
    		$scope.error = true;   
		}
		);
	}
	
	
	
	singleCtrl.send = function(data){
		singleCtrl.text ={
				message:singleCtrl.messageText,
				username:$rootScope.userSession.name
		}
		console.log(data.projectID);
		console.log(singleCtrl.messageText);
		console.log($rootScope.userSession.name);
		console.log(singleCtrl.text);
		
		singleService1.addMessage(singleCtrl.text,data.projectID,callbackSuccess,callbackError);
	};
	var callbackSuccess = function(data,headers) {
        
		singleCtrl.successmessage =  "Comment is successfully added";
		singleCtrl.init(data);
};

var callbackError = function(data) {
	
		authCtrl.message = data;
	
};



});

homeModule.factory('singleService', function($http,$timeout,APP_CONSTANT) {
	var singleService = {};
	//drawChart();
	singleService.listMessage = function (data,callback,callbackError) {
	
	if(!APP_CONSTANT.DEMO){
	
		
			 $http.get(
	         			APP_CONSTANT.REMOTE_HOST+'/project/message/'+data
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
	
	
	return singleService;
});

homeModule.factory('singleService1', function($http,$timeout,APP_CONSTANT) {
	var singleService1 = {};
	//drawChart();
	singleService1.addMessage = function (data,data1,callback,callbackError) {
	
	if(!APP_CONSTANT.DEMO){
	
		
			 $http.post(
	         			APP_CONSTANT.REMOTE_HOST+'/project/message/add/'+data1,data
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
	
	
	return singleService1;
});