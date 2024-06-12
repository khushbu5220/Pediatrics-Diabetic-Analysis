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
