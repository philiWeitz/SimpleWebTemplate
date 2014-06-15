

var getAllMessages = '/SimpleWebTemplateWebServices/service/message/getAll';	



var MessageApp = angular.module('MessageApp', []);

MessageApp.controller('MessageCtrl', 
	
	function ($scope, $http) {
	    $http.get(getAllMessages).
	    
	    success(function(data) {
	    	$scope.messages = data;
	    }) .
	    
	    error(function(data, status, headers, config) {
	    	$scope.errorMsg = "Couldn't access Message Service";
	    	$scope.errorStatus = status;	    	
	    });
	}

    //  ----- provides data for local development -----
    //	function ($scope) {
    //		$scope.messages = [
    //		    {'id':1,'caption':'Caption 1','text':'Message 1'},
    //		    {'id':2,'caption':'Caption 2','text':'Message 2'}];
    //	}

);
