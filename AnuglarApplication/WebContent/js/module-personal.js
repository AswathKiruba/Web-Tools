/**
 * 
 */
var personalModule = angular.module('personalModule');
personalModule.controller('personalContoller', function($scope,personalService,$rootScope,
		$location,personalService1,personalService2,personalService3) {
	$scope.message = "This is personal";
	var personalCtrl= this;
	
	personalCtrl.init = function(){
		
		console.log('init called')
		personalService.listProject(
				
		function(data) {
			
			
			$scope.message = "Success";
    		$scope.error = false;
			console.log(data);
			personalCtrl.projects = data;
			$scope.datum = data;
			
			
		},
		function(reponseData) {
			console.log('getting into error part')
			if("no" === reponseData.message){
				personalCtrl.addmessage1 = reponseData;
				personalCtrl.checkmessage1 =  "No project to display";
			}
			  
		}
		);
	}
	
personalCtrl.init1 = function(){
		
		console.log('init called')
		personalService2.listcity(
				
		function(data) {
			
			
			$scope.message = "Success";
    		$scope.error = false;
			console.log(data);
			personalCtrl.city = data;
			$scope.datum = data;
			
			
		},
		function(reponseData) {
			console.log('getting into error part')
			if("no" === reponseData.message){
				personalCtrl.addmessage1 = reponseData;
				personalCtrl.checkmessage1 =  "No city to display";
			}
			  
		}
		);
	}

personalCtrl.init2 = function(){
	
	console.log('init2 called')
	personalService3.liststat(
			
	function(data) {
		
		
		$scope.message = "Success";
		$scope.error = false;
		console.log(data);
		personalCtrl.stat = data;
		$scope.stat= data;
		$scope.datum = data;
		
		
	},
	function(reponseData) {
		console.log('getting into error part')
		if("no" === reponseData.message){
			personalCtrl.addmessage1 = reponseData;
			personalCtrl.checkmessage1 =  "No city to display";
		}
		  
	}
	);
}


	personalCtrl.viewProject = function (row) {
		console.log(row);
		$rootScope.project = row;
		console.log($rootScope.project);
		$location.path('/project/view');

	};
	
	personalCtrl.disable = function(project){
		
		console.log(project);
		console.log(project.projectID);
		personalService1.disableProject(project.projectID,
				function(response) {
			console.log('disable returns success message');
	        
			
			
			personalCtrl.disablemessage =  "This Project is disabled";
			
			
		},
		function(reponseData) {
			if("no" === reponseData.message){
				personalCtrl.message = reponseData;
				personalCtrl.disablemessage =  "Project is not found";
			}
		}
		);
	}

});


personalModule.factory('personalService', function($http,$timeout,APP_CONSTANT) {
	var personalService = {};
	
	personalService.listProject = function (callback,callbackError) {
	
	if(!APP_CONSTANT.DEMO){
	
			 $http.get(
         			APP_CONSTANT.REMOTE_HOST+'/admin/project/'
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
	
	return personalService;
});

personalModule.factory('personalService1', function($http,$timeout,APP_CONSTANT) {
	var personalService1 = {};
	
	personalService1.disableProject= function (data,callback,callbackError) {
		console.log('check service message');
	
	if(!APP_CONSTANT.DEMO){

	
			 $http.post(
         			APP_CONSTANT.REMOTE_HOST+'/admin/project/disable/'+data
         			).success(function (data, status, headers, config) {
    					callback(data,headers);
    					console.log('success is returned success message');
        			})
        			.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
        					if(status== 422){
        						callbackError(data);
        					}
        			});
			
		}
	};
	
	
	return personalService1;
});

personalModule.factory('personalService2', function($http,$timeout,APP_CONSTANT) {
	var personalService2 = {};
	
	personalService2.listcity= function (callback,callbackError) {
		console.log('check service message');
	
	if(!APP_CONSTANT.DEMO){

	
			 $http.get(
         			APP_CONSTANT.REMOTE_HOST+'/admin/city/'
         			).success(function (data, status, headers, config) {
    					callback(data,headers);
    					console.log('success is returned success message');
        			})
        			.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
        					if(status== 422){
        						callbackError(data);
        					}
        			});
			
		}
	};
	
	
	return personalService2;
});

personalModule.factory('personalService3', function($http,$timeout,APP_CONSTANT) {
	var personalService3 = {};
	
	personalService3.liststat= function (callback,callbackError) {
		console.log('check service message');
	
	if(!APP_CONSTANT.DEMO){

	
			 $http.get(
         			APP_CONSTANT.REMOTE_HOST+'/admin/stat/'
         			).success(function (data, status, headers, config) {
    					callback(data,headers);
    					console.log('success is returned success message');
        			})
        			.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
        					if(status== 422){
        						callbackError(data);
        					}
        			});
			
		}
	};
	
	
	return personalService3;
});
