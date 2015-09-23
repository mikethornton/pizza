angular.module('pizza', [])
.controller('makePizza', function($scope, $http) {
	$http.get('pizzas').success(function(data) {
		$scope.pizzas = data._embedded.pizzas;
	})
	$http.get('toppings').success(function(data) {
		$scope.toppings = data._embedded.toppings;
		var something = '';
	})
	$scope.submit = function(){
		var pizzaSize = $scope.selectedPizza
		var pizzaToppings = $scope.selectedToppings
		var price = pizzaSize.price;
		price += pizzaSize.toppingPrice * pizzaToppings.length;
		$scope.price = Math.round(price * 100) / 100;
	}
});