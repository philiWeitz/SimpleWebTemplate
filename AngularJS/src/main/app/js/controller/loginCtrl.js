
//Services
var httpLogin = '/SimpleWebTemplateWebServices/service/login/';
var httpLogout = '/SimpleWebTemplateWebServices/service/login/logout';
var httpIsLoggedIn = '/SimpleWebTemplateWebServices/service/login/isLoggedIn';

//Pages
var urlMessagePage = '/SimpleWebTemplateAngularJS/app/views/messageView.html'



var MessageApp = angular.module('MessageApp', []);

MessageApp.controller('LoginCtrl', 
		
	function ($scope, $http)
	{
		$scope.errorDialogVisible = "none";
		
		// ------ is user logged in
	    $http.get(httpIsLoggedIn)
	    .success(function(data) {
	    	if(data == "true") {
	        	var elem = document.getElementById('#loginForm');
	        	elem.parentNode.removeChild(elem);
	    	} else {
	        	var elem = document.getElementById('#logoutForm');
	        	elem.parentNode.removeChild(elem);
	    	}
	    }) 
	    .error(function(data, status, headers, config) {
	    	var elem = document.getElementById('#logoutForm');
	    	elem.parentNode.removeChild(elem);
	    });
		
	    
		// ------ login form 
	    $scope.login = function() {
	        $http.post(httpLogin, {'name': $scope.name, 'password': $scope.password}).
	               
	        success(function(data, status, headers, config) {
	        	window.location.href = urlMessagePage;
	        }).
	        
	        error(function(data, status) {
	        	$scope.errorDialogVisible = "visible";    
	        });
	    };
	    
		// ------ logout form 
	    $scope.logout = function() {
	        $http.post(httpLogout, {});
	        document.location.reload(true);      
	    };
	}
);