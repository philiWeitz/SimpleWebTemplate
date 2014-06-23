
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
	
	// update if user is logged in or not
	UserUtil.updateUserDetails($http);
	
	// add localization function to scope
	$rootScope.LangGet = Lang.get;
	$rootScope.LangCurr = Lang.getLanguage;
	$rootScope.LangSet = Lang.setLanguage;
});	


// available user roles
var UserRole = {
	ADMIN : "ROLE_ADMIN",
	USER : "ROLE_USER"
};


// handles user roles and user is logged in 
var UserUtil = {
	
	timeOutInMin : 10 * 100000, 
	
	storageKey : "userDetails",
	
	storeUserDetails : function (userDetails) {
		userDetails.timestamp = new Date().getTime();
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
		var timeNow = new Date().getTime();
		var userDetails = localStorage.getItem(this.storageKey);
		
		if(null != userDetails) {
			
			var user = angular.fromJson(userDetails);
			
			if((user.timestamp + UserUtil.timeOutInMin) < timeNow) {
				UserUtil.removeUserDetails();
				return null;
			} else {
				return user;
			}
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


// reads a localization key - value pair from "localizedContent"
var Lang = {
	
	language : 'en',
	
	getLanguage : function() {
		return Lang.language;
	},
	
	get : function(key) {		
		return localizedContent[Lang.language][key];
	},
	
	setLanguage : function(key) {		
		Lang.language = key;
	}
}


