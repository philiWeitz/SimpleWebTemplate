
describe("MessageApp", function () {
 
    beforeEach(module('MessageApp'));
 
    describe("MessageCtrl", function () {
 
        var scope, httpBackend, http, controller;
        var getAllMessages = '/SimpleWebTemplateWebServices/service/message/getAll';
        
        beforeEach(inject(function ($rootScope, $controller, $httpBackend, $http) {
            scope = $rootScope.$new();
            httpBackend = $httpBackend;
            http = $http;
            controller = $controller;
            httpBackend.when("GET", getAllMessages).respond("getAllMessagesResult");
        }));
     
        
        it('Add all messages to scope.messages', function () {
            httpBackend.expectGET(getAllMessages);
            controller('MessageCtrl', {
                $scope: scope,
                $http: http
            });
            
            httpBackend.flush();
            
            expect(scope.messages).toBe("getAllMessagesResult");
        });
    });
});




//
//describe('MessageCtrl', function() {
//
//	beforeEach(module('MessageApp'));
//
//	it('should create error message that service is not available', inject(function(
//			$controller) {
//		
//		var scope = {}, http = {}, ctrl = $controller('MessageCtrl', {
//			$scope : scope, $http : http
//		});
//
////		expect(scope.errorStatus).toBe('0');
////		expect(scope.errorMsg).toBe("Couldn't access Message Service");
//	}));
//
//});