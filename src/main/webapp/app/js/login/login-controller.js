angular.module('dexter').controller('LoginController',
	function($scope, $http, $rootScope, $location) {
		
		$rootScope.username = '';
		
		var self = $scope
		var authenticate = function(credentials, callback) {
			$rootScope.exception.code = '';
			$rootScope.exception.msg = '';
			
			var headers = credentials ? {
				authorization : "Basic "
						+ btoa(credentials.username + ":"
								+ credentials.password)
			} : {};

			$http.get('user', {
				headers : headers
			}).then(function(response) {
				if (response.data.name) {
					$rootScope.username = response.data.name;
					$rootScope.authenticated = true;
				} else {
					$rootScope.authenticated = false;
				}
				callback && callback();
			}, function(exception) {
				$rootScope.authenticated = false;
				callback && callback();

			});
		}


	  authenticate();
	  self.credentials = {};

	  $scope.login = function() {
		  authenticate(self.credentials, function() {
	        if ($rootScope.authenticated) {
	          $location.path("/");
	          self.error = false;
	        } else {
	          self.error = true;
	        }
	      });
	  };
	  
	  $scope.logout = function() {
		  	console.log('chamando /logout');
		  
	        $http.get('/logout')
	        .success(function(retorno) {
	        	self.credentials = {};
	        	$location.path("/");
	        }).error(function(exception) {
	            console.log(exception);
	            $scope.error.code = exception.status;
	            
	            $scope.error.msg = 'Oops... Something got wrong.' + 
	            	'<br>Error: '+ exception.status + ' - ' + exception.error  ;
	        });

	  };
	  

	  
	}

	

);
