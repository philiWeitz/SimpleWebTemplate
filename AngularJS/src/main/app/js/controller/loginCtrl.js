

var MessageApp = angular.module('MessageApp', []);

MessageApp.controller('LoginCtrl', 
		
	function ($scope, $http)
	{
		$scope.isLoggedIn = UserUtil.isUserLoggedIn();
		
		if($scope.isLoggedIn) {
			$scope.name = UserUtil.getUserName();
		}
		
		// ------ login form 
	    $scope.login = function() {
	        $http.post(httpLogin, {'name': $scope.name, 'password': $scope.password}).
	               
	        success(function(data, status, headers, config) {
	        	UserUtil.storeUserDetails(data);
	        	window.location.href = urlMessagePage;
	        }).
	        
	        error(function(data, status) {
	        	$scope.hasError = true;    
	        });
	    }
	    
		// ------ logout form 
	    $scope.logout = function() {
	        $http.post(httpLogout, {});
	        UserUtil.removeUserDetails();
	        document.location.reload(true);      
	    };
	}
);