
//Services
var httpGetAllMessages = '/SimpleWebTemplateWebServices/service/message/getAll';	


var MessageApp = angular.module('MessageApp', []);

MessageApp.controller('MessageCtrl', 
	
	function ($scope, $http) {
	    $http.get(httpGetAllMessages).
	    
	    success(function(data) {
	    	$scope.messages = data;
	    }) .
	    
	    error(function(data, status, headers, config) {
	    	$scope.errorMsg = "Couldn't access Message Service";
	    	$scope.errorStatus = status;	    	
	    });
	}
);