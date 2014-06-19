
describe("MessageApp", function () {
 
    beforeEach(module('MessageApp'));
 
    describe("MessageCtrl", function () {
 
        var scope, httpBackend, http, controller;

        beforeEach(inject(function ($rootScope, $controller, $httpBackend, $http) {
            scope = $rootScope.$new();
            httpBackend = $httpBackend;
            http = $http;
            controller = $controller;
            httpBackend.when("GET", httpGetAllMessages).respond("getAllMessagesResult");
            httpBackend.when("GET", httpIsLoggedIn).respond("User Logged In");
        }));
     
        
        it('Add all messages to scope.messages', function () {
            httpBackend.expectGET(httpGetAllMessages);
            controller('MessageCtrl', {
                $scope: scope,
                $http: http
            });
            
            httpBackend.flush();
            
            expect(scope.messages).toBe("getAllMessagesResult");
        });
    });
});
