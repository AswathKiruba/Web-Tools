/**
 * 
 */
var chatModule = angular.module('chatModule');
chatModule.controller('chatController', function($scope, $rootScope,$location,chatService,chatService1,
		chatService2,chatService3) {
	$scope.message ="This is chay ";
	
	var chatCtrl = this;
	
	
	chatCtrl.init = function(){
		
		console.log('init called')
		chatService.listUser($rootScope.userSession.id,
				
		function(data) {
			$scope.message = "Success";
    		$scope.error = false;
			console.log(data);
			chatCtrl.Users = data;
		},
		function(data) {
			if("no" === data.message){
				chatCtrl.TextMessage="No messages to display"
			}
		}
		);
	}
	
	
chatCtrl.init1 = function(){
		
		console.log('init called')
		chatService1.listMessageReceived($rootScope.userSession.id,
				
		function(data) {
			$scope.message = "Success";
    		$scope.error = false;
			console.log(data);
			chatCtrl.received = data;
		},
		function(data) {
			if("no" === data.message){
				chatCtrl.receivedMessage="No messages recived"
			}
		}
		);
	}


chatCtrl.init2 = function(){
		
		console.log('init called')
		chatService2.listMessageSent($rootScope.userSession.id,
				
		function(data) {
			$scope.message = "Success";
    		$scope.error = false;
			console.log(data);
			chatCtrl.sent = data;
		},
		function(data) {
			if("no" === data.message){
				chatCtrl.receivedMessage="No messages recived"
			}
		}
		);
	}

chatCtrl.messageText ="";


chatCtrl.send = function(chat) {
	console.log("add function called");
	console.log($rootScope.userSession.id);
	console.log(chatCtrl.messageText);
	console.log($rootScope.userSession.name);
	console.log(chat);
	
	chatService3.create($rootScope.userSession.id,$rootScope.userSession.name,chatCtrl.messageText,chat,callbackSuccess,
		callbackError);

}

var callbackSuccess = function(data, headers) { // Status
	
	chatCtrl.message = "The message has been successfully sent";
	chatCtrl.init2();
	chatCtrl.init1();
};

var callbackError = function(data, headers) {
	chatCtrl.message = data.message;
	chatCtrl.error = true;

};
	
	
});

chatModule.factory('chatService', function($http,$timeout,APP_CONSTANT) {
	var chatService = {};
	
	chatService.listUser = function (data,callback,callbackError) {
	
	if(!APP_CONSTANT.DEMO){
		
			 $http.post(
         			APP_CONSTANT.REMOTE_HOST+'/chat/user/'+data
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
	
	
	return chatService;
});


chatModule.factory('chatService1', function($http,$timeout,APP_CONSTANT) {
	var chatService1 = {};
	
	chatService1.listMessageReceived = function (data,callback,callbackError) {
	
	if(!APP_CONSTANT.DEMO){
		
			 $http.post(
         			APP_CONSTANT.REMOTE_HOST+'/chat/user/received/'+data
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
	
	
	return chatService1;
});


chatModule.factory('chatService2', function($http,$timeout,APP_CONSTANT) {
	var chatService2 = {};
	
	chatService2.listMessageSent = function (data,callback,callbackError) {
	
	if(!APP_CONSTANT.DEMO){
		
			 $http.post(
         			APP_CONSTANT.REMOTE_HOST+'/chat/user/sent/'+data
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
	
	
	return chatService2;
});


chatModule.factory('chatService3', function($http,$timeout,APP_CONSTANT) {
	var chatService3 = {};
	
	chatService3.create = function (id,name,msg,data,callback,callbackError) {
	
	if(!APP_CONSTANT.DEMO){
		
			 $http.post(
         			APP_CONSTANT.REMOTE_HOST+'/chat/user/put/'+id+'/'+name+'/'+msg,data
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
	
	
	return chatService3;
});