
var app = angular.module('homeapp', []);

app.controller('MainCtrl', ['$scope', '$window', function($scope, $window) {
  $scope.memId = $window.memId;
  $scope.templates =
	    [{ name: 'init.html', url: 'init'},
	     { name: 'test.html', url: 'test'}];
  $scope.template = $scope.templates[0];
  
  $scope.getMember = function(id) {
    console.log(id);
  };
  
  $scope.myclick = function(id) {
	$scope.template = $scope.templates[id];
  };
  
  
}]);