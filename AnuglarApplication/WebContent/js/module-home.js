/**
 * 
 */

var homeModule = angular.module('homeModule'); // Please dont not use [],
// dependency

homeModule.controller("homeController", function($rootScope,$scope,$window,homeService,$location,
		homeService1,homeService2,homeService3) {
	console.log('home controller called')
	var homeCtrl = this;
	
	homeCtrl.viewProjectsp = function (row) {
		console.log(row);
		$rootScope.project = row.project;
		console.log($rootScope.project);
		$location.path('/project/view');

	};
	
	
	
	homeCtrl.init = function(){
		//drawChart();
		console.log('home init called')
		homeService.listCategories(null,
		function(response) {
			$scope.message = "Success";
    		$scope.error = false;
			console.log(response);
			homeCtrl.categories = response;
		},
		function(reponseData) {
			$scope.message = "Failed to load data";
    		$scope.error = true;   
		}
		);
	}
	
	homeCtrl.viewProjectTrend = function (project) {
		console.log("view project");
		console.log(project);

		$rootScope.categoryProject=project;
		
		$rootScope.cat="Please login to continue";
		
		$location.path('/project/viewSingleproject');

	};
	
	homeCtrl.init1 = function(){
		
		console.log('home init1 called')
		homeService1.listprojs(
		function(response) {
			$scope.message = "Success";
    		$scope.error = false;
			console.log(response);
			homeCtrl.projs = response;
		},
		function(reponseData) {
			$scope.message = "Failed to load data";
    		$scope.error = true;   
		}
		);
	}
	
homeCtrl.init2 = function(){
		
		console.log('home init2 called')
		homeService2.listproj(
		function(response) {
			$scope.message = "Success";
    		$scope.error = false;
			console.log(response);
			$scope.allProject = response;
		},
		function(reponseData) {
			$scope.message = "Failed to load data";
    		$scope.error = true;   
		}
		);
	}

homeCtrl.init3 = function(){
	
	console.log('category init called')
	homeService3.listCategories(
	function(response) {
		$scope.message = "Success";
		$scope.error = false;
		console.log(response);
		homeCtrl.categories = response;
	},
	function(reponseData) {
		$scope.message = "Failed to load data";
		$scope.error = true;   
	}
	);
}

	
	homeCtrl.viewProject = function (category) {
		console.log("view project based on category called");
		console.log(category);
		console.log(category.categoryName);
		
		console.log(category.categoryId);

		
		$rootScope.category=category.categoryName;
		
		$location.path('/project/viewprojects');

	};
	
});

homeModule.factory('homeService', function($http,$timeout,APP_CONSTANT) {
	var homeService = {};
	//drawChart();
	homeService.listCategories = function (data,callback,callbackError) {
	
	if(APP_CONSTANT.DEMO){
		$timeout(function(){
	         	
	     		var response;
	     		
	     			response = [
	     						{
	     							"id": 1,
	     							"name": "Children"
	     						},
	     						{
	     							"id": 2,
	     							"name": "Events"
     						}
	     					];
	     	
	
	         callback(response);
	     }, 1000);
		}else{
		
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
	
	
	return homeService;
});


homeModule.factory('homeService1', function($http,$timeout,APP_CONSTANT) {
	var homeService1 = {};
	//drawChart();
	homeService1.listprojs = function (callback,callbackError) {
	
	if(!APP_CONSTANT.DEMO){
	
	 $http.get(
	         			APP_CONSTANT.REMOTE_HOST+'/home/project'
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
	
	
	return homeService1;
});


homeModule.factory('homeService2', function($http,$timeout,APP_CONSTANT) {
	var homeService2 = {};
	//drawChart();
	homeService2.listproj = function (callback,callbackError) {
	
	if(!APP_CONSTANT.DEMO){
	
	 $http.get(
	         			APP_CONSTANT.REMOTE_HOST+'/home/project/all'
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
	
	
	return homeService2;
});

homeModule.factory('homeService3', function($http,$timeout,APP_CONSTANT) {
	var homeService3 = {};
	
	homeService3.listCategories = function (callback,callbackError) {
	
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
	
	
	return homeService3;
});


