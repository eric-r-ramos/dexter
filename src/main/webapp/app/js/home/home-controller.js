angular.module('dexter').controller('HomeController', function($scope, $http, $rootScope, $location){
	$scope.logo = {
		title : 'Lion', 
		url : '/content/images/logo.gif'
	}
	
	$scope.home = {
		welcome : 'ING ATM Search'
	}
	
	$rootScope.exception.code = '';
	$rootScope.exception.msg = '';
	
	

	$scope.filterCity= '';
	$scope.selectedCity= '';
    $scope.cities = [];
    $scope.info = '';
    $scope.error = {
    	code : '', 
    	msg : '' 
    }

    $http.get('/api/city')
    .success(function(retorno) {
        console.log(retorno);
        $scope.cities = retorno; 
    })
    .error(function(erro) {
        console.log(erro);
        $scope.error = 'Oops... Something got wrong.' + erro; 
    });
    
    $scope.showTable = false;
    $scope.atms = [];
    $scope.search = function(){
    	
        $http.get('/api/atm/city/'+ $scope.selectedCity)
        .success(function(retorno) {
            $scope.atms = retorno; 
            $scope.showTable = $scope.atms.length > 0;
        })
        .error(function(exception) {
            console.log(exception);
            $scope.error.code = exception.status;
            
            $scope.error.msg = 'Oops... Something got wrong.' + 
            	'<br>Error: '+ exception.status + ' - ' + exception.error  ;
        });
    };
    
})
