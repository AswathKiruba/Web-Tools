/**
 * 
 */
var configModule = angular.module('app'); // Please dont not use [],
											// dependency



configModule.controller("applicationContoller", function($rootScope, $scope,$window) {

	

	$scope.init = function () {
		$scope.parentmethod();
	}
	
	$rootScope.$on("CallParentMethod", function() {
		$scope.parentmethod();
	});

	$scope.parentmethod = function() {
		console.log('..parent..');
		var globals = JSON.parse($window.localStorage.getItem("globals"));
		console.log(globals);

	
		
	 	if(globals){
	 		console.log('globals exits');
	 		
	 	
	 		$scope.username = globals.userSession.name;
	 		
	 		console.log(globals);
	 		console.log(globals.userSession);
	 		if(globals.userSession.role === 'admin'){
	 			$('div#admin').show();
	 			$('div#guest').hide();
	 			$('div#Donor').hide();
	 			$('div#Creator').hide();

	 		}
	 		if(globals.userSession.role === 'Creator'){
	 			$('div#Creator').show();
	 			$('div#guest').hide();
	 			$('div#admin').hide();
	 			$('div#Donor').hide();

	 		}
	 		if(globals.userSession.role === 'Donor'){
	 			$('div#Donor').show();
	 			$('div#guest').hide();
	 			$('div#Creator').hide();
	 			$('div#admin').hide();
	 			

	 		}
	 		
	 	
	 		
	 	}else{
	 		console.log('globals does not exits');
	 		$('div#guest').show();
	 		$('div#admin').hide();
	 		$('div#logout').hide();
	 		$('div#Donor').hide();
	 		$('div#Creator').hide();
	 	}

	};
});


