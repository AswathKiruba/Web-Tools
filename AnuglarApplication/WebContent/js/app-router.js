/**
 * 
 */
var configModule = angular.module('app'); // Please dont not use [], dependency 


configModule.config(function($routeProvider) {	
//    $urlRouterProvider.otherwise('/login');
	$routeProvider
    // route for the home page
	.when('/home', {
		 templateUrl : 'partial/home.html',
	     controller  : 'homeController',
	     controllerAs: 'homeCtrl'
	})
	.when('/chat', {
		 templateUrl : 'partial/chat.html',
	     controller  : 'chatController',
	     controllerAs: 'chatCtrl'
	})
	.when('/createProjects', {
		 templateUrl : 'partial/CreateProjects.html',
	     controller  : 'createController',
	     controllerAs: 'createCtrl'
	})
	.when('/project/viewprojects', {
		 templateUrl : 'partial/ViewProjects.html',
	     controller  : 'ViewProjectController',
	     controllerAs: 'viewCtrl'
	})
	.when('/donate', {
		 templateUrl : 'partial/Donate.html',
	     controller  : 'DonateController',
	     controllerAs: 'donateCtrl'
	})
	
	.when('/project/viewSingleproject', {
		 templateUrl : 'partial/SingleProject.html',
	     controller  : 'singleController',
	     controllerAs: 'singleCtrl'
	})
	.when('/login', {
		 templateUrl : 'partial/login.html',
	     controller  : 'authController',
	     controllerAs: 'authCtrl'
	})
	.when('/registration', {
		 templateUrl : 'partial/registration.html',
	     controller  : 'registrationController',
	     controllerAs: 'regCtrl'
	})
	.when('/logout', {
		template :"",
		controller  : 'logoutController',
		redirectTo: '/' 
	})
	.when('/mymission', {
		templateUrl : 'partial/cart.html',
		controller  : 'cartController',
		controllerAs: 'cartCtrl'
	})
	
	
    .when('/dashboard', {
        templateUrl : 'partial/dashboard.html',
        controller  : 'dashboardController',
        controllerAs: 'dashboardCtrl'
    })
    .when('/project/view', {
        templateUrl : 'partial/project.html',
        controller  : 'projectController',
        controllerAs: 'projectCtrl'
    })
    .when('/category', {
        templateUrl : 'partial/category.html',
        controller  : 'categoryContoller',
        controllerAs: 'categoryCtrl'
    })
    .when('/personal', {
        templateUrl : 'partial/personal.html',
        controller  : 'personalContoller',
        controllerAs: 'personalCtrl'
    })
    .otherwise({ redirectTo: '/home' });
});


configModule.run(
	    function ($rootScope, $location, $cookieStore,$window, $http,AUTH_EVENTS) {
	    	//Management 
	    	$rootScope.$on('$locationChangeStart', function (event, next, current) {
	            // redirect to login page if not logged in
	    		console.log(' Requested path '+$location.path());
	            if ( 
	            		!(
	            				$location.path() == '/home' || 
	            				$location.path() == '/registration'|| 
	            				$location.path() == '/login' ||
	            				$location.path() == '/mymission'||
	            				$location.path() == '/project/viewprojects'||
	            				
	            				$location.path() ==	'/project/view'
	            		 ) && 
	            		 !$rootScope.globals.userSession) {
	            	console.log('Invalid failed');
	                $location.path('/home');
	            }
	        });
	    	
			$rootScope.$on(AUTH_EVENTS.loginFailed, function(event, next){
		    		console.log('Login failed');
		        	 //$scope.message = "Login failed";
		    });
		
			$rootScope.$on(AUTH_EVENTS.logoutSuccess, function(event, next){
				console.log('Logout Success');
				$window.localStorage.removeItem("globals");
				$rootScope.userSession=null;
				$rootScope.$emit("CallParentMethod", {});
				
			});
			
			$rootScope.$on(AUTH_EVENTS.notAuthorized, function(event, next){
				console.log('notAuthorized');
				$window.localStorage.removeItem("globals");
				$rootScope.userSession=null;
				$rootScope.$emit("CallParentMethod", {});
				
			});
	    
		    $rootScope.$on(AUTH_EVENTS.loginSuccess, function(event, next){
				//$scope.message = "Login Success";
				console.log('Login success');
			    $window.localStorage.setItem("globals", angular.toJson($rootScope.globals));
			    //$rootScope.userSession=angular.toJson($rootScope.globals.userSession)
			    $rootScope.userSession = JSON.parse($window.localStorage.getItem("globals")).userSession;
			  
			    $rootScope.$emit("CallParentMethod", {});
				$location.path('/dashboard');
		    });
	    
		    // keep user logged in after page refresh
		    $rootScope.globals = $cookieStore.get('globals') || {};
		    if ($rootScope.globals.userSession) {
			    $window.localStorage.setItem("globals", angular.toJson($rootScope.globals));
			    $rootScope.userSession = JSON.parse($window.localStorage.getItem("globals")).userSession;
		    }

	})