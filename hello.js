var myapp = angular.module('demo', []);
myapp.controller('Hello', function($scope, $http) {
	
	$scope.home = "This is the home page";
	
	$scope.getMovie = function() {
	
	$http.get('http://localhost:8080/getmovie').
        then(function(response) {
            $scope.movies = response.data;
        });
		
	};
		
		$scope.updateMovies = function(movie){
      //  beer.likes+= likeCount;
	  console.log('---new movie---'+movie.title);
        $http({
              method: 'PUT',
              url: 'http://localhost:8080/addmovie',
              data: JSON.stringify(movie),
          }).then(function successCallback(response) {
                console.log("Updated!");
                //Trigger reload of data
               // $scope.beerSum();
          }, function errorCallback(response) {
			  console.log(response);
                console.log("not updated")
            });
    }; 

});