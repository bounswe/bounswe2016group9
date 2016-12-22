/**
 * Created by serpilkuzu on 17.12.2016.
 */
angular.module('InfoGrappoWeb').controller('SearchCtrl', function ($scope, $log) {

    $scope.search = function () {
        var x = $scope.searchInput;
        $window.localStorage.setItem("searchText", x);
        console.log("Search: " + x);
        $window.location = "index.html";
    };

    $scope.get = function (searchText) {
        // TODO
    }

});