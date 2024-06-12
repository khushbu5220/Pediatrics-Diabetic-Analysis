app.controller('LoginController', function($scope, $location, AuthService) {
    $scope.credentials = {};

    $scope.login = function() {
        AuthService.login($scope.credentials).then(function() {
            $location.path('/home');
        }, function(error) {
            $scope.errorMessage = "Login failed: " + (error.data ? error.data.message : "Unknown error");
        });
    };
});
