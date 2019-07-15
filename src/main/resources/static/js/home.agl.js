
var app = angular.module('homeapp', []);

app.controller('MainCtrl', ['$scope', '$window', function($scope, $window) {
  $scope.memId = $window.memId;
  $scope.templates =
	    [{ name: 'dashboard.html', url: 'dashboard'},
	     { name: 'test.html', url: 'test'}];
  $scope.template = $scope.templates[0];
  
  $scope.loadpage = function(id) {
   $scope.template = $scope.templates[id];
  };
    
}]);


//dahboard.html
app.controller('DashboardCtrl', ['$scope', '$window', function($scope, $window) {
	 
	  $scope.orders=$window.orders;
	  $scope.notifys=$window.notifys;
	    
}]);

