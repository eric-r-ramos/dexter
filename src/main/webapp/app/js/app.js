angular.module('dexter',['ngRoute'])
.config(function($routeProvider, $locationProvider, $httpProvider) {
	$locationProvider.html5Mode(true);
	
	

	
	$routeProvider.when('/', {
        templateUrl: 'app/js/home/home.html',
        controller: 'HomeController'
    }).when('/login', {
        templateUrl: 'app/js/home/home.html',
        controller: 'HomeController'
    });
    
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    $httpProvider.interceptors.push(function HttpInterceptor($q, $location, $rootScope ) {
        var service = {
            responseError: responseError
        };

        $rootScope.exception = {
        	code : '',
        	msg : ''
        }

        return service;

        function responseError(rejection) {
        	console.log(rejection);
        	$rootScope.exception.code = rejection.status;
        	$rootScope.exception.msg = 'Ooops ... something got wrong'
        	
        	
            if (rejection.status === 404) {
                $location.path('/error');
                return $q(function () { return null; })
            }
            
            if (rejection.status === 401) {
            	//give a expecific msg to the user
            	$rootScope.exception.msg = 'Sorry... this is a restrict service. You must to be logged to use it!'
                return $q(function () { return null; })
            }
            
            
            return $q.reject(rejection);
        }
    });
    

    
    
})
;