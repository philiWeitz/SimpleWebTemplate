
//Services
var httpGetAllMessages = '/SimpleWebTemplateWebServices/service/message/getAll';	
var httpLogin = '/SimpleWebTemplateWebServices/service/login/';

//Pages
var urlMessagePage = '/SimpleWebTemplateAngularJS/app/views/messageView.html'


	
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

    //  ----- provides data for local development -----
    //	function ($scope) {
    //		$scope.messages = [
    //		    {'id':1,'caption':'Caption 1','text':'Message 1'},
    //		    {'id':2,'caption':'Caption 2','text':'Message 2'}];
    //	}
);


var LoginController = function ($scope, $http)
{
	$scope.errorDialogVisible = "none";
	
    $scope.login = function() {
        $http.post(httpLogin, {'name': $scope.name, 'password': $scope.password}).
               
        success(function(data, status, headers, config) {
        	window.location.href = urlMessagePage;
        }).
        
        error(function(data, status) {
        	$scope.errorDialogVisible = "visible";    
        });
    };
};