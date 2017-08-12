/**
 * 
 */
 
var projectModule = angular.module('projectModule');
projectModule.controller('projectController', function($scope,$rootScope,$location) {
	$scope.data = $rootScope.project;
	
	var projectCtrl = this;
	
	projectCtrl.cancel = function() {
		
		$location.path('/dashboard');
	}
});