/**
 * 
 */
var ViewProjectModule = angular.module('ViewProjectModule');
ViewProjectModule.controller('ViewProjectController', function($scope,$location,$rootScope,projectService,$cookieStore,
		projectService1,projectService2,projectService3) {
	$scope.message = "This is project";
	var viewCtrl = this;
	
	$scope.sortData="-endDate";
	
	
	
	
	$scope.selectedData = $rootScope.category;
	console.log('ID-->',$rootScope.globals);
	console.log($scope.selectedData);
	var dateu = $rootScope.category;
	
	//$cookieStore.put('category', $rootScope.category);
	
	
	viewCtrl.like =function(project){
		console.log('like is staring')
		console.log(project);
		console.log(project.projectID);
		
		viewCtrl.data ={
				liked:project.liked,
				hate:project.hate
		}
		
		console.log(viewCtrl.data);
		projectService1.like(project.projectID,viewCtrl.data,
				function(response) {
			console.log('like returns success message');
	        viewCtrl.likemessage =  "This is liked";
			viewCtrl.init();
			
			
		},
		function(reponseData) {
			if("no" === reponseData.message){
				viewCtrl.message = reponseData;
				viewCtrl.disablemessage =  "";
			}
		}
		);
	}
	
	viewCtrl.dislike =function(project){
		console.log('dislike is staring')
		console.log(project);
		console.log(project.projectID);
		
		viewCtrl.data ={
				liked:project.liked,
				hate:project.hate
		}
		
		console.log(viewCtrl.data);
		projectService2.dislike(project.projectID,viewCtrl.data,
				function(response) {
			console.log('dislike returns success message');
	        viewCtrl.dislikemessage =  "This is liked";
			viewCtrl.init();
			
			
		},
		function(reponseData) {
			if("no" === reponseData.message){
				viewCtrl.message = reponseData;
				viewCtrl.disablemessage =  "";
			}
		}
		);
	}
	
	viewCtrl.viewProject = function (project) {
		console.log("view project");
		console.log(project);

		$rootScope.categoryProject=project;
		
		$rootScope.cat="Please login to continue";
		
		$location.path('/project/viewSingleproject');

	};
	
	
	viewCtrl.addCart = function (project) {
		console.log("add to cart project");
		console.log(project);
		console.log(project.projectID);

		projectService3.cart(project.projectID,
				
				function(data) {
					
					
					$scope.message = "Success";
		    		$scope.error = false;
					console.log(data);
					viewCtrl.cart = "Project added to cart";
					$scope.datum = data;
					
					
				},
				function(reponseData) {
					console.log('getting into error part')
					if("no" === reponseData.message){
					viewCtrl.addmessage1 = reponseData;
					viewCtrl.checkmessage1 =  "No project to display";
					}
					  
				}
				);
			}
		

	
	
	
	
	viewCtrl.init = function(){
		
		console.log('init called')
		projectService.listProject(dateu,null,
				
		function(data) {
			
			
			$scope.message = "Success";
    		$scope.error = false;
			console.log(data);
			viewCtrl.projects = data;
			$scope.datum = data;
			
			
		},
		function(reponseData) {
			console.log('getting into error part')
			if("no" === reponseData.message){
			viewCtrl.addmessage1 = reponseData;
			viewCtrl.checkmessage1 =  "No project to display";
			}
			  
		}
		);
	}
	

});

ViewProjectModule.factory('projectService', function($http,$timeout,APP_CONSTANT) {
	var projectService = {};
	
	projectService.listProject = function (data,data1, callback,callbackError) {
	
	if(!APP_CONSTANT.DEMO){
	
			 $http.post(
         			APP_CONSTANT.REMOTE_HOST+'/user/project/'+data
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
	
	
	return projectService;
});


ViewProjectModule.factory('projectService1', function($http,$timeout,APP_CONSTANT) {
	var projectService1 = {};
	
	projectService1.like = function (data,data1,callback,callbackError) {
	
	if(!APP_CONSTANT.DEMO){
	
			 $http.post(
         			APP_CONSTANT.REMOTE_HOST+'/user/liked/'+data,data1
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
	
	
	return projectService1;
});

ViewProjectModule.factory('projectService2', function($http,$timeout,APP_CONSTANT) {
	var projectService2 = {};
	
	projectService2.dislike = function (data,data1,callback,callbackError) {
	
	if(!APP_CONSTANT.DEMO){
	
			 $http.post(
         			APP_CONSTANT.REMOTE_HOST+'/user/dislike/'+data,data1
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
	
	
	return projectService2;
});

ViewProjectModule.factory('projectService3', function($http,$timeout,APP_CONSTANT) {
	var projectService3 = {};
	
	projectService3.cart = function (data,callback,callbackError) {
	
	if(!APP_CONSTANT.DEMO){
	
			 $http.post(
         			APP_CONSTANT.REMOTE_HOST+'/user/cart/'+data
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
	
	
	return projectService3;
});

