

var app = angular.module('app', []);

app.controller('MessageCtrl', 
	
	function ($scope, $http) {
		
		$scope.isLoggedIn = UserUtil.isUserLoggedIn();
		$scope.adminToolsEnabled = UserUtil.hasUserRole(UserRole.ADMIN);
	
	    $http.get(httpGetAllMessages).
	    
	    success(function(data) {
	    	$scope.messages = data;
	    }).
	    
	    error(function(data, status, headers, config) {
	    	$scope.errorMsg = "Couldn't access Message Service";
	    	$scope.errorStatus = status;	
	    	$scope.hasError = true;
	    	
	    	// DEBUG
	    	//$scope.hasError = false;
	    	//$scope.adminToolsEnabled = true;
	    	//$scope.messages = [{"id":1,"caption":"Caption 1","text":"Message 1"},{"id":2,"caption":"Caption 2","text":"Message 2"}];
	    });
		
	    $scope.logout = function() {
	        $http.get(httpLogout).
	        
	        success(function(data, status, headers, config) {
		        document.location.reload(true);
		        UserUtil.removeUserDetails();
	        });
	    };
		
		$scope.edit = function(message)  {
			message.isEditMode = true;
			message.old = angular.copy(message);
		};
		
		$scope.remove = function(message) {	
			var index = $scope.messages.indexOf(message);
			$scope.messages.splice(index,1);
			
			if(null != message) {
				$http.post(httpDeleteMessages, message);
			}
		};
		
		$scope.save = function(message)  {
			resetMessage(message);
			$http.post(httpSaveMessages, message).
			
	        success(function(data, status, headers, config) {
	        	message.id = data.id;
	        });
		};
		
		$scope.cancel = function(message)  {
			if(null == message.id) {
				$scope.remove(message);
			} else {
				angular.copy(message.old, message);
				resetMessage(message);
			}
		};
		
		$scope.add = function()  {
			$http.get(httpCreateMessage).
			
	        success(function(data, status, headers, config) {
	        	data.isEditMode = true;
	        	$scope.messages.push(data);  	
	        });
		};
		
		function resetMessage(message) {
			delete message.isEditMode;
			delete message.old;
		}
	}
);