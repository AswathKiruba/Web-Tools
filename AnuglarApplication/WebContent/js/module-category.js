/**
 * 
 */
var categoryModule = angular.module('categoryModule');
categoryModule.controller('categoryContoller', function($scope,categoryService,$rootScope,categoryService1,
		categoryService2,categoryService3,categoryService4,categoryService5) {
	var categoryCtrl = this;
	$scope.message = "";
	categoryCtrl.message="";
	categoryCtrl.checkmessage="";
	
	categoryCtrl.categoryName = '';
	
	categoryCtrl.deleted =function(category){
		
		console.log(category);
		console.log(category.categoryName);
		categoryService5.check(category.categoryName,
				function(response) {
			console.log('delete returns success message');
	        
			
			
			categoryCtrl.deletemessage =  "This Category is deleted";
			categoryCtrl.init();
			
			
		},
		function(reponseData) {
			if("no" === reponseData.message){
				categoryCtrl.message = reponseData;
		categoryCtrl.disablemessage =  "Category is not found";
			}
		}
		);
	}
	
categoryCtrl.active = function(category){
		
		console.log(category);
		console.log(category.categoryName);
		categoryService4.check(category.categoryName,
				function(response) {
			console.log('active returns success message');
	        
			
			
			categoryCtrl.activemessage =  "This Category is activated";
			
			
		},
		function(reponseData) {
			if("no" === reponseData.message){
				categoryCtrl.message = reponseData;
		categoryCtrl.disablemessage =  "Category is not found";
			}
		}
		);
	}
	
	
	categoryCtrl.disable = function(category){
		
		console.log(category);
		console.log(category.categoryName);
		categoryService3.check(category.categoryName,
				function(response) {
			console.log('disable returns success message');
	        
			
			
			categoryCtrl.disablemessage =  "This Category is disabled";
			
			
		},
		function(reponseData) {
			if("no" === reponseData.message){
				categoryCtrl.message = reponseData;
		categoryCtrl.disablemessage =  "Category is not found";
			}
		}
		);
	}
	
	

categoryCtrl.check = function(){
	
	console.log('check is started');
	categoryService2.check(categoryCtrl.categoryName,
			function(response) {
		console.log('it returns success message');
        
		if("yes" === response.message){
		
		categoryCtrl.checkmessage =  "Category already exists";
		console.log(response);}
		
		if("no" === response.message){
			
			
			console.log(response);
			categoryCtrl.addCategory();
		}
		
	},
	function(reponseData) {
		if("no" === reponseData.message){
			categoryCtrl.addmessage = reponseData;
	categoryCtrl.checkmessage =  "Category is not found";
		}
	}
	);
}





	
	
	
	
//Add category	categoryservice1
	categoryCtrl.addCategory = function(){
		
		categoryService1.addCategory(categoryCtrl.categoryName,callbackSuccess,callbackError);
	};
	var callbackSuccess = function(data,headers) {
        
		categoryCtrl.message =  "Category is added";
		categoryCtrl.init();
};

var callbackError = function(data) {
	
		authCtrl.message = data;
	
};


//////
/// init mehtod for populating data in db
// categroyservice
	categoryCtrl.init = function(){
		
		console.log('category init called')
		categoryService.listCategories(null,
		function(response) {
			$scope.message = "Success";
    		$scope.error = false;
			console.log(response);
			categoryCtrl.categories = response;
		},
		function(reponseData) {
			$scope.message = "Failed to load data";
    		$scope.error = true;   
		}
		);
	}
	
	
});

// category service
categoryModule.factory('categoryService', function($http,$timeout,APP_CONSTANT) {
	var categoryService = {};
	
	categoryService.listCategories = function (data,callback,callbackError) {
	
	if(!APP_CONSTANT.DEMO){
	
			 $http.get(
         			APP_CONSTANT.REMOTE_HOST+'/user/category'
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
	
	
	return categoryService;
});

//category service 1
categoryModule.factory('categoryService1', function($http,$timeout,APP_CONSTANT) {
	var categoryService1 = {};
	
	categoryService1.addCategory = function (data,callback,callbackError) {
	
	if(!APP_CONSTANT.DEMO){

	
			 $http.post(
         			APP_CONSTANT.REMOTE_HOST+'/user/category/add/'+data
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
	
	
	return categoryService1;
});

categoryModule.factory('categoryService2', function($http,$timeout,APP_CONSTANT) {
	var categoryService2 = {};
	
	categoryService2.check= function (data,callback,callbackError) {
		console.log('check service message');
	
	if(!APP_CONSTANT.DEMO){

	
			 $http.post(
         			APP_CONSTANT.REMOTE_HOST+'/user/category/add/'+data+'/check'
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
	
	
	return categoryService2;
});



categoryModule.factory('categoryService3', function($http,$timeout,APP_CONSTANT) {
	var categoryService3 = {};
	
	categoryService3.check= function (data,callback,callbackError) {
		console.log('check service message');
	
	if(!APP_CONSTANT.DEMO){

	
			 $http.post(
         			APP_CONSTANT.REMOTE_HOST+'/user/category/add/'+data+'/check/disable'
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
	
	
	return categoryService3;
});


categoryModule.factory('categoryService4', function($http,$timeout,APP_CONSTANT) {
	var categoryService4 = {};
	
	categoryService4.check= function (data,callback,callbackError) {
		console.log('check service message');
	
	if(!APP_CONSTANT.DEMO){

	
			 $http.post(
         			APP_CONSTANT.REMOTE_HOST+'/user/category/add/'+data+'/check/activate'
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
	
	
	return categoryService4;
});

categoryModule.factory('categoryService5', function($http,$timeout,APP_CONSTANT) {
	var categoryService5 = {};
	
	categoryService5.check= function (data,callback,callbackError) {
		console.log('check service message');
	
	if(!APP_CONSTANT.DEMO){

	
			 $http.post(
         			APP_CONSTANT.REMOTE_HOST+'/user/category/add/'+data+'/check/delete'
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
	
	
	return categoryService5;
});