/**
 * 
 */
var cartModule = angular.module('cartModule');
cartModule.controller('cartController', function($scope,$location,$rootScope,cartService) {
	$scope.message= "this is cart page";
	var cartCtrl = this;
	
	cartCtrl.viewProject = function (project) {
		console.log("view project");
		console.log(project);

		$rootScope.categoryProject=project;
		
		$rootScope.cat="Please login to continue";
		
		$location.path('/project/viewSingleproject');

	};
	
	
	cartCtrl.init = function(){
		
		console.log('init called')
		cartService.listProject(
				
		function(data) {
			
			
			$scope.message = "Success";
    		$scope.error = false;
			console.log(data);
			cartCtrl.projects = data;
			$scope.datum = data;
			
			
		},
		function(reponseData) {
			console.log('getting into error part')
			if("no" === reponseData.message){
			cartCtrl.addmessage1 = reponseData;
			cartCtrl.checkmessage1 =  "No project to display";
			}
			  
		}
		);
	}


});

cartModule.factory('cartService', function($http,$timeout,APP_CONSTANT) {
	var cartService = {};
	
	cartService.listProject = function (callback,callbackError) {
	
	if(!APP_CONSTANT.DEMO){
	
			 $http.get(
         			APP_CONSTANT.REMOTE_HOST+'/user/project/cart/service'
         			).success(function (data, status, headers, config) {
    					callback(data);
        			})
        			.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
        					if(status== 422){
        						callbackError(data);
        					}
        			});
			
		}
	};
	
	
	return cartService;
});

