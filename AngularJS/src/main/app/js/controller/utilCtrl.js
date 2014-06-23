
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
app.run(function ($rootScope, $http) {
	
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
		localStorage.setItem(UserUtil.storageKey, angular.toJson(userDetails,true));
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
		var userDetails = localStorage.getItem(UserUtil.storageKey);
		
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
		localStorage.removeItem(UserUtil.storageKey);
	},

	isUserLoggedIn : function () {
		var userDetails = localStorage.getItem(UserUtil.storageKey);
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
	
	defaultLanguage : 'en',
		
	storageKey : 'userLanguage',

	getLanguage : function() {
		var language = localStorage.getItem(Lang.storageKey);
		
		if(null == language) {
			localStorage.setItem(Lang.storageKey, Lang.defaultLanguage);
			language = localStorage.getItem(Lang.storageKey);
		}

		return language;
	},
	
	get : function(key) {	
		var language = Lang.getLanguage();
		return localizedContent[language][key];
	},
	
	setLanguage : function(key) {		
		localStorage.setItem(Lang.storageKey, key);
	}
}


// own directive to insert html templates 
// e.g. <div html-snippet snippet="languageBarSnippet"></div>
app.directive('htmlSnippet', ['$compile', function($compile) {
    return function(scope, elem, attrs) {
    	
    	if(null != scope.snippet) {
	    	// get snippet string
	    	var html = scope.snippet[attrs.snippet];
	    	
	    	// create an agular element from string and compile
	        var el = angular.element(html),
	        compiled = $compile(el);
	        
	        // append element and view
	        elem.append(el);
	        compiled(scope);

    	} else {
    		throw new Error("No html snippet defined (scope.snippet == null)");
    	}
    };
}]);

