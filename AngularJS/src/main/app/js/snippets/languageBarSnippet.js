


app.run(function ($rootScope, $http, $compile) {
	
	if(null == $rootScope.snippet) {
		$rootScope.snippet = {};
	}
	
	$rootScope.snippet['languageBarSnippet'] = languageBarSnippet;
});	


var languageBarSnippet = '' +
	'<div class="language-header">' +
		'<div ng-if="LangCurr() != \'en\'">' +
		'	<a href="" ng-click="LangSet(\'en\')">English</a>' +
		'</div>' +
		'		' +
		'<div ng-if="LangCurr() != \'de\'">' +
		'	<a href="" ng-click="LangSet(\'de\')">Deutsch</a>' +
		'</div>' +
	'</div>';