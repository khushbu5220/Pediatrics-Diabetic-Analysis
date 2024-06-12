var app = angular.module('myApp', []).directive('loading', ['$http', function($http) {
	return {
		restrict: 'A',
		template: '<div class="loading-spiner" style="width:100%; margin-top:300px !important; margin:auto;"><center><img style="max-width:80px;" src="https://cfapplication.aiims.edu/research-directory/assets/images/spinner.gif" /></center></div>',
		link: function(scope, elm, attrs) {
			scope.isLoading = function() {
				return $http.pendingRequests.length > 0;
			};

			scope.$watch(scope.isLoading, function(v) {
				if (v) {
					$(".loading-spiner").show();
				} else {
					$(".loading-spiner").hide();
				}
			});
		}
	};
}]);
app.controller('registrationCtrl', function($scope) {
	$scope.showInput = false;
	$scope.showInput1 = false;
	
});
