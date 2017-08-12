/**
 * 
 */
var dashboardModule = angular.module('dashboardModule');
dashboardModule.controller('dashboardController', function($scope, $rootScope,$location,dashboardService) {
	var dashboardCtrl = this;
	dashboardCtrl.messageDash = "Welcome " + $rootScope.userSession.name;
	

	
	dashboardCtrl.init = function(){
		
		console.log('init called')
		dashboardService.listProject($rootScope.userSession.id,
				
		function(data) {
			$scope.message = "Success";
    		$scope.error = false;
			console.log(data);
			dashboardCtrl.projects = data;
		},
		function(data) {
			if("no" === data.message){
			dashboardCtrl.TextMessage="No projects to display"
			}
		}
		);
	}
	
	dashboardCtrl.viewProject = function (row) {
		console.log(row);
		$rootScope.project = row.project;
		console.log($rootScope.project);
		$location.path('/project/view');

	};
	


});

dashboardModule.factory('dashboardService', function($http,$timeout,APP_CONSTANT) {
	var dashboardService = {};
	
	dashboardService.listProject = function (data,callback,callbackError) {
	
	if(APP_CONSTANT.DEMO){
//		$timeout(function(){
//	         	
//	     		var response;
//	     		
//	     			response = [
//	     						{
//	     							"name": "C",
//	     							"desc": null
//	     						},
//	     						{
//	     							"name": "C++",
//	     							"desc": null
//	     						}
//	     					];
//	     	
//	
//	         callback(response);
//	     }, 1000);
		}else{
//			
			 $http.get(
         			APP_CONSTANT.REMOTE_HOST+'/user/'+data+'/project'
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
	
	
	return dashboardService;
});
