var app = angular.module('myApp', ['ngRoute', 'ngStorage']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/login', {
            templateUrl: 'login.jsp',
            controller: 'LoginController'
        })
        .when('/home', {
            templateUrl: 'home.jsp',
            // Add HomeController if necessary
        })
        .otherwise({
            redirectTo: '/login'
        });
});


app.factory('AuthService', function($http, $localStorage) {
    var authService = {};

    authService.login = function(credentials) {
        return $http.post('/login', credentials).then(function(response) {
            $localStorage.token = response.data.jwt;
            return response;
        });
    };

    authService.isAuthenticated = function() {
        return !!$localStorage.token;
    };

    return authService;
});

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


app.config(function($httpProvider) {
    $httpProvider.interceptors.push(function($q, $location, $localStorage) {
        return {
            'request': function(config) {
                config.headers = config.headers || {};
                if ($localStorage.token) {
                    config.headers.Authorization = 'Bearer ' + $localStorage.token;
                }
                return config;
            },
            'responseError': function(response) {
                if (response.status === 401 || response.status === 403) {
                    $location.path('/login');
                }
                return $q.reject(response);
            }
        };
    });
});
