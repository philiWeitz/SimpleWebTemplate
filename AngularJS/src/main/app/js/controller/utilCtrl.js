
// Services
var httpLogin = '/SimpleWebTemplateWebServices/service/login/';
var httpLogout = '/SimpleWebTemplateWebServices/service/login/logout';
var httpIsLoggedIn = '/SimpleWebTemplateWebServices/service/login/isLoggedIn';

var httpSaveMessages = '/SimpleWebTemplateWebServices/service/message/save';	
var httpCreateMessage = '/SimpleWebTemplateWebServices/service/message/create';
var httpDeleteMessages = '/SimpleWebTemplateWebServices/service/message/delete';	
var httpGetAllMessages = '/SimpleWebTemplateWebServices/service/message/getAll';	


// Pages
var urlMessagePage = '/SimpleWebTemplateAngularJS/app/views/messageView.html'


	
// update user login status
MessageApp.run(function ($rootScope, $http) {
	UserUtil.updateUserDetails($http);
});	


// available user roles
var UserRole = {
	ADMIN : "ROLE_ADMIN",
	USER : "ROLE_USER"
};


var UserUtil = {
		
	storageKey : "userDetails",
	
	storeUserDetails : function (userDetails) {
		localStorage.setItem(this.storageKey, angular.toJson(userDetails,true));
	},

	getUserName : function () {
		return UserUtil.getUserData().name;
	},
	
	hasUserRole : function (role) {
		var details = UserUtil.getUserData();
		
		if(null != details && details.roles.indexOf(role) >= 0) {
			return true;
		}		
		return false;
	},
	
	getUserData : function () {
		var userDetails = localStorage.getItem(this.storageKey);
		
		if(null != userDetails) {
			return angular.fromJson(userDetails);
		}
		return null;
	},

	removeUserDetails : function () {
		localStorage.removeItem(this.storageKey);
	},

	isUserLoggedIn : function () {
		var userDetails = localStorage.getItem(this.storageKey);
		return (null != userDetails);
	},
	
	updateUserDetails : function ($http) {
		
		$http.get(httpIsLoggedIn).               
	        
		success(function(data, status, headers, config) {
			UserUtil.storeUserDetails(data);   
		}).
	        
	    error(function(data, status) {
	    	UserUtil.removeUserDetails();
	    });
	}
};
