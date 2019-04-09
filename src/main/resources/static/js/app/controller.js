angular.module('app.controllers', [])
    .controller("controller", ["$scope", "$http",
        function ($scope, $http) {

            $scope.player1 = {};
            $scope.player2 = {};

            $scope.restart = function () {
                $http.post("/restart").success(function (data) {
                    $scope.player1 = data[0];
                    $scope.player2 = data[1];
                });
            };

            $scope.move = function (index) {

                if ($scope.player1.myTurn) {
                    $scope.player1.pitIndex = index;
                } else {
                    $scope.player2.pitIndex = index;
                }

                $http.post("/move", [$scope.player1, $scope.player2]).success(function (data) {
                    $scope.player1 = data[0];
                    $scope.player2 = data[1];
                });
            };

        }]);